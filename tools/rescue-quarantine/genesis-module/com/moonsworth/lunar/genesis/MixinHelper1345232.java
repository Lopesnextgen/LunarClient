package com.moonsworth.lunar.genesis;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(serializable = true)
class MixinHelper1345232 extends MixinHelper134523<Object, Object> {
   static final MixinHelper1345232 field7 = new MixinHelper1345232();
   private static final long field8 = 0L;

   private MixinHelper1345232() {
      super(ImmutableMap.method1(), 0);
   }

   private Object readResolve() {
      return field7;
   }
}
