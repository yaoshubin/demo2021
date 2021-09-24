package com.example.demo.jvm;

/**
 * 通过小程序 理解JVM 运行原理，那张架构图一定能通过程序实现手绘
 */
public class MathDemo {

    public static final String str = "12323";

    //public static User user = new User();

    public static void main(String[] args) {

        MathDemo math = new MathDemo();
        math.computer();
        System.out.println("----test");
    }


    public int computer() {
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }
}
