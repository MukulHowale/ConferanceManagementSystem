import java.util.ArrayList;
import java.util.List;

public class Floor {

    private String name;
    private List<Room> listOfRooms;

    public Floor(String name) {
        this.name = name;
        listOfRooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getListOfRooms() {
        return listOfRooms;
    }

    public void addRoom(Room room) {
        listOfRooms.add(room);
    }

    public Room checkIfRoomExists(String room) {
        for(Room room1 : listOfRooms){
            if(room1.getName().equals(room)){
                return room1;
            }
        }
        return null;
    }

    public String getAllRooms(String building1){
        for (Room room : listOfRooms){
            building1 = room.getName() + " " + building1;
            String time = room.getAllTimeSlots();
            building1 = building1 + " " + time;
        }
        return building1;
    }
}
