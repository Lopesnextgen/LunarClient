package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.BridgeType3_3;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuThemeManager;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump38;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Objects;
import org.lwjgl.opengl.GL11;

public class MainMenuBackground {
   private static ResourceLocationBridge field1 = MainMenuHomeScreen.field22.method2();
   private static String field2 = MainMenuHomeScreen.field22.name();
   private static int field3 = 0;
   private static MainMenuBackground field4;
   private ResourceLocationBridge field5;

   public void init() {
      if (MainMenuThemeManager.method12() != null) {
         if (Objects.equals(MainMenuThemeManager.method12().name(), field2)) {
            return;
         }

         if (MainMenuThemeManager.method12().method2() != null) {
            field1 = MainMenuThemeManager.method12().method2();
            field2 = MainMenuThemeManager.method12().name();
         } else {
            field1 = MainMenuHomeScreen.field22.method2();
            field2 = MainMenuHomeScreen.field22.name();
         }

         Bridge5Extension6 var1 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
         if (ThreadModuleDump63.MC_VERSION >= 22 && var1 != null) {
            var1.bridge$setPanorama(method1());
         }
      }
   }

   public static ResourceLocationBridge method1() {
      return field1;
   }

   public static void method2(AbstractRenderContext var0, float var1, float var2, float var3) {
      var0.method17();
      method9().method6(var0, var1, var2, var3);
      var0.method16();
   }

   public static void method3(AbstractRenderContext var0, float var1, float var2, float var3) {
      var0.method17();
      method9().method6(var0, var1, var2, (float)((field3 + var3) * ThreadModuleDump63.method3().bridge$getGameSettings().getPanoramaSpeed()));
      var0.method16();
   }

   public void method4() {
      field3++;
   }

   public void method5() {
      field3 = 0;
   }

   private void method6(AbstractRenderContext var1, float var2, float var3, float var4) {
      if (this.field5 == null) {
         Bridge8Extension33 var5 = Bridge.method8().method21(256, 256);
         this.field5 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getDynamicTextureLocation("background", var5);
      }

      ThreadModuleDump63.method3().bridge$getMainRenderTarget().bridge$unbindFrameBuffer();
      var1.method27(0, 0, 256, 256);
      this.method8(var1, var4);
      this.method7(var1, var2, var3);
      byte var9 = 3;

      for (int var6 = 0; var6 < var9; var6++) {
         this.method7(var1, var2, var3);
         this.method7(var1, var2, var3);
      }

      ThreadModuleDump63.method3().method1(ThreadModuleDump63.method3().bridge$getMainRenderTarget(), true);
      var1.method27(0, 0, ThreadModuleDump63.method3().bridge$displayWidth(), ThreadModuleDump63.method3().bridge$displayHeight());
      float var10 = var2 > var3 ? 120.0F / var2 : 120.0F / var3;
      float var7 = var3 * var10 / 256.0F;
      float var8 = var2 * var10 / 256.0F;
      var1.method10(LunarRenderTypes.field38.get(this.field5))
         .method1()
         .method2(0.0, var3, 0.0)
         .method10(0.5F - var7, 0.5F + var8)
         .method9(-1)
         .method16()
         .method2(var2, var3, 0.0)
         .method10(0.5F - var7, 0.5F - var8)
         .method9(-1)
         .method16()
         .method2(var2, 0.0, 0.0)
         .method10(0.5F + var7, 0.5F - var8)
         .method9(-1)
         .method16()
         .method2(0.0, 0.0, 0.0)
         .method10(0.5F + var7, 0.5F + var8)
         .method9(-1)
         .method16()
         .method17(BufferBuildMode.BATCHED);
      if (var1.method38()) {
         var1.method30().method48();
      }
   }

   private void method7(AbstractRenderContext var1, float var2, float var3) {
      if (ThreadModuleDump63.MC_VERSION < 29) {
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$bindTexture(this.field5);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
         var1.method14();
         var1.method2(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA);
         var1.method5(true);
         GL11.glColorMask(true, true, true, false);
         var1.method17();
      }

      byte var4 = 3;
      byte var5 = 3;

      for (int var6 = 0; var6 < var5; var6++) {
         float var7 = 1.0F / (var6 + 1);
         float var8 = (var6 - var4 / 2) / 256.0F;
         Bridge2_32 var9 = var1.method10(LunarRenderTypes.field38.get(this.field5)).method1();
         int var10 = ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, var7);
         var9.method2(var2, var3, 0.0).method10(0.0F + var8, 1.0F).method9(var10).method16();
         var9.method2(var2, 0.0, 0.0).method10(1.0F + var8, 1.0F).method9(var10).method16();
         var9.method2(0.0, 0.0, 0.0).method10(1.0F + var8, 0.0F).method9(var10).method16();
         var9.method2(0.0, var3, 0.0).method10(0.0F + var8, 0.0F).method9(var10).method16();
         var9.method17(BufferBuildMode.BATCHED);
      }

      if (var1.method38()) {
         var1.method30().method48();
      }

      if (ThreadModuleDump63.MC_VERSION < 29) {
         var1.method5(false);
         var1.method16();
         GL11.glColorMask(true, true, true, true);
      }
   }

   private void method8(AbstractRenderContext var1, float var2) {
      RenderSystemBridge var3 = Bridge.method42();
      if (var1.method38()) {
         var3.method83().CROORCRRCORRICIOIRIICOOICHOHOO();
         if (ThreadModuleDump63.MC_VERSION > 9) {
            Bridge5_16 var4 = var3.method83().method12();
            var4.bridge$loadIdentity();
            var1.method34();
         } else {
            var3.bridge$loadIdentity();
         }
      }

      var1.method8(BridgeType3_3.GL_PROJECTION);
      if (var1.method38() && !Bridge.getMinecraftVersion().method23()) {
         var3.method4();
         var3.bridge$loadIdentity();
         var3.method75(120.0F, 1.0F, 0.05F, 10.0F);
      } else {
         var1.push();
         var1.method36();
         var1.method29(120.0F, 1.0F, 0.05F, 10.0F);
      }

      var1.method8(BridgeType3_3.GL_MODELVIEW);
      var1.push();
      var1.method36();
      var1.method4(180.0F, 1.0F, 0.0F, 0.0F);
      var1.method4(90.0F, 0.0F, 0.0F, 1.0F);
      var1.method34();
      var1.method14();
      var1.method17();
      var1.method21();
      var1.method6(false);
      var1.method4(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA, BridgeType2_9.GL_ONE, BridgeType2_9.GL_ZERO);
      var1.method5(true);

      for (int var12 = 0; var12 < 64; var12++) {
         var1.push();
         float var5 = (var12 % 8 / 8.0F - 0.5F) / 64.0F;
         float var6 = (var12 / 8 / 8.0F - 0.5F) / 64.0F;
         var1.translate(var5, var6, 0.0);
         float var7 = (float)(ThreadModuleDump38.sin(var2 / 400.0) * 25.0 + 20.0);
         float var8 = -((float)(var2 * 0.1 % 360.0));
         var1.method4(var7, 1.0F, 0.0F, 0.0F);
         var1.method4(var8, 0.0F, 1.0F, 0.0F);

         for (int var9 = 0; var9 < 6; var9++) {
            var1.push();
            if (var9 == 1) {
               var1.method4(90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var9 == 2) {
               var1.method4(180.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var9 == 3) {
               var1.method4(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var9 == 4) {
               var1.method4(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (var9 == 5) {
               var1.method4(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            var1.method34();
            int var10 = ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, 1.0F / (var12 + 1));
            RenderLayerBridge var11 = LunarRenderTypes.field37.get(ResourceLocationBridge.create(field1.bridge$getDomain(), field1.bridge$getPath() + "_" + var9 + ".png"));
            var1.method10(var11)
               .method1()
               .method2(-1.0, -1.0, 1.0)
               .method10(0.0F, 0.0F)
               .method9(var10)
               .method16()
               .method2(1.0, -1.0, 1.0)
               .method10(1.0F, 0.0F)
               .method9(var10)
               .method16()
               .method2(1.0, 1.0, 1.0)
               .method10(1.0F, 1.0F)
               .method9(var10)
               .method16()
               .method2(-1.0, 1.0, 1.0)
               .method10(0.0F, 1.0F)
               .method9(var10)
               .method16()
               .method17(BufferBuildMode.BATCHED);
            if (var1.method38()) {
               var1.method33(var11);
            }

            var1.pop();
         }

         var1.pop();
         GL11.glColorMask(true, true, true, false);
      }

      var1.method5(false);
      GL11.glColorMask(true, true, true, true);
      var1.method8(BridgeType3_3.GL_PROJECTION);
      if (var1.method38() && !Bridge.getMinecraftVersion().method23()) {
         var3.method5();
      } else {
         var1.pop();
      }

      var1.method8(BridgeType3_3.GL_MODELVIEW);
      var1.method5(var1x -> var3.method83().method8());
      var1.pop();
      var1.method34();
      var1.method6(true);
      var1.method20();
      var1.method18();
      if (var1.method38()) {
         var3.method83().IORRRICHRIHCCIORRIICIIORIRRRRC();
      }
   }

   public static MainMenuBackground method9() {
      if (field4 == null) {
         field4 = new MainMenuBackground();
      }

      return field4;
   }

   private static float method10(float var0, float var1) {
      return var0 > var1 ? var0 - var1 : var0;
   }
}
