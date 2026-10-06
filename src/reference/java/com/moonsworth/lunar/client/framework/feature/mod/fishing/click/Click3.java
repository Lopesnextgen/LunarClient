package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.Coordinates;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public final class Click3 {
   private static boolean field1 = false;

   public static boolean hasIsland() {
      return getIsland() != Gui2Extension3.NONE;
   }

   public static Gui2Extension3 getIsland() {
      return GuiRewindhandlersHandler23.field7.method9();
   }

   public static boolean hasPowderSources() {
      return getIsland() != null && getIsland().containsPowderSources();
   }

   public static boolean isMiningIsland() {
      return getIsland() != null && getIsland().isMiningIsland();
   }

   public static double getEyeHeight() {
      return ThreadModuleDump63.method7().bridge$isSneaking() ? 1.54 : 1.62;
   }

   public static void updateCoordinates() {
      Skyblock var0 = ThreadModuleDump63.method4().method40().method82();
      Coordinates.method2(var0.method17(), var0);
   }

   @Generated
   private Click3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static boolean isEnabled() {
      return field1;
   }

   @Generated
   public static void setEnabled(boolean var0) {
      field1 = var0;
   }
}
