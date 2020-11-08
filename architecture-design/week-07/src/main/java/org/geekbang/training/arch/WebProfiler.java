package org.geekbang.training.arch;

import java.io.IOException;

public class WebProfiler {
    public static void main(String[] args)throws IOException {
        Tester tester = new  WebPerformanceTester("https://www.baidu.com",100,10);
        tester.test();
        System.in.read();
    }
}
