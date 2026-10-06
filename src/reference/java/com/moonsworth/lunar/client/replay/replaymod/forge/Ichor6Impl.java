package com.moonsworth.lunar.client.replay.replaymod.forge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.forge.Ichor5Iterator;
import com.moonsworth.lunar.ichor.IchorLoader;
import com.moonsworth.lunar.ichor.IchorModule;
import com.moonsworth.lunar.ichor.IchorPipeline;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ichor6Impl extends IchorModule {
   public static final String field1 = "replaymod";

   public Ichor6Impl() {
      super("replaymod");
   }

   public List<IchorLoader> method1(IchorPipeline ichor71) {
      if (Config.method36(ichor71.method34().method6()).method19()) {
         return List.of();
      }

      ArrayList list2 = new ArrayList();
      Ichor5Iterator ichor5iterator3 = (Ichor5Iterator)ichor71.method19(Ichor5Iterator.class).orElseThrow();
      list2.add(new Ichor5Loader());
      list2.add(new Ichor5Loader2(ichor5iterator3));
      return list2;
   }

   public Map<String, String> method2(IchorPipeline ichor71) {
      Config config2 = Config.method36(ichor71.method34().method6());
      return Map.of("replaymod", "ReplayMod-" + config2.getId());
   }
}
