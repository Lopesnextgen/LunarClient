package com.moonsworth.lunar.client.pkg;

import com.moonsworth.lunar.client.util.Slayer;
import java.util.Hashtable;
import java.util.Optional;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

@FunctionalInterface
public interface Pkg5 {
   Pkg5 EMPTY = var0 -> Optional.empty();

   Optional<Pkg3> lookupRedirect(Pkg3 var1);

   static Pkg5 createDnsSrvRedirectHandler() {
      InitialDirContext var0;
      try {
         Class.forName("com.sun.jndi.dns.DnsContextFactory");
         Hashtable var1 = new Hashtable();
         var1.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
         var1.put("java.naming.provider.url", "dns:");
         var1.put("com.sun.jndi.dns.timeout.retries", "1");
         var0 = new InitialDirContext(var1);
      } catch (Throwable var2) {
         Slayer.error("Failed to initialize SRV redirect resolved, some servers might not work", var2);
         return EMPTY;
      }

      return var1x -> {
         if (var1x.getPort() == 25565) {
            try {
               InitialDirContext var2x = new InitialDirContext(var0.getEnvironment());
               Attributes var3 = var2x.getAttributes("_minecraft._tcp." + var1x.getHost(), new String[]{"SRV"});
               var2x.close();
               Attribute var4 = var3.get("srv");
               if (var4 != null) {
                  String[] var5 = var4.get().toString().split(" ", 4);
                  return Optional.of(new Pkg3(var5[3], Pkg3.method3(var5[2])));
               }
            } catch (Throwable var6) {
            }
         }

         return Optional.empty();
      };
   }
}
