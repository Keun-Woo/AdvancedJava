package Assignment;

public class enumPlanet {
		//열거형 객체 선언
	public enum Planet{
		수성(2439)//km
		,금성(6052)
		,지구(6371)
		,화성(3390)
		,목성(69911)
		,토성(58232)
		,천왕성(25362)
		,해왕성(24622);
	
	//괄호속 값이 저장될 변수 선언
	private int rad;
	
	//생성자 만들기
	Planet(int data){
		rad= data;
	}
	
	//값을 반환하는 메서드 작성
	public int getRad() {
		return rad;
	}
	}	
	
	
	//면적 = (4*(반지름)^2*π)km^2
	public static void main(String[] args) {
		//모든데이터를 for문돌려서 getRad()메서드를이용하여 반지름을가져온다.
		 for (Planet planet : Planet.values()) {
			System.out.println(planet +" : (" +4*Math.pow(planet.getRad(),2)+")πkm^2");
		}
	}
	
	
	
}

