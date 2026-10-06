package com.moonsworth.lunar.client.event.mixin.command;

import com.moonsworth.lunar.client.highlight.Highlight;
import java.util.Arrays;
import javax.annotation.Nullable;
import lombok.Generated;

public class EventCommandLegacy extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final String field1;
   private final String field2;
   @Nullable
   private String[] field3;

   public EventCommandLegacy(String var1) {
      this.field1 = var1;
      int var2 = var1.indexOf(32);
      this.field2 = var2 > 2 ? var1.substring(0, var2) : var1;
   }

   public boolean method1(String var1) {
      return var1.equals(this.field2);
   }

   public String get(int var1) {
      if (this.field3 == null) {
         String[] var2 = this.field1.split(" ");
         this.field3 = Arrays.copyOfRange(var2, 1, var2.length);
      }

      return var1 < this.field3.length ? this.field3[var1] : "";
   }

   @Generated
   public void method2(@Nullable String[] var1) {
      this.field3 = var1;
   }

   @Generated
   public String getCommand() {
      return this.field1;
   }

   @Generated
   public String method3() {
      return this.field2;
   }

   @Nullable
   @Generated
   public String[] method4() {
      return this.field3;
   }

   public static class Data extends Highlight {
      private final String field1;
      private final String field2;
      private boolean valid;

      public Data(String var1) {
         this.field1 = var1;
         int var2 = var1.indexOf(32);
         this.field2 = var2 > 2 ? var1.substring(0, var2) : var1;
      }

      public boolean method1(String var1) {
         return var1.equals(this.field2);
      }

      public void method2() {
         this.valid = true;
      }

      @Generated
      public String getCommand() {
         return this.field1;
      }

      @Generated
      public boolean isValid() {
         return this.valid;
      }
   }
}
