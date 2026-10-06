package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

class Wrapper2$Data {
   private final FaceBakery field1;
   private final int field2;
   private final int field3;

   private Wrapper2$Data(FaceBakery var1) {
      boolean var2 = Bridge.method5().map(Slayer2::getConfig).<Boolean>map(Slayer4::hasShaders).orElse(false);
      this.field1 = var1;
      this.field2 = var2 ? 56 : 28;
      this.field3 = this.field2 / 4;
   }

   private BakedQuad method1(
      EnumFacing var1,
      TextureAtlasSprite var2,
      int var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      float var16,
      float var17,
      float var18,
      float var19,
      float var20,
      float var21,
      float var22,
      float var23
   ) {
      int[] var24 = new int[this.field2];
      int var25 = this.field1.getFaceShadeColor(var1);
      this.method2(var24, 0, var4, var5, var6, var25, var7, var8);
      this.method2(var24, 1, var9, var10, var11, var25, var12, var13);
      this.method2(var24, 2, var14, var15, var16, var25, var17, var18);
      this.method2(var24, 3, var19, var20, var21, var25, var22, var23);
      return ThreadModuleDump63.MC_VERSION >= 5 ? new BakedQuad(var24, var3, var1, var2) : new BakedQuad(var24, var3, var1);
   }

   private void method2(int[] var1, int var2, float var3, float var4, float var5, int var6, float var7, float var8) {
      int var9 = var2 * this.field3;
      var1[var9] = Float.floatToRawIntBits(var3);
      var1[var9 + 1] = Float.floatToRawIntBits(var4);
      var1[var9 + 2] = Float.floatToRawIntBits(var5);
      var1[var9 + 3] = var6;
      var1[var9 + 4] = Float.floatToRawIntBits(var7);
      var1[var9 + 5] = Float.floatToRawIntBits(var8);
   }
}
