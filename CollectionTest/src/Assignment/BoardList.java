package Assignment;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class BoardList {
	public static void main(String[] args) {
		List<Student> StudentList = new ArrayList<Student>();
		String[] name = {"강건우","박상영","이휘로","최윤성","공슬기"};
			StudentList.add(new Student("5",name[0],
					(int)(Math.random()*101),
					(int)(Math.random()*101),
					(int)(Math.random()*101)
					));
			StudentList.add(new Student("9",name[1],
					(int)(Math.random()*101),
					(int)(Math.random()*101),
					(int)(Math.random()*101)
					));
			StudentList.add(new Student("2",name[2],
					(int)(Math.random()*101),
					(int)(Math.random()*101),
					(int)(Math.random()*101)
					));
			StudentList.add(new Student("1",name[3],
					(int)(Math.random()*101),
					(int)(Math.random()*101),
					(int)(Math.random()*101)
					));
			StudentList.add(new Student("7",name[4],
					(int)(Math.random()*101),
					(int)(Math.random()*101),
					(int)(Math.random()*101)
					));
			
			for(Student stu1: StudentList) {
				int rank=2;
				for(Student stu2 : StudentList) {
					if(stu1.getSum()<stu2.getSum()) {
						stu1.setRank(rank++);
					}
					
				}
			}
			
			
			
			for(Student stu: StudentList) {
		System.out.println(stu);
		}
		System.out.println("==================================================");
		Collections.sort(StudentList);
		for(Student stu: StudentList) {
			System.out.println(stu);	
		}
		Collections.sort(StudentList, new SortSumAsc());
		System.out.println("번호의 내림차순으로 정렬 후 ");
		for(Student stu: StudentList) {
			System.out.println(stu);
		}
		System.out.println("==================================================");
		}
	}

class SortSumAsc implements Comparator<Student>{
	
	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getSum()>stu2.getSum()) {
			return 1;
		}else if(stu1.getSum()==stu2.getSum()) {
			return stu1.getNum().compareTo(stu2.getNum())*-1;
		}else {
			return -1;
		}
//		return new Integer(stu1.getSum())
//				.compareTo(stu2.getSum())*-1;
	}
	
}



class Student implements Comparable<Student>{
	private String num;
	private String name;
	private int kor;
	private int math;
	private int eng;
	private int sum;
	private int rank;
	
	public Student(String num,String name, int kor, int math, int eng) {
		super();
		this.num=num;
		this.name=name;
		this.kor=kor;
		this.math=math;
		this.eng=eng;
		this.sum=eng+kor+math;
		this.rank=1;
	}
	
	

	public String getNum() {return num;}
	public void setNum(String num) {this.num = num;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getKor() {return kor;}
	public void setKor(int kor) {this.kor = kor;}
	public int getMath() {return math;}
	public void setMath(int math) {this.math = math;}
	public int getEng() {return eng;}
	public void setEng(int eng) {this.eng = eng;}
	public int getSum() {return sum;}
	public void setSum(int sum) {this.sum = sum;}
	public int getRank() {return rank;}
	public void setRank(int rank) {this.rank = rank;}	

	
	

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", math=" + math + ", eng=" + eng + ", sum="
				+ sum + ", rank=" + rank + "]";
	}


	@Override
	public int compareTo(Student stu) {
		
	  return this.getNum().compareTo(stu.getNum());
	  
	
	}
}