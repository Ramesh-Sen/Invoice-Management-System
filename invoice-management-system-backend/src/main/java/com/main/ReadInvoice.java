package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/read-invoice")
public class ReadInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadInvoice() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;

		try {
			String dbDriver = "com.mysql.cj.jdbc.Driver";
			String dbURL = "jdbc:mysql://localhost/invoice_db";
			String dbUsername = "root";
			String dbPassword = "root";
			int page_count = 0;
			int no_of_rows = 10;

			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			stmt = conn.createStatement();

			if (request.getParameter("pageCount") == null) {
				page_count = 0;
			} else {
				page_count = Integer.parseInt(request.getParameter("pageCount"));
			}

			System.out.println("conn.got");
			String sql;

			sql = "SELECT * FROM invoice_details WHERE isOpen = 0 Order BY document_create_date LIMIT "
					+ (page_count * no_of_rows) + ", " + (no_of_rows) + " ";

			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Invoice Read");
			ArrayList<InvoicePOJO> result = new ArrayList<InvoicePOJO>();

			while (rs.next()) {
				InvoicePOJO obj = new InvoicePOJO();
				obj.setNameCustomer(rs.getString("name_customer"));
				obj.setCustNumber(rs.getString("cust_number"));
				obj.setDocId(rs.getString("doc_id"));
				obj.setTotalOpenAmount(rs.getString("total_open_amount"));
				obj.setDueInDate(rs.getString("due_in_date"));
				obj.setNotes(rs.getString("notes"));

				result.add(obj);
			}
			Gson gson = new Gson();
			String data = gson.toJson(result);

			response.getWriter().print(data);
			response.setStatus(200);
			response.setContentType("application/json");
			response.setCharacterEncoding("charset=utf-8");

		} catch (ClassNotFoundException e) {
			response.setStatus(400);
			response.getWriter().append("Error");
			e.printStackTrace();
		} catch (SQLException e) {
			response.setStatus(400);
			response.getWriter().append("Error");
			e.printStackTrace();
		} catch (Exception e) {
			response.setStatus(400);
			response.getWriter().append("Error");
			e.printStackTrace();
		}

		finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
