package com.moonsworth.lunar.client.driver.holograms;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.shader.ShaderInjectedPipeline;
import com.moonsworth.lunar.client.render.shader.ShaderInjectRegistry;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.holograms.mixin.EmoteHologramLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.floats.FloatConsumer;
import it.unimi.dsi.fastutil.longs.Long2FloatOpenHashMap;
import java.util.UUID;
import lombok.Generated;

public class Holograms {
   private final UUID field1 = UUID.randomUUID();
   private final ShaderInjectedPipeline field2;
   private final ShaderInjectedPipeline field3;
   protected Bridge3_24 field4 = null;
   protected Bridge3_24 field5 = null;
   protected Bridge3_24 field6 = null;
   private int field7 = 0;
   private long field8 = 0L;
   private boolean field9;
   protected HoverModelLegacy field10 = null;
   protected HoverModelLegacy field11 = null;
   protected HoverModelLegacy field12 = null;
   private float field13;
   private float field14;
   private boolean field15;
   private int framebufferWidth;
   private int framebufferHeight;
   private Bridge3_24 field16;
   private long field17;
   private float field18;
   private final Long2FloatOpenHashMap field19 = new Long2FloatOpenHashMap();

   public Holograms() {
      this.field2 = method1("cosmetic_highlight", ThreadModuleDump63.MC_VERSION >= 39);
      this.field3 = ThreadModuleDump63.MC_VERSION >= 39 ? method1("cosmetic_highlight_normal_z", false) : null;
   }

   private static ShaderInjectedPipeline method1(String var0, boolean var1) {
      ShaderInjectRegistry var2 = ThreadModuleDump63.method4().method99();
      ShaderInjectedPipeline var3 = var2.method1(var0, new ColorsaturationExtension(var1));
      return var3 != null ? var3 : var2.method4(var0);
   }

   public void method2(Bridge3_24 var1, float var2, float var3, int var4, int var5, boolean var6) {
      this.field13 = (int)var2;
      this.field14 = (int)var3;
      this.field15 = var6;
      this.field16 = var1;
      this.method7(var4, var5);
   }

   public void method3(AbstractRenderContext var1, EmoteHologramLegacy var2, Runnable var3) {
      Bridge5Extension_5 var4 = HologramsIterator2.method14();
      UUID var5 = var4.bridge$getUniqueID();
      var4.bridge$setUniqueID(this.field1);
      this.method6(var1, var3, var1x -> this.field18 = var1x);
      if (var2.method33() != null) {
         String var6 = var2.method33().getType();
         if (var6.equals("classic")) {
            var6 = "default";
         }

         ResourceLocationBridge var7 = HologramsIterator2.method18(var2.method33().getHash(), var2.method33().getUrl());
         var4.bridge$setSkinLocationOverride(var7, var6);
      }

      var4.bridge$getInventory().bridge$getMainInventory().set(0, var2.method43());
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var4.bridge$getInventory().bridge$setOffhandItem(var2.method44());
      }

      var4.bridge$setUniqueID(var5);
      ThreadModuleDump63.method3().method1(this.field16, false);
   }

   public void method4(AbstractRenderContext var1, OwnedCosmetic var2, Runnable var3, boolean var4) {
      if (this.method15()) {
         if (!this.method14(var1, var2, var3)) {
            boolean var5 = this.field7 == var2.method9();
            float var6 = 0.0F;
            if (var4 && !var5 && this.method16()) {
               var6 = this.field19.computeIfAbsent(var2.method9(), var0 -> 1.0F);
               this.method6(var1, var3, var2x -> this.field19.put(var2.method9(), var2x));
               ThreadModuleDump63.method3().method1(this.field16, false);
               boolean var7 = var6 != 1.0F && var6 != 0.0F;
               boolean var8 = this.field12 == null || var6 < this.field12.method5();
               if (ThreadModuleDump63.method1()) {
                  var5 = var7 && var8 && var6 >= this.field18;
               } else {
                  var5 = var7 && var8 && var6 - (ThreadModuleDump63.MC_VERSION >= 29 ? 0.001F : 0.006F) <= this.field18;
               }
            }

            if (var5) {
               this.method5(var1, var2, var3, var6);
            }
         }
      }
   }

   private void method5(AbstractRenderContext var1, OwnedCosmetic var2, Runnable var3, float var4) {
      if (this.field11 != null && this.field11.method4() == var2) {
         this.field12 = this.field11;
      } else {
         this.field12 = new HoverModelLegacy(var2);
      }

      this.field12.method2(var4);
      if (var1.method38()) {
         var1.method30().method48();
      }

      this.field4.bridge$framebufferClear();
      ThreadModuleDump63.method3().method1(this.field4, false);
      var1.method33();
      var3.run();
      if (var1.method38()) {
         var1.method30().method48();
      }

      ThreadModuleDump63.method3().method1(this.field16, false);
   }

   private void method6(AbstractRenderContext var1, Runnable var2, FloatConsumer var3) {
      if (var1.method38()) {
         var1.method30().method48();
      }

      this.field6.bridge$framebufferClear();
      ThreadModuleDump63.method3().method1(this.field6, false);
      var1.method18();
      var1.method13();
      var2.run();
      var1.method12();
      if (var1.method38()) {
         var1.method30().method48();
      }

      int var4 = (int)this.field13;
      int var5 = (int)this.field14;
      if (var4 >= 0 && var5 >= 0 && var4 < this.field6.bridge$framebufferWidth() && var5 < this.field6.bridge$framebufferHeight()) {
         Bridge.method8()
            .method91(
               this.field6.bridge$getDepthTexture(),
               var4,
               var5,
               1,
               1,
               TexturePixelFormat.DEPTH32,
               var1x -> var3.accept(Float.intBitsToFloat(var1x.asIntBuffer().get(0)))
            );
      } else {
         var3.accept(1.0F);
      }
   }

   private void method7(int var1, int var2) {
      if (!this.method15()) {
         this.framebufferWidth = var1;
         this.framebufferHeight = var2;
         this.field4 = Bridge3_24.method4(var1, var2);
         this.field5 = Bridge3_24.method4(var1, var2);
         this.field6 = Bridge3_24.method4(var1, var2);
      } else {
         if (var1 != this.framebufferWidth || var2 != this.framebufferHeight) {
            this.framebufferWidth = var1;
            this.framebufferHeight = var2;
            this.field4.bridge$createBindFramebuffer(var1, var2);
            this.field5.bridge$createBindFramebuffer(var1, var2);
            this.field6.bridge$createBindFramebuffer(var1, var2);
         }
      }
   }

   public final void method8(AbstractRenderContext var1, Bridge3_24 var2) {
      this.method9();
      if (this.field11 != null || this.field10 != null) {
         var1.method14();
         var1.method2(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE);
         if (this.field11 != null) {
            this.field11.method1();
            this.method17(var1, var2, this.field4, 16777215);
         }

         if (this.field10 != null) {
            boolean var3 = ThreadModuleDump63.method4().method53().method22(this.field8).stream().allMatch(CosmeticMetadata::method3);
            int var4;
            if (this.field9) {
               if (var3) {
                  var4 = 16716049;
               } else {
                  var4 = 1179426;
               }
            } else if (var3) {
               var4 = 1179409;
            } else {
               var4 = 16777215;
            }

            this.method17(var1, var2, this.field5, var4);
         }

         this.field5.bridge$framebufferClear();
         this.field4.bridge$framebufferClear();
         ThreadModuleDump63.method3().method1(this.field16, false);
         var1.method2(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA);
         this.field12 = null;
         this.field18 = 0.0F;
      }
   }

   private void method9() {
      if (this.field12 == null && this.field11 != null) {
         this.field11.remove();
      }

      this.field11 = this.field12;
   }

   public void method10() {
      this.field12 = null;
      if (this.field11 != null) {
         this.field11.remove();
         this.field11 = null;
      }
   }

   public void method11(int var1, int var2, MarkerModel.Data5 var3) {
      if (this.field15 && var1 == 0) {
         if (var2 == 1) {
            this.field17 = System.currentTimeMillis();
         } else if (var2 == 0 && System.currentTimeMillis() - this.field17 < 200L) {
            this.method12();
         }
      }
   }

   private void method12() {
      JsonObject var1 = new JsonObject();
      if (this.field11 != null) {
         this.field8 = this.field11.method4().method9();
         var1.add("cosmetic", this.field11.method4().provide());
      } else {
         if (this.field8 == 0L || this.field10 == null) {
            return;
         }

         this.field8 = 0L;
         this.field10 = null;
      }

      var1.addProperty("id", this.field8);
      DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "model:select", var1);
   }

   public boolean method13() {
      return this.field8 != 0L || this.field7 != 0;
   }

   private boolean method14(AbstractRenderContext var1, OwnedCosmetic var2, Runnable var3) {
      if (this.field8 != var2.method9()) {
         return false;
      }

      if (this.field10 == null || this.field10.method4().method9() != var2.method9()) {
         this.field10 = new HoverModelLegacy(var2);
      }

      if (var1.method38()) {
         var1.method30().method48();
      }

      this.field5.bridge$framebufferClear();
      ThreadModuleDump63.method3().method1(this.field5, false);
      var1.method33();
      var3.run();
      if (var1.method38()) {
         var1.method30().method48();
      }

      ThreadModuleDump63.method3().method1(this.field16, false);
      return true;
   }

   private boolean method15() {
      return this.field6 != null;
   }

   private boolean method16() {
      return DriverViewportLegacy.method50().method64() == null;
   }

   private void method17(AbstractRenderContext var1, Bridge3_24 var2, Bridge3_24 var3, int var4) {
      ShaderInjectedPipeline var5 = this.field3 != null && !ThreadModuleDump63.method1() ? this.field3 : this.field2;
      RenderLayerBridge var6 = Bridge.method8()
         .method81()
         .method5(Bridge.method8().method93("Lunar Cosmetic Highlight", () -> var2))
         .method14(var5.method6(), "lunar_cosmetic_highlight", 256, false, false, false);
      var6.bridge$getShaderUniforms().ifPresent(var3x -> {
         var3x.bridge$bindSampler("CosmeticDepth", var3.bridge$getDepthTexture());
         var3x.bridge$bindSampler("SceneDepth", var2.bridge$getDepthTexture());
         var3x.bridge$getShaderUniform("HighlightColor").bridge$set((var4 >> 16 & 0xFF) / 255.0F, (var4 >> 8 & 0xFF) / 255.0F, (var4 & 0xFF) / 255.0F);
         var3x.bridge$getShaderUniform("Resolution").bridge$set(var2.bridge$framebufferTextureWidth(), var2.bridge$framebufferTextureHeight());
      });
      var1.method10(var6)
         .method1()
         .method2(0.0, 0.0, 0.0)
         .method16()
         .method2(1.0, 0.0, 0.0)
         .method16()
         .method2(0.0, 1.0, 0.0)
         .method16()
         .method2(1.0, 1.0, 0.0)
         .method16()
         .method17(BufferBuildMode.BATCHED);
      var1.method33(var6);
      var6.bridge$uncache();
   }

   @Generated
   public Bridge3_24 method18() {
      return this.field4;
   }

   @Generated
   public Bridge3_24 method19() {
      return this.field5;
   }

   @Generated
   public Bridge3_24 method20() {
      return this.field6;
   }

   @Generated
   public void method21(int var1) {
      this.field7 = var1;
   }

   @Generated
   public void method22(long var1) {
      this.field8 = var1;
   }

   @Generated
   public long method23() {
      return this.field8;
   }

   @Generated
   public void method24(boolean var1) {
      this.field9 = var1;
   }

   @Generated
   public void method25(HoverModelLegacy var1) {
      this.field10 = var1;
   }

   @Generated
   public void method26(boolean var1) {
      this.field15 = var1;
   }
}
