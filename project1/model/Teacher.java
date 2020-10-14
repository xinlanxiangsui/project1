package com.stx.project1.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Teacher implements Person {
    private String workNum;
    private String name;
    private String password;
    private String temperature;
    private String clockLocation;
    private String clockTime;
    private boolean loginState;

    /*
     * 教师登陆
     * */
    @Override
    public boolean login(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的工号：");
        String workNum = sc.next();
        System.out.println("请输入您的密码：");
        String password = sc.next();
        for (Object o : arr) {
            Teacher tea = (Teacher) o;
            if (tea.getWorkNum().equals(workNum) && tea.getPassword().equals(password)) {
                tea.setLoginState(true);
                System.out.println("\u001b[1;32;12m登陆成功!\u001b[0m");
                return true;
            }
        }
        System.out.println("\u001b[1;31;12m用户名或密码错误!\u001b[0m");
        return false;
    }


    /*
     * 教师注册
     * */
    @Override
    public boolean register(Person person, ArrayList arr) {
        // 向下转型
        Teacher tea = (Teacher) person;
        Scanner sc = new Scanner(System.in);
        boolean isTrue = true;
        String workNum;
        do {
            System.out.println("请输入您的工号：");
            workNum = sc.next();
            String regex = "^\\d{6,10}$";
            isTrue = Pattern.matches(regex, workNum);
            if (!isTrue) {
                System.out.println("\u001b[1;31;12m工号必须为6-10位的数字!\u001b[0m");
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
            Teacher teacher = (Teacher) o;
            if (teacher.getWorkNum().equals(workNum)) {
                System.out.println("\u001b[1;31;12m该教师已存在!\u001b[0m");
                return false;
            }
        }
        tea.setWorkNum(workNum);
        tea.setPassword(password);
        arr.add(tea);
        System.out.println("\u001b[1;32;12m注册成功!\u001b[0m");
        return false;
    }


    /*
     * 教师打卡
     * */
    @Override
    public boolean clock(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        boolean haveClock = false;
        for (Object o : arr) {
            Teacher tea = (Teacher) o;
            if (tea.isLoginState() && tea.getTemperature() == null) {
                System.out.println("请输入您的姓名：");
                tea.setName(sc.next());
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
                tea.setTemperature(tem);
                System.out.println("请输入您的打卡地点：");
                tea.setClockLocation(sc.next());
                // 自动记录时间
                tea.setClockTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
     * 教师菜单
     * */
    @Override
    public boolean showMenu(ArrayList stuArr, ArrayList teaArr) {
        Scanner sc = new Scanner(System.in);
        boolean back = true;
        do {
            System.out.println("请选择您的操作：1. 打卡 2. 我的打卡管理 3. 学生打卡管理 4. 修改密码 5. 退出登陆");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    clock(teaArr);
                    break;
                case "2":
                    boolean back3 = true;
                    do {
                        System.out.println("请选择您的操作：1. 查询我的打卡记录 2. 删除我的打卡记录 3. 返回上一级");
                        String teaChoice = sc.next();
                        switch (teaChoice) {
                            case "1":
                                query(teaArr);
                                break;
                            case "2":
                                remove(teaArr);
                                break;
                            case "3":
                                back3 = false;
                                break;
                            default:
                                System.out.println("\u001b[1;31;12m您的输入有误，请重新输入!\u001b[0m");

                                break;
                        }
                    } while (back3);
                    break;
                case "3":
                    boolean back1 = true;
                    do {
                        System.out.println("请选择您的操作：1. 查询学生打卡记录 2. 删除学生打卡记录 3. 返回上一级");
                        String teaChoice2 = sc.next();
                        switch (teaChoice2) {
                            case "1":
                                queryStu(stuArr);
                                break;
                            case "2":
                                removeStu(stuArr);
                                break;
                            case "3":
                                back1 = false;
                                break;
                            default:
                                System.out.println("\u001b[1;31;12m您的输入有误，请重新输入!\u001b[0m");
                                break;
                        }
                    } while (back1);
                    break;
                case "4":
                    changePwd(teaArr);
                    break;
                case "5":
                    // 将所有教师的登陆状态设置为false
                    for (Object o : teaArr) {
                        Teacher tea = (Teacher) o;
                        tea.setLoginState(false);
                    }
                    back = false;
                    break;
                default:
                    System.out.println("\u001b[1;31;12m您的输入有误，请重新输入!\u001b[0m");
                    break;
            }
        } while (back);
        return false;
    }

    /*
     * 删除学生打卡记录
     * */
    private boolean removeStu(ArrayList stuArr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要删除学生的学号");
        String stuNum = sc.next();
        for (Object o : stuArr) {
            Student stu = (Student) o;
            if (stu.getStuNum().equals(stuNum)) {
                stu.setTemperature(null);
                System.out.println("\u001b[1;32;12m删除成功!\u001b[0m");
                return false;
            }
        }
        System.out.println("\u001b[1;31;12m未找到该学生!\u001b[0m");
        return false;
    }


    @Override
    public boolean showMenu(ArrayList stuArr, ArrayList teaArr, ArrayList mngArr) {
        return false;
    }

    /*
     * 教师查询打卡记录
     * */
    public boolean query(ArrayList arr) {
        for (Object o : arr) {
            Teacher tea = (Teacher) o;
            if (tea.isLoginState() && tea.getTemperature() != null) {
                System.out.printf("%-15s", "STUNUM");
                System.out.printf("%-15s", "NAME");
                System.out.printf("%-15s", "TEMPERATURE");
                System.out.printf("%-15s", "LOCATION");
                System.out.printf("%-15s", "TIME");
                System.out.println();
                System.out.printf("%-15s", tea.getWorkNum());
                System.out.printf("%-15s", tea.getName());
                System.out.printf("%-15s", tea.getTemperature());
                System.out.printf("%-15s", tea.getClockLocation());
                System.out.printf("%-15s", tea.getClockTime());
                System.out.println();
                return false;
            }
        }
        System.out.println("\u001b[1;31;12m您还没有打卡哦\u001b[0m");
        return false;
    }

    /*
     * 查询学生打卡信息
     * */
    public boolean queryStu(ArrayList stuArr) {
        for (Object o : stuArr) {
            Student stu = (Student) o;
            if (stu.getTemperature() != null) {
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
        System.out.println("\u001b[1;31;12m暂无学生打卡记录\u001b[0m");
        return false;
    }


    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "workNum='" + workNum + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", temperature='" + temperature + '\'' +
                ", clockLocation='" + clockLocation + '\'' +
                ", clockTime='" + clockTime + '\'' +
                ", loginState=" + loginState +
                '}';
    }

    /*
     * 教师删除自己的打卡记录*/
    @Override
    public boolean remove(ArrayList arr) {
        for (Object o : arr) {
            Teacher tea = (Teacher) o;
            if (tea.isLoginState() && tea.getTemperature() != null) {
                tea.setTemperature(null);
                System.out.println("\u001b[1;32;12m删除成功!\u001b[0m");
                return false;
            }
        }
        System.out.println("\u001b[1;31;12m您还没有打卡！\u001b[0m");
        return false;
    }

    /*
     * 教师修改密码*/
    @Override
    public boolean changePwd(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入旧密码：");
        String oldPwd = sc.next();
        for (Object o : arr) {
            Teacher tea = (Teacher) o;
            if (tea.isLoginState() && tea.getPassword().equals(oldPwd)) {
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
                tea.setPassword(password);
                System.out.println("\u001b[1;32;12m修改密码成功!\u001b[0m");
                return false;
            }
        }
        System.out.println("\u001b[1;31;12m密码输入错误!\u001b[0m");
        return false;
    }

    @Override
    public boolean showMenu(ArrayList stuArr) {
        return false;
    }

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }


}
