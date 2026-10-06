package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import java.util.ServiceLoader;

public abstract class Legacy {
   private static Legacy instance;

   public static Legacy getInstance() {
      if (instance == null) {
         IchorPipeline var0 = (IchorPipeline)IchorAPI.getPipeline(Client.class).orElseThrow();
         Config var1 = Config.method36(var0.method34().method6());
         ClassLoader var2 = Legacy.class.getClassLoader();

         for (Legacy var5 : ServiceLoader.load(Legacy.class, var2)) {
            if (var5.getMinecraftVersion().equals(var1)) {
               instance = var5;
               break;
            }
         }

         if (instance == null) {
            throw new IllegalStateException("No MultiBridge found for Minecraft version " + var1);
         }

         FatalIchorError5.field1.info("Creating MultiBridgeLegacy instance: " + instance.getClass().getSimpleName(), new Object[0]);
      }

      return instance;
   }

   public abstract Config getMinecraftVersion();
}
