package com.clock;

import java.time.LocalTime;

import static com.clock.Color.*;

public class BerlinUhrClock {

    public static String getBerlinUhrClockTime(String timeString){
        LocalTime time = LocalTime.parse(timeString);

        return String.join("\n",
                second(time),
                hourFirstRow(time),
                hourSecondRow(time),
                minuteFirstRow(time),
                minuteSecondRow(time)
        );
    }

    static String second(LocalTime time) {
        return time.getSecond() % 2 == 0 ? YELLOW.colorValue : OFF.colorValue;
    }

    static String hourFirstRow(LocalTime time){

        int num = time.getHour();
        int onCount = num / 5;
        return ColorCoder.toFourDigitColorCode(onCount, RED);

    }

    static public String hourSecondRow(LocalTime time){

        int num = time.getHour();
        int onCount = num%5;
        return ColorCoder.toFourDigitColorCode(onCount, RED);

    }

    static String minuteFirstRow(LocalTime time){

        int num = time.getMinute();
        int onCount = num/5;
        return ColorCoder.toElevenDigitColorCode(onCount);
    }

    static String minuteSecondRow(LocalTime time){
        int num = time.getMinute();
        int onCount = num%5;
        return ColorCoder.toFourDigitColorCode(onCount, YELLOW);
    }


}
