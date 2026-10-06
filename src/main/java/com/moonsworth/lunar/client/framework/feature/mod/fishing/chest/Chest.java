package com.moonsworth.lunar.client.framework.feature.mod.fishing.chest;

import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.joml.Vector3d;

public class Chest {
   private Map<String, List<Vector3d>> locations;

   public List<Vector3d> getLocations(String var1) {
      return this.locations.get(var1);
   }

   @Generated
   public Chest(Map<String, List<Vector3d>> var1) {
      this.locations = var1;
   }
}
