package com.poly.entity;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class OrderExcelExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Order> listOrder;
	
	public OrderExcelExcelExporter(List<Order> listOrder) {
		this.listOrder = listOrder;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Order");
	}

	private void writeHeaderRow() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("Order ID");
		
		cell = row.createCell(1);
		cell.setCellValue("Date");

		cell = row.createCell(2);
		cell.setCellValue("Phone");
		
		cell = row.createCell(3);
		cell.setCellValue("Address");
		
		cell = row.createCell(4);
		cell.setCellValue("TotalAmount");
	}

	private void writeDataRows() {
		int rowCount = 1;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		for(Order order : listOrder) {
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(order.getId());
			
			cell = row.createCell(1);
	        cell.setCellValue(dateFormat.format(order.getCreateDate()));
			
			cell = row.createCell(2);
			cell.setCellValue(order.getDelivery_phone());
			
			cell = row.createCell(3);
			cell.setCellValue(order.getAddress());
			
			cell = row.createCell(4);
			cell.setCellValue(order.getTotalAmount());
		}
	}
	
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderRow();
		writeDataRows();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
