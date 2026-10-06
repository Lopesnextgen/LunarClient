package com.moonsworth.lunar.client.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadModuleDump79 {
   public static int findFreePort() {
      try (ServerSocket var0 = new ServerSocket(0)) {
         return var0.getLocalPort();
      } catch (IOException var6) {
         return 25564;
      }
   }

   public static boolean isPortOpen(int var0) {
      try {
         new Socket("localhost", var0).close();
         return true;
      } catch (IOException var2) {
         return false;
      }
   }
}
