package com.moonsworth.lunar.client.framework.feature.mod.fishing.chest;

import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.joml.Vector3d;

public class NpcLocations {
   private Map<String, List<Vector3d>> locations;

   public List<Vector3d> getLocations(String text) {
      return this.locations.get(text);
   }

   @Generated
   public NpcLocations(Map<String, List<Vector3d>> map) {
      this.locations = map;
   }
}
