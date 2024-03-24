package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FlightBuilder {
    private static final String NOT_EVEN_NUMBER_OF_DETAILS_MESSAGE =
            "Even number of dateTimeDetails should be provided";

    private FlightBuilder(){}
    static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                //A normal multi segment flight
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                //A flight departing in the past
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                //A flight that departs before it arrives
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                //A flight with more than two hours ground time
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    private static Flight createFlight(final LocalDateTime... dateTimeDetails) {
        if ((dateTimeDetails.length % 2) != 0) {
            throw new IllegalArgumentException(NOT_EVEN_NUMBER_OF_DETAILS_MESSAGE);
        }

        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < dateTimeDetails.length - 1; i += 2) {
            segments.add(new Segment(dateTimeDetails[i], dateTimeDetails[i + 1]));
        }

        return new Flight(segments);
    }
}
