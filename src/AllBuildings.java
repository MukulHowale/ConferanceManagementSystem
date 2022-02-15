import java.util.ArrayList;
import java.util.List;

public class AllBuildings {

    private List<Building> listOfBuilding;

    AllBuildings(){
        this.listOfBuilding = new ArrayList<>();
    }

    public List<Building> getListOfBuilding() {
        return listOfBuilding;
    }

    public void addBuilding(Building building) {
        listOfBuilding.add(building);
    }

    public Building checkIfBuildingExists(String building){
        for (Building building1 : listOfBuilding){
            if(building1.getName().equals(building)){
                return building1;
            }
        }

        return null;
    }
}
