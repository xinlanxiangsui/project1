package com.stx.project1.main;

import com.stx.project1.model.Manager;
import com.stx.project1.model.Student;
import com.stx.project1.model.Teacher;
import com.stx.project1.utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

// 注：请用IDEA打开本项目，某些代码格式eclipse不支持
public class Main {
    public static void main(String[] args) {
        // 创建三个集合，分别保存教师、学生和管理员的信息
        ArrayList<Student> stuArr = new ArrayList<>();
        ArrayList<Manager> mngArr = new ArrayList<>();
        ArrayList<Teacher> teaArr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        // back：控制一级菜单退出
        boolean back = true;
        do {
            System.out.println("---------------------------------------------");
            System.out.println("|        ✿  W  E  L  C  O  M  E  ✿         |");
            System.out.println("|            欢迎使用疫情打卡系统             |");
            System.out.println("|您的身份是：1. 学生 2. 教师 3. 管理员 4. 退出 |");
            System.out.println("---------------------------------------------");
            String choice1 = sc.next();
            switch (choice1) {
                case "1":
                    Utils.opreation(stuArr);
                    break;
                case "2":
                    Utils.opreation(stuArr, teaArr);
                    break;
                case "3":
                    Utils.opreation(stuArr, teaArr, mngArr);
                    break;
                case "4":
                    back = false;
                    break;
                default:
                    System.out.println("\u001b[1;31;12m您的输入有误，请重新输入!\u001b[0m");
                    break;
            }
        } while (back);
    }
}
