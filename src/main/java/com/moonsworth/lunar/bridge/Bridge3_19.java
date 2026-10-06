package com.moonsworth.lunar.bridge;

import java.util.function.LongConsumer;

public interface Bridge3_19 {
   String bridge$serverIP();

   void bridge$setPingToServer(long var1);

   long bridge$getPingToServer();

   String getLunarServer();

   String bridge$getServerName();

   void setLunarServer(String var1);

   String bridge$getBase64Icon();

   String bridge$getPopulationInfo();

   void bridge$setPingCallback(LongConsumer var1);

   LongConsumer bridge$getPingCallback();

   void bridge$disableResourcePack();

   void bridge$enableResourcePack();

   boolean bridge$wasPinnedClicked();

   void bridge$setPinnedClicked(boolean var1);

   boolean bridge$isPinned();

   void bridge$setIsPinned(boolean var1);

   Bridge3_19 bridge$init(String var1);
}
