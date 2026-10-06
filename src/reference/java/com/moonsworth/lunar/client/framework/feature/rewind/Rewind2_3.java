package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui2$Data;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui2_2;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight$Data;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_4;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.LinkedHashMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.holograms.Holograms;
import com.moonsworth.lunar.client.framework.feature.rewind.holograms.Holograms2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump73Type;
import com.moonsworth.lunar.client.util.io.JsonAdapters.SerializedNameExclusionStrategy;
import com.moonsworth.lunar.client.util.io.JsonAdapters.IntegerRangeDeserializer;
import com.moonsworth.lunar.client.util.ThreadModuleDump73Type.Data;
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

public class Rewind2_3 {
   private final Gson field1 = new GsonBuilder()
      .registerTypeAdapter(Highlight.class, new Highlight.Data2(this))
      .registerTypeAdapter(Range.class, new IntegerRangeDeserializer())
      .registerTypeAdapter(Gui2_2.class, new Gui2$Data(this))
      .registerTypeAdapter(Highlight_4.class, new Highlight$Data(this))
      .registerTypeAdapter(LinkedHashMapImpl.class, new LinkedHashMapImpl.Data(this))
      .registerTypeAdapter(ThreadModuleDump73Type.class, new Data())
      .registerTypeHierarchyAdapter(Rewindhandlers2.class, new Rewindhandlers2.Data4(this))
      .setExclusionStrategies(new ExclusionStrategy[]{new SerializedNameExclusionStrategy()})
      .create();
   private final File file;
   private final RewindHandlers field2;
   private final boolean field3;
   private final File field4;
   private final File field5;
   private long field6;
   @Nullable
   private com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3 field7;
   private boolean closed = false;
   private final List<Highlight_3> field8 = new ArrayList<>();
   private Highlight_3 field9;
   private final Holograms field10;
   private final Holograms2 field11;
   private final Nameplate2 field12 = new Nameplate2();
   private final Highlight3 field13;
   private final com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.Rewindhandlers2 field14;
   private Rewind3 field15 = new Rewind3();
   private final com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight.Highlight field16 = new com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight.Highlight();
   private final Rewind_5 field17;

   public Rewind2_3(File var1, RewindHandlers var2, String var3, RewindhandlersNameplate var4, boolean var5) {
      this.file = var1;
      this.field2 = var2;
      this.field3 = var5;
      this.field17 = new Rewind_5(this, var2);
      File var6 = new File(Gui.field10, Bridge.getMinecraftVersion().method45());
      this.field4 = var1.isDirectory() ? var1 : new File(var6, var3 == null ? var1.getName() : var3);
      if (!var5) {
         this.field4.mkdirs();
         if (!this.field4.isDirectory()) {
            throw new IOException("Failed to create project folder");
         }
      }

      this.field5 = new File(this.field4, "backups");
      this.cleanup();
      this.method3();
      if (this.field15 == null) {
         this.field15 = new Rewind3();
      }

      this.field13 = new Highlight3(this.field4);
      this.field15.method6().method1(this.field15.method5(), this.field13);
      if (var1.isFile()) {
         this.field7 = this.field15.method6().method6(var1);
         this.field15.method9(this.field7.method13().method4());
         this.field15.method10(this.field7.method13().method5());
         this.field15.method14(this.field7.method13().method11());
      } else {
         this.field15.method9(Bridge.getMinecraftVersion().method45());
         this.field15.method10(ThreadModuleDump63.method3().bridge$getProtocolVersion());
         this.field15.method14(Collections.emptyMap());
      }

      this.field10 = new Holograms(var2, this.field12);
      this.field11 = new Holograms2(this.field12);
      this.field14 = new com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.Rewindhandlers2(this.field4);
      this.field6 = System.currentTimeMillis();
      this.method2();
      this.method11();
      if (this.field8.isEmpty()) {
         this.method5(var4 == null ? new RewindhandlersNameplate() : var4);
      } else {
         for (Highlight_3 var8 : this.field8) {
            if (var8.getId().equals(this.field15.method4())) {
               this.method14(var8);
               break;
            }
         }

         if (this.field9 == null) {
            this.method14(this.field8.get(0));
         }
      }
   }

   private void cleanup() {
      File var1 = new File(this.field4, "audio_cache");
      if (var1.isDirectory()) {
         for (File var5 : Objects.requireNonNull(var1.listFiles())) {
            if (var5.getName().startsWith("renders_")) {
               try {
                  FileUtils.deleteDirectory(var5);
               } catch (IOException var7) {
                  throw new RuntimeException(var7);
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
         File var1 = new File(this.field4, "project.json");
         if (var1.isFile()) {
            try (FileInputStream var2 = new FileInputStream(var1)) {
               this.field15 = (Rewind3)this.field1.fromJson(new InputStreamReader(var2, StandardCharsets.UTF_8), Rewind3.class);
            } catch (Exception var7) {
               var7.printStackTrace();
            }
         }
      }
   }

   private synchronized void method4(File var1) {
      File var2 = new File(var1, "project.json");

      try (FileOutputStream var3 = new FileOutputStream(var2)) {
         var3.write(this.field1.toJson(this.field15).getBytes(StandardCharsets.UTF_8));
      } catch (Exception var8) {
         var8.printStackTrace();
      }
   }

   public void method5(RewindhandlersNameplate var1) {
      Highlight_3 var2 = new Highlight_3(this.field12);
      var2.setId(UUID.randomUUID());
      var2.method14(var1);
      this.method13(var2);
      this.method14(var2);
      if (this.file.isFile()) {
         this.field7 = this.field15.method6().method6(this.file);
         if (this.field7 != null) {
            this.method7(this.field7, 0, null);
         }

         this.field12.clear();
      }

      this.method17(false);
   }

   public void method6(Highlight_3 var1) {
      this.field8.remove(var1);
      this.method17(true);
      File var2 = new File(this.field4, "timelines/" + var1.getId() + ".json");
      var2.delete();
      if (var1 == this.field9) {
         if (this.field8.isEmpty()) {
            this.method5(new RewindhandlersNameplate());
         }

         this.method14(this.field8.get(0));
      }
   }

   public void method7(com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3 var1, int var2, UUID var3) {
      this.field9.method2(this.field10, var1, this.field13, this.field14, this.field12, this.field4, var2, var3);
   }

   public com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3 method8(File var1) {
      return this.field15.method6().method6(var1);
   }

   public com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3 method9(UUID var1) {
      return this.field15.method6().method4(var1);
   }

   public boolean method10(UUID var1) {
      if (this.field7 != null && this.field7.method13().getId().equals(var1)) {
         return false;
      }

      this.field7 = this.method9(var1);
      return this.field7 != null;
   }

   private void method11() {
      File var1 = new File(this.field4, "timelines");
      if (var1.isDirectory()) {
         for (File var5 : Objects.requireNonNull(var1.listFiles())) {
            if (var5.getName().endsWith(".json")) {
               try {
                  Highlight_3 var6 = this.method12(var5, Highlight_3.class);
                  if (var6 != null) {
                     this.field8.add(var6);
                  }
               } catch (Exception var7) {
                  var7.printStackTrace();
               }
            }
         }
      }
   }

   public <T extends Highlight_3> T method12(File var1, Class<T> var2) {
      this.field12.setRunning(true);

      try (FileInputStream var3 = new FileInputStream(var1)) {
         return (T)this.field1.fromJson(new InputStreamReader(var3, StandardCharsets.UTF_8), var2);
      } finally {
         this.field12.setRunning(false);
      }
   }

   public synchronized void method13(Highlight_3 var1) {
      this.field8.add(var1);
      this.method17(true);
   }

   public void method14(Highlight_3 var1) {
      this.field15.method11(var1 == null ? null : var1.getId());
      this.field9 = var1;
      this.method17(true);
      this.field12.clear();
      if (this.field2.method40() != null) {
         this.field2.method27();
      }
   }

   public void method15(Highlight_3 var1) {
      this.field9 = var1;
      if (this.field2.method40() != null) {
         this.field2.method27();
      }
   }

   public void method16() {
      this.field9 = this.field8.stream().filter(var1 -> var1.getId().equals(this.field15.method4())).findFirst().orElse(this.field8.get(0));
      if (this.field2.method40() != null) {
         this.field2.method27();
      }
   }

   public synchronized void method17(boolean var1) {
      if (!this.field3) {
         if (Inventorymod2.method10() && this.field2.method47().method30() + 60000L > System.currentTimeMillis()) {
            this.method18();
         }

         File var2 = var1 ? this.field5 : this.field4;
         this.method22(var2);
         this.method4(var2);
         this.field6 = System.currentTimeMillis();
         if (!var1) {
            FileUtils.deleteDirectory(this.field5);
         }
      }
   }

   private void method18() {
      if (this.field9 != null) {
         int var1 = this.field9.method15();

         for (GuiImpl3 var3 : this.field9.method11().method3()) {
            if (var3.isEnabled()) {
               Entry var4 = var3.method1(var1);
               if (var4 != null) {
                  for (Entry var6 : ((RewindIterator23)var4.getValue()).method18().entrySet()) {
                     Fishing2Iterator var7 = (Fishing2Iterator)var6.getValue();
                     String var8 = ((String)var6.getKey()).split("#")[0];
                     if (var8.equals("shader") && var7.isEnabled()) {
                        Fishing2Loader var9 = var7.method12().get("shaderPack");
                        Entry var10 = var9.method27().floorEntry(var1 - (Integer)((Range)var4.getKey()).getMinimum());
                        if (var10 != null) {
                           Slayer.method5("Game is crashing, clearing the active gameplay layer's shader before saving", new Object[0]);
                           ((Fishing2Loader.Data)var10.getValue()).setValue(var9.getOption().getDefaultValue());
                           return;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void method19(File var1) {
      this.method17(false);

      try (ZipOutputStream var2 = new ZipOutputStream(new FileOutputStream(var1))) {
         this.method20(var2, this.field4, "");
         JsonObject var3 = this.field1.toJsonTree(this.field15).getAsJsonObject();
         JsonObject var4 = var3.getAsJsonObject("mediaPool").getAsJsonObject("media");

         for (Entry var6 : this.field15.method5().entrySet()) {
            File var7 = (File)var6.getValue();
            if (var7.isFile() && !var7.toPath().toRealPath().startsWith(this.field4.toPath().toRealPath())) {
               this.method20(var2, var7, "media");
               var4.addProperty(((UUID)var6.getKey()).toString(), "media/" + var7.getName());
            }
         }

         ZipEntry var10 = new ZipEntry("project.json");
         var2.putNextEntry(var10);
         var2.write(this.field1.toJson(var3).getBytes(StandardCharsets.UTF_8));
         var2.closeEntry();
      }
   }

   private void method20(ZipOutputStream var1, File var2, String var3) {
      if (var2.isFile()) {
         this.method21(var1, var2, var3);
      } else {
         File[] var4 = var2.listFiles();

         for (File var8 : Objects.requireNonNull(var4)) {
            if (var8.isDirectory()) {
               String var9 = (var3.isEmpty() ? "" : var3 + "/") + var8.getName();
               this.method20(var1, var8, var9);
            } else {
               this.method21(var1, var8, var3);
            }
         }
      }
   }

   private void method21(ZipOutputStream var1, File var2, String var3) {
      if (!var2.equals(new File(this.field4, "project.json"))) {
         String var4 = (var3.isEmpty() ? "" : var3 + File.separator) + var2.getName();
         ZipEntry var5 = new ZipEntry(var4);
         var1.putNextEntry(var5);

         try (FileInputStream var6 = new FileInputStream(var2)) {
            byte[] var7 = new byte[1024];

            int var8;
            while ((var8 = var6.read(var7)) > 0) {
               var1.write(var7, 0, var8);
            }
         }

         var1.closeEntry();
      }
   }

   private synchronized void method22(File var1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      File var2 = new File(var1, "timelines");
      var2.mkdirs();
      if (!var2.isDirectory()) {
         throw new IOException("Failed to create timelines folder");
      }

      for (Highlight_3 var4 : this.field8) {
         this.method23(var2, var4, var4.getId().toString());
      }
   }

   public File method23(File var1, Highlight_3 var2, String var3) {
      var1.mkdirs();
      File var4 = new File(var1, var3 + ".json");

      try (FileOutputStream var5 = new FileOutputStream(var4)) {
         var5.write(this.field1.toJson(var2).getBytes(StandardCharsets.UTF_8));
      } catch (Exception var10) {
         var10.printStackTrace();
      }

      return var4;
   }

   @Nullable
   public synchronized com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2 method24(boolean var1, boolean var2) {
      return this.field7 == null ? null : this.field7.method2(var1, var2);
   }

   public synchronized void consume() {
      if (this.field7 != null) {
         this.field7.consume();
      }
   }

   public synchronized boolean method25() {
      return this.field7 == null ? true : this.field7.method6();
   }

   public synchronized boolean method26(boolean var1) {
      return this.field7 == null ? true : this.field7.method7(var1);
   }

   public synchronized void method27(String var1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      if (this.field7 != null) {
         this.field7.method8(var1);
      }
   }

   public synchronized long method28(long var1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      } else {
         return this.field7 == null ? 0L : this.field7.method9(var1);
      }
   }

   public synchronized void close(boolean var1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      if (var1) {
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
   public com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3 method35() {
      return this.field7;
   }

   @Generated
   public boolean isClosed() {
      return this.closed;
   }

   @Generated
   public List<Highlight_3> method36() {
      return this.field8;
   }

   @Generated
   public Highlight_3 method37() {
      return this.field9;
   }

   @Generated
   public Holograms method38() {
      return this.field10;
   }

   @Generated
   public Holograms2 method39() {
      return this.field11;
   }

   @Generated
   public Nameplate2 method40() {
      return this.field12;
   }

   @Generated
   public Highlight3 method41() {
      return this.field13;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin.Rewindhandlers2 method42() {
      return this.field14;
   }

   @Generated
   public Rewind3 method43() {
      return this.field15;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight.Highlight method44() {
      return this.field16;
   }

   @Generated
   public Rewind_5 method45() {
      return this.field17;
   }
}
