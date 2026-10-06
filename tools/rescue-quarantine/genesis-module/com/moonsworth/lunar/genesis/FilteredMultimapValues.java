package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;

@GwtCompatible
final class FilteredMultimapValues<K, V> extends AbstractCollection<V> {
   @Weak
   private final ServiceManagerBridge<K, V> field1;

   FilteredMultimapValues(ServiceManagerBridge<K, V> mixinhelper1351) {
      this.field1 = (ServiceManagerBridge<K, V>)Preconditions.checkNotNull(mixinhelper1351);
   }

   @Override
   public Iterator<V> iterator() {
      return Maps.valueIterator(this.field1.entries().iterator());
   }

   @Override
   public boolean contains(@Nullable Object obj1) {
      return this.field1.containsValue(obj1);
   }

   @Override
   public int size() {
      return this.field1.size();
   }

   @Override
   public boolean remove(@Nullable Object obj1) {
      Predicate predicateextension2 = this.field1.method4();
      Iterator iterator3 = this.field1.method1().entries().iterator();

      while (iterator3.hasNext()) {
         Entry entry4 = (Entry)iterator3.next();
         if (predicateextension2.apply(entry4) && Objects.equal(entry4.getValue(), obj1)) {
            iterator3.remove();
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean removeAll(Collection<?> list1) {
      return Iterables.method2(
         this.field1.method1().entries(), Predicates.method8(this.field1.method4(), Maps.method33(Predicates.method15(list1)))
      );
   }

   @Override
   public boolean retainAll(Collection<?> list1) {
      return Iterables.method2(
         this.field1.method1().entries(),
         Predicates.method8(this.field1.method4(), Maps.method33(Predicates.method5(Predicates.method15(list1))))
      );
   }

   @Override
   public void clear() {
      this.field1.clear();
   }
}
