package net.test;

import net.Cookie;

public class DifferentPackage {
    public static void call(){
        // Cookie在net包内且extends Object
        // 在net包内的main可以访问cookie的bite
        Cookie _饼干 = new Cookie();

        /*
        [ERROR] COMPILATION ERROR :
        [INFO] -------------------------------------------------------------
        [ERROR] /home/a114mc/IdeaProjects/JTests/src/main/java/net/test/DifferentPackage.java:[9,12] bite() 在 net.Cookie 中是 protected 访问控制
        [INFO] 1 error
        [INFO] -------------------------------------------------------------
        */
        //_饼干.bite();

    }
}
