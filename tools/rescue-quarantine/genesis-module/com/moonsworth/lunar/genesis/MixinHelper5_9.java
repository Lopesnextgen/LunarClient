package com.moonsworth.lunar.genesis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import com.google.common.base.Preconditions;

@Annotation3
final class MixinHelper5_9<L> {
   private static final Logger field1 = Logger.getLogger(MixinHelper5_9.class.getName());
   private final List<MixinHelper5$Data22<L>> field2 = Collections.synchronizedList(new ArrayList<>());

   public void addListener(L var1, Executor var2) {
      Preconditions.checkNotNull(var1, "listener");
      Preconditions.checkNotNull(var2, "executor");
      this.field2.add(new MixinHelper5$Data22<>((L)var1, var2));
   }

   public void method1(MixinHelper5$Extension<L> var1) {
      this.method3(var1, var1);
   }

   public void method2(MixinHelper5$Extension<L> var1, String var2) {
      this.method3(var1, var2);
   }

   private void method3(MixinHelper5$Extension<L> var1, Object var2) {
      Preconditions.checkNotNull(var1, "event");
      Preconditions.checkNotNull(var2, "label");
      synchronized (this.field2) {
         for (MixinHelper5$Data22 var5 : this.field2) {
            var5.method1(var1, var2);
         }
      }
   }

   public void dispatch() {
      for (int var1 = 0; var1 < this.field2.size(); var1++) {
         this.field2.get(var1).dispatch();
      }
   }
}
