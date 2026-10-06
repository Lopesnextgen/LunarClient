package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_14;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge5_13;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge6_8;
import com.moonsworth.lunar.bridge.Bridge8Extension;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import com.moonsworth.lunar.bridge.Bridge_52;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.render.shader.ShaderInjectedPipeline;
import com.moonsworth.lunar.client.render.shader.ShaderResource;
import com.moonsworth.lunar.client.render.shader.GlslBuiltin;
import com.moonsworth.lunar.client.render.shader.DevShaderEditor;
import com.moonsworth.lunar.client.render.jit.JitResource;
import com.moonsworth.lunar.client.render.jit.JitPaths;
import com.moonsworth.lunar.client.render.jit.JitAnimatedResource;
import com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod.Shaderdebugmod;
import com.moonsworth.lunar.client.framework.feature.screenshot.Screenshot2;
import com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot;
import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.Alert6;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import lombok.Generated;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.render.shader.ShaderUniformUpdater;
import com.moonsworth.lunar.client.render.shader.ShaderStateHelper;
import com.moonsworth.lunar.client.render.texture.NativeImageBuilder;
import com.moonsworth.lunar.client.cosmetics.emote.PositionHistory;
import com.moonsworth.lunar.client.render.shader.ShaderPass;
import com.moonsworth.lunar.client.cosmetics.gecko.VertexBuilder;

public class ShaderCloakRenderer {
   private static int field1 = 0;
   private static final ShaderCloakRenderer.Data field2 = new ShaderCloakRenderer.Data();
   private final int field3 = field1++;
   private final Alert6 field4;
   private final Set<GlslBuiltin> field5 = EnumSet.noneOf(GlslBuiltin.class);
   private final Map<GlslBuiltin, Float[]> field6 = new EnumMap<>(GlslBuiltin.class);
   private final List<ShaderCloakRenderer.Data2> field7;
   private final ShaderPass field8;
   private final ShaderStateHelper field9;
   private boolean field10 = true;
   private final int field11;
   private final int field12;
   private Bridge8Extension3 field13 = null;
   private ShaderInjectedPipeline field14;
   private RenderLayerBridge field15;
   private Bridge3_24 field16 = null;
   private long startTime = -1L;
   private Shaderdebugmod field17 = null;
   private PositionHistory field18 = null;
   private boolean field19 = false;

   public ShaderCloakRenderer(Alert6 var1) {
      this(var1, var1.getRenderWidth(), var1.getRenderHeight());
   }

   public ShaderCloakRenderer(Alert6 var1, int var2, int var3) {
      this.field4 = var1;
      this.field11 = var2;
      this.field12 = var3;
      this.field5.addAll(var1.getUniforms());
      this.method1();
      this.field7 = var1.getSamplers().stream().map(ShaderCloakRenderer.Data2::new).toList();
      if (var1.getPipeline().method3()) {
         this.field9 = new ShaderStateHelper();
      } else {
         this.field9 = null;
      }

      this.field8 = new ShaderPass(this);
   }

   private void method1() {
      this.method4(GlslBuiltin.OUT_SIZE, (float)this.method37(), (float)this.method38());
   }

   public void method2(@Nullable Bridge6_10 var1) {
      for (GlslBuiltin var3 : this.field4.getUniforms()) {
         if (!this.method18(var3)) {
            BiConsumer var4 = ShaderUniformUpdater.field2.get(var3);
            if (var4 != null) {
               var4.accept(this, var1);
            }
         }
      }
   }

   public void method3() {
      for (GlslBuiltin var2 : this.field4.getUniforms()) {
         if (this.method18(var2)) {
            return;
         }

         Consumer var3 = ShaderUniformUpdater.field3.get(var2);
         if (var3 != null) {
            var3.accept(this);
         }
      }
   }

   public void method4(GlslBuiltin var1, Float... var2) {
      this.field6.put(var1, var2);
   }

   public void method5(GlslBuiltin var1) {
      this.field6.put(var1, null);
   }

   public void method6() {
      if (this.field14 == null) {
         if (this.field16 == null) {
            throw new IllegalStateException("Tried loading Shader Cloak pipeline but the framebuffer hasn't been created yet!");
         }

         DevShaderEditor var1 = ThreadModuleDump63.method4().method102();
         if (this.field4.getVertexSource() == null || this.field4.getVertexShader().method8()) {
            String var2 = var1.method3(this.field4);
            if (var2 == null) {
               DevShaderEditor.method10("Unable to fetch VSH shader!");
               return;
            }

            this.field4.setVertexSource(var2);
         }

         if (this.field4.getFragmentSource() == null || this.field4.getFragmentShader().method8()) {
            String var3 = var1.getPipeline(this.field4);
            if (var3 == null) {
               DevShaderEditor.method10("Unable to fetch FSH shader!");
               return;
            }

            this.field4.setFragmentSource(var3);
         }

         this.field14 = ThreadModuleDump63.method4().method99().method1("shader_cloak_" + this.field3, this.field8);
      }
   }

   public void method7(Bridge8Extension3 var1) {
      if (Bridge.method22().method2()) {
         try {
            this.field16 = Bridge_52.method2().method1(this.field11, this.field12).method4(var1).method8(true).method3();
         } catch (Exception var4) {
            DevShaderEditor.method10("Failed to generate shader cloak FBO! " + var4.getMessage());
            Slayer.method8("Cosmetics", "Failed to generate shader cloak FBO!", new Object[0]);
            var4.printStackTrace();
            return;
         }

         this.field13 = var1;
         if (this.startTime <= 0L) {
            this.startTime = System.currentTimeMillis();
         }

         for (ShaderCloakRenderer.Data2 var3 : this.field7) {
            var3.method1();
         }
      }
   }

   public boolean method8() {
      return this.field14 != null && !this.field14.method10();
   }

   public void method9(UUID var1) {
      if (var1 == null) {
         this.method2(ThreadModuleDump63.method7());
      } else if (ThreadModuleDump63.method3().bridge$getWorld() != null && var1 != null) {
         Optional var2 = ThreadModuleDump63.method3().bridge$getWorld().bridge$getPlayerByUniqueId(var1);
         if (!var2.isEmpty()) {
            Bridge6_10 var3 = (Bridge6_10)var2.get();
            if (!(var3 instanceof ThreadModuleDump54)) {
               this.method2(var3);
            }
         }
      }
   }

   public RenderLayerBridge method10() {
      if (!this.method8()) {
         throw new IllegalStateException("Unable to create shader cloak RenderType! RenderPipeline was cleaned up!");
      }

      if (this.field15 == null) {
         this.field15 = Bridge.method8()
            .method81()
            .method5(Bridge.method8().method93("Lunar Shader Cloak #" + this.field3, () -> this.field16))
            .method14(this.field14.method6(), "lunar_shader_cloak", 256, false, false, false);
      }

      return this.field15;
   }

   private void method11(NativeImageBuilder.Extension var1) {
      if (!this.method21()) {
         this.method12(true);
      } else {
         boolean var2 = true;

         for (ShaderCloakRenderer.Data2 var4 : this.field7) {
            var2 &= var4.method2();
         }

         if (var2) {
            RenderLayerBridge var5 = this.method10();
            var5.bridge$getShaderUniforms().ifPresent(var1x -> {
               for (ShaderCloakRenderer.Data2 var3 : this.field7) {
                  var1x.bridge$bindSampler(var3.getVarName(), var3.field3);
               }

               if (!this.field6.isEmpty()) {
                  this.field6.forEach((var2x, var3x) -> {
                     if (this.field5.contains(var2x)) {
                        Bridge5_13 var4x = var1x.bridge$getShaderUniform(var2x.getVarName());
                        if (var4x != null) {
                           if (var3x == null) {
                              var3x = new Float[var2x.getType().getCount()];
                              Arrays.fill(var3x, Float.valueOf(0.0F));
                           }

                           var4x.bridge$set(var3x);
                        }
                     }
                  });
                  this.field6.clear();
               }

               if (this.field9 != null) {
                  this.field9.method2(var1x, this.field4);
               }
            });
            NativeImageBuilder var6 = var1.build(var5);
            var6.method1();
            this.method13(var6);
            if (this.field9 != null) {
               this.field9.method1();
            }

            var6.method6();
            if (this.field9 != null) {
               this.field9.method4();
            }

            this.method12(false);
         }
      }
   }

   private void method12(boolean var1) {
      if (this.field17 != null && this.field17.method39()) {
         if (var1) {
            this.field17.method32();
            this.field19 = false;
         } else {
            this.field19 = true;
         }
      }
   }

   private void method13(final NativeImageBuilder var1) {
      if (!this.method26()) {
         var1.getPipeline(0.0F, 0.0F, 0.0F).method5().method2(1.0F, 0.0F, 0.0F).method5().method2(0.0F, 1.0F, 0.0F).method5().method2(1.0F, 1.0F, 0.0F).method5();
      } else {
         VertexBuilder var2 = new VertexBuilder() {
            @Override
            protected void vertex(float var1x, float var2x, float var3, float var4, float var5, float var6, float var7, float var8) {
               var1.getPipeline(var3, var4, var5).method3(var1x, var2x).method4(var6, var7, var8).method5();
            }
         };
         if (this.field9.method11() != null) {
            this.field9.method11().accept(var2);
         }
      }
   }

   public void method14(Consumer<VertexBuilder> var1) {
      this.field9.method10(var1);
   }

   public void method15() {
      if (!this.method23()) {
         this.method16();
      }
   }

   public void method16() {
      this.field10 = true;
   }

   public void method17(NativeImageBuilder.Extension var1) {
      if (this.field10) {
         if (this.field19 && this.field17 != null) {
            this.field17.method32();
         }

         this.field10 = false;
         this.method3();
         this.method11(var1);
      }
   }

   private boolean method18(GlslBuiltin var1) {
      return this.field17 != null && this.field17.method36(var1);
   }

   public void method19(GlslBuiltin var1) {
      this.method5(var1);
   }

   public int method20() {
      return this.field12 * this.field11 * 4;
   }

   public boolean method21() {
      return this.method22() && this.method8();
   }

   public boolean method22() {
      return this.field16 != null;
   }

   public boolean method23() {
      return this.field4.isRenderOnTick();
   }

   public void method24(Consumer<BufferedImage> var1) {
      int var2 = this.field11;
      int var3 = this.field12;
      if (this.method21() && var2 > 0 && var3 > 0) {
         Bridge3_24 var4 = Bridge3_24.method2(var2, var3, false);
         this.field16.bridge$blitToRenderTarget(var4, 0, 0, var2, var3, 0, 0, var2, var3, false);
         Bridge.method8().method91(var4.bridge$getColorTexture(true), 0, 0, var2, var3, TexturePixelFormat.RGBA8, var4x -> {
            BufferedImage var5 = new BufferedImage(var2, var3, 2);

            for (int var6 = 0; var6 < var3; var6++) {
               for (int var7 = 0; var7 < var2; var7++) {
                  int var8 = (var7 + var6 * var2) * 4;
                  int var9 = var4x.get(var8) & 255;
                  int var10 = var4x.get(var8 + 1) & 255;
                  int var11 = var4x.get(var8 + 2) & 255;
                  int var12 = var4x.get(var8 + 3) & 255;
                  int var13 = var12 << 24 | var9 << 16 | var10 << 8 | var11;
                  var5.setRGB(var7, var6, var13);
               }
            }

            var1.accept(var5);
            var4.bridge$delete();
         });
      }
   }

   public boolean method25(BufferedImage var1) {
      if (var1 == null) {
         return false;
      }

      File var2 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "screenshots");

      try {
         File var3 = Screenshot.method7(var2);
         ImageIO.write(var1, "PNG", var3);
         Screenshot2.method6(ThreadModuleDump63.method4().method40().method32(), var3, var1, UUID.randomUUID(), false);
         return true;
      } catch (IOException var4) {
         Slayer.method8("Cosmetics", "Failed to screenshot render target texture", new Object[0]);
         return false;
      }
   }

   public boolean method26() {
      return this.field9 != null;
   }

   public void cleanup() {
      if (this.field13 != null) {
         this.field13 = null;
      }

      if (this.field16 != null) {
         this.field16.bridge$delete();
         this.field16 = null;
      }

      if (this.field14 != null) {
         this.field14.destroy();
         this.field14 = null;
      }

      if (this.field15 != null) {
         this.field15.bridge$uncache();
         this.field15.bridge$getShaderUniforms().ifPresent(Bridge6_8::bridge$close);
      }

      this.field15 = null;
   }

   public static boolean method27() {
      if (field2.field1 && field2.field2 != null) {
         ThreadModuleDump63.method3().bridge$overrideMainRenderTarget(field2.field2, true, true);
         return true;
      } else {
         return false;
      }
   }

   public static void restoreViewport() {
      int var0;
      int var1;
      if (field2.field1) {
         var0 = (int)field2.width;
         var1 = (int)field2.height;
      } else {
         var0 = ThreadModuleDump63.method3().bridge$displayWidth();
         var1 = ThreadModuleDump63.method3().bridge$displayHeight();
      }

      Bridge.method42().method54(0, 0, var0, var1);
   }

   public static boolean method28() {
      return field2.field1;
   }

   public static void method29(boolean var0) {
      field2.field1 = var0;
   }

   public static void method30(float var0, float var1) {
      field2.width = var0;
      field2.height = var1;
   }

   public static void method31(Bridge3_24 var0) {
      field2.field2 = var0;
   }

   public static float method32() {
      return field2.width;
   }

   public static float method33() {
      return field2.height;
   }

   public static boolean method34() {
      if (ShaderStateHelper.method7()) {
         return !((Slayer2)Bridge.method5().get()).getShaders().hasShadowPass();
      } else {
         return ShaderStateHelper.method8() ? !((Fishing2Extension)Fishing.method2(Fishing2Extension.class).get()).lunar$isShadowPass() : true;
      }
   }

   @Generated
   public Alert6 method35() {
      return this.field4;
   }

   @Generated
   public boolean method36() {
      return this.field10;
   }

   @Generated
   public int method37() {
      return this.field11;
   }

   @Generated
   public int method38() {
      return this.field12;
   }

   @Generated
   public Bridge8Extension3 method39() {
      return this.field13;
   }

   @Generated
   public Bridge3_24 method40() {
      return this.field16;
   }

   @Generated
   public long getStartTime() {
      return this.startTime;
   }

   @Generated
   public void setStartTime(long var1) {
      this.startTime = var1;
   }

   @Generated
   public void method42(Shaderdebugmod var1) {
      this.field17 = var1;
   }

   @Generated
   public PositionHistory method43() {
      return this.field18;
   }

   @Generated
   public void method44(PositionHistory var1) {
      this.field18 = var1;
   }

   private static class Data {
      private boolean field1 = false;
      private float width = 100.0F;
      private float height = 100.0F;
      private Bridge3_24 field2 = null;
   }

   private static class Data2 {
      private final ShaderResource field1;
      private final boolean field2;
      private Bridge8Extension field3;

      public Data2(ShaderResource var1) {
         this.field1 = var1;
         this.field2 = JitPaths.method1(var1.getPipeline());
      }

      public String getVarName() {
         return this.field1.method1();
      }

      public void method1() {
         if (this.field2) {
            ThreadModuleDump63.method4().method53().method37(this.field1.method2(), null);
         }
      }

      public boolean method2() {
         if (this.field2) {
            Optional var1 = ThreadModuleDump63.method4().method96().method1(this.field1.method2());
            if (var1.isEmpty()) {
               this.method1();
               return false;
            }

            JitResource var2 = (JitResource)var1.get();
            if (var2 instanceof JitAnimatedResource var3) {
               Optional var4 = var3.OIICRHIHHCCOCOCCOHOCRHOCIOICII();
               if (var4.isPresent()) {
                  this.method3((Bridge8Extension3)var4.get());
                  return true;
               }
            }

            return false;
         } else {
            this.method3(ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(this.field1.method2()));
            return true;
         }
      }

      private void method3(Bridge8Extension3 var1) {
         if (ThreadModuleDump63.MC_VERSION < 29) {
            this.field3 = var1;
         } else {
            var1.bridge$setFilter(false, false);
            if (var1 instanceof Bridge2_14 var2) {
               this.field3 = var2.bridge$getSamplerResource();
            } else {
               this.field3 = var1;
            }
         }
      }
   }
}
