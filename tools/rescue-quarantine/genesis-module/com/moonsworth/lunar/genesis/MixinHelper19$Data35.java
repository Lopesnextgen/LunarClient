package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

class MixinHelper19$Data35<K, V> extends AbstractCollection<V> {
   @Weak
   final Map<K, V> field1;

   MixinHelper19$Data35(Map<K, V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   final Map<K, V> method1() {
      return this.field1;
   }

   @Override
   public Iterator<V> iterator() {
      return Maps.valueIterator(this.method1().entrySet().iterator());
   }

   @Override
   public void forEach(Consumer<? super V> var1) {
      Preconditions.checkNotNull(var1);
      this.field1.forEach((var1x, var2) -> var1.accept(var2));
   }

   @Override
   public boolean remove(Object var1) {
      try {
         return super.remove(var1);
      } catch (UnsupportedOperationException var5) {
         for (Entry var4 : this.method1().entrySet()) {
            if (MixinHelper72.equal(var1, var4.getValue())) {
               this.method1().remove(var4.getKey());
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public boolean removeAll(Collection<?> var1) {
      try {
         return super.removeAll(Preconditions.checkNotNull(var1));
      } catch (UnsupportedOperationException var6) {
         HashSet var3 = Sets.newHashSet();

         for (Entry var5 : this.method1().entrySet()) {
            if (var1.contains(var5.getValue())) {
               var3.add(var5.getKey());
            }
         }

         return this.method1().keySet().removeAll(var3);
      }
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      try {
         return super.retainAll(Preconditions.checkNotNull(var1));
      } catch (UnsupportedOperationException var6) {
         HashSet var3 = Sets.newHashSet();

         for (Entry var5 : this.method1().entrySet()) {
            if (var1.contains(var5.getValue())) {
               var3.add(var5.getKey());
            }
         }

         return this.method1().keySet().retainAll(var3);
      }
   }

   @Override
   public int size() {
      return this.method1().size();
   }

   @Override
   public boolean isEmpty() {
      return this.method1().isEmpty();
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      return this.method1().containsValue(var1);
   }

   @Override
   public void clear() {
      this.method1().clear();
   }
}
