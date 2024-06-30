
/**
 * Application class is the main class of this programme
 *
 * @autho Md Nur Hossen Rafel
 * @version 09-03-2024
 * id: 104330222
 */
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
class Application {
    public static void main(String[] args) {
        CarPark carPark = new CarPark();
        //Car c1 = new Car("B1234", "Leo", "Toyota", "X2354", 2024);
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add a parking slot");
            System.out.println("2. Delete a parking slot");
            System.out.println("3. List all slots");
            System.out.println("4. Park a car");
            System.out.println("5. Find a car by registration number");
            System.out.println("6. Remove a car by registration number");
            System.out.println("7. Find cars by make");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter a parking slot ID(e.g A123): ");
                    String slotId = scanner.nextLine();

                    // Check if the parking slot ID is valid
                    if (slotId.matches("[A-Z]\\d{3}")) {
                        carPark.addSlot(new ParkingSlot(slotId));
                    } else {
                        System.out.println("Invalid parking slot ID format. Parking slot identifier must start with a capital letter and be followed by three digits.");
                    }
                    break;

                case 2:
                    System.out.print("Enter a existing slot ID to delete: ");
                    String deleteSlotId = scanner.nextLine();
                    carPark.deleteSlot(deleteSlotId);
                    break;

                case 3:
                    carPark.listAllSlots();

                case 4:
                    do {
                        System.out.print("Enter a existing slot ID to park the car: ");
                        String parkSlotId = scanner.nextLine();
                
                // checking parking slot ID
                ParkingSlot parkingSlot = carPark.findSlotByIdentifier(parkSlotId);
                if (parkingSlot != null) {
                    // Validate the current identifier 
                    if (parkingSlot.isValidSlotIdentifier(parkingSlot.getIdentifier())) {
                        String carRego;

                        
                        System.out.print("Enter car registration number(e.g. B1234): ");
                        carRego = scanner.nextLine();
        
                        // Create the Car object with user input
                        Car car = new Car(carRego, "", "", "", 0); 

                // Validate the registration number using the Car class method
                if (car.setRego(carRego)) {
                    System.out.print("Enter car owner: ");
                    String carOwner = scanner.nextLine();
                    System.out.print("Enter car make(e.g. Toyota): ");
                    String carMake = scanner.nextLine();
                    System.out.print("Enter car model(e.g.corolla): ");
                    String carModel = scanner.nextLine();
                    System.out.print("Enter car year(e.g. 2024): ");
                    int carYear = scanner.nextInt();
                    scanner.nextLine();  

                    // Set the rest the other value
                    car.setOwner(carOwner);
                    car.setMake(carMake);
                    car.setModel(carModel);
                    car.setYear(carYear);

                    // Proceed with parking the car
                    carPark.parkCar(parkSlotId, car);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    System.out.println("Car parked successfully on " + parkSlotId + " at " + LocalTime.now().format(formatter));
                    break; 
                        } else {
                            System.out.println("Invalid registration number. Please enter a valid registration number.");
                        }
                    } else {
                            System.out.println("Please enter a valid parking slot ID which is already created. Please try again.");
                        }
                    } else {
                        System.out.println("Please enter a valid parking slot ID which is already created. Please try again.");
                        }
                    } while (true); 
                    break; // Exit the case 4 block

                case 5:
                    System.out.print("Enter car registration number to find: ");
                    String findRegNumber = scanner.nextLine();
                    carPark.findCarByRegNumber(findRegNumber);
                    break;

                case 6:
                    System.out.print("Enter car registration number to remove: ");
                    String removeRegNumber = scanner.nextLine();
                    carPark.removeCar(removeRegNumber);
                    break;

                case 7:
                    System.out.print("Enter car make to find: ");
                    String findMake = scanner.nextLine();
                    carPark.findCarsByMake(findMake);
                    break;

                case 8:
                    System.out.println("Thanks for using our application!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 8);

        scanner.close();
    }
}