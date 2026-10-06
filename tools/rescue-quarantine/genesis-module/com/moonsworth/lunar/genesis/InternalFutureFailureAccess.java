package com.moonsworth.lunar.genesis;

public abstract class InternalFutureFailureAccess {
   protected InternalFutureFailureAccess() {
   }

   protected abstract Throwable tryInternalFastPathGetFailure();
}
