package com.zrz;

public class ForLoopTest {
    public static int[] numbers = {1,6,8};
    public static void main(String[] args){
        MovingAverage ma = new MovingAverage();
        for(int number:numbers){
            ma.submit(number);
        }
        double avg = ma.getAvg();
    }
}
