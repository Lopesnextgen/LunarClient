package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.ReflectionSupport;
import com.google.j2objc.annotations.ReflectionSupport.Level;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Logger;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.collect.Sets;

@GwtCompatible(emulated = true)
@ReflectionSupport(Level.FULL)
abstract class MixinHelper30_2<OutputT> extends AbstractFuture.Data5<OutputT> {
   private volatile Set<Throwable> seenExceptions = null;
   private volatile int remaining;
   private static final MixinHelper30$Data5 field8;
   private static final Logger field9 = Logger.getLogger(MixinHelper30_2.class.getName());

   MixinHelper30_2(int var1) {
      this.remaining = var1;
   }

   final Set<Throwable> method1() {
      Set var1 = this.seenExceptions;
      if (var1 == null) {
         var1 = Sets.newConcurrentHashSet();
         this.addInitialException(var1);
         field8.method1(this, null, var1);
         var1 = this.seenExceptions;
      }

      return var1;
   }

   abstract void addInitialException(Set<Throwable> var1);

   final int method3() {
      return field8.method2(this);
   }

   final void method4() {
      this.seenExceptions = null;
   }

   static {
      Throwable var1 = null;

      MixinHelper30$Data5 var0;
      try {
         var0 = new MixinHelper30$Data4(
            AtomicReferenceFieldUpdater.newUpdater(MixinHelper30_2.class, Set.class, "seenExceptions"),
            AtomicIntegerFieldUpdater.newUpdater(MixinHelper30_2.class, "remaining")
         );
      } catch (Throwable var3) {
         var1 = var3;
         var0 = new MixinHelper30$Data6();
      }

      field8 = var0;
      if (var1 != null) {
         field9.log(java.util.logging.Level.SEVERE, "SafeAtomicHelper is broken!", var1);
      }
   }
}
