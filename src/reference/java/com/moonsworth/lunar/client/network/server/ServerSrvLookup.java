package com.moonsworth.lunar.client.network.server;

import com.moonsworth.lunar.client.util.LunarLogger;
import java.util.Hashtable;
import java.util.Optional;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

@FunctionalInterface
public interface ServerSrvLookup {
   ServerSrvLookup EMPTY = arg0 -> Optional.empty();

   Optional<UnresolvedServerAddress> lookupRedirect(UnresolvedServerAddress pkg31);

   static ServerSrvLookup createDnsSrvRedirectHandler() {
      InitialDirContext initialdircontext0;
      try {
         Class.forName("com.sun.jndi.dns.DnsContextFactory");
         Hashtable hashtable1 = new Hashtable();
         hashtable1.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
         hashtable1.put("java.naming.provider.url", "dns:");
         hashtable1.put("com.sun.jndi.dns.timeout.retries", "1");
         initialdircontext0 = new InitialDirContext(hashtable1);
      } catch (Throwable exception2) {
         LunarLogger.error("Failed to initialize SRV redirect resolved, some servers might not work", exception2);
         return EMPTY;
      }

      return arg1x -> {
         if (arg1x.getPort() == 25565) {
            try {
               InitialDirContext initialdircontext2x = new InitialDirContext(initialdircontext0.getEnvironment());
               Attributes attributes3 = initialdircontext2x.getAttributes("_minecraft._tcp." + arg1x.getHost(), new String[]{"SRV"});
               initialdircontext2x.close();
               Attribute attribute4 = attributes3.get("srv");
               if (attribute4 != null) {
                  String[] items5 = attribute4.get().toString().split(" ", 4);
                  return Optional.of(new UnresolvedServerAddress(items5[3], UnresolvedServerAddress.method3(items5[2])));
               }
            } catch (Throwable exception6) {
            }
         }

         return Optional.empty();
      };
   }
}
