package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import lombok.Generated;

public class Gui {
   private boolean success;
   private int value;
   private Gui.Type field1;

   public static Gui method1() {
      return method3().method1(false).method2(-1).method3(Gui.Type.OBTAINER_FAILED).method4();
   }

   public static Gui method2(int value) {
      return method3().method1(true).method2(value).method4();
   }

   @Generated
   Gui(boolean var1, int value2, Gui.Type type) {
      this.success = var1;
      this.value = value2;
      this.field1 = type;
   }

   @Generated
   public static Gui.Data method3() {
      return new Gui.Data();
   }

   @Generated
   public boolean isSuccess() {
      return this.success;
   }

   @Generated
   public int getValue() {
      return this.value;
   }

   @Generated
   public Gui.Type method4() {
      return this.field1;
   }

   @Generated
   public void setValue(int var1) {
      this.value = var1;
   }

   @Generated
   public static class Data {
      @Generated
      private boolean success;
      @Generated
      private int value;
      @Generated
      private Gui.Type field1;

      @Generated
      Data() {
      }

      @Generated
      public Gui.Data method1(boolean var1) {
         this.success = var1;
         return this;
      }

      @Generated
      public Gui.Data method2(int var1) {
         this.value = var1;
         return this;
      }

      @Generated
      public Gui.Data method3(Gui.Type var1) {
         this.field1 = var1;
         return this;
      }

      @Generated
      public Gui method4() {
         return new Gui(this.success, this.value, this.field1);
      }

      @Generated
      @Override
      public String toString() {
         return "ItemValueResponse.ItemValueResponseBuilder(success=" + this.success + ", value=" + this.value + ", failureReason=" + this.field1 + ")";
      }
   }

   public enum Type {
      NO_OBTAINER,
      OBTAINER_FAILED;

      public String getDebugString() {
         return switch (this) {
            case NO_OBTAINER -> "(can't recognize item)";
            case OBTAINER_FAILED -> "(item parsing failed)";
         };
      }
   }
}
