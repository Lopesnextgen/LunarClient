package com.moonsworth.lunar.client.replay.project;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.replay.timeline.TrackCollectionDeserializer;
import com.moonsworth.lunar.client.replay.timeline.TrackCollection;
import com.moonsworth.lunar.client.replay.timeline.GameplayTrack;
import com.moonsworth.lunar.client.replay.timeline.SegmentTimeline;
import com.moonsworth.lunar.client.replay.timeline.MediaPoolAdapter;
import com.moonsworth.lunar.client.replay.timeline.ThumbnailManager;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.MediaPool;
import com.moonsworth.lunar.client.replay.timeline.PropertyMap;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.TimelineElementRegistry;
import com.moonsworth.lunar.client.replay.timeline.EntityOverrideRegistry;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.audio.AudioStream;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.config.option.InterpolationMode;
import com.moonsworth.lunar.client.util.io.JsonAdapters.SerializedNameExclusionStrategy;
import com.moonsworth.lunar.client.util.io.JsonAdapters.IntegerRangeDeserializer;
import com.moonsworth.lunar.client.config.option.InterpolationMode.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.annotation.Nullable;
import lombok.Generated;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.Range;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_5;
import com.moonsworth.lunar.client.replay.timeline.GameplaySegment;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;

public class ReplayProjectManager {
   private final Gson field1 = new GsonBuilder()
      .registerTypeAdapter(SegmentTimeline.class, new SegmentTimeline.HighlightAdapter(this))
      .registerTypeAdapter(Range.class, new IntegerRangeDeserializer())
      .registerTypeAdapter(TrackCollection.class, new TrackCollectionDeserializer(this))
      .registerTypeAdapter(MediaPool.class, new MediaPoolAdapter(this))
      .registerTypeAdapter(PropertyMap.class, new PropertyMap.PropertyMapAdapter(this))
      .registerTypeAdapter(InterpolationMode.class, new Data())
      .registerTypeHierarchyAdapter(AudioStream.class, new AudioStream.AudioStreamAdapter(this))
      .setExclusionStrategies(new ExclusionStrategy[]{new SerializedNameExclusionStrategy()})
      .create();
   private final File file;
   private final RewindHandlers field2;
   private final boolean field3;
   private final File field4;
   private final File field5;
   private long field6;
   @Nullable
   private com.moonsworth.lunar.client.replay.project.RewindFileReader field7;
   private boolean closed = false;
   private final List<ReplayTimeline> field8 = new ArrayList<>();
   private ReplayTimeline field9;
   private final TimelineElementRegistry field10;
   private final EntityOverrideRegistry field11;
   private final UndoRedoManager field12 = new UndoRedoManager();
   private final ThumbnailManager field13;
   private final com.moonsworth.lunar.client.replay.audio.AudioWaveformRenderer field14;
   private RewindProject field15 = new RewindProject();
   private final com.moonsworth.lunar.client.replay.audio.MusicTrackManager field16 = new com.moonsworth.lunar.client.replay.audio.MusicTrackManager();
   private final Rewind_5 field17;

   public ReplayProjectManager(File file1, RewindHandlers rewindhandlers2, String text3, ExportSettings rewindhandlersnameplate4, boolean flag5) {
      this.file = file1;
      this.field2 = rewindhandlers2;
      this.field3 = flag5;
      this.field17 = new Rewind_5(this, rewindhandlers2);
      File file6 = new File(RewindPaths.field10, Bridge.getMinecraftVersion().method45());
      this.field4 = file1.isDirectory() ? file1 : new File(file6, text3 == null ? file1.getName() : text3);
      if (!flag5) {
         this.field4.mkdirs();
         if (!this.field4.isDirectory()) {
            throw new IOException("Failed to create project folder");
         }
      }

      this.field5 = new File(this.field4, "backups");
      this.cleanup();
      this.method3();
      if (this.field15 == null) {
         this.field15 = new RewindProject();
      }

      this.field13 = new ThumbnailManager(this.field4);
      this.field15.method6().method1(this.field15.method5(), this.field13);
      if (file1.isFile()) {
         this.field7 = this.field15.method6().method6(file1);
         this.field15.method9(this.field7.method13().method4());
         this.field15.method10(this.field7.method13().method5());
         this.field15.method14(this.field7.method13().method11());
      } else {
         this.field15.method9(Bridge.getMinecraftVersion().method45());
         this.field15.method10(Ref.method3().bridge$getProtocolVersion());
         this.field15.method14(Collections.emptyMap());
      }

      this.field10 = new TimelineElementRegistry(rewindhandlers2, this.field12);
      this.field11 = new EntityOverrideRegistry(this.field12);
      this.field14 = new com.moonsworth.lunar.client.replay.audio.AudioWaveformRenderer(this.field4);
      this.field6 = System.currentTimeMillis();
      this.method2();
      this.method11();
      if (this.field8.isEmpty()) {
         this.method5(rewindhandlersnameplate4 == null ? new ExportSettings() : rewindhandlersnameplate4);
      } else {
         for (ReplayTimeline highlight_38 : this.field8) {
            if (highlight_38.getId().equals(this.field15.method4())) {
               this.method14(highlight_38);
               break;
            }
         }

         if (this.field9 == null) {
            this.method14(this.field8.get(0));
         }
      }
   }

   private void cleanup() {
      File file1 = new File(this.field4, "audio_cache");
      if (file1.isDirectory()) {
         for (File file5 : Objects.requireNonNull(file1.listFiles())) {
            if (file5.getName().startsWith("renders_")) {
               try {
                  FileUtils.deleteDirectory(file5);
               } catch (IOException exception7) {
                  throw new RuntimeException(exception7);
               }
            }
         }
      }
   }

   private boolean method1() {
      return this.field5.isDirectory();
   }

   private void method2() {
      if (this.method1()) {
         FileUtils.copyDirectory(this.field5, this.field4);
         FileUtils.deleteDirectory(this.field5);
      }
   }

   private void method3() {
      if (!this.field3) {
         File file1 = new File(this.field4, "project.json");
         if (file1.isFile()) {
            try (FileInputStream stream2 = new FileInputStream(file1)) {
               this.field15 = (RewindProject)this.field1.fromJson(new InputStreamReader(stream2, StandardCharsets.UTF_8), RewindProject.class);
            } catch (Exception exception7) {
               exception7.printStackTrace();
            }
         }
      }
   }

   private synchronized void method4(File file1) {
      File file2 = new File(file1, "project.json");

      try (FileOutputStream stream3 = new FileOutputStream(file2)) {
         stream3.write(this.field1.toJson(this.field15).getBytes(StandardCharsets.UTF_8));
      } catch (Exception exception8) {
         exception8.printStackTrace();
      }
   }

   public void method5(ExportSettings rewindhandlersnameplate1) {
      ReplayTimeline highlight_32 = new ReplayTimeline(this.field12);
      highlight_32.setId(UUID.randomUUID());
      highlight_32.method14(rewindhandlersnameplate1);
      this.method13(highlight_32);
      this.method14(highlight_32);
      if (this.file.isFile()) {
         this.field7 = this.field15.method6().method6(this.file);
         if (this.field7 != null) {
            this.method7(this.field7, 0, null);
         }

         this.field12.clear();
      }

      this.method17(false);
   }

   public void method6(ReplayTimeline highlight_31) {
      this.field8.remove(highlight_31);
      this.method17(true);
      File file2 = new File(this.field4, "timelines/" + highlight_31.getId() + ".json");
      file2.delete();
      if (highlight_31 == this.field9) {
         if (this.field8.isEmpty()) {
            this.method5(new ExportSettings());
         }

         this.method14(this.field8.get(0));
      }
   }

   public void method7(com.moonsworth.lunar.client.replay.project.RewindFileReader rewind31, int number2, UUID uuid3) {
      this.field9.method2(this.field10, rewind31, this.field13, this.field14, this.field12, this.field4, number2, uuid3);
   }

   public com.moonsworth.lunar.client.replay.project.RewindFileReader method8(File file1) {
      return this.field15.method6().method6(file1);
   }

   public com.moonsworth.lunar.client.replay.project.RewindFileReader method9(UUID uuid1) {
      return this.field15.method6().method4(uuid1);
   }

   public boolean method10(UUID uuid1) {
      if (this.field7 != null && this.field7.method13().getId().equals(uuid1)) {
         return false;
      }

      this.field7 = this.method9(uuid1);
      return this.field7 != null;
   }

   private void method11() {
      File file1 = new File(this.field4, "timelines");
      if (file1.isDirectory()) {
         for (File file5 : Objects.requireNonNull(file1.listFiles())) {
            if (file5.getName().endsWith(".json")) {
               try {
                  ReplayTimeline highlight_36 = this.method12(file5, ReplayTimeline.class);
                  if (highlight_36 != null) {
                     this.field8.add(highlight_36);
                  }
               } catch (Exception exception7) {
                  exception7.printStackTrace();
               }
            }
         }
      }
   }

   public <T extends ReplayTimeline> T method12(File file1, Class<T> clazz2) {
      this.field12.setRunning(true);

      try (FileInputStream stream3 = new FileInputStream(file1)) {
         return (T)this.field1.fromJson(new InputStreamReader(stream3, StandardCharsets.UTF_8), clazz2);
      } finally {
         this.field12.setRunning(false);
      }
   }

   public synchronized void method13(ReplayTimeline highlight_31) {
      this.field8.add(highlight_31);
      this.method17(true);
   }

   public void method14(ReplayTimeline highlight_31) {
      this.field15.method11(highlight_31 == null ? null : highlight_31.getId());
      this.field9 = highlight_31;
      this.method17(true);
      this.field12.clear();
      if (this.field2.method40() != null) {
         this.field2.method27();
      }
   }

   public void method15(ReplayTimeline highlight_31) {
      this.field9 = highlight_31;
      if (this.field2.method40() != null) {
         this.field2.method27();
      }
   }

   public void method16() {
      this.field9 = this.field8.stream().filter(arg1 -> arg1.getId().equals(this.field15.method4())).findFirst().orElse(this.field8.get(0));
      if (this.field2.method40() != null) {
         this.field2.method27();
      }
   }

   public synchronized void method17(boolean flag1) {
      if (!this.field3) {
         if (CrashReporter.method10() && this.field2.method47().method30() + 60000L > System.currentTimeMillis()) {
            this.method18();
         }

         File file2 = flag1 ? this.field5 : this.field4;
         this.method22(file2);
         this.method4(file2);
         this.field6 = System.currentTimeMillis();
         if (!flag1) {
            FileUtils.deleteDirectory(this.field5);
         }
      }
   }

   private void method18() {
      if (this.field9 != null) {
         int number1 = this.field9.method15();

         for (GameplayTrack guiimpl33 : this.field9.method11().method3()) {
            if (guiimpl33.isEnabled()) {
               Entry entry4 = guiimpl33.CCOIHCHRIHICROIOOCRRRHORHIRIOO(number1);
               if (entry4 != null) {
                  for (Entry entry6 : ((GameplaySegment)entry4.getValue()).HRCRIHHCHRHIOHHOCHIROOCORCICOI().entrySet()) {
                     PropertyGroup fishing2iterator7 = (PropertyGroup)entry6.getValue();
                     String text8 = ((String)entry6.getKey()).split("#")[0];
                     if (text8.equals("shader") && fishing2iterator7.isEnabled()) {
                        KeyframeProperty fishing2loader9 = fishing2iterator7.method12().get("shaderPack");
                        Entry entry10 = fishing2loader9.method27().floorEntry(number1 - (Integer)((Range)entry4.getKey()).getMinimum());
                        if (entry10 != null) {
                           LunarLogger.method5("Game is crashing, clearing the active gameplay layer's shader before saving", new Object[0]);
                           ((KeyframeProperty.Keyframe)entry10.getValue()).setValue(fishing2loader9.getOption().getDefaultValue());
                           return;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void method19(File file1) {
      this.method17(false);

      try (ZipOutputStream zipoutputstream2 = new ZipOutputStream(new FileOutputStream(file1))) {
         this.method20(zipoutputstream2, this.field4, "");
         JsonObject json3 = this.field1.toJsonTree(this.field15).getAsJsonObject();
         JsonObject json4 = json3.getAsJsonObject("mediaPool").getAsJsonObject("media");

         for (Entry entry6 : this.field15.method5().entrySet()) {
            File file7 = (File)entry6.getValue();
            if (file7.isFile() && !file7.toPath().toRealPath().startsWith(this.field4.toPath().toRealPath())) {
               this.method20(zipoutputstream2, file7, "media");
               json4.addProperty(((UUID)entry6.getKey()).toString(), "media/" + file7.getName());
            }
         }

         ZipEntry zipentry10 = new ZipEntry("project.json");
         zipoutputstream2.putNextEntry(zipentry10);
         zipoutputstream2.write(this.field1.toJson(json3).getBytes(StandardCharsets.UTF_8));
         zipoutputstream2.closeEntry();
      }
   }

   private void method20(ZipOutputStream zipoutputstream1, File file2, String text3) {
      if (file2.isFile()) {
         this.method21(zipoutputstream1, file2, text3);
      } else {
         File[] items4 = file2.listFiles();

         for (File file8 : Objects.requireNonNull(items4)) {
            if (file8.isDirectory()) {
               String text9 = (text3.isEmpty() ? "" : text3 + "/") + file8.getName();
               this.method20(zipoutputstream1, file8, text9);
            } else {
               this.method21(zipoutputstream1, file8, text3);
            }
         }
      }
   }

   private void method21(ZipOutputStream zipoutputstream1, File file2, String text3) {
      if (!file2.equals(new File(this.field4, "project.json"))) {
         String text4 = (text3.isEmpty() ? "" : text3 + File.separator) + file2.getName();
         ZipEntry zipentry5 = new ZipEntry(text4);
         zipoutputstream1.putNextEntry(zipentry5);

         try (FileInputStream stream6 = new FileInputStream(file2)) {
            byte[] items7 = new byte[1024];

            int number8;
            while ((number8 = stream6.read(items7)) > 0) {
               zipoutputstream1.write(items7, 0, number8);
            }
         }

         zipoutputstream1.closeEntry();
      }
   }

   private synchronized void method22(File file1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      File file2 = new File(file1, "timelines");
      file2.mkdirs();
      if (!file2.isDirectory()) {
         throw new IOException("Failed to create timelines folder");
      }

      for (ReplayTimeline highlight_34 : this.field8) {
         this.method23(file2, highlight_34, highlight_34.getId().toString());
      }
   }

   public File method23(File file1, ReplayTimeline highlight_32, String text3) {
      file1.mkdirs();
      File file4 = new File(file1, text3 + ".json");

      try (FileOutputStream stream5 = new FileOutputStream(file4)) {
         stream5.write(this.field1.toJson(highlight_32).getBytes(StandardCharsets.UTF_8));
      } catch (Exception exception10) {
         exception10.printStackTrace();
      }

      return file4;
   }

   @Nullable
   public synchronized com.moonsworth.lunar.client.replay.network.ReplayPacket method24(boolean flag1, boolean flag2) {
      return this.field7 == null ? null : this.field7.method2(flag1, flag2);
   }

   public synchronized void consume() {
      if (this.field7 != null) {
         this.field7.consume();
      }
   }

   public synchronized boolean method25() {
      return this.field7 == null ? true : this.field7.method6();
   }

   public synchronized boolean method26(boolean flag1) {
      return this.field7 == null ? true : this.field7.method7(flag1);
   }

   public synchronized void method27(String text1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      if (this.field7 != null) {
         this.field7.method8(text1);
      }
   }

   public synchronized long method28(long number1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      } else {
         return this.field7 == null ? 0L : this.field7.method9(number1);
      }
   }

   public synchronized void close(boolean flag1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      if (flag1) {
         this.method17(false);
      }

      FileUtils.deleteDirectory(this.field5);
      this.field13.method4();
      this.field15.method6().close();
      this.closed = true;
      this.field17.method1();
   }

   @Generated
   public Gson method29() {
      return this.field1;
   }

   @Generated
   public File getFile() {
      return this.file;
   }

   @Generated
   public RewindHandlers method30() {
      return this.field2;
   }

   @Generated
   public boolean method31() {
      return this.field3;
   }

   @Generated
   public File method32() {
      return this.field4;
   }

   @Generated
   public File method33() {
      return this.field5;
   }

   @Generated
   public long method34() {
      return this.field6;
   }

   @Nullable
   @Generated
   public com.moonsworth.lunar.client.replay.project.RewindFileReader method35() {
      return this.field7;
   }

   @Generated
   public boolean isClosed() {
      return this.closed;
   }

   @Generated
   public List<ReplayTimeline> method36() {
      return this.field8;
   }

   @Generated
   public ReplayTimeline method37() {
      return this.field9;
   }

   @Generated
   public TimelineElementRegistry method38() {
      return this.field10;
   }

   @Generated
   public EntityOverrideRegistry method39() {
      return this.field11;
   }

   @Generated
   public UndoRedoManager method40() {
      return this.field12;
   }

   @Generated
   public ThumbnailManager method41() {
      return this.field13;
   }

   @Generated
   public com.moonsworth.lunar.client.replay.audio.AudioWaveformRenderer method42() {
      return this.field14;
   }

   @Generated
   public RewindProject method43() {
      return this.field15;
   }

   @Generated
   public com.moonsworth.lunar.client.replay.audio.MusicTrackManager method44() {
      return this.field16;
   }

   @Generated
   public Rewind_5 method45() {
      return this.field17;
   }
}
