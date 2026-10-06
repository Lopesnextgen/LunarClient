package com.moonsworth.lunar.client.framework.feature.cooldowns;

import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.cooldowns.Cooldown;
import org.jetbrains.annotations.NotNull;

public class IconCooldown extends Cooldown {
   @NotNull
   private final ResourceLocationBridge field9;
   @NotNull
   private final Icon field10;

   public IconCooldown(String text1, long number2, @NotNull ResourceLocationBridge horsestats144, @NotNull Icon icon5) {
      this(text1, number2, horsestats144, icon5, null);
   }

   public IconCooldown(String text1, long number2, @NotNull ResourceLocationBridge horsestats144, @NotNull Icon icon5, CooldownStyle cooldownstyle6) {
      super(text1, number2, cooldownstyle6);
      this.field9 = horsestats144;
      this.field10 = icon5;
   }

   @Override
   public void method3(MixinHelper_4 mixinhelper_41, float value, float value2) {
      mixinhelper_41.push();
      LcuiScreen.method48(mixinhelper_41, this.field9, value, value2, 40, this.field10, -1);
      mixinhelper_41.pop();
   }
}
