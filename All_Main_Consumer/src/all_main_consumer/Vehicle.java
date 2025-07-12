package all_main_consumer;

public abstract class Vehicle {
    private String id;
    private String type;
    private int seats;
    private double price;

    public Vehicle(String id, String type, int seats, double price) {
        this.id = id;
        this.type = type;
        this.seats = seats;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
