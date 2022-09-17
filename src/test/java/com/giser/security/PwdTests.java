package com.giser.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author giserDev
 * @description
 * @date 2022-09-17 12:33:38
 */
public class PwdTests {
    @Test
    public void test001(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String giser = bCryptPasswordEncoder.encode("giser");
        System.out.println(giser);
    }
}
