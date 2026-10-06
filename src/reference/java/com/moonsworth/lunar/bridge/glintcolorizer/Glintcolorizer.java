package com.moonsworth.lunar.bridge.glintcolorizer;

import com.moonsworth.lunar.bridge.Bridge6Extension;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2;
import java.io.File;
import java.util.List;

public interface Glintcolorizer {
   boolean bridge$isReady();

   int bridge$getPublishedPort();

   void bridge$publishWorldToLan(ItemcounterType2 var1, boolean var2, int var3);

   void bridge$updateLanWorld(ItemcounterType2 var1, boolean var2);

   void bridge$closeLanServer(int var1);

   void bridge$setAllowCheats(boolean var1);

   ItemcounterType2 bridge$getGameType();

   boolean bridge$isAllowCheats();

   ItemcounterType bridge$getDifficulty();

   void bridge$setDifficulty(ItemcounterType var1);

   List<Bridge6Extension> bridge$getPlayers();

   void bridge$haltServer();

   File bridge$getWorldDirectory();
}
