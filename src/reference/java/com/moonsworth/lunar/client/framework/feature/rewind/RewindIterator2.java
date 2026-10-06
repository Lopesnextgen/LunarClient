package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.common.v1.Location;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.coordinates.FogIterator;
import com.moonsworth.lunar.client.framework.LiveExperienceManager;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui5;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui7;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType4;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.RewindThread;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.colorsaturation.Nameplate2Impl;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Task;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore_3;
import com.moonsworth.lunar.client.framework.feature.screenshot.Screenshot2;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.network.MixinHelper26;
import com.moonsworth.lunar.network.GameRewindRecordingLocation;
import com.moonsworth.lunar.network.GameRewindRecordingEvent;
import com.moonsworth.lunar.network.GameRewindRecordingLocation.Type;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import lombok.Generated;
import org.apache.commons.io.FileUtils;

public class RewindIterator2 implements Rewind_4 {
   private static final DateFormat field1 = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   private final com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2 field2;
   private final File field3;
   private final FileOutputStream field4;
   private final DataOutputStream field5;
   private final List<File> field6 = new ArrayList<>();
   private long startTime = -1L;
   private long duration = 0L;
   private boolean field7 = false;
   private boolean closed = false;
   private boolean field8 = false;
   private int tick = 0;
   private final List<RewindThread> field9 = new ArrayList<>();
   private RewindThread field10 = null;
   private final List<File> field11 = new ArrayList<>();
   private final RewindHandlers5 field12;
   private final RewindhandlersNameplateCore4Task field13;
   private final RewindhandlersNameplateCore4 field14;
   private RewindhandlersNameplateCore_3 field15;
   private RewindhandlersNameplateCore_3 field16;
   private File field17;
   private long field18 = 0L;
   private final boolean field19;
   private long field20;
   private long field21 = 1000L;
   private int field22 = 0;
   private Gui7 field23 = null;
   private String field24 = null;
   private boolean field25 = true;
   private final MixinHelper26 field26 = new MixinHelper26();

   public RewindIterator2(RewindHandlers5 var1, RewindhandlersNameplateCore4Task var2, RewindhandlersNameplateCore4 var3, boolean var4, boolean var5) {
      this.field12 = var1;
      this.field13 = var2;
      this.field14 = var3;
      this.field19 = var5;
      Gui.field5.mkdirs();
      if (ThreadModuleDumpType2.isWindows()) {
         Files.setAttribute(Gui.field5.toPath(), "dos:hidden", true);
      }

      Gui.field9.mkdirs();
      if (ThreadModuleDumpType2.isWindows()) {
         Files.setAttribute(Gui.field9.toPath(), "dos:hidden", true);
      }

      long var6 = System.currentTimeMillis();
      this.field3 = new File(Gui.field5, "rewind_" + var6);
      this.field3.deleteOnExit();
      this.field4 = new FileOutputStream(this.field3);
      this.field5 = new DataOutputStream(new BufferedOutputStream(this.field4));
      this.field2 = new com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2();
      this.field2.method14(3);
      this.field2.method23(RewindType.DYNAMIC);
      this.field2.method24(RewindType.ZSTD);
      if (var4) {
         this.field17 = new File(Gui.field5, "backup_" + var6);
      }

      if (var2 != null) {
         this.field15 = new RewindhandlersNameplateCore_3("mic", new File(Gui.field5, "audio_mic_" + var6), true);
      }

      if (var3 != null) {
         this.field16 = new RewindhandlersNameplateCore_3("system", new File(Gui.field5, "audio_system_" + var6), true);
      }

      if (ThreadModuleDump63.method7() != null) {
         this.method1(ThreadModuleDump63.method7());
      }

      this.method9();
   }

   private void method1(Bridge5Extension_5 var1) {
      this.field2.method15(Bridge.getMinecraftVersion().method45());
      this.field2.method16(ThreadModuleDump63.method3().bridge$getProtocolVersion());
      this.field2.setTimestamp(System.currentTimeMillis());
      this.field2.setPlayerName(var1.bridge$getName());
      this.field2.method20(var1.bridge$getUniqueID());
      this.field2.setShadow(this.field19);
      IchorPipeline var2 = (IchorPipeline)IchorAPI.getPipeline(this.getClass().getClassLoader()).orElseThrow(() -> new IllegalStateException("Pipeline not found!"));
      var2.method18().forEach(var1x -> {
         if (!var1x.getId().startsWith("fabric-") || !var1x.getVersion().contains("+")) {
            this.field2.method11().put(var1x.getId(), var1x.getVersion());
         }
      });
      this.method6();
      this.field7 = true;
      this.method9();
   }

   private void method6() {
      Bridge3_19 var1 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
      FogIterator var2 = ThreadModuleDump63.method4().method81();
      LiveExperienceManager var3 = ThreadModuleDump63.method4().method83();
      Location var4 = EntityRenderer4.method82();
      TranslationManager var5 = ThreadModuleDump63.method4().method67();
      Gui7 var6 = null;
      if (var3.method2() != null && var3.method3()) {
         String var12 = this.field2.method9().putIfAbsent("liveExperience", var3.method2().getName());
         var6 = new Gui7(GuiType4.LIVE_EXPERIENCE, var3.method2().getId());
         if (var12 == null) {
            this.field26.addLocationsItem(new GameRewindRecordingLocation().method1(Type.LIVE_EXPERIENCE).method4(var3.method2().getName()).method10(var3.method2().getId()));
         }
      } else if (!var2.method20() && !var2.method26()) {
         if (ThreadModuleDump63.method3().bridge$getIntegratedServer() != null) {
            if (this.field23 != null && this.field23.method1() == GuiType4.SINGLEPLAYER) {
               var6 = this.field23;
            } else {
               String var9 = this.field2.method9().putIfAbsent("singleplayer", var5.method2("gui.components", "singleplayer", new Object[0]));
               var6 = new Gui7(
                  GuiType4.SINGLEPLAYER,
                  Gui5.method2(
                     ThreadModuleDump63.method3().bridge$getIntegratedServer().bridge$getWorldDirectory(),
                     new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "saves")
                  )
               );
               if (var9 == null) {
                  this.field26.addLocationsItem(new GameRewindRecordingLocation().method1(Type.SINGLE_PLAYER));
               }
            }
         } else if (var4.hasPublicServer()) {
            String var10 = this.field2.method9().putIfAbsent(var4.getPublicServer().getServerMappingsId(), var4.getPublicServer().getName());
            var6 = new Gui7(GuiType4.MULTIPLAYER, var4.getPublicServer().getPrimaryAddress());
            if (var1 != null) {
               this.field2.method9().put(var1.bridge$serverIP(), null);
               this.field26.getLocations().removeIf(var1x -> Objects.equals(var1x.getIp(), var1.bridge$serverIP()));
               var6 = new Gui7(GuiType4.MULTIPLAYER, var1.bridge$serverIP());
            }

            if (var10 == null) {
               this.field26
                  .method5(
                     new GameRewindRecordingLocation()
                        .method1(Type.SERVER)
                        .method5(var4.getPublicServer().getPrimaryAddress())
                        .method4(var4.getPublicServer().getName())
                        .method8(var4.getPublicServer().getServerMappingsId())
                  );
            }
         } else if (var1 != null) {
            String var11 = this.field2.method9().putIfAbsent(var1.bridge$serverIP(), var1.bridge$getServerName());
            var6 = new Gui7(GuiType4.MULTIPLAYER, var1.bridge$serverIP());
            if (var11 == null) {
               this.field26.addLocationsItem(new GameRewindRecordingLocation().method1(Type.SERVER).method5(var1.bridge$serverIP()).method4(var1.bridge$getServerName()));
            }
         }
      } else {
         String var7;
         if (var2.method37() != null) {
            var7 = var5.method2("gui.components", "hostedWorldNameText", new Object[]{var2.method37().username()});
         } else {
            var7 = var5.method2("gui.components", "hostedWorld", new Object[0]);
         }

         String var8 = this.field2.method9().putIfAbsent("hostedWorld", var7);
         var6 = new Gui7(GuiType4.HOSTED_WORLD, var7);
         if (var8 == null) {
            this.field26.addLocationsItem(new GameRewindRecordingLocation().method1(Type.HOSTED_WORLD));
         }
      }

      if (this.field24 == null && !this.field2.method9().isEmpty()) {
         for (String var16 : this.field2.method9().values()) {
            if (var16 != null) {
               this.field24 = var16;
               break;
            }
         }

         if (var4.hasPublicServer()) {
            String var14 = var4.getPublicServer().getRichStatus().getGameName();
            if (!var14.isEmpty()) {
               this.field24 = this.field24 + " - " + var14;
            }
         }

         LocalDateTime var15 = LocalDateTime.now();
         DateTimeFormatter var17 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT).withLocale(Locale.getDefault());
         this.field24 = this.field24 + " - " + var15.format(var17);
      }

      this.field23 = var6;
   }

   @Override
   public List<Integer> method1(List<File> var1) {
      List var2 = Rewind_4.method13(var1, this.field6);
      this.method9();
      return var2;
   }

   @Override
   public void pause() {
      if (this.startTime != -1L) {
         this.duration = this.duration + (System.currentTimeMillis() - this.startTime);
         this.startTime = -1L;
         this.method9();
      }
   }

   @Override
   public long method3() {
      return this.method4();
   }

   @Override
   public long method4() {
      return this.startTime == -1L ? this.duration : this.duration + System.currentTimeMillis() - this.startTime;
   }

   @Override
   public void mark() {
      this.field2.getMarkers().add(this.method4());
      this.method9();
   }

   @Override
   public synchronized void method7() {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      this.field5.flush();
      String var1 = this.field10 == null ? "" : this.field10.method3().toString();
      com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2.Data var2 = new com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2.Data(
         this.field4.getChannel().size(), var1
      );
      this.field2.method7().put(this.method4(), var2);
      this.field8 = true;
      this.method9();
   }

   @Override
   public synchronized void method9(Nameplate2 var1, int var2) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      if (var2 == -1) {
         var2 = this.tick;
      }

      if (this.tick != var2) {
         if (var2 < this.tick) {
            throw new IllegalArgumentException("Tick is less than the previous tick");
         }

         if (this.field25) {
            this.field12.method13();
         }

         this.tick = var2;
         this.method9(new Nameplate2Impl(var2), var2);
         this.method6();
         if (this.method4() - this.field20 > this.field21) {
            this.method14();
         }
      }

      if (!this.field7 && ThreadModuleDump63.method7() != null) {
         this.method1(ThreadModuleDump63.method7());
      }

      if (this.field18 + 30000L < System.currentTimeMillis()) {
         this.method9();
      }

      if (this.startTime == -1L) {
         this.startTime = System.currentTimeMillis();
         if (this.field15 != null) {
            this.field15.method1(this.field13);
         }

         if (this.field16 != null) {
            this.field16.method1(this.field14);
         }
      }

      if (var1 instanceof Nameplate2Impl2 var3) {
         RewindThread var4 = new RewindThread(this.field2.method13());
         this.method9(new Nameplate2Impl3(var4.method3(), var3.method4()), var2);
         this.field10 = var4;
         File var5 = ThreadModuleDump63.method4().method40().method6();
         File var6 = new File(Gui.field5, "mods.json_" + System.currentTimeMillis() + "_" + this.field10.method3());
         if (var5.isFile()) {
            FileUtils.copyFile(var5, var6);
            var6.deleteOnExit();
            this.field11.add(var6);
         }
      }

      if (this.field10 != null) {
         this.field10.method1(var1);
      } else {
         Rewind_4.method12(var1, this.field5, this.field2.method12());
      }

      if (var1 instanceof com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl) {
         this.field9.add(this.field10);
         this.field10.start();
         this.field10 = null;
      }
   }

   @Override
   public synchronized void method10() {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      if (this.field15 != null) {
         this.field15.method2(this.field13);

         try {
            this.field15.close();
         } catch (IOException var8) {
            var8.printStackTrace();
         }

         this.field15.getFile().delete();
      }

      if (this.field16 != null) {
         this.field16.method2(this.field14);

         try {
            this.field16.close();
         } catch (IOException var7) {
            var7.printStackTrace();
         }

         this.field16.getFile().delete();
      }

      try {
         this.field5.close();
      } catch (IOException var6) {
         var6.printStackTrace();
      }

      for (RewindThread var2 : this.field9) {
         var2.interrupt();

         try {
            var2.close();
         } catch (IOException var5) {
            var5.printStackTrace();
         }

         var2.method2();
      }

      this.closed = true;
      this.field3.delete();

      for (File var11 : this.field11) {
         var11.delete();
      }

      if (this.field17 != null) {
         this.field17.delete();
      }

      File var10 = new File(Gui.field9, this.field2.getId().toString());

      try {
         FileUtils.deleteDirectory(var10);
      } catch (IOException var4) {
         var4.printStackTrace();
      }
   }

   private void method9() {
      if (this.field17 != null) {
         try (FileWriter var1 = new FileWriter(this.field17, StandardCharsets.UTF_8)) {
            JsonObject var2 = new JsonObject();
            var2.addProperty("duration", this.method4());
            var2.addProperty("rewind", this.field3.getName());
            if (this.field15 != null) {
               JsonObject var3 = new JsonObject();
               var3.addProperty("id", this.field15.getId());
               var3.addProperty("file", this.field15.getFile().getName());
               var2.add("micAudio", var3);
            }

            if (this.field16 != null) {
               JsonObject var11 = new JsonObject();
               var11.addProperty("id", this.field16.getId());
               var11.addProperty("file", this.field16.getFile().getName());
               var2.add("systemAudio", var11);
            }

            JsonObject var12 = new JsonObject();

            for (RewindThread var5 : this.field9) {
               var12.addProperty(var5.method3().toString(), var5.getFile().getName());
            }

            var2.add("snapshots", var12);
            JsonArray var13 = new JsonArray();

            for (File var6 : this.field6) {
               var13.add(var6.getAbsolutePath());
            }

            var2.add("packs", var13);
            JsonArray var15 = new JsonArray();

            for (File var7 : this.field11) {
               var15.add(var7.getAbsolutePath());
            }

            var2.add("files", var15);
            var2.add("metadata", ThreadModuleDump48.field22.toJsonTree(this.field2));
            if (this.field13 != null) {
               this.field13.method5().setDuration(this.field2.method1());
               var2.add("micAudioMetadata", ThreadModuleDump48.field22.toJsonTree(this.field13.method5()));
            }

            if (this.field14 != null) {
               this.field14.method5().setDuration(this.field2.method1());
               var2.add("loopbackAudioMetadata", ThreadModuleDump48.field22.toJsonTree(this.field14.method5()));
            }

            var1.write(var2.toString());
         } catch (IOException var10) {
            var10.printStackTrace();
         }

         this.field18 = System.currentTimeMillis();
      }
   }

   @Override
   public synchronized boolean method11() {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      if (!this.field7) {
         if (ThreadModuleDump63.method7() == null) {
            this.method10();
            return false;
         }

         this.method1(ThreadModuleDump63.method7());
      }

      this.field2.setDuration(this.method4());
      if (this.field15 != null) {
         this.field15.method2(this.field13);
      }

      if (this.field16 != null) {
         this.field16.method2(this.field14);
      }

      this.field5.close();
      this.closed = true;
      ThreadModuleDump63.method3().bridge$submit(this::method14);
      method11(
         this.field24,
         this.field3,
         this.field2,
         this.field9,
         this.field6,
         this.field15,
         this.field13 == null ? null : this.field13.method5(),
         this.field16,
         this.field14 == null ? null : this.field14.method5(),
         this.field17,
         this.field11
      );
      this.method16();
      return true;
   }

   public static void method11(
      String var0,
      File var1,
      com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2 var2,
      List<RewindThread> var3,
      List<File> var4,
      RewindhandlersNameplateCore_3 var5,
      RewindhandlersNameplateCore3 var6,
      RewindhandlersNameplateCore_3 var7,
      RewindhandlersNameplateCore3 var8,
      File var9,
      List<File> var10
   ) {
      if (var0 == null) {
         var0 = field1.format(new Date());
      }

      var0 = var0.replaceAll("[\\\\/:*?\"<>|]", "_").trim();
      int var12 = 0;

      File var11;
      do {
         var11 = new File(Gui.field8, var0 + (var12 == 0 ? "" : " (" + var12 + ")") + ".rewind");
         var12++;
      } while (var11.exists());

      Gui.field8.mkdirs();
      ZipOutputStream var13 = new ZipOutputStream(new FileOutputStream(var11), StandardCharsets.UTF_8);

      try {
         var13.setLevel(9);
         method12(var1, var13, "packets.dat");
         var13.putNextEntry(new ZipEntry("metadata.json"));
         var13.write(ThreadModuleDump48.field22.toJson(var2).getBytes(StandardCharsets.UTF_8));
         var13.closeEntry();

         for (RewindThread var15 : var3) {
            var15.join();
            var15.close();
            method12(var15.getFile(), var13, "snapshots/" + var15.method3() + "/packets.dat");
            Iterator var16 = var10.iterator();

            while (var16.hasNext()) {
               File var17 = (File)var16.next();
               if (var17.getName().endsWith(var15.method3().toString())) {
                  var16.remove();
                  String var18 = var17.getName().split("_")[0];
                  var13.putNextEntry(new ZipEntry("snapshots/" + var15.method3() + "/" + var18));
                  Files.copy(var17.toPath(), var13);
                  var13.closeEntry();
               }
            }
         }

         for (int var23 = 0; var23 < var4.size(); var23++) {
            File var26 = (File)var4.get(var23);
            var13.putNextEntry(new ZipEntry("packs/" + var23));
            Files.copy(var26.toPath(), var13);
            var13.closeEntry();
         }

         for (File var27 : var10) {
            var13.putNextEntry(new ZipEntry("files/" + var27.getName().split("_")[0]));
            Files.copy(var27.toPath(), var13);
            var13.closeEntry();
         }

         if (var5 != null) {
            var5.close();
            var13.putNextEntry(new ZipEntry("audio/" + var5.getId() + ".dat"));
            Files.copy(var5.getFile().toPath(), var13);
            var13.closeEntry();
            var13.putNextEntry(new ZipEntry("audio/" + var5.getId() + ".json"));
            var6.setDuration(var2.method1());
            var13.write(ThreadModuleDump48.field22.toJson(var6).getBytes(StandardCharsets.UTF_8));
            var13.closeEntry();
            var5.getFile().delete();
         }

         if (var7 != null) {
            var7.close();
            var13.putNextEntry(new ZipEntry("audio/" + var7.getId() + ".dat"));
            Files.copy(var7.getFile().toPath(), var13);
            var13.closeEntry();
            var13.putNextEntry(new ZipEntry("audio/" + var7.getId() + ".json"));
            var8.setDuration(var2.method1());
            var13.write(ThreadModuleDump48.field22.toJson(var8).getBytes(StandardCharsets.UTF_8));
            var13.closeEntry();
            var7.getFile().delete();
         }
      } catch (Throwable var20) {
         try {
            var13.close();
         } catch (Throwable var19) {
            var20.addSuppressed(var19);
         }

         throw var20;
      }

      var13.close();
      var1.delete();
      if (var9 != null) {
         var9.delete();
      }

      for (RewindThread var25 : var3) {
         var25.method2();
      }
   }

   private static void method12(File var0, ZipOutputStream var1, String var2) {
      long var3 = var0.length();
      long var5 = method13(var0);
      ZipEntry var7 = new ZipEntry(var2);
      var7.setMethod(0);
      var7.setSize(var3);
      var7.setCompressedSize(var3);
      var7.setCrc(var5);
      var1.putNextEntry(var7);
      Files.copy(var0.toPath(), var1);
      var1.closeEntry();
   }

   private static long method13(File var0) {
      CRC32 var1 = new CRC32();
      byte[] var2 = new byte[65536];

      int var5;
      try (
         FileInputStream var3 = new FileInputStream(var0);
         BufferedInputStream var4 = new BufferedInputStream(var3);
      ) {
         while ((var5 = var4.read(var2)) >= 0) {
            var1.update(var2, 0, var5);
         }
      }

      return var1.getValue();
   }

   private void method14() {
      if (ThreadModuleDump63.method8() != null
         && ThreadModuleDump63.method3().bridge$hasInGameFocus()
         && ThreadModuleDump63.method3().bridge$getCurrentScreen() == null) {
         this.method15();
         this.field20 = this.method4();
         if (this.field21 <= 1000L) {
            this.field21 = 10000L;
         } else if (this.field21 <= 10000L) {
            this.field21 = 30000L;
         } else if (this.field21 < 120000L) {
            this.field21 *= 2L;
         } else {
            this.field21 = 300000L;
         }
      } else {
         this.field20 = this.method4() - this.field21 + 1000L;
      }
   }

   private void method15() {
      File var1 = new File(Gui.field9, this.field2.getId().toString());
      File var2 = new File(var1, String.format("%04d.png", this.field22));
      this.field22++;
      Screenshot2 var3 = new Screenshot2();
      var3.method5(
         null,
         var2,
         ThreadModuleDump63.method3().bridge$displayWidth(),
         ThreadModuleDump63.method3().bridge$displayHeight(),
         ThreadModuleDump63.method3().bridge$getMainRenderTarget(),
         false,
         var0 -> {
            int var1x = ThreadModuleDump63.method3().bridge$displayWidth();
            int var2x = ThreadModuleDump63.method3().bridge$displayHeight();
            if (var1x < 560 && var2x < 560) {
               return null;
            }

            BufferedImage var3x = new BufferedImage(var1x, var2x, 1);
            var3x.setRGB(0, 0, var1x, var2x, var0, 0, var1x);
            if (var1x > var2x) {
               var2x = 560 * var2x / var1x;
               var1x = 560;
            } else {
               var1x = 560 * var1x / var2x;
               var2x = 560;
            }

            BufferedImage var4 = new BufferedImage(var1x, var2x, 1);
            var4.getGraphics().drawImage(var3x.getScaledInstance(var1x, var2x, 4), 0, 0, null);
            return var4;
         },
         () -> {}
      );
   }

   private void method16() {
      this.field26.playerUuid(BigDecimal.valueOf(this.field2.getDuration())).method8(this.field19).method11(this.field15 != null).method14(this.field16 != null);
      Client.method109().method105().method2(new GameRewindRecordingEvent().method4(this.field26));
   }

   @Generated
   public List<File> method17() {
      return this.field6;
   }

   @Generated
   @Override
   public boolean method2() {
      return this.field7;
   }

   @Generated
   @Override
   public boolean method5() {
      return this.field8;
   }

   @Generated
   @Override
   public void method6(boolean var1) {
      this.field8 = var1;
   }

   @Generated
   public RewindThread method21() {
      return this.field10;
   }

   @Generated
   @Override
   public Gui7 method8() {
      return this.field23;
   }

   @Generated
   public void method23(boolean var1) {
      this.field25 = var1;
   }
}
