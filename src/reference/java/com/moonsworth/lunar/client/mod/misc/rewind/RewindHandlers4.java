package com.moonsworth.lunar.client.mod.misc.rewind;

import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui5;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.HighlightImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Handler;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate_2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.mixin.RewindhandlersNameplate;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.mixin.RewindhandlersNameplateImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.mixin.RewindhandlersNameplateImpl2;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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

public class RewindHandlers4 extends AbstractFeature {
   private final RewindHandlers field8;
   private final Rewindhandlers field9;
   private boolean field10 = false;
   private boolean paused = false;
   private final RewindhandlersNameplate field11 = (RewindhandlersNameplate)(ThreadModuleDump63.method43()
      ? new RewindhandlersNameplateImpl2()
      : new RewindhandlersNameplateImpl());
   private final RewindhandlersNameplate_3 field12 = new RewindhandlersNameplate_3(this.field11);
   private final RewindhandlersNameplate_2 field13 = new RewindhandlersNameplate_2();
   private final Queue<HighlightImpl> field14 = new LinkedList<>();
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

   public RewindHandlers4(RewindHandlers var1) {
      super(true);
      this.field8 = var1;
      this.field9 = var1.method41();
      this.field15 = new File(var1.method40().method32(), "render_queue");
      Framework10Extension var2 = (Framework10Extension)var1.method64(Framework.field12, var0 -> Framework10Extension.method13());
      if (var2 != null) {
         var2.method4(this);
      }

      this.method13();
   }

   private void method13() {
      Rewind2_3 var1 = this.field8.method40();
      File var2 = new File(var1.method32(), "render_queue");
      if (var2.isDirectory()) {
         for (File var6 : Objects.requireNonNull(var2.listFiles())) {
            if (var6.isFile() && var6.getName().endsWith(".json")) {
               try {
                  HighlightImpl var7 = (HighlightImpl)var1.method12(var6, HighlightImpl.class);
                  var7.method2(var6);
                  this.field14.add(var7);
               } catch (IOException var8) {
                  throw new RuntimeException(var8);
               }
            }
         }
      }
   }

   public File method14() {
      String var1 = (String)ThreadModuleDump63.method4().method90().method18().get();
      if (var1.isEmpty()) {
         return Gui.field11;
      }

      File var2 = new File(var1);
      return !var2.isDirectory() ? Gui.field11 : var2;
   }

   public File method15() {
      String var1 = (String)ThreadModuleDump63.method4().method90().method19().get();
      if (var1.isEmpty()) {
         return Gui.field12;
      }

      File var2 = new File(var1);
      return !var2.isDirectory() ? Gui.field12 : var2;
   }

   public void method4(Highlight_3 var1) {
      try {
         com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate var2 = var1.method13();
         File var3 = Gui4.method9(
            null, new File(this.method14(), var1.getName() + "." + var2.field1.id()), var2.field1.toString(), new String[]{var2.field1.id()}
         );
         if (var3 != null) {
            if (!var3.getName().toLowerCase().endsWith(var2.field1.id().toLowerCase())) {
               var3 = new File(var3.getAbsolutePath() + "." + var2.field1.id());
            }

            ThreadModuleDump63.method4().method90().method18().OIRHOOIICOCIOOHICRRRICORIHHIHC(var3.getParent());
            Rewind2_3 var4 = this.field8.method40();
            File var5 = var4.method23(this.field15, var1, String.valueOf(this.field14.size()));
            HighlightImpl var6 = (HighlightImpl)var4.method12(var5, HighlightImpl.class);
            var6.setId(UUID.randomUUID());
            var6.setSavePath(Gui5.method2(var3, Gui.field11));
            var6.method2(var5);
            var4.method23(this.field15, var6, String.valueOf(this.field14.size()));
            this.field14.add(var6);
         }
      } catch (IOException var7) {
         throw new RuntimeException(var7);
      }
   }

   public void method5(HighlightImpl var1) {
      var1.method5().delete();
      this.field14.remove(var1);
   }

   public void method16() {
      if (this.field10) {
         if (this.paused) {
            this.field17 = true;
         } else {
            if (ThreadModuleDump63.MC_VERSION <= 25) {
               if (!this.field17) {
                  long var1 = System.currentTimeMillis();
                  if (var1 - this.field18 >= 33L) {
                     this.field18 = var1;
                     this.field17 = true;
                  }
               } else {
                  this.field17 = false;
               }
            } else {
               this.field17 = true;
            }

            Highlight_3 var9 = ((Nameplate4)this.field8.method42().get()).method4();
            boolean var2 = var9.method22()
               || !this.field12.method6()
               || !((Nameplate4)this.field8.method42().get()).method18()
               || !this.field8.method41().method2()
               || this.field8.isReloading()
               || this.field8.method48().method28()
               || !this.method19();
            if (var2) {
               field20 = true;
            }

            if (!var2 && this.method21() && (!field20 || !this.field9.isSkipping())) {
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
               com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate var3 = var9.method13();
               boolean var4 = this.field11.method2(var3);
               this.method10(var3);
               if (var4) {
                  var9.setPaused(true);
                  this.frameCount++;
                  if (var3.method6() && var3.method2().isSupportsAudio()) {
                     this.field13.method3(this.field8, this.frameCount);
                  }

                  int var5 = var3.method11();
                  boolean var6 = var5 > 0 && var9.method15() >= var5;
                  if ((this.field8.method25() || var6) && !this.field8.isReloading()) {
                     this.method23();
                  } else {
                     int var7 = this.frameCount * var3.fps / var3.field4;
                     int var8 = Math.max(0, var3.method10());
                     var9.method8(var8 + var7);
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
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      Nameplate3 var2 = ((Nameplate4)this.field8.method42().get()).method7();
      int var3 = (int)Math.floor(var2.getX() / 16.0);
      int var4 = (int)Math.floor(var2.getZ() / 16.0);
      return var1 != null && var1.bridge$isChunkLoaded(var3, var4);
   }

   private boolean method21() {
      if (ThreadModuleDump63.method3().bridge$getLevelRenderer().bridge$hasRenderedAllChunks()) {
         this.field19 = 0L;
         return true;
      }

      long var1 = System.currentTimeMillis();
      if (this.field19 == 0L) {
         this.field19 = var1;
      }

      return var1 - this.field19 > 5000L;
   }

   private void method10(com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate var1) {
      ByteBuffer var2;
      while ((var2 = this.field11.method3()) != null) {
         try {
            Nameplate var3 = new Nameplate(var2, var1.getHeight(), var1.getWidth());
            this.field12.method2(var3);
         } catch (IOException var4) {
            throw new RuntimeException(var4);
         }
      }
   }

   public void method22() {
      if (this.field14.isEmpty()) {
         throw new IllegalStateException("Render queue is empty");
      }

      this.field16 = true;
      this.method12((Highlight_3)this.field14.peek());
   }

   public void method12(Highlight_3 var1) {
      if (!this.field12.method5()) {
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Cannot render, FFMPEG is missing from .minecraft/ffmpeg")
            .method9(NotificationAnchor.BOTTOM_RIGHT);
      } else if (!this.field8.method44()) {
         this.field8.method34(true);
         ThreadModuleDump63.method3().bridge$schedule(() -> {
            try {
               this.method12(var1);
            } catch (IOException var3x) {
               throw new RuntimeException(var3x);
            }
         });
      } else {
         this.field8.method48().method22();
         Gui.field11.mkdirs();
         com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate var2 = var1.method13();
         var2.setWidth(Math.max(1, var2.getWidth()));
         var2.setHeight(Math.max(1, var2.getHeight()));
         File var3;
         if (var1 instanceof HighlightImpl var4) {
            var3 = Gui.field11.toPath().resolve(var4.method4()).toFile();
         } else {
            var3 = Gui4.method9(
               null, new File(this.method14(), var1.getName() + "." + var2.field1.id()), var2.field1.toString(), new String[]{var2.field1.id()}
            );
         }

         if (var3 == null) {
            var1.method6();
         } else {
            if (!var3.getName().toLowerCase().endsWith(var2.field1.id().toLowerCase())) {
               var3 = new File(var3.getAbsolutePath() + "." + var2.field1.id());
            }

            ThreadModuleDump63.method4().method90().method18().OIRHOOIICOCIOOHICRRRICORIHHIHC(var3.getParent());
            if (var3.isFile() && !var3.delete()) {
               ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Rewind", "Cannot overwrite file").method9(NotificationAnchor.BOTTOM_RIGHT);
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
               this.field8.method40().method15(var1);
               this.field12.method1(var3, var2);
               if (var2.method6() && var2.method2().isSupportsAudio()) {
                  try {
                     this.field13.method2(new File(var3.getParentFile(), var3.getName() + ".pcm"), var2, this.field8);
                  } catch (FileNotFoundException var5) {
                     throw new RuntimeException(var5);
                  }
               }

               var1.method6();
               var1.method8(Math.max(0, var2.method10()));
               var1.setPaused(true);
               RewindHandlers3Handler.method19().method4("rewindEffects");
               this.field8.method49().method17();
               this.field8.method40().method45().method5(var3, var1, var2, this.field12);
            }
         }
      }
   }

   public void method23() {
      if (this.field10) {
         Highlight_3 var1 = ((Nameplate4)this.field8.method42().get()).method4();
         com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate var2 = var1.method13();
         this.field10 = false;
         field20 = false;
         this.field12.method3(() -> this.method10(var2));
         FlawlessFrames.set(false);
         if (var2.method6() && var2.method2().isSupportsAudio()) {
            try {
               this.field13.method4(this.field8);
            } catch (IOException var6) {
               throw new RuntimeException(var6);
            }
         }

         try {
            this.field12.method4(this.field12.getFile(), this.field13.getFile(), var2);
         } catch (Exception var5) {
            throw new RuntimeException(var5);
         }

         if (this.field16) {
            this.field14.remove().method5().delete();
            if (!this.field14.isEmpty()) {
               try {
                  this.method22();
                  return;
               } catch (IOException var4) {
                  this.method24();
                  throw new RuntimeException(var4);
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
         ThreadModuleDump61.method10(this.field12.getFile().getParentFile());
         ThreadModuleDump63.method4()
            .method69()
            .method6(NotificationType.SUCCESS, "Rewind", "Opening rendered video in your file explorer...")
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
   public void setPaused(boolean var1) {
      this.paused = var1;
   }

   @Generated
   public RewindhandlersNameplate_3 method26() {
      return this.field12;
   }

   @Generated
   public RewindhandlersNameplate_2 method27() {
      return this.field13;
   }

   @Generated
   public Queue<HighlightImpl> method28() {
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
