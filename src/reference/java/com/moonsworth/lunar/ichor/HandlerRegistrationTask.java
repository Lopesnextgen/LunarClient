package com.moonsworth.lunar.ichor;

public class HandlerRegistrationTask implements MixinRegistrationTask {
   public HandlerRegistrationTask(Ichor5Handler_2 ichor5handler_21) {
      this.field1 = ichor5handler_21;
   }

   public void method1(IchorStage ichor41, IchorInjector mixininternal22, URLClassLoader urlclassloader3) {
      if (ichor41 == this.field1.field1) {
         mixininternal22.registerMixins(this.field1.method1(urlclassloader3.method16()));
         this.field1.method4(mixininternal22, urlclassloader3.method1());
      }
   }
}
