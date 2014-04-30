package com.tmg.data.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class DataGenerator {

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.100.46; database=UIFramework",
					"ebs", "ebs@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		DataGenerator dataGenerator = new DataGenerator();
		Velocity.init();
		VelocityContext context = new VelocityContext();
		Template template = Velocity
				.getTemplate("/src/main/resources/Class.vm");
		context.put("PackageName", "com.tmg.data");
		List<String> tables = dataGenerator.getAllTables();
		for (String table : tables) {
			List<Attribute> attributes = dataGenerator
					.getTableAttributes(table);

			context.put("ClassName", table);
			context.put("Attributes", attributes);
			FileWriter w;
			try {
				w = new FileWriter("src/main/java/com/tmg/data/" + table
						+ ".java");
				template.merge(context, w);
				w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public List<String> getAllTables() {
		Connection connection = null;
		List<String> list = new ArrayList<String>();
		try {
			connection = this.getConnection();
			Statement st = connection.createStatement();
			String query = "SELECT name FROM sys.tables";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Attribute> getTableAttributes(String tableName) {
		Connection connection = null;

		List<Attribute> attributes = new ArrayList<Attribute>();
		try {

			connection = this.getConnection();
			Statement st = connection.createStatement();
			String query = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
					+ tableName + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Attribute attribute = new Attribute();
				attribute.setName(rs.getString(1).trim());
				String dataType = convertSQLTypesToJava(rs.getString(2));
				attribute.setDataType(dataType);
				attributes.add(attribute);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attributes;
	}

	public String convertSQLTypesToJava(String dataType) {
		if (dataType.equalsIgnoreCase("bigint")) {
			dataType = "long";
		}

		if (dataType.equalsIgnoreCase("binary")
				|| dataType.equalsIgnoreCase("timestamp")
				|| dataType.equalsIgnoreCase("udt")
				|| dataType.equalsIgnoreCase("varbinary")
				|| dataType.equalsIgnoreCase("image")) {
			dataType = "byte[]";
		}

		if (dataType.equalsIgnoreCase("bit")) {
			dataType = "boolean";
		}

		if (dataType.equalsIgnoreCase("char")
				|| dataType.equalsIgnoreCase("nchar")
				|| dataType.equalsIgnoreCase("ntext")
				|| dataType.equalsIgnoreCase("nvarchar")
				|| dataType.equalsIgnoreCase("bit")
				|| dataType.equalsIgnoreCase("text")
				|| dataType.equalsIgnoreCase("uniqueidentifier")
				|| dataType.equalsIgnoreCase("varchar")
				|| dataType.equalsIgnoreCase("xml")) {
			dataType = "String";
		}

		if (dataType.equalsIgnoreCase("date")
				|| dataType.equalsIgnoreCase("datetime")
				|| dataType.equalsIgnoreCase("datetimeoffset (2)")
				|| dataType.equalsIgnoreCase("datetime2")
				|| dataType.equalsIgnoreCase("smalldatetime")
				|| dataType.equalsIgnoreCase("time")) {
			dataType = "Date";
		}
		if (dataType.equalsIgnoreCase("decimal")
				|| dataType.equalsIgnoreCase("float")
				|| dataType.equalsIgnoreCase("money")
				|| dataType.equalsIgnoreCase("numeric")
				|| dataType.equalsIgnoreCase("real")
				|| dataType.equalsIgnoreCase("smallmoney")) {
			dataType = "double";
		}
		if (dataType.equalsIgnoreCase("int")
				|| dataType.equalsIgnoreCase("smallint")
				|| dataType.equalsIgnoreCase("tinyint")) {
			dataType = "int";
		}
		return dataType;
	}

}
