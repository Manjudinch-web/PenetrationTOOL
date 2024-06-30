import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CarParkingSystemGUI extends JFrame {
    private CarPark carPark;
    private JPanel outputTextArea;

    public CarParkingSystemGUI() {
        carPark = new CarPark();

        setTitle("CAR PARKING SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        
        String imagePath = "auto_car-16.jpg"; // Replace "image.jpg" with the path to your image file

        // Create an ImageIcon object with the specified image path
        ImageIcon originalIcon = new ImageIcon(imagePath);
        
        // Get the original image from the ImageIcon
        Image originalImage = originalIcon.getImage();
        
        // Scale the image to the desired size
        int width = 200; // Set the desired width of the image
        int height = 100; // Set the desired height of the image
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        // Create a new ImageIcon with the scaled image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        // Create a JLabel with the scaled ImageIcon
        JLabel imageLabel = new JLabel(scaledIcon);
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(imageLabel, BorderLayout.WEST);
        JLabel titleLabel = new JLabel("CAR PARKING SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 20));
        titlePanel.add(titleLabel, BorderLayout.CENTER); // Add the label to the center
        
        // Add the titlePanel to the NORTH position of your main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);

      

        outputTextArea = new JPanel(new GridLayout(2, 1));
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
      
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between buttons
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make buttons the same size horizontally
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5); // Padding between buttons
        gbc2.fill = GridBagConstraints.HORIZONTAL; // Make buttons the same size horizontally
        
        // Define button size
        Dimension buttonSize = new Dimension(180, 40);

        JButton addSlotButton = new JButton("Add a parking slot");
        addSlotButton.setPreferredSize(buttonSize);
        addSlotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String slotId = JOptionPane.showInputDialog("Enter a parking slot ID (e.g., A123):");
                if (slotId != null && slotId.matches("[A-Z]\\d{3}")) {
                    carPark.addSlot(new ParkingSlot(slotId));
                    SlotButton slotButton = new SlotButton(slotId);
                    outputTextArea.add(slotButton, gbc2);
                    validate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid parking slot ID format. Parking slot identifier must start with a capital letter and be followed by three digits.");
                }
            }
        });
        buttonPanel.add(addSlotButton, gbc);

        JButton deleteSlotButton = new JButton("Delete a parking slot");
        deleteSlotButton.setPreferredSize(buttonSize);
        deleteSlotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String deleteSlotId = JOptionPane.showInputDialog("Enter an existing slot ID to delete:");
                carPark.deleteSlot(deleteSlotId);
                updateOutput("Slot " + deleteSlotId + " deleted from the car park.");
                
                Component[] components = outputTextArea.getComponents();
                for (Component component : components) {
                    if (component instanceof SlotButton) {
                        SlotButton slotButton = (SlotButton) component;
                        if (slotButton.getSlotNumber().equals(deleteSlotId)) {
                            outputTextArea.remove(slotButton);
                            validate();
                            repaint();
                            break;
                        }
                    }
                }
            }
        });
        gbc.gridx = 1;
        buttonPanel.add(deleteSlotButton, gbc);

        JButton listAllSlotsButton = new JButton("List all slots");
        listAllSlotsButton.setPreferredSize(buttonSize);
        listAllSlotsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carPark.listAllSlots();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(listAllSlotsButton, gbc);

        JButton parkCarButton = new JButton("Park a car");
        parkCarButton.setPreferredSize(buttonSize);
        parkCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String parkSlotId = JOptionPane.showInputDialog("Enter an existing slot ID to park the car:");
                ParkingSlot parkingSlot = carPark.findSlotByIdentifier(parkSlotId);
                
                if (parkingSlot != null) {
                    // Validate the parking slot ID
                    if (parkingSlot.isValidSlotIdentifier(parkSlotId)) {
                        String carRego = JOptionPane.showInputDialog("Enter car registration number (e.g., B1234):");
                        
                        // Validate the registration number using the Car class method
                        if (carRego != null && carRego.length() == 5 && 
                            carRego.matches("[A-Z]\\d{4}")) {
                            
                            String carOwner = JOptionPane.showInputDialog("Enter car owner:");
                            String carMake = JOptionPane.showInputDialog("Enter car make:");
                            String carModel = JOptionPane.showInputDialog("Enter car model:");
                            
                            int carYear = 0;
                            boolean validYear = false;
                            while (!validYear) {
                                try {
                                    carYear = Integer.parseInt(JOptionPane.showInputDialog("Enter car year:"));
                                    validYear = true;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid year.");
                                }
                            }
                            
                            // Create the Car object with user input
                            Car car = new Car(carRego, carOwner, carMake, carModel, carYear);
                            
                            // Park the car
                            carPark.parkCar(parkSlotId, car);
                            updateOutput("Car parked successfully in slot " + parkSlotId);
                            
                            // Change the color of the slot button to red
                            Component[] components = outputTextArea.getComponents();
                            for (Component component : components) {
                                if (component instanceof SlotButton) {
                                    SlotButton slotButton = (SlotButton) component;
                                    if (slotButton.getSlotNumber().equals(parkSlotId)) {
                                        slotButton.setBackground(Color.RED);
                                        break;
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid registration number. Please enter a valid registration number.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid parking slot ID which is already created.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Slot " + parkSlotId + " not found.");
                }
            }
        });
        gbc.gridx = 1;
        buttonPanel.add(parkCarButton, gbc);

        JButton findCarButton = new JButton("Find a car by registration number");
        findCarButton.setPreferredSize(buttonSize);
        findCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regNumber = JOptionPane.showInputDialog("Enter the registration number of the car:");
                
                if (regNumber != null && !regNumber.isEmpty()) {
                    String foundSlotNum = carPark.findCarByRegNumber(regNumber);
                    if (foundSlotNum != null) {
                        // Display car details in a popup
                        JOptionPane.showMessageDialog(null, "Car Found in Slot: " + foundSlotNum);
                    } else {
                        JOptionPane.showMessageDialog(null, "Car with registration number " + regNumber + " not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a registration number.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(findCarButton, gbc);

        JButton removeCarButton = new JButton("Remove a car by registration number");
        removeCarButton.setPreferredSize(buttonSize);
        removeCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String regNumber = JOptionPane.showInputDialog("Enter the registration number of the car to remove:");
                
                if (regNumber != null && !regNumber.isEmpty()) {
                    String removedSlot = carPark.removeCar(regNumber);
                    if (removedSlot != null) {
                        updateOutput("Car with registration number " + regNumber + " removed.");                        
                        // Change the color of the slot button to green
                        Component[] components = outputTextArea.getComponents();
                        for (Component component : components) {
                            if (component instanceof SlotButton) {
                                SlotButton slotButton = (SlotButton) component;
                                if (slotButton.getSlotNumber().equals(removedSlot)) {
                                    slotButton.setBackground(Color.GREEN);
                                    break;
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Car with registration number " + regNumber + " not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a registration number.");
                }
            }
        });
        gbc.gridx = 1;
        buttonPanel.add(removeCarButton, gbc);

        
        JButton findCarsByMakeButton = new JButton("Find cars by make");
        findCarsByMakeButton.setPreferredSize(buttonSize);
        findCarsByMakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String make = JOptionPane.showInputDialog("Enter the car make to find:");
                
                if (make != null && !make.isEmpty()) {
                    //carPark.findCarsByMake(make);
                    ArrayList<Car> carsByMake = carPark.findCarsByMake(make);
                    if (!carsByMake.isEmpty()) {
                        String master_string ="";
                        // Display car details in a popup
                        for (Car car : carsByMake) {
                            String foundSlotNum = carPark.findCarByRegNumber(car.getRego());
                            master_string = master_string + "Registration Number: " + car.getRego() + "\n" +
                               "Make: " + car.getMake() + "\n" +
                               "Model: " + car.getModel() + "\n" +
                               "Year: " + car.getYear() + "\n" +
                               "Slot No: " + foundSlotNum + "\n\n";
                        }
                        JOptionPane.showMessageDialog(null, "Car Details:\n" + master_string);
                    } else {
                        JOptionPane.showMessageDialog(null, "No cars found for make " + make);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a car make.");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(findCarsByMakeButton, gbc);

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(buttonSize);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridx = 1;
        buttonPanel.add(exitButton, gbc);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(500, 400); // Adjusted size for better fitting
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Method to update the output text area
    public void updateOutput(String message) {
        // Not implemented in this version
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarParkingSystemGUI());
    }
}

class SlotButton extends JButton {
    private String slotNumber;

    public SlotButton(String slotNumber) {
        this.slotNumber = slotNumber;
        setText(slotNumber);
        setBackground(Color.GREEN);
    }

    public String getSlotNumber() {
        return slotNumber;
    }
}
