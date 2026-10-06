package com.moonsworth.lunar.genesis;

import java.lang.reflect.InvocationTargetException;

class Subscriber$1 implements Runnable {
   Subscriber$1(MixinHelper6_6 mixinhelper6_61, Object obj2) {
      this.field2 = mixinhelper6_61;
      this.field1 = obj2;
   }

   @Override
   public void run() {
      try {
         this.field2.invokeSubscriberMethod(this.field1);
      } catch (InvocationTargetException invocationtargetexception2) {
         MixinHelper6_6.method5(this.field2).method3(invocationtargetexception2.getCause(), MixinHelper6_6.method4(this.field2, this.field1));
      }
   }
}
