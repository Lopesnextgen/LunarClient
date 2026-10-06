package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.nickhider.v1.OverrideNickHiderMessage;
import com.lunarclient.apollo.nickhider.v1.ResetNickHiderMessage;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import java.util.Set;
import com.moonsworth.lunar.client.framework.Client;

public class NickHiderApolloHandler extends ApolloModuleHandler {
   public NickHiderApolloHandler() {
      super("nick_hider", "NickHider");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideNickHiderMessage.class, ResetNickHiderMessage.class);
   }

   @Override
   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(OverrideNickHiderMessage.class).ifPresent(arg0 -> {
         String text1x = arg0.getNick();
         NickHider nickhider2 = Client.method109().method40().method41();
         if (nickhider2.method14(text1x)) {
            nickhider2.setNickname(text1x);
         }
      });
      highlightimpl_31.unpack(ResetNickHiderMessage.class).ifPresent(arg0 -> Client.method109().method40().method41().method13());
   }
}
