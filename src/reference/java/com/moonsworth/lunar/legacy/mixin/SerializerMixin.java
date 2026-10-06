package com.moonsworth.lunar.legacy.mixin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.moonsworth.lunar.bridge.ServerStatusResponseSerializerBridge;
import com.moonsworth.lunar.bridge.ServerStatusResponseBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.lang.reflect.Type;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.ServerStatusResponse.MinecraftProtocolVersionIdentifier;
import net.minecraft.network.ServerStatusResponse.PlayerCountData;
import net.minecraft.network.ServerStatusResponse.Serializer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(min = 1)
@Mixin(Serializer.class)
public class SerializerMixin implements ServerStatusResponseSerializerBridge {
   public SerializerMixin() {
   }

   @WrapMethod(
      method = "deserialize(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/network/ServerStatusResponse;"
   )
   private ServerStatusResponse lunar$deserialize(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3, Operation<ServerStatusResponse> operation4) {
      JsonObject json5;
      if (Ref.MC_VERSION >= 1) {
         json5 = JsonUtils.getJsonObject(element1, "status");
      } else {
         json5 = JsonUtils.getElementAsJsonObject$v1_7(element1, "status");
      }

      ServerStatusResponse serverstatusresponse6 = new ServerStatusResponse();
      if (json5.has("description")) {
         serverstatusresponse6.setServerDescription((IChatComponent)jsondeserializationcontext3.deserialize(json5.get("description"), IChatComponent.class));
      }

      if (json5.has("players")) {
         if (Ref.MC_VERSION >= 5) {
            serverstatusresponse6.setPlayers$v1_12((PlayerCountData)jsondeserializationcontext3.deserialize(json5.get("players"), PlayerCountData.class));
         } else {
            serverstatusresponse6.setPlayerCountData((PlayerCountData)jsondeserializationcontext3.deserialize(json5.get("players"), PlayerCountData.class));
         }
      }

      if (json5.has("version")) {
         if (Ref.MC_VERSION >= 5) {
            serverstatusresponse6.setVersion$v1_12((MinecraftProtocolVersionIdentifier)jsondeserializationcontext3.deserialize(json5.get("version"), MinecraftProtocolVersionIdentifier.class));
         } else {
            serverstatusresponse6.setProtocolVersionInfo((MinecraftProtocolVersionIdentifier)jsondeserializationcontext3.deserialize(json5.get("version"), MinecraftProtocolVersionIdentifier.class));
         }
      }

      if (json5.has("favicon")) {
         serverstatusresponse6.setFavicon(JsonUtils.getString(json5, "favicon"));
      }

      if (json5.has("lcServer")) {
         ((ServerStatusResponseBridge)serverstatusresponse6).setLunarServer(json5.get("lcServer").getAsString());
      }

      return serverstatusresponse6;
   }
}
