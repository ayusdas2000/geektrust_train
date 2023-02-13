package com.geektrust.backend.exceptions;

public class TrainEndedException extends RuntimeException {
    public TrainEndedException() {
        super();
    }

    public TrainEndedException(String msg) {
        super(msg);
    }    
}
