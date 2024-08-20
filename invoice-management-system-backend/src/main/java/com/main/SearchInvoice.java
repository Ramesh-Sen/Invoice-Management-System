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

@WebServlet("/search-invoice")
public class SearchInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchInvoice() {
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

			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			stmt = conn.createStatement();
			String sql;

			System.out.println("In search: " + request.getParameter("id"));

			sql = "SELECT * FROM invoice_details WHERE doc_id LIKE '" + request.getParameter("id")
					+ "%' ORDER BY document_create_date LIMIT 0, 10";

			ResultSet rs = stmt.executeQuery(sql);

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

			System.out.println("Sending Data : " + data);

			response.getWriter().print(data);
			response.setStatus(200);

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

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}

}
