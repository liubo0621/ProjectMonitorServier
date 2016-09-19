package com.utils;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author Boris
 * @description 
 * 2016��9��7��
 */
public class Tools {
	public static Tools getTools(){
		return new Tools();
	}
	
    ///////////////////////////////////////////////////
	
	public String camelToUnderline(String param){  
	       if (param==null||"".equals(param.trim())){  
	           return "";  
	       }  
	       int len=param.length();  
	       StringBuilder sb=new StringBuilder(len);  
	       for (int i = 0; i < len; i++) {  
	           char c=param.charAt(i);  
	           if (Character.isUpperCase(c)){  
	               sb.append("_");  
	               sb.append(Character.toLowerCase(c));  
	           }else{  
	               sb.append(c);  
	           }  
	       }  
	       return sb.toString();  
	}
	
	/**
	 * @Method: formateSqlValue 
	 * @Description: ��ʽ��sql ������� values �����ֵ  �ַ����Զ��ӵ�����
	 * @param value ��ʽ�����ֵ
	 * @return
	 * String
	 */
	public String formateSqlValue(Object value){
		String sqlValue = null;
		try {
			sqlValue = getPrettyNumber(Double.parseDouble(value.toString()));
		} catch (Exception e) {
			// TODO: handle exception
			sqlValue = "'" + value +"'";
		}
		sqlValue = reflectOldStr(sqlValue);
		return sqlValue;
	}
	
	/**
	 * @Method: reflectOldStr 
	 * @Description: ����ԭ����str �� strֵΪD:/xxx/xxx �򷵻�D://xxx//xxx//xxx
	 * @param str
	 * @return
	 * String
	 */
	private String reflectOldStr(String str) {
		if (str == null || "".equals(str.trim())) {
			return "";
		}
		int len = str.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (c == '\\') {
				sb.append("\\");
				sb.append(c);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * @Method: getPrettyNumber 
	 * @Description:  ȥ���������
	 * @param number
	 * @return
	 * String
	 */
	public static String getPrettyNumber(double number) {  
	    return number == 0 ? String.valueOf(0) : BigDecimal.valueOf(number).stripTrailingZeros().toPlainString();  
	}
	
	///////////////////////////////////////////////////
	
	private static String OS = System.getProperty("os.name").toLowerCase();  
	
	public boolean isLinux(){  
        return OS.indexOf("linux")>=0;  
    }
	
	public boolean isMacOS() {
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0&& OS.indexOf("x") < 0;
	}  
	
	public boolean isWindows(){  
	     return OS.indexOf("windows")>=0;  
    } 
	
	 /** 
     * @Method: getLocalIP 
     * @Description: ���ر�����ַ
     * @return
     * String
     */ 
    public String getLocalIP(){
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return ip;
	}
	
    ///////////////////////////////////////////////////
	
    //��ȡproperties�ļ�
    static Properties pps = new Properties();
    static{
		try {
			String path = Thread.currentThread().getContextClassLoader().getResource("service_config.properties").getPath();
			pps.load(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public String getProperty(String key) {
		return pps.getProperty(key).trim();
	}
	
    ///////////////////////////////////////////////////
	
	public void writeFile(final String fileName, final String content){
		try {
			//�ж�Ŀ¼�Ƿ���� �����ڴ���
    		File file = new File(fileName);
    		if (!file.getParentFile().exists()) {
    			file.getParentFile().mkdirs();
    		}
    		
    		FileWriter out = new FileWriter(fileName, false);
			out.write(content);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
 
    public String readFile(final String fileName){
    	String str = "";
    	try {
    		File file = new File(fileName);
    		if (file.exists()) {
    			FileInputStream is = new FileInputStream(file);
    			byte[] buffer = new byte[1024];
    			int byteRead;
    			while((byteRead = is.read(buffer)) != -1){
    				str += new String(buffer, 0, byteRead);
    			}

    			is.close();
//    			file.delete();
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return str;
    }
    
    ///////////////////////////////////////////////////
    
    public String getCurrentTime(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return df.format(new Date());
    }
    
    public long getBetweenCurrrentTime(String oldTime){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date writeDate = null;
		try {
			writeDate = df.parse(oldTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date currentDate = new Date();
		long betweenTime = currentDate.getTime() - writeDate.getTime();
		
		return betweenTime / 1000L;
    }
    
    /**
     * @Method: getBetweenTime 
     * @Description: ȡʱ����  �� �� �� ʱ �� ��
     * @param time ���������
     * @return P1Y3M3DT2H2M2S ��ʽ
     * String
     */
    public String getBetweenTime(long time){
    	long second = time % 60;
		long minute = ((time - second) / 60) % 60;
		long hour = ((time - minute * 60 - second) / (60 * 60)) % 24;
		long day = ((time - hour * 60 * 60 - minute * 60 - second) / (60 * 60 * 24));
		long year = day / 365;
		long month = (day - year * 365) / 30;
		day = day - year * 365 - month * 30;
		
//		System.out.println(String.format("%d �� %d ��  %d �� %d ʱ %d �� %d ��", year, month, day, hour, minute, second));
		//"P3Y6M4DT12H30M5S"
		String betweenTime = String.format("P%dY%dM%dDT%dH%dM%dS", year, month, day, hour, minute, second);
    	return betweenTime;
    }
    
    public static void main(String[] args) {
		long time = 1 * 365 * 24 * 60 * 60 + 3 * 30 * 24 * 60 * 60 + 3 * 24 * 60 * 60 + 2 * 60 * 60 + 2 * 60 + 2;//1y2m3d2h2m2s
		System.out.println(String.format("time%d", time));
		String btw = Tools.getTools().getBetweenTime(time);	
		
		System.out.println(btw);
		
	}

    ///////////////////////////////////////////////////
}
