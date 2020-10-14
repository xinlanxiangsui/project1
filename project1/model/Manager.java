package com.stx.project1.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Manager implements Person {
    private String account;
    private String password;
    private boolean loginState;

    /*
     * 管理员登陆
     * */
    @Override
    public boolean login(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入管理员账号：");
        String account = sc.next();
        System.out.println("请输入管理员密码：");
        String password = sc.next();
        for (Object o : arr) {
            Manager mng = (Manager) o;
            if (mng.getAccount().equals(account) && mng.getPassword().equals(password)) {
                mng.setLoginState(true);
                System.out.println("\u001b[1;32;12m登陆成功!\u001b[0m");
                return true;
            }
        }
        System.out.println("\u001b[1;31;12m用户名或密码错误!\u001b[0m");
        return false;
    }

    /*
     * 管理员注册
     * */
    @Override
    public boolean register(Person person, ArrayList arr) {
        // 向下转型
        Manager mng = (Manager) person;
        Scanner sc = new Scanner(System.in);

        boolean isTrue = true;
        String Account;
        do {
            System.out.println("请输入管理员账号：");
            Account = sc.next();
            String regex = "^\\d{6,10}$";
            isTrue = Pattern.matches(regex, Account);
            if (!isTrue) {
                System.out.println("\u001b[1;31;12m管理员账号必须为6-10位的数字!\u001b[0m");
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
            Manager manager = (Manager) o;
            if (manager.getAccount().equals(Account)) {
                System.out.println("\u001b[1;31;12m该管理员已存在!\u001b[0m");
                return false;
            }
        }
        mng.setAccount(Account);
        mng.setPassword(password);
        arr.add(mng);
        System.out.println("\u001b[1;32;12m注册成功!\u001b[0m");
        return false;
    }


    @Override
    public boolean clock(ArrayList arr) {
        return false;
    }

    @Override
    public boolean remove(ArrayList arr) {
        return false;
    }

    @Override
    public boolean changePwd(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        for (Object o : arr) {
            Manager mng = (Manager) o;
            if (mng.isLoginState()) {
                System.out.println("请输入旧密码：");
                String oldPwd = sc.next();
                if (mng.getPassword().equals(oldPwd)) {
                    boolean pwdIsTrue = true;
                    String newPwd;
                    do {
                        System.out.println("请输入新密码：");
                        newPwd = sc.next();
                        String regex = "^[\\w_]{6,20}$";
                        pwdIsTrue = Pattern.matches(regex, newPwd);
                        if (!pwdIsTrue) {
                            System.out.println("\u001b[1;31;12m密码必须是6-20位的字母、数字或下划线!\u001b[0m");
                        }
                    } while (!pwdIsTrue);
                    mng.setPassword(newPwd);
                    System.out.println("\u001b[1;32;12m修改密码成功!\u001b[0m");
                } else {
                    System.out.println("\u001b[1;31;12m密码错误!\u001b[0m");
                }
            }

        }
        return false;
    }

    @Override
    public boolean showMenu(ArrayList arr) {

        return false;
    }

    @Override
    public boolean showMenu(ArrayList stuArr, ArrayList teaArr) {
        return false;
    }

    @Override
    public boolean showMenu(ArrayList stuArr, ArrayList teaArr, ArrayList mngArr) {
        Scanner sc = new Scanner(System.in);
        boolean back1 = true;
        do {
            System.out.println("请输入您的操作：1. 学生打卡管理 2. 教师打卡管理 3. 修改密码 4. 退出登陆");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    boolean back2 = true;
                    do {
                        System.out.println("1. 查询学生打卡记录 2. 删除学生打卡记录 3. 返回");
                        String choice2 = sc.next();
                        switch (choice2) {
                            case "1":
                                queryStu(stuArr);
                                break;
                            case "2":
                                removeStu(stuArr);
                                break;
                            case "3":
                                back2 = false;
                                break;
                            default:
                                System.out.println("\u001b[1;31;12m您的输入有误!\u001b[0m");
                                break;
                        }
                    } while (back2);
                    break;
                case "2":
                    boolean back3 = true;
                    do {
                        System.out.println("1. 查询教师打卡记录 2. 删除教师打卡记录 3. 返回");
                        String choice3 = sc.next();
                        switch (choice3) {
                            case "1":
                                queryTea(teaArr);
                                break;
                            case "2":
                                removeTea(teaArr);
                                break;
                            case "3":
                                back3 = false;
                                break;
                            default:
                                System.out.println("\u001b[1;31;12m您的输入有误!\u001b[0m");
                                break;
                        }
                    } while (back3);
                    break;
                case "3":
                    changePwd(mngArr);
                    break;
                case "4":
                    // 将所有管理员的登陆状态设置为false
                    for (Object o : mngArr) {
                        Manager mng = (Manager) o;
                        mng.setLoginState(false);
                    }
                    back1 = false;
                    break;
                default:
                    System.out.println("\u001b[1;31;12m您的输入有误，请重新输入!\u001b[0m");
                    break;
            }

        } while (back1);
        return false;
    }

    public boolean queryStu(ArrayList arr) {
        for (Object o : arr) {
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

    public boolean queryTea(ArrayList arr) {
        for (Object o : arr) {
            Teacher tea = (Teacher) o;
            if (tea.getTemperature() != null) {
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
        System.out.println("\u001b[1;31;12m暂无教师打卡记录\u001b[0m");
        return false;
    }

    /*
     * 删除学生记录
     * */
    public boolean removeStu(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要删除学生的学号：");
        String stuNum = sc.next();
        for (Object o : arr) {
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

    public boolean removeTea(ArrayList arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要删除教师的工号：");
        String workNum = sc.next();
        for (Object o : arr) {
            Teacher tea = (Teacher) o;
            if (tea.getWorkNum().equals(workNum)) {
                tea.setTemperature(null);
                System.out.println("\u001b[1;32;12m删除成功!\u001b[0m");
                return false;
            }

        }
        System.out.println("\u001b[1;31;12m未找到该教师!\u001b[0m");
        return false;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }
}
