package day02;

import java.sql.Date;

public class Test01 {
	public static void main(String[] args) {
		// local 변수 , 명령문
		// 'A' = 65   'a' = 97
		int num = 'A';
		System.out.println(num);       
		
		// String
		java.lang.String name  = new String("홍길동");    
		
		System.out.println(name);
		System.out.println(name.charAt(0)+"**");
		System.out.println("*"+name.charAt(1)+"*");
		
		Date now = new Date();
		System.out.println(now);
		System.out.println(now.getYear());
		
		java.sql.Date now1 = new java.sql.Date(System.currentTimeMillis()) ;
		System.out.println(now1.getYear());
		
		name = null;
		now = null;
		now1 = null;
		
		System.gc();
		System.out.println();
	}
}
