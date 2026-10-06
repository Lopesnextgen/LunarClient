package com.moonsworth.lunar.client.framework;

import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Generated;
import com.moonsworth.lunar.client.config.GeneralSettings;

public class StreamerMode {
   private static final ExecutorService field1 = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-streamer-mode-thread", true));
   private final StreamerMode.Data4 field2 = new StreamerMode.Data4();
   private boolean field3 = false;
   private long field4 = 0L;

   public StreamerMode() {
      ClientEventBus.method29().method2(EventEverySecond.class, this::method2);
   }

   public boolean method1() {
      GeneralSettings var1 = ThreadModuleDump63.method4().method41().method6();
      if ((Boolean)var1.method70().get()) {
         return var1.method71().get() == StreamerMode.Type.ALWAYS_ON ? true : this.field3;
      } else {
         return false;
      }
   }

   private void method2(EventEverySecond var1) {
      if (System.currentTimeMillis() - this.field4 >= 10000L) {
         this.field4 = System.currentTimeMillis();
         if (!(Boolean)ThreadModuleDump63.method4().method41().method6().method70().get()) {
            this.field3 = false;
         } else {
            field1.submit(() -> this.field3 = this.field2.method1());
         }
      }
   }

   @Generated
   public boolean method3() {
      return this.field3;
   }

   static class Data4 {
      private final List<String> field1 = List.of(
         "obs64.exe",
         "obs32.exe",
         "obs-studio.exe",
         "StreamlabsDesktop.exe",
         "XSplit.Core.exe",
         "xsplitcore.exe",
         "TwitchStudio.exe",
         "vMix.exe",
         "Wirecast.exe"
      );
      private final List<String> field2 = List.of("OBS", "Wirecast", "Ecamm Live");
      private final List<String> field3 = List.of("obs");

      public boolean method1() {
         List var1;
         if (ThreadModuleDumpType2.isWindows()) {
            var1 = this.field1;
         } else if (ThreadModuleDumpType2.isMacos()) {
            var1 = this.field2;
         } else {
            var1 = this.field3;
         }

         return ProcessHandle.allProcesses().anyMatch(var1x -> {
            Optional var2 = var1x.info().command();
            if (var2.isEmpty()) {
               return false;
            }

            for (String var4 : var1) {
               if (((String)var2.get()).toLowerCase().contains(var4.toLowerCase())) {
                  return true;
               }
            }

            return false;
         });
      }
   }

   public enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue, Calculator2 {
      AUTOMATIC("automatic", "streamerModeAutomatic"),
      ALWAYS_ON("alwaysOn", "streamerModeAlwaysOn");

      private final String id;
      private final String description;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
      }

      public String description() {
         return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.description, new Object[0]);
      }

      public String getLanguagePath() {
         return "settings";
      }

      @Generated
      Type(String var3, String var4) {
         this.id = var3;
         this.description = var4;
      }
   }
}
