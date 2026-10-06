package com.moonsworth.lunar.mixin;

import com.moonsworth.lunar.client.cosmetics.DummyPlayer;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.legacy.wrapper.AncientDummyPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class DummyPlayerFactoryV1_7 extends AncientDummyPlayer {
   public DummyPlayerFactoryV1_7() {
   }

   public DummyPlayer createDummyPlayer(Minecraft minecraft1, World world2) {
      return new EntityClientPlayerMPImpl(minecraft1, world2);
   }

   public Config getMinecraftVersion() {
      return Config.field1;
   }
}
