package com.moonsworth.lunar.client.ui.menu;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.HoverAnimation;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;

public class FeatureSettingsScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private ModMenuWidget field19;
   private HoverAnimation field20;
   private final Bridge5Extension6 field21;

   public FeatureSettingsScreen(Bridge5Extension6 var1) {
      this.field21 = var1;
      this.field20 = new HoverAnimation(5000L);
      this.field20.start();
   }

   @Override
   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field19 = new ModMenuWidget(this, null));
   }

   public void method2(int var1) {
      this.method10().method3(var1);
   }

   @Override
   public void init() {
      float var1 = 492.0F;
      float var2 = 303.0F;
      this.field19.method2(this.method22() / 2.0F - var1 / 2.0F, this.method23() / 2.0F - var2 / 2.0F, var1, var2);
   }

   @Override
   public void update() {
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      if (ThreadModuleDump63.method3().bridge$getWorld() == null) {
         ClientEventBus.method29()
            .method12(
               EventRenderHudLegacy.Data.class,
               () -> new EventRenderHudLegacy.Data(
                  var1.method48(), var1, new MarkerModel.Data2(this.method22(), this.method23())
               )
            );
      }
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
      if (this.field19 != null) {
         if (this.field19.method18() != null) {
            return;
         }

         if (this.field19.method17().method8(var1, var2)) {
            IHIIRCRIIHICHHRIOOHIROHRHHRCHI();
         }
      }
   }

   @Override
   public void method12(MarkerModel.Data2 var1, int var2) {
   }

   @Override
   public void method14(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
      if (this.field19.method17() instanceof ModSearchWidget) {
         ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      }

      if (this.field19 != null) {
         this.field19.close();
      }
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + ".settings";
   }

   @Generated
   public ModMenuWidget method10() {
      return this.field19;
   }

   @Generated
   public HoverAnimation method11() {
      return this.field20;
   }

   @Generated
   public Bridge5Extension6 method12() {
      return this.field21;
   }
}
