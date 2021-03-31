package Assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Horse2RaceExam {
	public static int rank = 1; // 현재 말의 순위
	public static List<Horse2> Horse2List = new ArrayList<Horse2>(); // 말관리를 위한 리스트 변수 선언

	public static void main(String[] args) {

		Horse2List.add(new Horse2("01번말"));
		Horse2List.add(new Horse2("02번말"));
		Horse2List.add(new Horse2("03번말"));
		Horse2List.add(new Horse2("04번말"));
		Horse2List.add(new Horse2("05번말"));
		Horse2List.add(new Horse2("06번말"));
		Horse2List.add(new Horse2("07번말"));
		Horse2List.add(new Horse2("08번말"));
		Horse2List.add(new Horse2("09번말"));
		Horse2List.add(new Horse2("10번말"));

		Horse2PositionDisplay hpd = new Horse2PositionDisplay();
		hpd.start();

		for (int i = 0; i < Horse2List.size(); i++) {
			Horse2List.get(i).start();
		}

		try {
			hpd.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		System.out.println();
		System.out.println("지금 경마가 종료되었습니다.!!!");
		System.out.println();

		Collections.sort(Horse2List); // 리스트를 순위 오름차순으로 정렬하기

		System.out.println("================");
		System.out.println("   경마 순위    ");
		System.out.println("================");
		for (int i = 0; i < Horse2List.size(); i++) {
			System.out.println(Horse2List.get(i).getHorse2Rank() + "등" + " == >" + Horse2List.get(i).getHorse2Name());
		}

	}
}

class Horse2 extends Thread implements Comparable<Horse2> {
	private String Horse2Name; // 말이름
	private int Horse2Rank; // 순위
	private int Horse2Posi; // 위치

	/**
	 * 생성자
	 *
	 * @param Horse2Name 경주말 이름
	 */
	public Horse2(String Horse2Name) {
		this.Horse2Name = Horse2Name;
	}

	public String getHorse2Name() {
		return Horse2Name;
	}

	public void setHorse2Name(String Horse2Name) {
		this.Horse2Name = Horse2Name;
	}

	public int getHorse2Rank() {
		return Horse2Rank;
	}

	public void setHorse2Rank(int Horse2Rank) {
		this.Horse2Rank = Horse2Rank;
	}

	public int getHorse2Posi() {
		return Horse2Posi;
	}

	private void setHorse2Posi(int Horse2Posi) {
		this.Horse2Posi = Horse2Posi;
	}

	@Override
	public int compareTo(Horse2 o) {
		return Integer.compare(Horse2Rank, o.getHorse2Rank()); // 순위 오름차순으로 정렬하도록 함.
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep((int) (Math.random() * 500)); // 0~49 까지의 난수 발생(구간 사이의 시간 딜레이를 주기 위함)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setHorse2Posi(i); // 각 구간으로 말의 위치 이동
		}
		this.Horse2Rank = Horse2RaceExam.rank;
		Horse2RaceExam.rank++;
	}
}


/**
 * 전체 말의 위치를 출력하기 위한 스레드 클래스
 * @author macween
 *
 */
class Horse2PositionDisplay extends Thread {

	/**
	 * 화면 출력 정리를 위한 메서드
	 */
	public void clear() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}

	@Override
	public void run() {

		while (true) {

			clear(); // 화면지우기(화면 정렬용)

			int finishedCnt = 0; // 도착한 말의 수
			System.out.println("지금 경마가 시작되었습니다.!!!");
			System.out.println("==========================================================");
			System.out.println();

			for (int i = 0; i < Horse2RaceExam.Horse2List.size(); i++) {

				String Horse2Course = "--------------------------------------------------"; // 50구간
				Horse2 Horse2 = Horse2RaceExam.Horse2List.get(i);

				if (Horse2.getHorse2Posi() != 49) { // 아직 도착하지 않은 경우...
					System.out.print(Horse2.getHorse2Name() + " : ");
					System.out.print(Horse2Course.substring(0, Horse2.getHorse2Posi()) + ">");
					System.out.println(Horse2Course.substring(Horse2.getHorse2Posi(), 49));
				} else { // 도착한 경우...
					System.out.print(Horse2.getHorse2Name() + " : ");
					System.out.print(Horse2Course.substring(0, Horse2.getHorse2Posi()) + "도착");
					System.out.println();

					finishedCnt++; // 도착한 말수 증가시키기
				}
			}

			if (finishedCnt == 10) { // 모든 경주말이 도착한 경우 ...
				return; // 쓰레드 종료
			}

			try {
				Thread.sleep(1000); // 1초마다 화면 출력
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
