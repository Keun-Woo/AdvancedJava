package Assignment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
	static String input1 ="";
	static String input2 ="";
	static int input =0;
	private static Scanner sc;
	private static Map<String, String> room;
	private static String[] roomnum= {"401","402","403","404","301","302","303",
			"304","201","202","203","204","101","102","103","104"};
	public Hotel(){
		sc = new Scanner(System.in);
		room = new HashMap<>();
	}
	public void hotelStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
	}
	
	public static void main(String[] args) {
		new Hotel().hotelStart();
		while(true) {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 \t2.체크아웃 \t3.객실상태 \t4.업무종료");
		System.out.print(">");
		input = Integer.parseInt(sc.nextLine());
		switch (input) {
		case 1:checkIn();break;
		case 2:checkOut();break;
		case 3:checkRoom();break;
		case 4:
			System.out.println("**************************");
			System.out.println("호텔 문을 닫았습니다.");
			System.out.println("**************************");
			System.out.println();
		System.exit(0);
		default:
			System.out.println("다시 입력해주세요");
			break;
		}
	}
	}
	private static void checkOut() {
		showroom();
		Scanner sc =new Scanner(System.in);
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print(">");
		input1 =sc.nextLine();
		if(Arrays.asList(roomnum).contains(input1)) {
			if(room.remove(input1)==null) {
				System.out.println(input1+"번 방에는 체크인한 사람이 없습니다.");
			}else {
				System.out.println(input1+"번 방 체크아웃 완료했습니다.");
			}
		}else {
			System.out.println(input1+"이라는 방은 없습니다.");
		}
	
		
	}
	private static void showroom() {
		Set<String> keySet = room.keySet();
		System.out.println("-----------------------------------------------------------");
		for (int i = 0; i <	4; i++) {
			for (int j = 0; j < 4; j++) {
				if(room.get(roomnum[i*4+j])!=null) {
					System.out.print(roomnum[i*4+j]+"호[■]\t\t");
				}else {
					System.out.print(roomnum[i*4+j]+"호[□]\t\t");
				}
			}
			System.out.println();
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------");
	}
	private static void checkRoom() {
		Set<String> keySet = room.keySet();
		System.out.println("=========투숙객명단=========");
		System.out.println("방번호(호)\t\t투숙객성함");
		System.out.println("=========================");
		if(keySet.size()==0) {
			System.out.println("전부 빈방입니다");
		}else {
			for(String key : keySet) {
				System.out.println(key+"\t\t"+room.get(key));
			}
		}
		
	}
	private static void checkIn() {
		showroom();
		Scanner sc = new Scanner(System.in);
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print(">");
		input1 = sc.nextLine();
		if(Arrays.asList(roomnum).contains(input1)) {
			System.out.println("체크인 하시는분의 성함이 어떻게 되시나요?");
			System.out.print(">");
			input2 = sc.nextLine();
			if(room.get(input1)!=null) {
				System.out.println(input+"방은 이미 사람이 있습니다.");
			}
			room.put(input1, input2);
			System.out.println("체크인 되었습니다.");
		}else {
			System.out.println(input1+"이라는 방은 없습니다.");
		}
	
		
		
	}

}

