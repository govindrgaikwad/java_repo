package com.tmg.ebscore.dto;


public enum Level {

	MASTERLIST, TEMPLATE, PACKAGE, PACKAGEVERSION, BENEFITOFFERING;

	public String value() {
		return name();
	}

	public static Level fromValue(String v) {
		return valueOf(v);
	}

	public static Level fromValue(int value) {
		Level result = null;

		switch (value) {
		case 1:
			result = Level.MASTERLIST;
			break;
		case 2:
			result = Level.TEMPLATE;
			break;
		case 3:
			result = Level.PACKAGE;
			break;
		case 4:
			result = Level.PACKAGEVERSION;
			break;
		case 5:
			result = Level.BENEFITOFFERING;
			break;

		default:
			result = null;
			break;
		}

		return result;
	}

	public static int getLevelIdentifier(Level level) {
		if (level == Level.MASTERLIST) {
			return 1;
		}
		if (level == Level.TEMPLATE) {
			return 2;
		}
		if (level == Level.PACKAGE) {
			return 3;
		}
		if (level == Level.PACKAGEVERSION) {
			return 4;
		}
		if (level == Level.BENEFITOFFERING) {
			return 5;
		}

		return -1;

	}

}
