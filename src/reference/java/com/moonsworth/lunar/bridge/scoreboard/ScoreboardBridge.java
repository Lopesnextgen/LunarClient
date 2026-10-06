package com.moonsworth.lunar.bridge.scoreboard;

import java.util.Collection;
import org.jetbrains.annotations.Nullable;

public interface ScoreboardBridge {
   @Nullable
   ScorePlayerTeamBridge bridge$getPlayersTeam(String text1);

   ScoreboardObjectiveBridge bridge$getObjectiveInDisplaySlot(int number1);

   Collection<ScoreBridge> bridge$getSortedScores(ScoreboardObjectiveBridge lighting1);

   @Nullable
   ScoreBridge bridge$getValueFromObjective(String text1, ScoreboardObjectiveBridge lighting2);
}
