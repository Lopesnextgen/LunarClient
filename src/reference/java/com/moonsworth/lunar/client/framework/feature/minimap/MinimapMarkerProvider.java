package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import java.util.List;
import org.joml.Vector2i;

public abstract class MinimapMarkerProvider<T> {
   protected final com.moonsworth.lunar.client.mod.render.minimap.MinimapMod field1;
   protected final MinimapMap field2;
   protected final MinecraftBridge field3;

   protected MinimapMarkerProvider(com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimapMod, MinimapMap map, MinecraftBridge bridge5_123) {
      this.field1 = minimapMod;
      this.field2 = map;
      this.field3 = bridge5_123;
   }

   public abstract List<MinimapLayer<T>> method1(Bridge5Extension_5 bridge5extension_51, float value2, Vector2i vector2i3);
}
