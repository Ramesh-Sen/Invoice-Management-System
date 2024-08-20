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

@WebServlet("/delete-invoice")
public class DeleteInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteInvoice() {
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
			obj.setDocId(request.getParameter("id"));

//			For HARD DELETE
//			sql = "DELETE FROM invoice_details WHERE doc_id = " + obj.getDocId();

//			For SOFT DELETE
			sql = "UPDATE invoice_details SET isOpen = 1 WHERE doc_id = " + obj.getDocId() + "";

			stmt.execute(sql);
			System.out.println("Invoice Deleted");

			response.getWriter().print("Deleted Invoice Id is " + obj.getDocId());
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
