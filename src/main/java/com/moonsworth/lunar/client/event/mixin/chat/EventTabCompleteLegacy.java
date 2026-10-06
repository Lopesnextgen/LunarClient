package com.moonsworth.lunar.client.event.mixin.chat;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class EventTabCompleteLegacy extends Highlight {
   private final String field1;
   private final String field2;
   private String[] field3 = null;

   @Generated
   public EventTabCompleteLegacy(String var1, String text) {
      this.field1 = var1;
      this.field2 = text;
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
   public void method4(String[] var1) {
      this.field3 = var1;
   }
}
