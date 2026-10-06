package com.moonsworth.lunar.genesis;
import com.google.common.base.Preconditions;

abstract class Striped$PowerOfTwoStriped<L> extends MixinHelper27<L> {
   final int field5;

   Striped$PowerOfTwoStriped(int number1) {
      super(null);
      Preconditions.checkArgument(number1 > 0, "Stripes must be positive");
      this.field5 = number1 > 1073741824 ? -1 : MixinHelper27.access$200(number1) - 1;
   }

   final int indexFor(Object obj1) {
      int number2 = MixinHelper27.access$300(obj1.hashCode());
      return number2 & this.field5;
   }

   public final L get(Object obj1) {
      return (L)this.getAt(this.indexFor(obj1));
   }
}
