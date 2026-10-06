package com.moonsworth.lunar.client.pkg;

import com.moonsworth.lunar.client.util.Slayer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@FunctionalInterface
public interface Pkg4 {
   Pkg4 SYSTEM = var0 -> {
      try {
         InetAddress var1 = InetAddress.getByName(var0.getHost());
         return Optional.of(Pkg.method4(new InetSocketAddress(var1, var0.getPort())));
      } catch (UnknownHostException var2) {
         Slayer.method1("Couldn't resolve server %s address", var0.getHost(), var2);
         return Optional.empty();
      }
   };

   Optional<Pkg> resolve(Pkg3 var1);
}
