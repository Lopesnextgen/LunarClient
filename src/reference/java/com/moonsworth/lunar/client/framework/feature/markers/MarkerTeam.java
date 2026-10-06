package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.scoreboard.ScorePlayerTeamBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.account.TeamMemberManager;
import com.moonsworth.lunar.client.framework.listener.PartyState;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Objects;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public enum MarkerTeam implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   HYPIXEL_PARTY(
      "hypixelParty",
      (arg0, arg1, arg2) -> Ref.method4().method40().method87().method55().method7().<Set>map(PartyState::method1).map(arg1x -> {
         boolean flag2x = arg1x.contains(arg1.getName());
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9 && !flag2x) {
            String text3 = String.join(",", arg1x);
            LunarLogger.method3("Hypixel Party Members: %s", new Object[]{text3});
         }

         return flag2x;
      }).orElse(false)
   ),
   LUNAR_FRIENDS("lunarFriends", (arg0, arg1, arg2) -> Ref.method4().method50().method3(arg1.getId())),
   APOLLO("apolloTeams", (arg0, arg1, arg2) -> {
      TeamMemberManager foghandler233 = Client.method109().method60();
      Memory memory4 = (Memory)foghandler233.method2().get(arg1.getId());
      if (memory4 == null) {
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            LunarLogger.method3("[MarkerModel] Apollo team check failed, no team member %s", new Object[]{arg1.getId()});
         }

         return false;
      } else {
         if (foghandler233.method6(memory4)) {
            return true;
         }

         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            LunarLogger.method3("[MarkerModel] Apollo team check failed %s (%s <-> %s)", new Object[]{memory4.method10(), memory4.method23(), Client.method109().getWorld()});
         }

         return false;
      }
   }),
   SCOREBOARD("scoreboardTeams", (arg0, arg1, arg2) -> {
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (itemcounter6extension3 == null) {
         return false;
      }

      ScoreboardBridge lighting44 = itemcounter6extension3.bridge$getScoreboard();
      if (lighting44 == null) {
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            LunarLogger.method3("[MarkerModel] Scoreboard team check failed, sb is null", new Object[0]);
         }

         return false;
      } else {
         ScorePlayerTeamBridge lighting35 = lighting44.bridge$getPlayersTeam(Ref.method7().bridge$getName());
         ScorePlayerTeamBridge lighting36 = lighting44.bridge$getPlayersTeam(arg1.getName());
         if (lighting35 != null && lighting36 != null) {
            return Objects.equals(lighting35, lighting36);
         }

         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            LunarLogger.method3("[MarkerModel] Scoreboard team check failed, team is null %s %s %s", new Object[]{arg1.getId(), lighting35 == null, lighting36 == null});
         }

         return false;
      }
   }),
   NAME_COLOR("colorTeams", (arg0, arg1, arg2) -> {
      NetHandlerPlayClientBridge bridgeextension_73 = Ref.method3().bridge$getClientPacketListener();
      if (bridgeextension_73 == null) {
         return false;
      }

      PlayerInfoBridge bridge2_334 = bridgeextension_73.bridge$getPlayerInfo(Ref.method7().bridge$getUniqueID());
      if (bridge2_334 == null) {
         return false;
      }

      Component component5 = TextBridge.getFirstColoredComponent(bridge2_334.bridge$formatName());
      Component component6 = TextBridge.getFirstColoredComponent(arg0.bridge$formatName());
      if (component5 != null && component6 != null) {
         return Objects.equals(component5.style(), component6.style());
      }

      if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
         LunarLogger.method3("[MarkerModel] Name color team check failed, components null %s %s %s", new Object[]{arg1.getId(), component5 == null, component6 == null});
      }

      return false;
   });

   private final String id;
   private final MarkerDetectionFunction detectionFunction;

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   public String id() {
      return this.id;
   }

   @Generated
   MarkerTeam(String text3, MarkerDetectionFunction markers34) {
      this.id = text3;
      this.detectionFunction = markers34;
   }

   @Generated
   public MarkerDetectionFunction getDetectionFunction() {
      return this.detectionFunction;
   }
}
