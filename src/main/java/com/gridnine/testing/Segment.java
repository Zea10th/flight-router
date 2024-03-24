package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

class Segment {
    public static final String FORMATTER_PATTERN = "yyyy-MM-dd'T'HH:mm";
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;

    Segment(final LocalDateTime departureDateTime, final LocalDateTime arrivalDateTime) {
        this.departureDateTime = Objects.requireNonNull(departureDateTime);
        this.arrivalDateTime = Objects.requireNonNull(arrivalDateTime);
    }

    LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(FORMATTER_PATTERN);
        return '[' + departureDateTime.format(fmt) + '|' + arrivalDateTime.format(fmt) + ']';
    }
}
