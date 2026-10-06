package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.BoundType;
import com.google.common.eventbus.Subscribe;
import com.google.common.collect.Range;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.ImmutableSortedMap;

@Subscribe
final class TreeRangeSet$RangesByUpperBound<C extends Comparable<?>> extends MixinHelper47<SerializableLoader<C>, Range<C>> {
   private final NavigableMap<SerializableLoader<C>, Range<C>> field1;
   private final Range<SerializableLoader<C>> field2;

   TreeRangeSet$RangesByUpperBound(NavigableMap<SerializableLoader<C>, Range<C>> navigablemap1) {
      this.field1 = navigablemap1;
      this.field2 = Range.method16();
   }

   private TreeRangeSet$RangesByUpperBound(NavigableMap<SerializableLoader<C>, Range<C>> navigablemap1, Range<SerializableLoader<C>> serializablebase22) {
      this.field1 = navigablemap1;
      this.field2 = serializablebase22;
   }

   private NavigableMap<SerializableLoader<C>, Range<C>> method1(Range<SerializableLoader<C>> serializablebase21) {
      return (NavigableMap<SerializableLoader<C>, Range<C>>)(serializablebase21.method22(this.field2)
         ? new TreeRangeSet$RangesByUpperBound<C>(this.field1, serializablebase21.method23(this.field2))
         : ImmutableSortedMap.method3());
   }

   public NavigableMap<SerializableLoader<C>, Range<C>> method2(SerializableLoader<C> serializableloader1, boolean flag2, SerializableLoader<C> serializableloader3, boolean flag4) {
      return this.method1(Range.method9((C)serializableloader1, BoundType.forBoolean(flag2), (C)serializableloader3, BoundType.forBoolean(flag4)));
   }

   public NavigableMap<SerializableLoader<C>, Range<C>> method3(SerializableLoader<C> serializableloader1, boolean flag2) {
      return this.method1(Range.method12((C)serializableloader1, BoundType.forBoolean(flag2)));
   }

   public NavigableMap<SerializableLoader<C>, Range<C>> method4(SerializableLoader<C> serializableloader1, boolean flag2) {
      return this.method1(Range.method15((C)serializableloader1, BoundType.forBoolean(flag2)));
   }

   public Comparator<? super SerializableLoader<C>> comparator() {
      return com.google.common.collect.Ordering.method1();
   }

   public boolean containsKey(@Nullable Object obj1) {
      return this.method5(obj1) != null;
   }

   public Range<C> method5(@Nullable Object obj1) {
      if (obj1 instanceof SerializableLoader) {
         try {
            SerializableLoader serializableloader2 = (SerializableLoader)obj1;
            if (!this.field2.contains((C)serializableloader2)) {
               return null;
            }

            Entry entry3 = this.field1.lowerEntry(serializableloader2);
            if (entry3 != null && ((Range)entry3.getValue()).field3.equals(serializableloader2)) {
               return (Range<C>)entry3.getValue();
            }
         } catch (ClassCastException classcastexception4) {
            return null;
         }
      }

      return null;
   }

   Iterator<Entry<SerializableLoader<C>, Range<C>>> entryIterator() {
      Iterator iterator1;
      if (!this.field2.hasLowerBound()) {
         iterator1 = this.field1.values().iterator();
      } else {
         Entry entry2 = this.field1.lowerEntry(this.field2.lowerEndpoint());
         if (entry2 == null) {
            iterator1 = this.field1.values().iterator();
         } else if (this.field2.field2.isLessThan(((Range)entry2.getValue()).field3)) {
            iterator1 = this.field1.tailMap((SerializableLoader<C>)entry2.getKey(), true).values().iterator();
         } else {
            iterator1 = this.field1.tailMap(this.field2.lowerEndpoint(), true).values().iterator();
         }
      }

      return new Data4$1(this, iterator1);
   }

   Iterator<Entry<SerializableLoader<C>, Range<C>>> descendingEntryIterator() {
      Collection list1;
      if (this.field2.hasUpperBound()) {
         list1 = this.field1.headMap(this.field2.upperEndpoint(), false).descendingMap().values();
      } else {
         list1 = this.field1.descendingMap().values();
      }

      PeekingIterator mixinhelperiterator_22 = Iterators.method22(list1.iterator());
      if (mixinhelperiterator_22.hasNext() && this.field2.field3.isLessThan(((Range)mixinhelperiterator_22.peek()).field3)) {
         mixinhelperiterator_22.next();
      }

      return new Data4$2(this, mixinhelperiterator_22);
   }

   public int size() {
      return this.field2.equals(Range.method16()) ? this.field1.size() : Iterators.size(this.entryIterator());
   }

   public boolean isEmpty() {
      return this.field2.equals(Range.method16()) ? this.field1.isEmpty() : !this.entryIterator().hasNext();
   }
}
