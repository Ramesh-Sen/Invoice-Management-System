package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/update-invoice")
public class UpdateInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateInvoice() {
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

			InvoicePOJO obj = new InvoicePOJO();
//			obj.setDocId(request.getParameter("id"));
//			obj.setTotalOpenAmount(request.getParameter("amount"));
//			obj.setNotes(request.getParameter("notes"));

			obj.setNameCustomer(request.getParameter("name"));
			obj.setCustNumber(request.getParameter("number"));
			obj.setDocId(request.getParameter("id"));
			obj.setTotalOpenAmount(request.getParameter("amount"));
			obj.setDueInDate(request.getParameter("due"));
			obj.setNotes(request.getParameter("notes"));

			System.out.println(obj.getTotalOpenAmount() + " " + obj.getNotes() + " " + obj.getDocId());

//			sql = "UPDATE invoice_details SET total_open_amount = " + obj.getTotalOpenAmount() + ", notes = '"
//					+ obj.getNotes() + "' WHERE doc_id = " + obj.getDocId() + "";

			sql = "UPDATE invoice_details  SET name_customer = '" + obj.getNameCustomer() + "', cust_number = "
					+ obj.getCustNumber() + ", due_in_date = '" + obj.getDueInDate() + "', total_open_amount = "
					+ obj.getTotalOpenAmount() + ", notes = '" + obj.getNotes() + "' WHERE doc_id = " + obj.getDocId()
					+ "";

			stmt.executeUpdate(sql);
			System.out.println("Invoice Updated");

			Gson gson = new Gson();
			String data = gson.toJson(obj);

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
