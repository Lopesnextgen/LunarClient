package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeImplementation;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.mod.render.lighting.Lighting;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiVideoSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(GuiVideoSettings.class)
public class GuiVideoSettingsMixin {
   public GuiVideoSettingsMixin() {
   }

   @WrapOperation(
      method = "actionPerformed(Lnet/minecraft/client/gui/GuiButton;I)V",
      slice = @Slice(from = @At(value = "INVOKE", target = "net/optifine/Config.isAnisotropicFiltering ()Z")),
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", ordinal = 0)
   )
   private void lunar$onOpenScreen(Minecraft minecraft1, GuiScreen screen2, Operation<Void> operation3) {
      Lighting lighting4 = Ref.method4().method40().method56();
      TranslationManager foghandler285 = Ref.method4().method67();
      BridgeImplementation bridge26 = Bridge.method8();
      MinecraftBridge bridge5_127 = Objects.requireNonNull(Ref.method3());
      GuiScreenBridge bridge5extension68 = bridge5_127.bridge$getCurrentScreen();
      boolean flag9 = Ref.method4()
         .method84()
         .method3(ServerRuleModule.class)
         .filter(arg0 -> (Boolean)arg0.getOptions().get(ServerRuleModule.DISABLE_SHADERS))
         .isPresent();
      if (flag9) {
         bridge5_127.bridge$displayScreen(
            bridge26.method35(() -> bridge5_127.bridge$displayScreen(bridge5extension68), "", foghandler285.method2("gui.apollo", "disabledShadersServerRule", new Object[0]))
         );
      } else if (lighting4.isEnabled()) {
         String text10 = "gui.lightingMod.mod_enabled_cannot_enable_shaders";
         bridge5_127.bridge$displayScreen(
            bridge26.method34(
               foghandler285.method2(text10, "header", new Object[0]),
               foghandler285.method2(text10, "warning", new Object[0]),
               foghandler285.method2(text10, "confirmButton", new Object[0]),
               foghandler285.method2(text10, "denyButton", new Object[0]),
               () -> {
                  ((ModEnabledState)lighting4.method7(ModTraits.field6)).setEnabled(false);
                  operation3.call(new Object[]{minecraft1, screen2});
               },
               () -> bridge5_127.bridge$displayScreen(bridge5extension68)
            )
         );
      } else {
         operation3.call(new Object[]{minecraft1, screen2});
      }
   }
}
