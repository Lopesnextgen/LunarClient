package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.escape.Escaper;

@Annotation2
public final class MixinHelper4$Data21 {
   private final Map<Character, String> field1 = new HashMap<>();
   private char safeMin = 0;
   private char safeMax = '\uffff';
   private String unsafeReplacement = null;

   private MixinHelper4$Data21() {
   }

   @CanIgnoreReturnValue
   public MixinHelper4$Data21 method1(char var1, char var2) {
      this.safeMin = var1;
      this.safeMax = var2;
      return this;
   }

   @CanIgnoreReturnValue
   public MixinHelper4$Data21 method2(@Nullable String var1) {
      this.unsafeReplacement = var1;
      return this;
   }

   @CanIgnoreReturnValue
   public MixinHelper4$Data21 method3(char var1, String var2) {
      Preconditions.checkNotNull(var2);
      this.field1.put(var1, var2);
      return this;
   }

   public Escaper method4() {
      return new MixinHelper332(this.field1, this.safeMin, this.safeMax) {
         private final char[] field7 = MixinHelper4$Data21.this.unsafeReplacement != null ? MixinHelper4$Data21.this.unsafeReplacement.toCharArray() : null;

         @Override
         protected char[] escapeUnsafe(char var1) {
            return this.field7;
         }
      };
   }
}
