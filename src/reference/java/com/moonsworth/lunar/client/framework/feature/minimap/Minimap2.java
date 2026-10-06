package com.moonsworth.lunar.client.framework.feature.minimap;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRunDirectory;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.netty.util.concurrent.DefaultThreadFactory;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.imageio.ImageIO;
import org.jetbrains.annotations.Nullable;

public class Minimap2 {
   private static final Path field1 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "lunar").toPath();
   private static final Path field2 = field1.resolve("minimap");
   private static final int field3 = 512;
   private final ScheduledExecutorService field4 = Executors.newSingleThreadScheduledExecutor(
      new DefaultThreadFactory("lunar-map-manager-texture-save-thread", true)
   );
   private final AtomicReference<Long2ObjectMap<int[]>> field5 = new AtomicReference<>(new Long2ObjectArrayMap());
   private final AtomicBoolean field6 = new AtomicBoolean(false);
   private final Cache<Long, BufferedImage> field7 = CacheBuilder.newBuilder().maximumSize(64L).build();
   @Nullable
   private Path field8;

   Minimap2() {
      ClientEventBus.method29().method2(EventRunDirectory.class, var1 -> this.field8 = var1.method2().toPath());
   }

   void reset() {
      this.field7.invalidateAll();
      this.field6.set(false);
      this.field5.set(new Long2ObjectArrayMap());
   }

   public void method1(Long2ObjectMap<int[]> var1) {
      com.moonsworth.lunar.client.mod.render.minimap.Minimap var2 = ThreadModuleDump63.method4().method40().method94();
      if ((Boolean)var2.getSaveAsPictureFile().get()) {
         Path var3 = this.method6();
         if (var3 != null) {
            this.field5.getAndUpdate(var1x -> {
               Long2ObjectArrayMap var2x = new Long2ObjectArrayMap(var1x);
               var2x.putAll(var1);
               return var2x;
            });
            if (this.field6.compareAndSet(false, true)) {
               this.field4.schedule(() -> this.method2(var3), 1L, TimeUnit.SECONDS);
            }
         }
      }
   }

   private void method2(Path var1) {
      if (this.field6.get()) {
         LongOpenHashSet var2 = new LongOpenHashSet();
         Long2ObjectMap var3 = this.field5.getAndSet(new Long2ObjectArrayMap());
         ObjectIterator var4 = var3.long2ObjectEntrySet().iterator();

         while (var4.hasNext()) {
            Entry var5 = (Entry)var4.next();

            try {
               long var6 = var5.getLongKey();
               long var8 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method4(
                  com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var6),
                  com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var6)
               );
               BufferedImage var10 = this.method4(var1, var8);
               this.method3(var10, var8, var6, (int[])var5.getValue());
               var2.add(var8);
            } catch (Exception var11) {
            }
         }

         if (this.field6.get()) {
            LongIterator var12 = var2.iterator();

            while (var12.hasNext()) {
               long var13 = (Long)var12.next();
               this.method5(var1, var13);
            }

            this.field6.set(false);
            if (!this.field5.get().isEmpty() && this.field6.compareAndSet(false, true)) {
               this.field4.schedule(() -> this.method2(var1), 1L, TimeUnit.SECONDS);
            }
         }
      }
   }

   private void method3(BufferedImage var1, long var2, long var4, int[] var6) {
      int var7 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var4)
         - com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var2) * 32;
      int var8 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var4)
         - com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var2) * 32;
      int[] var9 = new int[var6.length];
      boolean var10 = true;

      for (int var11 = 0; var11 < var6.length; var11++) {
         int var12 = var6[var11];
         if (var10 && var12 != 0) {
            var10 = false;
         }

         if (!var10) {
            var9[var11] = ThreadModuleDump23.method17(var12);
         }
      }

      if (!var10) {
         var1.setRGB(var7 * 16, var8 * 16, 16, 16, var9, 0, 16);
      }
   }

   private BufferedImage method4(Path var1, long var2) {
      BufferedImage var4 = (BufferedImage)this.field7.getIfPresent(var2);
      if (var4 != null) {
         return var4;
      }

      File var5 = this.method8(var1, var2);
      BufferedImage var6 = null;
      if (var5.exists()) {
         try {
            var6 = ImageIO.read(var5);
         } catch (IOException var8) {
            Slayer.method8("Minimap", "Failed to read existing minimap region image " + var5, new Object[]{var8});
         }
      }

      if (var6 == null) {
         var6 = new BufferedImage(512, 512, 2);
      }

      this.field7.put(var2, var6);
      return var6;
   }

   private void method5(Path var1, long var2) {
      File var4 = this.method8(var1, var2);
      BufferedImage var5 = (BufferedImage)this.field7.getIfPresent(var2);
      if (var5 != null) {
         try {
            var4.getParentFile().mkdirs();
            ImageIO.write(var5, "png", var4);
         } catch (IOException var7) {
            Slayer.method8("Minimap", "Failed to save minimap region image to " + var4, new Object[]{var7});
         }
      }
   }

   @Nullable
   Path method6() {
      Bridge5_12 var1 = ThreadModuleDump63.method3();
      if (var1.bridge$getWorld() == null) {
         return null;
      }

      Path var2;
      if (ThreadModuleDump63.method4().method40().method94().method19()) {
         if (this.field8 == null) {
            return null;
         }

         var2 = this.field8.resolve("minimap").resolve("images");
      } else {
         var2 = field2.resolve(method7(WaypointStore.method19())).resolve("images");
      }

      String var3 = var1.bridge$getWorld().bridge$getDimensionKey();
      if (var3 == null) {
         var3 = "dim" + var1.bridge$getWorld().bridge$getDimensionId();
      }

      Path var4 = var2.resolve(method7(var3));
      return !var4.normalize().startsWith(var2.normalize()) ? null : var4;
   }

   private static String method7(String text) {
      String var1 = text.replaceAll("[^A-Za-z0-9._-]", "_");
      return var1.isEmpty() ? "_" : var1;
   }

   private File method8(Path var1, long var2) {
      String var4 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var2)
         + "_"
         + com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var2)
         + ".png";
      return var1.resolve(var4).toFile();
   }
}
