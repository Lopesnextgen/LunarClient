package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.richpresence.v1.OverrideServerRichPresenceMessage;
import com.lunarclient.apollo.richpresence.v1.ResetServerRichPresenceMessage;
import com.lunarclient.common.v1.ServerRichStatus;
import com.lunarclient.common.v1.ServerRichStatus.Source;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Set;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator7 extends ApolloModuleHandler {
   public Highlight3Iterator7() {
      super("rich_presence", "RichPresence");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideServerRichPresenceMessage.class, ResetServerRichPresenceMessage.class);
   }

   @Override
   public void method3(HighlightImpl_3 highlightImpl_3) {
      highlightImpl_3.unpack(OverrideServerRichPresenceMessage.class)
         .ifPresent(
            var0 -> ThreadModuleDump63.method5()
               .ifPresent(
                  var1x -> {
                     ServerRichStatus var2 = ServerRichStatus.newBuilder()
                        .setSource(Source.SOURCE_APOLLO)
                        .setGameName(var0.getGameName())
                        .setGameVariantName(var0.getGameVariantName())
                        .setGameState(var0.getGameState())
                        .setPlayerState(var0.getPlayerState())
                        .setMapName(var0.getMapName())
                        .setSubServer(var0.getSubServer())
                        .setTeamCurrentSize(var0.getTeamCurrentSize())
                        .setTeamMaxSize(var0.getTeamMaxSize())
                        .build();
                     var1x.method16(var2);
                  }
               )
         );
      highlightImpl_3.unpack(ResetServerRichPresenceMessage.class)
         .flatMap(var0 -> ThreadModuleDump63.method5())
         .ifPresent(var0 -> var0.method16(ServerRichStatus.getDefaultInstance()));
   }
}
