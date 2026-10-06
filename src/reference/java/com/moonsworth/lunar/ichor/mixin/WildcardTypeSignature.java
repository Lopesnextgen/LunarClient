package com.moonsworth.lunar.ichor.mixin;

public class WildcardTypeSignature extends MixinHelper3 {
   public WildcardTypeSignature() {
      super(new ClassTypeSignature("java/lang/Object"));
   }

   @Override
   public void method1(MixinHelper3 mixinhelper31) {
   }

   @Override
   public boolean equals(Object object) {
      return object instanceof WildcardTypeSignature;
   }

   @Override
   public String toString() {
      return "*";
   }
}
