package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.richpresence.v1.OverrideServerRichPresenceMessage;
import com.lunarclient.apollo.richpresence.v1.ResetServerRichPresenceMessage;
import com.lunarclient.common.v1.ServerRichStatus;
import com.lunarclient.common.v1.ServerRichStatus.Source;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Set;

public class RichPresenceApolloHandler extends ApolloModuleHandler {
   public RichPresenceApolloHandler() {
      super("rich_presence", "RichPresence");
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideServerRichPresenceMessage.class, ResetServerRichPresenceMessage.class);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(OverrideServerRichPresenceMessage.class)
         .ifPresent(
            arg0 -> Ref.method5()
               .ifPresent(
                  arg1x -> {
                     ServerRichStatus serverrichstatus2 = ServerRichStatus.newBuilder()
                        .setSource(Source.SOURCE_APOLLO)
                        .setGameName(arg0.getGameName())
                        .setGameVariantName(arg0.getGameVariantName())
                        .setGameState(arg0.getGameState())
                        .setPlayerState(arg0.getPlayerState())
                        .setMapName(arg0.getMapName())
                        .setSubServer(arg0.getSubServer())
                        .setTeamCurrentSize(arg0.getTeamCurrentSize())
                        .setTeamMaxSize(arg0.getTeamMaxSize())
                        .build();
                     arg1x.method16(serverrichstatus2);
                  }
               )
         );
      highlightimpl_31.unpack(ResetServerRichPresenceMessage.class)
         .flatMap(arg0 -> Ref.method5())
         .ifPresent(arg0 -> arg0.method16(ServerRichStatus.getDefaultInstance()));
   }
}
