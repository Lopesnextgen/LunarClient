package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.Weak;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;
import com.google.common.collect.Iterables;
import com.google.common.base.Predicates;
import com.google.common.base.Preconditions;

@GwtCompatible
final class AbstractCollectionIterator2<K, V> extends AbstractCollection<V> {
   @Weak
   private final MixinHelper135<K, V> field1;

   AbstractCollectionIterator2(MixinHelper135<K, V> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public Iterator<V> iterator() {
      return Maps.valueIterator(this.field1.entries().iterator());
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      return this.field1.containsValue(var1);
   }

   @Override
   public int size() {
      return this.field1.size();
   }

   @Override
   public boolean remove(@Nullable Object var1) {
      PredicateExtension var2 = this.field1.method4();
      Iterator var3 = this.field1.method1().entries().iterator();

      while (var3.hasNext()) {
         Entry var4 = (Entry)var3.next();
         if (var2.apply(var4) && MixinHelper72.equal(var4.getValue(), var1)) {
            var3.remove();
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean removeAll(Collection<?> var1) {
      return Iterables.method2(
         this.field1.method1().entries(), Predicates.method8(this.field1.method4(), Maps.method33(Predicates.method15(var1)))
      );
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      return Iterables.method2(
         this.field1.method1().entries(),
         Predicates.method8(this.field1.method4(), Maps.method33(Predicates.method5(Predicates.method15(var1))))
      );
   }

   @Override
   public void clear() {
      this.field1.clear();
   }
}
