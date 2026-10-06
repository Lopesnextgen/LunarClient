package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Comparator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Objects;
import com.google.common.collect.Range;
import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
final class GeneralRange<T> implements Serializable {
   private final Comparator<? super T> field1;
   private final boolean field2;
   private final @Nullable T field3;
   private final BoundType field4;
   private final boolean field5;
   private final @Nullable T field6;
   private final BoundType field7;
   private transient @Nullable GeneralRange<T> field8;

   static <T extends Comparable> GeneralRange<T> method1(Range<T> serializablebase20) {
      Comparable comparable1 = serializablebase20.hasLowerBound() ? serializablebase20.lowerEndpoint() : null;
      BoundType mixinhelpertype_32 = serializablebase20.hasLowerBound() ? serializablebase20.method19() : BoundType.OPEN;
      Comparable comparable3 = serializablebase20.hasUpperBound() ? serializablebase20.upperEndpoint() : null;
      BoundType mixinhelpertype_34 = serializablebase20.hasUpperBound() ? serializablebase20.method20() : BoundType.OPEN;
      return new GeneralRange<>(
         com.google.common.collect.Ordering.method1(), serializablebase20.hasLowerBound(), (T)comparable1, mixinhelpertype_32, serializablebase20.hasUpperBound(), (T)comparable3, mixinhelpertype_34
      );
   }

   static <T> GeneralRange<T> method2(Comparator<? super T> comparator0) {
      return new GeneralRange<>(comparator0, false, null, BoundType.OPEN, false, null, BoundType.OPEN);
   }

   static <T> GeneralRange<T> method3(Comparator<? super T> comparator0, @Nullable T value1, BoundType mixinhelpertype_32) {
      return new GeneralRange<>(comparator0, true, (T)value1, mixinhelpertype_32, false, null, BoundType.OPEN);
   }

   static <T> GeneralRange<T> method4(Comparator<? super T> comparator0, @Nullable T value1, BoundType mixinhelpertype_32) {
      return new GeneralRange<>(comparator0, false, null, BoundType.OPEN, true, (T)value1, mixinhelpertype_32);
   }

   static <T> GeneralRange<T> method5(Comparator<? super T> comparator0, @Nullable T value1, BoundType mixinhelpertype_32, @Nullable T value3, BoundType mixinhelpertype_34) {
      return new GeneralRange<>(comparator0, true, (T)value1, mixinhelpertype_32, true, (T)value3, mixinhelpertype_34);
   }

   private GeneralRange(
      Comparator<? super T> comparator1, boolean flag2, @Nullable T value3, BoundType mixinhelpertype_34, boolean flag5, @Nullable T value6, BoundType mixinhelpertype_37
   ) {
      this.field1 = Preconditions.checkNotNull(comparator1);
      this.field2 = flag2;
      this.field5 = flag5;
      this.field3 = (T)value3;
      this.field4 = Preconditions.checkNotNull(mixinhelpertype_34);
      this.field6 = (T)value6;
      this.field7 = Preconditions.checkNotNull(mixinhelpertype_37);
      if (flag2) {
         comparator1.compare(value3, value3);
      }

      if (flag5) {
         comparator1.compare(value6, value6);
      }

      if (flag2 && flag5) {
         int number8 = comparator1.compare(value3, value6);
         Preconditions.checkArgument(number8 <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", value3, value6);
         if (number8 == 0) {
            Preconditions.checkArgument(mixinhelpertype_34 != BoundType.OPEN | mixinhelpertype_37 != BoundType.OPEN);
         }
      }
   }

   Comparator<? super T> comparator() {
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

   boolean tooLow(@Nullable T value1) {
      if (!this.hasLowerBound()) {
         return false;
      }

      Object obj2 = this.getLowerEndpoint();
      int number3 = this.field1.compare((T)value1, (T)obj2);
      return number3 < 0 | number3 == 0 & this.method8() == BoundType.OPEN;
   }

   boolean tooHigh(@Nullable T value1) {
      if (!this.hasUpperBound()) {
         return false;
      }

      Object obj2 = this.getUpperEndpoint();
      int number3 = this.field1.compare((T)value1, (T)obj2);
      return number3 > 0 | number3 == 0 & this.method9() == BoundType.OPEN;
   }

   boolean contains(@Nullable T value1) {
      return !this.tooLow((T)value1) && !this.tooHigh((T)value1);
   }

   GeneralRange<T> method6(GeneralRange<T> serializableimpl21) {
      Preconditions.checkNotNull(serializableimpl21);
      Preconditions.checkArgument(this.field1.equals(serializableimpl21.field1));
      boolean flag2 = this.field2;
      Object obj3 = this.getLowerEndpoint();
      BoundType mixinhelpertype_34 = this.method8();
      if (!this.hasLowerBound()) {
         flag2 = serializableimpl21.field2;
         obj3 = serializableimpl21.getLowerEndpoint();
         mixinhelpertype_34 = serializableimpl21.method8();
      } else if (serializableimpl21.hasLowerBound()) {
         int number5 = this.field1.compare(this.getLowerEndpoint(), (T)serializableimpl21.getLowerEndpoint());
         if (number5 < 0 || number5 == 0 && serializableimpl21.method8() == BoundType.OPEN) {
            obj3 = serializableimpl21.getLowerEndpoint();
            mixinhelpertype_34 = serializableimpl21.method8();
         }
      }

      boolean flag9 = this.field5;
      Object obj6 = this.getUpperEndpoint();
      BoundType mixinhelpertype_37 = this.method9();
      if (!this.hasUpperBound()) {
         flag9 = serializableimpl21.field5;
         obj6 = serializableimpl21.getUpperEndpoint();
         mixinhelpertype_37 = serializableimpl21.method9();
      } else if (serializableimpl21.hasUpperBound()) {
         int number8 = this.field1.compare(this.getUpperEndpoint(), (T)serializableimpl21.getUpperEndpoint());
         if (number8 > 0 || number8 == 0 && serializableimpl21.method9() == BoundType.OPEN) {
            obj6 = serializableimpl21.getUpperEndpoint();
            mixinhelpertype_37 = serializableimpl21.method9();
         }
      }

      if (flag2 && flag9) {
         int number10 = this.field1.compare((T)obj3, (T)obj6);
         if (number10 > 0 || number10 == 0 && mixinhelpertype_34 == BoundType.OPEN && mixinhelpertype_37 == BoundType.OPEN) {
            obj3 = obj6;
            mixinhelpertype_34 = BoundType.OPEN;
            mixinhelpertype_37 = BoundType.CLOSED;
         }
      }

      return new GeneralRange<>(this.field1, flag2, (T)obj3, mixinhelpertype_34, flag9, (T)obj6, mixinhelpertype_37);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof GeneralRange)) {
         return false;
      }

      GeneralRange serializableimpl22 = (GeneralRange)obj1;
      return this.field1.equals(serializableimpl22.field1)
         && this.field2 == serializableimpl22.field2
         && this.field5 == serializableimpl22.field5
         && this.method8().equals(serializableimpl22.method8())
         && this.method9().equals(serializableimpl22.method9())
         && Objects.equal(this.getLowerEndpoint(), serializableimpl22.getLowerEndpoint())
         && Objects.equal(this.getUpperEndpoint(), serializableimpl22.getUpperEndpoint());
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(new Object[]{this.field1, this.getLowerEndpoint(), this.method8(), this.getUpperEndpoint(), this.method9()});
   }

   GeneralRange<T> method7() {
      GeneralRange serializableimpl21 = this.field8;
      if (serializableimpl21 == null) {
         serializableimpl21 = new GeneralRange(
            com.google.common.collect.Ordering.method2(this.field1).method9(),
            this.field5,
            this.getUpperEndpoint(),
            this.method9(),
            this.field2,
            this.getLowerEndpoint(),
            this.method8()
         );
         serializableimpl21.field8 = this;
         return this.field8 = serializableimpl21;
      } else {
         return serializableimpl21;
      }
   }

   @Override
   public String toString() {
      return this.field1
         + ":"
         + (this.field4 == BoundType.CLOSED ? 91 : 40)
         + (this.field2 ? this.field3 : "-∞")
         + ','
         + (this.field5 ? this.field6 : "∞")
         + (this.field7 == BoundType.CLOSED ? 93 : 41);
   }

   T getLowerEndpoint() {
      return this.field3;
   }

   BoundType method8() {
      return this.field4;
   }

   T getUpperEndpoint() {
      return this.field6;
   }

   BoundType method9() {
      return this.field7;
   }
}
