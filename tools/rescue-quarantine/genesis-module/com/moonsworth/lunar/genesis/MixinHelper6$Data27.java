package com.moonsworth.lunar.genesis;

import java.lang.reflect.Method;
import com.google.common.eventbus.EventBus;

@Annotation4
final class MixinHelper6$Data27 extends MixinHelper6_6 {
   private MixinHelper6$Data27(EventBus var1, Object var2, Method var3) {
      super(var1, var2, var3);
   }

   @Override
   void invokeSubscriberMethod(Object var1) {
      synchronized (this) {
         super.invokeSubscriberMethod(var1);
      }
   }
}
