public class Runner {
    public static void main(String[] args) {

        CoreLogic coreLogic = new CoreLogic();
        coreLogic.addBuilding("b1");
        coreLogic.addBuilding("b2");

        coreLogic.addFloor("b1","1");
        coreLogic.addFloor("b2","2");

        coreLogic.addRoom("b1","1","c1");
        coreLogic.addRoom("b2","2","c2");

        coreLogic.bookASlot("4:10","b1","1","c1");
        coreLogic.bookASlot("15:17","b1","1","c1");
        coreLogic.bookASlot("18:21","b2","2","c2");

        coreLogic.listOfBookings("b1","1");

        coreLogic.cancelASlot("4:11","b1","1","c1");
        coreLogic.cancelASlot("15:17","b1","1","c1");


        coreLogic.showListOfRooms();

    }
}
