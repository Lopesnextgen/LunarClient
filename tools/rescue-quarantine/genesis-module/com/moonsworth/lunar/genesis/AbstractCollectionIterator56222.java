package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Collection;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.common.collect.ContiguousSet;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;

@GwtCompatible(emulated = true)
final class AbstractCollectionIterator56222<C extends Comparable> extends ContiguousSet<C> {
   private final Range<C> field14;
   private static final long field15 = 0L;

   AbstractCollectionIterator56222(Range<C> var1, MixinHelper40<C> var2) {
      super(var2);
      this.field14 = var1;
   }

   private ContiguousSet<C> method1(Range<C> var1) {
      return this.field14.method22(var1)
         ? ContiguousSet.method1(this.field14.method23(var1), this.field13)
         : new AbstractCollectionIterator56223<>(this.field13);
   }

   @Override
   ContiguousSet<C> method12(C var1, boolean var2) {
      return this.method1(Range.method12((C)var1, MixinHelperType_3.forBoolean(var2)));
   }

   @Override
   ContiguousSet<C> method13(C var1, boolean var2, C var3, boolean var4) {
      return var1.compareTo(var3) == 0 && !var2 && !var4
         ? new AbstractCollectionIterator56223<>(this.field13)
         : this.method1(Range.method9((C)var1, MixinHelperType_3.forBoolean(var2), (C)var3, MixinHelperType_3.forBoolean(var4)));
   }

   @Override
   ContiguousSet<C> method14(C var1, boolean var2) {
      return this.method1(Range.method15((C)var1, MixinHelperType_3.forBoolean(var2)));
   }

   @Annotation3
   @Override
   int indexOf(Object var1) {
      return this.contains(var1) ? (int)this.field13.distance(this.first(), (C)var1) : -1;
   }

   @Override
   public MixinHelperIterator3<C> method1() {
      return new MixinHelperIterator33<C>(this.first()) {
         final Comparable field1 = AbstractCollectionIterator56222.this.last();

         protected C computeNext(C var1) {
            return (C)(AbstractCollectionIterator56222.equalsOrThrow(var1, this.field1)
               ? null
               : AbstractCollectionIterator56222.this.field13.next(var1));
         }
      };
   }

   @Annotation3
   @Override
   public MixinHelperIterator3<C> method33() {
      return new MixinHelperIterator33<C>(this.last()) {
         final Comparable field1 = AbstractCollectionIterator56222.this.first();

         protected C computeNext(C var1) {
            return (C)(AbstractCollectionIterator56222.equalsOrThrow(var1, this.field1)
               ? null
               : AbstractCollectionIterator56222.this.field13.previous(var1));
         }
      };
   }

   private static boolean equalsOrThrow(Comparable<?> var0, @Nullable Comparable<?> var1) {
      return var1 != null && Range.compareOrThrow(var0, var1) == 0;
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   public C first() {
      return this.field14.field2.method5(this.field13);
   }

   public C last() {
      return this.field14.field3.method6(this.field13);
   }

   @Override
   ImmutableList<C> method17() {
      return this.field13.field1 ? new AbstractCollectionIterator33<C>() {
         ImmutableSortedSet<C> method5() {
            return AbstractCollectionIterator56222.this;
         }

         public C get(int var1) {
            Preconditions.checkElementIndex(var1, this.size());
            return (C)AbstractCollectionIterator56222.this.field13.offset(AbstractCollectionIterator56222.this.first(), var1);
         }
      } : super.method17();
   }

   @Override
   public int size() {
      long var1 = this.field13.distance(this.first(), this.last());
      return var1 >= 2147483647L ? Integer.MAX_VALUE : (int)var1 + 1;
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      if (var1 == null) {
         return false;
      }

      try {
         return this.field14.contains((C)var1);
      } catch (ClassCastException var3) {
         return false;
      }
   }

   @Override
   public boolean containsAll(Collection<?> var1) {
      return MixinHelper39.containsAllImpl(this, var1);
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   @Override
   public ContiguousSet<C> method15(ContiguousSet<C> var1) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkArgument(this.field13.equals(var1.field13));
      if (var1.isEmpty()) {
         return var1;
      }

      Comparable var2 = Ordering.method1().max(this.first(), (Comparable)var1.first());
      Comparable var3 = Ordering.method1().min(this.last(), (Comparable)var1.last());
      return var2.compareTo(var3) <= 0
         ? ContiguousSet.method1(Range.method6((C)var2, (C)var3), this.field13)
         : new AbstractCollectionIterator56223<>(this.field13);
   }

   @Override
   public Range<C> method16() {
      return this.method17(MixinHelperType_3.CLOSED, MixinHelperType_3.CLOSED);
   }

   @Override
   public Range<C> method17(MixinHelperType_3 var1, MixinHelperType_3 var2) {
      return Range.method4(
         this.field14.field2.method3(var1, this.field13), this.field14.field3.method4(var2, this.field13)
      );
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (var1 instanceof AbstractCollectionIterator56222) {
         AbstractCollectionIterator56222 var2 = (AbstractCollectionIterator56222)var1;
         if (this.field13.equals(var2.field13)) {
            return this.first().equals(var2.first()) && this.last().equals(var2.last());
         }
      }

      return super.equals(var1);
   }

   @Override
   public int hashCode() {
      return Sets.hashCodeImpl(this);
   }

   @Annotation3
   @Override
   Object writeReplace() {
      return new AbstractCollectionIterator56222.Data(this.field14, this.field13);
   }

   @Annotation3
   private static final class Data<C extends Comparable> implements Serializable {
      final Range<C> field1;
      final MixinHelper40<C> field2;

      private Data(Range<C> var1, MixinHelper40<C> var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      private Object readResolve() {
         return new AbstractCollectionIterator56222<>(this.field1, this.field2);
      }
   }
}
