package com.clock;

public class ColorCoder {

    public static String toFourDigitColorCode(int onCount, Color onColor){
        if (onCount < 0 || onCount > 4){
            throw new IllegalArgumentException("Invalid onCount Value");
        }
        return onColor.colorValue.repeat(onCount) + Color.OFF.colorValue.repeat(4-onCount);
    }

    public static String toElevenDigitColorCode(int onCount){

        if (onCount < 0 || onCount > 11){
            throw new IllegalArgumentException("Invalid onCount Value");
        }

        StringBuilder onCode = new StringBuilder();

        for (int i=1;i<=onCount;i++){
            if (i%3==0){
                onCode.append(Color.RED.colorValue);
                continue;
            }
            onCode.append(Color.YELLOW.colorValue);
        }

        return onCode + Color.OFF.colorValue.repeat(11-onCount);

    }
}
