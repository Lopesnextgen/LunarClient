package com.moonsworth.lunar.genesis;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Range;
import com.google.common.collect.Lists;
import com.google.common.base.Predicates;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.RangeMap;

@Annotation2
@Annotation3
public final class ComparableIterator2<K extends Comparable, V> implements RangeMap<K, V> {
   private final NavigableMap<SerializableLoader<K>, ComparableIterator2.Data<K, V>> field1 = Maps.newTreeMap();
   private static final RangeMap field2 = new RangeMap() {
      @Override
      public @Nullable Object get(Comparable var1) {
         return null;
      }

      @Override
      public @Nullable Entry<Range, Object> getEntry(Comparable var1) {
         return null;
      }

      @Override
      public Range method1() {
         throw new NoSuchElementException();
      }

      @Override
      public void method2(Range var1, Object var2) {
         Preconditions.checkNotNull(var1);
         throw new IllegalArgumentException("Cannot insert range " + var1 + " into an empty subRangeMap");
      }

      @Override
      public void method3(Range var1, Object var2) {
         Preconditions.checkNotNull(var1);
         throw new IllegalArgumentException("Cannot insert range " + var1 + " into an empty subRangeMap");
      }

      @Override
      public void method4(RangeMap var1) {
         if (!var1.asMapOfRanges().isEmpty()) {
            throw new IllegalArgumentException("Cannot putAll(nonEmptyRangeMap) into an empty subRangeMap");
         }
      }

      @Override
      public void clear() {
      }

      @Override
      public void method5(Range var1) {
         Preconditions.checkNotNull(var1);
      }

      @Override
      public void method6(Range var1, @Nullable Object var2, BiFunction var3) {
         Preconditions.checkNotNull(var1);
         throw new IllegalArgumentException("Cannot merge range " + var1 + " into an empty subRangeMap");
      }

      @Override
      public Map<Range, Object> asMapOfRanges() {
         return Collections.emptyMap();
      }

      @Override
      public Map<Range, Object> asDescendingMapOfRanges() {
         return Collections.emptyMap();
      }

      @Override
      public RangeMap method7(Range var1) {
         Preconditions.checkNotNull(var1);
         return this;
      }
   };

   public static <K extends Comparable, V> ComparableIterator2<K, V> method2() {
      return new ComparableIterator2<>();
   }

   private ComparableIterator2() {
   }

   @Override
   public @Nullable V get(K var1) {
      Entry var2 = this.getEntry((K)var1);
      return (V)(var2 == null ? null : var2.getValue());
   }

   @Override
   public @Nullable Entry<Range<K>, V> getEntry(K var1) {
      Entry var2 = this.field1.floorEntry(SerializableLoader.method11((K)var1));
      return var2 != null && ((ComparableIterator2.Data)var2.getValue()).contains(var1) ? (Entry)var2.getValue() : null;
   }

   @Override
   public void method2(Range<K> var1, V var2) {
      if (!var1.isEmpty()) {
         Preconditions.checkNotNull(var2);
         this.method5(var1);
         this.field1.put(var1.field2, new ComparableIterator2.Data<>(var1, (V)var2));
      }
   }

   @Override
   public void method3(Range<K> var1, V var2) {
      if (this.field1.isEmpty()) {
         this.method2(var1, (V)var2);
      } else {
         Range var3 = this.method4(var1, Preconditions.checkNotNull((V)var2));
         this.method2(var3, (V)var2);
      }
   }

   private Range<K> method4(Range<K> var1, V var2) {
      Range var3 = var1;
      Entry var4 = this.field1.lowerEntry(var1.field2);
      var3 = method5(var3, var2, var4);
      Entry var5 = this.field1.floorEntry(var1.field3);
      return method5(var3, var2, var5);
   }

   private static <K extends Comparable, V> Range<K> method5(
      Range<K> var0, V var1, @Nullable Entry<SerializableLoader<K>, ComparableIterator2.Data<K, V>> var2
   ) {
      return var2 != null
            && ((ComparableIterator2.Data)var2.getValue()).method1().method22(var0)
            && ((ComparableIterator2.Data)var2.getValue()).getValue().equals(var1)
         ? var0.method25(((ComparableIterator2.Data)var2.getValue()).method1())
         : var0;
   }

   @Override
   public void method4(RangeMap<K, V> var1) {
      for (Entry var3 : var1.asMapOfRanges().entrySet()) {
         this.method2((Range<K>)var3.getKey(), (V)var3.getValue());
      }
   }

   @Override
   public void clear() {
      this.field1.clear();
   }

   @Override
   public Range<K> method1() {
      Entry var1 = this.field1.firstEntry();
      Entry var2 = this.field1.lastEntry();
      if (var1 == null) {
         throw new NoSuchElementException();
      } else {
         return Range.method4(
            ((ComparableIterator2.Data)var1.getValue()).method1().field2, ((ComparableIterator2.Data)var2.getValue()).method1().field3
         );
      }
   }

   private void method8(SerializableLoader<K> var1, SerializableLoader<K> var2, V var3) {
      this.field1.put(var1, new ComparableIterator2.Data<>(var1, var2, (V)var3));
   }

   @Override
   public void method5(Range<K> var1) {
      if (!var1.isEmpty()) {
         Entry var2 = this.field1.lowerEntry(var1.field2);
         if (var2 != null) {
            ComparableIterator2.Data var3 = (ComparableIterator2.Data)var2.getValue();
            if (var3.method3().method8(var1.field2) > 0) {
               if (var3.method3().method8(var1.field3) > 0) {
                  this.method8(var1.field3, var3.method3(), (V)((ComparableIterator2.Data)var2.getValue()).getValue());
               }

               this.method8(var3.method2(), var1.field2, (V)((ComparableIterator2.Data)var2.getValue()).getValue());
            }
         }

         Entry var5 = this.field1.lowerEntry(var1.field3);
         if (var5 != null) {
            ComparableIterator2.Data var4 = (ComparableIterator2.Data)var5.getValue();
            if (var4.method3().method8(var1.field3) > 0) {
               this.method8(var1.field3, var4.method3(), (V)((ComparableIterator2.Data)var5.getValue()).getValue());
            }
         }

         this.field1.subMap(var1.field2, var1.field3).clear();
      }
   }

   private void method10(SerializableLoader<K> var1) {
      Entry var2 = this.field1.lowerEntry(var1);
      if (var2 != null) {
         ComparableIterator2.Data var3 = (ComparableIterator2.Data)var2.getValue();
         if (var3.method3().method8(var1) > 0) {
            this.method8(var3.method2(), var1, (V)var3.getValue());
            this.method8(var1, var3.method3(), (V)var3.getValue());
         }
      }
   }

   @Override
   public void method6(Range<K> var1, @Nullable V var2, BiFunction<? super V, ? super V, ? extends V> var3) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var3);
      if (!var1.isEmpty()) {
         this.method10(var1.field2);
         this.method10(var1.field3);
         Set var4 = this.field1.subMap(var1.field2, var1.field3).entrySet();
         ImmutableMap.Data2 var5 = ImmutableMap.method7();
         if (var2 != null) {
            Iterator var6 = var4.iterator();
            SerializableLoader var7 = var1.field2;

            while (var6.hasNext()) {
               ComparableIterator2.Data var8 = (ComparableIterator2.Data)((Entry)var6.next()).getValue();
               SerializableLoader var9 = var8.method2();
               if (!var7.equals(var9)) {
                  var5.method1(var7, new ComparableIterator2.Data<>(var7, var9, var2));
               }

               var7 = var8.method3();
            }

            if (!var7.equals(var1.field3)) {
               var5.method1(var7, new ComparableIterator2.Data<>(var7, var1.field3, var2));
            }
         }

         Iterator var10 = var4.iterator();

         while (var10.hasNext()) {
            Entry var11 = (Entry)var10.next();
            Object var12 = var3.apply(((ComparableIterator2.Data)var11.getValue()).getValue(), var2);
            if (var12 == null) {
               var10.remove();
            } else {
               var11.setValue(
                  new ComparableIterator2.Data<>(
                     ((ComparableIterator2.Data)var11.getValue()).method2(), ((ComparableIterator2.Data)var11.getValue()).method3(), var12
                  )
               );
            }
         }

         this.field1.putAll(var5.method7());
      }
   }

   @Override
   public Map<Range<K>, V> asMapOfRanges() {
      return new ComparableIterator2.Data2(this.field1.values());
   }

   @Override
   public Map<Range<K>, V> asDescendingMapOfRanges() {
      return new ComparableIterator2.Data2(this.field1.descendingMap().values());
   }

   @Override
   public RangeMap<K, V> method7(Range<K> var1) {
      return var1.equals(Range.method16()) ? this : new ComparableIterator2.Data3(var1);
   }

   private RangeMap<K, V> method13() {
      return field2;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof RangeMap) {
         RangeMap var2 = (RangeMap)var1;
         return this.asMapOfRanges().equals(var2.asMapOfRanges());
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.asMapOfRanges().hashCode();
   }

   @Override
   public String toString() {
      return this.field1.values().toString();
   }

   private static final class Data<K extends Comparable, V> extends MixinHelper32<Range<K>, V> {
      private final Range<K> field1;
      private final V field2;

      Data(SerializableLoader<K> var1, SerializableLoader<K> var2, V var3) {
         this(Range.method4(var1, var2), (V)var3);
      }

      Data(Range<K> var1, V var2) {
         this.field1 = var1;
         this.field2 = (V)var2;
      }

      public Range<K> method1() {
         return this.field1;
      }

      @Override
      public V getValue() {
         return this.field2;
      }

      public boolean contains(K var1) {
         return this.field1.contains((K)var1);
      }

      SerializableLoader<K> method2() {
         return this.field1.field2;
      }

      SerializableLoader<K> method3() {
         return this.field1.field3;
      }
   }

   private final class Data2 extends MixinHelper19$Data24<Range<K>, V> {
      final Iterable<Entry<Range<K>, V>> field1;

      Data2(Iterable<ComparableIterator2.Data<K, V>> var2) {
         this.field1 = var2;
      }

      @Override
      public boolean containsKey(@Nullable Object var1) {
         return this.get(var1) != null;
      }

      @Override
      public V get(@Nullable Object var1) {
         if (var1 instanceof Range) {
            Range var2 = (Range)var1;
            ComparableIterator2.Data var3 = ComparableIterator2.this.field1.get(var2.field2);
            if (var3 != null && var3.method1().equals(var2)) {
               return (V)var3.getValue();
            }
         }

         return null;
      }

      @Override
      public int size() {
         return ComparableIterator2.this.field1.size();
      }

      @Override
      Iterator<Entry<Range<K>, V>> entryIterator() {
         return this.field1.iterator();
      }
   }

   private class Data3 implements RangeMap<K, V> {
      private final Range<K> field1;

      Data3(Range<K> var2) {
         this.field1 = var2;
      }

      @Override
      public @Nullable V get(K var1) {
         return this.field1.contains((K)var1) ? ComparableIterator2.this.get((K)var1) : null;
      }

      @Override
      public @Nullable Entry<Range<K>, V> getEntry(K var1) {
         if (this.field1.contains((K)var1)) {
            Entry var2 = ComparableIterator2.this.getEntry((K)var1);
            if (var2 != null) {
               return Maps.immutableEntry(((Range)var2.getKey()).method23(this.field1), (V)var2.getValue());
            }
         }

         return null;
      }

      @Override
      public Range<K> method1() {
         Entry var2 = ComparableIterator2.this.field1.floorEntry(this.field1.field2);
         SerializableLoader var1;
         if (var2 != null && ((ComparableIterator2.Data)var2.getValue()).method3().method8(this.field1.field2) > 0) {
            var1 = this.field1.field2;
         } else {
            var1 = ComparableIterator2.this.field1.ceilingKey(this.field1.field2);
            if (var1 == null || var1.method8(this.field1.field3) >= 0) {
               throw new NoSuchElementException();
            }
         }

         Entry var4 = ComparableIterator2.this.field1.lowerEntry(this.field1.field3);
         if (var4 == null) {
            throw new NoSuchElementException();
         }

         SerializableLoader var3;
         if (((ComparableIterator2.Data)var4.getValue()).method3().method8(this.field1.field3) >= 0) {
            var3 = this.field1.field3;
         } else {
            var3 = ((ComparableIterator2.Data)var4.getValue()).method3();
         }

         return Range.method4(var1, var3);
      }

      @Override
      public void method2(Range<K> var1, V var2) {
         Preconditions.checkArgument(this.field1.method21(var1), "Cannot put range %s into a subRangeMap(%s)", var1, this.field1);
         ComparableIterator2.this.method2(var1, (V)var2);
      }

      @Override
      public void method3(Range<K> var1, V var2) {
         if (!ComparableIterator2.this.field1.isEmpty() && !var1.isEmpty() && this.field1.method21(var1)) {
            Range var3 = ComparableIterator2.this.method4(var1, Preconditions.checkNotNull((V)var2));
            this.method2(var3.method23(this.field1), (V)var2);
         } else {
            this.method2(var1, (V)var2);
         }
      }

      @Override
      public void method4(RangeMap<K, V> var1) {
         if (!var1.asMapOfRanges().isEmpty()) {
            Range var2 = var1.method1();
            Preconditions.checkArgument(this.field1.method21(var2), "Cannot putAll rangeMap with span %s into a subRangeMap(%s)", var2, this.field1);
            ComparableIterator2.this.method4(var1);
         }
      }

      @Override
      public void clear() {
         ComparableIterator2.this.method5(this.field1);
      }

      @Override
      public void method5(Range<K> var1) {
         if (var1.method22(this.field1)) {
            ComparableIterator2.this.method5(var1.method23(this.field1));
         }
      }

      @Override
      public void method6(Range<K> var1, @Nullable V var2, BiFunction<? super V, ? super V, ? extends V> var3) {
         Preconditions.checkArgument(this.field1.method21(var1), "Cannot merge range %s into a subRangeMap(%s)", var1, this.field1);
         ComparableIterator2.this.method6(var1, (V)var2, var3);
      }

      @Override
      public RangeMap<K, V> method7(Range<K> var1) {
         return !var1.method22(this.field1) ? ComparableIterator2.this.method13() : ComparableIterator2.this.method7(var1.method23(this.field1));
      }

      @Override
      public Map<Range<K>, V> asMapOfRanges() {
         return new ComparableIterator2.Data3.Data();
      }

      @Override
      public Map<Range<K>, V> asDescendingMapOfRanges() {
         return new ComparableIterator2<K, V>.Data3.Data() {
            @Override
            Iterator<Entry<Range<K>, V>> entryIterator() {
               if (Data3.this.field1.isEmpty()) {
                  return Iterators.method1();
               }

               final Iterator var1 = ComparableIterator2.this.field1.headMap(Data3.this.field1.field3, false).descendingMap().values().iterator();
               return new MixinHelperIterator32_2<Entry<Range<K>, V>>() {
                  protected Entry<Range<K>, V> computeNext() {
                     if (var1.hasNext()) {
                        ComparableIterator2.Data var1x = (ComparableIterator2.Data)var1.next();
                        return var1x.method3().method8(Data3.this.field1.field2) <= 0
                           ? (Entry)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH()
                           : Maps.immutableEntry(var1x.method1().method23(Data3.this.field1), (V)var1x.getValue());
                     } else {
                        return (Entry<Range<K>, V>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
                     }
                  }
               };
            }
         };
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         if (var1 instanceof RangeMap) {
            RangeMap var2 = (RangeMap)var1;
            return this.asMapOfRanges().equals(var2.asMapOfRanges());
         } else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         return this.asMapOfRanges().hashCode();
      }

      @Override
      public String toString() {
         return this.asMapOfRanges().toString();
      }

      class Data extends AbstractMap<Range<K>, V> {
         @Override
         public boolean containsKey(Object var1) {
            return this.get(var1) != null;
         }

         @Override
         public V get(Object var1) {
            try {
               if (var1 instanceof Range) {
                  Range var2 = (Range)var1;
                  if (!Data3.this.field1.method21(var2) || var2.isEmpty()) {
                     return null;
                  }

                  ComparableIterator2.Data var3 = null;
                  if (var2.field2.method8(Data3.this.field1.field2) == 0) {
                     Entry var4 = ComparableIterator2.this.field1.floorEntry(var2.field2);
                     if (var4 != null) {
                        var3 = (ComparableIterator2.Data)var4.getValue();
                     }
                  } else {
                     var3 = ComparableIterator2.this.field1.get(var2.field2);
                  }

                  if (var3 != null && var3.method1().method22(Data3.this.field1) && var3.method1().method23(Data3.this.field1).equals(var2)) {
                     return (V)var3.getValue();
                  }
               }

               return null;
            } catch (ClassCastException var5) {
               return null;
            }
         }

         @Override
         public V remove(Object var1) {
            Object var2 = this.get(var1);
            if (var2 != null) {
               Range var3 = (Range)var1;
               ComparableIterator2.this.method5(var3);
               return (V)var2;
            } else {
               return null;
            }
         }

         @Override
         public void clear() {
            Data3.this.clear();
         }

         private boolean method1(PredicateExtension<? super Entry<Range<K>, V>> var1) {
            ArrayList var2 = Lists.newArrayList();

            for (Entry var4 : this.entrySet()) {
               if (var1.apply(var4)) {
                  var2.add(var4.getKey());
               }
            }

            for (Range var6 : var2) {
               ComparableIterator2.this.method5(var6);
            }

            return !var2.isEmpty();
         }

         @Override
         public Set<Range<K>> keySet() {
            return new MixinHelper19$Data25<Range<K>, V>(this) {
               @Override
               public boolean remove(@Nullable Object var1) {
                  return Data.this.remove(var1) != null;
               }

               @Override
               public boolean retainAll(Collection<?> var1) {
                  return Data.this.method1(Predicates.method16(Predicates.method5(Predicates.method15(var1)), Maps.method1()));
               }
            };
         }

         @Override
         public Set<Entry<Range<K>, V>> entrySet() {
            return new MixinHelper19$Data32<Range<K>, V>() {
               @Override
               Map<Range<K>, V> map() {
                  return Data.this;
               }

               @Override
               public Iterator<Entry<Range<K>, V>> iterator() {
                  return Data.this.entryIterator();
               }

               @Override
               public boolean retainAll(Collection<?> var1) {
                  return Data.this.method1(Predicates.method5(Predicates.method15(var1)));
               }

               @Override
               public int size() {
                  return Iterators.size(this.iterator());
               }

               @Override
               public boolean isEmpty() {
                  return !this.iterator().hasNext();
               }
            };
         }

         Iterator<Entry<Range<K>, V>> entryIterator() {
            if (Data3.this.field1.isEmpty()) {
               return Iterators.method1();
            }

            SerializableLoader var1 = MoreObjects.firstNonNull(ComparableIterator2.this.field1.floorKey(Data3.this.field1.field2), Data3.this.field1.field2);
            final Iterator var2 = ComparableIterator2.this.field1.tailMap(var1, true).values().iterator();
            return new MixinHelperIterator32_2<Entry<Range<K>, V>>() {
               protected Entry<Range<K>, V> computeNext() {
                  while (var2.hasNext()) {
                     ComparableIterator2.Data var1x = (ComparableIterator2.Data)var2.next();
                     if (var1x.method2().method8(Data3.this.field1.field3) >= 0) {
                        return (Entry<Range<K>, V>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
                     }

                     if (var1x.method3().method8(Data3.this.field1.field2) > 0) {
                        return Maps.immutableEntry(var1x.method1().method23(Data3.this.field1), (V)var1x.getValue());
                     }
                  }

                  return (Entry<Range<K>, V>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
               }
            };
         }

         @Override
         public Collection<V> values() {
            return new MixinHelper19$Data35<Range<K>, V>(this) {
               @Override
               public boolean removeAll(Collection<?> var1) {
                  return Data.this.method1(Predicates.method16(Predicates.method15(var1), Maps.method2()));
               }

               @Override
               public boolean retainAll(Collection<?> var1) {
                  return Data.this.method1(Predicates.method16(Predicates.method5(Predicates.method15(var1)), Maps.method2()));
               }
            };
         }
      }
   }
}
