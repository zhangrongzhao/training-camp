package org.geekbang.homework.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器控件基类
 * **/
public abstract class AbstractContainerControl implements Control {
    private String name;
    private List<Control>  controls;

    protected AbstractContainerControl(String name){
        this.name=name;
        this.controls=new ArrayList<Control>();
    }
    public void addControl(Control control){
        this.controls.add(control);
    }

    public String getName(){
        return this.name;
    }
    @Override
    public void draw() {
        System.out.println("print "+this.getClass().getSimpleName()+"("+this.getName()+")");

        for(Control control:this.controls){
           control.draw();
       }
    }
}
