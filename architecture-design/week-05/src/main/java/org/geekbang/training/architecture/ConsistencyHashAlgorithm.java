package org.geekbang.training.architecture;

import org.geekbang.training.architecture.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 一致性hash算法
 * */
public class ConsistencyHashAlgorithm {
    /**物理节点总数*/
    private int realCount=0;
    /**每物理节点虚拟出来的虚拟节点总数**/
    private int virtualCountPerNode=0;
    private List<Node> realNodes = null;
    private List<Node> virtualNodes=null;
    private static final Ring ring = new HashRing();

    public ConsistencyHashAlgorithm(int realCount,int virtualCountPerReal){
        this.init(realCount,virtualCountPerReal);
    }

    private void init(int realCount,int virtualCountPerReal){
        this.realCount=realCount;
        this.virtualCountPerNode=virtualCountPerReal;
        this.virtualNodes = new ArrayList<Node>();
        this.realNodes = new ArrayList<Node>(realCount);
        //1.初始化物理节点
        this.initRealNodes(this.realCount);
        //2.初始化虚拟节点
        this.initVirtualNodes(virtualCountPerReal);
        //3.虚拟节点分配到环上。
        this.allocateVirtualNode(virtualNodes);
    }
    private void initRealNodes(int realCount){
        for(int i=0;i<realCount;i++){
            Node node = new RealNode();
            node.setLocation(i);
            node.setName(node.getClass().getSimpleName()+String.valueOf(i));
            node.setNode(null);
             this.realNodes.add(node);
        }
    }

    private void initVirtualNodes(int virtualCountPerReal){
        for(Node node:realNodes){
            virtualizeNode(node,virtualCountPerReal);
        }
    }

    private void virtualizeNode(Node node,int virtualCountPerReal){
        for(int i=0;i<virtualCountPerReal;i++){
            Node virtualNode = new VirtualNode();
            virtualNode.setLocation(i);
            virtualNode.setName(virtualNode.getClass().getSimpleName()+String.valueOf(i));
            virtualNode.setNode(node);
            this.virtualNodes.add(virtualNode);
        }
    }

    /**随机分配虚拟节点到hash环上**/
    private void allocateVirtualNode(List<Node> virtualNodes){
        Random random = new Random();
        for(Node node:this.virtualNodes){
            //随机分配虚拟节点
            while(true){
                int index = Math.abs(random.nextInt(Integer.MAX_VALUE));
                if(!ring.hasAllocated(index)){
                    node.setLocation(index);
                    ring.allocate(index);
                    break;
                }
            }
        }
        //对所有虚拟节点排序
        for(int i=0;i<this.virtualNodes.size();i++){
            for(int j=0;j<this.virtualNodes.size()-i;j++){
                if(virtualNodes.size()<=(j+1)){ continue;}
                Node node1=this.virtualNodes.get(j);
                Node node2=this.virtualNodes.get(j+1);
                if(node1.getLocation()>node2.getLocation()){
                    Node temp=node1;
                    this.virtualNodes.set(j,node2);
                    this.virtualNodes.set(j+1,temp);
                }
            }
        }
    }

    /**key顺时针查找里自己最近的虚拟节点，并存储*/
    public void put(String key,Object value){
         if(key==null||key.trim().length()<=0){
             return;
         }
         //int hashCode = key.hashCode();
         Random random = new Random();
         int keyLocation=random.nextInt(Integer.MAX_VALUE);
         //System.out.println("keyLocation="+keyLocation);
         int theNearestNodeLocation = this.findNearestNode(keyLocation);
         //System.out.println("theNearestNodeLocation="+theNearestNodeLocation);
         Node theNearestNode=null;
         for(Node node:this.virtualNodes){
             if(node.getLocation()==theNearestNodeLocation){
                 theNearestNode=node;
                 break;
             }
         }
         if(theNearestNode!=null){
             theNearestNode.put(key,value);
         }
    }

    /**查找距离当前位置最近的节点**/
    private int findNearestNode(int currentLocation){
        /**判断当前位置是否在最后一个虚拟节点之后，如果是，则返回第一个节点位置。（顺时针查找）*/
        Node lastNodeAtRing=this.virtualNodes.get(this.virtualNodes.size()-1);
        if((currentLocation-lastNodeAtRing.getLocation())==0){
            return lastNodeAtRing.getLocation();
        }else if((currentLocation-lastNodeAtRing.getLocation())>0){
            return this.virtualNodes.get(0).getLocation();
        }
        for(Node node:this.virtualNodes){
            int result = currentLocation-node.getLocation();
            if(result>0){
                continue;
            }
            return node.getLocation();
        }
        return this.virtualNodes.get(0).getLocation();
    }


    public List<Node> getRealNodes(){
        return this.realNodes;
    }

    public List<Node> getVirtualNodes(){
        return this.virtualNodes;
    }

    public static void main(String[] args){
        test(2,50,1000000);
        test(2,100,1000000);
        test(2,150,1000000);
        test(2,200,1000000);
        test(2,250,1000000);
        test(2,300,1000000);
        System.out.println();


        test(6,50,1000000);
        test(6,100,1000000);
        test(6,150,1000000);
        test(6,200,1000000);
        test(6,250,1000000);
        test(6,300,1000000);
        System.out.println();

        test(10,50,1000000);
        test(10,100,1000000);
        test(10,150,1000000);
        test(10,200,1000000);
        test(10,250,1000000);
        test(10,300,1000000);
        System.out.println();

        test(20,50,1000000);
        test(20,100,1000000);
        test(20,150,1000000);
        test(20,200,1000000);
        test(20,250,1000000);
        test(20,300,1000000);
        System.out.println();

        test(30,50,1000000);
        test(30,100,1000000);
        test(30,150,1000000);
        test(30,200,1000000);
        test(30,250,1000000);
        test(30,300,1000000);
        System.out.println();

        test(50,50,1000000);
        test(50,100,1000000);
        test(50,150,1000000);
        test(50,200,1000000);
        test(50,250,1000000);
        test(50,300,1000000);
        System.out.println();

        test(100,50,1000000);
        test(100,100,1000000);
        test(100,150,1000000);
        test(100,200,1000000);
        test(100,250,1000000);
        test(100,300,1000000);

    }

    public static void test(int realCount,int virtualCountPerReal,int allKeys){
        ConsistencyHashAlgorithm consistencyHashAlgorithm = new ConsistencyHashAlgorithm(realCount,virtualCountPerReal);
        //System.out.println(consistencyHashAlgorithm.getVirtualNodes());

        for(int i=0;i<allKeys;i++){
            consistencyHashAlgorithm.put("key"+i,"value"+i);
        }
        //Stream.of(consistencyHashAlgorithm.getRealNodes()).map((node)->node.d);
        //System.out.println(consistencyHashAlgorithm.getRealNodes());

        //System.out.println(realCount+"台物理节点*"+virtualCountPerReal+"虚拟节点:方差="+ConsistencyHashAlgorithm.Variance(consistencyHashAlgorithm.getRealNodes()));
        System.out.println(realCount+"台物理节点*"+virtualCountPerReal+"虚拟节点：标准差="+ConsistencyHashAlgorithm.StandardDiviation(consistencyHashAlgorithm.getRealNodes()));

    }


    //方差s^2=[(x1-x)^2 +...(xn-x)^2]/n 或者s^2=[(x1-x)^2 +...(xn-x)^2]/(n-1)
    public static long Variance(List<Node> realNodes) {
        long m = realNodes.size();
        long sum = 0;
        for (int i = 0; i < m; i++) {//求和
            sum += realNodes.get(i).count();
        }
        long dAve = sum / m;//求平均值
        long dVar = 0;
        for (int i = 0; i < m; i++) {//求方差
            dVar += (realNodes.get(i).count() - dAve) * (realNodes.get(i).count() - dAve);
        }
        return dVar / m;
    }

    //标准差σ=sqrt(s^2)
    public static double  StandardDiviation(List<Node> realNodes) {
        double  m = realNodes.size();
        double  sum = 0;
        for (int i = 0; i < m; i++) {//求和
            sum += realNodes.get(i).count();
        }
        double  dAve = sum / m;//求平均值
        long dVar = 0;
        for (int i = 0; i < m; i++) {//求方差
            dVar += (realNodes.get(i).count() - dAve) * (realNodes.get(i).count() - dAve);
        }
        return Math.sqrt(dVar/m);
    }
}
