package com.moonsworth.lunar.replaymod.mixin;

import com.replaymod.core.ReplayModMixinConfigPlugin;
import java.io.InputStream;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ReplayModMixinConfigPlugin.class)
public class ReplayModMixinConfigPluginMixin_v1_8 {
   @Overwrite
   public static boolean hasClass(String text) {
      InputStream var1 = ReplayModMixinConfigPlugin.class.getClassLoader().getResourceAsStream(text);
      if (var1 == null) {
         return false;
      }

      try {
         var1.close();
      } catch (Exception var3) {
      }

      return true;
   }
}
