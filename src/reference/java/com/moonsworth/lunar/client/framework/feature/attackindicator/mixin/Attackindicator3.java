package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;

public class Attackindicator3 {
   private final boolean field1;
   private final boolean active;
   private final float value;
   private final boolean field2;
   private final boolean field3;
   private final ItemStackBridge field4;
   final Attackindicator2 field5;

   Attackindicator3(Attackindicator2 attackindicator2, Bridge5Extension_5 bridge5Extension_5) {
      this.field1 = attackindicator2.isVanilla();
      this.active = attackindicator2.method1(bridge5Extension_5);
      this.value = attackindicator2.method2(bridge5Extension_5);
      this.field2 = attackindicator2.method3(bridge5Extension_5);
      this.field3 = attackindicator2.method4(bridge5Extension_5);
      this.field4 = attackindicator2.method6(bridge5Extension_5);
      this.field5 = attackindicator2;
   }

   @Generated
   public boolean isVanilla() {
      return this.field1;
   }

   @Generated
   public boolean isActive() {
      return this.active;
   }

   @Generated
   public float getValue() {
      return this.value;
   }

   @Generated
   public boolean method1() {
      return this.field2;
   }

   @Generated
   public boolean method2() {
      return this.field3;
   }

   @Generated
   public ItemStackBridge getIcon() {
      return this.field4;
   }

   @Generated
   public Attackindicator2 method3() {
      return this.field5;
   }
}
