package com.moonsworth.lunar.client.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge17Extension_2;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType3_3;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuHomeScreen;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuThemeManager;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump72;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.apache.commons.io.IOUtils;
import com.moonsworth.lunar.client.framework.loading.LoadableResource;
import com.moonsworth.lunar.client.framework.loading.LoadingStage;

public class LoadingScreen {
   private final ResourceLocationBridge field1 = MainMenuHomeScreen.field22.method1();
   private final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "logo/logo-128x117.png");
   private ResourceLocationBridge field3;
   private ResourceLocationBridge field4;
   private static LoadingScreen field5;
   private final Bridge5_12 field6;
   private final Bridge8Handler2 field7;
   private CachedFontImpl field8;
   private static final List<LoadingStage> field9 = new ArrayList<>();
   private final List<LoadingStage> field10 = new ArrayList<>();
   private final List<LoadingStage> field11 = new ArrayList<>();
   private LoadingStage field12;
   private int field13;
   private long field14;
   private static final long field15 = 60L;
   private static final long field16 = Duration.ofSeconds(1L).toNanos() / 60L;
   private int width;
   private int height;
   private String field17;

   public LoadingScreen(Bridge5_12 var1, Bridge8Handler2 var2, int var3, int var4) {
      field5 = this;
      this.field6 = var1;
      this.field7 = var2;
      this.field10.addAll(field9);
      field9.clear();
      this.method13(var3, var4);
   }

   public static void method1(String var0) {
      LoadingScreen var1 = method14();
      if (var1 != null) {
         var1.method3(var1.field12);
         var1.field12 = null;
         var1.field17 = var0;
         var1.method6(var1.method12());
      }
   }

   public void method2(LoadingStage var1) {
      this.method3(this.field12);
      this.field12 = var1;
      this.method6(this.method12());
   }

   private void method3(LoadingStage var1) {
      if (var1 != null) {
         this.field11.add(var1);
         this.field13 = 0;
      }
   }

   public void method4(LoadableResource var1) {
      this.field13++;
      long var2 = System.nanoTime();
      if (this.field14 == 0L || var2 - this.field14 > field16) {
         this.field14 = var2;
         this.method6(this.method12());
      }
   }

   public static void method5(LoadingStage var0) {
      LoadingScreen var1 = method14();
      if (var1 != null) {
         var1.field10.add(var0);
      } else {
         field9.add(var0);
      }
   }

   public void method6(AbstractRenderContext var1) {
      if (this.width != this.field6.bridge$displayWidth() || this.height != this.field6.bridge$displayHeight()) {
         this.method13(this.field6.bridge$displayWidth(), this.field6.bridge$displayHeight());
      }

      var1.method19();
      float var2 = (float)this.field6.bridge$displayHeight() / this.field6.bridge$logicalHeight();
      if (var1.method38()) {
         var1.method19();
      }

      var1.push();
      if (ThreadModuleDump63.MC_VERSION < 29) {
         var1.method27(0, 0, this.width, this.height);
         var1.method8(BridgeType3_3.GL_PROJECTION);
         var1.method36();
         var1.method8(BridgeType3_3.GL_MODELVIEW);
         var1.method36();
      } else {
         ThreadModuleDump63.method3().bridge$getMainRenderTarget().bridge$framebufferClear();
      }

      if (!Bridge.getMinecraftVersion().method23()) {
         Bridge.method42().method4();
      }

      Bridge.method42().method59(0.0, this.width, this.height, 0.0, 1000.0, ThreadModuleDump63.MC_VERSION >= 29 ? 21000.0 : 3000.0);
      var1.translate(0.0, 0.0, ThreadModuleDump63.MC_VERSION >= 29 ? -11000.0 : -2000.0);
      if (this.field3 == null) {
         if (MainMenuThemeManager.method12() != null) {
            this.field3 = this.method9("lunar_background", MainMenuThemeManager.method12().method1());
         } else {
            this.field3 = this.method9("lunar_background", this.field1);
         }
      }

      if (this.field4 == null) {
         this.field4 = this.method9("lunar_logo", this.field2);
      }

      this.method8(var1, this.field3, 0.0F, 0.0F, this.width, this.height, -1);
      float var3 = 128.0F * var2;
      float var4 = 117.0F * var2;
      int var5 = (int)(this.width / 2.0F - var3 / 2.0F);
      int var6 = Math.round(this.height / 2.0F - var4 / 2.0F - 16.0F);
      this.method8(var1, this.field4, var5 + 1, var6 + 1, var3, var4, 855638016);
      this.method8(var1, this.field4, var5, var6, var3, var4, -1);
      var1.method13();
      var1.method15();
      if (this.field8 == null) {
         this.field8 = new CachedFontImpl(FontRegistry.method23(), 18.0F);
         this.field8.isLoaded();
      }

      this.method7(var1, var2);
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         Bridge.method9().bridge$getRenderBuffers().bridge$bufferSource().bridge$endBatch();
      }

      this.field6.bridge$prepareWindowSurface();
      ThreadModuleDump63.method3().bridge$getMainRenderTarget().bridge$present();
      this.field6.bridge$updateDisplay();
      var1.pop();
      if (!Bridge.getMinecraftVersion().method23()) {
         Bridge.method42().method5();
      }
   }

   private void method7(AbstractRenderContext var1, float var2) {
      if (this.field8 != null) {
         float var3 = this.height / 2.0F + 48.0F * var2;
         float var4 = 12.0F * var2;
         float var5 = 300.0F * var2;
         float var6 = var3 + 48.0F * var2;
         float var7 = this.width / 2.0F - var5 / 2.0F;
         LcuiScreen.method116(var1, var7, var6, var5, var4, var4, 1621139616);
         if (this.field12 != null) {
            float var8 = var5 / this.method10();
            var1.push();
            var1.scale(2.0F * var2, 2.0F * var2, 1.0F);
            this.field8.method6(var1, this.field12.getCategory(), this.width / 4.0F / var2, (var3 + 20.0F * var2) / 2.0F / var2, -1);
            var1.pop();
            float var9 = 0.0F;

            for (LoadingStage var11 : this.field10) {
               boolean var12 = this.field11.contains(var11);
               if (var12) {
                  var9 += var8;
               } else if (this.field12 == var11) {
                  float var13 = (float)Math.min(this.method11(var11), this.field13) / this.method11(var11);
                  var9 += var8 * var13;
               }
            }

            LcuiScreen.method116(var1, var7, var6, Math.max(5.0F, var9), var4, var4, -1342177281);
         } else {
            var1.push();
            var1.scale(2.0F * var2, 2.0F * var2, 1.0F);
            this.field8.method6(var1, this.field17, this.width / 4.0F / var2, (var3 + 20.0F * var2) / 2.0F / var2, -1);
            var1.pop();
            if (!this.field10.isEmpty() && this.field11.size() == this.field10.size()) {
               LcuiScreen.method116(var1, var7, var6, var5, var4, var4, -1342177281);
            }
         }
      }
   }

   private void method8(AbstractRenderContext var1, ResourceLocationBridge var2, float var3, float var4, float var5, float var6, int var7) {
      Bridge2_32 var8 = var1.method10(LunarRenderTypes.field33.get(var2));
      var6 = (float)Math.ceil(var6);
      var8.method1();
      var8.method5(var3, var4).method10(0.0F, 0.0F).method9(var7).method16();
      var8.method5(var3, var4 + var6).method10(0.0F, 1.0F).method9(var7).method16();
      var8.method5(var3 + var5, var4 + var6).method10(1.0F, 1.0F).method9(var7).method16();
      var8.method5(var3 + var5, var4).method10(1.0F, 0.0F).method9(var7).method16();
      var8.method17(BufferBuildMode.BATCHED);
   }

   private ResourceLocationBridge method9(String var1, ResourceLocationBridge var2) {
      InputStream var3 = null;

      try {
         var3 = this.field6.bridge$getMcDefaultResourcePack().bridge$getInputStream(var2);
         Bridge8Extension33 var4 = Bridge.method8().method22(ThreadModuleDump72.readImage(var3));
         return this.field7.bridge$getDynamicTextureLocation(var1, var4);
      } catch (IOException var9) {
         Slayer.method7("Unable to load logo: " + var2, new Object[0]);
         var9.printStackTrace();
      } finally {
         IOUtils.closeQuietly(var3);
      }

      return ResourceLocationBridge.create("missingno");
   }

   public int method10() {
      return this.field10.size();
   }

   public int method11(LoadingStage var1) {
      return var1.method1().size();
   }

   public AbstractRenderContext method12() {
      if (Bridge.getMinecraftVersion().method19()) {
         Bridge17Extension_2 var1 = Bridge.method9().bridge$getRenderBuffers().bridge$bufferSource();
         return BridgeExtension2_11.method50().method1(Bridge.method8().method61()).method3(var1).method5(Bridge.method8().method92()).method7();
      } else {
         return AbstractRenderContext.method32();
      }
   }

   private void method13(int var1, int var2) {
      this.width = var1;
      this.height = var2;
   }

   @Generated
   public static LoadingScreen method14() {
      return field5;
   }

   @Generated
   public static void method15(LoadingScreen var0) {
      field5 = var0;
   }
}
