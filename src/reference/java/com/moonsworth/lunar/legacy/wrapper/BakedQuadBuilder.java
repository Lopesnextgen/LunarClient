package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.optifine.OptifineBridge;
import com.moonsworth.lunar.bridge.optifine.OptifineConfigBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

class BakedQuadBuilder {
   private final FaceBakery field1;
   private final int field2;
   private final int field3;

   private BakedQuadBuilder(FaceBakery facebakery1) {
      boolean flag2 = Bridge.method5().map(OptifineBridge::getConfig).<Boolean>map(OptifineConfigBridge::hasShaders).orElse(false);
      this.field1 = facebakery1;
      this.field2 = flag2 ? 56 : 28;
      this.field3 = this.field2 / 4;
   }

   private BakedQuad method1(
      EnumFacing facing1,
      TextureAtlasSprite textureatlassprite2,
      int number3,
      float value4,
      float value5,
      float value6,
      float value7,
      float value8,
      float value9,
      float value10,
      float value11,
      float value12,
      float value13,
      float value14,
      float value15,
      float value16,
      float value17,
      float value18,
      float value19,
      float value20,
      float value21,
      float value22,
      float value23
   ) {
      int[] items24 = new int[this.field2];
      int number25 = this.field1.getFaceShadeColor(facing1);
      this.method2(items24, 0, value4, value5, value6, number25, value7, value8);
      this.method2(items24, 1, value9, value10, value11, number25, value12, value13);
      this.method2(items24, 2, value14, value15, value16, number25, value17, value18);
      this.method2(items24, 3, value19, value20, value21, number25, value22, value23);
      return Ref.MC_VERSION >= 5 ? new BakedQuad(items24, number3, facing1, textureatlassprite2) : new BakedQuad(items24, number3, facing1);
   }

   private void method2(int[] items1, int number2, float value3, float value4, float value5, int number6, float value7, float value8) {
      int index9 = number2 * this.field3;
      items1[index9] = Float.floatToRawIntBits(value3);
      items1[index9 + 1] = Float.floatToRawIntBits(value4);
      items1[index9 + 2] = Float.floatToRawIntBits(value5);
      items1[index9 + 3] = number6;
      items1[index9 + 4] = Float.floatToRawIntBits(value7);
      items1[index9 + 5] = Float.floatToRawIntBits(value8);
   }
}
