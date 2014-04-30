package com.tmg.uifwk.impexp.process;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.uifwk.orm.ui.DataSource;
import com.tmg.uifwk.orm.ui.LayoutType;
import com.tmg.uifwk.orm.ui.SectionUIElement;
import com.tmg.uifwk.orm.ui.UIElementType;

@Component("SectionUIElementProcess")
public class SectionUIElementProcess {

	private Workbook excelWorkBook;

	@Autowired
	private GenericUIElementProcess genericUIElementProcess;

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

	public void exportSectionUIElementToExcelSheet(
			UIElementChangeTracker changedElement,
			SectionUIElement sectionUIElement, int curRow) throws Exception {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row currentRow = sheet.createRow(curRow);

		genericUIElementProcess.exportUIElementToExcelSheet(changedElement,
				currentRow);

		currentRow.createCell(24).setCellValue(
				sectionUIElement.getUielementType().getUIElementTypeID());

		currentRow.createCell(25).setCellValue(
				sectionUIElement.getUielementType().getDisplayText());

		currentRow.createCell(26)
				.setCellValue(sectionUIElement.getChildCount());
		currentRow.createCell(27).setCellValue(
				sectionUIElement.getLayoutType().getLayoutTypeID());
		currentRow.createCell(28).setCellValue(
				sectionUIElement.getLayoutType().getName());

		if (sectionUIElement.getDataSource() != null) {
			currentRow.createCell(29).setCellValue(
					sectionUIElement.getDataSource().getDataSourceID());
		}

	}

	public void importSectionUIElementFromExcelSheet(
			SectionUIElement sectionUIElement, int uiElementRow,
			LayoutType layoutType, UIElementType uiElementType,
			DataSource dataSource) {

		sectionUIElement.setLayoutType(layoutType);
		sectionUIElement.setUielementType(uiElementType);
		sectionUIElement.setDataSource(dataSource);

	}

}
