package com.db.operation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONObject;

import com.db.jdbc.DBConn;
import com.utils.Log;

/**
 * @author Boris
 * @description ��ɾ�Ĳ�
 * 2016��8��10��
 */
public class CRUD {
	private Connection conn = null;
	private Statement st = null;
	
	public ResultSet find(String sql){
		conn = DBConn.getConn();
		
		ResultSet rs = null;
		try {
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.out.error(e);
			e.printStackTrace();
		}
		
//		printSearchContent(rs, "��ѯ����Ϣ");
		
		return rs;
	}
	
	public void close(){
		try {
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.out.error(e);
			e.printStackTrace();
		}
		
	}
	
	public void update(String sql){
		Connection conn = DBConn.getConn();
		Statement st = null;;
		try {
			 st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.out.error(e);
			e.printStackTrace();
	
		} finally{
			try {
				if (st != null) {
					st.close();
				}
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Log.out.error(e);
				e.printStackTrace();
			}
		}
	}
	
	public void instert(String sql){
		update(sql);
	}
	
	public void printSearchContent(ResultSet rs, String description){
		//��ȡ����Ϣ
		ResultSetMetaData m;
		int colNum = 0;
		try {
			m = rs.getMetaData();
			colNum = m.getColumnCount();
			
			String content = description + "�� ";
			// ��ʾ�������
			while (rs.next()) {
				for (int i = 1; i <= colNum; i++) {
					content += rs.getString(i) + " | ";
				}
				System.out.println(content);
			}
			
			rs.beforeFirst();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.out.error(e);
			e.printStackTrace();
		}
	}
}
