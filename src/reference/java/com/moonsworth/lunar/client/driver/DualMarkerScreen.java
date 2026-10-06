package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MarkerPositionBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class DualMarkerScreen implements Bridge7_8 {
   private final Bridge7_8 field1;
   private final Bridge7_8 field2;
   protected boolean field3 = true;

   public DualMarkerScreen(Bridge7_8 bridge7_81, Bridge7_8 bridge7_82) {
      this.field1 = bridge7_81;
      this.field2 = bridge7_82;
   }

   public int getWidth() {
      return this.field1.getWidth();
   }

   public int getHeight() {
      return this.field1.getHeight();
   }

   public void method1(MixinHelper_4 mixinhelper_41, MarkerPositionBridge bridge_652, float value3) {
      this.field1.method1(mixinhelper_41, this.field3 ? bridge_652 : new MarkerModel.Data4(0.0, 0.0), value3);
      this.field2.method1(mixinhelper_41, bridge_652, value3);
   }

   public void method2(MinecraftBridge bridge5_121, MarkerPositionBridge bridge_652) {
      this.field1.method2(bridge5_121, bridge_652);
      this.field2.method2(bridge5_121, bridge_652);
   }

   public void method3(MarkerPositionBridge bridge_651, int number2) {
      if (this.field3) {
         this.field1.method3(bridge_651, number2);
      }

      this.field2.method3(bridge_651, number2);
   }

   public void method4(MarkerPositionBridge bridge_651, int number2) {
      if (this.field3) {
         this.field1.method4(bridge_651, number2);
      }

      this.field2.method4(bridge_651, number2);
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

   public void method5(char character1, KeyCode bridgetype_82) {
      if (this.field3) {
         this.field1.method5(character1, bridgetype_82);
      }

      this.field2.method5(character1, bridgetype_82);
   }

   public void initGui() {
      this.field1.initGui();
      this.field2.initGui();
   }

   public void method6(int number1, MarkerPositionBridge bridge_652) {
      if (this.field3) {
         this.field1.method6(number1, bridge_652);
      }

      this.field2.method6(number1, bridge_652);
   }

   public boolean method7() {
      return this.field1.method7() && this.field2.method7();
   }

   public boolean doesGuiPauseGame() {
      return this.field1.doesGuiPauseGame();
   }

   public final Bridge7_8 method10() {
      Ref.method3().bridge$displayScreen(Bridge.method8().method18(this));
      return this;
   }

   @Generated
   public Bridge7_8 method11() {
      return this.field2;
   }

   @Generated
   public void method10(boolean flag1) {
      this.field3 = flag1;
   }
}
