package com.moonsworth.lunar.client.network.transfer;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.network.server.UnresolvedServerAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.spi.NamingManager;

public class TransferSrvResolver {
   private static final String field1 = "mc_transfer_accept_from";

   public TransferSrvResolver() {
   }

   public static boolean method1(String text0, String text1) {
      String[] items2 = method2(text0).split("\\.");
      String[] items3 = method2(text1).split("\\.");
      if (items3.length == 0) {
         return false;
      }

      int number4 = Math.max(items3.length, items2.length);
      int number5 = Math.abs(items3.length - items2.length);

      for (int index6 = 0; index6 < number4; index6++) {
         String text7 = items3[Math.min(index6, items3.length - 1)];
         String text8 = items2[Math.min(index6, items2.length - 1)];
         if (index6 > items3.length - 1) {
            return text8.equalsIgnoreCase("*") && number5 <= 1;
         }

         if (!text8.equalsIgnoreCase(text7) && !text8.equals("*")) {
            return false;
         }
      }

      return true;
   }

   private static String method2(String text0) {
      return new StringBuffer(text0).reverse().toString();
   }

   private static String method3(String text0) {
      if (text0 != null && !text0.isEmpty() && UnresolvedServerAddress.method2(text0)) {
         String text1 = UnresolvedServerAddress.method1(text0).getHost();
         if (text1 != null && !text1.isEmpty()) {
            for (int index2 = 0; index2 < text1.length(); index2++) {
               char character3 = text1.charAt(index2);
               if (character3 == ':' || character3 == '/' || character3 == '\\' || character3 == '?' || character3 == '#' || character3 == '@') {
                  return null;
               }
            }

            return text1;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public static void method4(String text0, Consumer<Set<String>> consumer1) {
      new Thread(() -> {
         try {
            Class.forName("com.sun.jndi.dns.DnsContextFactory");
            String text2 = method3(text0);
            if (text2 == null || text2.isEmpty()) {
               consumer1.accept(null);
               return;
            }

            Hashtable hashtable3 = new Hashtable();
            hashtable3.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            hashtable3.put("java.naming.provider.url", "dns:");
            hashtable3.put("com.sun.jndi.dns.timeout.retries", "1");
            DirContext dircontext4 = (DirContext)NamingManager.getInitialContext(hashtable3);

            try {
               String text5 = text2;

               HashSet set6;
               for (set6 = new HashSet(); text5.indexOf(".") < text5.lastIndexOf(".") && set6.isEmpty(); text5 = text5.substring(text5.indexOf(".") + 1)) {
                  method5(text5, set6, dircontext4);
               }

               if (set6.isEmpty()) {
                  method5(text5, set6, dircontext4);
               }

               consumer1.accept(set6);
            } catch (Throwable exception7) {
               consumer1.accept(null);
            }
         } catch (Throwable exception8) {
            LunarLogger.error("Failed to initialize SRV redirect resolved, some servers might not work", exception8);
         }
      }).start();
   }

   private static void method5(String text0, Set<String> set, DirContext dircontext2) {
      try {
         Attributes attributes3 = dircontext2.getAttributes(text0, new String[]{"TXT"});
         Attribute attribute4 = attributes3.get("TXT");
         if (attribute4 != null) {
            Iterator iterator5 = attribute4.getAll().asIterator();

            while (iterator5.hasNext()) {
               Object obj6 = iterator5.next();
               if (obj6 != null) {
                  String[] items7 = obj6.toString().split("=");
                  if (items7.length == 2 && items7[0].equalsIgnoreCase("mc_transfer_accept_from")) {
                     String[] items8 = items7[1].split(",");
                     set.addAll(Arrays.stream(items8).collect(Collectors.toSet()));
                  }
               }
            }
         }
      } catch (NamingException namingexception9) {
      }
   }
}
