package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_24;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import java.util.Collection;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class Itemcounter {
   private static final int field1 = 16;
   @NotNull
   private final Itemcounter_2 field2;
   private final String field3;
   private long count;

   public Itemcounter(@NotNull Itemcounter_2 var1) {
      this.field2 = var1;
      this.field3 = this.field2.toString();
   }

   public void method1(MixinHelper_4 var1, Bridge5_19 var2, float var3, float var4, Itemcounter.Type3 var5, boolean var6) {
      Bridge10_2 var7 = ThreadModuleDump63.method10();
      float var8 = var3;
      float var9 = var4;
      float var10 = 2.0F;
      if (var5 != Itemcounter.Type3.NONE) {
         byte var11 = 8;
         float var12 = var7.method19();
         float var13 = var12 / 2.0F;
         float var14 = var3;
         float var15 = var4 + 16.0F;
         String var16 = var6 ? this.field3 : this.count + " " + this.field3;
         if (var6) {
            switch (var5) {
               case BOTTOM:
                  var8 = var3 + var7.bridge$getStringWidth(var16) / 2.0F - 8.0F;
                  break;
               case TOP:
                  var8 = var3 + var7.bridge$getStringWidth(var16) / 2.0F - 8.0F;
                  var9 = var4 + var12;
                  var15 = var4;
                  break;
               case LEFT:
                  var8 = var3 + var7.bridge$getStringWidth(var16) + var10;
                  var15 = var4 + 8.0F - var13;
                  break;
               case RIGHT:
                  var14 = var3 + 16.0F + var10;
                  var15 = var4 + 8.0F - var13;
                  break;
               default:
                  throw new IllegalStateException("Unknown text side " + var5);
            }
         } else {
            var14 = var3 + 1.0F;
            var15 = var4 + 1.0F;
         }

         var1.method19(var7, var16, var10 + var14, var10 + var15, -1, false);
      }

      if (var6) {
         var1.method44(var0 -> var0.method29().method6(var0x -> {
            var0x.IHORHICICIHRCOCRROCHHOROCHCHCR();
            Bridge.method14().method2();
         }));
         var1.method34(this.field2.method4(), (int)(var10 + var8 - 1.0F), (int)(var10 + var9 - 1.0F), ThreadModuleDump63.method3());
         var1.method44(var0 -> var0.method29().method6(var0x -> {
            Bridge.method14().method3();
            var0x.ICOHHORICHCROOOCOHIRIHOHORRCHH();
            var0x.ICRCRICCCORRHICIHHIHORROOHIROO();
         }));
         this.method4(var1, var7, (int)(var10 + var8 - 1.0F), (int)(var10 + var9 - 1.0F));
      }
   }

   public FloatFloatPair method2(Itemcounter.Type3 var1, boolean var2) {
      float var3 = 2.0F;
      if (var1 == Itemcounter.Type3.NONE) {
         return var2 ? FloatFloatPair.of(16.0F + var3 * 2.0F, 16.0F + var3 * 2.0F) : FloatFloatPair.of(0.0F, 0.0F);
      }

      Bridge10_2 var4 = ThreadModuleDump63.method10();
      String var5 = var2 ? this.field3 : this.count + " " + this.field3;
      float var6 = var4.method19();
      float var7 = var2 ? 16.0F + var3 : 0.0F;
      float var8;
      if (var1 != Itemcounter.Type3.LEFT && var1 != Itemcounter.Type3.RIGHT) {
         var7 = var4.bridge$getStringWidth(var5);
         var8 = var2 ? 16.0F + var6 : var6;
      } else {
         if (var2) {
            var7 += var3;
         }

         var7 += var4.bridge$getStringWidth(var5);
         var8 = var2 ? 16.0F : var6;
      }

      return FloatFloatPair.of(var7 + var3 * 2.0F, var8 + var3 * 2.0F);
   }

   public void method3() {
      this.count = this.method5(ThreadModuleDump63.method7());
   }

   private void method4(MixinHelper_4 var1, Bridge10_2 var2, int var3, int var4) {
      var1.push();
      var1.method38(0.0F, 0.0F, 500.0F);
      int var5 = this.count == 0L ? -65536 : -1;
      String var6 = String.valueOf(this.count);
      var1.method19(var2, var6, var3 + 19 - 2 - var2.bridge$getStringWidth(var6), var4 + 6 + 3, var5, true);
      var1.pop();
   }

   private long method5(Bridge6_10 var1) {
      if (var1 == null) {
         return 0L;
      }

      Bridge_24 var2 = var1.bridge$getInventory();
      return Stream.of(var2.bridge$getMainInventory(), var2.bridge$getArmorInventory(), var2.bridge$getOffhandInventory())
         .flatMap(Collection::stream)
         .filter(var1x -> var1x != null && this.field2.method2(var1x))
         .mapToLong(ItemStackBridge::bridge$getStackSize)
         .sum();
   }

   public enum Type3 implements OptionEnumValue {
      NONE("none"),
      TOP("top"),
      BOTTOM("bottom"),
      LEFT("left"),
      RIGHT("right");

      private final String id;

      Type3(String var3) {
         this.id = var3;
      }

      public String id() {
         return this.id;
      }
   }
}
