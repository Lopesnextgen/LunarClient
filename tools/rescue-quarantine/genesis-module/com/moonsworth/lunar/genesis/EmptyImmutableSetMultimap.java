package com.moonsworth.lunar.genesis;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
class EmptyImmutableSetMultimap extends MixinHelper134523<Object, Object> {
   static final EmptyImmutableSetMultimap field7 = new EmptyImmutableSetMultimap();
   private static final long field8 = 0L;

   private EmptyImmutableSetMultimap() {
      super(ImmutableMap.method1(), 0);
   }

   private Object readResolve() {
      return field7;
   }
}
