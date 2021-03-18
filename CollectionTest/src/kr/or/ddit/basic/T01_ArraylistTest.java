package kr.or.ddit.basic;


import java.util.ArrayList;
import java.util.List;



public class T01_ArraylistTest {
	public static void main(String[] args) {
		//Arraylist는 기본적인사용법이 Vector와 같다.
		//DEFAULT_CAPACITY = 10
		List list1 = new ArrayList();  //= new Vector();
		
		//add()메서드를 사용해서 데이터를 추가한다.
		list1.add("aaa");//String
		list1.add("bbb");
		list1.add(111);//int
		list1.add('k');//char
		list1.add(true);//boolean
		list1.add(12.34);//double (default) <-> float >>12.34f
		// integer 라는 숫자로111을 감싸 래핑한다(WRAPPING)
		
		
		
		//size() => 데이터의 개수
		System.out.println("size => " + list1.size());
		System.out.println("list1 => " + list1);
		
		//get으로 데이터 꺼내오기
		System.out.println("꺼내오기>");
		System.out.println("1번째 자료 : " + list1.get(1));
		
		//데이터 끼워넣기도 같다.
		list1.add(0,"zzz");
		System.out.println("끼워넣기>");
		System.out.println("list1 => " + list1);
		
		//데이터 변경하기 (set메서드)
		System.out.println("변경하기>");
		String temp = (String)list1.set(0, "YYY");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		//삭제하기
		list1.remove(0);
		System.out.println("삭제 후 : " + list1);
		
		list1.remove("bbb");
		System.out.println("bbb 삭제후 : " + list1);
		System.out.println("================================");
		
		
		// 제너릭을 지정하여 선언할 수 있다.
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("---------------------------------");
		
		
		
		//형상된 for문  
		//list.size만큼 자동적으로 그 값을 s에 저장한다.
		for(String s : list2) {
			System.out.println(s);
		}
		System.out.println("----------------------------------");
		
		// contains(비교객체); => 리스트에 '비교객체'가 있으면 true
									//없으면 false 리턴함.
		System.out.println(list2.contains("DDD")); //true
		System.out.println(list2.contains("ZZZ")); //false
		
		//toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환한다.
		//  		=> 기본적으로 Object형 배열로 변환한다.
		Object[] strArr = list2.toArray();
		System.out.println("배열의 갯수 : " + strArr.length);
		
		// 리스트의 제너릭타입에 맞는 자료형의 배열로 변환하는 방법
		// 제너릭 타입의 0개짜리 배열을 생성해서 매개변수로 넣어준다.
		// => 배열을 크기가 리스트 크기보다 작으면 리스트의 크기에 맞는 배열을 넣어준다.
		
		
		//String[] strArra2 = (String)list.toArray(); >>cast 불가
		//Object ->String
		String[] strArr2 = list2.toArray(new String[0]);
		System.out.println("strArr2의 개수 : " + strArr2.length);
		
		
		for (int i = 0; i < list2.size(); i++) {
			list2.remove(i);
			//remove 시 0번째 index가 채워지므로 list2.remove(0);
			//또는  거꾸로 끝번째index부터 지워야한다.
		}
		
		System.out.println(list2.size());
		
	
		
		List list3 = new ArrayList<Integer>();
		list3.add(1);
		list3.add(3);
		list3.add(2);
		list3.add(1);
		list3.add(2);
		list3.add(3);
		list3.add(4);
		list3.add(1);
		list3.add(1);
		
//		list3.remove(1);
		//안된다. 기본적으로 index로 값을 인지하기때문에 따라서
		list3.remove((Integer)1);//★★★★★★★★★★★★★★★★★★★★★★
		
		
		System.out.println("list3 > " + list3);
		
		
		//[스택] 후입선출 / [큐] 선입선출
		
	}
}
