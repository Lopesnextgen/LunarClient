package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.EntityFXBridge;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.world.World;

public class EntityDiggingFXImpl extends EntityDiggingFX {
   @VersionGate(0)
   public EntityDiggingFXImpl(World world1, double value2, double value4, double value6, double value8, double value10, double value12, Block block14, int number15) {
      super(world1, value2, value4, value6, value8, value10, value12, block14, number15);
      ((EntityFXBridge)this).bridge$setParticleType(ParticleType.BLOOD);
   }

   @VersionGate(min = 1)
   public EntityDiggingFXImpl(World world1, double value2, double value4, double value6, double value8, double value10, double value12, IBlockState state14) {
      super(world1, value2, value4, value6, value8, value10, value12, state14);
      ((EntityFXBridge)this).bridge$setParticleType(ParticleType.BLOOD);
   }
}
