package com.moonsworth.lunar.ichor;

class StageClassLoaderKey {
   private final IchorStage field1;
   private final ClassLoader field2;

   private StageClassLoaderKey(IchorStage ichor41, ClassLoader type) {
      this.field1 = ichor41;
      this.field2 = type;
   }

   public IchorStage method1() {
      return this.field1;
   }

   public ClassLoader method2() {
      return this.field2;
   }
}
