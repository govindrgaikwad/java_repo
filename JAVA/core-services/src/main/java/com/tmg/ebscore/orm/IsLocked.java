package com.tmg.ebscore.orm;

public enum IsLocked {

	INT, STRING, LIMIT, MESSAGE, SERVICEDEFINITION, ACCUMULATION, INTTEMP, STRINGTEMP, LIMITTEMP, MESSAGETEMP;

	public String value() {
		return name();
	}

	public static IsLocked fromValue(String v) {
		return valueOf(v);
	}

	public static String fromValue(int value) {
		String result = null;

		switch (value) {
		case 1:
			result = "{call usp_GetAccumulationsVal(?,?)}";
			break;
		case 2:
			result = "{call usp_GetIntVal(?,?)}";
			break;
		case 3:
			result = "{call usp_GetLimitVal(?,?)}";
			break;
		case 4:
			result = "{call usp_GetMessageVal(?,?)}";
			break;
		case 5:
			result = "{call usp_GetServiceDefinitionVal(?,?)}";
			break;
		case 6:
			result = "{call usp_GetStringVal(?,?)}";
			break;
		case 7:
			result = "{call usp_GetTemplateIntValAssociation(?,?,?)}";
			break;
		case 8:
			result = "{call usp_GetTemplateLimitValAssociation(?,?,?)}";
			break;
		case 9:
			result = "{call usp_GetTemplateMessageValAssociation(?,?,?)}";
			break;

		case 10:
			result = "{call usp_GetTemplateStringValAssociation(?,?,?)}";
			break;
		default:
			result = null;
			break;
		}

		return result;
	}

	public static int getStoredProcedure(IsLocked isLocked) {
		if (isLocked == IsLocked.ACCUMULATION) {
			return 1;
		}
		if (isLocked == IsLocked.INT) {
			return 2;
		}
		if (isLocked == IsLocked.LIMIT) {
			return 3;
		}
		if (isLocked == IsLocked.MESSAGE) {
			return 4;
		}
		if (isLocked == IsLocked.SERVICEDEFINITION) {
			return 5;
		}
		if (isLocked == IsLocked.STRING) {
			return 6;
		}
		if (isLocked == IsLocked.INTTEMP) {
			return 7;
		}
		if (isLocked == IsLocked.LIMITTEMP) {
			return 8;
		}
		if (isLocked == IsLocked.MESSAGETEMP) {
			return 9;
		}

		if (isLocked == IsLocked.STRINGTEMP) {
			return 10;
		}

		return -1;

	}

}
