package org.geekbang.training.architecture.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 真实的物理节点
 * */
public class RealNode implements Node {
    private String name;
    private Node node;
    private int location;
    private Map<String,Object> dataContainer = new HashMap<String, Object>();

    public void setName(String name) {
        this.name=name;
    }
    public String getName() {
        return this.name;
    }

    public void setNode(Node node) {
        this.node=node;
    }
    public Node getNode() {
        return this.node;
    }

    public void setLocation(int location) {
        this.location=location;
    }
    public int getLocation() {
        return this.location;
    }

    @Override
    public int count() {
        return dataContainer.size();
    }

    public void put(String key, Object value) {
        if(this.dataContainer!=null){
            this.dataContainer.put(key,value);
        }
    }


    @Override
    public String toString() {
        return "RealNode{" +
                "dataCount=" + dataContainer.size() +
                '}'+
                "\n\r";
    }
}