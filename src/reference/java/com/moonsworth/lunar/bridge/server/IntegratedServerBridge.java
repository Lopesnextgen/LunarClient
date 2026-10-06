package com.moonsworth.lunar.bridge.server;

import com.moonsworth.lunar.bridge.Bridge6Extension;
import com.moonsworth.lunar.bridge.world.DifficultyBridge;
import com.moonsworth.lunar.bridge.world.GameTypeBridge;
import java.io.File;
import java.util.List;

public interface IntegratedServerBridge {
   boolean bridge$isReady();

   int bridge$getPublishedPort();

   void bridge$publishWorldToLan(GameTypeBridge itemcountertype21, boolean flag2, int number3);

   void bridge$updateLanWorld(GameTypeBridge itemcountertype21, boolean flag2);

   void bridge$closeLanServer(int number1);

   void bridge$setAllowCheats(boolean flag1);

   GameTypeBridge bridge$getGameType();

   boolean bridge$isAllowCheats();

   DifficultyBridge bridge$getDifficulty();

   void bridge$setDifficulty(DifficultyBridge itemcountertype1);

   List<Bridge6Extension> bridge$getPlayers();

   void bridge$haltServer();

   File bridge$getWorldDirectory();
}
