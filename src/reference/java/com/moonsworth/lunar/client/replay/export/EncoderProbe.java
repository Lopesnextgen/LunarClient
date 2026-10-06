package com.moonsworth.lunar.client.replay.export;

import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.OperatingSystem;
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

public class EncoderProbe {
   private final FFmpegRenderer field1;
   private final Object field2 = new Object();
   private final Set<String> field3 = new HashSet<>();
   private final Map<String, Boolean> field4 = new ConcurrentHashMap<>();
   private volatile boolean field5 = false;
   private volatile boolean field6 = false;
   private volatile boolean done = false;
   private final List<VideoEncoder> field7 = Collections.synchronizedList(new ArrayList<>());

   public EncoderProbe(FFmpegRenderer rewindhandlersnameplate_31) {
      this.field1 = rewindhandlersnameplate_31;
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
               ProcessBuilder processbuilder2 = new ProcessBuilder(this.field1.method7().getAbsolutePath(), "-encoders");
               processbuilder2.redirectErrorStream(true);
               Process process3 = processbuilder2.start();

               try (BufferedReader reader4 = new BufferedReader(new InputStreamReader(process3.getInputStream()))) {
                  boolean flag6 = false;
                  boolean flag7 = false;

                  String text5;
                  while ((text5 = reader4.readLine()) != null) {
                     if (!flag6) {
                        if (text5.trim().equals("Encoders:")) {
                           flag6 = true;
                        }
                     } else if (!flag7) {
                        if (text5.trim().startsWith("------")) {
                           flag7 = true;
                        }
                     } else {
                        String text8 = text5.trim();
                        if (!text8.isEmpty()) {
                           String[] items9 = text8.split("\\s+", 3);
                           if (items9.length >= 2 && items9[0].startsWith("V")) {
                              this.field3.add(items9[1]);
                           }
                        }
                     }
                  }
               }

               process3.waitFor();
            } catch (Exception exception19) {
               CrashReporter.method5(exception19, "RewindEncoders");
            } finally {
               this.field5 = true;
            }
         }
      }
   }

   private boolean method3(VideoEncoder videoEncoder) {
      if (!videoEncoder.isHardware()) {
         return true;
      }

      String text2 = videoEncoder.getId();
      String text3 = text2 + ":" + String.join("|", videoEncoder.getArguments(1000));
      if (this.field4.containsKey(text3)) {
         Boolean flag4 = this.field4.get(text3);
         if (flag4 != null) {
            return flag4;
         }
      }

      ArrayList list25 = new ArrayList();
      list25.add(this.field1.method7().getAbsolutePath());
      list25.add("-loglevel");
      list25.add("error");
      list25.add("-f");
      list25.add("lavfi");
      list25.add("-i");
      list25.add("testsrc=size=320x240:rate=10:duration=0.2");
      list25.addAll(videoEncoder.getArguments(1000));
      list25.add("-f");
      list25.add("null");
      list25.add("-");
      Process process5 = null;
      boolean flag6 = false;
      StringBuilder builder7 = new StringBuilder();

      try {
         ProcessBuilder processbuilder8 = new ProcessBuilder(list25);
         process5 = processbuilder8.start();

         String text10;
         try (BufferedReader reader9 = new BufferedReader(new InputStreamReader(process5.getErrorStream()))) {
            while ((text10 = reader9.readLine()) != null) {
               builder7.append(text10).append(System.lineSeparator());
            }
         }

         try (InputStream input26 = process5.getInputStream()) {
            input26.readAllBytes();
         }

         if (!process5.waitFor(10L, TimeUnit.SECONDS)) {
            process5.destroyForcibly();
         } else {
            String text27 = builder7.toString().toLowerCase();
            if (process5.exitValue() == 0) {
               boolean flag28 = text27.contains("driver does not support the required nvenc api version")
                  || text27.contains("cannot create a cuda context")
                  || text27.contains("no nvenc capable devices found")
                  || text2.contains("videotoolbox")
                     && (
                        text27.contains("error initializing videotoolbox") || text27.contains("failed to create compression session") || text27.contains("-12905")
                     )
                  || text2.contains("qsv") && (text27.contains("initialize failed") || text27.contains("error creating a mfx session"))
                  || text2.contains("amf")
                     && (text27.contains("amf function failed") || text27.contains("no amd amf capable device found") || text27.contains("encoder->init() failed"));
               flag6 = !flag28;
            }
         }
      } catch (Exception exception23) {
         if (exception23 instanceof InterruptedException) {
            Thread.currentThread().interrupt();
         }

         CrashReporter.method5(exception23, "RewindEncoders");
      } finally {
         if (process5 != null && process5.isAlive()) {
            process5.destroyForcibly();
         }
      }

      this.field4.put(text3, flag6);
      return flag6;
   }

   private synchronized void method4() {
      if (!this.field6) {
         this.field6 = true;
         new Thread(() -> {
            this.field7.clear();

            for (VideoEncoder gui2extension34 : VideoEncoder.values()) {
               if (this.field3.contains(gui2extension34.getId()) && this.method3(gui2extension34) && (!gui2extension34.isMacOS() || OperatingSystem.isMacos())) {
                  this.field7.add(gui2extension34);
               }
            }

            this.done = true;
            Ref.method3().bridge$submit(RewindEditorContext::refreshExportSettings);
         }, "RewindEncoderProbeThread").start();
      }
   }

   public VideoEncoder method5(VideoCodec videoCodec) {
      for (VideoEncoder gui2extension33 : videoCodec.getEncoders()) {
         if (this.field7.contains(gui2extension33)) {
            return gui2extension33;
         }
      }

      return null;
   }

   @Generated
   public boolean isDone() {
      return this.done;
   }

   @Generated
   public List<VideoEncoder> method6() {
      return this.field7;
   }
}
