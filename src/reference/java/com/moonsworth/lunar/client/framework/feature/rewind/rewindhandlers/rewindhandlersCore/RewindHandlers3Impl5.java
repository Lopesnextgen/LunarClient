package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.moonsworth.lunar.bridge.Bridge4_15;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension611;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ContainerMarker;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.screen.ResolutionChangeEvent;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.screen.ScreenOpenEvent;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent.ContainerSlotPreEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import lombok.Generated;

public class RewindHandlers3Impl5 extends RewindHandlers3 {
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("show").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "cursors/cursor.png");
   private ThreadModuleDump71 field11;
   private ThreadModuleDump71 field12;

   public RewindHandlers3Impl5(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
      this.handle(ScreenChangeEvent.class, this::method1);
      this.handle(ContainerSlotPreEvent.class, this::method3);
      this.handle(ResolutionChangeEvent.class, this::method6);
   }

   private void method1(ScreenChangeEvent var1) {
      Nameplate4 var2 = (Nameplate4)this.field8.get();
      boolean var3 = var1.method1() instanceof ContainerMarker || var1.method1() instanceof Bridge4_15 || var1.method1() instanceof Bridge5Extension612;
      Bridge7_8 var4 = ThreadModuleDump63.method31(ThreadModuleDump63.method3().bridge$getCurrentScreen());
      Bridge7_8 var5 = ThreadModuleDump63.method31(var1.method1());
      boolean var6 = var4 != null && var4.getClass() == DualMarkerScreenLegacy.class;
      boolean var7 = var5 != null && var5.getClass() == DualMarkerScreenLegacy.class;
      if ((var2.method18() || var3) && !var7) {
         if (var1.method1() instanceof Bridge5Extension611) {
            var1.cancel();
            var2.method6().method22();
         } else {
            Nameplate2 var8 = var2.method8();
            if (var1.method1() != null) {
               var8.method1(var1.method1());
            }

            if (var8.method5() != null) {
               var1.cancel();
               if (ThreadModuleDump63.method3().bridge$getCurrentScreen() != null) {
                  ClientEventBus.method29()
                     .method12(ScreenOpenEvent.class, () -> new ScreenOpenEvent(ThreadModuleDump63.method3().bridge$getCurrentScreen(), var8.method5()));
               }

               this.method7(true);
            } else {
               if (var6) {
                  var1.cancel();
                  if (ThreadModuleDump63.method3().bridge$getCurrentScreen() != null) {
                     ClientEventBus.method29()
                        .method12(ScreenOpenEvent.class, () -> new ScreenOpenEvent(ThreadModuleDump63.method3().bridge$getCurrentScreen(), null));
                  }
               }

               var8.method2(0, 0);
            }
         }
      } else {
         if (var6 && !var7) {
            var1.cancel();
         }
      }
   }

   public boolean method14() {
      Nameplate4 var1 = (Nameplate4)this.field8.get();
      return var1.method8().method5() != null
         && (Boolean)this.field9.get()
         && ThreadModuleDump63.method3().bridge$getRenderViewEntity() == ThreadModuleDump63.method3().bridge$getPlayer()
         && var1.method6().method45().method15().isFirstPerson()
         && !ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isHideGui();
   }

   private void method3(ContainerSlotPreEvent var1) {
      Nameplate4 var2 = (Nameplate4)this.field8.get();
      if (this.method14()) {
         Nameplate2 var3 = var2.method8();
         Bridge5Extension6 var4 = var3.method5();
         float var5 = var2.method6().method41().getPartialTick();
         AbstractRenderContext var6 = var1.OCCRRRIHHOCOHOOOOIRROIORRCHIOR();
         int var7 = ThreadModuleDump63.MC_VERSION >= 6 ? var4.bridge$getInventoryScale() : 0;
         boolean var8 = var7 > 0;
         float var9 = 1.0F;
         ThreadModuleDump71 var10 = null;
         if (var8) {
            double var11 = LcuiScreen.method20();
            int var13 = Math.max(1, (int)(this.mc.bridge$getGuiScale() / var11));
            var9 = (float)var7 / var13;
            this.mc.bridge$setRawGuiScale(var7);
            var6.push();
            var6.scale(var9, var9, 1.0F);
            var4.bridge$setInventoryScaleFactor(var9);
            var10 = LcuiScreen.method151();
            LcuiScreen.method150(this.method15());
         }

         ThreadModuleDump71 var17 = LcuiScreen.method151();
         this.field12 = var17;
         int var12 = var3.method3(var5, var17.getScaledWidth());
         int var18 = var3.method4(var5, var17.getScaledHeight());
         var4.bridge$drawScreen(var6, var12, var18, var1.method2());
         var6.method33();
         var6.method11();
         float var14 = 20.0F / var17.getScaleFactor();
         MixinHelper_4 var15 = var1.method5();
         var15.push();
         var15.method38(0.0F, 0.0F, 500.0F);
         LcuiScreen.method31(var15, this.field10, var12, var18, var14, var14, -1);
         var15.pop();
         if (var8) {
            var6.pop();
            var4.bridge$setInventoryScaleFactor(1.0F);
            LcuiScreen.method150(var10);
            this.mc.bridge$setGuiScale(this.mc.bridge$getGameSettings().bridge$getGuiScale());
         }
      }
   }

   private ThreadModuleDump71 method15() {
      int var1 = this.mc.bridge$getGuiScale();
      int var2 = this.mc.bridge$displayWidth();
      int var3 = this.mc.bridge$displayHeight();
      return new ThreadModuleDump71(var1, var2, var3, (double)var2 / var1, (double)var3 / var1);
   }

   public ThreadModuleDump71 method16() {
      return this.field12 != null ? this.field12 : this.field11;
   }

   private void method6(ResolutionChangeEvent var1) {
      this.method7(false);
   }

   public void method7(boolean var1) {
      Nameplate2 var2 = ((Nameplate4)this.field8.get()).method8();
      Bridge5Extension6 var3 = var2.method5();
      if (var3 != null) {
         RewindHandlers var4 = ((Nameplate4)this.field8.get()).method6();
         RewindHandlers3Impl8 var5 = var4.method48();
         int var6 = var5.method24().method11().bridge$framebufferWidth();
         int var7 = var5.method24().method11().bridge$framebufferHeight();
         if (!var4.method44() || var4.method40().method31()) {
            var6 = ThreadModuleDump63.method3().bridge$displayWidth();
            var7 = ThreadModuleDump63.method3().bridge$displayHeight();
         }

         ThreadModuleDump71 var8 = new ThreadModuleDump71(ThreadModuleDump63.method3(), var6, var7);
         LcuiScreen.method150(var8);
         if (var1 || this.field11.getWidth() != var6 || this.field11.getHeight() != var7 || this.field11.getScaleFactor() != var8.getScaleFactor()) {
            this.field11 = var8;
            var3.bridge$setWorldAndResolution(var6 / var8.getScaleFactor(), var7 / var8.getScaleFactor());
         }
      }
   }

   @Generated
   public ThreadModuleDump71 method17() {
      return this.field11;
   }
}
