package com.moonsworth.lunar.client.framework.feature.markers.mixin;

import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.lighting.Lighting3;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.account.TeamMemberManager;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Objects;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   HYPIXEL_PARTY(
      "hypixelParty",
      (var0, var1, var2) -> ThreadModuleDump63.method4().method40().method87().method55().method7().<Set>map(Rewindhandlers3::method1).map(var1x -> {
         boolean var2x = var1x.contains(var1.getName());
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9 && !var2x) {
            String var3 = String.join(",", var1x);
            Slayer.method3("Hypixel Party Members: %s", new Object[]{var3});
         }

         return var2x;
      }).orElse(false)
   ),
   LUNAR_FRIENDS("lunarFriends", (var0, var1, var2) -> ThreadModuleDump63.method4().method50().method3(var1.getId())),
   APOLLO("apolloTeams", (var0, var1, var2) -> {
      TeamMemberManager var3 = Client.method109().method60();
      Memory var4 = (Memory)var3.method3().get(var1.getId());
      if (var4 == null) {
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            Slayer.method3("[Markers] Apollo team check failed, no team member %s", new Object[]{var1.getId()});
         }

         return false;
      } else {
         if (var3.method6(var4)) {
            return true;
         }

         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            Slayer.method3("[Markers] Apollo team check failed %s (%s <-> %s)", new Object[]{var4.method10(), var4.method23(), Client.method109().getWorld()});
         }

         return false;
      }
   }),
   SCOREBOARD("scoreboardTeams", (var0, var1, var2) -> {
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var3 == null) {
         return false;
      }

      Lighting4 var4 = var3.bridge$getScoreboard();
      if (var4 == null) {
         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            Slayer.method3("[Markers] Scoreboard team check failed, sb is null", new Object[0]);
         }

         return false;
      } else {
         Lighting3 var5 = var4.bridge$getPlayersTeam(ThreadModuleDump63.method7().bridge$getName());
         Lighting3 var6 = var4.bridge$getPlayersTeam(var1.getName());
         if (var5 != null && var6 != null) {
            return Objects.equals(var5, var6);
         }

         if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
            Slayer.method3("[Markers] Scoreboard team check failed, team is null %s %s %s", new Object[]{var1.getId(), var5 == null, var6 == null});
         }

         return false;
      }
   }),
   NAME_COLOR("colorTeams", (var0, var1, var2) -> {
      ClientPacketListenerBridge var3 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
      if (var3 == null) {
         return false;
      }

      Bridge2_33 var4 = var3.bridge$getPlayerInfo(ThreadModuleDump63.method7().bridge$getUniqueID());
      if (var4 == null) {
         return false;
      }

      Component var5 = AdventureTextBridge.getFirstColoredComponent(var4.bridge$formatName());
      Component var6 = AdventureTextBridge.getFirstColoredComponent(var0.bridge$formatName());
      if (var5 != null && var6 != null) {
         return Objects.equals(var5.style(), var6.style());
      }

      if (com.moonsworth.lunar.client.mod.render.markers.Markers.field9) {
         Slayer.method3("[Markers] Name color team check failed, components null %s %s %s", new Object[]{var1.getId(), var5 == null, var6 == null});
      }

      return false;
   });

   private final String id;
   private final Markers3 detectionFunction;

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   public String id() {
      return this.id;
   }

   @Generated
   Gui2Extension(String var3, Markers3 var4) {
      this.id = var3;
      this.detectionFunction = var4;
   }

   @Generated
   public Markers3 getDetectionFunction() {
      return this.detectionFunction;
   }
}
