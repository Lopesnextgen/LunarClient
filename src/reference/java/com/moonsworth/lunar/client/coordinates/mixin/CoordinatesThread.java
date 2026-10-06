package com.moonsworth.lunar.client.coordinates.mixin;

import com.lunarclient.websocket.hostedworld.v1.ListHostedWorldsRequest;
import com.moonsworth.lunar.bridge.Bridge5Extension65;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class CoordinatesThread extends Thread {
   @Override
   public void run() {
      try {
         ThreadModuleDump63.method4().method81().method44(true);

         while (!this.isInterrupted() && ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension65) {
            method1();

            try {
               Thread.sleep(ThreadModuleDump63.method4().method81().getMultiplayerRefreshIntervalSeconds() * 1000L);
            } catch (InterruptedException var2) {
            }
         }
      } catch (Throwable var3) {
         throw var3;
      }
   }

   public static void method1() {
      ThreadModuleDump63.method5().ifPresent(var0 -> var0.method99().listHostedWorlds(null, ListHostedWorldsRequest.newBuilder().build(), var0x -> {
         if (!var0x.getHostedWorldsList().equals(ThreadModuleDump63.method4().method81().method41())) {
            ThreadModuleDump63.method4().method81().method42(var0x.getHostedWorldsList());
            ThreadModuleDump63.method4().method81().method44(true);
         }
      }));
   }
}
