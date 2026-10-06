package com.moonsworth.lunar.client.framework.feature.minimap;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Bridge_52;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.alert.Alert5Impl;
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

public class Minimap {
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
   private final Cache<Long, com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4> field17 = CacheBuilder.newBuilder()
      .maximumSize(8192L)
      .expireAfterAccess(5L, TimeUnit.MINUTES)
      .build();
   private final Long2ObjectMap<LongSet> field18 = new Long2ObjectOpenHashMap();
   private final LongSet field19 = new LongOpenHashSet();
   private final ResourceLocationBridge field20 = ResourceLocationBridge.create("lunar", "minimap-mod-texture");
   private Bridge8Extension3 field21;
   private Bridge3_24 field22;
   private final Queue<Minimap5> field23 = new ConcurrentLinkedDeque<>();
   private final Minimap2 field24 = new Minimap2();

   public void setup() {
      this.method10((this.field1 - 1) * 16, (this.field2 - 1) * 16);
      if (ThreadModuleDump63.method8() != null) {
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
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field20);
         this.field21 = null;
      }

      this.field14 = null;
      this.field5 = false;
      this.field24.reset();
   }

   public void method1(@NotNull Itemcounter2 var1) {
      long var2 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method2(var1.bridge$getX(), var1.bridge$getZ());
      if (this.field16.contains(var2)) {
         com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4 var4 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4)this.field17
            .getIfPresent(var2);
         if (var4 != null) {
            var4.setBuilt(false);
         }

         this.field15.submit(() -> {
            Minimap5 var4x = new Minimap5(Minimap5.Type.FULL_MAP, this.field6, this.field7);
            this.method6(var1, var2, var4x);
            if (!var4x.method1().isEmpty()) {
               this.field23.add(var4x);
            }
         });
      }
   }

   public void method2(@NotNull Itemcounter2 var1) {
      if (Minimap3.method1()) {
         long var2 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method4(var1.bridge$getX(), var1.bridge$getZ());
         LongSet var4 = (LongSet)this.field18.get(var2);
         if (var4 != null) {
            var4.remove(com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method2(var1.bridge$getX(), var1.bridge$getZ()));
            if (var4.isEmpty()) {
               this.field18.remove(var2);
               this.field19.remove(var2);

               try {
                  Minimap3.method5(var1.bridge$getWorld().bridge$getDimensionId(), var2, this.field17, this);
               } catch (IOException var6) {
                  throw new RuntimeException(var6);
               }
            }
         }
      }
   }

   public void method3(MixinHelper_4 var1, BridgeExtension var2) {
      if (var2 != null) {
         double var3 = ThreadModuleDump67.method15(
            var2.method3(), var2.bridge$getPosX(), ThreadModuleDump63.method3().bridge$isGamePaused() ? 1.0F : var1.method43()
         );
         double var5 = ThreadModuleDump67.method15(
            var2.method5(), var2.bridge$getPosZ(), ThreadModuleDump63.method3().bridge$isGamePaused() ? 1.0F : var1.method43()
         );
         this.field12 = var3;
         this.field13 = var5;
      }
   }

   public void method4(MixinHelper_4 var1, BridgeExtension var2, boolean var3) {
      if (var2 != null) {
         int var4 = ThreadModuleDump67.method9(this.field12);
         int var5 = ThreadModuleDump67.method9(this.field13);
         int var6 = ThreadModuleDump67.method9(var2.bridge$getPosY());
         if (this.field14 == null || this.field14.x != var4 >> 4 || this.field14.z != var5 >> 4) {
            this.field23.clear();
            this.method8(var4, var5, false);
            this.field14 = new Vector3i(var4 >> 4, var6, var5 >> 4);
         }

         if (this.field20 != null) {
            double var7 = this.method13();
            double var9 = this.method14();
            var1.push();
            float var11 = (int)(this.field1 / 2.0F) * 16;
            float var12 = (int)(this.field2 / 2.0F) * 16;
            if (var3) {
               var1.method38(-var11 + 16.0F, -var12 + 16.0F, 0.0F);
               var1.method38((float)(-var7), (float)(-var9), 0.0F);
            }

            LcuiScreen.method94(var1, 0.0F, 0.0F, this.field3, this.field4, -16777216);
            LcuiScreen.method31(var1, this.field20, 0.0F, 0.0F, this.field3, this.field4, -1);
            var1.pop();
         }
      }
   }

   public void method5() {
      if (this.field3 > 0 && this.field4 > 0 && this.field5 && !ThreadModuleDump63.method3().bridge$isGamePaused()) {
         Minimap5 var1 = this.field23.peek();
         if (var1 != null) {
            Long2ObjectOpenHashMap var2 = new Long2ObjectOpenHashMap();

            do {
               if (var1.method2() == Minimap5.Type.FULL_MAP) {
                  this.field22.bridge$framebufferClear(true);
                  ThreadModuleDump63.method3().bridge$overrideMainRenderTarget(ThreadModuleDump63.method3().bridge$getMainRenderTarget(), false, true);
               }

               while (!var1.method1().isEmpty() && !ThreadModuleDump63.method3().bridge$isGamePaused()) {
                  Minimap5.Data var3 = var1.method1().remove();
                  if (this.field16.contains(var3.method1())) {
                     int var4 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var3.method1()) << 4) - var1.method3();
                     int var5 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var3.method1()) << 4) - var1.method4();
                     if (var4 >= 0 && var4 <= this.field3 - 16 && var5 >= 0 && var5 <= this.field4 - 16) {
                        int var6 = Bridge.method42().method85().field4;
                        if (16 > var6) {
                           throw new IllegalStateException("glTexSubImage2D: Texture dimensions exceed maximum allowed size!");
                        }

                        this.field21.bridge$upload(var4, var5, 16, 16, var3.method2());
                     }
                  }
               }

               boolean var7 = !var1.method1().isEmpty();
               if (var7) {
                  return;
               }

               var2.putAll(var1.method5());
               if (var1.method2() == Minimap5.Type.FULL_MAP) {
                  this.field10 = this.field8;
                  this.field11 = this.field9;
               }

               this.field23.poll();
               var1 = this.field23.peek();
            } while (var1 != null && var1.method2() == Minimap5.Type.FULL_MAP);

            if (!var2.isEmpty()) {
               this.field24.method1(var2);
            }
         }
      }
   }

   private Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4> method6(@Nullable Itemcounter2 var1, long var2, Minimap5 var4) {
      Object var5 = null;

      try {
         if (Minimap3.method1()) {
            long var6 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method4(
               com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var2),
               com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var2)
            );
            if (!this.field19.contains(var6) && var1 != null) {
               this.field19.add(var6);
               var5 = Minimap3.method6(var1.bridge$getWorld().bridge$getDimensionId(), var6, this);
               if (var1.bridge$isLoaded()) {
                  ((LongSet)this.field18.computeIfAbsent(var6, var0 -> new LongOpenHashSet())).add(var2);
               }
            }
         }

         if (var5 == null) {
            var5 = new Long2ObjectArrayMap();
         }

         com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4 var11 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4)this.field17
            .getIfPresent(var2);
         if (var11 != null) {
            if (var1 != null && !var1.bridge$isLoaded()) {
               this.method2(var1);
               return (Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4>)var5;
            }
         } else {
            if (var1 == null || !var1.bridge$isLoaded()) {
               return (Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4>)var5;
            }

            var11 = new com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4();
            this.field17.put(var2, var11);
            Itemcounter6Extension var7 = ThreadModuleDump63.method8();
            if (var7 != null) {
               int var8 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var2);
               int var9 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var2);
               this.method1(var7.bridge$getChunk(var8, var9 - 1));
            }
         }

         if (!var11.isBuilt()) {
            Itemcounter6Extension var12 = ThreadModuleDump63.method8();
            boolean var14 = var11.update(this, var1, var2, var12 != null && var12.bridge$getDimensionId() == -1);
            if (var14) {
               var5.put(var2, var11);
            }
         }

         var4.method1().add(new Minimap5.Data(var2, var11.getColors()));
         ObjectIterator var13 = var5.long2ObjectEntrySet().iterator();

         while (var13.hasNext()) {
            Entry var15 = (Entry)var13.next();
            var4.method5().put(var15.getLongKey(), ((com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4)var15.getValue()).getColors());
         }
      } catch (Throwable var10) {
         var10.printStackTrace();
         Slayer.method8("Minimap", "Failed to run computeColorMapAndUpdateTexture()", new Object[]{var10});
      }

      return (Long2ObjectMap<com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4>)(var5 == null ? new Long2ObjectArrayMap() : var5);
   }

   private void method7() {
      Minimap5 var1 = new Minimap5(Minimap5.Type.FULL_MAP, this.field6, this.field7);
      this.field16
         .forEach(
            var2 -> {
               Itemcounter6Extension var4 = ThreadModuleDump63.method8();
               if (var4 != null) {
                  Itemcounter2 var5 = var4.bridge$getChunk(
                     com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var2),
                     com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var2)
                  );
                  this.method6(var5, var2, var1);
               }
            }
         );
      if (!var1.method1().isEmpty()) {
         this.field23.add(var1);
      } else {
         this.field10 = this.field8;
         this.field11 = this.field9;
      }
   }

   public void method8(int var1, int var2, boolean var3) {
      int var4 = this.field8;
      int var5 = this.field9;
      if (var3 || (var4 & -16) != (var1 & -16) || (var5 & -16) != (var2 & -16)) {
         this.field8 = var1 & -16;
         this.field9 = var2 & -16;
         this.field6 = this.field8 - (this.field1 - 1) / 2 * 16;
         this.field7 = this.field9 - (this.field2 - 1) / 2 * 16;
         int var6 = this.field1 / 2;
         int var7 = this.field2 / 2;
         Itemcounter6Extension var8 = ThreadModuleDump63.method8();
         if (var8 != null) {
            LongIterator var9 = this.field16.iterator();

            while (var9.hasNext()) {
               Long var10 = (Long)var9.next();
               int var11 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method5(var10) - (this.field8 >> 4);
               int var12 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method6(var10) - (this.field9 >> 4);
               if (var11 <= -var6 || var11 >= var6 || var12 <= -var7 || var12 >= var7) {
                  this.method2(var8.bridge$getChunk(var11, var12));
               }
            }
         }

         this.field16.clear();
         int var15 = this.field8 >> 4;
         int var16 = this.field9 >> 4;

         for (int var17 = -var6 + 1; var17 < var6; var17++) {
            for (int var18 = -var7 + 1; var18 < var7; var18++) {
               int var13 = var15 + var17;
               int var14 = var16 + var18;
               this.field16.add(com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method2(var13, var14));
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

   public void method9(int var1, int var2) {
      if ((var1 != this.field1 || var2 != this.field2) && !ThreadModuleDump63.method3().bridge$isGamePaused()) {
         this.field1 = var1;
         this.field2 = var2;
         this.method10((var1 - 1) * 16, (var2 - 1) * 16);
         this.field23.clear();
         this.method8(this.field8, this.field9, true);
      }
   }

   private void method10(int var1, int var2) {
      this.field5 = false;
      this.field3 = var1;
      this.field4 = var2;
      if (this.field21 != null) {
         this.field21.method1();
      }

      this.field21 = ThreadModuleDump63.method3().bridge$getTextureManager().method3(this.field20, new Alert5Impl());
      this.field22 = Bridge_52.method2().method1(var1, var2).method4(this.field21).method8(true).method3();
      this.field22.bridge$setClearColor(0.0F, 0.0F, 0.0F, 0.0F);
      this.field5 = true;
   }

   public void method11(double var1, double var3, BiConsumer<Float, Float> var5) {
      int var6 = this.field3 / 2 + 16;
      int var7 = this.field4 / 2 + 16;
      float var8 = (float)(var1 - this.field12);
      float var9 = (float)(var3 - this.field13);
      float var10 = (float)(var1 - this.field10);
      float var11 = (float)(var3 - this.field11);
      if (!(var10 < -var6) && !(var11 < -var7) && !(var10 > var6) && !(var11 > var7)) {
         var5.accept(var8, var9);
      }
   }

   public int method12(@Nullable Itemcounter2 var1, int var2, int var3, int var4) {
      if (var1 == null) {
         return var3;
      }

      long var5 = com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2.method2(var2 >> 4, var4 >> 4);
      com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4 var7 = (com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4)this.field17
         .getIfPresent(var5);
      if (var7 != null) {
         if (!var7.isBuilt()) {
            return var3;
         }

         Itemcounter6Extension var8 = ThreadModuleDump63.method8();
         return var7.getTerrainColumn(var1, var8 != null && var8.bridge$getDimensionId() == -1, var2, var4).method1();
      } else {
         return var3;
      }
   }

   public double method13() {
      return this.field12 - this.field10;
   }

   public double method14() {
      return this.field13 - this.field11;
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
   public Cache<Long, com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap4> method22() {
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
   public Minimap2 method25() {
      return this.field24;
   }
}
