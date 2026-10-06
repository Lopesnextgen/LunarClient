package com.moonsworth.lunar.client.render.turbo;

import com.eliotlash.molang.utils.MathUtils;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge17Extension;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge2_31;
import com.moonsworth.lunar.bridge.Bridge4Extension;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.TileEntityRendererBridge;
import com.moonsworth.lunar.bridge.Bridge_38;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.RenderSystemBridge.Extension2;
import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.config.PerformanceSettings;
import com.moonsworth.lunar.client.render.turbo.FragDataFactory;
import com.moonsworth.lunar.client.render.turbo.EntityFragDataFactory;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.render.turbo.PathSearchContext;
import com.moonsworth.lunar.client.render.texture.SpriteSource;
import com.moonsworth.lunar.client.render.turbo.EntityBatchType;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.event.render.EventFogSetup.FogSource;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEntityPos;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockModified;
import com.moonsworth.lunar.client.event.mixin.fishing.PlayerBlockInteractBaseEvent.EventBlockInteract;
import com.moonsworth.lunar.client.event.mixin.fishing.PlayerBlockInteractBaseEvent.EventBlockInteractExtended;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldLoaded;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import it.unimi.dsi.fastutil.longs.Long2ObjectAVLTreeMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectSortedMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lombok.Generated;

@Annotation2(min = 8)
public class TurboBlockEntityRecorder extends com.moonsworth.lunar.client.render.turbo.TurboSectionCollector<Horsestats20Extension, Void> {
   public static final boolean field9 = true;
   private static final int field10 = 5;
   private final Long2ObjectSortedMap<Set<Horsestats20Extension>> field11 = new Long2ObjectAVLTreeMap();
   private final Object2LongOpenHashMap<TurboEntityCost> field12 = new Object2LongOpenHashMap();
   private boolean field13;
   private int field14;

   public TurboBlockEntityRecorder(TurboEngineManager var1) {
      super(var1);
      this.handle(EventBlockInteractExtended.class, var1x -> ThreadModuleDump37.method19(() -> {
         HitcolorExtension var2x = var1x.RCOCRIIIOIIRHHRHICHCCICROIRCHH();
         boolean var3 = var2x.lunar$supportsTurbo() && var2x.bridge$canTurbo();
         if (var3) {
            float var4 = var2x.bridge$minimumTurboDistance();
            if (var4 != 0.0F && this.method14(ThreadModuleDump63.method13().bridge$getCameraPos(), var2x) < var4) {
               var3 = false;
            }
         }

         if (var3) {
            this.method6(var1x.method1());
         }

         HitcolorExtension var5 = var1x.method3();
         if (var5 != null && var5.bridge$isTurbo()) {
            var5.bridge$setTurbo(false);
            if (!var3) {
               this.method6(var1x.method1());
            }
         }
      }));
      this.handle(EventBlockInteract.class, var1x -> {
         HitcolorExtension var2x = var1x.RCOCRIIIOIIRHHRHICHCCICROIRCHH();
         if (var2x.bridge$isTurbo()) {
            this.method6(var1x.method1());
         }
      });
      this.handle(EventBlockModified.class, var0 -> var0.method2().bridge$getBlock().bridge$onStateChange(var0.method1(), var0.method2(), var0.method3()));
      this.handle(EventEntityPos.class, var1x -> {
         Horsestats20Extension var2x = var1x.method1();
         if (this.OICORIRRCCHHRHRICICOOHHRRHOORR.containsKey(var2x)) {
            this.method17(var2x, false);
         }
      });
      this.handle(EventClientTick.class, var1x -> {
         if (!this.field11.isEmpty()) {
            ObjectBidirectionalIterator var2x = this.field11.long2ObjectEntrySet().iterator();

            while (var2x.hasNext()) {
               Entry var3 = (Entry)var2x.next();
               long var4 = var3.getLongKey();
               if (var4 - EventClientTick.field1 > 0L) {
                  break;
               }

               for (Horsestats20Extension var7 : (Set)var3.getValue()) {
                  this.method17(var7, true);
               }

               var2x.remove();
            }
         }

         if (!this.field12.isEmpty()) {
            long var10 = EventClientTick.field1;
            ArrayList var11 = null;
            ObjectIterator var5 = this.field12.object2LongEntrySet().iterator();

            while (var5.hasNext()) {
               it.unimi.dsi.fastutil.objects.Object2LongMap.Entry var13 = (it.unimi.dsi.fastutil.objects.Object2LongMap.Entry)var5.next();
               if (var13.getLongValue() - var10 <= 0L) {
                  if (var11 == null) {
                     var11 = new ArrayList();
                  }

                  var11.add((TurboEntityCost)var13.getKey());
               }
            }

            if (var11 != null) {
               Vec3Bridge var12 = ThreadModuleDump63.method13().bridge$getCameraPos();

               for (TurboEntityCost var15 : var11) {
                  double var8 = this.method1(var15.field1, var12);
                  if (var8 <= var15.field2) {
                     this.field12.removeLong(var15);
                     this.method17(var15.field1, true);
                  } else if (var8 >= var15.field2 * 2.0F) {
                     this.field12.put(var15, var10 + 10L);
                  } else {
                     this.field12.put(var15, var10 + 5L);
                  }
               }
            }
         }
      });
      this.handle(EventWorldLoaded.class, var1x -> this.method3());
      PerformanceSettings var2 = Client.method109().method41().method7();
      this.field13 = !(Boolean)var2.method21().get();
      this.field14 = (Integer)var2.method20().get();
   }

   private float method1(Horsestats20Extension var1, Vec3Bridge var2) {
      int var3 = Horsestats20Extension.method14(var1.bridge$getX());
      int var4 = Horsestats20Extension.method14(var1.bridge$getY());
      int var5 = Horsestats20Extension.method14(var1.bridge$getZ());
      int var6 = (int)Math.floor(MathUtils.clamp(var2.bridge$xCoord(), var3, var3 + 16));
      int var7 = (int)Math.floor(MathUtils.clamp(var2.bridge$yCoord(), var4, var4 + 16));
      int var8 = (int)Math.floor(MathUtils.clamp(var2.bridge$zCoord(), var5, var5 + 16));
      return (float)var2.method4(var6 + 0.5F, var7 + 0.5F, var8 + 0.5F);
   }

   public static float method2(double var0, int var2, int var3) {
      return (float)Math.max(var2, Math.min(var3, var0));
   }

   @Override
   public BatchEntityType method1() {
      return BatchEntityType.ENTITIES;
   }

   @Override
   public EntityBatchType<Horsestats20Extension> method9() {
      return EntityBatchType.field1;
   }

   @Override
   public FragDataFactory<Itemcounter_4> method2() {
      return EntityFragDataFactory.field2;
   }

   private void method6(Vector3iBridge var1) {
      this.method17(Horsestats20Extension.method4(var1), true);
   }

   public void method7(long var1, Horsestats20Extension var3) {
      ((Set)this.field11.computeIfAbsent(EventClientTick.field1 + var1, var0 -> new HashSet())).add(var3);
   }

   @Override
   public void clear() {
      super.clear();
      this.field11.clear();
      this.field12.clear();
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 != null) {
         for (HitcolorExtension var3 : var1.bridge$getBlockEntities()) {
            if (var3 != null && var3.bridge$isTurbo()) {
               var3.bridge$setTurbo(false);
            }
         }
      }
   }

   @Override
   public void method3() {
      this.clear();
      if (ThreadModuleDump63.method8() != null) {
         Vec3Bridge var1 = ThreadModuleDump63.method13().bridge$getCameraPos();

         for (HitcolorExtension var3 : ThreadModuleDump63.method8().bridge$getBlockEntities()) {
            if (var3 != null && var3.lunar$supportsTurbo() && var3.bridge$canTurbo()) {
               float var4 = var3.bridge$minimumTurboDistance();
               if (var4 == 0.0F || !(this.method14(var1, var3) < var4)) {
                  Vector3iBridge var5 = var3.bridge$getBlockPos();
                  this.method17(Horsestats20Extension.method4(var5), false);
               }
            }
         }
      }
   }

   @Override
   public void method4(List<String> var1) {
      var1.add("[LC Turbo Block Entities] Affected Blocks: " + this.CCHHRRHCHIOOHCROCICHHIHCCORHCR);
      var1.add("[LC Turbo Block Entities] Affected Sections: " + this.method7());
      var1.add("[LC Turbo Block Entities] Render Types: " + this.CHRRCRIHOCCRIIRRCHOOCHOIRROHIO.size());
   }

   @Override
   public List<Bridge_38> method5(Predicate<Bridge_38> var1) {
      ArrayList var2 = new ArrayList();
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var3 != null) {
         for (HitcolorExtension var5 : var3.bridge$getBlockEntities()) {
            if (var5.lunar$supportsTurbo() && var1.test(var5)) {
               var2.add(var5);
            }
         }
      }

      return var2;
   }

   @Override
   protected int method2(Extension2 var1) {
      return this.field3.method4() ? -1 : super.method2(var1);
   }

   protected boolean method12(@Nullable Void var1) {
      if (TurboEngineManager.method22(this.field3.method28()) && !PathSearchContext.method7(Bridge.method42())) {
         for (int var2 = 0; var2 < this.field14; var2++) {
            Horsestats20Extension var3 = (Horsestats20Extension)this.field7.pollFirst();
            if (var3 == null) {
               break;
            }

            this.method14(var1, var3);
         }

         return false;
      } else {
         return true;
      }
   }

   public boolean method13(TileEntityRendererBridge var1, HitcolorExtension var2) {
      if (!Client.method109().method41().method7().method4(var2, var2.bridge$getBlockType())) {
         return true;
      }

      Vector3iBridge var3 = var2.bridge$getBlockPos();
      if (ThreadModuleDump63.method3().bridge$getLevelRenderer().bridge$hasDestroyProgress(var3.bridge$getX(), var3.bridge$getY(), var3.bridge$getZ())) {
         return true;
      }

      Vec3Bridge var4 = ThreadModuleDump63.method13().bridge$getCameraPos();
      if (this.field13 && !var1.bridge$shouldRender(var2, var4)) {
         return true;
      }

      FogSource var5 = this.field3.method28();
      if (TurboEngineManager.method22(var5) && !PathSearchContext.method7(Bridge.method42())) {
         if (this.field3.method4()) {
            return false;
         }

         float var6 = PathSearchContext.method9(Bridge.method42());
         double var7 = this.method14(var4, var2);
         if (var7 <= var6 * var6) {
            return false;
         }
      }

      return true;
   }

   public double method14(Vec3Bridge var1, HitcolorExtension var2) {
      Vector3iBridge var3 = var2.bridge$getBlockPos();
      return var1.method4(var3.bridge$getX() + 0.5F, var3.bridge$getY() + 0.5F, var3.bridge$getZ() + 0.5F);
   }

   public boolean method15(Horsestats20Extension var1, Horsestats20Extension var2) {
      return ThreadModuleDump63.method3().bridge$getLevelRenderer().bridge$isInViewDistance(var2, var1);
   }

   protected void method16(@Nullable Void var1, Horsestats20Extension var2, Vector3iBridge var3, Map<RenderLayerBridge, Bridge4Extension> var4, List<Object> var5) {
      Itemcounter6Extension var6 = ThreadModuleDump63.method8();
      Collection var7 = var6.bridge$getBlockEntities(var2.bridge$getX(), var2.bridge$getZ());
      if (!var7.isEmpty()) {
         Bridge2_31 var8 = ThreadModuleDump63.method3().bridge$getBlockEntityRenderDispatcher();
         SpriteSource var9 = this.field3.method23();
         Bridge17Extension var10 = Bridge.method8().method76(var2x -> var9.method7(var4, var2x));
         Vec3Bridge var11 = ThreadModuleDump63.method13().bridge$getCameraPos();
         float var12 = Float.MAX_VALUE;

         for (HitcolorExtension var14 : new ArrayList(var7)) {
            if (var14 != null) {
               Vector3iBridge var15 = var14.bridge$getBlockPos();
               if (Horsestats20Extension.method9(var15, var2)) {
                  float var16 = var14.bridge$minimumTurboDistance();
                  if (var14.lunar$supportsTurbo() && var14.bridge$canTurbo() && (var16 == 0.0F || !(this.method14(var11, var14) < var16))) {
                     TileEntityRendererBridge var17 = var8.bridge$getBlockEntityRenderer(var14);
                     if (var17 != null && !this.method13(var17, var14)) {
                        var9.method5();
                        this.method18(var10, var8, var17, var14);
                        var9.method6();
                        var14.bridge$setTurbo(true);
                        Bridge2_17 var18 = var6.method2(var15);
                        Itemcounter_4 var19 = var18.bridge$getBlockShape(var6, var15);
                        if (var19 == null) {
                           var19 = Bridge.method8().method72(AxisAlignedBBBridge.method1());
                        }

                        var5.add(var19.bridge$move(var15.bridge$getX(), var15.bridge$getY(), var15.bridge$getZ()));
                        if (var16 != 0.0F && var16 < var12) {
                           var12 = var16;
                        }
                     } else {
                        var14.bridge$setTurbo(false);
                     }
                  } else {
                     var14.bridge$setTurbo(false);
                  }
               }
            }
         }

         if (var12 != Float.MAX_VALUE) {
            this.field12.put(new TurboEntityCost(var2, var12), EventClientTick.field1 + 5);
         }

         if (ThreadModuleDump63.MC_VERSION >= 39) {
            try {
               ((AutoCloseable)var10).close();
            } catch (Exception var20) {
            }
         }
      }
   }

   protected void method17(Horsestats20Extension var1) {
      super.method15(var1);
      this.field12.removeLong(new TurboEntityCost(var1, 0.0F));
   }

   private void method18(Bridge17Extension var1, Bridge2_31 var2, TileEntityRendererBridge var3, HitcolorExtension var4) {
      Bridge5_16 var5 = Bridge.method8().method61();
      var5.bridge$pushPose();
      Vector3iBridge var6 = var4.bridge$getBlockPos();
      var5.bridge$translate(
         Horsestats20Extension.method18(var6.bridge$getX()),
         Horsestats20Extension.method18(var6.bridge$getY()),
         Horsestats20Extension.method18(var6.bridge$getZ())
      );
      var2.bridge$render(var3, var4, 1.0F, var5, var1);
      var5.bridge$popPose();
   }

   public static boolean method19() {
      return FeatureFlag.TURBO_BLOCK_ENTITIES.isEnabled();
   }

   public static boolean method20() {
      TurboEngineManager var0 = ThreadModuleDump63.method4().method89();
      return var0.method6() && var0.method27().isRecording();
   }

   @Generated
   public void method21(boolean var1) {
      this.field13 = var1;
   }

   @Generated
   public void method22(int var1) {
      this.field14 = var1;
   }
}
