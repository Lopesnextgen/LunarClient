package com.moonsworth.lunar.client.network.hostedworld;

import com.lunarclient.websocket.hostedworld.v1.ListHostedWorldsRequest;
import com.moonsworth.lunar.bridge.GuiMultiplayerBridge;
import com.moonsworth.lunar.client.framework.Ref;

public class HostedWorldRefreshThread extends Thread {
   public HostedWorldRefreshThread() {
   }

   @Override
   public void run() {
      try {
         Ref.method4().method81().method44(true);

         while (!this.isInterrupted() && Ref.method3().bridge$getCurrentScreen() instanceof GuiMultiplayerBridge) {
            method1();

            try {
               Thread.sleep(Ref.method4().method81().getMultiplayerRefreshIntervalSeconds() * 1000L);
            } catch (InterruptedException interruptedexception2) {
            }
         }
      } catch (Throwable exception3) {
         throw exception3;
      }
   }

   public static void method1() {
      Ref.method5().ifPresent(arg0 -> arg0.method99().listHostedWorlds(null, ListHostedWorldsRequest.newBuilder().build(), arg0x -> {
         if (!arg0x.getHostedWorldsList().equals(Ref.method4().method81().method41())) {
            Ref.method4().method81().method42(arg0x.getHostedWorldsList());
            Ref.method4().method81().method44(true);
         }
      }));
   }
}
