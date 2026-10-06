package com.moonsworth.lunar.client.replay.replaymod.forge;

import com.moonsworth.lunar.forge.Ichor5Iterator;
import com.moonsworth.lunar.ichor.IchorTransformer;

public class Ichor5Loader2 extends com.moonsworth.lunar.forge.ModMixinLoader {
   public static final String field6 = "com.replaymod.core.ReplayModBackend";

   public Ichor5Loader2(Ichor5Iterator ichor5iterator1) {
      super(ichor5iterator1, "replaymod", "lunar", "com/replaymod/", "com.replaymod.core.ReplayModBackend");
   }

   public void method3(IchorTransformer autocloseableiterator21) {
      Ichor5Loader.registerMixins(this.OORHIHOOORHHIRORRICRRORCHRRIIC);
   }
}
