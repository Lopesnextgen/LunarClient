package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.client.mod.render.minimap.Minimap.Type2;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.joml.Vector2i;

public class MinimapImpl2 extends Minimap_2<BridgeExtension2_5> {
   public MinimapImpl2(com.moonsworth.lunar.client.mod.render.minimap.Minimap var1, Minimap var2, Bridge5_12 var3) {
      super(var1, var2, var3);
   }

   @Override
   public List<Minimap2_2<BridgeExtension2_5>> method1(Bridge5Extension_5 var1, float var2, Vector2i var3) {
      Type2 var4 = (Type2)this.field1.method24().get();
      boolean var5 = var4 != Type2.NONE && this.field1.method19();
      return !var5
         ? Collections.emptyList()
         : this.field3
            .bridge$getWorld()
            .bridge$getEntities()
            .stream()
            .filter(
               var3x -> var3x != var1
                  && !var3x.bridge$isInvisibleTo(var1)
                  && !var3x.bridge$isRemoved()
                  && var3x.method2() > 2
                  && var3x instanceof BridgeExtension2_5
                  && !(var3x instanceof ArmorStandBridge)
                  && var3x.method15(var3.x, var3x.bridge$getPosY(), var3.y) <= var2
                  && Math.abs(var3x.bridge$getPosY() - var1.bridge$getPosY()) < 20.0
            )
            .map(
               var1x -> new Minimap2Impl2(
                  this.field1,
                  var1x.bridge$getPosX(),
                  var1x.bridge$getPosZ(),
                  (Float)this.field1.method26().get(),
                  (Float)this.field1.method26().get(),
                  (BridgeExtension2_5)var1x
               )
            )
            .collect(Collectors.toList());
   }
}
