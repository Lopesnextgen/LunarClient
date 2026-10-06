package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;

public class WailaHandler6 implements Waila {
   private final ItemStackBridge field1;

   public WailaHandler6(ItemStackBridge var1) {
      this.field1 = var1;
   }

   @Override
   public int getWidth() {
      return 16;
   }

   @Override
   public int getHeight() {
      return 16;
   }

   @Override
   public void method1(MixinHelper_4 var1, com.moonsworth.lunar.client.mod.hud.waila.Waila var2, int var3, int var4) {
      var1.method44(var0 -> {
         var0.method29().method6(var0x -> {
            var0x.IHORHICICIHRCOCRROCHHOROCHCHCR();
            Bridge.method14().method2();
         });
         if (var0.method29().method38()) {
            var0.method29().method30().method48();
         }
      });
      var1.method35(this.field1, var3, var4 + 1, true);
      var1.method44(var0 -> var0.method29().method6(var0x -> {
         Bridge.method14().method3();
         var0x.ICOHHORICHCROOOCOHIRIHOHORRCHH();
         var0x.ICRCRICCCORRHICIHHIHORROOHIROO();
      }));
   }

   public ItemStackBridge method2() {
      return this.field1;
   }
}
