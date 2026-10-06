package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Bridge_65;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.config.GeneralSettings.Type3;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.VanillaHomeContextLegacy;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class MainMenuButton extends DualMarkerScreenLegacy {
   private final boolean field6;
   private boolean field7 = false;
   private final Bridge5Extension6 field8;

   public MainMenuButton(Bridge7_8 var1) {
      super(DriverRouteRegistryLegacy.field5, new VanillaHomeContextLegacy(var1 instanceof Bridge5Extension610));
      this.field6 = true;
      this.field8 = Bridge.method8().method18(var1);
   }

   public MainMenuButton(Bridge5Extension6 var1) {
      super(DriverRouteRegistryLegacy.field5, new VanillaHomeContextLegacy(var1 instanceof Bridge5Extension610));
      this.field6 = true;
      this.field8 = var1;
   }

   @Override
   public void method2(Bridge5_12 var1, Bridge_65 var2) {
      this.method10(() -> this.field8.bridge$setWorldAndResolution(var2.xi(), var2.method5()));
      super.method2(var1, var2);
   }

   @Override
   public void initGui() {
      if (this.field8 instanceof Bridge5Extension62 var1) {
         this.method10(() -> var1.method2().initGui());
      }

      super.initGui();
   }

   @Override
   public void updateScreen() {
      this.method10(this.field8::bridge$updateScreen);
      super.updateScreen();
   }

   public boolean method4() {
      return this.field8 instanceof Bridge5Extension610;
   }

   @Override
   public void method1(MixinHelper_4 var1, Bridge_65 var2, float var3) {
      AbstractRenderContext var4 = var1.method48();
      boolean var5 = this.method4();
      boolean var6 = DriverViewportLegacy.method50().method64() == null && !var5;
      super.method1(var1, var2, var3);
      if (Client.method109().method41().method6().method65().get() == Type3.VANILLA) {
         this.method17(var1);
      }

      if (var6) {
         DriverViewLegacy.method21().method2(var1);
      }

      if (ThreadModuleDump63.method3().bridge$areResourcesLoaded()) {
         if (this.field8 instanceof Bridge5Extension62 var7) {
            this.method10(() -> var7.method2().method1(var1, var2, var3));
         } else if (!var4.method38() || ThreadModuleDump63.MC_VERSION < 30) {
            this.method10(() -> this.field8.bridge$drawScreen(var4, var2.xi(), var2.method5(), var3));
         }
      }

      var4.method33();
      if (!var6) {
         DriverViewLegacy.method21().method2(var1);
      }

      var4.method19();
   }

   @Override
   public void method4(MixinHelper_4 var1) {
   }

   @Override
   public void method3(Bridge_65 var1, int var2) {
      if (DriverViewportLegacy.method50().method64() == null) {
         this.field8.bridge$mouseClicked(var1.xi(), var1.method5(), var2);
      }

      super.method3(var1, var2);
   }

   @Override
   public void method4(Bridge_65 var1, int var2) {
      if (DriverViewportLegacy.method50().method64() == null) {
         this.field8.bridge$mouseReleased(var1.xi(), var1.method5(), var2);
      }

      super.method4(var1, var2);
   }

   @Override
   public void method5(char var1, KeyCode var2) {
      if (this.field8 instanceof Bridge5Extension62 var3) {
         var3.method2().method5(var1, var2);
      } else {
         this.field8.bridge$keyTyped(var1, var2.getVk(), 0);
      }

      super.method5(var1, var2);
   }

   @Override
   public void onGuiClosed() {
      if (this.field8 instanceof Bridge5Extension62 var1) {
         var1.method2().onGuiClosed();
      }

      super.onGuiClosed();
   }

   @Override
   public void method6(int var1, Bridge_65 var2) {
      if (this.field8 instanceof Bridge5Extension62 var3) {
         var3.method2().method6(var1, var2);
      } else {
         this.field8.bridge$mouseScrolled(var2.xi(), var2.method5(), 0.0, var1);
      }

      super.method6(var1, var2);
   }

   @Override
   public boolean method16(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data6 var1) {
      if (DriverViewportLegacy.method50().method64() != null) {
         this.method11(true);
         return true;
      } else if (this.field8 instanceof com.moonsworth.lunar.client.ui.LcuiScreen var2) {
         this.method11(false);
         return var2.method16(null, var1.method5());
      } else {
         this.method11(false);
         return true;
      }
   }

   public void method10(Runnable var1) {
      Bridge5_12 var2 = ThreadModuleDump63.method3();
      Bridge5Extension6 var3 = var2.bridge$getCurrentScreen();
      if (var3 == this.field8) {
         var1.run();
      } else {
         var2.bridge$setCurrentScreenSilent(this.field8);

         try {
            var1.run();
         } finally {
            var2.bridge$setCurrentScreenSilent(var3);
         }
      }
   }

   private void method11(boolean var1) {
      if (var1 != this.field7 && var1 && this.field8 instanceof Bridge5Extension62 var2) {
         var2.method2().method9();
      }

      this.field7 = var1;
   }

   public float method12() {
      return this.field8 instanceof com.moonsworth.lunar.client.ui.LcuiScreen var1 ? var1.method22() : this.getWidth();
   }

   public float method13() {
      return this.field8 instanceof com.moonsworth.lunar.client.ui.LcuiScreen var1 ? var1.method23() : this.getHeight();
   }

   @Generated
   public boolean method15() {
      return this.field6;
   }

   @Generated
   public boolean method16() {
      return this.field7;
   }

   @Generated
   public Bridge5Extension6 method17() {
      return this.field8;
   }
}
