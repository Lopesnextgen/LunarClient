package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiYesNoBridge;
import java.util.function.BiConsumer;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiYesNo.class)
public abstract class GuiYesNoMixin implements GuiYesNoBridge {
   @Shadow
   public GuiYesNoCallback parentScreen;
   @Shadow
   public int parentButtonClickedId;

   public GuiYesNoMixin() {
   }

   public BiConsumer<Boolean, Integer> bridge$getYesNoCallback() {
      return (arg1, arg2) -> this.parentScreen.confirmClicked(arg1, arg2);
   }

   public int bridge$getParentButtonClickedId() {
      return this.parentButtonClickedId;
   }
}
