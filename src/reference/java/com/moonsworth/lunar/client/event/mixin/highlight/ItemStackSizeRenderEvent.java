package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import lombok.Generated;

public class ItemStackSizeRenderEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private String text;
   private final ItemStackBridge field1;

   public boolean method1() {
      return this.field1 == null ? false : this.field1.bridge$getStackSize() != 1 || this.text != null;
   }

   @Generated
   public String getText() {
      return this.text;
   }

   @Generated
   public ItemStackBridge getItem() {
      return this.field1;
   }

   @Generated
   public ItemStackSizeRenderEvent(String var1, ItemStackBridge var2) {
      this.text = var1;
      this.field1 = var2;
   }

   @Generated
   public void setText(String var1) {
      this.text = var1;
   }

   public static class Data extends ItemStackSizeRenderEvent {
      private final MixinHelper_4 field2;
      private final int field3;
      private final int field4;

      public Data(MixinHelper_4 var1, String var2, ItemStackBridge itemStackBridge, int value, int value2) {
         super(var2, itemStackBridge);
         this.field2 = var1;
         this.field3 = value;
         this.field4 = value2;
      }

      @Generated
      public MixinHelper_4 method2() {
         return this.field2;
      }

      @Generated
      public int getX() {
         return this.field3;
      }

      @Generated
      public int getY() {
         return this.field4;
      }
   }
}
