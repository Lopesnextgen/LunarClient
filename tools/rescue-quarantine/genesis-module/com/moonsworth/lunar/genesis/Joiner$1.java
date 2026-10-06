package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

class Joiner$1 extends MixinHelper16_2 {
   Joiner$1(MixinHelper16_2 mixinhelper16_21, MixinHelper16_2 mixinhelper16_22, String text3) {
      super(mixinhelper16_22, null);
      this.field3 = mixinhelper16_21;
      this.field2 = text3;
   }

   CharSequence toString(@Nullable Object obj1) {
      return obj1 == null ? this.field2 : this.field3.toString(obj1);
   }

   public MixinHelper16_2 method13(String text1) {
      throw new UnsupportedOperationException("already specified useForNull");
   }

   public MixinHelper16_2 method14() {
      throw new UnsupportedOperationException("already specified useForNull");
   }
}
