package com.moonsworth.lunar.genesis;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
class MixinHelper1345222 extends ImmutableSetMultimap<Object, Object> {
   static final MixinHelper1345222 field9 = new MixinHelper1345222();
   private static final long field10 = 0L;

   private MixinHelper1345222() {
      super(ImmutableMap.method1(), 0, null);
   }

   private Object readResolve() {
      return field9;
   }
}
