package com.tmg.uifwk.service.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmg.equinox.view.formdesign.FormDesignChangeTracker;
import com.tmg.equinox.view.formdesign.FormDesignVersionChangeTracker;
import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.equinox.view.formdesign.ValidatorChangeTraker;
import com.tmg.uifwk.impexp.process.CalendarUIElementProcess;
import com.tmg.uifwk.impexp.process.CheckBoxUIElementProcess;
import com.tmg.uifwk.impexp.process.DropDownUIElementProcess;
import com.tmg.uifwk.impexp.process.FormDesignProcess;
import com.tmg.uifwk.impexp.process.FormDesignVersionProcess;
import com.tmg.uifwk.impexp.process.GenericUIElementProcess;
import com.tmg.uifwk.impexp.process.RadioButtonUIElementProcess;
import com.tmg.uifwk.impexp.process.RepeaterUIElementProcess;
import com.tmg.uifwk.impexp.process.SectionUIElementProcess;
import com.tmg.uifwk.impexp.process.TabUIElementProcess;
import com.tmg.uifwk.impexp.process.TextBoxUIElementProcess;
import com.tmg.uifwk.impexp.process.ValidatorProcess;
import com.tmg.uifwk.orm.ui.ApplicationDataType;
import com.tmg.uifwk.orm.ui.CalendarUIElement;
import com.tmg.uifwk.orm.ui.CheckBoxUIElement;
import com.tmg.uifwk.orm.ui.DataSource;
import com.tmg.uifwk.orm.ui.DropDownUIElement;
import com.tmg.uifwk.orm.ui.FormDesign;
import com.tmg.uifwk.orm.ui.FormDesignVersion;
import com.tmg.uifwk.orm.ui.LayoutType;
import com.tmg.uifwk.orm.ui.RadioButtonUIElement;
import com.tmg.uifwk.orm.ui.RegexLibrary;
import com.tmg.uifwk.orm.ui.RepeaterUIElement;
import com.tmg.uifwk.orm.ui.SectionUIElement;
import com.tmg.uifwk.orm.ui.Status;
import com.tmg.uifwk.orm.ui.TabUIElement;
import com.tmg.uifwk.orm.ui.TextBoxUIElement;
import com.tmg.uifwk.orm.ui.UIElement;
import com.tmg.uifwk.orm.ui.UIElementType;
import com.tmg.uifwk.orm.ui.Validator;
import com.tmg.uifwk.repository.ui.GenericUIRepository;
import com.tmg.uifwk.util.UIElementUtils;

@Service("ImportExportDataService")
public class ImportExportDataService {

	@Autowired
	private UIDataService UIDataService;

	@Autowired
	private GenericUIRepository genericUIRepository;

	@Autowired
	private Map<String, Object> impExpProcessors;

	@Transactional
	public void getExportToExcelSheet() throws Exception {

		FormDesignVersion formDesignVersion = UIDataService
				.getFormDesignVersionById(2);

		List<UIElementChangeTracker> changedUIElements = UIDataService
				.getChangedUIElementsForFormDesignVersion(1, "1.0",
						formDesignVersion.getFormDesign().getFormID(),
						new Date(), new Date());

		String file = ".\\ChangeTracker\\ChangeTracker.xlsx";
		InputStream istr = new FileInputStream(new File(file));
		Workbook workBook = WorkbookFactory.create(istr);

		FormDesignChangeTracker formDesignChangeTracker = UIDataService
				.getChangedFormDesign(formDesignVersion.getFormDesign()
						.getFormID(), new Date(), new Date());

		FormDesignProcess formDesignProcess = (FormDesignProcess) impExpProcessors
				.get("FormDesignProcess");
		formDesignProcess.setExcelWorkBook(workBook);
		formDesignProcess.printHeaders();
		formDesignProcess.exportFormDesignToExcelSheet(formDesignChangeTracker);

		FormDesignVersionChangeTracker formDesignVersionChangeTracker = UIDataService
				.getChangedFormDesignVersion(
						formDesignVersion.getFormDesignVersionID(), new Date(),
						new Date());
		FormDesignVersionProcess formDesignVersionProcess = (FormDesignVersionProcess) impExpProcessors
				.get("FormDesignVersionProcess");
		formDesignVersionProcess.setExcelWorkBook(workBook);

		formDesignVersionProcess.printHeaders();
		formDesignVersionProcess
				.exportFormDesignVersionToExcelSheet(formDesignVersionChangeTracker);

		GenericUIElementProcess genericUIElementProcess = (GenericUIElementProcess) impExpProcessors
				.get("GenericUIElementProcess");
		genericUIElementProcess.setExcelWorkBook(workBook);
		genericUIElementProcess.printHeaders();

		List<Integer> changedUIElementIDList = new ArrayList<Integer>();

		int startRow = 6;
		for (UIElementChangeTracker changedElement : changedUIElements) {

			changedUIElementIDList.add(changedElement.getUiElement()
					.getUIElementID());

			if (UIElementUtils.getUIElementType(
					changedElement.getUiElement().getClass()).equals(
					"TabUIElement")) {

				TabUIElement tabUIElement = genericUIRepository
						.findTabUIElementById(changedElement.getUiElement()
								.getUIElementID());

				TabUIElementProcess tabUIElementProcess = (TabUIElementProcess) impExpProcessors
						.get("TabUIElementProcess");

				tabUIElementProcess.setExcelWorkBook(workBook);
				tabUIElementProcess.exportTabUIElementToExcelSheet(
						changedElement, tabUIElement, startRow);

			}

			if (UIElementUtils.getUIElementType(
					changedElement.getUiElement().getClass()).equals(
					"SectionUIElement")) {

				SectionUIElement sectionUIElement = genericUIRepository
						.findSectionUIElementById(changedElement.getUiElement()
								.getUIElementID());

				SectionUIElementProcess sectionUIElementProcess = (SectionUIElementProcess) impExpProcessors
						.get("SectionUIElementProcess");

				sectionUIElementProcess.setExcelWorkBook(workBook);

				sectionUIElementProcess.exportSectionUIElementToExcelSheet(
						changedElement, sectionUIElement, startRow);

			}

			if (UIElementUtils.getUIElementType(
					changedElement.getUiElement().getClass()).equals(
					"RepeaterUIElement")) {

				RepeaterUIElement repeaterUIElement = genericUIRepository
						.findRepeaterUIElementById(changedElement
								.getUiElement().getUIElementID());

				RepeaterUIElementProcess repeaterUIElementProcess = (RepeaterUIElementProcess) impExpProcessors
						.get("RepeaterUIElementProcess");

				repeaterUIElementProcess.setExcelWorkBook(workBook);

				repeaterUIElementProcess.exportRepeaterUIElementToExcelSheet(
						changedElement, repeaterUIElement, startRow);

			}

			if (UIElementUtils.getUIElementType(
					changedElement.getUiElement().getClass()).equals(
					"TextBoxUIElement")) {

				TextBoxUIElement textBoxUIElement = genericUIRepository
						.findTextBoxUIElementById(changedElement.getUiElement()
								.getUIElementID());

				TextBoxUIElementProcess textBoxUIElementProcess = (TextBoxUIElementProcess) impExpProcessors
						.get("TextBoxUIElementProcess");
				textBoxUIElementProcess.setExcelWorkBook(workBook);

				textBoxUIElementProcess.exportTextBoxUIElementToExcelSheet(
						changedElement, textBoxUIElement, startRow);

			}

			if (UIElementUtils.getUIElementType(
					changedElement.getUiElement().getClass()).equals(
					"CalendarUIElement")) {

				CalendarUIElement calendarUIElement = genericUIRepository
						.findCalendarUIElementById(changedElement
								.getUiElement().getUIElementID());

				CalendarUIElementProcess calendarUIElementProcess = (CalendarUIElementProcess) impExpProcessors
						.get("CalendarUIElementProcess");

				calendarUIElementProcess.setExcelWorkBook(workBook);

				calendarUIElementProcess.exportCalendarUIElementToExcelSheet(
						changedElement, calendarUIElement, startRow);

			}

			if (UIElementUtils.getUIElementType(
					changedElement.getUiElement().getClass()).equals(
					"CheckBoxUIElement")) {

				CheckBoxUIElement checkBoxUIElement = genericUIRepository
						.findCheckBoxUIElementById(changedElement
								.getUiElement().getUIElementID());

				CheckBoxUIElementProcess checkBoxUIElementProcess = (CheckBoxUIElementProcess) impExpProcessors
						.get("CheckBoxUIElementProcess");
				checkBoxUIElementProcess.setExcelWorkBook(workBook);
				checkBoxUIElementProcess.exportCheckBoxUIElementToExcelSheet(
						changedElement, checkBoxUIElement, startRow);

			}

			if (UIElementUtils.getUIElementType(
					changedElement.getUiElement().getClass()).equals(
					"DropDownUIElement")) {

				DropDownUIElement dropDownUIElement = genericUIRepository
						.findDropDownUIElementById(changedElement
								.getUiElement().getUIElementID());

				DropDownUIElementProcess dropDownUIElementProcess = (DropDownUIElementProcess) impExpProcessors
						.get("DropDownUIElementProcess");
				dropDownUIElementProcess.setExcelWorkBook(workBook);

				dropDownUIElementProcess.exportDropDownUIElementToExcelSheet(
						changedElement, dropDownUIElement, startRow);

			}

			if (UIElementUtils.getUIElementType(
					changedElement.getUiElement().getClass()).equals(
					"RadioButtonUIElement")) {

				RadioButtonUIElement radioButtonUIElement = genericUIRepository
						.findRadioButtonUIElementById(changedElement
								.getUiElement().getUIElementID());

				RadioButtonUIElementProcess radioButtonUIElementProcess = (RadioButtonUIElementProcess) impExpProcessors
						.get("RadioButtonUIElementProcess");

				radioButtonUIElementProcess.setExcelWorkBook(workBook);

				radioButtonUIElementProcess
						.exportRadioButtonUIElementToExcelSheet(changedElement,
								radioButtonUIElement, startRow);

			}

			startRow++;

		}

		workBook.getSheetAt(0).createRow(startRow).createCell(0)
				.setCellValue("End OF UIElement");

		ValidatorProcess validatorProcess = (ValidatorProcess) impExpProcessors
				.get("ValidatorProcess");
		validatorProcess.setExcelWorkBook(workBook);
		validatorProcess.printHeaders(startRow + 1);

		List<ValidatorChangeTraker> changedValidators = UIDataService
				.getChangedValidatorsForFormDesignVersion(1, "1.0",
						formDesignVersion.getFormDesign().getFormID(),
						new Date(), new Date());

		validatorProcess.setExcelWorkBook(workBook);
		validatorProcess.exportValidatorToExcelSheet(changedValidators,
				startRow + 2);

		istr.close();

		FileOutputStream outFile = new FileOutputStream(new File(file));
		workBook.write(outFile);
		outFile.flush();
		outFile.close();

	}

	public void getImportFromExcelSheet() throws Exception {
		String file = ".\\ChangeTracker\\ChangeTracker.xlsx";

		InputStream istr = new FileInputStream(new File(file));

		Workbook workBook = WorkbookFactory.create(istr);
		Sheet sheet = workBook.getSheetAt(0);

		Row formDesignDataRow = sheet.getRow(1);
		FormDesign formDesign = processFormDesignData(formDesignDataRow,
				workBook);

		Row formDesignVersionRow = sheet.getRow(3);

		processFormDesignVersionData(formDesign, formDesignVersionRow, workBook);

		Map<String, UIElement> createdElements = new HashMap<String, UIElement>();
		List<UIElement> changedElements = new ArrayList<UIElement>();

		GenericUIElementProcess genericUIElementProcess = (GenericUIElementProcess) impExpProcessors
				.get("GenericUIElementProcess");

		int rowNum = 0;
		for (Row uiElementRow : sheet) {
			rowNum = uiElementRow.getRowNum();
			if (rowNum < 6) {
				continue;
			}
			if (uiElementRow.getCell(0).getStringCellValue()
					.equals("End OF UIElement")) {
				break;
			}

			Cell containerCell = uiElementRow.getCell(7);
			boolean isContainer = false;
			if (containerCell == null) {
				throw new Exception("Incorrect Data in Container cell for row "
						+ uiElementRow.getRowNum());
			}
			isContainer = containerCell.getBooleanCellValue();

			String uiElementName = uiElementRow.getCell(5).getStringCellValue();
			String parentUIElementName = null;
			if (uiElementRow.getCell(2) != null) {
				parentUIElementName = uiElementRow.getCell(2)
						.getStringCellValue();
			}

			UIElement currentUIElement = null;
			UIElement parentUIElement = null;

			String uiElementType = uiElementRow.getCell(1).getStringCellValue();

			ApplicationDataType dataType = UIDataService
					.getUIElementDataType(uiElementRow.getCell(23)
							.getStringCellValue());

			LayoutType layoutType = UIDataService.getLayoutType(uiElementRow
					.getCell(28).getStringCellValue());

			UIElementType uiType = UIDataService.getuiElementType(uiElementRow
					.getCell(25).getStringCellValue());

			DataSource dataSource = null;
			if (uiElementRow.getCell(29) != null) {
				dataSource = UIDataService.getDataSourceById((int) uiElementRow
						.getCell(29).getNumericCellValue());

			}

			if (isContainer) {

				if (parentUIElementName == null) {
					currentUIElement = UIDataService
							.getUIElementForParentIsNull(uiElementName);
				} else {
					currentUIElement = UIDataService
							.getUIElementForParentNotNull(uiElementName,
									parentUIElementName);
					parentUIElement = createdElements.get(parentUIElementName);
					if (parentUIElement == null) {
						parentUIElement = UIDataService
								.getParentUIElement(parentUIElementName);
					}
				}

				if (currentUIElement == null) {

					currentUIElement = UIElementUtils
							.getUIElementOfGivenType(uiElementType);
				}

				genericUIElementProcess.setExcelWorkBook(workBook);
				genericUIElementProcess.importUIElementFromExcelSheet(
						currentUIElement, formDesign, dataType,
						uiElementRow.getRowNum());
				processUIElementType(currentUIElement, workBook,
						uiElementRow.getRowNum(), layoutType, uiType,
						dataSource);
				createdElements.put(uiElementName, currentUIElement);

				if (parentUIElement != null) {
					currentUIElement.setParentUIElement(parentUIElement);
				}

			} else {

				parentUIElement = createdElements.get(parentUIElementName);
				if (parentUIElement == null) {
					UIDataService.getParentUIElement(parentUIElementName);
				}
				if (parentUIElement == null) {
					throw new Exception("Incorrect Data in cell for row "
							+ uiElementRow.getRowNum() + ". No parent found");
				}

				currentUIElement = UIDataService.getUIElementForParentNotNull(
						uiElementName, parentUIElementName);
				if (currentUIElement == null) {
					currentUIElement = UIElementUtils
							.getUIElementOfGivenType(uiElementType);
				}
				genericUIElementProcess.setExcelWorkBook(workBook);
				genericUIElementProcess.importUIElementFromExcelSheet(
						currentUIElement, formDesign, dataType,
						uiElementRow.getRowNum());
				processUIElementType(currentUIElement, workBook,
						uiElementRow.getRowNum(), layoutType, uiType,
						dataSource);
				currentUIElement.setParentUIElement(parentUIElement);

			}
			changedElements.add(currentUIElement);

		}

		UIDataService.saveUIElements(changedElements);

		for (int validatorRowNum = rowNum + 2; validatorRowNum <= sheet
				.getLastRowNum(); validatorRowNum++) {
			Row validatorRow = sheet.getRow(validatorRowNum);
			RegexLibrary regexLibrary = null;

			if (validatorRow.getCell(29) != null) {
				regexLibrary = UIDataService
						.getRegexLibraryByLibraryRegexName(validatorRow
								.getCell(10).getStringCellValue());

			}

			processValidatorData(regexLibrary, validatorRow, workBook);
		}

	}

	private void processValidatorData(RegexLibrary regexLibrary,
			Row validatorRow, Workbook workBook) throws Exception {

		ValidatorProcess validatorProcess = (ValidatorProcess) impExpProcessors
				.get("ValidatorProcess");
		validatorProcess.setExcelWorkBook(workBook);

		String uiElemntName = validatorRow.getCell(2).getStringCellValue();

		UIElement uiElement = UIDataService
				.getUIElementByUIElementName(uiElemntName);

		if (uiElement == null) {
			throw new Exception("Incorrect Data in Container cell for row "
					+ validatorRow.getRowNum());
		}

		Validator validator = null;
		validator = UIDataService.getValidatorsByUIElementID(uiElement
				.getUIElementID());

		if (validator == null) {
			validator = new Validator();
		}
		validatorProcess.importValidatorFromExcelSheet(validator, uiElement,
				regexLibrary, validatorRow.getRowNum());

		UIDataService.saveValidators(validator);

	}

	private void processUIElementType(UIElement currentUIElement,
			Workbook workBook, int uiElementRow, LayoutType layoutType,
			UIElementType uiElementType, DataSource dataSource) {

		if (UIElementUtils.getUIElementType(currentUIElement.getClass())
				.equals("TabUIElement")) {

			TabUIElementProcess tabUIElementProcess = (TabUIElementProcess) impExpProcessors
					.get("TabUIElementProcess");
			TabUIElement tabUIElement = (TabUIElement) currentUIElement;
			tabUIElementProcess.setExcelWorkBook(workBook);
			tabUIElementProcess.importTabUIElementFromExcelSheet(tabUIElement,
					uiElementRow, layoutType, uiElementType);

		}

		if (UIElementUtils.getUIElementType(currentUIElement.getClass())
				.equals("SectionUIElement")) {

			SectionUIElementProcess sectionUIElementProcess = (SectionUIElementProcess) impExpProcessors
					.get("SectionUIElementProcess");

			SectionUIElement sectionUIElement = (SectionUIElement) currentUIElement;

			sectionUIElementProcess.setExcelWorkBook(workBook);

			sectionUIElementProcess.importSectionUIElementFromExcelSheet(
					sectionUIElement, uiElementRow, layoutType, uiElementType,
					dataSource);

		}

		if (UIElementUtils.getUIElementType(currentUIElement.getClass())
				.equals("RepeaterUIElement")) {

			RepeaterUIElementProcess repeaterUIElementProcess = (RepeaterUIElementProcess) impExpProcessors
					.get("RepeaterUIElementProcess");

			RepeaterUIElement repeaterUIElement = (RepeaterUIElement) currentUIElement;
			repeaterUIElementProcess.setExcelWorkBook(workBook);
			repeaterUIElementProcess.importRepeaterUIElementToExcelSheet(
					repeaterUIElement, uiElementRow, layoutType, uiElementType,
					dataSource);

		}

		if (UIElementUtils.getUIElementType(currentUIElement.getClass())
				.equals("TextBoxUIElement")) {

			TextBoxUIElementProcess textBoxUIElementProcess = (TextBoxUIElementProcess) impExpProcessors
					.get("TextBoxUIElementProcess");

			TextBoxUIElement textBoxUIElement = (TextBoxUIElement) currentUIElement;
			textBoxUIElementProcess.setExcelWorkBook(workBook);

			textBoxUIElementProcess.importTextBoxUIElementFromExcelSheet(
					textBoxUIElement, uiElementRow, uiElementType);

		}

		if (UIElementUtils.getUIElementType(currentUIElement.getClass())
				.equals("CalendarUIElement")) {
			CalendarUIElementProcess calendarUIElementProcess = (CalendarUIElementProcess) impExpProcessors
					.get("CalendarUIElementProcess");

			CalendarUIElement calendarUIElement = (CalendarUIElement) currentUIElement;

			calendarUIElementProcess.setExcelWorkBook(workBook);

			calendarUIElementProcess.importCalendarUIElementFromExcelSheet(
					calendarUIElement, uiElementRow, uiElementType);

		}

		if (UIElementUtils.getUIElementType(currentUIElement.getClass())
				.equals("CheckBoxUIElement")) {

			CheckBoxUIElementProcess checkBoxUIElementProcess = (CheckBoxUIElementProcess) impExpProcessors
					.get("CheckBoxUIElementProcess");

			CheckBoxUIElement checkBoxUIElement = (CheckBoxUIElement) currentUIElement;
			checkBoxUIElementProcess.setExcelWorkBook(workBook);
			checkBoxUIElementProcess.importCheckBoxUIElementFromExcelSheet(
					checkBoxUIElement, uiElementRow, uiElementType);

		}

		if (UIElementUtils.getUIElementType(currentUIElement.getClass())
				.equals("DropDownUIElement")) {

			DropDownUIElementProcess dropDownUIElementProcess = (DropDownUIElementProcess) impExpProcessors
					.get("DropDownUIElementProcess");

			DropDownUIElement dropDownUIElement = (DropDownUIElement) currentUIElement;
			dropDownUIElementProcess.setExcelWorkBook(workBook);

			dropDownUIElementProcess.importDropDownUIElementFromExcelSheet(
					dropDownUIElement, uiElementRow, uiElementType);

		}
		if (UIElementUtils.getUIElementType(currentUIElement.getClass())
				.equals("RadioButtonUIElement")) {

			RadioButtonUIElementProcess radioButtonUIElementProcess = (RadioButtonUIElementProcess) impExpProcessors
					.get("RadioButtonUIElementProcess");

			RadioButtonUIElement radioButtonUIElement = (RadioButtonUIElement) currentUIElement;

			radioButtonUIElementProcess.setExcelWorkBook(workBook);

			radioButtonUIElementProcess
					.importRadioButtonUIElementFromExcelSheet(
							radioButtonUIElement, uiElementRow, uiElementType);

		}

	}

	private void processFormDesignVersionData(FormDesign formDesign,
			Row formDesignVersionRow, Workbook workBook) {

		FormDesignVersion currentFormDesignVersion = UIDataService
				.getFormDesignVersionByFormVersionNumber(
						formDesign.getFormID(), formDesignVersionRow.getCell(3)
								.getStringCellValue());

		if (currentFormDesignVersion == null) {
			currentFormDesignVersion = new FormDesignVersion();
		}

		FormDesignVersionProcess formDesignVersionProcess = (FormDesignVersionProcess) impExpProcessors
				.get("FormDesignVersionProcess");

		formDesignVersionProcess.setExcelWorkBook(workBook);

		Status status = UIDataService
				.getStatusByStatusName(formDesignVersionRow.getCell(7)
						.getStringCellValue());

		formDesignVersionProcess.importFormDesignVersionFromExcelSheet(
				currentFormDesignVersion, formDesign, status);

		UIDataService.saveFormDesingVersion(currentFormDesignVersion);

	}

	private FormDesign processFormDesignData(Row formDesignDataRow,
			Workbook workBook) throws Exception {

		FormDesignProcess formDesignProcess = (FormDesignProcess) impExpProcessors
				.get("FormDesignProcess");
		formDesignProcess.setExcelWorkBook(workBook);

		FormDesign currentForm = UIDataService
				.getFormDesignByformName(formDesignDataRow.getCell(2)
						.getStringCellValue());

		if (currentForm == null) {
			currentForm = new FormDesign();
		}
		formDesignProcess.importFormDesignFromExcelSheet(currentForm);
		currentForm = UIDataService.saveForm(currentForm);
		return currentForm;
	}

}
