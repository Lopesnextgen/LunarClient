package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge8_6;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import java.util.function.Function;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Bridge8Handler implements Bridge8_6 {
   private final Function<World, Entity> field1;

   public Bridge8Handler(Function<World, Entity> var1) {
      this.field1 = var1;
   }

   public BridgeExtension bridge$create(Itemcounter6 var1) {
      return (BridgeExtension)this.field1.apply((World)var1);
   }
}
