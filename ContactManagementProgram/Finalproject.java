package project01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Finalproject {

	static ArrayList<Identity> id = new ArrayList<>(); // static 키워드로 객체를 전역 변수처럼 모든 함수에서 참조할 수 있게 함.
	static int i = 0; // 연락처를 등록하거나 삭제할 때 쓰이는 제어변수

	public static void main(String[] args) {
		System.out.println("[기말프로젝트] 60221331 김재현");
		System.out.println("연락처 관리 프로그램\n");
		print_menu();
	}

	public static void print_menu() { // 메뉴를 출력하는 함수
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		System.out.print("다음 항목 중 원하는 작업을 선택하세요.\n1.연락처 출력\n2.연락처 등록\n3.연락처 삭제\n4.끝내기\n입력:");
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e) { // 정수 형태 이외의 형태의 값을 입력했을때의 예외처리
			choice = 5; // else문으로 넘어가기 위해 choice에 5를 대입하게 함. (flag로서의 역할)
		}
		if (choice == 1) {
			view_juso(); // 연락처 출력 함수
		} else if (choice == 2) {
			add_juso(); // 연락처 등록 함수
		} else if (choice == 3) {
			delete_juso(); // 연락처 삭제 함수
		} else if (choice == 4) {
			System.out.println("\n프로그램을 종료합니다.");
			System.exit(0); // 프로그램 종료 함수
		} else { // 1,2,3,4 이외의 정수를 입력했을 때
			System.out.println("숫자 1,2,3,4 중에서만 입력해주세요.\n");
			System.out.println("프로그램을 다시 시작합니다.");
			print_menu();
		}
	}

	public static void view_juso() {
		if (id.isEmpty()) { // id 배열 안에 값이 존재하지 않는지 확인, 존재하지 않을 시 true가 반환되어 if문이 실행된다.
			System.out.println("등록된 연락처가 존재하지 않습니다.\n");
			print_menu();
		} else {
			System.out.println("순번  이름   나이      번호");
			for (int j = 0; j < id.size(); j++) { // 반복문으로 id 배열 내의 요소를 하나 하나 탐색해가며 출력
				System.out.println(
						" " + (j + 1) + "  " + id.get(j).name + "   " + id.get(j).age + "  " + id.get(j).number + "\n");
			} // 배열의 인덱스는 0부터 시작하므로 순번을 표현하기 위해선 j에 1을 더해야함.
			print_menu();
		}
	}

	public static void add_juso() {
		int age = 0; // 변수 초기화
		String name;
		String number;
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 입력:");
		name = sc.next();
		System.out.print("나이 입력:");
		try {
			age = sc.nextInt();
		} catch (InputMismatchException ex) { // 정수 형태 이외의 형태의 값을 입력했을때의 예외처리
			System.out.println("오직 숫자만을 입력해주세요.\n");
			add_juso();
		}
		System.out.print("번호 입력:");
		number = sc.next();
		id.add(i, new Identity(name, age, number)); // 새로운 객체를 생성한 뒤 id 배열에 요소로 추가
		i++; // 제어변수 i를 1증가시킴으로써 다음에 연락처를 등록할 때 배열의 다음 인덱스에 추가하게 만들어준다.
		File f = new File("juso.txt"); // 파일 입출력
		try {
			FileWriter fw = new FileWriter(f);
			fw.write("순번  이름   나이      번호\n");
			for (int j = 0; j < id.size(); j++) { // 반복문으로 id 배열안의 요소를 하나하나 탐색해가며 write()함수로 juso.txt파일에 요소 내용을 작성
				fw.write(" " + (j + 1) + "  " + id.get(j).name + "   " + id.get(j).age + "  " + id.get(j).number
						+ "\n\n");
			}
			fw.close();
		} catch (IOException e) { // 파일 입출력 예외처리

		}
		System.out.printf("\n%s의 연락처가 등록되었습니다.\n", name);
		print_menu();
	}

	public static void delete_juso() {
		if (id.isEmpty()) {
			System.out.println("삭제할 연락처가 존재하지 않습니다.\n");
			print_menu();
		}
		int choice_del = 0;
		int choice_del2 = 0; // 삭제할 연락처의 순번
		String name_del; // 삭제할 연락처의 이름
		String number_del; // 삭제할 연락처의 번호
		System.out.println("\n세부 작업을 선택하세요.");
		System.out.print("1.순번 삭제\n2.이름 삭제\n3.번호 삭제\n4.메인 메뉴로 이동\n");
		System.out.print("입력:");
		Scanner sc = new Scanner(System.in);
		try {
			choice_del = sc.nextInt();
		} catch (InputMismatchException e) {
			choice_del = 5;
		}
		if (choice_del == 1) {
			System.out.print("삭제할 연락처 순번을 입력해주세요:");
			try {
				choice_del2 = sc.nextInt();
				System.out.printf("\n%d번 연락처(%s)가 삭제되었습니다.\n", choice_del2, id.get(choice_del2 - 1).name);
				id.remove(choice_del2 - 1); // 인덱스는 0부터 시작하므로 입력한 값의 1을 빼주어야 한다.
			} catch (IndexOutOfBoundsException e) { // 입력한 순번이 인덱스의 범위를 넘었을때의 예외처리
				System.out.println("존재하지 않는 연락처입니다.");
				delete_juso();
			} catch (InputMismatchException e) { // 정수 이외의 형태의 값을 입력했을 때의 예외처리
				System.out.println("오직 숫자만을 입력해주세요.");
				delete_juso();
			}
			File f = new File("juso.txt");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write("순번  이름   나이      번호\n");
				for (int j = 0; j < id.size(); j++) { // 요소가 삭제된 상태에서 다시 juso.txt에 내용 작성 -> 요소가 삭제된 상태가 juso.txt에 입력됨.
					fw.write(" " + (j + 1) + "  " + id.get(j).name + "   " + id.get(j).age + "  " + id.get(j).number
							+ "\n\n");
				}
				fw.close();
			} catch (IOException e) {

			}
			i--; // 제어변수 i를 1감소시킴으로써 요소가 삭제되었음을 내부적으로 알림
			print_menu();
		} else if (choice_del == 2) {
			int a = 0; // flag으로서의 역할을 함
			System.out.print("삭제할 연락처 이름을 입력해주세요:");
			name_del = sc.next();
			for (int j = 0; j < id.size(); j++) {
				if (name_del.equals(id.get(j).name)) {
					System.out.printf("\n%s의 연락처가 삭제되었습니다.\n", name_del);
					id.remove(j);
					a = 1; // 연락처가 성공적으로 삭제되면 a에 1이 대입되어 하단의 if문이 실행되지 않음.
				}
			}
			if (a != 1) {
				System.out.println("존재하지 않는 연락처입니다.");
				delete_juso();
			}
			File f = new File("juso.txt");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write("순번  이름   나이      번호\n");
				for (int j = 0; j < id.size(); j++) {
					fw.write(" " + (j + 1) + "  " + id.get(j).name + "   " + id.get(j).age + "  " + id.get(j).number
							+ "\n\n");
				}
				fw.close();
			} catch (IOException e) {

			}
			i--;
			print_menu();
		} else if (choice_del == 3) {
			int a = 0;
			System.out.print("삭제할 연락처 번호를 입력해주세요:");
			number_del = sc.next();
			for (int j = 0; j < id.size(); j++) {
				if (number_del.equals(id.get(j).number)) {
					System.out.printf("\n번호가 %s인 연락처(%s)가 삭제되었습니다.\n", number_del, id.get(j).name);
					id.remove(j);
					a = 1;
				}
			}
			if (a != 1) {
				System.out.println("존재하지 않는 연락처입니다.");
				delete_juso();
			}
			File f = new File("juso.txt");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write("순번  이름   나이      번호\n");
				for (int j = 0; j < id.size(); j++) {
					fw.write(" " + (j + 1) + "  " + id.get(j).name + "   " + id.get(j).age + "  " + id.get(j).number
							+ "\n\n");
				}
				fw.close();
			} catch (IOException e) {

			}
			i--;
			print_menu();
		} else if (choice_del == 4) {
			print_menu();
		} else {
			System.out.println("숫자 1,2,3,4 중에서만 입력해주세요.");
			delete_juso();
		}

	}
}

class Identity { // 연락처 클래스
	String name;
	int age;
	String number;

	public Identity(String name, int age, String number) { // 생성자 정의
		this.name = name;
		this.age = age;
		this.number = number;
	}
}