package com.moonsworth.lunar.client.framework.feature.lightoverlay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5_5;
import com.moonsworth.lunar.bridge.Bridge9_7;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.mod.render.LightOverlay;
import com.moonsworth.lunar.client.mod.render.LightOverlay.Type;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;

class Lightoverlay2 {
   private static final int field1 = 75000;
   private static final int field2 = 500;
   private Lightoverlay2.Data field3 = new Lightoverlay2.Data();
   private volatile Lightoverlay2.Data field4 = new Lightoverlay2.Data();
   private Lightoverlay2.Data2 field5 = new Lightoverlay2.Data2();
   private volatile Lightoverlay2.Data2 field6 = new Lightoverlay2.Data2();
   private Bridge9_7[] field7 = new Bridge9_7[500];

   public Lightoverlay2() {
   }

   public void method1(List<Bridge9_7> var1) {
      int var2 = var1.size();
      Bridge9_7[] var3 = this.field7;
      int var4 = 0;

      for (int var5 = 0; var5 < var2; var5++) {
         var3 = this.method11(var3, (Bridge9_7)var1.get(var5), var4++);
      }

      for (int var6 = var4; var6 < var3.length; var6++) {
         var3[var6] = null;
      }

      this.field7 = var3;
   }

   public void method2(LightOverlay var1, Itemcounter6Extension var2) {
      Lightoverlay2.Data var3 = this.field3;
      Bridge9_7[] var4 = this.field7;
      Horsestats20Extension2[] var5 = var3.field1;
      short[] var6 = var3.field2;
      byte[] var7 = var3.field3;
      int var8 = 0;
      com.moonsworth.lunar.bridge.horsestats.Horsestats20.Extension var9 = null;

      for (int var10 = 0; var10 < var4.length; var10++) {
         Bridge9_7 var11 = var4[var10];
         if (var11 == null) {
            break;
         }

         Bridge5_5 var12 = var11.bridge$lightOverlayTracker();
         if (var12 == null) {
            Horsestats20Extension2 var13 = var11.bridge$origin();
            int var14 = var13.bridge$getX();
            int var15 = var13.bridge$getY();
            int var16 = var13.bridge$getZ();
            Itemcounter2 var17 = var2.bridge$getChunk(var14 >> 4, var16 >> 4);
            if (var9 == null) {
               var9 = Bridge.method8().method9(0, 0, 0);
            }

            for (int var18 = 0; var18 < 4096; var18++) {
               int var19 = var18 >> 8 & 15;
               int var20 = var18 >> 4 & 15;
               int var21 = var18 & 15;
               var9.bridge$setPos(var14 + var19, var15 + var20, var16 + var21);
               Bridge2_17 var22 = var17.bridge$getBlockState((Horsestats20Extension2)var9);
               if (var1.method12((Horsestats20Extension2)var9, var22)) {
                  if (ThreadModuleDump63.MC_VERSION == 0) {
                     var9.bridge$setPos(var14 + var19, var15 + var20 + 1, var16 + var21);
                  }

                  int var23 = var17.bridge$getSkyLight((Horsestats20Extension2)var9);
                  int var24 = var17.bridge$getBlockLight((Horsestats20Extension2)var9);
                  short var25 = (short)((var19 & 15) << 8 | (var20 & 15) << 4 | var21 & 15);
                  byte var26 = (byte)((var24 & 15) << 4 | var23 & 15);
                  var5 = this.method11(var5, var13, var8);
                  var6 = this.method8(var6, var25, var8);
                  var7 = this.method9(var7, var26, var8);
                  var8++;
               }
            }
         } else {
            ShortArrayList var29 = var12.method3();
            ByteArrayList var30 = var12.method4();
            if (var29 != null && var30 != null) {
               int var31 = var29.size();
               if (var31 > 0) {
                  var6 = this.method6(var6, var29.elements(), var8, var31);
                  var7 = this.method7(var7, var30.elements(), var8, var31);
                  var5 = this.method12(var5, var11.bridge$origin(), var8, var31);
                  var8 += var31;
               }
            }
         }
      }

      for (int var27 = var8; var27 < var5.length; var27++) {
         var5[var27] = null;
      }

      var3.field1 = var5;
      var3.field2 = var6;
      var3.field3 = var7;
      var3.field4 = var8;
      Lightoverlay2.Data var28 = this.field4;
      this.field4 = var3;
      this.field3 = var28;
   }

   public void method3(
      int var1,
      int var2,
      int var3,
      Itemcounter6Extension var4,
      @Nullable Lightoverlay2.Extension var5,
      boolean var6,
      boolean var7,
      int var8,
      int var9,
      int var10
   ) {
      Lightoverlay2.Data var11 = this.field4;
      int var12 = var11.field4;
      if (var12 != 0) {
         Lightoverlay2.Data2 var13 = this.field5;
         Horsestats20Extension2[] var14 = var11.field1;
         short[] var15 = var11.field2;
         byte[] var16 = var11.field3;
         int[] var17 = var13.field2;
         int var18 = 0;
         int var19 = (int)Math.pow(var9 * 8, 2.0);
         int var20 = (int)Math.pow(var9 * 5, 2.0);

         for (int var21 = 0; var21 < var12; var21++) {
            Horsestats20Extension2 var22 = var14[var21];
            if (var22 != null) {
               int var23 = var22.bridge$getX();
               int var24 = var22.bridge$getY();
               int var25 = var22.bridge$getZ();
               short var26 = var15[var21];
               int var27 = (var26 >> 8 & 15) + var23;
               int var28 = (var26 >> 4 & 15) + var24;
               int var29 = (var26 & 15) + var25;
               int var30 = var27 - var1;
               int var31 = var28 + 1 - var2;
               int var32 = var29 - var3;
               if (var31 * var31 <= var20 && var30 * var30 + var32 * var32 <= var19) {
                  byte var33 = var16[var21];
                  byte var34 = (byte)(var33 >> 4 & 15);
                  byte var35 = (byte)(var33 & 15);
                  if ((!var6 || (!var7 || LightOverlay.method17(var34, var35, var10) <= var8) && (var7 || LightOverlay.method16(var34, var35, var10)))
                     && (var5 == null || var5.isBlockVisible(var27, var28, var29))) {
                     Bridge3_23 var36 = var4.bridge$getBlockAt(var27, var28, var29);
                     if (var36 == null || !var36.bridge$isAir()) {
                        var17 = this.method10(var17, var21, var18++);
                     }
                  }
               }
            }
         }

         var13.field2 = var17;
         var13.field3 = var18;
         var13.field1 = var11;
         Lightoverlay2.Data2 var37 = this.field6;
         this.field6 = var13;
         this.field5 = var37;
      }
   }

   public void method4(BridgeExtension_9 var1, Bridge2_43 var2, LightOverlay var3) {
      Lightoverlay2.Data2 var4 = this.field6;
      int var5 = var4.field3;
      if (var5 != 0) {
         Lightoverlay2.Data var6 = var4.field1;
         Horsestats20Extension2[] var7 = var6.field1;
         short[] var8 = var6.field2;
         byte[] var9 = var6.field3;
         int[] var10 = var4.field2;
         Type var11 = (Type)var3.field16.get();
         LightoverlayHandler2 var12 = null;
         if (var11 == Type.CROSS) {
            var12 = new LightoverlayHandler(var1, var3);
         } else if (var11 == Type.OVERLAY) {
            var12 = new LightoverlayHandler3(var1, var3);
         }

         if (var12 != null) {
            this.method5(var12, var5, var10, var7, var8, var9, var2);
            var12.end();
         }

         if ((Boolean)var3.field17.get()) {
            var12 = new LightoverlayHandler2(var1, var3);
            this.method5(var12, var5, var10, var7, var8, var9, var2);
            var12.end();
         }
      }
   }

   private void method5(Lightoverlay var1, int var2, int[] var3, Horsestats20Extension2[] var4, short[] var5, byte[] var6, Bridge2_43 var7) {
      double var8 = var7.bridge$renderPosX();
      double var10 = var7.bridge$renderPosY();
      double var12 = var7.bridge$renderPosZ();

      for (int var14 = 0; var14 < var2; var14++) {
         int var15 = var3[var14];
         Horsestats20Extension2 var16 = var4[var15];
         if (var16 != null) {
            int var17 = var16.bridge$getX();
            int var18 = var16.bridge$getY();
            int var19 = var16.bridge$getZ();
            short var20 = var5[var15];
            int var21 = (var20 >> 8 & 15) + var17;
            int var22 = (var20 >> 4 & 15) + var18;
            int var23 = (var20 & 15) + var19;
            byte var24 = var6[var15];
            byte var25 = (byte)(var24 >> 4 & 15);
            byte var26 = (byte)(var24 & 15);
            double var27 = var21 - var8;
            double var29 = var22 + 1.01 - var10;
            double var31 = var23 - var12;
            var1.method1(var27, var29, var31, var25, var26);
         }
      }
   }

   private short[] method6(short[] var1, short[] var2, int var3, int var4) {
      int var5 = var1.length;
      int var6 = var3 + var4;
      if (var6 > var5) {
         short[] var7 = new short[var6 * 3 / 2];
         System.arraycopy(var1, 0, var7, 0, var5);
         var1 = var7;
      }

      System.arraycopy(var2, 0, var1, var3, var4);
      return var1;
   }

   private byte[] method7(byte[] var1, byte[] var2, int var3, int var4) {
      int var5 = var1.length;
      int var6 = var3 + var4;
      if (var6 > var5) {
         byte[] var7 = new byte[var6 * 3 / 2];
         System.arraycopy(var1, 0, var7, 0, var5);
         var1 = var7;
      }

      System.arraycopy(var2, 0, var1, var3, var4);
      return var1;
   }

   private short[] method8(short[] var1, short var2, int var3) {
      int var4 = var1.length;
      if (var3 >= var4) {
         short[] var5 = new short[var4 * 3 / 2];
         System.arraycopy(var1, 0, var5, 0, var4);
         var1 = var5;
      }

      var1[var3] = var2;
      return var1;
   }

   private byte[] method9(byte[] var1, byte var2, int var3) {
      int var4 = var1.length;
      if (var3 >= var4) {
         byte[] var5 = new byte[var4 * 3 / 2];
         System.arraycopy(var1, 0, var5, 0, var4);
         var1 = var5;
      }

      var1[var3] = var2;
      return var1;
   }

   private int[] method10(int[] var1, int var2, int var3) {
      int var4 = var1.length;
      if (var3 >= var4) {
         int[] var5 = new int[var4 * 3 / 2];
         System.arraycopy(var1, 0, var5, 0, var4);
         var1 = var5;
      }

      var1[var3] = var2;
      return var1;
   }

   private <T> T[] method11(T[] var1, T var2, int var3) {
      int var4 = var1.length;
      if (var3 >= var4) {
         var1 = Arrays.copyOf(var1, var4 * 3 / 2);
      }

      var1[var3] = var2;
      return (T[])var1;
   }

   private <T> T[] method12(T[] var1, T var2, int var3, int var4) {
      int var5 = var1.length;
      int var6 = var3 + var4;
      if (var6 > var5) {
         var1 = Arrays.copyOf(var1, var6 * 3 / 2);
      }

      Arrays.fill(var1, var3, var3 + var4, var2);
      return (T[])var1;
   }

   private static class Data {
      private Horsestats20Extension2[] field1 = new Horsestats20Extension2[75000];
      private short[] field2 = new short[75000];
      private byte[] field3 = new byte[75000];
      private int field4 = 0;

      public Data() {
      }
   }

   private static class Data2 {
      private Lightoverlay2.Data field1;
      private int[] field2 = new int[500];
      private int field3 = 0;

      public Data2() {
      }
   }

   @FunctionalInterface
   public interface Extension {
      boolean isBlockVisible(int var1, int var2, int var3);
   }
}
