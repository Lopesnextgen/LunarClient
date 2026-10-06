package com.moonsworth.lunar.forge.mixin;

import javax.annotation.Nullable;
import net.minecraftforge.common.util.EnumHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EnumHelper.class)
public class EnumHelperMixin {
   public EnumHelperMixin() {
   }

   @Overwrite
   public static <T extends Enum<?>> T addEnum(boolean flag, Class<T> clazz1, @Nullable String text2, Class<?>[] items3, @Nullable Object[] items4) {
      return null;
   }
}
