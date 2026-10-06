package com.moonsworth.lunar.client.util.performance;

import com.lunarclient.websocket.performance.v1.RecordPerfSnapshotRequest;
import com.lunarclient.websocket.performance.v1.RecordPerfSnapshotRequest.AverageGroup;
import com.lunarclient.websocket.performance.v1.RecordPerfSnapshotRequest.Builder;
import com.lunarclient.websocket.performance.v1.RecordPerfSnapshotRequest.GraphicsStatus;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.hud.HudEditorScreen;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.util.performance.GcMonitor;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase.EventRenderTickBegin;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.util.Optional;
import lombok.Generated;
import org.apache.commons.lang3.mutable.MutableInt;
import com.moonsworth.lunar.client.config.PerformanceSettings;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;

public class PerformanceReporter implements LoadableHandler, EventRegistrar {
   private static final int field1 = 8;
   private static final int field2 = 300;
   private static final int field3 = 30;
   private static final int field4 = Math.max(0, 270);
   private static final BufferPoolMXBean field5 = method9();
   private final IntArrayList field6 = new IntArrayList();
   private int field7;
   private int field8;
   private int field9;
   private int field10 = 0;

   public PerformanceReporter() {
      this.handle(EventRenderTickBegin.class, var1 -> this.method1());
      this.handle(
         EventEverySecond.class,
         var1 -> {
            Bridge5_12 var2 = ThreadModuleDump63.method3();
            if (var2.bridge$getWorld() == null) {
               this.field10 = 0;
            } else {
               Bridge5Extension6 var3 = var2.bridge$getCurrentScreen();
               if (!var2.bridge$isWindowFocused()
                  || var2.bridge$isGamePaused()
                  || var3 != null && !(var3 instanceof Bridge5Extension612)
                  || var2.bridge$isFpsLimited()) {
                  if (this.field10 >= field4) {
                     this.field10 = field4;
                  } else {
                     this.field10++;
                  }

                  this.field6.clear();
                  return;
               }

               if (this.field10 == 299) {
                  var2.bridge$enableGpuProfiling();
               } else if (this.field10 >= 300) {
                  this.field10 = 0;
                  if (!this.field6.isEmpty()) {
                     this.method5();
                  }

                  this.field6.clear();
                  var2.bridge$disableGpuProfiling();
                  return;
               }

               this.field10++;
               this.field6.add(var2.bridge$getDebugFPS());
            }
         }
      );
   }

   public void method1() {
      this.field7 = 0;
      this.field8 = 0;
      this.field9 = 0;
   }

   public void method2() {
      this.field7++;
   }

   public void method3() {
      this.field8++;
   }

   public void method4() {
      this.field9++;
   }

   public void method5() {
      ThreadModuleDump63.method5().ifPresent(var1 -> var1.method98().recordPerfSnapshot(null, this.method6(), var0 -> {}));
   }

   private RecordPerfSnapshotRequest method6() {
      Bridge5_12 var1 = ThreadModuleDump63.method3();
      double var2 = 0.0;
      int var4 = 0;
      int var5 = Integer.MAX_VALUE;
      IntListIterator var6 = this.field6.iterator();

      while (var6.hasNext()) {
         int var7 = (Integer)var6.next();
         var2 += var7;
         if (var7 < var5) {
            var5 = var7;
         }

         if (var7 > var4) {
            var4 = var7;
         }
      }

      var2 /= this.field6.size();
      Bridge14_3 var13 = var1.bridge$getLevelRenderer();
      GameOptionsBridge var14 = var1.bridge$getGameSettings();
      PerformanceSettings var8 = Client.method109().method41().method7();
      OverlayMod var9 = Client.method109().method40().method84();
      Builder var10 = RecordPerfSnapshotRequest.newBuilder()
         .setFps(var1.bridge$getDebugFPS())
         .setRenderedChunkSections(var13.bridge$getUnculledRenderCount())
         .setLoadedChunks(var1.bridge$getWorld().bridge$getLoadedChunkCount())
         .setRenderedEntities(var13.bridge$getRenderedEntityCount())
         .setRenderedPlayers(var13.bridge$getRenderedPlayersCount())
         .setRenderedParticles(var1.bridge$getEffectRenderer().bridge$countParticles())
         .setRenderedBlockEntities(var13.bridge$getRenderedBlockEntityCount())
         .setFreeMemoryKb((int)(Runtime.getRuntime().freeMemory() / 1000L))
         .setTotalMemoryKb((int)(Runtime.getRuntime().totalMemory() / 1000L))
         .setMaxMemoryKb((int)(Runtime.getRuntime().maxMemory() / 1000L))
         .setDirectMemoryKb((int)(field5.getMemoryUsed() / 1000L))
         .setOffHeapMemoryKb((int)(ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed() / 1000L))
         .setLunarHuds(method8())
         .setRenderedGeckolibCosmetics(this.field7)
         .setRenderedCosmetics(this.field8)
         .setRenderedEmotes(this.field9)
         .setFrametime(var1.bridge$getFrametime())
         .setAverageFps(AverageGroup.newBuilder().setAverage((float)var2).setHigh(var4).setLow(var5))
         .setUsingExternalShaders(method7())
         .setWidth(var1.bridge$displayWidth())
         .setHeight(var1.bridge$displayHeight())
         .setFullscreen(var1.bridge$isFullScreen())
         .setVsync(var14.bridge$getVSync())
         .setSmoothLighting(var14.bridge$getAmbientOcclusion())
         .setEntityShadows(var14.bridge$getEntityShadows() && var9.shouldRenderEntityShadow())
         .setMaxFramerate(var14.bridge$getFrameRateLimit())
         .setGraphicsStatus(
            var14.bridge$isFabulousGraphics()
               ? GraphicsStatus.GRAPHICS_STATUS_FABULOUS
               : (var14.bridge$isFancyGraphics() ? GraphicsStatus.GRAPHICS_STATUS_FANCY : GraphicsStatus.GRAPHICS_STATUS_FAST)
         )
         .setChunkBuilderValue(var14.bridge$getPrioritizeChunkUpdates().getProtoId())
         .setCloudsValue(var14.bridge$getCloudStatus().getProtoId())
         .setParticlesValue(var14.bridge$getParticleStatus().getProtoId())
         .setRenderDistance(var14.bridge$getRenderDistance())
         .setSimulatedDistance(var14.bridge$getSimulationDistance())
         .setEntityDistance((int)Math.round(var14.bridge$getEntityScaling() * 64.0))
         .setBiomeBlend(var14.bridge$getBiomeBlend())
         .setFov(var14.bridge$getFov())
         .setHudCaching((Boolean)var8.method23().get())
         .setParticlePhysics((Boolean)var8.method26().get())
         .setLazyChunkLoadingValue(((PerformanceSettings.Type)var8.method22().get()).getProtoId())
         .setTurboEntities(ThreadModuleDump63.MC_VERSION >= 8 && ThreadModuleDump63.method4().method89().method3())
         .setGpuName(Bridge.method42().method85().field3)
         .setTextureFilteringValue(var14.bridge$getTextureFiltering().getProtoId());
      GcMonitor.method2().method1(var10);
      com.moonsworth.lunar.client.util.performance.PerfSnapshotRecorder.method4().method3(var10);
      if (ThreadModuleDump63.MC_VERSION >= 13) {
         var10.setGpuUsage(var1.bridge$getGpuUtilization());
      }

      if (ThreadModuleDump63.MC_VERSION >= 8) {
         TurboEngineManager var11 = ThreadModuleDump63.method4().method89();
         if (var11.method3()) {
            var10.setRenderedTurboEntities(var11.method26().method7());
         }

         if (var11.method6()) {
            var10.setRenderedTurboBlockEntities(var11.method27().method7());
         }
      }

      return var10.build();
   }

   private static boolean method7() {
      Optional var0 = Bridge.method5();
      if (var0.isPresent()) {
         Slayer3 var3 = ((Slayer2)var0.get()).getShaders();
         String var4 = var3.getShaderPack();
         return !var3.getPackNone().equals(var4);
      } else {
         Optional var1 = Fishing.method2(Fishing2Extension.class);
         if (var1.isPresent()) {
            String var2 = ((Fishing2Extension)var1.get()).lunar$getShaderPack();
            return !"(off)".equals(var2);
         } else {
            return false;
         }
      }
   }

   private static int method8() {
      if (ThreadModuleDump63.method3().bridge$getGameSettings().bridge$showDebugInfo() && !(Boolean)Client.method109().method41().method6().method24().get()) {
         return 0;
      }

      if (com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method64() == DriverOverlayRegistryLegacy.field2
         && !(Boolean)ThreadModuleDump63.method4().method41().method6().method25().get()) {
         return 0;
      }

      Class var0 = ThreadModuleDump63.method11();
      int var1 = 0;
      boolean var2 = var0 == HudEditorScreen.class || var0 == com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen.class;
      MutableInt var3 = new MutableInt(0);

      for (Framework7Extension var5 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         if (var5.isEnabled()) {
            AlertExtension var6 = (AlertExtension)var5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
            if (var6 != null) {
               var3.setValue(0);
               var6.method3(var2x -> {
                  if (var2x.isEnabled()) {
                     MixinCore9Extension var3x = (MixinCore9Extension)var2x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1);
                     if (var3x != null && var3x.method5(var2, var2x)) {
                        var3.increment();
                     }

                     return true;
                  } else {
                     return false;
                  }
               });
               var1 += var3.intValue();
            }

            MixinCore9Extension var7 = (MixinCore9Extension)var5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1);
            if (var7 != null && var7.method5(var2, var5)) {
               var1++;
            }
         }
      }

      return var1;
   }

   @Override
   public void close() {
   }

   @Override
   public void init() {
   }

   public static BufferPoolMXBean method9() {
      for (BufferPoolMXBean var1 : ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class)) {
         if (var1.getName().equalsIgnoreCase("direct")) {
            return var1;
         }
      }

      throw new IllegalStateException();
   }

   @Generated
   public int getRenderedGeckolibCosmetics() {
      return this.field7;
   }

   @Generated
   public int getRenderedCosmetics() {
      return this.field8;
   }

   @Generated
   public int getRenderedEmotes() {
      return this.field9;
   }
}
