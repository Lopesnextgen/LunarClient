package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.FluentTessellatorBridge;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.LightTextureBridge;
import com.moonsworth.lunar.bridge.VertexFormatBridge;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(Tessellator.class)
public abstract class TessellatorMixin implements FluentTessellatorBridge {
   public TessellatorMixin() {
   }

   @VersionGate(1)
   @Shadow
   public abstract WorldRenderer getWorldRenderer();

   @VersionGate(min = 5)
   @Shadow
   public abstract WorldRenderer getBuffer$v1_12();

   @VersionGate(min = 1)
   @Shadow
   public abstract void draw();

   private WorldRenderer impl$getBuffer() {
      return Ref.MC_VERSION >= 5 ? this.getBuffer$v1_12() : this.getWorldRenderer();
   }

   public void bridge$begin(DrawMode bridgetype_61, VertexFormatBridge bridge_632) {
      this.impl$getBuffer().begin(bridgetype_61.getGLMode(false), (VertexFormat)bridge_632);
   }

   public FluentTessellatorBridge bridge$pos(float value1, float value2, float value3) {
      this.impl$getBuffer().pos(value1, value2, value3);
      return this;
   }

   public FluentTessellatorBridge bridge$pos(MixinHelper_21 mixinhelper_211, float value2, float value3, float value4) {
      throw new RuntimeException("Tessellator pos with matrix's are unsupported in legacy!");
   }

   public FluentTessellatorBridge bridge$color(float value1, float value2, float value3, float value4) {
      this.impl$getBuffer().color(value1, value2, value3, value4);
      return this;
   }

   public FluentTessellatorBridge bridge$uv(float value1, float value2) {
      this.impl$getBuffer().tex(value1, value2);
      return this;
   }

   public FluentTessellatorBridge bridge$lightmap(int number1) {
      this.impl$getBuffer().lightmap(LightTextureBridge.method1(number1), LightTextureBridge.method2(number1));
      return this;
   }

   public FluentTessellatorBridge bridge$normal(float value1, float value2, float value3) {
      WorldRenderer worldrenderer4 = this.impl$getBuffer();
      ByteBuffer buffer5 = worldrenderer4.byteBuffer;
      VertexFormat vertexformat6 = worldrenderer4.getVertexFormat();
      int number7 = vertexformat6.nextOffset * worldrenderer4.vertexCount + vertexformat6.getOffset(worldrenderer4.vertexFormatIndex);
      buffer5.put(number7, (byte)((int)(value1 * 127.0F) & 0xFF));
      buffer5.put(number7 + 1, (byte)((int)(value2 * 127.0F) & 0xFF));
      buffer5.put(number7 + 2, (byte)((int)(value3 * 127.0F) & 0xFF));
      worldrenderer4.nextVertexFormatIndex();
      return this;
   }

   public FluentTessellatorBridge bridge$normal(Matrix3fBridge mixinhelper2_61, float value2, float value3, float value4) {
      throw new RuntimeException("Tessellator normal with matrix's are unsupported in legacy!");
   }

   public FluentTessellatorBridge bridge$endVertex() {
      this.impl$getBuffer().endVertex();
      return this;
   }

   public boolean bridge$isDrawing() {
      return this.impl$getBuffer().isDrawing;
   }

   public void bridge$end() {
      this.draw();
   }

   public void bridge$setTranslation(double value1, double value3, double value5) {
      this.impl$getBuffer().setTranslation(value1, value3, value5);
   }
}
