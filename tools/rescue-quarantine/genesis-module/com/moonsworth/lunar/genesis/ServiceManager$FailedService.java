package com.moonsworth.lunar.genesis;
import com.google.common.util.concurrent.Service;

final class ServiceManager$FailedService extends Throwable {
   ServiceManager$FailedService(Service mixinhelper23_41) {
      super(mixinhelper23_41.toString(), mixinhelper23_41.failureCause(), false, false);
   }
}
