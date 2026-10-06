package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
public class MixinHelper16_2 {
   private final String field1;

   public static MixinHelper16_2 method1(String var0) {
      return new MixinHelper16_2(var0);
   }

   public static MixinHelper16_2 method2(char var0) {
      return new MixinHelper16_2(String.valueOf(var0));
   }

   private MixinHelper16_2(String var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   private MixinHelper16_2(MixinHelper16_2 var1) {
      this.field1 = var1.field1;
   }

   @CanIgnoreReturnValue
   public <A extends Appendable> A appendTo(A var1, Iterable<?> var2) {
      return this.appendTo((A)var1, var2.iterator());
   }

   @CanIgnoreReturnValue
   public <A extends Appendable> A appendTo(A var1, Iterator<?> var2) {
      Preconditions.checkNotNull(var1);
      if (var2.hasNext()) {
         var1.append(this.toString(var2.next()));

         while (var2.hasNext()) {
            var1.append(this.field1);
            var1.append(this.toString(var2.next()));
         }
      }

      return (A)var1;
   }

   @CanIgnoreReturnValue
   public final <A extends Appendable> A method3(A var1, Object[] var2) {
      return this.appendTo((A)var1, Arrays.asList(var2));
   }

   @CanIgnoreReturnValue
   public final <A extends Appendable> A method4(A var1, @Nullable Object var2, @Nullable Object var3, Object... var4) {
      return this.appendTo((A)var1, iterable(var2, var3, var4));
   }

   @CanIgnoreReturnValue
   public final StringBuilder method5(StringBuilder var1, Iterable<?> var2) {
      return this.method6(var1, var2.iterator());
   }

   @CanIgnoreReturnValue
   public final StringBuilder method6(StringBuilder var1, Iterator<?> var2) {
      try {
         this.appendTo(var1, var2);
         return var1;
      } catch (IOException var4) {
         throw new AssertionError(var4);
      }
   }

   @CanIgnoreReturnValue
   public final StringBuilder method7(StringBuilder var1, Object[] var2) {
      return this.method5(var1, Arrays.asList(var2));
   }

   @CanIgnoreReturnValue
   public final StringBuilder method8(StringBuilder var1, @Nullable Object var2, @Nullable Object var3, Object... var4) {
      return this.method5(var1, iterable(var2, var3, var4));
   }

   public final String method9(Iterable<?> var1) {
      return this.method10(var1.iterator());
   }

   public final String method10(Iterator<?> var1) {
      return this.method6(new StringBuilder(), var1).toString();
   }

   public final String method11(Object[] var1) {
      return this.method9(Arrays.asList(var1));
   }

   public final String method12(@Nullable Object var1, @Nullable Object var2, Object... var3) {
      return this.method9(iterable(var1, var2, var3));
   }

   public MixinHelper16_2 method13(final String var1) {
      Preconditions.checkNotNull(var1);
      return new MixinHelper16_2(this) {
         @Override
         CharSequence toString(@Nullable Object var1x) {
            return var1x == null ? var1 : MixinHelper16_2.this.toString(var1x);
         }

         @Override
         public MixinHelper16_2 method13(String var1x) {
            throw new UnsupportedOperationException("already specified useForNull");
         }

         @Override
         public MixinHelper16_2 method14() {
            throw new UnsupportedOperationException("already specified useForNull");
         }
      };
   }

   public MixinHelper16_2 method14() {
      return new MixinHelper16_2(this) {
         @Override
         public <A extends Appendable> A appendTo(A var1, Iterator<?> var2) {
            Preconditions.checkNotNull(var1, "appendable");
            Preconditions.checkNotNull(var2, "parts");

            while (var2.hasNext()) {
               Object var3 = var2.next();
               if (var3 != null) {
                  var1.append(MixinHelper16_2.this.toString(var3));
                  break;
               }
            }

            while (var2.hasNext()) {
               Object var4 = var2.next();
               if (var4 != null) {
                  var1.append(MixinHelper16_2.this.field1);
                  var1.append(MixinHelper16_2.this.toString(var4));
               }
            }

            return (A)var1;
         }

         @Override
         public MixinHelper16_2 method13(String var1) {
            throw new UnsupportedOperationException("already specified skipNulls");
         }

         @Override
         public MixinHelper16$Data6 method16(String var1) {
            throw new UnsupportedOperationException("can't use .skipNulls() with maps");
         }
      };
   }

   public MixinHelper16$Data6 method15(char var1) {
      return this.method16(String.valueOf(var1));
   }

   public MixinHelper16$Data6 method16(String var1) {
      return new MixinHelper16$Data6(this, var1);
   }

   CharSequence toString(Object var1) {
      Preconditions.checkNotNull(var1);
      return var1 instanceof CharSequence ? (CharSequence)var1 : var1.toString();
   }

   private static Iterable<Object> iterable(final Object var0, final Object var1, final Object[] var2) {
      Preconditions.checkNotNull(var2);
      return new AbstractList<Object>() {
         @Override
         public int size() {
            return var2.length + 2;
         }

         @Override
         public Object get(int var1x) {
            switch (var1x) {
               case 0:
                  return var0;
               case 1:
                  return var1;
               default:
                  return var2[var1x - 2];
            }
         }
      };
   }
}
