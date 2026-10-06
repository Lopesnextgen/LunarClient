package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class JitEntityResource extends JitResourceBase<com.moonsworth.lunar.client.inactive.mixin.nameplate.Nameplate2> {
   private final com.moonsworth.lunar.client.inactive.mixin.nameplate.Nameplate field7;

   public JitEntityResource(com.moonsworth.lunar.client.inactive.mixin.nameplate.Nameplate var1, JitAssetKey var2) {
      super(var2, JitResource.Data6.method1());
      this.field7 = var1;
   }

   protected com.moonsworth.lunar.client.inactive.mixin.nameplate.Nameplate2 method7() {
      Bridge11_2 var1 = ThreadModuleDump63.method3().bridge$getResourceManager();
      return this.field7.method1(this.field3.method3(), var1);
   }
}
