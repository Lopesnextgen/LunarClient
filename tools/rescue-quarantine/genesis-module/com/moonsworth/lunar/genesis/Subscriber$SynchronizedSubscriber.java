package com.moonsworth.lunar.genesis;

import java.lang.reflect.Method;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

@Subscribe
final class Subscriber$SynchronizedSubscriber extends MixinHelper6_6 {
   private Subscriber$SynchronizedSubscriber(EventBus mixinhelper8_31, Object obj2, Method method3) {
      super(mixinhelper8_31, obj2, method3, null);
   }

   void invokeSubscriberMethod(Object obj1) {
      synchronized (this) {
         super.invokeSubscriberMethod(obj1);
      }
   }
}
