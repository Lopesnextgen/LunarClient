package com.moonsworth.lunar.client.mod.render.itemcustomizer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.itemcounter.ItemCounterEntry;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemRotation;
import com.moonsworth.lunar.client.event.mixin.highlight.EventGroundItemTransform;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ItemSelectOption;
import com.moonsworth.lunar.client.config.option.ItemSelectOption.Data;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.util.text.TextUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public class CustomDroppedItems extends AbstractFeature {
   private final Map<String, FloatOption> field8 = new HashMap<>();
   private final MultiSelectOption field9 = (MultiSelectOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method28("selectedItems")
         .method5(arg1x -> {
            boolean flag2 = !this.field9.contains(arg1x);
            if (flag2) {
               OptionContainer framework53 = (OptionContainer)this.method7(ModTraits.field14);
               framework53.method1().remove(this.field8.remove(arg1x));
            } else {
               this.method5(arg1x);
            }

            ItemCustomizer.method6(this, false);
            ((ModEnabledState)this.method7(ModTraits.field6)).method1().ifPresent(arg0 -> {
               if (arg0 instanceof ToggleOption lightingextension4431xx) {
                  lightingextension4431xx.method3(true);
               }
            });
         }))
      .method31();

   public CustomDroppedItems(ItemCustomizer itemcustomizer1) {
      super(false);
      this.method6(ModTraits.field16, ChildModBinding.method3(itemcustomizer1));
      this.handle(EventRenderItemRotation.class, arg1x -> {
         Float value2 = this.method6(arg1x.method2().bridge$getItemState());
         if (value2 != null) {
            arg1x.method1().scale(value2, value2, value2);
            if (Ref.MC_VERSION < 5) {
               arg1x.method1().translate(0.0, 0.15, 0.0);
            }
         }
      });
      this.handle(EventGroundItemTransform.class, arg1x -> {
         if (Ref.method4().method40().method29().isEnabled()) {
            Float value2 = this.method6(arg1x.method1());
            if (value2 != null) {
               arg1x.setScale(value2);
               if (Ref.MC_VERSION < 5) {
                  arg1x.method4().translate(0.0, 0.15, 0.0);
               }
            }
         }
      });
   }

   public String getId() {
      return "CUSTOM_DROPPED_ITEMS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9});
   }

   public void method4() {
      super.method4();
      this.method3(null);
   }

   public void load(JsonObject json1) {
      super.load(json1);
      this.method3(json1);
   }

   private void method3(@Nullable JsonObject json1) {
      OptionContainer framework52 = (OptionContainer)this.method7(ModTraits.field14);

      for (FloatOption lightingextension4724 : this.field8.values()) {
         framework52.method1().remove(lightingextension4724);
      }

      this.field8.clear();
      if (json1 != null) {
         JsonElement element8 = json1.get("options");
         JsonObject json9 = element8 != null && !element8.isJsonNull() ? element8.getAsJsonObject() : new JsonObject();

         for (String text6 : (Set)this.field9.get()) {
            FloatOption lightingextension4727 = this.method5(text6);
            if (lightingextension4727 != null) {
               lightingextension4727.load(json9);
            }
         }
      }

      BackgroundExecutor.method13(() -> ItemCustomizer.method6(this, false), 1);
   }

   protected boolean method23(String text1) {
      return text1.endsWith("Scale");
   }

   @Nullable
   private FloatOption method5(String text1) {
      ItemCounterEntry itemcounter_22 = (ItemCounterEntry)ItemSelectOption.method11().get(text1);
      if (itemcounter_22 == null) {
         return null;
      }

      String text3 = itemcounter_22.toString();
      String text4 = TextUtils.toCamelCase(text1, false);
      int index5 = text4.indexOf(58);
      if (index5 != -1 && index5 < text4.length() - 1) {
         text4 = text4.substring(index5 + 1);
      }

      FloatOption lightingextension4726 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
                        text4 + "Scale"
                     )
                     .method4(2.0F))
                  .method8(1.0F, 5.0F))
               .method6(2))
            .ROICHOCCIOCHCIHOIHIHICCORIROCC(text3))
         .method31();
      OptionContainer framework57 = (OptionContainer)this.method7(ModTraits.field14);
      framework57.method1().add(lightingextension4726);
      this.field8.put(text1, lightingextension4726);
      return lightingextension4726;
   }

   @Nullable
   public Float method6(ItemStackRenderStateBridge mixinhelper_141) {
      if (mixinhelper_141 == null) {
         return null;
      }

      ItemCustomizer itemcustomizer2 = (ItemCustomizer)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      ItemCounterEntry itemcounter_23 = mixinhelper_141 instanceof ItemStackBridge bridgeextension_44 ? itemcustomizer2.method3(bridgeextension_44) : itemcustomizer2.method4(mixinhelper_141.bridge$getItemRegistryName());
      if (itemcounter_23 == null) {
         return null;
      }

      FloatOption lightingextension4725 = this.field8.get(itemcounter_23.method1());
      return lightingextension4725 == null ? null : (Float)lightingextension4725.get();
   }
}
