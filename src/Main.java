import spark.Spark;

public class Main {

    public static void main(String[] args) {
        Spark.externalStaticFileLocation();
        Spark.init();

        Spark.get(
                //main page - routes to all other pages based on condition
                "/",
                ((request, response) -> {

                })
        );
        Spark.post(
                //currentLocation page - will contain googleMaps to select or find current location
                "/currentLocation",
                ((request, response) -> {


                })
        );
        Spark.post(
                //addParking page - will allow the user to input the long/lat/address for the new parking spot found
                "/addParking",
                ((request, response) -> {
                    request.queryParams("addParkingLocation");
                    request.queryParams("parkingInfo");
                })
        );
    }
}
