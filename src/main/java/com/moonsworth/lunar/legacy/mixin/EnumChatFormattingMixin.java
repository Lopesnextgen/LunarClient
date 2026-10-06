package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.minecraft.EnumChatFormattingBridge;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EnumChatFormatting.class)
public class EnumChatFormattingMixin implements EnumChatFormattingBridge {
   @Final
   @Shadow
   public char formattingCode;

   public EnumChatFormattingMixin() {
   }

   public char bridge$formattingCode() {
      return this.formattingCode;
   }
}
