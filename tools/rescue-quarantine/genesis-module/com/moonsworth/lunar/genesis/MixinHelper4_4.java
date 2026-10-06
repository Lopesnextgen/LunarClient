package com.moonsworth.lunar.genesis;
import com.google.common.escape.UnicodeEscaper;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.base.Preconditions;

@Annotation2
@GwtCompatible
public final class MixinHelper4_4 {
   private static final Escaper field1 = new MixinHelper33_3() {
      @Override
      public String escape(String var1) {
         return Preconditions.checkNotNull(var1);
      }

      @Override
      protected char[] escape(char var1) {
         return null;
      }
   };

   private MixinHelper4_4() {
   }

   public static Escaper method1() {
      return field1;
   }

   public static MixinHelper4$Data21 method2() {
      return new MixinHelper4$Data21();
   }

   static UnicodeEscaper method3(Escaper var0) {
      Preconditions.checkNotNull(var0);
      if (var0 instanceof UnicodeEscaper) {
         return (UnicodeEscaper)var0;
      } else if (var0 instanceof MixinHelper33_3) {
         return method6((MixinHelper33_3)var0);
      } else {
         throw new IllegalArgumentException("Cannot create a UnicodeEscaper from: " + var0.getClass().getName());
      }
   }

   public static String method4(MixinHelper33_3 var0, char var1) {
      return stringOrNull(var0.escape(var1));
   }

   public static String method5(UnicodeEscaper var0, int var1) {
      return stringOrNull(var0.escape(var1));
   }

   private static String stringOrNull(char[] var0) {
      return var0 == null ? null : new String(var0);
   }

   private static UnicodeEscaper method6(final MixinHelper33_3 var0) {
      return new UnicodeEscaper() {
         @Override
         protected char[] escape(int var1) {
            if (var1 < 65536) {
               return var0.escape((char)var1);
            }

            char[] var2 = new char[2];
            Character.toChars(var1, var2, 0);
            char[] var3 = var0.escape(var2[0]);
            char[] var4 = var0.escape(var2[1]);
            if (var3 == null && var4 == null) {
               return null;
            }

            int var5 = var3 != null ? var3.length : 1;
            int var6 = var4 != null ? var4.length : 1;
            char[] var7 = new char[var5 + var6];
            if (var3 != null) {
               for (int var8 = 0; var8 < var3.length; var8++) {
                  var7[var8] = var3[var8];
               }
            } else {
               var7[0] = var2[0];
            }

            if (var4 != null) {
               for (int var9 = 0; var9 < var4.length; var9++) {
                  var7[var5 + var9] = var4[var9];
               }
            } else {
               var7[var5] = var2[1];
            }

            return var7;
         }
      };
   }
}
