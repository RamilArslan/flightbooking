package IBA.model;



public class Flight {
    private long id;
    private int seats;
    private String destination;
    private String location;
    private String date;

    public Flight(int id, int seats, String destination, String location, String date) {
        this.id = id;
        this.seats = seats;
        this.destination = destination;
        this.location = location;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {

        return id + "/" + seats + "/" + location + "->" + destination + "/" + date;
    }

}
