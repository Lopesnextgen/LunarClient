package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public abstract class BedrockComponentAppearanceBillboard extends BedrockComponentBase implements IComponentParticleRender {
   public MolangExpression field1 = MolangParser.field3;
   public MolangExpression field2 = MolangParser.field3;
   public CameraFacing field3 = CameraFacing.LOOKAT_XYZ;
   public int field4 = 128;
   public int field5 = 128;
   public MolangExpression field6 = MolangParser.field3;
   public MolangExpression field7 = MolangParser.field3;
   public MolangExpression field8 = MolangParser.field3;
   public MolangExpression field9 = MolangParser.field3;
   public boolean field10 = false;
   public float field11;
   public float field12;
   public float field13;
   public MolangExpression field14 = MolangParser.field3;
   public boolean field15 = false;
   public boolean loop = false;
   protected float field16;
   protected float field17;
   protected float field18;
   protected float field19;
   protected float field20;
   protected float field21;

   public BedrockComponentAppearanceBillboard() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser glintcolorizer3iterator2) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, glintcolorizer3iterator2);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("size") && json3.get("size").isJsonArray()) {
         JsonArray array4 = json3.getAsJsonArray("size");
         if (array4.size() >= 2) {
            this.field1 = glintcolorizer3iterator2.method5(array4.get(0));
            this.field2 = glintcolorizer3iterator2.method5(array4.get(1));
         }
      }

      if (json3.has("facing_camera_mode")) {
         this.field3 = CameraFacing.fromString(json3.get("facing_camera_mode").getAsString());
      }

      if (json3.has("uv") && json3.get("uv").isJsonObject()) {
         this.method2(json3.get("uv").getAsJsonObject(), glintcolorizer3iterator2);
      }

      return super.method1(json3, glintcolorizer3iterator2);
   }

   private void method2(JsonObject json1, MolangParser glintcolorizer3iterator2) {
      if (json1.has("texture_width")) {
         this.field4 = json1.get("texture_width").getAsInt();
      }

      if (json1.has("texture_height")) {
         this.field5 = json1.get("texture_height").getAsInt();
      }

      if (json1.has("uv") && json1.get("uv").isJsonArray()) {
         JsonArray array3 = json1.getAsJsonArray("uv");
         if (array3.size() >= 2) {
            this.field6 = glintcolorizer3iterator2.method5(array3.get(0));
            this.field7 = glintcolorizer3iterator2.method5(array3.get(1));
         }
      }

      if (json1.has("uv_size") && json1.get("uv_size").isJsonArray()) {
         JsonArray array4 = json1.getAsJsonArray("uv_size");
         if (array4.size() >= 2) {
            this.field8 = glintcolorizer3iterator2.method5(array4.get(0));
            this.field9 = glintcolorizer3iterator2.method5(array4.get(1));
         }
      }

      if (json1.has("flipbook") && json1.get("flipbook").isJsonObject()) {
         this.field10 = true;
         this.method3(json1.get("flipbook").getAsJsonObject(), glintcolorizer3iterator2);
      }
   }

   private void method3(JsonObject json1, MolangParser glintcolorizer3iterator2) {
      if (json1.has("base_UV") && json1.get("base_UV").isJsonArray()) {
         JsonArray array3 = json1.getAsJsonArray("base_UV");
         if (array3.size() >= 2) {
            this.field6 = glintcolorizer3iterator2.method5(array3.get(0));
            this.field7 = glintcolorizer3iterator2.method5(array3.get(1));
         }
      }

      if (json1.has("size_UV") && json1.get("size_UV").isJsonArray()) {
         JsonArray array4 = json1.getAsJsonArray("size_UV");
         if (array4.size() >= 2) {
            this.field8 = glintcolorizer3iterator2.method5(array4.get(0));
            this.field9 = glintcolorizer3iterator2.method5(array4.get(1));
         }
      }

      if (json1.has("step_UV") && json1.get("step_UV").isJsonArray()) {
         JsonArray array5 = json1.getAsJsonArray("step_UV");
         if (array5.size() >= 2) {
            this.field11 = array5.get(0).getAsFloat();
            this.field12 = array5.get(1).getAsFloat();
         }
      }

      if (json1.has("frames_per_second")) {
         this.field13 = json1.get("frames_per_second").getAsFloat();
      }

      if (json1.has("max_frame")) {
         this.field14 = glintcolorizer3iterator2.method5(json1.get("max_frame"));
      }

      if (json1.has("stretch_to_lifetime")) {
         this.field15 = json1.get("stretch_to_lifetime").getAsBoolean();
      }

      if (json1.has("loop")) {
         this.loop = json1.get("loop").getAsBoolean();
      }
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      JsonArray array2 = new JsonArray();
      JsonObject json3 = new JsonObject();
      array2.add(this.field1.method5());
      array2.add(this.field2.method5());
      json3.addProperty("texture_width", this.field4);
      json3.addProperty("texture_height", this.field5);
      if (!this.field10 && !MolangExpression.method1(this.field6) || !MolangExpression.method1(this.field7)) {
         JsonArray array4 = new JsonArray();
         array4.add(this.field6.method5());
         array4.add(this.field7.method5());
         json3.add("uv", array4);
      }

      if (!this.field10 && !MolangExpression.method1(this.field8) || !MolangExpression.method1(this.field9)) {
         JsonArray array6 = new JsonArray();
         array6.add(this.field8.method5());
         array6.add(this.field9.method5());
         json3.add("uv_size", array6);
      }

      if (this.field10) {
         JsonObject json7 = new JsonObject();
         if (!MolangExpression.method1(this.field6) || !MolangExpression.method1(this.field7)) {
            JsonArray array5 = new JsonArray();
            array5.add(this.field6.method5());
            array5.add(this.field7.method5());
            json7.add("base_UV", array5);
         }

         if (!MolangExpression.method1(this.field8) || !MolangExpression.method1(this.field9)) {
            JsonArray array8 = new JsonArray();
            array8.add(this.field8.method5());
            array8.add(this.field9.method5());
            json7.add("size_UV", array8);
         }

         if (this.field11 != 0.0F || this.field12 != 0.0F) {
            JsonArray array9 = new JsonArray();
            array9.add(new JsonPrimitive(this.field11));
            array9.add(new JsonPrimitive(this.field12));
            json7.add("step_UV", array9);
         }

         if (this.field13 != 0.0F) {
            json7.addProperty("frames_per_second", this.field13);
         }

         if (!MolangExpression.method1(this.field14)) {
            json7.add("max_frame", this.field14.method5());
         }

         if (this.field15) {
            json7.addProperty("stretch_to_lifetime", true);
         }

         if (this.loop) {
            json7.addProperty("loop", true);
         }

         json3.add("flipbook", json7);
      }

      json1.add("size", array2);
      json1.addProperty("facing_camera_mode", this.field3.id);
      json1.add("uv", json3);
      return json1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, float value2) {
   }

   public void method6(BedrockParticle glintcolorizer4_21, float value2) {
      this.field16 = (float)this.field1.get() * 2.25F;
      this.field17 = (float)this.field2.get() * 2.25F;
      float value3 = (float)this.field6.get();
      float value4 = (float)this.field7.get();
      float value5 = (float)this.field8.get();
      float value6 = (float)this.field9.get();
      if (this.field10) {
         int number7 = (int)(glintcolorizer4_21.method1(value2) * this.field13);
         int number8 = (int)this.field14.get();
         if (this.field15) {
            float value9 = glintcolorizer4_21.lifetime <= 0 ? 0.0F : (glintcolorizer4_21.field5 + value2) / glintcolorizer4_21.lifetime;
            number7 = (int)(value9 * number8);
         }

         if (this.loop && number8 != 0) {
            number7 %= number8;
         }

         if (number7 > number8) {
            number7 = number8;
         }

         value3 += this.field11 * number7;
         value4 += this.field12 * number7;
      }

      this.field18 = value3;
      this.field19 = value4;
      this.field20 = value3 + value5;
      this.field21 = value4 + value6;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, float value2) {
   }
}
