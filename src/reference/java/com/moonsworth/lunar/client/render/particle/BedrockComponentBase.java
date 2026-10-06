package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.render.particle.MolangParser;

public abstract class BedrockComponentBase {
   public BedrockComponentBase() {
   }

   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      return this;
   }

   public JsonElement method2() {
      return new JsonObject();
   }

   public boolean method3() {
      return false;
   }
}
