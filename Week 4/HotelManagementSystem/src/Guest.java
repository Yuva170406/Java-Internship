/**
 * Represents a hotel guest.
 */
public class Guest {
    private int guestId;
    private String name;
    private String contactNumber;

    public Guest(int guestId, String name, String contactNumber) {
        this.guestId = guestId;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public int getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String toFileString() {
        return guestId + "," + name + "," + contactNumber;
    }

    public static Guest fromFileString(String line) throws HotelException {
        try {
            String[] parts = line.split(",");
            if (parts.length != 3) {
                throw new HotelException("Malformed guest record: " + line);
            }
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String contact = parts[2].trim();
            return new Guest(id, name, contact);
        } catch (NumberFormatException e) {
            throw new HotelException("Invalid number format in guest record: " + line);
        }
    }

    @Override
    public String toString() {
        return String.format("Guest #%-4d | Name: %-15s | Contact: %s", guestId, name, contactNumber);
    }
}
