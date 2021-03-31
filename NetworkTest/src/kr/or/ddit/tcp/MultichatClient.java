package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultichatClient {
	Scanner scan = new Scanner(System.in);
	//대화명을 저장할 멤버변수
	private String nickName;//대화명
	
	//프로그램 시작
	public void statrClient() {
		//대화명 입력받기
		System.out.println("대화명 >> ");
		nickName = scan.next();
		
		Socket socket = null;
		
		try {
			socket = new Socket("192.168.43.36",7777);
			
			System.out.println("서버에 연결되었습니다.");
			
			//송신용 스레드 생성
			
			//수신용 스레드 생성
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//메세지를 전송하는 스레드
	class CientSender extends Thread{
		Socket socket;
		DataOutputStream dos;
		String name;
		Scanner scan = new Scanner(System.in);
		
		public CientSender(Socket socket, String name) {
			this.socket = socket;
			this.name = name;
			
			try {
				dos = new DataOutputStream(
						socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				//시작하자마자 자신의 대화명을 서버로 전송
				if(dos != null) {
					dos.writeUTF(name);
				}
				while(dos !=null) {
					//키보드를 입력받은 메세지를 서버로 전송
					dos.writeUTF(scan.nextLine());
				}
			
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		//수신용 Thread
		class ClientReceiver extends Thread{
			Socket socket;
			DataInputStream dis;
			
			public ClientReceiver(Socket socket) {
				this.socket = socket;
				
				try {
					dis = new DataInputStream(socket.getInputStream());
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		@Override
		public void run() {
			while(dis!= null) {
				try {
					//서버로 부터 수신한 메세지 출력하기
					System.out.println(dis.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
		public static void main(String[] args) {
			new MultichatClient().statrClient();
		}
		
}
