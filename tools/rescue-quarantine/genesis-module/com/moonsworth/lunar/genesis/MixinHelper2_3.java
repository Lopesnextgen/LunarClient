package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@Annotation2
@GwtCompatible
public final class MixinHelper2_3 {
   private final char[][] field1;
   private static final char[][] field2 = new char[0][0];

   public static MixinHelper2_3 method1(Map<Character, String> var0) {
      return new MixinHelper2_3(createReplacementArray(var0));
   }

   private MixinHelper2_3(char[][] var1) {
      this.field1 = var1;
   }

   char[][] getReplacementArray() {
      return this.field1;
   }

   @Annotation4
   static char[][] createReplacementArray(Map<Character, String> var0) {
      Preconditions.checkNotNull(var0);
      if (var0.isEmpty()) {
         return field2;
      }

      char var1 = Collections.max(var0.keySet());
      char[][] var2 = new char[var1 + 1][];

      for (char var4 : var0.keySet()) {
         var2[var4] = ((String)var0.get(var4)).toCharArray();
      }

      return var2;
   }
}
