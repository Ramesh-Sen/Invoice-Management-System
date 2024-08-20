package com.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/add-invoice")
public class AddInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddInvoice() {
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

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);
			System.out.println("Current date: " + strDate);

			ArrayList<InvoicePOJO> ob = new ArrayList<InvoicePOJO>();
			InvoicePOJO obj = new InvoicePOJO();

			obj.setNameCustomer(request.getParameter("name"));
			obj.setCustNumber(request.getParameter("number"));
			obj.setDocId(request.getParameter("id"));
			obj.setTotalOpenAmount(request.getParameter("amount"));
			obj.setDueInDate(request.getParameter("due"));
			obj.setNotes(request.getParameter("notes"));
			obj.setDocumentCreateDate(strDate);

			ob.add(obj);
			String sql;
			for (int i = 0; i < ob.size(); i++) {
				sql = "INSERT INTO `invoice_details`(name_customer, cust_number, doc_id, total_open_amount, due_in_date, notes, document_create_date) VALUES ( "
						+ "'" + ob.get(i).getNameCustomer() + "', " + "'" + ob.get(i).getCustNumber() + "', " + "'"
						+ ob.get(i).getDocId() + "', " + "'" + ob.get(i).getTotalOpenAmount() + "', " + "'"
						+ ob.get(i).getDueInDate() + "', " + "'" + ob.get(i).getNotes() + "', " + "'" + strDate + "')";

				stmt.addBatch(sql);
			}

			stmt.executeBatch();
			System.out.println("Invoice Added");

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
