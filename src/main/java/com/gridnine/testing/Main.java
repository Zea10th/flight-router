package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        FlightRouter flightRouter = new FlightRouter(FlightBuilder.createFlights());

        Rule departuresBeforeNowRule = flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getDepartureDateTime().isBefore(LocalDateTime.now()));

        System.out.println("Flights that departures before now:");
        flightRouter.filterByRule(departuresBeforeNowRule).forEach(System.out::println);

        Rule arrivesBeforeDeparturesRule = flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getArrivalDateTime().isBefore(segment.getDepartureDateTime()));

        System.out.println("Flights that has segments where arrivals before departures:");
        flightRouter.filterByRule(arrivesBeforeDeparturesRule).forEach(System.out::println);

        Rule transfersMoreThanTwoHoursRule = flight -> {
            List<Segment> segments = flight.getSegments();
            Duration totalStopDuration = IntStream.range(0, segments.size() - 1)
                    .mapToObj(i -> Duration.between(
                            segments.get(i).getArrivalDateTime(),
                            segments.get(i + 1).getDepartureDateTime()))
                    .reduce(Duration.ZERO, Duration::plus);

            return totalStopDuration.toMillis() > 7200_000L;
        };

        System.out.println("Flights with a total transfer time of more than 2 hours:");
        flightRouter.filterByRule(transfersMoreThanTwoHoursRule).forEach(System.out::println);
    }
}
