package com.tmg.uifwk.impexp.process;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.uifwk.orm.ui.CheckBoxUIElement;
import com.tmg.uifwk.orm.ui.UIElementType;

@Component("CheckBoxUIElementProcess")
public class CheckBoxUIElementProcess {

	private Workbook excelWorkBook;

	@Autowired
	private GenericUIElementProcess genericUIElementProcess;

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

	public void exportCheckBoxUIElementToExcelSheet(
			UIElementChangeTracker changedElement,
			CheckBoxUIElement checkBoxUIElement, int curRow) throws Exception {
		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row currentRow = sheet.createRow(curRow);

		genericUIElementProcess.exportUIElementToExcelSheet(changedElement,
				currentRow);

		currentRow.createCell(24).setCellValue(
				checkBoxUIElement.getUielementType().getUIElementTypeID());
		currentRow.createCell(25).setCellValue(
				checkBoxUIElement.getUielementType().getDisplayText());

		if (checkBoxUIElement.getDefaultValue() != null) {
			currentRow.createCell(39).setCellValue(
					checkBoxUIElement.getDefaultValue());
		}
		if (checkBoxUIElement.getOptionLabel() != null) {
			currentRow.createCell(38).setCellValue(
					checkBoxUIElement.getOptionLabel());

		}

	}

	public void importCheckBoxUIElementFromExcelSheet(
			CheckBoxUIElement checkBoxUIElement, int curRow,
			UIElementType uiElementType) {
		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row uiElementRow = sheet.getRow(curRow);

		checkBoxUIElement.setUielementType(uiElementType);

		if (uiElementRow.getCell(38) != null) {
			checkBoxUIElement.setOptionLabel(uiElementRow.getCell(38)
					.getStringCellValue());
		} else {
			checkBoxUIElement.setOptionLabel(null);
		}

		if (uiElementRow.getCell(39) != null) {
			checkBoxUIElement.setDefaultValue(uiElementRow.getCell(39)
					.getBooleanCellValue());
		} else {
			checkBoxUIElement.setDefaultValue(null);
		}

	}
}
