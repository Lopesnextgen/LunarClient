package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.google.common.collect.Queues;
import com.google.common.base.Preconditions;

final class MixinHelper4$Data17 extends MixinHelper4_9 {
   private final ConcurrentLinkedQueue<MixinHelper4$Data17.Data> field1 = Queues.newConcurrentLinkedQueue();

   private MixinHelper4$Data17() {
   }

   @Override
   void dispatch(Object var1, Iterator<MixinHelper6_6> var2) {
      Preconditions.checkNotNull(var1);

      while (var2.hasNext()) {
         this.field1.add(new MixinHelper4$Data17.Data(var1, (MixinHelper6_6)var2.next()));
      }

      MixinHelper4$Data17.Data var3;
      while ((var3 = this.field1.poll()) != null) {
         var3.field2.method2(var3.field1);
      }
   }

   private static final class Data {
      private final Object field1;
      private final MixinHelper6_6 field2;

      private Data(Object var1, MixinHelper6_6 var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }
}
