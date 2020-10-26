package org.geekbang.training.architecture.domain;

/**
 * 环的顶层接口
 * */
public interface Ring {
    /**
     * 环大小
     * */
    int getSize();

    /**环指定位置是否已经分配**/
    boolean hasAllocated(int index);

    /**分配指定位置，同时返回分配位置**/
    int allocate(int index);
}
