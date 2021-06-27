package com.zrz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;

public class HelloXlassLoader extends ClassLoader{
    public static void main(String[] args){
        try{
            Class<?> helloClass = new HelloXlassLoader().findClass("Hello");
            Method helloMethod = helloClass.getMethod("hello");
            helloMethod.invoke(helloClass.newInstance());
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(NoSuchMethodException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected Class<?> findClass(String name)throws ClassNotFoundException{
       try {
            FileInputStream fileInputStream=new FileInputStream("F:\\workspace\\training-camp\\thinking-in-java\\lesson-01\\src\\main\\java\\com\\zrz\\Hello.xlass");
            byte[] helloXlassBytes = fileInputStream.readAllBytes();
            byte[] helloClassBytes=new byte[helloXlassBytes.length];
            for(int i=0;i<helloXlassBytes.length;i++){
                helloClassBytes[i] = (byte)(255-helloXlassBytes[i]);
            }
            return defineClass(name,helloClassBytes,0,helloClassBytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
