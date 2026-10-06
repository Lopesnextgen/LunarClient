package com.moonsworth.lunar.genesis;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.eventbus.EventBus;

final class MixinHelper8$Data27 implements MixinHelper3_4 {
   static final MixinHelper8$Data27 field1 = new MixinHelper8$Data27();

   @Override
   public void method1(Throwable var1, MixinHelper7_6 var2) {
      Logger var3 = method2(var2);
      if (var3.isLoggable(Level.SEVERE)) {
         var3.log(Level.SEVERE, method3(var2), var1);
      }
   }

   private static Logger method2(MixinHelper7_6 var0) {
      return Logger.getLogger(EventBus.class.getName() + "." + var0.method1().method1());
   }

   private static String method3(MixinHelper7_6 var0) {
      Method var1 = var0.getSubscriberMethod();
      return "Exception thrown by subscriber method "
         + var1.getName()
         + '('
         + var1.getParameterTypes()[0].getName()
         + ')'
         + " on subscriber "
         + var0.getSubscriber()
         + " when dispatching event: "
         + var0.getEvent();
   }
}
