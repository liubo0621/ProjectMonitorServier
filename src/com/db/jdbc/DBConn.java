package com.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.utils.Tools;

/**
 * @author Boris
 * @description �������ݿ� oracle
 * 2016��8��10��
 */
public class DBConn {
	private static Tools tools = Tools.getTools();
	private static Connection conn = null;
	
	private final static String DRIVER = tools.getProperty("db.driver");
	private final static String URL = tools.getProperty("db.url");
	private final static String USERNAME = tools.getProperty("db.username");
	private final static String PASSWORD = tools.getProperty("db.password");
	
	public static Connection getConn(){
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection conn = DBConn.getConn();
		if (conn != null) {
			System.out.println("�������ӳɹ�");
		}
	}
}
