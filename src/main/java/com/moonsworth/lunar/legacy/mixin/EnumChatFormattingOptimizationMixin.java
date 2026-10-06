package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.text.FormattingCodes;
import javax.annotation.Nullable;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EnumChatFormatting.class)
public class EnumChatFormattingOptimizationMixin {
   public EnumChatFormattingOptimizationMixin() {
   }

   @Nullable
   @Overwrite
   public static String getTextWithoutFormattingCodes(String text) {
      return FormattingCodes.getTextWithoutFormattingCodes(text);
   }
}
