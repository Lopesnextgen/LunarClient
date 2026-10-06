package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge8_6;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.function.Function;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityFactoryBridgeImpl implements Bridge8_6 {
   private final Function<World, Entity> field1;

   public EntityFactoryBridgeImpl(Function<World, Entity> function1) {
      this.field1 = function1;
   }

   public BridgeExtension bridge$create(Itemcounter6 itemcounter61) {
      return (BridgeExtension)this.field1.apply((World)itemcounter61);
   }
}
