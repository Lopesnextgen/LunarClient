package com.moonsworth.lunar.client.util.colorsaturation;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.OpenGlHelperBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.CompiledData;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;

public class ColorsaturationHandler implements Colorsaturation {
   private final FloatBuffer field1;
   private final IntBuffer indices;
   private final AxisAlignedBBBridge field2;
   private int vertexBuffer;
   private int normalBuffer;
   private int field3;
   private int indexBuffer;

   @Override
   public int method1() {
      return this.field1.capacity() + this.indices.capacity();
   }

   protected ColorsaturationHandler(CompiledData var1) {
      this.field1 = BufferUtils.createFloatBuffer(var1.texData.length);
      this.field1.put(var1.texData).flip();
      this.indices = BufferUtils.createIntBuffer(var1.indexData.length);
      this.indices.put(var1.indexData).flip();
      ThreadModuleDump63.method3().bridge$submit(() -> this.method6(var1));
      this.field2 = this.method5(var1.posData);
   }

   @Annotation2(max = 7)
   @Override
   public void method2(AbstractRenderContext var1, ResourceLocationBridge var2) {
      RenderLayerBridge var3 = LunarRenderTypes.field45.get(var2);
      var3.bridge$setupRenderState();
      GL15.glBindBuffer(34962, this.vertexBuffer);
      GL11.glVertexPointer(4, 5126, 0, 0L);
      GL15.glBindBuffer(34962, this.normalBuffer);
      GL11.glNormalPointer(5126, 0, 0L);
      GL15.glBindBuffer(34962, this.field3);
      GL11.glTexCoordPointer(2, 5126, 0, 0L);
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32885);
      GL11.glEnableClientState(32888);
      GL15.glBindBuffer(34963, this.indexBuffer);
      GL11.glDrawElements(4, this.indices.capacity(), 5125, 0L);
      GL15.glBindBuffer(34963, 0);
      GL15.glBindBuffer(34962, 0);
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32885);
      GL11.glDisableClientState(32888);
      var3.bridge$clearRenderState();
   }

   @Annotation2(min = 6, max = 7)
   @Override
   public void method3(RenderLayerBridge var1, BridgeExtension2_11 var2, RenderSystemBridge var3, int var4) {
      var1.bridge$setupRenderState();
      OpenGlHelperBridge var5 = Bridge.method22();
      int var6 = (Integer)var2.method46().get();
      Bridge.method14().method9();
      GL11.glEnable(2912);
      var3.method47();
      var5.method10();
      var5.method12();
      var3.method4();
      Bridge.method42().method83().method17(var2.method51().bridge$last().bridge$pose());
      GL15.glBindBuffer(34962, this.vertexBuffer);
      GL11.glVertexPointer(4, 5126, 0, 0L);
      GL15.glBindBuffer(34962, this.normalBuffer);
      GL11.glNormalPointer(5126, 0, 0L);
      GL13.glClientActiveTexture(33984 + var5.method6());
      GL15.glBindBuffer(34962, this.field3);
      GL11.glTexCoordPointer(2, 5126, 0, 0L);
      GL13.glMultiTexCoord2s(33984 + var5.method5(), (short)(var6 & 0xFF), (short)(var6 >> 16 & 0xFF));
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32885);
      GL11.glEnableClientState(32888);
      GL15.glBindBuffer(34963, this.indexBuffer);
      Fishing.method2(Fishing2Extension.class).ifPresent(Fishing2Extension::beforeOBJMeshRender);
      GL11.glDrawElements(4, this.indices.capacity(), 5125, 0L);
      GL15.glBindBuffer(34963, 0);
      GL15.glBindBuffer(34962, 0);
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32885);
      GL11.glDisableClientState(32888);
      var3.method5();
      GL11.glDisable(2912);
      var1.bridge$clearRenderState();
   }

   @Override
   public void delete() {
      GL15.glDeleteBuffers(this.vertexBuffer);
      GL15.glDeleteBuffers(this.normalBuffer);
      GL15.glDeleteBuffers(this.field3);
      GL15.glDeleteBuffers(this.indexBuffer);
   }

   @Override
   public AxisAlignedBBBridge method4() {
      return this.field2;
   }

   private AxisAlignedBBBridge method5(float[] var1) {
      float var2 = Float.MAX_VALUE;
      float var3 = Float.MAX_VALUE;
      float var4 = Float.MAX_VALUE;
      float var5 = -Float.MAX_VALUE;
      float var6 = -Float.MAX_VALUE;
      float var7 = -Float.MAX_VALUE;

      for (int var8 = 0; var8 < var1.length / 4; var8++) {
         float var9 = var1[var8 * 4 + 3];
         float var10 = var1[var8 * 4] / var9;
         float var11 = var1[var8 * 4 + 1] / var9;
         float var12 = var1[var8 * 4 + 2] / var9;
         if (var10 < var2) {
            var2 = var10;
         }

         if (var10 > var5) {
            var5 = var10;
         }

         if (var11 < var3) {
            var3 = var11;
         }

         if (var11 > var6) {
            var6 = var11;
         }

         if (var12 < var4) {
            var4 = var12;
         }

         if (var12 > var7) {
            var7 = var12;
         }
      }

      return Bridge.method8().method45(var2, var3, var4, var5, var6, var7);
   }

   private void method6(@NotNull CompiledData var1) {
      FloatBuffer var2 = BufferUtils.createFloatBuffer(var1.posData.length);
      var2.put(var1.posData).flip();
      FloatBuffer var3 = BufferUtils.createFloatBuffer(var1.normData.length);
      var3.put(var1.normData).flip();
      this.vertexBuffer = GL15.glGenBuffers();
      GL15.glBindBuffer(34962, this.vertexBuffer);
      GL15.glBufferData(34962, var2, 35048);
      this.normalBuffer = GL15.glGenBuffers();
      GL15.glBindBuffer(34962, this.normalBuffer);
      GL15.glBufferData(34962, var3, 35044);
      this.field3 = GL15.glGenBuffers();
      GL15.glBindBuffer(34962, this.field3);
      GL15.glBufferData(34962, this.field1, 35044);
      this.indexBuffer = GL15.glGenBuffers();
      GL15.glBindBuffer(34963, this.indexBuffer);
      GL15.glBufferData(34963, this.indices, 35044);
      GL15.glBindBuffer(34962, 0);
      GL15.glBindBuffer(34963, 0);
   }
}
