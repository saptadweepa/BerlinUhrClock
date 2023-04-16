package com.clock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorCoderTest {

    @ParameterizedTest
    @CsvSource({
            "0, RED, OOOO",
            "1, YELLOW, YOOO",
            "2, RED, RROO",
            "3, YELLOW, YYYO",
            "4, RED, RRRR"

    })
    void shouldReturnExpectedFourDigits(int onCount, Color color, String expectedDigits){
        assertEquals(expectedDigits, ColorCoder.toFourDigitColorCode(onCount, color));
    }

    @ParameterizedTest
    @CsvSource({
            "0, OOOOOOOOOOO",
            "1, YOOOOOOOOOO",
            "2, YYOOOOOOOOO",
            "3, YYROOOOOOOO",
            "4, YYRYOOOOOOO",
            "5, YYRYYOOOOOO",
            "6, YYRYYROOOOO",
            "7, YYRYYRYOOOO",
            "8, YYRYYRYYOOO",
            "9, YYRYYRYYROO",
            "10, YYRYYRYYRYO",
            "11, YYRYYRYYRYY",
    })
    void shouldReturnExpectedElevenDigits(int onCount, String expectedDigits){
        assertEquals(expectedDigits, ColorCoder.toElevenDigitColorCode(onCount));
    }


    @ParameterizedTest
    @MethodSource
    void shouldThrowExceptionWhenInvokedWithInvalidOnCount(Executable executable) {
        Assertions.assertThrows(IllegalArgumentException.class,executable);
    }

    static List<Executable> shouldThrowExceptionWhenInvokedWithInvalidOnCount(){
        Executable invalidInvocationFourDigit1 =  () -> ColorCoder.toFourDigitColorCode(-1, Color.RED);
        Executable invalidInvocationFourDigit2 =  () -> ColorCoder.toFourDigitColorCode(-5, Color.RED);
        Executable invalidInvocationElevenDigit1 = () -> ColorCoder.toElevenDigitColorCode(-1);
        Executable invalidInvocationElevenDigit2 = () -> ColorCoder.toElevenDigitColorCode(15);

        return List.of(
                invalidInvocationFourDigit1,
                invalidInvocationFourDigit2,
                invalidInvocationElevenDigit1,
                invalidInvocationElevenDigit2
        );
    }
}
