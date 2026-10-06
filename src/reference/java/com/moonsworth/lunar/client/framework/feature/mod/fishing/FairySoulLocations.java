package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

public class FairySoulLocations {
   @SerializedName("fairySouls")
   private final Map<SkyblockIsland, Set<Vector3i>> field1;

   public FairySoulLocations() {
      this.field1 = new EnumMap<>(SkyblockIsland.class);
   }

   @NotNull
   public Set<Vector3i> method1(SkyblockIsland gui2extension31) {
      return this.method4().getOrDefault(gui2extension31, new HashSet<>());
   }

   public boolean method2(SkyblockIsland gui2extension31, Vector3i vector3i2) {
      Set set3 = this.method1(gui2extension31);
      if (set3.add(vector3i2)) {
         this.method4().put(gui2extension31, set3);
         return true;
      } else {
         return false;
      }
   }

   public boolean method3(SkyblockIsland gui2extension31, Set<Vector3i> set) {
      Set set3 = this.method1(gui2extension31);
      if (set3.addAll(set)) {
         this.method4().put(gui2extension31, set);
         return true;
      } else {
         return false;
      }
   }

   @Generated
   public FairySoulLocations(Map<SkyblockIsland, Set<Vector3i>> map) {
      this.field1 = map;
   }

   @Generated
   public Map<SkyblockIsland, Set<Vector3i>> method4() {
      return this.field1;
   }
}
