package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import lombok.Generated;

public class ItemValueResponse {
   private boolean success;
   private int value;
   private ItemValueResponse.Type field1;

   public static ItemValueResponse method1() {
      return method3().method1(false).method2(-1).method3(ItemValueResponse.Type.OBTAINER_FAILED).method4();
   }

   public static ItemValueResponse method2(int value) {
      return method3().method1(true).method2(value).method4();
   }

   @Generated
   ItemValueResponse(boolean flag1, int value2, ItemValueResponse.Type type) {
      this.success = flag1;
      this.value = value2;
      this.field1 = type;
   }

   @Generated
   public static ItemValueResponse.Data method3() {
      return new ItemValueResponse.Data();
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
   public ItemValueResponse.Type method4() {
      return this.field1;
   }

   @Generated
   public void setValue(int number1) {
      this.value = number1;
   }

   @Generated
   public static class Data {
      @Generated
      private boolean success;
      @Generated
      private int value;
      @Generated
      private ItemValueResponse.Type field1;

      @Generated
      Data() {
      }

      @Generated
      public ItemValueResponse.Data method1(boolean flag1) {
         this.success = flag1;
         return this;
      }

      @Generated
      public ItemValueResponse.Data method2(int number1) {
         this.value = number1;
         return this;
      }

      @Generated
      public ItemValueResponse.Data method3(ItemValueResponse.Type type) {
         this.field1 = type;
         return this;
      }

      @Generated
      public ItemValueResponse method4() {
         return new ItemValueResponse(this.success, this.value, this.field1);
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

      Type() {
      }

      public String getDebugString() {
         return switch (this) {
            case NO_OBTAINER -> "(can't recognize item)";
            case OBTAINER_FAILED -> "(item parsing failed)";
         };
      }
   }
}
