package com.moonsworth.lunar.client.util.io;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import java.io.Closeable;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class DirectoryWatcher implements Closeable {
   private static final Kind<?>[] field1 = new Kind[]{
      StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW
   };
   private final Path field2;
   private final Consumer<List<WatchEvent<?>>> field3;
   @Nullable
   private WatchService field4;
   @Nullable
   private ScheduledFuture<?> field5;

   public DirectoryWatcher(Path path1, Consumer<List<WatchEvent<?>>> consumer2) {
      this(path1, consumer2, field1);
   }

   public DirectoryWatcher(Path path1, Consumer<List<WatchEvent<?>>> consumer2, Kind<?>... items3) {
      this.field2 = path1;
      this.field3 = consumer2;

      try {
         this.field4 = FileSystems.getDefault().newWatchService();
         WatchKey watchkey4 = path1.register(this.field4, items3);
         this.field5 = BackgroundExecutor.method5().scheduleAtFixedRate(() -> this.method1(watchkey4), 0L, 1L, TimeUnit.SECONDS);
      } catch (Exception exception5) {
         LunarLogger.method5("Could not create watch service!", new Object[]{exception5.getMessage()});
      }
   }

   private void method1(WatchKey watchkey1) {
      List list2 = watchkey1.pollEvents();
      if (!list2.isEmpty()) {
         this.field3.accept(list2);
      }
   }

   @Override
   public void close() {
      if (this.field5 != null) {
         this.field5.cancel(false);
      }

      if (this.field4 != null) {
         try {
            this.field4.close();
         } catch (Exception exception2) {
            LunarLogger.method5("Could not close watch service!", new Object[]{exception2.getMessage()});
         }
      }
   }

   @Generated
   public Path method2() {
      return this.field2;
   }

   @Generated
   public Consumer<List<WatchEvent<?>>> method3() {
      return this.field3;
   }

   @Nullable
   @Generated
   public WatchService method4() {
      return this.field4;
   }

   @Nullable
   @Generated
   public ScheduledFuture<?> method5() {
      return this.field5;
   }
}
