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

import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.uifwk.orm.BaseEntityAttributes;
import com.tmg.uifwk.orm.ui.ApplicationDataType;
import com.tmg.uifwk.orm.ui.CalendarUIElement;
import com.tmg.uifwk.orm.ui.CheckBoxUIElement;
import com.tmg.uifwk.orm.ui.DropDownUIElement;
import com.tmg.uifwk.orm.ui.FormDesign;
import com.tmg.uifwk.orm.ui.LayoutType;
import com.tmg.uifwk.orm.ui.RadioButtonUIElement;
import com.tmg.uifwk.orm.ui.TextBoxUIElement;
import com.tmg.uifwk.orm.ui.UIElement;
import com.tmg.uifwk.orm.ui.UIElementType;
import com.tmg.uifwk.util.UIElementUtils;

@Component("GenericUIElementProcess")
public class GenericUIElementProcess {

	private Workbook excelWorkBook;

	public void printHeaders() {

		Sheet sheet = excelWorkBook.getSheetAt(0);
		Row UIElementHeaderRow = sheet.createRow(5);
		List<String> columnNames = new ArrayList<String>();

		columnNames.add("Operation");
		columnNames.add("Type");

		columnNames.add("ParentName");
		columnNames.add("ParentUIElementID");

		for (Field field : UIElement.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columnNames.add(column.name());
			}
		}

		for (Field field : BaseEntityAttributes.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columnNames.add(column.name());
			}
		}

		for (Field field : FormDesign.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {

				if (column.name().equals("FormID")
						|| column.name().equals("FormName")) {
					columnNames.add(column.name());
				}
			}
		}

		for (Field field : ApplicationDataType.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {

				if (column.name().equals("ApplicationDataTypeID")
						|| column.name().equals("ApplicationDataTypeName")) {
					columnNames.add(column.name());
				}
			}
		}

		for (Field field : UIElementType.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {

				if (column.name().equals("UIElementTypeID")
						|| column.name().equals("DisplayText")) {
					columnNames.add(column.name());
				}
			}
		}

		columnNames.add("ChildCount");
		for (Field field : LayoutType.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {

				if (column.name().equals("LayoutTypeID")
						|| column.name().equals("Name")) {
					columnNames.add(column.name());
				}
			}
		}

		columnNames.add("DataSourceID");

		for (Field field : TextBoxUIElement.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columnNames.add(column.name());
			}
		}

		for (Field field : CalendarUIElement.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columnNames.add(column.name());
			}
		}

		for (Field field : CheckBoxUIElement.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				if (column.name().equals("OptionLabel")
						|| column.name().equals("DefaultValue")) {
					columnNames.add(column.name());
				}
			}
		}

		for (Field field : DropDownUIElement.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				columnNames.add(column.name());
			}
		}

		for (Field field : RadioButtonUIElement.class.getDeclaredFields()) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				if (column.name().equals("IsYesNo")
						|| column.name().equals("OptionLabelNo")) {
					columnNames.add(column.name());
				}
			}
		}

		int startCell = 0;
		for (int i = 0; i < columnNames.size(); i++) {

			UIElementHeaderRow.createCell(startCell).setCellValue(
					columnNames.get(i));
			startCell++;
		}

	}

	public void exportUIElementToExcelSheet(
			UIElementChangeTracker changedElement, Row curRow) throws Exception {

		Row currentRow = curRow;
		currentRow.createCell(0).setCellValue(changedElement.getAction());
		currentRow.createCell(1).setCellValue(
				UIElementUtils.getUIElementType(changedElement.getUiElement()
						.getClass()));

		if (changedElement.getUiElement().getParentUIElement() != null) {
			currentRow.createCell(2).setCellValue(
					changedElement.getUiElement().getParentUIElement()
							.getUIElementName());
			currentRow.createCell(3).setCellValue(
					changedElement.getUiElement().getParentUIElement()
							.getUIElementID());
		}
		currentRow.createCell(4).setCellValue(
				changedElement.getUiElement().getUIElementID());
		currentRow.createCell(5).setCellValue(
				changedElement.getUiElement().getUIElementName());
		currentRow.createCell(6).setCellValue(
				changedElement.getUiElement().getElementLabel());

		currentRow.createCell(7).setCellValue(
				changedElement.getUiElement().isContainer());
		currentRow.createCell(8).setCellValue(
				changedElement.getUiElement().getEnabled());
		currentRow.createCell(9).setCellValue(
				changedElement.getUiElement().getVisible());
		currentRow.createCell(10).setCellValue(
				changedElement.getUiElement().getSequence());
		currentRow.createCell(11).setCellValue(
				changedElement.getUiElement().getRequiresValidation());
		if (changedElement.getUiElement().getHelpText() != null) {
			currentRow.createCell(12).setCellValue(
					changedElement.getUiElement().getHelpText());
		}
		currentRow.createCell(13).setCellValue(
				changedElement.getUiElement().isActive());

		currentRow.createCell(14).setCellValue(
				changedElement.getUiElement().getHasCustomRule());
		if (changedElement.getUiElement().getGeneratedName() != null) {
			currentRow.createCell(15).setCellValue(
					changedElement.getUiElement().getGeneratedName());
		}
		currentRow.createCell(16).setCellValue(
				changedElement.getUiElement().getAddedBy());

		currentRow.createCell(17).setCellValue(
				changedElement.getUiElement().getAddedDate());

		if (changedElement.getUiElement().getUpdatedBy() != null) {
			currentRow.createCell(18).setCellValue(
					changedElement.getUiElement().getUpdatedBy());
		}
		if (changedElement.getUiElement().getUpdatedDate() != null) {
			currentRow.createCell(19).setCellValue(
					changedElement.getUiElement().getUpdatedDate());
		}

		currentRow.createCell(20).setCellValue(
				changedElement.getUiElement().getFormDesign().getFormID());
		currentRow.createCell(21).setCellValue(
				changedElement.getUiElement().getFormDesign().getFormName());
		currentRow.createCell(22).setCellValue(
				changedElement.getUiElement().getApplicationDataType()
						.getApplicationDataTypeID());
		currentRow.createCell(23).setCellValue(
				changedElement.getUiElement().getApplicationDataType()
						.getApplicationDataTypeName());

		System.out.println(changedElement.getAction() + "---"
				+ changedElement.getUiElement().getClass().getName());

	}

	public void importUIElementFromExcelSheet(UIElement newuiElement,
			FormDesign formDesign, ApplicationDataType dataType, int rowNum) {

		Sheet sheet = excelWorkBook.getSheetAt(0);

		Row uiElementRow = sheet.getRow(rowNum);

		newuiElement.setUIElementName(uiElementRow.getCell(5)
				.getStringCellValue());
		newuiElement.setElementLabel(uiElementRow.getCell(6)
				.getStringCellValue());
		newuiElement
				.setContainer(uiElementRow.getCell(7).getBooleanCellValue());
		newuiElement.setEnabled(uiElementRow.getCell(8).getBooleanCellValue());
		newuiElement.setVisible(uiElementRow.getCell(9).getBooleanCellValue());
		newuiElement.setSequence((int) uiElementRow.getCell(10)
				.getNumericCellValue());
		newuiElement.setRequiresValidation(uiElementRow.getCell(11)
				.getBooleanCellValue());
		if (uiElementRow.getCell(12) != null) {
			newuiElement.setHelpText(uiElementRow.getCell(12)
					.getStringCellValue());
		} else {
			newuiElement.setHelpText(null);
		}
		newuiElement.setActive(uiElementRow.getCell(13).getBooleanCellValue());

		newuiElement.setHasCustomRule(uiElementRow.getCell(14)
				.getBooleanCellValue());

		if (uiElementRow.getCell(15) != null) {
			newuiElement.setGeneratedName(uiElementRow.getCell(15)
					.getStringCellValue());

		} else {
			newuiElement.setGeneratedName(null);
		}
		newuiElement.setAddedBy(uiElementRow.getCell(16).getStringCellValue());
		newuiElement.setAddedDate(new Timestamp(uiElementRow.getCell(17)
				.getDateCellValue().getTime()));

		if (uiElementRow.getCell(18) != null) {
			newuiElement.setUpdatedBy(uiElementRow.getCell(18)
					.getStringCellValue());
		} else {
			newuiElement.setUpdatedBy(null);
		}
		if (uiElementRow.getCell(19) != null) {
			newuiElement.setUpdatedDate(new Timestamp(uiElementRow.getCell(19)
					.getDateCellValue().getTime()));
		} else {
			newuiElement.setUpdatedDate(null);
		}

		newuiElement.setFormDesign(formDesign);

		newuiElement.setApplicationDataType(dataType);

	}

	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}

	public void setExcelWorkBook(Workbook excelWorkBook) {
		this.excelWorkBook = excelWorkBook;
	}

}
