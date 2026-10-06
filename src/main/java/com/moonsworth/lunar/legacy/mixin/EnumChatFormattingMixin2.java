package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump86;
import javax.annotation.Nullable;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EnumChatFormatting.class)
public class EnumChatFormattingMixin2 {
   @Nullable
   @Overwrite
   public static String getTextWithoutFormattingCodes(String text) {
      return ThreadModuleDump86.getTextWithoutFormattingCodes(text);
   }
}
