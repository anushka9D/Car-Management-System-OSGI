package car_producer;

public class CarServiceImpl implements CarService {

    public void displayVehicleOptions() {
        System.out.println("\nSelect Vehicle Type:");
        System.out.println("1. Car");
        System.out.println("2. Van");
        System.out.println("3. Bike");
    }

    public void displayCarDetails() {
        System.out.println("\nCar Details:");
        System.out.println("-------------------------------------------------");
        System.out.println("| Type      | Seats | Price per Day | Fuel Type |");
        System.out.println("-------------------------------------------------");
        System.out.println("| Sedan     |  4    | $50           | Petrol    |");
        System.out.println("| SUV       |  7    | $80           | Diesel    |");
        System.out.println("| Hatchback |  4    | $40           | Petrol    |");
        System.out.println("-------------------------------------------------");
    }

    public void displayVanDetails() {
        System.out.println("\nVan Details:");
        System.out.println("--------------------------------------------------");
        System.out.println("| Type       | Seats | Price per Day | Fuel Type |");
        System.out.println("--------------------------------------------------");
        System.out.println("| Mini Van   |  7    | $70           | Petrol    |");
        System.out.println("| Cargo Van  |  2    | $60           | Diesel    |");
        System.out.println("| Passenger  | 12    | $100          | Diesel    |");
        System.out.println("--------------------------------------------------");
    }

    public void displayBikeDetails() {
        System.out.println("\nBike Details:");
        System.out.println("-----------------------------------------------");
        System.out.println("| Type       | Engine CC | Price per Day |");
        System.out.println("-----------------------------------------------");
        System.out.println("| Scooter    |  125cc    | $15           |");
        System.out.println("| Sports     |  600cc    | $50           |");
        System.out.println("| Cruiser    |  900cc    | $70           |");
        System.out.println("-----------------------------------------------");
    }
}
