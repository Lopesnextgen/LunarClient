package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump77;
import java.util.concurrent.CompletableFuture;

public abstract class JitResourceBase<T> extends JitResource<T> {
   public JitResourceBase(JitAssetKey nameplate3, JitResource.Data6 data) {
      super(nameplate3, data);
   }

   @Override
   protected final CompletableFuture<T> method8() {
      return CompletableFuture.supplyAsync(ThreadModuleDump77.supplier(this::method4), ThreadModuleDump37.method6());
   }

   protected abstract T method4();
}
