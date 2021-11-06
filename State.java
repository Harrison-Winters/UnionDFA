public class State {
    
    public String stateID;
    public boolean isStart = false;
    public boolean isAccept = false;


    public State(String id, boolean start, boolean accept){ //constructor
        stateID = id;
        isStart = start;
        isAccept = accept;
    }
}
