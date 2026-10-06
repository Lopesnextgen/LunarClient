package com.moonsworth.lunar.client.framework.mod;

import com.google.common.base.CaseFormat;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.MixinHelper_15;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ModSettingApolloHandler;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.calculator.mixin.Calculator;
import com.moonsworth.lunar.client.framework.feature.Module2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Task;
import com.moonsworth.lunar.client.framework.mod.Alert2Iterator;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.trait.TraitHost;
import com.moonsworth.lunar.client.config.option.trait.TraitContainer;
import com.moonsworth.lunar.client.config.option.trait.MutableTraitHost;
import com.moonsworth.lunar.client.config.option.trait.MutableTraitContainer;
import com.moonsworth.lunar.client.config.option.trait.TraitSnapshot;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.BuilderTraitType;
import com.moonsworth.lunar.client.mod.misc.debug.DynamiclistenerDebugMod;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump44;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.command.MixinNameplate2;

public abstract class AbstractFeature implements Framework7Extension, EventRegistrar {
   public static final boolean field1 = false;
   private static final boolean field2 = false;
   private final Cache<String, Calculator> field3 = CacheBuilder.newBuilder().initialCapacity(4).concurrencyLevel(2).build();
   protected final Bridge5_12 mc;
   protected final Client field4;
   protected final MutableTraitHost field5;
   @Nullable
   private final Module2 field6 = this.IHRCCOIOORRHHIRCRIROCCHICCRHIR() ? new Module2(this) : null;
   private String field7;
   private boolean enabled;

   protected AbstractFeature(boolean var1) {
      this.mc = ThreadModuleDump63.method3();
      this.field4 = Client.method109();
      this.field5 = new MutableTraitContainer(Framework.field22);
      this.field5.method1(Framework.field8, FrameworkType.CONSTRUCTOR);
      this.method1(var1);
      this.method3(Framework.field6)
         .flatMap(ModEnabledState::method1)
         .ifPresent(
            var1x -> var1x.method8(
               var2 -> {
                  this.method7(var2);
                  if (Client.method109() != null) {
                     try {
                        Client.method109()
                           .method84()
                           .<ApolloModuleHandler>method3(ModSettingModule.class)
                           .ifPresent(var3 -> ((ModSettingApolloHandler)var3).method17().method3((ClientOption<?>)var1x, this.getId(), var2));
                     } catch (Throwable var4) {
                        Slayer.method6("Apollo", "Apollo broadcast failed", var4);
                     }
                  }
               }
            )
         );
   }

   protected void method1(boolean var1) {
      this.method1(Framework.field6, ModEnabledState.method6(var1));
      this.method1(Framework.field4, new Alert2Iterator());
      this.method1(Framework.field9, ModSearchIndex.method7());
      this.method1(Framework.field10, Framework12.method9());
      this.method1(Framework.field15, this.method3());
   }

   @NotNull
   public Framework10 method3() {
      return Framework10.method9(this.method28("20x20"), this.method28("52x52"), this::method18, false, 0L, false);
   }

   protected List<Framework7Extension> method9() {
      return ImmutableList.of();
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public final void updateEnabled() {
      Framework4 var1 = (Framework4)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field16);
      if (var1 != null && !var1.<Framework7Extension>method1().isEnabled()) {
         this.enabled = false;
         this.method11();
      } else {
         ModSupport var2 = (ModSupport)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field18);
         if (var2 != null && var2.method2()) {
            KeystrokesType var3 = Highlight3Iterator.method9();
            if (var3 == null || !var2.method3(var3)) {
               this.enabled = false;
               this.method11();
               return;
            }
         }

         Framework7 var6 = (Framework7)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field20);
         if (var6 != null && !var6.method3()) {
            this.enabled = false;
            this.method11();
         } else {
            Alert2 var4 = (Alert2)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
            if (var4 != null) {
               Optional var5 = var4.method3();
               if (var5.isPresent()) {
                  this.enabled = (Boolean)var5.get();
                  this.method11();
                  return;
               }
            }

            this.enabled = Framework7Extension.super.isEnabled();
            this.method11();
         }
      }
   }

   private void method11() {
      if (!this.method2(Framework.field12)) {
         this.method3(Framework.field19).ifPresent(var0 -> var0.method3(false));
      }
   }

   @Override
   public void method5() {
      ModDetails var1 = this.method5(Framework.field13, var1x -> this.method20());
      this.method4(Framework.field8, var0 -> FrameworkType.INITIALIZED);
      if (!this.method2(Framework.field16)
         && var1 != null
         && var1.method4()
         && this.method3(Framework.field6).flatMap(ModEnabledState::method1).isPresent()) {
         this.method5(Framework.field21, var1x -> new FeatureToggleKeybind(this));
      }

      Framework5 var2 = this.method5(Framework.field14, var1x -> Framework5.method6(this::method23, this.OICIRIORCRCOIOIIORROIIIRIRIHHO()));
      var2.method5(this);
      if (var2.method1().isEmpty()) {
         this.method7(Framework.field14);
      }

      this.method4(Framework.field8, var0 -> FrameworkType.OPTIONS_INITIALIZED);
      ModSearchIndex var3 = (ModSearchIndex)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field9);
      if (var3 != null) {
         var3.method3(this);
      }

      List var4 = this.method9();
      if (!var4.isEmpty()) {
         this.method5(Framework.field5, var1x -> AlertExtension.method5(this::method24)).method5(this, var4);
         this.method4(Framework.field8, var0 -> FrameworkType.CHILDREN_INITIALIZED);
      } else {
         this.method7(Framework.field5);
      }

      for (ClientOption var6 : var2.method2()) {
         var6.method8(this::method7);
         if (var6 instanceof AbstractKeybindOption var7) {
            var7.method3(() -> this.method8(var7, true, false));
            var7.method2(var2x -> this.method8(var7, false, var2x));
         }
      }
   }

   protected <T> T method6(String var1, T var2) {
      return this.field6.method1(var1, (T)var2);
   }

   private void method7(Object var1) {
      if (this.IHRCCOIOORRHHIRCRIROCCHICCRHIR()) {
         if (ThreadModuleDump63.method4() != null) {
            Rewind var2 = ThreadModuleDump63.method4().method40().method85();
            if (var2.isRecording()) {
               JsonObject var3 = new JsonObject();
               this.method1(var3);
               var3.addProperty("version", ConfigMigrator.field2);
               var2.method34().method24().put(this.getId(), var3);
            }
         }
      }
   }

   private void method8(AbstractKeybindOption<?> var1, boolean var2, boolean var3) {
      if (this.IHRCCOIOORRHHIRCRIROCCHICCRHIR()) {
         Rewind var4 = ThreadModuleDump63.method4().method40().method85();
         if (var4.isRecording()) {
            JsonObject var5 = new JsonObject();
            this.method1(var5);
            var5.addProperty("version", ConfigMigrator.field2);

            try {
               var4.method34().method16().method9(new Nameplate2Task(this.getId(), var1.getId(), var2, var3), var4.method34().getTick());
            } catch (Exception var7) {
               throw new RuntimeException(var7);
            }
         }
      }
   }

   public String method12() {
      if (this.field7 == null) {
         this.field7 = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, this.getId());
      }

      return this.field7;
   }

   @Override
   public void method3(boolean var1) {
   }

   @Override
   public <T extends Highlight> void handle(Class<T> var1, Consumer<T> var2) {
      this.method2(var1, var2, 100);
   }

   @Override
   public <T extends Highlight> void method1(Class<T> var1, Runnable var2) {
      this.method3(var1, var2, 100);
   }

   @Override
   public <T extends Highlight> void method2(Class<T> var1, Consumer<T> var2, int var3) {
      this.method5(Framework.field12, var0 -> Framework10Extension.method13()).method1(var1, var2, var3);
   }

   @Override
   public <T extends Highlight> void method3(Class<T> var1, Runnable var2, int var3) {
      this.method5(Framework.field12, var0 -> Framework10Extension.method13()).method1(var1, var1x -> var2.run(), var3);
   }

   public void method14(MixinNameplate2 var1) {
      this.method5(Framework.field12, var0 -> Framework10Extension.method13()).method8(var1);
   }

   public void method15(Runnable var1) {
      this.method5(Framework.field12, var0 -> Framework10Extension.method13()).method9(var1);
   }

   public void method16(Runnable var1) {
      this.method5(Framework.field12, var0 -> Framework10Extension.method13()).method10(var1);
   }

   public void method17(Runnable var1) {
      this.method5(Framework.field12, var0 -> Framework10Extension.method13()).method11(var1);
   }

   protected String method18() {
      ModDetails var1 = (ModDetails)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      return var1 == null ? this.getId() : var1.getName();
   }

   protected <T extends DynamicListener> T method19(Class<T> var1) {
      DynamicListener var2 = DynamicListener.method7(var1);
      this.method5(Framework.field12, var0 -> Framework10Extension.method13()).method2(var2);
      DynamiclistenerDebugMod.method5(this, var2);
      return (T)var2;
   }

   protected ModDetails method20() {
      return ModDetails.method7().method11(this);
   }

   @Override
   public void method6() {
      this.field3.invalidateAll();
   }

   @Override
   public String method9(@Annotation(method1 = Annotation.Type.MOD_INFO) String var1, Object... var2) {
      ModDetails var3 = (ModDetails)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      if (var3 == null) {
         return var1;
      }

      try {
         return ((Calculator)this.field3.get(var1, () -> var2 != null && var2.length > 0 ? var3.method6(var1) : Calculator.method4(var3.method5(var1))))
            .method2(var2);
      } catch (ExecutionException var5) {
         throw new RuntimeException(var5);
      }
   }

   protected boolean method23(String var1) {
      return false;
   }

   protected boolean method24(String var1) {
      return false;
   }

   @Override
   public void method1(JsonObject var1) {
      for (JsonPersistable var3 : this.method27(null)) {
         try {
            var3.method1(var1);
         } catch (IOException var5) {
            var5.printStackTrace();
         }
      }
   }

   @Override
   public void load(JsonObject var1) {
      ConfigMigrator.method3(this, var1);
      this.method26(var1, null);
   }

   private void method26(JsonObject var1, @Nullable List<JsonPersistable> var2) {
      int var3 = this.field5.size();
      List var4 = this.method27(var2);

      for (JsonPersistable var6 : var4) {
         try {
            var6.load(var1);
         } catch (IOException var8) {
            var8.printStackTrace();
         }
      }

      if (var3 != this.field5.size()) {
         if (var2 != null) {
            var4.addAll(var2);
         }

         this.method26(var1, var4);
      }
   }

   private List<JsonPersistable> method27(@Nullable List<JsonPersistable> var1) {
      ArrayList var2 = new ArrayList();

      for (Object var4 : this.field5.values()) {
         if (var4 instanceof JsonPersistable var5 && (var1 == null || !var1.contains(var5))) {
            var2.add(var5);
         }
      }

      var2.sort(Comparator.comparingInt(JsonPersistable::priority));
      return var2;
   }

   @Override
   public String toString() {
      ModDetails var1 = (ModDetails)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      return var1 == null ? this.getId() : var1.toString();
   }

   protected ResourceLocationBridge method28(String var1) {
      ResourceLocationBridge var2 = ResourceLocationBridge.create("lunar", "icons/features/" + this.getId().toLowerCase() + "-" + var1 + ".png");
      return this.mc.bridge$getResourceManager().bridge$getResource(var2) != null ? var2 : null;
   }

   protected Framework7Loader method29(@Nullable String var1, @Nullable String var2, String var3) {
      JsonObject var4;
      List var5;
      if (var1 != null) {
         Framework7Loader var6 = this.field5 == null ? null : (Framework7Loader)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field3);
         if (var6 == null) {
            var4 = new JsonObject();
            var5 = new ArrayList();
         } else {
            var4 = var6.method8();
            var5 = var6.method7();
         }

         for (MixinHelper_15 var10 : this.mc.bridge$getGameSettings().bridge$getKeyBindings()) {
            if (var10.bridge$getCategory().equals(var1)) {
               var5.add(var10);
               String var11 = var10.bridge$getUntranslatedKeyDescription();
               if (var4.has(var11)) {
                  var10.bridge$setKey(Bridge.method18().method6(var4.get(var11).getAsString()));
               } else {
                  var4.addProperty(var11, var10.bridge$getKey().name());
               }
            }
         }
      } else {
         var4 = new JsonObject();
         var5 = new ArrayList();
      }

      return new Framework7Loader(var2, var1, var3, var5, var4);
   }

   @com.moonsworth.lunar.client.driver.core.gui.Annotation("details")
   @Override
   public JsonElement method10() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.getId());
      var1.addProperty("enabled", this.isEnabled());
      Alert2 var2 = (Alert2)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field4);
      var1.addProperty("allowed", var2 == null || var2.method3().isEmpty() || (Boolean)var2.method3().get());
      var1.addProperty("rewind", this.IHRCCOIOORRHHIRCRIROCCHICCRHIR());
      ModDetails var3 = (ModDetails)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field13);
      if (var3 != null) {
         var1.addProperty("name", var3.getName());
         if (var3.method1() != null) {
            JsonArray var4 = new JsonArray();
            var3.method1().stream().map(Calculator2Handler::toString).forEach(var4::add);
            var1.add("categories", var4);
         }

         if (var3.method2() != null) {
            JsonArray var5 = new JsonArray();
            var3.method2().forEach(var5::add);
            var1.add("aliases", var5);
         }
      }

      AlertExtension var6 = (AlertExtension)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
      if (var6 != null) {
         var1.add("children", var6.method5(JsonArray::new, (var0, var1x, var2x) -> {
            JsonElement var3x = var1x.method10();
            if (var3x instanceof JsonObject var4x) {
               var4x.add("children", var2x);
            }

            var0.add(var3x);
         }));
      }

      return var1;
   }

   @com.moonsworth.lunar.client.driver.core.gui.Annotation("settings")
   public JsonElement method31() {
      Framework5 var1 = (Framework5)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
      if (var1 != null) {
         Set var2 = var1.method2();
         JsonArray var3 = new JsonArray(var2.size());

         for (ClientOption var5 : var2) {
            OptionDataProvider var6 = (OptionDataProvider)var5.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
            if (var6 != null) {
               var3.add(var6.method128());
            }
         }

         return var3;
      } else {
         return new JsonArray();
      }
   }

   @Generated
   public MutableTraitHost method32() {
      return this.field5;
   }

   @Nullable
   @Generated
   public Module2 method33() {
      return this.field6;
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method1(TraitType<TRAIT> var1, TRAIT var2) {
      return this.method32().method1(var1, (TRAIT)var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method2(TraitType<TRAIT> var1, ThreadModuleDump44<TRAIT> var2) {
      return (TRAIT)this.method32().method1(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT, B extends ThreadModuleDump44<TRAIT>> TRAIT method3(BuilderTraitType<TRAIT, B> var1, Consumer<B> var2) {
      return (TRAIT)this.method32().method1(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method4(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends TRAIT> var2) {
      return (TRAIT)this.method32().method1(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method5(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends TRAIT> var2) {
      return (TRAIT)this.method32().method2(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method6(TraitType<TRAIT> var1, BiFunction<TraitType<TRAIT>, ? super TRAIT, ? extends TRAIT> var2) {
      return (TRAIT)this.method32().method1(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method7(TraitType<? extends TRAIT> var1) {
      return this.method32().method1(var1);
   }

   @Generated
   @Override
   public void method8(TraitSnapshot var1) {
      this.method32().method1(var1);
   }

   @Generated
   @Override
   public void method9(TraitContainer var1) {
      this.method32().method1(var1);
   }

   @Generated
   @Override
   public void method10(TraitHost var1) {
      this.method32().method1(var1);
   }
}
