package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.ContainerClickType;
import javax.annotation.Nullable;
import lombok.Generated;
import com.moonsworth.lunar.client.highlight.HighlightImpl;

public class SlotRenderEvent extends HighlightImpl {
   private final Bridge5Extension_3 field1;
   @Nullable
   private final Bridge3_18 field2;
   private int field3;
   private int field4;
   private ContainerClickType field5;
   public boolean field6;

   public SlotRenderEvent(Bridge5Extension_3 var1, Bridge3_18 bridge3_18, int value, int value2, ContainerClickType containerClickType) {
      this.field1 = var1;
      this.field2 = bridge3_18;
      this.field3 = value;
      this.field4 = value2;
      this.field5 = containerClickType;
   }

   public void method1(int var1) {
      this.field3 = var1;
      this.field6 = true;
   }

   public void method2(int var1) {
      this.field4 = var1;
      this.field6 = true;
   }

   public void method3(ContainerClickType var1) {
      this.field5 = var1;
      this.field6 = true;
   }

   @Generated
   public Bridge5Extension_3 method4() {
      return this.field1;
   }

   @Nullable
   @Generated
   public Bridge3_18 method5() {
      return this.field2;
   }

   @Generated
   public int getSlotId() {
      return this.field3;
   }

   @Generated
   public int method6() {
      return this.field4;
   }

   @Generated
   public ContainerClickType method7() {
      return this.field5;
   }

   @Generated
   public boolean isModified() {
      return this.field6;
   }
}
