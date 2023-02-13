package com.geektrust.backend.entities;

import java.util.LinkedList;

public class Train extends BaseEntitiy {
    private LinkedList<Boggy> boggies;
    private String trainName;

    public Train(String id, String trainName, LinkedList<Boggy> boggies) {
        this(trainName, boggies);
        this.id = id;
    }

    public Train(String trainName, LinkedList<Boggy> boggies) {
        this.trainName = trainName;
        this.boggies = boggies;
    }

    public LinkedList<Boggy> getBoggies() {
        return boggies;
    }

    public String getTrainName() {
        return trainName;
    }

    public void deleteBoggy(Boggy boggy) {
        boggies.remove(boggy); 
    }

    public void addBoggy(Boggy boggy) {
        boggies.add(boggy);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((boggies == null) ? 0 : boggies.hashCode());
        result = prime * result + ((trainName == null) ? 0 : trainName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Train other = (Train) obj;
        if (boggies == null && other.boggies != null) {
            return false;
        } 
        if (!boggies.equals(other.boggies))
            return false;
        
        if (trainName == null && other.trainName != null) {
            return false;
        } 
        if(!trainName.equals(other.trainName))
            return false;
            
        return true;
    }    
}
