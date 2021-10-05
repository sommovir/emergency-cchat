package cyber.polygon.ecchat.logic;

import java.util.LinkedList;
import java.util.List;

public class EventManager {

    private boolean serverOnline = false;

    private static EventManager _instance = null;

    private List<ConnectionEventListener> connectionEventListenerList = new LinkedList<>();

    private EventManager(){

    }

    public boolean isServerOnline() {
        return serverOnline;
    }

    public static EventManager getInstance(){
        if(_instance == null){
            _instance = new EventManager();
        }
        return _instance;
    }



    public void addConnectionEventListener(ConnectionEventListener listener){
        this.connectionEventListenerList.add(listener);
    }

    public void serverOnline(){
        this.serverOnline = true;
        for (ConnectionEventListener listener : connectionEventListenerList) {
            listener.serverOnline();
        }
    }

    public void serverOffline(){
        this.serverOnline = false;
        for (ConnectionEventListener listener : connectionEventListenerList) {
            listener.serverOffline();
        }
    }


}
