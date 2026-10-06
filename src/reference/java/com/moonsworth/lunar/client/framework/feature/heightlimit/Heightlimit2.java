package com.moonsworth.lunar.client.framework.feature.heightlimit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.mod.render.heightlimit.HeightLimit;
import com.moonsworth.lunar.client.mod.render.heightlimit.HeightLimitProfile;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

class Heightlimit2 {
   private static final int field1 = 40000;
   private static final int field2 = 8;
   private static final double field3 = 0.005;
   private static final int field4 = 1;
   private static final int field5 = 2;
   private static final int field6 = 4;
   private static final int field7 = 8;
   private static final int field8 = 16;
   private static final int field9 = 32;
   private volatile boolean dirty = true;
   private volatile Heightlimit2.Data3 field10 = Heightlimit2.Data3.field7;
   private final AtomicBoolean field11 = new AtomicBoolean();
   private final ExecutorService field12 = Executors.newSingleThreadExecutor(
      new ThreadFactoryBuilder()
         .setDaemon(true)
         .setNameFormat("Height Limit Executor %d")
         .setUncaughtExceptionHandler((var0, var1) -> Slayer.method9(var1, "Uncaught error on thread %s!", var0.getName()))
         .build()
   );
   private int field13 = Integer.MIN_VALUE;
   private int field14 = Integer.MIN_VALUE;
   private int field15 = -1;
   private int[] field16;
   private int field17;
   private int field18 = -1;

   void markDirty() {
      this.dirty = true;
   }

   void clear() {
      this.field10 = Heightlimit2.Data3.field7;
      this.dirty = true;
   }

   void method1(int var1, int var2, int var3) {
      if (this.field13 != Integer.MIN_VALUE) {
         int var4 = var1 << 4;
         int var5 = var2 << 4;
         int var6 = Math.max(this.field13 - (var4 + 15), var4 - this.field13);
         int var7 = Math.max(this.field14 - (var5 + 15), var5 - this.field14);
         if (Math.max(var6, var7) <= var3 + 8) {
            this.dirty = true;
         }
      }
   }

   void method2(AbstractRenderContext var1, HeightLimit var2) {
      if (var2.isEnabled()) {
         HeightLimit.Data var3 = var2.method15();
         if (var3 != null && var3.method1().method15().get()) {
            HeightLimitProfile var4 = var3.method1();
            int var5 = var3.limit();
            Bridge5_12 var6 = ThreadModuleDump63.method3();
            Bridge5Extension_5 var7 = var6.bridge$getPlayer();
            Itemcounter6Extension var8 = var6.bridge$getWorld();
            Bridge2_43 var9 = var6.bridge$getEntityRenderDispatcher();
            if (var7 != null && var8 != null && var9 != null) {
               int var10 = var7.bridge$getBlockX();
               int var11 = var7.bridge$getBlockZ();
               boolean var12 = this.field13 == Integer.MIN_VALUE || Math.max(Math.abs(var10 - this.field13), Math.abs(var11 - this.field14)) >= 8;
               if ((this.dirty || var12 || var5 != this.field15) && this.field11.compareAndSet(false, true)) {
                  this.dirty = false;
                  this.field13 = var10;
                  this.field14 = var11;
                  this.field15 = var5;
                  Heightlimit2.Data var13 = new Heightlimit2.Data(
                     var8, var10, var11, var5, var4.method14(), var2.method19().get() * 8, var4.method22().get(), var4.method30().getFilter()
                  );
                  this.field12.execute(() -> {
                     try {
                        this.method4(var13);
                     } catch (Throwable var6x) {
                        Inventorymod2.method5(var6x, "Height Limit Scan");
                        this.dirty = true;
                     } finally {
                        this.field11.set(false);
                     }
                  });
               }

               Heightlimit2.Data3 var39 = this.field10;
               int var14 = var39.method1().size();
               if (var14 != 0 && var39.method6() == var8) {
                  double var15 = var9.bridge$renderPosX();
                  double var17 = var9.bridge$renderPosY();
                  double var19 = var9.bridge$renderPosZ();
                  int var21 = var39.method5() - 1;
                  HeightLimit.Type2 var22 = var4.method16().get();
                  if (var22 != HeightLimit.Type2.BARRIER) {
                     int[] var23 = this.method3(var4);
                     Bridge.method5().ifPresent(var0 -> {
                        if (var0.getConfig().hasShaders()) {
                           Slayer3 var1x = var0.getShaders();
                           var1x.pushUseProgram(var1x.getProgramBasic());
                        }
                     });
                     Bridge2_32 var24 = var1.method10(LunarRenderTypes.field26);
                     var24.method1();

                     for (int var25 = 0; var25 < var14; var25++) {
                        int var26 = var39.method2().getInt(var25);
                        int var27 = var21 - var26;
                        if (var27 >= 0 && var27 < var23.length) {
                           int var28 = var23[var27];
                           if ((var28 >>> 24 & 0xFF) != 0) {
                              float var29 = ThreadModuleDump23.method5(var28);
                              float var30 = ThreadModuleDump23.greenFloat(var28);
                              float var31 = ThreadModuleDump23.method7(var28);
                              float var32 = ThreadModuleDump23.method8(var28);
                              double var33 = var39.method1().getInt(var25) - var15;
                              double var35 = var26 - var17;
                              double var37 = var39.method3().getInt(var25) - var19;
                              this.method6(var24, var33, var35, var37, var39.method4().getByte(var25), var29, var30, var31, var32);
                           }
                        }
                     }

                     var24.method17(BufferBuildMode.IMMEDIATE);
                     var1.method33(LunarRenderTypes.field26);
                     Bridge.method5().ifPresent(var0 -> {
                        if (var0.getConfig().hasShaders()) {
                           var0.getShaders().popProgram();
                        }
                     });
                  }

                  if (var22 != HeightLimit.Type2.DARKEN) {
                     int var40 = var4.method21().method13();
                     if ((var40 >>> 24 & 0xFF) == 0) {
                        return;
                     }

                     Bridge2_32 var41 = var1.method10(LunarRenderTypes.field63);
                     var41.method1();

                     for (int var42 = 0; var42 < var14; var42++) {
                        if (var39.method2().getInt(var42) == var21) {
                           double var43 = var39.method1().getInt(var42) - var15;
                           double var44 = var21 - var17;
                           double var45 = var39.method3().getInt(var42) - var19;
                           this.method7(var41, var43, var44, var45, var39.method4().getByte(var42), var40);
                        }
                     }

                     var41.method17(BufferBuildMode.IMMEDIATE);
                     var1.method33(LunarRenderTypes.field63);
                  }
               }
            }
         }
      }
   }

   private int[] method3(HeightLimitProfile var1) {
      int var2 = var1.method19().method13();
      int var3 = var1.method14();
      if (this.field16 == null || var2 != this.field17 || var3 != this.field18) {
         this.field16 = var1.method6(var3);
         this.field17 = var2;
         this.field18 = var3;
      }

      return this.field16;
   }

   private void method4(Heightlimit2.Data var1) {
      IntArrayList var2 = new IntArrayList();
      IntArrayList var3 = new IntArrayList();
      IntArrayList var4 = new IntArrayList();
      ByteArrayList var5 = new ByteArrayList();
      this.method5(var1, var2, var3, var4, var5);
      this.field10 = new Heightlimit2.Data3(var2, var3, var4, var5, var1.limit(), var1.method1());
   }

   private void method5(Heightlimit2.Data var1, IntArrayList var2, IntArrayList var3, IntArrayList var4, ByteArrayList var5) {
      Heightlimit2.Data2 var6 = new Heightlimit2.Data2(var1.method1());
      int var7 = var1.limit() - 1;
      int var8 = var7 - var1.method4();
      int var9 = var1.method5() + 8;

      for (int var10 = var1.method2() - var9; var10 <= var1.method2() + var9; var10++) {
         for (int var11 = var1.method3() - var9; var11 <= var1.method3() + var9; var11++) {
            for (int var12 = var8; var12 <= var7; var12++) {
               Bridge3_23 var13 = var6.method1(var10, var12, var11);
               if (var13 != null && !var13.bridge$isAir() && var1.method7().matches(var13)) {
                  int var14 = var6.method3(var10, var12, var11, var1.method6());
                  if (var14 != 0) {
                     var2.add(var10);
                     var3.add(var12);
                     var4.add(var11);
                     var5.add((byte)var14);
                     if (var2.size() >= 40000) {
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private void method6(Bridge2_32 var1, double var2, double var4, double var6, byte var8, float var9, float var10, float var11, float var12) {
      double var13 = var2 - 0.005;
      double var15 = var2 + 1.0 + 0.005;
      double var17 = var4 - 0.005;
      double var19 = var4 + 1.0 + 0.005;
      double var21 = var6 - 0.005;
      double var23 = var6 + 1.0 + 0.005;
      if ((var8 & 2) != 0) {
         var1.method2(var13, var19, var21).method8(var9, var10, var11, var12).method16();
         var1.method2(var13, var19, var23).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var19, var23).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var19, var21).method8(var9, var10, var11, var12).method16();
      }

      if ((var8 & 1) != 0) {
         var1.method2(var13, var17, var21).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var17, var21).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var17, var23).method8(var9, var10, var11, var12).method16();
         var1.method2(var13, var17, var23).method8(var9, var10, var11, var12).method16();
      }

      if ((var8 & 4) != 0) {
         var1.method2(var13, var17, var21).method8(var9, var10, var11, var12).method16();
         var1.method2(var13, var19, var21).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var19, var21).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var17, var21).method8(var9, var10, var11, var12).method16();
      }

      if ((var8 & 8) != 0) {
         var1.method2(var15, var17, var23).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var19, var23).method8(var9, var10, var11, var12).method16();
         var1.method2(var13, var19, var23).method8(var9, var10, var11, var12).method16();
         var1.method2(var13, var17, var23).method8(var9, var10, var11, var12).method16();
      }

      if ((var8 & 16) != 0) {
         var1.method2(var13, var17, var23).method8(var9, var10, var11, var12).method16();
         var1.method2(var13, var19, var23).method8(var9, var10, var11, var12).method16();
         var1.method2(var13, var19, var21).method8(var9, var10, var11, var12).method16();
         var1.method2(var13, var17, var21).method8(var9, var10, var11, var12).method16();
      }

      if ((var8 & 32) != 0) {
         var1.method2(var15, var17, var21).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var19, var21).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var19, var23).method8(var9, var10, var11, var12).method16();
         var1.method2(var15, var17, var23).method8(var9, var10, var11, var12).method16();
      }
   }

   private void method7(Bridge2_32 var1, double var2, double var4, double var6, byte var8, int var9) {
      double var10 = var2 - 0.005;
      double var12 = var2 + 1.0 + 0.005;
      double var14 = var4 - 0.005;
      double var16 = var4 + 1.0 + 0.005;
      double var18 = var6 - 0.005;
      double var20 = var6 + 1.0 + 0.005;
      if ((var8 & 2) != 0) {
         var1.method2(var10, var16, var18).method10(0.0F, 0.0F).method9(var9).method16();
         var1.method2(var10, var16, var20).method10(0.0F, 1.0F).method9(var9).method16();
         var1.method2(var12, var16, var20).method10(1.0F, 1.0F).method9(var9).method16();
         var1.method2(var12, var16, var18).method10(1.0F, 0.0F).method9(var9).method16();
      }

      if ((var8 & 1) != 0) {
         var1.method2(var10, var14, var18).method10(0.0F, 0.0F).method9(var9).method16();
         var1.method2(var12, var14, var18).method10(1.0F, 0.0F).method9(var9).method16();
         var1.method2(var12, var14, var20).method10(1.0F, 1.0F).method9(var9).method16();
         var1.method2(var10, var14, var20).method10(0.0F, 1.0F).method9(var9).method16();
      }

      if ((var8 & 4) != 0) {
         var1.method2(var10, var14, var18).method10(0.0F, 1.0F).method9(var9).method16();
         var1.method2(var10, var16, var18).method10(0.0F, 0.0F).method9(var9).method16();
         var1.method2(var12, var16, var18).method10(1.0F, 0.0F).method9(var9).method16();
         var1.method2(var12, var14, var18).method10(1.0F, 1.0F).method9(var9).method16();
      }

      if ((var8 & 8) != 0) {
         var1.method2(var12, var14, var20).method10(0.0F, 1.0F).method9(var9).method16();
         var1.method2(var12, var16, var20).method10(0.0F, 0.0F).method9(var9).method16();
         var1.method2(var10, var16, var20).method10(1.0F, 0.0F).method9(var9).method16();
         var1.method2(var10, var14, var20).method10(1.0F, 1.0F).method9(var9).method16();
      }

      if ((var8 & 16) != 0) {
         var1.method2(var10, var14, var20).method10(0.0F, 1.0F).method9(var9).method16();
         var1.method2(var10, var16, var20).method10(0.0F, 0.0F).method9(var9).method16();
         var1.method2(var10, var16, var18).method10(1.0F, 0.0F).method9(var9).method16();
         var1.method2(var10, var14, var18).method10(1.0F, 1.0F).method9(var9).method16();
      }

      if ((var8 & 32) != 0) {
         var1.method2(var12, var14, var18).method10(0.0F, 1.0F).method9(var9).method16();
         var1.method2(var12, var16, var18).method10(0.0F, 0.0F).method9(var9).method16();
         var1.method2(var12, var16, var20).method10(1.0F, 0.0F).method9(var9).method16();
         var1.method2(var12, var14, var20).method10(1.0F, 1.0F).method9(var9).method16();
      }
   }

   private class Data {
      private final Itemcounter6Extension field1;
      private final int field2;
      private final int field3;
      private final int field4;
      private final int field5;
      private final int field6;
      private final boolean field7;
      private final HeightLimit.Type field8;

      private Data(Itemcounter6Extension var1, int var2, int var3, int var4, int var5, int var6, boolean var7, HeightLimit.Type var8) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
         this.field7 = var7;
         this.field8 = var8;
      }

      public Itemcounter6Extension method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public int method3() {
         return this.field3;
      }

      public int limit() {
         return this.field4;
      }

      public int method4() {
         return this.field5;
      }

      public int method5() {
         return this.field6;
      }

      public boolean method6() {
         return this.field7;
      }

      public HeightLimit.Type method7() {
         return this.field8;
      }
   }

   private static final class Data2 {
      private final Itemcounter6Extension field1;
      private final Vector3iBridge.Extension field2 = Bridge.method8().method9(0, 0, 0);
      private Itemcounter2 field3;
      private int field4 = Integer.MIN_VALUE;
      private int field5 = Integer.MIN_VALUE;

      private Data2(Itemcounter6Extension var1) {
         this.field1 = var1;
      }

      private Bridge3_23 method1(int var1, int var2, int var3) {
         int var4 = var1 >> 4;
         int var5 = var3 >> 4;
         if (this.field3 == null || var4 != this.field4 || var5 != this.field5) {
            this.field3 = this.field1.bridge$getChunk(var4, var5);
            this.field4 = var4;
            this.field5 = var5;
         }

         if (this.field3 == null) {
            return null;
         }

         this.field2.bridge$setPos(var1, var2, var3);
         Bridge2_17 var6 = this.field3.bridge$getBlockState((Horsestats20Extension2)this.field2);
         return var6 == null ? null : var6.bridge$getBlock();
      }

      private boolean method2(int var1, int var2, int var3) {
         Bridge3_23 var4 = this.method1(var1, var2, var3);
         return var4 == null || var4.bridge$isAir();
      }

      private int method3(int var1, int var2, int var3, boolean var4) {
         if (var4) {
            return this.method2(var1, var2 + 1, var3) ? 2 : 0;
         }

         byte var5 = 0;
         if (this.method2(var1, var2 + 1, var3)) {
            var5 |= 2;
         }

         if (this.method2(var1, var2 - 1, var3)) {
            var5 |= 1;
         }

         if (this.method2(var1, var2, var3 - 1)) {
            var5 |= 4;
         }

         if (this.method2(var1, var2, var3 + 1)) {
            var5 |= 8;
         }

         if (this.method2(var1 - 1, var2, var3)) {
            var5 |= 16;
         }

         if (this.method2(var1 + 1, var2, var3)) {
            var5 |= 32;
         }

         return var5;
      }
   }

   private class Data3 {
      private final IntArrayList field1;
      private final IntArrayList field2;
      private final IntArrayList field3;
      private final ByteArrayList field4;
      private final int field5;
      private final Itemcounter6Extension field6;
      private static final Heightlimit2.Data3 field7 = new Heightlimit2.Data3(
         new IntArrayList(0), new IntArrayList(0), new IntArrayList(0), new ByteArrayList(0), -1, null
      );

      private Data3(IntArrayList var1, IntArrayList var2, IntArrayList var3, ByteArrayList var4, int var5, Itemcounter6Extension var6) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
      }

      public IntArrayList method1() {
         return this.field1;
      }

      public IntArrayList method2() {
         return this.field2;
      }

      public IntArrayList method3() {
         return this.field3;
      }

      public ByteArrayList method4() {
         return this.field4;
      }

      public int method5() {
         return this.field5;
      }

      public Itemcounter6Extension method6() {
         return this.field6;
      }
   }
}
