package com.moonsworth.lunar.client.replay.project;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.replay.project.ReplayCompression;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind3_2;
import com.moonsworth.lunar.client.replay.project.RewindType;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_3;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.replay.project.ZipEntryLocator;
import com.moonsworth.lunar.client.replay.project.SeekableByteChannelLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.network.NoOpPacket;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.OperatingSystem;
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
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2;

public class RewindFileReader {
   private static final int field1 = 3000;
   private static final int field2 = 10;
   private final ExecutorService field3 = Executors.newFixedThreadPool(3, arg0 -> {
      Thread thread1x = new Thread(arg0, "RewindFileCache Packets Worker");
      thread1x.setDaemon(true);
      return thread1x;
   });
   private final File field4;
   private final ZipFile field5;
   private final SeekableByteChannel field6;
   private final DataInputStream field7;
   private DataInputStream field8;
   private final Rewind2 field9;
   private final boolean field10;
   private Queue<CompletableFuture<ReplayPacket>> field11 = new LinkedList<>();
   private long mark = -1L;
   private boolean closed = false;
   private boolean loaded = false;

   public RewindFileReader(File file1) {
      this.field4 = file1;
      this.field5 = new ZipFile(file1, StandardCharsets.UTF_8);
      this.field9 = (Rewind2)LunarConstants.field22.fromJson(this.method12("metadata.json"), Rewind2.class);
      this.field10 = this.field9.method2() >= 1;
      FileChannel filechannel2 = FileChannel.open(file1.toPath(), StandardOpenOption.READ);
      ZipEntryLocator.ZipEntryLocation data43 = ZipEntryLocator.method1(filechannel2, "packets.dat");
      this.field6 = new SeekableByteChannelLoader(filechannel2, data43.method1(), data43.method2());
      this.field7 = new DataInputStream(new InputStreamLoader(this.field6));
      this.method1();
   }

   private void method1() {
      File file1 = new File(RewindPaths.field6, this.field9.getId().toString());
      file1.mkdirs();
      Path path2 = file1.getCanonicalFile().toPath();
      this.field5.stream().filter(arg0 -> arg0.getName().startsWith("packs/") && !arg0.isDirectory()).forEach(arg3 -> {
         try {
            File file4 = new File(file1, arg3.getName().substring("packs/".length()));
            if (!file4.getCanonicalFile().toPath().startsWith(path2)) {
               LunarLogger.method5("Skipping invalid pack entry " + arg3.getName(), new Object[0]);
               return;
            }

            FileUtils.copyInputStreamToFile(this.field5.getInputStream(arg3), file4);
            file4.deleteOnExit();
         } catch (IOException exception5) {
            exception5.printStackTrace();
         }
      });
      if (RewindPaths.field6.isDirectory() && OperatingSystem.isWindows()) {
         Files.setAttribute(RewindPaths.field6.toPath(), "dos:hidden", true);
      }
   }

   @Nullable
   public synchronized ReplayPacket method2(boolean flag1, boolean flag2) {
      if (!this.loaded) {
         try {
            this.method9(0L);
         } catch (IOException exception7) {
            throw new RuntimeException(exception7);
         }
      }

      byte number3 = 10;
      if ((Boolean)Ref.method4().method90().method20().get()) {
         number3 = 1;
      }

      while (this.field11.size() < number3) {
         CompletableFuture completablefuture4 = this.method3(flag1);
         if (completablefuture4 == null) {
            break;
         }

         this.field11.add(completablefuture4);
      }

      if (this.field11.isEmpty()) {
         return null;
      }

      CompletableFuture completablefuture8 = this.field11.peek();
      if (flag2) {
         return (ReplayPacket)completablefuture8.join();
      }

      try {
         return (ReplayPacket)completablefuture8.get(100000L, TimeUnit.NANOSECONDS);
      } catch (Exception exception6) {
         return null;
      }
   }

   private synchronized CompletableFuture<ReplayPacket> method3(boolean flag1) {
      try {
         boolean flag2 = this.field8 == this.field7;
         if (this.field8.available() == 0) {
            if (!flag2 || !flag1) {
               if (this.field8 != this.field7) {
                  this.field8 = this.field7;
                  return this.method3(flag1);
               } else {
                  return null;
               }
            }

            if (!this.field10) {
               return null;
            }

            this.field6.position(this.mark);
         }

         if (flag2 && flag1 && this.field6.position() == 0L) {
            return null;
         }

         RewindType rewindtype3 = flag2 ? this.field9.method12() : this.field9.method13();
         this.mark = this.field6.position();
         int index4 = ByteBufLoader.method12(this.field8);
         int number5;
         if (rewindtype3.isStoresUncompressedSize()) {
            number5 = ByteBufLoader.method12(this.field8);
         } else {
            number5 = -1;
         }

         rewindtype3 = rewindtype3.getDynamicCompressor().apply(number5);
         byte[] items6 = new byte[index4];
         this.field8.readFully(items6);
         if (this.field10 && (!flag2 || !flag1)) {
            this.field8.skipBytes(2);
         }

         byte[] items7;
         boolean flag8;
         if (this.field10 && flag2 && flag1) {
            items7 = method4(items6, rewindtype3, number5);
            flag8 = false;
            this.field6.position(this.mark - 2L);
            int number9 = this.field8.readUnsignedShort() + 2;
            this.field6.position(this.field6.position() - number9);
         } else {
            items7 = items6;
            flag8 = true;
         }

         RewindType rewindtype12 = rewindtype3;
         return CompletableFuture.supplyAsync(() -> {
            ByteBufLoader bytebufloader5x;
            if (flag8) {
               bytebufloader5x = new ByteBufLoader(method5(items7, rewindtype12, number5));
            } else {
               bytebufloader5x = new ByteBufLoader(Unpooled.wrappedBuffer(items7));
            }

            int number6x = bytebufloader5x.readVarInt();

            try {
               ReplayPacket nameplate27x = com.moonsworth.lunar.client.replay.network.ReplayPacketRegistry.method2(this.field9.method3(), number6x);
               nameplate27x.method1(bytebufloader5x);
               return nameplate27x;
            } catch (Exception exception12x) {
               exception12x.printStackTrace();
               return new NoOpPacket();
            } finally {
               bytebufloader5x.release();
            }
         }, this.field3);
      } catch (Exception exception10) {
         exception10.printStackTrace();
         return CompletableFuture.completedFuture(new NoOpPacket());
      }
   }

   private static byte[] method4(byte[] items0, RewindType rewindtype1, int number2) {
      try {
         return switch (rewindtype1) {
            case ZLIB -> ReplayCompression.method2(items0);
            case ZSTD -> Rewind3_2.method3(items0, number2);
            case LZ4 -> Rewind_3.method3(items0, number2);
            default -> throw new IllegalStateException("Unexpected value: " + rewindtype1);
         };
      } catch (Exception exception4) {
         throw new RuntimeException(exception4);
      }
   }

   private static ByteBuf method5(byte[] items0, RewindType rewindtype1, int number2) {
      try {
         return switch (rewindtype1) {
            case ZSTD -> Rewind3_2.method4(items0, number2);
            case LZ4 -> Rewind_3.method4(items0, number2);
            default -> Unpooled.wrappedBuffer(method4(items0, rewindtype1, number2));
         };
      } catch (Exception exception4) {
         throw new RuntimeException(exception4);
      }
   }

   public synchronized void consume() {
      this.field11.poll();
   }

   public synchronized boolean method6() {
      return !this.field11.isEmpty();
   }

   public synchronized boolean method7(boolean flag1) {
      if (this.field8 == null) {
         return true;
      }

      boolean flag2 = this.field8 == this.field7;
      if (!flag2) {
         return false;
      }

      if (flag1) {
         try {
            return this.field6.position() == 0L;
         } catch (IOException exception4) {
            exception4.printStackTrace();
            return true;
         }
      } else {
         try {
            return this.field8.available() == 0;
         } catch (IOException exception5) {
            exception5.printStackTrace();
            return true;
         }
      }
   }

   public synchronized void method8(String text1) {
      if (this.closed) {
         throw new IllegalStateException("File is closed");
      }

      if (this.field8 != null && this.field8 != this.field7) {
         this.field8.close();
      }

      this.field8 = this.method10("snapshots/" + text1 + "/packets.dat");
      String text2 = "snapshots/" + text1 + "/mods.json";
      if (this.field5.getEntry(text2) != null) {
         JsonObject json3 = (JsonObject)LunarConstants.field22.fromJson(this.method11(text2), JsonObject.class);
         Ref.method4().method40().load(json3);
      }
   }

   public synchronized long method9(long number1) {
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
         Long index3 = this.method13().method7().floorKey(number1 - 3000L);
         if (index3 == null) {
            index3 = this.method13().method7().firstKey();
         }

         Rewind2.Data data4 = this.method13().method7().get(index3);
         this.field6.position(data4.position());
         this.mark = this.field6.position();
         if (!data4.uuid().isEmpty()) {
            this.method8(data4.uuid());
         }

         return index3 - this.method13().method7().firstKey();
      } catch (Exception exception5) {
         return 0L;
      }
   }

   public DataInputStream method10(String text1) {
      return new DataInputStream(new BufferedInputStream(this.field5.getInputStream(this.field5.getEntry(text1))));
   }

   public BufferedReader method11(String text1) {
      return new BufferedReader(new InputStreamReader(new BufferedInputStream(this.field5.getInputStream(this.field5.getEntry(text1)))));
   }

   public InputStreamReader method12(String text1) {
      return new InputStreamReader(this.field5.getInputStream(this.field5.getEntry(text1)), StandardCharsets.UTF_8);
   }

   public boolean has(String text1) {
      return this.field5.getEntry(text1) != null;
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
