public enum MenuOptions {
    EXIT("Exit"),
    VIEW_ROOMS("View Available Rooms"),
    CHECKIN("Book a Room."),
    CHECKOUT("Checkout a room"),
    UPDATE("Update a room");
    private final String title;

    MenuOptions(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
