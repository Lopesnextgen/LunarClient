package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.BridgeExtension;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

class IterableEntityTracker extends ParentTHandler<BridgeExtension, BridgeExtension> implements EntityChangeListener<BridgeExtension> {
   IterableEntityTracker(final Iterable<BridgeExtension> list1) {
      super(new IterableExtension<BridgeExtension>() {
         public void method1() {
         }

         public void method3() {
         }

         @NotNull
         @Override
         public Iterator<BridgeExtension> iterator() {
            return list1.iterator();
         }
      });
   }

   @Override
   public void method1(BridgeExtension bridgeextension1) {
      if (this.isEnabled()) {
         super.method5(bridgeextension1);
      }
   }

   @Override
   public void method2(BridgeExtension bridgeextension1) {
      if (this.isEnabled()) {
         super.method6(bridgeextension1);
      }
   }

   @Override
   public void method3(BridgeExtension bridgeextension1) {
      if (this.isEnabled()) {
         super.method7(bridgeextension1);
      }
   }
}
