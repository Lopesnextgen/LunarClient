package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.mixin;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.Profilerdebugmod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;

public class Profilerdebugmod {
   private final Thread thread;
   private final List<Profilerdebugmod2> field1 = new ArrayList<>();
   protected volatile Profilerdebugmod2 field2 = null;
   private long field3 = 0L;
   private volatile boolean field4;

   public Profilerdebugmod(Thread var1) {
      this.thread = var1;
   }

   public void method1() {
      if (!this.field4) {
         Profilerdebugmod2 var1 = this.field2;
         if (var1 != null) {
            StackTraceElement[] var2 = this.thread.getStackTrace();
            var1.method2(var2);
         }
      }
   }

   public void method2(com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.Profilerdebugmod.Data var1) {
      if (!this.field4) {
         Profilerdebugmod2 var2 = this.field2;
         if (var2 != null) {
            var2.method6(var1);
         }
      }
   }

   public void method3() {
      if (!this.field4) {
         if (ThreadModuleDump63.method7() == null) {
            this.field2 = null;
         } else {
            Profilerdebugmod2 var1 = this.field2;
            this.field2 = new Profilerdebugmod2();
            long var2 = this.field3;
            long var4 = System.nanoTime();
            if (var1 != null) {
               var1.method1(var4 - var2);
               var1.method8(ThreadModuleDump63.method11() == null);
               this.field1.add(var1);
            }

            this.field3 = var4;
         }
      }
   }

   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   public List<Profilerdebugmod2> method4() {
      return this.field1;
   }

   public void stop() {
      this.field4 = true;
   }
}
