/**
 * Represents a single hotel room.
 * Demonstrates encapsulation and simple file-serialization (toFileString / fromFileString)
 * used by FileManager for File I/O (Week 3 topic).
 */
public class Room {
    private int roomId;
    private String roomType;      // e.g. Single, Double, Deluxe, Suite
    private double pricePerNight;
    private boolean available;

    public Room(int roomId, String roomType, double pricePerNight, boolean available) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.available = available;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    /** Converts this room to a single CSV line for saving to a text file. */
    public String toFileString() {
        return roomId + "," + roomType + "," + pricePerNight + "," + available;
    }

    /**
     * Parses a CSV line (as written by toFileString) back into a Room object.
     * Throws HotelException if the line is malformed, demonstrating exception handling.
     */
    public static Room fromFileString(String line) throws HotelException {
        try {
            String[] parts = line.split(",");
            if (parts.length != 4) {
                throw new HotelException("Malformed room record: " + line);
            }
            int id = Integer.parseInt(parts[0].trim());
            String type = parts[1].trim();
            double price = Double.parseDouble(parts[2].trim());
            boolean isAvailable = Boolean.parseBoolean(parts[3].trim());
            return new Room(id, type, price, isAvailable);
        } catch (NumberFormatException e) {
            throw new HotelException("Invalid number format in room record: " + line);
        }
    }

    @Override
    public String toString() {
        return String.format("Room #%-4d | Type: %-10s | Price/Night: $%-8.2f | %s",
                roomId, roomType, pricePerNight, available ? "AVAILABLE" : "OCCUPIED");
    }
}
