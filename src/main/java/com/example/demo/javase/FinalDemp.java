package com.example.demo.javase;

/**
 * final  关键字
 * 修饰类：表示类不可被继承
 * 修饰方法：表示方法不可被子类覆盖，但是可以重载
 * 修饰变量：表示变量一旦被赋值就不可以更改它的值
 *
 */
public class FinalDemp {

    final static int a = 0;  //再声明的时候就需要赋值 或者静态代码块赋值
    static {
      // a = 3;
    }
    final static FinalDemp finalDemp = new FinalDemp();

    public  final void  m1(){
        System.out.println("111");
    }

    public static void main(String[] args) {
        // a = 2;              //Cannot assign a value to final variable 'a'
        // finalDemp = null;   // Cannot assign a value to final variable 'finalDemp'

    }

    class P1 extends FinalDemp{
        // 'm1()' cannot override 'm1()' in 'com.example.demo.javase.FinalDemp';
        // overridden method is final

       /* public  final void  m1(){
            System.out.println("222");
        }*/
    }


}
