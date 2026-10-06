package com.moonsworth.lunar.client.config.option.trait;

import com.moonsworth.lunar.client.util.ThreadModuleDump44;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.trait.BuilderTraitType;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.TraitHost;
import com.moonsworth.lunar.client.config.option.trait.TraitSnapshot;

public interface TraitMutator {
   @Nullable
   <TRAIT> TRAIT method1(TraitType<TRAIT> var1, @Nullable TRAIT var2);

   @Nullable
   default <TRAIT> TRAIT method2(TraitType<TRAIT> var1, ThreadModuleDump44<TRAIT> var2) {
      return this.method1(var1, (TRAIT)var2.build());
   }

   @Nullable
   default <TRAIT, B extends ThreadModuleDump44<TRAIT>> TRAIT method3(BuilderTraitType<TRAIT, B> var1, Consumer<B> var2) {
      ThreadModuleDump44 var3 = var1.method1();
      var2.accept(var3);
      return this.method1(var1, (TRAIT)var3.build());
   }

   @Nullable
   <TRAIT> TRAIT method4(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends @NotNull TRAIT> var2);

   <TRAIT> TRAIT method5(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends @NotNull TRAIT> var2);

   @Nullable
   <TRAIT> TRAIT method6(TraitType<TRAIT> var1, BiFunction<TraitType<TRAIT>, ? super @Nullable TRAIT, ? extends @Nullable TRAIT> var2);

   @Nullable
   <TRAIT> TRAIT method7(TraitType<? extends TRAIT> var1);

   void method8(TraitSnapshot var1);

   void method9(TraitContainer var1);

   default void method10(TraitHost var1) {
      this.method9(var1.method2());
   }
}
