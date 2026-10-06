package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.Bridge4Extension;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension2;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

@Annotation2(min = 8)
public abstract class TurboBatchCollector<Context> extends TurboBatchRecorder {
   protected final Map<RenderLayerBridge, Bridge2_5> field5 = new HashMap<>();
   protected FragData field6;
   protected boolean field7;

   public TurboBatchCollector(TurboEngineManager var1) {
      super(var1);
   }

   @Override
   public void clear() {
      for (Bridge2_5 var2 : this.field5.values()) {
         var2.bridge$close();
      }

      this.field6 = null;
      this.field5.clear();
      this.field1 = 0;
      this.field7 = false;
      this.field7 = true;
   }

   public void method4() {
      this.field7 = true;
   }

   protected abstract boolean method5();

   protected abstract boolean method6();

   protected boolean method4(@Nullable Context var1) {
      if (this.field7 && var1 != null) {
         this.method10((Context)var1);
         this.field7 = false;
      }

      return false;
   }

   protected abstract boolean method5(Vec3Bridge var1, AxisAlignedBBBridge var2);

   public void method6(Bridge5_16 var1, double var2, double var4, double var6, long var8) {
      this.method7(null, var1, var2, var4, var6, var8);
   }

   public void method7(@Nullable Context var1, Bridge5_16 var2, double var3, double var5, double var7, long var9) {
      if (!this.method4(var1)) {
         if (!this.field5.isEmpty()) {
            var2.bridge$pushPose();
            Extension2 var12 = Bridge.method42().method83();
            MixinHelper_21 var11 = var12.method15().bridge$copy();
            var11.bridge$multiply(var2.bridge$last().bridge$pose());
            if (this.method5()) {
               this.field3.method23().method1(this.field6);

               for (Entry var14 : this.field5.entrySet()) {
                  Bridge2_5 var15 = (Bridge2_5)var14.getValue();
                  if (var15 != null) {
                     MixinHelper_21 var16 = var11.bridge$copy();
                     PathSearchContext.method4(var3, var5, var7, null, var16);
                     RenderLayerBridge var17 = (RenderLayerBridge)var14.getKey();
                     this.field3
                        .method23()
                        .method3(this.method1(), var17, () -> PathSearchContext.method5(var16, var17, var15), true);
                  }
               }
            } else {
               Vec3Bridge var20 = this.field3.method29();
               if (this.field6.method2() && this.method5(var20, this.field6.method1())) {
                  var2.bridge$popPose();
                  return;
               }

               this.field3.method23().method1(this.field6);

               for (Entry var22 : this.field5.entrySet()) {
                  Bridge2_5 var23 = (Bridge2_5)var22.getValue();
                  if (var23 != null) {
                     MixinHelper_21 var24 = var11.bridge$copy();
                     RenderLayerBridge var18 = (RenderLayerBridge)var22.getKey();
                     if (this.field3.method2() && this.method6()) {
                        PathSearchContext.method6(var18, var20, null, var23, () -> {
                           Vector3d var2x = this.field6.method1().method13();
                           return var20.method4(var2x.x, var2x.y, var2x.z);
                        }, var9, false, true);
                     }

                     PathSearchContext.method4(var3, var5, var7, null, var24);
                     if (ThreadModuleDump63.MC_VERSION >= 22) {
                        this.field3
                           .method23()
                           .method3(this.method1(), var18, () -> PathSearchContext.method5(var24, var18, var23), true);
                     } else {
                        Matrix3fBridge var19 = PathSearchContext.method3(var24);
                        this.field3
                           .method23()
                           .method3(this.method1(), var18, () -> PathSearchContext.method11(var19, var24, var18, var23), true);
                     }
                  }
               }
            }

            var2.bridge$popPose();
         }
      }
   }

   protected abstract void method8(@Nullable Context var1, Map<RenderLayerBridge, Bridge4Extension> var2, List<Object> var3);

   protected void method9(boolean var1) {
   }

   protected void method10(@Nullable Context var1) {
      for (Bridge2_5 var3 : this.field5.values()) {
         if (var3 != null) {
            var3.bridge$close();
         }
      }

      HashMap var18 = new HashMap();
      ArrayList var19 = new ArrayList();
      this.field7 = true;
      this.method8((Context)var1, var18, var19);
      this.field7 = false;
      boolean var4 = !var18.isEmpty();
      if (var4) {
         FragData var5 = this.field3.method23().method8(null, this.field4, var19);
         if (this.field6 != null) {
            this.field1 = this.field1 - this.field6.getCount();
         }

         this.field1 = this.field1 + var5.getCount();
         this.field6 = var5;
         Extension2 var6 = Bridge.method42().method83();
         Vec3Bridge var7 = ThreadModuleDump63.method13().bridge$getCameraPos();
         boolean var8 = !this.method5() && this.method6();
         boolean var9 = true;

         for (Entry var11 : var18.entrySet()) {
            RenderLayerBridge var12 = (RenderLayerBridge)var11.getKey();
            Bridge4Extension var13 = (Bridge4Extension)var11.getValue();
            if (var13.method1()) {
               boolean var14 = var8 && var12.bridge$sortOnUpload();
               Bridge2_5 var15 = Bridge.method8().method74(var14);
               Bridge2_5 var16 = var6.method12(var15, var13, var14, var12);
               var13.bridge$close();
               Bridge2_5 var17 = this.field5.put(var12, var16);
               if (var17 != null) {
                  var17.bridge$close();
               }

               if (var8) {
                  PathSearchContext.method6(var12, var7, null, var16, () -> 0.0, 0L, true, true);
               }

               var9 = false;
            }
         }

         if (var9) {
            var4 = false;
            this.removeSelf();
         }
      } else {
         this.removeSelf();
      }

      this.method9(var4);
      this.field3.method23().method9();
   }

   private void removeSelf() {
      FragData var1 = this.field6;
      if (var1 != null) {
         this.field1 = this.field1 - var1.getCount();
      }

      this.field5.clear();
      this.field6 = null;
   }
}
