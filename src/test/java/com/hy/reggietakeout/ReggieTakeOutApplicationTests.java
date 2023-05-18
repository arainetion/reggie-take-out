package com.hy.reggietakeout;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReggieTakeOutApplicationTests {

    @Test
    void exceptionType() {

        try {
            int a = 2/0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }

        System.out.println();

    }

}
