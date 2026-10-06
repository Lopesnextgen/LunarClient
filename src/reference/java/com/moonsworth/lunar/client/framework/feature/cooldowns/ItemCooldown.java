package com.moonsworth.lunar.client.framework.feature.cooldowns;

import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.NotNull;

public class ItemCooldown extends Cooldown {
   private static final float field9 = 1.4F;
   private static final int field10 = 16;
   @NotNull
   private final ItemStackBridge field11;

   public ItemCooldown(String text1, long number2, @NotNull ItemStackBridge bridgeextension_44) {
      this(text1, number2, bridgeextension_44, null);
   }

   public ItemCooldown(String text1, long number2, @NotNull ItemStackBridge bridgeextension_44, CooldownStyle cooldownstyle5) {
      super(text1, number2, cooldownstyle5);
      this.field11 = bridgeextension_44;
   }

   @Override
   public void method3(MixinHelper_4 mixinhelper_41, float value2, float value3) {
      RenderItemBridge bridge5_194 = Ref.method3().bridge$getRenderItem();
      float value5 = bridge5_194.bridge$getZLevel();
      bridge5_194.bridge$setZLevel(-200.0F);
      mixinhelper_41.push();
      float value6 = 40.0F;
      mixinhelper_41.method38(value2 + (value6 - 22.4F) / 2.0F, value3 + (value6 - 22.4F) / 2.0F, 0.0F);
      mixinhelper_41.scale(1.4F, 1.4F, 1.4F);
      mixinhelper_41.method44(arg0 -> arg0.method29().method6(arg0x -> {
         arg0x.IHORHICICIHRCOCRROCHHOROCHCHCR();
         Bridge.method14().method2();
      }));
      mixinhelper_41.method34(this.field11, 0, 0, Ref.method3());
      mixinhelper_41.method44(arg0 -> arg0.method29().method6(arg0x -> {
         Bridge.method14().method3();
         arg0x.ICOHHORICHCROOOCOHIRIHOHORRCHH();
         arg0x.ICRCRICCCORRHICIHHIHORROOHIROO();
      }));
      mixinhelper_41.pop();
      bridge5_194.bridge$setZLevel(value5);
      mixinhelper_41.method44(arg0 -> arg0.method29().method5(arg0x -> arg0x.method48()));
   }
}
