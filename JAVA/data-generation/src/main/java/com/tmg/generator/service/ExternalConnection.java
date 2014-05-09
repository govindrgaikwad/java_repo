package com.tmg.generator.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("ExternalConnection")
public class ExternalConnection {

	private static Logger logger = Logger.getLogger(ExternalConnection.class);

	public static Connection getConnection() throws DataAccessException {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.100.46;database=eBenefitSyncMaster",
					"ebs", "ebs@123");
		} catch (SQLException e) {
			logger.error("Error creating connection" + e.getMessage(), e);
			throw new DataAccessException("Error creating connection"
					+ e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error("Driver Class Not Found " + e.getMessage(), e);
			throw new DataAccessException("Driver Class Not Found "
					+ e.getMessage(), e);
		}
		return conn;
	}

}
