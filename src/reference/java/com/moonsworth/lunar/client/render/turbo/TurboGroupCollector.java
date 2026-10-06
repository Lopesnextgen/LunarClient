package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.Bridge4Extension;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension2;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.render.turbo.BatchGroup;
import com.moonsworth.lunar.client.render.turbo.EntityBatchType;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import lombok.Generated;
import org.jetbrains.annotations.VisibleForTesting;

@Annotation2(min = 8)
public abstract class TurboGroupCollector<ID, Context, Sorter extends BatchGroup<ID>> extends TurboBatchRecorder {
   protected final Map<RenderLayerBridge, Map<ID, Bridge2_5>> field5 = new HashMap<>();
   protected final Map<ID, FragData> field6 = new HashMap<>();
   protected final ArrayDeque<ID> field7 = new ArrayDeque<>();
   protected final Sorter field8 = this.method8();

   public TurboGroupCollector(TurboEngineManager var1) {
      super(var1);
   }

   @VisibleForTesting
   public List<AxisAlignedBBBridge> method4() {
      EntityBatchType var1 = this.method9();
      ArrayList var2 = new ArrayList();

      for (Object var4 : this.field6.keySet()) {
         var2.add(var1.method4(var4));
      }

      return var2;
   }

   @VisibleForTesting
   public Collection<FragData> method5() {
      return this.field6.values();
   }

   public int method6() {
      return this.field6.size();
   }

   public abstract Sorter method8();

   public abstract EntityBatchType<ID> method9();

   @Override
   public void clear() {
      for (Map var2 : this.field5.values()) {
         for (Bridge2_5 var4 : var2.values()) {
            var4.bridge$close();
         }
      }

      this.field5.clear();
      this.field7.clear();
      this.field6.clear();
      this.field8.clear();
      this.field1 = 0;
      this.field2 = false;
   }

   public void method6(ID var1, boolean var2) {
      if (!this.field7.contains(var1)) {
         if (var2) {
            this.field7.addFirst((ID)var1);
         } else {
            this.field7.addLast((ID)var1);
         }
      }
   }

   protected boolean method10() {
      return true;
   }

   protected abstract boolean method8(@Nullable Context var1);

   public void method9(Bridge5_16 var1, double var2, double var4, double var6, long var8) {
      this.method10(null, var1, var2, var4, var6, var8);
   }

   public abstract void method10(@Nullable Context var1, Bridge5_16 var2, double var3, double var5, double var7, long var9);

   public boolean method11(ID var1, ID var2) {
      return true;
   }

   protected abstract void method12(@Nullable Context var1, ID var2, Vector3iBridge var3, Map<RenderLayerBridge, Bridge4Extension> var4, List<Object> var5);

   protected void method13(ID var1, boolean var2) {
   }

   protected void method14(@Nullable Context var1, ID var2) {
      EntityBatchType var3 = this.method9();
      var3.method9(this.field3);

      for (Map var5 : this.field5.values()) {
         Bridge2_5 var6 = (Bridge2_5)var5.remove(var2);
         if (var6 != null) {
            var6.bridge$close();
         }
      }

      if (ThreadModuleDump63.method8() == null) {
         this.method15((ID)var2);
      } else {
         HashMap var23 = new HashMap();
         ArrayList var24 = new ArrayList();
         Vector3iBridge var25 = var3.method6(var2);
         this.field2 = true;
         this.method12((Context)var1, (ID)var2, var25, var23, var24);
         this.field2 = false;
         boolean var7 = !var23.isEmpty();
         if (var7) {
            this.field8.method2((ID)var2);
            FragData var8 = this.field3.method23().method8(var2, this.field4, var24);
            FragData var9 = this.field6.put((ID)var2, var8);
            if (var9 != null) {
               this.field1 = this.field1 - var9.getCount();
            }

            this.field1 = this.field1 + var8.getCount();
            Extension2 var10 = Bridge.method42().method83();
            Vec3Bridge var11 = ThreadModuleDump63.method13().bridge$getCameraPos();
            boolean var12 = this.method10();
            boolean var13 = true;

            for (Entry var15 : var23.entrySet()) {
               RenderLayerBridge var16 = (RenderLayerBridge)var15.getKey();
               Map var17 = this.field5.computeIfAbsent(var16, var0 -> new HashMap<>());
               Bridge4Extension var18 = (Bridge4Extension)var15.getValue();
               if (var18.method1()) {
                  boolean var19 = var12 && var16.bridge$sortOnUpload();
                  Bridge2_5 var20 = Bridge.method8().method74(var19);
                  Bridge2_5 var21 = var10.method12(var20, var18, var19, var16);
                  var18.bridge$close();
                  Bridge2_5 var22 = var17.put(var2, var21);
                  if (var22 != null) {
                     var22.bridge$close();
                  }

                  if (var12) {
                     PathSearchContext.method6(var16, var11, var25, var21, () -> 0.0, 0L, true, true);
                  }

                  var13 = false;
               }
            }

            if (var13) {
               var7 = false;
               this.method15((ID)var2);
            }
         } else {
            this.method15((ID)var2);
         }

         var3.method10(this.field3);
         this.method13((ID)var2, var7);
      }
   }

   @OverridingMethodsMustInvokeSuper
   protected void method15(ID var1) {
      this.field8.method3((ID)var1);
      FragData var2 = this.field6.remove(var1);
      if (var2 != null) {
         this.field1 = this.field1 - var2.getCount();
      }

      this.field5.entrySet().removeIf(var0 -> var0.getValue().isEmpty());
   }

   @Generated
   public Sorter method16() {
      return this.field8;
   }
}
