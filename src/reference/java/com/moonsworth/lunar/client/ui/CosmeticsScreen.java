package com.moonsworth.lunar.client.ui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.List;
import lombok.Generated;

public abstract class CosmeticsScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private CosmeticsBrowserWidget field19;
   private final Bridge5Extension6 field20;

   public CosmeticsScreen(Bridge5Extension6 var1) {
      this.field20 = var1;
   }

   public abstract CosmeticsBrowserWidget method1(CosmeticsScreen var1);

   @Override
   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field19 = this.method1(this));
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
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
   }

   @Override
   public void method12(MarkerModel.Data2 var1, int var2) {
      if (!this.field19.method3(var1)) {
         this.field19.method7(var1, var2);
      }
   }

   @Override
   public void method14(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Generated
   public Bridge5Extension6 method10() {
      return this.field20;
   }
}
