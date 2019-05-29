package com.example.antonio.cryptracer.Models;


/**
 * Created by Antonio on 25/01/2018.
 */

public class Option {

    private String head;
    private String desc;

    public Option(String head, String desc){
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
