package com.tmg.uifwk.impexp.process;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.uifwk.orm.ui.RadioButtonUIElement;
import com.tmg.uifwk.orm.ui.UIElementType;

@Component("RadioButtonUIElementProcess")
public class RadioButtonUIElementProcess {

	private Workbook excelWorkBook;

	@Autowired
	private GenericUIElementProcess genericUIElementProcess;

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

	public void exportRadioButtonUIElementToExcelSheet(
			UIElementChangeTracker changedElement,
			RadioButtonUIElement radioButtonUIElement, int curRow)
			throws Exception {
		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row currentRow = sheet.createRow(curRow);

		genericUIElementProcess.exportUIElementToExcelSheet(changedElement,
				currentRow);

		currentRow.createCell(24).setCellValue(
				radioButtonUIElement.getUielementType().getUIElementTypeID());
		currentRow.createCell(25).setCellValue(
				radioButtonUIElement.getUielementType().getDisplayText());

		currentRow.createCell(38).setCellValue(
				radioButtonUIElement.getOptionLabel());

		if (radioButtonUIElement.getDefaultValue() != null) {

			currentRow.createCell(39).setCellValue(
					radioButtonUIElement.getDefaultValue());
		}

		currentRow.createCell(42).setCellValue(radioButtonUIElement.isYesNo());

		currentRow.createCell(43).setCellValue(
				radioButtonUIElement.getOptionLabelNo());

	}

	public void importRadioButtonUIElementFromExcelSheet(
			RadioButtonUIElement radioButtonUIElement, int curRow,
			UIElementType uiElementType) {
		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row uiElementRow = sheet.createRow(curRow);

		radioButtonUIElement.setUielementType(uiElementType);
		radioButtonUIElement.setOptionLabel(uiElementRow.getCell(38)
				.getStringCellValue());
		if (uiElementRow.getCell(39) != null) {
			radioButtonUIElement.setDefaultValue(uiElementRow.getCell(39)
					.getBooleanCellValue());
		} else {
			radioButtonUIElement.setDefaultValue(null);
		}

		radioButtonUIElement.setYesNo(uiElementRow.getCell(42)
				.getBooleanCellValue());
		radioButtonUIElement.setOptionLabelNo(uiElementRow.getCell(43)
				.getStringCellValue());

	}
}
