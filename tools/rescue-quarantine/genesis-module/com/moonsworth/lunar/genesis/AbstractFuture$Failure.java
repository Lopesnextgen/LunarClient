package com.moonsworth.lunar.genesis;
import com.google.common.base.Preconditions;

final class AbstractFuture$Failure {
   static final AbstractFuture$Failure field1 = new AbstractFuture$Failure(new Data4$1("Failure occurred while trying to finish a future."));
   final Throwable field2;

   AbstractFuture$Failure(Throwable exception1) {
      this.field2 = (Throwable)Preconditions.checkNotNull(exception1);
   }
}
