package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.RenderStateLifecycleBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_15;
import com.moonsworth.lunar.bridge.Bridge2_2;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge8Handler2;
import com.moonsworth.lunar.bridge.DepthTestMode;
import com.moonsworth.lunar.bridge.Bridge_45;
import com.moonsworth.lunar.bridge.MixinHelper7$Type;
import com.moonsworth.lunar.bridge.MixinHelper7$Type2;
import com.moonsworth.lunar.bridge.MixinHelper7$Type3;
import com.moonsworth.lunar.bridge.MixinHelper7$Type4;
import com.moonsworth.lunar.bridge.MixinHelper7$Type5;
import com.moonsworth.lunar.bridge.MixinHelper7$Type6;
import com.moonsworth.lunar.bridge.MixinHelper7$Type8;
import com.moonsworth.lunar.bridge.RenderTypeBuilder;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Shader;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class LegacyRenderTypeFactory extends RenderTypeBuilder {
   public static final GlStateRecorder field9 = new GlStateRecorder();
   private static Map<ResourceLocationBridge, Bridge19Handler> field10;
   private static final boolean field11 = false;
   private static final RenderStateLifecycleBridge field12 = new LegacyRenderTypeFactory.ShadeModelStateShard(7424);
   private static final RenderStateLifecycleBridge field13 = new LegacyRenderTypeFactory.ShadeModelStateShard(7425);
   private static final RenderStateLifecycleBridge field14 = new LegacyRenderTypeFactory.TextureStateShard(false);
   private static final Bridge19Task field15 = new Bridge19Task(() -> method5(8.0F), LegacyRenderTypeFactory::method6);
   private static final Bridge19Task field16 = new Bridge19Task(() -> method5(0.16F), LegacyRenderTypeFactory::method6);
   private static final RenderStateLifecycleBridge field17 = new LegacyRenderTypeFactory.LightmapStateShard(true);
   private static final RenderStateLifecycleBridge field18 = new LegacyRenderTypeFactory.LightingStateShard(true);
   private static final RenderStateLifecycleBridge field19 = new LegacyRenderTypeFactory.CullStateShard(false);
   private static final RenderStateLifecycleBridge field20 = new LegacyRenderTypeFactory.CullStateShard(true);
   private static final RenderStateLifecycleBridge field21 = new LegacyRenderTypeFactory.ColorMaskStateShard(false, false, false, true);
   private static final RenderStateLifecycleBridge field22 = new LegacyRenderTypeFactory.ColorMaskStateShard(false, false, false, false);
   private static final RenderStateLifecycleBridge field23 = new LegacyRenderTypeFactory.ColorMaskStateShard(true, true, true, false);
   private static final RenderStateLifecycleBridge field24 = new LegacyRenderTypeFactory.DepthMaskStateShard(false);
   private static final Bridge19Task field25 = new Bridge19Task(() -> {
      Bridge.method42().method4();
      Bridge.method42().bridge$scale(0.99975586F, 0.99975586F, 0.99975586F);
   }, () -> Bridge.method42().method5());
   private static final Bridge19Task field26 = new Bridge19Task(() -> {
      Bridge3_24 var0 = ThreadModuleDump63.method3().bridge$getLevelRenderer().bridge$entityTarget();
      if (var0 != null) {
         ThreadModuleDump63.method3().method1(var0, false);
      }
   }, () -> ThreadModuleDump63.method3().method1(ThreadModuleDump63.method3().bridge$getMainRenderTarget(), false));
   @Nullable
   protected LegacyRenderTypeFactory.MixinHelper7$Type4 field27;
   @Nullable
   protected LegacyRenderTypeFactory.MixinHelper7$Type2 field28;

   @Override
   public RenderTypeBuilder method9(@Nullable LegacyRenderTypeFactory.MixinHelper7$Type4 var1) {
      this.field27 = var1;
      return this;
   }

   @Override
   public RenderTypeBuilder method10(@Nullable LegacyRenderTypeFactory.MixinHelper7$Type2 var1) {
      this.field28 = var1;
      return this;
   }

   public static void method3(ResourceLocationBridge var0) {
      field10.remove(var0);
   }

   @Override
   public RenderLayerBridge method15(Bridge_45 var1, String var2, int var3, boolean var4, boolean var5, MixinHelper7$Type8 var6) {
      Builder var7 = ImmutableList.builder();
      if (this.field1 == MixinHelper7$Type3.LIGHTMAP) {
         var7.add(field17);
      }

      if (this.field3 == MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING
         || this.field3 == MixinHelper7$Type.VIEW_OFFSET_Z_LAYERING_FORWARD) {
         var7.add(field25);
      }

      if (this.field4.method1() && this.field4.method6() == MixinHelper7$Type6.OUTLINE_TARGET) {
         var7.add(field26);
      } else if (this.field4.method2()) {
         var7.add((Bridge19Task2)this.field4.method7());
      }

      if (this.field5 == MixinHelper7$Type5.GLINT_TEXTURING) {
         var7.add(field15);
      } else if (this.field5 == MixinHelper7$Type5.ENTITY_GLINT_TEXTURING
         || this.field5 == MixinHelper7$Type5.ARMOR_ENTITY_GLINT_TEXTURING) {
         var7.add(field16);
      }

      if (this.field27 == MixinHelper7$Type4.FLAT) {
         var7.add(field12);
      } else if (this.field27 == MixinHelper7$Type4.SMOOTH) {
         var7.add(field13);
      }

      if (this.field6 != null && !this.field6.isDefault()) {
         var7.add(new LegacyRenderTypeFactory.LineWidthStateShard(this.field6.method3()));
      }

      RenderPipelineBridgeAdapter var8 = (RenderPipelineBridgeAdapter)var1;
      boolean var9 = var8.method2() != null;
      boolean var10 = false;
      if (this.field7 != null) {
         if (this.field7.method5()) {
            var10 = true;
         } else if (this.field7.method8() == null) {
            var10 = true;
         } else {
            var7.add(
               new LegacyRenderTypeFactory.BoundTextureStateShard(
                  this.field7.method8(), this.field7.method7(), this.field7.method6()
               )
            );
         }
      } else {
         var10 = true;
      }

      if (var10 && !var9) {
         var7.add(field14);
      }

      if (this.field28 == MixinHelper7$Type2.DEFAULT) {
         var7.add(field18);
      }

      if (this.field8 != null) {
         var7.add(new LegacyRenderTypeFactory.RescaleNormalStateShard(this.field8));
      }

      Bridge2_2 var11 = var8.bridge$shaderDefines();
      Bridge2_15 var12 = var8.bridge$blendFunction();
      if (var12 != null) {
         var7.add(new LegacyRenderTypeFactory.BlendStateShard(var12.method1().getGlId(), var12.method3().getGlId(), var12.method2().getGlId(), var12.method4().getGlId()));
      } else {
         var7.add(new LegacyRenderTypeFactory.BlendStateShard());
      }

      if (!var8.bridge$cull()) {
         var7.add(field19);
      } else if (var8.method1()) {
         var7.add(field20);
      }

      if (!var8.bridge$writeColor()) {
         if (var8.bridge$writeAlpha()) {
            var7.add(field21);
         } else {
            var7.add(field22);
         }
      } else if (!var8.bridge$writeAlpha()) {
         var7.add(field23);
      }

      if (!var8.bridge$writeDepth()) {
         var7.add(field24);
      }

      DepthTestMode var13 = var8.bridge$depthTestFunction();
      if (var13 != null) {
         var7.add(new LegacyRenderTypeFactory.DepthTestStateShard(var13.getGlId()));
      }

      float var14 = var8.bridge$getDepthBiasScaleFactor();
      float var15 = var8.bridge$getDepthBiasConstant();
      if (var14 != 0.0F || var15 != 0.0F) {
         var7.add(new LegacyRenderTypeFactory.PolygonOffsetStateShard(var14, var15));
      }

      if (var11 != null && var11.bridge$values().containsKey("ALPHA_CUTOUT")) {
         String var16 = var11.bridge$values().get("ALPHA_CUTOUT");
         float var17 = Float.parseFloat(var16);
         var7.add(new LegacyRenderTypeFactory.AlphaTestStateShard(var17 != 0.0F, 516, var17));
      }

      if (var9) {
         if (field10 == null) {
            field10 = new HashMap<>();
         }

         Bridge19Handler var18 = field10.computeIfAbsent(var8.method2(), var0 -> {
            String var1x = var0.bridge$getDomain() + "/" + var0.bridge$getPath();
            Minecraft var2x = Minecraft.getMinecraft();
            return Bridge19Handler.method1(Shader.newInstance(var2x.getResourceManager(), var1x, var2x.framebufferMc, var2x.framebufferMc));
         });
         var7.add(var18);
      }

      return new Bridge20Iterator(var1, var2, var7.build());
   }

   private static void method5(float var0) {
      RenderSystemBridge var1 = Bridge.method42();
      var1.method24(5890);
      var1.method4();
      var1.bridge$loadIdentity();
      long var2 = System.currentTimeMillis() * 8L;
      float var4 = (float)(var2 % 110000L) / 110000.0F;
      float var5 = (float)(var2 % 30000L) / 30000.0F;
      var1.bridge$translate(-var4, var5, 0.0F);
      var1.method6(10.0F, 0.0F, 0.0F, 1.0F);
      var1.bridge$scale(var0, var0, var0);
      var1.method24(5888);
   }

   private static void method6() {
      RenderSystemBridge var0 = Bridge.method42();
      var0.method24(5890);
      var0.method5();
      var0.method24(5888);
   }

   private static class PolygonOffsetStateShard implements RenderStateLifecycleBridge {
      private final float field1;
      private final float field2;

      @Override
      public void bridge$setupState() {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.doPolygonOffset(this.field1, this.field2);
            GlStateManager.enablePolygonOffset();
         } else {
            GL11.glPolygonOffset(this.field1, this.field2);
            GL11.glEnable(32823);
         }
      }

      @Override
      public void bridge$clearState() {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.doPolygonOffset(0.0F, 0.0F);
            GlStateManager.disablePolygonOffset();
         } else {
            GL11.glPolygonOffset(0.0F, 0.0F);
            GL11.glDisable(32823);
         }
      }

      @Generated
      public PolygonOffsetStateShard(float var1, float var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }

   private static class BlendStateShard implements RenderStateLifecycleBridge {
      public final boolean field1;
      public final int field2;
      public final int field3;
      public final int field4;
      public final int field5;
      public boolean field6;
      public int field7;
      public int field8;
      public int field9;
      public int field10;
      private boolean changed = false;

      public BlendStateShard() {
         this.field1 = false;
         this.field2 = 0;
         this.field3 = 0;
         this.field4 = 0;
         this.field5 = 0;
      }

      public BlendStateShard(int var1, int var2, int var3, int var4) {
         this.field1 = true;
         this.field2 = var1;
         this.field3 = var2;
         this.field4 = var3;
         this.field5 = var4;
      }

      @Override
      public void bridge$setupState() {
         GlStateRecorder var1 = LegacyRenderTypeFactory.field9;
         this.field6 = var1.field16;
         this.field7 = var1.field17;
         this.field8 = var1.field18;
         this.field9 = var1.field19;
         this.field10 = var1.field20;
         if (this.field6 != this.field1) {
            this.set(this.field1);
         }

         if (!this.field1 || this.field7 == this.field2 && this.field8 == this.field3 && this.field9 == this.field4 && this.field10 == this.field5) {
            this.changed = false;
         } else {
            OpenGlHelper.glBlendFunc(this.field2, this.field4, this.field3, this.field5);
            this.changed = true;
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field6 != this.field1) {
            this.set(this.field6);
         }

         if (this.changed) {
            OpenGlHelper.glBlendFunc(this.field7, this.field9, this.field8, this.field10);
         }
      }

      private void set(boolean var1) {
         if (var1) {
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               GlStateManager.enableBlend();
            } else {
               GL11.glEnable(3042);
            }
         } else if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.disableBlend();
         } else {
            GL11.glDisable(3042);
         }
      }
   }

   private static class BoundTextureStateShard extends LegacyRenderTypeFactory.TextureStateShard {
      private final ResourceLocationBridge field3;
      private final boolean field4;
      private final boolean field5;

      public BoundTextureStateShard(ResourceLocationBridge var1, boolean var2, boolean var3) {
         super(true);
         this.field3 = var1;
         this.field4 = var2;
         this.field5 = var3;
      }

      @Override
      public void bridge$setupState() {
         super.bridge$setupState();
         Bridge8Handler2 var1 = ThreadModuleDump63.method3().bridge$getTextureManager();
         var1.bridge$bindTexture(this.field3);
         var1.bridge$getTexture(this.field3).bridge$setFilter(this.field4, this.field5);
      }

      @Override
      public void bridge$clearState() {
         super.bridge$clearState();
      }
   }

   private static class AlphaTestStateShard implements RenderStateLifecycleBridge {
      private final boolean field1;
      private final int field2;
      private final float field3;
      private boolean field4;
      private int field5;
      private float field6;
      private boolean changed = false;

      public AlphaTestStateShard(boolean var1, int var2, float var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      @Override
      public void bridge$setupState() {
         GlStateRecorder var1 = LegacyRenderTypeFactory.field9;
         this.field4 = var1.field3;
         this.field5 = var1.field4;
         this.field6 = var1.field5;
         if (this.field4 != this.field1) {
            this.set(this.field1);
         }

         if (!this.field1 || this.field5 == this.field2 && this.field6 == this.field3) {
            this.changed = false;
         } else {
            this.method1(this.field2, this.field3);
            this.changed = true;
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field4 != this.field1) {
            this.set(this.field4);
         }

         if (this.changed) {
            this.method1(this.field5, this.field6);
         }
      }

      private void method1(int var1, float var2) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.alphaFunc(var1, var2);
         } else {
            GL11.glAlphaFunc(var1, var2);
         }
      }

      private void set(boolean var1) {
         if (var1) {
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               GlStateManager.enableAlpha();
            } else {
               GL11.glEnable(3008);
            }
         } else if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.disableAlpha();
         } else {
            GL11.glDisable(3008);
         }
      }
   }

   private static class ColorMaskStateShard implements RenderStateLifecycleBridge {
      private final boolean field1;
      private final boolean field2;
      private final boolean field3;
      private final boolean field4;
      private boolean field5;
      private boolean field6;
      private boolean field7;
      private boolean field8;
      private boolean changed = false;

      public ColorMaskStateShard(boolean var1, boolean var2, boolean var3, boolean var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      @Override
      public void bridge$setupState() {
         GlStateRecorder var1 = LegacyRenderTypeFactory.field9;
         this.field5 = var1.field11;
         this.field6 = var1.field12;
         this.field7 = var1.field13;
         this.field8 = var1.field14;
         this.changed = this.field5 != this.field1 || this.field6 != this.field2 || this.field7 != this.field3 || this.field8 != this.field4;
         if (this.changed) {
            this.method1(this.field1, this.field2, this.field3, this.field4);
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.changed) {
            this.method1(this.field5, this.field6, this.field7, this.field8);
         }
      }

      private void method1(boolean var1, boolean var2, boolean var3, boolean var4) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.colorMask(var1, var2, var3, var4);
         } else {
            GL11.glColorMask(var1, var2, var3, var4);
         }
      }
   }

   private static class LineWidthStateShard implements RenderStateLifecycleBridge {
      private final float field1;
      private float field2;

      public LineWidthStateShard(float var1) {
         this.field1 = var1;
      }

      @Override
      public void bridge$setupState() {
         this.field2 = LegacyRenderTypeFactory.field9.lineWidth;
         if (this.field2 != this.field1) {
            GL11.glLineWidth(this.field1);
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field2 != this.field1) {
            GL11.glLineWidth(this.field2);
         }
      }
   }

   private static class CullStateShard implements RenderStateLifecycleBridge {
      private boolean field1;
      private final boolean cull;

      public CullStateShard(boolean var1) {
         this.cull = var1;
      }

      @Override
      public void bridge$setupState() {
         this.field1 = LegacyRenderTypeFactory.field9.field2;
         if (this.field1 != this.cull) {
            this.set(this.cull);
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field1 != this.cull) {
            this.set(this.field1);
         }
      }

      private void set(boolean var1) {
         if (var1) {
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               GlStateManager.enableCull();
            } else {
               GL11.glEnable(2884);
            }
         } else if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.disableCull();
         } else {
            GL11.glDisable(2884);
         }
      }
   }

   private static class RescaleNormalStateShard implements RenderStateLifecycleBridge {
      private final boolean field1;
      private boolean field2;

      public RescaleNormalStateShard(boolean var1) {
         this.field1 = var1;
      }

      @Override
      public void bridge$setupState() {
         this.field2 = LegacyRenderTypeFactory.field9.field21;
         if (this.field2 != this.field1) {
            this.set(this.field1);
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field2 != this.field1) {
            this.set(this.field2);
         }
      }

      private void set(boolean var1) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            if (var1) {
               GlStateManager.enableRescaleNormal();
            } else {
               GlStateManager.disableRescaleNormal();
            }
         } else if (var1) {
            GL11.glEnable(32826);
         } else {
            GL11.glDisable(32826);
         }
      }
   }

   private static class DepthTestStateShard implements RenderStateLifecycleBridge {
      private final int field1;
      private boolean field2;
      private int field3;
      private boolean field4 = false;

      public DepthTestStateShard(int var1) {
         this.field1 = var1;
      }

      @Override
      public void bridge$setupState() {
         GlStateRecorder var1 = LegacyRenderTypeFactory.field9;
         this.field2 = var1.field6;
         this.field3 = var1.field7;
         boolean var2 = this.field1 != 519;
         if (this.field2 != var2) {
            this.set(var2);
         }

         if (var2 && this.field3 != this.field1) {
            this.set(this.field1);
            this.field4 = true;
         } else {
            this.field4 = false;
         }
      }

      @Override
      public void bridge$clearState() {
         boolean var1 = this.field1 != 519;
         if (this.field2 != var1) {
            this.set(this.field2);
         }

         if (this.field4) {
            this.set(this.field3);
         }
      }

      private void set(boolean var1) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            if (var1) {
               GlStateManager.enableDepth();
            } else {
               GlStateManager.disableDepth();
            }
         } else if (var1) {
            GL11.glEnable(2929);
         } else {
            GL11.glDisable(2929);
         }
      }

      private void set(int var1) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.depthFunc(var1);
         } else {
            GL11.glDepthFunc(var1);
         }
      }
   }

   private static class ShadeModelStateShard implements RenderStateLifecycleBridge {
      private final int field1;
      private int field2;

      public ShadeModelStateShard(int var1) {
         this.field1 = var1;
      }

      @Override
      public void bridge$setupState() {
         this.field2 = LegacyRenderTypeFactory.field9.field1;
         if (this.field2 != this.field1) {
            this.set(this.field1);
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field2 != this.field1) {
            this.set(this.field2);
         }
      }

      private void set(int var1) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.shadeModel(var1);
         } else {
            GL11.glShadeModel(var1);
         }
      }
   }

   private static class LightingStateShard implements RenderStateLifecycleBridge {
      private final boolean lighting;
      private boolean field1;

      public LightingStateShard(boolean var1) {
         this.lighting = var1;
      }

      @Override
      public void bridge$setupState() {
         this.field1 = LegacyRenderTypeFactory.field9.lighting;
         if (this.field1 != this.lighting) {
            this.set(this.lighting);
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field1 != this.lighting) {
            this.set(this.field1);
         }
      }

      public void set(boolean var1) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            if (var1) {
               GlStateManager.enableLighting();
            } else {
               GlStateManager.disableLighting();
            }
         } else if (var1) {
            GL11.glEnable(2896);
         } else {
            GL11.glDisable(2896);
         }
      }
   }

   private static class LightmapStateShard implements RenderStateLifecycleBridge {
      private boolean field1;
      private final boolean field2;

      public LightmapStateShard(boolean var1) {
         this.field2 = var1;
      }

      @Override
      public void bridge$setupState() {
         this.field1 = LegacyRenderTypeFactory.field9.field10;
         if (this.field1 != this.field2) {
            this.set(this.field2);
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field1 != this.field2) {
            this.set(this.field1);
         }
      }

      private void set(boolean var1) {
         EntityRenderer var2 = Minecraft.getMinecraft().entityRenderer;
         if (var1) {
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               var2.enableLightmap();
            } else {
               var2.enableLightmap(0.0);
            }
         } else if (ThreadModuleDump63.MC_VERSION >= 1) {
            var2.disableLightmap();
         } else {
            var2.disableLightmap(0.0);
         }
      }
   }

   private static class DepthMaskStateShard implements RenderStateLifecycleBridge {
      private final boolean field1;
      private boolean field2;

      public DepthMaskStateShard(boolean var1) {
         this.field1 = var1;
      }

      @Override
      public void bridge$setupState() {
         this.field2 = LegacyRenderTypeFactory.field9.field15;
         if (this.field2 != this.field1) {
            this.set(this.field1);
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field2 != this.field1) {
            this.set(this.field2);
         }
      }

      private void set(boolean var1) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.depthMask(var1);
         } else {
            GL11.glDepthMask(var1);
         }
      }
   }

   private static class TextureStateShard implements RenderStateLifecycleBridge {
      private final boolean field1;
      private boolean field2;

      public TextureStateShard(boolean var1) {
         this.field1 = var1;
      }

      @Override
      public void bridge$setupState() {
         this.field2 = LegacyRenderTypeFactory.field9.method3();
         if (this.field2 != this.field1) {
            this.set(this.field1);
         }
      }

      @Override
      public void bridge$clearState() {
         if (this.field2 != this.field1) {
            this.set(this.field2);
         }
      }

      private void set(boolean var1) {
         if (var1) {
            if (ThreadModuleDump63.MC_VERSION >= 1) {
               GlStateManager.enableTexture2D();
            } else {
               GL11.glEnable(3553);
            }
         } else if (ThreadModuleDump63.MC_VERSION >= 1) {
            GlStateManager.disableTexture2D();
         } else {
            GL11.glDisable(3553);
         }
      }
   }
}
