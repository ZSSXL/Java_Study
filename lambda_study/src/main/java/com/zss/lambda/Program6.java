package com.zss.lambda;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 14:27
 * @desc Lambda表达式
 */
public class Program6 {

    public static void main(String[] args) {
        // 开线程
        Thread t = new Thread(() -> {
            for (int i = 0 ; i < 100 ; i ++){
                System.out.println("Thread : " + i);
            }
        });
        t.start();
        System.out.println(t.getId() + " : " + t.getName());
    }

}
