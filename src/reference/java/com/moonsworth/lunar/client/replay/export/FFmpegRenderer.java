package com.moonsworth.lunar.client.replay.export;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
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
import com.moonsworth.lunar.client.replay.render.VideoFrameBuffer;

public class FFmpegRenderer {
   private final File field1 = RewindPaths.field13.resolve("bin/ffmpeg").toFile();
   private Process field2;
   private WritableByteChannel field3;
   private BufferedOutputStream field4;
   private Thread field5;
   private final ArrayBlockingQueue<ByteBuffer> field6 = new ArrayBlockingQueue<>(5);
   private final com.moonsworth.lunar.client.replay.render.FrameBufferPool field7;
   private File file;
   private final EncoderProbe field8;

   public FFmpegRenderer(com.moonsworth.lunar.client.replay.render.FrameBufferPool rewindhandlersnameplate1) {
      this.field7 = rewindhandlersnameplate1;
      this.field8 = new EncoderProbe(this);
      this.field8.method1();
   }

   public void method1(File file1, ExportSettings rewindhandlersnameplate2) {
      int index3 = rewindhandlersnameplate2.getWidth();
      int index4 = rewindhandlersnameplate2.getHeight();
      this.file = file1;
      this.field5 = new Thread(() -> {
         while (this.method6()) {
            try {
               ByteBuffer buffer1x = this.field6.take();
               this.field3.write(buffer1x);
               this.field7.method1(buffer1x);
            } catch (InterruptedException interruptedexception2x) {
            } catch (Exception exception3x) {
               throw new RuntimeException(exception3x);
            }
         }
      }, "Rewind-Render-IO");
      Thread thread5 = new Thread(
         () -> {
            try {
               int number5x = index3 * index4 * 4;
               ArrayList list6 = new ArrayList();
               list6.add(this.field1.getAbsolutePath());
               list6.add("-y");
               list6.add("-f");
               list6.add("rawvideo");
               list6.add("-pix_fmt");
               list6.add("bgra");
               list6.add("-s");
               list6.add(index3 + "x" + index4);
               list6.add("-r");
               list6.add(String.valueOf(rewindhandlersnameplate2.method5()));
               list6.add("-i");
               list6.add("-");
               list6.add("-an");
               list6.add("-pix_fmt");
               list6.add("yuv420p");
               list6.add("-vf");
               if (index3 % 2 == 0 && index4 % 2 == 0) {
                  list6.add("vflip");
               } else {
                  list6.add("pad=ceil(iw/2)*2:ceil(ih/2)*2,vflip");
               }

               list6.addAll(
                  rewindhandlersnameplate2.method4() == null ? this.field8.method5(rewindhandlersnameplate2.method3()).getArguments(rewindhandlersnameplate2.method1()) : rewindhandlersnameplate2.method4().getArguments(rewindhandlersnameplate2.method1())
               );
               list6.add("-f");
               list6.add(rewindhandlersnameplate2.method2().id());
               list6.add("-use_editlist");
               list6.add("0");
               list6.add("-movflags");
               list6.add("+frag_keyframe+empty_moov+default_base_moof");
               list6.add(file1.getName());
               LunarLogger.method3("[Rewind] Starting render process", new Object[0]);
               ProcessBuilder processbuilder7 = new ProcessBuilder(list6);
               processbuilder7.directory(file1.getParentFile());
               File file8 = new File(file1.getParentFile(), "output.log");
               processbuilder7.redirectOutput(file8);
               processbuilder7.redirectError(Redirect.appendTo(file8));
               this.field2 = processbuilder7.start();
               this.field4 = new BufferedOutputStream(this.field2.getOutputStream(), number5x);
               this.field3 = Channels.newChannel(this.field4);
               this.field5.start();
            } catch (Exception exception9) {
               CrashReporter.method5(exception9, "Rewind");
            }
         },
         "Rewind-FFmpeg"
      );
      thread5.start();
   }

   public void method2(VideoFrameBuffer nameplate1) {
      try {
         this.field6.put(nameplate1.buffer());
      } catch (InterruptedException interruptedexception3) {
         throw new RuntimeException(interruptedexception3);
      }
   }

   public void method3(Runnable runnable1) {
      LunarLogger.method3("[Rewind] Stopping render process...", new Object[0]);

      try {
         for (int index2 = 0; index2 < 300 && (!this.field6.isEmpty() || !this.field7.isFull()); index2++) {
            runnable1.run();
            Thread.sleep(100L);
         }

         try {
            this.field3.close();
         } catch (IOException exception3) {
         }

         for (int index5 = 0; index5 < 30 && this.field2.isAlive(); index5++) {
            Thread.sleep(100L);
         }

         if (this.field2 != null) {
            this.field2.destroy();
         }

         this.field5.interrupt();
      } catch (Exception exception4) {
         CrashReporter.method5(exception4, "Rewind");
      }
   }

   public void method4(File file1, File file2, ExportSettings rewindhandlersnameplate3) {
      String text4 = file1.getName().substring(file1.getName().lastIndexOf(46));
      File file5 = new File(file1.getParentFile(), "tmp_" + System.currentTimeMillis() + "." + text4);
      ArrayList list6 = new ArrayList<>(List.of(this.field1.getAbsolutePath(), "-y"));
      if (file2 != null && file2.isFile() && rewindhandlersnameplate3.method6() && rewindhandlersnameplate3.method2().isSupportsAudio()) {
         list6.addAll(List.of("-f", "s16le", "-ar", String.valueOf(rewindhandlersnameplate3.getFrequency()), "-ac", rewindhandlersnameplate3.method9() ? "2" : "1", "-i", file2.getAbsolutePath()));
      }

      list6.addAll(
         List.of("-i", file1.getAbsolutePath(), "-c:v", "copy", "-c:a", "aac", "-b:a", "192k", "-movflags", "+faststart", "-shortest", file5.getAbsolutePath())
      );
      File file7 = new File(file5.getParentFile(), "output.log");
      ProcessBuilder processbuilder8 = new ProcessBuilder(list6);
      processbuilder8.directory(file5.getParentFile());
      processbuilder8.redirectOutput(Redirect.appendTo(file7));
      processbuilder8.redirectError(Redirect.appendTo(file7));
      Process process9 = processbuilder8.start();
      process9.waitFor();
      if (file2 != null) {
         file2.delete();
      }

      file1.delete();
      file5.renameTo(file1);
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
   public EncoderProbe method8() {
      return this.field8;
   }
}
