import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * HOTEL MANAGEMENT SYSTEM - Capstone Project
 * Week 3: Data Structures & File Handling
 * <p>
 * Demonstrates:
 *  - Java Collections Framework (List, Map) via ArrayList and HashMap
 *  - Sorting and Searching algorithms
 *  - File Handling (FileReader / FileWriter, via FileManager)
 *  - Exception Handling (try, catch, finally, throw, throws)
 */
public class HotelManagementSystem {

    private static final String ROOMS_FILE = "rooms.txt";
    private static final String GUESTS_FILE = "guests.txt";
    private static final String BOOKINGS_FILE = "bookings.txt";

    // Collections Framework in action: List for ordered records, Map for fast ID lookup.
    private final List<Room> rooms = new ArrayList<>();
    private final Map<Integer, Guest> guests = new HashMap<>();
    private final List<Booking> bookings = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        HotelManagementSystem system = new HotelManagementSystem();
        system.run();
    }

    private void run() {
        System.out.println("==========================================");
        System.out.println("   WELCOME TO THE HOTEL MANAGEMENT SYSTEM  ");
        System.out.println("==========================================");
        loadAllData(); // try to auto-load any previously saved data

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1: addRoom(); break;
                    case 2: viewAllRooms(); break;
                    case 3: searchRoomById(); break;
                    case 4: sortRoomsByPrice(); break;
                    case 5: addGuest(); break;
                    case 6: viewAllGuests(); break;
                    case 7: bookRoom(); break;
                    case 8: checkOut(); break;
                    case 9: viewAllBookings(); break;
                    case 10: saveAllData(); break;
                    case 11: loadAllData(); break;
                    case 0:
                        running = false;
                        System.out.println("Saving data before exit...");
                        saveAllData();
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid menu option.");
                }
            } catch (HotelException e) {
                // Business-logic errors (e.g. room unavailable, unknown ID)
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println(); // blank line for readability after every action
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("------------------ MENU ------------------");
        System.out.println(" 1. Add Room");
        System.out.println(" 2. View All Rooms");
        System.out.println(" 3. Search Room by ID");
        System.out.println(" 4. Sort Rooms by Price");
        System.out.println(" 5. Add Guest");
        System.out.println(" 6. View All Guests");
        System.out.println(" 7. Book a Room");
        System.out.println(" 8. Check-Out (End a Booking)");
        System.out.println(" 9. View All Bookings");
        System.out.println("10. Save All Data to File");
        System.out.println("11. Reload All Data from File");
        System.out.println(" 0. Exit");
        System.out.println("-------------------------------------------");
    }

    // ---------- ROOM OPERATIONS ----------

    private void addRoom() {
        int id = getNextRoomId();
        System.out.print("Enter room type (Single/Double/Deluxe/Suite): ");
        String type = scanner.nextLine().trim();
        double price = readDouble("Enter price per night: $");
        Room room = new Room(id, type.isEmpty() ? "Standard" : type, price, true);
        rooms.add(room);
        System.out.println("Room added successfully -> " + room);
    }

    private void viewAllRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms in the system yet.");
            return;
        }
        System.out.println("---- All Rooms ----");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    /** Linear search by room ID (Searching algorithm topic). */
    private void searchRoomById() throws HotelException {
        int id = readInt("Enter room ID to search: ");
        for (Room room : rooms) {
            if (room.getRoomId() == id) {
                System.out.println("Found -> " + room);
                return;
            }
        }
        throw new HotelException("No room found with ID " + id);
    }

    /** Sorting algorithm topic: sorts rooms by price using a Comparator. */
    private void sortRoomsByPrice() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms to sort.");
            return;
        }
        rooms.sort(Comparator.comparingDouble(Room::getPricePerNight));
        System.out.println("Rooms sorted by price (ascending):");
        viewAllRooms();
    }

    private int getNextRoomId() {
        int max = 0;
        for (Room room : rooms) {
            max = Math.max(max, room.getRoomId());
        }
        return max + 1;
    }

    // ---------- GUEST OPERATIONS ----------

    private void addGuest() {
        int id = getNextGuestId();
        System.out.print("Enter guest name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter contact number: ");
        String contact = scanner.nextLine().trim();
        Guest guest = new Guest(id, name, contact);
        guests.put(id, guest); // HashMap keyed by guestId for O(1) lookup
        System.out.println("Guest added successfully -> " + guest);
    }

    private void viewAllGuests() {
        if (guests.isEmpty()) {
            System.out.println("No guests registered yet.");
            return;
        }
        System.out.println("---- All Guests ----");
        for (Guest guest : guests.values()) {
            System.out.println(guest);
        }
    }

    private int getNextGuestId() {
        int max = 0;
        for (int id : guests.keySet()) {
            max = Math.max(max, id);
        }
        return max + 1;
    }

    // ---------- BOOKING OPERATIONS ----------

    private void bookRoom() throws HotelException {
        int roomId = readInt("Enter room ID to book: ");
        Room roomToBook = findRoomById(roomId);
        if (roomToBook == null) {
            throw new HotelException("Room ID " + roomId + " does not exist.");
        }
        if (!roomToBook.isAvailable()) {
            throw new HotelException("Room " + roomId + " is currently occupied.");
        }

        int guestId = readInt("Enter guest ID: ");
        if (!guests.containsKey(guestId)) {
            throw new HotelException("Guest ID " + guestId + " is not registered. Add the guest first.");
        }

        int nights = readInt("Enter number of nights: ");
        if (nights <= 0) {
            throw new HotelException("Number of nights must be positive.");
        }

        double total = nights * roomToBook.getPricePerNight();
        int bookingId = getNextBookingId();
        Booking booking = new Booking(bookingId, roomId, guestId, nights, total, "ACTIVE");
        bookings.add(booking);
        roomToBook.setAvailable(false);

        System.out.println("Booking confirmed -> " + booking);
    }

    private void checkOut() throws HotelException {
        int bookingId = readInt("Enter booking ID to check out: ");
        Booking booking = findActiveBookingById(bookingId);
        if (booking == null) {
            throw new HotelException("No active booking found with ID " + bookingId);
        }
        booking.setStatus("CHECKED_OUT");
        Room room = findRoomById(booking.getRoomId());
        if (room != null) {
            room.setAvailable(true);
        }
        System.out.printf("Check-out complete. Total bill for booking #%d was $%.2f%n",
                booking.getBookingId(), booking.getTotalAmount());
    }

    private void viewAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        System.out.println("---- All Bookings ----");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private Room findRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    private Booking findActiveBookingById(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId && booking.getStatus().equals("ACTIVE")) {
                return booking;
            }
        }
        return null;
    }

    private int getNextBookingId() {
        int max = 0;
        for (Booking booking : bookings) {
            max = Math.max(max, booking.getBookingId());
        }
        return max + 1;
    }

    // ---------- FILE I/O OPERATIONS ----------

    private void saveAllData() {
        try {
            FileManager.saveRooms(rooms, ROOMS_FILE);
            FileManager.saveGuests(new ArrayList<>(guests.values()), GUESTS_FILE);
            FileManager.saveBookings(bookings, BOOKINGS_FILE);
            System.out.println("All data saved to " + ROOMS_FILE + ", " + GUESTS_FILE + ", " + BOOKINGS_FILE);
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    private void loadAllData() {
        try {
            List<Room> loadedRooms = FileManager.loadRooms(ROOMS_FILE);
            List<Guest> loadedGuests = FileManager.loadGuests(GUESTS_FILE);
            List<Booking> loadedBookings = FileManager.loadBookings(BOOKINGS_FILE);

            rooms.clear();
            rooms.addAll(loadedRooms);

            guests.clear();
            for (Guest guest : loadedGuests) {
                guests.put(guest.getGuestId(), guest);
            }

            bookings.clear();
            bookings.addAll(loadedBookings);

            System.out.println("Loaded " + rooms.size() + " room(s), " + guests.size()
                    + " guest(s), " + bookings.size() + " booking(s) from file.");
        } catch (IOException e) {
            System.out.println("Failed to load data: " + e.getMessage());
        } catch (HotelException e) {
            System.out.println("Data file appears corrupted: " + e.getMessage());
        }
    }

    // ---------- INPUT HELPERS (robust exception handling for user input) ----------

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(line);
                if (value < 0) {
                    System.out.println("Price cannot be negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
