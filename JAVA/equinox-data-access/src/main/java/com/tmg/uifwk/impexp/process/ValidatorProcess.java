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

import com.tmg.equinox.view.formdesign.ValidatorChangeTraker;
import com.tmg.uifwk.orm.BaseEntityAttributes;
import com.tmg.uifwk.orm.ui.RegexLibrary;
import com.tmg.uifwk.orm.ui.UIElement;
import com.tmg.uifwk.orm.ui.Validator;

@Component("ValidatorProcess")
public class ValidatorProcess {

	private Workbook excelWorkBook;

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

	public void printHeaders(int rowNum) {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row validatorsHeaderRow = sheet.createRow(rowNum);
		List<String> columnNames = new ArrayList<String>();

		columnNames.add("Operation");
		columnNames.add("Type");
		columnNames.add("UIElementName");

		for (Field field : Validator.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {

				columnNames.add(column.name());

			}
		}

		for (Field field : RegexLibrary.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {

				if (column.name().equals("LibraryRegexID")
						|| column.name().equals("LibraryRegexName")) {
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

			validatorsHeaderRow.createCell(startCell).setCellValue(
					columnNames.get(i));
			startCell++;
		}

	}

	public void exportValidatorToExcelSheet(
			List<ValidatorChangeTraker> validators, int curRow) {

		Sheet sheet = excelWorkBook.getSheetAt(0);

		int rowNum = curRow;
		for (ValidatorChangeTraker validator : validators) {
			Row validatorRow = sheet.createRow(rowNum);
			validatorRow.createCell(2).setCellValue(
					validator.getValidator().getUielement().getUIElementName());
			validatorRow.createCell(3).setCellValue(
					validator.getValidator().getValidatorID());
			validatorRow.createCell(4).setCellValue(
					validator.getValidator().getRequired());
			if (validator.getValidator().getRegex() != null) {
				validatorRow.createCell(5).setCellValue(
						validator.getValidator().getRegex());
			}
			if (validator.getValidator().getLibraryRegex() != null) {
				validatorRow.createCell(6).setCellValue(
						validator.getValidator().getLibraryRegex());
			}

			validatorRow.createCell(7).setCellValue(
					validator.getValidator().getActive());

			if (validator.getValidator().getMessage() != null) {
				validatorRow.createCell(8).setCellValue(
						validator.getValidator().getMessage());
			}

			if (validator.getValidator().getRegexLibrary() != null) {
				validatorRow.createCell(9).setCellValue(
						validator.getValidator().getRegexLibrary()
								.getLibraryRegexID());

				validatorRow.createCell(10).setCellValue(
						validator.getValidator().getRegexLibrary()
								.getLibraryRegexName());
			}

			validatorRow.createCell(11).setCellValue(
					validator.getValidator().getAddedBy());

			validatorRow.createCell(12).setCellValue(
					validator.getValidator().getAddedDate());

			if (validator.getValidator().getUpdatedBy() != null) {
				validatorRow.createCell(13).setCellValue(
						validator.getValidator().getUpdatedBy());
			}
			if (validator.getValidator().getUpdatedDate() != null) {
				validatorRow.createCell(14).setCellValue(
						validator.getValidator().getUpdatedDate());
			}

			rowNum++;

		}

	}

	public void importValidatorFromExcelSheet(Validator validator,
			UIElement uiElement, RegexLibrary regexLibrary, int validatorRow) {
		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row validatorDataRow = sheet.getRow(validatorRow);

		validator.setUielement(uiElement);
		validator
				.setRequired(validatorDataRow.getCell(4).getBooleanCellValue());
		if (validatorDataRow.getCell(5) != null) {
			validator
					.setRegex(validatorDataRow.getCell(5).getStringCellValue());
		} else {
			validator.setRegex(null);
		}

		if (validatorDataRow.getCell(6) != null) {
			validator.setLibraryRegex(validatorDataRow.getCell(6)
					.getBooleanCellValue());
		} else {
			validator.setRegex(null);
		}
		validator.setRegexLibrary(regexLibrary);

		validator.setActive(validatorDataRow.getCell(7).getBooleanCellValue());
		if (validatorDataRow.getCell(0) != null) {
			validator.setMessage(validatorDataRow.getCell(7)
					.getStringCellValue());
		} else {
			validator.setMessage(null);
		}

		validator.setAddedBy(validatorDataRow.getCell(11).getStringCellValue());
		validator.setAddedDate(new Timestamp(validatorDataRow.getCell(12)
				.getDateCellValue().getTime()));

		if (validatorDataRow.getCell(13) != null) {
			validator.setUpdatedBy(validatorDataRow.getCell(13)
					.getStringCellValue());
		} else {
			validator.setUpdatedBy(null);
		}
		if (validatorDataRow.getCell(14) != null) {
			validator.setUpdatedDate(new Timestamp(validatorDataRow.getCell(14)
					.getDateCellValue().getTime()));
		} else {
			validator.setUpdatedDate(null);
		}

	}
}
