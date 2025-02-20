package cn.a114.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SoutUtils {
    public static Logger logger = LogManager.getLogger("SoutUtils Logger");

    public static synchronized void out(String... content){
        for (String _content: content){
            logger.info(_content);
//            System.out.println(_content);
        }
    }
}
