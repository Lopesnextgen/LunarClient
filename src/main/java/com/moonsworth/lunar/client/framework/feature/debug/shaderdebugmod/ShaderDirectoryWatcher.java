package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.function.Consumer;

public class ShaderDirectoryWatcher {
   private final Thread field1;
   private WatchService field2;
   private volatile boolean shutdown = false;

   public ShaderDirectoryWatcher(Path path1, Consumer<Path> consumer2) {
      try {
         this.field2 = FileSystems.getDefault().newWatchService();
      } catch (Exception exception4) {
         this.shutdown = true;
         this.field1 = null;
         return;
      }

      this.field1 = new Thread(() -> {
         try {
            path1.register(this.field2, StandardWatchEventKinds.ENTRY_MODIFY);

            while (!this.shutdown) {
               WatchKey watchkey3 = this.field2.take();

               for (WatchEvent watchevent5 : watchkey3.pollEvents()) {
                  Path path6 = (Path)watchevent5.context();
                  consumer2.accept(path6);
               }

               watchkey3.reset();
            }
         } catch (InterruptedException interruptedexception7) {
            Thread.currentThread().interrupt();
         } catch (Exception exception8) {
         }
      });
      this.field1.start();
   }

   public void shutdown() {
      if (!this.shutdown) {
         this.shutdown = true;
         if (this.field2 != null) {
            try {
               this.field2.close();
            } catch (Exception exception2) {
            }
         }

         if (this.field1 != null) {
            this.field1.interrupt();
         }
      }
   }
}
