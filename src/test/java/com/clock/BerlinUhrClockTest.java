package com.clock;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BerlinUhrClockTest {

    @ParameterizedTest
    @ValueSource(strings = {"10:15:30","13:12:12","12:12:12","10:02:04","11:08:00"})
    void whenEvenSecondsTheSecondLampShouldBeYellow(String timeString){
        var time = LocalTime.parse(timeString);
        assertEquals(Color.YELLOW.colorValue,BerlinUhrClock.second(time));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10:16:31","13:16:09","12:13:19","10:52:43","11:58:59"})
    void whenOddSecondsTheSecondLampShouldBeOff(String timeString){
        var time = LocalTime.parse(timeString);
        assertEquals(Color.OFF.colorValue,BerlinUhrClock.second(time));
    }

    @Nested
    class LampsShouldPresentExpectedPatternForGivenTime {
        @ParameterizedTest
        @CsvSource({
                "00:00:00, OOOO",
                "03:59:59, OOOO",
                "05:09:59, ROOO",
                "07:50:59, ROOO",
                "10:16:31, RROO",
                "13:16:09, RROO",
                "16:13:19, RRRO",
                "19:52:43, RRRO",
                "21:58:59, RRRR",
                "23:58:59, RRRR",

        })
        void hourFirstRow(String timeString,String expectedPattern){
            var time = LocalTime.parse(timeString);
            var actualPattern = BerlinUhrClock.hourFirstRow(time);
            assertEquals(expectedPattern,actualPattern);
        }

        @ParameterizedTest
        @CsvSource({
                "00:20:06, OOOO",
                "05:59:59, OOOO",
                "06:09:59, ROOO",
                "11:50:59, ROOO",
                "02:16:31, RROO",
                "12:16:09, RROO",
                "08:13:19, RRRO",
                "13:52:43, RRRO",
                "04:58:59, RRRR",
                "19:58:59, RRRR",

        })
        void hourSecondRow(String timeString,String expectedPattern){
            var time = LocalTime.parse(timeString);
            var actualPattern = BerlinUhrClock.hourSecondRow(time);
            assertEquals(expectedPattern,actualPattern);
        }

        @ParameterizedTest
        @CsvSource({
                "00:20:06, YYRYOOOOOOO",
                "03:59:59, YYRYYRYYRYY",
                "07:09:59, YOOOOOOOOOO",
                "15:50:59, YYRYYRYYRYO",
                "12:16:31, YYROOOOOOOO",
                "19:21:09, YYRYOOOOOOO",
                "03:13:19, YYOOOOOOOOO",
                "13:42:43, YYRYYRYYOOO",
                "14:28:59, YYRYYOOOOOO",
                "21:38:59, YYRYYRYOOOO",

        })
        void minuteFirstRow(String timeString,String expectedPattern){
            var time = LocalTime.parse(timeString);
            var actualPattern = BerlinUhrClock.minuteFirstRow(time);
            assertEquals(expectedPattern,actualPattern);
        }

        @ParameterizedTest
        @CsvSource({
                "00:15:06, OOOO",
                "03:05:59, OOOO",
                "07:01:59, YOOO",
                "15:16:59, YOOO",
                "12:07:31, YYOO",
                "19:22:09, YYOO",
                "03:13:19, YYYO",
                "13:38:43, YYYO",
                "14:24:59, YYYY",
                "21:59:59, YYYY",

        })
        void minuteSecondRow(String timeString,String expectedPattern){
            var time = LocalTime.parse(timeString);
            var actualPattern = BerlinUhrClock.minuteSecondRow(time);
            assertEquals(expectedPattern,actualPattern);
        }

    }

    @Nested
    class BerlinUhrClockDisplay{

        @ParameterizedTest
        @CsvSource({
                "00:00:00,  'Y" + "\n" +
                            "OOOO" + "\n" +
                            "OOOO" + "\n" +
                            "OOOOOOOOOOO" + "\n" +
                            "OOOO'",
                "13:17:02,  'Y" + "\n" +
                            "RROO" + "\n" +
                            "RRRO" + "\n" +
                            "YYROOOOOOOO" + "\n" +
                            "YYOO'",
                "15:11:59,  'O" + "\n" +
                            "RRRO" + "\n" +
                            "OOOO" + "\n" +
                            "YYOOOOOOOOO" + "\n" +
                            "YOOO'",

        })
        void shouldReturnExpectedLampPattern(String time, String expectedPattern){
            assertEquals(expectedPattern, BerlinUhrClock.getBerlinUhrClockTime(time));
        }

        @Test
        void shouldThrowExceptionOnInvalidFormatTime(){
            assertThrows(DateTimeParseException.class, ()->BerlinUhrClock.getBerlinUhrClockTime("dafsfadf"));
        }


    }


}
