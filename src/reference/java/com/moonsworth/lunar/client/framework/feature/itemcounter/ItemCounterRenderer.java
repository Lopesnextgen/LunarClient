package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.InventoryPlayerBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import java.util.Collection;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class ItemCounterRenderer {
   private static final int field1 = 16;
   @NotNull
   private final ItemCounterEntry field2;
   private final String field3;
   private long count;

   public ItemCounterRenderer(@NotNull ItemCounterEntry itemcounter_21) {
      this.field2 = itemcounter_21;
      this.field3 = this.field2.toString();
   }

   public void method1(MixinHelper_4 mixinhelper_41, RenderItemBridge bridge5_192, float value3, float value4, ItemCounterRenderer.ItemCounterTextSide type35, boolean flag6) {
      Bridge10_2 bridge10_27 = Ref.method10();
      float value8 = value3;
      float value9 = value4;
      float value10 = 2.0F;
      if (type35 != ItemCounterRenderer.ItemCounterTextSide.NONE) {
         byte number11 = 8;
         float value12 = bridge10_27.method19();
         float value13 = value12 / 2.0F;
         float value14 = value3;
         float value15 = value4 + 16.0F;
         String text16 = flag6 ? this.field3 : this.count + " " + this.field3;
         if (flag6) {
            switch (type35) {
               case BOTTOM:
                  value8 = value3 + bridge10_27.bridge$getStringWidth(text16) / 2.0F - 8.0F;
                  break;
               case TOP:
                  value8 = value3 + bridge10_27.bridge$getStringWidth(text16) / 2.0F - 8.0F;
                  value9 = value4 + value12;
                  value15 = value4;
                  break;
               case LEFT:
                  value8 = value3 + bridge10_27.bridge$getStringWidth(text16) + value10;
                  value15 = value4 + 8.0F - value13;
                  break;
               case RIGHT:
                  value14 = value3 + 16.0F + value10;
                  value15 = value4 + 8.0F - value13;
                  break;
               default:
                  throw new IllegalStateException("Unknown text side " + type35);
            }
         } else {
            value14 = value3 + 1.0F;
            value15 = value4 + 1.0F;
         }

         mixinhelper_41.method19(bridge10_27, text16, value10 + value14, value10 + value15, -1, false);
      }

      if (flag6) {
         mixinhelper_41.method44(arg0 -> arg0.method29().method6(arg0x -> {
            arg0x.IHORHICICIHRCOCRROCHHOROCHCHCR();
            Bridge.method14().method2();
         }));
         mixinhelper_41.method34(this.field2.method4(), (int)(value10 + value8 - 1.0F), (int)(value10 + value9 - 1.0F), Ref.method3());
         mixinhelper_41.method44(arg0 -> arg0.method29().method6(arg0x -> {
            Bridge.method14().method3();
            arg0x.ICOHHORICHCROOOCOHIRIHOHORRCHH();
            arg0x.ICRCRICCCORRHICIHHIHORROOHIROO();
         }));
         this.method4(mixinhelper_41, bridge10_27, (int)(value10 + value8 - 1.0F), (int)(value10 + value9 - 1.0F));
      }
   }

   public FloatFloatPair method2(ItemCounterRenderer.ItemCounterTextSide type31, boolean flag2) {
      float value3 = 2.0F;
      if (type31 == ItemCounterRenderer.ItemCounterTextSide.NONE) {
         return flag2 ? FloatFloatPair.of(16.0F + value3 * 2.0F, 16.0F + value3 * 2.0F) : FloatFloatPair.of(0.0F, 0.0F);
      }

      Bridge10_2 bridge10_24 = Ref.method10();
      String text5 = flag2 ? this.field3 : this.count + " " + this.field3;
      float value6 = bridge10_24.method19();
      float value7 = flag2 ? 16.0F + value3 : 0.0F;
      float value8;
      if (type31 != ItemCounterRenderer.ItemCounterTextSide.LEFT && type31 != ItemCounterRenderer.ItemCounterTextSide.RIGHT) {
         value7 = bridge10_24.bridge$getStringWidth(text5);
         value8 = flag2 ? 16.0F + value6 : value6;
      } else {
         if (flag2) {
            value7 += value3;
         }

         value7 += bridge10_24.bridge$getStringWidth(text5);
         value8 = flag2 ? 16.0F : value6;
      }

      return FloatFloatPair.of(value7 + value3 * 2.0F, value8 + value3 * 2.0F);
   }

   public void method3() {
      this.count = this.method5(Ref.method7());
   }

   private void method4(MixinHelper_4 mixinhelper_41, Bridge10_2 bridge10_22, int number3, int number4) {
      mixinhelper_41.push();
      mixinhelper_41.method38(0.0F, 0.0F, 500.0F);
      int number5 = this.count == 0L ? -65536 : -1;
      String text6 = String.valueOf(this.count);
      mixinhelper_41.method19(bridge10_22, text6, number3 + 19 - 2 - bridge10_22.bridge$getStringWidth(text6), number4 + 6 + 3, number5, true);
      mixinhelper_41.pop();
   }

   private long method5(Bridge6_10 bridge6_101) {
      if (bridge6_101 == null) {
         return 0L;
      }

      InventoryPlayerBridge bridge_242 = bridge6_101.bridge$getInventory();
      return Stream.of(bridge_242.bridge$getMainInventory(), bridge_242.bridge$getArmorInventory(), bridge_242.bridge$getOffhandInventory())
         .flatMap(Collection::stream)
         .filter(arg1x -> arg1x != null && this.field2.method2(arg1x))
         .mapToLong(ItemStackBridge::bridge$getStackSize)
         .sum();
   }

   public enum ItemCounterTextSide implements OptionEnumValue {
      NONE("none"),
      TOP("top"),
      BOTTOM("bottom"),
      LEFT("left"),
      RIGHT("right");

      private final String id;

      ItemCounterTextSide(String text3) {
         this.id = text3;
      }

      public String id() {
         return this.id;
      }
   }
}
