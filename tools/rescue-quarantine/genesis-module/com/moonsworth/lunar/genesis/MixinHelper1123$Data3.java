package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.Maps;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Range;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

final class MixinHelper1123$Data3<C extends Comparable<?>> extends MixinHelper47<SerializableLoader<C>, Range<C>> {
   private final NavigableMap<SerializableLoader<C>, Range<C>> field1;
   private final NavigableMap<SerializableLoader<C>, Range<C>> field2;
   private final Range<SerializableLoader<C>> field3;

   MixinHelper1123$Data3(NavigableMap<SerializableLoader<C>, Range<C>> var1) {
      this(var1, Range.method16());
   }

   private MixinHelper1123$Data3(NavigableMap<SerializableLoader<C>, Range<C>> var1, Range<SerializableLoader<C>> var2) {
      this.field1 = var1;
      this.field2 = new MixinHelper1123$Data4<>(var1);
      this.field3 = var2;
   }

   private NavigableMap<SerializableLoader<C>, Range<C>> method1(Range<SerializableLoader<C>> var1) {
      if (!this.field3.method22(var1)) {
         return ImmutableSortedMap.method3();
      }

      var1 = var1.method23(this.field3);
      return new MixinHelper1123$Data3<>(this.field1, var1);
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
   Iterator<Entry<SerializableLoader<C>, Range<C>>> entryIterator() {
      Collection var1;
      if (this.field3.hasLowerBound()) {
         var1 = this.field2.tailMap(this.field3.lowerEndpoint(), this.field3.method19() == MixinHelperType_3.CLOSED).values();
      } else {
         var1 = this.field2.values();
      }

      final PeekingIterator var2 = Iterators.method22(var1.iterator());
      final SerializableLoader var3;
      if (!this.field3.contains(SerializableLoader.method9()) || var2.hasNext() && ((Range)var2.peek()).field2 == SerializableLoader.method9()) {
         if (!var2.hasNext()) {
            return Iterators.method1();
         }

         var3 = ((Range)var2.next()).field3;
      } else {
         var3 = SerializableLoader.method9();
      }

      return new MixinHelperIterator32_2<Entry<SerializableLoader<C>, Range<C>>>() {
         SerializableLoader<C> field2 = var3;

         protected Entry<SerializableLoader<C>, Range<C>> computeNext() {
            if (!MixinHelper1123$Data3.this.field3.field3.isLessThan(this.field2) && this.field2 != SerializableLoader.method10()) {
               Range var1x;
               if (var2.hasNext()) {
                  Range var2x = (Range)var2.next();
                  var1x = Range.method4(this.field2, var2x.field2);
                  this.field2 = var2x.field3;
               } else {
                  var1x = Range.method4(this.field2, SerializableLoader.method10());
                  this.field2 = SerializableLoader.method10();
               }

               return Maps.immutableEntry(var1x.field2, var1x);
            } else {
               return (Entry<SerializableLoader<C>, Range<C>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
            }
         }
      };
   }

   @Override
   Iterator<Entry<SerializableLoader<C>, Range<C>>> descendingEntryIterator() {
      SerializableLoader var1 = this.field3.hasUpperBound() ? this.field3.upperEndpoint() : SerializableLoader.method10();
      boolean var2 = this.field3.hasUpperBound() && this.field3.method20() == MixinHelperType_3.CLOSED;
      final PeekingIterator var3 = Iterators.method22(this.field2.headMap(var1, var2).descendingMap().values().iterator());
      SerializableLoader var4;
      if (var3.hasNext()) {
         var4 = ((Range)var3.peek()).field3 == SerializableLoader.method10()
            ? ((Range)var3.next()).field2
            : this.field1.higherKey(((Range)var3.peek()).field3);
      } else {
         if (!this.field3.contains(SerializableLoader.method9()) || this.field1.containsKey(SerializableLoader.method9())) {
            return Iterators.method1();
         }

         var4 = this.field1.higherKey(SerializableLoader.method9());
      }

      final SerializableLoader var5 = MoreObjects.firstNonNull(var4, SerializableLoader.method10());
      return new MixinHelperIterator32_2<Entry<SerializableLoader<C>, Range<C>>>() {
         SerializableLoader<C> field2 = var5;

         protected Entry<SerializableLoader<C>, Range<C>> computeNext() {
            if (this.field2 == SerializableLoader.method9()) {
               return (Entry<SerializableLoader<C>, Range<C>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
            }

            if (var3.hasNext()) {
               Range var1x = (Range)var3.next();
               Range var2x = Range.method4(var1x.field3, this.field2);
               this.field2 = var1x.field2;
               if (MixinHelper1123$Data3.this.field3.field2.isLessThan(var2x.field2)) {
                  return Maps.immutableEntry(var2x.field2, var2x);
               }
            } else if (MixinHelper1123$Data3.this.field3.field2.isLessThan(SerializableLoader.method9())) {
               Range var3x = Range.method4(SerializableLoader.method9(), this.field2);
               this.field2 = SerializableLoader.method9();
               return Maps.immutableEntry(SerializableLoader.method9(), var3x);
            }

            return (Entry<SerializableLoader<C>, Range<C>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
         }
      };
   }

   @Override
   public int size() {
      return Iterators.size(this.entryIterator());
   }

   public @Nullable Range<C> method5(Object var1) {
      if (var1 instanceof SerializableLoader) {
         try {
            SerializableLoader var2 = (SerializableLoader)var1;
            Entry var3 = this.method4(var2, true).firstEntry();
            if (var3 != null && ((SerializableLoader)var3.getKey()).equals(var2)) {
               return (Range<C>)var3.getValue();
            }
         } catch (ClassCastException var4) {
            return null;
         }
      }

      return null;
   }

   @Override
   public boolean containsKey(Object var1) {
      return this.method5(var1) != null;
   }
}
