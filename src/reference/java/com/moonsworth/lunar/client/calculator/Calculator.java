package com.moonsworth.lunar.client.calculator;

import com.moonsworth.lunar.client.framework.Client;

public class Calculator {
   public static String field1;
   public static String field2;
   public static String field3;
   public static String field4;
   public static String field5;
   public static String field6;
   public static String field7;

   public static void method1() {
      field1 = method2("cps");
      field2 = method2("ms");
      field3 = method2("blocks");
      field4 = method2("true");
      field5 = method2("false");
      field6 = method2("ok");
      field7 = method2("takeMeThere");
   }

   private static String method2(String text) {
      return Client.method109().method67().method2("shared_info", text);
   }

   static {
      method1();
   }
}
