package com.moonsworth.lunar.genesis;

import java.lang.reflect.Method;
import com.google.common.eventbus.EventBus;
import com.google.common.base.Preconditions;

public class MixinHelper7_6 {
   private final EventBus field1;
   private final Object field2;
   private final Object field3;
   private final Method field4;

   MixinHelper7_6(EventBus var1, Object var2, Object var3, Method var4) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
      this.field3 = Preconditions.checkNotNull(var3);
      this.field4 = Preconditions.checkNotNull(var4);
   }

   public EventBus method1() {
      return this.field1;
   }

   public Object getEvent() {
      return this.field2;
   }

   public Object getSubscriber() {
      return this.field3;
   }

   public Method getSubscriberMethod() {
      return this.field4;
   }
}
