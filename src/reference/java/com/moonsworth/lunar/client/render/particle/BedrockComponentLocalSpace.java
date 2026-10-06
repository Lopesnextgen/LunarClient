package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.IComponentParticleInitialize;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public class BedrockComponentLocalSpace extends BedrockComponentBase implements IComponentParticleInitialize {
   public boolean field1;
   public boolean field2;
   public boolean field3 = true;
   public boolean field4 = true;
   public boolean field5;
   public boolean field6;
   public boolean field7;
   public float field8;
   public float field9;

   public BedrockComponentLocalSpace() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("position")) {
         this.field1 = json3.get("position").getAsBoolean();
      }

      if (json3.has("rotation")) {
         this.field2 = json3.get("rotation").getAsBoolean();
      }

      if (json3.has("scale")) {
         this.field3 = json3.get("scale").getAsBoolean();
      }

      if (json3.has("scale_billboard")) {
         this.field4 = json3.get("scale_billboard").getAsBoolean();
      }

      if (json3.has("direction")) {
         this.field5 = json3.get("direction").getAsBoolean();
      }

      if (json3.has("acceleration")) {
         this.field6 = json3.get("acceleration").getAsBoolean();
      }

      if (json3.has("gravity")) {
         this.field7 = json3.get("gravity").getAsBoolean();
      }

      if (json3.has("linear_velocity")) {
         this.field8 = json3.get("linear_velocity").getAsFloat();
      }

      if (json3.has("angular_velocity")) {
         this.field9 = json3.get("angular_velocity").getAsFloat();
      }

      return super.method1(json3, molangParser);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      if (this.field1) {
         json1.addProperty("position", true);
      }

      if (this.field2) {
         json1.addProperty("rotation", true);
      }

      if (this.field3) {
         json1.addProperty("scale", true);
      }

      if (this.field4) {
         json1.addProperty("scale_billboard", true);
      }

      if (this.field5) {
         json1.addProperty("direction", true);
      }

      if (this.field6) {
         json1.addProperty("acceleration", true);
      }

      if (this.field7) {
         json1.addProperty("gravity", true);
      }

      if (this.field8 != 0.0F) {
         json1.addProperty("linear_velocity", this.field8);
      }

      if (this.field9 != 0.0F) {
         json1.addProperty("angular_velocity", this.field9);
      }

      return json1;
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, BedrockParticle glintcolorizer4_22) {
      glintcolorizer4_22.field7 = this.field1;
      glintcolorizer4_22.field8 = this.field2;
      glintcolorizer4_22.field10 = this.field3;
      glintcolorizer4_22.field11 = this.field4;
      glintcolorizer4_22.field9 = this.field5;
      glintcolorizer4_22.field12 = this.field6;
      glintcolorizer4_22.field16 = this.field7;
      glintcolorizer4_22.field14 = this.field8;
      glintcolorizer4_22.field15 = this.field9;
      glintcolorizer4_22.method5(glintcolorizer5_21);
   }

   @Override
   public int method1() {
      return 1000;
   }
}
