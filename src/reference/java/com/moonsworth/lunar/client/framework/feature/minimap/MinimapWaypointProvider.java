package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.waypoints.Waypoint;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.joml.Vector2i;

public class MinimapWaypointProvider extends MinimapMarkerProvider<Waypoint> {
   public MinimapWaypointProvider(com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimap1, MinimapMap minimap2, MinecraftBridge bridge5_123) {
      super(minimap1, minimap2, bridge5_123);
   }

   @Override
   public List<MinimapLayer<Waypoint>> method1(Bridge5Extension_5 bridge5extension_51, float value2, Vector2i vector2i3) {
      return !Ref.method4().method40().method20().isEnabled()
         ? Collections.emptyList()
         : Client.method109()
            .method48()
            .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
            .stream()
            .filter(
               arg2x -> arg2x.isVisible()
                  && arg2x.shouldRender()
                  && MathUtils.method17(arg2x.method35().bridge$xCoord(), arg2x.method35().bridge$zCoord(), vector2i3.x, vector2i3.y) <= value2
            )
            .map(
               arg1x -> new WaypointMinimapLayer(
                  this.HIIRICOCOHRIIHRHHHHCHOIOHOOIRI, arg1x.method35().bridge$xCoord(), arg1x.method35().bridge$zCoord(), 9.0F, 9.0F, arg1x
               )
            )
            .collect(Collectors.toList());
   }
}
