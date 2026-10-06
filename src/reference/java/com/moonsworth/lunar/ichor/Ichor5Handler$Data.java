package com.moonsworth.lunar.ichor;

public class Ichor5Handler$Data implements MixinInternal3 {
   public Ichor5Handler$Data(Ichor5Handler_2 var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(Ichor4 var1, MixinInternal2 var2, URLClassLoader var3) {
      if (var1 == this.field1.field1) {
         var2.registerMixins(this.field1.method1(var3.method16()));
         this.field1.method4(var2, var3.method1());
      }
   }
}
