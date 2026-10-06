package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.joml.Vector2i;

public class MinimapImpl3 extends Minimap_2<GuiHandler2> {
   public MinimapImpl3(com.moonsworth.lunar.client.mod.render.minimap.Minimap var1, Minimap var2, Bridge5_12 var3) {
      super(var1, var2, var3);
   }

   @Override
   public List<Minimap2_2<GuiHandler2>> method1(Bridge5Extension_5 var1, float var2, Vector2i var3) {
      return !ThreadModuleDump63.method4().method40().method20().isEnabled()
         ? Collections.emptyList()
         : Client.method109()
            .method48()
            .IIORHHIRHIORHRCCCOICCRCHRRCCRH()
            .stream()
            .filter(
               var2x -> var2x.isVisible()
                  && var2x.shouldRender()
                  && ThreadModuleDump67.method17(var2x.method35().bridge$xCoord(), var2x.method35().bridge$zCoord(), var3.x, var3.y) <= var2
            )
            .map(
               var1x -> new Minimap2Impl3(
                  this.field1, var1x.method35().bridge$xCoord(), var1x.method35().bridge$zCoord(), 9.0F, 9.0F, var1x
               )
            )
            .collect(Collectors.toList());
   }
}
