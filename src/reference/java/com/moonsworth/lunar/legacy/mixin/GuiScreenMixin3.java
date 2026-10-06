package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.render.menublur.MenuBlur;
import java.util.Set;
import net.minecraft.client.gui.GuiScreen;
import net.optifine.gui.GuiAnimationSettingsOF;
import net.optifine.gui.GuiDetailSettingsOF;
import net.optifine.gui.GuiOtherSettingsOF;
import net.optifine.gui.GuiPerformanceSettingsOF;
import net.optifine.gui.GuiQualitySettingsOF;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiScreen.class, priority = 1001)
public class GuiScreenMixin3 {
   @Unique
   private static boolean lunar$lazyInitOF = true;

   @Inject(method = "<clinit>", at = @At("TAIL"))
   private static void lunar$blurMenusOptiFine(CallbackInfo callbackInfo) {
      if (lunar$lazyInitOF && Client.method109() != null) {
         MenuBlur var1 = Client.method109().method40().method43();
         Set var2 = var1.getPauseScreenData().method5();
         var2.add(GuiAnimationSettingsOF.class);
         var2.add(GuiDetailSettingsOF.class);
         var2.add(GuiQualitySettingsOF.class);
         var2.add(GuiPerformanceSettingsOF.class);
         var2.add(GuiOtherSettingsOF.class);
         lunar$lazyInitOF = false;
      }
   }
}
