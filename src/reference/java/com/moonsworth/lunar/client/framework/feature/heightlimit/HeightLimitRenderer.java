package com.moonsworth.lunar.client.framework.feature.heightlimit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge.Extension;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.bridge.optifine.ShadersBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.mod.render.heightlimit.HeightLimit;
import com.moonsworth.lunar.client.mod.render.heightlimit.HeightLimitProfile;
import com.moonsworth.lunar.client.mod.render.heightlimit.HeightLimit.Type;
import com.moonsworth.lunar.client.mod.render.heightlimit.HeightLimit.Type2;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

class HeightLimitRenderer {
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
   private volatile HeightLimitRenderer.HeightScanResult field10 = HeightLimitRenderer.HeightScanResult.field7;
   private final AtomicBoolean field11 = new AtomicBoolean();
   private final ExecutorService field12 = Executors.newSingleThreadExecutor(
      new ThreadFactoryBuilder()
         .setDaemon(true)
         .setNameFormat("Height Limit Executor %d")
         .setUncaughtExceptionHandler((arg0, arg1) -> LunarLogger.method9(arg1, "Uncaught error on thread %s!", new Object[]{arg0.getName()}))
         .build()
   );
   private int field13 = Integer.MIN_VALUE;
   private int field14 = Integer.MIN_VALUE;
   private int field15 = -1;
   private int[] field16;
   private int field17;
   private int field18 = -1;

   HeightLimitRenderer() {
   }

   void markDirty() {
      this.dirty = true;
   }

   void clear() {
      this.field10 = HeightLimitRenderer.HeightScanResult.field7;
      this.dirty = true;
   }

   void method1(int number1, int number2, int number3) {
      if (this.field13 != Integer.MIN_VALUE) {
         int number4 = number1 << 4;
         int number5 = number2 << 4;
         int number6 = Math.max(this.field13 - (number4 + 15), number4 - this.field13);
         int number7 = Math.max(this.field14 - (number5 + 15), number5 - this.field14);
         if (Math.max(number6, number7) <= number3 + 8) {
            this.dirty = true;
         }
      }
   }

   void method2(AbstractRenderContext bridgeextension_91, HeightLimit heightlimit2) {
      if (heightlimit2.isEnabled()) {
         com.moonsworth.lunar.client.mod.render.heightlimit.HeightLimit.Data data3 = heightlimit2.method15();
         if (data3 != null && (Boolean)data3.method1().method15().get()) {
            HeightLimitProfile heightlimitchildmod4 = data3.method1();
            int number5 = data3.limit();
            MinecraftBridge bridge5_126 = Ref.method3();
            Bridge5Extension_5 bridge5extension_57 = bridge5_126.bridge$getPlayer();
            WorldBridgeExtension itemcounter6extension8 = bridge5_126.bridge$getWorld();
            EntityRenderDispatcherBridge bridge2_439 = bridge5_126.bridge$getEntityRenderDispatcher();
            if (bridge5extension_57 != null && itemcounter6extension8 != null && bridge2_439 != null) {
               int number10 = bridge5extension_57.bridge$getBlockX();
               int number11 = bridge5extension_57.bridge$getBlockZ();
               boolean flag12 = this.field13 == Integer.MIN_VALUE || Math.max(Math.abs(number10 - this.field13), Math.abs(number11 - this.field14)) >= 8;
               if ((this.dirty || flag12 || number5 != this.field15) && this.field11.compareAndSet(false, true)) {
                  this.dirty = false;
                  this.field13 = number10;
                  this.field14 = number11;
                  this.field15 = number5;
                  HeightLimitRenderer.Data data13 = new HeightLimitRenderer.Data(
                     itemcounter6extension8, number10, number11, number5, heightlimitchildmod4.method14(), (Integer)heightlimit2.method19().get() * 8, (Boolean)heightlimitchildmod4.method22().get(), heightlimitchildmod4.method30().getFilter()
                  );
                  this.field12.execute(() -> {
                     try {
                        this.method4(data13);
                     } catch (Throwable exception6x) {
                        CrashReporter.method5(exception6x, "Height Limit Scan");
                        this.dirty = true;
                     } finally {
                        this.field11.set(false);
                     }
                  });
               }

               HeightLimitRenderer.HeightScanResult data339 = this.field10;
               int number14 = data339.method1().size();
               if (number14 != 0 && data339.method6() == itemcounter6extension8) {
                  double value15 = bridge2_439.bridge$renderPosX();
                  double value17 = bridge2_439.bridge$renderPosY();
                  double value19 = bridge2_439.bridge$renderPosZ();
                  int number21 = data339.method5() - 1;
                  Type2 type222 = (Type2)heightlimitchildmod4.method16().get();
                  if (type222 != Type2.BARRIER) {
                     int[] items23 = this.method3(heightlimitchildmod4);
                     Bridge.method5().ifPresent(arg0 -> {
                        if (arg0.getConfig().hasShaders()) {
                           ShadersBridge slayer31x = arg0.getShaders();
                           slayer31x.pushUseProgram(slayer31x.getProgramBasic());
                        }
                     });
                     DrawBufferBridge bridge2_3224 = bridgeextension_91.method10(LunarRenderTypes.field26);
                     bridge2_3224.method1();

                     for (int index25 = 0; index25 < number14; index25++) {
                        int number26 = data339.method2().getInt(index25);
                        int index27 = number21 - number26;
                        if (index27 >= 0 && index27 < items23.length) {
                           int number28 = items23[index27];
                           if ((number28 >>> 24 & 0xFF) != 0) {
                              float value29 = ColorUtils.method5(number28);
                              float value30 = ColorUtils.method6(number28);
                              float value31 = ColorUtils.method7(number28);
                              float value32 = ColorUtils.method8(number28);
                              double value33 = data339.method1().getInt(index25) - value15;
                              double value35 = number26 - value17;
                              double value37 = data339.method3().getInt(index25) - value19;
                              this.method6(bridge2_3224, value33, value35, value37, data339.method4().getByte(index25), value29, value30, value31, value32);
                           }
                        }
                     }

                     bridge2_3224.method17(BufferMode.IMMEDIATE);
                     bridgeextension_91.method33(LunarRenderTypes.field26);
                     Bridge.method5().ifPresent(arg0 -> {
                        if (arg0.getConfig().hasShaders()) {
                           arg0.getShaders().popProgram();
                        }
                     });
                  }

                  if (type222 != Type2.DARKEN) {
                     int number40 = heightlimitchildmod4.method21().method13();
                     if ((number40 >>> 24 & 0xFF) == 0) {
                        return;
                     }

                     DrawBufferBridge bridge2_3241 = bridgeextension_91.method10(LunarRenderTypes.field63);
                     bridge2_3241.method1();

                     for (int index42 = 0; index42 < number14; index42++) {
                        if (data339.method2().getInt(index42) == number21) {
                           double value43 = data339.method1().getInt(index42) - value15;
                           double value44 = number21 - value17;
                           double value45 = data339.method3().getInt(index42) - value19;
                           this.method7(bridge2_3241, value43, value44, value45, data339.method4().getByte(index42), number40);
                        }
                     }

                     bridge2_3241.method17(BufferMode.IMMEDIATE);
                     bridgeextension_91.method33(LunarRenderTypes.field63);
                  }
               }
            }
         }
      }
   }

   private int[] method3(HeightLimitProfile heightlimitchildmod1) {
      int number2 = heightlimitchildmod1.method19().method13();
      int number3 = heightlimitchildmod1.method14();
      if (this.field16 == null || number2 != this.field17 || number3 != this.field18) {
         this.field16 = heightlimitchildmod1.method6(number3);
         this.field17 = number2;
         this.field18 = number3;
      }

      return this.field16;
   }

   private void method4(HeightLimitRenderer.Data data1) {
      IntArrayList intarraylist2 = new IntArrayList();
      IntArrayList intarraylist3 = new IntArrayList();
      IntArrayList intarraylist4 = new IntArrayList();
      ByteArrayList bytearraylist5 = new ByteArrayList();
      this.method5(data1, intarraylist2, intarraylist3, intarraylist4, bytearraylist5);
      this.field10 = new HeightLimitRenderer.HeightScanResult(intarraylist2, intarraylist3, intarraylist4, bytearraylist5, data1.limit(), data1.method1());
   }

   private void method5(HeightLimitRenderer.Data data1, IntArrayList intarraylist2, IntArrayList intarraylist3, IntArrayList intarraylist4, ByteArrayList bytearraylist5) {
      HeightLimitRenderer.HeightScanChunk data26 = new HeightLimitRenderer.HeightScanChunk(data1.method1());
      int number7 = data1.limit() - 1;
      int number8 = number7 - data1.method4();
      int number9 = data1.method5() + 8;

      for (int index10 = data1.method2() - number9; index10 <= data1.method2() + number9; index10++) {
         for (int index11 = data1.method3() - number9; index11 <= data1.method3() + number9; index11++) {
            for (int index12 = number8; index12 <= number7; index12++) {
               Bridge3_23 bridge3_2313 = data26.method1(index10, index12, index11);
               if (bridge3_2313 != null && !bridge3_2313.bridge$isAir() && data1.method7().matches(bridge3_2313)) {
                  int index14 = data26.method3(index10, index12, index11, data1.method6());
                  if (index14 != 0) {
                     intarraylist2.add(index10);
                     intarraylist3.add(index12);
                     intarraylist4.add(index11);
                     bytearraylist5.add((byte)index14);
                     if (intarraylist2.size() >= 40000) {
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private void method6(DrawBufferBridge bridge2_321, double value2, double value4, double value6, byte number8, float value9, float value10, float value11, float value12) {
      double value13 = value2 - 0.005;
      double value15 = value2 + 1.0 + 0.005;
      double value17 = value4 - 0.005;
      double value19 = value4 + 1.0 + 0.005;
      double value21 = value6 - 0.005;
      double value23 = value6 + 1.0 + 0.005;
      if ((number8 & 2) != 0) {
         bridge2_321.method2(value13, value19, value21).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value13, value19, value23).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value19, value23).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value19, value21).method8(value9, value10, value11, value12).method16();
      }

      if ((number8 & 1) != 0) {
         bridge2_321.method2(value13, value17, value21).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value17, value21).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value17, value23).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value13, value17, value23).method8(value9, value10, value11, value12).method16();
      }

      if ((number8 & 4) != 0) {
         bridge2_321.method2(value13, value17, value21).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value13, value19, value21).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value19, value21).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value17, value21).method8(value9, value10, value11, value12).method16();
      }

      if ((number8 & 8) != 0) {
         bridge2_321.method2(value15, value17, value23).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value19, value23).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value13, value19, value23).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value13, value17, value23).method8(value9, value10, value11, value12).method16();
      }

      if ((number8 & 16) != 0) {
         bridge2_321.method2(value13, value17, value23).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value13, value19, value23).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value13, value19, value21).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value13, value17, value21).method8(value9, value10, value11, value12).method16();
      }

      if ((number8 & 32) != 0) {
         bridge2_321.method2(value15, value17, value21).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value19, value21).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value19, value23).method8(value9, value10, value11, value12).method16();
         bridge2_321.method2(value15, value17, value23).method8(value9, value10, value11, value12).method16();
      }
   }

   private void method7(DrawBufferBridge bridge2_321, double value2, double value4, double value6, byte number8, int number9) {
      double value10 = value2 - 0.005;
      double value12 = value2 + 1.0 + 0.005;
      double value14 = value4 - 0.005;
      double value16 = value4 + 1.0 + 0.005;
      double value18 = value6 - 0.005;
      double value20 = value6 + 1.0 + 0.005;
      if ((number8 & 2) != 0) {
         bridge2_321.method2(value10, value16, value18).method10(0.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value10, value16, value20).method10(0.0F, 1.0F).method9(number9).method16();
         bridge2_321.method2(value12, value16, value20).method10(1.0F, 1.0F).method9(number9).method16();
         bridge2_321.method2(value12, value16, value18).method10(1.0F, 0.0F).method9(number9).method16();
      }

      if ((number8 & 1) != 0) {
         bridge2_321.method2(value10, value14, value18).method10(0.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value12, value14, value18).method10(1.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value12, value14, value20).method10(1.0F, 1.0F).method9(number9).method16();
         bridge2_321.method2(value10, value14, value20).method10(0.0F, 1.0F).method9(number9).method16();
      }

      if ((number8 & 4) != 0) {
         bridge2_321.method2(value10, value14, value18).method10(0.0F, 1.0F).method9(number9).method16();
         bridge2_321.method2(value10, value16, value18).method10(0.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value12, value16, value18).method10(1.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value12, value14, value18).method10(1.0F, 1.0F).method9(number9).method16();
      }

      if ((number8 & 8) != 0) {
         bridge2_321.method2(value12, value14, value20).method10(0.0F, 1.0F).method9(number9).method16();
         bridge2_321.method2(value12, value16, value20).method10(0.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value10, value16, value20).method10(1.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value10, value14, value20).method10(1.0F, 1.0F).method9(number9).method16();
      }

      if ((number8 & 16) != 0) {
         bridge2_321.method2(value10, value14, value20).method10(0.0F, 1.0F).method9(number9).method16();
         bridge2_321.method2(value10, value16, value20).method10(0.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value10, value16, value18).method10(1.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value10, value14, value18).method10(1.0F, 1.0F).method9(number9).method16();
      }

      if ((number8 & 32) != 0) {
         bridge2_321.method2(value12, value14, value18).method10(0.0F, 1.0F).method9(number9).method16();
         bridge2_321.method2(value12, value16, value18).method10(0.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value12, value16, value20).method10(1.0F, 0.0F).method9(number9).method16();
         bridge2_321.method2(value12, value14, value20).method10(1.0F, 1.0F).method9(number9).method16();
      }
   }

   private class Data {
      private final WorldBridgeExtension field1;
      private final int field2;
      private final int field3;
      private final int field4;
      private final int field5;
      private final int field6;
      private final boolean field7;
      private final Type field8;

      private Data(WorldBridgeExtension itemcounter6extension1, int number2, int number3, int number4, int number5, int number6, boolean flag7, Type type8) {
         this.field1 = itemcounter6extension1;
         this.field2 = number2;
         this.field3 = number3;
         this.field4 = number4;
         this.field5 = number5;
         this.field6 = number6;
         this.field7 = flag7;
         this.field8 = type8;
      }

      public WorldBridgeExtension method1() {
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

      public Type method7() {
         return this.field8;
      }
   }

   private static final class HeightScanChunk {
      private final WorldBridgeExtension field1;
      private final Extension field2 = Bridge.method8().method9(0, 0, 0);
      private ChunkBridge field3;
      private int field4 = Integer.MIN_VALUE;
      private int field5 = Integer.MIN_VALUE;

      private HeightScanChunk(WorldBridgeExtension itemcounter6extension1) {
         this.field1 = itemcounter6extension1;
      }

      private Bridge3_23 method1(int number1, int number2, int number3) {
         int number4 = number1 >> 4;
         int number5 = number3 >> 4;
         if (this.field3 == null || number4 != this.field4 || number5 != this.field5) {
            this.field3 = this.field1.bridge$getChunk(number4, number5);
            this.field4 = number4;
            this.field5 = number5;
         }

         if (this.field3 == null) {
            return null;
         }

         this.field2.bridge$setPos(number1, number2, number3);
         BlockStateBridge bridge2_176 = this.field3.bridge$getBlockState((Horsestats20Extension2)this.field2);
         return bridge2_176 == null ? null : bridge2_176.bridge$getBlock();
      }

      private boolean method2(int number1, int number2, int number3) {
         Bridge3_23 bridge3_234 = this.method1(number1, number2, number3);
         return bridge3_234 == null || bridge3_234.bridge$isAir();
      }

      private int method3(int number1, int number2, int number3, boolean flag4) {
         if (flag4) {
            return this.method2(number1, number2 + 1, number3) ? 2 : 0;
         }

         byte number5 = 0;
         if (this.method2(number1, number2 + 1, number3)) {
            number5 |= 2;
         }

         if (this.method2(number1, number2 - 1, number3)) {
            number5 |= 1;
         }

         if (this.method2(number1, number2, number3 - 1)) {
            number5 |= 4;
         }

         if (this.method2(number1, number2, number3 + 1)) {
            number5 |= 8;
         }

         if (this.method2(number1 - 1, number2, number3)) {
            number5 |= 16;
         }

         if (this.method2(number1 + 1, number2, number3)) {
            number5 |= 32;
         }

         return number5;
      }
   }

   private class HeightScanResult {
      private final IntArrayList field1;
      private final IntArrayList field2;
      private final IntArrayList field3;
      private final ByteArrayList field4;
      private final int field5;
      private final WorldBridgeExtension field6;
      private static final HeightLimitRenderer.HeightScanResult field7 = new HeightLimitRenderer.HeightScanResult(
         new IntArrayList(0), new IntArrayList(0), new IntArrayList(0), new ByteArrayList(0), -1, null
      );

      private HeightScanResult(IntArrayList intarraylist1, IntArrayList intarraylist2, IntArrayList intarraylist3, ByteArrayList bytearraylist4, int number5, WorldBridgeExtension itemcounter6extension6) {
         this.field1 = intarraylist1;
         this.field2 = intarraylist2;
         this.field3 = intarraylist3;
         this.field4 = bytearraylist4;
         this.field5 = number5;
         this.field6 = itemcounter6extension6;
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

      public WorldBridgeExtension method6() {
         return this.field6;
      }
   }
}
