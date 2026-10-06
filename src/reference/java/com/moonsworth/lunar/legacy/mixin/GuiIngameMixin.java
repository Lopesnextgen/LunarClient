package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiIngameBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Objects;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import net.kyori.adventure.util.Ticks;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(max = 0)
@Mixin(GuiIngame.class)
public abstract class GuiIngameMixin implements GuiIngameBridge {
   @Unique
   public int bridge$titlesTimer;
   @Unique
   public int bridge$titleDisplayTime;
   @Unique
   public int bridge$titleFadeIn;
   @Unique
   public int bridge$titleFadeOut;
   @Unique
   public String bridge$displayedTitle;
   @Unique
   public String bridge$displayedSubTitle;
   @Unique
   private float bridge$scale;
   @Unique
   private float bridge$interpolationScale;
   @Unique
   private float bridge$interpolationRate;

   public GuiIngameMixin() {
   }

   public void bridge$displayTitle(@Nullable Component component1, @Nullable Component component2, Times times3, float value, float value2, float value3) {
      if (component1 != null || component2 != null) {
         if (component1 != null) {
            this.bridge$displayedTitle = TextBridge.asLegacyString(component1);
         }

         if (component2 != null) {
            this.bridge$displayedSubTitle = TextBridge.asLegacyString(component2);
         }

         this.bridge$titleFadeIn = (int)(times3.fadeIn().toMillis() / 50L);
         this.bridge$titleDisplayTime = (int)(times3.stay().toMillis() / 50L);
         this.bridge$titleFadeOut = (int)(times3.fadeOut().toMillis() / 50L);
         this.bridge$titlesTimer = this.bridge$titleFadeIn + this.bridge$titleDisplayTime + this.bridge$titleFadeOut;
         this.bridge$scale = value;
         this.bridge$interpolationScale = value2;
         this.bridge$interpolationRate = value3;
      }
   }

   public void bridge$clearTitle() {
      this.bridge$titleFadeIn = 10;
      this.bridge$titleDisplayTime = 70;
      this.bridge$titleFadeOut = 20;
      this.bridge$titlesTimer = 0;
      this.bridge$displayedTitle = null;
      this.bridge$displayedSubTitle = null;
      this.bridge$scale = 1.0F;
      this.bridge$interpolationRate = 0.0F;
      this.bridge$interpolationScale = 0.0F;
   }

   public float bridge$getTitleScale() {
      return this.bridge$scale;
   }

   public Title bridge$getTitle() {
      if (this.bridge$displayedTitle == null && this.bridge$displayedSubTitle == null) {
         return null;
      }

      String text1 = Objects.requireNonNullElse(this.bridge$displayedTitle, "");
      String text2 = Objects.requireNonNullElse(this.bridge$displayedSubTitle, "");
      return Title.title(
         Component.text(text1),
         Component.text(text2),
         Times.times(Ticks.duration(this.bridge$titleFadeIn), Ticks.duration(this.bridge$titleDisplayTime), Ticks.duration(this.bridge$titleFadeOut))
      );
   }

   public int bridge$titlesTimer() {
      return this.bridge$titlesTimer;
   }

   public void bridge$interpolateTitle() {
      if (this.bridge$interpolationScale != 0.0F && this.bridge$interpolationRate != 0.0F) {
         if (!(this.bridge$scale > 1.0F) && !(this.bridge$scale < 0.0F)) {
            if (this.bridge$interpolationScale > this.bridge$scale) {
               this.bridge$scale = Math.min(this.bridge$interpolationScale, this.bridge$scale + this.bridge$interpolationRate);
            } else {
               this.bridge$scale = Math.max(this.bridge$interpolationScale, this.bridge$scale - this.bridge$interpolationRate);
            }
         }
      }
   }

   @Inject(method = "updateTick", at = @At("HEAD"))
   private void lunar$decrementTitlesTimer$v1_7(CallbackInfo callback1) {
      this.bridge$interpolateTitle();
      if (this.bridge$titlesTimer > 0) {
         this.bridge$titlesTimer--;
         if (this.bridge$titlesTimer <= 0) {
            this.bridge$displayedTitle = "";
            this.bridge$displayedSubTitle = "";
            this.bridge$scale = 1.0F;
            this.bridge$interpolationRate = 0.0F;
            this.bridge$interpolationScale = 0.0F;
         }
      }
   }
}
