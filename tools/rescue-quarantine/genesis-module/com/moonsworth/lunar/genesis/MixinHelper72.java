package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class MixinHelper72 extends MixinHelper7_4 {
   private MixinHelper72() {
   }

   public static boolean equal(@Nullable Object var0, @Nullable Object var1) {
      return var0 == var1 || var0 != null && var0.equals(var1);
   }

   public static int hashCode(@Nullable Object @Nullable ... var0) {
      return Arrays.hashCode(var0);
   }
}
