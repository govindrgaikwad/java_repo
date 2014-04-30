package com.tmg.uifwk.impexp.process;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.uifwk.orm.ui.TextBoxUIElement;
import com.tmg.uifwk.orm.ui.UIElementType;

@Component("TextBoxUIElementProcess")
public class TextBoxUIElementProcess {

	private Workbook excelWorkBook;

	@Autowired
	private GenericUIElementProcess genericUIElementProcess;

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

	public void exportTextBoxUIElementToExcelSheet(
			UIElementChangeTracker changedElement,
			TextBoxUIElement textBoxUIElement, int curRow) throws Exception {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row currentRow = sheet.createRow(curRow);

		genericUIElementProcess.exportUIElementToExcelSheet(changedElement,
				currentRow);

		currentRow.createCell(24).setCellValue(
				textBoxUIElement.getUielementType().getUIElementTypeID());
		currentRow.createCell(25).setCellValue(
				textBoxUIElement.getUielementType().getDisplayText());
		currentRow.createCell(30).setCellValue(textBoxUIElement.isMultiline());

		if (textBoxUIElement.getDefaultValue() != null) {
			currentRow.createCell(31).setCellValue(
					textBoxUIElement.getDefaultValue());
		}
		currentRow.createCell(32).setCellValue(textBoxUIElement.getMaxLength());
		currentRow.createCell(33).setCellValue(textBoxUIElement.isLabel());
		currentRow.createCell(34)
				.setCellValue(textBoxUIElement.getSpellCheck());

	}

	public void importTextBoxUIElementFromExcelSheet(
			TextBoxUIElement textBoxUIElement, int uiElementRow,
			UIElementType uiElementType) {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row currentRow = sheet.createRow(uiElementRow);

		textBoxUIElement.setUielementType(uiElementType);
		textBoxUIElement.setMultiline(currentRow.getCell(30)
				.getBooleanCellValue());

		if (currentRow.getCell(31) != null) {
			textBoxUIElement.setDefaultValue(currentRow.getCell(31)
					.getStringCellValue());
		} else {
			textBoxUIElement.setDefaultValue(null);
		}
		textBoxUIElement.setMaxLength((int) currentRow.getCell(32)
				.getNumericCellValue());

		textBoxUIElement.setLabel(currentRow.getCell(33).getBooleanCellValue());

		textBoxUIElement.setSpellCheck(currentRow.getCell(34)
				.getBooleanCellValue());

	}
}
