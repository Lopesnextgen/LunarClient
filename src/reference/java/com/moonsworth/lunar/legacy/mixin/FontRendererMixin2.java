package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FontRenderer.class)
public class FontRendererMixin2 {
   @Annotation2(0)
   @ModifyVariable(method = "drawString$v1_7(Ljava/lang/String;IIIZ)I", at = @At("HEAD"), ordinal = 2, argsOnly = true)
   private int lunar$overlayGuiTextColorHook$v1_7(int var1) {
      return Gui2.method15(var1);
   }

   @Annotation2(min = 1)
   @ModifyVariable(method = "drawString$v1_8(Ljava/lang/String;FFIZ)I", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private int lunar$overlayGuiTextColorHook$v1_8(int var1) {
      return Gui2.method15(var1);
   }
}
