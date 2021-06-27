package com.zrz;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        //启动类加载器 Oracle JDK,OpenJDK.
//        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
//        System.out.println("启动类加载器");
//        for(URL url:urls){
//            System.out.println("===>"+url.toExternalForm());
//        }
        //扩展类加载器
        printClassLoader("扩展类加载器",JvmClassLoaderPrintPath.class.getClassLoader().getParent());

        //应用类加载器
        printClassLoader("应用类加载器",JvmClassLoaderPrintPath.class.getClassLoader());
    }

    private static void printClassLoader(String name, ClassLoader classLoader) {
        System.out.println();
        if(null!=classLoader){
            System.out.println(name+"ClassLoader -> " +classLoader.toString());
            printURLForClassLoader(classLoader);
        }else{
            System.out.println(name+"classLoader-> null");
        }
    }

    private static void printURLForClassLoader(ClassLoader classLoader) {
        Object ucp=insightField(classLoader,"ucp");
        Object path=insightField(ucp,"path");
        List paths=(List)path;
        for(Object p:paths){
            System.out.println("===>"+p.toString());
        }
    }

    private static Object insightField(Object obj, String fname) {
        Field f=null;
        try{
            //JDK11调试方式查看各个ClassLoader加载的jar包。 ，JDK8可以使用本代码方式，待验证效果。
            if(obj instanceof URLClassLoader){
                f = URLClassLoader.class.getDeclaredField(fname);
            }else{
                f=obj.getClass().getDeclaredField(fname);
            }
            f.setAccessible(true);
            return f.get(obj);
        }catch(NoSuchFieldException ex) {

        }catch(IllegalAccessException ex){

        }
        return null;
    }


}
