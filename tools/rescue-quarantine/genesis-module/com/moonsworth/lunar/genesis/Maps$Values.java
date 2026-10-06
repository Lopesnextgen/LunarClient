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
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

class Maps$Values<K, V> extends AbstractCollection<V> {
   @Weak
   final Map<K, V> field1;

   Maps$Values(Map<K, V> map1) {
      this.field1 = (Map<K, V>)Preconditions.checkNotNull(map1);
   }

   final Map<K, V> method1() {
      return this.field1;
   }

   @Override
   public Iterator<V> iterator() {
      return MixinHelper19_3.valueIterator(this.method1().entrySet().iterator());
   }

   @Override
   public void forEach(Consumer<? super V> consumer1) {
      Preconditions.checkNotNull(consumer1);
      this.field1.forEach((arg1x, arg2) -> consumer1.accept(arg2));
   }

   @Override
   public boolean remove(Object obj1) {
      try {
         return super.remove(obj1);
      } catch (UnsupportedOperationException unsupportedoperationexception5) {
         for (Entry entry4 : this.method1().entrySet()) {
            if (Objects.equal(obj1, entry4.getValue())) {
               this.method1().remove(entry4.getKey());
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public boolean removeAll(Collection<?> list1) {
      try {
         return super.removeAll((Collection<?>)Preconditions.checkNotNull(list1));
      } catch (UnsupportedOperationException unsupportedoperationexception6) {
         HashSet set3 = Sets.newHashSet();

         for (Entry entry5 : this.method1().entrySet()) {
            if (list1.contains(entry5.getValue())) {
               set3.add(entry5.getKey());
            }
         }

         return this.method1().keySet().removeAll(set3);
      }
   }

   @Override
   public boolean retainAll(Collection<?> list1) {
      try {
         return super.retainAll((Collection<?>)Preconditions.checkNotNull(list1));
      } catch (UnsupportedOperationException unsupportedoperationexception6) {
         HashSet set3 = Sets.newHashSet();

         for (Entry entry5 : this.method1().entrySet()) {
            if (list1.contains(entry5.getValue())) {
               set3.add(entry5.getKey());
            }
         }

         return this.method1().keySet().retainAll(set3);
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
   public boolean contains(@Nullable Object obj1) {
      return this.method1().containsValue(obj1);
   }

   @Override
   public void clear() {
      this.method1().clear();
   }
}
