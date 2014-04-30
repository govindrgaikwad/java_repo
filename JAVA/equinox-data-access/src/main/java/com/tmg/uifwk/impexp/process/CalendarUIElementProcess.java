package com.tmg.uifwk.impexp.process;

import java.sql.Timestamp;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.uifwk.orm.ui.CalendarUIElement;
import com.tmg.uifwk.orm.ui.UIElementType;

@Component("CalendarUIElementProcess")
public class CalendarUIElementProcess {

	private Workbook excelWorkBook;

	@Autowired
	private GenericUIElementProcess genericUIElementProcess;

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

	public void exportCalendarUIElementToExcelSheet(
			UIElementChangeTracker changedElement,
			CalendarUIElement calendarUIElement, int curRow) throws Exception {
		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row currentRow = sheet.createRow(curRow);

		genericUIElementProcess.exportUIElementToExcelSheet(changedElement,
				currentRow);

		currentRow.createCell(24).setCellValue(
				calendarUIElement.getUielementType().getUIElementTypeID());
		currentRow.createCell(25).setCellValue(
				calendarUIElement.getUielementType().getDisplayText());

		if (calendarUIElement.getMinDate() != null) {
			currentRow.createCell(35).setCellValue(
					calendarUIElement.getMinDate());
		}
		if (calendarUIElement.getMaxDate() != null) {
			currentRow.createCell(36).setCellValue(
					calendarUIElement.getMaxDate());
		}
		if (calendarUIElement.getDefaultDate() != null) {
			currentRow.createCell(37).setCellValue(
					calendarUIElement.getDefaultDate());
		}

	}

	public void importCalendarUIElementFromExcelSheet(
			CalendarUIElement calendarUIElement, int curRow,
			UIElementType uiElementType) {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row uiElementRow = sheet.getRow(curRow);

		calendarUIElement.setUielementType(uiElementType);

		if (uiElementRow.getCell(35) != null) {
			calendarUIElement.setMinDate(new Timestamp(uiElementRow.getCell(35)
					.getDateCellValue().getTime()));
		} else {
			calendarUIElement.setMinDate(null);
		}

		if (uiElementRow.getCell(36) != null) {
			calendarUIElement.setMaxDate(new Timestamp(uiElementRow.getCell(36)
					.getDateCellValue().getTime()));
		} else {
			calendarUIElement.setMaxDate(null);
		}

		if (uiElementRow.getCell(37) != null) {
			calendarUIElement.setDefaultDate(new Timestamp(uiElementRow.getCell(37)
					.getDateCellValue().getTime()));
		} else {
			calendarUIElement.setDefaultDate(null);
		}

	}

}
