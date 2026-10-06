package com.moonsworth.lunar.client.util;

public class ThreadModuleDump10 {
   public static void disconnect() {
      if (ThreadModuleDump63.method3().bridge$getCurrentServerData() != null && ThreadModuleDump63.method3().bridge$getWorld() != null) {
         ThreadModuleDump63.method3().bridge$getWorld().bridge$disconnect();
         ThreadModuleDump63.method3().bridge$loadWorld(null);
      }

      if (ThreadModuleDump63.method3().bridge$getIntegratedServer() != null) {
         ThreadModuleDump63.method3().bridge$getIntegratedServer().bridge$haltServer();
      }
   }
}
