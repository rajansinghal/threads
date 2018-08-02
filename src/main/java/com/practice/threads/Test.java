package com.practice.threads;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rsinghal on 6/22/2017.
 */
public class Test {
    public static  void main(String arr[]) {
       /* String a = "hello";
        String b = new String(a);
        String c = a;
        char[] d = {'h','e','l','l','o'};

        System.out.println( a == "hello");*/
//
   /*     widenAndBox((byte)10);
                widenAndBox(10);
        widenAndBox((long)10);
                widenAndBox(10L);
    }
    private  static  void  widenAndBox(Long lValue){
        System.out.println("");
    }*/

   Map<Integer, String> m = new HashMap<Integer, String>();
   m.put(1, "1");
   m.put(null, "2");
   for(int i : m.keySet())
      System.out.println(m.get(i));

        widenAndBox(10);
        widenAndBox("str");
    }
    private  static  void  widenAndBox(Number lValue){
        System.out.println("--");
    }
    private  static  void  widenAndBox(Object lValue){
        System.out.println("==");
    }
    private  static  void  widenAndBox(String lValue){
        System.out.println("===");
    }
    private  static  void  widenAndBox(Integer lValue){
        System.out.println("====");
    }
 /*   public long sum(long a , long b){return a+ b;}
    public long sum(long a , int b ){return  a + b;}*/
}
