package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventBossBarUpdate;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(max = 1)
@Mixin(BossStatus.class)
public abstract class BossStatusMixin {
   @Shadow
   public static int statusBarTime;

   public BossStatusMixin() {
   }

   @Inject(method = "setBossStatus(Lnet/minecraft/entity/boss/IBossDisplayData;Z)V", at = @At("HEAD"), cancellable = true)
   private static void lunar$setBossStatus(IBossDisplayData ibossdisplaydata0, boolean flag1, CallbackInfo callback2) {
      if (statusBarTime == -1) {
         callback2.cancel();
      }

      LunarEventBus.method29().method12(EventBossBarUpdate.class, () -> {
         Bridge2_42 bridge2_421x = Ref.MC_VERSION >= 1 ? (Bridge2_42)ibossdisplaydata0.getDisplayName() : (Bridge2_42)ibossdisplaydata0.getFormattedCommandSenderName$v1_7();
         return new EventBossBarUpdate(TextBridge.asAdventure(bridge2_421x));
      });
   }
}
