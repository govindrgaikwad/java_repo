package com.tmg.uifwk.service.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmg.equinox.view.formdesign.FormDesignChangeTracker;
import com.tmg.equinox.view.formdesign.FormDesignVersionChangeTracker;
import com.tmg.equinox.view.formdesign.UIElementChangeTracker;
import com.tmg.equinox.view.formdesign.UIElementRowModel;
import com.tmg.equinox.view.formdesign.ValidatorChangeTraker;
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

@Service("UIDataService")
public class UIDataService {
	@Autowired
	private GenericUIRepository genericUIRepository;

	@Transactional(readOnly = true)
	public UIElement getUIElement(int elementID, boolean deep) {
		UIElement uiElement = genericUIRepository.getUIElement(elementID);
		if (deep) {
			populateChildren(uiElement);
		}
		return uiElement;
	}

	@Transactional(readOnly = true)
	public List<UIElementChangeTracker> getChangedUIElementsForFormDesignVersion(
			int tenantId, String formVersionNumber, int formId, Date startDate,
			Date endDate) {
		List<UIElement> uiElements = genericUIRepository
				.getUIElementsForFormDesignVersion(tenantId, formVersionNumber,
						formId);

		List<UIElementChangeTracker> itemsThatChanged = new ArrayList<UIElementChangeTracker>();

		for (UIElement uiElement : uiElements) {
			UIElementChangeTracker ct = new UIElementChangeTracker();
			ct.setUiElement(uiElement);
			if (uiElement.isContainer()) {
				if (uiElement.getAddedDate().after(startDate)) {
					ct.setAction("A");
				} else if (uiElement.getAddedDate().after(startDate)
						&& uiElement.getUpdatedDate().before(endDate)) {
					ct.setAction("U");
				}
				itemsThatChanged.add(ct);
			} else {
				if (uiElement.getAddedDate().after(startDate)) {
					ct.setAction("A");
					itemsThatChanged.add(ct);
				} else if (uiElement.getAddedDate().after(startDate)
						&& uiElement.getUpdatedDate().before(endDate)) {
					ct.setAction("U");
					itemsThatChanged.add(ct);
				}

			}
		}

		return itemsThatChanged;
	}

	private void populateChildren(UIElement uiElement) {
		if (uiElement.getChildElements() == null
				|| uiElement.getChildElements().size() == 0) {
			return;
		}
		for (UIElement childElement : uiElement.getChildElements()) {
			genericUIRepository.getUIElement(childElement.getUIElementID());
		}
	}

	@Transactional(readOnly = true)
	public List<UIElementRowModel> getUIElementListForFormDesignVersion(
			int tenantId, int formDesignVersionId) {
		// select from FDV, FDVUIEMap, UIElement where UIElement. parent is null

		UIElement rootUIElement = genericUIRepository
				.getRootUIElementForFormDesignVersion(tenantId,
						formDesignVersionId);
		rootUIElement = this.getUIElement(rootUIElement.getUIElementID(), true);

		List<UIElementRowModel> elementRowModels = getElementRowModel(
				rootUIElement, 0);

		System.out.println(elementRowModels.size());

		return elementRowModels;
	}

	private List<UIElementRowModel> getElementRowModel(UIElement uiElement,
			int level) {
		List<UIElementRowModel> result = new ArrayList<UIElementRowModel>();

		UIElementRowModel elementRowModel = new UIElementRowModel();
		elementRowModel.setUIElementID(uiElement.getUIElementID());

		elementRowModel.setDataType(uiElement.getApplicationDataType()
				.getApplicationDataTypeName());
		elementRowModel.setSequence(uiElement.getSequence());
		elementRowModel.setLabel(uiElement.getElementLabel());

		if (uiElement.getParentUIElement() != null) {
			elementRowModel.setParent(""
					+ uiElement.getParentUIElement().getUIElementID());
		}

		if (uiElement.getChildElements() != null
				&& uiElement.getChildElements().size() > 0) {
			elementRowModel.setLeaf(false);
		} else {
			elementRowModel.setLeaf(true);
		}

		elementRowModel.setElementType(getUIElementType(uiElement));
		if (elementRowModel.getElementType().equalsIgnoreCase("blank")) {
			elementRowModel.setLabel("[Blank]");
		}
		if (uiElement instanceof TextBoxUIElement) {
			TextBoxUIElement textBoxUIElement = (TextBoxUIElement) uiElement;
			elementRowModel.setMaxLength(textBoxUIElement.getMaxLength());

		}

		if (uiElement.getValidators() != null
				&& uiElement.getValidators().size() > 0) {
			for (Validator validator : uiElement.getValidators()) {
				if (validator.getRequired()) {
					elementRowModel.setRequired("Yes");
				}
			}
		} else {
			elementRowModel.setRequired("No");
		}

		// elementRowModel.setExt();
		// elementRowModel.setLoaded();
		elementRowModel.setLevel(level);

		elementRowModel.setAddedBy(uiElement.getAddedBy());
		elementRowModel.setUpdatedBy(uiElement.getUpdatedBy());
		elementRowModel.setAddedDate(uiElement.getAddedDate());
		elementRowModel.setUpdatedDate(uiElement.getUpdatedDate());

		result.add(elementRowModel);

		if (uiElement.getChildElements() == null
				|| uiElement.getChildElements().size() == 0) {
			return result;
		}

		for (UIElement childElement : uiElement.getChildElements()) {
			List<UIElementRowModel> innerResult = this.getElementRowModel(
					childElement, level + 1);
			result.addAll(innerResult);
		}

		return result;
	}

	private String getUIElementType(UIElement uiElement) {
		if (uiElement instanceof RadioButtonUIElement) {
			return "Radio Button";
		}
		if (uiElement instanceof CheckBoxUIElement) {
			return "CheckBox";
		}
		if (uiElement instanceof TextBoxUIElement) {
			TextBoxUIElement textBoxUIElement = (TextBoxUIElement) uiElement;
			if (textBoxUIElement.isMultiline()) {
				return "Multilne TextBox";
			} else if (textBoxUIElement.isLabel()) {
				if (textBoxUIElement.getUielementType().getDisplayText()
						.equals("Blank")) {
					return "Blank";
				}
				return "Label";
			} else {
				return "TextBox";
			}
		}
		if (uiElement instanceof DropDownUIElement) {
			return "Dropdown List";
		}
		if (uiElement instanceof CalendarUIElement) {
			return "Calendar";
		}
		if (uiElement instanceof RepeaterUIElement) {
			return "Repeater";
		}
		if (uiElement instanceof TabUIElement) {
			return "Tab";
		}
		if (uiElement instanceof SectionUIElement) {
			return "Section";
		}
		if (uiElement.getApplicationDataType().getApplicationDataTypeName() == null
				|| uiElement.getApplicationDataType()
						.getApplicationDataTypeName().equals("")) {
			return "";
		}

		return "";
	}

	@Transactional(readOnly = true)
	public FormDesignVersion getFormDesignVersionById(int formDesignVersionId) {
		FormDesignVersion formDesignVersion = this.genericUIRepository
				.getFormDesignVersionById(formDesignVersionId);
		return formDesignVersion;
	}

	@Transactional(readOnly = true)
	public UIElement getUIElementForParentNotNull(String elementName,
			String parentName) {

		return genericUIRepository.findUIElementForParentNotNull(elementName,
				parentName);

	}

	@Transactional(readOnly = true)
	public UIElement getUIElementForParentIsNull(String elementName) {
		return genericUIRepository.findUIElementForParentIsNull(elementName);

	}

	@Transactional(readOnly = true)
	public UIElement getParentUIElement(String elementName) {
		return genericUIRepository.findParentUIElement(elementName);
	}

	@Transactional(readOnly = true)
	public ApplicationDataType getUIElementDataType(String dataTypeName) {
		return genericUIRepository.findUIElementDataType(dataTypeName);
	}

	@Transactional
	public void saveUIElements(List<UIElement> updatedUIElements) {

		for (UIElement uiElement : updatedUIElements) {

			if (uiElement.getUIElementID() != null
					&& uiElement.getUIElementID() > 0) {
				genericUIRepository.updateData(uiElement);
			} else {
				genericUIRepository.saveData(uiElement);
			}
		}

	}

	@Transactional(readOnly = true)
	public FormDesign getFormDesignByformName(String formName) {
		return genericUIRepository.findFormDesignByFormName(formName);
	}

	@Transactional
	public FormDesign saveForm(FormDesign currentForm) {

		if (currentForm.getFormID() == null && currentForm.getFormID() > 0) {

			currentForm = genericUIRepository.saveData(currentForm);

		} else {
			currentForm = genericUIRepository.updateData(currentForm);
		}
		return currentForm;

	}

	@Transactional(readOnly = true)
	public FormDesignVersion getFormDesignVersionByFormVersionNumber(
			int formId, String versionNumber) {

		return genericUIRepository.findFormDesignVersionByVersionNumber(formId,
				versionNumber);
	}

	@Transactional
	public FormDesignVersion saveFormDesingVersion(
			FormDesignVersion currentFormDesignVersion) {

		if (currentFormDesignVersion.getFormDesignVersionID() == null
				&& currentFormDesignVersion.getFormDesignVersionID() > 0) {

			currentFormDesignVersion = genericUIRepository
					.saveData(currentFormDesignVersion);

		} else {
			currentFormDesignVersion = genericUIRepository
					.updateData(currentFormDesignVersion);
		}
		return currentFormDesignVersion;
	}

	@Transactional(readOnly = true)
	public Status getStatusByStatusName(String statusName) {

		return genericUIRepository.findStatusByStatusName(statusName);
	}

	@Transactional(readOnly = true)
	public LayoutType getLayoutType(String name) {
		return genericUIRepository.findLayoutTypeByName(name);
	}

	@Transactional(readOnly = true)
	public UIElementType getuiElementType(String displayText) {
		return genericUIRepository.findUIElementTypeByDisplayText(displayText);
	}

	@Transactional(readOnly = true)
	public List<ValidatorChangeTraker> getChangedValidatorsForFormDesignVersion(
			int tenantId, String formVersionNumber, int formId, Date startDate,
			Date endDate) {

		List<Integer> UIElementIDList = genericUIRepository
				.findAllUIElementIdsListForForm(tenantId, formVersionNumber,
						formId);

		List<Validator> validators = genericUIRepository
				.findValidatorsForChangedUIElements(UIElementIDList);

		List<ValidatorChangeTraker> itemsThatChanged = new ArrayList<ValidatorChangeTraker>();

		for (Validator validator : validators) {
			ValidatorChangeTraker ct = new ValidatorChangeTraker();

			ct.setValidator(validator);

			if (validator.getAddedDate().after(startDate)) {
				ct.setAction("A");
				itemsThatChanged.add(ct);
			} else if (validator.getAddedDate().after(startDate)
					&& validator.getUpdatedDate().before(endDate)) {
				ct.setAction("U");
				itemsThatChanged.add(ct);
			}

		}

		return itemsThatChanged;
	}

	@Transactional(readOnly = true)
	public FormDesignChangeTracker getChangedFormDesign(int formID,
			Date startDate, Date endDate) {
		FormDesign formDesign = genericUIRepository.findFormDesignById(formID);

		FormDesignChangeTracker itemsThatChanged = new FormDesignChangeTracker();

		if (formDesign.getAddedDate().after(startDate)) {
			itemsThatChanged.setAction("A");

		} else if (formDesign.getAddedDate().after(startDate)
				&& formDesign.getUpdatedDate().before(endDate)) {
			itemsThatChanged.setAction("U");

		}
		itemsThatChanged.setFormDesign(formDesign);

		return itemsThatChanged;
	}

	@Transactional(readOnly = true)
	public FormDesignVersionChangeTracker getChangedFormDesignVersion(
			int formDesignVersionID, Date startDate, Date endDate) {

		FormDesignVersion formDesignVersion = genericUIRepository
				.findFormDesignVersionById(formDesignVersionID);

		FormDesignVersionChangeTracker itemsThatChanged = new FormDesignVersionChangeTracker();

		if (formDesignVersion.getAddedDate().after(startDate)) {
			itemsThatChanged.setAction("A");

		} else if (formDesignVersion.getAddedDate().after(startDate)
				&& formDesignVersion.getUpdatedDate().before(endDate)) {
			itemsThatChanged.setAction("U");

		}
		itemsThatChanged.setFormDesignVersion(formDesignVersion);

		return itemsThatChanged;
	}

	@Transactional(readOnly = true)
	public DataSource getDataSourceById(int dataSourceId) {
		return genericUIRepository.findDataSourceById(dataSourceId);
	}

	@Transactional(readOnly = true)
	public UIElement getUIElementByUIElementName(String uiElemntName) {
		return genericUIRepository.findUIElementByUIElementName(uiElemntName);
	}

	@Transactional(readOnly = true)
	public Validator getValidatorsByUIElementID(int uiElementID) {
		return genericUIRepository.findValidatorByUIElementID(uiElementID);
	}

	@Transactional(readOnly = true)
	public RegexLibrary getRegexLibraryByLibraryRegexName(
			String libraryRegexName) {

		return genericUIRepository
				.findRegexLibraryByLibraryRegexName(libraryRegexName);
	}

	@Transactional
	public void saveValidators(Validator validator) {

		if (validator.getValidatorID() == null
				&& validator.getValidatorID() > 0) {

			genericUIRepository.saveData(validator);

		} else {
			genericUIRepository.updateData(validator);
		}
	}
}
