package com.moonsworth.lunar.bridge.stats;

import com.moonsworth.lunar.bridge.Bridge6_10;

public interface StatisticsFileBridge {
   void bridge$setValueFromPacket(Bridge6_10 bridge6_101, StatBaseBridge hitbox22, int number3);

   void bridge$increment(Bridge6_10 bridge6_101, StatBaseBridge hitbox22, int number3);

   void bridge$recordStat(StatBaseBridge hitbox21, int number2);
}
