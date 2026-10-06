package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.client.mod.render.minimap.MinimapMod.EntityMarkerType;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.joml.Vector2i;

public class MinimapPlayerProvider extends MinimapMarkerProvider<EntityLivingBridge> {
   public MinimapPlayerProvider(com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimap1, MinimapMap minimap2, MinecraftBridge bridge5_123) {
      super(minimap1, minimap2, bridge5_123);
   }

   @Override
   public List<MinimapLayer<EntityLivingBridge>> method1(Bridge5Extension_5 bridge5extension_51, float value2, Vector2i vector2i3) {
      EntityMarkerType type24 = (EntityMarkerType)this.HIIRICOCOHRIIHRHHHHCHOIOHOOIRI.method24().get();
      boolean flag5 = type24 != EntityMarkerType.NONE && this.HIIRICOCOHRIIHRHHHHCHOIOHOOIRI.method19();
      return !flag5
         ? Collections.emptyList()
         : this.IOOCCHHCCHHRRRIOIRHOHHOIHROIOO
            .bridge$getWorld()
            .bridge$getEntities()
            .stream()
            .filter(
               arg3x -> arg3x != bridge5extension_51
                  && !arg3x.bridge$isInvisibleTo(bridge5extension_51)
                  && !arg3x.bridge$isRemoved()
                  && arg3x.method2() > 2
                  && arg3x instanceof EntityLivingBridge
                  && !(arg3x instanceof EntityArmorStandBridge)
                  && arg3x.method15(vector2i3.x, arg3x.bridge$getPosY(), vector2i3.y) <= value2
                  && Math.abs(arg3x.bridge$getPosY() - bridge5extension_51.bridge$getPosY()) < 20.0
            )
            .map(
               arg1x -> new EntityMinimapLayer(
                  this.HIIRICOCOHRIIHRHHHHCHOIOHOOIRI,
                  arg1x.bridge$getPosX(),
                  arg1x.bridge$getPosZ(),
                  (Float)this.HIIRICOCOHRIIHRHHHHCHOIOHOOIRI.method26().get(),
                  (Float)this.HIIRICOCOHRIIHRHHHHCHOIOHOOIRI.method26().get(),
                  (EntityLivingBridge)arg1x
               )
            )
            .collect(Collectors.toList());
   }
}
