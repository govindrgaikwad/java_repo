package com.tmg.uifwk.orm;

import java.sql.Types;

import org.hibernate.type.StandardBasicTypes;

public class SQLServer2008Dialect extends
		org.hibernate.dialect.SQLServer2008Dialect {

	public SQLServer2008Dialect() {
		super();
		registerColumnType(Types.NVARCHAR, 255, "nvarchar($l)");
		registerHibernateType(Types.NVARCHAR,
				StandardBasicTypes.STRING.getName());

		registerColumnType(Types.NCHAR, "nchar(1)");
		registerHibernateType(Types.NCHAR,
				StandardBasicTypes.CHARACTER.getName());

		registerColumnType(Types.NCLOB, "nvarchar(max)");
		registerHibernateType(Types.NCLOB, StandardBasicTypes.CLOB.getName());
	}

}
