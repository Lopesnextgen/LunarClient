package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.GuiRecipeBookBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiIngameMenuBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ContainerBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.replay.gui.GuiScreenContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.screen.EventResolutionChange;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPre;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import lombok.Generated;

public class ScreenRenderHandler extends RewindHandler {
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("show").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "cursors/cursor.png");
   private GuiResolution field11;
   private GuiResolution field12;

   public ScreenRenderHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
      this.handle(EventScreenChange.class, this::method1);
      this.handle(EventRenderContainerSlotPre.class, this::method3);
      this.handle(EventResolutionChange.class, this::method6);
   }

   private void method1(EventScreenChange highlightimpl71) {
      ReplayContext nameplate42 = (ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get();
      boolean flag3 = highlightimpl71.method1() instanceof ContainerBridge || highlightimpl71.method1() instanceof GuiRecipeBookBridge || highlightimpl71.method1() instanceof Bridge5Extension612;
      Bridge7_8 bridge7_84 = Ref.method31(Ref.method3().bridge$getCurrentScreen());
      Bridge7_8 bridge7_85 = Ref.method31(highlightimpl71.method1());
      boolean flag6 = bridge7_84 != null && bridge7_84.getClass() == DualMarkerScreenLegacy.class;
      boolean flag7 = bridge7_85 != null && bridge7_85.getClass() == DualMarkerScreenLegacy.class;
      if ((nameplate42.method18() || flag3) && !flag7) {
         if (highlightimpl71.method1() instanceof GuiIngameMenuBridge) {
            highlightimpl71.cancel();
            nameplate42.method6().method22();
         } else {
            GuiScreenContext nameplate28 = nameplate42.method8();
            if (highlightimpl71.method1() != null) {
               nameplate28.method1(highlightimpl71.method1());
            }

            if (nameplate28.method5() != null) {
               highlightimpl71.cancel();
               if (Ref.method3().bridge$getCurrentScreen() != null) {
                  LunarEventBus.method29()
                     .method12(EventScreenOpen.class, () -> new EventScreenOpen(Ref.method3().bridge$getCurrentScreen(), nameplate28.method5()));
               }

               this.method7(true);
            } else {
               if (flag6) {
                  highlightimpl71.cancel();
                  if (Ref.method3().bridge$getCurrentScreen() != null) {
                     LunarEventBus.method29()
                        .method12(EventScreenOpen.class, () -> new EventScreenOpen(Ref.method3().bridge$getCurrentScreen(), null));
                  }
               }

               nameplate28.method2(0, 0);
            }
         }
      } else {
         if (flag6 && !flag7) {
            highlightimpl71.cancel();
         }
      }
   }

   public boolean method14() {
      ReplayContext nameplate41 = (ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get();
      return nameplate41.method8().method5() != null
         && (Boolean)this.field9.get()
         && Ref.method3().bridge$getRenderViewEntity() == Ref.method3().bridge$getPlayer()
         && nameplate41.method6().method45().method15().isFirstPerson()
         && !Ref.method3().bridge$getGameSettings().bridge$isHideGui();
   }

   private void method3(EventRenderContainerSlotPre data41) {
      ReplayContext nameplate42 = (ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get();
      if (this.method14()) {
         GuiScreenContext nameplate23 = nameplate42.method8();
         GuiScreenBridge bridge5extension64 = nameplate23.method5();
         float value5 = nameplate42.method6().method41().getPartialTick();
         AbstractRenderContext bridgeextension_96 = data41.OCCRRRIHHOCOHOOOOIRROIORRCHIOR();
         int number7 = Ref.MC_VERSION >= 6 ? bridge5extension64.bridge$getInventoryScale() : 0;
         boolean flag8 = number7 > 0;
         float value9 = 1.0F;
         GuiResolution threadmoduledump7110 = null;
         if (flag8) {
            double value11 = LcuiScreen.method20();
            int number13 = Math.max(1, (int)(this.mc.bridge$getGuiScale() / value11));
            value9 = (float)number7 / number13;
            this.mc.bridge$setRawGuiScale(number7);
            bridgeextension_96.push();
            bridgeextension_96.scale(value9, value9, 1.0F);
            bridge5extension64.bridge$setInventoryScaleFactor(value9);
            threadmoduledump7110 = LcuiScreen.method151();
            LcuiScreen.method150(this.method15());
         }

         GuiResolution threadmoduledump7117 = LcuiScreen.method151();
         this.field12 = threadmoduledump7117;
         int number12 = nameplate23.method3(value5, threadmoduledump7117.getScaledWidth());
         int number18 = nameplate23.method4(value5, threadmoduledump7117.getScaledHeight());
         bridge5extension64.bridge$drawScreen(bridgeextension_96, number12, number18, data41.OICCCIHIRHICIOOHHRHOOIHHRROORC());
         bridgeextension_96.method33();
         bridgeextension_96.method11();
         float value14 = 20.0F / threadmoduledump7117.method3();
         MixinHelper_4 mixinhelper_415 = data41.HHHRIHCIOHCICRCCOCIRRROHIOHRIH();
         mixinhelper_415.push();
         mixinhelper_415.method38(0.0F, 0.0F, 500.0F);
         LcuiScreen.method31(mixinhelper_415, this.field10, number12, number18, value14, value14, -1);
         mixinhelper_415.pop();
         if (flag8) {
            bridgeextension_96.pop();
            bridge5extension64.bridge$setInventoryScaleFactor(1.0F);
            LcuiScreen.method150(threadmoduledump7110);
            this.mc.bridge$setGuiScale(this.mc.bridge$getGameSettings().bridge$getGuiScale());
         }
      }
   }

   private GuiResolution method15() {
      int number1 = this.mc.bridge$getGuiScale();
      int number2 = this.mc.bridge$displayWidth();
      int number3 = this.mc.bridge$displayHeight();
      return new GuiResolution(number1, number2, number3, (double)number2 / number1, (double)number3 / number1);
   }

   public GuiResolution method16() {
      return this.field12 != null ? this.field12 : this.field11;
   }

   private void method6(EventResolutionChange highlightimpl101) {
      this.method7(false);
   }

   public void method7(boolean flag1) {
      GuiScreenContext nameplate22 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method8();
      GuiScreenBridge bridge5extension63 = nameplate22.method5();
      if (bridge5extension63 != null) {
         RewindHandlers rewindhandlers4 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
         ExportTargetHandler rewindhandlers3impl85 = rewindhandlers4.method48();
         int number6 = rewindhandlers3impl85.method24().CHCHHIRHOORIOHCRORICRHIHCOOOII().bridge$framebufferWidth();
         int number7 = rewindhandlers3impl85.method24().CHCHHIRHOORIOHCRORICRHIHCOOOII().bridge$framebufferHeight();
         if (!rewindhandlers4.method44() || rewindhandlers4.method40().method31()) {
            number6 = Ref.method3().bridge$displayWidth();
            number7 = Ref.method3().bridge$displayHeight();
         }

         GuiResolution threadmoduledump718 = new GuiResolution(Ref.method3(), number6, number7);
         LcuiScreen.method150(threadmoduledump718);
         if (flag1 || this.field11.getWidth() != number6 || this.field11.getHeight() != number7 || this.field11.method3() != threadmoduledump718.method3()) {
            this.field11 = threadmoduledump718;
            bridge5extension63.bridge$setWorldAndResolution(number6 / threadmoduledump718.method3(), number7 / threadmoduledump718.method3());
         }
      }
   }

   @Generated
   public GuiResolution method17() {
      return this.field11;
   }
}
