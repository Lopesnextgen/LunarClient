package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.GuiPlayerTabOverlayBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import javax.annotation.Nullable;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(GuiPlayerTabOverlay.class)
public class GuiPlayerTabOverlayMixin implements GuiPlayerTabOverlayBridge {
   @Shadow
   @Nullable
   public IChatComponent header;
   @Shadow
   @Nullable
   public IChatComponent footer;

   public GuiPlayerTabOverlayMixin() {
   }

   public Bridge2_42 bridge$getHeader() {
      return (Bridge2_42)this.header;
   }

   public Bridge2_42 bridge$getFooter() {
      return (Bridge2_42)this.footer;
   }
}
