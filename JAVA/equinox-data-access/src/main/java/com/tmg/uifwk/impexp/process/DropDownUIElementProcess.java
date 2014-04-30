package com.tmg.uifwk.impexp.process;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.uifwk.orm.ui.DropDownUIElement;
import com.tmg.uifwk.orm.ui.UIElementType;

@Component("DropDownUIElementProcess")
public class DropDownUIElementProcess {

	private Workbook excelWorkBook;

	@Autowired
	private GenericUIElementProcess genericUIElementProcess;

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

	public void exportDropDownUIElementToExcelSheet(
			UIElementChangeTracker changedElement,
			DropDownUIElement dropDownUIElement, int curRow) throws Exception {
		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row currentRow = sheet.createRow(curRow);

		genericUIElementProcess.exportUIElementToExcelSheet(changedElement,
				currentRow);

		currentRow.createCell(24).setCellValue(
				dropDownUIElement.getUielementType().getUIElementTypeID());
		currentRow.createCell(25).setCellValue(
				dropDownUIElement.getUielementType().getDisplayText());

		if (dropDownUIElement.getSelectedValue() != null) {
			currentRow.createCell(40).setCellValue(
					dropDownUIElement.getSelectedValue());
		}

		currentRow.createCell(41).setCellValue(
				dropDownUIElement.isMultiSelect());

	}

	public void importDropDownUIElementFromExcelSheet(
			DropDownUIElement dropDownUIElement, int curRow,
			UIElementType uiElementType) {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row uiElementRow = sheet.createRow(curRow);

		dropDownUIElement.setUielementType(uiElementType);

		if (uiElementRow.getCell(40) != null) {
			dropDownUIElement.setSelectedValue(uiElementRow.getCell(40)
					.getStringCellValue());
		} else {
			dropDownUIElement.setSelectedValue(null);
		}

		dropDownUIElement.setMultiSelect(uiElementRow.getCell(41)
				.getBooleanCellValue());

	}
}
