package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension2;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.render.turbo.EntityBatchGroup;
import com.moonsworth.lunar.client.render.turbo.EntityBatchType;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

@Annotation2(min = 8)
public abstract class TurboEntityGroupCollector<ID, Context> extends TurboGroupCollector<ID, Context, EntityBatchGroup<ID>> {
   public TurboEntityGroupCollector(TurboEngineManager var1) {
      super(var1);
   }

   public EntityBatchGroup<ID> method11() {
      return new EntityBatchGroup<>(this.HCRRHRCHOIOCOOCOCOHOOROOORCCII());
   }

   protected abstract boolean method2(Vec3Bridge var1, ID var2, AxisAlignedBBBridge var3);

   @Override
   public void method10(@Nullable Context var1, Bridge5_16 var2, double var3, double var5, double var7, long var9) {
      if (!this.method8(var1)) {
         if (!this.CHRRCRIHOCCRIIRRCHOOCHOIRROHIO.isEmpty()) {
            var2.bridge$pushPose();
            Extension2 var12 = Bridge.method42().method83();
            MixinHelper_21 var11 = var12.method15().bridge$copy();
            var11.bridge$multiply(var2.bridge$last().bridge$pose());
            Vec3Bridge var13 = this.field3.method29();
            EntityBatchType var14 = this.HCRRHRCHOIOCOOCOCOHOOROOORCCII();
            Object var15 = var14.method1(var13);
            ((EntityBatchGroup)this.field8).method1(var13, var15);
            Object2DoubleOpenHashMap var16 = new Object2DoubleOpenHashMap();
            Object2BooleanOpenHashMap var17 = new Object2BooleanOpenHashMap();

            for (Entry var19 : this.OICORIRRCCHHRHRICICOOHHRRHOORR.entrySet()) {
               Object var20 = var19.getKey();
               FragData var21 = (FragData)var19.getValue();
               if (var21 == null) {
                  var17.put(var20, false);
               } else if (!var21.method2() || !var17.computeIfAbsent(var20, var4 -> this.method2(var13, (ID)var15, var21.method1()))) {
                  this.field3.method23().method1(var21);
               }
            }

            boolean var29 = this.isRecording();

            for (Entry var31 : this.CHRRCRIHOCCRIIRRCHOOCHOIRROHIO.entrySet()) {
               RenderLayerBridge var32 = (RenderLayerBridge)var31.getKey();
               boolean var22 = true;
               Map var23 = (Map)var31.getValue();

               for (Object var25 : (EntityBatchGroup)this.field8) {
                  if (!var17.getBoolean(var25)) {
                     Bridge2_5 var26 = (Bridge2_5)var23.get(var25);
                     if (var26 != null) {
                        Vector3iBridge var27 = var14.method6(var25);
                        MixinHelper_21 var28 = var11.bridge$copy();
                        if (var29) {
                           PathSearchContext.method6(
                              var32, this.field3.method2() ? var13 : null, var27, var26, () -> var16.computeIfAbsent(var25, var2xx -> {
                                 Vector3iBridge var3xx = var14.method5(var2xx);
                                 return var13.method4(var3xx.bridge$getX(), var3xx.bridge$getY(), var3xx.bridge$getZ());
                              }), var9, false, true
                           );
                        }

                        PathSearchContext.method4(var3, var5, var7, var27, var28);
                        this.field3
                           .method23()
                           .method3(this.method1(), var32, () -> PathSearchContext.method5(var28, var32, var26), var22);
                        if (var22) {
                           var22 = false;
                        }
                     }
                  }
               }
            }

            var2.bridge$popPose();
         }
      }
   }
}
