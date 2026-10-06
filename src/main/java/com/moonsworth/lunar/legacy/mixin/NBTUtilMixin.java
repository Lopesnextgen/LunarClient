package com.moonsworth.lunar.legacy.mixin;

import java.util.UUID;
import net.minecraft.nbt.NBTUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NBTUtil.class)
public class NBTUtilMixin {
   public NBTUtilMixin() {
   }

   @Redirect(method = "readGameProfileFromNBT", at = @At(value = "INVOKE", target = "Ljava/util/UUID;fromString(Ljava/lang/String;)Ljava/util/UUID;"))
   private static UUID lunar$parseUUID(String text0) {
      return fromString(text0);
   }

   private static UUID fromString(String text0) {
      if (text0 == null) {
         return null;
      }

      String[] items1 = text0.split("-");
      if (items1.length != 5) {
         throw new IllegalArgumentException("Invalid UUID string: " + text0);
      }

      for (int index2 = 0; index2 < 5; index2++) {
         items1[index2] = "0x" + items1[index2];
      }

      long number6 = Long.decode(items1[0]);
      number6 <<= 16;
      number6 |= Long.decode(items1[1]);
      number6 <<= 16;
      number6 |= Long.decode(items1[2]);
      long number4 = Long.decode(items1[3]);
      number4 <<= 48;
      number4 |= Long.decode(items1[4]);
      return new UUID(number6, number4);
   }
}
