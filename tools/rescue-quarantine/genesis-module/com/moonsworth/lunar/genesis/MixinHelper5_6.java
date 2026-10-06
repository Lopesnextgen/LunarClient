package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class MixinHelper5_6<K, V> {
   private final Map<K, V> field1;
   private transient volatile @Nullable Entry<K, V> cacheEntry;

   MixinHelper5_6(Map<K, V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @CanIgnoreReturnValue
   public final V method1(@Nullable K var1, @Nullable V var2) {
      this.clearCache();
      return this.field1.put((K)var1, (V)var2);
   }

   @CanIgnoreReturnValue
   public final V method2(@Nullable Object var1) {
      this.clearCache();
      return this.field1.remove(var1);
   }

   public final void method3() {
      this.clearCache();
      this.field1.clear();
   }

   public V get(@Nullable Object var1) {
      Object var2 = this.getIfCached(var1);
      return (V)(var2 != null ? var2 : this.method4(var1));
   }

   public final V method4(@Nullable Object var1) {
      return this.field1.get(var1);
   }

   public final boolean method5(@Nullable Object var1) {
      return this.getIfCached(var1) != null || this.field1.containsKey(var1);
   }

   public final Set<K> method6() {
      return new AbstractSet<K>() {
         public MixinHelperIterator3<K> method1() {
            final Iterator var1 = MixinHelper5_6.this.field1.entrySet().iterator();
            return new MixinHelperIterator3<K>() {
               @Override
               public boolean hasNext() {
                  return var1.hasNext();
               }

               @Override
               public K next() {
                  Entry var1x = (Entry)var1.next();
                  MixinHelper5_6.this.cacheEntry = var1x;
                  return (K)var1x.getKey();
               }
            };
         }

         @Override
         public int size() {
            return MixinHelper5_6.this.field1.size();
         }

         @Override
         public boolean contains(@Nullable Object var1) {
            return MixinHelper5_6.this.method5(var1);
         }
      };
   }

   protected V getIfCached(@Nullable Object var1) {
      Entry var2 = this.cacheEntry;
      return (V)(var2 != null && var2.getKey() == var1 ? var2.getValue() : null);
   }

   protected void clearCache() {
      this.cacheEntry = null;
   }
}
