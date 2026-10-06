package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge17Extension;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge2_5;
import com.moonsworth.lunar.bridge.Bridge4Extension;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemFrameEntityBridge;
import com.moonsworth.lunar.bridge.Bridge_38;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.bridge.itemcounter.ItemcounterType2;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.config.PerformanceSettings;
import com.moonsworth.lunar.client.render.turbo.FragDataFactory;
import com.moonsworth.lunar.client.render.turbo.EntityFragDataFactory;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.render.turbo.FragData;
import com.moonsworth.lunar.client.render.turbo.PathSearchContext;
import com.moonsworth.lunar.client.render.turbo.SectionBatchGroup;
import com.moonsworth.lunar.client.render.texture.SpriteSource;
import com.moonsworth.lunar.client.render.turbo.EntityBatchType;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.event.combat.ProjectileBaseEvent;
import com.moonsworth.lunar.client.event.entity.EventEntityRemoval;
import com.moonsworth.lunar.client.event.player.GameModeChangeEvent;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.event.render.EventFogSetup;
import com.moonsworth.lunar.client.event.entity.EventEntityWorldJoin;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunkLifecycle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEntityPos;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventMapUpdateLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

@Annotation2(min = 8)
public class TurboEntityRecorder extends com.moonsworth.lunar.client.render.turbo.TurboSectionCollector<Horsestats20Extension, Void> {
   private final TurboSectionCache field9 = new TurboSectionCache();
   private int field10;

   public TurboEntityRecorder(TurboEngineManager var1) {
      super(var1);
      this.handle(EventPlayerRemoval.class, var1x -> this.method12(var1x.method1()));
      this.handle(ProjectileBaseEvent.EventProjectileRemoval.class, var1x -> this.method12(var1x.IIHCRIOCIOCROOIRCHHORRORIOOCRC()));
      this.handle(EventEntityRemoval.class, var1x -> this.method12(var1x.method1()));
      this.handle(EventEntityWorldJoin.class, var1x -> {
         if (var1x.field1.lunar$supportsTurbo()) {
            this.field9.method3(var1x.field1, 0);
         }
      });
      this.handle(EventChunkLifecycle.EventChunkUnloaded.class, var1x -> this.method13(var1x.IIOHICICIRRRHOCCIOORRHHHIHHICR()));
      this.handle(EventMapUpdateLegacy.class, var1x -> {
         if (var1x.method2()) {
            int var2x = var1x.getId();

            for (BridgeExtension var4 : ThreadModuleDump63.method3().bridge$getWorld().bridge$getEntities()) {
               if (var4.bridge$isTurbo() && var4 instanceof ItemFrameEntityBridge var5) {
                  var5.bridge$getFramedMapId().ifPresent(var3 -> {
                     if (var2x == var3) {
                        this.field9.method3(var4, 0);
                        this.method13(var4.CCHCORHCRHOCHHHCRHRCIHHIROHRCO(), false);
                     }
                  });
               }
            }
         }
      });
      this.handle(GameModeChangeEvent.class, var1x -> {
         if (var1x.method1() == ItemcounterType2.SPECTATOR) {
            this.clear();
         }
      });
      this.handle(EventEntityPos.class, var1x -> {
         Horsestats20Extension var2x = var1x.method1();
         if (this.OICORIRRCCHHRHRICICOOHHRRHOORR.containsKey(var2x)) {
            this.method13(var2x, false);
         }
      });
      PerformanceSettings var2 = Client.method109().method41().method7();
      this.field10 = var2.method18().get();
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
   public FragDataFactory<BridgeExtension> method2() {
      return EntityFragDataFactory.field1;
   }

   @Override
   public void clear() {
      super.clear();
      this.field9.clear();
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 != null) {
         for (BridgeExtension var3 : var1.bridge$getEntities()) {
            if (var3.bridge$isTurbo()) {
               var3.bridge$setTurbo(false);
            }
         }
      }
   }

   @Override
   public void method3() {
      this.clear();
   }

   @Override
   public void method4(List<String> var1) {
      var1.add("[LC Turbo Entities] Affected Entities: " + this.field10);
      var1.add("[LC Turbo Entities] Affected Sections: " + this.method7());
      var1.add("[LC Turbo Entities] Render Types: " + this.CHRRCRIHOCCRIIRRCHOOCHOIRROHIO.size());
   }

   @Override
   public List<Bridge_38> method5(Predicate<Bridge_38> var1) {
      ArrayList var2 = new ArrayList();
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var3 != null) {
         for (BridgeExtension var5 : var3.bridge$entitiesForRendering()) {
            if (var1.test(var5)) {
               var2.add(var5);
            }
         }
      }

      return var2;
   }

   public EventFogSetup.FogSource method12() {
      return this.field3.method28();
   }

   protected boolean method8(@Nullable Void var1) {
      EventFogSetup.FogSource var2 = this.field3.method28();
      if (var2 == EventFogSetup.FogSource.WATER || TurboEngineManager.method22(var2) && !PathSearchContext.method7(Bridge.method42())) {
         for (int var3 = 0; var3 < this.field10 && !this.field7.isEmpty(); var3++) {
            this.method14(var1, (Horsestats20Extension)this.field7.removeFirst());
         }

         return false;
      } else {
         return true;
      }
   }

   protected void method9(@Nullable Void var1, Horsestats20Extension var2, Vector3iBridge var3, Map<RenderLayerBridge, Bridge4Extension> var4, List<Object> var5) {
      Bridge2_43 var6 = ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher();
      List var7 = ThreadModuleDump63.method8()
         .bridge$getEntities(
            var2.method25(),
            var2x -> var2x.lunar$supportsTurbo() && this.field9.method4(this, var2x) && Horsestats20Extension.method9(var2x.bridge$getBlockPos(), var2)
         );
      if (var7.isEmpty()) {
         this.field9.method7(var2, false);
      } else {
         SpriteSource var8 = this.field3.method23();

         for (BridgeExtension var10 : new ArrayList(var7)) {
            var8.method5();
            this.method10(var8, var3, var6, var10, var4);
            var8.method6();
         }

         var5.addAll(var7);
      }
   }

   private void method10(SpriteSource var1, Vector3iBridge var2, Bridge2_43 var3, BridgeExtension var4, Map<RenderLayerBridge, Bridge4Extension> var5) {
      Bridge5_16 var6 = Bridge.method8().method61();
      var6.bridge$pushPose();
      var6.bridge$translate(var4.bridge$getPosX() - var2.bridge$getX(), var4.bridge$getPosY() - var2.bridge$getY(), var4.bridge$getPosZ() - var2.bridge$getZ());
      Bridge17Extension var7 = Bridge.method8().method76(var2x -> var1.method7(var5, var2x));
      var3.bridge$render(
         var4, 0.0, 0.0, 0.0, (float)var4.bridge$getRotationYaw(), 1.0F, var6, var7, var3.bridge$getRenderer(var4).bridge$getPackedLightCoords(var4, 1.0F)
      );
      if (ThreadModuleDump63.MC_VERSION >= 39) {
         try {
            ((AutoCloseable)var7).close();
         } catch (Exception var9) {
         }
      }

      var6.bridge$popPose();
   }

   protected void method11(Horsestats20Extension var1, boolean var2) {
      this.field9.method7(var1, true);
   }

   private void method12(BridgeExtension var1) {
      if (var1.lunar$supportsTurbo()) {
         this.field9.method2(var1);
         if (!var1.bridge$isAlive() && var1.bridge$isTurbo()) {
            this.field9.method6(this, var1.CCHCORHCRHOCHHHCRHRCIHHIROHRCO(), var1, false, true);
         }
      }
   }

   private void method13(Itemcounter2 var1) {
      this.field9.method1(var1);
      Iterator var2 = this.CHRRCRIHOCCRIIRRCHOOCHOIRROHIO.entrySet().iterator();

      while (var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         Map var4 = (Map)var3.getValue();
         Iterator var5 = var4.entrySet().iterator();

         while (var5.hasNext()) {
            Entry var6 = (Entry)var5.next();
            Horsestats20Extension var7 = (Horsestats20Extension)var6.getKey();
            if (var1.bridge$getX() == var7.bridge$getX() && var1.bridge$getZ() == var7.bridge$getZ()) {
               ((Bridge2_5)var6.getValue()).bridge$close();
               var5.remove();
            }
         }

         if (var4.isEmpty()) {
            var2.remove();
         }
      }

      this.field7.removeIf(var1x -> var1.bridge$getX() == var1x.bridge$getX() && var1.bridge$getZ() == var1x.bridge$getZ());

      for (int var8 = var1.bridge$getMinSection(); var8 < var1.bridge$getMaxSection(); var8++) {
         Horsestats20Extension var9 = Horsestats20Extension.method2(var1.bridge$getX(), var8, var1.bridge$getZ());
         ((SectionBatchGroup)this.field8).method5(var9);
         FragData var10 = (FragData)this.OICORIRRCCHHRHRICICOOHHRRHOORR.remove(var9);
         if (var10 != null) {
            this.field10 = this.field10 - var10.getCount();
         }
      }
   }

   public static boolean method14() {
      return FeatureFlag.TURBO_ENTITIES.isEnabled();
   }

   @Generated
   public TurboSectionCache method15() {
      return this.field9;
   }

   @Generated
   public int method17() {
      return this.field10;
   }

   @Generated
   public void method17(int var1) {
      this.field10 = var1;
   }
}
