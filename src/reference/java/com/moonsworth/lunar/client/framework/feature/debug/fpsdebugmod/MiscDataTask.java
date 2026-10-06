package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.LaunchOptions;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import java.time.Duration;
import java.util.concurrent.Future;

public class MiscDataTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   public MiscDataTask() {
   }

   @Override
   public String name() {
      return "misc-data";
   }

   @Override
   public boolean method1() {
      return true;
   }

   @Override
   public Duration method2() {
      return Duration.ofMillis(100L);
   }

   @Override
   public FpsDebugPhase method3() {
      return FpsDebugPhase.PRE_COLLECT;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive> method4() {
      return BackgroundExecutor.method12(
         () -> new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive().method2("misc-data.txt", this.method5())
      );
   }

   private String method5() {
      StringBuilder builder1 = new StringBuilder();
      builder1.append("Long Lunar version: ").append(Client.method18()).append("\n");
      builder1.append("Branch: ").append(LunarBuildData.field1).append("\n");
      builder1.append("Proguard UUID: ").append(LunarBuildData.field5).append("\n");
      builder1.append("Git hash: ").append(LunarBuildData.field3).append("\n");
      builder1.append("Production: ").append(LunarBuildData.field4).append("\n");
      builder1.append("Lunar version: ").append(LunarBuildData.field6).append("\n");
      builder1.append("UI Branch: ").append(LunarBuildData.field7).append("\n");
      builder1.append("UI Git Hash: ").append(LunarBuildData.field8).append("\n");
      builder1.append("Java version: ").append(System.getProperty("java.version")).append("\n");
      builder1.append("Minecraft version: ").append(Bridge.getMinecraftVersion().method45()).append("\n");
      builder1.append("Launcher Version: ").append(LaunchOptions.field7).append("\n");
      builder1.append("Canary Token: ").append(LaunchOptions.field5).append("\n");
      builder1.append("Username: ").append(Ref.method4().method31().getName()).append("\n");
      builder1.append("UUID: ").append(Ref.method4().method31().method10()).append("\n");
      Bridge7_2 bridge7_22 = Bridge.method42().method85();
      builder1.append("Gpu Renderer: ").append(bridge7_22.field3).append("\n");
      builder1.append("Gpu Vendor: ").append(bridge7_22.field1).append("\n");
      builder1.append("Gpu Backend: ").append(bridge7_22.field2).append("\n");
      builder1.append("Gpu Version: ").append(bridge7_22.version).append("\n");
      IchorPipeline ichor73 = (IchorPipeline)IchorAPI.getPipeline(Ref.class.getClassLoader()).orElseThrow();
      builder1.append("Ichor Modules: ");

      for (String text5 : ichor73.method31().keySet()) {
         builder1.append(text5).append(", ");
      }

      builder1.append("\n");
      builder1.append("Ichor External Mods: ");

      for (Ichor5Handler_2 ichor5handler_27 : ichor73.method18().toList()) {
         builder1.append(ichor5handler_27.getId()).append(" ").append(ichor5handler_27.getVersion()).append(", ");
      }

      builder1.append("\n");
      return builder1.toString();
   }
}
