package com.moonsworth.lunar.network;

import com.lunarclient.common.v1.HostedWorld;
import com.lunarclient.common.v1.InboundLocation;
import com.lunarclient.common.v1.Location;
import com.lunarclient.common.v1.PublicServer;
import com.lunarclient.common.v1.Location.LocationCase;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public final class AnalyticsBatcher {
   public static final int field1 = 15000;
   public static final int field2 = 100;
   public static final int field3 = 5000;
   public static final int field4 = 2;
   private static final Set<String> field5 = Set.of("?", "not supplied");
   private static final String field6 = "00000000-0000-0000-0000-000000000000";
   private final com.moonsworth.lunar.network.mixin.MixinHelper field7;
   private final ConcurrentLinkedQueue<AnalyticsBatcher.Data> field8 = new ConcurrentLinkedQueue<>();
   private final ScheduledExecutorService field9;
   private final AtomicBoolean field10 = new AtomicBoolean(false);
   private final MixinHelper_2 field11;

   public AnalyticsBatcher(AnalyticsBatcher.Type var1) {
      this.field7 = new com.moonsworth.lunar.network.mixin.MixinHelper();
      this.field7.method41(5000);
      this.field11 = new MixinHelper_2(this.field7);
      this.method1(var1);
      this.field9 = Executors.newSingleThreadScheduledExecutor(new DefaultThreadFactory("lunar-analytics-service-thread", true));
   }

   public void method1(AnalyticsBatcher.Type var1) {
      this.field7.method7(var1.serverIndex);
   }

   public void start() {
      if (this.field10.compareAndSet(false, true)) {
         this.field9.scheduleAtFixedRate(this::method3, 0L, 15000L, TimeUnit.MILLISECONDS);
      }
   }

   public void method2(@NotNull Object var1) {
      MixinHelper62 var2 = new MixinHelper62(var1);
      this.method5((Network)var2.getInstance());
      this.field8.offer(new AnalyticsBatcher.Data(var2));
   }

   public void shutdown() {
      this.field9.shutdown();
      this.method3();
   }

   private void method3() {
      try {
         this.method4();
      } catch (Exception var2) {
         Slayer.method8("Analytics", "Failed to flush analytics batch: " + var2.getMessage());
      }
   }

   private void method4() {
      if (!this.field8.isEmpty()) {
         ArrayList var1 = new ArrayList();

         while (!this.field8.isEmpty() && var1.size() < 100) {
            AnalyticsBatcher.Data var2 = this.field8.poll();
            if (var2 != null) {
               var1.add(var2);
            }
         }

         if (!var1.isEmpty()) {
            List var5 = var1.stream().map(var0 -> var0.field1).collect(Collectors.toList());

            try {
               this.field11.method9(new MixinHelper11().method1(var5));
            } catch (Exception var4) {
               Slayer.method6("Analytics", "Failed to send batch, retrying " + var1.size() + " events");
               var1.forEach(var1x -> {
                  if (var1x.field2 < 2) {
                     var1x.field2++;
                     this.field8.offer(var1x);
                  }
               });
            }
         }
      }
   }

   private void method5(@NotNull Network var1) {
      MixinHelper2 var2 = var1.method1();
      var2.lunarClientGitCommit(LunarBuildData.field3)
         .method31(LunarBuildData.field1)
         .method34(Client.method19())
         .method37(LunarBuildData.field8)
         .method40(LunarBuildData.field7)
         .method43(ThreadModuleDump48.field1)
         .method45(ThreadModuleDump48.field2)
         .method47(ThreadModuleDump48.field3)
         .method48(ThreadModuleDump80.launcherVersion)
         .method51(ThreadModuleDump80.canaryToken);
      if (!field5.contains(ThreadModuleDump80.launchId)) {
         var2.launchId(ThreadModuleDump80.launchId);
      } else {
         var2.launchId("00000000-0000-0000-0000-000000000000");
      }

      if (!field5.contains(ThreadModuleDump80.installationId)) {
         var2.installationId(ThreadModuleDump80.installationId);
      } else {
         var2.installationId("00000000-0000-0000-0000-000000000000");
      }

      if (!field5.contains(ThreadModuleDump80.overwolfMuid)) {
         var2.overwolfMuid(ThreadModuleDump80.overwolfMuid);
      } else {
         var2.overwolfMuid("00000000-0000-0000-0000-000000000000");
      }

      if (Bridge.method8() != null) {
         var2.minecraftVersion(Bridge.getMinecraftVersion().getId());
         IchorAPI.getPipeline(Client.class.getClassLoader()).ifPresent(var1x -> var2.ichorModules(var1x.method31().keySet().stream().toList()));
         IchorAPI.getPipeline(Client.class).ifPresent(var1x -> var2.overwolfMuid1(var1x.method18().map(AnalyticsBatcher::method8).collect(Collectors.toList())));
         Client var3 = Client.method109();
         if (var3.method31() != null && var3.method31().method10() != null) {
            var2.playerUuid(var3.method31().method10().toString());
         }

         UUID var4 = EntityRenderer4.method78();
         if (var4 != null) {
            var2.playerUuid4(var4);
         }

         this.method6(var2);
         this.method7(var2);
      }
   }

   private void method6(MixinHelper2 var1) {
      InboundLocation var2 = EntityRenderer4.method80();
      if (var2 != null) {
         MixinHelper9 var3 = new MixinHelper9().method1(var2.getLocationCase().name());
         String var4 = var2.getServer().getServerIp();
         if (!var4.isEmpty()) {
            var3.method2(var4);
         }

         var1.inboundLocation(var3);
      }
   }

   private void method7(MixinHelper2 var1) {
      Location var2 = EntityRenderer4.method82();
      if (var2 != null) {
         MixinHelper10 var3 = new MixinHelper10().method1(var2.getLocationCase().name());
         if (var2.getLocationCase() == LocationCase.PUBLIC_SERVER) {
            PublicServer var4 = var2.getPublicServer();
            var3.method2(
               new MixinHelper13()
                  .method1(var4.getServerMappingsId())
                  .method3(var4.getRichStatus().getSource().name())
                  .method6(var4.getRichStatus().getGameName())
                  .method9(var4.getRichStatus().getGameVariantName())
                  .method12(var4.getRichStatus().getGameState())
                  .method15(var4.getRichStatus().getPlayerState())
                  .method18(var4.getRichStatus().getMapName())
                  .method21(var4.getRichStatus().getSubServer())
            );
         }

         if (var2.getLocationCase() == LocationCase.HOSTED_WORLD) {
            HostedWorld var5 = var2.getHostedWorld();
            var3.hostedWorld(new MixinHelper12().method1(var5.getWorldHost().getUuid().toString()).method4(var5.getWorldHost().getUsername()));
         }

         var1.location(var3);
      }
   }

   @NotNull
   private static MixinHelper14 method8(Ichor5Handler_2 var0) {
      MixinHelper14 var1 = new MixinHelper14()
         .method1(var0.getId())
         .method2(var0.getId())
         .method4(
            Bridge.getMinecraftVersion().method19()
               ? (
                  var0.method5()
                     ? com.lunarclient.websocket.handshake.v1.InstalledMod.Type.TYPE_FABRIC_INTERNAL.name()
                     : com.lunarclient.websocket.handshake.v1.InstalledMod.Type.TYPE_FABRIC_EXTERNAL.name()
               )
               : com.lunarclient.websocket.handshake.v1.InstalledMod.Type.TYPE_FORGE_INTERNAL.name()
         );
      String var2 = var0.getVersion();
      if (var2 != null) {
         var1.version(var2);
      }

      return var1;
   }

   private static final class Data {
      final MixinHelper62 field1;
      int field2;

      Data(MixinHelper62 var1) {
         this.field1 = var1;
      }
   }

   public enum Type {
      PRODUCTION(0),
      DEVELOPMENT(1);

      final int serverIndex;

      @Generated
      Type(int var3) {
         this.serverIndex = var3;
      }
   }
}
