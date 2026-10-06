package com.moonsworth.lunar.legacy.mixin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.moonsworth.lunar.bridge.Bridge2_3;
import com.moonsworth.lunar.bridge.Bridge6_5;
import com.moonsworth.lunar.ichor.Annotation2;
import java.lang.reflect.Type;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.ServerStatusResponse.MinecraftProtocolVersionIdentifier;
import net.minecraft.network.ServerStatusResponse.PlayerCountData;
import net.minecraft.network.ServerStatusResponse.Serializer_v1_7;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;
import org.spongepowered.asm.mixin.Mixin;

@Annotation2(max = 0)
@Mixin(Serializer_v1_7.class)
public class Serializer_v1_7Mixin implements Bridge2_3 {
   @WrapMethod(method = "deserialize")
   private ServerStatusResponse lunar$deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3, Operation<ServerStatusResponse> var4) {
      JsonObject var5 = JsonUtils.getElementAsJsonObject$v1_7(var1, "status");
      ServerStatusResponse var6 = new ServerStatusResponse();
      if (var5.has("description")) {
         var6.setServerDescription((IChatComponent)var3.deserialize(var5.get("description"), IChatComponent.class));
      }

      if (var5.has("players")) {
         var6.setPlayerCountData((PlayerCountData)var3.deserialize(var5.get("players"), PlayerCountData.class));
      }

      if (var5.has("version")) {
         var6.setProtocolVersionInfo((MinecraftProtocolVersionIdentifier)var3.deserialize(var5.get("version"), MinecraftProtocolVersionIdentifier.class));
      }

      if (var5.has("favicon")) {
         var6.setFavicon(JsonUtils.getJsonObjectStringFieldValue$v1_7(var5, "favicon"));
      }

      if (var5.has("lcServer")) {
         ((Bridge6_5)var6).setLunarServer(var5.get("lcServer").getAsString());
      }

      return var6;
   }
}
