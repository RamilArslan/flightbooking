package IBA.service;

import IBA.model.Flight;

import java.util.Optional;

import static IBA.dao.BooksDao.bookedFlights;
import static IBA.dao.FlightsScheduleDao.flightList;
import static IBA.dao.FlightsScheduleDao.getFlightById;

public class FlightService {
    public static FlightService flightService = new FlightService();

    public String createFlight(Flight flight) {
        flightList.add(flight);
        return "Flight added to list!";
    }
    public String getFreeSeatsCount(long flightId) {
        Optional<Flight> flightInDao = getFlightById(flightId);
        if(!flightInDao.isPresent()) return "Wrong id!";
        int bookedSeatsCount = bookedFlights.stream()
                .filter(it -> it.getFlight().getId() == flightId)
                .map(it -> it.getPassengers().size()).reduce(0, Integer::sum);
        String message = "Available seats count: ";
        return message + (flightInDao.get().getSeats() - bookedSeatsCount);
    }
    public int getFreeSeatsCountRaw(long flightId) {
        Optional<Flight> flightInDao = getFlightById(flightId);
        if(!flightInDao.isPresent()) return 0;
        int bookedSeatsCount = bookedFlights.stream()
                .filter(it -> it.getFlight().getId() == flightId)
                .map(it -> it.getPassengers().size()).reduce(0, Integer::sum);
        return flightInDao.get().getSeats() - bookedSeatsCount;
    }


    public String getFlightsSchedule() {
        StringBuilder sb = new StringBuilder();
        flightList.forEach(it -> sb.append(it.toString()));
        return sb.toString();
    }

    public String getFlightInfo(long flightId) {
        Optional<Flight> flightInDao = getFlightById(flightId);
        if(!flightInDao.isPresent()) return "Wrong id!";
        return flightInDao.get().toString() + "; Free seats: " +  getFreeSeatsCount(flightId);
    }


}
