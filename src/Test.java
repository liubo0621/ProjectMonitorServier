import java.math.BigDecimal;

import com.utils.Tools;


/**
 * @author Boris
 * @description 
 * 2016Äê9ÔÂ18ÈÕ
 */
public class Test {
	public void a(){
		String str = "111";
		b(str);
		System.out.println(str);
	}
	
	public void b(String str){
		str = "2222";
	}
	
	public static void main(String[] args) {
		new Test().a();
		String str = "1232null";  
		String str2 = "1232" + null;
	    System.out.println("string = " + str);  
	  System.out.println(str2);
	  System.out.println(str2.equals(str));
	  System.out.println(1/0);
	}
}


//UPDATE test_tb set name = 'liubo', record_time = CURRENT_TIMESTAMP where id=1;
