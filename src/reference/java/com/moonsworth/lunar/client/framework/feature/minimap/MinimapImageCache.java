package com.moonsworth.lunar.client.framework.feature.minimap;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventGameDirectory;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
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

public class MinimapImageCache {
   private static final Path field1 = new File(Ref.method3().bridge$getMcDataDir(), "lunar").toPath();
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

   MinimapImageCache() {
      LunarEventBus.method29().method2(EventGameDirectory.class, arg1 -> this.field8 = arg1.method2().toPath());
   }

   void reset() {
      this.field7.invalidateAll();
      this.field6.set(false);
      this.field5.set(new Long2ObjectArrayMap());
   }

   public void method1(Long2ObjectMap<int[]> long2objectmap1) {
      com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimap2 = Ref.method4().method40().method94();
      if ((Boolean)minimap2.getSaveAsPictureFile().get()) {
         Path path3 = this.method6();
         if (path3 != null) {
            this.field5.getAndUpdate(arg1x -> {
               Long2ObjectArrayMap long2objectarraymap2x = new Long2ObjectArrayMap(arg1x);
               long2objectarraymap2x.putAll(long2objectmap1);
               return long2objectarraymap2x;
            });
            if (this.field6.compareAndSet(false, true)) {
               this.field4.schedule(() -> this.method2(path3), 1L, TimeUnit.SECONDS);
            }
         }
      }
   }

   private void method2(Path path1) {
      if (this.field6.get()) {
         LongOpenHashSet longopenhashset2 = new LongOpenHashSet();
         Long2ObjectMap long2objectmap3 = this.field5.getAndSet(new Long2ObjectArrayMap());
         ObjectIterator objectiterator4 = long2objectmap3.long2ObjectEntrySet().iterator();

         while (objectiterator4.hasNext()) {
            Entry entry5 = (Entry)objectiterator4.next();

            try {
               long number6 = entry5.getLongKey();
               long index8 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method4(
                  com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(number6),
                  com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(number6)
               );
               BufferedImage bufferedimage10 = this.method4(path1, index8);
               this.method3(bufferedimage10, index8, number6, (int[])entry5.getValue());
               longopenhashset2.add(index8);
            } catch (Exception exception11) {
            }
         }

         if (this.field6.get()) {
            LongIterator longiterator12 = longopenhashset2.iterator();

            while (longiterator12.hasNext()) {
               long number13 = (Long)longiterator12.next();
               this.method5(path1, number13);
            }

            this.field6.set(false);
            if (!this.field5.get().isEmpty() && this.field6.compareAndSet(false, true)) {
               this.field4.schedule(() -> this.method2(path1), 1L, TimeUnit.SECONDS);
            }
         }
      }
   }

   private void method3(BufferedImage bufferedimage1, long number2, long number4, int[] items6) {
      int number7 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(number4)
         - com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(number2) * 32;
      int number8 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(number4)
         - com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(number2) * 32;
      int[] items9 = new int[items6.length];
      boolean flag10 = true;

      for (int index11 = 0; index11 < items6.length; index11++) {
         int number12 = items6[index11];
         if (flag10 && number12 != 0) {
            flag10 = false;
         }

         if (!flag10) {
            items9[index11] = ColorUtils.method17(number12);
         }
      }

      if (!flag10) {
         bufferedimage1.setRGB(number7 * 16, number8 * 16, 16, 16, items9, 0, 16);
      }
   }

   private BufferedImage method4(Path path1, long number2) {
      BufferedImage bufferedimage4 = (BufferedImage)this.field7.getIfPresent(number2);
      if (bufferedimage4 != null) {
         return bufferedimage4;
      }

      File file5 = this.method8(path1, number2);
      BufferedImage bufferedimage6 = null;
      if (file5.exists()) {
         try {
            bufferedimage6 = ImageIO.read(file5);
         } catch (IOException exception8) {
            LunarLogger.method8("MinimapMap", "Failed to read existing minimap region image " + file5, new Object[]{exception8});
         }
      }

      if (bufferedimage6 == null) {
         bufferedimage6 = new BufferedImage(512, 512, 2);
      }

      this.field7.put(number2, bufferedimage6);
      return bufferedimage6;
   }

   private void method5(Path path1, long number2) {
      File file4 = this.method8(path1, number2);
      BufferedImage bufferedimage5 = (BufferedImage)this.field7.getIfPresent(number2);
      if (bufferedimage5 != null) {
         try {
            file4.getParentFile().mkdirs();
            ImageIO.write(bufferedimage5, "png", file4);
         } catch (IOException exception7) {
            LunarLogger.method8("MinimapMap", "Failed to save minimap region image to " + file4, new Object[]{exception7});
         }
      }
   }

   @Nullable
   Path method6() {
      MinecraftBridge bridge5_121 = Ref.method3();
      if (bridge5_121.bridge$getWorld() == null) {
         return null;
      }

      Path path2;
      if (Ref.method4().method40().method94().method19()) {
         if (this.field8 == null) {
            return null;
         }

         path2 = this.field8.resolve("minimap").resolve("images");
      } else {
         path2 = field2.resolve(method7(WaypointStore.method19())).resolve("images");
      }

      String text3 = bridge5_121.bridge$getWorld().bridge$getDimensionKey();
      if (text3 == null) {
         text3 = "dim" + bridge5_121.bridge$getWorld().bridge$getDimensionId();
      }

      Path path4 = path2.resolve(method7(text3));
      return !path4.normalize().startsWith(path2.normalize()) ? null : path4;
   }

   private static String method7(String text0) {
      String text1 = text0.replaceAll("[^A-Za-z0-9._-]", "_");
      return text1.isEmpty() ? "_" : text1;
   }

   private File method8(Path path1, long number2) {
      String text4 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(number2)
         + "_"
         + com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(number2)
         + ".png";
      return path1.resolve(text4).toFile();
   }
}
