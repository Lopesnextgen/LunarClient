package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Iterators;

final class MixinHelper1123$Data5<C extends Comparable<?>> extends MixinHelper47<SerializableLoader<C>, Range<C>> {
   private final Range<SerializableLoader<C>> field1;
   private final Range<C> field2;
   private final NavigableMap<SerializableLoader<C>, Range<C>> field3;
   private final NavigableMap<SerializableLoader<C>, Range<C>> field4;

   private MixinHelper1123$Data5(
      Range<SerializableLoader<C>> var1, Range<C> var2, NavigableMap<SerializableLoader<C>, Range<C>> var3
   ) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
      this.field3 = Preconditions.checkNotNull(var3);
      this.field4 = new MixinHelper1123$Data4<>(var3);
   }

   private NavigableMap<SerializableLoader<C>, Range<C>> method1(Range<SerializableLoader<C>> var1) {
      return !var1.method22(this.field1) ? ImmutableSortedMap.method3() : new MixinHelper1123$Data5<>(this.field1.method23(var1), this.field2, this.field3);
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

   public @Nullable Range<C> method5(@Nullable Object var1) {
      if (var1 instanceof SerializableLoader) {
         try {
            SerializableLoader var2 = (SerializableLoader)var1;
            if (!this.field1.contains(var2) || var2.method8(this.field2.field2) < 0 || var2.method8(this.field2.field3) >= 0) {
               return null;
            }

            if (var2.equals(this.field2.field2)) {
               Range var3 = Maps.valueOrNull(this.field3.floorEntry(var2));
               if (var3 != null && var3.field3.method8(this.field2.field2) > 0) {
                  return var3.method23(this.field2);
               }
            } else {
               Range var5 = this.field3.get(var2);
               if (var5 != null) {
                  return var5.method23(this.field2);
               }
            }
         } catch (ClassCastException var4) {
            return null;
         }
      }

      return null;
   }

   @Override
   Iterator<Entry<SerializableLoader<C>, Range<C>>> entryIterator() {
      if (this.field2.isEmpty()) {
         return Iterators.method1();
      }

      if (this.field1.field3.isLessThan(this.field2.field2)) {
         return Iterators.method1();
      }

      final Iterator var1;
      if (this.field1.field2.isLessThan(this.field2.field2)) {
         var1 = this.field4.tailMap(this.field2.field2, false).values().iterator();
      } else {
         var1 = this.field3.tailMap(this.field1.field2.endpoint(), this.field1.method19() == MixinHelperType_3.CLOSED).values().iterator();
      }

      final SerializableLoader var2 = Ordering.method1().min(this.field1.field3, SerializableLoader.method11(this.field2.field3));
      return new MixinHelperIterator32_2<Entry<SerializableLoader<C>, Range<C>>>() {
         protected Entry<SerializableLoader<C>, Range<C>> computeNext() {
            if (!var1.hasNext()) {
               return (Entry<SerializableLoader<C>, Range<C>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
            }

            Range var1x = (Range)var1.next();
            if (var2.isLessThan(var1x.field2)) {
               return (Entry<SerializableLoader<C>, Range<C>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
            }

            var1x = var1x.method23(MixinHelper1123$Data5.this.field2);
            return Maps.immutableEntry(var1x.field2, var1x);
         }
      };
   }

   @Override
   Iterator<Entry<SerializableLoader<C>, Range<C>>> descendingEntryIterator() {
      if (this.field2.isEmpty()) {
         return Iterators.method1();
      }

      SerializableLoader var1 = Ordering.method1().min(this.field1.field3, SerializableLoader.method11(this.field2.field3));
      final Iterator var2 = this.field3.headMap(var1.endpoint(), var1.method2() == MixinHelperType_3.CLOSED).descendingMap().values().iterator();
      return new MixinHelperIterator32_2<Entry<SerializableLoader<C>, Range<C>>>() {
         protected Entry<SerializableLoader<C>, Range<C>> computeNext() {
            if (!var2.hasNext()) {
               return (Entry<SerializableLoader<C>, Range<C>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
            }

            Range var1x = (Range)var2.next();
            if (MixinHelper1123$Data5.this.field2.field2.method8(var1x.field3) >= 0) {
               return (Entry<SerializableLoader<C>, Range<C>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
            }

            var1x = var1x.method23(MixinHelper1123$Data5.this.field2);
            return MixinHelper1123$Data5.this.field1.contains(var1x.field2)
               ? Maps.immutableEntry(var1x.field2, var1x)
               : (Entry)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
         }
      };
   }

   @Override
   public int size() {
      return Iterators.size(this.entryIterator());
   }
}
