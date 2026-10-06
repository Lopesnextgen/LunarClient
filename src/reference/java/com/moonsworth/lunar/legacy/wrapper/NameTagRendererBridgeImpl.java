package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge8Extension2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.Bridge_46;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.mod.render.nametag.Nametag;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.click.Click11;
import com.moonsworth.lunar.client.util.click.Click4;
import com.moonsworth.lunar.ichor.Annotation2;
import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.format.TextDecoration.State;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.BlendState;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class NameTagRendererBridgeImpl implements Bridge_46 {
   private static final Cleaner field1 = Cleaner.create();
   private final FloatBuffer field2 = BufferUtils.createFloatBuffer(16);
   private final Matrix4f field3 = new Matrix4f();
   private float yaw;
   private float pitch;
   private final Set<Click4> field4 = Collections.newSetFromMap(new WeakHashMap<>());
   private final Set<Integer> field5 = ConcurrentHashMap.newKeySet();
   private int field6 = -1;
   private int field7 = -1;

   public void method1(Component var1, boolean var2, CosmeticManager.Data var3, int var4, int var5, boolean var6) {
      Nametag var7 = ThreadModuleDump63.method4().method40().method51();
      float var8 = var7.getBackgroundOpacity();
      boolean var9 = var7.isEnabled() && var7.getNametagShadow().get();
      byte var10 = 0;
      if (AdventureTextBridge.getTextContent(var1).equals("deadmau5")) {
         var10 = -10;
      }

      ByteBuffer var11;
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         var11 = Tessellator.theMinecraft.worldRenderer.byteBuffer;
      } else {
         var11 = Tessellator.theMinecraft.byteBuffer$v1_7;
      }

      var11.position(0);
      var11.limit(var11.capacity());
      FloatBuffer var12 = var11.asFloatBuffer();
      int var13 = (int)(AdventureTextBridge.getTextWidth(var1, ThreadModuleDump63.method3().bridge$getFontRenderer()) / 2.0F);
      int var14 = var13 + 1 + (var2 ? 6 : 0);
      int var15 = ThreadModuleDump23.method11(0.0F, 0.0F, 0.0F, var8);
      float var16 = Float.intBitsToFloat(Integer.reverseBytes(var15 << 8 | var15 >>> 24));
      this.method2(var12, -var14, -1 + var10, 0.0F, 0.0F, 0.0F, var16);
      this.method2(var12, -var14, 8 + var10, 0.0F, 0.0F, 0.0F, var16);
      this.method2(var12, var14, 8 + var10, 0.0F, 0.0F, 0.0F, var16);
      this.method2(var12, var14, -1 + var10, 0.0F, 0.0F, 0.0F, var16);
      Wrapper$Data3 var17 = new Wrapper$Data3(0, 4, null);
      List var18 = TextMeshBuilder.method5(var12, (-(var13 * 2) + (var2 ? 12 : 0)) / 2, var10, var1, var9, var5, 32, false, var6);
      Wrapper$Data3 var19 = null;
      Wrapper$Data3 var20 = null;
      if (var2) {
         int var21 = ThreadModuleDump23.method11(var3.method5(), var3.method6(), var3.method7(), 1.0F);
         int var22 = var12.position() / 6;
         this.method3(var12, (-(var13 * 2) + 12) / 2.0F - 12.0F, var10 - 1.5F, 0.0F, 10.0F, 10.0F, var21);
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(CosmeticManager.field40);
         var19 = new Wrapper$Data3(var22, 4, CosmeticManager.field40);
         if (var3.method1()) {
            int var23 = var12.position() / 6;
            this.method3(var12, (-(var13 * 2) + 12) / 2.0F - 12.0F + 7.25F, var10 - 1.5F + 1.5F, -1.0F, 3.0F, 3.0F, var3.method9());
            ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(CosmeticManager.field37);
            var20 = new Wrapper$Data3(var23, 4, CosmeticManager.field37);
         }
      }

      List var25 = TextMeshBuilder.method5(var12, (-(var13 * 2) + (var2 ? 12 : 0)) / 2, var10, var1, var9, var5, 255, true, var6);
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32888);
      GL11.glEnableClientState(32886);
      GL11.glVertexPointer(3, 5126, 24, var11);
      var11.position(12);
      GL11.glTexCoordPointer(2, 5126, 24, var11);
      var11.position(20);
      GL11.glColorPointer(4, 5121, 24, var11);

      for (Wrapper$Data3 var31 : var18) {
         method9(var31);
      }

      for (Wrapper$Data3 var32 : var25) {
         method9(var32);
      }

      Bridge.method42().method84().method2(var4);
      GL11.glDisable(2896);
      GL11.glDepthMask(false);
      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL14.glBlendFuncSeparate(770, 771, 1, 0);
      this.method8();
      GL11.glDrawArrays(7, var17.method1(), var17.count());

      for (Wrapper$Data3 var33 : var18) {
         if (var33.method2() == null) {
            this.method8();
            GL11.glDrawArrays(7, var33.method1(), var33.count());
         } else {
            int var24 = ((Bridge8Extension2)ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var33.method2())).lunar$getHandle();
            GL11.glBindTexture(3553, var24);
            GL11.glDrawArrays(7, var33.method1(), var33.count());
         }
      }

      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      if (var19 != null) {
         int var29 = ((Bridge8Extension2)ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var19.method2())).lunar$getHandle();
         GL11.glBindTexture(3553, var29);
         GL11.glDrawArrays(7, var19.method1(), var19.count());
         if (var20 != null) {
            int var34 = ((Bridge8Extension2)ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var20.method2())).lunar$getHandle();
            GL11.glBindTexture(3553, var34);
            GL11.glDrawArrays(7, var20.method1(), var20.count());
         }
      }

      for (Wrapper$Data3 var35 : var25) {
         if (var35.method2() == null) {
            this.method8();
            GL11.glDrawArrays(7, var35.method1(), var35.count());
         } else {
            int var36 = ((Bridge8Extension2)ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var35.method2())).lunar$getHandle();
            GL11.glBindTexture(3553, var36);
            GL11.glDrawArrays(7, var35.method1(), var35.count());
         }
      }

      GL11.glEnable(2896);
      GL11.glDisable(3042);
      Bridge.method42().method84().method3(var4);
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32888);
      GL11.glDisableClientState(32886);
   }

   private void method2(FloatBuffer var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      var1.put(var2);
      var1.put(var3);
      var1.put(var4);
      var1.put(var5);
      var1.put(var6);
      var1.put(var7);
   }

   private void method3(FloatBuffer var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      float var8 = Float.intBitsToFloat(Integer.reverseBytes(var7 << 8 | var7 >>> 24));
      this.method2(var1, var2, var3, var4, 0.0F, 0.0F, var8);
      this.method2(var1, var2, var3 + var6, var4, 0.0F, 1.0F, var8);
      this.method2(var1, var2 + var5, var3 + var6, var4, 1.0F, 1.0F, var8);
      this.method2(var1, var2 + var5, var3, var4, 1.0F, 0.0F, var8);
   }

   public int method4(Component var1, CosmeticManager.Data var2, Click4 var3, int var4, boolean var5, boolean var6) {
      if (var3.field2 != -1) {
         this.field5.remove(var3.field2);
         Bridge.method42().method84().OHROCHICOIOICHOCRROORRCIIICIHO(var3.field2, 1);
      }

      int var7 = Bridge.method42().method84().method6();
      var3.field2 = var7;
      var3.field4 = var1;
      this.field4.add(var3);
      field1.register(var3, () -> ThreadModuleDump63.method3().bridge$submit(() -> {
         if (this.field5.remove(var7)) {
            Bridge.method42().method84().OHROCHICOIOICHOCRROORRCIIICIHO(var7, 1);
         }
      }));
      this.method1(var1, var6, var2, var7, var4, var5);
      return var7;
   }

   private void method5() {
      Bridge2_43 var1 = ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher();
      float var2 = (float)var1.bridge$playerViewX();
      float var3 = (float)var1.bridge$playerViewY();
      if (HologramsIterator2.field7) {
         var3 = HologramsIterator2.playerViewY;
         var2 = HologramsIterator2.playerViewX;
      }

      if (-var3 != this.yaw || var2 != this.pitch) {
         Matrix4f var4 = this.field3;
         var4.identity();
         var4.rotateY((float)Math.toRadians(-var3));
         var4.rotateX((float)Math.toRadians(var2));
         var4.scale(-0.02666667F, -0.02666667F, 0.02666667F);
         var4.get(this.field2);
         this.pitch = var2;
         this.yaw = -var3;
      }

      Bridge.method42().method70(this.field2);
   }

   @Override
   public boolean method1(Component var1, double var2, double var4, double var6, BridgeExtension var8, boolean var9) {
      if (!ThreadModuleDump63.method4().method41().method7().method16().get()) {
         return false;
      } else if (var8 instanceof BridgeExtension2_5 var10 && var10.bridge$getDisplayNameComponent() != var1) {
         return false;
      } else if (!(var8 instanceof Click11 var25)) {
         return false;
      } else {
         UUID var11 = var8.bridge$getUniqueID();
         CosmeticManager.Data var12 = ThreadModuleDump63.method4().method53().method63().get(var11);
         boolean var13 = var8 instanceof Bridge6_10 && var9 && var12 != null;
         boolean var14 = var13 && var12.method11() != null && (var12.method8() || ThreadModuleDump63.method4().method41().method6().method44().get());
         if (var14) {
            return false;
         }

         boolean var15 = var13 && (var12.method8() || ThreadModuleDump63.method4().method41().method6().method43().get());
         Click4 var16 = var25.lunar$getCache();
         NickHider var17 = ThreadModuleDump63.method4().method40().method41();
         var1 = var17.transformComponentCached(var1);
         if (var1.decorations().get(TextDecoration.OBFUSCATED) == State.TRUE) {
            return false;
         }

         boolean var18 = false;
         byte var19 = -1;
         boolean var20 = false;
         if (var16.field3 != var15) {
            var18 = true;
            var16.field3 = var15;
         }

         int var21;
         if (var16.field4 == var1 && var16.field2 != -1 && !var18) {
            var21 = var16.field2;
         } else {
            var21 = this.method4(var1, var12, var16, var19, var20, var15);
         }

         RenderSystemBridge.Extension var22 = Bridge.method42().method84();
         var22.CROORCRRCORRICIOIRIICOOICHOHOO();
         var22.bridge$translate((float)var2, (float)var4, (float)var6);
         this.method5();
         var22.IRCHCHOHRRIOORHRCHRIHIOHRCIHRH(var21);
         var22.IORRRICHRIHCCIORRIICIIORIRRRRC();
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            this.method7();
         }

         GlStateRecorder var23 = LegacyRenderTypeFactory.field9;
         var23.lighting = true;
         var23.field15 = true;
         var23.field16 = false;
         var23.field17 = 770;
         var23.field19 = 771;
         var23.field18 = 1;
         var23.field20 = 0;
         return true;
      }
   }

   @Override
   public void free() {
      this.field4.forEach(var0 -> {
         if (var0.field2 != -1) {
            Bridge.method42().method84().OHROCHICOIOICHOCRROORRCIIICIHO(var0.field2, 1);
            var0.field2 = -1;
         }
      });
      this.field4.clear();
      this.field5.clear();
   }

   @Annotation2(min = 1)
   private void method7() {
      GlStateManager.resetColor();
      GlStateManager.textureState[0].textureName = 0;
      GlStateManager.textureState[0].texture2DState.currentState = true;
      GlStateManager.lightingState.currentState = true;
      GlStateManager.depthState.maskEnabled = true;
      GlStateManager.depthState.depthTest.currentState = true;
      BlendState var1 = GlStateManager.blendState;
      var1.blend.currentState = false;
      var1.srcFactor = 770;
      var1.dstFactor = 771;
      var1.srcFactorAlpha = 1;
      var1.dstFactorAlpha = 0;
   }

   public void method8() {
      if (this.field6 == -1) {
         this.field6 = GL11.glGenTextures();
         GL11.glBindTexture(3553, this.field6);
         IntBuffer var1 = BufferUtils.createIntBuffer(1);
         var1.put(0, -1);
         GL11.glTexImage2D(3553, 0, 32856, 1, 1, 0, 6408, 5121, var1);
      } else {
         GL11.glBindTexture(3553, this.field6);
      }
   }

   private static void method9(Wrapper$Data3 var0) {
      if (var0.method2() != null) {
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(var0.method2());
      }
   }
}
