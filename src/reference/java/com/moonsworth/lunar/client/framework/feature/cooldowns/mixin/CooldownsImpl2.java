package com.moonsworth.lunar.client.framework.feature.cooldowns.mixin;

import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldowns;
import org.jetbrains.annotations.NotNull;

public class CooldownsImpl2 extends Cooldowns {
   @NotNull
   private final ResourceLocationBridge field9;
   @NotNull
   private final Icon field10;

   public CooldownsImpl2(String var1, long var2, @NotNull ResourceLocationBridge var4, @NotNull Icon var5) {
      this(var1, var2, var4, var5, null);
   }

   public CooldownsImpl2(String var1, long var2, @NotNull ResourceLocationBridge var4, @NotNull Icon var5, CooldownStyle cooldownStyle) {
      super(var1, var2, cooldownStyle);
      this.field9 = var4;
      this.field10 = var5;
   }

   @Override
   public void method3(MixinHelper_4 var1, float var2, float value) {
      var1.push();
      LcuiScreen.method48(var1, this.field9, var2, value, 40, this.field10, -1);
      var1.pop();
   }
}
