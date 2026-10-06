package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class JitItemModelResource extends JitResourceBase<ItemTransformsBridge> {
   public JitItemModelResource(JitAssetKey var1) {
      super(var1, JitResource.Data6.method1());
   }

   protected ItemTransformsBridge method7() {
      Bridge11_2 var1 = ThreadModuleDump63.method3().bridge$getResourceManager();
      String var2 = com.moonsworth.lunar.client.inactive.mixin.nameplate.Nameplate.method3(this.field3.method3(), var1);
      return (ItemTransformsBridge)ItemTransformsBridge.field1.fromJson(var2, ItemTransformsBridge.class);
   }
}
