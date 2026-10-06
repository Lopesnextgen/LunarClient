package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.audio.LunarSoundPlayer;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public final class IslandUtils {
   private static boolean field1 = false;

   public static boolean isOnIsland() {
      return getIsland() != SkyblockIsland.NONE;
   }

   public static SkyblockIsland getIsland() {
      return HypixelLocationListener.field7.method9();
   }

   public static boolean hasPowderSources() {
      return getIsland() != null && getIsland().containsPowderSources();
   }

   public static boolean isMiningIsland() {
      return getIsland() != null && getIsland().isMiningIsland();
   }

   public static double getEyeHeight() {
      return Ref.method7().bridge$isSneaking() ? 1.54 : 1.62;
   }

   public static void playSound() {
      Skyblock skyblock0 = Ref.method4().method40().method82();
      LunarSoundPlayer.method2(skyblock0.method17(), skyblock0);
   }

   @Generated
   private IslandUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static boolean method7() {
      return field1;
   }

   @Generated
   public static void method8(boolean flag) {
      field1 = flag;
   }
}
