package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ThreadDownloadImageDataBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.framework.Ref;

public final class HeadTextureCache {
   private static final Map<UUID, ResourceLocationBridge> field1 = new HashMap<>();
   private static final Map<UUID, ResourceLocationBridge> field2 = new HashMap<>();

   public static ResourceLocationBridge method1(UUID uuid0) {
      if (!field1.containsKey(uuid0)) {
         ResourceLocationBridge horsestats141 = ResourceLocationBridge.create("lunar", "download/heads/" + uuid0 + ".png");
         ThreadDownloadImageDataBridge bridge20extension2 = Bridge.method8()
            .method14(null, ServiceEndpoints.method3() + "/face/" + uuid0.toString(), horsestats141, ResourceLocationBridge.create("lunar", "steve.png"));
         Ref.method3().bridge$getTextureManager().bridge$loadTexture(horsestats141, bridge20extension2);
         field1.put(uuid0, horsestats141);
         return horsestats141;
      } else {
         return field1.get(uuid0);
      }
   }

   @NotNull
   public static ResourceLocationBridge method2(UUID uuid0) {
      if (!field2.containsKey(uuid0)) {
         ResourceLocationBridge horsestats141 = ResourceLocationBridge.create("lunar", "download/wrapped/" + uuid0 + ".png");
         ThreadDownloadImageDataBridge bridge20extension2 = Bridge.method8()
            .method14(
               null,
               ServiceEndpoints.method3() + "/body/front/" + uuid0.toString() + "?cropBottom=170&disableCosmeticType=all",
               horsestats141,
               ResourceLocationBridge.create("lunar", "steve-bust.png")
            );
         Ref.method3().bridge$getTextureManager().bridge$loadTexture(horsestats141, bridge20extension2);
         field2.put(uuid0, horsestats141);
         return horsestats141;
      } else {
         return field2.get(uuid0);
      }
   }

   public static boolean method3(UUID uuid0) {
      ResourceLocationBridge horsestats141 = field1.remove(uuid0);
      if (horsestats141 != null) {
         Ref.method3().bridge$getTextureManager().bridge$deleteTexture(horsestats141);
         return true;
      } else {
         return false;
      }
   }

   public static boolean method4(UUID uuid0) {
      ResourceLocationBridge horsestats141 = field2.remove(uuid0);
      if (horsestats141 != null) {
         Ref.method3().bridge$getTextureManager().bridge$deleteTexture(horsestats141);
         return true;
      } else {
         return false;
      }
   }

   @Generated
   private HeadTextureCache() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
