package com.moonsworth.lunar.client.framework.feature.debug.shaderdebugmod;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.function.Consumer;

public class Shaderdebugmod3 {
   private final Thread field1;
   private WatchService field2;
   private volatile boolean shutdown = false;

   public Shaderdebugmod3(Path path, Consumer<Path> var2) {
      try {
         this.field2 = FileSystems.getDefault().newWatchService();
      } catch (Exception var4) {
         this.shutdown = true;
         this.field1 = null;
         return;
      }

      this.field1 = new Thread(() -> {
         try {
            path.register(this.field2, StandardWatchEventKinds.ENTRY_MODIFY);

            while (!this.shutdown) {
               WatchKey var3 = this.field2.take();

               for (WatchEvent var5 : var3.pollEvents()) {
                  Path var6 = (Path)var5.context();
                  var2.accept(var6);
               }

               var3.reset();
            }
         } catch (InterruptedException var7) {
            Thread.currentThread().interrupt();
         } catch (Exception var8) {
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
            } catch (Exception var2) {
            }
         }

         if (this.field1 != null) {
            this.field1.interrupt();
         }
      }
   }
}
