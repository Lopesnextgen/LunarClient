package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.team.v1.ResetTeamMembersMessage;
import com.lunarclient.apollo.team.v1.TeamMember;
import com.lunarclient.apollo.team.v1.UpdateTeamMembersMessage;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.account.TeamMemberManager;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.framework.Client;

public class TeamApolloHandler extends ApolloModuleHandler {
   public TeamApolloHandler() {
      super("team", "Team");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(UpdateTeamMembersMessage.class);
   }

   @Override
   protected void onEnable() {
      this.method3();
   }

   @Override
   protected void onDisable() {
      this.method3();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(UpdateTeamMembersMessage.class).ifPresent(var0 -> {
         if (ThreadModuleDump63.method7() != null) {
            Client var1x = Client.method109();
            TeamMemberManager var2 = var1x.method60();
            Map var3 = var2.method3();
            List var4 = var0.getMembersList();
            HashSet var5 = new HashSet(var4.size());
            UUID var6 = ThreadModuleDump63.method7().bridge$getUniqueID();

            for (TeamMember var8 : var4) {
               if (var8.hasPlayerUuid()) {
                  UUID var9 = NetworkTypes.fromProtobuf(var8.getPlayerUuid());
                  Memory var10 = (Memory)var3.get(var9);
                  boolean var11 = var9.equals(var6);
                  if (var11) {
                     var10 = var1x.method31();
                  }

                  if (var10 == null) {
                     var10 = new Memory(var9);
                     var2.method4(var10);
                  }

                  String var12 = var8.getAdventureJsonPlayerName();
                  if (!var12.isEmpty()) {
                     Component var13 = Rewindhandlers3.method4(var12);
                     if (var13 != null) {
                        var10.method43(var13);
                     }
                  }

                  if (var8.hasMarkerColor()) {
                     var10.setColor(var8.getMarkerColor().getColor());
                  }

                  if (!var11) {
                     if (var8.hasLocation()) {
                        ApolloLocation var16 = NetworkTypes.fromProtobuf(var8.getLocation());
                        Vec3Bridge var14 = Vec3Bridge.method2(var16.getX(), var16.getY(), var16.getZ());
                        String var15 = var16.getWorld();
                        var10.method42(var15.isEmpty() ? null : var15);
                        var10.method1(var14);
                     } else {
                        var10.method2();
                     }
                  }

                  var5.add(var9);
               }
            }

            var3.entrySet().stream().filter(var1xx -> !var5.contains(var1xx.getKey())).forEach(var1xx -> var3.remove(var1xx.getKey()));
         }
      });
      var1.unpack(ResetTeamMembersMessage.class).ifPresent(var1x -> this.method3());
   }

   private void method3() {
      Client.method109().method60().clear();
   }
}
