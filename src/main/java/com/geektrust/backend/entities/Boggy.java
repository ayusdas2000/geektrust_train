package com.geektrust.backend.entities;

public class Boggy extends BaseEntitiy {
    private Station destStation;

    private Boolean findIfOneDestStationIsNull(Boggy o1, Boggy o2){
        if(o1.getDestStation() == null && o2.getDestStation()!=null || (o1.getDestStation()!= null && o2.getDestStation() == null)){
            return true;
        }
        return false;
    }

    public Boggy(String id, Station destStation) {
        this(destStation);
        this.id = id;
    }

    public Boggy(Station destStation) {
        this.destStation = destStation;
    }

    public Station getDestStation() {
        return destStation;
    }

    public String getDestStationCode(){
        return destStation.getStationCode();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((destStation == null) ? 0 : destStation.hashCode());
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
        Boggy other = (Boggy) obj;
        if (id != null && other.getId()!= null) {
            return id.equals(other.getId());
        } 
        if (id == null && other.getId() == null && destStation == null && other.getDestStation() == null)
            return true;
        if(id == null && other.getId() == null && (findIfOneDestStationIsNull(this, other))){
            return false;
        }
        return destStation.equals(other.getDestStation());
    }

   


    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;

    //     Boggy other = (Boggy) obj;
        
    //     if(id != null && other.getId()!= null){
    //         return id.equals(other.getId());            
    //     }else if(id == null && other.getId() == null){
    //         if((destStation == null && other.getDestStation() != null) || (destStation != null && other.getDestStation() == null)){
    //             return false;
    //         }else if(destStation == null && other.getDestStation() == null){
    //             return true;
    //         }
    //     }
    //     return destStation.equals(other.getDestStation());
    // }  
}
