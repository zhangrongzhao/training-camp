package org.geekbang.training.architecture.domain;

/**
 * 节点顶层接口
 * **/
public interface Node {
    /**节点名称**/
    void setName(String name);
    String getName();

    /**链接节点*/
    void setNode(Node node);
    Node getNode();

    /**节点所在位置*/
    void setLocation(int location);
    int getLocation();

    /***获取当前节点拥有的数据总量****/
    int count();

    /**存放key-value**/
    void put(String key,Object value);
}
