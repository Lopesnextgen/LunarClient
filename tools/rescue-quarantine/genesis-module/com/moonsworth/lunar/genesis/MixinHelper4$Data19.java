package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Queue;
import com.google.common.collect.Queues;
import com.google.common.base.Preconditions;

final class MixinHelper4$Data19 extends MixinHelper4_9 {
   private final ThreadLocal<Queue<MixinHelper4$Data19.Data>> field1 = new ThreadLocal<Queue<MixinHelper4$Data19.Data>>() {
      protected Queue<MixinHelper4$Data19.Data> initialValue() {
         return Queues.newArrayDeque();
      }
   };
   private final ThreadLocal<Boolean> field2 = new ThreadLocal<Boolean>() {
      protected Boolean initialValue() {
         return false;
      }
   };

   private MixinHelper4$Data19() {
   }

   @Override
   void dispatch(Object var1, Iterator<MixinHelper6_6> var2) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var2);
      Queue var3 = this.field1.get();
      var3.offer(new MixinHelper4$Data19.Data(var1, var2));
      if (!this.field2.get()) {
         this.field2.set(true);

         MixinHelper4$Data19.Data var4;
         try {
            while ((var4 = (MixinHelper4$Data19.Data)var3.poll()) != null) {
               while (var4.field2.hasNext()) {
                  var4.field2.next().method2(var4.field1);
               }
            }
         } finally {
            this.field2.remove();
            this.field1.remove();
         }
      }
   }

   private static final class Data {
      private final Object field1;
      private final Iterator<MixinHelper6_6> field2;

      private Data(Object var1, Iterator<MixinHelper6_6> var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }
}
