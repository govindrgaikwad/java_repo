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

import com.tmg.equinox.view.formdesign.FormDesignVersionChangeTracker;
import com.tmg.uifwk.orm.BaseEntityAttributes;
import com.tmg.uifwk.orm.ui.FormDesign;
import com.tmg.uifwk.orm.ui.FormDesignVersion;
import com.tmg.uifwk.orm.ui.Status;

@Component("FormDesignVersionProcess")
public class FormDesignVersionProcess {

	private Workbook excelWorkBook;

	public void printHeaders() {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row formVersionHeaderRow = sheet.createRow(2);
		List<String> columnNames = new ArrayList<String>();

		columnNames.add("Operation");
		columnNames.add("Type");

		for (Field field : FormDesignVersion.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				if (!column.name().equals("FormDesignVersionID")) {
					columnNames.add(column.name());
				}
			}
		}

		for (Field field : Status.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				if (column.name().equals("StatusID")
						|| column.name().equals("Status")) {
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

			formVersionHeaderRow.createCell(startCell).setCellValue(
					columnNames.get(i));
			startCell++;
		}

	}

	public void exportFormDesignVersionToExcelSheet(
			FormDesignVersionChangeTracker formDesignVersionChangeTracker)
			throws Exception {

		Sheet sheet = excelWorkBook.getSheetAt(0);

		Row formDesignVersionDataRow = sheet.createRow(3);

		formDesignVersionDataRow.createCell(0).setCellValue(
				formDesignVersionChangeTracker.getAction());
		formDesignVersionDataRow.createCell(1).setCellValue(
				formDesignVersionChangeTracker.getFormDesignVersion()
						.getClass().getSimpleName());

		if (formDesignVersionChangeTracker.getFormDesignVersion().getTenantID() != null) {
			formDesignVersionDataRow.createCell(2).setCellValue(
					formDesignVersionChangeTracker.getFormDesignVersion()
							.getTenantID());
		}

		formDesignVersionDataRow.createCell(3).setCellValue(
				formDesignVersionChangeTracker.getFormDesignVersion()
						.getVersionNumber());

		formDesignVersionDataRow.createCell(4).setCellValue(
				formDesignVersionChangeTracker.getFormDesignVersion()
						.getEffectiveDate());
		formDesignVersionDataRow.createCell(5).setCellValue(
				formDesignVersionChangeTracker.getFormDesignVersion()
						.getFormDesignVersionData());
		formDesignVersionDataRow.createCell(6).setCellValue(
				formDesignVersionChangeTracker.getFormDesignVersion()
						.getStatus().getStatusID());
		formDesignVersionDataRow.createCell(7).setCellValue(
				formDesignVersionChangeTracker.getFormDesignVersion()
						.getStatus().getStatus());
		formDesignVersionDataRow.createCell(8).setCellValue(
				formDesignVersionChangeTracker.getFormDesignVersion()
						.getAddedBy());

		formDesignVersionDataRow.createCell(9).setCellValue(
				formDesignVersionChangeTracker.getFormDesignVersion()
						.getAddedDate());

		if (formDesignVersionChangeTracker.getFormDesignVersion()
				.getUpdatedBy() != null) {
			formDesignVersionDataRow.createCell(10).setCellValue(
					formDesignVersionChangeTracker.getFormDesignVersion()
							.getUpdatedBy());
		}
		if (formDesignVersionChangeTracker.getFormDesignVersion()
				.getUpdatedDate() != null) {
			formDesignVersionDataRow.createCell(11).setCellValue(
					formDesignVersionChangeTracker.getFormDesignVersion()
							.getUpdatedDate());
		}

	}

	public void importFormDesignVersionFromExcelSheet(
			FormDesignVersion currentFormDesignVersion, FormDesign formDesign,
			Status status) {

		Sheet sheet = excelWorkBook.getSheetAt(0);

		Row formDesignVersionRow = sheet.getRow(3);

		currentFormDesignVersion.setFormDesign(formDesign);
		if (formDesignVersionRow.getCell(2) != null) {
			currentFormDesignVersion.setTenantID((int) formDesignVersionRow
					.getCell(2).getNumericCellValue());
		} else {
			currentFormDesignVersion.setTenantID(null);
		}

		currentFormDesignVersion.setVersionNumber(formDesignVersionRow.getCell(
				3).getStringCellValue());

		currentFormDesignVersion.setEffectiveDate(new Timestamp(
				formDesignVersionRow.getCell(4).getDateCellValue().getTime()));

		currentFormDesignVersion.setFormDesignVersionData(formDesignVersionRow
				.getCell(5).getStringCellValue());

		currentFormDesignVersion.setStatus(status);

		currentFormDesignVersion.setAddedBy(formDesignVersionRow.getCell(8)
				.getStringCellValue());

		currentFormDesignVersion.setAddedDate(new Timestamp(
				formDesignVersionRow.getCell(9).getDateCellValue().getTime()));

		if (formDesignVersionRow.getCell(10) != null) {
			currentFormDesignVersion.setUpdatedBy(formDesignVersionRow.getCell(
					10).getStringCellValue());
		} else {
			currentFormDesignVersion.setUpdatedBy(null);
		}
		if (formDesignVersionRow.getCell(11) != null) {
			currentFormDesignVersion.setUpdatedDate(new Timestamp(
					formDesignVersionRow.getCell(11).getDateCellValue()
							.getTime()));
		} else {
			currentFormDesignVersion.setUpdatedDate(null);
		}

		// if (currentFormDesignVersion.getFormDesignVersionID() != null
		// && currentFormDesignVersion.getFormDesignVersionID() > 0) {
		// // currentFormDesignVersion.setUpdatedBy("");
		// currentFormDesignVersion.setUpdatedDate(new Timestamp(new Date()
		// .getTime()));
		// } else {
		// // currentFormDesignVersion.setAddedBy("");
		// currentFormDesignVersion.setAddedDate(new Timestamp(new Date()
		// .getTime()));
		// }

	}

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

}
