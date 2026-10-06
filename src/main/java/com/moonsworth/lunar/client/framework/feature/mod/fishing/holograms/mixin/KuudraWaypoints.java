package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.mixin;

import java.util.HashMap;
import java.util.List;
import lombok.Generated;
import org.joml.Vector3i;

public class KuudraWaypoints {
   private final HashMap<String, KuudraPearlWaypoint> field1;
   private final List<Vector3i> field2;

   @Generated
   public KuudraWaypoints(HashMap<String, KuudraPearlWaypoint> map, List<Vector3i> list) {
      this.field1 = map;
      this.field2 = list;
   }

   @Generated
   public HashMap<String, KuudraPearlWaypoint> method1() {
      return this.field1;
   }

   @Generated
   public List<Vector3i> method2() {
      return this.field2;
   }
}
