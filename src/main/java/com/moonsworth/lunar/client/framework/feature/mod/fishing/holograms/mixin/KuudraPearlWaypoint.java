package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.mixin;

import java.util.List;
import lombok.Generated;
import org.joml.Vector3i;

public class KuudraPearlWaypoint {
   private final Vector3i field1;
   private final List<Vector3i> field2;
   private final List<Vector3i> field3;

   @Generated
   public KuudraPearlWaypoint(Vector3i vector3i1, List<Vector3i> list, List<Vector3i> list2) {
      this.field1 = vector3i1;
      this.field2 = list;
      this.field3 = list2;
   }

   @Generated
   public Vector3i method1() {
      return this.field1;
   }

   @Generated
   public List<Vector3i> method2() {
      return this.field2;
   }

   @Generated
   public List<Vector3i> method3() {
      return this.field3;
   }
}
