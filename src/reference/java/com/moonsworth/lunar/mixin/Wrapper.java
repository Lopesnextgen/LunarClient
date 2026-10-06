package com.moonsworth.lunar.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.legacy.wrapper.AncientDummyPlayerFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class Wrapper extends AncientDummyPlayerFactory {
   @Override
   public ThreadModuleDump54 createDummyPlayer(Minecraft minecraft, World world) {
      return new EntityClientPlayerMPImpl(minecraft, world);
   }

   @Override
   public Config getMinecraftVersion() {
      return Config.field1;
   }
}
