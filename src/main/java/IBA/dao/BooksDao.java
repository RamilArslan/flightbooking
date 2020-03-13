package IBA.dao;

import IBA.model.Booking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BooksDao implements Serializable {
    public static List<Booking> bookedFlights = new ArrayList<Booking>();
}
