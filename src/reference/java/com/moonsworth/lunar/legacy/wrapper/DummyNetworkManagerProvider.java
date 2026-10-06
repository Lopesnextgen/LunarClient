package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.netty.channel.SimpleChannelInboundHandler;

public class DummyNetworkManagerProvider {
   public static SimpleChannelInboundHandler method1() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return new Wrapper$Data5();
      } else {
         return (SimpleChannelInboundHandler)(ThreadModuleDump63.MC_VERSION >= 1 ? new Wrapper$Data6() : new Wrapper$Data4());
      }
   }
}
