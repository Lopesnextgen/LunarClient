package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.paynow.v1.OpenPayNowEmbeddedCheckoutMessage;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump42;
import java.util.Set;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator13 extends ApolloModuleHandler {
   public Highlight3Iterator13() {
      super("pay_now", "PayNow");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OpenPayNowEmbeddedCheckoutMessage.class);
   }

   @Override
   public void method3(HighlightImpl_3 highlightImpl_3) {
      highlightImpl_3.unpack(OpenPayNowEmbeddedCheckoutMessage.class).ifPresent(var0 -> ThreadModuleDump42.method2(var0.getCheckoutToken()));
   }
}
