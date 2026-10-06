package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.IchorLogger;
import java.util.ServiceLoader;

public abstract class MultiBridge {
   private static MultiBridge instance;

   public MultiBridge() {
   }

   public static MultiBridge getInstance() {
      if (instance == null) {
         IchorPipeline ichor70 = IchorAPI.getPipeline(Client.class).orElseThrow();
         Config config1 = Config.method36(ichor70.method34().method6());
         ClassLoader classloader2 = MultiBridge.class.getClassLoader();

         for (MultiBridge legacy5 : ServiceLoader.load(MultiBridge.class, classloader2)) {
            if (legacy5.getMinecraftVersion().equals(config1)) {
               instance = legacy5;
               break;
            }
         }

         if (instance == null) {
            throw new IllegalStateException("No MultiBridge found for Minecraft version " + config1);
         }

         IchorLogger.field1.info("Creating MultiBridgeLegacy instance: " + instance.getClass().getSimpleName(), new Object[0]);
      }

      return instance;
   }

   public abstract Config getMinecraftVersion();
}
