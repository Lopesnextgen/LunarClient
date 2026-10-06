package com.moonsworth.lunar.client.event.mixin;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventTabComplete extends LunarEvent {
   private final String field1;
   private final String field2;
   private String[] field3 = null;

   @Generated
   public EventTabComplete(String text, String text2) {
      this.field1 = text;
      this.field2 = text2;
   }

   @Generated
   public String method1() {
      return this.field1;
   }

   @Generated
   public String method2() {
      return this.field2;
   }

   @Generated
   public String[] method3() {
      return this.field3;
   }

   @Generated
   public void method4(String[] items1) {
      this.field3 = items1;
   }
}
