package kr.or.ddit.basic;


import java.util.ArrayList;
import java.util.List;

/**
 * 와일드 카드 예제
 * 
 * <? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능.
 * <? super T> 	 =>	와일드 카드의 하한 제한. T와 그 조상들만 가능. 
 * <?> 			 =>	모든 타입이 가능<? extends Object>와 동일.
 *
 */

public class T06_WildcardTest {
	public static void main(String[] args) {
//		List<String> bbb= new ArrayList<String>();
//		List<?> aaa = new ArrayList<Integer>();
//		//=List<? extends Integer> aaa = new ArrayList<Integer>();
//		//=List<? super Integer> aaa = new ArrayList<Integer>();
		
		
		
		
		FruitBox<Fruit> fruitBox = new FruitBox<>();//과일상자
		FruitBox<Apple> appleBox = new FruitBox<Apple>();//사과상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
		
		Juicer.makeJuice(fruitBox); //과일 상자인 경우에는 아무런 문제없음
		Juicer.makeJuice(appleBox);
	}
}

class Juicer{
//	static void makeJuice(FruitBox<Fruit> box) {
//	불가능하다>>>>>>>Juicer.makeJuice(appleBox);
	//static <T /*extends Fruit*/> void makeJuice(FruitBox<T> box) {
		/*extends Fruit(아무타입만 오는것을 막아준다. Fruit타입만 받을수있도록한다.)*/
	static void makeJuice(FruitBox<? extends Fruit> box) {
	//특정한 타입에 제너릭하게
	//타입은 넣을때만 ~
		String fruitListStr ="";//과일목록
		 
		int cnt = 0;
		for (Fruit f : box.getFruitList()) {
			if(cnt==0) {
				fruitListStr += f;
			}else {
				fruitListStr +="," +f; 
			}
			cnt++;
		}
		System.out.println(fruitListStr + "=> 쥬스 완성!");
	}
}





/*
 *과일 
 * 
 */
class Fruit{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
		
	}

	@Override
	public String toString() {
		return "과일(" + name + ")";
	}

	public Fruit(String name) {
		super();
		this.name = name;
	}
}
//사과클래스
class Apple extends Fruit{
	public Apple() {
		super("사과");
	}
}
//포도클래스
class Grape extends Fruit{
	public Grape() {
		super("포도");
	}
}

//과일상자
class FruitBox<T>{
	private List<T> fruitList;
	
	public FruitBox() {
		fruitList = new ArrayList<>();
	}
	
	public List<T> getFruitList(){
		return fruitList;
	}
	
	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}