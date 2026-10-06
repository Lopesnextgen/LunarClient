package com.moonsworth.lunar.client.mod.misc.rewind;

import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModLifecycle;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.replay.project.PathUtils;
import com.moonsworth.lunar.client.replay.timeline.TimelineRenderJob;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.recording.ReplayClock;
import com.moonsworth.lunar.client.replay.gui.LocalPlayerContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.render.ReplayDriverHandler;
import com.moonsworth.lunar.client.replay.render.VideoFrameBuffer;
import com.moonsworth.lunar.client.replay.audio.LoopbackAudioRecorder;
import com.moonsworth.lunar.client.replay.export.FFmpegRenderer;
import com.moonsworth.lunar.client.replay.render.FrameBufferPool;
import com.moonsworth.lunar.client.replay.render.QueuedFrameBufferPool;
import com.moonsworth.lunar.client.replay.render.PersistentPixelBufferPool;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.util.FlawlessFrames;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.UUID;
import lombok.Generated;

public class RewindRenderQueue extends AbstractFeature {
   private final RewindHandlers field8;
   private final ReplayClock field9;
   private boolean field10 = false;
   private boolean paused = false;
   private final FrameBufferPool field11 = (FrameBufferPool)(Ref.method43()
      ? new PersistentPixelBufferPool()
      : new QueuedFrameBufferPool());
   private final FFmpegRenderer field12 = new FFmpegRenderer(this.field11);
   private final LoopbackAudioRecorder field13 = new LoopbackAudioRecorder();
   private final Queue<TimelineRenderJob> field14 = new LinkedList<>();
   private final File field15;
   private boolean field16 = false;
   private boolean field17 = false;
   private long field18 = 0L;
   private long field19 = 0L;
   private static boolean field20 = false;
   private int field21 = 0;
   private long field22 = 0L;
   private boolean field23 = false;
   private int frameCount = 0;

   public RewindRenderQueue(RewindHandlers rewindhandlers1) {
      super(true);
      this.field8 = rewindhandlers1;
      this.field9 = rewindhandlers1.method41();
      this.field15 = new File(rewindhandlers1.method40().method32(), "render_queue");
      ModLifecycle framework10extension2 = (ModLifecycle)rewindhandlers1.method64(ModTraits.field12, arg0 -> ModLifecycle.method13());
      if (framework10extension2 != null) {
         framework10extension2.method4(this);
      }

      this.method13();
   }

   private void method13() {
      ReplayProjectManager rewind2_31 = this.field8.method40();
      File file2 = new File(rewind2_31.method32(), "render_queue");
      if (file2.isDirectory()) {
         for (File file6 : Objects.requireNonNull(file2.listFiles())) {
            if (file6.isFile() && file6.getName().endsWith(".json")) {
               try {
                  TimelineRenderJob highlightimpl7 = (TimelineRenderJob)rewind2_31.method12(file6, TimelineRenderJob.class);
                  highlightimpl7.method2(file6);
                  this.field14.add(highlightimpl7);
               } catch (IOException exception8) {
                  throw new RuntimeException(exception8);
               }
            }
         }
      }
   }

   public File method14() {
      String text1 = (String)Ref.method4().method90().method18().get();
      if (text1.isEmpty()) {
         return RewindPaths.field11;
      }

      File file2 = new File(text1);
      return !file2.isDirectory() ? RewindPaths.field11 : file2;
   }

   public File method15() {
      String text1 = (String)Ref.method4().method90().method19().get();
      if (text1.isEmpty()) {
         return RewindPaths.field12;
      }

      File file2 = new File(text1);
      return !file2.isDirectory() ? RewindPaths.field12 : file2;
   }

   public void method4(ReplayTimeline highlight_31) {
      try {
         com.moonsworth.lunar.client.replay.export.ExportSettings rewindhandlersnameplate2 = highlight_31.method13();
         File file3 = Gui4.method9(
            null, new File(this.method14(), highlight_31.getName() + "." + rewindhandlersnameplate2.field1.id()), rewindhandlersnameplate2.field1.toString(), new String[]{rewindhandlersnameplate2.field1.id()}
         );
         if (file3 != null) {
            if (!file3.getName().toLowerCase().endsWith(rewindhandlersnameplate2.field1.id().toLowerCase())) {
               file3 = new File(file3.getAbsolutePath() + "." + rewindhandlersnameplate2.field1.id());
            }

            Ref.method4().method90().method18().OIRHOOIICOCIOOHICRRRICORIHHIHC(file3.getParent());
            ReplayProjectManager rewind2_34 = this.field8.method40();
            File file5 = rewind2_34.method23(this.field15, highlight_31, String.valueOf(this.field14.size()));
            TimelineRenderJob highlightimpl6 = (TimelineRenderJob)rewind2_34.method12(file5, TimelineRenderJob.class);
            highlightimpl6.setId(UUID.randomUUID());
            highlightimpl6.method1(PathUtils.method2(file3, RewindPaths.field11));
            highlightimpl6.method2(file5);
            rewind2_34.method23(this.field15, highlightimpl6, String.valueOf(this.field14.size()));
            this.field14.add(highlightimpl6);
         }
      } catch (IOException exception7) {
         throw new RuntimeException(exception7);
      }
   }

   public void method5(TimelineRenderJob highlightimpl1) {
      highlightimpl1.method5().delete();
      this.field14.remove(highlightimpl1);
   }

   public void method16() {
      if (this.field10) {
         if (this.paused) {
            this.field17 = true;
         } else {
            if (Ref.MC_VERSION <= 25) {
               if (!this.field17) {
                  long number1 = System.currentTimeMillis();
                  if (number1 - this.field18 >= 33L) {
                     this.field18 = number1;
                     this.field17 = true;
                  }
               } else {
                  this.field17 = false;
               }
            } else {
               this.field17 = true;
            }

            ReplayTimeline highlight_39 = ((ReplayContext)this.field8.method42().get()).method4();
            boolean flag2 = highlight_39.method22()
               || !this.field12.method6()
               || !((ReplayContext)this.field8.method42().get()).method18()
               || !this.field8.method41().method2()
               || this.field8.isReloading()
               || this.field8.method48().method28()
               || !this.method19();
            if (flag2) {
               field20 = true;
            }

            if (!flag2 && this.method21() && (!field20 || !this.field9.method5())) {
               if (field20 && this.field23) {
                  this.field21++;
                  if (this.field21 < 2 && System.currentTimeMillis() - this.field22 < 5000L) {
                     return;
                  }
               }

               field20 = false;
               this.field21 = 0;
               this.field22 = 0L;
               this.field23 = false;
               com.moonsworth.lunar.client.replay.export.ExportSettings rewindhandlersnameplate3 = highlight_39.method13();
               boolean flag4 = this.field11.method2(rewindhandlersnameplate3);
               this.method10(rewindhandlersnameplate3);
               if (flag4) {
                  highlight_39.setPaused(true);
                  this.frameCount++;
                  if (rewindhandlersnameplate3.method6() && rewindhandlersnameplate3.method2().isSupportsAudio()) {
                     this.field13.method3(this.field8, this.frameCount);
                  }

                  int number5 = rewindhandlersnameplate3.method11();
                  boolean flag6 = number5 > 0 && highlight_39.method15() >= number5;
                  if ((this.field8.method25() || flag6) && !this.field8.isReloading()) {
                     this.method23();
                  } else {
                     int number7 = this.frameCount * rewindhandlersnameplate3.fps / rewindhandlersnameplate3.field4;
                     int number8 = Math.max(0, rewindhandlersnameplate3.method10());
                     highlight_39.method8(number8 + number7);
                  }
               }
            } else {
               this.field21 = 0;
               this.field23 = true;
               if (this.field22 == 0L) {
                  this.field22 = System.currentTimeMillis();
               }
            }
         }
      }
   }

   public void method17() {
      if (this.field10) {
         field20 = true;
      }
   }

   private boolean method19() {
      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      LocalPlayerContext nameplate32 = ((ReplayContext)this.field8.method42().get()).method7();
      int number3 = (int)Math.floor(nameplate32.getX() / 16.0);
      int number4 = (int)Math.floor(nameplate32.getZ() / 16.0);
      return itemcounter6extension1 != null && itemcounter6extension1.bridge$isChunkLoaded(number3, number4);
   }

   private boolean method21() {
      if (Ref.method3().bridge$getLevelRenderer().bridge$hasRenderedAllChunks()) {
         this.field19 = 0L;
         return true;
      }

      long number1 = System.currentTimeMillis();
      if (this.field19 == 0L) {
         this.field19 = number1;
      }

      return number1 - this.field19 > 5000L;
   }

   private void method10(com.moonsworth.lunar.client.replay.export.ExportSettings rewindhandlersnameplate1) {
      ByteBuffer buffer2;
      while ((buffer2 = this.field11.method3()) != null) {
         try {
            VideoFrameBuffer nameplate3 = new VideoFrameBuffer(buffer2, rewindhandlersnameplate1.getHeight(), rewindhandlersnameplate1.getWidth());
            this.field12.method2(nameplate3);
         } catch (IOException exception4) {
            throw new RuntimeException(exception4);
         }
      }
   }

   public void method22() {
      if (this.field14.isEmpty()) {
         throw new IllegalStateException("Render queue is empty");
      }

      this.field16 = true;
      this.method12((ReplayTimeline)this.field14.peek());
   }

   public void method12(ReplayTimeline highlight_31) {
      if (!this.field12.method5()) {
         Ref.method4()
            .method69()
            .method6(NotificationType.ERROR, "RewindMod", "Cannot render, FFMPEG is missing from .minecraft/ffmpeg")
            .method9(NotificationAnchor.BOTTOM_RIGHT);
      } else if (!this.field8.method44()) {
         this.field8.method34(true);
         Ref.method3().bridge$schedule(() -> {
            try {
               this.method12(highlight_31);
            } catch (IOException exception3x) {
               throw new RuntimeException(exception3x);
            }
         });
      } else {
         this.field8.method48().method22();
         RewindPaths.field11.mkdirs();
         com.moonsworth.lunar.client.replay.export.ExportSettings rewindhandlersnameplate2 = highlight_31.method13();
         rewindhandlersnameplate2.setWidth(Math.max(1, rewindhandlersnameplate2.getWidth()));
         rewindhandlersnameplate2.setHeight(Math.max(1, rewindhandlersnameplate2.getHeight()));
         File file3;
         if (highlight_31 instanceof TimelineRenderJob highlightimpl4) {
            file3 = RewindPaths.field11.toPath().resolve(highlightimpl4.method4()).toFile();
         } else {
            file3 = Gui4.method9(
               null, new File(this.method14(), highlight_31.getName() + "." + rewindhandlersnameplate2.field1.id()), rewindhandlersnameplate2.field1.toString(), new String[]{rewindhandlersnameplate2.field1.id()}
            );
         }

         if (file3 == null) {
            highlight_31.method6();
         } else {
            if (!file3.getName().toLowerCase().endsWith(rewindhandlersnameplate2.field1.id().toLowerCase())) {
               file3 = new File(file3.getAbsolutePath() + "." + rewindhandlersnameplate2.field1.id());
            }

            Ref.method4().method90().method18().OIRHOOIICOCIOOHICRRRICORIHHIHC(file3.getParent());
            if (file3.isFile() && !file3.delete()) {
               Ref.method4().method69().method6(NotificationType.ERROR, "RewindMod", "Cannot overwrite file").method9(NotificationAnchor.BOTTOM_RIGHT);
               this.method24();
            } else {
               this.field10 = true;
               this.paused = false;
               this.frameCount = 0;
               this.field19 = 0L;
               this.field21 = 0;
               this.field22 = 0L;
               this.field23 = false;
               field20 = true;
               FlawlessFrames.set(true);
               this.field8.method40().method15(highlight_31);
               this.field12.method1(file3, rewindhandlersnameplate2);
               if (rewindhandlersnameplate2.method6() && rewindhandlersnameplate2.method2().isSupportsAudio()) {
                  try {
                     this.field13.method2(new File(file3.getParentFile(), file3.getName() + ".pcm"), rewindhandlersnameplate2, this.field8);
                  } catch (FileNotFoundException filenotfoundexception5) {
                     throw new RuntimeException(filenotfoundexception5);
                  }
               }

               highlight_31.method6();
               highlight_31.method8(Math.max(0, rewindhandlersnameplate2.method10()));
               highlight_31.setPaused(true);
               ReplayDriverHandler.method19().method4("rewindEffects");
               this.field8.method49().method17();
               this.field8.method40().method45().method5(file3, highlight_31, rewindhandlersnameplate2, this.field12);
            }
         }
      }
   }

   public void method23() {
      if (this.field10) {
         ReplayTimeline highlight_31 = ((ReplayContext)this.field8.method42().get()).method4();
         com.moonsworth.lunar.client.replay.export.ExportSettings rewindhandlersnameplate2 = highlight_31.method13();
         this.field10 = false;
         field20 = false;
         this.field12.method3(() -> this.method10(rewindhandlersnameplate2));
         FlawlessFrames.set(false);
         if (rewindhandlersnameplate2.method6() && rewindhandlersnameplate2.method2().isSupportsAudio()) {
            try {
               this.field13.method4(this.field8);
            } catch (IOException exception6) {
               throw new RuntimeException(exception6);
            }
         }

         try {
            this.field12.method4(this.field12.getFile(), this.field13.getFile(), rewindhandlersnameplate2);
         } catch (Exception exception5) {
            throw new RuntimeException(exception5);
         }

         if (this.field16) {
            this.field14.remove().method5().delete();
            if (!this.field14.isEmpty()) {
               try {
                  this.method22();
                  return;
               } catch (IOException exception4) {
                  this.method24();
                  throw new RuntimeException(exception4);
               }
            }
         }

         this.method24();
      }
   }

   private void method24() {
      this.field16 = false;
      this.field8.method40().method16();
      this.field11.close();
      if (this.field12.getFile() != null && this.field12.getFile().isFile()) {
         BrowserUtils.method10(this.field12.getFile().getParentFile());
         Ref.method4()
            .method69()
            .method6(NotificationType.SUCCESS, "RewindMod", "Opening rendered video in your file explorer...")
            .method9(NotificationAnchor.BOTTOM_RIGHT);
      }
   }

   public String getId() {
      return "REWIND_HANDLERS";
   }

   @Generated
   public boolean method25() {
      return this.field10;
   }

   @Generated
   public boolean isPaused() {
      return this.paused;
   }

   @Generated
   public void setPaused(boolean flag1) {
      this.paused = flag1;
   }

   @Generated
   public FFmpegRenderer method26() {
      return this.field12;
   }

   @Generated
   public LoopbackAudioRecorder method27() {
      return this.field13;
   }

   @Generated
   public Queue<TimelineRenderJob> method28() {
      return this.field14;
   }

   @Generated
   public boolean method29() {
      return this.field17;
   }

   @Generated
   public static boolean method30() {
      return field20;
   }
}
