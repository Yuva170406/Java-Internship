/**
 * Represents a booking that links a Guest to a Room for a number of nights.
 */
public class Booking {
    private int bookingId;
    private int roomId;
    private int guestId;
    private int numberOfNights;
    private double totalAmount;
    private String status; // "ACTIVE" or "CHECKED_OUT"

    public Booking(int bookingId, int roomId, int guestId, int numberOfNights,
                    double totalAmount, String status) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.guestId = guestId;
        this.numberOfNights = numberOfNights;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getGuestId() {
        return guestId;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return bookingId + "," + roomId + "," + guestId + "," + numberOfNights
                + "," + totalAmount + "," + status;
    }

    public static Booking fromFileString(String line) throws HotelException {
        try {
            String[] parts = line.split(",");
            if (parts.length != 6) {
                throw new HotelException("Malformed booking record: " + line);
            }
            int id = Integer.parseInt(parts[0].trim());
            int roomId = Integer.parseInt(parts[1].trim());
            int guestId = Integer.parseInt(parts[2].trim());
            int nights = Integer.parseInt(parts[3].trim());
            double amount = Double.parseDouble(parts[4].trim());
            String status = parts[5].trim();
            return new Booking(id, roomId, guestId, nights, amount, status);
        } catch (NumberFormatException e) {
            throw new HotelException("Invalid number format in booking record: " + line);
        }
    }

    @Override
    public String toString() {
        return String.format("Booking #%-4d | Room #%-4d | Guest #%-4d | %d night(s) | Total: $%-8.2f | %s",
                bookingId, roomId, guestId, numberOfNights, totalAmount, status);
    }
}
