package com.moonsworth.lunar.bridge.potion;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

public interface PotionRegistryBridge extends com.moonsworth.lunar.bridge.fog.Fog3 {
   PotionBridge method1();

   PotionBridge method2();

   PotionBridge method3();

   PotionBridge method4();

   PotionBridge method5();

   PotionBridge method6();

   PotionBridge method7();

   PotionBridge method8(int number1);

   @VersionGate(min = 6)
   PotionBridge method9(String text1);

   PotionEffectBridge method10(int number1, String text2, int number3, int number4);

   String method11(String text1);

   Component method12(PotionEffectBridge fog1);

   String method13(PotionEffectBridge fog1);

   boolean method14(ItemStackBridge bridgeextension_41);

   @Unmodifiable List<String> method15();

   @Nullable
   String method16(ItemStackBridge bridgeextension_41);

   @VersionGate(min = 5)
   default boolean method17(ItemStackBridge bridgeextension_41) {
      return false;
   }

   void method18(ItemStackBridge bridgeextension_41, String text2);
}
