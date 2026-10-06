package com.moonsworth.lunar.client.util.raytrace;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.LightTextureBridge;
import com.moonsworth.lunar.bridge.EntityRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.BiomeHitResult;
import com.moonsworth.lunar.bridge.horsestats.ChunkHitResult;
import com.moonsworth.lunar.bridge.horsestats.SprayHitResult;
import com.moonsworth.lunar.bridge.horsestats.ClientEntityHitResult;
import com.moonsworth.lunar.bridge.horsestats.EntityHitResult;
import com.moonsworth.lunar.bridge.horsestats.LightHitResult;
import com.moonsworth.lunar.bridge.horsestats.CosmeticHitResult;
import com.moonsworth.lunar.bridge.horsestats.Horsestats_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.cosmetics.SprayPlacement;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.util.raytrace.CosmeticRaycastContext;
import com.moonsworth.lunar.client.util.raytrace.BlockRaycastContext;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.util.raytrace.Chest2;

public class Raycaster<T, O, I, H extends Horsestats_3<H, ? extends H>, S extends com.moonsworth.lunar.client.util.raytrace.RaycastContext> {
   public static final Raycaster<Itemcounter6, BlockStateBridge, Vec3iBridge, MissResult, BlockRaycastContext> field1 = new Raycaster<>(
      Raycaster.Type.BLOCK,
      MissResult::method9,
      Itemcounter6::method2,
      (arg0, arg1, arg2, arg3) -> (H)arg3.bridge$clip(arg0, arg2, arg1.method4(), arg1.method5())
   );
   public static final Raycaster<Itemcounter6, BlockStateBridge, Vec3iBridge, MissResult, BlockRaycastContext> field2 = new Raycaster<>(
      Raycaster.Type.BLOCK, MissResult::method9, Itemcounter6::method2, (arg0, arg1, arg2, arg3) -> {
         MissResult horsestatshandler4 = arg3.bridge$clip(arg0, arg2, arg1.method4(), arg1.method5());
         if (horsestatshandler4 == null || horsestatshandler4.CCCOHROOIHRCCROOICCOIROHHIHROI()) {
            horsestatshandler4 = MissResult.method8(arg2);
         }

         return (H)horsestatshandler4;
      }
   );
   public static final Raycaster<Itemcounter6, Bridge3_23, Vec3iBridge, MissResult, BlockRaycastContext> field3 = new Raycaster<>(
      Raycaster.Type.BLOCK,
      MissResult::method9,
      Itemcounter6::method4,
      (arg0, arg1, arg2, arg3) -> (H)arg3.bridge$clip(arg0, arg2, arg1.method4(), arg1.method5())
   );
   public static final Raycaster<Itemcounter6, Bridge3_23, Vec3iBridge, MissResult, BlockRaycastContext> field4 = new Raycaster<>(
      Raycaster.Type.BLOCK, MissResult::method9, Itemcounter6::method4, (arg0, arg1, arg2, arg3) -> {
         MissResult horsestatshandler4 = arg3.bridge$clip(arg0, arg2, arg1.method4(), arg1.method5());
         if (horsestatshandler4 == null || horsestatshandler4.CCCOHROOIHRCCROOICCOIROHHIHROI()) {
            horsestatshandler4 = MissResult.method8(arg2);
         }

         return (H)horsestatshandler4;
      }
   );
   public static final Raycaster<Itemcounter6, Integer, Vec3iBridge, LightHitResult, BlockRaycastContext> field5 = new Raycaster<>(
      Raycaster.Type.BLOCK,
      LightHitResult::method5,
      Itemcounter6::bridge$getPackedLight,
      (arg0, arg1, arg2, arg3) -> (H)LightHitResult.method2(arg2, com.moonsworth.lunar.bridge.horsestats.LightHitResult.Type.PACKED_LIGHT, arg3)
   );
   public static final Raycaster<Itemcounter6, Integer, Vec3iBridge, LightHitResult, BlockRaycastContext> field6 = new Raycaster<>(
      Raycaster.Type.BLOCK,
      LightHitResult::method5,
      (arg0, arg1) -> LightTextureBridge.method2(arg0.bridge$getPackedLight(arg1)) / 16,
      (arg0, arg1, arg2, arg3) -> (H)LightHitResult.method2(arg2, com.moonsworth.lunar.bridge.horsestats.LightHitResult.Type.BLOCK_LIGHT, arg3)
   );
   public static final Raycaster<Itemcounter6, Integer, Vec3iBridge, LightHitResult, BlockRaycastContext> field7 = new Raycaster<>(
      Raycaster.Type.BLOCK,
      LightHitResult::method5,
      (arg0, arg1) -> LightTextureBridge.method1(arg0.bridge$getPackedLight(arg1)) / 16,
      (arg0, arg1, arg2, arg3) -> (H)LightHitResult.method2(arg2, com.moonsworth.lunar.bridge.horsestats.LightHitResult.Type.SKY_LIGHT, arg3)
   );
   public static final Raycaster<Itemcounter6, BiomeBridge, Vec3iBridge, BiomeHitResult, BlockRaycastContext> field8 = new Raycaster<>(
      Raycaster.Type.BLOCK, BiomeHitResult::method5, Itemcounter6::method1, (arg0, arg1, arg2, arg3) -> (H)BiomeHitResult.method2(arg2, arg3)
   );
   public static final Raycaster<Itemcounter6, BridgeExtension, BridgeExtension, EntityHitResult, com.moonsworth.lunar.client.util.raytrace.RaycastContext> field9 = new Raycaster<>(
      Raycaster.Type.ENTITY, EntityHitResult::method6, (arg0, arg1) -> arg1, (arg0, arg1, arg2, arg3) -> (H)EntityHitResult.method3(arg2)
   );
   public static final Raycaster<Itemcounter6, ChunkBridge, ChunkBridge, ChunkHitResult, com.moonsworth.lunar.client.util.raytrace.RaycastContext> field10 = new Raycaster<>(
      Raycaster.Type.CHUNK, ChunkHitResult::method5, (arg0, arg1) -> arg1, (arg0, arg1, arg2, arg3) -> (H)ChunkHitResult.method2(arg2)
   );
   public static final Raycaster<Bridge5_11, CosmeticHitResult, CosmeticHitResult, CosmeticHitResult, CosmeticRaycastContext> field11 = new Raycaster<>(
      Raycaster.Type.COSMETIC, CosmeticHitResult::method5, (arg0, arg1) -> arg1, (arg0, arg1, arg2, arg3) -> (H)arg3
   );
   public static final Raycaster<List<? extends SprayPlacement>, SprayHitResult, SprayHitResult, SprayHitResult, com.moonsworth.lunar.client.util.raytrace.RaycastContext> field12 = new Raycaster<>(
      Raycaster.Type.SPRAY, SprayHitResult::method5, (arg0, arg1) -> arg1, (arg0, arg1, arg2, arg3) -> (H)arg3
   );
   public static final Raycaster<Set<EntityRenderStateBridge>, ClientEntityHitResult, ClientEntityHitResult, ClientEntityHitResult, com.moonsworth.lunar.client.util.raytrace.RaycastContext> field13 = new Raycaster<>(
      Raycaster.Type.CLIENT_ENTITY, ClientEntityHitResult::method6, (arg0, arg1) -> arg1, (arg0, arg1, arg2, arg3) -> (H)arg3
   );
   private final Raycaster.Type field14;
   private final Supplier<H> field15;
   private final BiFunction<T, I, O> field16;
   private final Raycaster.Extension<T, O, I, H, S> field17;

   public Ray<T, I, H, S> method1(
      Vec3Bridge horsestats151, Vec3Bridge horsestats152, final S value3, @Nullable final BiPredicate<I, O> bipredicate4, @Nullable final Raycaster.Extension<T, O, I, H, S> extension5
   ) {
      return new FixedRay<T, O, I, H, S>(this, horsestats151, horsestats152) {
         @Nullable
         @Override
         public S method3() {
            return (S)value3;
         }

         @Override
         public BiFunction<T, I, H> method6() {
            return extension5 != null ? (arg2x, arg3xx) -> {
               Object obj4x = this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method5().apply(arg2x, arg3xx);
               return extension5.create(arg2x, this, arg3xx, obj4x);
            } : (arg2x, arg3xx) -> {
               Object obj4x = this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method5().apply(arg2x, arg3xx);
               return bipredicate4.test(arg3xx, obj4x) ? this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method6().create(arg2x, this, arg3xx, obj4x) : null;
            };
         }
      };
   }

   public Ray<T, I, H, S> method2(
      Supplier<RaySegment> supplier1, final S value2, @Nullable final BiPredicate<I, O> bipredicate3, @Nullable final Raycaster.Extension<T, O, I, H, S> extension4
   ) {
      return new DynamicRay<T, O, I, H, S>(this, supplier1) {
         @Nullable
         @Override
         public S method3() {
            return (S)value2;
         }

         @Override
         public BiFunction<T, I, H> method6() {
            return extension4 != null ? (arg2xx, arg3xx) -> {
               Object obj4x = this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method5().apply(arg2xx, arg3xx);
               return extension4.create(arg2xx, this, arg3xx, obj4x);
            } : (arg2xx, arg3xx) -> {
               Object obj4x = this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method5().apply(arg2xx, arg3xx);
               return bipredicate3.test(arg3xx, obj4x) ? this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method6().create(arg2xx, this, arg3xx, obj4x) : null;
            };
         }
      };
   }

   @Generated
   public Raycaster.Type method3() {
      return this.field14;
   }

   @Generated
   public Supplier<H> method4() {
      return this.field15;
   }

   @Generated
   public BiFunction<T, I, O> method5() {
      return this.field16;
   }

   @Generated
   public Raycaster.Extension<T, O, I, H, S> method6() {
      return this.field17;
   }

   @Generated
   public Raycaster(Raycaster.Type type1, Supplier<H> supplier2, BiFunction<T, I, O> function3, Raycaster.Extension<T, O, I, H, S> extension4) {
      this.field14 = type1;
      this.field15 = supplier2;
      this.field16 = function3;
      this.field17 = extension4;
   }

   @FunctionalInterface
   public interface Extension<T, O, I, H extends Horsestats_3<H, ? extends H>, E extends com.moonsworth.lunar.client.util.raytrace.RaycastContext> {
      H create(T value1, Ray<T, I, H, E> sextension2, I value3, O value4);
   }

   public enum Type implements OptionEnumValue {
      BLOCK((arg0, arg1) -> Chest2.rayTraceBlocks((Itemcounter6)arg0, (Ray<Itemcounter6, Vec3iBridge, Horsestats_3<?, ?>, BlockRaycastContext>)arg1)),
      ENTITY(
         (arg0, arg1) -> Chest2.rayTraceEntities(
            (Itemcounter6)arg0, (Ray<Itemcounter6, BridgeExtension, EntityHitResult, com.moonsworth.lunar.client.util.raytrace.RaycastContext>)arg1
         )
      ),
      CHUNK(
         (arg0, arg1) -> Chest2.rayTraceChunks(
            (Itemcounter6)arg0, (Ray<Itemcounter6, ChunkBridge, ChunkHitResult, com.moonsworth.lunar.client.util.raytrace.RaycastContext>)arg1
         )
      ),
      COSMETIC((arg0, arg1) -> Chest2.rayTraceCosmetics((Bridge5_11)arg0, (Ray<Bridge5_11, CosmeticHitResult, CosmeticHitResult, CosmeticRaycastContext>)arg1)),
      SPRAY(
         (arg0, arg1) -> Chest2.rayTraceSprays(
            (List<? extends SprayPlacement>)arg0,
            (Ray<List<? extends SprayPlacement>, SprayHitResult, SprayHitResult, com.moonsworth.lunar.client.util.raytrace.RaycastContext>)arg1
         )
      ),
      CLIENT_ENTITY(
         (arg0, arg1) -> Chest2.rayTraceClientEntities(
            (Collection<EntityRenderStateBridge>)arg0,
            (Ray<Collection<EntityRenderStateBridge>, ClientEntityHitResult, ClientEntityHitResult, com.moonsworth.lunar.client.util.raytrace.RaycastContext>)arg1
         )
      );

      private final BiFunction<Object, Ray<?, ?, ?, ?>, Horsestats_3<?, ?>> traverseMethod;

      public String id() {
         return this.name().toLowerCase(Locale.ROOT);
      }

      public <T, H extends Horsestats_3<H, ? extends H>, R extends Ray<T, ?, H, ?>> H traverse(T value1, R value2) {
         return (H)this.traverseMethod.apply(value1, value2);
      }

      @Generated
      Type(BiFunction<Object, Ray<?, ?, ?, ?>, Horsestats_3<?, ?>> function3) {
         this.traverseMethod = function3;
      }
   }
}
