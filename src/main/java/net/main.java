package net;

import net.test.DifferentPackage;

import java.util.Random;

import java.util.Scanner;

public class main {
    public static Random RANDOM = new Random();

    public static void main(String[] args) {

        DifferentPackage.call();

//        Scanner scanner = new Scanner(System.in);
//
//
//        while (true){
//            if(scanner.nextLine().equals("stop")){break;}
//            synchronized((Object) false){
//                System.out.println(System.currentTimeMillis());
//            }
//            System.out.println(RANDOM.nextGaussian());
//        }

    }
    public static void 退(){
        synchronized ((Object)true){
            System.exit(0);
        }

    }
}
