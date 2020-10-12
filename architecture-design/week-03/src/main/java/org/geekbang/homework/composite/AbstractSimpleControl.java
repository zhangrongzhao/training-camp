package org.geekbang.homework.composite;
/**
 * 简单控件基类
 * */
public abstract class AbstractSimpleControl implements Control {
    private String name;
    @Override
    public String getName(){
        return this.name;
    }
    protected AbstractSimpleControl(String name){
        this.name=name;
    }
    @Override
    public void addControl(Control control){
        throw new UnsupportedOperationException();
    }
    @Override
    public void draw(){
        System.out.println("print "+this.getClass().getSimpleName()+"("+this.getName()+")");
    }
}
