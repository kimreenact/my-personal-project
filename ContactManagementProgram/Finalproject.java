package project01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Finalproject {

	static ArrayList<Identity> id = new ArrayList<>(); // static Ű����� ��ü�� ���� ����ó�� ��� �Լ����� ������ �� �ְ� ��.
	static int i = 0; // ����ó�� ����ϰų� ������ �� ���̴� �����

	public static void main(String[] args) {
		System.out.println("[�⸻������Ʈ] 60221331 ������");
		System.out.println("����ó ���� ���α׷�\n");
		print_menu();
	}

	public static void print_menu() { // �޴��� ����ϴ� �Լ�
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		System.out.print("���� �׸� �� ���ϴ� �۾��� �����ϼ���.\n1.����ó ���\n2.����ó ���\n3.����ó ����\n4.������\n�Է�:");
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e) { // ���� ���� �̿��� ������ ���� �Է��������� ����ó��
			choice = 5; // else������ �Ѿ�� ���� choice�� 5�� �����ϰ� ��. (flag�μ��� ����)
		}
		if (choice == 1) {
			view_juso(); // ����ó ��� �Լ�
		} else if (choice == 2) {
			add_juso(); // ����ó ��� �Լ�
		} else if (choice == 3) {
			delete_juso(); // ����ó ���� �Լ�
		} else if (choice == 4) {
			System.out.println("\n���α׷��� �����մϴ�.");
			System.exit(0); // ���α׷� ���� �Լ�
		} else { // 1,2,3,4 �̿��� ������ �Է����� ��
			System.out.println("���� 1,2,3,4 �߿����� �Է����ּ���.\n");
			System.out.println("���α׷��� �ٽ� �����մϴ�.");
			print_menu();
		}
	}

	public static void view_juso() {
		if (id.isEmpty()) { // id �迭 �ȿ� ���� �������� �ʴ��� Ȯ��, �������� ���� �� true�� ��ȯ�Ǿ� if���� ����ȴ�.
			System.out.println("��ϵ� ����ó�� �������� �ʽ��ϴ�.\n");
			print_menu();
		} else {
			System.out.println("����  �̸�   ����      ��ȣ");
			for (int j = 0; j < id.size(); j++) { // �ݺ������� id �迭 ���� ��Ҹ� �ϳ� �ϳ� Ž���ذ��� ���
				System.out.println(
						" " + (j + 1) + "  " + id.get(j).name + "   " + id.get(j).age + "  " + id.get(j).number + "\n");
			} // �迭�� �ε����� 0���� �����ϹǷ� ������ ǥ���ϱ� ���ؼ� j�� 1�� ���ؾ���.
			print_menu();
		}
	}

	public static void add_juso() {
		int age = 0; // ���� �ʱ�ȭ
		String name;
		String number;
		Scanner sc = new Scanner(System.in);
		System.out.print("�̸� �Է�:");
		name = sc.next();
		System.out.print("���� �Է�:");
		try {
			age = sc.nextInt();
		} catch (InputMismatchException ex) { // ���� ���� �̿��� ������ ���� �Է��������� ����ó��
			System.out.println("���� ���ڸ��� �Է����ּ���.\n");
			add_juso();
		}
		System.out.print("��ȣ �Է�:");
		number = sc.next();
		id.add(i, new Identity(name, age, number)); // ���ο� ��ü�� ������ �� id �迭�� ��ҷ� �߰�
		i++; // ����� i�� 1������Ŵ���ν� ������ ����ó�� ����� �� �迭�� ���� �ε����� �߰��ϰ� ������ش�.
		File f = new File("juso.txt"); // ���� �����
		try {
			FileWriter fw = new FileWriter(f);
			fw.write("����  �̸�   ����      ��ȣ\n");
			for (int j = 0; j < id.size(); j++) { // �ݺ������� id �迭���� ��Ҹ� �ϳ��ϳ� Ž���ذ��� write()�Լ��� juso.txt���Ͽ� ��� ������ �ۼ�
				fw.write(" " + (j + 1) + "  " + id.get(j).name + "   " + id.get(j).age + "  " + id.get(j).number
						+ "\n\n");
			}
			fw.close();
		} catch (IOException e) { // ���� ����� ����ó��

		}
		System.out.printf("\n%s�� ����ó�� ��ϵǾ����ϴ�.\n", name);
		print_menu();
	}

	public static void delete_juso() {
		if (id.isEmpty()) {
			System.out.println("������ ����ó�� �������� �ʽ��ϴ�.\n");
			print_menu();
		}
		int choice_del = 0;
		int choice_del2 = 0; // ������ ����ó�� ����
		String name_del; // ������ ����ó�� �̸�
		String number_del; // ������ ����ó�� ��ȣ
		System.out.println("\n���� �۾��� �����ϼ���.");
		System.out.print("1.���� ����\n2.�̸� ����\n3.��ȣ ����\n4.���� �޴��� �̵�\n");
		System.out.print("�Է�:");
		Scanner sc = new Scanner(System.in);
		try {
			choice_del = sc.nextInt();
		} catch (InputMismatchException e) {
			choice_del = 5;
		}
		if (choice_del == 1) {
			System.out.print("������ ����ó ������ �Է����ּ���:");
			try {
				choice_del2 = sc.nextInt();
				System.out.printf("\n%d�� ����ó(%s)�� �����Ǿ����ϴ�.\n", choice_del2, id.get(choice_del2 - 1).name);
				id.remove(choice_del2 - 1); // �ε����� 0���� �����ϹǷ� �Է��� ���� 1�� ���־�� �Ѵ�.
			} catch (IndexOutOfBoundsException e) { // �Է��� ������ �ε����� ������ �Ѿ������� ����ó��
				System.out.println("�������� �ʴ� ����ó�Դϴ�.");
				delete_juso();
			} catch (InputMismatchException e) { // ���� �̿��� ������ ���� �Է����� ���� ����ó��
				System.out.println("���� ���ڸ��� �Է����ּ���.");
				delete_juso();
			}
			File f = new File("juso.txt");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write("����  �̸�   ����      ��ȣ\n");
				for (int j = 0; j < id.size(); j++) { // ��Ұ� ������ ���¿��� �ٽ� juso.txt�� ���� �ۼ� -> ��Ұ� ������ ���°� juso.txt�� �Էµ�.
					fw.write(" " + (j + 1) + "  " + id.get(j).name + "   " + id.get(j).age + "  " + id.get(j).number
							+ "\n\n");
				}
				fw.close();
			} catch (IOException e) {

			}
			i--; // ����� i�� 1���ҽ�Ŵ���ν� ��Ұ� �����Ǿ����� ���������� �˸�
			print_menu();
		} else if (choice_del == 2) {
			int a = 0; // flag���μ��� ������ ��
			System.out.print("������ ����ó �̸��� �Է����ּ���:");
			name_del = sc.next();
			for (int j = 0; j < id.size(); j++) {
				if (name_del.equals(id.get(j).name)) {
					System.out.printf("\n%s�� ����ó�� �����Ǿ����ϴ�.\n", name_del);
					id.remove(j);
					a = 1; // ����ó�� ���������� �����Ǹ� a�� 1�� ���ԵǾ� �ϴ��� if���� ������� ����.
				}
			}
			if (a != 1) {
				System.out.println("�������� �ʴ� ����ó�Դϴ�.");
				delete_juso();
			}
			File f = new File("juso.txt");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write("����  �̸�   ����      ��ȣ\n");
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
			System.out.print("������ ����ó ��ȣ�� �Է����ּ���:");
			number_del = sc.next();
			for (int j = 0; j < id.size(); j++) {
				if (number_del.equals(id.get(j).number)) {
					System.out.printf("\n��ȣ�� %s�� ����ó(%s)�� �����Ǿ����ϴ�.\n", number_del, id.get(j).name);
					id.remove(j);
					a = 1;
				}
			}
			if (a != 1) {
				System.out.println("�������� �ʴ� ����ó�Դϴ�.");
				delete_juso();
			}
			File f = new File("juso.txt");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write("����  �̸�   ����      ��ȣ\n");
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
			System.out.println("���� 1,2,3,4 �߿����� �Է����ּ���.");
			delete_juso();
		}

	}
}

class Identity { // ����ó Ŭ����
	String name;
	int age;
	String number;

	public Identity(String name, int age, String number) { // ������ ����
		this.name = name;
		this.age = age;
		this.number = number;
	}
}