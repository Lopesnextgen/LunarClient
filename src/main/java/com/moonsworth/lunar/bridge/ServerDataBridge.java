package com.moonsworth.lunar.bridge;

import java.util.function.LongConsumer;

public interface ServerDataBridge {
   String bridge$serverIP();

   void bridge$setPingToServer(long number1);

   long bridge$getPingToServer();

   String getLunarServer();

   String bridge$getServerName();

   void setLunarServer(String text1);

   String bridge$getBase64Icon();

   String bridge$getPopulationInfo();

   void bridge$setPingCallback(LongConsumer longconsumer1);

   LongConsumer bridge$getPingCallback();

   void bridge$disableResourcePack();

   void bridge$enableResourcePack();

   boolean bridge$wasPinnedClicked();

   void bridge$setPinnedClicked(boolean flag1);

   boolean bridge$isPinned();

   void bridge$setIsPinned(boolean flag1);

   ServerDataBridge bridge$init(String text1);
}
