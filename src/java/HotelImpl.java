
import customer.Customer;
import room.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HotelImpl implements Hotel {

    private String path ;
    private Room[][] hotel;
    private Customer customer;
    Scanner console = new Scanner(System.in);


    public HotelImpl(String filePath) throws IOException {
        hotel = new Room[X][Y];
        Arrays.stream(hotel).forEach(cell -> Arrays.fill(cell, new Room(false, null)));
        fillDummyHotel();
        this.path = filePath;
    }


    public void fillDummyHotel() throws IOException {
//        List<String> customersCheckedIn = Files.readAllLines(Paths.get(path));

        int customersInHotel = 5;

        Random rand = new Random();

        for (int i = 0; i < customersInHotel; i++) {
            int randX = rand.nextInt(X);
            int randY = rand.nextInt(Y);

            Customer dummy = new Customer(i, "DUMMY CUSTOMER", 10, randX, randY);// dummy customer information
            hotel[dummy.getX()][dummy.getY()] = new Room(true, customer);
        }
    }


    @Override
    public boolean addHotelCustomer(Customer customer) {
        ArrayList<Customer> all = findAllCustomers();
        if (validateRoom(customer)) {
            //List<Customer> all = findAllCustomers();
            customer.setId(getNextId(all));
            all.add(customer);
            writeAll(all);
            hotel[customer.getX()][customer.getY()] = new Room(true, customer);
            return true;

        } else if (!validateRoom(customer)) {
            System.out.println("Already booked");
        }
        return false;
    }


    public boolean validateRoom(Customer customer) {
        for (int row = 0; row < hotel.length; row++) {
            for (int col = 0; col < hotel[row].length; col++) {
                if (!hotel[customer.getX()][customer.getY()].isHasCustomer()) {
                    System.out.println("You Successfully booked the room.");
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void showHotel() {
        // show 2d list
        System.out.println("--------    H O T E L   --------");

        for (Room[] row : hotel) {
            for (Room el : row) {

                if (el.isHasCustomer()) {
                    System.out.printf("| %c ", Room.getBookedSymbol());
                } else {
                    System.out.printf("| %c ", Room.getRoomSymbol());
                }
            }
            System.out.println("|");
        }
    }


    @Override
    public Room getRoom(int x, int y) {
        return hotel[x][y];
    }

    public Room[][] getHotel() {
        return hotel;
    }

    public void setHotel(Room[][] hotel) {
        this.hotel = hotel;
    }


    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return "HotelImpl{" +
                "hotel=" + Arrays.toString(hotel) +
                ", customer=" + customer +
                '}';
    }
    //create a find all method first

    public ArrayList<Customer> findAllCustomers() {
        ArrayList<Customer> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    Customer customer = new Customer();
                    customer.setId(Integer.parseInt(fields[0]));
                    customer.setName(fields[1]);
                    customer.setAge(Integer.parseInt(fields[2]));
                    customer.setX(Integer.parseInt(fields[3]));
                    customer.setY(Integer.parseInt(fields[4]));
                    result.add(customer);
                }

            }


        } catch (Exception e) {
            System.out.println("An Error Occurred :(" + e);
        }
        return result;
    }


    public boolean removeCustomer(int customerId) throws IOException {

        try {
            List<Customer> all = findAllCustomers();
            for (int index = 0; index < all.size(); index++) {
                if (all.get(index).getId() == customerId) {
                    hotel[all.get(index).getX()][all.get(index).getY()] = new Room(false, null);
                    all.remove(index);
                    writeAll(all);
                    System.out.println("You successfully remove a customer from a hotel");
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("An Error Occurred");
        }
        return false;
    }

    private void writeAll(List<Customer> customers) {
        try (PrintWriter writer = new PrintWriter(path)) {
            for (Customer customer : customers) {
                String searalize = String.format("%s,%s,%s,%s,%s",
                        customer.getId(), customer.getName(), customer.getAge(), customer.getX(), customer.getY());
                writer.println(searalize);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public int getNextId(List<Customer> customers) {
        int nextId = 0;
        for (Customer c : customers) {
            nextId = Math.max(nextId, c.getId());
        }
        return nextId + 1;
    }

    public Customer updateCustomer(int id, String newName, int age, int x, int y) {
        try {
            List<Customer> customers = findAllCustomers();
            for (int index = 0; index < customers.size(); index++) {
                if (customers.get(index).getId() == id) {
                    hotel[customers.get(index).getX()][customers.get(index).getY()] = new Room(false, null);
                    customers.get(index).setName(newName);
                    customers.get(index).setAge(age);
                    customers.get(index).setX(x);
                    customers.get(index).setY(y);
                    writeAll(customers);
                    hotel[customers.get(index).getX()][customers.get(index).getY()] = new Room(true, customers.get(index));
                    System.out.println("You successfully update a customer");
                    return customers.get(index);
                }
            }
            customers.add(customers.get(id - 1));

        } catch (Exception e) {
            System.out.println("An Error Occurred");
        }
        return new Customer();
    }
}
