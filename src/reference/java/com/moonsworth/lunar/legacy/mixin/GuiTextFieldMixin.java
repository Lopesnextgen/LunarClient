package com.moonsworth.lunar.legacy.mixin;

import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiTextField.class)
public interface GuiTextFieldMixin {
   @Mutable
   @Accessor("width")
   void bridge$setWidth(int number1);

   @Mutable
   @Accessor("height")
   void bridge$setHeight(int number1);
}
