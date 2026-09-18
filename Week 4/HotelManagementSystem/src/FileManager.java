import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all persistence for the Hotel Management System using
 * FileReader / FileWriter, as required by the Week 3 File Handling topic.
 * Each entity type is stored as a simple CSV text file.
 */
public class FileManager {

    public static void saveRooms(List<Room> rooms, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Room room : rooms) {
                writer.write(room.toFileString());
                writer.newLine();
            }
        }
    }

    public static List<Room> loadRooms(String filename) throws IOException, HotelException {
        List<Room> rooms = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return rooms; // nothing to load yet
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    rooms.add(Room.fromFileString(line));
                }
            }
        }
        return rooms;
    }

    public static void saveGuests(List<Guest> guests, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Guest guest : guests) {
                writer.write(guest.toFileString());
                writer.newLine();
            }
        }
    }

    public static List<Guest> loadGuests(String filename) throws IOException, HotelException {
        List<Guest> guests = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return guests;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    guests.add(Guest.fromFileString(line));
                }
            }
        }
        return guests;
    }

    public static void saveBookings(List<Booking> bookings, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Booking booking : bookings) {
                writer.write(booking.toFileString());
                writer.newLine();
            }
        }
    }

    public static List<Booking> loadBookings(String filename) throws IOException, HotelException {
        List<Booking> bookings = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return bookings;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    bookings.add(Booking.fromFileString(line));
                }
            }
        }
        return bookings;
    }
}
