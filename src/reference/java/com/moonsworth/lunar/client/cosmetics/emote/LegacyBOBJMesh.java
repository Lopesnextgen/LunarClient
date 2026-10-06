package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.OpenGlHelperBridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.CompiledData;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;

public class LegacyBOBJMesh implements BOBJMesh {
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

   protected LegacyBOBJMesh(CompiledData compileddata1) {
      this.field1 = BufferUtils.createFloatBuffer(compileddata1.texData.length);
      this.field1.put(compileddata1.texData).flip();
      this.indices = BufferUtils.createIntBuffer(compileddata1.indexData.length);
      this.indices.put(compileddata1.indexData).flip();
      Ref.method3().bridge$submit(() -> this.method6(compileddata1));
      this.field2 = this.method5(compileddata1.posData);
   }

   @VersionGate(max = 7)
   @Override
   public void method2(AbstractRenderContext bridgeextension_91, ResourceLocationBridge horsestats142) {
      RenderTypeBridge bridge203 = LunarRenderTypes.field45.get(horsestats142);
      bridge203.bridge$setupRenderState();
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
      bridge203.bridge$clearRenderState();
   }

   @VersionGate(min = 6, max = 7)
   @Override
   public void method3(RenderTypeBridge bridge201, BridgeExtension2_11 bridgeextension2_112, RenderSystemBridge bridge123, int number4) {
      bridge201.bridge$setupRenderState();
      OpenGlHelperBridge bridge185 = Bridge.method22();
      int number6 = (Integer)bridgeextension2_112.method46().get();
      Bridge.method14().method9();
      GL11.glEnable(2912);
      bridge123.method47();
      bridge185.method10();
      bridge185.method12();
      bridge123.method4();
      Bridge.method42().method83().method17(bridgeextension2_112.method51().bridge$last().bridge$pose());
      GL15.glBindBuffer(34962, this.vertexBuffer);
      GL11.glVertexPointer(4, 5126, 0, 0L);
      GL15.glBindBuffer(34962, this.normalBuffer);
      GL11.glNormalPointer(5126, 0, 0L);
      GL13.glClientActiveTexture(33984 + bridge185.method6());
      GL15.glBindBuffer(34962, this.field3);
      GL11.glTexCoordPointer(2, 5126, 0, 0L);
      GL13.glMultiTexCoord2s(33984 + bridge185.method5(), (short)(number6 & 0xFF), (short)(number6 >> 16 & 0xFF));
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32885);
      GL11.glEnableClientState(32888);
      GL15.glBindBuffer(34963, this.indexBuffer);
      ExternalLinkRegistry.method2(Fishing2Extension.class).ifPresent(Fishing2Extension::beforeOBJMeshRender);
      GL11.glDrawElements(4, this.indices.capacity(), 5125, 0L);
      GL15.glBindBuffer(34963, 0);
      GL15.glBindBuffer(34962, 0);
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32885);
      GL11.glDisableClientState(32888);
      bridge123.method5();
      GL11.glDisable(2912);
      bridge201.bridge$clearRenderState();
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

   private AxisAlignedBBBridge method5(float[] items1) {
      float value2 = Float.MAX_VALUE;
      float value3 = Float.MAX_VALUE;
      float value4 = Float.MAX_VALUE;
      float value5 = -Float.MAX_VALUE;
      float value6 = -Float.MAX_VALUE;
      float value7 = -Float.MAX_VALUE;

      for (int index8 = 0; index8 < items1.length / 4; index8++) {
         float value9 = items1[index8 * 4 + 3];
         float value10 = items1[index8 * 4] / value9;
         float value11 = items1[index8 * 4 + 1] / value9;
         float value12 = items1[index8 * 4 + 2] / value9;
         if (value10 < value2) {
            value2 = value10;
         }

         if (value10 > value5) {
            value5 = value10;
         }

         if (value11 < value3) {
            value3 = value11;
         }

         if (value11 > value6) {
            value6 = value11;
         }

         if (value12 < value4) {
            value4 = value12;
         }

         if (value12 > value7) {
            value7 = value12;
         }
      }

      return Bridge.method8().method45(value2, value3, value4, value5, value6, value7);
   }

   private void method6(@NotNull CompiledData compileddata1) {
      FloatBuffer floatbuffer2 = BufferUtils.createFloatBuffer(compileddata1.posData.length);
      floatbuffer2.put(compileddata1.posData).flip();
      FloatBuffer floatbuffer3 = BufferUtils.createFloatBuffer(compileddata1.normData.length);
      floatbuffer3.put(compileddata1.normData).flip();
      this.vertexBuffer = GL15.glGenBuffers();
      GL15.glBindBuffer(34962, this.vertexBuffer);
      GL15.glBufferData(34962, floatbuffer2, 35048);
      this.normalBuffer = GL15.glGenBuffers();
      GL15.glBindBuffer(34962, this.normalBuffer);
      GL15.glBufferData(34962, floatbuffer3, 35044);
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
