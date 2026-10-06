package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge_7;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import lombok.Generated;

abstract class Module_2 {
   protected int field1 = 0;

   public abstract boolean method1(Bridge5Extension6 var1);

   public abstract void method2(Bridge5Extension6 var1, MixinHelper_4 var2, int var3, int var4, float var5);

   public boolean method3(Bridge5Extension6 var1, int var2, int var3, int var4) {
      return false;
   }

   public boolean method4(Bridge5Extension6 var1, int var2, int var3, int var4) {
      return false;
   }

   public boolean method5(Bridge5Extension6 var1, double var2) {
      return false;
   }

   public boolean method6(Bridge5Extension6 var1, int var2, int var3, int var4, long var5) {
      return false;
   }

   public void method7() {
      this.field1++;
   }

   public void method8() {
   }

   public void method9() {
   }

   public void onClose() {
   }

   public boolean method10(Bridge5Extension6 var1, Bridge_7 var2) {
      return false;
   }

   protected String method11(Bridge5Extension6 var1) {
      return var1 instanceof Bridge5Extension_3 var2 ? AdventureTextBridge.getTextContent(AdventureTextBridge.asAdventure(var2.bridge$title())) : null;
   }

   @Generated
   protected Module_2() {
   }
}
