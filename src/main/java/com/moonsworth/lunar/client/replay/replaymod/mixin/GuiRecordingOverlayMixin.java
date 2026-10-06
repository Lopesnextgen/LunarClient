package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.replaymod.lib.de.johni0702.minecraft.gui.versions.MatrixStack;
import com.replaymod.recording.gui.GuiRecordingOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GuiRecordingOverlay.class)
public class GuiRecordingOverlayMixin {
   public GuiRecordingOverlayMixin() {
   }

   @Overwrite
   public void renderRecordingIndicator(MatrixStack matrixstack1) {
   }
}
