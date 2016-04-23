import java.util.ArrayList;

/**
 * Created by Brittany on 4/22/16.
 */
public class ParkingSpot {
    String address;
    String street;
    String city;
    String state;
    String longitude;
    String latitude;

    public ParkingSpot(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s \n", street, city, state);
    }
}
