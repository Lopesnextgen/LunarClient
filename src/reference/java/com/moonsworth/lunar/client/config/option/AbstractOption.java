package com.moonsworth.lunar.client.config.option;

import com.google.common.base.CaseFormat;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.lunarclient.apollo.module.modsetting.ModSettingModule;
import com.lunarclient.dfu.serialization.Codec;
import com.lunarclient.dfu.serialization.JsonOps;
import com.lunarclient.dfu.serialization.DataResult.Error;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleManager;
import com.moonsworth.lunar.client.network.apollo.ModSettingApolloHandler;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation2;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers2;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlers3;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.AlertExtension;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionUpdateListeners;
import com.moonsworth.lunar.client.config.option.OptionDisplay;
import com.moonsworth.lunar.client.config.option.trait.TraitHost;
import com.moonsworth.lunar.client.config.option.trait.TraitContainer;
import com.moonsworth.lunar.client.config.option.trait.MutableTraitHost;
import com.moonsworth.lunar.client.config.option.trait.MutableTraitContainer;
import com.moonsworth.lunar.client.config.option.trait.TraitSnapshot;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.BuilderTraitType;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.Annotation3;
import com.moonsworth.lunar.client.util.ThreadModuleDump44;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.mixin.MixinHelper2;
import com.moonsworth.lunar.client.util.mixin.MixinHelper22;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractOption<T> implements ClientOption<T>, GuiRewindhandlers2 {
   public static final boolean field1 = false;
   private static final boolean field2 = false;
   protected MutableTraitHost field3;
   @Annotation3
   private final String field4;
   @Nullable
   private final Codec<T> field5;
   private String field6;

   public AbstractOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<T> var2) {
      this.field4 = var1;
      this.field5 = var2;
      this.field3 = new MutableTraitContainer(OptionTraits.field15);
   }

   @Override
   public String method3() {
      return this.method3(OptionTraits.field11).orElse(this.field4);
   }

   @Override
   public String method4() {
      if (this.field6 == null) {
         this.field6 = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, this.getId());
      }

      return this.field6;
   }

   protected abstract void method4(T var1);

   @Override
   public void method1(Runnable var1) {
      this.method6().method3(var1);
   }

   @Override
   public void method2(Runnable var1) {
      OptionUpdateListeners var2 = (OptionUpdateListeners)this.method7(OptionTraits.field1);
      if (var2 != null) {
         var2.method4(var1);
      }
   }

   @Override
   public void method10(T var1) {
      this.method11((T)var1, false);
   }

   @Override
   public void method11(T var1, boolean var2) {
      if (var2 || !Objects.equals(this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI(), var1)) {
         this.method4((T)var1);
         this.method11((T)var1);
         Alert2 var3 = (Alert2)this.method7(OptionTraits.field5);
         if (var3 == null || var3.method3().isEmpty()) {
            OptionUpdateListeners var4 = (OptionUpdateListeners)this.method7(OptionTraits.field1);
            if (var4 != null) {
               var4.method5(this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI());
            }
         }

         if (ThreadModuleDump63.method8() != null && !ThreadModuleDump63.method4().method40().method85().method19()) {
            ThreadModuleDump63.method4().method41().method2(this);
         }
      }
   }

   @Override
   public void method12(T var1) {
      if (!Objects.equals(this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI(), var1)) {
         this.method4((T)var1);
         this.method11((T)var1);
         if (ThreadModuleDump63.method8() != null) {
            ThreadModuleDump63.method4().method41().method2(this);
         }
      }
   }

   @Override
   public void method13(Object var1) {
      if (var1 instanceof ClientOption var2) {
         var1 = var2.get();
      }

      if (var1 != null && var1.getClass().isInstance(this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI())) {
         this.method4((T)var1);
         this.method11((T)var1);
      }
   }

   private void method11(T var1) {
      if (Client.method109() != null) {
         try {
            ApolloModuleManager var2 = Client.method109().method84();
            if (var2 != null) {
               var2.<ApolloModuleHandler>method3(ModSettingModule.class).ifPresent(var2x -> ((ModSettingApolloHandler)var2x).method17().method2(this, var1));
            }
         } catch (Throwable var3) {
            Slayer.method6("Apollo", "Apollo broadcast failed", var3);
         }
      }
   }

   @com.moonsworth.lunar.client.guiRewindhandlers.Annotation(CCOIIIIHHRCHIIHRIIHCCRCHRRCOIH = Annotation2.Type.DYNAMICLISTENER_ISENABLED)
   @Override
   public T get() {
      GuiRewindhandlers3.method1(this);
      Alert2 var1 = (Alert2)this.method7(OptionTraits.field5);
      return (T)(var1 == null ? this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI() : var1.method3().orElse(this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()));
   }

   @Override
   public String getValueAsString() {
      return this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI() == null ? "null" : this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI().toString();
   }

   @Override
   public boolean isHidden() {
      OptionDisplay var1 = (OptionDisplay)this.method7(OptionTraits.field2);
      return var1 != null ? var1.method4(this) : true;
   }

   protected void method11(MixinHelper2<ClientOption<?>> var1) {
   }

   @Nullable
   @Override
   public final Collection<ClientOption<?>> method22() {
      MixinHelper22.Data2 var1 = MixinHelper22.method10(LinkedList::new, (Class<T>)null);
      this.method11(var1);
      return var1.build();
   }

   @Override
   public void method1(JsonObject var1) {
      Collection var2 = this.method22();
      JsonObject var3 = var2 == null ? var1 : new JsonObject();
      if (this.field5 != null && !Objects.equals(this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI(), this.getDefaultValue())) {
         this.field5
            .encodeStart(JsonOps.INSTANCE, this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI())
            .ifError(var1x -> this.method15(var1x, "saving", "Option Save"))
            .ifSuccess(var3x -> {
               if (var2 == null) {
                  if (var3x instanceof JsonObject var4) {
                     if (!var4.isEmpty()) {
                        var3.add(this.field4, var4);
                     }
                  } else if (var3x instanceof JsonArray var5x) {
                     if (!var5x.isEmpty()) {
                        var3.add(this.field4, var5x);
                     }
                  } else if (!var3x.isJsonNull()) {
                     var3.add(this.field4, var3x);
                  }
               } else {
                  var3.add("value", var3x);
               }
            });
      } else if (var2 == null) {
         var3.remove(this.field4);
      }

      if (var2 != null) {
         for (ClientOption var5 : var2) {
            var5.method1(var3);
         }

         if (!var3.isEmpty()) {
            var1.add(this.field4, var3);
         }
      }

      for (JsonPersistable var9 : this.method17(null)) {
         try {
            var9.method1(var1);
         } catch (IOException var7) {
            var7.printStackTrace();
         }
      }
   }

   @Override
   public void method16(JsonObject var1, boolean var2) {
      try {
         this.load(var1);
      } finally {
         if (var2) {
            OptionUpdateListeners var5 = (OptionUpdateListeners)this.method7(OptionTraits.field1);
            if (var5 != null) {
               var5.method5(this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI());
            }
         }
      }
   }

   @Override
   public void load(JsonObject var1) {
      this.method16(var1, null);
      MixinHelper22.Data2 var2 = MixinHelper22.method10(LinkedList::new, (Class<T>)null);
      this.method11(var2);
      List var3 = var2.build();
      if (var1.has(this.field4) && !var1.get(this.field4).isJsonNull()) {
         JsonElement var8 = var1.get(this.field4);
         if (this.field5 != null && (var3 == null || !var8.isJsonObject())) {
            this.field5.parse(JsonOps.INSTANCE, var8).ifError(var1x -> this.method15(var1x, "loading", "Option Load")).ifSuccess(this::method10);
            if (var3 != null) {
               JsonObject var10 = new JsonObject();

               for (ClientOption var14 : var3) {
                  var14.load(var10);
               }
            }
         } else {
            JsonObject var9;
            if (var8.isJsonObject()) {
               var9 = var8.getAsJsonObject();
               String var11 = "value";
               if (this.field5 != null) {
                  if (var9.has(var11) && !var9.get(var11).isJsonNull()) {
                     this.field5
                        .parse(JsonOps.INSTANCE, var9.get(var11))
                        .ifError(var1x -> this.method15(var1x, "loading", "Option Load"))
                        .ifSuccess(this::method10);
                  } else {
                     this.method10(this.getDefaultValue());
                  }
               }
            } else {
               var9 = new JsonObject();
            }

            if (var3 != null) {
               for (ClientOption var7 : var3) {
                  var7.load(var9);
               }
            }
         }
      } else {
         this.method10(this.getDefaultValue());
         if (var3 != null) {
            JsonObject var4 = new JsonObject();

            for (ClientOption var6 : var3) {
               var6.load(var4);
            }
         }
      }
   }

   private void method15(Error<?> var1, String var2, String var3) {
      String var4 = "Error " + var2 + " option " + this + ": " + var1;
      if (FeatureFlag.SENTRY_OPTION_PARSING.isEnabled()) {
         Inventorymod2.method5(new IOException(var4), var3);
      } else {
         Slayer.method7(var4);
      }
   }

   private void method16(JsonObject var1, @Nullable List<JsonPersistable> var2) {
      int var3 = this.field3.size();
      List var4 = this.method17(var2);

      for (JsonPersistable var6 : var4) {
         try {
            var6.load(var1);
         } catch (IOException var8) {
            var8.printStackTrace();
         }
      }

      if (var3 != this.field3.size()) {
         if (var2 != null) {
            var4.addAll(var2);
         }

         this.method16(var1, var4);
      }
   }

   private List<JsonPersistable> method17(@Nullable List<JsonPersistable> var1) {
      ArrayList var2 = new ArrayList();

      for (Object var4 : this.field3.values()) {
         if (var4 instanceof JsonPersistable var5 && (var1 == null || !var1.contains(var5))) {
            var2.add(var5);
         }
      }

      var2.sort(Comparator.comparingInt(JsonPersistable::priority));
      return var2;
   }

   @Override
   public String getLanguagePath() {
      return "settings";
   }

   @Override
   public void method19(ClientOption<?> var1) {
      Object var2 = var1.get();
      if (var2 == null) {
         throw new IllegalArgumentException("Can't set value to null!");
      }

      if (this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI() == null) {
         throw new IllegalArgumentException("Can't copy into a null option!");
      }

      Class var3 = this.ICCOIHCHIRROOOHIOIHOCIIHHICCCI().getClass();
      Class var4 = var2.getClass();
      if (!Objects.equals(var3, var4)) {
         String var5 = String.format(
            "Can't set '%s %s' option value, expected %s, got %s from '%s %s'",
            this.getId(),
            this.getClass().getName(),
            var3,
            var4,
            var1.getId(),
            var1.getClass().getName()
         );
         throw new IllegalArgumentException(var5);
      }

      this.method11((T)var2, false);
   }

   @Override
   public ClientOption<T> method20() {
      try {
         AbstractOption var1 = (AbstractOption)super.clone();
         var1.field3 = this.field3.method3();
         var1.method7(OptionTraits.field1);
         OptionDataProvider var2 = (OptionDataProvider)var1.method7(OptionTraits.field10);
         if (var2 != null) {
            var1.method1(OptionTraits.field10, var2.method7(var1));
         }

         return var1;
      } catch (CloneNotSupportedException var3) {
         throw new AssertionError();
      }
   }

   @Override
   public void method21(@NotNull ResolvedOptionNode<?> var1) {
      if (!var1.getChildren().isEmpty()) {
         this.method5(OptionTraits.field4, var0 -> AlertExtension.method2()).method1(this, var1.getChildren());
      }

      BooleanSupplier var2 = var1.method1();
      if (var2 != null) {
         this.method6(OptionTraits.field2, (var1x, var2x) -> var2x == null ? OptionDisplay.method10(var2) : var2x.method7(var2));
      }

      if (var1.getFeatureId() != null) {
         this.method5(OptionTraits.field9, var1x -> var1.getFeatureId());
      }
   }

   @Override
   public List<ClientOption<?>> getChildren() {
      AlertExtension var1 = (AlertExtension)this.method7(OptionTraits.field4);
      return var1 == null ? List.of() : var1.getChildren();
   }

   @Override
   public void method21(String var1) {
      this.method14(method22(var1)).ifPresent(this::method10);
   }

   protected static JsonElement method22(String var0) {
      try {
         JsonElement var1 = (JsonElement)ThreadModuleDump48.field22.fromJson(var0, JsonElement.class);
         return (JsonElement)(var1 == null ? new JsonPrimitive(var0) : var1);
      } catch (JsonSyntaxException var2) {
         return new JsonPrimitive(var0);
      }
   }

   @Override
   public final Optional<T> method14(JsonElement var1) {
      return this.field5 != null
         ? this.field5.parse(JsonOps.INSTANCE, var1).ifError(var1x -> this.method15(var1x, "parsing", "Option Parse")).result()
         : Optional.empty();
   }

   @Nullable
   @Override
   public final OptionWidget<?> method18(GuiWidget var1) {
      BiFunction var2 = (BiFunction)this.method7(OptionTraits.field13);
      return var2 != null ? (OptionWidget)var2.apply(this, var1) : this.method25(var1);
   }

   @Nullable
   protected OptionWidget<?> method25(GuiWidget var1) {
      return null;
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName() + "[" + this.field4 + "]";
   }

   @Contract("->this")
   @Override
   public final ClientOption<T> method1() {
      return this;
   }

   @Generated
   public MutableTraitHost method27() {
      return this.field3;
   }

   @Generated
   @Override
   public String getId() {
      return this.field4;
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method1(TraitType<TRAIT> var1, TRAIT var2) {
      return this.method27().method1(var1, (TRAIT)var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method2(TraitType<TRAIT> var1, ThreadModuleDump44<TRAIT> var2) {
      return (TRAIT)this.method27().method1(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT, B extends ThreadModuleDump44<TRAIT>> TRAIT method3(BuilderTraitType<TRAIT, B> var1, Consumer<B> var2) {
      return (TRAIT)this.method27().method1(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method4(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends TRAIT> var2) {
      return (TRAIT)this.method27().method1(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method5(TraitType<TRAIT> var1, Function<TraitType<TRAIT>, ? extends TRAIT> var2) {
      return (TRAIT)this.method27().method2(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method6(TraitType<TRAIT> var1, BiFunction<TraitType<TRAIT>, ? super TRAIT, ? extends TRAIT> var2) {
      return (TRAIT)this.method27().method1(var1, var2);
   }

   @Generated
   @Override
   public <TRAIT> TRAIT method7(TraitType<? extends TRAIT> var1) {
      return this.method27().method1(var1);
   }

   @Generated
   @Override
   public void method8(TraitSnapshot var1) {
      this.method27().method1(var1);
   }

   @Generated
   @Override
   public void method9(TraitContainer var1) {
      this.method27().method1(var1);
   }

   @Generated
   @Override
   public void method10(TraitHost var1) {
      this.method27().method1(var1);
   }
}
