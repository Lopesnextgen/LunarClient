package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.base.Preconditions;

public final class MixinHelper16$Data6 {
   private final MixinHelper16_2 field1;
   private final String field2;

   private MixinHelper16$Data6(MixinHelper16_2 var1, String var2) {
      this.field1 = var1;
      this.field2 = Preconditions.checkNotNull(var2);
   }

   @CanIgnoreReturnValue
   public <A extends Appendable> A appendTo(A var1, Map<?, ?> var2) {
      return this.appendTo((A)var1, var2.entrySet());
   }

   @CanIgnoreReturnValue
   public StringBuilder appendTo(StringBuilder var1, Map<?, ?> var2) {
      return this.appendTo(var1, var2.entrySet());
   }

   @Annotation2
   @CanIgnoreReturnValue
   public <A extends Appendable> A appendTo(A var1, Iterable<? extends Entry<?, ?>> var2) {
      return this.appendTo((A)var1, var2.iterator());
   }

   @Annotation2
   @CanIgnoreReturnValue
   public <A extends Appendable> A appendTo(A var1, Iterator<? extends Entry<?, ?>> var2) {
      Preconditions.checkNotNull(var1);
      if (var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         var1.append(this.field1.toString(var3.getKey()));
         var1.append(this.field2);
         var1.append(this.field1.toString(var3.getValue()));

         while (var2.hasNext()) {
            var1.append(MixinHelper16_2.method17(this.field1));
            Entry var4 = (Entry)var2.next();
            var1.append(this.field1.toString(var4.getKey()));
            var1.append(this.field2);
            var1.append(this.field1.toString(var4.getValue()));
         }
      }

      return (A)var1;
   }

   @Annotation2
   @CanIgnoreReturnValue
   public StringBuilder appendTo(StringBuilder var1, Iterable<? extends Entry<?, ?>> var2) {
      return this.appendTo(var1, var2.iterator());
   }

   @Annotation2
   @CanIgnoreReturnValue
   public StringBuilder appendTo(StringBuilder var1, Iterator<? extends Entry<?, ?>> var2) {
      try {
         this.appendTo((StringBuilder)var1, var2);
         return var1;
      } catch (IOException var4) {
         throw new AssertionError(var4);
      }
   }

   public String join(Map<?, ?> var1) {
      return this.join(var1.entrySet());
   }

   @Annotation2
   public String join(Iterable<? extends Entry<?, ?>> var1) {
      return this.join(var1.iterator());
   }

   @Annotation2
   public String join(Iterator<? extends Entry<?, ?>> var1) {
      return this.appendTo(new StringBuilder(), var1).toString();
   }

   public MixinHelper16$Data6 method1(String var1) {
      return new MixinHelper16$Data6(this.field1.method13(var1), this.field2);
   }
}
