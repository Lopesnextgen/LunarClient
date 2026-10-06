package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class SerializableImpl2<T> implements Serializable {
   private final java.util.Comparator<? super T> field1;
   private final boolean field2;
   private final @Nullable T field3;
   private final MixinHelperType_3 field4;
   private final boolean field5;
   private final @Nullable T field6;
   private final MixinHelperType_3 field7;
   private transient @Nullable SerializableImpl2<T> field8;

   static <T extends Comparable> SerializableImpl2<T> method1(Range<T> var0) {
      Comparable var1 = var0.hasLowerBound() ? var0.lowerEndpoint() : null;
      MixinHelperType_3 var2 = var0.hasLowerBound() ? var0.method19() : MixinHelperType_3.OPEN;
      Comparable var3 = var0.hasUpperBound() ? var0.upperEndpoint() : null;
      MixinHelperType_3 var4 = var0.hasUpperBound() ? var0.method20() : MixinHelperType_3.OPEN;
      return new SerializableImpl2<>(Ordering.method1(), var0.hasLowerBound(), (T)var1, var2, var0.hasUpperBound(), (T)var3, var4);
   }

   static <T> SerializableImpl2<T> method2(java.util.Comparator<? super T> var0) {
      return new SerializableImpl2<>(var0, false, null, MixinHelperType_3.OPEN, false, null, MixinHelperType_3.OPEN);
   }

   static <T> SerializableImpl2<T> method3(java.util.Comparator<? super T> var0, @Nullable T var1, MixinHelperType_3 var2) {
      return new SerializableImpl2<>(var0, true, (T)var1, var2, false, null, MixinHelperType_3.OPEN);
   }

   static <T> SerializableImpl2<T> method4(java.util.Comparator<? super T> var0, @Nullable T var1, MixinHelperType_3 var2) {
      return new SerializableImpl2<>(var0, false, null, MixinHelperType_3.OPEN, true, (T)var1, var2);
   }

   static <T> SerializableImpl2<T> method5(
      java.util.Comparator<? super T> var0, @Nullable T var1, MixinHelperType_3 var2, @Nullable T var3, MixinHelperType_3 var4
   ) {
      return new SerializableImpl2<>(var0, true, (T)var1, var2, true, (T)var3, var4);
   }

   private SerializableImpl2(
      java.util.Comparator<? super T> var1, boolean var2, @Nullable T var3, MixinHelperType_3 var4, boolean var5, @Nullable T var6, MixinHelperType_3 var7
   ) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = var2;
      this.field5 = var5;
      this.field3 = (T)var3;
      this.field4 = Preconditions.checkNotNull(var4);
      this.field6 = (T)var6;
      this.field7 = Preconditions.checkNotNull(var7);
      if (var2) {
         var1.compare(var3, var3);
      }

      if (var5) {
         var1.compare(var6, var6);
      }

      if (var2 && var5) {
         int var8 = var1.compare(var3, var6);
         Preconditions.checkArgument(var8 <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", var3, var6);
         if (var8 == 0) {
            Preconditions.checkArgument(var4 != MixinHelperType_3.OPEN | var7 != MixinHelperType_3.OPEN);
         }
      }
   }

   java.util.Comparator<? super T> comparator() {
      return this.field1;
   }

   boolean hasLowerBound() {
      return this.field2;
   }

   boolean hasUpperBound() {
      return this.field5;
   }

   boolean isEmpty() {
      return this.hasUpperBound() && this.tooLow(this.getUpperEndpoint()) || this.hasLowerBound() && this.tooHigh(this.getLowerEndpoint());
   }

   boolean tooLow(@Nullable T var1) {
      if (!this.hasLowerBound()) {
         return false;
      }

      Object var2 = this.getLowerEndpoint();
      int var3 = this.field1.compare((T)var1, (T)var2);
      return var3 < 0 | var3 == 0 & this.method8() == MixinHelperType_3.OPEN;
   }

   boolean tooHigh(@Nullable T var1) {
      if (!this.hasUpperBound()) {
         return false;
      }

      Object var2 = this.getUpperEndpoint();
      int var3 = this.field1.compare((T)var1, (T)var2);
      return var3 > 0 | var3 == 0 & this.method9() == MixinHelperType_3.OPEN;
   }

   boolean contains(@Nullable T var1) {
      return !this.tooLow((T)var1) && !this.tooHigh((T)var1);
   }

   SerializableImpl2<T> method6(SerializableImpl2<T> var1) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkArgument(this.field1.equals(var1.field1));
      boolean var2 = this.field2;
      Object var3 = this.getLowerEndpoint();
      MixinHelperType_3 var4 = this.method8();
      if (!this.hasLowerBound()) {
         var2 = var1.field2;
         var3 = var1.getLowerEndpoint();
         var4 = var1.method8();
      } else if (var1.hasLowerBound()) {
         int var5 = this.field1.compare(this.getLowerEndpoint(), (T)var1.getLowerEndpoint());
         if (var5 < 0 || var5 == 0 && var1.method8() == MixinHelperType_3.OPEN) {
            var3 = var1.getLowerEndpoint();
            var4 = var1.method8();
         }
      }

      boolean var9 = this.field5;
      Object var6 = this.getUpperEndpoint();
      MixinHelperType_3 var7 = this.method9();
      if (!this.hasUpperBound()) {
         var9 = var1.field5;
         var6 = var1.getUpperEndpoint();
         var7 = var1.method9();
      } else if (var1.hasUpperBound()) {
         int var8 = this.field1.compare(this.getUpperEndpoint(), (T)var1.getUpperEndpoint());
         if (var8 > 0 || var8 == 0 && var1.method9() == MixinHelperType_3.OPEN) {
            var6 = var1.getUpperEndpoint();
            var7 = var1.method9();
         }
      }

      if (var2 && var9) {
         int var10 = this.field1.compare((T)var3, (T)var6);
         if (var10 > 0 || var10 == 0 && var4 == MixinHelperType_3.OPEN && var7 == MixinHelperType_3.OPEN) {
            var3 = var6;
            var4 = MixinHelperType_3.OPEN;
            var7 = MixinHelperType_3.CLOSED;
         }
      }

      return new SerializableImpl2<>(this.field1, var2, (T)var3, var4, var9, (T)var6, var7);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (!(var1 instanceof SerializableImpl2)) {
         return false;
      }

      SerializableImpl2 var2 = (SerializableImpl2)var1;
      return this.field1.equals(var2.field1)
         && this.field2 == var2.field2
         && this.field5 == var2.field5
         && this.method8().equals(var2.method8())
         && this.method9().equals(var2.method9())
         && MixinHelper72.equal(this.getLowerEndpoint(), var2.getLowerEndpoint())
         && MixinHelper72.equal(this.getUpperEndpoint(), var2.getUpperEndpoint());
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.field1, this.getLowerEndpoint(), this.method8(), this.getUpperEndpoint(), this.method9());
   }

   SerializableImpl2<T> method7() {
      SerializableImpl2 var1 = this.field8;
      if (var1 == null) {
         var1 = new SerializableImpl2<>(
            Ordering.method2(this.field1).method9(),
            this.field5,
            this.getUpperEndpoint(),
            this.method9(),
            this.field2,
            this.getLowerEndpoint(),
            this.method8()
         );
         var1.field8 = this;
         return this.field8 = var1;
      } else {
         return var1;
      }
   }

   @Override
   public String toString() {
      return this.field1
         + ":"
         + (this.field4 == MixinHelperType_3.CLOSED ? 91 : 40)
         + (this.field2 ? this.field3 : "-∞")
         + ','
         + (this.field5 ? this.field6 : "∞")
         + (this.field7 == MixinHelperType_3.CLOSED ? 93 : 41);
   }

   T getLowerEndpoint() {
      return this.field3;
   }

   MixinHelperType_3 method8() {
      return this.field4;
   }

   T getUpperEndpoint() {
      return this.field6;
   }

   MixinHelperType_3 method9() {
      return this.field7;
   }
}
