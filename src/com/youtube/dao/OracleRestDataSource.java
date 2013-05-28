package com.youtube.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OracleRestDataSource {
	
	private static DataSource OracleRestDS = null;
	private static Context context = null;
	
	public static DataSource OracleRestConn() throws Exception {
		try {
			if (context == null) {
				context = new InitialContext();
			}
			OracleRestDS = (DataSource) context.lookup("java:jboss/oraRest");
		} catch (Exception err) {
			err.printStackTrace();
		}
		return OracleRestDS;
	}
	
	
}
