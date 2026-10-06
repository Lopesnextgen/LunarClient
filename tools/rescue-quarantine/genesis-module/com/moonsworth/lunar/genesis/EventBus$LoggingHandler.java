package com.moonsworth.lunar.genesis;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

final class EventBus$LoggingHandler implements SubscriberExceptionHandler {
   static final EventBus$LoggingHandler field1 = new EventBus$LoggingHandler();

   EventBus$LoggingHandler() {
   }

   public void method1(Throwable exception1, SubscriberExceptionContext mixinhelper7_62) {
      Logger logger3 = method2(mixinhelper7_62);
      if (logger3.isLoggable(Level.SEVERE)) {
         logger3.log(Level.SEVERE, method3(mixinhelper7_62), exception1);
      }
   }

   private static Logger method2(SubscriberExceptionContext mixinhelper7_60) {
      return Logger.getLogger(MixinHelper8_3.class.getName() + "." + mixinhelper7_60.method1().method1());
   }

   private static String method3(SubscriberExceptionContext mixinhelper7_60) {
      Method method1_ = mixinhelper7_60.getSubscriberMethod();
      return "Exception thrown by subscriber method "
         + method1_.getName()
         + '('
         + method1_.getParameterTypes()[0].getName()
         + ')'
         + " on subscriber "
         + mixinhelper7_60.getSubscriber()
         + " when dispatching event: "
         + mixinhelper7_60.getEvent();
   }
}
