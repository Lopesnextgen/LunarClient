package com.moonsworth.lunar.client.util;

import com.lunarclient.apollo.module.saturation.SaturationModule;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.bridge.Bridge_9;
import com.moonsworth.lunar.bridge.MixinHelper2_13;
import com.moonsworth.lunar.bridge.ItemDataComponentTypes;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers;
import java.util.Optional;
import lombok.Generated;

public final class ThreadModuleDump2 {
   public static float REGEN_EXHAUSTION = 6.0F;
   public static float EXHAUSTION_PER_SATURATION = 4.0F;

   public static boolean canEat(ItemStackBridge var0, Bridge5_11 var1) {
      Optional var2 = var0.bridge$getFood();
      return var2.isEmpty() ? false : var1.bridge$canEat(((Bridge_9)var2.get()).bridge$canEatWhenFull());
   }

   private static ThreadModuleDump90 getSaturationOverride(ItemStackBridge var0) {
      ApolloModuleManager var1 = ThreadModuleDump63.method4().method84();
      Optional var2 = var1.method3(SaturationModule.class);
      if (var2.isEmpty()) {
         return null;
      }

      Bridge_57 var3 = Rewindhandlers.method1(var0);
      if (var3 == null) {
         return null;
      }

      if (!Rewindhandlers.method4(var3, "hunger") && !Rewindhandlers.method4(var3, "saturation")) {
         return null;
      }

      var1.method15("saturation", "Override");
      return new ThreadModuleDump90(var3.bridge$getInteger("hunger"), var3.bridge$getFloat("saturation"));
   }

   public static ThreadModuleDump90 getFoodValues(ItemStackBridge var0) {
      ThreadModuleDump90 var1 = getSaturationOverride(var0);
      if (var1 != null) {
         return var1;
      }

      Optional var2 = var0.bridge$getFood();
      int var3 = var2.<Integer>map(var1x -> var1x.bridge$getHealing(var0)).orElse(0);
      float var4 = var2.<Float>map(var1x -> var1x.bridge$getSaturation(var0)).orElse(0.0F);
      return new ThreadModuleDump90(var3, var4);
   }

   public static ThreadModuleDump90 getPlayerFoodValues(ItemStackBridge var0, Bridge5_11 var1) {
      return getFoodValues(var0);
   }

   public static boolean givesBadEffect(ItemStackBridge var0) {
      Optional var1 = var0.bridge$getFood();
      if (var1.isEmpty()) {
         return false;
      }

      if (ThreadModuleDump63.MC_VERSION < 26) {
         return ((Bridge_9)var1.get()).bridge$givesBadEffect();
      }

      MixinHelper2_13 var2 = (MixinHelper2_13)var0.bridge$getDataComponent(ItemDataComponentTypes.field22);
      return var2 != null && var2.bridge$givesBadEffect();
   }

   public static float countHeals(int var0, float var1, float var2) {
      float var3 = 0.0F;
      if (Float.isFinite(var2) && Float.isFinite(var1)) {
         while (var0 >= 18) {
            while (var2 > field2) {
               var2 -= field2;
               if (var1 > 0.0F) {
                  var1 = Math.max(var1 - 1.0F, 0.0F);
               } else {
                  var0--;
               }
            }

            if (var0 >= 20 && Float.compare(var1, Float.MIN_NORMAL) > 0) {
               float var4 = Math.min(var1, field1);
               float var5 = Math.nextUp(field2) - var2;
               int var6 = Math.max(1, (int)Math.ceil(var5 / var4));
               var3 += var4 / field1 * var6;
               var2 += var4 * var6;
            } else if (var0 >= 18) {
               var3++;
               var2 += field1;
            }
         }

         return var3;
      } else {
         return 0.0F;
      }
   }

   @Generated
   private ThreadModuleDump2() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
