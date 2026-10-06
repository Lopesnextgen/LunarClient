package com.moonsworth.lunar.client.framework.feature.armorstatus.armorstatusbarschild;

import com.moonsworth.lunar.bridge.Bridge6Extension3.Type;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump59;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

class Armorstatusbarschild {
   private static final int field1 = 9066288;
   private static final Map<Type, Integer> field2 = new EnumMap<>(Type.class);
   private static final EquipmentSlotBridge[] field3 = new EquipmentSlotBridge[]{
      EquipmentSlotBridge.CHEST, EquipmentSlotBridge.HEAD, EquipmentSlotBridge.LEGS, EquipmentSlotBridge.FEET
   };
   private final Map<Type, Integer> field4 = new EnumMap<>(Type.class);

   public int method1(Type var1, boolean var2) {
      Integer var3 = this.field4.get(var1);
      if (var3 != null) {
         return var3;
      }

      int var4 = var2 ? this.method2(var1) : field2.getOrDefault(var1, 0);
      this.field4.put(var1, var4);
      return var4;
   }

   public void reset() {
      this.field4.clear();
   }

   private int method2(Type var1) {
      for (EquipmentSlotBridge var5 : field3) {
         BufferedImage var6 = this.method3(var1, var5);
         if (var6 != null) {
            int var7 = this.method4(var6);
            if (var7 != 0) {
               if (var1 == Type.LEATHER) {
                  var7 = 0xFF000000 | (var7 >> 16 & 0xFF) * 138 / 255 << 16 | (var7 >> 8 & 0xFF) * 87 / 255 << 8 | (var7 & 0xFF) * 48 / 255;
               }

               int var8 = field2.getOrDefault(var1, 0);
               if (var8 == 0) {
                  return var7;
               }

               int var9 = var8 >> 16 & 0xFF;
               int var10 = var8 >> 8 & 0xFF;
               int var11 = var8 & 0xFF;
               float var12 = Math.max(var9, Math.max(var10, var11)) / 255.0F;
               float[] var13 = Color.RGBtoHSB(var7 >> 16 & 0xFF, var7 >> 8 & 0xFF, var7 & 0xFF, null);
               return Color.HSBtoRGB(var13[0], var13[1], var12);
            }
         }
      }

      return field2.getOrDefault(var1, 0);
   }

   @Nullable
   private BufferedImage method3(Type var1, EquipmentSlotBridge var2) {
      if (var1 != Type.ARMADILLO && var1 != Type.UNKNOWN) {
         String var3 = var1.getMaterial();
         if (var1 == Type.GOLD) {
            var3 = ThreadModuleDump63.MC_VERSION > 5 ? "golden" : "gold";
         }

         if (var3 == null) {
            return null;
         }

         String var4 = switch (var2) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> null;
         };
         return var4 == null
            ? null
            : ThreadModuleDump59.method4(
               ResourceLocationBridge.create("minecraft", "textures/" + (ThreadModuleDump63.MC_VERSION > 5 ? "item" : "items") + "/" + var3 + "_" + var4 + ".png")
            );
      } else {
         return null;
      }
   }

   private int method4(BufferedImage var1) {
      int var2 = var1.getWidth();
      int var3 = var1.getHeight();
      float[] var4 = new float[var2 * var3];
      long var5 = 0L;
      long var7 = 0L;
      long var9 = 0L;
      long var11 = 0L;
      int var13 = 0;

      for (int var14 = 0; var14 < var2; var14++) {
         for (int var15 = 0; var15 < var3; var15++) {
            int var16 = var1.getRGB(var14, var15);
            if ((var16 >> 24 & 0xFF) >= 128) {
               int var17 = var16 >> 16 & 0xFF;
               int var18 = var16 >> 8 & 0xFF;
               int var19 = var16 & 0xFF;
               int var20 = Math.max(var17, Math.max(var18, var19));
               var4[var13++] = var20 / 255.0F;
               int var21 = 1 + var20 - Math.min(var17, Math.min(var18, var19));
               var5 += (long)var17 * var21;
               var7 += (long)var18 * var21;
               var9 += (long)var19 * var21;
               var11 += var21;
            }
         }
      }

      if (var11 == 0L) {
         return 0;
      }

      float[] var22 = Color.RGBtoHSB((int)(var5 / var11), (int)(var7 / var11), (int)(var9 / var11), null);
      Arrays.sort(var4, 0, var13);
      float var23 = var4[Math.round((var13 - 1) * 0.8F)];
      return Color.HSBtoRGB(var22[0], Math.min(1.0F, var22[1] * 1.25F), var23);
   }

   static {
      field2.put(Type.LEATHER, -7644629);
      field2.put(Type.CHAIN, -9079435);
      field2.put(Type.IRON, -2565928);
      field2.put(Type.GOLD, -1062853);
      field2.put(Type.DIAMOND, -11608621);
      field2.put(Type.NETHERITE, -10925746);
      field2.put(Type.COPPER, -4102851);
      field2.put(Type.TURTLE, -10704801);
      field2.put(Type.TURTLE_SCUTE, -10704801);
      field2.put(Type.UNKNOWN, -1);
   }
}
