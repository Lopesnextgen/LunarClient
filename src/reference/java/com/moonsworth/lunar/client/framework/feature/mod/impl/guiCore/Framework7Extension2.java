package com.moonsworth.lunar.client.framework.feature.mod.impl.guiCore;

import com.moonsworth.lunar.bridge.Bridge5Extension9;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Gui2Extension5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms3_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsHandler;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_9;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler23_2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler29;
import com.moonsworth.lunar.client.event.input.MarkerInputEvent;
import com.moonsworth.lunar.client.event.input.MouseInputTypeLegacy;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump85;
import lombok.Generated;

public abstract class Framework7Extension2 extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   private final GuiRewindhandlersHandler2_2 field8 = (GuiRewindhandlersHandler2_2)this.method19(GuiRewindhandlersHandler2_2.class);
   private final GuiRewindhandlersHandler23_2 field9 = (GuiRewindhandlersHandler23_2)this.method19(GuiRewindhandlersHandler23_2.class);
   private final GuiRewindhandlersHandler29 field10 = (GuiRewindhandlersHandler29)this.method19(GuiRewindhandlersHandler29.class);
   private final Holograms3_2 field11 = new Holograms3_2(new HologramsHandler(this.field8, this.field9));
   private final Nameplate field12;
   private final Holograms_9 field13;
   private Holograms2_5 field14;

   public Framework7Extension2(Skyblock var1, HologramsType var2) {
      this(var1, false, var2);
   }

   public Framework7Extension2(Skyblock var1, boolean var2, HologramsType var3) {
      super(var2);
      this.field13 = new Holograms_9(var3, this.field10);
      this.field12 = Nameplate.method4(new Framework7Extension2.Data2(this.field13));
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(Framework.field1, this.field12);
      this.method2(Framework.field17, Framework2.method2(SettingsPage.DUNGEONS));
      this.method2(Framework.field19, Framework11.method1(this, () -> Click3.getIsland() == Gui2Extension3.DUNGEON));
      this.handle(MarkerInputEvent.class, this::method2);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method45(var1);
      this.field13.method1(var1);
   }

   private void method2(MarkerInputEvent var1) {
      if (var1.method4() == MouseInputTypeLegacy.CLICK) {
         if (ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            float var2 = this.field12.method2();
            if (this.field13.method28()) {
               var2 += 10.0F;
            }

            this.field11
               .method9(
                  this.field13,
                  this.field12.method1() * this.field12.getScale(),
                  var2 * this.field12.getScale(),
                  this.field12.getScale(),
                  var1.method2(),
                  var1.method3()
               );
         }
      }
   }

   @Generated
   public Holograms_9 method13() {
      return this.field13;
   }

   private class Data2 extends HudElementBase {
      private final Holograms_9 field9;

      public Data2(Holograms_9 var2) {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
         this.field9 = var2;
         this.method5();
         var2.method51(this::method5);
         var2.method52(this::method5);
      }

      public void method5() {
         byte var1 = 100;
         if (this.field9.method28()) {
            var1 += 10;
         }

         if (this.field9.method31() != Gui2Extension5.NONE) {
            var1 += 15;
         }

         this.method58(100.0F, var1);
      }

      @Override
      public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
         Holograms2_5 var5 = Framework7Extension2.this.field8.method5().orElse(null);
         if (var5 == null) {
            if (!var4) {
               return;
            }

            if (Framework7Extension2.this.field14 == null) {
               Framework7Extension2.this.field14 = Holograms2_2.method1(true);
            }

            var5 = Framework7Extension2.this.field14;
         }

         if (this.field9.method28()) {
            var3 += 10.0F;
         }

         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var6 = new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2(-9999.0, -9999.0);
         if (!var4 && ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            var6 = ThreadModuleDump85.get().method5();
         }

         float var7 = this.getScale();
         var1.method2().push();
         var1.method2().scale(1.0F / var7, 1.0F / var7, 1.0F);
         Framework7Extension2.this.field11.method1(var1.method2(), this.field9, var5, var2 * var7, var3 * var7, var7, var6);
         var1.method2().pop();
      }

      @Override
      public boolean method4(boolean var1) {
         if (var1) {
            return true;
         } else {
            Bridge5Extension9 var2 = ThreadModuleDump63.method3().bridge$getGuiIngame();
            if (var2 == null) {
               return false;
            } else {
               return var2.bridge$isTabVisible() && !this.field9.method42() ? false : Click3.getIsland() == Gui2Extension3.DUNGEON;
            }
         }
      }

      @Override
      public boolean method31() {
         return false;
      }
   }
}
