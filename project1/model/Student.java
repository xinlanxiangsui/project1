package com.stx.project1.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Student implements Person {
    private String stuNum;
    private String name;
    private String password;
    private String temperature;
    private String clockLocation;
    private String clockTime;
    private boolean loginState;

    public Student() {
    }

    /*
     * 学生登陆
     * */
    @Override
    public boolean login(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的学号：");
        String stuNum = sc.next();
        System.out.println("请输入您的密码：");
        String password = sc.next();
        for (Object o : arr) {
            Student stu = (Student) o;
            if (stu.getStuNum().equals(stuNum) && stu.getPassword().equals(password)) {
                stu.setLoginState(true);
                System.out.println("\u001b[1;32;12m登陆成功!\u001b[0m");
                return true;
            }
        }
        System.out.println("\u001b[1;31;12m用户名或密码错误!\u001b[0m");
        return false;

    }

    /*
     * 学生注册
     * */
    @Override
    public boolean register(Person person, ArrayList arr) {
        // 向下转型
        Student s = (Student) person;
        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;
        String stuNum;
        do {
            System.out.println("请输入您的学号：");
            stuNum = sc.next();
            String regex = "^\\d{6,10}$";
            isTrue = Pattern.matches(regex, stuNum);
            if (!isTrue) {
                System.out.println("\u001b[1;31;12m学号必须为6-10位的数字!\u001b[0m");
            }
        } while (!isTrue);

        boolean pwdIsTrue = true;
        String password;
        do {
            System.out.println("请输入您的密码：");
            password = sc.next();
            String regex = "^[\\w_]{6,20}$";
            pwdIsTrue = Pattern.matches(regex, password);
            if (!pwdIsTrue) {
                System.out.println("\u001b[1;31;12m密码必须是6-20位的字母、数字或下划线!\u001b[0m");
            }
        } while (!pwdIsTrue);


        for (Object o : arr) {
            Student stu = (Student) o;
            if (stu.getStuNum().equals(stuNum)) {
                System.out.println("\u001b[1;31;12m该学生已存在!\u001b[0m");
                return false;
            }
        }
        s.setStuNum(stuNum);
        s.setPassword(password);
        arr.add(s);
        System.out.println("\u001b[1;32;12m注册成功!\u001b[0m");
        return false;
    }

    /*
     * 学生打卡
     * */
    @Override
    public boolean clock(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        boolean haveClock = false;
        for (Object o : arr) {
            Student stu = (Student) o;
            if (stu.isLoginState() && stu.getTemperature() == null) {
                System.out.println("请输入您的姓名：");
                stu.setName(sc.next());
                boolean isTrue = true;
                String tem;
                do {
                    System.out.println("请输入您的体温：");
                    tem = sc.next();
                    String regex = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
                    isTrue = Pattern.matches(regex, tem);
                    if (!isTrue) {
                        System.out.println("\u001b[1;31;12m体温只能输入数字!\u001b[0m");
                    }
                } while (!isTrue);
                stu.setTemperature(tem);
                System.out.println("请输入您的打卡地点：");
                stu.setClockLocation(sc.next());
                // 自动记录时间
                stu.setClockTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                System.out.println("\u001b[1;32;12m打卡成功!\u001b[0m");
                haveClock = true;
            }
        }

        if (!haveClock) {
            System.out.println("\u001b[1;31;12m您已经打过卡了!\u001b[0m");
        }

        return false;
    }

    /*
     * 学生删除打卡记录
     * */
    @Override
    public boolean remove(ArrayList arr) {
        for (Object o : arr) {
            Student stu = (Student) o;
            if (stu.isLoginState() && stu.getTemperature() != null) {
                stu.setTemperature(null);
                System.out.println("\u001b[1;32;12m删除成功!\u001b[0m");
                return false;
            }
        }
        System.out.println("\u001b[1;31;12m您还没有打卡！\u001b[0m");
        return false;
    }

    /*
     * 学生修改密码
     * */
    @Override
    public boolean changePwd(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入旧密码：");
        String oldPwd = sc.next();
        for (Object o : arr) {
            Student stu = (Student) o;
            if (stu.isLoginState() && stu.getPassword().equals(oldPwd)) {
                boolean pwdIsTrue = true;
                String password;
                do {
                    System.out.println("请输入新密码：");
                    password = sc.next();
                    String regex = "^[\\w_]{6,20}$";
                    pwdIsTrue = Pattern.matches(regex, password);
                    if (!pwdIsTrue) {
                        System.out.println("\u001b[1;31;12m密码必须是6-20位的字母、数字或下划线!\u001b[0m");
                    }
                } while (!pwdIsTrue);
                stu.setPassword(password);
                System.out.println("\u001b[1;32;12m修改密码成功!\u001b[0m");
                return false;
            }
        }
        System.out.println("\u001b[1;31;12m密码错误!\u001b[0m");

        return false;
    }


    /*
     * 展示学生菜单
     * */
    @Override
    public boolean showMenu(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        boolean back = true;
        do {
            System.out.println("请选择您的操作：1. 打卡 2. 查询我的打卡记录 3.删除我的打卡记录 4. 修改密码 5. 退出登陆");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    clock(arr);
                    break;
                case "2":
                    query(arr);
                    break;
                case "3":
                    remove(arr);
                    break;
                case "4":
                    changePwd(arr);
                    break;
                case "5":
                    back = false;
                    // 将所有学生的登陆状态设置为false
                    for (Object o : arr) {
                        Student stu = (Student) o;
                        stu.setLoginState(false);
                    }
                    break;
                default:
                    System.out.println("\u001b[1;31;12m您的输入有误，请重新输入!\u001b[0m");

                    break;
            }
        } while (back);

        return false;
    }

    /*
     * 学生查询打卡记录
     * */
    public boolean query(ArrayList arr) {
        for (Object o : arr) {
            Student stu = (Student) o;
            if (stu.isLoginState() && stu.getTemperature() != null) {
                System.out.printf("%-15s", "STUNUM");
                System.out.printf("%-15s", "NAME");
                System.out.printf("%-15s", "TEMPERATURE");
                System.out.printf("%-15s", "LOCATION");
                System.out.printf("%-15s", "TIME");
                System.out.println();
                System.out.printf("%-15s", stu.getStuNum());
                System.out.printf("%-15s", stu.getName());
                System.out.printf("%-15s", stu.getTemperature());
                System.out.printf("%-15s", stu.getClockLocation());
                System.out.printf("%-15s", stu.getClockTime());
                System.out.println();
                return false;
            }
        }
        System.out.println("\u001b[1;31;12m您还没有打卡哦\u001b[0m");
        return false;
    }

    @Override
    public boolean showMenu(ArrayList stuArr, ArrayList teaArr) {
        return false;
    }

    @Override
    public boolean showMenu(ArrayList stuArr, ArrayList teaArr, ArrayList mngArr) {
        return false;
    }

    // getter and setter
    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNum='" + stuNum + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", temperature='" + temperature + '\'' +
                ", clockLocation='" + clockLocation + '\'' +
                ", clockTime='" + clockTime + '\'' +
                ", loginState=" + loginState +
                '}';
    }

    public String getClockLocation() {
        return clockLocation;
    }

    public void setClockLocation(String clockLocation) {
        this.clockLocation = clockLocation;
    }

    public String getClockTime() {
        return clockTime;
    }

    public void setClockTime(String clockTime) {
        this.clockTime = clockTime;
    }


}
