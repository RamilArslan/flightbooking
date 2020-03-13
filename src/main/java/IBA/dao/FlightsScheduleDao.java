package IBA.dao;

import IBA.model.Flight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightsScheduleDao implements Serializable {
    public static List<Flight> flightList = new ArrayList<Flight>();

    public static Optional<Flight> getFlightById(long flightId) {
        return flightList.stream().filter(it -> it.getId() == flightId).findFirst();
    }
}
