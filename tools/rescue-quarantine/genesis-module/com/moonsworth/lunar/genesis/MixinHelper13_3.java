package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Multimap;
import com.google.common.base.Preconditions;

@Annotation3
public final class MixinHelper13_3 {
   private static final Logger field1 = Logger.getLogger(MixinHelper13_3.class.getName());
   @GuardedBy("this")
   private @Nullable Multimap.MixinHelper13$Data8 field2;
   @GuardedBy("this")
   private boolean executed;

   public void add(Runnable var1, Executor var2) {
      Preconditions.checkNotNull(var1, "Runnable was null.");
      Preconditions.checkNotNull(var2, "Executor was null.");
      synchronized (this) {
         if (!this.executed) {
            this.field2 = new MixinHelper13$Data8(var1, var2, this.field2);
            return;
         }
      }

      executeListener(var1, var2);
   }

   public void execute() {
      MixinHelper13$Data8 var1;
      synchronized (this) {
         if (this.executed) {
            return;
         }

         this.executed = true;
         var1 = this.field2;
         this.field2 = null;
      }

      MixinHelper13$Data8 var2 = null;

      while (var1 != null) {
         MixinHelper13$Data8 var3 = var1;
         var1 = var1.field3;
         var3.field3 = var2;
         var2 = var3;
      }

      while (var2 != null) {
         executeListener(var2.field1, var2.field2);
         var2 = var2.field3;
      }
   }

   private static void executeListener(Runnable var0, Executor var1) {
      try {
         var1.execute(var0);
      } catch (RuntimeException var3) {
         field1.log(Level.SEVERE, "RuntimeException while executing runnable " + var0 + " with executor " + var1, var3);
      }
   }
}
