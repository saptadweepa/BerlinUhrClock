package com.clock;

public class BerlinClockApp {

    public static void main(String[] args) {
        if (args != null && args.length > 0){
            try {
                System.out.println(BerlinUhrClock.getBerlinUhrClockTime(args[0]));
            } catch (Exception e){
                System.out.println("Invalid time format");
            }

        } else {
            System.out.println("Time not provided in args");
        }

    }

}
