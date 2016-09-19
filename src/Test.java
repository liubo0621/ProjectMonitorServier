import java.math.BigDecimal;

import com.utils.Tools;


/**
 * @author Boris
 * @description 
 * 2016Äê9ÔÂ18ÈÕ
 */
public class Test {
	public static String getPrettyNumber(double number) {  
	    return number == 0 ? 0+"" :BigDecimal.valueOf(number)  
	            .stripTrailingZeros().toPlainString();  
	}
	
	public static void main(String[] args) {
		String sql = "C:/xxx";
		sql = "'" + sql + "'";
		System.out.println(sql);
		
//		Tools tools = Tools.getTools();
//		String str = "\\xxx";
//		str = tools.formateSqlValue(str);
//		System.out.println(str);
//		System.out.println(str.length());
	}
}


//UPDATE test_tb set name = 'liubo', record_time = CURRENT_TIMESTAMP where id=1;
