package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;

public class ItemWailaComponent implements WailaComponent {
   private final ItemStackBridge field1;

   public ItemWailaComponent(ItemStackBridge bridgeextension_41) {
      this.field1 = bridgeextension_41;
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
   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int number3, int number4) {
      mixinhelper_41.method44(arg0 -> {
         arg0.method29().method6(arg0x -> {
            arg0x.IHORHICICIHRCOCRROCHHOROCHCHCR();
            Bridge.method14().method2();
         });
         if (arg0.method29().method38()) {
            arg0.method29().method30().method48();
         }
      });
      mixinhelper_41.method35(this.field1, number3, number4 + 1, true);
      mixinhelper_41.method44(arg0 -> arg0.method29().method6(arg0x -> {
         Bridge.method14().method3();
         arg0x.ICOHHORICHCROOOCOHIRIHOHORRCHH();
         arg0x.ICRCRICCCORRHICIHHIHORROOHIROO();
      }));
   }

   public ItemStackBridge method2() {
      return this.field1;
   }
}
