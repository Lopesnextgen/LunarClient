package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.Escaper;

@Annotation2
@GwtCompatible
public final class MixinHelper6_9 {
   private final Map<Character, String> field1;
   private int max = -1;

   public MixinHelper6_9() {
      this.field1 = new HashMap<>();
   }

   @CanIgnoreReturnValue
   public MixinHelper6_9 method1(char var1, String var2) {
      this.field1.put(var1, Preconditions.checkNotNull(var2));
      if (var1 > this.max) {
         this.max = var1;
      }

      return this;
   }

   @CanIgnoreReturnValue
   public MixinHelper6_9 method2(char[] var1, String var2) {
      Preconditions.checkNotNull(var2);

      for (char var6 : var1) {
         this.method1(var6, var2);
      }

      return this;
   }

   public char[][] toArray() {
      char[][] var1 = new char[this.max + 1][];

      for (Entry var3 : this.field1.entrySet()) {
         var1[var3.getKey()] = ((String)var3.getValue()).toCharArray();
      }

      return var1;
   }

   public Escaper method3() {
      return new MixinHelper6$Data29(this.toArray());
   }
}
