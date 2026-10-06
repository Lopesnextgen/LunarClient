package com.moonsworth.lunar.client.framework.feature.waila;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge8_2;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class CosmeticWailaComponent implements WailaComponent {
   @Nullable
   private final Bridge5_11 field1;
   private final CosmeticMetadata field2;
   @Nullable
   private final AxisAlignedBBBridge field3;
   private final int field4;

   public CosmeticWailaComponent(@Nullable Bridge5_11 bridge5_111, CosmeticMetadata gui2handler32, @Nullable AxisAlignedBBBridge horsestats123) {
      this(bridge5_111, gui2handler32, horsestats123, 20);
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
   public void method1(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.mod.hud.waila.WailaHud waila2, int index3, int number4) {
      int number5 = index3;
      int number6 = number4;
      float value7 = 1.0F;
      boolean flag8 = false;
      switch (this.field2.method4().method10()) {
         case CLOAK:
            index3++;
            number4 -= 5;
            value7 = 1.6F;
            break;
         case SUITS:
            index3 += 7;
            number4 += 12;
            value7 = 0.65F;
            break;
         case BACKPACK:
            index3 += 5;
            number4 += 10;
            value7 = 0.65F;
            break;
         case SHOES:
            index3 += 3;
            number4 += 9;
            value7 = 0.9F;
            break;
         case WINGS:
            if (this.field2.method4().method19()) {
               index3 += 4;
               number4 += 20;
               value7 = 0.5F;
            } else {
               index3 -= 6;
               number4 -= 12;
               value7 = 2.1F;
            }
            break;
         case BELTS:
            index3 += 4;
            number4 += 9;
            value7 = 0.9F;
            break;
         case NECKWEAR:
            index3 += 5;
            number4 += 11;
            value7 = 0.75F;
            break;
         case BODYWEAR:
            index3 += 3;
            number4 += 10;
            value7 = 0.9F;
            break;
         case MASK:
            index3 += 3;
            number4 += 8;
            break;
         case GLASSES:
            number4 += 9;
            index3 += 4;
            value7 = 0.9F;
            break;
         case BANDANNA:
            index3 += 3;
            number4 += 6;
            value7 = 0.9F;
            break;
         case HAT:
            index3 += 4;
            number4 += 10;
            value7 = 0.75F;
            flag8 = true;
            break;
         case PET:
            index3 += 5;
            number4 += 10;
            value7 = 0.65F;
            flag8 = true;
            break;
         case COMPANION:
            index3 += 9;
            number4 -= 18;
            value7 = 0.55F;
      }

      if (flag8 && this.field3 != null) {
         Vector3f vector3f9 = new Vector3f(
               (float)(this.field3.bridge$getMaxX() - this.field3.bridge$getMinX()),
               (float)(this.field3.bridge$getMaxY() - this.field3.bridge$getMinY()),
               (float)(this.field3.bridge$getMaxZ() - this.field3.bridge$getMinZ())
            )
            .normalize();
         if (vector3f9.y() > Math.max(vector3f9.x(), vector3f9.z())) {
            value7 = vector3f9.y();
            index3 += (int)((this.field4 - this.field4 * value7) / 2.0F);
            number4 += (int)((this.field4 - this.field4 * value7) / 3.0F);
         }
      }

      if (mixinhelper_41 instanceof LegacyGuiGraphicsBridge mixinhelper515) {
         mixinhelper_41.push();
         mixinhelper_41.scale(value7, value7, value7);
         AbstractRenderContext bridgeextension_910 = mixinhelper515.method29();
         this.field2.method4().method2(this.field2, this.field1, bridgeextension_910, index3 / value7, number4 / value7, this.field4, this.field4, -1);
         bridgeextension_910.method12();
         mixinhelper_41.pop();
      } else if (Ref.MC_VERSION >= 30) {
         int number16 = number5 - this.field4;
         int number11 = number6 - this.field4;
         float value12 = value7;
         float value13 = (index3 - number16) / value7;
         float value14 = (number4 - number11) / value7;
         ((Bridge8_2)mixinhelper_41.method49().orElseThrow())
            .bridge$submitPictureInPicture$v1_21_6(
               this.field2.method4(), number16, number11, number5 + this.getWidth() + this.field4, number6 + this.getHeight() + this.field4, arg4x -> {
                  arg4x.push();
                  arg4x.scale(value12, value12, value12);
                  this.field2.method4().method2(this.field2, this.field1, arg4x, value13, value14, this.field4, this.field4, -1);
                  arg4x.pop();
               }
            );
      }
   }

   @Generated
   public CosmeticWailaComponent(@Nullable Bridge5_11 bridge5_111, CosmeticMetadata gui2handler32, @Nullable AxisAlignedBBBridge horsestats123, int number4) {
      this.field1 = bridge5_111;
      this.field2 = gui2handler32;
      this.field3 = horsestats123;
      this.field4 = number4;
   }

   @Generated
   public CosmeticMetadata method2() {
      return this.field2;
   }
}
