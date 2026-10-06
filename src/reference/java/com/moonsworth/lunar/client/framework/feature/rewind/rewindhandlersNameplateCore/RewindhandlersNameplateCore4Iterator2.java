package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType2;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.util.opus.Opus;

public class RewindhandlersNameplateCore4Iterator2 extends RewindhandlersNameplateCore4 {
   private static final GuiType2 field5 = GuiType2.MONO16;
   private static final int field6 = 48000;
   private Process field7;

   public RewindhandlersNameplateCore4Iterator2() {
      this.field1.setChannels(field5.getChannels());
      this.field1.setFrequency(48000);
      this.field1.setFrameSize((int)(this.field1.getFrequency() * 0.02));
      this.field1.setBytesPerSample(field5.getBytesPerSample());
      this.method1();
   }

   @Override
   public void start() {
      int var1 = this.field1.getFrameSize() * this.field1.getChannels();
      int var2 = var1 * this.field1.getBytesPerSample();
      ByteBuffer var3 = BufferUtils.createByteBuffer(var2).order(ByteOrder.LITTLE_ENDIAN);
      ByteBuffer var4 = BufferUtils.createByteBuffer(var2).order(ByteOrder.LITTLE_ENDIAN);
      byte[] var5 = new byte[var2];
      ShortBuffer var6 = var3.asShortBuffer();
      ByteBuffer var7 = ByteBuffer.allocate(var2 * 3).order(ByteOrder.LITTLE_ENDIAN);
      this.method4(() -> {
         try {
            if (!this.method4() && !this.method2() && !this.method6()) {
               throw new RuntimeException("Failed to start audio capture");
            }

            byte[] var7x = new byte[var2];

            while (!Thread.currentThread().isInterrupted() && this.field7 != null && this.field7.isAlive()) {
               int var8 = 0;

               try {
                  var8 = this.field7.getInputStream().read(var7x);
               } catch (IOException var16) {
               }

               if (var8 < 0) {
                  break;
               }

               if (!this.isPaused()) {
                  var7.put(var7x, 0, var8);
               }

               while (var7.position() >= var2) {
                  var7.flip();
                  var3.clear().put(var7.array(), 0, var2).flip();
                  var7.position(var2).compact();
                  var4.clear();
                  int var9 = Opus.opus_encode(this.field2, var6, this.field1.getFrameSize(), var4);
                  var4.get(var5, 0, var9);

                  for (DataOutputStream var11 : this.field4) {
                     ByteBufLoader.method13(var11, var9);
                     var11.write(var5, 0, var9);
                  }
               }
            }
         } catch (IOException var17) {
            Inventorymod2.method5(var17, "Rewind");
         } finally {
            if (this.field7 != null) {
               this.field7.destroy();
            }
         }
      });
   }

   private boolean method2() {
      try {
         if (!this.method6("pw-record")) {
            return false;
         }

         String var1 = this.method3();
         ProcessBuilder var2;
         if (var1 != null) {
            var2 = new ProcessBuilder(
               "pw-record", "--raw", "--format=s16", "--rate=48000", "--channels=" + field5.getChannels(), "--latency=20", "-d", var1, "-"
            );
         } else {
            var2 = new ProcessBuilder(
               "pw-record",
               "--raw",
               "--format=s16",
               "--rate=48000",
               "--channels=" + field5.getChannels(),
               "--latency=20",
               "-P",
               "{ stream.capture.sink=true }",
               "-"
            );
         }

         this.field7 = var2.start();
         return true;
      } catch (IOException var3) {
         return false;
      }
   }

   private String method3() {
      try {
         if (this.method6("pactl")) {
            String var1 = this.method7("pactl", "get-default-sink");
            if (var1 != null && !var1.isBlank()) {
               return var1.trim() + ".monitor";
            }
         }

         List var8 = this.method8("pw-record", "--list-targets");
         Pattern var2 = Pattern.compile(".*\\b([\\w.-]+\\.monitor)\\b.*");

         for (String var4 : var8) {
            Matcher var5 = var2.matcher(var4);
            if (var5.matches()) {
               return var5.group(1);
            }
         }

         Pattern var9 = Pattern.compile(".*monitor.*\\b([\\w.-]+\\.monitor)\\b.*", 2);

         for (String var11 : var8) {
            Matcher var6 = var9.matcher(var11);
            if (var6.matches()) {
               return var6.group(1);
            }
         }
      } catch (Exception var7) {
      }

      return null;
   }

   private boolean method4() {
      try {
         if (!this.method6("parec")) {
            return false;
         }

         ProcessBuilder var1 = new ProcessBuilder(
            "parec", "--format=s16le", "--rate=48000", "--channels=" + field5.getChannels(), "--latency-msec=20", "--device=@DEFAULT_MONITOR@"
         );
         this.field7 = var1.start();
         return true;
      } catch (IOException var2) {
         return false;
      }
   }

   private boolean method6() {
      try {
         if (!this.method6("arecord")) {
            return false;
         } else {
            String var1 = this.method7();
            if (var1 == null) {
               System.err.println("ALSA capture failed. No suitable loopback/monitor device found.");
               return false;
            } else {
               ProcessBuilder var2 = new ProcessBuilder(
                  "arecord", "-f", "S16_LE", "-r", String.valueOf(48000), "-c", String.valueOf(field5.getChannels()), "-D", var1
               );
               this.field7 = var2.start();
               return true;
            }
         }
      } catch (IOException var3) {
         return false;
      }
   }

   private String method7() {
      try {
         Process var1 = new ProcessBuilder("arecord", "-L").start();
         Scanner var2 = new Scanner(var1.getInputStream()).useDelimiter("\\A");
         String var3 = var2.hasNext() ? var2.next() : "";
         var2.close();
         String var4 = null;

         for (String var8 : var3.split("\n")) {
            var8 = var8.trim();
            if (var8.toLowerCase().startsWith("hw:loopback")) {
               return var8.split(",")[0];
            }

            if (var8.toLowerCase().contains("monitor")) {
               var4 = var8;
            }
         }

         return var4;
      } catch (IOException var9) {
         return null;
      }
   }

   private boolean method6(String var1) {
      try {
         Process var2 = new ProcessBuilder("which", var1).start();
         return var2.waitFor() == 0;
      } catch (IOException | InterruptedException var3) {
         return false;
      }
   }

   private String method7(String... var1) {
      Process var2 = new ProcessBuilder(var1).start();

      String var4;
      try (BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.getInputStream()))) {
         while ((var4 = var3.readLine()) != null) {
            if (!var4.isBlank()) {
               var2.waitFor();
               return var4.trim();
            }
         }
      }

      var2.waitFor();
      return null;
   }

   private List<String> method8(String... var1) {
      Process var2 = new ProcessBuilder(var1).start();
      ArrayList var3 = new ArrayList();

      String var5;
      try (BufferedReader var4 = new BufferedReader(new InputStreamReader(var2.getInputStream()))) {
         while ((var5 = var4.readLine()) != null) {
            var3.add(var5);
         }
      }

      var2.waitFor();
      return var3;
   }

   @Override
   public void stop() {
      if (this.field7 != null) {
         this.field7.destroy();
      }

      super.stop();
   }
}
