package com.moonsworth.lunar.ichor.mixin;

public class MixinHelper32 extends MixinHelper3 {
   public MixinHelper32() {
      super(new MixinHelper7("java/lang/Object"));
   }

   @Override
   public void method1(MixinHelper3 var1) {
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof MixinHelper32;
   }

   @Override
   public String toString() {
      return "*";
   }
}
