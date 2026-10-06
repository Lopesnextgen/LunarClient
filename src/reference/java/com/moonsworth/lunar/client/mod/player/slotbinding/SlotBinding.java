package com.moonsworth.lunar.client.mod.player.slotbinding;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ClickTypeBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.inventorymod.InventorySlotUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.collection.MapRemoval;
import com.moonsworth.lunar.files.ValuePair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.player.slotlocking.SlotLocking;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;

public class SlotBinding extends AbstractFeature {
   private static final int field8 = Ref.MC_VERSION >= 6 ? 10 : 8;
   private final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "icons/assets/link-24x24.png");
   private final ModifierKeybindOption field10 = (ModifierKeybindOption)OptionFactory.method18("slotBindingKeybind")
      .method5(KeyCode.KEY_L)
      .method11()
      .method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("slotBindingSkyBlockOnly").method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("slotBindingLockBound").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("slotBindingIcons").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field14 = (ColorOption)((Data)OptionFactory.method8("slotBindingIconColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-13619152))
      .method31();
   private final Map<Integer, Integer> field15 = new HashMap<>();
   private int field16;

   public SlotBinding(InventoryMods inventorymod1) {
      super(true);
      this.method9(ModTraits.field16, ChildModBinding.method3(inventorymod1));
      this.handle(EventKeybind.class, this::method3);
      this.method9(EventRenderSlot.class, this::method2, 99);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotAfterItems.class, this::method5);
      this.field12.CICORRHIOIIOORRRICCORIOIOCIHII(arg0 -> {
         if (arg0) {
            ModsSettings fogloader31x = Ref.method4().method40();
            if (fogloader31x == null) {
               return;
            }

            SlotLocking slotlocking2 = fogloader31x.method93().method14();
            ((ModEnabledState)slotlocking2.method7(ModTraits.field6)).setEnabled(true);
         }
      });
   }

   public boolean method1(int number1) {
      return this.field15.containsKey(number1) || this.field15.containsValue(number1);
   }

   private void method2(EventRenderSlot highlightimpl51) {
      if (!(Boolean)this.field11.get() || IslandUtils.isOnIsland()) {
         int number2 = highlightimpl51.getSlotId();
         if (number2 >= 0) {
            int number3 = InventorySlotUtils.method4(highlightimpl51.method4(), number2);
            int number4 = this.field15.getOrDefault(number3, -1);
            if (number4 != -1) {
               if (InventorySlotUtils.method7(number3) && InventorySlotUtils.method7(number4)) {
                  if (highlightimpl51.method7() == ClickTypeBridge.QUICK_MOVE) {
                     highlightimpl51.method3(ClickTypeBridge.SWAP);
                     highlightimpl51.method2(9 - Math.min(number3, number4) - 1);
                     highlightimpl51.method1(InventorySlotUtils.method6(highlightimpl51.method4(), Math.max(number3, number4)));
                     highlightimpl51.setCancelled(false);
                  }
               }
            }
         }
      }
   }

   private void method3(EventKeybind highlightimpl1) {
      if (this.method13()) {
         if (highlightimpl1.method10() == this.field10.method8()) {
            if (highlightimpl1.method11() == InputAction.DOWN) {
               this.field16 = InventorySlotUtils.method1();
               if (!InventorySlotUtils.method7(this.field16)) {
                  return;
               }

               HashSet set2 = new HashSet();
               this.field15.forEach((arg2x, arg3) -> {
                  if (arg2x == this.field16 || arg3 == this.field16) {
                     set2.add(arg2x);
                     set2.add(arg3);
                  }
               });
               MapRemoval.method1(this.field15, arg1x -> arg1x == this.field16);
               MapRemoval.method2(this.field15, arg1x -> arg1x == this.field16);

               for (int index4 : set2) {
                  if (!this.field15.containsKey(index4) && !this.field15.containsValue(index4)) {
                     SlotLocking slotlocking5 = Ref.method4().method40().method93().method14();
                     slotlocking5.method10(index4, false);
                  }
               }
            } else {
               if (!InventorySlotUtils.method7(this.field16)) {
                  return;
               }

               int number6 = InventorySlotUtils.method1();
               if (!InventorySlotUtils.method7(number6)) {
                  return;
               }

               if (number6 == this.field16) {
                  for (Entry entry9 : this.field15.entrySet()) {
                     if (number6 == (Integer)entry9.getValue()) {
                        this.field15.put(number6, (Integer)entry9.getKey());
                        break;
                     }
                  }

                  return;
               }

               if (this.method4(this.field16, number6)) {
                  this.field15.put(this.field16, number6);
                  this.field15.put(number6, this.field16);
                  SlotLocking slotlocking7 = Ref.method4().method40().method93().method14();
                  slotlocking7.method10(this.field16, (Boolean)this.field12.get());
                  slotlocking7.method10(number6, (Boolean)this.field12.get());
               }
            }
         }
      }
   }

   private boolean method4(int number1, int number2) {
      return number1 <= 8 ? number2 > 8 : number2 <= 8;
   }

   private void method5(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotAfterItems data21) {
      if (this.method13()) {
         this.method6(data21);
         this.method7(data21);
      }
   }

   private void method6(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotAfterItems data21) {
      if (this.method13()) {
         if ((Boolean)this.field13.get()) {
            HashSet set2 = new HashSet();
            set2.addAll(this.field15.keySet());
            set2.addAll(this.field15.values());
            if (!(Boolean)this.field11.get() || IslandUtils.isOnIsland()) {
               GuiScreenBridge bridge5extension63 = data21.method3();
               if (bridge5extension63 instanceof GuiContainerBridge) {
                  MixinHelper_4 mixinhelper_44 = data21.method5();

                  for (Integer index6 : set2) {
                     if (InventorySlotUtils.method7(index6)) {
                        ValuePair files6_27 = InventorySlotUtils.method8(index6);
                        if (files6_27 != null) {
                           int number8 = (Integer)files6_27.field1;
                           int number9 = (Integer)files6_27.field2;
                           mixinhelper_44.method24(this.field9, number8, number9, 6, 6, this.field14.method14(0.0F));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method7(EventRenderContainerSlot highlightimpl151) {
      MixinHelper_4 mixinhelper_42 = highlightimpl151.method5();
      if (this.field10.isKeyDown() && InventorySlotUtils.method7(this.field16)) {
         this.method9(this.field16, mixinhelper_42, highlightimpl151.method1());
      }

      int number3 = InventorySlotUtils.method1();
      if (InventorySlotUtils.method7(number3)) {
         if (this.field15.containsKey(number3) || this.field15.containsValue(number3)) {
            HashSet set4 = new HashSet();

            for (Entry entry6 : this.field15.entrySet()) {
               int number7 = (Integer)entry6.getKey();
               int number8 = (Integer)entry6.getValue();
               if (InventorySlotUtils.method7(number7)
                  && InventorySlotUtils.method7(number8)
                  && (number7 == number3 || (Integer)entry6.getValue() == number3)
                  && !this.method8(set4, number7, number8)) {
                  ValuePair files6_29 = InventorySlotUtils.method8(number7);
                  ValuePair files6_210 = InventorySlotUtils.method8(number8);
                  if (files6_29 != null && files6_210 != null) {
                     mixinhelper_42.method31(
                        (Integer)files6_29.field1 + field8,
                        (Integer)files6_29.field2 + field8,
                        (Integer)files6_210.field1 + field8,
                        (Integer)files6_210.field2 + field8,
                        3.0F,
                        -16711681
                     );
                     set4.add(new ValuePair(number7, number8));
                  }
               }
            }
         }
      }
   }

   private boolean method8(Set<ValuePair<Integer, Integer>> set1, int number2, int number3) {
      for (ValuePair files6_25 : set1) {
         if ((Integer)files6_25.field1 == number2 && (Integer)files6_25.field2 == number3 || (Integer)files6_25.field2 == number2 && (Integer)files6_25.field1 == number3) {
            return true;
         }
      }

      return false;
   }

   private void method9(int number1, MixinHelper_4 mixinhelper_42, Data4 data43) {
      ValuePair files6_24 = InventorySlotUtils.method8(number1);
      if (files6_24 != null) {
         mixinhelper_42.method31(
            (Integer)files6_24.field1 + field8,
            (Integer)files6_24.field2 + field8,
            data43.IIRCROICCRROCOCOIOIHHOCRHOIHIR(),
            data43.CRCOHORRCCORCCIIOOIOOCIRRCRHHH(),
            3.0F,
            -16711681
         );
      }
   }

   private boolean method13() {
      if ((Boolean)this.field11.get() && !IslandUtils.isOnIsland()) {
         return false;
      }

      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      return bridge5extension_51 != null && !bridge5extension_51.bridge$getPlayerCapabilities().bridge$isCreativeMode();
   }

   @ConstantName
   public String getId() {
      return "SLOT_BINDING";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field10, this.field13, this.field14, this.field12, this.field11});
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      json1.add("bindings", LunarConstants.field22.toJsonTree(this.field15));
   }

   public void load(JsonObject json1) {
      super.load(json1);
      if (json1.has("bindings")) {
         JsonElement element2 = json1.get("bindings");

         try {
            this.field15
               .putAll(
                  (Map<? extends Integer, ? extends Integer>)LunarConstants.field22
                     .fromJson(element2, new TypeToken<Map<? extends Integer, ? extends Integer>>() {})
               );
         } catch (JsonSyntaxException jsonsyntaxexception4) {
         }
      }
   }

   @Generated
   public ToggleOption method14() {
      return this.field12;
   }
}
