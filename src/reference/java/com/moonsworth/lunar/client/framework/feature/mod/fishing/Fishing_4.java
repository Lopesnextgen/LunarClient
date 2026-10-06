package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

public class Fishing_4 {
   @SerializedName("fairySouls")
   private final Map<Gui2Extension3, Set<Vector3i>> field1;

   public Fishing_4() {
      this.field1 = new EnumMap<>(Gui2Extension3.class);
   }

   @NotNull
   public Set<Vector3i> method1(Gui2Extension3 var1) {
      return this.method4().getOrDefault(var1, new HashSet<>());
   }

   public boolean method2(Gui2Extension3 var1, Vector3i var2) {
      Set var3 = this.method1(var1);
      if (var3.add(var2)) {
         this.method4().put(var1, var3);
         return true;
      } else {
         return false;
      }
   }

   public boolean method3(Gui2Extension3 var1, Set<Vector3i> var2) {
      Set var3 = this.method1(var1);
      if (var3.addAll(var2)) {
         this.method4().put(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   @Generated
   public Fishing_4(Map<Gui2Extension3, Set<Vector3i>> var1) {
      this.field1 = var1;
   }

   @Generated
   public Map<Gui2Extension3, Set<Vector3i>> method4() {
      return this.field1;
   }
}
