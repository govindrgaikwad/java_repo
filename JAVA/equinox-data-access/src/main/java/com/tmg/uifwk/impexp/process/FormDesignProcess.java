package com.tmg.uifwk.impexp.process;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.tmg.equinox.view.formdesign.FormDesignChangeTracker;
import com.tmg.uifwk.orm.BaseEntityAttributes;
import com.tmg.uifwk.orm.ui.FormDesign;

@Component("FormDesignProcess")
public class FormDesignProcess {

	private Workbook excelWorkBook;

	public void printHeaders() {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row formHeaderRow = sheet.createRow(0);
		List<String> columnNames = new ArrayList<String>();

		columnNames.add("Operation");
		columnNames.add("Type");

		for (Field field : FormDesign.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {

				if (!column.name().equals("FormID")) {
					columnNames.add(column.name());
				}
			}
		}

		for (Field field : BaseEntityAttributes.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columnNames.add(column.name());
			}
		}

		int startCell = 0;
		for (int i = 0; i < columnNames.size(); i++) {

			formHeaderRow.createCell(startCell)
					.setCellValue(columnNames.get(i));
			startCell++;
		}

	}

	public void exportFormDesignToExcelSheet(
			FormDesignChangeTracker formDesignChangeTracker) throws Exception {

		Sheet sheet = excelWorkBook.getSheetAt(0);

		Row formDataRow = sheet.createRow(1);

		formDataRow.createCell(0).setCellValue(
				formDesignChangeTracker.getAction());
		formDataRow.createCell(1).setCellValue(
				formDesignChangeTracker.getFormDesign().getClass()
						.getSimpleName());
		formDataRow.createCell(2).setCellValue(
				formDesignChangeTracker.getFormDesign().getFormName());
		formDataRow.createCell(3).setCellValue(
				formDesignChangeTracker.getFormDesign().getDisplayText());
		formDataRow.createCell(4).setCellValue(
				formDesignChangeTracker.getFormDesign().isActive());
		formDataRow.createCell(5).setCellValue(
				formDesignChangeTracker.getFormDesign().getAbbreviation());
		formDataRow.createCell(6).setCellValue(
				formDesignChangeTracker.getFormDesign().getTenantID());
		formDataRow.createCell(7).setCellValue(
				formDesignChangeTracker.getFormDesign().getAddedBy());
		if (formDesignChangeTracker.getFormDesign().getAddedDate() != null) {
			formDataRow.createCell(8).setCellValue(
					formDesignChangeTracker.getFormDesign().getAddedDate());
		}
		if (formDesignChangeTracker.getFormDesign().getUpdatedBy() != null) {
			formDataRow.createCell(9).setCellValue(
					formDesignChangeTracker.getFormDesign().getUpdatedBy());
		}
		if (formDesignChangeTracker.getFormDesign().getUpdatedDate() != null) {
			formDataRow.createCell(10).setCellValue(
					formDesignChangeTracker.getFormDesign().getUpdatedDate());
		}

	}

	public void importFormDesignFromExcelSheet(FormDesign currentForm)
			throws Exception {

		Sheet sheet = excelWorkBook.getSheetAt(0);

		Row formDesignDataRow = sheet.getRow(1);

		currentForm.setFormName(formDesignDataRow.getCell(2)
				.getStringCellValue());

		currentForm.setDisplayText(formDesignDataRow.getCell(3)
				.getStringCellValue());

		currentForm.setActive(formDesignDataRow.getCell(4)
				.getBooleanCellValue());

		currentForm.setAbbreviation(formDesignDataRow.getCell(5)
				.getStringCellValue());
		currentForm.setTenantID((int) formDesignDataRow.getCell(6)
				.getNumericCellValue());

		currentForm.setAddedBy(formDesignDataRow.getCell(7)
				.getStringCellValue());
		currentForm.setAddedDate(new Timestamp(formDesignDataRow.getCell(8)
				.getDateCellValue().getTime()));

		if (formDesignDataRow.getCell(9) != null) {
			currentForm.setUpdatedBy(formDesignDataRow.getCell(9)
					.getStringCellValue());
		} else {
			currentForm.setUpdatedBy(null);
		}
		if (formDesignDataRow.getCell(10) != null) {
			currentForm.setUpdatedDate(new Timestamp(formDesignDataRow
					.getCell(10).getDateCellValue().getTime()));
		} else {
			currentForm.setUpdatedDate(null);
		}

		// if (currentForm.getFormID() != null && currentForm.getFormID() > 0) {
		// // currentForm.setUpdatedBy("");
		// currentForm.setUpdatedDate(new Timestamp(new Date().getTime()));
		//
		// } else {
		// // currentForm.setAddedBy("");
		// currentForm.setAddedDate(new Timestamp(new Date().getTime()));
		// }

	}

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

}
