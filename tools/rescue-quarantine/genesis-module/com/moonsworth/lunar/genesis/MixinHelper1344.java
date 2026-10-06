package com.moonsworth.lunar.genesis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets;
import com.google.common.collect.Multiset;
import com.google.common.collect.Lists;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimap;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

@GwtCompatible
class MixinHelper1344<K, V> extends MixinHelper134<K, V> implements MixinHelper135<K, V> {
   final Multimap<K, V> field2;
   final PredicateExtension<? super Entry<K, V>> field3;

   MixinHelper1344(Multimap<K, V> var1, PredicateExtension<? super Entry<K, V>> var2) {
      this.field2 = Preconditions.checkNotNull(var1);
      this.field3 = Preconditions.checkNotNull(var2);
   }

   @Override
   public Multimap<K, V> method1() {
      return this.field2;
   }

   @Override
   public PredicateExtension<? super Entry<K, V>> method4() {
      return this.field3;
   }

   @Override
   public int size() {
      return this.entries().size();
   }

   private boolean satisfies(K var1, V var2) {
      return this.field3.apply(Maps.immutableEntry((K)var1, (V)var2));
   }

   static <E> Collection<E> method3(Collection<E> var0, PredicateExtension<? super E> var1) {
      return var0 instanceof Set ? Sets.method7((Set<E>)var0, var1) : MixinHelper39.method1(var0, var1);
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      return this.asMap().get(var1) != null;
   }

   @Override
   public Collection<V> removeAll(@Nullable Object var1) {
      return MoreObjects.firstNonNull(this.asMap().remove(var1), this.unmodifiableEmptyCollection());
   }

   Collection<V> unmodifiableEmptyCollection() {
      return this.field2 instanceof MixinHelper132_2 ? Collections.emptySet() : Collections.emptyList();
   }

   @Override
   public void clear() {
      this.entries().clear();
   }

   @Override
   public Collection<V> get(K var1) {
      return method3(this.field2.get((K)var1), new MixinHelper1344.Data3(var1));
   }

   @Override
   Collection<Entry<K, V>> createEntries() {
      return method3(this.field2.entries(), this.field3);
   }

   @Override
   Collection<V> createValues() {
      return new AbstractCollectionIterator2<>(this);
   }

   @Override
   Iterator<Entry<K, V>> entryIterator() {
      throw new AssertionError("should never be called");
   }

   @Override
   Map<K, Collection<V>> createAsMap() {
      return new MixinHelper1344.Data2();
   }

   @Override
   Set<K> createKeySet() {
      return this.asMap().keySet();
   }

   boolean method4(PredicateExtension<? super Entry<K, Collection<V>>> var1) {
      Iterator var2 = this.field2.asMap().entrySet().iterator();
      boolean var3 = false;

      while (var2.hasNext()) {
         Entry var4 = (Entry)var2.next();
         Object var5 = var4.getKey();
         Collection var6 = method3((Collection<V>)var4.getValue(), new MixinHelper1344.Data3(var5));
         if (!var6.isEmpty() && var1.apply(Maps.immutableEntry(var5, var6))) {
            if (var6.size() == ((Collection)var4.getValue()).size()) {
               var2.remove();
            } else {
               var6.clear();
            }

            var3 = true;
         }
      }

      return var3;
   }

   @Override
   Multiset<K> method3() {
      return new MixinHelper1344.Data();
   }

   class Data extends MixinHelper37.Data11<K, V> {
      Data() {
         super(MixinHelper1344.this);
      }

      @Override
      public int remove(@Nullable Object var1, int var2) {
         MixinHelper18_3.checkNonnegative(var2, "occurrences");
         if (var2 == 0) {
            return this.count(var1);
         }

         Collection var3 = MixinHelper1344.this.field2.asMap().get(var1);
         if (var3 == null) {
            return 0;
         }

         Object var4 = var1;
         int var5 = 0;
         Iterator var6 = var3.iterator();

         while (var6.hasNext()) {
            Object var7 = var6.next();
            if (MixinHelper1344.this.satisfies((K)var4, (V)var7)) {
               if (++var5 <= var2) {
                  var6.remove();
               }
            }
         }

         return var5;
      }

      @Override
      public Set<Multiset.Extension<K>> entrySet() {
         return new MixinHelper33$Data6<K>() {
            @Override
            Multiset<K> method1() {
               return Data.this;
            }

            @Override
            public Iterator<Multiset.Extension<K>> iterator() {
               return Data.this.entryIterator();
            }

            @Override
            public int size() {
               return MixinHelper1344.this.keySet().size();
            }

            private boolean method2(final PredicateExtension<? super Multiset.Extension<K>> var1) {
               return MixinHelper1344.this.method4(new PredicateExtension<Entry<K, Collection<V>>>() {
                  public boolean apply(Entry<K, Collection<V>> var1x) {
                     return var1.apply(Multisets.method4(var1x.getKey(), ((Collection)var1x.getValue()).size()));
                  }
               });
            }

            @Override
            public boolean removeAll(Collection<?> var1) {
               return this.method2(Predicates.method15(var1));
            }

            @Override
            public boolean retainAll(Collection<?> var1) {
               return this.method2(Predicates.method5(Predicates.method15(var1)));
            }
         };
      }
   }

   class Data2 extends MixinHelper19$Data29<K, Collection<V>> {
      @Override
      public boolean containsKey(@Nullable Object var1) {
         return this.get(var1) != null;
      }

      @Override
      public void clear() {
         MixinHelper1344.this.clear();
      }

      public Collection<V> get(@Nullable Object var1) {
         Collection var2 = MixinHelper1344.this.field2.asMap().get(var1);
         if (var2 == null) {
            return null;
         }

         Object var3 = var1;
         var2 = MixinHelper1344.method3(var2, MixinHelper1344.this.new Data3(var3));
         return var2.isEmpty() ? null : var2;
      }

      public Collection<V> remove(@Nullable Object var1) {
         Collection var2 = MixinHelper1344.this.field2.asMap().get(var1);
         if (var2 == null) {
            return null;
         }

         Object var3 = var1;
         ArrayList var4 = Lists.newArrayList();
         Iterator var5 = var2.iterator();

         while (var5.hasNext()) {
            Object var6 = var5.next();
            if (MixinHelper1344.this.satisfies((K)var3, (V)var6)) {
               var5.remove();
               var4.add(var6);
            }
         }

         if (var4.isEmpty()) {
            return null;
         } else {
            return MixinHelper1344.this.field2 instanceof MixinHelper132_2
               ? Collections.unmodifiableSet(Sets.newLinkedHashSet(var4))
               : Collections.unmodifiableList(var4);
         }
      }

      @Override
      Set<K> createKeySet() {
         class Data extends MixinHelper19$Data25<K, Collection<V>> {
            Data() {
               super(Data2.this);
            }

            @Override
            public boolean removeAll(Collection<?> var1) {
               return MixinHelper1344.this.method4(Maps.method32(Predicates.method15(var1)));
            }

            @Override
            public boolean retainAll(Collection<?> var1) {
               return MixinHelper1344.this.method4(Maps.method32(Predicates.method5(Predicates.method15(var1))));
            }

            @Override
            public boolean remove(@Nullable Object var1) {
               return Data2.this.remove(var1) != null;
            }
         }

         return new Data();
      }

      @Override
      Set<Entry<K, Collection<V>>> createEntrySet() {
         class Data2 extends MixinHelper19$Data32<K, Collection<V>> {
            @Override
            Map<K, Collection<V>> map() {
               return Data2.this;
            }

            @Override
            public Iterator<Entry<K, Collection<V>>> iterator() {
               return new MixinHelperIterator32_2<Entry<K, Collection<V>>>() {
                  final Iterator<Entry<K, Collection<V>>> field2 = MixinHelper1344.this.field2.asMap().entrySet().iterator();

                  protected Entry<K, Collection<V>> computeNext() {
                     while (this.field2.hasNext()) {
                        Entry var1 = this.field2.next();
                        Object var2 = var1.getKey();
                        Collection var3 = MixinHelper1344.method3((Collection<V>)var1.getValue(), MixinHelper1344.this.new Data3(var2));
                        if (!var3.isEmpty()) {
                           return Maps.immutableEntry((K)var2, var3);
                        }
                     }

                     return (Entry<K, Collection<V>>)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
                  }
               };
            }

            @Override
            public boolean removeAll(Collection<?> var1) {
               return MixinHelper1344.this.method4(Predicates.method15(var1));
            }

            @Override
            public boolean retainAll(Collection<?> var1) {
               return MixinHelper1344.this.method4(Predicates.method5(Predicates.method15(var1)));
            }

            @Override
            public int size() {
               return Iterators.size(this.iterator());
            }
         }

         return new Data2();
      }

      @Override
      Collection<Collection<V>> createValues() {
         class Data3 extends MixinHelper19$Data35<K, Collection<V>> {
            Data3() {
               super(Data2.this);
            }

            @Override
            public boolean remove(@Nullable Object var1) {
               if (var1 instanceof Collection) {
                  Collection var2 = (Collection)var1;
                  Iterator var3 = MixinHelper1344.this.field2.asMap().entrySet().iterator();

                  while (var3.hasNext()) {
                     Entry var4 = (Entry)var3.next();
                     Object var5 = var4.getKey();
                     Collection var6 = MixinHelper1344.method3((Collection<V>)var4.getValue(), MixinHelper1344.this.new Data3(var5));
                     if (!var6.isEmpty() && var2.equals(var6)) {
                        if (var6.size() == ((Collection)var4.getValue()).size()) {
                           var3.remove();
                        } else {
                           var6.clear();
                        }

                        return true;
                     }
                  }
               }

               return false;
            }

            @Override
            public boolean removeAll(Collection<?> var1) {
               return MixinHelper1344.this.method4(Maps.method33(Predicates.method15(var1)));
            }

            @Override
            public boolean retainAll(Collection<?> var1) {
               return MixinHelper1344.this.method4(Maps.method33(Predicates.method5(Predicates.method15(var1))));
            }
         }

         return new Data3();
      }
   }

   final class Data3 implements PredicateExtension<V> {
      private final Object field1;

      Data3(K var2) {
         this.field1 = var2;
      }

      @Override
      public boolean apply(@Nullable V var1) {
         return MixinHelper1344.this.satisfies((K)this.field1, (V)var1);
      }
   }
}
