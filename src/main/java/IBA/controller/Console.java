package IBA.controller;

import IBA.dao.FlightsScheduleDao;
import IBA.model.Flight;
import IBA.service.BookingService;
import IBA.tool.DataProcessor;

import java.util.InputMismatchException;
import java.util.Scanner;

import static IBA.dao.FlightsScheduleDao.flightList;
import static IBA.dao.SessionDao.currentUser;
import static IBA.service.FlightService.flightService;
import static IBA.service.UserService.userService;

public class Console {
    public static void main(String[] args) {
        Flight flight1 = new Flight(1, 100, "Moscow", "Kiev", "2020-01-30");
        Flight flight2 = new Flight(2, 75, "Baku", "Kiev", "2020-02-04");
        Flight flight3 = new Flight(3, 100, "Istanbul", "Kiev", "2020-02-12");
        Flight flight4 = new Flight(4, 100, "Oslo", "Kiev", "2020-02-25");

        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        flightList.add(flight4);
//        DataProcessor dataProcessor = new DataProcessor();


        FlightsScheduleDao flights = new FlightsScheduleDao();
        while (true) {
            if (currentUser == null) {
                userLogInMenu();
            } else {
                flightsMenu();
            }
        }
    }

    private static void flightsMenu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome, " + currentUser.getUsername() + "! Available commands:");
        System.out.println(" 1-| Online-board\n 2-| Show the flight info \n 3-| Search and book a flight\n 4-| Cancel booking\n 5-| My flights\n 6-| Log out");
        String input = sc.nextLine();

        BookingService bookings = new BookingService();
        switch (input) {
            case "1":
                for (Flight flights : flightList) {
                    System.out.println(flights.toString());
                }
                break;
            case "2": {
                long flightId;
                System.out.println("Enter the flight id:");
                try {
                    flightId = Long.parseLong(sc.nextLine());
                } catch (InputMismatchException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println(flightService.getFlightInfo(flightId));
            }
            break;
            case "3": {
                long flightId;
                System.out.println("Enter the flight id:");
                try {
                    flightId = Long.parseLong(sc.nextLine());
                } catch (InputMismatchException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println("Enter the additional passengers count");
            }
            break;
            case "4":

                break;
            case "5":
                System.out.println(bookings.getMyBookings());

                break;

            case "6":
                currentUser = null;
                System.out.println("Logged off");
                break;
            default:
                System.out.println("Wrong command!");


        }

    }

    private static void userLogInMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________");
        System.out.println("|You are not logged in. Available commands:|");
        System.out.println("|     new user   *******   log in          |");
        System.out.println("____________________________________________");

        String input = sc.nextLine();
        switch (input) {
            case "new user":
                while (true) {
                    String username;
                    String password;
                    System.out.println("Please, enter the username:");
                    username = sc.nextLine();
                    if (!username.matches("[a-zA-Z0-9]+")) {
                        System.out.println("Wrong username format. Username must contain ancaq latin herfleri.");
                        continue;
                    }

                    System.out.println("Please, enter the password:");
                    password = sc.nextLine();

                    System.out.println(userService.createUser(username, password));
                    break;
                }
                break;
            case "log in":
                String username;
                String password;
                System.out.println("Please, enter the username:");
                username = sc.nextLine();
                System.out.println("Please, enter the password:");
                password = sc.nextLine();

                System.out.println(userService.login(username, password));
                break;
            default:
                System.out.println("Wrong command!");
        }
    }


}
