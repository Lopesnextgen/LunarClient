package com.moonsworth.lunar.bridge;

public interface ServerListBridge {
   void bridge$load();

   void bridge$add(ServerDataBridge bridge3_191);

   void bridge$save();

   boolean bridge$containsUnpinnedAddress(String text1);

   boolean bridge$canSwapServers(int number1, int number2);
}
