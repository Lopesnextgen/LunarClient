package com.moonsworth.lunar.client.mod.render.itemcustomizer;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ItemTransformVec3fBridge;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.animations.customhelditems.CustomhelditemsType;
import com.moonsworth.lunar.client.framework.feature.animations.customhelditems.Lighting3Loader;
import com.moonsworth.lunar.client.framework.feature.animations.customhelditems.Lighting3Loader2;
import com.moonsworth.lunar.client.framework.feature.itemcounter.Itemcounter_2;
import com.moonsworth.lunar.client.event.mixin.highlight.EntityOffsetRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.GlintTransformEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ItemSelectOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class CustomHeldItems extends AbstractFeature {
   private final Lighting3Loader2 field8 = new Lighting3Loader2(this, this.field4.method67().method2("settings", "allItems"), false);
   private final Lighting3Loader2 field9 = new Lighting3Loader2(this, this.field4.method67().method2("settings", "emptyHands"), false);
   private final Map<String, Lighting3Loader2> field10 = new LinkedHashMap<>();
   private final MultiSelectOption field11 = (MultiSelectOption)((ItemSelectOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method28(
            "selectedItems"
         )
         .method5(var1x -> {
            Itemcounter_2 var2 = this.method13().method4(var1x);
            if (var2 != null) {
               boolean var3 = false;
               if (this.field11.contains(var1x)) {
                  if (!this.field10.containsKey(var2.method1())) {
                     this.field10.put(var2.method1(), new Lighting3Loader2(this, var2.toString(), true));
                     var3 = true;
                  }
               } else {
                  var3 = this.field10.remove(var2.method1()) != null;
               }

               if (var3) {
                  ItemCustomizer.method6(this, true);
                  ((ModEnabledState)this.method7(Framework.field6)).method1().ifPresent(var0 -> {
                     if (var0 instanceof ToggleOption var1xx) {
                        var1xx.method3(true);
                     }
                  });
               }
            }
         }))
      .method31();

   public CustomHeldItems(ItemCustomizer var1) {
      super(false);
      this.method8(Framework.field16, Framework4.method3(var1));
      this.method8(GlintTransformEvent.class, this::method6, 2147483632);
      this.handle(EntityOffsetRenderEvent.class, this::method7);
   }

   @Override
   public String getId() {
      return "CUSTOM_HELD_ITEMS";
   }

   @Override
   public void method1(JsonObject var1) {
      super.method1(var1);
      var1.remove("options");
      JsonObject var2 = new JsonObject();
      this.field10.keySet().forEach(var2x -> this.method2(var2x, this.field10.get(var2x), var2));
      this.method2("global", this.field8, var2);
      this.method2("emptyArms", this.field9, var2);
      if (!var2.isEmpty()) {
         var1.add("individualConfigs", var2);
      }
   }

   private void method2(String var1, Lighting3Loader2 var2, JsonObject var3) {
      JsonObject var4 = new JsonObject();
      var2.method1(var4);
      var3.add(var1, var4);
   }

   @Override
   public void load(JsonObject var1) {
      super.load(var1);
      this.field10.clear();
      this.field11.method10(new LinkedHashSet());
      ThreadModuleDump9.findJsonObject(var1, "individualConfigs").ifPresent(var1x -> {
         for (String var3 : var1x.keySet()) {
            ThreadModuleDump9.findJsonObject(var1x, var3).ifPresent(var2 -> this.method3(var3, var2));
         }
      });
      ItemCustomizer.method5(this, true);
   }

   private void method3(String var1, JsonObject var2) {
      if ("global".equals(var1)) {
         this.field8.load(var2);
      } else if ("emptyArms".equals(var1)) {
         this.field9.load(var2);
      } else {
         Itemcounter_2 var3 = this.method13().method4(var1);
         if (var3 != null) {
            Lighting3Loader2 var4 = new Lighting3Loader2(this, var3.toString(), true);
            var4.load(var2);
            this.field10.put(var1, var4);
            this.field11.method10(var1);
         }
      }
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      this.field8.method2(var1);
      this.field9.method2(var1);
      this.field10.values().forEach(var1x -> var1x.method2(var1));
      var1.method11(new OptionSupplier[]{com.moonsworth.lunar.client.config.option.OptionFactory.method14("addHeldItem").method4(() -> {
         if (this.mc.bridge$getPlayer() != null) {
            ItemStackBridge var1x = this.mc.bridge$getPlayer().bridge$getHeldItem();
            if (var1x != null && !var1x.bridge$isEmpty()) {
               Itemcounter_2 var2 = this.method13().method3(var1x);
               if (var2 != null) {
                  this.field11.method10(var2.method1());
               }
            }
         }
      })});
      var1.method11(new ClientOption[]{this.field11});
   }

   @Override
   public void method4() {
      super.method4();
      this.field10.clear();
      ItemCustomizer.method6(this, true);
   }

   private void method6(GlintTransformEvent var1) {
      if (var1.method8().firstPerson() && var1.method7() != null && !var1.method7().bridge$isEmpty()) {
         Lighting3Loader var2 = this.method8(var1.method7(), var1.method8().rightHand());
         if (var2 != null) {
            var2.method2(var1);
         }
      }
   }

   private void method7(EntityOffsetRenderEvent var1) {
      if (this.field9.isEnabled()) {
         Lighting3Loader var2 = this.field9.method3(var1.method3() ? CustomhelditemsType.LEFT : CustomhelditemsType.RIGHT);
         if (var2 != null) {
            var1.method1(var2.field5.get(), var2.field6.get(), var2.field7.get());
            var1.method2(var2.field8.get(), var2.field9.get(), var2.field10.get());
            var1.setScale(var2.field4.get());
         }
      }
   }

   @Nullable
   private Lighting3Loader method8(ItemStackBridge var1, boolean var2) {
      Itemcounter_2 var3 = this.method13().method3(var1);
      if (var3 == null) {
         return null;
      }

      String var4 = var3.method1();
      Lighting3Loader2 var5 = this.field10.get(var4);
      if (var5 == null || !var5.isEnabled()) {
         var5 = this.field8;
         if (!var5.isEnabled()) {
            return null;
         }
      }

      return var5.method3(var2 ? CustomhelditemsType.RIGHT : CustomhelditemsType.LEFT);
   }

   public float method9(ItemStackBridge var1, boolean var2) {
      Lighting3Loader var3 = this.method8(var1, var2);
      return var3 != null ? var3.field4.get() : 1.0F;
   }

   @Nullable
   public ItemTransformVec3fBridge method10(ItemStackBridge var1, boolean var2) {
      Lighting3Loader var3 = this.method8(var1, var2);
      return var3 != null
         ? new ItemTransformVec3fBridge(
            new Vector3f(var3.field8.get(), var3.field9.get(), var3.field10.get()),
            new Vector3f(var3.field5.get(), var3.field6.get(), var3.field7.get()),
            new Vector3f(var3.field4.get(), var3.field4.get(), var3.field4.get())
         )
         : null;
   }

   private ItemCustomizer method13() {
      return ((Framework4)this.method7(Framework.field16)).method1();
   }

   void method12(Lighting3Loader2 var1) {
      for (Entry var3 : this.field10.entrySet()) {
         if (var3.getValue() == var1) {
            this.field11.remove((String)var3.getKey());
            break;
         }
      }
   }
}
