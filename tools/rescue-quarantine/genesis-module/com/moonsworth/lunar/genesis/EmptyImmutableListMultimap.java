package com.moonsworth.lunar.genesis;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
class EmptyImmutableListMultimap extends ImmutableSetMultimap<Object, Object> {
   static final EmptyImmutableListMultimap field9 = new EmptyImmutableListMultimap();
   private static final long field10 = 0L;

   private EmptyImmutableListMultimap() {
      super(ImmutableMap.method1(), 0, null);
   }

   private Object readResolve() {
      return field9;
   }
}
