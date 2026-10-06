package com.moonsworth.lunar.client.heightlimit;

public class Heightlimit {
   public static final int field1 = 2;
   public static final int field2 = 3;
   public static final int field3 = 3;
   private static int field4 = 3;

   public static void setEnabled(boolean flag) {
      field4 = flag ? 3 : 2;
   }

   public static int method1() {
      return field4;
   }
}
