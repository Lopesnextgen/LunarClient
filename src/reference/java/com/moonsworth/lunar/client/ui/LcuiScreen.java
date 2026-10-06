package com.moonsworth.lunar.client.ui;

import com.google.common.collect.Sets;
import com.lunarclient.apollo.common.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge3_18;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.Bridge8_2;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ShadingModel;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Bridge_65;
import com.moonsworth.lunar.bridge.TextColorSource;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.RenderTypeResolver;
import com.moonsworth.lunar.bridge.ModernGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuBackground;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.config.GeneralSettings.Type;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.render.menublur.MenuBlur;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump38;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.render.texture.ChromaTexture;

public abstract class LcuiScreen implements Bridge7_8, EventRegistrar, Calculator2 {
   public static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "icons/settings/reset-16x16.png");
   public static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "icons/stars/star-64x64.png");
   private static final AnimatedValue field3 = new AnimatedValue(1000L, -1338261932, -12861868);
   private static boolean field4;
   public static boolean field5 = false;
   public static float field6 = 2.0F;
   private static boolean field7 = false;
   private boolean field8;
   public static double z = 0.0;
   protected int width;
   protected int height;
   protected float field9;
   protected float field10;
   protected List<GuiWidget> field11 = new CopyOnWriteArrayList<>(this.method4());
   private static final List<int[]> field12 = new ArrayList<>();
   private static float field13;
   private static ThreadModuleDump71 field14;
   private static final ResourceLocationBridge field15 = ResourceLocationBridge.create("gui.button.press");
   private static final ResourceLocationBridge field16 = ResourceLocationBridge.create("ui.button.click");
   private static ResourceLocationBridge field17;
   private static long field18 = System.currentTimeMillis();

   protected abstract List<GuiWidget> method25();

   protected List<ClientOption<?>> method2() {
      return new ArrayList<>();
   }

   public String getLanguagePath() {
      return "gui";
   }

   public void method2(Bridge5_12 var1, Bridge_65 var2) {
      this.width = (int)var2.method1();
      this.height = (int)var2.method2();
      if (!ThreadModuleDump63.method4().method40().method85().method19()) {
         field14 = new ThreadModuleDump71(var1);
      }

      float var3 = getScale();
      this.field9 = (float)(field14.getScaledWidth_double() / var3);
      this.field10 = (float)(field14.getScaledHeight_double() / var3);
      this.initGui();
   }

   public void initGui() {
      this.init();
   }

   protected List<GuiWidget> method4() {
      return this.method25();
   }

   public void method1(MixinHelper_4 var1, Bridge_65 var2, float var3) {
      field5 = true;
      float var4 = getScale();
      MenuBlur var5 = Client.method109().method40().method43();
      if (!this.field8
         && var5.isEnabled()
         && (Boolean)var5.getLunarScreenData().method3().get()
         && DriverViewportLegacy.method50().method63() != DriverRouteRegistryLegacy.field4
         && DriverViewportLegacy.method50().method63() != DriverRouteRegistryLegacy.field5
         && DriverViewportLegacy.method50().method63() != DriverRouteRegistryLegacy.field10
         && DriverViewportLegacy.method50().method64() != DriverOverlayRegistryLegacy.field2) {
         var5.ensureBlurStarted();
         ColorOption var6 = var5.getLunarScreenData().method4();
         var6.IIRIOOOHIHOIIRRRHCRORHIHHIHHIO(Math.min(var6.getAlpha() / 255.0F, 0.75F));
         var6.method11(var1, 0.0F, 0.0F, field14.getScaledWidth(), field14.getScaledHeight());
      }

      MarkerModel.Data2 var10 = ((MarkerModel.Data4)var2).OCRCCHICRRIROCIHCOROROHCIRCICO();
      var1.push();
      var1.scale(var4, var4, var4);
      this.method10(var1, var10);

      for (GuiWidget var8 : this.field11) {
         if (var8.method24()
            && var8.method13()
            && !(var8 instanceof com.moonsworth.lunar.client.ui.widget.OptionWidget var9 && var9.getOption().isHidden())) {
            var8.method3(var1, var10, this.method16(var8, var10));
         }
      }

      var1.pop();
      field5 = false;
   }

   public void method3(Bridge_65 var1, int var2) {
      MarkerModel.Data2 var3 = ((MarkerModel.Data4)var1).OCRCCHICRRIROCIHCOROROHCIRCICO();

      try {
         for (GuiWidget var5 : this.field11) {
            if (var5.getShader()) {
               var5.getScreenBackgroundColor(var3, var2);
            }
         }

         Object var13 = null;
         synchronized (this.field11) {
            for (GuiWidget var7 : this.field11) {
               if (var7.method24() && var7.method1(var3) && this.method16(var7, var3) && var7.method6(var3, var2)) {
                  method15();
                  return;
               }
            }
         }

         if (var13 != null) {
            synchronized (this.field11) {
               int var16 = this.field11.indexOf(var13);
               if (var16 != -1) {
                  this.field11.add(this.field11.remove(var16));
               }
            }
         }
      } catch (Exception var12) {
         var12.printStackTrace();
         Inventorymod2.method5(var12, "LCUI: Mouse Clicked");
      }

      this.method11(var3, var2);
   }

   public void method4(Bridge_65 var1, int var2) {
      MarkerModel.Data2 var3 = ((MarkerModel.Data4)var1).OCRCCHICRRIROCIHCOROROHCIRCICO();
      this.method12(var3, var2);

      for (GuiWidget var5 : this.field11) {
         if (var5.getShader() && var5.method1(var3) && this.method16(var5, var3) && var5.applyFade(var3, var2)) {
            method15();
            break;
         }
      }
   }

   public void onGuiClosed() {
      this.close();

      for (GuiWidget var2 : this.field11) {
         var2.close();
      }
   }

   public void updateScreen() {
      this.update();

      for (GuiWidget var2 : this.field11) {
         var2.update();
      }
   }

   public void method5(char var1, KeyCode var2) {
      this.method14(var1, var2);

      for (GuiWidget var4 : this.field11) {
         if (var4.method24()) {
            var4.method4(var1, var2);
         }
      }
   }

   public void method6(int var1, Bridge_65 var2) {
      MarkerModel.Data2 var3 = ((MarkerModel.Data4)var2).OCRCCHICRRIROCIHCOROROHCIRCICO();
      this.method13(var1, var3);

      for (GuiWidget var5 : this.field11) {
         if (var5.getShader()) {
            var5.method5(var1);
         }
      }
   }

   public abstract void init();

   public abstract void update();

   public abstract void method10(MixinHelper_4 var1, MarkerModel.Data2 var2);

   public abstract void method11(MarkerModel.Data2 var1, int var2);

   public abstract void method12(MarkerModel.Data2 var1, int var2);

   public void method13(int var1, MarkerModel.Data2 var2) {
   }

   public abstract void method14(char var1, KeyCode var2);

   public abstract void close();

   public static void method15() {
      if (System.currentTimeMillis() - field18 > 50L) {
         Config var0 = Bridge.getMinecraftVersion();
         if (var0.method22()) {
            ThreadModuleDump63.method3().bridge$getSoundHandler().method1(field15);
         } else {
            ThreadModuleDump63.method3().bridge$getSoundHandler().method1(field16);
         }

         field18 = System.currentTimeMillis();
      }
   }

   public boolean method16(GuiWidget var1, MarkerModel.Data2 var2, GuiWidget... var3) {
      List var4 = Arrays.asList(var3);
      boolean var5 = true;

      for (int var6 = this.field11.size() - 1; var6 >= 0; var6--) {
         GuiWidget var7 = this.field11.get(var6);
         if (var7 == var1) {
            break;
         }

         if (!var4.contains(var7) && var7.method1(var2)) {
            var5 = false;
            break;
         }
      }

      return var5;
   }

   public static float getScale() {
      if (field14 != null && ThreadModuleDump63.method4().method37()) {
         GeneralSettings var0 = ThreadModuleDump63.method4().method41().method6();
         Type var1 = var0.method16();
         if (var1 == Type.ALL) {
            return 1.0F;
         }

         boolean var2 = var1 == Type.MODS
            && (
               ThreadModuleDump63.method11() == null
                  || ThreadModuleDump63.method11() == com.moonsworth.lunar.client.ui.hud.HudEditorScreen.class
                  || ThreadModuleDump63.MC_VERSION >= 7 && ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension612
            );
         if (var2) {
            return 1.0F;
         }

         float var3 = (float)method20();
         float var4 = field6;
         if (var0.method26().get() == Type.AUTO && field14.getScaleFactor() / var3 > 4.0F) {
            var4 *= 2.0F;
         }

         return var4 / Math.max(1, (int)(field14.getScaleFactor() / var3));
      } else {
         return 1.0F;
      }
   }

   public static int method17() {
      return method151() == null ? 2 : (int)(method151().method3() * getScale());
   }

   public static boolean method18() {
      return !field7 && ThreadModuleDump63.MC_VERSION > 5 && ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method41() != null
         ? (Boolean)ThreadModuleDump63.method4().method41().method6().method27().get()
         : false;
   }

   public static double method19() {
      return ThreadModuleDump63.method2()
            && ThreadModuleDump63.method3().bridge$displayHeight() > 0
            && ThreadModuleDump63.method3().bridge$logicalHeight() > 0
            && !field7
         ? Math.max(1, ThreadModuleDump63.method3().bridge$displayHeight() / ThreadModuleDump63.method3().bridge$logicalHeight())
         : 1.0;
   }

   public static double method20() {
      return method18() ? method19() : 1.0;
   }

   public static double method21(double var0) {
      return var0 * method20();
   }

   public float method22() {
      return this.method24(this.width, this.field9);
   }

   public float method23() {
      return this.method24(this.height, this.field10);
   }

   private float method24(int var1, float var2) {
      boolean var3 = false;
      Bridge5Extension6 var4 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      if (var4 != null) {
         if (var4 instanceof Bridge5Extension62) {
            Bridge7_8 var5 = ((Bridge5Extension62)var4).method2();
            if (var5 instanceof com.moonsworth.lunar.client.ui.hud.HudEditorScreen) {
               var3 = true;
            }
         }
      } else {
         var3 = true;
      }

      return ThreadModuleDump63.method4().method41().method6().method16() != Type.ALL
            && (ThreadModuleDump63.method4().method41().method6().method16() != Type.MODS || !var3)
         ? var2
         : var1;
   }

   public static void method25(MixinHelper_4 var0, float var1, float var2, float var3, int var4) {
      var1 -= var3 / 2.0F;
      float var5 = var1 + var3;
      float var6 = var2 + var3 / 1.5F;
      float var7 = var1;
      var0.method9(LunarRenderTypes.field19, null, var1, var2, var3, var3 / 1.5F, var6x -> {
         var6x.method5(var7 + var3 / 2.0F, var6).method9(var4).method16();
         var6x.method5(var5, var2).method9(var4).method16();
         var6x.method5(var7, var2).method9(var4).method16();
      });
   }

   public static void method26(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      float var8 = Math.min(var1, Math.min(var3, var5));
      float var9 = Math.max(var1, Math.max(var3, var5));
      float var10 = Math.min(var2, Math.min(var4, var6));
      float var11 = Math.max(var2, Math.max(var4, var6));
      var0.method9(LunarRenderTypes.field19, null, var8, var10, var9 - var8, var11 - var10, var7x -> {
         var7x.method5(var1, var2).method9(var7).method16();
         var7x.method5(var3, var4).method9(var7).method16();
         var7x.method5(var5, var6).method9(var7).method16();
      });
   }

   public static void method27(MixinHelper_4 var0, float var1, float var2, float var3, int var4) {
      float var5 = var1 - var3 / 2.0F;
      float var6 = var5 + var3;
      float var7 = var2 + var3;
      var0.method9(LunarRenderTypes.field19, null, var5, var2, var3, var3, var5x -> {
         var5x.method5(var6, var7).method9(var4).method16();
         var5x.method5(var6, var2).method9(var4).method16();
         var5x.method5(var5, var2 + (var7 - var2) / 2.0F).method9(var4).method16();
      });
   }

   public static void method28(AbstractRenderContext var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      int var7 = 1076176165;
      int var8 = 553648127;
      method55(var0, var1, var2, var3, var4, var5, var7, var8, var6);
   }

   public static void method29(AbstractRenderContext var0, @NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, int var6) {
      if (ThreadModuleDump63.MC_VERSION >= 30) {
         Bridge8_2 var7 = (Bridge8_2)var0.method30().method45().orElseThrow();
         int var8 = Math.round(var2);
         int var9 = Math.round(var3);
         int var10 = Math.round(var4);
         int var11 = Math.round(var5);
         var7.bridge$blit$v1_21_6(MixinHelper_2.field21, var1, var8, var9, var10, var11, var10, var11, var10, var11, var6);
      } else if (ThreadModuleDump63.MC_VERSION >= 26) {
         method41(
            ThreadModuleDump63.method3().bridge$getRenderBuffers().bridge$getGuiTextured(var1),
            var0,
            var2,
            var3,
            0.0F,
            0.0F,
            0.0F,
            var4,
            var5,
            var4,
            var5,
            var6
         );
      } else {
         method34(var0, var1, var2, var3, var4, var5, 0.0F, 0.0F, 1.0F, 1.0F, var6);
      }
   }

   public static void method30(AbstractRenderContext var0, @NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, int var6) {
      method34(var0, var1, var2, var3, var4, var5, 0.0F, 0.0F, 1.0F, 1.0F, var6);
   }

   public static void method31(MixinHelper_4 var0, @NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, int var6) {
      var0.method25(var1, var2, var3, 0.0F, 0.0F, var4, var5, var4, var5, var6);
   }

   public static void method32(AbstractRenderContext var0, @NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, int var6, boolean var7) {
      if (var7 && ThreadModuleDump63.MC_VERSION >= 30) {
         Bridge8_2 var8 = (Bridge8_2)var0.method30().method45().orElseThrow();
         int var9 = Math.round(var4);
         int var10 = Math.round(var5);
         var8.bridge$blit$v1_21_6(MixinHelper_2.field21, var1, Math.round(var2), Math.round(var3), 0.0F, 0.0F, var9, var10, var9, var10, var6);
      } else {
         method34(var0, var1, var2, var3, var4, var5, 0.0F, 0.0F, 1.0F, 1.0F, var6);
      }
   }

   public static void method33(
      MixinHelper_4 var0, @NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9
   ) {
      method35(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, -1);
   }

   public static void method34(
      AbstractRenderContext var0, ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10
   ) {
      method36(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, false, var10);
   }

   public static void method35(
      MixinHelper_4 var0, ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10
   ) {
      float var11 = var4 / (var8 - var6);
      float var12 = var5 / (var9 - var7);
      var0.method25(var1, var2, var3, var6 * var11, var7 * var12, var4, var5, var11, var12, var10);
   }

   public static void method36(
      AbstractRenderContext var0,
      ResourceLocationBridge var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      boolean var10,
      int var11
   ) {
      RenderTypeResolver var12 = var10 ? LunarRenderTypes.field36 : LunarRenderTypes.field33;
      Bridge2_32 var13 = var0.method10(var12.get(var1)).method1();
      var13.method2(var2, var3, z).method10(var6, var7).method9(var11).method16();
      var13.method2(var2, var3 + var5, z).method10(var6, var9).method9(var11).method16();
      var13.method2(var2 + var4, var3 + var5, z).method10(var8, var9).method9(var11).method16();
      var13.method2(var2 + var4, var3, z).method10(var8, var7).method9(var11).method16();
      var13.method17(BufferBuildMode.BATCHED);
   }

   public static void method37(
      MixinHelper_4 var0, @NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10
   ) {
      float var11 = var6 / var4;
      float var12 = var7 / var5;
      float var13 = var8 / var4;
      float var14 = var9 / var5;
      float var15 = var8 - var6;
      float var16 = var9 - var7;
      method35(var0, var1, var2, var3, var15, var16, var11, var12, var13, var14, var10);
   }

   public static void method38(AbstractRenderContext var0, ResourceLocationBridge var1, float var2, float var3, float var4, int var5) {
      method30(var0, var1, var3, var4, var2 * 2.0F, var2 * 2.0F, var5);
   }

   public static void method39(MixinHelper_4 var0, ResourceLocationBridge var1, float var2, float var3, float var4, int var5) {
      method31(var0, var1, var3, var4, var2 * 2.0F, var2 * 2.0F, var5);
   }

   public static void method40(
      AbstractRenderContext var0,
      @NotNull ResourceLocationBridge var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      int var11
   ) {
      method41(LunarRenderTypes.field33.get(var1), var0, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
   }

   public static void method41(
      RenderLayerBridge var0,
      AbstractRenderContext var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      int var11
   ) {
      float var12 = 1.0F / var9;
      float var13 = 1.0F / var10;
      var1.method10(var0)
         .method1()
         .method2(var2, var3 + var8, var4)
         .method10(var5 * var12, (var6 + var8) * var13)
         .method9(var11)
         .method16()
         .method2(var2 + var7, var3 + var8, var4)
         .method10((var5 + var7) * var12, (var6 + var8) * var13)
         .method9(var11)
         .method16()
         .method2(var2 + var7, var3, var4)
         .method10((var5 + var7) * var12, var6 * var13)
         .method9(var11)
         .method16()
         .method2(var2, var3, var4)
         .method10(var5 * var12, var6 * var13)
         .method9(var11)
         .method16()
         .method17(BufferBuildMode.BATCHED);
   }

   public static void method42(
      RenderLayerBridge var0,
      ResourceLocationBridge var1,
      MixinHelper_4 var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      int var12
   ) {
      float var13 = 1.0F / var10;
      float var14 = 1.0F / var11;
      var2.method9(var0, var1, var3, var4, var8, var9, var10x -> {
         var10x.method2(var3, var4 + var9, var5).method10(var6 * var13, (var7 + var9) * var14).method9(var12).method16();
         var10x.method2(var3 + var8, var4 + var9, var5).method10((var6 + var8) * var13, (var7 + var9) * var14).method9(var12).method16();
         var10x.method2(var3 + var8, var4, var5).method10((var6 + var8) * var13, var7 * var14).method9(var12).method16();
         var10x.method2(var3, var4, var5).method10(var6 * var13, var7 * var14).method9(var12).method16();
      });
   }

   public static void method43(
      AbstractRenderContext var0,
      @NotNull ResourceLocationBridge var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      int var10,
      int var11,
      int var12,
      int var13
   ) {
      float var14 = 1.0F / var8;
      float var15 = 1.0F / var9;
      var0.method10(LunarRenderTypes.field33.get(var1))
         .method1()
         .method5(var2, var3 + var7)
         .method10(var4 * var14, (var5 + var7) * var15)
         .method9(var10)
         .method16()
         .method5(var2 + var6, var3 + var7)
         .method10((var4 + var6) * var14, (var5 + var7) * var15)
         .method9(var11)
         .method16()
         .method5(var2 + var6, var3)
         .method10((var4 + var6) * var14, var5 * var15)
         .method9(var12)
         .method16()
         .method5(var2, var3)
         .method10(var4 * var14, var5 * var15)
         .method9(var13)
         .method16()
         .method17(BufferBuildMode.BATCHED);
   }

   public static void method44(
      MixinHelper_4 var0,
      @NotNull ResourceLocationBridge var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      int var10,
      int var11,
      int var12,
      int var13
   ) {
      float var14 = 1.0F / var8;
      float var15 = 1.0F / var9;
      var0.method9(LunarRenderTypes.field33.get(var1), var1, var2, var3, var6, var7, var12x -> {
         var12x.method5(var2, var3 + var7).method10(var4 * var14, (var5 + var7) * var15).method9(var10).method16();
         var12x.method5(var2 + var6, var3 + var7).method10((var4 + var6) * var14, (var5 + var7) * var15).method9(var11).method16();
         var12x.method5(var2 + var6, var3).method10((var4 + var6) * var14, var5 * var15).method9(var12).method16();
         var12x.method5(var2, var3).method10(var4 * var14, var5 * var15).method9(var13).method16();
      });
   }

   public static void method45(
      AbstractRenderContext var0,
      @NotNull ResourceLocationBridge var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      int var10
   ) {
      method40(var0, var1, var2, var3, 0.0F, var4, var5, var6, var7, var8, var9, var10);
   }

   public static void method46(
      MixinHelper_4 var0, @NotNull ResourceLocationBridge var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10
   ) {
      var0.method25(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   public static void method47(MixinHelper_4 var0, @NotNull ResourceLocationBridge var1, float var2, float var3, int var4, int var5, int var6, int var7, int var8) {
      var0.method25(var1, var2, var3, var4, var5, var6, var7, 256.0F, 256.0F, var8);
   }

   public static void method48(MixinHelper_4 var0, @NotNull ResourceLocationBridge var1, float var2, float var3, int var4, Icon var5, int var6) {
      if (var5 instanceof AdvancedResourceLocationIcon var7) {
         if (!var1.bridge$getPath().endsWith(".svg")) {
            method35(
               var0,
               var1,
               var2 + (var4 - var7.getWidth()) / 2.0F,
               var3 + (var4 - var7.getHeight()) / 2.0F,
               var7.getWidth(),
               var7.getHeight(),
               var7.getMinU(),
               var7.getMinV(),
               var7.getMaxU(),
               var7.getMaxV(),
               var6
            );
         }
      } else if (var5 instanceof SimpleResourceLocationIcon var8 && !var1.bridge$getPath().endsWith(".svg")) {
         float var9 = var8.getSize();
         method31(var0, var1, var2 + (var4 - var9) / 2.0F, var3 + (var4 - var9) / 2.0F, var9, var9, var6);
      }
   }

   public static void method49(MixinHelper_4 var0, @Nullable ResourceLocationBridge var1, float var2, float var3, int var4) {
      if (var1 != null) {
         method50(var0, var1, var2, var3, var4, true);
      }
   }

   public static void method50(MixinHelper_4 var0, @NotNull ResourceLocationBridge var1, float var2, float var3, int var4, boolean var5) {
      var0.method25(var1, (int)var2, (int)var3, 8.0F, 8.0F, 8.0F, 8.0F, 64.0F, 64.0F, var4);
      if (var5) {
         var0.method25(var1, (int)var2, (int)var3, 40.0F, 8.0F, 8.0F, 8.0F, 64.0F, 64.0F, var4);
      }
   }

   public static void method51(
      MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6, boolean var7, boolean var8, boolean var9, boolean var10
   ) {
      double var11 = var5 - 1.0F;
      byte var13 = 4;
      byte var14 = 0;
      byte var15 = 10;
      if (var10) {
         method61(var0, var1 + var3 - var5 + 1.0F, var2 + var4 - var5 + 1.0F, var5, var11, 0.0, var13, var14, var6, var15);
      } else {
         method68(var0, var1 + var3 - var5 + 1.0F, var1 + var3, var2 + var4, var6);
         method87(var0, var1 + var3, var2 + var4 - var5, var2 + var4, var6);
      }

      if (var9) {
         method61(var0, var1 + var5 - 1.0F, var2 + var4 - var5 + 1.0F, var5, var11, 1.0, var13, var14, var6, var15);
      } else {
         method87(var0, var1 - 1.0F, var2 + var4 - var5, var2 + var4, var6);
         method68(var0, var1 - 1.0F, var1 + var5 - 2.0F, var2 + var4, var6);
      }

      if (var7) {
         method61(var0, var1 + var5 - 1.0F, var2 + var5 - 1.0F, var5, var11, 2.0, var13, var14, var6, var15);
      } else {
         method68(var0, var1 - 1.0F, var1 + var5 - 2.0F, var2 - 1.0F, var6);
         method87(var0, var1 - 1.0F, var2 - 1.0F, var2 + var5 - 1.0F, var6);
      }

      if (var8) {
         method61(var0, var1 + var3 - var5 + 1.0F, var2 + var5 - 1.0F, var5, var11, 3.0, var13, var14, var6, var15);
      } else {
         method68(var0, var1 + var3 - var5 + 1.0F, var1 + var3 - 1.0F, var2 - 1.0F, var6);
         method87(var0, var1 + var3, var2 - 2.0F, var2 + var5 - 1.0F, var6);
      }

      method68(var0, var1 + var5 - 1.0F, var1 + var3 - var5, var2 + var4, var6);
      method68(var0, var1 + var5 - 1.0F, var1 + var3 - var5, var2 - 1.0F, var6);
      method87(var0, var1 - 1.0F, var2 + var5 - 2.0F, var2 + var4 - var5 + 1.0F, var6);
      method87(var0, var1 + var3, var2 + var5 - 2.0F, var2 + var4 - var5 + 1.0F, var6);
   }

   public static void method52(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      var5++;
      double var7 = var5 - 1.0F;
      byte var9 = 4;
      byte var10 = 0;
      method61(var0, var1 + var3 - var5 + 1.0F, var2 + var4 - var5 + 1.0F, var5, var7, 0.0, var9, var10, var6, var9 * 90);
      method61(var0, var1 + var5 - 1.0F, var2 + var4 - var5 + 1.0F, var5, var7, 1.0, var9, var10, var6, var9 * 90);
      method61(var0, var1 + var5 - 1.0F, var2 + var5 - 1.0F, var5, var7, 2.0, var9, var10, var6, var9 * 90);
      method61(var0, var1 + var3 - var5 + 1.0F, var2 + var5 - 1.0F, var5, var7, 3.0, var9, var10, var6, var9 * 90);

      for (int var11 = 0; var11 < (int)((var3 - var5) / 10.0F) - 1; var11++) {
         float var12 = var1 + var5 - 1.0F + var11 * 10;
         method68(var0, var12 + 5.0F, var12 + 10.0F, var2 + var4, var6);
         method68(var0, var12 + 5.0F, var12 + 10.0F, var2 - 1.0F, var6);
      }

      for (int var14 = 0; var14 < (int)((var4 - var5) / 10.0F); var14++) {
         float var15 = var2 + var5 - 1.0F + var14 * 10;
         method87(var0, var1 - 1.0F, var15 + 5.0F, var15 + 10.0F, var6);
         method87(var0, var1 + var3, var15 + 5.0F, var15 + 10.0F, var6);
      }
   }

   public static void method53(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6, int var7, int var8) {
      method117(var0, var1, var2, var3, var4, ++var5, var8);
      method56(var0, var1, var2, var3, var4, var5 - 1.0F, var6);
      method56(var0, var1 + 1.0F, var2 + 1.0F, var3 - 2.0F, var4 - 2.0F, var5 - 2.25F, var7);
   }

   public static void method54(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      int var7 = 1076176165;
      int var8 = 553648127;
      method53(var0, var1, var2, var3, var4, var5, var7, var8, var6);
   }

   public static void method55(AbstractRenderContext var0, float var1, float var2, float var3, float var4, float var5, int var6, int var7, int var8) {
      method116(var0, var1, var2, var3, var4, ++var5, var8);
      method57(var0, var1, var2, var3, var4, var5 - 1.0F, var6);
      method57(var0, var1 + 1.0F, var2 + 1.0F, var3 - 2.0F, var4 - 2.0F, var5 - 2.25F, var7);
   }

   public static void method56(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      method58(var0, var1, var2, var3, var4, 1.0F, var5, var6);
   }

   public static void method57(AbstractRenderContext var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      method59(var0, var1, var2, var3, var4, 1.0F, var5, var6);
   }

   public static void method58(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      var0.method9(LunarRenderTypes.field29, null, var1, var2, var3, var4, var7x -> {
         float var8 = var6 - var5;
         method60(var7x, var1 + var3 - var6 + 1.0F, var2 + var6 - 1.0F, var6, var8, 0.0, 0.25, var7);
         method60(var7x, var1 + var3 - var6 + 1.0F, var2 + var4 - var6 + 1.0F, var6, var8, 0.25, 0.5, var7);
         method60(var7x, var1 + var6 - 1.0F, var2 + var4 - var6 + 1.0F, var6, var8, 0.5, 0.75, var7);
         method60(var7x, var1 + var6 - 1.0F, var2 + var6 - 1.0F, var6, var8, 0.75, 1.0, var7);
         method96(var7x, var1 + var6 - 1.0F, var2 - 1.0F, var3 - var6 * 2.0F + 2.0F, 1.0F, var7);
         method96(var7x, var1 + var3, var2 + var6 - 1.0F, 1.0F, var4 - var6 * 2.0F + 2.0F, var7);
         method96(var7x, var1 + var6 - 1.0F, var2 + var4, var3 - var6 * 2.0F + 2.0F, 1.0F, var7);
         method96(var7x, var1 - 1.0F, var2 + var6 - 1.0F, 1.0F, var4 - var6 * 2.0F + 2.0F, var7);
      });
   }

   public static void method59(AbstractRenderContext var0, float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      double var8 = var6 - var5;
      byte var10 = 4;
      byte var11 = 0;
      byte var12 = 10;
      method62(var0, var1 + var3 - var6 + 1.0F, var2 + var4 - var6 + 1.0F, var6, var8, 0.0, var10, var11, var7, var12);
      method62(var0, var1 + var6 - 1.0F, var2 + var4 - var6 + 1.0F, var6, var8, 1.0, var10, var11, var7, var12);
      method62(var0, var1 + var6 - 1.0F, var2 + var6 - 1.0F, var6, var8, 2.0, var10, var11, var7, var12);
      method62(var0, var1 + var3 - var6 + 1.0F, var2 + var6 - 1.0F, var6, var8, 3.0, var10, var11, var7, var12);
      method69(var0, var1 + var6 - 1.0F, var1 + var3 - var6, var2 + var4, var7);
      method69(var0, var1 + var6 - 1.0F, var1 + var3 - var6, var2 - 1.0F, var7);
      method88(var0, var1 - 1.0F, var2 + var6 - 2.0F, var2 + var4 - var6 + 1.0F, var7);
      method88(var0, var1 + var3, var2 + var6 - 2.0F, var2 + var4 - var6 + 1.0F, var7);
   }

   public static void method60(Bridge2_32 var0, double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
      byte var14 = 64;
      double var15 = (Math.PI * 2) / var14;
      int var17 = (int)Math.round(var14 * ThreadModuleDump67.method11(var9, 0.0, 1.0));
      int var18 = (int)Math.round(var14 * ThreadModuleDump67.method11(var11, 0.0, 1.0));
      double var19 = -Math.PI / 2;
      double var21 = ThreadModuleDump38.method1(var19 + var17 * var15);
      double var23 = ThreadModuleDump38.sin(var19 + var17 * var15);

      for (int var25 = var17; var25 < var18; var25++) {
         double var26 = (var25 + 1) * var15;
         double var28 = ThreadModuleDump38.method1(var19 + var26);
         double var30 = ThreadModuleDump38.sin(var19 + var26);
         var0.method2(var1 + var21 * var7, var3 + var23 * var7, 0.0).method9(var13).method16();
         var0.method2(var1 + var28 * var7, var3 + var30 * var7, 0.0).method9(var13).method16();
         var0.method2(var1 + var28 * var5, var3 + var30 * var5, 0.0).method9(var13).method16();
         var0.method2(var1 + var21 * var5, var3 + var23 * var5, 0.0).method9(var13).method16();
         var21 = var28;
         var23 = var30;
      }
   }

   public static void method61(
      MixinHelper_4 var0, double var1, double var3, double var5, double var7, double var9, double var11, double var13, int var15, int var16
   ) {
      double var17 = var0.method50() ? z : 0.0;
      Consumer var19 = var18 -> {
         double var19x = (var9 + var11) % var11;
         double var21 = 360.0 / var11 * (var19x + var13);
         double var23 = 360.0 / var11 * (var19x + 1.0 - var13);
         double var25 = -var21 * Math.PI / 180.0;
         double var27 = ThreadModuleDump38.sin(var25);
         double var29 = ThreadModuleDump38.method1(var25);

         for (int var31 = 0; var31 < var16; var31++) {
            double var32 = var21 + (var23 - var21) * ((double)(var31 + 1) / var16);
            double var34 = -var32 * Math.PI / 180.0;
            double var36 = ThreadModuleDump38.sin(var34);
            double var38 = ThreadModuleDump38.method1(var34);
            double var40 = var29 * var7;
            double var42 = -var27 * var7;
            double var44 = var38 * var7;
            double var46 = -var36 * var7;
            double var48 = var29 * var5;
            double var50 = -var27 * var5;
            double var52 = var38 * var5;
            double var54 = -var36 * var5;
            var18.method2(var1 + var40, var3 + var42, var17).method9(var15).method16();
            var18.method2(var1 + var44, var3 + var46, var17).method9(var15).method16();
            var18.method2(var1 + var48, var3 + var50, var17).method9(var15).method16();
            var18.method2(var1 + var44, var3 + var46, var17).method9(var15).method16();
            var18.method2(var1 + var52, var3 + var54, var17).method9(var15).method16();
            var18.method2(var1 + var48, var3 + var50, var17).method9(var15).method16();
            var27 = var36;
            var29 = var38;
         }
      };
      var0.method9(LunarRenderTypes.field19, null, var1 - var5, var3 - var5, var5 * 2.0, var5 * 2.0, var19);
   }

   public static void method62(
      AbstractRenderContext var0, double var1, double var3, double var5, double var7, double var9, double var11, double var13, int var15, int var16
   ) {
      Bridge2_32 var17 = var0.method10(LunarRenderTypes.field19).method1();
      var9 = (var9 + var11) % var11;
      double var18 = 360.0 / var11 * (var9 + var13);
      double var20 = 360.0 / var11 * (var9 + 1.0 - var13);

      for (int var22 = 0; var22 < var16; var22++) {
         double var23 = var18 + (var20 - var18) * ((double)var22 / var16);
         double var25 = var18 + (var20 - var18) * ((double)(var22 + 1) / var16);
         double var27 = -var23 * Math.PI / 180.0;
         double var29 = -var25 * Math.PI / 180.0;
         double var31 = ThreadModuleDump38.method1(var27) * var7;
         double var33 = -ThreadModuleDump38.sin(var27) * var7;
         double var35 = ThreadModuleDump38.method1(var29) * var7;
         double var37 = -ThreadModuleDump38.sin(var29) * var7;
         double var39 = ThreadModuleDump38.method1(var27) * var5;
         double var41 = -ThreadModuleDump38.sin(var27) * var5;
         double var43 = ThreadModuleDump38.method1(var29) * var5;
         double var45 = -ThreadModuleDump38.sin(var29) * var5;
         var17.method2(var1 + var31, var3 + var33, z).method9(var15).method16();
         var17.method2(var1 + var35, var3 + var37, z).method9(var15).method16();
         var17.method2(var1 + var39, var3 + var41, z).method9(var15).method16();
         var17.method2(var1 + var35, var3 + var37, z).method9(var15).method16();
         var17.method2(var1 + var43, var3 + var45, z).method9(var15).method16();
         var17.method2(var1 + var39, var3 + var41, z).method9(var15).method16();
      }

      var17.method17(BufferBuildMode.BATCHED);
   }

   public static void method63(MixinHelper_4 var0, double var1, double var3, double var5, double var7, double var9, int var11, double var12, int var14) {
      Consumer var15 = var14x -> {
         double var15x = (var9 + var11) % var11;

         for (double var17 = 360.0 / var11 * var15x; var17 < 360.0 / var11 * (var15x + var12); var17++) {
            double var19 = var17 * Math.PI / 180.0;
            double var21 = (var17 - 1.0) * Math.PI / 180.0;
            double var23 = ThreadModuleDump38.method1(var19) * var5;
            double var25 = -ThreadModuleDump38.sin(var19) * var5;
            double var27 = ThreadModuleDump38.method1(var21) * var5;
            double var29 = -ThreadModuleDump38.sin(var21) * var5;
            double var31 = ThreadModuleDump38.method1(var19) * var7;
            double var33 = -ThreadModuleDump38.sin(var19) * var7;
            double var35 = ThreadModuleDump38.method1(var21) * var7;
            double var37 = -ThreadModuleDump38.sin(var21) * var7;
            var14x.method2(var1 + var31, var3 + var33, 0.0).method9(var14).method16();
            var14x.method2(var1 + var35, var3 + var37, 0.0).method9(var14).method16();
            var14x.method2(var1 + var27, var3 + var29, 0.0).method9(var14).method16();
            var14x.method2(var1 + var23, var3 + var25, 0.0).method9(var14).method16();
         }
      };
      var0.method9(LunarRenderTypes.field29, null, var1 - var5, var3 - var5, var5 * 2.0, var5 * 2.0, var15);
   }

   public static void method64(MixinHelper_4 var0, float var1, float var2, float var3, double[] var4, int[] var5) {
      method65(var0, var1, var2, var3, var4, var5, null);
   }

   public static void method65(MixinHelper_4 var0, float var1, float var2, float var3, double[] var4, int[] var5, Integer var6) {
      if (var4.length != 0) {
         var0.method9(LunarRenderTypes.field19, null, var1 - var3, var2 - var3, var3 * 2.0F, var3 * 2.0F, var6x -> {
            double var7 = 0.0;

            for (int var9 = 0; var9 < var4.length; var9++) {
               double var10 = var7 + (Math.PI * 2) * var4[var9] / 100.0;
               if (var9 == var4.length - 1) {
                  var10 = Math.PI * 2;
               }

               int var12 = var5[var9 % var5.length];
               if (var6 != null && var9 == 0) {
                  var12 = var6;
               }

               var12 = ThreadModuleDump23.method22(var12, 255);
               int var13 = ThreadModuleDump23.method22(var12, 0);
               int var14 = Math.max(1, (int)(var4[var9] / 100.0 * 90.0));

               for (int var15 = 0; var15 < var14; var15++) {
                  double var16 = -(var7 + (var10 - var7) * ((double)var15 / var14));
                  double var18 = -(var7 + (var10 - var7) * ((double)(var15 + 1) / var14));
                  var6x.method2(var1, var2, z).method9(var12).method16();
                  var6x.method2(var1 + ThreadModuleDump38.method1(var18) * var3, var2 - ThreadModuleDump38.sin(var18) * var3, z).method9(var12).method16();
                  var6x.method2(var1 + ThreadModuleDump38.method1(var16) * var3, var2 - ThreadModuleDump38.sin(var16) * var3, z).method9(var12).method16();
                  float var20 = var3 + 0.5F;
                  float var21 = var3 - 0.05F;
                  float var22 = (float)(var1 + ThreadModuleDump38.method1(var16) * var21);
                  float var23 = (float)(var2 - ThreadModuleDump38.sin(var16) * var21);
                  float var24 = (float)(var1 + ThreadModuleDump38.method1(var16) * var20);
                  float var25 = (float)(var2 - ThreadModuleDump38.sin(var16) * var20);
                  float var26 = (float)(var1 + ThreadModuleDump38.method1(var18) * var21);
                  float var27 = (float)(var2 - ThreadModuleDump38.sin(var18) * var21);
                  float var28 = (float)(var1 + ThreadModuleDump38.method1(var18) * var20);
                  float var29 = (float)(var2 - ThreadModuleDump38.sin(var18) * var20);
                  var6x.method2(var22, var23, z).method9(var12).method16();
                  var6x.method2(var26, var27, z).method9(var12).method16();
                  var6x.method2(var28, var29, z).method9(var13).method16();
                  var6x.method2(var22, var23, z).method9(var12).method16();
                  var6x.method2(var28, var29, z).method9(var13).method16();
                  var6x.method2(var24, var25, z).method9(var13).method16();
               }

               var7 = var10;
            }
         });
      }
   }

   public static void method66(MixinHelper_4 var0, float var1, float var2, float var3, float var4, int var5) {
      if (var1 < var3) {
         float var6 = var1;
         var1 = var3;
         var3 = var6;
      }

      if (var2 < var4) {
         float var7 = var2;
         var2 = var4;
         var4 = var7;
      }

      var0.method2(var1, var2, var3, var4, var5);
   }

   public static void method67(AbstractRenderContext var0, float var1, float var2, float var3, float var4, int var5) {
      if (var1 < var3) {
         float var6 = var1;
         var1 = var3;
         var3 = var6;
      }

      if (var2 < var4) {
         float var7 = var2;
         var2 = var4;
         var4 = var7;
      }

      var0.method10(LunarRenderTypes.field19)
         .method1()
         .method2(var1, var4, z)
         .method9(var5)
         .method16()
         .method2(var3, var4, z)
         .method9(var5)
         .method16()
         .method2(var1, var2, z)
         .method9(var5)
         .method16()
         .method2(var3, var4, z)
         .method9(var5)
         .method16()
         .method2(var3, var2, z)
         .method9(var5)
         .method16()
         .method2(var1, var2, z)
         .method9(var5)
         .method16()
         .method17(BufferBuildMode.BATCHED);
   }

   public static void method68(MixinHelper_4 var0, float var1, float var2, float var3, int var4) {
      if (var2 < var1) {
         float var5 = var1;
         var1 = var2;
         var2 = var5;
      }

      method66(var0, var1, var3, var2 + 1.0F, var3 + 1.0F, var4);
   }

   public static void method69(AbstractRenderContext var0, float var1, float var2, float var3, int var4) {
      if (var2 < var1) {
         float var5 = var1;
         var1 = var2;
         var2 = var5;
      }

      method67(var0, var1, var3, var2 + 1.0F, var3 + 1.0F, var4);
   }

   public static void method70(MixinHelper_4 var0, GuiWidget var1) {
      method71(var0, var1, -1);
   }

   public static void method71(MixinHelper_4 var0, GuiWidget var1, int var2) {
      method72(var0, var1.getX() + var1.getWidth() - 1.25F, var1.getY() + 1.25F, var2);
   }

   public static void method72(MixinHelper_4 var0, double var1, double var3, int var5) {
      method73(var0, var1, var3, 2.5, var5);
   }

   public static void method73(MixinHelper_4 var0, double var1, double var3, double var5, int var7) {
      if (field4 && field3.method6()) {
         field4 = false;
      } else if (!field4 && field3.method6()) {
         field4 = true;
      }

      int var8 = ThreadModuleDump23.method27(field3.method2(field4), var7);
      method78(var0, var1, var3, var5, var8);
   }

   public static void method74(MixinHelper_4 var0, double var1, double var3, double var5, double var7) {
      double var9 = var1 + var5;
      double var11 = var3 + var7;
      var0.method9(LunarRenderTypes.field21, null, var1, var3, var5, var7, var8 -> {
         var8.method2(var1, var11, 0.0).method9(-1).method16();
         var8.method2(var9, var11, 0.0).method9(-1).method16();
         var8.method2(var1, var3, 0.0).method9(-1).method16();
         var8.method2(var9, var11, 0.0).method9(-1).method16();
         var8.method2(var9, var3, 0.0).method9(-1).method16();
         var8.method2(var1, var3, 0.0).method9(-1).method16();
      });
   }

   public static void method75(MixinHelper_4 var0, double var1, double var3, double var5, double var7, int var9) {
      var0.push();
      var0.method38((float)var1, (float)var3, 0.0F);
      var0.method9(LunarRenderTypes.field19, null, var1 - var5, var3 - var5, var5 * 2.0, var5 * 2.0, var5x -> {
         byte var6 = 64;
         double var7x = (Math.PI * 2) / var6;
         double var9x = ThreadModuleDump38.method1(0.0);
         double var11 = ThreadModuleDump38.sin(0.0);
         double var13 = var5;
         double var15 = var7;

         for (int var17 = 0; var17 < var6; var17++) {
            double var18 = (var17 + 1) * var7x;
            double var20 = ThreadModuleDump38.method1(-var18);
            double var22 = ThreadModuleDump38.sin(-var18);
            var5x.method2(var9x * var15, var11 * var15, 0.0).method9(var9).method16();
            var5x.method2(var9x * var13, var11 * var13, 0.0).method9(var9).method16();
            var5x.method2(var20 * var13, var22 * var13, 0.0).method9(var9).method16();
            var5x.method2(var20 * var13, var22 * var13, 0.0).method9(var9).method16();
            var5x.method2(var20 * var15, var22 * var15, 0.0).method9(var9).method16();
            var5x.method2(var9x * var15, var11 * var15, 0.0).method9(var9).method16();
            var9x = var20;
            var11 = var22;
         }
      });
      var0.pop();
   }

   public static void method76(MixinHelper_4 var0, double var1, double var3, double var5, int var7) {
      var0.push();
      var0.method38((float)var1, (float)var3, 0.0F);
      var0.method9(LunarRenderTypes.field21, null, var1 - var5, var3 - var5, var5 * 2.0, var5 * 2.0, var3x -> {
         byte var4 = 64;
         double var5x = (Math.PI * 2) / var4;
         double var7x = ThreadModuleDump38.method1(0.0);
         double var9 = ThreadModuleDump38.sin(0.0);
         double var11 = var5 / Math.max(Math.abs(var7x), Math.abs(var9)) + 0.5;

         for (int var13 = 0; var13 < var4; var13++) {
            double var14 = (var13 + 1) * var5x;
            double var16 = var5 / Math.max(Math.abs(ThreadModuleDump38.method1(var14)), Math.abs(ThreadModuleDump38.sin(var14))) + 0.5;
            double var18 = ThreadModuleDump38.method1(-var14);
            double var20 = ThreadModuleDump38.sin(-var14);
            var3x.method2(var7x * var5, var9 * var5, 0.0).method9(var7).method16();
            var3x.method2(var7x * var11, var9 * var11, 0.0).method9(var7).method16();
            var3x.method2(var18 * var16, var20 * var16, 0.0).method9(var7).method16();
            var3x.method2(var18 * var16, var20 * var16, 0.0).method9(var7).method16();
            var3x.method2(var18 * var5, var20 * var5, 0.0).method9(var7).method16();
            var3x.method2(var7x * var5, var9 * var5, 0.0).method9(var7).method16();
            var7x = var18;
            var9 = var20;
            var11 = var16;
         }
      });
      var0.pop();
   }

   public static void method77(AbstractRenderContext var0, double var1, double var3, double var5, int var7) {
      method78(new LegacyGuiGraphicsBridge(var0), var1, var3, var5, var7);
   }

   public static void method78(MixinHelper_4 var0, double var1, double var3, double var5, int var7) {
      var0.push();
      var0.method38(0.0F, 0.0F, 10.0F);
      method79(var0, var1, var3, var5, 1.0, var7);
      var0.pop();
   }

   public static void method79(MixinHelper_4 var0, double var1, double var3, double var5, double var7, int var9) {
      method80(var0, var1, var3, var5, 0.0, var7, var9);
   }

   public static void method80(MixinHelper_4 var0, double var1, double var3, double var5, double var7, double var9, int var11) {
      var0.method9(
         LunarRenderTypes.field19, null, var1 - var5, var3 - var5, var5 * 2.0, var5 * 2.0, var11x -> method81(var11x, var1, var3, var5, var7, var9, var11)
      );
   }

   public static void method81(Bridge2_32 var0, double var1, double var3, double var5, double var7, double var9, int var11) {
      byte var12 = 64;
      double var13 = (Math.PI * 2) / var12;
      int var15 = (int)Math.round(var12 * ThreadModuleDump67.method11(var7, 0.0, 1.0));
      int var16 = (int)Math.round(var12 * ThreadModuleDump67.method11(var9, 0.0, 1.0));
      double var17 = -Math.PI / 2;
      double var19 = ThreadModuleDump38.method1(var17 + var15 * var13) * var5;
      double var21 = ThreadModuleDump38.sin(var17 + var15 * var13) * var5;

      for (int var23 = var15; var23 < var16; var23++) {
         double var24 = (var23 + 1) * var13;
         double var26 = ThreadModuleDump38.method1(var17 + var24) * var5;
         double var28 = ThreadModuleDump38.sin(var17 + var24) * var5;
         var0.method2(var1, var3, 0.0).method9(var11).method16();
         var0.method2(var1 + var26, var3 + var28, 0.0).method9(var11).method16();
         var0.method2(var1 + var19, var3 + var21, 0.0).method9(var11).method16();
         var19 = var26;
         var21 = var28;
      }
   }

   public static void method82(MixinHelper_4 var0, List<String> var1, int var2, int var3) {
      method85(var0, var1, var2 - 8, var3 + 16);
   }

   public static void method83(MixinHelper_4 var0, List<Component> var1, int var2, int var3) {
      method84(var0, var1, var2 - 8, var3 + 16);
   }

   public static void method84(MixinHelper_4 var0, List<Component> var1, int var2, int var3) {
      int var4 = 0;

      for (Component var6 : var1) {
         float var7 = ThreadModuleDump63.method10().bridge$getStringWidth(var6);
         if (var7 > var4) {
            var4 = (int)var7;
         }
      }

      int var10 = var2 + 12;
      int var11 = var3 - 12;
      int var12 = 8;
      if (var1.size() > 1) {
         var12 += 2 + (var1.size() - 1) * 10;
      }

      method86(var0, var10, var11, var4, var12);
      var0.method38(0.0F, 0.0F, 201.0F);

      for (int var8 = 0; var8 < var1.size(); var8++) {
         Component var9 = (Component)var1.get(var8);
         var0.method10(ThreadModuleDump63.method10(), var9, var10, var11, -1, true);
         if (var8 == 0) {
            var11 += 2;
         }

         var11 += 10;
      }

      var0.method38(0.0F, 0.0F, -201.0F);
   }

   public static void method85(MixinHelper_4 var0, List<String> var1, int var2, int var3) {
      int var4 = 0;

      for (String var6 : var1) {
         float var7 = ThreadModuleDump63.method10().bridge$getStringWidth(var6);
         if (var7 > var4) {
            var4 = (int)var7;
         }
      }

      int var10 = var2 + 12;
      int var11 = var3 - 12;
      int var12 = 8;
      if (var1.size() > 1) {
         var12 += 2 + (var1.size() - 1) * 10;
      }

      method86(var0, var10, var11, var4, var12);
      var0.method38(0.0F, 0.0F, 201.0F);

      for (int var8 = 0; var8 < var1.size(); var8++) {
         String var9 = (String)var1.get(var8);
         var0.method18(ThreadModuleDump63.method10(), var9, var10, var11, -1, true);
         if (var8 == 0) {
            var11 += 2;
         }

         var11 += 10;
      }

      var0.method38(0.0F, 0.0F, -201.0F);
   }

   private static void method86(MixinHelper_4 var0, int var1, int var2, int var3, int var4) {
      int var5 = -267386864;
      var0.method5(var1 - 3, var2 - 4, var1 + var3 + 3, var2 - 3, var5, var5);
      var0.method5(var1 - 3, var2 + var4 + 3, var1 + var3 + 3, var2 + var4 + 4, var5, var5);
      var0.method5(var1 - 3, var2 - 3, var1 + var3 + 3, var2 + var4 + 3, var5, var5);
      var0.method5(var1 - 4, var2 - 3, var1 - 3, var2 + var4 + 3, var5, var5);
      var0.method5(var1 + var3 + 3, var2 - 3, var1 + var3 + 4, var2 + var4 + 3, var5, var5);
      int var6 = 1347420415;
      int var7 = (var6 & 16711422) >> 1 | var6 & 0xFF000000;
      var0.method5(var1 - 3, var2 - 3 + 1, var1 - 3 + 1, var2 + var4 + 3 - 1, var6, var7);
      var0.method5(var1 + var3 + 2, var2 - 3 + 1, var1 + var3 + 3, var2 + var4 + 3 - 1, var6, var7);
      var0.method5(var1 - 3, var2 - 3, var1 + var3 + 3, var2 - 3 + 1, var6, var6);
      var0.method5(var1 - 3, var2 + var4 + 2, var1 + var3 + 3, var2 + var4 + 3, var7, var7);
   }

   public static void method87(MixinHelper_4 var0, float var1, float var2, float var3, int var4) {
      if (var3 < var2) {
         float var5 = var2;
         var2 = var3;
         var3 = var5;
      }

      method66(var0, var1, var2 + 1.0F, var1 + 1.0F, var3, var4);
   }

   public static void method88(AbstractRenderContext var0, float var1, float var2, float var3, int var4) {
      if (var3 < var2) {
         float var5 = var2;
         var2 = var3;
         var3 = var5;
      }

      method67(var0, var1, var2 + 1.0F, var1 + 1.0F, var3, var4);
   }

   public static void method89(AbstractRenderContext var0) {
      var0.method9(ShadingModel.GL_SMOOTH);
      var0.method33();
   }

   public static void method90(AbstractRenderContext var0) {
      var0.method9(ShadingModel.GL_FLAT);
   }

   public static void method91(MixinHelper_4 var0) {
      var0.method44(var0x -> {
         var0x.method29().method9(ShadingModel.GL_SMOOTH);
         var0x.method29().method33();
      });
   }

   public static void method92(MixinHelper_4 var0) {
      var0.method44(var0x -> var0x.method29().method9(ShadingModel.GL_FLAT));
   }

   public static void method93(AbstractRenderContext var0, float var1, float var2, float var3, float var4, int var5, boolean var6) {
      if (!var6) {
         method97(var0, var1, var2, var3, var4, var5);
      } else if (ThreadModuleDump63.MC_VERSION >= 17) {
         Bridge8_2 var7 = (Bridge8_2)var0.method30().method45().orElseThrow();
         int var8 = Math.round(var1);
         int var9 = var8 + Math.round(var3);
         int var10 = Math.round(var2);
         int var11 = var10 + Math.round(var4);
         var7.bridge$fill$v1_20_0(var8, var10, var9, var11, var5);
      } else {
         throw new RuntimeException("Gui graphics not supported on versions below 1.20");
      }
   }

   public static void method94(MixinHelper_4 var0, float var1, float var2, float var3, float var4, int var5) {
      var0.method2(var1, var2, var1 + var3, var2 + var4, var5);
   }

   public static void method95(Bridge2_32 var0, float var1, float var2, float var3, float var4, int var5) {
      float var6 = var1 + var3;
      float var7 = var2 + var4;
      var0.method2(var1, var7, 0.0).method9(var5).method16();
      var0.method2(var6, var7, 0.0).method9(var5).method16();
      var0.method2(var1, var2, 0.0).method9(var5).method16();
      var0.method2(var6, var7, 0.0).method9(var5).method16();
      var0.method2(var6, var2, 0.0).method9(var5).method16();
      var0.method2(var1, var2, 0.0).method9(var5).method16();
   }

   public static void method96(Bridge2_32 var0, float var1, float var2, float var3, float var4, int var5) {
      float var6 = var1 + var3;
      float var7 = var2 + var4;
      var0.method2(var1, var2, 0.0).method9(var5).method16();
      var0.method2(var1, var7, 0.0).method9(var5).method16();
      var0.method2(var6, var7, 0.0).method9(var5).method16();
      var0.method2(var6, var2, 0.0).method9(var5).method16();
   }

   public static void method97(AbstractRenderContext var0, float var1, float var2, float var3, float var4, int var5) {
      Bridge2_32 var6 = var0.method10(LunarRenderTypes.field19);
      var6.method1();
      method95(var6, var1, var2, var3, var4, var5);
      var6.method17(BufferBuildMode.BATCHED);
   }

   public static void method98(MixinHelper_4 var0, float var1, float var2, float var3, float var4, int var5) {
      var0.method9(LunarRenderTypes.field25, null, var1, var2, var3, var4, var5x -> method95(var5x, var1, var2, var3, var4, var5));
   }

   private static int[] method99(int var0) {
      int var1 = var0 >> 24 & 0xFF;
      int var2 = var0 >> 16 & 0xFF;
      int var3 = var0 >> 8 & 0xFF;
      int var4 = var0 & 0xFF;
      return new int[]{var2, var3, var4, var1};
   }

   public static void method100(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6, int var7) {
      var0.method9(LunarRenderTypes.field19, null, var1, var2, var3, var4, var7x -> {
         method95(var7x, var1, var2, var3, var4, var7);
         method95(var7x, var1 - var5, var2 - var5, var3 + var5 * 2.0F, var5, var6);
         method95(var7x, var1 - var5, var2 + var4, var3 + var5 * 2.0F, var5, var6);
         method95(var7x, var1 - var5, var2, var5, var4, var6);
         method95(var7x, var1 + var3, var2, var5, var4, var6);
      });
   }

   public static void method101(
      MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6, boolean var7, boolean var8, boolean var9, boolean var10
   ) {
      if (!var7 && !var8 && !var9 && !var10) {
         method94(var0, var1, var2, var3, var4, var6);
      } else {
         var0.method9(LunarRenderTypes.field19, null, var1, var2, var3, var4, var10x -> {
            float var11 = Math.min(var5, Math.min(var3, var4));
            float var12 = var11 / 2.0F;
            int var13 = (var7 ? 1 : 0) + (var8 ? 1 : 0) + (var9 ? 1 : 0) + (var10 ? 1 : 0);
            boolean var14 = var13 == 2 && (var7 && var10 || var8 && var9);
            if (var13 > 2 || var14) {
               method95(var10x, var1 + var12, var2, var3 - var11, var4, var6);
               float var20 = var7 ? var2 + var12 : var2;
               float var22 = var9 ? var2 + var4 - var12 : var2 + var4;
               method95(var10x, var1, var20, var12, var22 - var20, var6);
               float var17 = var8 ? var2 + var12 : var2;
               float var18 = var10 ? var2 + var4 - var12 : var2 + var4;
               method95(var10x, var1 + var3 - var12, var17, var12, var18 - var17, var6);
            } else if (!var9 && !var10) {
               float var19 = var7 ? var1 + var12 : var1;
               float var21 = var8 ? var1 + var3 - var12 : var1 + var3;
               method95(var10x, var19, var2, var21 - var19, var12, var6);
               method95(var10x, var1, var2 + var12, var3, var4 - var12, var6);
            } else if (!var7 && !var8) {
               float var15 = var9 ? var1 + var12 : var1;
               float var16 = var10 ? var1 + var3 - var12 : var1 + var3;
               method95(var10x, var15, var2 + var4 - var12, var16 - var15, var12, var6);
               method95(var10x, var1, var2, var3, var4 - var12, var6);
            } else if (var7) {
               method95(var10x, var1, var2 + var12, var12, var4 - var11, var6);
               method95(var10x, var1 + var12, var2, var3 - var12, var4, var6);
            } else {
               method95(var10x, var1 + var3 - var12, var2 + var12, var12, var4 - var11, var6);
               method95(var10x, var1, var2, var3 - var12, var4, var6);
            }

            if (var8) {
               method81(var10x, var1 + var3 - var12, var2 + var12, var12, 0.0, 0.25, var6);
            }

            if (var10) {
               method81(var10x, var1 + var3 - var12, var2 + var4 - var12, var12, 0.25, 0.5, var6);
            }

            if (var9) {
               method81(var10x, var1 + var12, var2 + var4 - var12, var12, 0.5, 0.75, var6);
            }

            if (var7) {
               method81(var10x, var1 + var12, var2 + var12, var12, 0.75, 1.0, var6);
            }
         });
      }
   }

   public static void method102(
      AbstractRenderContext var0, float var1, float var2, float var3, float var4, float var5, int var6, boolean var7, boolean var8, boolean var9, boolean var10
   ) {
      var5 = Math.min(var5, Math.min(var3, var4));
      float var11 = var5 / 2.0F;
      method97(var0, var1 + var11, var2, var3 - var11 * 2.0F, var4, var6);
      method97(var0, var1, var2 + var11, var11, var4 - var11 * 2.0F, var6);
      method97(var0, var1 + var3 - var11, var2 + var11, var11, var4 - var11 * 2.0F, var6);
      if (var7) {
         method62(var0, var1 + var11, var2 + var11, var11, 0.0, 2.0, 4.0, 0.0, var6, 10);
      } else {
         method97(var0, var1, var2, var11, var11, var6);
      }

      if (var8) {
         method62(var0, var1 + var3 - var11, var2 + var11, var11, 0.0, 3.0, 4.0, 0.0, var6, 10);
      } else {
         method97(var0, var1 + var3 - var11, var2, var11, var11, var6);
      }

      if (var9) {
         method62(var0, var1 + var11, var2 + var4 - var11, var11, 0.0, 1.0, 4.0, 0.0, var6, 10);
      } else {
         method97(var0, var1, var2 + var4 - var11, var11, var11, var6);
      }

      if (var10) {
         method62(var0, var1 + var3 - var11, var2 + var4 - var11, var11, 0.0, 0.0, 4.0, 0.0, var6, 10);
      } else {
         method97(var0, var1 + var3 - var11, var2 + var4 - var11, var11, var11, var6);
      }
   }

   public static void method103(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      method101(var0, var1, var2, var3, var4, var5, var6, true, true, false, false);
   }

   public static void method104(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      method101(var0, var1, var2, var3, var4, var5, var6, true, false, true, false);
   }

   public static void method105(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      method101(var0, var1, var2, var3, var4, var5, var6, false, false, true, false);
   }

   public static void method106(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      method101(var0, var1, var2, var3, var4, var5, var6, true, true, false, false);
   }

   public static boolean method107() {
      return field12.size() > 0;
   }

   public static void method108(float var0) {
      field13 += var0;
   }

   public static float method109() {
      return field13;
   }

   public static void method110(MixinHelper_4 var0, int[] var1) {
      var0.method44(var1x -> {
         AbstractRenderContext var2 = var1x.method29();
         if (var2.method38()) {
            var2.method30().method48();
         }

         method115(var1);
      });
      var0.method45(var1x -> {
         field12.add(var1);
         var1x.method30(var1[0], var1[1], var1[2], var1[3]);
      });
   }

   public static void method111(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5) {
      var0.method44(
         var5x -> method113(
            var5x.method29(),
            (int)(var1 * var5),
            (int)((var2 + method109()) * var5),
            (int)Math.ceil((var1 + var3) * var5),
            (int)Math.ceil((var2 + method109() + var4) * var5),
            method17(),
            ThreadModuleDump63.method3().bridge$displayHeight() / method17()
         )
      );
      var0.method45(var4x -> {
         var4x.method27((int)var1, (int)var2, (int)(var1 + var3), (int)(var2 + var4));
         int[] var5x = var4x.method29();
         field12.add(var5x != null ? var5x : new int[]{(int)var1, (int)var2, (int)(var1 + var3), (int)(var2 + var4)});
      });
   }

   public static int[] method112(MixinHelper_4 var0) {
      if (var0 instanceof ModernGuiGraphicsBridge var1) {
         var1.method28();
         return field12.remove(field12.size() - 1);
      } else {
         return method114(var0.method46().method29());
      }
   }

   public static void method113(AbstractRenderContext var0, int var1, int var2, int var3, int var4, float var5, int var6) {
      if (var0.method38()) {
         var0.method30().method48();
      }

      int var7 = var4 - var2;
      int var8 = var3 - var1;
      int var9 = var6 - var4;
      int var10 = (int)(var1 * var5);
      int var11 = (int)(var9 * var5);
      int var12 = (int)(var8 * var5);
      int var13 = (int)(var7 * var5);
      if (field12.isEmpty()) {
         Bridge.method42().method86(true);
      } else {
         int[] var14 = field12.get(field12.size() - 1);
         int var15 = Math.max(var14[0], var10);
         int var16 = Math.min(var14[0] + var14[2], var10 + var12);
         var10 = var15;
         var12 = var16 - var15;
         int var17 = Math.max(var14[1], var11);
         int var18 = Math.min(var14[1] + var14[3], var11 + var13);
         var11 = var17;
         var13 = var18 - var17;
      }

      field12.add(new int[]{var10, var11, var12, var13});
      Bridge.method42().method87(var10, var11, var12, var13);
   }

   public static int[] method114(AbstractRenderContext var0) {
      if (var0.method38()) {
         var0.method30().method48();
      }

      int[] var1 = field12.remove(field12.size() - 1);
      if (field12.isEmpty()) {
         Bridge.method42().method86(false);
      } else {
         int[] var2 = field12.get(field12.size() - 1);
         Bridge.method42().method87(var2[0], var2[1], var2[2], var2[3]);
      }

      return var1;
   }

   public static void method115(int[] var0) {
      if (ThreadModuleDump63.MC_VERSION >= 29) {
         ThreadModuleDump63.method3().bridge$getRenderBuffers().bridge$bufferSource().bridge$endBatch();
      }

      if (field12.isEmpty()) {
         Bridge.method42().method86(true);
      }

      field12.add(var0);
      Bridge.method42().method87(var0[0], var0[1], var0[2], var0[3]);
   }

   public static void method116(AbstractRenderContext var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      method102(var0, var1, var2, var3, var4, var5, var6, true, true, true, true);
   }

   public static void method117(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      method101(var0, var1, var2, var3, var4, var5, var6, true, true, true, true);
   }

   public static void method118(MixinHelper_4 var0, float var1, float var2, float var3, float var4, float var5, TextColorSource var6) {
      if (!var6.method14()) {
         int var7 = var6.getColor();
         var0.method9(LunarRenderTypes.field29, null, var1, var2, var3, var4, var6x -> {
            method96(var6x, var1 - var5, var2 - var5, var5, var4 + var5 * 2.0F, var7);
            method96(var6x, var1 + var3, var2 - var5, var5, var4 + var5 * 2.0F, var7);
            method96(var6x, var1, var2 - var5, var3, var5, var7);
            method96(var6x, var1, var2 + var4, var3, var5, var7);
         });
      } else {
         RenderLayerBridge var8 = LunarRenderTypes.field33.get(ChromaTexture.method1());
         var0.method9(var8, ChromaTexture.method1(), var1, var2, var3, var4, var6x -> {
            method120(var6x, var1 - var5, var2 - var5, var5, var4 + var5 * 2.0F, var6);
            method120(var6x, var1 + var3, var2 - var5, var5, var4 + var5 * 2.0F, var6);
            method120(var6x, var1, var2 - var5, var3, var5, var6);
            method120(var6x, var1, var2 + var4, var3, var5, var6);
         });
      }
   }

   public static void method119(MixinHelper_4 var0, float var1, float var2, float var3, float var4, TextColorSource var5) {
      if (!var5.method14()) {
         var0.method2(var1, var2, var1 + var3, var2 + var4, var5.getColor());
      } else {
         RenderLayerBridge var6 = LunarRenderTypes.field33.get(ChromaTexture.method1());
         var0.method9(var6, ChromaTexture.method1(), var1, var2, var3, var4, var5x -> method120(var5x, var1, var2, var3, var4, var5));
      }
   }

   public static void method120(Bridge2_32 var0, float var1, float var2, float var3, float var4, TextColorSource var5) {
      int var6 = var5.method1(var1 + var2);
      int var7 = var5.method1(var1 + var3 + var2);
      int var8 = var5.method1(var1 + var2 + var4);
      int var9 = var5.method1(var1 + var3 + var2 + var4);
      float var10 = ChromaTexture.method4(var6);
      float var11 = method121(ChromaTexture.method4(var7), var10);
      float var12 = method121(ChromaTexture.method4(var8), var10);
      float var13 = method121(ChromaTexture.method4(var9), var11 + var12 - var10);
      var0.method2(var1, var2, 0.0);
      ChromaTexture.method3(var0, var6, var10);
      var0.method16();
      var0.method2(var1, var2 + var4, 0.0);
      ChromaTexture.method3(var0, var8, var12);
      var0.method16();
      var0.method2(var1 + var3, var2 + var4, 0.0);
      ChromaTexture.method3(var0, var9, var13);
      var0.method16();
      var0.method2(var1 + var3, var2, 0.0);
      ChromaTexture.method3(var0, var7, var11);
      var0.method16();
   }

   private static float method121(float var0, float var1) {
      return var0 + Math.round(var1 - var0);
   }

   public static void method122(MixinHelper_4 var0, float var1, float var2, boolean var3) {
      var0.push();
      int var4 = -1;
      if (var3) {
         var4 = ThreadModuleDump63.method4().method41().method6().method66().method14(0.0F);
      }

      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(CosmeticManager.field46).bridge$setFilter(true, false);
      method31(var0, CosmeticManager.field46, var1, var2, 107.5F, 16.5F, var4);
      var0.pop();
   }

   public static void method123(MixinHelper_4 var0, float var1, float var2) {
      int var3 = field14.getScaleFactor();
      float var4 = var1;
      float var5 = var2;
      byte var6 = 16;
      CachedFontImpl var7 = FontRegistry.field9;
      var0.push();
      if (var3 > 2) {
         var6 = 38;
         var7 = FontRegistry.field19;
         float var8 = 2.375F;
         float var9 = 1.0F / var8;
         var0.scale(var9, var9, 1.0F);
         var1 *= var8;
         var2 *= var8;
      }

      if (var3 > 4) {
         float var11 = 4.0F / var3;
         var0.scale(var11, var11, 1.0F);
         var1 *= 1.0F / var11;
         var2 *= 1.0F / var11;
      }

      var7.method17(var0, "Version " + Client.method18(), 10.0, var2 - var6, -1597125171, false);
      var0.pop();
      method122(var0, var4 - 115.0F, var5 - 20.0F, true);
   }

   public static boolean isCtrlKeyDown() {
      return ThreadModuleDumpType2.isMacos()
         ? Bridge.method18().method1(KeyCode.KEY_LCOMMAND) || Bridge.method18().method1(KeyCode.KEY_RCOMMAND)
         : Bridge.method18().method1(KeyCode.KEY_LCONTROL) || Bridge.method18().method1(KeyCode.KEY_RCONTROL);
   }

   public static boolean isShiftKeyDown() {
      return Bridge.method18().method1(KeyCode.KEY_LSHIFT) || Bridge.method18().method1(KeyCode.KEY_RSHIFT);
   }

   public static boolean method124() {
      return Bridge.method18().method1(KeyCode.KEY_LMENU) || Bridge.method18().method1(KeyCode.KEY_RMENU);
   }

   public boolean method7() {
      return false;
   }

   public static void method126(AbstractRenderContext var0, int var1, int var2, int var3, float var4) {
      MainMenuBackground.method2(var0, var1, var2, var3 + var4);
   }

   public void handleMouseInput() {
   }

   public static void method127(MixinHelper_4 var0, Bridge5Extension_3 var1, Bridge3_18 var2, int var3) {
      int var4 = var1.bridge$getGuiLeft() + var2.bridge$getXDisplayPosition();
      int var5 = var1.bridge$getGuiTop() + var2.bridge$getYDisplayPosition();
      var0.push();
      var0.method38(0.0F, 0.0F, 10.001F);
      var0.method1(var4, var5, var4 + 16, var5 + 16, var3);
      var0.pop();
   }

   public static void method128(MixinHelper_4 var0, Bridge5Extension_3 var1, Bridge3_18 var2, TextComponent var3) {
      int var4 = var1.bridge$getGuiLeft() + var2.bridge$getXDisplayPosition() + 8;
      int var5 = var1.bridge$getGuiTop() + var2.bridge$getYDisplayPosition() + 5;
      var0.push();
      var0.method38(0.0F, 0.0F, 301.0F);
      var0.method27(ThreadModuleDump63.method10(), var3, var4, var5, -1, true);
      var0.pop();
   }

   public static void method129(MixinHelper_4 var0, Bridge5Extension_3 var1, Bridge3_18 var2, TextComponent var3) {
      method130(var0, var1, var2, var3, 17, 9);
   }

   public static void method130(MixinHelper_4 var0, Bridge5Extension_3 var1, Bridge3_18 var2, TextComponent var3, int var4, int var5) {
      method131(var0, var1, var2, var3, var4, var5, -1);
   }

   public static void method131(MixinHelper_4 var0, Bridge5Extension_3 var1, Bridge3_18 var2, TextComponent var3, int var4, int var5, int var6) {
      int var7 = (int)ThreadModuleDump63.method10().bridge$getStringWidth(var3);
      int var8 = var2.bridge$getXDisplayPosition() + var4 - var7;
      int var9 = var2.bridge$getYDisplayPosition() + var5;
      if (ThreadModuleDump63.MC_VERSION <= 5 || ThreadModuleDump63.MC_VERSION >= 16) {
         var8 += var1.bridge$getGuiLeft();
         var9 += var1.bridge$getGuiTop();
      }

      var0.push();
      var0.method38(0.0F, 0.0F, 301.0F);
      var0.method10(ThreadModuleDump63.method10(), var3, var8, var9, var6, true);
      var0.pop();
      if (ThreadModuleDump63.MC_VERSION >= 7 && ThreadModuleDump63.MC_VERSION <= 16) {
         ThreadModuleDump63.method3().bridge$getRenderBuffers().bridge$bufferSource().bridge$endLastBatch();
      }
   }

   public Optional<String> method8() {
      return Optional.of("LCUI " + this.method133());
   }

   public String method133() {
      return this.getLanguagePath();
   }

   public static Optional<com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen> method134() {
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 var0) {
         Bridge7_8 var5 = var0.method2();
         if (var5 instanceof com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen var6) {
            return Optional.of(var6);
         }

         if (var5 instanceof MainMenuButton var2 && var2.method17() instanceof com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen var3) {
            return Optional.of(var3);
         }
      }

      return Optional.empty();
   }

   public static float method135(float var0) {
      return method137(var0, method17());
   }

   public static double method136(double var0) {
      return method138(var0, method17());
   }

   public static float method137(float var0, float var1) {
      return Math.round(var0 * var1) / var1;
   }

   public static double method138(double var0, double var2) {
      return Math.round(var0 * var2) / var2;
   }

   public static String method139(KeyCombo var0) {
      String var1 = var0.method8().getName();
      boolean var2 = var0.method5() || var0.method6() || var0.method7();
      String var3 = var0.method5() ? "ALT" : (var0.method6() ? "SHIFT" : "CTRL");
      return var2 ? var3 + " + " + var1 : var1;
   }

   public static void method140(
      MixinHelper_4 var0, String var1, Calculator2 var2, KeyCombo var3, CachedFontImpl var4, float var5, float var6, boolean var7, int var8, int var9, int var10
   ) {
      String var11 = var3.method8().getName();
      boolean var12 = var3.method5() || var3.method6() || var3.method7();
      String var13 = var3.method5() ? "ALT" : (var3.method6() ? "SHIFT" : "CTRL");
      String var14 = var2.method1(var1, new Object[]{"$0"});
      String[] var15 = var14.split("\\$0");
      String var16 = var14.replace("$0", var12 ? "  " + var13 + " + " + var11 + "  " : "  " + var11 + "  ");
      float var17 = var4.method4(var16);
      float var18 = var4.method13(var0, var15[0], var5 - (var7 ? var17 / 2.0F : 0.0F), var6, var8);
      float var19 = var4.method4(" ");
      if (var12) {
         float var20 = var4.method4(var13);
         method117(var0, var18 + var19 + 1.0F, var6, var20 + 2.0F, var4.HCICOIIIIHOHIHHCRCCCHIROHCIRHO().getSize() / 2.0F + 2.0F, 4.0F, var10);
         var18 = var4.method13(var0, "  " + var13 + " ", var18, var6, var9);
         var18 = var4.method13(var0, " + ", var18, var6, var10);
      } else {
         var18 += var19;
      }

      float var24 = var4.method4(var11);
      method117(var0, var18 + var19 - 1.0F, var6, var24 + 2.0F, var4.HCICOIIIIHOHIHHCRCCCHIROHCIRHO().getSize() / 2.0F + 2.0F, 4.0F, var10);
      var18 = var4.method13(var0, " " + var11 + "  ", var18, var6, var9);
      var4.method13(var0, var15[1], var18, var6, var8);
   }

   public static void method141(
      AbstractRenderContext var0,
      String var1,
      Calculator2 var2,
      KeyCombo var3,
      CachedFontImpl var4,
      float var5,
      float var6,
      boolean var7,
      int var8,
      int var9,
      int var10
   ) {
      String var11 = var3.method8().getName();
      boolean var12 = var3.method5() || var3.method6() || var3.method7();
      String var13 = var3.method5() ? "ALT" : (var3.method6() ? "SHIFT" : "CTRL");
      String var14 = var2.method1(var1, new Object[]{"$0"});
      String[] var15 = var14.split("\\$0");
      String var16 = var14.replace("$0", var12 ? "  " + var13 + " + " + var11 + "  " : "  " + var11 + "  ");
      float var17 = var4.method4(var16);
      float var18 = var4.method5(var0, var15[0], var5 - (var7 ? var17 / 2.0F : 0.0F), var6, var8);
      float var19 = var4.method4(" ");
      if (var12) {
         float var20 = var4.method4(var13);
         method116(var0, var18 + var19 + 1.0F, var6, var20 + 2.0F, var4.HCICOIIIIHOHIHHCRCCCHIROHCIRHO().getSize() / 2.0F + 2.0F, 4.0F, var10);
         var18 = var4.method5(var0, "  " + var13 + " ", var18, var6, var9);
         var18 = var4.method5(var0, " + ", var18, var6, var10);
      } else {
         var18 += var19;
      }

      float var24 = var4.method4(var11);
      method116(var0, var18 + var19 - 1.0F, var6, var24 + 2.0F, var4.HCICOIIIIHOHIHHCRCCCHIROHCIRHO().getSize() / 2.0F + 2.0F, 4.0F, var10);
      var18 = var4.method5(var0, " " + var11 + "  ", var18, var6, var9);
      var4.method5(var0, var15[1], var18, var6, var8);
   }

   public boolean doesGuiPauseGame() {
      return true;
   }

   protected LinkedHashSet<GuiWidget> method142(GuiWidget... var1) {
      LinkedHashSet var2 = Sets.newLinkedHashSet();
      var2.addAll(Arrays.asList(var1));
      return var2;
   }

   public boolean method143() {
      for (GuiWidget var2 : this.field11) {
         if (var2 instanceof EditState var3 && var3.isEditing()) {
            return true;
         }
      }

      return false;
   }

   public static void method144() {
      method134().ifPresent(var0 -> {
         if (var0.method10().method17() instanceof com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget var1) {
            var1.field16.clear();
            var1.method2();
         }
      });
   }

   public static void method145() {
      method134().ifPresent(var0 -> {
         if (var0.method10().method17() instanceof com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget var1) {
            var1.method15();
            var1.method17();
         }
      });
   }

   @Generated
   public static void method146(boolean var0) {
      field7 = var0;
   }

   @Generated
   public void method147(boolean var1) {
      this.field8 = var1;
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
   public List<GuiWidget> method148() {
      return this.field11;
   }

   @Generated
   public void method149(List<GuiWidget> var1) {
      this.field11 = var1;
   }

   @Generated
   public static void method150(ThreadModuleDump71 var0) {
      field14 = var0;
   }

   @Generated
   public static ThreadModuleDump71 method151() {
      return field14;
   }
}
