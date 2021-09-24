package com.example.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过Java自带jvisualvm 工具 : 查看GC 回收过程
 *
 */
public class GcDemo {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        while(true){
            User person = new User();
            list.add(person);
        }
    }
}
