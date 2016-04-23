import jodd.json.JsonParser;
import spark.Spark;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    User user;
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
    static File f = new File("parking.json");

    public static void main(String[] args) throws FileNotFoundException {

        Scanner load = new Scanner(f);
        load.useDelimiter("\\Z");
        String jsonContents = load.next();
        JsonParser p = new JsonParser();
        parkingSpots = p.parse(jsonContents);

        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "Content-Type");
            response.header("Content-Type", "application/json");
        });

        Spark.init();

        Spark.get(
                //main page - routes to all other pages based on condition
                "/",
                ((request, response) -> {
//                    HashMap p = new HashMap();
//                    p.put("parkingSpots", parkingSpots);
                    response.status(200);
                    return "";
                })
        );

        Spark.get(
                //will send back a JSON object containing all of the stored parking spots in the Json file
                //add json file contents to the parkingSpots ArrayList.
                "/parking-spots",
                ((request, response) -> {
                    String contents = "";
                        if (parkingSpots.isEmpty()){
                            Scanner s = new Scanner(f);
                            s.useDelimiter("\\Z");
                            contents = s.next();
                            parkingSpots = p.parse(contents);
                        }else{
                            contents = new JsonSerializer().serialize(parkingSpots);
                        }
                        return contents;

                })
        );

        Spark.options(
                //addParking page - will allow the user to input the long/lat/address for the new parking spot found
                "/*",
                (request, response) -> "This is working!!"
        );
        Spark.post(
                //addresses will allow the user to input the address, do not overwrite previous entries
                "/add-parking",
                (request, response) -> {
                    JsonParser jp = new JsonParser();
                    HashMap<String, String> addressHash = jp.parse(request.body());

                    String street = String.valueOf(addressHash.get("street"));
                    String city = String.valueOf(addressHash.get("city"));
                    String state = String.valueOf(addressHash.get("state"));
                    ParkingSpot newSpot = new ParkingSpot(street, city, state);

                    String returnMessage = "";

                    response.status(200);

                    if (newSpot == null) {
                        response.status(404);
                    }
                    else if (!parkingSpots.contains(newSpot)) {
                        parkingSpots.add(newSpot);

                        JsonSerializer serializer = new JsonSerializer();
                        String json = serializer.serialize(parkingSpots);
                        FileWriter fw = new FileWriter(f);
                        fw.write(json);
                        fw.close();

                    } else {
                        returnMessage = "Address already exists";
                    }
                    System.out.println(parkingSpots);
                    return returnMessage;

                }
        );
        /*Spark.post(
                "/createUser",
                ((request, response) -> {
                    String userName = request.queryParams("userName");
                    String password = request.queryParams("password");

                    users.put("userName", new User(userName, password));


                    Session session = request.session();
                    session.attribute("userName");

                    return "";
                })
        );
        Spark.post(
                "/login",
                ((request, response) -> {
                    String userName = request.queryParams("userName");
                    String password = request.queryParams("password");
                    User user = users.get(userName);
                    HashMap u = new HashMap();

                    if (!users.containsValue(password)){

                    }
                    else {
                        u.put("name", user);
                    }
                    return "";
                })
        );
        Spark.post(
                //currentLocation page - will contain googleMaps to select or find current location
                "/main",
                ((request, response) -> {
                    request.queryParams("searchBar");
                    return "";
                })
        );*/

    }
}
