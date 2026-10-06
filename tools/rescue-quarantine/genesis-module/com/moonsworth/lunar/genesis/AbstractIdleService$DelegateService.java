package com.moonsworth.lunar.genesis;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.MoreExecutors;

final class AbstractIdleService$DelegateService extends AbstractService {
   private AbstractIdleService$DelegateService(MixinHelper233 mixinhelper2331) {
      this.field16 = mixinhelper2331;
   }

   protected final void doStart() {
      MoreExecutors.method7(this.field16.executor(), MixinHelper233.method5(this.field16)).execute(new Data2$1(this));
   }

   protected final void doStop() {
      MoreExecutors.method7(this.field16.executor(), MixinHelper233.method5(this.field16)).execute(new Data2$2(this));
   }

   public String toString() {
      return this.field16.toString();
   }
}
