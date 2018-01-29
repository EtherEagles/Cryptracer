package com.example.antonio.cryptracer.Models;


/**
 * Created by Antonio on 25/01/2018.
 */

public class Model {

    private String head;
    private String desc;

    public Model(String head, String desc){
        this.head = head;
        this.desc = desc;
    }

    public String getHead(){
        return head;
    }

    public String getDesc(){
        return desc;
    }
}
