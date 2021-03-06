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
 * 2016年9月7日
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
	 * @Description: 格式化sql 插入语句 values 后面的值  字符串自动加单引号
	 * @param value 格式化后的值
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
	 * @Description: 反射原来的str 如 str值为D:/xxx/xxx 则返回D://xxx//xxx//xxx
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
	 * @Description:  去除多余的零
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
     * @Description: 返回本机地址
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
	
    //读取properties文件
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
			//判断目录是否存在 不存在创建
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
     * @Description: 取时间间隔  年 月 日 时 分 秒
     * @param time 间隔的秒数
     * @return P1Y3M3DT2H2M2S 格式
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
		
//		System.out.println(String.format("%d 年 %d 月  %d 日 %d 时 %d 分 %d 秒", year, month, day, hour, minute, second));
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
