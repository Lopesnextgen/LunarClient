package com.moonsworth.lunar.client.driver.hologram;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlBlendFactor;
import com.moonsworth.lunar.bridge.TextureFormat;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.shader.ShaderInjectedPipeline;
import com.moonsworth.lunar.client.render.shader.ShaderInjectRegistry;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.hologram.EmoteHologram;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.floats.FloatConsumer;
import it.unimi.dsi.fastutil.longs.Long2FloatOpenHashMap;
import java.util.UUID;
import lombok.Generated;
import com.moonsworth.lunar.client.cosmetics.Cosmetic;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;

public class CosmeticHighlightRenderer {
   private final UUID field1 = UUID.randomUUID();
   private final ShaderInjectedPipeline field2;
   private final ShaderInjectedPipeline field3;
   protected Bridge3_24 field4 = null;
   protected Bridge3_24 field5 = null;
   protected Bridge3_24 field6 = null;
   private int field7 = 0;
   private long field8 = 0L;
   private boolean field9;
   protected HoverModel field10 = null;
   protected HoverModel field11 = null;
   protected HoverModel field12 = null;
   private float field13;
   private float field14;
   private boolean field15;
   private int framebufferWidth;
   private int framebufferHeight;
   private Bridge3_24 field16;
   private long field17;
   private float field18;
   private final Long2FloatOpenHashMap field19 = new Long2FloatOpenHashMap();

   public CosmeticHighlightRenderer() {
      this.field2 = method1("cosmetic_highlight", Ref.MC_VERSION >= 39);
      this.field3 = Ref.MC_VERSION >= 39 ? method1("cosmetic_highlight_normal_z", false) : null;
   }

   private static ShaderInjectedPipeline method1(String text0, boolean flag1) {
      ShaderInjectRegistry fogiterator22 = Ref.method4().method99();
      ShaderInjectedPipeline colorsaturation3_23 = fogiterator22.method1(text0, new CosmeticHighlightShader(flag1));
      return colorsaturation3_23 != null ? colorsaturation3_23 : fogiterator22.method4(text0);
   }

   public void method2(Bridge3_24 bridge3_241, float value2, float value3, int number4, int number5, boolean flag6) {
      this.field13 = (int)value2;
      this.field14 = (int)value3;
      this.field15 = flag6;
      this.field16 = bridge3_241;
      this.method7(number4, number5);
   }

   public void method3(AbstractRenderContext bridgeextension_91, EmoteHologram holograms2iterator2, Runnable runnable3) {
      Bridge5Extension_5 bridge5extension_54 = HologramsIterator2.method14();
      UUID uuid5 = bridge5extension_54.bridge$getUniqueID();
      bridge5extension_54.bridge$setUniqueID(this.field1);
      this.method6(bridgeextension_91, runnable3, arg1x -> this.field18 = arg1x);
      if (holograms2iterator2.method33() != null) {
         String text6 = holograms2iterator2.method33().getType();
         if (text6.equals("classic")) {
            text6 = "default";
         }

         ResourceLocationBridge horsestats147 = HologramsIterator2.method18(holograms2iterator2.method33().getHash(), holograms2iterator2.method33().getUrl());
         bridge5extension_54.bridge$setSkinLocationOverride(horsestats147, text6);
      }

      bridge5extension_54.bridge$getInventory().bridge$getMainInventory().set(0, holograms2iterator2.method43());
      if (Ref.MC_VERSION >= 5) {
         bridge5extension_54.bridge$getInventory().bridge$setOffhandItem(holograms2iterator2.method44());
      }

      bridge5extension_54.bridge$setUniqueID(uuid5);
      Ref.method3().method1(this.field16, false);
   }

   public void method4(AbstractRenderContext bridgeextension_91, OwnedCosmetic gui2handler2, Runnable runnable3, boolean flag4) {
      if (this.method15()) {
         if (!this.method14(bridgeextension_91, gui2handler2, runnable3)) {
            boolean flag5 = this.field7 == gui2handler2.method9();
            float value6 = 0.0F;
            if (flag4 && !flag5 && this.method16()) {
               value6 = this.field19.computeIfAbsent(gui2handler2.method9(), arg0 -> 1.0F);
               this.method6(bridgeextension_91, runnable3, arg2x -> this.field19.put(gui2handler2.method9(), arg2x));
               Ref.method3().method1(this.field16, false);
               boolean flag7 = value6 != 1.0F && value6 != 0.0F;
               boolean flag8 = this.field12 == null || value6 < this.field12.method5();
               if (Ref.method1()) {
                  flag5 = flag7 && flag8 && value6 >= this.field18;
               } else {
                  flag5 = flag7 && flag8 && value6 - (Ref.MC_VERSION >= 29 ? 0.001F : 0.006F) <= this.field18;
               }
            }

            if (flag5) {
               this.method5(bridgeextension_91, gui2handler2, runnable3, value6);
            }
         }
      }
   }

   private void method5(AbstractRenderContext bridgeextension_91, OwnedCosmetic gui2handler2, Runnable runnable3, float value4) {
      if (this.field11 != null && this.field11.method4() == gui2handler2) {
         this.field12 = this.field11;
      } else {
         this.field12 = new HoverModel(gui2handler2);
      }

      this.field12.method2(value4);
      if (bridgeextension_91.method38()) {
         bridgeextension_91.method30().method48();
      }

      this.field4.bridge$framebufferClear();
      Ref.method3().method1(this.field4, false);
      bridgeextension_91.method33();
      runnable3.run();
      if (bridgeextension_91.method38()) {
         bridgeextension_91.method30().method48();
      }

      Ref.method3().method1(this.field16, false);
   }

   private void method6(AbstractRenderContext bridgeextension_91, Runnable runnable2, FloatConsumer floatconsumer3) {
      if (bridgeextension_91.method38()) {
         bridgeextension_91.method30().method48();
      }

      this.field6.bridge$framebufferClear();
      Ref.method3().method1(this.field6, false);
      bridgeextension_91.method18();
      bridgeextension_91.method13();
      runnable2.run();
      bridgeextension_91.method12();
      if (bridgeextension_91.method38()) {
         bridgeextension_91.method30().method48();
      }

      int number4 = (int)this.field13;
      int number5 = (int)this.field14;
      if (number4 >= 0 && number5 >= 0 && number4 < this.field6.bridge$framebufferWidth() && number5 < this.field6.bridge$framebufferHeight()) {
         Bridge.method8()
            .method91(
               this.field6.bridge$getDepthTexture(),
               number4,
               number5,
               1,
               1,
               TextureFormat.DEPTH32,
               arg1x -> floatconsumer3.accept(Float.intBitsToFloat(arg1x.asIntBuffer().get(0)))
            );
      } else {
         floatconsumer3.accept(1.0F);
      }
   }

   private void method7(int number1, int number2) {
      if (!this.method15()) {
         this.framebufferWidth = number1;
         this.framebufferHeight = number2;
         this.field4 = Bridge3_24.method4(number1, number2);
         this.field5 = Bridge3_24.method4(number1, number2);
         this.field6 = Bridge3_24.method4(number1, number2);
      } else {
         if (number1 != this.framebufferWidth || number2 != this.framebufferHeight) {
            this.framebufferWidth = number1;
            this.framebufferHeight = number2;
            this.field4.bridge$createBindFramebuffer(number1, number2);
            this.field5.bridge$createBindFramebuffer(number1, number2);
            this.field6.bridge$createBindFramebuffer(number1, number2);
         }
      }
   }

   public final void method8(AbstractRenderContext bridgeextension_91, Bridge3_24 bridge3_242) {
      this.method9();
      if (this.field11 != null || this.field10 != null) {
         bridgeextension_91.method14();
         bridgeextension_91.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE);
         if (this.field11 != null) {
            this.field11.method1();
            this.method17(bridgeextension_91, bridge3_242, this.field4, 16777215);
         }

         if (this.field10 != null) {
            boolean flag3 = Ref.method4().method53().method22(this.field8).stream().allMatch(CosmeticMetadata::method3);
            int number4;
            if (this.field9) {
               if (flag3) {
                  number4 = 16716049;
               } else {
                  number4 = 1179426;
               }
            } else if (flag3) {
               number4 = 1179409;
            } else {
               number4 = 16777215;
            }

            this.method17(bridgeextension_91, bridge3_242, this.field5, number4);
         }

         this.field5.bridge$framebufferClear();
         this.field4.bridge$framebufferClear();
         Ref.method3().method1(this.field16, false);
         bridgeextension_91.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
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

   public void method11(int number1, int number2, MarkerModel.Data5 data53) {
      if (this.field15 && number1 == 0) {
         if (number2 == 1) {
            this.field17 = System.currentTimeMillis();
         } else if (number2 == 0 && System.currentTimeMillis() - this.field17 < 200L) {
            this.method12();
         }
      }
   }

   private void method12() {
      JsonObject json1 = new JsonObject();
      if (this.field11 != null) {
         this.field8 = this.field11.method4().method9();
         json1.add("cosmetic", this.field11.method4().provide());
      } else {
         if (this.field8 == 0L || this.field10 == null) {
            return;
         }

         this.field8 = 0L;
         this.field10 = null;
      }

      json1.addProperty("id", this.field8);
      DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "model:select", json1);
   }

   public boolean method13() {
      return this.field8 != 0L || this.field7 != 0;
   }

   private boolean method14(AbstractRenderContext bridgeextension_91, OwnedCosmetic gui2handler2, Runnable runnable3) {
      if (this.field8 != gui2handler2.method9()) {
         return false;
      }

      if (this.field10 == null || this.field10.method4().method9() != gui2handler2.method9()) {
         this.field10 = new HoverModel(gui2handler2);
      }

      if (bridgeextension_91.method38()) {
         bridgeextension_91.method30().method48();
      }

      this.field5.bridge$framebufferClear();
      Ref.method3().method1(this.field5, false);
      bridgeextension_91.method33();
      runnable3.run();
      if (bridgeextension_91.method38()) {
         bridgeextension_91.method30().method48();
      }

      Ref.method3().method1(this.field16, false);
      return true;
   }

   private boolean method15() {
      return this.field6 != null;
   }

   private boolean method16() {
      return DriverViewportLegacy.method50().method64() == null;
   }

   private void method17(AbstractRenderContext bridgeextension_91, Bridge3_24 bridge3_242, Bridge3_24 bridge3_243, int number4) {
      ShaderInjectedPipeline colorsaturation3_25 = this.field3 != null && !Ref.method1() ? this.field3 : this.field2;
      RenderTypeBridge bridge206 = Bridge.method8()
         .method81()
         .method5(Bridge.method8().method93("Lunar Cosmetic Highlight", () -> bridge3_242))
         .method14(colorsaturation3_25.method6(), "lunar_cosmetic_highlight", 256, false, false, false);
      bridge206.bridge$getShaderUniforms().ifPresent(arg3x -> {
         arg3x.bridge$bindSampler("CosmeticDepth", bridge3_243.bridge$getDepthTexture());
         arg3x.bridge$bindSampler("SceneDepth", bridge3_242.bridge$getDepthTexture());
         arg3x.bridge$getShaderUniform("HighlightColor").bridge$set((number4 >> 16 & 0xFF) / 255.0F, (number4 >> 8 & 0xFF) / 255.0F, (number4 & 0xFF) / 255.0F);
         arg3x.bridge$getShaderUniform("Resolution").bridge$set(bridge3_242.bridge$framebufferTextureWidth(), bridge3_242.bridge$framebufferTextureHeight());
      });
      bridgeextension_91.method10(bridge206)
         .method1()
         .method2(0.0, 0.0, 0.0)
         .method16()
         .method2(1.0, 0.0, 0.0)
         .method16()
         .method2(0.0, 1.0, 0.0)
         .method16()
         .method2(1.0, 1.0, 0.0)
         .method16()
         .method17(BufferMode.BATCHED);
      bridgeextension_91.method33(bridge206);
      bridge206.bridge$uncache();
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
   public void method21(int number1) {
      this.field7 = number1;
   }

   @Generated
   public void method22(long number1) {
      this.field8 = number1;
   }

   @Generated
   public long method23() {
      return this.field8;
   }

   @Generated
   public void method24(boolean flag1) {
      this.field9 = flag1;
   }

   @Generated
   public void method25(HoverModel holograms21) {
      this.field10 = holograms21;
   }

   @Generated
   public void method26(boolean flag1) {
      this.field15 = flag1;
   }
}
