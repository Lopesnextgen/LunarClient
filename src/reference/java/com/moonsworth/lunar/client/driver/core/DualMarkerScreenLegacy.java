package com.moonsworth.lunar.client.driver.core;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.DepthComparison;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Bridge_65;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuHomeScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuBackground;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuThemeManager;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverContextLegacy;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.mod.misc.panoramamaker.PanoramaMaker;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.render.menublur.MenuBlur;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class DualMarkerScreenLegacy implements Bridge7_8 {
   private static final MainMenuBackground field1 = new MainMenuBackground();
   private final DriverRouteRegistryLegacy field2;
   private final DriverContextLegacy field3;
   private final DriverViewLegacy field4;
   private final DriverViewportLegacy field5;
   private int width;
   private int height;

   public DualMarkerScreenLegacy(DriverRouteRegistryLegacy var1, DriverContextLegacy var2) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = DriverViewLegacy.method21();
      this.field5 = DriverViewportLegacy.method50();
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public void method1(MixinHelper_4 var1, Bridge_65 var2, float var3) {
      AbstractRenderContext var4 = var1.method48();
      DriverViewportLegacy var5 = DriverViewportLegacy.method50();
      if (ThreadModuleDump63.method8() == null && this.method11()) {
         field1.init();
         if (this.method3()) {
            Bridge.method14().method6(var1, false);
            this.method17(var1);
         } else if (ThreadModuleDump63.MC_VERSION >= 22) {
            Bridge.method14().method6(var1, true);
         } else {
            ResourceLocationBridge var11 = this.method2();
            if (var11 != null) {
               if (PanoramaMaker.field12) {
                  field1.method5();
               }

               LcuiScreen.method39(var1, this.method2(), 0.0F, 0.0F, this.width, this.height);
               MainMenuBackground.method3(var4, this.width, this.height, var1.method43());
            }
         }
      } else if (ThreadModuleDump63.method8() != null) {
         MenuBlur var6 = Client.method109().method40().method43();
         RewindHandlers var7 = ThreadModuleDump63.method4().method40().method85().method35();
         boolean var8 = var5.method63() == DriverRouteRegistryLegacy.field10;
         if (var8 && var7 != null) {
            Nameplate4 var9 = (Nameplate4)var7.method42().get();
            Nameplate2 var10 = var9.method8();
            var8 = !var7.method46().method14() || !var6.shouldBlurScreen(var10.method5());
         }

         if (var6.isEnabled() && (Boolean)var6.getLunarScreenData().method3().get() && !var8) {
            var6.ensureBlurStarted();
            if (var7 == null) {
               ColorOption var12 = var6.getLunarScreenData().method4();
               var12.IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(Math.min(var12.getAlpha() / 255.0F, 0.75F));
               var12.method11(var1, 0.0F, 0.0F, this.width, this.height);
            }
         }

         if (var8) {
            var6.stopBlurAnimation();
         }
      }

      if (var4.method38()) {
         var4.method30().method48();
      }

      if (!PanoramaMaker.field12 && !ThreadModuleDump63.method4().method40().method85().method19()) {
         this.method4(var1);
      }

      var4.method19();
   }

   @Nullable
   private ResourceLocationBridge method2() {
      return MainMenuThemeManager.method12() != null && MainMenuThemeManager.method12().method2() != null
         ? MainMenuThemeManager.method12().method1()
         : MainMenuHomeScreen.field22.method1();
   }

   private boolean method3() {
      return MainMenuThemeManager.method12() != null && MainMenuThemeManager.method12().method5();
   }

   public void method4(MixinHelper_4 var1) {
      this.field4.method2(var1);
   }

   public void method2(Bridge5_12 var1, Bridge_65 var2) {
      this.width = var2.xi();
      this.height = var2.method5();
      this.initGui();
      Bridge.method18().method3(true);
   }

   public void method3(Bridge_65 var1, int var2) {
   }

   public void method4(Bridge_65 var1, int var2) {
   }

   public void handleMouseInput() {
   }

   public void onGuiClosed() {
      Bridge.method18().method3(false);
   }

   public void updateScreen() {
      if (ThreadModuleDump63.method8() == null && this.method11()) {
         if (this.method3()) {
            Bridge.method14().method7();
         }

         field1.method4();
      }
   }

   public void method5(char var1, KeyCode var2) {
   }

   public void initGui() {
      this.field5.method4(this.field4.getWidth(), this.field4.getHeight(), this.field4.method16(), this.field4.method17());
      if (this.field2.method1()) {
         this.field5.method17(this.field2, this.field3 == null ? new DriverContextLegacy() : this.field3);
      }

      Bridge5Extension_5 var1 = HologramsIterator2.method14();
      if (ThreadModuleDump63.method8() == null && var1 != null) {
         ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher().bridge$prepare(var1.bridge$getWorld(), var1);
      }
   }

   public void method6(int var1, Bridge_65 var2) {
   }

   public boolean method7() {
      return this.field5.method64() == null && (this.field5.method63().method18() || this.field5.method61().method18());
   }

   public boolean doesGuiPauseGame() {
      DriverOverlayRegistryLegacy var1 = this.field5.method64();
      return var1 != null && !var1.method14() ? false : this.field5.method63().method19();
   }

   public boolean method11() {
      return this.method12(DriverViewportLegacy.method50().method63())
         || DriverViewportLegacy.method50().method63() == DriverRouteRegistryLegacy.field3 && this.method12(DriverViewportLegacy.method50().method61());
   }

   private boolean method12(DriverRouteRegistryLegacy var1) {
      return var1 == DriverRouteRegistryLegacy.field4 || var1 == DriverRouteRegistryLegacy.field5 || ThreadModuleDump63.method8() == null && var1.method21();
   }

   public static boolean method13(Class<?> var0) {
      return var0 != null && DualMarkerScreenLegacy.class.isAssignableFrom(var0);
   }

   public static boolean method14() {
      Bridge7_8 var0 = null;
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 var1) {
         var0 = var1.method2();
      }

      return var0 instanceof DualMarkerScreenLegacy;
   }

   public Optional<String> method8() {
      return Optional.of(this.field5.method61().getPath());
   }

   public boolean method16(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data6 var1) {
      return true;
   }

   protected void method17(MixinHelper_4 var1) {
      if (ThreadModuleDump63.MC_VERSION <= 5) {
         AbstractRenderContext var2 = var1.method46().method29();
         var2.method3(DepthComparison.GL_GREATER, 1.0E-4F);
         var1.method5(0, 0, this.width, this.height, -2130706433, 16777215);
         var1.method5(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
         var2.method3(DepthComparison.GL_GREATER, 0.1F);
      }
   }

   @Generated
   public static MainMenuBackground method18() {
      return field1;
   }

   @Generated
   public DriverRouteRegistryLegacy method19() {
      return this.field2;
   }
}
