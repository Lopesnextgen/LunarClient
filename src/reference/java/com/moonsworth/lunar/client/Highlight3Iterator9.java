package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.tebex.v1.OpenTebexEmbeddedCheckoutMessage;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump42;
import java.util.Set;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator9 extends ApolloModuleHandler {
   public Highlight3Iterator9() {
      super("tebex", "Tebex");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OpenTebexEmbeddedCheckoutMessage.class);
   }

   @Override
   public void method3(HighlightImpl_3 highlightImpl_3) {
      highlightImpl_3.unpack(OpenTebexEmbeddedCheckoutMessage.class).ifPresent(var0 -> ThreadModuleDump42.method1(var0.getBasketIdent(), var0.getLocale()));
   }
}
