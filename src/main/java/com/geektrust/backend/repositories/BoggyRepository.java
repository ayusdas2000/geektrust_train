package com.geektrust.backend.repositories;

import java.util.HashMap;
import com.geektrust.backend.entities.Boggy;

public class BoggyRepository implements IBoggyRepository {
    private HashMap<String, Boggy> boggyMapping;
    private Integer autoIncrement = 0;

    public BoggyRepository() {
        this.boggyMapping = new HashMap<String, Boggy>();
    }

    public BoggyRepository(HashMap<String, Boggy> boggyMapping){
        this.boggyMapping = boggyMapping;
    }

    @Override
    public Boggy saveBoggy(Boggy boggy) {
        if(boggy.getId() == null){
            autoIncrement++;
            Boggy newBoggy = new Boggy(Integer.toString(autoIncrement), boggy.getDestStation());
            boggyMapping.put(newBoggy.getId(), newBoggy);
            return newBoggy;
        }
        boggyMapping.put(boggy.getId(), boggy);
        return boggy;
    }

    @Override
    public HashMap<String, Boggy> getBoggyMapping() {
        return boggyMapping;
    }
    
}
