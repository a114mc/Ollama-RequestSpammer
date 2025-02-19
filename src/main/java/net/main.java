package net;

import cn.a114.utils.SoutUtils;
import net.deepseek.RequestSpammer;

import java.io.IOException;
import java.util.Random;

public class main {
    public static Random RANDOM = new Random();

    public static void main(String[] args) {

        try {
            SoutUtils.out(RequestSpammer.randomWords(3,true));
        } catch (IOException e) {

        }

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
    public static void exitProcess() {
        synchronized ((Object)true) {
            Runtime.getRuntime().exit(0);
            System.exit(0);
        }

    }
}
