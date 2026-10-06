package com.moonsworth.lunar.client.pkg;

import java.net.InetSocketAddress;

public interface Pkg {
   String method1();

   String method2();

   int getPort();

   InetSocketAddress method3();

   static Pkg method4(final InetSocketAddress inetSocketAddress) {
      return new Pkg() {
         @Override
         public String method1() {
            return inetSocketAddress.getAddress().getHostName();
         }

         @Override
         public String method2() {
            return inetSocketAddress.getAddress().getHostAddress();
         }

         @Override
         public int getPort() {
            return inetSocketAddress.getPort();
         }

         @Override
         public InetSocketAddress method3() {
            return inetSocketAddress;
         }
      };
   }
}
