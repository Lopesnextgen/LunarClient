package com.moonsworth.lunar.genesis;

import java.util.Map;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@Annotation2
@GwtCompatible
public abstract class MixinHelper332 extends MixinHelper33_3 {
   private final char[][] field3;
   private final int field4;
   private final char field5;
   private final char field6;

   protected MixinHelper332(Map<Character, String> var1, char var2, char var3) {
      this(MixinHelper2_3.method1(var1), var2, var3);
   }

   protected MixinHelper332(MixinHelper2_3 var1, char var2, char var3) {
      Preconditions.checkNotNull(var1);
      this.field3 = var1.getReplacementArray();
      this.field4 = this.field3.length;
      if (var3 < var2) {
         var3 = 0;
         var2 = '\uffff';
      }

      this.field5 = var2;
      this.field6 = var3;
   }

   @Override
   public final String escape(String var1) {
      Preconditions.checkNotNull(var1);

      for (int var2 = 0; var2 < var1.length(); var2++) {
         char var3 = var1.charAt(var2);
         if (var3 < this.field4 && this.field3[var3] != null || var3 > this.field6 || var3 < this.field5) {
            return this.method1(var1, var2);
         }
      }

      return var1;
   }

   @Override
   protected final char[] escape(char var1) {
      if (var1 < this.field4) {
         char[] var2 = this.field3[var1];
         if (var2 != null) {
            return var2;
         }
      }

      return var1 >= this.field5 && var1 <= this.field6 ? null : this.escapeUnsafe(var1);
   }

   protected abstract char[] escapeUnsafe(char var1);
}
