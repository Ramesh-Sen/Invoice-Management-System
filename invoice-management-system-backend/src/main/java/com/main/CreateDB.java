package com.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateDB {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/invoice_db";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		String line = "";

		try {
			// Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// Execute a query
			stmt = conn.createStatement();
			String sql;

			// Creating DB
//			sql = "CREATE DATABASE IF NOT EXISTS invoice_db;";
//			stmt.addBatch(sql);

			// Using DB
//			sql = "USE invoice_temp;";
//			stmt.addBatch(sql);

			// Creating Table
			sql = "CREATE TABLE IF NOT EXISTS invoice_details (business_code CHAR(4) DEFAULT NULL,  cust_number VARCHAR(255) DEFAULT NULL,  name_customer VARCHAR(255) DEFAULT NULL,  clear_date DATETIME DEFAULT NULL,  business_year YEAR DEFAULT NULL,  doc_id BIGINT NOT NULL,  posting_date DATE DEFAULT NULL,  document_create_date DATE DEFAULT NULL,  document_create_date_1 DATE DEFAULT NULL,  due_in_date DATE DEFAULT NULL,  invoice_currency CHAR(3) DEFAULT NULL,  document_type CHAR(2) DEFAULT NULL,  posting_id TINYINT DEFAULT NULL,  area_business VARCHAR(255) DEFAULT NULL,  total_open_amount DOUBLE DEFAULT NULL,  baseline_create_date DATE DEFAULT NULL,  cust_payment_terms CHAR(4) DEFAULT NULL,  invoice_id BIGINT DEFAULT NULL,  isOpen TINYINT DEFAULT 0, notes VARCHAR(255) DEFAULT NULL,  PRIMARY KEY (doc_id))";
			stmt.addBatch(sql);
			stmt.executeBatch();

			List<InvoicePOJO> data = new ArrayList<>();

			System.out.println("Reading from csv");
			BufferedReader br = new BufferedReader(
					new FileReader("C:\\\\MY STUFF\\\\Invoice Management System\\\\1806597.csv"));
			br.readLine();
			while ((line = br.readLine()) != null) {

				InvoicePOJO obj = new InvoicePOJO();

				// use comma as separator
				String[] col = line.split(",");

				// Setting values in respective columns
				obj.setBusinessCode(col[0]);
				obj.setCustNumber(col[1]);
				obj.setNameCustomer(col[2]);
				obj.setClearDate(col[3]);
				obj.setBusinessYear(col[4]);
				obj.setDocId(col[5]);
				obj.setPostingDate(col[6]);
				obj.setDocumentCreateDate(col[7]);
				obj.setDocumentCreateDate1(col[8]);
				obj.setDueInDate(col[9]);
				obj.setInvoiceCurrency(col[10]);
				obj.setDocumentType(col[11]);
				obj.setPostingId(col[12]);
				obj.setAreaBusiness(col[13]);
				obj.setTotalOpenAmount(col[14]);
				obj.setBaselineCreateDate(col[15]);
				obj.setCustPaymentTerms(col[16]);
				obj.setInvoiceId(col[17]);
				obj.setIsOpen(col[18]);
				obj.setNotes(null);

				data.add(obj);
			}

			System.out.println("Reading from csv completed");
			System.out.println("Inserting data into DB");
			for (int i = 0; i < data.size(); i++) {
				sql = "INSERT INTO invoice_details VALUES (" + "'" + data.get(i).getBusinessCode() + "', " + "'"
						+ data.get(i).getCustNumber() + "', " + "'" + data.get(i).getNameCustomer() + "', ";

				if (data.get(i).getClearDate() == "NULL") {
					sql += "NULL , ";
				} else {
					sql += "'" + data.get(i).getClearDate() + "', ";
				}

				sql += data.get(i).getBusinessYear() + ", " + data.get(i).getDocId() + ", " + "'"
						+ data.get(i).getPostingDate() + "', " + "'" + data.get(i).getDocumentCreateDate() + "', " + "'"
						+ data.get(i).getDocumentCreateDate1() + "', " + "'" + data.get(i).getDueInDate() + "', " + "'"
						+ data.get(i).getInvoiceCurrency() + "', " + "'" + data.get(i).getDocumentType() + "', "
						+ data.get(i).getPostingId() + ", " + "'" + data.get(i).getAreaBusiness() + "', "
						+ data.get(i).getTotalOpenAmount() + ", " + "'" + data.get(i).getBaselineCreateDate() + "', "
						+ "'" + data.get(i).getCustPaymentTerms() + "', " + data.get(i).getInvoiceId() + ", "
						+ data.get(i).getIsOpen() + ", NULL " + ")";

				stmt.addBatch(sql);
			}

			stmt.executeBatch();
			System.out.println("Inserting data into DB Completed");

			br.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
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

}
