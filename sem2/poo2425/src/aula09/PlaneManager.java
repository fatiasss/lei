package aula09;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;

public class PlaneManager {
    private HashSet<Plane> planes = new HashSet<>();


    public void addPlane(Plane plane){
        planes.add(plane);
    }
    public void removePlane(String id){
        Iterator<Plane> it = planes.iterator();
        while(it.hasNext()){
            Plane plane = it.next();
            if (plane.getId().equals(id)){it.remove(); break;}
        }
    }
    public Plane searchPlane(String id){
        Iterator<Plane> it = planes.iterator();
        while(it.hasNext()){
            Plane plane = it.next();
            if (plane.getId().equals(id)){return plane;}
        }
        return null;
    }
    public ArrayList<CommercialPlane> getCommercialPlanes(){
        Iterator<Plane> it = planes.iterator();
        ArrayList<CommercialPlane> retList = new ArrayList<>();
        while(it.hasNext()){
            Plane plane = it.next();
            if (plane.getPlaneType().equals("Commercial")){retList.add((CommercialPlane)plane);}
        }
        return retList;

    }
    public ArrayList<MilitaryPlane> getMilitaryPlanes(){
        Iterator<Plane> it = planes.iterator();
        ArrayList<MilitaryPlane> retList = new ArrayList<>();
        while(it.hasNext()){
            Plane plane = it.next();
            if (plane.getPlaneType().equals("Military")){retList.add((MilitaryPlane)plane);}
        }
        return retList;

    }

    public void printAllPlanes(){
        Iterator<Plane> it = planes.iterator();
        while(it.hasNext()){
            Plane plane = it.next();
            System.out.println(plane.toString());
        }
    }

    public Plane getFastestPlane(){
        Iterator<Plane> it = planes.iterator();
        Plane fastestPlane = it.next();
        while(it.hasNext()){
            Plane plane = it.next();
            if (plane.getVmax()>fastestPlane.getVmax()){fastestPlane=plane;}
        }
        return fastestPlane;
    }

}
