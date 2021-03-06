package board.main;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import board.service.BoardService;
import board.service.BoardServiceImpl;
import board.vo.BoardVO;




/*		위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content zclob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

 시퀀스의 다음 값 구하기
	시퀀스이름.nextVal
*/
public class BoardMain {
	// 서비스객체 멤버변수 선언
		private BoardService boardService;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		public BoardMain() {
			boardService = new BoardServiceImpl();
		}
		
		private Scanner scan = new Scanner(System.in); 
	
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체목록 출력");
		System.out.println("  2. 새 글 작성");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 자료 삭제");
		System.out.println("  5. 자료 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	
	public void start(){
		int input;
		do{
			displayMenu(); //메뉴 출력
			input = scan.nextInt(); // 메뉴번호 입력받기
			switch(input){
				case 1 :  // 전체목록 출력
					displayAll();
					break;
				case 2 :  // 새 글 작성
					insertList();
					break;
				case 3 :  // 자료 수정
					updateList();
					break;
				case 4 :  // 자료 삭제
					deleteList();
					break;
				case 5 : // 자료 검색
					searchList();
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(input!=6);
	}	
	
	private void searchList() {
		scan.nextLine(); // 입력버퍼 지우기
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		String boardno = scan.nextLine().trim();		

		System.out.print("제목 >> ");
		String boardtitle = scan.nextLine().trim();
		
		System.out.print("작성자 >> ");
		String boardwriter = scan.nextLine().trim();
		
		System.out.print("내용 >> ");
		String boardcontent = scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		if (boardno.equals("")) {
			bv.setBoardno(0);
		}else {
			bv.setBoardno(Integer.parseInt(boardno));
		}
		bv.setBoardtitle(boardtitle);
		bv.setBoardwriter(boardwriter);
		bv.setBoardcontent(boardcontent);

		// 입력한 정보로 검색한 내용을 출력하는 부분
		List<BoardVO> boardList = boardService.getSearchList(bv);
		
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println(" 번호\t제 목\t작성자\t날짜\t\t내용");
		System.out.println("--------------------------------------------");
		
		for(BoardVO bv2 : boardList) {
			System.out.println(bv2.getBoardno() 
					+ "\t" + bv2.getBoardtitle() + "\t"
							+ bv2.getBoardwriter() + "\t"
					+ bv2.getBoarddate().substring(0, 10) + "\t" + bv2.getBoardcontent());
		}
	
		System.out.println("---------------------------------------");
		System.out.println("출력 작업 끝...");
		
	}
		



	private void deleteList() {
		System.out.println();
		System.out.println("삭제할 번호를 입력하세요.");
		System.out.print("번호 >> ");
		int boardno = scan.nextInt();
		
		int cnt = boardService.deleteList(boardno);
		
		if(cnt > 0) {
			System.out.println(boardno + "번 글 삭제 작업 성공");
		}else {
			System.out.println(boardno + "번 글 삭제 작업 실패!!!");
		}
		
	}


	private void updateList() {
		boolean chk = false;
		int boardno;
		
		do {
			System.out.println();
			System.out.println("수정할 게시글 번호를 입력하세요.");
			System.out.print("번호 >> ");
			boardno = scan.nextInt();
			
			chk =  boardService.checkList(boardno);
			
			if(chk == false) {
				System.out.println("번호가 " + boardno + "인 글이 없습니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		}while(chk == false);
		
		System.out.print("제목 >> ");
		String boardtitle = scan.next();
		
		System.out.print("작성자 >> ");
		String boardwriter = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("내용 >> ");
		String boardcontent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardno(boardno);
		bv.setBoardtitle(boardtitle);
		bv.setBoardwriter(boardwriter);
		bv.setBoardcontent(boardcontent);
		
		int cnt = boardService.updateList(bv);
		
		if(cnt > 0) {
			System.out.println(boardno + "번글  수정 작업 성공");
		}else {
			System.out.println(boardno + "번글  수정 작업 실패!!!");
		}
	}
		



	private void displayAll() {
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println(" 번호\t제 목\t작성자\t날짜\t\t내용");
		System.out.println("--------------------------------------------");
		
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		
		for(BoardVO bv : boardList) {
			System.out.println(bv.getBoardno() 
					+ "\t" + bv.getBoardtitle() + "\t"
							+ bv.getBoardwriter() + "\t"
					+ (bv.getBoarddate()).substring(0, 10) + "\t" + bv.getBoardcontent());
		}
		
		System.out.println("--------------------------------------------");
		System.out.println("출력 작업 끝...");
	}

	private void insertList() {
		
		
		System.out.print("제목 >> ");
		String boardtitle = scan.next();
		
		System.out.print("작성자 >> ");
		String boardwriter = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("내용 >> ");
		String boardcontent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardtitle(boardtitle);
		bv.setBoardwriter(boardwriter);
		bv.setBoarddate("sysdate");
		bv.setBoardcontent(boardcontent);
		
		int cnt = boardService.insertList(bv);
		
		if(cnt > 0) {
			System.out.println("새 글 추가 작업 성공");
		}else {
			System.out.println("새 글 추가 작업 실패!!!");
		}
	}
	

	public static void main(String[] args) {
		BoardMain boardObj = new BoardMain();
		boardObj.start();
		






	}

}
