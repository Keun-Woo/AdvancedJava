package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BaseBall {
	public static void main(String[] args) {
		HashSet<Integer> num = new HashSet<Integer>();
		while(num.size()<3) {
			int k = (int)(Math.random()*9)+1;
			num.add(k);
		}
		
		List<Integer> number = new ArrayList<>(num);
		Collections.shuffle(number);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("game start!");
		System.out.println();
		int reS=0; 
		int reB=0;
		int reO=0;
		
		
		
		while(reS!=3){
			reS=0; 
			reB=0;
			reO=0;
		
	
		
		System.out.println("첫번째숫자를 말씀하시오.");
		int ans1=Integer.parseInt(sc.nextLine());
		System.out.println("두번째숫자를 말씀하시오.");
		int ans2=Integer.parseInt(sc.nextLine());
		System.out.println("세번째숫자를 말씀하시오.");
		int ans3=Integer.parseInt(sc.nextLine());
//		추출한 변수가 입력한 숫자와 같은지
		
		if (ans1 == number.get(0)) {
			reS++;
		} else if (ans1 == number.get(1) || ans1 == number.get(2)) {
			reB++;
		} else {
			reO++;
		}
		
		
		if (ans2 ==number.get(1)) {
			reS++;
		} else if (ans2 == number.get(0) || ans2 == number.get(2)) {
			reB++;
		} else {
			reO++;
		}
		
		if (ans3 ==number.get(2)) {
			reS++;
		} else if (ans3 == number.get(0) || ans3 == number.get(1)) {
			reB++;
		} else {
			reO++;
		}
		
		System.out.println(reS+"S"+"   "+reB+"B"+"   "+reO+"O");
		if( reS==3){System.out.println("축하드립니다!!!!!!!!");}
		}
		
	}
	
	
	
	
	
}
