package com.moonsworth.lunar.client.util.click;

import net.kyori.adventure.text.Component;

public class Click4 {
   private Object field1;
   public int field2 = -1;
   public boolean field3 = false;
   public Component field4;

   public <T> T method1() {
      return (T)this.field1;
   }

   public <T> T method2(T t) {
      this.field1 = t;
      return (T)t;
   }
}
