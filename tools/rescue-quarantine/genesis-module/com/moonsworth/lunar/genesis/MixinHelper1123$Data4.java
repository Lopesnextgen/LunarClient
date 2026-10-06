package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

@Annotation4
final class MixinHelper1123$Data4<C extends Comparable<?>> extends MixinHelper47<SerializableLoader<C>, Range<C>> {
   private final NavigableMap<SerializableLoader<C>, Range<C>> field1;
   private final Range<SerializableLoader<C>> field2;

   MixinHelper1123$Data4(NavigableMap<SerializableLoader<C>, Range<C>> var1) {
      this.field1 = var1;
      this.field2 = Range.method16();
   }

   private MixinHelper1123$Data4(NavigableMap<SerializableLoader<C>, Range<C>> var1, Range<SerializableLoader<C>> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   private NavigableMap<SerializableLoader<C>, Range<C>> method1(Range<SerializableLoader<C>> var1) {
      return var1.method22(this.field2) ? new MixinHelper1123$Data4<>(this.field1, var1.method23(this.field2)) : ImmutableSortedMap.method3();
   }

   public NavigableMap<SerializableLoader<C>, Range<C>> method2(SerializableLoader<C> var1, boolean var2, SerializableLoader<C> var3, boolean var4) {
      return this.method1(Range.method9(var1, MixinHelperType_3.forBoolean(var2), var3, MixinHelperType_3.forBoolean(var4)));
   }

   public NavigableMap<SerializableLoader<C>, Range<C>> method3(SerializableLoader<C> var1, boolean var2) {
      return this.method1(Range.method12(var1, MixinHelperType_3.forBoolean(var2)));
   }

   public NavigableMap<SerializableLoader<C>, Range<C>> method4(SerializableLoader<C> var1, boolean var2) {
      return this.method1(Range.method15(var1, MixinHelperType_3.forBoolean(var2)));
   }

   @Override
   public java.util.Comparator<? super SerializableLoader<C>> comparator() {
      return Ordering.method1();
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      return this.method5(var1) != null;
   }

   public Range<C> method5(@Nullable Object var1) {
      if (var1 instanceof SerializableLoader) {
         try {
            SerializableLoader var2 = (SerializableLoader)var1;
            if (!this.field2.contains(var2)) {
               return null;
            }

            Entry var3 = this.field1.lowerEntry(var2);
            if (var3 != null && ((Range)var3.getValue()).field3.equals(var2)) {
               return (Range<C>)var3.getValue();
            }
         } catch (ClassCastException var4) {
            return null;
         }
      }

      return null;
   }

   @Override
   Iterator<Entry<SerializableLoader<C>, Range<C>>> entryIterator() {
      final Iterator var1;
      if (!this.field2.hasLowerBound()) {
         var1 = this.field1.values().iterator();
      } else {
         Entry var2 = this.field1.lowerEntry(this.field2.lowerEndpoint());
         if (var2 == null) {
            var1 = this.field1.values().iterator();
         } else if (this.field2.field2.isLessThan(((Range)var2.getValue()).field3)) {
            var1 = this.field1.tailMap((SerializableLoader<C>)var2.getKey(), true).values().iterator();
         } else {
            var1 = this.field1.tailMap(this.field2.lowerEndpoint(), true).values().iterator();
         }
      }

      return new MixinHelperIterator32_2<Entry<SerializableLoader<C>, Range<C>>>() {
         protected Entry<SerializableLoader<C>, Range<C>> computeNext() {
            if (!var1.hasNext()) {
               return (Entry<SerializableLoader<C>, Range<C>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
            }

            Range var1x = (Range)var1.next();
            return MixinHelper1123$Data4.this.field2.field3.isLessThan(var1x.field3)
               ? (Entry)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH()
               : Maps.immutableEntry(var1x.field3, var1x);
         }
      };
   }

   @Override
   Iterator<Entry<SerializableLoader<C>, Range<C>>> descendingEntryIterator() {
      Collection var1;
      if (this.field2.hasUpperBound()) {
         var1 = this.field1.headMap(this.field2.upperEndpoint(), false).descendingMap().values();
      } else {
         var1 = this.field1.descendingMap().values();
      }

      final PeekingIterator var2 = Iterators.method22(var1.iterator());
      if (var2.hasNext() && this.field2.field3.isLessThan(((Range)var2.peek()).field3)) {
         var2.next();
      }

      return new MixinHelperIterator32_2<Entry<SerializableLoader<C>, Range<C>>>() {
         protected Entry<SerializableLoader<C>, Range<C>> computeNext() {
            if (!var2.hasNext()) {
               return (Entry<SerializableLoader<C>, Range<C>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
            }

            Range var1x = (Range)var2.next();
            return MixinHelper1123$Data4.this.field2.field2.isLessThan(var1x.field3)
               ? Maps.immutableEntry(var1x.field3, var1x)
               : (Entry)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
         }
      };
   }

   @Override
   public int size() {
      return this.field2.equals(Range.method16()) ? this.field1.size() : Iterators.size(this.entryIterator());
   }

   @Override
   public boolean isEmpty() {
      return this.field2.equals(Range.method16()) ? this.field1.isEmpty() : !this.entryIterator().hasNext();
   }
}
