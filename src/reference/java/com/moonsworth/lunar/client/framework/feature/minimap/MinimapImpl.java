package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.client.framework.feature.markers.Markers2;
import com.moonsworth.lunar.client.mod.render.markers.Markers;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.joml.Vector2i;
import org.joml.Vector3d;

public class MinimapImpl extends MinimapMarkerProvider<Markers2> {
   public MinimapImpl(com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimapMod, MinimapMap map, MinecraftBridge bridge5_123) {
      super(minimapMod, map, bridge5_123);
   }

   @Override
   public List<MinimapLayer<Markers2>> method1(Bridge5Extension_5 bridge5extension_51, float value, Vector2i vector2i3) {
      Markers markers4 = Ref.method4().method40().method87();
      return !markers4.isEnabled() ? Collections.emptyList() : markers4.getMarkerManager().method10().stream().filter(arg2x -> {
         Vector3d vector3d3x = arg2x.getPos();
         double value4x = vector3d3x.x - vector2i3.x;
         double value6 = vector3d3x.z - vector2i3.y;
         return value4x * value4x + value6 * value6 <= value;
      }).map(arg1x -> new PlayerMinimapLayer(this.field1, arg1x.getPos().x, arg1x.getPos().z, 8.0F, 8.0F, arg1x)).collect(Collectors.toList());
   }
}
