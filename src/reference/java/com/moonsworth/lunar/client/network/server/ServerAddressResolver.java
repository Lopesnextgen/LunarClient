package com.moonsworth.lunar.client.network.server;

import com.moonsworth.lunar.client.util.LunarLogger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@FunctionalInterface
public interface ServerAddressResolver {
   ServerAddressResolver SYSTEM = arg0 -> {
      try {
         InetAddress inetaddress1 = InetAddress.getByName(arg0.getHost());
         return Optional.of(ResolvedServerAddress.method4(new InetSocketAddress(inetaddress1, arg0.getPort())));
      } catch (UnknownHostException unknownhostexception2) {
         LunarLogger.method1("Couldn't resolve server %s address", new Object[]{arg0.getHost(), unknownhostexception2});
         return Optional.empty();
      }
   };

   Optional<ResolvedServerAddress> resolve(UnresolvedServerAddress pkg31);
}
