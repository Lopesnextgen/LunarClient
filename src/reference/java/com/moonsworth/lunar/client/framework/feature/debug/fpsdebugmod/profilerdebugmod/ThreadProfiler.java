package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.Profile;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;

public class ThreadProfiler {
   private final Thread thread;
   private final List<Profile> field1 = new ArrayList<>();
   protected volatile Profile field2 = null;
   private long field3 = 0L;
   private volatile boolean field4;

   public ThreadProfiler(Thread thread2) {
      this.thread = thread2;
   }

   public void method1() {
      if (!this.field4) {
         Profile profilerdebugmod21 = this.field2;
         if (profilerdebugmod21 != null) {
            StackTraceElement[] items2 = this.thread.getStackTrace();
            profilerdebugmod21.method2(items2);
         }
      }
   }

   public void method2(com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.ProfilerEngine.Data data) {
      if (!this.field4) {
         Profile profilerdebugmod22 = this.field2;
         if (profilerdebugmod22 != null) {
            profilerdebugmod22.method6(data);
         }
      }
   }

   public void method3() {
      if (!this.field4) {
         if (Ref.method7() == null) {
            this.field2 = null;
         } else {
            Profile profilerdebugmod21 = this.field2;
            this.field2 = new Profile();
            long number2 = this.field3;
            long number4 = System.nanoTime();
            if (profilerdebugmod21 != null) {
               profilerdebugmod21.method1(number4 - number2);
               profilerdebugmod21.method8(Ref.method11() == null);
               this.field1.add(profilerdebugmod21);
            }

            this.field3 = number4;
         }
      }
   }

   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   public List<Profile> method4() {
      return this.field1;
   }

   public void stop() {
      this.field4 = true;
   }
}
