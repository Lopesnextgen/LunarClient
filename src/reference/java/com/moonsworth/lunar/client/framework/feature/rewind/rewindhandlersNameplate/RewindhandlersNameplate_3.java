package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import lombok.Generated;

public class RewindhandlersNameplate_3 {
   private final File field1 = Gui.field13.resolve("bin/ffmpeg").toFile();
   private Process field2;
   private WritableByteChannel field3;
   private BufferedOutputStream field4;
   private Thread field5;
   private final ArrayBlockingQueue<ByteBuffer> field6 = new ArrayBlockingQueue<>(5);
   private final com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.mixin.RewindhandlersNameplate field7;
   private File file;
   private final RewindhandlersNameplate_4 field8;

   public RewindhandlersNameplate_3(com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.mixin.RewindhandlersNameplate var1) {
      this.field7 = var1;
      this.field8 = new RewindhandlersNameplate_4(this);
      this.field8.method1();
   }

   public void method1(File var1, RewindhandlersNameplate var2) {
      int var3 = var2.getWidth();
      int var4 = var2.getHeight();
      this.file = var1;
      this.field5 = new Thread(() -> {
         while (this.method6()) {
            try {
               ByteBuffer var1x = this.field6.take();
               this.field3.write(var1x);
               this.field7.method1(var1x);
            } catch (InterruptedException var2x) {
            } catch (Exception var3x) {
               throw new RuntimeException(var3x);
            }
         }
      }, "Rewind-Render-IO");
      Thread var5 = new Thread(
         () -> {
            try {
               int var5x = var3 * var4 * 4;
               ArrayList var6 = new ArrayList();
               var6.add(this.field1.getAbsolutePath());
               var6.add("-y");
               var6.add("-f");
               var6.add("rawvideo");
               var6.add("-pix_fmt");
               var6.add("bgra");
               var6.add("-s");
               var6.add(var3 + "x" + var4);
               var6.add("-r");
               var6.add(String.valueOf(var2.method5()));
               var6.add("-i");
               var6.add("-");
               var6.add("-an");
               var6.add("-pix_fmt");
               var6.add("yuv420p");
               var6.add("-vf");
               if (var3 % 2 == 0 && var4 % 2 == 0) {
                  var6.add("vflip");
               } else {
                  var6.add("pad=ceil(iw/2)*2:ceil(ih/2)*2,vflip");
               }

               var6.addAll(
                  var2.method4() == null ? this.field8.method5(var2.method3()).getArguments(var2.method1()) : var2.method4().getArguments(var2.method1())
               );
               var6.add("-f");
               var6.add(var2.method2().id());
               var6.add("-use_editlist");
               var6.add("0");
               var6.add("-movflags");
               var6.add("+frag_keyframe+empty_moov+default_base_moof");
               var6.add(var1.getName());
               Slayer.method3("[Rewind] Starting render process", new Object[0]);
               ProcessBuilder var7 = new ProcessBuilder(var6);
               var7.directory(var1.getParentFile());
               File var8 = new File(var1.getParentFile(), "output.log");
               var7.redirectOutput(var8);
               var7.redirectError(Redirect.appendTo(var8));
               this.field2 = var7.start();
               this.field4 = new BufferedOutputStream(this.field2.getOutputStream(), var5x);
               this.field3 = Channels.newChannel(this.field4);
               this.field5.start();
            } catch (Exception var9) {
               Inventorymod2.method5(var9, "Rewind");
            }
         },
         "Rewind-FFmpeg"
      );
      var5.start();
   }

   public void method2(Nameplate var1) {
      try {
         this.field6.put(var1.buffer());
      } catch (InterruptedException var3) {
         throw new RuntimeException(var3);
      }
   }

   public void method3(Runnable var1) {
      Slayer.method3("[Rewind] Stopping render process...", new Object[0]);

      try {
         for (int var2 = 0; var2 < 300 && (!this.field6.isEmpty() || !this.field7.isFull()); var2++) {
            var1.run();
            Thread.sleep(100L);
         }

         try {
            this.field3.close();
         } catch (IOException var3) {
         }

         for (int var5 = 0; var5 < 30 && this.field2.isAlive(); var5++) {
            Thread.sleep(100L);
         }

         if (this.field2 != null) {
            this.field2.destroy();
         }

         this.field5.interrupt();
      } catch (Exception var4) {
         Inventorymod2.method5(var4, "Rewind");
      }
   }

   public void method4(File var1, File var2, RewindhandlersNameplate var3) {
      String var4 = var1.getName().substring(var1.getName().lastIndexOf(46));
      File var5 = new File(var1.getParentFile(), "tmp_" + System.currentTimeMillis() + "." + var4);
      ArrayList var6 = new ArrayList<>(List.of(this.field1.getAbsolutePath(), "-y"));
      if (var2 != null && var2.isFile() && var3.method6() && var3.method2().isSupportsAudio()) {
         var6.addAll(List.of("-f", "s16le", "-ar", String.valueOf(var3.getFrequency()), "-ac", var3.method9() ? "2" : "1", "-i", var2.getAbsolutePath()));
      }

      var6.addAll(
         List.of("-i", var1.getAbsolutePath(), "-c:v", "copy", "-c:a", "aac", "-b:a", "192k", "-movflags", "+faststart", "-shortest", var5.getAbsolutePath())
      );
      File var7 = new File(var5.getParentFile(), "output.log");
      ProcessBuilder var8 = new ProcessBuilder(var6);
      var8.directory(var5.getParentFile());
      var8.redirectOutput(Redirect.appendTo(var7));
      var8.redirectError(Redirect.appendTo(var7));
      Process var9 = var8.start();
      var9.waitFor();
      if (var2 != null) {
         var2.delete();
      }

      var1.delete();
      var5.renameTo(var1);
   }

   public boolean method5() {
      return this.field1.isFile() || new File(this.field1.getAbsolutePath() + ".exe").isFile();
   }

   public boolean method6() {
      return this.field2 != null && this.field2.isAlive() && this.field3 != null && this.field3.isOpen();
   }

   @Generated
   public File method7() {
      return this.field1;
   }

   @Generated
   public File getFile() {
      return this.file;
   }

   @Generated
   public RewindhandlersNameplate_4 method8() {
      return this.field8;
   }
}
