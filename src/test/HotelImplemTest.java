import customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import room.Room;

public class HotelImplemTest {
    private static final int X = 8;

    private static final int Y = 8;
    Customer customer;
    Room[][] hotel;
    String SEED_PATH = "data\\Customers.txt";
    String TEST_PATH = "data\\TestCustomer.txt";
    HotelImpl hotelImplementation = new HotelImpl(TEST_PATH);

    public HotelImplemTest() throws IOException {
    }

    @BeforeEach
    void setup() throws IOException {
//        String SEED_PATH = "data\\Customers.txt";
//        String TEST_PATH = "data\\TestCustomer.txt";
        ArrayList<Customer> customers = new ArrayList<>();
        customer = new Customer(1, "aniq", 25, 0, 1);
        hotel = new Room[X][Y];
        Files.copy(Paths.get(SEED_PATH), Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }
//        hotelImplementation = new HotelImpl(TEST_PATH);}

    @Test
    void addHotelCustomer() {
        ArrayList<Customer> ALL = new ArrayList<>();
        Customer test = new Customer(3, "test", 30, 5, 5);
        ALL.add(test);
        assertTrue(hotelImplementation.addHotelCustomer(test));
//       assertEquals(ALL,c);
//       assertNotNull(c);
    }

    @Test
    void shouldNotAddIfAlreadyBooked() {
        ArrayList<Customer> ALL = new ArrayList<>();
        Customer test = new Customer(1, "test", 30, 5, 5);
        ALL.add(test);
        hotelImplementation.addHotelCustomer(test);
        Customer test1 = new Customer(2, "test1", 30, 5, 5);
        //ArrayList<Customer> customer1 = hotelImplementation.addHotelCustomer(test1);
        assertFalse(hotelImplementation.validateRoom(test1));
//        assertNotNull(c);
    }


    @Test
    void shouldFindAll() {
        assertEquals(1, hotelImplementation.findAllCustomers().size());
    }

    @Test
    void shouldRemoveCustomer() throws IOException {

        Customer test = new Customer(1, "test", 30, 5, 5);
        assertTrue(hotelImplementation.removeCustomer(1));
    }

    @Test
    void shouldUpdateCustomer() throws IOException {
        Customer test = new Customer(1, "test", 30, 5, 5);
        Customer updatedCustomer = new Customer(1,"new test",23,3,3);
        assertEquals(updatedCustomer.toString(),hotelImplementation.updateCustomer(1,"new test",23,3,3).toString());
    }

    @Override
    public String toString() {
        return "HotelImplemTest{" +
                "hotelImplementation=" + hotelImplementation +
                ", customer=" + customer +
                ", hotel=" + Arrays.toString(hotel) +
                '}';
    }
}
