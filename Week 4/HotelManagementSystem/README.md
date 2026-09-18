# Hotel Management System (Capstone Project)
### Week 3 — Data Structures & File Handling (Java)

A console-based Hotel Management System that ties together everything from
Week 3: the Collections Framework, sorting/searching, file handling, and
exception handling.

## Features
- **Room management** — add rooms, view all rooms, search by ID (linear search),
  sort rooms by price (Comparator-based sort)
- **Guest management** — register guests, stored in a `HashMap<Integer, Guest>`
  for O(1) lookup by ID
- **Booking workflow** — book a room for a guest for N nights, auto-calculates
  the total bill, marks the room as occupied
- **Check-out** — closes a booking, prints the final bill, frees the room
- **File I/O** — all data (rooms, guests, bookings) is saved to and loaded from
  plain `.txt` files (`rooms.txt`, `guests.txt`, `bookings.txt`) using
  `FileReader`/`FileWriter`, so data survives between runs
- **Exception handling** — a custom checked exception (`HotelException`) is
  thrown for invalid business operations (booking an occupied room, unknown
  guest/room IDs, corrupted data files), and all user input is validated with
  `try/catch` so bad input never crashes the program

## Project Structure
```
src/
  Room.java                  - Room entity + file (de)serialization
  Guest.java                 - Guest entity + file (de)serialization
  Booking.java                - Booking entity + file (de)serialization
  HotelException.java        - Custom checked exception
  FileManager.java           - All File I/O (read/write .txt files)
  HotelManagementSystem.java - Main class: menu loop + business logic
```

## How to Compile & Run

```bash
cd src
javac -d ../bin *.java
cd ../bin
java HotelManagementSystem
```

On exit (option 0), or whenever you choose "Save All Data to File", the
program writes `rooms.txt`, `guests.txt`, and `bookings.txt` into the
directory you ran it from. Next time you launch it, that data is loaded
back in automatically.

## Sample Menu
```
 1. Add Room
 2. View All Rooms
 3. Search Room by ID
 4. Sort Rooms by Price
 5. Add Guest
 6. View All Guests
 7. Book a Room
 8. Check-Out (End a Booking)
 9. View All Bookings
10. Save All Data to File
11. Reload All Data from File
 0. Exit
```

## Concepts Demonstrated (mapped to Week 3 topics)
| Week 3 Topic                          | Where it's used                                   |
|----------------------------------------|----------------------------------------------------|
| Collections Framework (List, Map)      | `ArrayList<Room>`, `ArrayList<Booking>`, `HashMap<Integer, Guest>` |
| ArrayList, HashMap                     | Same as above                                       |
| Sorting algorithms                     | `rooms.sort(Comparator...)` in `sortRoomsByPrice()` |
| Searching algorithms                   | Linear search in `searchRoomById()` / `findRoomById()` |
| File Handling (FileReader/FileWriter)  | `FileManager.java`                                  |
| Exception Handling (try/catch/finally/throw/throws) | `HotelException`, input validation, `run()` method |
