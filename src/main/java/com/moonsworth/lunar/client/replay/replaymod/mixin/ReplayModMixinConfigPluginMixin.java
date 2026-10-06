package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.replaymod.core.ReplayModMixinConfigPlugin;
import java.io.InputStream;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ReplayModMixinConfigPlugin.class)
public class ReplayModMixinConfigPluginMixin {
   public ReplayModMixinConfigPluginMixin() {
   }

   @Overwrite
   public static boolean hasClass(String text) {
      InputStream input1 = ReplayModMixinConfigPlugin.class.getClassLoader().getResourceAsStream(text);
      if (input1 == null) {
         return false;
      }

      try {
         input1.close();
      } catch (Exception exception3) {
      }

      return true;
   }
}
