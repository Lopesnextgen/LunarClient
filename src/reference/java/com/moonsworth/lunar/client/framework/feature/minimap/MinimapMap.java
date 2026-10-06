package com.moonsworth.lunar.client.framework.feature.minimap;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Bridge_52;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.client.render.texture.PersistentTextureImpl;
import io.netty.util.concurrent.DefaultThreadFactory;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3i;

public class MinimapMap {
   private int field1 = 14;
   private int field2 = 14;
   private int field3 = 0;
   private int field4 = 0;
   private boolean field5 = false;
   protected int field6;
   protected int field7;
   private int field8;
   private int field9;
   private int field10;
   private int field11;
   private double field12;
   private double field13;
   private Vector3i field14;
   private final ExecutorService field15 = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-map-manager-thread", true));
   private final LongSet field16 = new LongOpenHashSet();
   private final Cache<Long, com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap> field17 = CacheBuilder.newBuilder()
      .maximumSize(8192L)
      .expireAfterAccess(5L, TimeUnit.MINUTES)
      .build();
   private final Long2ObjectMap<LongSet> field18 = new Long2ObjectOpenHashMap();
   private final LongSet field19 = new LongOpenHashSet();
   private final ResourceLocationBridge field20 = ResourceLocationBridge.create("lunar", "minimap-mod-texture");
   private Bridge8Extension3 field21;
   private Bridge3_24 field22;
   private final Queue<MinimapUpdateTask> field23 = new ConcurrentLinkedDeque<>();
   private final MinimapImageCache field24 = new MinimapImageCache();

   public void setup() {
      this.method10((this.field1 - 1) * 16, (this.field2 - 1) * 16);
      if (Ref.method8() != null) {
         this.method8(this.field8, this.field9, true);
      }
   }

   public void clear() {
      this.field17.invalidateAll();
      this.field18.clear();
      this.field19.clear();
      this.field16.clear();
      this.field23.clear();
      if (this.field21 != null) {
         Ref.method3().bridge$getTextureManager().bridge$deleteTexture(this.field20);
         this.field21 = null;
      }

      this.field14 = null;
      this.field5 = false;
      this.field24.reset();
   }

   public void method1(@NotNull ChunkBridge itemcounter21) {
      long number2 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method2(itemcounter21.bridge$getX(), itemcounter21.bridge$getZ());
      if (this.field16.contains(number2)) {
         com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap minimap44 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap)this.field17
            .getIfPresent(number2);
         if (minimap44 != null) {
            minimap44.setBuilt(false);
         }

         this.field15.submit(() -> {
            MinimapUpdateTask minimap54x = new MinimapUpdateTask(MinimapUpdateTask.Type.SINGLE_CHUNK, this.field6, this.field7);
            this.method6(itemcounter21, number2, minimap54x);
            if (!minimap54x.method1().isEmpty()) {
               this.field23.add(minimap54x);
            }
         });
      }
   }

   public void method2(@NotNull ChunkBridge itemcounter21) {
      if (MinimapRegionFile.method1()) {
         long index2 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method4(itemcounter21.bridge$getX(), itemcounter21.bridge$getZ());
         LongSet longset4 = (LongSet)this.field18.get(index2);
         if (longset4 != null) {
            longset4.remove(com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method2(itemcounter21.bridge$getX(), itemcounter21.bridge$getZ()));
            if (longset4.isEmpty()) {
               this.field18.remove(index2);
               this.field19.remove(index2);

               try {
                  MinimapRegionFile.method5(itemcounter21.bridge$getWorld().bridge$getDimensionId(), index2, this.field17, this);
               } catch (IOException exception6) {
                  throw new RuntimeException(exception6);
               }
            }
         }
      }
   }

   public void method3(MixinHelper_4 mixinhelper_41, BridgeExtension bridgeextension2) {
      if (bridgeextension2 != null) {
         double value3 = MathUtils.method15(
            bridgeextension2.method3(), bridgeextension2.bridge$getPosX(), Ref.method3().bridge$isGamePaused() ? 1.0F : mixinhelper_41.method43()
         );
         double value5 = MathUtils.method15(
            bridgeextension2.method5(), bridgeextension2.bridge$getPosZ(), Ref.method3().bridge$isGamePaused() ? 1.0F : mixinhelper_41.method43()
         );
         this.field12 = value3;
         this.field13 = value5;
      }
   }

   public void method4(MixinHelper_4 mixinhelper_41, BridgeExtension bridgeextension2, boolean flag3) {
      if (bridgeextension2 != null) {
         int number4 = MathUtils.method9(this.field12);
         int number5 = MathUtils.method9(this.field13);
         int number6 = MathUtils.method9(bridgeextension2.bridge$getPosY());
         if (this.field14 == null || this.field14.x != number4 >> 4 || this.field14.z != number5 >> 4) {
            this.field23.clear();
            this.method8(number4, number5, false);
            this.field14 = new Vector3i(number4 >> 4, number6, number5 >> 4);
         }

         if (this.field20 != null) {
            double value7 = this.method13();
            double value9 = this.method14();
            mixinhelper_41.push();
            float value11 = (int)(this.field1 / 2.0F) * 16;
            float value12 = (int)(this.field2 / 2.0F) * 16;
            if (flag3) {
               mixinhelper_41.method38(-value11 + 16.0F, -value12 + 16.0F, 0.0F);
               mixinhelper_41.method38((float)(-value7), (float)(-value9), 0.0F);
            }

            LcuiScreen.method94(mixinhelper_41, 0.0F, 0.0F, this.field3, this.field4, -16777216);
            LcuiScreen.method31(mixinhelper_41, this.field20, 0.0F, 0.0F, this.field3, this.field4, -1);
            mixinhelper_41.pop();
         }
      }
   }

   public void method5() {
      if (this.field3 > 0 && this.field4 > 0 && this.field5 && !Ref.method3().bridge$isGamePaused()) {
         MinimapUpdateTask minimap51 = this.field23.peek();
         if (minimap51 != null) {
            Long2ObjectOpenHashMap long2objectopenhashmap2 = new Long2ObjectOpenHashMap();

            do {
               if (minimap51.method2() == MinimapUpdateTask.Type.FULL_MAP) {
                  this.field22.bridge$framebufferClear(true);
                  Ref.method3().bridge$overrideMainRenderTarget(Ref.method3().bridge$getMainRenderTarget(), false, true);
               }

               while (!minimap51.method1().isEmpty() && !Ref.method3().bridge$isGamePaused()) {
                  MinimapUpdateTask.Data data3 = minimap51.method1().remove();
                  if (this.field16.contains(data3.method1())) {
                     int number4 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(data3.method1()) << 4) - minimap51.method3();
                     int number5 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(data3.method1()) << 4) - minimap51.method4();
                     if (number4 >= 0 && number4 <= this.field3 - 16 && number5 >= 0 && number5 <= this.field4 - 16) {
                        int number6 = Bridge.method42().method85().field4;
                        if (16 > number6) {
                           throw new IllegalStateException("glTexSubImage2D: Texture dimensions exceed maximum allowed size!");
                        }

                        this.field21.bridge$upload(number4, number5, 16, 16, data3.method2());
                     }
                  }
               }

               boolean flag7 = !minimap51.method1().isEmpty();
               if (flag7) {
                  return;
               }

               long2objectopenhashmap2.putAll(minimap51.method5());
               if (minimap51.method2() == MinimapUpdateTask.Type.FULL_MAP) {
                  this.field10 = this.field8;
                  this.field11 = this.field9;
               }

               this.field23.poll();
               minimap51 = this.field23.peek();
            } while (minimap51 != null && minimap51.method2() == MinimapUpdateTask.Type.SINGLE_CHUNK);

            if (!long2objectopenhashmap2.isEmpty()) {
               this.field24.method1(long2objectopenhashmap2);
            }
         }
      }
   }

   private Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap> method6(@Nullable ChunkBridge itemcounter21, long index2, MinimapUpdateTask minimap54) {
      Object obj5 = null;

      try {
         if (MinimapRegionFile.method1()) {
            long index6 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method4(
               com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(index2),
               com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(index2)
            );
            if (!this.field19.contains(index6) && itemcounter21 != null) {
               this.field19.add(index6);
               obj5 = MinimapRegionFile.method6(itemcounter21.bridge$getWorld().bridge$getDimensionId(), index6, this);
               if (itemcounter21.bridge$isLoaded()) {
                  ((LongSet)this.field18.computeIfAbsent(index6, arg0 -> new LongOpenHashSet())).add(index2);
               }
            }
         }

         if (obj5 == null) {
            obj5 = new Long2ObjectArrayMap();
         }

         com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap minimap411 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap)this.field17
            .getIfPresent(index2);
         if (minimap411 != null) {
            if (itemcounter21 != null && !itemcounter21.bridge$isLoaded()) {
               this.method2(itemcounter21);
               return (Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap>)obj5;
            }
         } else {
            if (itemcounter21 == null || !itemcounter21.bridge$isLoaded()) {
               return (Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap>)obj5;
            }

            minimap411 = new com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap();
            this.field17.put(index2, minimap411);
            WorldBridgeExtension itemcounter6extension7 = Ref.method8();
            if (itemcounter6extension7 != null) {
               int number8 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(index2);
               int number9 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(index2);
               this.method1(itemcounter6extension7.bridge$getChunk(number8, number9 - 1));
            }
         }

         if (!minimap411.isBuilt()) {
            WorldBridgeExtension itemcounter6extension12 = Ref.method8();
            boolean flag14 = minimap411.method1(this, itemcounter21, index2, itemcounter6extension12 != null && itemcounter6extension12.bridge$getDimensionId() == -1);
            if (flag14) {
               obj5.put(index2, minimap411);
            }
         }

         minimap54.method1().add(new MinimapUpdateTask.Data(index2, minimap411.method5()));
         ObjectIterator objectiterator13 = obj5.long2ObjectEntrySet().iterator();

         while (objectiterator13.hasNext()) {
            Entry entry15 = (Entry)objectiterator13.next();
            minimap54.method5().put(entry15.getLongKey(), ((com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap)entry15.getValue()).method5());
         }
      } catch (Throwable exception10) {
         exception10.printStackTrace();
         LunarLogger.method8("MinimapMap", "Failed to run computeColorMapAndUpdateTexture()", new Object[]{exception10});
      }

      return (Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap>)(obj5 == null ? new Long2ObjectArrayMap() : obj5);
   }

   private void method7() {
      MinimapUpdateTask minimap51 = new MinimapUpdateTask(MinimapUpdateTask.Type.FULL_MAP, this.field6, this.field7);
      this.field16
         .forEach(
            arg2 -> {
               WorldBridgeExtension itemcounter6extension4 = Ref.method8();
               if (itemcounter6extension4 != null) {
                  ChunkBridge itemcounter25 = itemcounter6extension4.bridge$getChunk(
                     com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(arg2),
                     com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(arg2)
                  );
                  this.method6(itemcounter25, arg2, minimap51);
               }
            }
         );
      if (!minimap51.method1().isEmpty()) {
         this.field23.add(minimap51);
      } else {
         this.field10 = this.field8;
         this.field11 = this.field9;
      }
   }

   public void method8(int number1, int number2, boolean flag3) {
      int number4 = this.field8;
      int number5 = this.field9;
      if (flag3 || (number4 & -16) != (number1 & -16) || (number5 & -16) != (number2 & -16)) {
         this.field8 = number1 & -16;
         this.field9 = number2 & -16;
         this.field6 = this.field8 - (this.field1 - 1) / 2 * 16;
         this.field7 = this.field9 - (this.field2 - 1) / 2 * 16;
         int number6 = this.field1 / 2;
         int number7 = this.field2 / 2;
         WorldBridgeExtension itemcounter6extension8 = Ref.method8();
         if (itemcounter6extension8 != null) {
            LongIterator longiterator9 = this.field16.iterator();

            while (longiterator9.hasNext()) {
               Long number10 = (Long)longiterator9.next();
               int number11 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method5(number10) - (this.field8 >> 4);
               int number12 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method6(number10) - (this.field9 >> 4);
               if (number11 <= -number6 || number11 >= number6 || number12 <= -number7 || number12 >= number7) {
                  this.method2(itemcounter6extension8.bridge$getChunk(number11, number12));
               }
            }
         }

         this.field16.clear();
         int number15 = this.field8 >> 4;
         int number16 = this.field9 >> 4;

         for (int index17 = -number6 + 1; index17 < number6; index17++) {
            for (int index18 = -number7 + 1; index18 < number7; index18++) {
               int number13 = number15 + index17;
               int number14 = number16 + index18;
               this.field16.add(com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method2(number13, number14));
            }
         }

         this.field15.submit(this::method7);
      }
   }

   public void reset() {
      this.field23.clear();
      this.field16.clear();
      this.field17.invalidateAll();
      this.field18.clear();
      this.field19.clear();
      this.field15.submit(this::method7);
      this.field24.reset();
   }

   public void method9(int number1, int number2) {
      if ((number1 != this.field1 || number2 != this.field2) && !Ref.method3().bridge$isGamePaused()) {
         this.field1 = number1;
         this.field2 = number2;
         this.method10((number1 - 1) * 16, (number2 - 1) * 16);
         this.field23.clear();
         this.method8(this.field8, this.field9, true);
      }
   }

   private void method10(int number1, int number2) {
      this.field5 = false;
      this.field3 = number1;
      this.field4 = number2;
      if (this.field21 != null) {
         this.field21.method1();
      }

      this.field21 = Ref.method3().bridge$getTextureManager().method3(this.field20, new PersistentTextureImpl());
      this.field22 = Bridge_52.method2().method1(number1, number2).method4(this.field21).method8(true).method3();
      this.field22.bridge$setClearColor(0.0F, 0.0F, 0.0F, 0.0F);
      this.field5 = true;
   }

   public void method11(double value1, double value3, BiConsumer<Float, Float> biconsumer5) {
      int number6 = this.field3 / 2 + 16;
      int number7 = this.field4 / 2 + 16;
      float value8 = (float)(value1 - this.field12);
      float value9 = (float)(value3 - this.field13);
      float value10 = (float)(value1 - this.field10);
      float value11 = (float)(value3 - this.field11);
      if (!(value10 < -number6) && !(value11 < -number7) && !(value10 > number6) && !(value11 > number7)) {
         biconsumer5.accept(value8, value9);
      }
   }

   public int method12(@Nullable ChunkBridge itemcounter21, int number2, int number3, int number4) {
      if (itemcounter21 == null) {
         return number3;
      }

      long number5 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.MapCoord.method2(number2 >> 4, number4 >> 4);
      com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap minimap47 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap)this.field17
         .getIfPresent(number5);
      if (minimap47 != null) {
         if (!minimap47.isBuilt()) {
            return number3;
         }

         WorldBridgeExtension itemcounter6extension8 = Ref.method8();
         return minimap47.method2(itemcounter21, itemcounter6extension8 != null && itemcounter6extension8.bridge$getDimensionId() == -1, number2, number4).method1();
      } else {
         return number3;
      }
   }

   public double method13() {
      return this.field12 - this.field10;
   }

   public double method14() {
      return this.field13 - this.field11;
   }

   @Generated
   public MinimapMap() {
   }

   @Generated
   public int method15() {
      return this.field1;
   }

   @Generated
   public int method16() {
      return this.field2;
   }

   @Generated
   public int getTextureWidth() {
      return this.field3;
   }

   @Generated
   public int getTextureHeight() {
      return this.field4;
   }

   @Generated
   public int method17() {
      return this.field8;
   }

   @Generated
   public int method18() {
      return this.field9;
   }

   @Generated
   public int method19() {
      return this.field10;
   }

   @Generated
   public int method20() {
      return this.field11;
   }

   @Generated
   public LongSet method21() {
      return this.field16;
   }

   @Generated
   public Cache<Long, com.moonsworth.lunar.client.framework.feature.minimap.mixin.ChunkColorMap> method22() {
      return this.field17;
   }

   @Generated
   public Long2ObjectMap<LongSet> method23() {
      return this.field18;
   }

   @Generated
   public LongSet method24() {
      return this.field19;
   }

   @Generated
   public MinimapImageCache method25() {
      return this.field24;
   }
}
