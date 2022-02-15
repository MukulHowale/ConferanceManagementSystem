import java.util.ArrayList;
import java.util.List;

public class Room {

    private String name;
    private final int maxBookingTime = 12;
    private List<TimeSlots> listOfTimeSlots;
    private List<TimeSlots> listOfBookedSlots;

    public Room(String name) {
        this.name = name;
        listOfTimeSlots = new ArrayList<>();
        listOfTimeSlots.add(new TimeSlots());
        listOfBookedSlots = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxBookingTime() {
        return maxBookingTime;
    }

    public List<TimeSlots> getListOfTimeSlots() {
        return listOfTimeSlots;
    }

    public void setListOfTimeSlots(TimeSlots timeSlot) {
        listOfTimeSlots.add(timeSlot);
    }

    public List<TimeSlots> getListOfBookedSlots() {
        return listOfBookedSlots;
    }

    public void setListOfBookedSlots(TimeSlots timeSlot) {
        listOfBookedSlots.add(timeSlot);
    }

    public String getAllTimeSlots(){
        String time = "";
        for(int l=0; l<listOfTimeSlots.size(); l++){
            TimeSlots timeSlots = listOfTimeSlots.get(l);
            if(listOfTimeSlots.size() > 1 && l != listOfTimeSlots.size()-1) {
                time = time + "{" + timeSlots.getStartTime() + ":" + timeSlots.getEndTime() + "},";
            }
            else{
                time = time + "{" + timeSlots.getStartTime() + ":" + timeSlots.getEndTime() + "}";
            }
        }
        return "[" + time + "]";
    }

    public TimeSlots checkIfSlotAvailable(int startTime, int endTime) {
        for(TimeSlots timeSlots : listOfTimeSlots) {
            if (startTime >= timeSlots.getStartTime() && endTime <= timeSlots.getEndTime()) {
                return timeSlots;
            }
        }
        return null;
    }

    public void addBookedSlot(TimeSlots timeSlot, int startTime, int endTime){
        listOfTimeSlots.remove(timeSlot);
        if(timeSlot.getStartTime() == startTime){
            listOfTimeSlots.add(new TimeSlots(endTime+1,timeSlot.getEndTime()));
        }
        else if(timeSlot.getEndTime() == endTime){
            listOfTimeSlots.add(new TimeSlots(timeSlot.getStartTime(),startTime-1));
        }
        else{
            listOfTimeSlots.add(new TimeSlots(timeSlot.getStartTime(),startTime-1));
            listOfTimeSlots.add(new TimeSlots(endTime+1,timeSlot.getEndTime()));
        }
        listOfBookedSlots.add(new TimeSlots(startTime,endTime));
    }

    public TimeSlots checkIfSlotBooked(int startTime, int endTime) {
        for(TimeSlots timeSlots : listOfBookedSlots){
            if (startTime >= timeSlots.getStartTime() && endTime <= timeSlots.getEndTime()) {
                return timeSlots;
            }
        }
        return null;
    }

    public void cancelBookedSlot(TimeSlots timeSlots) {
        listOfBookedSlots.remove(timeSlots);
        for(int i=0; i<listOfTimeSlots.size(); i++){
            if((i != listOfTimeSlots.size()-1) && (timeSlots.getStartTime()-1 == listOfTimeSlots.get(i).getEndTime())
                    && (timeSlots.getEndTime()+1 == listOfTimeSlots.get(i+1).getStartTime())){
                TimeSlots slots = new TimeSlots(listOfTimeSlots.get(i).getStartTime(),listOfTimeSlots.get(i+1).getEndTime());
                listOfTimeSlots.remove(i);
                listOfTimeSlots.remove(i);
                listOfTimeSlots.add(i,slots);
                break;
            }
            else if(timeSlots.getEndTime()+1 == listOfTimeSlots.get(i).getStartTime()){
                TimeSlots slots = new TimeSlots(timeSlots.getStartTime(),listOfTimeSlots.get(i).getEndTime());
                listOfTimeSlots.remove(i);
                listOfTimeSlots.add(i,slots);
                break;
            }
            else if(timeSlots.getStartTime()-1 == listOfTimeSlots.get(i).getEndTime()){
                TimeSlots slots = new TimeSlots(listOfTimeSlots.get(i).getStartTime(),timeSlots.getEndTime());
                listOfTimeSlots.remove(i);
                listOfTimeSlots.add(i,slots);
                break;
            }

        }
    }
}
