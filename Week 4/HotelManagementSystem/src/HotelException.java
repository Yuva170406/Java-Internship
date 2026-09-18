/**
 * Custom checked exception used throughout the system for invalid input
 * and business-logic errors (e.g. booking an unavailable room, unknown IDs,
 * malformed data read from file). Demonstrates try/catch/finally/throw/throws.
 */
public class HotelException extends Exception {
    public HotelException(String message) {
        super(message);
    }
}
