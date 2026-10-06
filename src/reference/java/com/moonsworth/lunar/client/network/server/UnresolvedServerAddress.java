package com.moonsworth.lunar.client.network.server;

import com.google.common.net.HostAndPort;
import com.moonsworth.lunar.client.util.LunarLogger;
import java.net.IDN;
import lombok.Generated;

public final class UnresolvedServerAddress {
   private final HostAndPort field1;
   private static final UnresolvedServerAddress field2 = new UnresolvedServerAddress(HostAndPort.fromParts("server.invalid", 25565));

   public UnresolvedServerAddress(String text, int number2) {
      this(HostAndPort.fromParts(text, number2));
   }

   public UnresolvedServerAddress(HostAndPort hostandport1) {
      this.field1 = hostandport1;
   }

   public String getHost() {
      try {
         return IDN.toASCII(this.field1.getHostText());
      } catch (IllegalArgumentException illegalargumentexception2) {
         return "";
      }
   }

   public int getPort() {
      return this.field1.getPort();
   }

   public static UnresolvedServerAddress method1(String text0) {
      if (text0 == null) {
         return field2;
      }

      try {
         HostAndPort hostandport1 = HostAndPort.fromString(text0).withDefaultPort(25565);
         return hostandport1.getHostText().isEmpty() ? field2 : new UnresolvedServerAddress(hostandport1);
      } catch (IllegalArgumentException illegalargumentexception2) {
         LunarLogger.method3("Failed to parse URL {}", new Object[]{text0, illegalargumentexception2});
         return field2;
      }
   }

   public static boolean method2(String text0) {
      try {
         HostAndPort hostandport1 = HostAndPort.fromString(text0);
         String text2 = hostandport1.getHostText();
         if (!text2.isEmpty()) {
            IDN.toASCII(text2);
            return true;
         }
      } catch (IllegalArgumentException illegalargumentexception3) {
      }

      return false;
   }

   static int method3(String text0) {
      try {
         return Integer.parseInt(text0.trim());
      } catch (Exception exception2) {
         return 25565;
      }
   }

   @Generated
   @Override
   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else if (!(object instanceof UnresolvedServerAddress pkg32)) {
         return false;
      } else {
         HostAndPort hostandport3 = this.field1;
         HostAndPort hostandport4 = pkg32.field1;
         return hostandport3 == null ? hostandport4 == null : hostandport3.equals(hostandport4);
      }
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      byte number2 = 1;
      HostAndPort hostandport3 = this.field1;
      return number2 * 59 + (hostandport3 == null ? 43 : hostandport3.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "ServerAddress(hostAndPort=" + this.field1 + ")";
   }
}
