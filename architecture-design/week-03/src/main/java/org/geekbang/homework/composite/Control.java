package org.geekbang.homework.composite;

/**
 * 控件顶层接口
 * **/
public interface Control {
    String getName();
    void addControl(Control control);
    void draw();
}
