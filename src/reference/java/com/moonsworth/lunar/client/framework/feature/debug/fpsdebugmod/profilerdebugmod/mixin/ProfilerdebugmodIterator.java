package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.mixin;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod.Profilerdebugmod2;
import java.util.Map;
import java.util.Map.Entry;

public class ProfilerdebugmodIterator extends Profilerdebugmod {
   public ProfilerdebugmodIterator() {
      super(null);
   }

   @Override
   public void method1() {
      Profilerdebugmod2 var1 = this.field2;
      if (var1 != null) {
         Map var2 = Thread.getAllStackTraces();

         for (Entry var4 : var2.entrySet()) {
            var1.method3(((Thread)var4.getKey()).getName(), (StackTraceElement[])var4.getValue());
         }
      }
   }
}
