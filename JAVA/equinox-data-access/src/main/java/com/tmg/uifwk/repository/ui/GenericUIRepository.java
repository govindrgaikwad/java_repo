package com.tmg.uifwk.repository.ui;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

public interface GenericUIRepository extends JpaRepository<UIElement, Long>,
		GenericUICustomRepository {

	@Query(value = "SELECT list from UIElement list "
			+ "LEFT JOIN FETCH list.childElements children "
			+ "LEFT JOIN FETCH list.parentUIElement "
			+ "LEFT JOIN FETCH list.applicationDataType "
			+ "WHERE list.UIElementID = :id")
	public UIElement getUIElement(@Param("id") int id);

	@Query(value = "SELECT list from UIElement list "
			+ "LEFT JOIN list.formDesignVersionUielementMaps UIElementMap "
			+ "WHERE UIElementMap.formDesignVersion.formDesignVersionID = :formDesignVersionId "
			+ "AND UIElementMap.formDesignVersion.formDesign.tenantID = :tenantId "
			+ "AND list.parentUIElement IS NULL")
	public UIElement getRootUIElementForFormDesignVersion(
			@Param("tenantId") int tenantId,
			@Param("formDesignVersionId") int formDesignVersionId);

	@Query(value = "SELECT list from Validator list "
			+ "WHERE list.uielement.UIElementID = :uiElementId ")
	public List<Validator> getValidatorsForUIElement(
			@Param("uiElementId") int uiElementId);

	@Query(value = "SELECT list from UIElement list "
			+ "LEFT JOIN FETCH list.formDesignVersionUielementMaps UIElementMap "
			+ "LEFT JOIN FETCH list.parentUIElement "
			+ "LEFT JOIN FETCH list.applicationDataType "
			+ "LEFT JOIN FETCH list.formDesign "
			+ "WHERE UIElementMap.formDesignVersion.versionNumber = :formVersionNumber "
			+ "AND UIElementMap.formDesignVersion.formDesign.tenantID = :tenantId "
			+ "AND UIElementMap.formDesignVersion.formDesign.formID = :formId "
			+ "AND UIElementMap.formDesignVersion.formDesign.active = true order by list.container")
	public List<UIElement> getUIElementsForFormDesignVersion(
			@Param("tenantId") int tenantId,
			@Param("formVersionNumber") String formVersionNumber,
			@Param("formId") int formId);

	@Query(value = "SELECT formDesignVersion from FormDesignVersion formDesignVersion "
			+ "left join fetch formDesignVersion.formDesign "
			+ "left join fetch formDesignVersion.status "
			+ "WHERE formDesignVersion.formDesignVersionID = :formDesignVersion ")
	public FormDesignVersion getFormDesignVersionById(
			@Param("formDesignVersion") int formDesignVersion);

	@Query(value = "SELECT list from UIElement list "
			+ "LEFT JOIN FETCH list.parentUIElement parent "
			+ "WHERE list.UIElementName = :elementName "
			+ "AND parent.UIElementName = :parentName")
	public UIElement findUIElementForParentNotNull(
			@Param("elementName") String elementName,
			@Param("parentName") String parentName);

	@Query(value = "SELECT list from UIElement list "
			+ "WHERE list.UIElementName = :elementName")
	public UIElement findParentUIElement(
			@Param("elementName") String elementName);

	@Query(value = "SELECT list from FormDesign list "
			+ "WHERE list.formName = :formName")
	public FormDesign findFormDesignByFormName(
			@Param("formName") String formName);

	@Query(value = "SELECT list from ApplicationDataType list "
			+ "WHERE list.applicationDataTypeName = :dataTypeName")
	public ApplicationDataType findUIElementDataType(
			@Param("dataTypeName") String dataTypeName);

	@Query(value = "SELECT formDesignVersion from FormDesignVersion formDesignVersion "
			+ "left join fetch formDesignVersion.formDesign formDesign "
			+ "left join fetch formDesignVersion.status "
			+ "WHERE formDesign.formID = :formId "
			+ "AND formDesignVersion.versionNumber = :versionNumber ")
	public FormDesignVersion findFormDesignVersionByVersionNumber(
			@Param("formId") int formId,
			@Param("versionNumber") String versionNumber);

	@Query(value = "SELECT list from Status list "
			+ "WHERE list.status = :statusName")
	public Status findStatusByStatusName(@Param("statusName") String statusName);

	@Query(value = "SELECT uiElement from UIElement uiElement "
			+ "WHERE uiElement.UIElementName = :elementName "
			+ "AND uiElement.parentUIElement IS NULL")
	public UIElement findUIElementForParentIsNull(
			@Param("elementName") String elementName);

	@Query(value = "SELECT list from TabUIElement list "
			+ "left join fetch list.layoutType "
			+ "left join fetch list.uielementType "
			+ "WHERE list.UIElementID = :uiElementId")
	public TabUIElement findTabUIElementById(
			@Param("uiElementId") int uiElementId);

	@Query(value = "SELECT list from SectionUIElement list "
			+ "left join fetch list.layoutType "
			+ "left join fetch list.uielementType "
			+ "left join fetch list.dataSource "
			+ "WHERE list.UIElementID = :uiElementId")
	public SectionUIElement findSectionUIElementById(
			@Param("uiElementId") int uiElementId);

	@Query(value = "SELECT list from RepeaterUIElement list "
			+ "left join fetch list.layoutType "
			+ "left join fetch list.uielementType "
			+ "left join fetch list.dataSource "
			+ "WHERE list.UIElementID = :uiElementId")
	public RepeaterUIElement findRepeaterUIElementById(
			@Param("uiElementId") int uiElementId);

	@Query(value = "SELECT list from TextBoxUIElement list "
			+ "left join fetch list.uielementType "
			+ "WHERE list.UIElementID = :uiElementId")
	public TextBoxUIElement findTextBoxUIElementById(
			@Param("uiElementId") int uiElementId);

	@Query(value = "SELECT list from CalendarUIElement list "
			+ "left join fetch list.uielementType "
			+ "WHERE list.UIElementID = :uiElementId")
	public CalendarUIElement findCalendarUIElementById(
			@Param("uiElementId") int uiElementId);

	@Query(value = "SELECT list from CheckBoxUIElement list "
			+ "left join fetch list.uielementType "
			+ "WHERE list.UIElementID = :uiElementId")
	public CheckBoxUIElement findCheckBoxUIElementById(
			@Param("uiElementId") int uiElementId);

	@Query(value = "SELECT list from DropDownUIElement list "
			+ "left join fetch list.uielementType "
			+ "WHERE list.UIElementID = :uiElementId")
	public DropDownUIElement findDropDownUIElementById(
			@Param("uiElementId") int uiElementId);

	@Query(value = "SELECT list from RadioButtonUIElement list "
			+ "left join fetch list.uielementType "
			+ "WHERE list.UIElementID = :uiElementId")
	public RadioButtonUIElement findRadioButtonUIElementById(
			@Param("uiElementId") int uiElementId);

	@Query(value = "SELECT list from Validator list "
			+ "WHERE list.uielement.UIElementID in :uIElementIDList ")
	public List<Validator> findValidatorsForChangedUIElements(
			@Param("uIElementIDList") List<Integer> uIElementIDList);

	@Query(value = "Select list.UIElementID from UIElement list "
			+ "LEFT JOIN list.formDesignVersionUielementMaps UIElementMap  "
			+ "WHERE UIElementMap.formDesignVersion.versionNumber = :formVersionNumber "
			+ "AND UIElementMap.formDesignVersion.formDesign.tenantID = :tenantId "
			+ "AND UIElementMap.formDesignVersion.formDesign.formID = :formId "
			+ "AND UIElementMap.formDesignVersion.formDesign.active = true")
	public List<Integer> findAllUIElementIdsListForForm(
			@Param("tenantId") int tenantId,
			@Param("formVersionNumber") String formVersionNumber,
			@Param("formId") int formId);

	@Query(value = "Select list from LayoutType list "
			+ "Where list.name = :name")
	public LayoutType findLayoutTypeByName(@Param("name") String name);

	@Query(value = "Select list from UIElementType list "
			+ "Where list.displayText = :displayText")
	public UIElementType findUIElementTypeByDisplayText(
			@Param("displayText") String displayText);

	@Query(value = "SELECT list from FormDesign list "
			+ "WHERE list.formID = :formID")
	public FormDesign findFormDesignById(@Param("formID") int formID);

	@Query(value = "SELECT list from FormDesignVersion list "
			+ "WHERE list.formDesignVersionID = :formDesignVersionID")
	public FormDesignVersion findFormDesignVersionById(
			@Param("formDesignVersionID") int formDesignVersionID);

	@Query(value = "SELECT list from DataSource list "
			+ "WHERE list.dataSourceID = :dataSourceId")
	public DataSource findDataSourceById(@Param("dataSourceId") int dataSourceId);

	@Query(value = "SELECT list from UIElement list "
			+ "WHERE list.UIElementName = :uiElemntName")
	public UIElement findUIElementByUIElementName(
			@Param("uiElemntName") String uiElemntName);

	@Query(value = "SELECT list from Validator list "
			+ "WHERE list.uielement.UIElementID = :uiElementID")
	public Validator findValidatorByUIElementID(
			@Param("uiElementID") int uiElementID);

	@Query(value = "SELECT list from RegexLibrary list "
			+ "WHERE list.libraryRegexName = :libraryRegexName")
	public RegexLibrary findRegexLibraryByLibraryRegexName(
			@Param("libraryRegexName") String libraryRegexName);

}
