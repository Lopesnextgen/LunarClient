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
public class GuiScreenMenuBlurMixin {
   @Unique
   private static boolean lunar$lazyInitOF = true;

   public GuiScreenMenuBlurMixin() {
   }

   @Inject(method = "<clinit>", at = @At("TAIL"))
   private static void lunar$blurMenusOptiFine(CallbackInfo callback0) {
      if (lunar$lazyInitOF && Client.method109() != null) {
         MenuBlur menublur1 = Client.method109().method40().method43();
         Set set2 = menublur1.getPauseScreenData().method5();
         set2.add(GuiAnimationSettingsOF.class);
         set2.add(GuiDetailSettingsOF.class);
         set2.add(GuiQualitySettingsOF.class);
         set2.add(GuiPerformanceSettingsOF.class);
         set2.add(GuiOtherSettingsOF.class);
         lunar$lazyInitOF = false;
      }
   }
}
