package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.holograms.EventClientShutdown;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.mod.skyblock.scathatrackerhud.SkyblockScathaTrackerHud.ScathaSessionStats;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.concurrent.SupplierExtension;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class PersistentValuesListener extends DynamicListener {
   private final ProfileIdListener field7 = (ProfileIdListener)this.method3(ProfileIdListener.class);
   private final SupplierExtension<PersistentValuesListener.PersistentValues> field8 = SupplierExtension.concurrentLazy(this::method8);
   private boolean dirty = false;

   public PersistentValuesListener() {
      AtomicInteger number1 = new AtomicInteger();
      this.handle(EventSecond.class, arg2 -> {
         if (number1.incrementAndGet() >= 60) {
            number1.set(0);
            this.method7();
         }
      });
      this.handle(EventClientShutdown.class, arg1x -> this.method7());
   }

   @Override
   protected void onEnable() {
      if (!this.field8.initialized()) {
         new Thread(this::method5).start();
      }
   }

   public PersistentValuesListener.PersistentValues method5() {
      return (PersistentValuesListener.PersistentValues)this.field8.get();
   }

   public PersistentValuesListener.Data method6() {
      return this.method5().sbProfiles.computeIfAbsent(this.field7.getKey(), arg0 -> new PersistentValuesListener.Data());
   }

   public PersistentValuesListener.Data method3(String text1) {
      return this.method5().sbProfiles.computeIfAbsent(this.field7.method3(text1), arg0 -> new PersistentValuesListener.Data());
   }

   public void markDirty() {
      this.dirty = true;
   }

   public void method7() {
      if (this.dirty) {
         this.dirty = false;
         String text1 = LunarConstants.field22.toJson(this.method5());
         File file2 = this.getFile();
         file2.getParentFile().mkdirs();

         try {
            Files.writeString(file2.toPath(), text1);
         } catch (Exception exception4) {
            CrashReporter.method5(exception4, "Saving persistent values");
         }
      }
   }

   private PersistentValuesListener.PersistentValues method8() {
      File file1 = this.getFile();
      if (!file1.exists()) {
         return new PersistentValuesListener.PersistentValues();
      }

      try {
         String text2 = Files.readString(file1.toPath());
         PersistentValuesListener.PersistentValues data23 = (PersistentValuesListener.PersistentValues)LunarConstants.field22
            .fromJson(text2, PersistentValuesListener.PersistentValues.class);
         return data23 == null ? new PersistentValuesListener.PersistentValues() : data23;
      } catch (Exception exception4) {
         if (!LunarBuildData.field4) {
            throw new RuntimeException(exception4);
         }

         CrashReporter.method5(exception4, "Loading persistent values");
         return new PersistentValuesListener.PersistentValues();
      }
   }

   private File getFile() {
      return new File(Ref.method3().bridge$getMcDataDir(), "config" + File.separator + "lunar" + File.separator + "persistent.json");
   }

   @com.moonsworth.lunar.ichor.util.KeepName
   public static class Data {
      @com.moonsworth.lunar.client.util.io.NotNullSerialized
      public final com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.StorageOverlayListener.Data storageContents = new com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.StorageOverlayListener.Data();
      @com.moonsworth.lunar.client.util.io.NotNullSerialized
      public final ScathaSessionStats scathaStats = new ScathaSessionStats();

      public Data() {
      }
   }

   @com.moonsworth.lunar.ichor.util.KeepName
   public static class PersistentValues {
      @com.moonsworth.lunar.client.util.io.NotNullSerialized
      private final Map<String, PersistentValuesListener.Data> sbProfiles = new HashMap<>();
      @com.moonsworth.lunar.client.util.io.NotNullSerialized
      public final com.moonsworth.lunar.client.mod.skyblock.protectitem.SkyblockProtectItem.Data protectedItems = new com.moonsworth.lunar.client.mod.skyblock.protectitem.SkyblockProtectItem.Data();

      public PersistentValues() {
      }
   }
}
