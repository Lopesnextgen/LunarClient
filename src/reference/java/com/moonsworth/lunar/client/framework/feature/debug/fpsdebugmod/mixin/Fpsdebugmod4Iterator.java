package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import java.time.Duration;
import java.util.concurrent.Future;

public class Fpsdebugmod4Iterator implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
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
   public FpsdebugmodType method3() {
      return FpsdebugmodType.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod> method4() {
      return ThreadModuleDump37.method12(
         () -> new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod().method2("misc-data.txt", this.method5())
      );
   }

   private String method5() {
      StringBuilder var1 = new StringBuilder();
      var1.append("Long Lunar version: ").append(Client.method18()).append("\n");
      var1.append("Branch: ").append(LunarBuildData.field1).append("\n");
      var1.append("Proguard UUID: ").append(LunarBuildData.field5).append("\n");
      var1.append("Git hash: ").append(LunarBuildData.field3).append("\n");
      var1.append("Production: ").append(LunarBuildData.field4).append("\n");
      var1.append("Lunar version: ").append(LunarBuildData.field6).append("\n");
      var1.append("UI Branch: ").append(LunarBuildData.field7).append("\n");
      var1.append("UI Git Hash: ").append(LunarBuildData.field8).append("\n");
      var1.append("Java version: ").append(System.getProperty("java.version")).append("\n");
      var1.append("Minecraft version: ").append(Bridge.getMinecraftVersion().method45()).append("\n");
      var1.append("Launcher Version: ").append(ThreadModuleDump80.launcherVersion).append("\n");
      var1.append("Canary Token: ").append(ThreadModuleDump80.canaryToken).append("\n");
      var1.append("Username: ").append(ThreadModuleDump63.method4().method31().getName()).append("\n");
      var1.append("UUID: ").append(ThreadModuleDump63.method4().method31().method10()).append("\n");
      Bridge7_2 var2 = Bridge.method42().method85();
      var1.append("Gpu Renderer: ").append(var2.field3).append("\n");
      var1.append("Gpu Vendor: ").append(var2.field1).append("\n");
      var1.append("Gpu Backend: ").append(var2.field2).append("\n");
      var1.append("Gpu Version: ").append(var2.version).append("\n");
      IchorPipeline var3 = (IchorPipeline)IchorAPI.getPipeline(ThreadModuleDump63.class.getClassLoader()).orElseThrow();
      var1.append("Ichor Modules: ");

      for (String var5 : var3.method31().keySet()) {
         var1.append(var5).append(", ");
      }

      var1.append("\n");
      var1.append("Ichor External Mods: ");

      for (Ichor5Handler_2 var7 : var3.method18().toList()) {
         var1.append(var7.getId()).append(" ").append(var7.getVersion()).append(", ");
      }

      var1.append("\n");
      return var1.toString();
   }
}
