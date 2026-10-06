package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class MixinHelper23 extends MixinHelper2 implements Serializable {
   private static final long field56 = 1L;
   public static final String SERIALIZED_NAME_LAYER = "layer";
   @SerializedName("layer")
   private MixinHelper63 layer;
   public static HashSet<String> field59 = new HashSet<>();
   public static HashSet<String> field60 = new HashSet<>();

   public MixinHelper23 method1(MixinHelper63 var1) {
      this.layer = var1;
      return this;
   }

   @Nonnull
   public MixinHelper63 method3() {
      return this.layer;
   }

   public void method3(MixinHelper63 var1) {
      this.layer = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MixinHelper23 var2 = (MixinHelper23)var1;
         return Objects.equals(this.layer, var2.layer) && super.equals(var1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.layer, super.hashCode());
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GameRewindLayerAddEventData {\n");
      var1.append("    ").append(this.toIndentedString(super.toString())).append("\n");
      var1.append("    layer: ").append(this.toIndentedString(this.layer)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field60.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GameRewindLayerAddEventData is not found in the empty JSON string", field60.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field59.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GameRewindLayerAddEventData` properties. JSON: %s", var3.getKey(), var0.toString()
               )
            );
         }
      }

      for (String var6 : field60) {
         if (var0.getAsJsonObject().get(var6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var6, var0.toString()));
         }
      }

      JsonObject var5 = var0.getAsJsonObject();
      MixinHelper63.validateJsonElement(var5.get("layer"));
   }

   public static MixinHelper23 method8(String var0) {
      return (MixinHelper23)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, MixinHelper23.class);
   }

   @Override
   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field59.add("player_uuid");
      field59.add("installation_id");
      field59.add("overwolf_muid");
      field59.add("timestamp");
      field59.add("event_id");
      field59.add("assetserver_session_id");
      field59.add("launch_id");
      field59.add("inbound_location");
      field59.add("location");
      field59.add("minecraft_version");
      field59.add("lunar_client_git_commit");
      field59.add("lunar_client_git_branch");
      field59.add("lunar_client_semver");
      field59.add("lunar_client_ui_git_commit");
      field59.add("lunar_client_ui_git_branch");
      field59.add("operating_system");
      field59.add("operating_system_release");
      field59.add("cpu_architecture");
      field59.add("launcher_version");
      field59.add("canary_token");
      field59.add("ichor_modules");
      field59.add("gl_extensions");
      field59.add("installed_mods");
      field59.add("wearer_uuid");
      field59.add("wearer_cosmetic_ids");
      field59.add("wearer_outfit_id");
      field59.add("trigger");
      field59.add("geo_location");
      field59.add("layer");
      field60.add("layer");
      field60.add("installation_id");
      field60.add("installed_mods");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!MixinHelper23.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(MixinHelper23.class));
         return (new TypeAdapter<MixinHelper23>() {
            public void method1(JsonWriter var1, MixinHelper23 var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public MixinHelper23 method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (MixinHelper23)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }
}
