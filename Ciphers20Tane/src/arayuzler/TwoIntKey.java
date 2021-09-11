package arayuzler;

import tools.Tool;

import javax.swing.*;

public abstract class TwoIntKey extends TwoKey<Integer>{

    public Integer getKey1(){
        if(!Tool.isNumeric(key1))return null;
        return Integer.parseInt(this.key1);
    }
    public Integer getKey2(){
        if(!Tool.isNumeric(key2))return null;
        return Integer.parseInt(this.key2);
    }

    public void setKey1(int key1) {
        this.key1 = String.valueOf(key1);
    }

    public void setKey2(int key2) {
        this.key2 = String.valueOf(key2);
    }
}
