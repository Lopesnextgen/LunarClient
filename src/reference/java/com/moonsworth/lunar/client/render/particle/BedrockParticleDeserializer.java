package com.moonsworth.lunar.client.render.particle;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.particle.BedrockComponentShapeDisc;
import com.moonsworth.lunar.client.render.particle.BedrockComponentShapeBox;
import com.moonsworth.lunar.client.render.particle.MolangException;
import com.moonsworth.lunar.client.render.particle.BedrockComponentMotionCollision;
import java.lang.reflect.Type;
import java.util.Map.Entry;

public abstract class BedrockParticleDeserializer implements JsonDeserializer<BedrockScheme>, JsonSerializer<BedrockScheme> {
   public BiMap<String, Class<? extends BedrockComponentBase>> field1 = HashBiMap.create();

   public static boolean method1(JsonElement element0) {
      if (element0.isJsonArray()) {
         return element0.getAsJsonArray().size() == 0;
      }

      if (element0.isJsonObject()) {
         return element0.getAsJsonObject().entrySet().size() == 0;
      }

      if (element0.isJsonPrimitive()) {
         JsonPrimitive json1 = element0.getAsJsonPrimitive();
         if (json1.isString()) {
            return json1.getAsString().isEmpty();
         }

         if (json1.isNumber()) {
            return Operation.equals(json1.getAsDouble(), 0.0);
         }
      }

      return element0.isJsonNull();
   }

   public BedrockParticleDeserializer() {
      this.field1.put("minecraft:emitter_local_space", com.moonsworth.lunar.client.render.particle.BedrockComponentLocalSpace.class);
      this.field1.put("minecraft:emitter_initialization", com.moonsworth.lunar.client.render.particle.BedrockComponentInitialization.class);
      this.field1.put("minecraft:emitter_rate_instant", com.moonsworth.lunar.client.render.particle.BedrockComponentRateInstant.class);
      this.field1.put("minecraft:emitter_rate_steady", com.moonsworth.lunar.client.render.particle.BedrockComponentRateSteady.class);
      this.field1.put("minecraft:emitter_lifetime_looping", BedrockComponentLifetimeLooping.class);
      this.field1.put("minecraft:emitter_lifetime_once", BedrockComponentLifetimeOnce.class);
      this.field1.put("minecraft:emitter_lifetime_expression", BedrockComponentLifetimeExpression.class);
      this.field1.put("minecraft:emitter_shape_disc", BedrockComponentShapeDisc.class);
      this.field1.put("minecraft:emitter_shape_box", BedrockComponentShapeBox.class);
      this.field1.put("minecraft:emitter_shape_entity_aabb", com.moonsworth.lunar.client.render.particle.BedrockComponentShapeEntityAABB.class);
      this.field1.put("minecraft:emitter_shape_point", com.moonsworth.lunar.client.render.particle.BedrockComponentShapePoint.class);
      this.field1.put("minecraft:emitter_shape_sphere", com.moonsworth.lunar.client.render.particle.BedrockComponentShapeSphere.class);
      this.field1.put("minecraft:particle_lifetime_expression", BedrockComponentParticleLifetime.class);
      this.field1.put("minecraft:particle_kill_plane", BedrockComponentKillPlane.class);
      this.field1.put("minecraft:particle_appearance_lighting", BedrockComponentAppearanceLighting.class);
      this.field1.put("minecraft:particle_initial_speed", com.moonsworth.lunar.client.render.particle.BedrockComponentInitialSpeed.class);
      this.field1.put("minecraft:particle_initial_spin", com.moonsworth.lunar.client.render.particle.BedrockComponentInitialSpin.class);
      this.field1.put("minecraft:particle_motion_collision", BedrockComponentMotionCollision.class);
      this.field1.put("minecraft:particle_motion_dynamic", com.moonsworth.lunar.client.render.particle.BedrockComponentMotionDynamic.class);
      this.field1.put("minecraft:particle_motion_parametric", com.moonsworth.lunar.client.render.particle.BedrockComponentMotionParametric.class);
   }

   public BedrockScheme method2(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      BedrockScheme glintcolorizer3_24 = new BedrockScheme();
      if (!element1.isJsonObject()) {
         throw new JsonParseException("The root element of Bedrock particle should be an object!");
      }

      JsonObject json5 = element1.getAsJsonObject();

      try {
         this.method3(glintcolorizer3_24, this.method7(json5, "particle_effect", "No particle_effect was found..."));
      } catch (MolangException glintcolorizerexception7) {
         throw new JsonParseException("Couldn't parse some MoLang expression!", glintcolorizerexception7);
      }

      glintcolorizer3_24.setup();
      return glintcolorizer3_24;
   }

   private void method3(BedrockScheme glintcolorizer3_21, JsonObject json2) {
      this.method4(glintcolorizer3_21, this.method7(json2, "description", "No particle_effect.description was found..."));
      if (json2.has("curves")) {
         JsonElement element3 = json2.get("curves");
         if (element3.isJsonObject()) {
            this.method5(glintcolorizer3_21, element3.getAsJsonObject());
         }
      }

      this.method6(glintcolorizer3_21, this.method7(json2, "components", "No particle_effect.components was found..."));
   }

   private void method4(BedrockScheme glintcolorizer3_21, JsonObject json2) {
      if (json2.has("identifier")) {
         glintcolorizer3_21.identifier = json2.get("identifier").getAsString();
      }

      JsonObject json3 = this.method7(json2, "basic_render_parameters", "No particle_effect.basic_render_parameters was found...");
      if (json3.has("material")) {
         glintcolorizer3_21.field3 = BedrockMaterial.fromString(json3.get("material").getAsString());
      }

      if (json3.has("bone")) {
         glintcolorizer3_21.bone = json3.get("bone").getAsString();
      }

      if (json3.has("texture")) {
         String text4 = json3.get("texture").getAsString();
         if (!text4.equals("textures/particle/particles")) {
            if (Bridge.getMinecraftVersion().method19()) {
               text4 = text4.replace("minecraft:textures/blocks/", "minecraft:textures/block/");
               text4 = text4.replace("minecraft:textures/items/", "minecraft:textures/item/");
               if (json3.has("modern_texture")) {
                  text4 = json3.get("modern_texture").getAsString();
               }
            } else {
               text4 = text4.replace("minecraft:textures/block/", "minecraft:textures/blocks/");
               text4 = text4.replace("minecraft:textures/item/", "minecraft:textures/items/");
            }

            glintcolorizer3_21.texture = ResourceLocationBridge.create(text4);
         }
      }
   }

   private void method5(BedrockScheme glintcolorizer3_21, JsonObject json2) {
      for (Entry entry4 : json2.entrySet()) {
         JsonElement element5 = (JsonElement)entry4.getValue();
         if (element5.isJsonObject()) {
            BedrockCurve glintcolorizer_36 = new BedrockCurve();
            glintcolorizer_36.method4(element5.getAsJsonObject(), glintcolorizer3_21.field12);
            glintcolorizer3_21.field4.put((String)entry4.getKey(), glintcolorizer_36);
         }
      }
   }

   private void method6(BedrockScheme glintcolorizer3_21, JsonObject json2) {
      for (Entry entry4 : json2.entrySet()) {
         String text5 = (String)entry4.getKey();
         if (this.field1.containsKey(text5)) {
            BedrockComponentBase glintcolorizer2_36 = null;

            try {
               glintcolorizer2_36 = (BedrockComponentBase)((Class)this.field1.get(text5)).getConstructor().newInstance();
            } catch (Exception exception8) {
            }

            if (glintcolorizer2_36 != null) {
               glintcolorizer2_36.method1((JsonElement)entry4.getValue(), glintcolorizer3_21.field12);
               glintcolorizer3_21.field5.add(glintcolorizer2_36);
            } else {
               System.out.println("Failed to parse given component " + text5 + " in " + glintcolorizer3_21.identifier + "!");
            }
         }
      }
   }

   private JsonObject method7(JsonObject json1, String text2, String text3) {
      if (!json1.has(text2) && !json1.get(text2).isJsonObject()) {
         throw new JsonParseException(text3);
      } else {
         return json1.get(text2).getAsJsonObject();
      }
   }

   public JsonElement method8(BedrockScheme glintcolorizer3_21, Type type2, JsonSerializationContext jsonserializationcontext3) {
      JsonObject json4 = new JsonObject();
      JsonObject json5 = new JsonObject();
      json4.addProperty("format_version", "1.10.0");
      json4.add("particle_effect", json5);
      this.method9(json5, glintcolorizer3_21);
      this.method10(json5, glintcolorizer3_21);
      this.method11(json5, glintcolorizer3_21);
      return json4;
   }

   private void method9(JsonObject json1, BedrockScheme glintcolorizer3_22) {
      JsonObject json3 = new JsonObject();
      JsonObject json4 = new JsonObject();
      json1.add("description", json3);
      json3.addProperty("identifier", glintcolorizer3_22.identifier);
      json3.add("basic_render_parameters", json4);
      json4.addProperty("material", glintcolorizer3_22.field3.id);
      json4.addProperty("texture", "textures/particle/particles");
      if (glintcolorizer3_22.texture != null && !glintcolorizer3_22.texture.equals(BedrockScheme.field1)) {
         json4.addProperty("texture", glintcolorizer3_22.texture.toString());
      }
   }

   private void method10(JsonObject json1, BedrockScheme glintcolorizer3_22) {
      JsonObject json3 = new JsonObject();
      json1.add("curves", json3);

      for (Entry entry5 : glintcolorizer3_22.field4.entrySet()) {
         json3.add((String)entry5.getKey(), ((BedrockCurve)entry5.getValue()).method5());
      }
   }

   private void method11(JsonObject json1, BedrockScheme glintcolorizer3_22) {
      JsonObject json3 = new JsonObject();
      json1.add("components", json3);

      for (BedrockComponentBase glintcolorizer2_35 : glintcolorizer3_22.field5) {
         JsonElement element6 = glintcolorizer2_35.method2();
         if (!method1(element6) || glintcolorizer2_35.method3()) {
            json3.add((String)this.field1.inverse().get(glintcolorizer2_35.getClass()), element6);
         }
      }
   }
}
