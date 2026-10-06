package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.DriverContext;
import com.moonsworth.lunar.client.driver.hologram.HologramRenderer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.MousePosition;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.util.List;
import lombok.Generated;
import org.lwjgl.opengl.GL11;
import com.moonsworth.lunar.client.driver.core.DualMarkerScreenLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;

public class DriverView {
   private static DriverView field1;
   private final HologramRenderer field2;
   private final DriverViewportLegacy field3;
   private int width = Ref.method3().bridge$displayWidth();
   private int height = Ref.method3().bridge$displayHeight();
   private int field4 = Ref.method3().bridge$getMainRenderTarget().bridge$framebufferWidth();
   private int field5 = Ref.method3().bridge$getMainRenderTarget().bridge$framebufferHeight();
   private com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 field6 = new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5(0.0, 0.0);
   private boolean field7;
   private long field8;
   private long field9;

   public static void init() {
      field1 = new DriverView();
   }

   public DriverView() {
      this.field2 = new HologramRenderer();
      this.field3 = new DriverViewportLegacy(this);
   }

   public void method1(DriverRouteRegistry markers2handler21, DriverContext markers_32) {
      Ref.method3().bridge$displayScreen(Bridge.method8().method18(new DualMarkerScreenLegacy(markers2handler21, markers_32)));
   }

   public void method2(MixinHelper_4 mixinhelper_41) {
      com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 data52 = MousePosition.method1().method8();
      if (DriverViewportLegacy.method52() != null && System.currentTimeMillis() - this.field9 >= 5L) {
         DriverViewportLegacy.method52().invokeIteration();
         this.field9 = System.currentTimeMillis();
      }

      AbstractRenderContext bridgeextension_93 = mixinhelper_41.method48();
      boolean flag4 = !mixinhelper_41.method50();
      if (flag4) {
         bridgeextension_93.push();
         bridgeextension_93.translate(0.0, 0.0, -11000.0);
      }

      if (this.method12() != null) {
         this.method12().method1(bridgeextension_93, data52);
      }

      if (this.field7) {
         this.field3.method12(data52);
         this.field7 = false;
      }

      bridgeextension_93.method40();
      this.field2.method2(bridgeextension_93, data52);
      bridgeextension_93.method41();
      if (flag4) {
         bridgeextension_93.pop();
      }

      this.field3.method12(mixinhelper_41, data52);
      if (DriverViewportLegacy.method50().method71()) {
         this.method13();
      }
   }

   public void method3(int number1, int number2) {
      this.field4 = number1;
      this.field5 = number2;
      this.width = number1;
      this.height = number2;
      this.field3.method3(this.width, this.height, this.field4, this.field5);
      if (this.method12() != null) {
         this.method12().method2(number1, number2);
      }
   }

   public void method4(boolean flag1) {
      if (DriverViewportLegacy.method43()) {
         this.field3.method8(flag1);
      }
   }

   public void method5(KeyCode bridgetype_81, int number2, int number3, int number4, int number5) {
      if (this.method11()) {
         this.field3.method9(bridgetype_81, number2, number3, number4, number5);
         if (this.method12() != null) {
            this.method12().method3(bridgetype_81, number2, number3, number4, number5);
         }
      }
   }

   public void method6(int number1) {
      if (this.method11()) {
         this.field3.method10(number1);
      }
   }

   public void method7(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data6 data61) {
      com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 data52 = data61.method8();
      if (!this.field6.equals(data52)) {
         this.field6 = data52;
         this.field7 = true;
      }
   }

   public void method8(int number1, int number2, int number3) {
      if (this.method11()) {
         this.field3.method12(number1, number2, number3, this.field6);
         this.field2.method4(number1, number2, this.field6);
         if (this.method12() != null) {
            this.method12().method4(number1, number2, number3);
         }
      }
   }

   public void method9(double value1, double value3) {
      if (this.method11()) {
         if (LcuiScreen.isShiftKeyDown()) {
            value1 = value3;
            value3 = 0.0;
         }

         if (this.field2.method5(value1, value3, this.field6)) {
            return;
         }

         this.field3.method13(value1, value3);
         if (this.method12() != null) {
            this.method12().method5(value1, value3);
         }
      }
   }

   public void method10(List<Path> list1) {
      if (this.method11()) {
         this.field3.method14(list1);
      }
   }

   private boolean method11() {
      return !Ref.method3().bridge$areResourcesLoaded()
         ? false
         : this.field3 != null && DriverViewportLegacy.method43() && (this.field3.hasFocus() || Ref.method11() != null);
   }

   private com.moonsworth.lunar.client.driver.DriverViewContext method12() {
      return this.field3.method63().method22();
   }

   private void method13() {
      if (System.currentTimeMillis() - this.field8 >= 30L) {
         this.field8 = System.currentTimeMillis();
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data3 data31 = this.field6.IRIIIICCIOIHCOICOHCRRHORCIOIIC();
         ByteBuffer buffer2 = ByteBuffer.allocateDirect(4);
         buffer2.order(ByteOrder.nativeOrder());
         int number3 = Ref.method3().bridge$getMainRenderTarget().bridge$framebufferHeight();
         GL11.glReadBuffer(1028);
         GL11.glReadPixels(data31.xi(), number3 - data31.RROCOHICOORRHCIHHHCHRCICHIIHCO(), 1, 1, 32993, 33639, buffer2);
         int number4 = buffer2.get(0) & 255;
         int number5 = buffer2.get(1) & 255;
         int number6 = buffer2.get(2) & 255;
         float[] items7 = new float[3];
         Color.RGBtoHSB(number6, number5, number4, items7);
         float value8 = items7[0];
         float value9 = items7[1];
         float value10 = items7[2];
         JsonObject json11 = new JsonObject();
         json11.addProperty("hue", value8);
         json11.addProperty("saturation", value9);
         json11.addProperty("brightness", value10);
         json11.addProperty("alpha", 1.0F);
         DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "pickedColor", json11);
      }
   }

   @Generated
   public HologramRenderer method14() {
      return this.field2;
   }

   @Generated
   public DriverViewportLegacy method15() {
      return this.field3;
   }

   @Generated
   public int getWidth() {
      return this.width;
   }

   @Generated
   public int getHeight() {
      return this.height;
   }

   @Generated
   public int method16() {
      return this.field4;
   }

   @Generated
   public int method17() {
      return this.field5;
   }

   @Generated
   public boolean method18() {
      return this.field7;
   }

   @Generated
   public long method19() {
      return this.field8;
   }

   @Generated
   public long method20() {
      return this.field9;
   }

   @Generated
   public static DriverView method21() {
      return field1;
   }
}
