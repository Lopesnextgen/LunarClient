package com.moonsworth.lunar.client.mod.render.saturation;

import com.lunarclient.apollo.module.saturation.SaturationModule;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.ItemFoodBridge;
import com.moonsworth.lunar.bridge.FoodComponentBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.util.game.ItemTagUtils;
import java.util.Optional;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.Ref;

public final class FoodUtils {
   public static float field1 = 6.0F;
   public static float field2 = 4.0F;

   public static boolean method1(ItemStackBridge bridgeextension_40, Bridge5_11 bridge5_111) {
      Optional optional2 = bridgeextension_40.bridge$getFood();
      return optional2.isEmpty() ? false : bridge5_111.bridge$canEat(((ItemFoodBridge)optional2.get()).bridge$canEatWhenFull());
   }

   private static FoodValues method2(ItemStackBridge bridgeextension_40) {
      ApolloModuleManager foghandler21 = Ref.method4().method84();
      Optional optional2 = foghandler21.method3(SaturationModule.class);
      if (optional2.isEmpty()) {
         return null;
      }

      CompoundTagBridge bridge_573 = ItemTagUtils.method1(bridgeextension_40);
      if (bridge_573 == null) {
         return null;
      }

      if (!ItemTagUtils.method4(bridge_573, "hunger") && !ItemTagUtils.method4(bridge_573, "saturation")) {
         return null;
      }

      foghandler21.method15("saturation", "Override");
      return new FoodValues(bridge_573.bridge$getInteger("hunger"), bridge_573.bridge$getFloat("saturation"));
   }

   public static FoodValues method3(ItemStackBridge bridgeextension_40) {
      FoodValues threadmoduledump901 = method2(bridgeextension_40);
      if (threadmoduledump901 != null) {
         return threadmoduledump901;
      }

      Optional optional2 = bridgeextension_40.bridge$getFood();
      int number3 = optional2.<Integer>map(arg1x -> arg1x.bridge$getHealing(bridgeextension_40)).orElse(0);
      float value4 = optional2.<Float>map(arg1x -> arg1x.bridge$getSaturation(bridgeextension_40)).orElse(0.0F);
      return new FoodValues(number3, value4);
   }

   public static FoodValues method4(ItemStackBridge bridgeextension_40, Bridge5_11 bridge5_111) {
      return method3(bridgeextension_40);
   }

   public static boolean method5(ItemStackBridge bridgeextension_40) {
      Optional optional1 = bridgeextension_40.bridge$getFood();
      if (optional1.isEmpty()) {
         return false;
      }

      if (Ref.MC_VERSION < 26) {
         return ((ItemFoodBridge)optional1.get()).bridge$givesBadEffect();
      }

      FoodComponentBridge mixinhelper2_132 = (FoodComponentBridge)bridgeextension_40.bridge$getDataComponent(DataComponentTypes.field22);
      return mixinhelper2_132 != null && mixinhelper2_132.bridge$givesBadEffect();
   }

   public static float method6(int index0, float value1, float value2) {
      float value3 = 0.0F;
      if (Float.isFinite(value2) && Float.isFinite(value1)) {
         while (index0 >= 18) {
            while (value2 > field2) {
               value2 -= field2;
               if (value1 > 0.0F) {
                  value1 = Math.max(value1 - 1.0F, 0.0F);
               } else {
                  index0--;
               }
            }

            if (index0 >= 20 && Float.compare(value1, Float.MIN_NORMAL) > 0) {
               float value4 = Math.min(value1, field1);
               float value5 = Math.nextUp(field2) - value2;
               int number6 = Math.max(1, (int)Math.ceil(value5 / value4));
               value3 += value4 / field1 * number6;
               value2 += value4 * number6;
            } else if (index0 >= 18) {
               value3++;
               value2 += field1;
            }
         }

         return value3;
      } else {
         return 0.0F;
      }
   }

   @Generated
   private FoodUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
