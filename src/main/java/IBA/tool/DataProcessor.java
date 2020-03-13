package IBA.tool;

import IBA.dao.BooksDao;
import IBA.dao.FlightsScheduleDao;
import IBA.dao.UserDao;

import java.io.*;

import static IBA.dao.BooksDao.bookedFlights;

public class DataProcessor {
    private static final String BOOKS_LOC = "project_data_books.sc";
    private static final String FLIGHTS_LOC = "project_data_flights.sc";
    private static final String USERS_LOC = "project_data_users.sc";

    public DataProcessor() {
        File booksFile = new File(BOOKS_LOC);
        File flightsFile = new File(FLIGHTS_LOC);
        File usersFile = new File(USERS_LOC);


        BooksDao booksDao = (BooksDao) gatherFromFile(booksFile);
        FlightsScheduleDao flightsScheduleDao = (FlightsScheduleDao) gatherFromFile(flightsFile);
        UserDao userDao = (UserDao) gatherFromFile(usersFile);

        bookedFlights = bookedFlights;

    }

    private boolean writeToFile(Serializable obj, File file) {
        try {
            //Saving of object in a fos
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);

            // Method for serialization of object
            out.writeObject(obj);
            out.close();
            fos.close();

        } catch (IOException ex) {
            System.out.println("No file found!!!!!!!!!!!!");
            return false;
        }
        return true;

    }

    private Serializable gatherFromFile(File file) {
        try {
            //Saving of object in a fis
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fis);

            Serializable succ;
            // Method for serialization of object
            try {
                succ = (Serializable)in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            in.close();
            fis.close();

        } catch (IOException ex) {
            System.out.println("IO exception!!!!!!!!!!");
            return false;
        }
        return true;

    }

}
