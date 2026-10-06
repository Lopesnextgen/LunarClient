package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.framework.loading.LoadableHandler;
import com.moonsworth.lunar.client.framework.loading.LoadingStage;
import com.moonsworth.lunar.client.framework.loading.LoadableResource;
import com.moonsworth.lunar.client.render.texture.SpriteHandler;
import com.moonsworth.lunar.client.render.texture.SpriteTask;
import com.moonsworth.lunar.client.render.texture.SpriteSource;
import com.moonsworth.lunar.client.render.turbo.TurboBlockEntityRecorder;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod.OptimizationdebugmodType;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.render.EventFogSetup.Data;
import com.moonsworth.lunar.client.event.render.EventFogSetup.EventFogDistance;
import com.moonsworth.lunar.client.event.render.EventFogSetup.FogSource;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase.EventRenderTickBegin;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.AlertUpdateEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.PerformanceSettings;

@Annotation2(min = 8)
public class TurboEngineManager implements LoadableHandler, EventRegistrar {
   public static final boolean field1 = true;
   public static final boolean field2 = true;
   public static final boolean field3 = true;
   public static final boolean field4 = true;
   public static final boolean field5 = true;
   private final Map<Object, Map<Class<Highlight>, Consumer<Highlight>>> field6 = new HashMap<>();
   private final Map<BatchEntityType, TurboBatchRecorder> field7 = new HashMap<>();
   @Nullable
   private SpriteSource field8;
   private final LoadingStage field9 = new LoadingStage();
   private int field10;
   private com.moonsworth.lunar.client.render.turbo.TurboEntityRecorder field11;
   private TurboBlockEntityRecorder field12;
   private FogSource field13;
   private Vec3Bridge field14;
   private boolean field15;

   public TurboEngineManager() {
      if (!method21()) {
         this.field8 = null;
      } else {
         this.setEnabled(true);
      }
   }

   public void setEnabled(boolean var1) {
      var1 &= Bridge.method5().isEmpty();
      if (var1) {
         if (this.field8 != null) {
            return;
         }

         if (ThreadModuleDump63.MC_VERSION >= 33) {
            this.field8 = new SpriteTask();
         } else {
            this.field8 = (SpriteSource)com.moonsworth.lunar.client.fishing.Fishing.method3(Fishing2Extension.class, SpriteHandler::new, SpriteTask::new);
         }

         if ((Boolean)ThreadModuleDump63.method4().method41().method7().method17().get()) {
            this.method5(true);
         }

         if ((Boolean)ThreadModuleDump63.method4().method41().method7().method19().get()) {
            this.method7(true);
         }

         this.handle(DisconnectEvent.class, var1x -> this.clear());
         if (ThreadModuleDump63.MC_VERSION <= 25) {
            this.handle(Data.class, var1x -> this.field13 = var1x.method1());
         } else {
            this.handle(EventFogDistance.class, var1x -> this.field13 = var1x.method1());
         }

         this.handle(EventRenderTickBegin.class, var1x -> {
            Vec3Bridge var2x = ThreadModuleDump63.method13().bridge$getCameraPos();
            if (this.field14 != null && this.field14.equals(var2x)) {
               this.field15 = false;
            } else {
               this.field14 = var2x;
               this.field15 = true;
            }
         });
         this.handle(EventClientTick.class, var1x -> {
            this.method8();
            this.field7.forEach(this.field9::method1);
         });
         this.handle(AlertUpdateEvent.class, var1x -> ThreadModuleDump37.method19(() -> {
            this.method8();
            this.field7.forEach((var0, var1xx) -> var1xx.method3());
         }));
      } else {
         this.method5(false);
         this.method7(false);
         this.field8 = null;
         this.method20();
      }
   }

   public void method1() {
      this.field10++;
   }

   public boolean method2() {
      return this.field15;
   }

   public boolean method3() {
      return this.field11 != null;
   }

   public boolean method4() {
      com.moonsworth.lunar.client.mod.render.fog.Fog var1 = ThreadModuleDump63.method4().method40().method17();
      return var1.isEnabled() && (Float)var1.method14().get() == 0.0F;
   }

   public void method5(boolean var1) {
      if (this.field8 != null) {
         if (var1 && (PathSearchContext.method2() || !OptimizationdebugmodType.ALLOW_TURBO_ENTITIES.isEnabled())) {
            var1 = false;
         }

         if (this.field11 != null != var1) {
            if (com.moonsworth.lunar.client.render.turbo.TurboEntityRecorder.method14()) {
               if (var1) {
                  this.field11 = new com.moonsworth.lunar.client.render.turbo.TurboEntityRecorder(this);
                  this.method17(this.field11);
               } else {
                  this.method18(this.field11);
                  this.field11 = null;
               }

               boolean var2 = var1;
               com.moonsworth.lunar.client.fishing.Fishing.method2(com.moonsworth.lunar.client.fishing.rewindhandlers.Fishing2Extension.class)
                  .ifPresent(var1x -> var1x.lunar$onTurboEntityStateChange(var2));
            }
         }
      }
   }

   public boolean method6() {
      return this.field12 != null;
   }

   public void method7(boolean var1) {
      if (this.field8 != null) {
         if (var1 && (PathSearchContext.method2() || !OptimizationdebugmodType.ALLOW_TURBO_BLOCK_ENTITIES.isEnabled())) {
            var1 = false;
         }

         if (this.field12 != null != var1) {
            if (var1) {
               if (!TurboBlockEntityRecorder.method19()) {
                  return;
               }

               this.field12 = new TurboBlockEntityRecorder(this);
               this.method17(this.field12);
               this.field12.method3();
            } else {
               this.method18(this.field12);
               this.field12 = null;
            }

            boolean var2 = var1;
            com.moonsworth.lunar.client.fishing.Fishing.method2(com.moonsworth.lunar.client.fishing.rewindhandlers.Fishing2Extension.class)
               .ifPresent(var1x -> var1x.lunar$onTurboBlockEntityStateChange(var2));
         }
      }
   }

   private void method8() {
      if (this.field8 != null) {
         PerformanceSettings var1 = ThreadModuleDump63.method4().method41().method7();
         this.method5((Boolean)var1.method17().get());
         this.method7((Boolean)var1.method19().get());
      }
   }

   public void method9(BatchEntityType var1, LoadableResource var2, int var3) {
      this.field9.method2(var1, var2, var3);
   }

   public void clear() {
      this.field7.forEach((var0, var1) -> var1.clear());
      this.field9.clear();
      this.field13 = null;
      this.field14 = null;
      this.field15 = true;
   }

   public void method10(BatchEntityType var1) {
      TurboBatchRecorder var2 = this.field7.get(var1);
      if (var2 != null) {
         var2.clear();
      }

      this.field9.method3(var1);
      this.field13 = null;
      this.field14 = null;
      this.field15 = true;
   }

   public void method11(List<String> var1) {
      if (this.field8 != null) {
         var1.add("");
         this.field7.forEach((var1x, var2) -> var2.method4(var1));
      }
   }

   public void method12(BiConsumer<String, String> var1) {
      if (this.field8 != null) {
         ArrayList var2 = new ArrayList();
         this.field7.forEach((var1x, var2x) -> var2x.method4(var2));

         for (String var4 : var2) {
            int var5 = var4.indexOf(58);
            if (var5 >= 0) {
               var1.accept(var4.substring(0, var5 + 1) + " ", var4.substring(var5 + 1).trim());
            } else {
               var1.accept(var4, "");
            }
         }
      }
   }

   @Nullable
   public TurboBatchRecorder method13(BatchEntityType var1) {
      return this.field7.get(var1);
   }

   public <T extends Highlight> void method14(TurboBatchRecorder var1, Class<T> var2, Consumer<T> var3) {
      this.field6.computeIfAbsent(var1, var0 -> new HashMap<>()).put(var2, var3);
      ClientEventBus.method29().method2(var2, var3);
   }

   public <T extends Highlight> void method15(TurboBatchRecorder var1, Class<T> var2, Consumer<T> var3, int var4) {
      this.method14(var1, var2, var3);
   }

   public <T extends Highlight> void handle(Class<T> var1, Consumer<T> var2) {
      this.field6.computeIfAbsent(this, var0 -> new HashMap<>()).put(var1, var2);
      ClientEventBus.method29().method2(var1, var2);
   }

   public <T extends Highlight> void method2(Class<T> var1, Consumer<T> var2, int var3) {
      this.handle(var1, var2);
   }

   @Override
   public void init() {
   }

   @Override
   public void close() {
   }

   private void method17(TurboBatchRecorder var1) {
      this.field7.put(var1.method1(), var1);
   }

   private void method18(TurboBatchRecorder var1) {
      var1.clear();
      this.method19(var1);
      this.field7.remove(var1.method1());
   }

   private void method19(TurboBatchRecorder var1) {
      Map var2 = this.field6.remove(var1);
      if (var2 != null) {
         for (Entry var4 : var2.entrySet()) {
            ClientEventBus.method29().method6((Class)var4.getKey(), (Consumer)var4.getValue());
         }
      }
   }

   private void method20() {
      for (Map var2 : this.field6.values()) {
         for (Entry var4 : var2.entrySet()) {
            ClientEventBus.method29().method6((Class)var4.getKey(), (Consumer)var4.getValue());
         }
      }
   }

   public static boolean method21() {
      return FeatureFlag.TURBO_ENGINE.isEnabled();
   }

   public static boolean method22(@Nullable FogSource var0) {
      return var0 == FogSource.RENDER_DISTANCE || var0 == FogSource.ATMOSPHERIC || var0 == FogSource.DIMENSION || var0 == FogSource.BOSS;
   }

   @Nullable
   @Generated
   public SpriteSource method23() {
      return this.field8;
   }

   @Generated
   public void method24(int var1) {
      this.field10 = var1;
   }

   @Generated
   public int method25() {
      return this.field10;
   }

   @Generated
   public com.moonsworth.lunar.client.render.turbo.TurboEntityRecorder method26() {
      return this.field11;
   }

   @Generated
   public TurboBlockEntityRecorder method27() {
      return this.field12;
   }

   @Generated
   public FogSource method28() {
      return this.field13;
   }

   @Generated
   public Vec3Bridge method29() {
      return this.field14;
   }
}
