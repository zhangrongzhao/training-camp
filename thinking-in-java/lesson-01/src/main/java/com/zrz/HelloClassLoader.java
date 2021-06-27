package com.zrz;

import java.io.*;
import java.util.Base64;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args){
        try{
            new HelloClassLoader().findClass("com.zrz.Hello").newInstance();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }catch(InstantiationException e){
            e.printStackTrace();
        }
    }

    protected Class<?> findClass(String name)throws ClassNotFoundException{
 //       try {
//            FileInputStream fileInputStream=new FileInputStream("F:\\workspace\\training-camp\\thinking-in-java\\lesson-01\\src\\main\\java\\com\\zrz\\Hello.class");
//            byte[] bytes1 = fileInputStream.readAllBytes();
//            String helloBase64 = Base64.getEncoder().encodeToString(bytes1);
            String helloBase64="yv66vgAAADcAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEAGEhlbGxvIENsYXNzIEluaXRpYWxpemVkIQcAGQwAGgAbAQANY29tL3pyei9IZWxsbwEAEGphdmEvbGFuZy9PYmplY3QBABBqYXZhL2xhbmcvU3lzdGVtAQADb3V0AQAVTGphdmEvaW8vUHJpbnRTdHJlYW07AQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYAIQAFAAYAAAAAAAIAAQAHAAgAAQAJAAAAHQABAAEAAAAFKrcAAbEAAAABAAoAAAAGAAEAAAADAAgACwAIAAEACQAAACUAAgAAAAAACbIAAhIDtgAEsQAAAAEACgAAAAoAAgAAAAUACAAGAAEADAAAAAIADQ==";
            byte[] bytes=decode(helloBase64);
            return defineClass(name,bytes,0,bytes.length);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        return null;
    }
    public byte[] decode(String base64){
        return Base64.getDecoder().decode(base64);
    }
}
