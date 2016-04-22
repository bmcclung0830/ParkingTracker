import java.util.ArrayList;

/**
 * Created by Brittany on 4/22/16.
 */
public class ParkingSpot {
    String longitude;
    String latitude;
    String address;
    ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();

    public ParkingSpot(String longitude, String latitude, String address) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
