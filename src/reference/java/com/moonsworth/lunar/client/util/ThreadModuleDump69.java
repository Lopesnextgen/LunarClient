package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge20Extension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;

public final class ThreadModuleDump69 {
   private static final Map<UUID, ResourceLocationBridge> headTextures = new HashMap<>();
   private static final Map<UUID, ResourceLocationBridge> wrappedTextures = new HashMap<>();

   public static ResourceLocationBridge getHeadTexture(UUID var0) {
      if (!field1.containsKey(var0)) {
         ResourceLocationBridge var1 = ResourceLocationBridge.create("lunar", "download/heads/" + var0 + ".png");
         Bridge20Extension var2 = Bridge.method8()
            .method14(null, ServiceEndpoints.method3() + "/face/" + var0.toString(), var1, ResourceLocationBridge.create("lunar", "steve.png"));
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$loadTexture(var1, var2);
         field1.put(var0, var1);
         return var1;
      } else {
         return field1.get(var0);
      }
   }

   @NotNull
   public static ResourceLocationBridge getWrappedTexture(UUID var0) {
      if (!field2.containsKey(var0)) {
         ResourceLocationBridge var1 = ResourceLocationBridge.create("lunar", "download/wrapped/" + var0 + ".png");
         Bridge20Extension var2 = Bridge.method8()
            .method14(
               null,
               ServiceEndpoints.method3() + "/body/front/" + var0.toString() + "?cropBottom=170&disableCosmeticType=all",
               var1,
               ResourceLocationBridge.create("lunar", "steve-bust.png")
            );
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$loadTexture(var1, var2);
         field2.put(var0, var1);
         return var1;
      } else {
         return field2.get(var0);
      }
   }

   public static boolean removeHeadTexture(UUID var0) {
      ResourceLocationBridge var1 = field1.remove(var0);
      if (var1 != null) {
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(var1);
         return true;
      } else {
         return false;
      }
   }

   public static boolean removeWrappedTexture(UUID var0) {
      ResourceLocationBridge var1 = field2.remove(var0);
      if (var1 != null) {
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(var1);
         return true;
      } else {
         return false;
      }
   }

   @Generated
   private ThreadModuleDump69() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
