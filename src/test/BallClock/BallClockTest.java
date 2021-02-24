package BallClock;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BallClockTest {

    static Stream<Arguments> argumentProvider() {
        return Stream.of(
                Arguments.of(30, 15),
                Arguments.of(45, 378),
                Arguments.of(123, 108855)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    void ballClockTest (int ballCount, int expectedDays) {
        BallClockOperation testClock = new BallClockOperation(ballCount);
        assertEquals(expectedDays, testClock.calculateDaysToCycle());
    }
}