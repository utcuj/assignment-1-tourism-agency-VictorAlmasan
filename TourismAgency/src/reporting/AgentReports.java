package reporting;

import java.io.*;
import java.util.*;
import java.sql.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import db.ConnectionFactory;

public class AgentReports {

	public static void reports() {
		 Connection conn = ConnectionFactory.getConnection();
		 Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         ResultSet query_set = null;
		try {
			query_set = stmt.executeQuery("SELECT l.log_id, l.user_id, l.operation_type_code, l.log_date, op.operation_type_code, op.operation_type_description " + 
					"FROM logging l " + 
					"INNER JOIN operation_types op ON l.operation_type_code = op.operation_type_code;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         Document my_pdf_report = new Document();
         try {
			PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         my_pdf_report.open();            
         //we have four columns in our table
         PdfPTable my_report_table = new PdfPTable(4);
         //create a cell object
         PdfPCell table_cell;
        
         try {
			while (query_set.next()) {                
			                 String logId = query_set.getString("l.log_id");
			                 table_cell = new PdfPCell(new Phrase(logId));
			                 my_report_table.addCell(table_cell);
			                 String userId=query_set.getString("l.user_id");
			                 table_cell=new PdfPCell(new Phrase(userId));
			                 my_report_table.addCell(table_cell);
			                 String logDate=query_set.getString("l.log_date");
			                 table_cell=new PdfPCell(new Phrase(logDate));
			                 my_report_table.addCell(table_cell);
			                 String opDescription=query_set.getString("op.operation_type_description");
			                 table_cell=new PdfPCell(new Phrase(opDescription));
			                 my_report_table.addCell(table_cell);
			                 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         try {
			my_pdf_report.add(my_report_table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                       
         my_pdf_report.close();
         
         
         
         try {
        	 query_set.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
                        
	}
	


}

