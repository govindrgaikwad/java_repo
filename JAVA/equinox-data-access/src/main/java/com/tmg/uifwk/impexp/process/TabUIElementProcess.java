package com.tmg.uifwk.impexp.process;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.uifwk.orm.ui.LayoutType;
import com.tmg.uifwk.orm.ui.TabUIElement;
import com.tmg.uifwk.orm.ui.UIElementType;

@Component("TabUIElementProcess")
public class TabUIElementProcess {

	private Workbook excelWorkBook;

	@Autowired
	private GenericUIElementProcess genericUIElementProcess;

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

	public void exportTabUIElementToExcelSheet(
			UIElementChangeTracker changedElement, TabUIElement tabUIElement,
			int curRow) throws Exception {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row currentRow = sheet.createRow(curRow);

		genericUIElementProcess.exportUIElementToExcelSheet(changedElement,
				currentRow);

		currentRow.createCell(24).setCellValue(
				tabUIElement.getUielementType().getUIElementTypeID());
		currentRow.createCell(25).setCellValue(
				tabUIElement.getUielementType().getDisplayText());
		currentRow.createCell(26).setCellValue(tabUIElement.getChildCount());

		currentRow.createCell(27).setCellValue(
				tabUIElement.getLayoutType().getLayoutTypeID());
		currentRow.createCell(28).setCellValue(
				tabUIElement.getLayoutType().getName());

	}

	public void importTabUIElementFromExcelSheet(TabUIElement tabUIElement,
			int rowNum, LayoutType layoutType, UIElementType uiElementType) {

		tabUIElement.setLayoutType(layoutType);
		tabUIElement.setUielementType(uiElementType);

	}
}
