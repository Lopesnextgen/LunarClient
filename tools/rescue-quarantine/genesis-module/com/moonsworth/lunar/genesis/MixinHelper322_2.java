package com.moonsworth.lunar.genesis;

import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.UnicodeEscaper;

@Annotation2
@GwtCompatible
public abstract class MixinHelper322_2 extends UnicodeEscaper {
   private final char[][] field3;
   private final int field4;
   private final int field5;
   private final int field6;
   private final char field7;
   private final char field8;

   protected MixinHelper322_2(Map<Character, String> var1, int var2, int var3, @Nullable String var4) {
      this(MixinHelper2_3.method1(var1), var2, var3, var4);
   }

   protected MixinHelper322_2(MixinHelper2_3 var1, int var2, int var3, @Nullable String var4) {
      Preconditions.checkNotNull(var1);
      this.field3 = var1.getReplacementArray();
      this.field4 = this.field3.length;
      if (var3 < var2) {
         var3 = -1;
         var2 = Integer.MAX_VALUE;
      }

      this.field5 = var2;
      this.field6 = var3;
      if (var2 >= 55296) {
         this.field7 = '\uffff';
         this.field8 = 0;
      } else {
         this.field7 = (char)var2;
         this.field8 = (char)Math.min(var3, 55295);
      }
   }

   @Override
   public final String escape(String var1) {
      Preconditions.checkNotNull(var1);

      for (int var2 = 0; var2 < var1.length(); var2++) {
         char var3 = var1.charAt(var2);
         if (var3 < this.field4 && this.field3[var3] != null || var3 > this.field8 || var3 < this.field7) {
            return this.method1(var1, var2);
         }
      }

      return var1;
   }

   @Override
   protected final char[] escape(int var1) {
      if (var1 < this.field4) {
         char[] var2 = this.field3[var1];
         if (var2 != null) {
            return var2;
         }
      }

      return var1 >= this.field5 && var1 <= this.field6 ? null : this.escapeUnsafe(var1);
   }

   @Override
   protected final int nextEscapeIndex(CharSequence var1, int var2, int var3) {
      while (var2 < var3) {
         char var4 = var1.charAt(var2);
         if ((var4 >= this.field4 || this.field3[var4] == null) && var4 <= this.field8 && var4 >= this.field7) {
            var2++;
            continue;
         }
         break;
      }

      return var2;
   }

   protected abstract char[] escapeUnsafe(int var1);
}
