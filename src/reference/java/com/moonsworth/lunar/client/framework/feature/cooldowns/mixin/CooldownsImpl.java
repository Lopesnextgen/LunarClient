package com.moonsworth.lunar.client.framework.feature.cooldowns.mixin;

import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldowns;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.jetbrains.annotations.NotNull;

public class CooldownsImpl extends Cooldowns {
   private static final float field9 = 1.4F;
   private static final int field10 = 16;
   @NotNull
   private final ItemStackBridge field11;

   public CooldownsImpl(String var1, long var2, @NotNull ItemStackBridge var4) {
      this(var1, var2, var4, null);
   }

   public CooldownsImpl(String var1, long var2, @NotNull ItemStackBridge var4, CooldownStyle var5) {
      super(var1, var2, var5);
      this.field11 = var4;
   }

   @Override
   public void method3(MixinHelper_4 var1, float var2, float var3) {
      Bridge5_19 var4 = ThreadModuleDump63.method3().bridge$getRenderItem();
      float var5 = var4.bridge$getZLevel();
      var4.bridge$setZLevel(-200.0F);
      var1.push();
      float var6 = 40.0F;
      var1.method38(var2 + (var6 - 22.4F) / 2.0F, var3 + (var6 - 22.4F) / 2.0F, 0.0F);
      var1.scale(1.4F, 1.4F, 1.4F);
      var1.method44(var0 -> var0.method29().method6(var0x -> {
         var0x.IHORHICICIHRCOCRROCHHOROCHCHCR();
         Bridge.method14().method2();
      }));
      var1.method34(this.field11, 0, 0, ThreadModuleDump63.method3());
      var1.method44(var0 -> var0.method29().method6(var0x -> {
         Bridge.method14().method3();
         var0x.ICOHHORICHCROOOCOHIRIHOHORRCHH();
         var0x.ICRCRICCCORRHICIHHIHORROOHIROO();
      }));
      var1.pop();
      var4.bridge$setZLevel(var5);
      var1.method44(var0 -> var0.method29().method5(var0x -> var0x.method48()));
   }
}
