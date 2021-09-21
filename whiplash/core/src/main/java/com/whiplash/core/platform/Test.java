package com.whiplash.core.platform;

import java.util.Optional;

/**
 * @author Administrator
 * @date 2021/9/3 15:58
 * @projectName whiplash
 * @title: Test
 * @description: TODO
 */
public class Test {

    public static void main(String[] args) {
        String str1 = null;

        String str2 = "optional";

        Optional<String> opt1 = Optional.ofNullable(str1);

        System.out.println(opt1.orElse("112233"));
        // 会报异常
        System.out.println(opt1.get());
    }
}
