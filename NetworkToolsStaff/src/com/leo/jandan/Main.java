package com.leo.jandan;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("填个目录来存放妹子吧：");
        String savePath = new Scanner(System.in).next();
        new Thread(new Spider(savePath)).start();
    }
}