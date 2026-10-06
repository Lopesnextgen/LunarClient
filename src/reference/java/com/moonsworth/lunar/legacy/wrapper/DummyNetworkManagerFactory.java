package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.framework.Ref;
import io.netty.channel.SimpleChannelInboundHandler;

public class DummyNetworkManagerFactory {
   public DummyNetworkManagerFactory() {
   }

   public static SimpleChannelInboundHandler method1() {
      if (Ref.MC_VERSION >= 5) {
         return new Wrapper$Data5();
      } else {
         return (SimpleChannelInboundHandler)(Ref.MC_VERSION >= 1 ? new DummyNetworkManager() : new Wrapper$Data4());
      }
   }
}
