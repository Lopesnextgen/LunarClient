package com.moonsworth.lunar.genesis;

import java.lang.ref.WeakReference;
import java.util.logging.Level;
import com.google.common.util.concurrent.ServiceManager;
import com.google.common.util.concurrent.Service;

final class MixinHelper122$Data8 extends MixinHelper23$Data11 {
   final Service field1;
   final WeakReference<MixinHelper122$Data7> field2;

   MixinHelper122$Data8(Service var1, WeakReference<MixinHelper122$Data7> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   public void starting() {
      MixinHelper122$Data7 var1 = this.field2.get();
      if (var1 != null) {
         var1.method5(this.field1, MixinHelper23$Type.NEW, MixinHelper23$Type.STARTING);
         if (!(this.field1 instanceof MixinHelper122$Data6)) {
            ServiceManager.access$200().log(Level.FINE, "Starting {0}.", this.field1);
         }
      }
   }

   @Override
   public void running() {
      MixinHelper122$Data7 var1 = this.field2.get();
      if (var1 != null) {
         var1.method5(this.field1, MixinHelper23$Type.STARTING, MixinHelper23$Type.RUNNING);
      }
   }

   @Override
   public void method1(MixinHelper23$Type var1) {
      MixinHelper122$Data7 var2 = this.field2.get();
      if (var2 != null) {
         var2.method5(this.field1, var1, MixinHelper23$Type.STOPPING);
      }
   }

   @Override
   public void method2(MixinHelper23$Type var1) {
      MixinHelper122$Data7 var2 = this.field2.get();
      if (var2 != null) {
         if (!(this.field1 instanceof MixinHelper122$Data6)) {
            ServiceManager.access$200().log(Level.FINE, "Service {0} has terminated. Previous state was: {1}", new Object[]{this.field1, var1});
         }

         var2.method5(this.field1, var1, MixinHelper23$Type.TERMINATED);
      }
   }

   @Override
   public void method3(MixinHelper23$Type var1, Throwable var2) {
      MixinHelper122$Data7 var3 = this.field2.get();
      if (var3 != null) {
         boolean var4 = !(this.field1 instanceof MixinHelper122$Data6);
         var4 &= var1 != MixinHelper23$Type.STARTING;
         if (var4) {
            ServiceManager.access$200().log(Level.SEVERE, "Service " + this.field1 + " has failed in the " + var1 + " state.", var2);
         }

         var3.method5(this.field1, var1, MixinHelper23$Type.FAILED);
      }
   }
}
