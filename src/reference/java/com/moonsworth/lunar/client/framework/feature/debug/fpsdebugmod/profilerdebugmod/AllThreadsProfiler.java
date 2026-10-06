package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.Profile;
import java.util.Map;
import java.util.Map.Entry;

public class AllThreadsProfiler extends ThreadProfiler {
   public AllThreadsProfiler() {
      super(null);
   }

   @Override
   public void method1() {
      Profile profilerdebugmod21 = this.RORHOHICRROHOIORIHHHOCCCIOCROC;
      if (profilerdebugmod21 != null) {
         Map map2 = Thread.getAllStackTraces();

         for (Entry entry4 : map2.entrySet()) {
            profilerdebugmod21.method3(((Thread)entry4.getKey()).getName(), (StackTraceElement[])entry4.getValue());
         }
      }
   }
}
