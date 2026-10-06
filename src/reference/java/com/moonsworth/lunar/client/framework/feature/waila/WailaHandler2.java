package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8_2;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class WailaHandler2 implements Waila {
   @Nullable
   private final Bridge5_11 field1;
   private final CosmeticMetadata field2;
   @Nullable
   private final AxisAlignedBBBridge field3;
   private final int field4;

   public WailaHandler2(@Nullable Bridge5_11 var1, CosmeticMetadata var2, @Nullable AxisAlignedBBBridge var3) {
      this(var1, var2, var3, 20);
   }

   @Override
   public int getWidth() {
      return this.field4;
   }

   @Override
   public int getHeight() {
      return this.field4 + 4;
   }

   @Override
   public void method1(MixinHelper_4 var1, com.moonsworth.lunar.client.mod.hud.waila.Waila var2, int var3, int var4) {
      int var5 = var3;
      int var6 = var4;
      float var7 = 1.0F;
      boolean var8 = false;
      switch (this.field2.method4().method10()) {
         case CLOAK:
            var3++;
            var4 -= 5;
            var7 = 1.6F;
            break;
         case SUITS:
            var3 += 7;
            var4 += 12;
            var7 = 0.65F;
            break;
         case BACKPACK:
            var3 += 5;
            var4 += 10;
            var7 = 0.65F;
            break;
         case SHOES:
            var3 += 3;
            var4 += 9;
            var7 = 0.9F;
            break;
         case WINGS:
            if (this.field2.method4().method19()) {
               var3 += 4;
               var4 += 20;
               var7 = 0.5F;
            } else {
               var3 -= 6;
               var4 -= 12;
               var7 = 2.1F;
            }
            break;
         case BELTS:
            var3 += 4;
            var4 += 9;
            var7 = 0.9F;
            break;
         case NECKWEAR:
            var3 += 5;
            var4 += 11;
            var7 = 0.75F;
            break;
         case BODYWEAR:
            var3 += 3;
            var4 += 10;
            var7 = 0.9F;
            break;
         case MASK:
            var3 += 3;
            var4 += 8;
            break;
         case GLASSES:
            var4 += 9;
            var3 += 4;
            var7 = 0.9F;
            break;
         case BANDANNA:
            var3 += 3;
            var4 += 6;
            var7 = 0.9F;
            break;
         case HAT:
            var3 += 4;
            var4 += 10;
            var7 = 0.75F;
            var8 = true;
            break;
         case PET:
            var3 += 5;
            var4 += 10;
            var7 = 0.65F;
            var8 = true;
            break;
         case COMPANION:
            var3 += 9;
            var4 -= 18;
            var7 = 0.55F;
      }

      if (var8 && this.field3 != null) {
         Vector3f var9 = new Vector3f(
               (float)(this.field3.bridge$getMaxX() - this.field3.bridge$getMinX()),
               (float)(this.field3.bridge$getMaxY() - this.field3.bridge$getMinY()),
               (float)(this.field3.bridge$getMaxZ() - this.field3.bridge$getMinZ())
            )
            .normalize();
         if (var9.y() > Math.max(var9.x(), var9.z())) {
            var7 = var9.y();
            var3 += (int)((this.field4 - this.field4 * var7) / 2.0F);
            var4 += (int)((this.field4 - this.field4 * var7) / 3.0F);
         }
      }

      if (var1 instanceof LegacyGuiGraphicsBridge var15) {
         var1.push();
         var1.scale(var7, var7, var7);
         AbstractRenderContext var10 = var15.method29();
         this.field2.method4().method2(this.field2, this.field1, var10, var3 / var7, var4 / var7, this.field4, this.field4, -1);
         var10.method12();
         var1.pop();
      } else if (ThreadModuleDump63.MC_VERSION >= 30) {
         int var16 = var5 - this.field4;
         int var11 = var6 - this.field4;
         float var12 = var7;
         float var13 = (var3 - var16) / var7;
         float var14 = (var4 - var11) / var7;
         ((Bridge8_2)var1.method49().orElseThrow())
            .bridge$submitPictureInPicture$v1_21_6(
               this.field2.method4(), var16, var11, var5 + this.getWidth() + this.field4, var6 + this.getHeight() + this.field4, var4x -> {
                  var4x.push();
                  var4x.scale(var12, var12, var12);
                  this.field2.method4().method2(this.field2, this.field1, var4x, var13, var14, this.field4, this.field4, -1);
                  var4x.pop();
               }
            );
      }
   }

   @Generated
   public WailaHandler2(@Nullable Bridge5_11 var1, CosmeticMetadata var2, @Nullable AxisAlignedBBBridge var3, int var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
   }

   @Generated
   public CosmeticMetadata method2() {
      return this.field2;
   }
}
