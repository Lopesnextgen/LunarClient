package com.moonsworth.lunar.client.util.net;
import com.moonsworth.lunar.client.framework.Ref;

public class DisconnectUtils {
   public DisconnectUtils() {
   }

   public static void method1() {
      if (Ref.method3().bridge$getCurrentServerData() != null && Ref.method3().bridge$getWorld() != null) {
         Ref.method3().bridge$getWorld().bridge$disconnect();
         Ref.method3().bridge$loadWorld(null);
      }

      if (Ref.method3().bridge$getIntegratedServer() != null) {
         Ref.method3().bridge$getIntegratedServer().bridge$haltServer();
      }
   }
}
