package BallClock.util;

import BallClock.BallClockOperation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static BallClock.util.Util.isValidIntegerInput;
import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    static Stream<Arguments> argumentProvider() {
        return Stream.of(
                Arguments.of("thirty", -1),
                Arguments.of("1T", -1),
                Arguments.of("30", 30),
                Arguments.of("127", 127),
                Arguments.of("27", 27),
                Arguments.of("26", -1),
                Arguments.of("128", -1)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    void utilTest (String inputVal, int expectResult) {
        assertEquals(expectResult, isValidIntegerInput(inputVal));
    }
}