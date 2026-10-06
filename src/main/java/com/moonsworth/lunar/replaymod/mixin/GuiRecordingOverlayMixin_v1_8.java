package com.moonsworth.lunar.replaymod.mixin;

import com.replaymod.lib.de.johni0702.minecraft.gui.versions.MatrixStack;
import com.replaymod.recording.gui.GuiRecordingOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GuiRecordingOverlay.class)
public class GuiRecordingOverlayMixin_v1_8 {
   @Overwrite
   public void renderRecordingIndicator(MatrixStack matrixStack) {
   }
}
