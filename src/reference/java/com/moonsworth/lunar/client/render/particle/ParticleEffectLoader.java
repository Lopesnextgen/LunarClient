package com.moonsworth.lunar.client.render.particle;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.render.particle.BedrockScheme;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.Nullable;

public class ParticleEffectLoader {
   public ParticleEffectLoader() {
   }

   @Nullable
   public static BedrockScheme method1(ResourceLocationBridge horsestats140) {
      Bridge11_2 bridge11_21 = Ref.method3().bridge$getResourceManager();
      ResourceBridge bridge152 = bridge11_21.bridge$getResource(horsestats140);
      if (bridge152 == null) {
         LunarLogger.method5("Couldn't find the bedrock scheme resource: " + horsestats140, new Object[0]);
         return null;
      }

      try (
         InputStreamReader reader3 = new InputStreamReader(bridge152.bridge$getInputStream(), StandardCharsets.UTF_8);
         BufferedReader reader4 = new BufferedReader(reader3);
      ) {
         JsonObject json5 = (JsonObject)LunarConstants.field22.fromJson(reader4, JsonObject.class);
         return BedrockScheme.method4(json5);
      }
   }
}
