package com.moonsworth.lunar.client.pkg;

import com.google.common.net.HostAndPort;
import com.moonsworth.lunar.client.util.Slayer;
import java.net.IDN;
import lombok.Generated;

public final class Pkg3 {
   private final HostAndPort field1;
   private static final Pkg3 field2 = new Pkg3(HostAndPort.fromParts("server.invalid", 25565));

   public Pkg3(String var1, int var2) {
      this(HostAndPort.fromParts(var1, var2));
   }

   public Pkg3(HostAndPort var1) {
      this.field1 = var1;
   }

   public String getHost() {
      try {
         return IDN.toASCII(this.field1.getHostText());
      } catch (IllegalArgumentException var2) {
         return "";
      }
   }

   public int getPort() {
      return this.field1.getPort();
   }

   public static Pkg3 method1(String var0) {
      if (var0 == null) {
         return field2;
      }

      try {
         HostAndPort var1 = HostAndPort.fromString(var0).withDefaultPort(25565);
         return var1.getHostText().isEmpty() ? field2 : new Pkg3(var1);
      } catch (IllegalArgumentException var2) {
         Slayer.method3("Failed to parse URL {}", var0, var2);
         return field2;
      }
   }

   public static boolean method2(String var0) {
      try {
         HostAndPort var1 = HostAndPort.fromString(var0);
         String var2 = var1.getHostText();
         if (!var2.isEmpty()) {
            IDN.toASCII(var2);
            return true;
         }
      } catch (IllegalArgumentException var3) {
      }

      return false;
   }

   static int method3(String var0) {
      try {
         return Integer.parseInt(var0.trim());
      } catch (Exception var2) {
         return 25565;
      }
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Pkg3 var2)) {
         return false;
      } else {
         HostAndPort var3 = this.field1;
         HostAndPort var4 = var2.field1;
         return var3 == null ? var4 == null : var3.equals(var4);
      }
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      byte var2 = 1;
      HostAndPort var3 = this.field1;
      return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "ServerAddress(hostAndPort=" + this.field1 + ")";
   }
}
