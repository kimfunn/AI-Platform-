package exam1;

import java.util.ArrayList;


public class BookTest {
	   public static void main(String [] args) {
		   
		   
		   ArrayList<BookDTO> list;
		   
		   list = new ArrayList<BookDTO>();
		   
		   list.add( new BookDTO(21424, "Java Basic","���ϳ�","Jaen.kr",15000,"Java �⺻ ����"));
	       list.add( new BookDTO(33455, "JDBC pro","��ö��","Jaen.kr",23000,""));
	       list.add( new BookDTO(55355, "Sevlet/JSP","���ڹ�","Jaen.kr",41000,"Model2���"));
	       list.add( new BookDTO(35332, "Android APP","ȫ�浿","Jaen.kr",25000,"Lightweight Framework"));
	       list.add( new BookDTO(35355, "OOAD �м�,����","�ҳ���","Jaen.kr",30000,""));
	      
     System.out.println("*************************************** ���� ��� *******************************************");
        for(int i=0; i<list.size(); i++){
        	Object aa = list.get(i);      	
        	System.out.println(aa);
        	
        }
	   
	   }
}
	   
                              
