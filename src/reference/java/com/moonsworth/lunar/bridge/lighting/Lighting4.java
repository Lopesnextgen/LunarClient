package com.moonsworth.lunar.bridge.lighting;

import java.util.Collection;
import org.jetbrains.annotations.Nullable;

public interface Lighting4 {
   @Nullable
   Lighting3 bridge$getPlayersTeam(String var1);

   Lighting bridge$getObjectiveInDisplaySlot(int var1);

   Collection<Lighting2> bridge$getSortedScores(Lighting var1);

   @Nullable
   Lighting2 bridge$getValueFromObjective(String var1, Lighting var2);
}
