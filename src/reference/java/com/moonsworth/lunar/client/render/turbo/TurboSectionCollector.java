package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge14_3;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.Matrix3fBridge;
import com.moonsworth.lunar.bridge.MixinHelper_21;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension2;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.render.turbo.SingleEntityBatchGroup;
import com.moonsworth.lunar.client.render.turbo.SectionBatchGroup;
import com.moonsworth.lunar.client.render.texture.SpriteSource;
import com.moonsworth.lunar.client.render.turbo.EntityBatchType;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import org.jetbrains.annotations.Nullable;

@Annotation2(min = 8)
public abstract class TurboSectionCollector<ID, Context> extends TurboGroupCollector<ID, Context, SectionBatchGroup<ID>> {
   public TurboSectionCollector(TurboEngineManager var1) {
      super(var1);
   }

   public SectionBatchGroup<ID> method11() {
      return new SingleEntityBatchGroup<>(this.HCRRHRCHOIOCOOCOCOHOOROOORCCII());
   }

   protected int method2(Extension2 var1) {
      return PathSearchContext.method8(var1);
   }

   @Override
   public void method10(@Nullable Context var1, Bridge5_16 var2, double var3, double var5, double var7, long var9) {
      if (!this.method8(var1)) {
         if (!this.CHRRCRIHOCCRIIRRCHOOCHOIRROHIO.isEmpty()) {
            SpriteSource var11 = this.field3.method23();
            if (var11 != null) {
               var2.bridge$pushPose();
               Extension2 var13 = Bridge.method42().method83();
               MixinHelper_21 var12 = var13.method15().bridge$copy();
               var12.bridge$multiply(var2.bridge$last().bridge$pose());
               Vec3Bridge var14 = this.field3.method29();
               if (var14 != null) {
                  EntityBatchType var15 = this.HCRRHRCHOIOCOOCOCOHOOROOORCCII();
                  Object var16 = var15.method1(var14);
                  ((SectionBatchGroup)this.field8).method4(var14, var16);
                  Bridge14_3 var17 = ThreadModuleDump63.method3().bridge$getLevelRenderer();
                  int var18 = this.method2(var13);
                  HashSet var19 = new HashSet();

                  for (Entry var21 : this.OICORIRRCCHHRHRICICOOHHRRHOORR.entrySet()) {
                     Object var22 = var21.getKey();
                     FragData var23 = (FragData)var21.getValue();
                     if (var23 == null) {
                        ((SectionBatchGroup)this.field8).method4(var22);
                     } else if (!((SectionBatchGroup)this.field8).method6(var22)) {
                        if (var17 != null && var23.method2()) {
                           if (var15.method7(var17, var16, var23.method1())) {
                              var19.add(var22);
                              continue;
                           }

                           if (((SectionBatchGroup)this.field8)
                              .method4(var22, var5x -> var15.method8(var17, var16, var23.method1(), var18))) {
                              continue;
                           }
                        }

                        if (!this.method11(var22, var16)) {
                           ((SectionBatchGroup)this.field8).method4(var22);
                        } else {
                           var11.method1(var23);
                        }
                     }
                  }

                  Object2DoubleOpenHashMap var33 = new Object2DoubleOpenHashMap();
                  boolean var34 = this.isRecording();

                  for (Entry var36 : this.CHRRCRIHOCCRIIRRCHOOCHOIRROHIO.entrySet()) {
                     RenderLayerBridge var24 = (RenderLayerBridge)var36.getKey();
                     boolean var25 = true;
                     Map var26 = (Map)var36.getValue();

                     for (Object var28 : (SectionBatchGroup)this.field8) {
                        if (!var19.contains(var28)) {
                           Bridge2_5 var29 = (Bridge2_5)var26.get(var28);
                           if (var29 != null) {
                              Vector3iBridge var30 = var15.method6(var28);
                              MixinHelper_21 var31 = var12.bridge$copy();
                              if (var34) {
                                 PathSearchContext.method6(
                                    var24,
                                    this.field3.method2() ? var14 : null,
                                    var30,
                                    var29,
                                    () -> var33.computeIfAbsent(var28, var2xx -> {
                                       Vector3iBridge var3xx = var15.method5(var2xx);
                                       return var14.method4(var3xx.bridge$getX(), var3xx.bridge$getY(), var3xx.bridge$getZ());
                                    }),
                                    var9,
                                    false,
                                    true
                                 );
                              }

                              PathSearchContext.method4(var3, var5, var7, var30, var31);
                              if (ThreadModuleDump63.MC_VERSION >= 22) {
                                 var11.method3(this.method1(), var24, () -> PathSearchContext.method5(var31, var24, var29), var25);
                              } else {
                                 Matrix3fBridge var32 = PathSearchContext.method3(var31);
                                 var11.method3(this.method1(), var24, () -> PathSearchContext.method11(var32, var31, var24, var29), var25);
                              }

                              if (var25) {
                                 var25 = false;
                              }
                           }
                        }
                     }
                  }

                  var2.bridge$popPose();
                  ((SectionBatchGroup)this.field8).method1();
               }
            }
         }
      }
   }
}
