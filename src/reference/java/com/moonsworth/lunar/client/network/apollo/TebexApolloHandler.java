package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.tebex.v1.OpenTebexEmbeddedCheckoutMessage;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.network.ipc.CheckoutUtils;
import java.util.Set;

public class TebexApolloHandler extends ApolloModuleHandler {
   public TebexApolloHandler() {
      super("tebex", "Tebex");
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(OpenTebexEmbeddedCheckoutMessage.class);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(OpenTebexEmbeddedCheckoutMessage.class).ifPresent(arg0 -> CheckoutUtils.method1(arg0.getBasketIdent(), arg0.getLocale()));
   }
}
