package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.DummyPlayer;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.IchorLogger;
import java.util.ServiceLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public abstract class AncientDummyPlayer {
   private static AncientDummyPlayer instance;

   public AncientDummyPlayer() {
   }

   public static AncientDummyPlayer getInstance() {
      if (instance == null) {
         IchorPipeline ichor70 = (IchorPipeline)IchorAPI.getPipeline(Client.class).orElseThrow();
         Config config1 = Config.method36(ichor70.method34().method6());
         ClassLoader classloader2 = AncientDummyPlayer.class.getClassLoader();

         for (AncientDummyPlayer wrapper_45 : ServiceLoader.load(AncientDummyPlayer.class, classloader2)) {
            if (wrapper_45.getMinecraftVersion().equals(config1)) {
               instance = wrapper_45;
               break;
            }
         }

         if (instance == null) {
            throw new IllegalStateException("No AncientDummyPlayer found for Minecraft version " + config1);
         }

         IchorLogger.field1.info("Creating AncientDummyPlayer instance: " + instance.getClass().getSimpleName(), new Object[0]);
      }

      return instance;
   }

   public abstract DummyPlayer createDummyPlayer(Minecraft minecraft1, World world2);

   public abstract Config getMinecraftVersion();
}
