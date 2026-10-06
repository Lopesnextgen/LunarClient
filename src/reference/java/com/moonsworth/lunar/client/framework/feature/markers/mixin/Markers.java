package com.moonsworth.lunar.client.framework.feature.markers.mixin;

import com.google.gson.annotations.SerializedName;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Markers {
   @SerializedName("teams")
   @NotNull
   private final Set<Gui2Extension> field1;
   @SerializedName("game_version")
   private final int field2;
   @SerializedName("dimension")
   private final int dimension;
   @SerializedName("server")
   @Nullable
   private final String field3;

   public Markers(@NotNull Set<Gui2Extension> var1, int var2, int var3, @Nullable String var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.dimension = var3;
      this.field3 = var4;
   }

   public String method1() {
      return com.moonsworth.lunar.client.framework.feature.markers.Markers2.field1.toJson(this);
   }

   public static Markers method2(String var0) {
      return (Markers)com.moonsworth.lunar.client.framework.feature.markers.Markers2.field1.fromJson(var0, Markers.class);
   }

   @SerializedName("teams")
   @NotNull
   public Set<Gui2Extension> method3() {
      return this.field1;
   }

   @SerializedName("game_version")
   public int method4() {
      return this.field2;
   }

   @SerializedName("server")
   @Nullable
   public String method5() {
      return this.field3;
   }
}
