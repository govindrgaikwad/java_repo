package com.tmg.uifwk.impexp.process;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.uifwk.orm.ui.DataSource;
import com.tmg.uifwk.orm.ui.LayoutType;
import com.tmg.uifwk.orm.ui.RepeaterUIElement;
import com.tmg.uifwk.orm.ui.UIElementType;

@Component("RepeaterUIElementProcess")
public class RepeaterUIElementProcess {

	private Workbook excelWorkBook;

	@Autowired
	private GenericUIElementProcess genericUIElementProcess;

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

	public void exportRepeaterUIElementToExcelSheet(
			UIElementChangeTracker changedElement,
			RepeaterUIElement repeaterUIElement, int curRow) throws Exception {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row currentRow = sheet.createRow(curRow);

		genericUIElementProcess.exportUIElementToExcelSheet(changedElement,
				currentRow);

		currentRow.createCell(24).setCellValue(
				repeaterUIElement.getUielementType().getUIElementTypeID());

		currentRow.createCell(25).setCellValue(
				repeaterUIElement.getUielementType().getDisplayText());

		currentRow.createCell(26).setCellValue(
				repeaterUIElement.getChildCount());
		currentRow.createCell(27).setCellValue(
				repeaterUIElement.getLayoutType().getLayoutTypeID());
		currentRow.createCell(28).setCellValue(
				repeaterUIElement.getLayoutType().getName());

		if (repeaterUIElement.getDataSource() != null) {
			currentRow.createCell(29).setCellValue(
					repeaterUIElement.getDataSource().getDataSourceID());
		}

	}

	public void importRepeaterUIElementToExcelSheet(
			RepeaterUIElement repeaterUIElement, int uiElementRow,
			LayoutType layoutType, UIElementType uiElementType,
			DataSource dataSource) {
		repeaterUIElement.setLayoutType(layoutType);
		repeaterUIElement.setUielementType(uiElementType);
		repeaterUIElement.setDataSource(dataSource);

	}
}
