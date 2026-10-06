package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.nbt.NBTTagString;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(max = 1)
@Mixin(NBTTagString.class)
public abstract class NBTTagStringMixin {
   @Shadow
   public String data;

   public NBTTagStringMixin() {
   }

   @Override
   public String toString() {
      return quoteAndEscape(this.data);
   }

   private static String quoteAndEscape(String text) {
      StringBuilder builder1 = new StringBuilder("\"");

      for (int index2 = 0; index2 < text.length(); index2++) {
         char character3 = text.charAt(index2);
         if (character3 == '\\' || character3 == '"') {
            builder1.append('\\');
         }

         builder1.append(character3);
      }

      return builder1.append('"').toString();
   }
}
