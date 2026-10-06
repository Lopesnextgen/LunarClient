package com.moonsworth.lunar.client.network.server;

import java.net.InetSocketAddress;

public interface ResolvedServerAddress {
   String method1();

   String method2();

   int getPort();

   InetSocketAddress method3();

   static ResolvedServerAddress method4(final InetSocketAddress inetsocketaddress0) {
      return new ResolvedServerAddress() {
         @Override
         public String method1() {
            return inetsocketaddress0.getAddress().getHostName();
         }

         @Override
         public String method2() {
            return inetsocketaddress0.getAddress().getHostAddress();
         }

         @Override
         public int getPort() {
            return inetsocketaddress0.getPort();
         }

         @Override
         public InetSocketAddress method3() {
            return inetsocketaddress0;
         }
      };
   }
}
