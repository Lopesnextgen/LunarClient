package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

@GwtCompatible
abstract class MixinHelper1342<K, V> extends MixinHelper134<K, V> implements Serializable {
   private transient Map<K, Collection<V>> map;
   private transient int totalSize;
   private static final long field2 = 2447537837011683357L;

   protected MixinHelper1342(Map<K, Collection<V>> var1) {
      Preconditions.checkArgument(var1.isEmpty());
      this.map = var1;
   }

   final void method1(Map<K, Collection<V>> var1) {
      this.map = var1;
      this.totalSize = 0;

      for (Collection var3 : var1.values()) {
         Preconditions.checkArgument(!var3.isEmpty());
         this.totalSize = this.totalSize + var3.size();
      }
   }

   Collection<V> createUnmodifiableEmptyCollection() {
      return this.unmodifiableCollectionSubclass(this.createCollection());
   }

   abstract Collection<V> createCollection();

   Collection<V> createCollection(@Nullable K var1) {
      return this.createCollection();
   }

   Map<K, Collection<V>> backingMap() {
      return this.map;
   }

   @Override
   public int size() {
      return this.totalSize;
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      return this.map.containsKey(var1);
   }

   @Override
   public boolean put(@Nullable K var1, @Nullable V var2) {
      Collection var3 = this.map.get(var1);
      if (var3 == null) {
         var3 = this.createCollection((K)var1);
         if (var3.add(var2)) {
            this.totalSize++;
            this.map.put((K)var1, var3);
            return true;
         } else {
            throw new AssertionError("New Collection violated the Collection spec");
         }
      } else if (var3.add(var2)) {
         this.totalSize++;
         return true;
      } else {
         return false;
      }
   }

   private Collection<V> getOrCreateCollection(@Nullable K var1) {
      Collection var2 = this.map.get(var1);
      if (var2 == null) {
         var2 = this.createCollection((K)var1);
         this.map.put((K)var1, var2);
      }

      return var2;
   }

   @Override
   public Collection<V> replaceValues(@Nullable K var1, Iterable<? extends V> var2) {
      Iterator var3 = var2.iterator();
      if (!var3.hasNext()) {
         return this.removeAll(var1);
      }

      Collection var4 = this.getOrCreateCollection((K)var1);
      Collection var5 = this.createCollection();
      var5.addAll(var4);
      this.totalSize = this.totalSize - var4.size();
      var4.clear();

      while (var3.hasNext()) {
         if (var4.add(var3.next())) {
            this.totalSize++;
         }
      }

      return this.unmodifiableCollectionSubclass(var5);
   }

   @Override
   public Collection<V> removeAll(@Nullable Object var1) {
      Collection var2 = this.map.remove(var1);
      if (var2 == null) {
         return this.createUnmodifiableEmptyCollection();
      }

      Collection var3 = this.createCollection();
      var3.addAll(var2);
      this.totalSize = this.totalSize - var2.size();
      var2.clear();
      return this.unmodifiableCollectionSubclass(var3);
   }

   <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> var1) {
      return Collections.unmodifiableCollection(var1);
   }

   @Override
   public void clear() {
      for (Collection var2 : this.map.values()) {
         var2.clear();
      }

      this.map.clear();
      this.totalSize = 0;
   }

   @Override
   public Collection<V> get(@Nullable K var1) {
      Collection var2 = this.map.get(var1);
      if (var2 == null) {
         var2 = this.createCollection((K)var1);
      }

      return this.wrapCollection((K)var1, var2);
   }

   Collection<V> wrapCollection(@Nullable K var1, Collection<V> var2) {
      return new MixinHelper1342.Data6(var1, var2, null);
   }

   final List<V> method2(@Nullable K var1, List<V> var2, MixinHelper1342.@Nullable Data6 var3) {
      return var2 instanceof RandomAccess ? new MixinHelper1342.Data9(var1, var2, var3) : new MixinHelper1342.Data(var1, var2, var3);
   }

   private static <E> Iterator<E> iteratorOrListIterator(Collection<E> var0) {
      return var0 instanceof List ? ((List)var0).listIterator() : var0.iterator();
   }

   @Override
   Set<K> createKeySet() {
      return new MixinHelper1342.Data5(this.map);
   }

   final Set<K> method4() {
      if (this.map instanceof NavigableMap) {
         return new MixinHelper1342.Data13((NavigableMap<K, Collection<V>>)this.map);
      } else {
         return this.map instanceof SortedMap ? new MixinHelper1342.Data12((SortedMap<K, Collection<V>>)this.map) : new MixinHelper1342.Data5(this.map);
      }
   }

   private void removeValuesForKey(Object var1) {
      Collection var2 = Maps.safeRemove(this.map, var1);
      if (var2 != null) {
         int var3 = var2.size();
         var2.clear();
         this.totalSize -= var3;
      }
   }

   @Override
   public Collection<V> values() {
      return super.values();
   }

   @Override
   Collection<V> createValues() {
      return new MixinHelper134.Data4();
   }

   @Override
   Iterator<V> valueIterator() {
      return new MixinHelper1342<K, V>.Data2<V>() {
         @Override
         V output(K var1, V var2) {
            return (V)var2;
         }
      };
   }

   @Override
   Spliterator<V> valueSpliterator() {
      return MixinHelper3_5.flatMap(this.map.values().spliterator(), Collection::spliterator, 64, this.size());
   }

   @Override
   Multiset<K> method3() {
      return new MixinHelper37.Data11<>(this);
   }

   @Override
   public Collection<Entry<K, V>> entries() {
      return super.entries();
   }

   @Override
   Collection<Entry<K, V>> createEntries() {
      return this instanceof MixinHelper132_2 ? new MixinHelper134.Data2() : new MixinHelper134.Data3();
   }

   @Override
   Iterator<Entry<K, V>> entryIterator() {
      return new MixinHelper1342<K, V>.Data2<Entry<K, V>>() {
         Entry<K, V> output(K var1, V var2) {
            return Maps.immutableEntry((K)var1, (V)var2);
         }
      };
   }

   @Override
   Spliterator<Entry<K, V>> entrySpliterator() {
      return MixinHelper3_5.flatMap(this.map.entrySet().spliterator(), var0 -> {
         Object var1 = var0.getKey();
         Collection var2 = var0.getValue();
         return MixinHelper3_5.map(var2.spliterator(), var1x -> Maps.immutableEntry((K)var1, (V)var1x));
      }, 64, this.size());
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      Preconditions.checkNotNull(var1);
      this.map.forEach((var1x, var2) -> var2.forEach(var2x -> var1.accept(var1x, var2x)));
   }

   @Override
   Map<K, Collection<V>> createAsMap() {
      return new MixinHelper1342.Data4(this.map);
   }

   final Map<K, Collection<V>> method5() {
      if (this.map instanceof NavigableMap) {
         return new MixinHelper1342.Data7((NavigableMap<K, Collection<V>>)this.map);
      } else {
         return this.map instanceof SortedMap ? new MixinHelper1342.Data11((SortedMap<K, Collection<V>>)this.map) : new MixinHelper1342.Data4(this.map);
      }
   }

   class Data extends MixinHelper1342<K, V>.Data6 implements List<V> {
      Data(@Nullable K var2, List<V> var3, MixinHelper1342.@Nullable Data6 var4) {
         super(var2, var3, var4);
      }

      List<V> getListDelegate() {
         return (List<V>)this.getDelegate();
      }

      @Override
      public boolean addAll(int var1, Collection<? extends V> var2) {
         if (var2.isEmpty()) {
            return false;
         }

         int var3 = this.size();
         boolean var4 = this.getListDelegate().addAll(var1, var2);
         if (var4) {
            int var5 = this.getDelegate().size();
            MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize + (var5 - var3);
            if (var3 == 0) {
               this.addToMap();
            }
         }

         return var4;
      }

      @Override
      public V get(int var1) {
         this.refreshIfEmpty();
         return (V)this.getListDelegate().get(var1);
      }

      @Override
      public V set(int var1, V var2) {
         this.refreshIfEmpty();
         return (V)this.getListDelegate().set(var1, var2);
      }

      @Override
      public void add(int var1, V var2) {
         this.refreshIfEmpty();
         boolean var3 = this.getDelegate().isEmpty();
         this.getListDelegate().add(var1, var2);
         MixinHelper1342.this.totalSize++;
         if (var3) {
            this.addToMap();
         }
      }

      @Override
      public V remove(int var1) {
         this.refreshIfEmpty();
         Object var2 = this.getListDelegate().remove(var1);
         MixinHelper1342.this.totalSize--;
         this.removeIfEmpty();
         return (V)var2;
      }

      @Override
      public int indexOf(Object var1) {
         this.refreshIfEmpty();
         return this.getListDelegate().indexOf(var1);
      }

      @Override
      public int lastIndexOf(Object var1) {
         this.refreshIfEmpty();
         return this.getListDelegate().lastIndexOf(var1);
      }

      @Override
      public ListIterator<V> listIterator() {
         this.refreshIfEmpty();
         return new MixinHelper1342.Data.Data();
      }

      @Override
      public ListIterator<V> listIterator(int var1) {
         this.refreshIfEmpty();
         return new MixinHelper1342.Data.Data(var1);
      }

      @Override
      public List<V> subList(int var1, int var2) {
         this.refreshIfEmpty();
         return MixinHelper1342.this.method2(
            (K)this.getKey(),
            this.getListDelegate().subList(var1, var2),
            this.HRORCOORCHICOCRHHIRIHOHOCRCIII() == null ? this : this.HRORCOORCHICOCRHHIRIHOHOCRCIII()
         );
      }

      private class Data extends MixinHelper1342<K, V>.Data6.Data implements ListIterator<V> {
         Data() {
         }

         public Data(int var2) {
            super(Data.this.getListDelegate().listIterator(var2));
         }

         private ListIterator<V> getDelegateListIterator() {
            return (ListIterator<V>)this.getDelegateIterator();
         }

         @Override
         public boolean hasPrevious() {
            return this.getDelegateListIterator().hasPrevious();
         }

         @Override
         public V previous() {
            return (V)this.getDelegateListIterator().previous();
         }

         @Override
         public int nextIndex() {
            return this.getDelegateListIterator().nextIndex();
         }

         @Override
         public int previousIndex() {
            return this.getDelegateListIterator().previousIndex();
         }

         @Override
         public void set(V var1) {
            this.getDelegateListIterator().set(var1);
         }

         @Override
         public void add(V var1) {
            boolean var2 = Data.this.isEmpty();
            this.getDelegateListIterator().add(var1);
            MixinHelper1342.this.totalSize++;
            if (var2) {
               Data.this.addToMap();
            }
         }
      }
   }

   class Data10 extends MixinHelper1342<K, V>.Data6 implements Set<V> {
      Data10(@Nullable K var2, Set<V> var3) {
         super(var2, var3, null);
      }

      @Override
      public boolean removeAll(Collection<?> var1) {
         if (var1.isEmpty()) {
            return false;
         }

         int var2 = this.size();
         boolean var3 = Sets.removeAllImpl((Set<?>)this.delegate, var1);
         if (var3) {
            int var4 = this.delegate.size();
            MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize + (var4 - var2);
            this.removeIfEmpty();
         }

         return var3;
      }
   }

   private class Data11 extends MixinHelper1342<K, V>.Data4 implements SortedMap<K, Collection<V>> {
      @Nullable SortedSet<K> sortedKeySet;

      Data11(SortedMap<K, Collection<V>> var2) {
         super(var2);
      }

      SortedMap<K, Collection<V>> sortedMap() {
         return (SortedMap<K, Collection<V>>)this.HCHORORCICROHHOIORCOHRRCOIHOIR;
      }

      @Override
      public java.util.Comparator<? super K> comparator() {
         return this.sortedMap().comparator();
      }

      @Override
      public K firstKey() {
         return (K)this.sortedMap().firstKey();
      }

      @Override
      public K lastKey() {
         return (K)this.sortedMap().lastKey();
      }

      @Override
      public SortedMap<K, Collection<V>> headMap(K var1) {
         return MixinHelper1342.this.new Data11(this.sortedMap().headMap(var1));
      }

      @Override
      public SortedMap<K, Collection<V>> subMap(K var1, K var2) {
         return MixinHelper1342.this.new Data11(this.sortedMap().subMap(var1, var2));
      }

      @Override
      public SortedMap<K, Collection<V>> tailMap(K var1) {
         return MixinHelper1342.this.new Data11(this.sortedMap().tailMap(var1));
      }

      public SortedSet<K> keySet() {
         SortedSet var1 = this.sortedKeySet;
         return var1 == null ? (this.sortedKeySet = this.createKeySet()) : var1;
      }

      SortedSet<K> createKeySet() {
         return MixinHelper1342.this.new Data12(this.sortedMap());
      }
   }

   private class Data12 extends MixinHelper1342<K, V>.Data5 implements SortedSet<K> {
      Data12(SortedMap<K, Collection<V>> var2) {
         super(var2);
      }

      SortedMap<K, Collection<V>> sortedMap() {
         return (SortedMap<K, Collection<V>>)super.map();
      }

      @Override
      public java.util.Comparator<? super K> comparator() {
         return this.sortedMap().comparator();
      }

      @Override
      public K first() {
         return (K)this.sortedMap().firstKey();
      }

      @Override
      public SortedSet<K> headSet(K var1) {
         return MixinHelper1342.this.new Data12(this.sortedMap().headMap(var1));
      }

      @Override
      public K last() {
         return (K)this.sortedMap().lastKey();
      }

      @Override
      public SortedSet<K> subSet(K var1, K var2) {
         return MixinHelper1342.this.new Data12(this.sortedMap().subMap(var1, var2));
      }

      @Override
      public SortedSet<K> tailSet(K var1) {
         return MixinHelper1342.this.new Data12(this.sortedMap().tailMap(var1));
      }
   }

   class Data13 extends MixinHelper1342<K, V>.Data12 implements NavigableSet<K> {
      Data13(NavigableMap<K, Collection<V>> var2) {
         super(var2);
      }

      NavigableMap<K, Collection<V>> sortedMap() {
         return (NavigableMap<K, Collection<V>>)super.sortedMap();
      }

      @Override
      public K lower(K var1) {
         return (K)this.sortedMap().lowerKey(var1);
      }

      @Override
      public K floor(K var1) {
         return (K)this.sortedMap().floorKey(var1);
      }

      @Override
      public K ceiling(K var1) {
         return (K)this.sortedMap().ceilingKey(var1);
      }

      @Override
      public K higher(K var1) {
         return (K)this.sortedMap().higherKey(var1);
      }

      @Override
      public K pollFirst() {
         return Iterators.pollNext(this.iterator());
      }

      @Override
      public K pollLast() {
         return Iterators.pollNext(this.descendingIterator());
      }

      @Override
      public NavigableSet<K> descendingSet() {
         return MixinHelper1342.this.new Data13(this.sortedMap().descendingMap());
      }

      @Override
      public Iterator<K> descendingIterator() {
         return this.descendingSet().iterator();
      }

      public NavigableSet<K> headSet(K var1) {
         return (NavigableSet<K>)this.headSet((boolean)var1, false);
      }

      @Override
      public NavigableSet<K> headSet(K var1, boolean var2) {
         return MixinHelper1342.this.new Data13(this.sortedMap().headMap(var1, var2));
      }

      public NavigableSet<K> subSet(K var1, K var2) {
         return (NavigableSet<K>)this.subSet((boolean)var1, true, (boolean)var2, false);
      }

      @Override
      public NavigableSet<K> subSet(K var1, boolean var2, K var3, boolean var4) {
         return MixinHelper1342.this.new Data13(this.sortedMap().subMap(var1, var2, var3, var4));
      }

      public NavigableSet<K> tailSet(K var1) {
         return (NavigableSet<K>)this.tailSet((boolean)var1, true);
      }

      @Override
      public NavigableSet<K> tailSet(K var1, boolean var2) {
         return MixinHelper1342.this.new Data13(this.sortedMap().tailMap(var1, var2));
      }
   }

   private abstract class Data2<T> implements Iterator<T> {
      final Iterator<Entry<K, Collection<V>>> field1 = MixinHelper1342.this.map.entrySet().iterator();
      @Nullable Object key = null;
      @Nullable Collection<V> collection = null;
      Iterator<V> valueIterator = Iterators.emptyModifiableIterator();

      Data2() {
      }

      abstract T output(K var1, V var2);

      @Override
      public boolean hasNext() {
         return this.field1.hasNext() || this.valueIterator.hasNext();
      }

      @Override
      public T next() {
         if (!this.valueIterator.hasNext()) {
            Entry var1 = this.field1.next();
            this.key = var1.getKey();
            this.collection = (Collection<V>)var1.getValue();
            this.valueIterator = this.collection.iterator();
         }

         return this.output((V)this.key, this.valueIterator.next());
      }

      @Override
      public void remove() {
         this.valueIterator.remove();
         if (this.collection.isEmpty()) {
            this.field1.remove();
         }

         MixinHelper1342.this.totalSize--;
      }
   }

   class Data3 extends MixinHelper1342<K, V>.Data6 implements SortedSet<V> {
      Data3(@Nullable K var2, SortedSet<V> var3, MixinHelper1342.@Nullable Data6 var4) {
         super(var2, var3, var4);
      }

      SortedSet<V> getSortedSetDelegate() {
         return (SortedSet<V>)this.getDelegate();
      }

      @Override
      public java.util.Comparator<? super V> comparator() {
         return this.getSortedSetDelegate().comparator();
      }

      @Override
      public V first() {
         this.refreshIfEmpty();
         return (V)this.getSortedSetDelegate().first();
      }

      @Override
      public V last() {
         this.refreshIfEmpty();
         return (V)this.getSortedSetDelegate().last();
      }

      @Override
      public SortedSet<V> headSet(V var1) {
         this.refreshIfEmpty();
         return MixinHelper1342.this.new Data3(
            this.getKey(),
            this.getSortedSetDelegate().headSet(var1),
            this.HRORCOORCHICOCRHHIRIHOHOCRCIII() == null ? this : this.HRORCOORCHICOCRHHIRIHOHOCRCIII()
         );
      }

      @Override
      public SortedSet<V> subSet(V var1, V var2) {
         this.refreshIfEmpty();
         return MixinHelper1342.this.new Data3(
            this.getKey(),
            this.getSortedSetDelegate().subSet(var1, var2),
            this.HRORCOORCHICOCRHHIRIHOHOCRCIII() == null ? this : this.HRORCOORCHICOCRHHIRIHOHOCRCIII()
         );
      }

      @Override
      public SortedSet<V> tailSet(V var1) {
         this.refreshIfEmpty();
         return MixinHelper1342.this.new Data3(
            this.getKey(),
            this.getSortedSetDelegate().tailSet(var1),
            this.HRORCOORCHICOCRHHIRIHOHOCRCIII() == null ? this : this.HRORCOORCHICOCRHHIRIHOHOCRCIII()
         );
      }
   }

   private class Data4 extends MixinHelper19$Data29<K, Collection<V>> {
      final transient Map<K, Collection<V>> field1;

      Data4(Map<K, Collection<V>> var2) {
         this.field1 = var2;
      }

      @Override
      protected Set<Entry<K, Collection<V>>> createEntrySet() {
         return new MixinHelper1342.Data4.Data2();
      }

      @Override
      public boolean containsKey(Object var1) {
         return Maps.safeContainsKey(this.field1, var1);
      }

      public Collection<V> get(Object var1) {
         Collection var2 = Maps.safeGet(this.field1, var1);
         if (var2 == null) {
            return null;
         }

         Object var3 = var1;
         return MixinHelper1342.this.wrapCollection((K)var3, var2);
      }

      @Override
      public Set<K> keySet() {
         return MixinHelper1342.this.keySet();
      }

      @Override
      public int size() {
         return this.field1.size();
      }

      public Collection<V> remove(Object var1) {
         Collection var2 = this.field1.remove(var1);
         if (var2 == null) {
            return null;
         }

         Collection var3 = MixinHelper1342.this.createCollection();
         var3.addAll(var2);
         MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize - var2.size();
         var2.clear();
         return var3;
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         return this == var1 || this.field1.equals(var1);
      }

      @Override
      public int hashCode() {
         return this.field1.hashCode();
      }

      @Override
      public String toString() {
         return this.field1.toString();
      }

      @Override
      public void clear() {
         if (this.field1 == MixinHelper1342.this.map) {
            MixinHelper1342.this.clear();
         } else {
            Iterators.clear(new MixinHelper1342.Data4.Data());
         }
      }

      Entry<K, Collection<V>> wrapEntry(Entry<K, Collection<V>> var1) {
         Object var2 = var1.getKey();
         return Maps.immutableEntry((K)var2, MixinHelper1342.this.wrapCollection((K)var2, (Collection<V>)var1.getValue()));
      }

      class Data implements Iterator<Entry<K, Collection<V>>> {
         final Iterator<Entry<K, Collection<V>>> field1 = Data4.this.field1.entrySet().iterator();
         @Nullable Collection<V> collection;

         @Override
         public boolean hasNext() {
            return this.field1.hasNext();
         }

         public Entry<K, Collection<V>> next() {
            Entry var1 = this.field1.next();
            this.collection = (Collection<V>)var1.getValue();
            return Data4.this.wrapEntry(var1);
         }

         @Override
         public void remove() {
            MixinHelper18_3.checkRemove(this.collection != null);
            this.field1.remove();
            MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize - this.collection.size();
            this.collection.clear();
            this.collection = null;
         }
      }

      class Data2 extends MixinHelper19$Data32<K, Collection<V>> {
         @Override
         Map<K, Collection<V>> map() {
            return Data4.this;
         }

         @Override
         public Iterator<Entry<K, Collection<V>>> iterator() {
            return Data4.this.new Data();
         }

         @Override
         public Spliterator<Entry<K, Collection<V>>> spliterator() {
            return MixinHelper3_5.map(Data4.this.field1.entrySet().spliterator(), Data4.this::wrapEntry);
         }

         @Override
         public boolean contains(Object var1) {
            return MixinHelper39.safeContains(Data4.this.field1.entrySet(), var1);
         }

         @Override
         public boolean remove(Object var1) {
            if (!this.contains(var1)) {
               return false;
            }

            Entry var2 = (Entry)var1;
            MixinHelper1342.this.removeValuesForKey(var2.getKey());
            return true;
         }
      }
   }

   private class Data5 extends MixinHelper19$Data25<K, Collection<V>> {
      Data5(Map<K, Collection<V>> var2) {
         super(var2);
      }

      @Override
      public Iterator<K> iterator() {
         final Iterator var1 = this.map().entrySet().iterator();
         return new Iterator<K>() {
            @Nullable Entry<K, Collection<V>> entry;

            @Override
            public boolean hasNext() {
               return var1.hasNext();
            }

            @Override
            public K next() {
               this.entry = (Entry<K, Collection<V>>)var1.next();
               return this.entry.getKey();
            }

            @Override
            public void remove() {
               MixinHelper18_3.checkRemove(this.entry != null);
               Collection var1x = this.entry.getValue();
               var1.remove();
               MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize - var1x.size();
               var1x.clear();
               this.entry = null;
            }
         };
      }

      @Override
      public Spliterator<K> spliterator() {
         return this.map().keySet().spliterator();
      }

      @Override
      public boolean remove(Object var1) {
         int var2 = 0;
         Collection var3 = (Collection)this.map().remove(var1);
         if (var3 != null) {
            var2 = var3.size();
            var3.clear();
            MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize - var2;
         }

         return var2 > 0;
      }

      @Override
      public void clear() {
         Iterators.clear(this.iterator());
      }

      @Override
      public boolean containsAll(Collection<?> var1) {
         return this.map().keySet().containsAll(var1);
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         return this == var1 || this.map().keySet().equals(var1);
      }

      @Override
      public int hashCode() {
         return this.map().keySet().hashCode();
      }
   }

   class Data6 extends AbstractCollection<V> {
      final @Nullable Object field1;
      Collection<V> delegate;
      final MixinHelper1342.@Nullable Data6 field2;
      final @Nullable Collection<V> field3;

      Data6(@Nullable K var2, Collection<V> var3, MixinHelper1342.@Nullable Data6 var4) {
         this.field1 = var2;
         this.delegate = var3;
         this.field2 = var4;
         this.field3 = var4 == null ? null : var4.getDelegate();
      }

      void refreshIfEmpty() {
         if (this.field2 != null) {
            this.field2.refreshIfEmpty();
            if (this.field2.getDelegate() != this.field3) {
               throw new ConcurrentModificationException();
            }
         } else if (this.delegate.isEmpty()) {
            Collection var1 = MixinHelper1342.this.map.get(this.field1);
            if (var1 != null) {
               this.delegate = var1;
            }
         }
      }

      void removeIfEmpty() {
         if (this.field2 != null) {
            this.field2.removeIfEmpty();
         } else if (this.delegate.isEmpty()) {
            MixinHelper1342.this.map.remove(this.field1);
         }
      }

      K getKey() {
         return (K)this.field1;
      }

      void addToMap() {
         if (this.field2 != null) {
            this.field2.addToMap();
         } else {
            MixinHelper1342.this.map.put((K)this.field1, this.delegate);
         }
      }

      @Override
      public int size() {
         this.refreshIfEmpty();
         return this.delegate.size();
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         if (var1 == this) {
            return true;
         }

         this.refreshIfEmpty();
         return this.delegate.equals(var1);
      }

      @Override
      public int hashCode() {
         this.refreshIfEmpty();
         return this.delegate.hashCode();
      }

      @Override
      public String toString() {
         this.refreshIfEmpty();
         return this.delegate.toString();
      }

      Collection<V> getDelegate() {
         return this.delegate;
      }

      @Override
      public Iterator<V> iterator() {
         this.refreshIfEmpty();
         return new MixinHelper1342.Data6.Data();
      }

      @Override
      public Spliterator<V> spliterator() {
         this.refreshIfEmpty();
         return this.delegate.spliterator();
      }

      @Override
      public boolean add(V var1) {
         this.refreshIfEmpty();
         boolean var2 = this.delegate.isEmpty();
         boolean var3 = this.delegate.add((V)var1);
         if (var3) {
            MixinHelper1342.this.totalSize++;
            if (var2) {
               this.addToMap();
            }
         }

         return var3;
      }

      MixinHelper1342<K, V>.Data6 method1() {
         return this.field2;
      }

      @Override
      public boolean addAll(Collection<? extends V> var1) {
         if (var1.isEmpty()) {
            return false;
         }

         int var2 = this.size();
         boolean var3 = this.delegate.addAll(var1);
         if (var3) {
            int var4 = this.delegate.size();
            MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize + (var4 - var2);
            if (var2 == 0) {
               this.addToMap();
            }
         }

         return var3;
      }

      @Override
      public boolean contains(Object var1) {
         this.refreshIfEmpty();
         return this.delegate.contains(var1);
      }

      @Override
      public boolean containsAll(Collection<?> var1) {
         this.refreshIfEmpty();
         return this.delegate.containsAll(var1);
      }

      @Override
      public void clear() {
         int var1 = this.size();
         if (var1 != 0) {
            this.delegate.clear();
            MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize - var1;
            this.removeIfEmpty();
         }
      }

      @Override
      public boolean remove(Object var1) {
         this.refreshIfEmpty();
         boolean var2 = this.delegate.remove(var1);
         if (var2) {
            MixinHelper1342.this.totalSize--;
            this.removeIfEmpty();
         }

         return var2;
      }

      @Override
      public boolean removeAll(Collection<?> var1) {
         if (var1.isEmpty()) {
            return false;
         }

         int var2 = this.size();
         boolean var3 = this.delegate.removeAll(var1);
         if (var3) {
            int var4 = this.delegate.size();
            MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize + (var4 - var2);
            this.removeIfEmpty();
         }

         return var3;
      }

      @Override
      public boolean retainAll(Collection<?> var1) {
         Preconditions.checkNotNull(var1);
         int var2 = this.size();
         boolean var3 = this.delegate.retainAll(var1);
         if (var3) {
            int var4 = this.delegate.size();
            MixinHelper1342.this.totalSize = MixinHelper1342.this.totalSize + (var4 - var2);
            this.removeIfEmpty();
         }

         return var3;
      }

      class Data implements Iterator<V> {
         final Iterator<V> field1;
         final Collection<V> field2;

         Data() {
            this.field2 = Data6.this.delegate;
            this.field1 = MixinHelper1342.iteratorOrListIterator(Data6.this.delegate);
         }

         Data(Iterator<V> var2) {
            this.field2 = Data6.this.delegate;
            this.field1 = var2;
         }

         void validateIterator() {
            Data6.this.refreshIfEmpty();
            if (Data6.this.delegate != this.field2) {
               throw new ConcurrentModificationException();
            }
         }

         @Override
         public boolean hasNext() {
            this.validateIterator();
            return this.field1.hasNext();
         }

         @Override
         public V next() {
            this.validateIterator();
            return this.field1.next();
         }

         @Override
         public void remove() {
            this.field1.remove();
            MixinHelper1342.this.totalSize--;
            Data6.this.removeIfEmpty();
         }

         Iterator<V> getDelegateIterator() {
            this.validateIterator();
            return this.field1;
         }
      }
   }

   class Data7 extends MixinHelper1342<K, V>.Data11 implements NavigableMap<K, Collection<V>> {
      Data7(NavigableMap<K, Collection<V>> var2) {
         super(var2);
      }

      NavigableMap<K, Collection<V>> sortedMap() {
         return (NavigableMap<K, Collection<V>>)super.sortedMap();
      }

      @Override
      public Entry<K, Collection<V>> lowerEntry(K var1) {
         Entry var2 = this.sortedMap().lowerEntry(var1);
         return var2 == null ? null : this.wrapEntry(var2);
      }

      @Override
      public K lowerKey(K var1) {
         return (K)this.sortedMap().lowerKey(var1);
      }

      @Override
      public Entry<K, Collection<V>> floorEntry(K var1) {
         Entry var2 = this.sortedMap().floorEntry(var1);
         return var2 == null ? null : this.wrapEntry(var2);
      }

      @Override
      public K floorKey(K var1) {
         return (K)this.sortedMap().floorKey(var1);
      }

      @Override
      public Entry<K, Collection<V>> ceilingEntry(K var1) {
         Entry var2 = this.sortedMap().ceilingEntry(var1);
         return var2 == null ? null : this.wrapEntry(var2);
      }

      @Override
      public K ceilingKey(K var1) {
         return (K)this.sortedMap().ceilingKey(var1);
      }

      @Override
      public Entry<K, Collection<V>> higherEntry(K var1) {
         Entry var2 = this.sortedMap().higherEntry(var1);
         return var2 == null ? null : this.wrapEntry(var2);
      }

      @Override
      public K higherKey(K var1) {
         return (K)this.sortedMap().higherKey(var1);
      }

      @Override
      public Entry<K, Collection<V>> firstEntry() {
         Entry var1 = this.sortedMap().firstEntry();
         return var1 == null ? null : this.wrapEntry(var1);
      }

      @Override
      public Entry<K, Collection<V>> lastEntry() {
         Entry var1 = this.sortedMap().lastEntry();
         return var1 == null ? null : this.wrapEntry(var1);
      }

      @Override
      public Entry<K, Collection<V>> pollFirstEntry() {
         return this.pollAsMapEntry(this.entrySet().iterator());
      }

      @Override
      public Entry<K, Collection<V>> pollLastEntry() {
         return this.pollAsMapEntry(this.descendingMap().entrySet().iterator());
      }

      Entry<K, Collection<V>> pollAsMapEntry(Iterator<Entry<K, Collection<V>>> var1) {
         if (!var1.hasNext()) {
            return null;
         }

         Entry var2 = (Entry)var1.next();
         Collection var3 = MixinHelper1342.this.createCollection();
         var3.addAll((Collection)var2.getValue());
         var1.remove();
         return Maps.immutableEntry((K)var2.getKey(), MixinHelper1342.this.unmodifiableCollectionSubclass(var3));
      }

      @Override
      public NavigableMap<K, Collection<V>> descendingMap() {
         return MixinHelper1342.this.new Data7(this.sortedMap().descendingMap());
      }

      public NavigableSet<K> keySet() {
         return (NavigableSet<K>)super.keySet();
      }

      NavigableSet<K> createKeySet() {
         return MixinHelper1342.this.new Data13(this.sortedMap());
      }

      @Override
      public NavigableSet<K> navigableKeySet() {
         return this.keySet();
      }

      @Override
      public NavigableSet<K> descendingKeySet() {
         return this.descendingMap().navigableKeySet();
      }

      public NavigableMap<K, Collection<V>> subMap(K var1, K var2) {
         return (NavigableMap<K, Collection<V>>)this.subMap((boolean)var1, true, (boolean)var2, false);
      }

      @Override
      public NavigableMap<K, Collection<V>> subMap(K var1, boolean var2, K var3, boolean var4) {
         return MixinHelper1342.this.new Data7(this.sortedMap().subMap(var1, var2, var3, var4));
      }

      public NavigableMap<K, Collection<V>> headMap(K var1) {
         return (NavigableMap<K, Collection<V>>)this.headMap((boolean)var1, false);
      }

      @Override
      public NavigableMap<K, Collection<V>> headMap(K var1, boolean var2) {
         return MixinHelper1342.this.new Data7(this.sortedMap().headMap(var1, var2));
      }

      public NavigableMap<K, Collection<V>> tailMap(K var1) {
         return (NavigableMap<K, Collection<V>>)this.tailMap((boolean)var1, true);
      }

      @Override
      public NavigableMap<K, Collection<V>> tailMap(K var1, boolean var2) {
         return MixinHelper1342.this.new Data7(this.sortedMap().tailMap(var1, var2));
      }
   }

   class Data8 extends MixinHelper1342<K, V>.Data3 implements NavigableSet<V> {
      Data8(@Nullable K var2, NavigableSet<V> var3, MixinHelper1342.@Nullable Data6 var4) {
         super(var2, var3, var4);
      }

      NavigableSet<V> getSortedSetDelegate() {
         return (NavigableSet<V>)super.getSortedSetDelegate();
      }

      @Override
      public V lower(V var1) {
         return (V)this.getSortedSetDelegate().lower(var1);
      }

      @Override
      public V floor(V var1) {
         return (V)this.getSortedSetDelegate().floor(var1);
      }

      @Override
      public V ceiling(V var1) {
         return (V)this.getSortedSetDelegate().ceiling(var1);
      }

      @Override
      public V higher(V var1) {
         return (V)this.getSortedSetDelegate().higher(var1);
      }

      @Override
      public V pollFirst() {
         return Iterators.pollNext(this.iterator());
      }

      @Override
      public V pollLast() {
         return Iterators.pollNext(this.descendingIterator());
      }

      private NavigableSet<V> wrap(NavigableSet<V> var1) {
         return MixinHelper1342.this.new Data8(
            this.HRCICOIHHHIHRCCHCOHIOROIIROHIO, var1, this.HRORCOORCHICOCRHHIRIHOHOCRCIII() == null ? this : this.HRORCOORCHICOCRHHIRIHOHOCRCIII()
         );
      }

      @Override
      public NavigableSet<V> descendingSet() {
         return this.wrap(this.getSortedSetDelegate().descendingSet());
      }

      @Override
      public Iterator<V> descendingIterator() {
         return new MixinHelper1342.Data6.Data(this.getSortedSetDelegate().descendingIterator());
      }

      @Override
      public NavigableSet<V> subSet(V var1, boolean var2, V var3, boolean var4) {
         return this.wrap((NavigableSet<V>)this.getSortedSetDelegate().subSet(var1, var2, var3, var4));
      }

      @Override
      public NavigableSet<V> headSet(V var1, boolean var2) {
         return this.wrap((NavigableSet<V>)this.getSortedSetDelegate().headSet(var1, var2));
      }

      @Override
      public NavigableSet<V> tailSet(V var1, boolean var2) {
         return this.wrap((NavigableSet<V>)this.getSortedSetDelegate().tailSet(var1, var2));
      }
   }

   private class Data9 extends MixinHelper1342<K, V>.Data implements RandomAccess {
      Data9(@Nullable K var2, List<V> var3, MixinHelper1342.@Nullable Data6 var4) {
         super(var2, var3, var4);
      }
   }
}
