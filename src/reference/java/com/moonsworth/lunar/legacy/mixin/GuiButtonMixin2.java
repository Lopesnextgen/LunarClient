package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiButton.class)
public class GuiButtonMixin2 {
   @Annotation2(max = 1)
   @WrapMethod(method = "drawButton$v1_7")
   private void lunar$tintButton$v1_7(Minecraft var1, int var2, int var3, Operation<Void> var4) {
      Gui2.method17(() -> var4.call(new Object[]{var1, var2, var3}));
   }

   @Annotation2(min = 5)
   @WrapMethod(method = "drawButton$v1_12")
   private void lunar$tintButton$v1_12(Minecraft var1, int var2, int var3, float var4, Operation<Void> operation) {
      Gui2.method17(() -> operation.call(new Object[]{var1, var2, var3, var4}));
   }
}
