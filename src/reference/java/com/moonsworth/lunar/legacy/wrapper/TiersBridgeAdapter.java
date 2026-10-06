package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge4_5;
import com.moonsworth.lunar.bridge.Bridge_50;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Generated;
import net.minecraft.item.Item.ToolMaterial;

public class TiersBridgeAdapter implements Bridge_50 {
   private final AtomicReference<Object> field1 = new AtomicReference<>();

   public Bridge4_5 method1() {
      return (Bridge4_5)ToolMaterial.WOOD;
   }

   public Bridge4_5 method2() {
      return (Bridge4_5)ToolMaterial.STONE;
   }

   public Bridge4_5 method3() {
      return (Bridge4_5)ToolMaterial.IRON;
   }

   public Bridge4_5 method4() {
      return (Bridge4_5)ToolMaterial.goldColor;
   }

   public Bridge4_5 method5() {
      return ThreadModuleDump63.MC_VERSION <= 1 ? (Bridge4_5)ToolMaterial.EMERALD : (Bridge4_5)ToolMaterial.DIAMOND$v1_12;
   }

   public Bridge4_5 method6() {
      throw new AbstractMethodErrorImpl();
   }

   public List<Bridge4_5> method7() {
      return this.method8();
   }

   @Generated
   public List<Bridge4_5> method8() {
      Object var1 = this.field1.get();
      if (var1 == null) {
         synchronized (this.field1) {
            var1 = this.field1.get();
            if (var1 == null) {
               List var3 = List.of(this.method1(), this.method2(), this.method3(), this.method4(), this.method5());
               var1 = var3 == null ? this.field1 : var3;
               this.field1.set(var1);
            }
         }
      }

      return (List<Bridge4_5>)(var1 == this.field1 ? null : var1);
   }
}
