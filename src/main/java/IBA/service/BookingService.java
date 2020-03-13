package IBA.service;

import IBA.model.Booking;
import IBA.model.Flight;
import IBA.model.Person;

import java.util.List;
import java.util.Optional;

import static IBA.dao.BooksDao.bookedFlights;
import static IBA.dao.FlightsScheduleDao.flightList;
import static IBA.dao.SessionDao.currentUser;
import static IBA.service.FlightService.flightService;

public class BookingService {
    public static BookingService bookingService = new BookingService();

    public String bookAFlight(int flightId, List<Person> passengers) {
        if(currentUser == null) return "Please, log in!";
        Optional<Flight> flightInDao = flightList.stream().filter(it -> it.getId() == flightId).findFirst();
        if(!flightInDao.isPresent()) return "Wrong id!";
        if(flightService.getFreeSeatsCountRaw(flightId) < (passengers.size() + 1)) return "No enough seats!";
        Booking newBooking = new Booking(currentUser,flightInDao.get(), passengers);
        bookedFlights.add(newBooking);
        return "Booked!";
    }
    public String cancelBooking(Long flightId){
        if(currentUser == null) return "Please, log in!";
        Optional<Booking> flightInBookingDao = bookedFlights.stream().filter(it -> it.getFlight().getId() == flightId).findFirst();
        if(!flightInBookingDao.isPresent()) return "Wrong id!";
        bookedFlights.remove(flightInBookingDao.get());
        return "Booking canceled!";
    }

    public String getMyBookings() {
        if(currentUser == null) return "Please, log in!";
        StringBuilder booked = new StringBuilder();
        bookedFlights.stream()
                .filter(it -> it.getUser().getUsername().equals(currentUser.getUsername()))
                .forEach(it -> {
                    Flight fl = it.getFlight();
                    booked.append(fl.toString()).append("\n");
        });
        return booked.toString();
    }
}
