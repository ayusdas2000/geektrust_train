package com.geektrust.backend.repositories;

import java.util.HashMap;
import com.geektrust.backend.entities.Boggy;

public interface IBoggyRepository {
    Boggy saveBoggy(Boggy boggy);     
    HashMap<String,Boggy> getBoggyMapping();   
}
