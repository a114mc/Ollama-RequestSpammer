package net;

import cn.a114.utils.SoutUtils;

public class Cookie {
    public Cookie(){
        SoutUtils.out("Cookie constructor.");
    }
    protected void bite(){
        SoutUtils.out("Protected void bite called.");
    }
}
