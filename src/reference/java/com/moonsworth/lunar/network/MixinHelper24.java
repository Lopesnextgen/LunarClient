package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Map.Entry;
import javax.annotation.Nonnull;

public class GamePromotionInteractionEventData extends MixinHelper2 implements Serializable {
   private static final long field56 = 1L;
   public static final String SERIALIZED_NAME_INTERACTION_TYPE = "interaction_type";
   @SerializedName("interaction_type")
   private GamePromotionInteractionEventData.Type interactionType;
   public static final String SERIALIZED_NAME_PROMOTION_TYPE = "promotion_type";
   @SerializedName("promotion_type")
   private String promotionType;
   public static HashSet<String> field61 = new HashSet<>();
   public static HashSet<String> field62 = new HashSet<>();

   public GamePromotionInteractionEventData method1(GamePromotionInteractionEventData.Type var1) {
      this.interactionType = var1;
      return this;
   }

   @Nonnull
   public GamePromotionInteractionEventData.Type method3() {
      return this.interactionType;
   }

   public void method3(GamePromotionInteractionEventData.Type var1) {
      this.interactionType = var1;
   }

   public GamePromotionInteractionEventData promotionType(String var1) {
      this.promotionType = var1;
      return this;
   }

   @Nonnull
   public String getPromotionType() {
      return this.promotionType;
   }

   public void setPromotionType(String var1) {
      this.promotionType = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         GamePromotionInteractionEventData var2 = (GamePromotionInteractionEventData)var1;
         return Objects.equals(this.interactionType, var2.interactionType) && Objects.equals(this.promotionType, var2.promotionType) && super.equals(var1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.interactionType, this.promotionType, super.hashCode());
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("class GamePromotionInteractionEventData {\n");
      var1.append("    ").append(this.toIndentedString(super.toString())).append("\n");
      var1.append("    interactionType: ").append(this.toIndentedString(this.interactionType)).append("\n");
      var1.append("    promotionType: ").append(this.toIndentedString(this.promotionType)).append("\n");
      var1.append("}");
      return var1.toString();
   }

   private String toIndentedString(Object var1) {
      return var1 == null ? "null" : var1.toString().replace("\n", "\n    ");
   }

   public static void validateJsonElement(JsonElement var0) {
      if (var0 == null && !field62.isEmpty()) {
         throw new IllegalArgumentException(
            String.format("The required field(s) %s in GamePromotionInteractionEventData is not found in the empty JSON string", field62.toString())
         );
      }

      for (Entry var3 : var0.getAsJsonObject().entrySet()) {
         if (!field61.contains(var3.getKey())) {
            throw new IllegalArgumentException(
               String.format(
                  "The field `%s` in the JSON string is not defined in the `GamePromotionInteractionEventData` properties. JSON: %s",
                  var3.getKey(),
                  var0.toString()
               )
            );
         }
      }

      for (String var6 : field62) {
         if (var0.getAsJsonObject().get(var6) == null) {
            throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", var6, var0.toString()));
         }
      }

      JsonObject var5 = var0.getAsJsonObject();
      if (!var5.get("interaction_type").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format(
               "Expected the field `interaction_type` to be a primitive type in the JSON string but got `%s`", var5.get("interaction_type").toString()
            )
         );
      }

      GamePromotionInteractionEventData.Type.validateJsonElement(var5.get("interaction_type"));
      if (!var5.get("promotion_type").isJsonPrimitive()) {
         throw new IllegalArgumentException(
            String.format("Expected the field `promotion_type` to be a primitive type in the JSON string but got `%s`", var5.get("promotion_type").toString())
         );
      }
   }

   public static GamePromotionInteractionEventData method10(String var0) {
      return (GamePromotionInteractionEventData)com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().fromJson(var0, GamePromotionInteractionEventData.class);
   }

   @Override
   public String toJson() {
      return com.moonsworth.lunar.network.mixin.MixinHelper7.getGson().toJson(this);
   }

   static {
      field61.add("player_uuid");
      field61.add("installation_id");
      field61.add("overwolf_muid");
      field61.add("timestamp");
      field61.add("event_id");
      field61.add("assetserver_session_id");
      field61.add("launch_id");
      field61.add("inbound_location");
      field61.add("location");
      field61.add("minecraft_version");
      field61.add("lunar_client_git_commit");
      field61.add("lunar_client_git_branch");
      field61.add("lunar_client_semver");
      field61.add("lunar_client_ui_git_commit");
      field61.add("lunar_client_ui_git_branch");
      field61.add("operating_system");
      field61.add("operating_system_release");
      field61.add("cpu_architecture");
      field61.add("launcher_version");
      field61.add("canary_token");
      field61.add("ichor_modules");
      field61.add("gl_extensions");
      field61.add("installed_mods");
      field61.add("wearer_uuid");
      field61.add("wearer_cosmetic_ids");
      field61.add("wearer_outfit_id");
      field61.add("trigger");
      field61.add("geo_location");
      field61.add("interaction_type");
      field61.add("promotion_type");
      field62.add("interaction_type");
      field62.add("promotion_type");
      field62.add("installation_id");
      field62.add("installed_mods");
   }

   public static class Data implements TypeAdapterFactory {
      public <T> TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         if (!GamePromotionInteractionEventData.class.isAssignableFrom(var2.getRawType())) {
            return null;
         }

         final TypeAdapter var3 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var4 = var1.getDelegateAdapter(this, TypeToken.get(GamePromotionInteractionEventData.class));
         return (new TypeAdapter<GamePromotionInteractionEventData>() {
            public void method1(JsonWriter var1, GamePromotionInteractionEventData var2x) {
               JsonObject var3x = var4.toJsonTree(var2x).getAsJsonObject();
               var3.write(var1, var3x);
            }

            public GamePromotionInteractionEventData method2(JsonReader var1) {
               JsonElement var2x = (JsonElement)var3.read(var1);
               return (GamePromotionInteractionEventData)var4.fromJsonTree(var2x);
            }
         }).nullSafe();
      }
   }

   @JsonAdapter(GamePromotionInteractionEventData.Type.Data.class)
   public enum Type {
      IMPRESSION("impression"),
      CLICK("click");

      private String value;

      Type(String var3) {
         this.value = var3;
      }

      public String getValue() {
         return this.value;
      }

      @Override
      public String toString() {
         return String.valueOf(this.value);
      }

      public static GamePromotionInteractionEventData.Type fromValue(String var0) {
         for (GamePromotionInteractionEventData.Type var4 : values()) {
            if (var4.value.equals(var0)) {
               return var4;
            }
         }

         throw new IllegalArgumentException("Unexpected value '" + var0 + "'");
      }

      public static void validateJsonElement(JsonElement var0) {
         String var1 = var0.getAsString();
         fromValue(var1);
      }

      public static class Data extends TypeAdapter<GamePromotionInteractionEventData.Type> {
         public void method1(JsonWriter var1, GamePromotionInteractionEventData.Type var2) {
            var1.value(var2.getValue());
         }

         public GamePromotionInteractionEventData.Type method2(JsonReader var1) {
            String var2 = var1.nextString();
            return GamePromotionInteractionEventData.Type.fromValue(var2);
         }
      }
   }
}
