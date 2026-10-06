package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.Multiset;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimap;
import com.google.common.collect.Iterators;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Maps;

@GwtCompatible(emulated = true)
public final class MixinHelper37 {
   private MixinHelper37() {
   }

   @Annotation2
   public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> toMultimap(
      Function<? super T, ? extends K> var0, Function<? super T, ? extends V> var1, Supplier<M> var2
   ) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      return (Collector<T, ?, M>)Collector.of(var2, (var2x, var3) -> var2x.put(var0.apply(var3), var1.apply(var3)), (var0x, var1x) -> {
         var0x.method1(var1x);
         return var0x;
      });
   }

   @Annotation2
   public static <T, K, V, M extends Multimap<K, V>> Collector<T, ?, M> flatteningToMultimap(
      Function<? super T, ? extends K> var0, Function<? super T, ? extends Stream<? extends V>> var1, Supplier<M> var2
   ) {
      Preconditions.checkNotNull(var0);
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      return (Collector<T, ?, M>)Collector.of(var2, (var2x, var3) -> {
         Object var4 = var0.apply(var3);
         Collection var5 = var2x.get(var4);
         ((Stream)var1.apply(var3)).forEachOrdered(var5::add);
      }, (var0x, var1x) -> {
         var0x.method1(var1x);
         return var0x;
      });
   }

   public static <K, V> Multimap<K, V> method1(Map<K, Collection<V>> var0, SupplierExtension<? extends Collection<V>> var1) {
      return new MixinHelper37.Data5<>(var0, var1);
   }

   public static <K, V> MixinHelper133<K, V> method2(Map<K, Collection<V>> var0, SupplierExtension<? extends List<V>> var1) {
      return new MixinHelper37.Data2<>(var0, var1);
   }

   public static <K, V> MixinHelper132_2<K, V> method3(Map<K, Collection<V>> var0, SupplierExtension<? extends Set<V>> var1) {
      return new MixinHelper37.Data7<>(var0, var1);
   }

   public static <K, V> MixinHelper1322<K, V> method4(Map<K, Collection<V>> var0, SupplierExtension<? extends SortedSet<V>> var1) {
      return new MixinHelper37.Data13<>(var0, var1);
   }

   @CanIgnoreReturnValue
   public static <K, V, M extends Multimap<K, V>> M method5(Multimap<? extends V, ? extends K> var0, M var1) {
      Preconditions.checkNotNull(var1);

      for (Entry var3 : var0.entries()) {
         var1.put(var3.getValue(), var3.getKey());
      }

      return (M)var1;
   }

   public static <K, V> Multimap<K, V> method6(Multimap<K, V> var0) {
      return MixinHelper_8.method2(var0, null);
   }

   public static <K, V> Multimap<K, V> method7(Multimap<K, V> var0) {
      return !(var0 instanceof MixinHelper37.Data10) && !(var0 instanceof ImmutableMultimap) ? new MixinHelper37.Data10<>(var0) : var0;
   }

   @Deprecated
   public static <K, V> Multimap<K, V> method8(ImmutableMultimap<K, V> var0) {
      return Preconditions.checkNotNull(var0);
   }

   public static <K, V> MixinHelper132_2<K, V> method9(MixinHelper132_2<K, V> var0) {
      return MixinHelper_8.method4(var0, null);
   }

   public static <K, V> MixinHelper132_2<K, V> method10(MixinHelper132_2<K, V> var0) {
      return !(var0 instanceof MixinHelper37.Data3) && !(var0 instanceof ImmutableSetMultimap) ? new MixinHelper37.Data3<>(var0) : var0;
   }

   @Deprecated
   public static <K, V> MixinHelper132_2<K, V> method11(ImmutableSetMultimap<K, V> var0) {
      return Preconditions.checkNotNull(var0);
   }

   public static <K, V> MixinHelper1322<K, V> method12(MixinHelper1322<K, V> var0) {
      return MixinHelper_8.method5(var0, null);
   }

   public static <K, V> MixinHelper1322<K, V> method13(MixinHelper1322<K, V> var0) {
      return var0 instanceof MixinHelper37.Data14 ? var0 : new MixinHelper37.Data14<>(var0);
   }

   public static <K, V> MixinHelper133<K, V> method14(MixinHelper133<K, V> var0) {
      return MixinHelper_8.method3(var0, null);
   }

   public static <K, V> MixinHelper133<K, V> method15(MixinHelper133<K, V> var0) {
      return !(var0 instanceof MixinHelper37.Data8) && !(var0 instanceof MixinHelper134523) ? new MixinHelper37.Data8<>(var0) : var0;
   }

   @Deprecated
   public static <K, V> MixinHelper133<K, V> method16(MixinHelper134523<K, V> var0) {
      return Preconditions.checkNotNull(var0);
   }

   private static <V> Collection<V> unmodifiableValueCollection(Collection<V> var0) {
      if (var0 instanceof SortedSet) {
         return Collections.unmodifiableSortedSet((SortedSet<V>)var0);
      } else if (var0 instanceof Set) {
         return Collections.unmodifiableSet((Set<? extends V>)var0);
      } else {
         return var0 instanceof List ? Collections.unmodifiableList((List<? extends V>)var0) : Collections.unmodifiableCollection(var0);
      }
   }

   private static <K, V> Collection<Entry<K, V>> unmodifiableEntries(Collection<Entry<K, V>> var0) {
      return var0 instanceof Set
         ? Maps.unmodifiableEntrySet((Set<Entry<K, V>>)var0)
         : new MixinHelper19$Data38<>(Collections.unmodifiableCollection(var0));
   }

   @Annotation2
   public static <K, V> Map<K, List<V>> method17(MixinHelper133<K, V> var0) {
      return var0.asMap();
   }

   @Annotation2
   public static <K, V> Map<K, Set<V>> method18(MixinHelper132_2<K, V> var0) {
      return var0.asMap();
   }

   @Annotation2
   public static <K, V> Map<K, SortedSet<V>> method19(MixinHelper1322<K, V> var0) {
      return var0.asMap();
   }

   @Annotation2
   public static <K, V> Map<K, Collection<V>> method20(Multimap<K, V> var0) {
      return var0.asMap();
   }

   public static <K, V> MixinHelper132_2<K, V> method21(Map<K, V> var0) {
      return new MixinHelper37.Data12<>(var0);
   }

   public static <K, V1, V2> Multimap<K, V2> method22(Multimap<K, V1> var0, MixinHelper24_2<? super V1, V2> var1) {
      Preconditions.checkNotNull(var1);
      MixinHelper19$Extension var2 = Maps.method27(var1);
      return method24(var0, var2);
   }

   public static <K, V1, V2> MixinHelper133<K, V2> method23(MixinHelper133<K, V1> var0, MixinHelper24_2<? super V1, V2> var1) {
      Preconditions.checkNotNull(var1);
      MixinHelper19$Extension var2 = Maps.method27(var1);
      return method25(var0, var2);
   }

   public static <K, V1, V2> Multimap<K, V2> method24(Multimap<K, V1> var0, MixinHelper19$Extension<? super K, ? super V1, V2> var1) {
      return new MixinHelper37.Data<>(var0, var1);
   }

   public static <K, V1, V2> MixinHelper133<K, V2> method25(MixinHelper133<K, V1> var0, MixinHelper19$Extension<? super K, ? super V1, V2> var1) {
      return new MixinHelper37.Data6<>(var0, var1);
   }

   public static <K, V> MixinHelper134523<K, V> method26(Iterable<V> var0, MixinHelper24_2<? super V, K> var1) {
      return method27(var0.iterator(), var1);
   }

   public static <K, V> MixinHelper134523<K, V> method27(Iterator<V> var0, MixinHelper24_2<? super V, K> var1) {
      Preconditions.checkNotNull(var1);
      MixinHelper134523.Data var2 = MixinHelper134523.method8();

      while (var0.hasNext()) {
         Object var3 = var0.next();
         Preconditions.checkNotNull(var3, var0);
         var2.method2(var1.apply(var3), var3);
      }

      return var2.method11();
   }

   public static <K, V> Multimap<K, V> method28(Multimap<K, V> var0, PredicateExtension<? super K> var1) {
      if (var0 instanceof MixinHelper132_2) {
         return method29((MixinHelper132_2<K, V>)var0, var1);
      } else if (var0 instanceof MixinHelper133) {
         return method30((MixinHelper133<K, V>)var0, var1);
      } else if (var0 instanceof MixinHelper1346) {
         MixinHelper1346 var3 = (MixinHelper1346)var0;
         return new MixinHelper1346<>(var3.field2, Predicates.method8(var3.field3, var1));
      } else if (var0 instanceof MixinHelper135) {
         MixinHelper135 var2 = (MixinHelper135)var0;
         return method35(var2, Maps.method32(var1));
      } else {
         return new MixinHelper1346<>(var0, var1);
      }
   }

   public static <K, V> MixinHelper132_2<K, V> method29(MixinHelper132_2<K, V> var0, PredicateExtension<? super K> var1) {
      if (var0 instanceof MixinHelper13463) {
         MixinHelper13463 var3 = (MixinHelper13463)var0;
         return new MixinHelper13463<>(var3.method5(), Predicates.method8(var3.field3, var1));
      } else if (var0 instanceof MixinHelper1323) {
         MixinHelper1323 var2 = (MixinHelper1323)var0;
         return method36(var2, Maps.method32(var1));
      } else {
         return new MixinHelper13463<>(var0, var1);
      }
   }

   public static <K, V> MixinHelper133<K, V> method30(MixinHelper133<K, V> var0, PredicateExtension<? super K> var1) {
      if (var0 instanceof MixinHelper13462) {
         MixinHelper13462 var2 = (MixinHelper13462)var0;
         return new MixinHelper13462<>(var2.method5(), Predicates.method8(var2.field3, var1));
      } else {
         return new MixinHelper13462<>(var0, var1);
      }
   }

   public static <K, V> Multimap<K, V> method31(Multimap<K, V> var0, PredicateExtension<? super V> var1) {
      return method33(var0, Maps.method33(var1));
   }

   public static <K, V> MixinHelper132_2<K, V> method32(MixinHelper132_2<K, V> var0, PredicateExtension<? super V> var1) {
      return method34(var0, Maps.method33(var1));
   }

   public static <K, V> Multimap<K, V> method33(Multimap<K, V> var0, PredicateExtension<? super Entry<K, V>> var1) {
      Preconditions.checkNotNull(var1);
      if (var0 instanceof MixinHelper132_2) {
         return method34((MixinHelper132_2<K, V>)var0, var1);
      } else {
         return var0 instanceof MixinHelper135 ? method35((MixinHelper135<K, V>)var0, var1) : new MixinHelper1344<>(Preconditions.checkNotNull(var0), var1);
      }
   }

   public static <K, V> MixinHelper132_2<K, V> method34(MixinHelper132_2<K, V> var0, PredicateExtension<? super Entry<K, V>> var1) {
      Preconditions.checkNotNull(var1);
      return var0 instanceof MixinHelper1323 ? method36((MixinHelper1323<K, V>)var0, var1) : new MixinHelper13442<>(Preconditions.checkNotNull(var0), var1);
   }

   private static <K, V> Multimap<K, V> method35(MixinHelper135<K, V> var0, PredicateExtension<? super Entry<K, V>> var1) {
      PredicateExtension var2 = Predicates.method8(var0.method4(), var1);
      return new MixinHelper1344<>(var0.method1(), var2);
   }

   private static <K, V> MixinHelper132_2<K, V> method36(MixinHelper1323<K, V> var0, PredicateExtension<? super Entry<K, V>> var1) {
      PredicateExtension var2 = Predicates.method8(var0.method4(), var1);
      return new MixinHelper13442<>(var0.method5(), var2);
   }

   static boolean method37(Multimap<?, ?> var0, @Nullable Object var1) {
      if (var1 == var0) {
         return true;
      } else if (var1 instanceof Multimap) {
         Multimap var2 = (Multimap)var1;
         return var0.asMap().equals(var2.asMap());
      } else {
         return false;
      }
   }

   private static class Data<K, V1, V2> extends MixinHelper134<K, V2> {
      final Multimap<K, V1> field2;
      final MixinHelper19$Extension<? super K, ? super V1, V2> field3;

      Data(Multimap<K, V1> var1, MixinHelper19$Extension<? super K, ? super V1, V2> var2) {
         this.field2 = Preconditions.checkNotNull(var1);
         this.field3 = Preconditions.checkNotNull(var2);
      }

      Collection<V2> transform(K var1, Collection<V1> var2) {
         MixinHelper24_2 var3 = Maps.method28(this.field3, (K)var1);
         return var2 instanceof List ? Lists.method1((List)var2, var3) : MixinHelper39.method2(var2, var3);
      }

      @Override
      Map<K, Collection<V2>> createAsMap() {
         return Maps.method24(this.field2.asMap(), new MixinHelper19$Extension<K, Collection<V1>, Collection<V2>>() {
            public Collection<V2> transformEntry(K var1, Collection<V1> var2) {
               return Data.this.transform(var1, var2);
            }
         });
      }

      @Override
      public void clear() {
         this.field2.clear();
      }

      @Override
      public boolean containsKey(Object var1) {
         return this.field2.containsKey(var1);
      }

      @Override
      Collection<Entry<K, V2>> createEntries() {
         return new MixinHelper134.Data3();
      }

      @Override
      Iterator<Entry<K, V2>> entryIterator() {
         return Iterators.method17(this.field2.entries().iterator(), Maps.method31(this.field3));
      }

      @Override
      public Collection<V2> get(K var1) {
         return this.transform((K)var1, this.field2.get((K)var1));
      }

      @Override
      public boolean isEmpty() {
         return this.field2.isEmpty();
      }

      @Override
      Set<K> createKeySet() {
         return this.field2.keySet();
      }

      @Override
      Multiset<K> method3() {
         return this.field2.method2();
      }

      @Override
      public boolean put(K var1, V2 var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean putAll(K var1, Iterable<? extends V2> var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean method1(Multimap<? extends K, ? extends V2> var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean remove(Object var1, Object var2) {
         return this.get((K)var1).remove(var2);
      }

      @Override
      public Collection<V2> removeAll(Object var1) {
         return this.transform((K)var1, this.field2.removeAll(var1));
      }

      @Override
      public Collection<V2> replaceValues(K var1, Iterable<? extends V2> var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public int size() {
         return this.field2.size();
      }

      @Override
      Collection<V2> createValues() {
         return MixinHelper39.method2(this.field2.entries(), Maps.method29(this.field3));
      }
   }

   private static class Data10<K, V> extends MixinHelper317<K, V> implements Serializable {
      final Multimap<K, V> field1;
      transient @Nullable Collection<Entry<K, V>> entries;
      transient @Nullable Multiset<K> field2;
      transient @Nullable Set<K> keySet;
      transient @Nullable Collection<V> values;
      transient @Nullable Map<K, Collection<V>> map;
      private static final long field3 = 0L;

      Data10(Multimap<K, V> var1) {
         this.field1 = Preconditions.checkNotNull(var1);
      }

      @Override
      protected Multimap<K, V> method1() {
         return this.field1;
      }

      @Override
      public void clear() {
         throw new UnsupportedOperationException();
      }

      @Override
      public Map<K, Collection<V>> asMap() {
         Map var1 = this.map;
         if (var1 == null) {
            var1 = this.map = Collections.unmodifiableMap(Maps.method21(this.field1.asMap(), new MixinHelper24_2<Collection<V>, Collection<V>>() {
               public Collection<V> apply(Collection<V> var1) {
                  return MixinHelper37.unmodifiableValueCollection(var1);
               }
            }));
         }

         return var1;
      }

      @Override
      public Collection<Entry<K, V>> entries() {
         Collection var1 = this.entries;
         if (var1 == null) {
            this.entries = var1 = MixinHelper37.unmodifiableEntries(this.field1.entries());
         }

         return var1;
      }

      @Override
      public Collection<V> get(K var1) {
         return MixinHelper37.unmodifiableValueCollection(this.field1.get((K)var1));
      }

      @Override
      public Multiset<K> method2() {
         Multiset var1 = this.field2;
         if (var1 == null) {
            this.field2 = var1 = Multisets.method1(this.field1.method2());
         }

         return var1;
      }

      @Override
      public Set<K> keySet() {
         Set var1 = this.keySet;
         if (var1 == null) {
            this.keySet = var1 = Collections.unmodifiableSet(this.field1.keySet());
         }

         return var1;
      }

      @Override
      public boolean put(K var1, V var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean putAll(K var1, Iterable<? extends V> var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean method1(Multimap<? extends K, ? extends V> var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean remove(Object var1, Object var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public Collection<V> removeAll(Object var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public Collection<V> replaceValues(K var1, Iterable<? extends V> var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public Collection<V> values() {
         Collection var1 = this.values;
         if (var1 == null) {
            this.values = var1 = Collections.unmodifiableCollection(this.field1.values());
         }

         return var1;
      }
   }

   static class Data11<K, V> extends AbstractCollectionBase<K> {
      @Weak
      final Multimap<K, V> field1;

      Data11(Multimap<K, V> var1) {
         this.field1 = var1;
      }

      @Override
      Iterator<Multiset.Extension<K>> entryIterator() {
         return new MixinHelperIterator2<Entry<K, Collection<V>>, Multiset.Extension<K>>(this.field1.asMap().entrySet().iterator()) {
            Multiset.Extension<K> method1(final Entry<K, Collection<V>> var1) {
               return new MixinHelper33$Data3<K>() {
                  @Override
                  public K getElement() {
                     return (K)var1.getKey();
                  }

                  @Override
                  public int getCount() {
                     return ((Collection)var1.getValue()).size();
                  }
               };
            }
         };
      }

      @Override
      public Spliterator<K> spliterator() {
         return MixinHelper3_5.map(this.field1.entries().spliterator(), Entry::getKey);
      }

      @Override
      public void forEach(Consumer<? super K> var1) {
         Preconditions.checkNotNull(var1);
         this.field1.entries().forEach(var1x -> var1.accept(var1x.getKey()));
      }

      @Override
      int distinctElements() {
         return this.field1.asMap().size();
      }

      @Override
      public int size() {
         return this.field1.size();
      }

      @Override
      public boolean contains(@Nullable Object var1) {
         return this.field1.containsKey(var1);
      }

      @Override
      public Iterator<K> iterator() {
         return Maps.keyIterator(this.field1.entries().iterator());
      }

      @Override
      public int count(@Nullable Object var1) {
         Collection var2 = Maps.safeGet(this.field1.asMap(), var1);
         return var2 == null ? 0 : var2.size();
      }

      @Override
      public int remove(@Nullable Object var1, int var2) {
         MixinHelper18_3.checkNonnegative(var2, "occurrences");
         if (var2 == 0) {
            return this.count(var1);
         }

         Collection var3 = Maps.safeGet(this.field1.asMap(), var1);
         if (var3 == null) {
            return 0;
         }

         int var4 = var3.size();
         if (var2 >= var4) {
            var3.clear();
         } else {
            Iterator var5 = var3.iterator();

            for (int var6 = 0; var6 < var2; var6++) {
               var5.next();
               var5.remove();
            }
         }

         return var4;
      }

      @Override
      public void clear() {
         this.field1.clear();
      }

      @Override
      public Set<K> elementSet() {
         return this.field1.keySet();
      }

      @Override
      Iterator<K> elementIterator() {
         throw new AssertionError("should never be called");
      }
   }

   private static class Data12<K, V> extends MixinHelper134<K, V> implements MixinHelper132_2<K, V>, Serializable {
      final Map<K, V> field2;
      private static final long field3 = 7845222491160860175L;

      Data12(Map<K, V> var1) {
         this.field2 = Preconditions.checkNotNull(var1);
      }

      @Override
      public int size() {
         return this.field2.size();
      }

      @Override
      public boolean containsKey(Object var1) {
         return this.field2.containsKey(var1);
      }

      @Override
      public boolean containsValue(Object var1) {
         return this.field2.containsValue(var1);
      }

      @Override
      public boolean containsEntry(Object var1, Object var2) {
         return this.field2.entrySet().contains(Maps.immutableEntry(var1, var2));
      }

      @Override
      public Set<V> get(final K var1) {
         return new MixinHelper10$Data14<V>() {
            @Override
            public Iterator<V> iterator() {
               return new Iterator<V>() {
                  int i;

                  @Override
                  public boolean hasNext() {
                     return this.i == 0 && Data12.this.field2.containsKey(var1);
                  }

                  @Override
                  public V next() {
                     if (!this.hasNext()) {
                        throw new NoSuchElementException();
                     }

                     this.i++;
                     return Data12.this.field2.get(var1);
                  }

                  @Override
                  public void remove() {
                     MixinHelper18_3.checkRemove(this.i == 1);
                     this.i = -1;
                     Data12.this.field2.remove(var1);
                  }
               };
            }

            @Override
            public int size() {
               return Data12.this.field2.containsKey(var1) ? 1 : 0;
            }
         };
      }

      @Override
      public boolean put(K var1, V var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean putAll(K var1, Iterable<? extends V> var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean method1(Multimap<? extends K, ? extends V> var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public Set<V> replaceValues(K var1, Iterable<? extends V> var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean remove(Object var1, Object var2) {
         return this.field2.entrySet().remove(Maps.immutableEntry(var1, var2));
      }

      @Override
      public Set<V> removeAll(Object var1) {
         HashSet var2 = new HashSet(2);
         if (!this.field2.containsKey(var1)) {
            return var2;
         }

         var2.add(this.field2.remove(var1));
         return var2;
      }

      @Override
      public void clear() {
         this.field2.clear();
      }

      @Override
      Set<K> createKeySet() {
         return this.field2.keySet();
      }

      @Override
      Collection<V> createValues() {
         return this.field2.values();
      }

      @Override
      public Set<Entry<K, V>> entries() {
         return this.field2.entrySet();
      }

      @Override
      Collection<Entry<K, V>> createEntries() {
         throw new AssertionError("unreachable");
      }

      @Override
      Multiset<K> method3() {
         return new MixinHelper37.Data11<>(this);
      }

      @Override
      Iterator<Entry<K, V>> entryIterator() {
         return this.field2.entrySet().iterator();
      }

      @Override
      Map<K, Collection<V>> createAsMap() {
         return new MixinHelper37.Data4<>(this);
      }

      @Override
      public int hashCode() {
         return this.field2.hashCode();
      }
   }

   private static class Data13<K, V> extends MixinHelper134222<K, V> {
      transient SupplierExtension<? extends SortedSet<V>> field5;
      transient java.util.Comparator<? super V> valueComparator;
      @Annotation3
      private static final long field6 = 0L;

      Data13(Map<K, Collection<V>> var1, SupplierExtension<? extends SortedSet<V>> var2) {
         super(var1);
         this.field5 = Preconditions.checkNotNull(var2);
         this.valueComparator = ((SortedSet)var2.get()).comparator();
      }

      @Override
      Set<K> createKeySet() {
         return this.HICORCIICHIROCHIRCOCROOIOIRHCH();
      }

      @Override
      Map<K, Collection<V>> createAsMap() {
         return this.CRIOHHHIOHHCCIOHHCIIROHRIIHCII();
      }

      @Override
      protected SortedSet<V> createCollection() {
         return (SortedSet<V>)this.field5.get();
      }

      @Override
      public java.util.Comparator<? super V> valueComparator() {
         return this.valueComparator;
      }

      @Annotation3
      private void writeObject(ObjectOutputStream var1) {
         var1.defaultWriteObject();
         var1.writeObject(this.field5);
         var1.writeObject(this.backingMap());
      }

      @Annotation3
      private void readObject(ObjectInputStream var1) {
         var1.defaultReadObject();
         this.field5 = (SupplierExtension<? extends SortedSet<V>>)var1.readObject();
         this.valueComparator = this.field5.get().comparator();
         Map var2 = (Map)var1.readObject();
         this.HOCCRCCRCHROIICOOHOHRIICRHCOHR(var2);
      }
   }

   private static class Data14<K, V> extends MixinHelper37.Data3<K, V> implements MixinHelper1322<K, V> {
      private static final long field5 = 0L;

      Data14(MixinHelper1322<K, V> var1) {
         super(var1);
      }

      public MixinHelper1322<K, V> method4() {
         return (MixinHelper1322<K, V>)super.method3();
      }

      @Override
      public SortedSet<V> get(K var1) {
         return Collections.unmodifiableSortedSet(this.method4().get((K)var1));
      }

      @Override
      public SortedSet<V> removeAll(Object var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public SortedSet<V> replaceValues(K var1, Iterable<? extends V> var2) {
         throw new UnsupportedOperationException();
      }

      @Override
      public java.util.Comparator<? super V> valueComparator() {
         return this.method4().valueComparator();
      }
   }

   private static class Data2<K, V> extends MixinHelper13423<K, V> {
      transient SupplierExtension<? extends List<V>> field4;
      @Annotation3
      private static final long field5 = 0L;

      Data2(Map<K, Collection<V>> var1, SupplierExtension<? extends List<V>> var2) {
         super(var1);
         this.field4 = Preconditions.checkNotNull(var2);
      }

      @Override
      Set<K> createKeySet() {
         return this.HICORCIICHIROCHIRCOCROOIOIRHCH();
      }

      @Override
      Map<K, Collection<V>> createAsMap() {
         return this.CRIOHHHIOHHCCIOHHCIIROHRIIHCII();
      }

      @Override
      protected List<V> createCollection() {
         return (List<V>)this.field4.get();
      }

      @Annotation3
      private void writeObject(ObjectOutputStream var1) {
         var1.defaultWriteObject();
         var1.writeObject(this.field4);
         var1.writeObject(this.backingMap());
      }

      @Annotation3
      private void readObject(ObjectInputStream var1) {
         var1.defaultReadObject();
         this.field4 = (SupplierExtension<? extends List<V>>)var1.readObject();
         Map var2 = (Map)var1.readObject();
         this.HOCCRCCRCHROIICOOHOHRIICRHCOHR(var2);
      }
   }

   private static class Data3<K, V> extends MixinHelper37.Data10<K, V> implements MixinHelper132_2<K, V> {
      private static final long field4 = 0L;

      Data3(MixinHelper132_2<K, V> var1) {
         super(var1);
      }

      public MixinHelper132_2<K, V> method3() {
         return (MixinHelper132_2<K, V>)super.method1();
      }

      @Override
      public Set<V> get(K var1) {
         return Collections.unmodifiableSet(this.method3().get((K)var1));
      }

      @Override
      public Set<Entry<K, V>> entries() {
         return Maps.unmodifiableEntrySet(this.method3().entries());
      }

      @Override
      public Set<V> removeAll(Object var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public Set<V> replaceValues(K var1, Iterable<? extends V> var2) {
         throw new UnsupportedOperationException();
      }
   }

   static final class Data4<K, V> extends MixinHelper19$Data29<K, Collection<V>> {
      @Weak
      private final Multimap<K, V> field1;

      Data4(Multimap<K, V> var1) {
         this.field1 = Preconditions.checkNotNull(var1);
      }

      @Override
      public int size() {
         return this.field1.keySet().size();
      }

      @Override
      protected Set<Entry<K, Collection<V>>> createEntrySet() {
         return new MixinHelper37.Data4.Data();
      }

      void removeValuesForKey(Object var1) {
         this.field1.keySet().remove(var1);
      }

      public Collection<V> get(Object var1) {
         return this.containsKey(var1) ? this.field1.get((K)var1) : null;
      }

      public Collection<V> remove(Object var1) {
         return this.containsKey(var1) ? this.field1.removeAll(var1) : null;
      }

      @Override
      public Set<K> keySet() {
         return this.field1.keySet();
      }

      @Override
      public boolean isEmpty() {
         return this.field1.isEmpty();
      }

      @Override
      public boolean containsKey(Object var1) {
         return this.field1.containsKey(var1);
      }

      @Override
      public void clear() {
         this.field1.clear();
      }

      class Data extends MixinHelper19$Data32<K, Collection<V>> {
         @Override
         Map<K, Collection<V>> map() {
            return Data4.this;
         }

         @Override
         public Iterator<Entry<K, Collection<V>>> iterator() {
            return Maps.method11(Data4.this.field1.keySet(), new MixinHelper24_2<K, Collection<V>>() {
               public Collection<V> apply(K var1) {
                  return Data4.this.field1.get((K)var1);
               }
            });
         }

         @Override
         public boolean remove(Object var1) {
            if (!this.contains(var1)) {
               return false;
            }

            Entry var2 = (Entry)var1;
            Data4.this.removeValuesForKey(var2.getKey());
            return true;
         }
      }
   }

   private static class Data5<K, V> extends MixinHelper1342<K, V> {
      transient SupplierExtension<? extends Collection<V>> field3;
      @Annotation3
      private static final long field4 = 0L;

      Data5(Map<K, Collection<V>> var1, SupplierExtension<? extends Collection<V>> var2) {
         super(var1);
         this.field3 = Preconditions.checkNotNull(var2);
      }

      @Override
      Set<K> createKeySet() {
         return this.HICORCIICHIROCHIRCOCROOIOIRHCH();
      }

      @Override
      Map<K, Collection<V>> createAsMap() {
         return this.CRIOHHHIOHHCCIOHHCIIROHRIIHCII();
      }

      @Override
      protected Collection<V> createCollection() {
         return (Collection<V>)this.field3.get();
      }

      @Override
      <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> var1) {
         if (var1 instanceof NavigableSet) {
            return Sets.unmodifiableNavigableSet((NavigableSet<E>)var1);
         } else if (var1 instanceof SortedSet) {
            return Collections.unmodifiableSortedSet((SortedSet<E>)var1);
         } else if (var1 instanceof Set) {
            return Collections.unmodifiableSet((Set<? extends E>)var1);
         } else {
            return var1 instanceof List ? Collections.unmodifiableList((List<? extends E>)var1) : Collections.unmodifiableCollection(var1);
         }
      }

      @Override
      Collection<V> wrapCollection(K var1, Collection<V> var2) {
         if (var2 instanceof List) {
            return this.HORHROIOIOICIRHIOCOICHHHIHCIIO(var1, (List)var2, null);
         } else if (var2 instanceof NavigableSet) {
            return new MixinHelper1342.Data8(var1, (NavigableSet<V>)var2, null);
         } else if (var2 instanceof SortedSet) {
            return new MixinHelper1342.Data3(var1, (SortedSet<V>)var2, null);
         } else {
            return var2 instanceof Set ? new MixinHelper1342.Data10(var1, (Set<V>)var2) : new MixinHelper1342.Data6(var1, var2, null);
         }
      }

      @Annotation3
      private void writeObject(ObjectOutputStream var1) {
         var1.defaultWriteObject();
         var1.writeObject(this.field3);
         var1.writeObject(this.backingMap());
      }

      @Annotation3
      private void readObject(ObjectInputStream var1) {
         var1.defaultReadObject();
         this.field3 = (SupplierExtension<? extends Collection<V>>)var1.readObject();
         Map var2 = (Map)var1.readObject();
         this.HOCCRCCRCHROIICOOHOHRIICRHCOHR(var2);
      }
   }

   private static final class Data6<K, V1, V2> extends MixinHelper37.Data<K, V1, V2> implements MixinHelper133<K, V2> {
      Data6(MixinHelper133<K, V1> var1, MixinHelper19$Extension<? super K, ? super V1, V2> var2) {
         super(var1, var2);
      }

      List<V2> transform(K var1, Collection<V1> var2) {
         return Lists.method1((List<V1>)var2, Maps.method28(this.CCHIHHRHIHRHIORIOHIORIOICIRRRO, var1));
      }

      @Override
      public List<V2> get(K var1) {
         return this.transform((K)var1, this.RHCRCHOHCHIOICRRCOCOCHHORHRHOH.get(var1));
      }

      @Override
      public List<V2> removeAll(Object var1) {
         return this.transform((K)var1, this.RHCRCHOHCHIOICRRCOCOCHHORHRHOH.removeAll(var1));
      }

      @Override
      public List<V2> replaceValues(K var1, Iterable<? extends V2> var2) {
         throw new UnsupportedOperationException();
      }
   }

   private static class Data7<K, V> extends MixinHelper13422<K, V> {
      transient SupplierExtension<? extends Set<V>> field4;
      @Annotation3
      private static final long field5 = 0L;

      Data7(Map<K, Collection<V>> var1, SupplierExtension<? extends Set<V>> var2) {
         super(var1);
         this.field4 = Preconditions.checkNotNull(var2);
      }

      @Override
      Set<K> createKeySet() {
         return this.HICORCIICHIROCHIRCOCROOIOIRHCH();
      }

      @Override
      Map<K, Collection<V>> createAsMap() {
         return this.CRIOHHHIOHHCCIOHHCIIROHRIIHCII();
      }

      @Override
      protected Set<V> createCollection() {
         return (Set<V>)this.field4.get();
      }

      @Override
      <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> var1) {
         if (var1 instanceof NavigableSet) {
            return Sets.unmodifiableNavigableSet((NavigableSet<E>)var1);
         } else {
            return var1 instanceof SortedSet ? Collections.unmodifiableSortedSet((SortedSet<E>)var1) : Collections.unmodifiableSet((Set<? extends E>)var1);
         }
      }

      @Override
      Collection<V> wrapCollection(K var1, Collection<V> var2) {
         if (var2 instanceof NavigableSet) {
            return new MixinHelper1342.Data8(var1, (NavigableSet<V>)var2, null);
         } else {
            return var2 instanceof SortedSet ? new MixinHelper1342.Data3(var1, (SortedSet<V>)var2, null) : new MixinHelper1342.Data10(var1, (Set<V>)var2);
         }
      }

      @Annotation3
      private void writeObject(ObjectOutputStream var1) {
         var1.defaultWriteObject();
         var1.writeObject(this.field4);
         var1.writeObject(this.backingMap());
      }

      @Annotation3
      private void readObject(ObjectInputStream var1) {
         var1.defaultReadObject();
         this.field4 = (SupplierExtension<? extends Set<V>>)var1.readObject();
         Map var2 = (Map)var1.readObject();
         this.HOCCRCCRCHROIICOOHOHRIICRHCOHR(var2);
      }
   }

   private static class Data8<K, V> extends MixinHelper37.Data10<K, V> implements MixinHelper133<K, V> {
      private static final long field4 = 0L;

      Data8(MixinHelper133<K, V> var1) {
         super(var1);
      }

      public MixinHelper133<K, V> method3() {
         return (MixinHelper133<K, V>)super.method1();
      }

      @Override
      public List<V> get(K var1) {
         return Collections.unmodifiableList(this.method3().get((K)var1));
      }

      @Override
      public List<V> removeAll(Object var1) {
         throw new UnsupportedOperationException();
      }

      @Override
      public List<V> replaceValues(K var1, Iterable<? extends V> var2) {
         throw new UnsupportedOperationException();
      }
   }

   abstract static class Data9<K, V> extends AbstractCollection<Entry<K, V>> {
      abstract Multimap<K, V> method1();

      @Override
      public int size() {
         return this.method1().size();
      }

      @Override
      public boolean contains(@Nullable Object var1) {
         if (var1 instanceof Entry) {
            Entry var2 = (Entry)var1;
            return this.method1().containsEntry(var2.getKey(), var2.getValue());
         } else {
            return false;
         }
      }

      @Override
      public boolean remove(@Nullable Object var1) {
         if (var1 instanceof Entry) {
            Entry var2 = (Entry)var1;
            return this.method1().remove(var2.getKey(), var2.getValue());
         } else {
            return false;
         }
      }

      @Override
      public void clear() {
         this.method1().clear();
      }
   }
}
