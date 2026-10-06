package com.moonsworth.lunar.client.guiRewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler212;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.holograms.ClientShutdownEvent;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.mod.skyblock.scathatrackerhud.SkyblockScathaTrackerHud;
import com.moonsworth.lunar.client.mod.skyblock.protectitem.SkyblockProtectItem;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.concurrent.SupplierExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class GuiRewindhandlersHandler26 extends DynamicListener {
   private final GuiRewindhandlersHandler212 field7 = (GuiRewindhandlersHandler212)this.method3(GuiRewindhandlersHandler212.class);
   private final SupplierExtension<GuiRewindhandlersHandler26.Data2> field8 = SupplierExtension.concurrentLazy(this::method8);
   private boolean dirty = false;

   public GuiRewindhandlersHandler26() {
      AtomicInteger var1 = new AtomicInteger();
      this.handle(EventEverySecond.class, var2 -> {
         if (var1.incrementAndGet() >= 60) {
            var1.set(0);
            this.method7();
         }
      });
      this.handle(ClientShutdownEvent.class, var1x -> this.method7());
   }

   @Override
   protected void onEnable() {
      if (!this.field8.initialized()) {
         new Thread(this::method5).start();
      }
   }

   public GuiRewindhandlersHandler26.Data2 method5() {
      return this.field8.get();
   }

   public GuiRewindhandlersHandler26.Data method6() {
      return this.method5().sbProfiles.computeIfAbsent(this.field7.getKey(), var0 -> new GuiRewindhandlersHandler26.Data());
   }

   public GuiRewindhandlersHandler26.Data method3(String var1) {
      return this.method5().sbProfiles.computeIfAbsent(this.field7.method3(var1), var0 -> new GuiRewindhandlersHandler26.Data());
   }

   public void markDirty() {
      this.dirty = true;
   }

   public void method7() {
      if (this.dirty) {
         this.dirty = false;
         String var1 = ThreadModuleDump48.field22.toJson(this.method5());
         File var2 = this.getFile();
         var2.getParentFile().mkdirs();

         try {
            Files.writeString(var2.toPath(), var1);
         } catch (Exception var4) {
            Inventorymod2.method5(var4, "Saving persistent values");
         }
      }
   }

   private GuiRewindhandlersHandler26.Data2 method8() {
      File var1 = this.getFile();
      if (!var1.exists()) {
         return new GuiRewindhandlersHandler26.Data2();
      }

      try {
         String var2 = Files.readString(var1.toPath());
         GuiRewindhandlersHandler26.Data2 var3 = (GuiRewindhandlersHandler26.Data2)ThreadModuleDump48.field22
            .fromJson(var2, GuiRewindhandlersHandler26.Data2.class);
         return var3 == null ? new GuiRewindhandlersHandler26.Data2() : var3;
      } catch (Exception var4) {
         if (!LunarBuildData.field4) {
            throw new RuntimeException(var4);
         }

         Inventorymod2.method5(var4, "Loading persistent values");
         return new GuiRewindhandlersHandler26.Data2();
      }
   }

   private File getFile() {
      return new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "config" + File.separator + "lunar" + File.separator + "persistent.json");
   }

   @com.moonsworth.lunar.ichor.util.Annotation2
   public static class Data {
      @com.moonsworth.lunar.client.util.Annotation2
      public final com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler23.Data storageContents = new com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler23.Data();
      @com.moonsworth.lunar.client.util.Annotation2
      public final SkyblockScathaTrackerHud.Data3 scathaStats = new SkyblockScathaTrackerHud.Data3();
   }

   @com.moonsworth.lunar.ichor.util.Annotation2
   public static class Data2 {
      @com.moonsworth.lunar.client.util.Annotation2
      private final Map<String, GuiRewindhandlersHandler26.Data> sbProfiles = new HashMap<>();
      @com.moonsworth.lunar.client.util.Annotation2
      public final SkyblockProtectItem.Data protectedItems = new SkyblockProtectItem.Data();
   }
}
