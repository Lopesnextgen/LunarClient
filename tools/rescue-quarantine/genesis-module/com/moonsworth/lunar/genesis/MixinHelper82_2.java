package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import com.google.common.eventbus.EventBus;

@Annotation2
public class MixinHelper82_2 extends EventBus {
   public MixinHelper82_2(String var1, Executor var2) {
      super(var1, var2, MixinHelper4_9.method2(), MixinHelper8$Data27.field1);
   }

   public MixinHelper82_2(Executor var1, MixinHelper3_4 var2) {
      super("default", var1, MixinHelper4_9.method2(), var2);
   }

   public MixinHelper82_2(Executor var1) {
      super("default", var1, MixinHelper4_9.method2(), MixinHelper8$Data27.field1);
   }
}
