package com.moonsworth.lunar.client.driver.core;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverContextLegacy;
import com.moonsworth.lunar.client.driver.core.holograms.HologramRendererLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump85;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.util.List;
import lombok.Generated;
import org.lwjgl.opengl.GL11;

public class DriverViewLegacy {
   private static DriverViewLegacy field1;
   private final HologramRendererLegacy field2;
   private final DriverViewportLegacy field3;
   private int width = ThreadModuleDump63.method3().bridge$displayWidth();
   private int height = ThreadModuleDump63.method3().bridge$displayHeight();
   private int field4 = ThreadModuleDump63.method3().bridge$getMainRenderTarget().bridge$framebufferWidth();
   private int field5 = ThreadModuleDump63.method3().bridge$getMainRenderTarget().bridge$framebufferHeight();
   private com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 field6 = new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5(0.0, 0.0);
   private boolean field7;
   private long field8;
   private long field9;

   public static void init() {
      field1 = new DriverViewLegacy();
   }

   public DriverViewLegacy() {
      this.field2 = new HologramRendererLegacy();
      this.field3 = new DriverViewportLegacy(this);
   }

   public void method1(DriverRouteRegistryLegacy var1, DriverContextLegacy var2) {
      ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new DualMarkerScreenLegacy(var1, var2)));
   }

   public void method2(MixinHelper_4 var1) {
      com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 var2 = ThreadModuleDump85.get().method8();
      if (DriverViewportLegacy.method52() != null && System.currentTimeMillis() - this.field9 >= 5L) {
         DriverViewportLegacy.method52().invokeIteration();
         this.field9 = System.currentTimeMillis();
      }

      AbstractRenderContext var3 = var1.method48();
      boolean var4 = !var1.method50();
      if (var4) {
         var3.push();
         var3.translate(0.0, 0.0, -11000.0);
      }

      if (this.method12() != null) {
         this.method12().method1(var3, var2);
      }

      if (this.field7) {
         this.field3.method12(var2);
         this.field7 = false;
      }

      var3.method40();
      this.field2.method2(var3, var2);
      var3.method41();
      if (var4) {
         var3.pop();
      }

      this.field3.method12(var1, var2);
      if (DriverViewportLegacy.method50().method71()) {
         this.method13();
      }
   }

   public void method3(int var1, int var2) {
      this.field4 = var1;
      this.field5 = var2;
      this.width = var1;
      this.height = var2;
      this.field3.method4(this.width, this.height, this.field4, this.field5);
      if (this.method12() != null) {
         this.method12().method2(var1, var2);
      }
   }

   public void method4(boolean var1) {
      if (DriverViewportLegacy.method43()) {
         this.field3.method8(var1);
      }
   }

   public void method5(KeyCode var1, int var2, int var3, int var4, int var5) {
      if (this.method11()) {
         this.field3.method9(var1, var2, var3, var4, var5);
         if (this.method12() != null) {
            this.method12().method3(var1, var2, var3, var4, var5);
         }
      }
   }

   public void method6(int var1) {
      if (this.method11()) {
         this.field3.method10(var1);
      }
   }

   public void method7(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data6 var1) {
      com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 var2 = var1.method8();
      if (!this.field6.equals(var2)) {
         this.field6 = var2;
         this.field7 = true;
      }
   }

   public void method8(int var1, int var2, int var3) {
      if (this.method11()) {
         this.field3.method12(var1, var2, var3, this.field6);
         this.field2.method4(var1, var2, this.field6);
         if (this.method12() != null) {
            this.method12().method4(var1, var2, var3);
         }
      }
   }

   public void method9(double var1, double var3) {
      if (this.method11()) {
         if (LcuiScreen.isShiftKeyDown()) {
            var1 = var3;
            var3 = 0.0;
         }

         if (this.field2.method5(var1, var3, this.field6)) {
            return;
         }

         this.field3.method13(var1, var3);
         if (this.method12() != null) {
            this.method12().method5(var1, var3);
         }
      }
   }

   public void method10(List<Path> var1) {
      if (this.method11()) {
         this.field3.method14(var1);
      }
   }

   private boolean method11() {
      return !ThreadModuleDump63.method3().bridge$areResourcesLoaded()
         ? false
         : this.field3 != null && DriverViewportLegacy.method43() && (this.field3.hasFocus() || ThreadModuleDump63.method11() != null);
   }

   private com.moonsworth.lunar.client.driver.DriverViewContextLegacy method12() {
      return this.field3.method63().method22();
   }

   private void method13() {
      if (System.currentTimeMillis() - this.field8 >= 30L) {
         this.field8 = System.currentTimeMillis();
         com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data3 var1 = this.field6.IRIIIICCIOIHCOICOHCRRHORCIOIIC();
         ByteBuffer var2 = ByteBuffer.allocateDirect(4);
         var2.order(ByteOrder.nativeOrder());
         int var3 = ThreadModuleDump63.method3().bridge$getMainRenderTarget().bridge$framebufferHeight();
         GL11.glReadBuffer(1028);
         GL11.glReadPixels(var1.xi(), var3 - var1.RROCOHICOORRHCIHHHCHRCICHIIHCO(), 1, 1, 32993, 33639, var2);
         int var4 = var2.get(0) & 255;
         int var5 = var2.get(1) & 255;
         int var6 = var2.get(2) & 255;
         float[] var7 = new float[3];
         Color.RGBtoHSB(var6, var5, var4, var7);
         float var8 = var7[0];
         float var9 = var7[1];
         float var10 = var7[2];
         JsonObject var11 = new JsonObject();
         var11.addProperty("hue", var8);
         var11.addProperty("saturation", var9);
         var11.addProperty("brightness", var10);
         var11.addProperty("alpha", 1.0F);
         DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "pickedColor", var11);
      }
   }

   @Generated
   public HologramRendererLegacy method14() {
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
   public static DriverViewLegacy method21() {
      return field1;
   }
}
