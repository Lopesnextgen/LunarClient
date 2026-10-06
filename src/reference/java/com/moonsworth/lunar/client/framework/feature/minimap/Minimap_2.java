package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import java.util.List;
import org.joml.Vector2i;

public abstract class Minimap_2<T> {
   protected final com.moonsworth.lunar.client.mod.render.minimap.Minimap field1;
   protected final Minimap field2;
   protected final Bridge5_12 field3;

   protected Minimap_2(com.moonsworth.lunar.client.mod.render.minimap.Minimap var1, Minimap var2, Bridge5_12 var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public abstract List<Minimap2_2<T>> method1(Bridge5Extension_5 var1, float var2, Vector2i var3);
}
