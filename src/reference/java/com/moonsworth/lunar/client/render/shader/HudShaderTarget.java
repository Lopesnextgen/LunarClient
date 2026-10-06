package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

public enum HudShaderTarget {
   BOSSBAR_MOD(ModsSettings::method65, "BOSSBAR"),
   ACTION_BAR_MOD(ModsSettings::method90, "ACTIONBAR"),
   SCOREBOARD_MOD(ModsSettings::method15, "SCOREBOARD");

   private final Function<ModsSettings, Framework7Extension> feature;
   private final String suffix;

   HudShaderTarget(Function<ModsSettings, Framework7Extension> function, String var4) {
      this.feature = function;
      this.suffix = var4;
   }

   public String getDefineSuffix() {
      return "_" + this.suffix;
   }

   public Framework7Extension getFeature() {
      return this.feature.apply(ThreadModuleDump63.method4().method40());
   }

   @Nullable
   public static HudShaderTarget fromDefineSuffix(String text) {
      for (HudShaderTarget var4 : values()) {
         if (var4.getDefineSuffix().equals(text)) {
            return var4;
         }
      }

      return null;
   }
}
