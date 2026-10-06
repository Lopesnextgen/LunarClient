package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

abstract class AbstractSetBase<E> extends AbstractSet<E> {
   private final Map<E, ?> field1;
   private final Object field2;

   AbstractSetBase(Map<E, ?> var1, Object var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   public MixinHelperIterator3<E> method1() {
      final Iterator var1 = this.field1.entrySet().iterator();
      return new MixinHelperIterator32_2<E>() {
         @Override
         protected E computeNext() {
            while (var1.hasNext()) {
               Entry var1x = (Entry)var1.next();
               if (AbstractSetBase.this.field2.equals(var1x.getValue())) {
                  return (E)var1x.getKey();
               }
            }

            return (E)this.HRRCOHCCIHRRRCRHRCROIOOCOHRCCH();
         }
      };
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      return this.field2.equals(this.field1.get(var1));
   }
}
