package com.moonsworth.lunar.client.event.mixin;

import com.moonsworth.lunar.client.event.LunarEvent;
import java.util.Arrays;
import javax.annotation.Nullable;
import lombok.Generated;

public class EventCommand extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final String field1;
   private final String field2;
   @Nullable
   private String[] field3;

   public EventCommand(String text1) {
      this.field1 = text1;
      int index2 = text1.indexOf(32);
      this.field2 = index2 > 2 ? text1.substring(0, index2) : text1;
   }

   public boolean method1(String text1) {
      return text1.equals(this.field2);
   }

   public String get(int index1) {
      if (this.field3 == null) {
         String[] items2 = this.field1.split(" ");
         this.field3 = Arrays.copyOfRange(items2, 1, items2.length);
      }

      return index1 < this.field3.length ? this.field3[index1] : "";
   }

   @Generated
   public void method2(@Nullable String[] items1) {
      this.field3 = items1;
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

   public static class CommandInput extends LunarEvent {
      private final String field1;
      private final String field2;
      private boolean valid;

      public CommandInput(String text1) {
         this.field1 = text1;
         int index2 = text1.indexOf(32);
         this.field2 = index2 > 2 ? text1.substring(0, index2) : text1;
      }

      public boolean method1(String text1) {
         return text1.equals(this.field2);
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
