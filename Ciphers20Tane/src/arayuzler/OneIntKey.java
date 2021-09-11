package arayuzler;

import tools.Tool;

import javax.swing.*;

public abstract class OneIntKey extends OneKey<Integer> {

    public void setKey(int key) {
        this.key = String.valueOf(key);
    }

    @Override
    public Integer getKey(){
        if(!Tool.isNumeric(key)){
            JOptionPane.showMessageDialog(null, "Key Sayı olmak zorunda");
            return null;
        }
        return Integer.parseInt(this.key);
    }
}
