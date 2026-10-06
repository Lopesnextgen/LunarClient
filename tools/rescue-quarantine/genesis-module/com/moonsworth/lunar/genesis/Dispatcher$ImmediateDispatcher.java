package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import com.google.common.base.Preconditions;

final class Dispatcher$ImmediateDispatcher extends MixinHelper4_9 {
   private static final Dispatcher$ImmediateDispatcher field1 = new Dispatcher$ImmediateDispatcher();

   private Dispatcher$ImmediateDispatcher() {
   }

   void dispatch(Object obj1, Iterator<MixinHelper6_6> iterator2) {
      Preconditions.checkNotNull(obj1);

      while (iterator2.hasNext()) {
         ((MixinHelper6_6)iterator2.next()).method2(obj1);
      }
   }
}
