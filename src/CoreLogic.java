import java.util.ArrayList;
import java.util.List;

public class CoreLogic {

    AllBuildings allBuildings;

    public CoreLogic() {
        allBuildings = new AllBuildings();
    }

    public void addBuilding(String building){
        Building building1 = allBuildings.checkIfBuildingExists(building);
        if(building1 == null){
            allBuildings.addBuilding(new Building(building));
            System.out.println("Added building " + building + " into the system");
        }
        else{
            System.out.println("Building already exists");
        }
    }

    public void addFloor(String building, String floor){
        Building building1 = allBuildings.checkIfBuildingExists(building);
        if(building1 != null){
            Floor floor1 = building1.checkIfFloorExists(floor);
            if(floor1 == null){
                building1.addFloor(new Floor(floor));
                System.out.println("Added floor " + floor + " into building " + building);
            }
            else{
                System.out.println("Floor already exists");
            }
        }
        else{
            System.out.println("No Such building exists");
        }
    }

    public void addRoom(String building, String floor, String room){
        Building building1 = allBuildings.checkIfBuildingExists(building);
        if(building1 != null) {
            Floor floor1 = building1.checkIfFloorExists(floor);
            if (floor1 != null) {
                Room room1 = floor1.checkIfRoomExists(room);
                if (room1 == null){
                    floor1.addRoom(new Room(room));
                    System.out.println("Added conference room " + room + " in floor no. " + floor + " of building " + building);
                }
                else{
                    System.out.println("Room already exists");
                }
            }
            else{
                System.out.println("No Such floor exists");
            }
        }
        else{
            System.out.println("No Such building exists");
        }
    }

    public void showListOfRooms(){
        List<Building> buildingList = allBuildings.getListOfBuilding();
        for(Building building : buildingList){
            String building1 = building.getName();
            building1 = building.getAllFloors(building1);
            System.out.println(building1);
        }
    }

    public void bookASlot(String timeSlot, String building, String floor, String room){
        String[] timeSlotsArray = timeSlot.split(":");
        int startTime = Integer.parseInt(timeSlotsArray[0]);
        int endTime = Integer.parseInt(timeSlotsArray[1]);
        Object[] o = checkErrors(startTime,endTime,building,floor,room);
        if(o != null){
            Room room1 = (Room)o[0];
            TimeSlots timeSlots = room1.checkIfSlotAvailable(startTime,endTime);
            if(timeSlots != null){
                room1.addBookedSlot(timeSlots,startTime,endTime);
                System.out.println("Your slot is booked");
            }
            else{
                System.out.println("No required time slot");
            }
        }
    }

    public void cancelASlot(String timeSlot, String building, String floor, String room) {
        String[] timeSlotsArray = timeSlot.split(":");
        int startTime = Integer.parseInt(timeSlotsArray[0]);
        int endTime = Integer.parseInt(timeSlotsArray[1]);
        Object[] o = checkErrors(startTime,endTime,building,floor,room);
        if(o != null){
            Room room1 = (Room)o[0];
            TimeSlots timeSlots = room1.checkIfSlotBooked(startTime,endTime);
            if(timeSlots != null){
                room1.cancelBookedSlot(timeSlots);
                System.out.println("Your slot is canceled");
            }
            else{
                System.out.println("No such time slot is booked");
            }
        }
    }

    public void listOfBookings(String building, String floor){
        Building building1 = allBuildings.checkIfBuildingExists(building);
        if(building1 != null) {
            Floor floor1 = building1.checkIfFloorExists(floor);
            if (floor1 != null) {
                List<Room> roomList = floor1.getListOfRooms();
                for(Room room : roomList){
                    List<TimeSlots> timeSlots = room.getListOfBookedSlots();
                    if(!timeSlots.isEmpty()){
                        for(TimeSlots timeSlots1 : timeSlots){
                            System.out.println(timeSlots1.getStartTime() + ":" +
                                    timeSlots1.getEndTime() + " " + floor +
                                    " " + building + " " + room.getName());
                        }
                    }
                }
            }
            else {
                System.out.println("No Such floor exists");
            }
        }
        else{
            System.out.println("No Such building exists");
        }
    }

    public void suggestASlot(String timeSlot){
        String[] timeSlotsArray = timeSlot.split(":");
        int startTime = Integer.parseInt(timeSlotsArray[0]);
        int endTime = Integer.parseInt(timeSlotsArray[1]);

    }

    private Object[] checkErrors(int startTime, int endTime, String building, String floor, String room){
        Building building1 = allBuildings.checkIfBuildingExists(building);
        if((startTime < 1 || startTime > 24) && (endTime < 1 || endTime > 24)){
            System.out.println("Start time and end time should be between 1 and 24");
        }
        else if(endTime - startTime > 12){
            System.out.println("Maximum slot time should be 12hrs.");
        }
        else if(building1 != null) {
            Floor floor1 = building1.checkIfFloorExists(floor);
            if (floor1 != null) {
                Room room1 = floor1.checkIfRoomExists(room);
                if (room1 != null){
                    return new Object[]{room1};
                }
                else{
                    System.out.println("No such room exists");
                }
            }
            else{
                System.out.println("No Such floor exists");
            }
        }
        else{
            System.out.println("No Such building exists");
        }

        return null;
    }
}
