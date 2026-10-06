package com.moonsworth.lunar.client.util.colorsaturation;

import com.google.common.primitives.Floats;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.bridge.BatchingBufferSourceBridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.DrawMode;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension2;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.ints.IntArrays;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.util.Objects;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.CompiledData;
import org.joml.Vector3f;

@Annotation2(min = 8)
public class ColorsaturationTask implements Colorsaturation {
   private final CompiledData data;
   private final int[] field1;
   private final Reference2IntOpenHashMap<Bridge2_5> field2 = new Reference2IntOpenHashMap();
   private final ReferenceOpenHashSet<Bridge2_5> field3 = new ReferenceOpenHashSet();
   private Bridge2_5 field4;
   private Bridge2_5 field5;
   private int field6 = Bridge.method8().method92();
   private int field7 = -1;
   private Bridge2_5 field8;
   private boolean field9;

   protected ColorsaturationTask(CompiledData var1) {
      this.field1 = method11(var1);
      this.data = var1;
   }

   @Override
   public int method1() {
      return (this.data.posData.length + this.data.texData.length + this.data.normData.length + this.data.normData.length + this.data.weightData.length) * 4
         + (this.data.boneIndexData.length + this.data.indexData.length) * 4;
   }

   @Override
   public void method2(AbstractRenderContext var1, ResourceLocationBridge var2) {
   }

   @Override
   public void method3(RenderLayerBridge var1, BridgeExtension2_11 var2, RenderSystemBridge var3, int var4) {
      if (this.field1.length != 0) {
         Extension2 var5 = var3.method83();
         int var6 = var2.method46().orElse(Bridge.method8().method92());
         boolean var7 = method14();
         boolean var8 = method15();
         if (var7 != this.field9) {
            if (var7) {
               if (this.field4 != null) {
                  this.field8 = this.field4;
                  this.field4 = null;
               }
            } else if (this.field8 != null) {
               if (this.field4 != null) {
                  this.method9(this.field4);
                  this.field4 = this.field8;
               }

               this.field8 = null;
               this.field6 = Bridge.method8().method92();
            } else if (this.field4 != null) {
               this.method9(this.field4);
               this.field4 = null;
            }

            if (this.field5 != null) {
               this.method9(this.field5);
               this.field5 = null;
            }
         }

         if (this.field7 != var4) {
            if (this.field5 != null) {
               this.method9(this.field5);
               this.field5 = null;
            }

            this.field7 = var4;
         }

         this.field9 = var7;
         boolean var9 = this.field6 != var6;
         if (var8) {
            boolean var10 = var1.bridge$getVertexFormatMode() == DrawMode.QUADS;
            Bridge2_5 var11 = var10 ? this.field5 : this.field4;
            if (var11 == null || var9) {
               if (var11 != null) {
                  this.method9(var11);
                  if (var10) {
                     this.field5 = null;
                  } else {
                     this.field4 = null;
                  }
               }

               if (var10) {
                  var11 = var5.method10(var1, var3x -> method12(this.field1, Objects.requireNonNull(this.data), var3x, var6, var4, true), false);
                  this.field5 = var11;
               } else {
                  var11 = var5.method10(var1, var2x -> method12(this.field1, Objects.requireNonNull(this.data), var2x, var6, -1, false), false);
                  this.field4 = var11;
               }

               this.field6 = var6;
               if (var11 == null) {
                  throw new RuntimeException("Attempting to render an empty cosmetic!");
               }
            }
         }

         if (this.field8 == null && !var8) {
            this.field8 = var5.method10(
               var1, var1x -> method12(this.field1, Objects.requireNonNull(this.data), var1x, Bridge.method8().method92(), -1, false), false
            );
            if (this.field8 == null) {
               throw new RuntimeException("Attempting to render an empty cosmetic!");
            }
         }

         MixinHelper_21 var17;
         if (Bridge.getMinecraftVersion().method23()) {
            var17 = var5.method15().bridge$copy();
            var17.bridge$multiply(var2.method51().bridge$last().bridge$pose());
         } else {
            var17 = var5.method15();
         }

         if (ThreadModuleDump63.MC_VERSION >= 22) {
            if (var1 != null && var2.method43().isPresent()) {
               Bridge2_5 var19 = this.method7(var1);
               this.method8(var2, var1, () -> var19.bridge$draw(var17, var1), var19);
            } else {
               this.method6(var17, var1);
            }
         } else {
            Matrix3fBridge var20 = this.method4(var17);
            Vector3f[] var12 = var5.method16();
            Vector3f[] var13 = new Vector3f[]{new Vector3f(var12[0]), new Vector3f(var12[1])};
            Vector3f[] var14 = new Vector3f[2];

            for (int var15 = 0; var15 < 2; var15++) {
               Vector3f var16 = var13[var15];
               var14[var15] = new Vector3f(
                  var20.bridge$getTransformX(var16.x, var16.y, var16.z),
                  var20.bridge$getTransformY(var16.x, var16.y, var16.z),
                  var20.bridge$getTransformZ(var16.x, var16.y, var16.z)
               );
            }

            if (var1 != null && var2.method43().isPresent()) {
               ((BatchingBufferSourceBridge)var2.method43().orElseThrow()).bridge$renderUnbatchableAfter(var1, () -> this.method5(var5, var17, var13, var14, var1));
            } else {
               this.method5(var5, var17, var13, var14, var1);
            }
         }
      }
   }

   private Matrix3fBridge method4(MixinHelper_21 var1) {
      MixinHelper_21 var2 = var1.bridge$copy();
      var2.bridge$invert();
      var2.bridge$transpose();
      Matrix3fBridge var3 = var2.bridge$truncateToMatrix3f();
      var3.bridge$invert();
      return var3;
   }

   @Annotation2(max = 29)
   private void method5(Extension2 var1, MixinHelper_21 var2, Vector3f[] var3, Vector3f[] var4, RenderLayerBridge var5) {
      var1.method14(var4[0], var4[1]);
      this.method6(var2, var5);
      var1.method14(var3[0], var3[1]);
   }

   private void method6(MixinHelper_21 var1, RenderLayerBridge var2) {
      this.method7(var2).bridge$draw(var1, var2);
   }

   private Bridge2_5 method7(RenderLayerBridge var1) {
      if (method15()) {
         return var1.bridge$getVertexFormatMode() == DrawMode.QUADS ? this.field5 : this.field4;
      } else {
         return this.field8;
      }
   }

   private void method8(BridgeExtension2_11 var1, RenderLayerBridge var2, Runnable var3, Bridge2_5 var4) {
      this.field2.addTo(var4, 1);
      ((BatchingBufferSourceBridge)var1.method43().orElseThrow()).bridge$renderUnbatchableAfter(var2, () -> {
         try {
            var3.run();
         } finally {
            this.method10(var4);
         }
      });
   }

   private void method9(Bridge2_5 var1) {
      if (var1 != null) {
         if (this.field2.getInt(var1) > 0) {
            this.field3.add(var1);
         } else {
            var1.bridge$close();
         }
      }
   }

   private void method10(Bridge2_5 var1) {
      if (this.field2.addTo(var1, -1) <= 1) {
         this.field2.removeInt(var1);
         if (this.field3.remove(var1)) {
            var1.bridge$close();
         }
      }
   }

   private static int[] method11(CompiledData var0) {
      int var11 = 0;
      int var2 = var0.posData.length / 4 / 3;
      float[] var3 = new float[var2];
      int[] var4 = new int[var2];

      for (int var5 = 0; var5 < var2; var4[var5] = var5++) {
         float var6 = 0.0F;

         for (int var7 = 0; var7 < 3; var7++) {
            var11 += 2;
            float var8 = var0.posData[var11++];
            float var9 = var0.posData[var11++];
            float var10 = var8 / var9;
            var6 += var10;
         }

         var6 /= 3.0F;
         var3[var5] = var6;
      }

      IntArrays.mergeSort(var4, (var1, var2x) -> Floats.compare(var3[var1], var3[var2x]));
      return var4;
   }

   private static void method12(int[] var0, CompiledData var1, Bridge4_6 var2, int var3, int var4, boolean var5) {
      int var6 = var1.posData.length / 4 / 3;
      int var7 = var4 >>> 16 & 0xFF;
      int var8 = var4 >>> 8 & 0xFF;
      int var9 = var4 & 0xFF;
      int var10 = var4 >>> 24;

      for (int var11 = 0; var11 < var6; var11++) {
         int var12 = var0[var11];

         for (int var13 = 0; var13 < 3; var13++) {
            int var14 = var12 * 3 + var13;
            float var15 = var1.posData[var14 * 4];
            float var16 = var1.posData[var14 * 4 + 1];
            float var17 = var1.posData[var14 * 4 + 2];
            float var18 = var1.posData[var14 * 4 + 3];
            float var19 = var1.normData[var14 * 3];
            float var20 = var1.normData[var14 * 3 + 1];
            float var21 = var1.normData[var14 * 3 + 2];
            int var22 = var5 && var13 == 2 ? 2 : 1;

            for (int var23 = 0; var23 < var22; var23++) {
               var2.bridge$vertex(var15 / var18, var16 / var18, var17 / var18)
                  .bridge$color(var7, var8, var9, var10)
                  .bridge$uv(var1.texData[var14 * 2], var1.texData[var14 * 2 + 1])
                  .bridge$overlayCoords(0, 10)
                  .bridge$uv2(var3 & 0xFF, var3 >> 16 & 0xFF)
                  .bridge$normal(var19, var20, var21)
                  .bridge$endVertex();
            }
         }
      }
   }

   @Override
   public AxisAlignedBBBridge method4() {
      float var1 = Float.MAX_VALUE;
      float var2 = Float.MAX_VALUE;
      float var3 = Float.MAX_VALUE;
      float var4 = -Float.MAX_VALUE;
      float var5 = -Float.MAX_VALUE;
      float var6 = -Float.MAX_VALUE;

      for (int var7 = 0; var7 < this.data.posData.length / 4; var7++) {
         float var8 = this.data.posData[var7 * 4 + 3];
         float var9 = this.data.posData[var7 * 4] / var8;
         float var10 = this.data.posData[var7 * 4 + 1] / var8;
         float var11 = this.data.posData[var7 * 4 + 2] / var8;
         if (var9 < var1) {
            var1 = var9;
         }

         if (var9 > var4) {
            var4 = var9;
         }

         if (var10 < var2) {
            var2 = var10;
         }

         if (var10 > var5) {
            var5 = var10;
         }

         if (var11 < var3) {
            var3 = var11;
         }

         if (var11 > var6) {
            var6 = var11;
         }
      }

      return Bridge.method8().method45(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void delete() {
      ObjectIterator var1 = this.field3.iterator();

      while (var1.hasNext()) {
         Bridge2_5 var2 = (Bridge2_5)var1.next();
         var2.bridge$close();
      }

      this.field3.clear();
      this.field2.clear();
      if (this.field4 != null) {
         this.field4.bridge$close();
         this.field4 = null;
      }

      if (this.field5 != null) {
         this.field5.bridge$close();
         this.field5 = null;
      }

      if (this.field8 != null) {
         this.field8.bridge$close();
         this.field8 = null;
      }
   }

   private static boolean method14() {
      return Fishing.method2(Fishing2Extension.class)
         .<Boolean>map(Fishing2Extension::lunar$isUsingExtendedVertexFormat)
         .orElseGet(() -> Bridge.method5().<Slayer3>map(Slayer2::getShaders).map(var0 -> {
            String var1 = var0.getShaderPack();
            return var1 != null && !var1.equals(var0.getPackNone());
         }).orElse(false));
   }

   private static boolean method15() {
      return Fishing.method2(Fishing2Extension.class).<Boolean>map(Fishing2Extension::lunar$isRenderingLevel).orElseGet(() -> !LcuiScreen.field5);
   }
}
