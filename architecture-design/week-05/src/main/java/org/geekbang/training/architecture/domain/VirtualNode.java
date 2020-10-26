package org.geekbang.training.architecture.domain;

/**虚拟节点**/
public class VirtualNode implements Node {
    private String name;
    private Node node;
    private int location;

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
        return 0;
    }

    public void put(String key, Object value) {
         if(this.getNode()!=null){
             this.getNode().put(key,value);
         }
    }

    @Override
    public String toString() {
        return "VirtualNode{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}'+
                "\n";
    }
}
