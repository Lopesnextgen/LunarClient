package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.trait.TraitHost;
import com.moonsworth.lunar.client.config.option.trait.TraitMutator;
import com.moonsworth.lunar.client.util.Annotation5;

public interface Framework7Extension extends JsonPersistable, TraitHost, TraitMutator {
   @Annotation5
   String getId();

   default boolean isEnabled() {
      ModEnabledState var1 = (ModEnabledState)this.method1(Framework.field6);
      return var1 == null || var1.isEnabled();
   }

   default boolean method1() {
      Framework11 var1 = (Framework11)this.method1(Framework.field19);
      return var1 == null ? this.isEnabled() : this.isEnabled() && var1.method2();
   }

   default void updateEnabled() {
   }

   default void method2(RootSettingsAssembler var1) {
   }

   void method3(boolean var1);

   default void method4() {
   }

   void method5();

   default void method6() {
   }

   default boolean method7() {
      return true;
   }

   default boolean method8() {
      return false;
   }

   default String method9(String var1, Object... items) {
      ModDetails var3 = (ModDetails)this.method1(Framework.field13);
      return var3 == null ? var1 : var3.method5(var1, items);
   }

   JsonElement method10();
}
