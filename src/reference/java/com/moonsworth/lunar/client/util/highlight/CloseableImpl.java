package com.moonsworth.lunar.client.util.highlight;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
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

public class CloseableImpl implements Closeable {
   private static final Kind<?>[] field1 = new Kind[]{
      StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW
   };
   private final Path field2;
   private final Consumer<List<WatchEvent<?>>> field3;
   @Nullable
   private WatchService field4;
   @Nullable
   private ScheduledFuture<?> field5;

   public CloseableImpl(Path var1, Consumer<List<WatchEvent<?>>> var2) {
      this(var1, var2, field1);
   }

   public CloseableImpl(Path var1, Consumer<List<WatchEvent<?>>> var2, Kind<?>... items) {
      this.field2 = var1;
      this.field3 = var2;

      try {
         this.field4 = FileSystems.getDefault().newWatchService();
         WatchKey var4 = var1.register(this.field4, items);
         this.field5 = ThreadModuleDump37.method5().scheduleAtFixedRate(() -> this.method1(var4), 0L, 1L, TimeUnit.SECONDS);
      } catch (Exception var5) {
         Slayer.method5("Could not create watch service!", new Object[]{var5.getMessage()});
      }
   }

   private void method1(WatchKey var1) {
      List var2 = var1.pollEvents();
      if (!var2.isEmpty()) {
         this.field3.accept(var2);
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
         } catch (Exception var2) {
            Slayer.method5("Could not close watch service!", new Object[]{var2.getMessage()});
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
