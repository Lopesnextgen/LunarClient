package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.paynow.v1.OpenPayNowEmbeddedCheckoutMessage;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.network.ipc.CheckoutUtils;
import java.util.Set;

public class PayNowApolloHandler extends ApolloModuleHandler {
   public PayNowApolloHandler() {
      super("pay_now", "PayNow");
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(OpenPayNowEmbeddedCheckoutMessage.class);
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(OpenPayNowEmbeddedCheckoutMessage.class).ifPresent(arg0 -> CheckoutUtils.method2(arg0.getCheckoutToken()));
   }
}
