package com.moonsworth.lunar.client.cosmetics.inactive;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Random;
import javax.annotation.Nullable;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public class WanderPositionResolver {
   public WanderPositionResolver() {
   }

   @Nullable
   public static Horsestats20Extension2 method1(EmoteDefinition inactive30, PathFilter holograms3handler1, int number2, int number3, int number4) {
      Horsestats20Extension2 horsestats20extension25 = method5(inactive30.method9(), number2, number3, number4);
      return method4(inactive30, holograms3handler1, horsestats20extension25);
   }

   @Nullable
   public static Horsestats20Extension2 method2(EmoteDefinition inactive30, PathFilter holograms3handler1, Horsestats20Extension2 horsestats20extension22, int number3, int number4, int number5) {
      Horsestats20Extension2 horsestats20extension26 = method5(inactive30.method9(), number3, number4, number5);
      return method3(horsestats20extension22, holograms3handler1, horsestats20extension26);
   }

   @Nullable
   public static Horsestats20Extension2 method3(Horsestats20Extension2 horsestats20Extension2, PathFilter holograms3handler1, Horsestats20Extension2 horsestats20extension22) {
      Horsestats20Extension2 horsestats20extension23 = Bridge.method8()
         .method4(horsestats20Extension2.bridge$getX() + horsestats20extension22.bridge$getX(), horsestats20Extension2.bridge$getY() + horsestats20extension22.bridge$getY(), horsestats20Extension2.bridge$getZ() + horsestats20extension22.bridge$getZ());
      WorldBridgeExtension itemcounter6extension4 = Ref.method8();
      if (itemcounter6extension4 == null) {
         return null;
      } else {
         return horsestats20extension23.bridge$getY() - 1 > itemcounter6extension4.bridge$getMinBuildHeight()
               && horsestats20extension23.bridge$getY() < itemcounter6extension4.bridge$getMaxBuildHeight()
               && holograms3handler1.method5().method22(horsestats20extension23)
            ? horsestats20extension23
            : null;
      }
   }

   @Nullable
   public static Horsestats20Extension2 method4(EmoteDefinition inactive30, PathFilter holograms3handler1, Horsestats20Extension2 horsestats20extension22) {
      Horsestats20Extension2 horsestats20extension23 = Bridge.method8()
         .method6(inactive30.bridge$getPosX() + horsestats20extension22.bridge$getX(), inactive30.bridge$getPosY() + horsestats20extension22.bridge$getY(), inactive30.bridge$getPosZ() + horsestats20extension22.bridge$getZ());
      WorldBridgeExtension itemcounter6extension4 = Ref.method8();
      if (itemcounter6extension4 == null) {
         return null;
      } else {
         return horsestats20extension23.bridge$getY() - 1 > itemcounter6extension4.bridge$getMinBuildHeight()
               && horsestats20extension23.bridge$getY() < itemcounter6extension4.bridge$getMaxBuildHeight()
               && holograms3handler1.method5().method22(horsestats20extension23)
            ? horsestats20extension23
            : null;
      }
   }

   public static Horsestats20Extension2 method5(Random random0, int value, int number2, int number3) {
      int number4 = random0.nextInt(value, number2 + 1);
      int number5 = random0.nextInt(2 * number3 + 1) - number3;
      int number6 = random0.nextInt(value, number2 + 1);
      if (random0.nextBoolean()) {
         number4 = -number4;
      }

      if (random0.nextBoolean()) {
         number6 = -number6;
      }

      return Bridge.method8().method4(number4, number5, number6);
   }
}
