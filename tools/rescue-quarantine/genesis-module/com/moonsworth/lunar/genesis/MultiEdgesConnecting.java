package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;

abstract class MultiEdgesConnecting<E> extends AbstractSet<E> {
   private final Map<E, ?> field1;
   private final Object field2;

   MultiEdgesConnecting(Map<E, ?> map1, Object obj2) {
      this.field1 = (Map<E, ?>)Preconditions.checkNotNull(map1);
      this.field2 = Preconditions.checkNotNull(obj2);
   }

   public UnmodifiableIterator<E> method1() {
      Iterator iterator1 = this.field1.entrySet().iterator();
      return new AbstractSetBase$1(this, iterator1);
   }

   @Override
   public boolean contains(@Nullable Object obj1) {
      return this.field2.equals(this.field1.get(obj1));
   }
}
