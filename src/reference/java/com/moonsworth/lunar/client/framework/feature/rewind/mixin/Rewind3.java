package com.moonsworth.lunar.client.framework.feature.rewind.mixin;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_2;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind3_2;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindType;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.nameplate.SeekableByteChannelLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.highlight.Nameplate2Impl;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;
import lombok.Generated;
import org.apache.commons.io.FileUtils;

public class Rewind3 {
   private static final int field1 = 3000;
   private static final int field2 = 10;
   private final ExecutorService field3 = Executors.newFixedThreadPool(3, var0 -> {
      Thread var1x = new Thread(var0, "Rewind Packets Worker");
      var1x.setDaemon(true);
      return var1x;
   });
   private final File field4;
   private final ZipFile field5;
   private final SeekableByteChannel field6;
   private final DataInputStream field7;
   private DataInputStream field8;
   private final Rewind2 field9;
   private final boolean field10;
   private Queue<CompletableFuture<Nameplate2>> field11 = new LinkedList<>();
   private long mark = -1L;
   private boolean closed = false;
   private boolean loaded = false;

   public Rewind3(File var1) {
      this.field4 = var1;
      this.field5 = new ZipFile(var1, StandardCharsets.UTF_8);
      this.field9 = (Rewind2)ThreadModuleDump48.field22.fromJson(this.method12("metadata.json"), Rewind2.class);
      this.field10 = this.field9.method2() >= 1;
      FileChannel var2 = FileChannel.open(var1.toPath(), StandardOpenOption.READ);
      Nameplate.Data4 var3 = Nameplate.method1(var2, "packets.dat");
      this.field6 = new SeekableByteChannelLoader(var2, var3.method1(), var3.method2());
      this.field7 = new DataInputStream(new InputStreamLoader(this.field6));
      this.method1();
   }

   private void method1() {
      File var1 = new File(Gui.field6, this.field9.getId().toString());
      var1.mkdirs();
      Path var2 = var1.getCanonicalFile().toPath();
      this.field5.stream().filter(var0 -> var0.getName().startsWith("packs/") && !var0.isDirectory()).forEach(var3 -> {
         try {
            File var4 = new File(var1, var3.getName().substring("packs/".length()));
            if (!var4.getCanonicalFile().toPath().startsWith(var2)) {
               Slayer.method5("Skipping invalid pack entry " + var3.getName(), new Object[0]);
               return;
            }

            FileUtils.copyInputStreamToFile(this.field5.getInputStream(var3), var4);
            var4.deleteOnExit();
         } catch (IOException var5) {
            var5.printStackTrace();
         }
      });
      if (Gui.field6.isDirectory() && ThreadModuleDumpType2.isWindows()) {
         Files.setAttribute(Gui.field6.toPath(), "dos:hidden", true);
      }
   }

   @Nullable
   public synchronized Nameplate2 method2(boolean var1, boolean var2) {
      if (!this.loaded) {
         try {
            this.method9(0L);
         } catch (IOException var7) {
            throw new RuntimeException(var7);
         }
      }

      byte var3 = 10;
      if ((Boolean)ThreadModuleDump63.method4().method90().method20().get()) {
         var3 = 1;
      }

      while (this.field11.size() < var3) {
         CompletableFuture var4 = this.method3(var1);
         if (var4 == null) {
            break;
         }

         this.field11.add(var4);
      }

      if (this.field11.isEmpty()) {
         return null;
      }

      CompletableFuture var8 = this.field11.peek();
      if (var2) {
         return (Nameplate2)var8.join();
      }

      try {
         return (Nameplate2)var8.get(100000L, TimeUnit.NANOSECONDS);
      } catch (Exception var6) {
         return null;
      }
   }

   private synchronized CompletableFuture<Nameplate2> method3(boolean var1) {
      try {
         boolean var2 = this.field8 == this.field7;
         if (this.field8.available() == 0) {
            if (!var2 || !var1) {
               if (this.field8 != this.field7) {
                  this.field8 = this.field7;
                  return this.method3(var1);
               } else {
                  return null;
               }
            }

            if (!this.field10) {
               return null;
            }

            this.field6.position(this.mark);
         }

         if (var2 && var1 && this.field6.position() == 0L) {
            return null;
         }

         RewindType var3 = var2 ? this.field9.method12() : this.field9.method13();
         this.mark = this.field6.position();
         int var4 = ByteBufLoader.method12(this.field8);
         int var5;
         if (var3.isStoresUncompressedSize()) {
            var5 = ByteBufLoader.method12(this.field8);
         } else {
            var5 = -1;
         }

         var3 = var3.getDynamicCompressor().apply(var5);
         byte[] var6 = new byte[var4];
         this.field8.readFully(var6);
         if (this.field10 && (!var2 || !var1)) {
            this.field8.skipBytes(2);
         }

         byte[] var7;
         boolean var8;
         if (this.field10 && var2 && var1) {
            var7 = method4(var6, var3, var5);
            var8 = false;
            this.field6.position(this.mark - 2L);
            int var9 = this.field8.readUnsignedShort() + 2;
            this.field6.position(this.field6.position() - var9);
         } else {
            var7 = var6;
            var8 = true;
         }

         RewindType var12 = var3;
         return CompletableFuture.supplyAsync(() -> {
            ByteBufLoader var5x;
            if (var8) {
               var5x = new ByteBufLoader(method5(var7, var12, var5));
            } else {
               var5x = new ByteBufLoader(Unpooled.wrappedBuffer(var7));
            }

            int var6x = var5x.readVarInt();

            try {
               Nameplate2 var7x = com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate.method2(this.field9.method3(), var6x);
               var7x.method1(var5x);
               return var7x;
            } catch (Exception var12x) {
               var12x.printStackTrace();
               return new Nameplate2Impl();
            } finally {
               var5x.release();
            }
         }, this.field3);
      } catch (Exception var10) {
         var10.printStackTrace();
         return CompletableFuture.completedFuture(new Nameplate2Impl());
      }
   }

   private static byte[] method4(byte[] var0, RewindType var1, int var2) {
      try {
         return switch (var1) {
            case ZLIB -> Rewind2_2.method2(var0);
            case ZSTD -> Rewind3_2.method3(var0, var2);
            case LZ4 -> Rewind_3.method3(var0, var2);
            default -> throw new IllegalStateException("Unexpected value: " + var1);
         };
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   private static ByteBuf method5(byte[] var0, RewindType var1, int var2) {
      try {
         return switch (var1) {
            case ZSTD -> Rewind3_2.method4(var0, var2);
            case LZ4 -> Rewind_3.method4(var0, var2);
            default -> Unpooled.wrappedBuffer(method4(var0, var1, var2));
         };
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public synchronized void consume() {
      this.field11.poll();
   }

   public synchronized boolean method6() {
      return !this.field11.isEmpty();
   }

   public synchronized boolean method7(boolean var1) {
      if (this.field8 == null) {
         return true;
      }

      boolean var2 = this.field8 == this.field7;
      if (!var2) {
         return false;
      }

      if (var1) {
         try {
            return this.field6.position() == 0L;
         } catch (IOException var4) {
            var4.printStackTrace();
            return true;
         }
      } else {
         try {
            return this.field8.available() == 0;
         } catch (IOException var5) {
            var5.printStackTrace();
            return true;
         }
      }
   }

   public synchronized void method8(String var1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      if (this.field8 != null && this.field8 != this.field7) {
         this.field8.close();
      }

      this.field8 = this.method10("snapshots/" + var1 + "/packets.dat");
      String var2 = "snapshots/" + var1 + "/mods.json";
      if (this.field5.getEntry(var2) != null) {
         JsonObject var3 = (JsonObject)ThreadModuleDump48.field22.fromJson(this.method11(var2), JsonObject.class);
         ThreadModuleDump63.method4().method40().load(var3);
      }
   }

   public synchronized long method9(long var1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      this.loaded = true;
      if (this.field8 != null && this.field8 != this.field7) {
         this.field8.close();
      }

      this.field8 = this.field7;
      this.field11.clear();

      try {
         Long var3 = this.method13().method7().floorKey(var1 - 3000L);
         if (var3 == null) {
            var3 = this.method13().method7().firstKey();
         }

         Rewind2.Data var4 = this.method13().method7().get(var3);
         this.field6.position(var4.position());
         this.mark = this.field6.position();
         if (!var4.uuid().isEmpty()) {
            this.method8(var4.uuid());
         }

         return var3 - this.method13().method7().firstKey();
      } catch (Exception var5) {
         return 0L;
      }
   }

   public DataInputStream method10(String var1) {
      return new DataInputStream(new BufferedInputStream(this.field5.getInputStream(this.field5.getEntry(var1))));
   }

   public BufferedReader method11(String var1) {
      return new BufferedReader(new InputStreamReader(new BufferedInputStream(this.field5.getInputStream(this.field5.getEntry(var1)))));
   }

   public InputStreamReader method12(String var1) {
      return new InputStreamReader(this.field5.getInputStream(this.field5.getEntry(var1)), StandardCharsets.UTF_8);
   }

   public boolean has(String var1) {
      return this.field5.getEntry(var1) != null;
   }

   public synchronized void close() {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      this.field5.close();
      this.field6.close();
      this.closed = true;
   }

   @Generated
   public File getFile() {
      return this.field4;
   }

   @Generated
   public Rewind2 method13() {
      return this.field9;
   }

   @Generated
   public boolean method14() {
      return this.field10;
   }
}
