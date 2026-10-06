package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.client.cosmetics.gecko.BoneList;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.ints.IntObjectPair;

public class JitItemResource extends JitResourceBase<BoneList> {
   public JitItemResource(JitAssetKey var1) {
      super(var1, JitResource.Data6.method1());
   }

   protected BoneList method7() {
      Bridge11_2 var1 = ThreadModuleDump63.method3().bridge$getResourceManager();
      IntObjectPair var2 = com.moonsworth.lunar.client.inactive.mixin.nameplate.Nameplate3.method1(var1, this.field3.method3());
      return var2 != null ? (BoneList)var2.value() : null;
   }
}
