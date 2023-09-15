package ru.itmo.se.bl.lab3.model;

public enum BookingResult {
    SUCCESSFUL_BOOKING(true, "Booking was successful"),
    INVALID_TRAVEL(false, "Invalid travel"),
    TRAVEL_BOOK_FAILURE(false, "Booking failed due to travel booking failure"),
    INVALID_HOTEL(false, "Invalid hotel"),
    HOTEL_BOOK_FAILURE(false, "Booking failed due to hotel booking failure"),
    ;
    private final boolean successful;
    private final String message;

    private BookingResult(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}
