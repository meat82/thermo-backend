package com.runoi.thermobackend.execeptions;

public class InvalidTemperatureExeception extends RuntimeException{
    public InvalidTemperatureExeception(String msg){
        super(msg);
    }
}
