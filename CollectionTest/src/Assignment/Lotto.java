package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Lotto {
 static int money=1000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int input = Integer.parseInt(sc.nextLine());
		int input = 0;

		while(true) {
	System.out.println("==================================");	
	System.out.println("Lotto 프로그램");
	System.out.println("----------------------------------");	
	System.out.println("1. Lotto 구입");
	System.out.println("2. 프로그램 종료");
	System.out.println("==================================");	
	input = Integer.parseInt(sc.nextLine());
	switch (input) {
	case 1:lotto();
	break;
	case 2:
	System.out.println("감사합니다");
	System.exit(0);
	default:
		System.out.println("다시 입력해주세요");
		break;
	}
		}
	}

	private static void lotto() {//로또구입
		Scanner sc = new Scanner(System.in);
	System.out.println("Lotto를 구입 합니다.");
	System.out.println("1000원에 로또번호 하나입니다.");
	System.out.println("금액을 입력해주세요 >");
	int input =Integer.parseInt(sc.nextLine());
	if(money>=input&&input>=6000) {
		System.out.println("행운의 로또 번호는 아래와 같습니다.");
		for (int i = 0; i < input/6000; i++) {
			System.out.print("로또번호"+(i+1)+" : ");
			Number();
		}
		money = money-6000*(input/6000);//통장잔액
		System.out.println("받은 금액은 "+input+"이고 거스름돈은 "+(input-6000*(input/6000)));
		System.out.println("잔액은 " + money+ "입니다.");
		
	}else if(money<input||input<6000){
		System.out.println("금액이 모자랍니다.");
	}
}
	
	
		static void Number() {//로또번호추출
		HashSet<Integer> selectnum = new HashSet<Integer>();
		while(selectnum.size()<6) {
			int k = (int)(Math.random()*45)+1;
			selectnum.add(k);
		}
		List<Integer> number = new ArrayList<>(selectnum);
		Collections.shuffle(number);
		System.out.println(number);
	}
}


