package com.tmg.uifwk.util;

import com.tmg.uifwk.orm.ui.CalendarUIElement;
import com.tmg.uifwk.orm.ui.DropDownUIElement;
import com.tmg.uifwk.orm.ui.RadioButtonUIElement;
import com.tmg.uifwk.orm.ui.RepeaterUIElement;
import com.tmg.uifwk.orm.ui.SectionUIElement;
import com.tmg.uifwk.orm.ui.TabUIElement;
import com.tmg.uifwk.orm.ui.TextBoxUIElement;
import com.tmg.uifwk.orm.ui.UIElement;

public class UIElementUtils {

	public static String getUIElementType(Class<? extends UIElement> clazz) {
		return clazz.getSimpleName();
	}

	public static UIElement getUIElementOfGivenType(String uiElementType)
			throws Exception {
		if (uiElementType == null || uiElementType.equals("")) {
			throw new Exception("Unknown Element Type");
		}
		if (uiElementType.equals("SectionUIElement")) {
			return new SectionUIElement();
		}
		if (uiElementType.equals("RepeaterUIElement")) {
			return new RepeaterUIElement();
		}
		if (uiElementType.equals("TabUIElement")) {
			return new TabUIElement();
		}
		if (uiElementType.equals("CalendarUIElement")) {
			return new TextBoxUIElement();
		}
		if (uiElementType.equals("CheckBoxUIElement")) {
			return new CalendarUIElement();
		}
		if (uiElementType.equals("DropDownUIElement")) {
			return new DropDownUIElement();
		}
		if (uiElementType.equals("RadioButtonUIElement")) {
			return new RadioButtonUIElement();
		}

		if (uiElementType.equals("TextBoxUIElement")) {
			return new TextBoxUIElement();
		}

		return null;
	}

}
