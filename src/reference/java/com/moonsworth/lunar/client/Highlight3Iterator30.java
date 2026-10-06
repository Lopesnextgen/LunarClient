package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.nickhider.v1.OverrideNickHiderMessage;
import com.lunarclient.apollo.nickhider.v1.ResetNickHiderMessage;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import java.util.Set;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.framework.Client;

public class Highlight3Iterator30 extends ApolloModuleHandler {
   public Highlight3Iterator30() {
      super("nick_hider", "NickHider");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideNickHiderMessage.class, ResetNickHiderMessage.class);
   }

   @Override
   public void method3(HighlightImpl_3 highlightImpl_3) {
      highlightImpl_3.unpack(OverrideNickHiderMessage.class).ifPresent(var0 -> {
         String var1x = var0.getNick();
         NickHider var2 = Client.method109().method40().method41();
         if (var2.method14(var1x)) {
            var2.setNickname(var1x);
         }
      });
      highlightImpl_3.unpack(ResetNickHiderMessage.class).ifPresent(var0 -> Client.method109().method40().method41().method13());
   }
}
