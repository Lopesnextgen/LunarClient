package com.moonsworth.lunar.client.util.chest;

import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Bridge_34;
import com.moonsworth.lunar.bridge.Bridge_61;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
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
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_3;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.cosmetics.SprayPlacement;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.util.chest.mixin.ChestHandler;
import com.moonsworth.lunar.client.util.chest.mixin.ChestHandler2;
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

public class SImpl<T, O, I, H extends Horsestats_3<H, ? extends H>, S extends com.moonsworth.lunar.client.util.chest.mixin.Chest> {
   public static final SImpl<Itemcounter6, Bridge2_17, Vector3iBridge, MissResult, ChestHandler2> BLOCK = new SImpl<>(
      SImpl.Type.BLOCK,
      MissResult::method9,
      Itemcounter6::method2,
      (var0, var1, var2, var3) -> (H)var3.bridge$clip(var0, var2, var1.method4(), var1.method5())
   );
   public static final SImpl<Itemcounter6, Bridge2_17, Vector3iBridge, MissResult, ChestHandler2> BLOCK_OR_MISS = new SImpl<>(
      SImpl.Type.BLOCK, MissResult::method9, Itemcounter6::method2, (var0, var1, var2, var3) -> {
         MissResult var4 = var3.bridge$clip(var0, var2, var1.method4(), var1.method5());
         if (var4 == null || var4.CCCOHROOIHRCCROOICCOIROHHIHROI()) {
            var4 = MissResult.method8(var2);
         }

         return (H)var4;
      }
   );
   public static final SImpl<Itemcounter6, Bridge3_23, Vector3iBridge, MissResult, ChestHandler2> BLOCK_AT = new SImpl<>(
      SImpl.Type.BLOCK,
      MissResult::method9,
      Itemcounter6::method4,
      (var0, var1, var2, var3) -> (H)var3.bridge$clip(var0, var2, var1.method4(), var1.method5())
   );
   public static final SImpl<Itemcounter6, Bridge3_23, Vector3iBridge, MissResult, ChestHandler2> BLOCK_AT_OR_MISS = new SImpl<>(
      SImpl.Type.BLOCK, MissResult::method9, Itemcounter6::method4, (var0, var1, var2, var3) -> {
         MissResult var4 = var3.bridge$clip(var0, var2, var1.method4(), var1.method5());
         if (var4 == null || var4.CCCOHROOIHRCCROOICCOIROHHIHROI()) {
            var4 = MissResult.method8(var2);
         }

         return (H)var4;
      }
   );
   public static final SImpl<Itemcounter6, Integer, Vector3iBridge, LightHitResult, ChestHandler2> PACKED_LIGHT = new SImpl<>(
      SImpl.Type.BLOCK,
      LightHitResult::method5,
      Itemcounter6::bridge$getPackedLight,
      (var0, var1, var2, var3) -> (H)LightHitResult.method2(var2, com.moonsworth.lunar.bridge.horsestats.LightHitResult.Type.PACKED_LIGHT, var3)
   );
   public static final SImpl<Itemcounter6, Integer, Vector3iBridge, LightHitResult, ChestHandler2> BLOCK_LIGHT = new SImpl<>(
      SImpl.Type.BLOCK,
      LightHitResult::method5,
      (var0, var1) -> Bridge_34.method2(var0.bridge$getPackedLight(var1)) / 16,
      (var0, var1, var2, var3) -> (H)LightHitResult.method2(var2, com.moonsworth.lunar.bridge.horsestats.LightHitResult.Type.BLOCK_LIGHT, var3)
   );
   public static final SImpl<Itemcounter6, Integer, Vector3iBridge, LightHitResult, ChestHandler2> SKY_LIGHT = new SImpl<>(
      SImpl.Type.BLOCK,
      LightHitResult::method5,
      (var0, var1) -> Bridge_34.method1(var0.bridge$getPackedLight(var1)) / 16,
      (var0, var1, var2, var3) -> (H)LightHitResult.method2(var2, com.moonsworth.lunar.bridge.horsestats.LightHitResult.Type.SKY_LIGHT, var3)
   );
   public static final SImpl<Itemcounter6, Itemcounter_3, Vector3iBridge, BiomeHitResult, ChestHandler2> BIOME = new SImpl<>(
      SImpl.Type.BLOCK, BiomeHitResult::method5, Itemcounter6::method1, (var0, var1, var2, var3) -> (H)BiomeHitResult.method2(var2, var3)
   );
   public static final SImpl<Itemcounter6, BridgeExtension, BridgeExtension, EntityHitResult, com.moonsworth.lunar.client.util.chest.mixin.Chest> ENTITY = new SImpl<>(
      SImpl.Type.ENTITY, EntityHitResult::method6, (var0, var1) -> var1, (var0, var1, var2, var3) -> (H)EntityHitResult.method3(var2)
   );
   public static final SImpl<Itemcounter6, Itemcounter2, Itemcounter2, ChunkHitResult, com.moonsworth.lunar.client.util.chest.mixin.Chest> CHUNK = new SImpl<>(
      SImpl.Type.CHUNK, ChunkHitResult::method5, (var0, var1) -> var1, (var0, var1, var2, var3) -> (H)ChunkHitResult.method2(var2)
   );
   public static final SImpl<Bridge5_11, CosmeticHitResult, CosmeticHitResult, CosmeticHitResult, ChestHandler> COSMETIC = new SImpl<>(
      SImpl.Type.COSMETIC, CosmeticHitResult::method5, (var0, var1) -> var1, (var0, var1, var2, var3) -> (H)var3
   );
   public static final SImpl<List<? extends SprayPlacement>, SprayHitResult, SprayHitResult, SprayHitResult, com.moonsworth.lunar.client.util.chest.mixin.Chest> SPRAY = new SImpl<>(
      SImpl.Type.SPRAY, SprayHitResult::method5, (var0, var1) -> var1, (var0, var1, var2, var3) -> (H)var3
   );
   public static final SImpl<Set<Bridge_61>, ClientEntityHitResult, ClientEntityHitResult, ClientEntityHitResult, com.moonsworth.lunar.client.util.chest.mixin.Chest> CLIENT_ENTITY = new SImpl<>(
      SImpl.Type.CLIENT_ENTITY, ClientEntityHitResult::method6, (var0, var1) -> var1, (var0, var1, var2, var3) -> (H)var3
   );
   private final SImpl.Type type;
   private final Supplier<H> missSupplier;
   private final BiFunction<T, I, O> valueFunction;
   private final SImpl.Extension<T, O, I, H, S> resultFactory;

   public SExtension<T, I, H, S> create(
      Vec3Bridge var1, Vec3Bridge var2, final S var3, @Nullable final BiPredicate<I, O> var4, @Nullable final SImpl.Extension<T, O, I, H, S> var5
   ) {
      return new SBase2<T, O, I, H, S>(this, var1, var2) {
         @Nullable
         @Override
         public S getType() {
            return (S)var3;
         }

         @Override
         public BiFunction<T, I, H> getResultFactory() {
            return var5 != null ? (var2x, var3xx) -> {
               Object var4x = this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method5().apply(var2x, var3xx);
               return var5.create(var2x, this, var3xx, var4x);
            } : (var2x, var3xx) -> {
               Object var4x = this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method5().apply(var2x, var3xx);
               return var4.test(var3xx, var4x) ? this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method6().create(var2x, this, var3xx, var4x) : null;
            };
         }
      };
   }

   public SExtension<T, I, H, S> create(
      Supplier<Chest> var1, final S var2, @Nullable final BiPredicate<I, O> var3, @Nullable final SImpl.Extension<T, O, I, H, S> var4
   ) {
      return new SBase<T, O, I, H, S>(this, var1) {
         @Nullable
         @Override
         public S getType() {
            return (S)var2;
         }

         @Override
         public BiFunction<T, I, H> getResultFactory() {
            return var4 != null ? (var2xx, var3xx) -> {
               Object var4x = this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method5().apply(var2xx, var3xx);
               return var4.create(var2xx, this, var3xx, var4x);
            } : (var2xx, var3xx) -> {
               Object var4x = this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method5().apply(var2xx, var3xx);
               return var3.test(var3xx, var4x) ? this.CHROIOOIRRIRRIRCCHICRIOOCCOHRH().method6().create(var2xx, this, var3xx, var4x) : null;
            };
         }
      };
   }

   @Generated
   public SImpl.Type getType() {
      return this.type;
   }

   @Generated
   public Supplier<H> getMissSupplier() {
      return this.missSupplier;
   }

   @Generated
   public BiFunction<T, I, O> getValueFunction() {
      return this.valueFunction;
   }

   @Generated
   public SImpl.Extension<T, O, I, H, S> getResultFactory() {
      return this.resultFactory;
   }

   @Generated
   public SImpl(SImpl.Type var1, Supplier<H> var2, BiFunction<T, I, O> var3, SImpl.Extension<T, O, I, H, S> var4) {
      this.type = var1;
      this.missSupplier = var2;
      this.valueFunction = var3;
      this.resultFactory = var4;
   }

   @FunctionalInterface
   public interface Extension<T, O, I, H extends Horsestats_3<H, ? extends H>, E extends com.moonsworth.lunar.client.util.chest.mixin.Chest> {
      H create(T var1, SExtension<T, I, H, E> var2, I var3, O var4);
   }

   public enum Type implements OptionEnumValue {
      BLOCK((var0, var1) -> Chest2.rayTraceBlocks((Itemcounter6)var0, (SExtension<Itemcounter6, Vector3iBridge, Horsestats_3<?, ?>, ChestHandler2>)var1)),
      ENTITY(
         (var0, var1) -> Chest2.rayTraceEntities(
            (Itemcounter6)var0, (SExtension<Itemcounter6, BridgeExtension, EntityHitResult, com.moonsworth.lunar.client.util.chest.mixin.Chest>)var1
         )
      ),
      CHUNK(
         (var0, var1) -> Chest2.rayTraceChunks(
            (Itemcounter6)var0, (SExtension<Itemcounter6, Itemcounter2, ChunkHitResult, com.moonsworth.lunar.client.util.chest.mixin.Chest>)var1
         )
      ),
      COSMETIC((var0, var1) -> Chest2.rayTraceCosmetics((Bridge5_11)var0, (SExtension<Bridge5_11, CosmeticHitResult, CosmeticHitResult, ChestHandler>)var1)),
      SPRAY(
         (var0, var1) -> Chest2.rayTraceSprays(
            (List<? extends SprayPlacement>)var0,
            (SExtension<List<? extends SprayPlacement>, SprayHitResult, SprayHitResult, com.moonsworth.lunar.client.util.chest.mixin.Chest>)var1
         )
      ),
      CLIENT_ENTITY(
         (var0, var1) -> Chest2.rayTraceClientEntities(
            (Collection<Bridge_61>)var0,
            (SExtension<Collection<Bridge_61>, ClientEntityHitResult, ClientEntityHitResult, com.moonsworth.lunar.client.util.chest.mixin.Chest>)var1
         )
      );

      private final BiFunction<Object, SExtension<?, ?, ?, ?>, Horsestats_3<?, ?>> traverseMethod;

      @Override
      public String id() {
         return this.name().toLowerCase(Locale.ROOT);
      }

      public <T, H extends Horsestats_3<H, ? extends H>, R extends SExtension<T, ?, H, ?>> H traverse(T var1, R var2) {
         return (H)this.traverseMethod.apply(var1, var2);
      }

      @Generated
      Type(BiFunction<Object, SExtension<?, ?, ?, ?>, Horsestats_3<?, ?>> var3) {
         this.traverseMethod = var3;
      }
   }
}
