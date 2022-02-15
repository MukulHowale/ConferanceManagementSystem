import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Building {

    private String name;
    private List<Floor> listOfFloors;

    public Building(String name) {
        this.name = name;
        this.listOfFloors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Floor> getListOfFloors() {
        return listOfFloors;
    }

    public void addFloor(Floor floor) {
        listOfFloors.add(floor);
    }

    public Floor checkIfFloorExists(String floor){
        for(Floor floor1 : listOfFloors){
            if (floor1.getName().equals(floor)){
                return floor1;
            }
        }

        return null;
    }

    public String getAllFloors(String building1){
        for(Floor floor : listOfFloors){
            building1 = floor.getName() + " " +  building1;
            building1 = floor.getAllRooms(building1);
        }
        return building1;
    }
}
