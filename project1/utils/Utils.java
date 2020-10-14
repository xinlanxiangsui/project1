package com.stx.project1.utils;

import com.stx.project1.model.Manager;
import com.stx.project1.model.Person;
import com.stx.project1.model.Student;
import com.stx.project1.model.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static void opreation(ArrayList stuArr) {
        Scanner sc = new Scanner(System.in);
        Person person = new Student();
        boolean back = true;
        do {
            System.out.println("请选择您的操作：1. 登陆 2. 注册 3. 返回主界面");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    if (!person.login(stuArr)) {
                        break;
                    }
                    person.showMenu(stuArr);
                    break;
                case "2":
                    // 创建一个新的学生对象，不然永远只能注册一个人
                    Student stu = new Student();
                    person.register(stu, stuArr);
                    break;
                case "3":
                    back = false;
                    break;
                default:
                    System.out.println("\u001b[1;31;12m您的输入有误，请重新输入!\u001b[0m");
                    break;
            }
        } while (back);
    }

    public static void opreation(ArrayList stuArr, ArrayList teaArr) {
        Scanner sc = new Scanner(System.in);
        Person person = new Teacher();
        boolean back = true;
        do {
            System.out.println("请选择您的操作：1. 登陆 2. 注册 3. 返回主界面");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    if (!person.login(teaArr)) {
                        break;
                    }
                    person.showMenu(stuArr, teaArr);
                    break;
                case "2":
                    Teacher tea = new Teacher();
                    person.register(tea, teaArr);
                    break;
                case "3":
                    back = false;
                    break;
                default:
                    System.out.println("\u001b[1;31;12m您的输入有误，请重新输入!\u001b[0m");
                    break;
            }
        } while (back);
    }

    public static void opreation(ArrayList stuArr, ArrayList teaArr, ArrayList mngArr) {
        Scanner sc = new Scanner(System.in);
        Person person = new Manager();
        boolean back = true;
        do {
            System.out.println("请选择您的操作：1. 登陆 2. 注册 3. 返回主界面");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    if (!person.login(mngArr)) {
                        break;
                    }
                    person.showMenu(stuArr, teaArr, mngArr);
                    break;
                case "2":
                    Manager mng = new Manager();
                    person.register(mng, mngArr);
                    break;
                case "3":
                    back = false;
                    break;
                default:
                    System.out.println("\u001b[1;31;12m您的输入有误，请重新输入!\u001b[0m");
                    break;
            }
        } while (back);
    }
}
