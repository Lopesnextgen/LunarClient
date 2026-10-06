package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;

class Nameplate2$Data4 {
   private final RewindHandlers field1;
   private final RewindIterator<?> field2;
   private final RewindHandlers3Impl2 field3;
   private final String field4;

   Nameplate2$Data4(RewindHandlers handler, RewindIterator<?> iterator, RewindHandlers3Impl2 handler2, String text) {
      this.field1 = handler;
      this.field2 = iterator;
      this.field3 = handler2;
      this.field4 = text;
   }

   public RewindHandlers method1() {
      return this.field1;
   }

   public RewindIterator<?> method2() {
      return this.field2;
   }

   public RewindHandlers3Impl2 method3() {
      return this.field3;
   }

   public String method4() {
      return this.field4;
   }
}
