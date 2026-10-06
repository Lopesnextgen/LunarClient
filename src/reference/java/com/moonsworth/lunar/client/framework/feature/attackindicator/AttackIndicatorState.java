package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;

public class AttackIndicatorState {
   private final boolean field1;
   private final boolean active;
   private final float value;
   private final boolean field2;
   private final boolean field3;
   private final ItemStackBridge field4;
   final AttackIndicatorProvider field5;

   AttackIndicatorState(AttackIndicatorProvider attackindicator21, Bridge5Extension_5 bridge5extension_52) {
      this.field1 = attackindicator21.isVanilla();
      this.active = attackindicator21.method1(bridge5extension_52);
      this.value = attackindicator21.method2(bridge5extension_52);
      this.field2 = attackindicator21.method3(bridge5extension_52);
      this.field3 = attackindicator21.method4(bridge5extension_52);
      this.field4 = attackindicator21.method6(bridge5extension_52);
      this.field5 = attackindicator21;
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
   public AttackIndicatorProvider method3() {
      return this.field5;
   }
}
