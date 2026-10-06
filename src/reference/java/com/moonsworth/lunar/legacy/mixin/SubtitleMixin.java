package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge4_13;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.ichor.Annotation2;
import net.kyori.adventure.text.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSubtitleOverlay.Subtitle;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 5)
@Mixin(Subtitle.class)
public abstract class SubtitleMixin implements Bridge4_13 {
   @Shadow
   public abstract String getString();

   @Shadow
   public abstract long getStartTime();

   @Shadow
   public abstract Vec3 getLocation();

   @Override
   public Component bridge$getText() {
      return Component.text(this.getString());
   }

   @Override
   public long bridge$getAliveTime() {
      return Minecraft.getSystemTime() - this.getStartTime();
   }

   @Override
   public Vec3Bridge bridge$getLocation() {
      return (Vec3Bridge)this.getLocation();
   }
}
