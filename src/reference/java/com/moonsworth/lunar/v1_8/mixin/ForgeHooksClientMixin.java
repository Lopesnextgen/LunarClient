package com.moonsworth.lunar.v1_8.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.overlay.HudColorOverride;
import com.moonsworth.lunar.client.framework.feature.mod.GuiModuleManager;
import com.moonsworth.lunar.client.framework.feature.mod.GuiEventDispatcher;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPre;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.MixinCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ForgeHooksClient.class)
public class ForgeHooksClientMixin {
   @Unique
   private static Integer lunar$inventoryGuiScale;
   @Unique
   private static float lunar$inventoryScaleFactor = 1.0F;

   public ForgeHooksClientMixin() {
   }

   @Redirect(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V"))
   private static void lunar$updateCameraAndRender$drawScreen$forge(GuiScreen screen0, int number1, int value, float value2) {
      try {
         int number4;
         int number15;
         if (lunar$inventoryGuiScale$pre()) {
            number4 = (int)(number1 / lunar$inventoryScaleFactor);
            number15 = (int)(value / lunar$inventoryScaleFactor);
         } else {
            number4 = number1;
            number15 = value;
         }

         boolean flag16 = DriverViewportLegacy.method50() == null || !DriverViewportLegacy.method50().method40();
         int number17 = flag16 ? number4 : 0;
         int number18 = flag16 ? number15 : 0;
         BridgeExtension3_5 bridgeextension3_519 = AbstractRenderContext.method32();

         try {
            LunarEventBus.method29()
               .method16(
                  EventRenderContainerSlotPre.class,
                  EventRenderContainerSlotPost.class,
                  arg5x -> arg5x.wrapEvents(
                     () -> new EventRenderContainerSlotPre(new MarkerModel.Data4(number17, number18), value2, (GuiScreenBridge)screen0, bridgeextension3_519, new LegacyGuiGraphicsBridge(bridgeextension3_519)),
                     () -> new EventRenderContainerSlotPost(new MarkerModel.Data4(number17, number18), value2, (GuiScreenBridge)screen0, bridgeextension3_519, new LegacyGuiGraphicsBridge(bridgeextension3_519))
                  ),
                  () -> screen0.drawScreen(number17, number18, value2)
               );
         } finally {
            HudColorOverride.method10();
         }

         lunar$inventoryGuiScale$post();
      } catch (Throwable exception14) {
         Minecraft minecraft5 = Minecraft.getMinecraft();
         CrashReport crashreport6 = CrashReport.makeCrashReport(exception14, "Rendering screen");
         CrashReportCategory crashreportcategory7 = crashreport6.makeCategory("Screen render details");
         String text8 = minecraft5.currentScreen.getClass().getCanonicalName();
         crashreportcategory7.addCrashSectionCallable("Screen name", () -> text8);
         String text9 = String.format("Scaled: (%d, %d). Absolute: (%d, %d)", number1, value, Mouse.getX(), Mouse.getY());
         crashreportcategory7.addCrashSectionCallable("Mouse location", () -> text9);
         String text10 = String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", 0, 0, minecraft5.displayWidth, minecraft5.displayHeight, 0);
         crashreportcategory7.addCrashSectionCallable("Screen size", () -> text10);
         throw new ReportedException(crashreport6);
      }
   }

   @VersionGate(1)
   @Inject(method = "drawScreen", at = @At("HEAD"), cancellable = true)
   @MixinCondition(present = "optifine")
   private static void lunar$overwriteGuiRender$forgeOptifine$1_8(
      CallbackInfo callback0,
      @Local(argsOnly = true) GuiScreen screen1,
      @Local(argsOnly = true, ordinal = 0) LocalIntRef localintref2,
      @Local(argsOnly = true, ordinal = 1) LocalIntRef localintref3,
      @Local(argsOnly = true) float value4
   ) {
      GuiEventDispatcher module25 = GuiModuleManager.field7.method11();
      if (module25.method1()) {
         callback0.cancel();
      } else if (module25.method2()) {
         localintref2.set(-1);
         localintref3.set(-1);
      }

      module25.render(Ref.method3().bridge$getCurrentScreen(), new LegacyGuiGraphicsBridge(AbstractRenderContext.method32()), localintref2.get(), localintref3.get(), value4);
   }

   @Unique
   private static boolean lunar$inventoryGuiScale$pre() {
      GuiScreenBridge bridge5extension60 = (GuiScreenBridge)Minecraft.getMinecraft().currentScreen;
      int number1 = bridge5extension60 == null ? 0 : bridge5extension60.bridge$getInventoryScale();
      if (number1 <= 0) {
         lunar$inventoryScaleFactor = 1.0F;
         return false;
      } else {
         lunar$inventoryGuiScale = Minecraft.getMinecraft().gameSettings.guiScale;
         Minecraft.getMinecraft().gameSettings.guiScale = number1;
         lunar$inventoryScaleFactor = (float)number1 / LcuiScreen.method151().method3();
         bridge5extension60.bridge$setInventoryScaleFactor(lunar$inventoryScaleFactor);
         Bridge.method42().method4();
         Bridge.method42().bridge$scale(lunar$inventoryScaleFactor, lunar$inventoryScaleFactor, 1.0F);
         return true;
      }
   }

   @Unique
   private static void lunar$inventoryGuiScale$post() {
      if (lunar$inventoryGuiScale != null) {
         Minecraft.getMinecraft().gameSettings.guiScale = lunar$inventoryGuiScale;
         Bridge.method42().method5();
         lunar$inventoryGuiScale = null;
         lunar$inventoryScaleFactor = 1.0F;
         GuiScreenBridge bridge5extension60 = (GuiScreenBridge)Minecraft.getMinecraft().currentScreen;
         if (bridge5extension60 != null) {
            bridge5extension60.bridge$setInventoryScaleFactor(1.0F);
         }
      }
   }
}
