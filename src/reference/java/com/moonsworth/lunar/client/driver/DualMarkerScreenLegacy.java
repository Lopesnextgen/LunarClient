package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Bridge_65;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class DualMarkerScreenLegacy implements Bridge7_8 {
   private final Bridge7_8 field1;
   private final Bridge7_8 field2;
   protected boolean field3 = true;

   public DualMarkerScreenLegacy(Bridge7_8 var1, Bridge7_8 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public int getWidth() {
      return this.field1.getWidth();
   }

   public int getHeight() {
      return this.field1.getHeight();
   }

   public void method1(MixinHelper_4 var1, Bridge_65 var2, float var3) {
      this.field1.method1(var1, this.field3 ? var2 : new MarkerModel.Data4(0.0, 0.0), var3);
      this.field2.method1(var1, var2, var3);
   }

   public void method2(Bridge5_12 var1, Bridge_65 var2) {
      this.field1.method2(var1, var2);
      this.field2.method2(var1, var2);
   }

   public void method3(Bridge_65 var1, int var2) {
      if (this.field3) {
         this.field1.method3(var1, var2);
      }

      this.field2.method3(var1, var2);
   }

   public void method4(Bridge_65 var1, int var2) {
      if (this.field3) {
         this.field1.method4(var1, var2);
      }

      this.field2.method4(var1, var2);
   }

   public void handleMouseInput() {
      if (this.field3) {
         this.field1.handleMouseInput();
      }

      this.field2.handleMouseInput();
   }

   public void onGuiClosed() {
      this.field1.onGuiClosed();
      this.field2.onGuiClosed();
   }

   public void updateScreen() {
      this.field1.updateScreen();
      this.field2.updateScreen();
   }

   public void method5(char var1, KeyCode var2) {
      if (this.field3) {
         this.field1.method5(var1, var2);
      }

      this.field2.method5(var1, var2);
   }

   public void initGui() {
      this.field1.initGui();
      this.field2.initGui();
   }

   public void method6(int var1, Bridge_65 var2) {
      if (this.field3) {
         this.field1.method6(var1, var2);
      }

      this.field2.method6(var1, var2);
   }

   public boolean method7() {
      return this.field1.method7() && this.field2.method7();
   }

   public boolean doesGuiPauseGame() {
      return this.field1.doesGuiPauseGame();
   }

   public final Bridge7_8 method10() {
      ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(this));
      return this;
   }

   @Generated
   public Bridge7_8 method11() {
      return this.field2;
   }

   @Generated
   public void method10(boolean var1) {
      this.field3 = var1;
   }
}
