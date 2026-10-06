package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import java.util.ServiceLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public abstract class AncientDummyPlayerFactory {
   private static AncientDummyPlayerFactory instance;

   public static AncientDummyPlayerFactory getInstance() {
      if (instance == null) {
         IchorPipeline var0 = (IchorPipeline)IchorAPI.getPipeline(Client.class).orElseThrow();
         Config var1 = Config.method36(var0.method34().method6());
         ClassLoader var2 = AncientDummyPlayerFactory.class.getClassLoader();

         for (AncientDummyPlayerFactory var5 : ServiceLoader.load(AncientDummyPlayerFactory.class, var2)) {
            if (var5.getMinecraftVersion().equals(var1)) {
               instance = var5;
               break;
            }
         }

         if (instance == null) {
            throw new IllegalStateException("No AncientDummyPlayer found for Minecraft version " + var1);
         }

         FatalIchorError5.field1.info("Creating AncientDummyPlayer instance: " + instance.getClass().getSimpleName(), new Object[0]);
      }

      return instance;
   }

   public abstract ThreadModuleDump54 createDummyPlayer(Minecraft var1, World var2);

   public abstract Config getMinecraftVersion();
}
