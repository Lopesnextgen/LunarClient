package com.moonsworth.lunar.client.util.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PortUtils {
   public PortUtils() {
   }

   public static int method1() {
      try (ServerSocket serversocket0 = new ServerSocket(0)) {
         return serversocket0.getLocalPort();
      } catch (IOException exception6) {
         return 25564;
      }
   }

   public static boolean method2(int value) {
      try {
         new Socket("localhost", value).close();
         return true;
      } catch (IOException exception2) {
         return false;
      }
   }
}
