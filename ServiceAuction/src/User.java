public class User {
    private final String username;
    private final Auction.Notification contact;

    User(String username, Auction.Notification contact) {
        this.username = username;
        this.contact = contact;
    }

    public String getUsername() {
        return this.username;
    }

    public Auction.Notification getContact() {
        return this.contact;
    }
}
