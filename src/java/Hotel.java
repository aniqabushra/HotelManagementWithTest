
import customer.Customer;
import room.Room;

import java.util.ArrayList;

public interface Hotel {
    void showHotel();
   boolean addHotelCustomer(Customer customer);
    Room getRoom(int x, int y);
    int X = 8;
    int Y = 8;
}
