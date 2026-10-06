package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.BridgeExtension;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

class ParentTImpl3 extends ParentTHandler<BridgeExtension, BridgeExtension> implements Holograms<BridgeExtension> {
   ParentTImpl3(final Iterable<BridgeExtension> var1) {
      super(new IterableExtension<BridgeExtension>() {
         public void method1() {
         }

         public void method3() {
         }

         @NotNull
         @Override
         public Iterator<BridgeExtension> iterator() {
            return var1.iterator();
         }
      });
   }

   @Override
   public void method1(BridgeExtension var1) {
      if (this.isEnabled()) {
         super.method5(var1);
      }
   }

   @Override
   public void method2(BridgeExtension var1) {
      if (this.isEnabled()) {
         super.method6(var1);
      }
   }

   @Override
   public void method3(BridgeExtension var1) {
      if (this.isEnabled()) {
         super.method7(var1);
      }
   }
}
