package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

class MixinHelper19$Data10<K, V> extends MixinHelper19$Data18<K, V> {
   final Set<Entry<K, V>> field3;

   MixinHelper19$Data10(Map<K, V> var1, PredicateExtension<? super Entry<K, V>> var2) {
      super(var1, var2);
      this.field3 = Sets.method7(var1.entrySet(), this.field2);
   }

   @Override
   protected Set<Entry<K, V>> createEntrySet() {
      return new MixinHelper19$Data10.Data2();
   }

   @Override
   Set<K> createKeySet() {
      return new MixinHelper19$Data10.Data();
   }

   static <K, V> boolean method1(Map<K, V> var0, PredicateExtension<? super Entry<K, V>> var1, Collection<?> var2) {
      Iterator var3 = var0.entrySet().iterator();
      boolean var4 = false;

      while (var3.hasNext()) {
         Entry var5 = (Entry)var3.next();
         if (var1.apply(var5) && var2.contains(var5.getKey())) {
            var3.remove();
            var4 = true;
         }
      }

      return var4;
   }

   static <K, V> boolean method2(Map<K, V> var0, PredicateExtension<? super Entry<K, V>> var1, Collection<?> var2) {
      Iterator var3 = var0.entrySet().iterator();
      boolean var4 = false;

      while (var3.hasNext()) {
         Entry var5 = (Entry)var3.next();
         if (var1.apply(var5) && !var2.contains(var5.getKey())) {
            var3.remove();
            var4 = true;
         }
      }

      return var4;
   }

   class Data extends MixinHelper19$Data25<K, V> {
      Data() {
         super(MixinHelper19$Data10.this);
      }

      @Override
      public boolean remove(Object var1) {
         if (MixinHelper19$Data10.this.containsKey(var1)) {
            MixinHelper19$Data10.this.OCOHCCOOIHHHOCHICCCIIIIOCRHCCI.remove(var1);
            return true;
         } else {
            return false;
         }
      }

      @Override
      public boolean removeAll(Collection<?> var1) {
         return MixinHelper19$Data10.method1(
            MixinHelper19$Data10.this.OCOHCCOOIHHHOCHICCCIIIIOCRHCCI, MixinHelper19$Data10.this.field2, var1
         );
      }

      @Override
      public boolean retainAll(Collection<?> var1) {
         return MixinHelper19$Data10.method2(
            MixinHelper19$Data10.this.OCOHCCOOIHHHOCHICCCIIIIOCRHCCI, MixinHelper19$Data10.this.field2, var1
         );
      }

      @Override
      public Object[] toArray() {
         return Lists.newArrayList(this.iterator()).toArray();
      }

      @Override
      public <T> T[] toArray(T[] var1) {
         return (T[])Lists.newArrayList(this.iterator()).toArray(var1);
      }
   }

   private class Data2 extends MixinHelper3165<Entry<K, V>> {
      private Data2() {
      }

      @Override
      protected Set<Entry<K, V>> delegate() {
         return MixinHelper19$Data10.this.field3;
      }

      @Override
      public Iterator<Entry<K, V>> iterator() {
         return new MixinHelperIterator2<Entry<K, V>, Entry<K, V>>(MixinHelper19$Data10.this.field3.iterator()) {
            Entry<K, V> transform(final Entry<K, V> var1) {
               return new MixinHelper315<K, V>() {
                  @Override
                  protected Entry<K, V> delegate() {
                     return var1;
                  }

                  @Override
                  public V setValue(V var1x) {
                     Preconditions.checkArgument(MixinHelper19$Data10.this.apply(this.getKey(), (V)var1x));
                     return (V)super.setValue(var1x);
                  }
               };
            }
         };
      }
   }
}
