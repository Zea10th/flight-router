package com.gridnine.testing;

import java.util.List;

public class FlightRouter {
    private List<Flight> flightList;

    FlightRouter(List<Flight> flightList) {
        this.flightList = flightList;
    }

    List<Flight> filterByRule(Rule rule) {
        return flightList.stream()
                .filter(rule::check)
                .toList();
    }
}
