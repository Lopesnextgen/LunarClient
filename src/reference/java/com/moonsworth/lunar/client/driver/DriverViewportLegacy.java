package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public abstract class DriverViewportLegacy implements EventRegistrar, Calculator2 {
   public static boolean debug = Boolean.getBoolean("lunar.webosr.debug");
   protected final Bridge5_12 field1 = ThreadModuleDump63.method3();
   protected int width;
   protected int height;
   protected int framebufferWidth;
   protected int framebufferHeight;
   private final List<DriverComponentLegacy<?>> field2 = Collections.synchronizedList(new ArrayList<>());

   public final int getWidth() {
      return this.width;
   }

   public final int getHeight() {
      return this.height;
   }

   public final void method3(int var1, int var2, int var3, int var4) {
      this.method4(var1, var2, var3, var4);
      this.width = var1;
      this.height = var2;
      this.framebufferWidth = var3;
      this.framebufferHeight = var4;

      for (DriverComponentLegacy var6 : this.field2) {
         var6.method22(var1, var2, var3, var4);
      }

      if (debug) {
         System.out.printf("[%s - COMPONENTS: %d]%n", this.getClass().getSimpleName(), this.method19(this.method20()));

         for (DriverComponentLegacy var8 : this.method20()) {
            this.method18("", var8);
         }
      }
   }

   public void method4(int var1, int var2, int var3, int var4) {
   }

   public final void method5(MixinHelper_4 var1, MarkerModel.Data5 var2) {
      this.method15(var1, var2);

      for (DriverComponentLegacy var4 : this.field2) {
         var4.method3(var1, var2);
      }
   }

   public void method6(int var1, int var2) {
      this.framebufferWidth = var1;
      this.framebufferHeight = var2;

      for (DriverComponentLegacy var4 : this.field2) {
         var4.method13(var1, var2);
      }
   }

   public void method7(int var1, int var2) {
      this.width = var1;
      this.height = var2;

      for (DriverComponentLegacy var4 : this.field2) {
         var4.method14(var1, var2);
      }
   }

   public void method8(boolean var1) {
      for (DriverComponentLegacy var3 : this.field2) {
         var3.method15(var1);
      }
   }

   public void method9(KeyCode var1, int var2, int var3, int var4, int value) {
      for (DriverComponentLegacy var7 : this.field2) {
         var7.method16(var1, var2, var3, var4, value);
      }
   }

   public void method10(int var1) {
      for (DriverComponentLegacy var3 : this.field2) {
         var3.method17(var1);
      }
   }

   public void method11(MarkerModel.Data5 var1) {
      for (DriverComponentLegacy var3 : this.field2) {
         var3.method18(var1);
      }
   }

   public void method12(int var1, int var2, int var3, MarkerModel.Data5 var4) {
      for (DriverComponentLegacy var6 : this.field2) {
         var6.method19(var1, var2, var3, var4);
      }
   }

   public void method13(double var1, double var3) {
      for (DriverComponentLegacy var6 : this.field2) {
         var6.method20(var1, var3);
      }
   }

   public void method14(List<Path> var1) {
      for (DriverComponentLegacy var3 : this.field2) {
         var3.method21(var1);
      }
   }

   public void method15(MixinHelper_4 var1, MarkerModel.Data5 var2) {
   }

   public final void method16() {
      for (DriverComponentLegacy var2 : this.field2) {
         var2.method12();
      }

      this.tick();
   }

   public void tick() {
   }

   public final void method17() {
      for (DriverComponentLegacy var2 : this.field2) {
         var2.onClose();
      }
   }

   public void method18(String var1, DriverComponentLegacy<?> var2) {
      System.out.println(var1 + String.format("C: [%s]: %s", var2.getClass().getSimpleName(), var2.toString()));

      for (DriverComponentLegacy var4 : var2.getChildren()) {
         this.method18(var1 + "    ", var4);
      }
   }

   private int method19(List<DriverComponentLegacy<?>> var1) {
      int var2 = var1.size();

      for (DriverComponentLegacy var4 : var1) {
         var2 += this.method19(var4.getChildren());
      }

      return var2;
   }

   public String getLanguagePath() {
      return "gui.components";
   }

   @Generated
   public List<DriverComponentLegacy<?>> method20() {
      return this.field2;
   }
}
