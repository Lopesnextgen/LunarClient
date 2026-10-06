package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.particle.TintSolid;
import com.moonsworth.lunar.client.render.particle.BedrockComponentBase;
import com.moonsworth.lunar.client.render.particle.BedrockParticle;
import com.moonsworth.lunar.client.render.particle.BedrockEmitter;
import com.moonsworth.lunar.client.render.particle.MolangExpression;
import com.moonsworth.lunar.client.render.particle.Tint;
import com.moonsworth.lunar.client.render.particle.BedrockParticleDeserializer;
import com.moonsworth.lunar.client.render.particle.MolangParser;
import com.moonsworth.lunar.client.render.particle.HologramParticleEmitter;

public class NameplateCollisionTintingComponent extends BedrockComponentBase implements NameplateGlintRenderer {
   public MolangExpression field1 = MolangParser.field3;
   public Tint field2 = new TintSolid(
      MolangParser.field4, MolangParser.field4, MolangParser.field4, MolangParser.field4
   );

   public NameplateCollisionTintingComponent() {
   }

   @Override
   public BedrockComponentBase method1(JsonElement element1, MolangParser molangParser) {
      if (!element1.isJsonObject()) {
         return super.method1(element1, molangParser);
      }

      JsonObject json3 = element1.getAsJsonObject();
      if (json3.has("enabled")) {
         this.field1 = molangParser.method5(json3.get("enabled"));
      }

      if (json3.has("color")) {
         JsonElement element4 = json3.get("color");
         if (element4.isJsonArray() || element4.isJsonPrimitive()) {
            this.field2 = Tint.method1(element4, molangParser);
         } else if (element4.isJsonObject()) {
            this.field2 = Tint.method2(element4.getAsJsonObject(), molangParser);
         }
      }

      return super.method1(json3, molangParser);
   }

   @Override
   public JsonElement method2() {
      JsonObject json1 = new JsonObject();
      JsonElement element2 = this.field2.method4();
      json1.add("enabled", this.field1.method5());
      if (!BedrockParticleDeserializer.method1(element2)) {
         json1.add("color", element2);
      }

      return json1;
   }

   @Override
   public void method1(BedrockEmitter glintcolorizer5_21, float value2) {
   }

   @Override
   public void method1(HologramParticleEmitter iterator, BedrockParticle glintcolorizer4_22, DrawBufferBridge bridge2_323, AbstractRenderContext bridgeextension_94, float value, ResourceLocationBridge horsestats146) {
      if (glintcolorizer4_22.field19) {
         this.method2(glintcolorizer4_22, 0, 0, 0.0F, null, horsestats146);
      }
   }

   @Override
   public void method2(BedrockParticle glintcolorizer4_21, int value, int value2, float value3, AbstractRenderContext bridgeextension_95, ResourceLocationBridge horsestats146) {
      if (this.field2 != null) {
         this.field2.method3(glintcolorizer4_21);
      } else {
         glintcolorizer4_21.field35 = glintcolorizer4_21.field36 = glintcolorizer4_21.field37 = glintcolorizer4_21.field38 = 1.0F;
      }
   }

   @Override
   public void method2(BedrockEmitter glintcolorizer5_21, float value2) {
   }

   @Override
   public int method1() {
      return -5;
   }
}
