package org.geekbang.training.architecture.domain;
import java.util.BitSet;

/***
 * Hash环
 * **/
public class HashRing implements Ring {
    private final BitSet ring = new BitSet(Integer.MAX_VALUE);

    public int getSize() {
        return ring.size();
    }

    public boolean hasAllocated(int index) {
        return ring.get(index);
    }

    public int allocate(int index) {
         ring.set(index,true);
         return index;
    }
}
