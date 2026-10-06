package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;

final class AbstractFuture$Cancellation {
   static final AbstractFuture$Cancellation field1;
   static final AbstractFuture$Cancellation field2;
   final boolean field3;
   final @Nullable Throwable field4;

   AbstractFuture$Cancellation(boolean flag1, @Nullable Throwable exception2) {
      this.field3 = flag1;
      this.field4 = exception2;
   }

   static {
      if (MixinHelper292.access$300()) {
         field2 = null;
         field1 = null;
      } else {
         field2 = new AbstractFuture$Cancellation(false, null);
         field1 = new AbstractFuture$Cancellation(true, null);
      }
   }
}
