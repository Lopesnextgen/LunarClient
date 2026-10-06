package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import lombok.Generated;

public class RewindhandlersNameplate_4 {
   private final RewindhandlersNameplate_3 field1;
   private final Object field2 = new Object();
   private final Set<String> field3 = new HashSet<>();
   private final Map<String, Boolean> field4 = new ConcurrentHashMap<>();
   private volatile boolean field5 = false;
   private volatile boolean field6 = false;
   private volatile boolean done = false;
   private final List<Gui2Extension3> field7 = Collections.synchronizedList(new ArrayList<>());

   public RewindhandlersNameplate_4(RewindhandlersNameplate_3 var1) {
      this.field1 = var1;
   }

   public void method1() {
      if (this.field1.method5()) {
         this.method2();
         this.method4();
      }
   }

   private void method2() {
      synchronized (this.field2) {
         if (!this.field5) {
            try {
               ProcessBuilder var2 = new ProcessBuilder(this.field1.method7().getAbsolutePath(), "-encoders");
               var2.redirectErrorStream(true);
               Process var3 = var2.start();

               try (BufferedReader var4 = new BufferedReader(new InputStreamReader(var3.getInputStream()))) {
                  boolean var6 = false;
                  boolean var7 = false;

                  String var5;
                  while ((var5 = var4.readLine()) != null) {
                     if (!var6) {
                        if (var5.trim().equals("Encoders:")) {
                           var6 = true;
                        }
                     } else if (!var7) {
                        if (var5.trim().startsWith("------")) {
                           var7 = true;
                        }
                     } else {
                        String var8 = var5.trim();
                        if (!var8.isEmpty()) {
                           String[] var9 = var8.split("\\s+", 3);
                           if (var9.length >= 2 && var9[0].startsWith("V")) {
                              this.field3.add(var9[1]);
                           }
                        }
                     }
                  }
               }

               var3.waitFor();
            } catch (Exception var19) {
               Inventorymod2.method5(var19, "RewindEncoders");
            } finally {
               this.field5 = true;
            }
         }
      }
   }

   private boolean method3(Gui2Extension3 var1) {
      if (!var1.isHardware()) {
         return true;
      }

      String var2 = var1.getId();
      String var3 = var2 + ":" + String.join("|", var1.getArguments(1000));
      if (this.field4.containsKey(var3)) {
         Boolean var4 = this.field4.get(var3);
         if (var4 != null) {
            return var4;
         }
      }

      ArrayList var25 = new ArrayList();
      var25.add(this.field1.method7().getAbsolutePath());
      var25.add("-loglevel");
      var25.add("error");
      var25.add("-f");
      var25.add("lavfi");
      var25.add("-i");
      var25.add("testsrc=size=320x240:rate=10:duration=0.2");
      var25.addAll(var1.getArguments(1000));
      var25.add("-f");
      var25.add("null");
      var25.add("-");
      Process var5 = null;
      boolean var6 = false;
      StringBuilder var7 = new StringBuilder();

      try {
         ProcessBuilder var8 = new ProcessBuilder(var25);
         var5 = var8.start();

         String var10;
         try (BufferedReader var9 = new BufferedReader(new InputStreamReader(var5.getErrorStream()))) {
            while ((var10 = var9.readLine()) != null) {
               var7.append(var10).append(System.lineSeparator());
            }
         }

         try (InputStream var26 = var5.getInputStream()) {
            var26.readAllBytes();
         }

         if (!var5.waitFor(10L, TimeUnit.SECONDS)) {
            var5.destroyForcibly();
         } else {
            String var27 = var7.toString().toLowerCase();
            if (var5.exitValue() == 0) {
               boolean var28 = var27.contains("driver does not support the required nvenc api version")
                  || var27.contains("cannot create a cuda context")
                  || var27.contains("no nvenc capable devices found")
                  || var2.contains("videotoolbox")
                     && (
                        var27.contains("error initializing videotoolbox") || var27.contains("failed to create compression session") || var27.contains("-12905")
                     )
                  || var2.contains("qsv") && (var27.contains("initialize failed") || var27.contains("error creating a mfx session"))
                  || var2.contains("amf")
                     && (var27.contains("amf function failed") || var27.contains("no amd amf capable device found") || var27.contains("encoder->init() failed"));
               var6 = !var28;
            }
         }
      } catch (Exception var23) {
         if (var23 instanceof InterruptedException) {
            Thread.currentThread().interrupt();
         }

         Inventorymod2.method5(var23, "RewindEncoders");
      } finally {
         if (var5 != null && var5.isAlive()) {
            var5.destroyForcibly();
         }
      }

      this.field4.put(var3, var6);
      return var6;
   }

   private synchronized void method4() {
      if (!this.field6) {
         this.field6 = true;
         new Thread(() -> {
            this.field7.clear();

            for (Gui2Extension3 var4 : Gui2Extension3.values()) {
               if (this.field3.contains(var4.getId()) && this.method3(var4) && (!var4.isMacOS() || ThreadModuleDumpType2.isMacos())) {
                  this.field7.add(var4);
               }
            }

            this.done = true;
            ThreadModuleDump63.method3().bridge$submit(Coordinates::refreshExportSettings);
         }, "RewindEncoderProbeThread").start();
      }
   }

   public Gui2Extension3 method5(Gui2Extension2 var1) {
      for (Gui2Extension3 var3 : var1.getEncoders()) {
         if (this.field7.contains(var3)) {
            return var3;
         }
      }

      return null;
   }

   @Generated
   public boolean isDone() {
      return this.done;
   }

   @Generated
   public List<Gui2Extension3> method6() {
      return this.field7;
   }
}
