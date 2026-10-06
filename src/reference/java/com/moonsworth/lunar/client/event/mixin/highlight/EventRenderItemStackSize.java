package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import lombok.Generated;

public class EventRenderItemStackSize extends com.moonsworth.lunar.client.event.LunarEvent {
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
   public EventRenderItemStackSize(String text1, ItemStackBridge bridgeextension_42) {
      this.text = text1;
      this.field1 = bridgeextension_42;
   }

   @Generated
   public void setText(String text1) {
      this.text = text1;
   }

   public static class ItemStackSize extends EventRenderItemStackSize {
      private final MixinHelper_4 field2;
      private final int field3;
      private final int field4;

      public ItemStackSize(MixinHelper_4 mixinhelper_41, String text, ItemStackBridge bridgeextension_43, int value, int value2) {
         super(text, bridgeextension_43);
         this.field2 = mixinhelper_41;
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
