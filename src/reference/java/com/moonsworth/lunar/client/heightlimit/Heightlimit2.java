package com.moonsworth.lunar.client.heightlimit;

import com.moonsworth.lunar.client.util.Slayer;

public class Heightlimit2 {
   private static final Heightlimit2 field1 = new Heightlimit2();
   private boolean supported = false;

   public void method1(boolean flag) {
      Slayer.method3("Threaded Present Supported %b", flag);
      this.supported = flag;
   }

   public boolean isSupported() {
      return this.supported;
   }

   public static Heightlimit2 method3() {
      return field1;
   }
}
