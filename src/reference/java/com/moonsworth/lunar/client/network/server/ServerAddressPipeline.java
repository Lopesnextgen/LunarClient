package com.moonsworth.lunar.client.network.server;

import java.util.Optional;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ServerAddressPipeline {
   public static ServerAddressPipeline field1;
   public static Component field2;
   private final ServerAddressResolver field3;
   private final ServerSrvLookup field4;
   private final ServerBlocklist field5;

   ServerAddressPipeline(ServerAddressResolver pkg41, ServerSrvLookup pkg52, ServerBlocklist pkg23) {
      this.field3 = pkg41;
      this.field4 = pkg52;
      this.field5 = pkg23;
   }

   public Optional<ResolvedServerAddress> method1(UnresolvedServerAddress pkg31) {
      Optional optional2 = this.method2(pkg31);
      Optional optional3 = this.field4.lookupRedirect(pkg31);
      if (optional3.isPresent()) {
         optional2 = this.method2((UnresolvedServerAddress)optional3.get());
      }

      return optional2;
   }

   private Optional<ResolvedServerAddress> method2(UnresolvedServerAddress pkg31) {
      return Optional.of(pkg31).filter(this.field5::method2).flatMap(this.field3::resolve).filter(this.field5::method1);
   }

   @Generated
   public ServerBlocklist method3() {
      return this.field5;
   }

   static {
      try {
         field1 = new ServerAddressPipeline(ServerAddressResolver.SYSTEM, ServerSrvLookup.createDnsSrvRedirectHandler(), ServerBlocklist.method3());
      } catch (Throwable exception1) {
         exception1.printStackTrace();
      }

      field2 = Component.text("This server is blocked by Mojang!").color(NamedTextColor.RED);
   }
}
