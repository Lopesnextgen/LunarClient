package com.moonsworth.lunar.client.network.server;

public interface PingEntry<S extends PingEntry> {
   float getValue();

   String method1();

   S method2(S var1);
}
