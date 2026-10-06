package com.moonsworth.lunar.client.mod.skyblock.wardrobehotkeys;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.input.EventKeyInput;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramText;
import com.moonsworth.lunar.client.event.input.KeyInputType;
import com.moonsworth.lunar.client.event.input.MouseInputType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public class SkyblockWardrobeHotkeys extends AbstractFeature {
   private static final long field8 = 250L;
   private final HighlightTypeListener field9 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ScreenTitleListener field10 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("wardrobeHotkeys").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("equipmentHotkeys").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("loadoutHotkeys").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption[] field14 = new ModifierKeybindOption[12];
   private final ModifierKeybindOption field15 = (ModifierKeybindOption)OptionFactory.method18("previousPageKeybind")
      .method5(KeyCode.KEY_LEFT)
      .method31();
   private final ModifierKeybindOption field16 = (ModifierKeybindOption)OptionFactory.method18("nextPageKeybind")
      .method5(KeyCode.KEY_RIGHT)
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showHotkeyButton").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final List<SkyblockWardrobeHotkeys.Data> field18 = List.of(
      new SkyblockWardrobeHotkeys.Data(
         this.field11, 9, new int[]{36, 37, 38, 39, 40, 41, 42, 43, 44}, 45, 53, SkyblockMenuType.WARDROBE_OLD, SkyblockMenuType.WARDROBE
      ),
      new SkyblockWardrobeHotkeys.Data(this.field12, 9, new int[]{36, 37, 38, 39, 40, 41, 42, 43, 44}, 45, 53, SkyblockMenuType.EQUIPMENT),
      new SkyblockWardrobeHotkeys.Data(this.field13, 12, new int[]{14, 15, 16, 23, 24, 25, 32, 33, 34, 41, 42, 43}, 17, 44, SkyblockMenuType.LOADOUTS)
   );
   private long field19;

   public SkyblockWardrobeHotkeys(Skyblock skyblock1) {
      super(false);
      this.method5(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method5(ModTraits.field17, ModCategories.method2(SettingsPage.INVENTORY));
      this.method5(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));

      for (int index2 = 0; index2 < 12; index2++) {
         this.field14[index2] = (ModifierKeybindOption)OptionFactory.method18("wardrobeSlot" + (index2 + 1) + "Keybind")
            .method5(index2 < 9 ? KeyCode.valueOf("KEY_" + (index2 + 1)) : KeyCode.KEY_NONE)
            .method11()
            .method31();
      }

      this.handle(EventRenderHologramText.class, this::method1);
      this.handle(EventKeyInput.class, this::method3);
      this.handle(EventMarkerInput.class, this::method4);
   }

   private void method1(EventRenderHologramText data61) {
      if ((Boolean)this.field17.get()) {
         SkyblockWardrobeHotkeys.Data data2 = this.method13();
         if (data2 != null && (Boolean)data2.method1().get()) {
            SlotBridge bridge3_183 = data61.method3();
            ItemStackBridge bridgeextension_44 = bridge3_183.bridge$getItemStack();
            if (bridgeextension_44 != null && !bridgeextension_44.bridge$isEmpty() && !bridgeextension_44.bridge$getDisplayName().isEmpty()) {
               int number5 = bridge3_183.bridge$getIndex();
               if (number5 == data2.method4()) {
                  if (this.field15.method8() == KeyCode.KEY_NONE) {
                     return;
                  }

                  data61.method2(Component.text(this.method2(this.field15)));
               } else if (number5 == data2.method5()) {
                  if (this.field16.method8() == KeyCode.KEY_NONE) {
                     return;
                  }

                  data61.method2(Component.text(this.method2(this.field16)));
               } else {
                  for (int index6 = 0; index6 < data2.method2(); index6++) {
                     int number7 = data2.method3()[index6];
                     if (number5 == number7) {
                        ModifierKeybindOption lightingextension491338 = this.field14[index6];
                        if (lightingextension491338 != null && lightingextension491338.method8() != KeyCode.KEY_NONE) {
                           data61.method2(Component.text(this.method2(lightingextension491338)));
                           return;
                        }

                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private String method2(ModifierKeybindOption lightingextension491331) {
      return switch (lightingextension491331.method8()) {
         case KEY_UP -> "↑";
         case KEY_DOWN -> "↓";
         case KEY_LEFT -> "←";
         case KEY_RIGHT -> "→";
         default -> lightingextension491331.method8().getName().replace("MOUSE", "M");
      };
   }

   private void method3(EventKeyInput highlightimpl131) {
      SkyblockWardrobeHotkeys.Data data2 = this.method13();
      if (data2 != null && (Boolean)data2.method1().get()) {
         if (highlightimpl131.method4() == KeyInputType.PRESS) {
            highlightimpl131.setCancelled(this.method5(data2, highlightimpl131.method1()));
         }
      }
   }

   private void method4(EventMarkerInput highlightimpl141) {
      SkyblockWardrobeHotkeys.Data data2 = this.method13();
      if (data2 != null && (Boolean)data2.method1().get()) {
         if (highlightimpl141.method4() == MouseInputType.CLICK) {
            try {
               KeyCode bridgetype_83 = KeyCode.valueOf("KEY_MOUSE" + (highlightimpl141.method3() + 1));
               highlightimpl141.setCancelled(this.method5(data2, bridgetype_83));
            } catch (Exception exception4) {
            }
         }
      }
   }

   private boolean method5(SkyblockWardrobeHotkeys.Data data1, KeyCode bridgetype_82) {
      if (Ref.method3().bridge$getSystemTime() <= this.field19 + 250L) {
         return false;
      }

      int number3 = ModifierKeybindOption.method18();
      GuiContainerBridge bridge5extension_34 = this.field10.method6();
      if (bridge5extension_34 == null) {
         return false;
      }

      for (int index5 = 0; index5 < data1.method2(); index5++) {
         ModifierKeybindOption lightingextension491336 = this.field14[index5];
         if (lightingextension491336 != null && lightingextension491336.method5(bridgetype_82, number3)) {
            this.field19 = Ref.method3().bridge$getSystemTime();
            SlotBridge bridge3_187 = (SlotBridge)bridge5extension_34.bridge$inventorySlots().get(data1.method3()[index5]);
            bridge5extension_34.bridge$clickSlot(bridge3_187);
            return true;
         }
      }

      if (this.field15.method5(bridgetype_82, number3)) {
         this.field19 = Ref.method3().bridge$getSystemTime();
         SlotBridge bridge3_189 = (SlotBridge)bridge5extension_34.bridge$inventorySlots().get(data1.method4());
         bridge5extension_34.bridge$clickSlot(bridge3_189);
         return true;
      } else if (this.field16.method5(bridgetype_82, number3)) {
         this.field19 = Ref.method3().bridge$getSystemTime();
         SlotBridge bridge3_188 = (SlotBridge)bridge5extension_34.bridge$inventorySlots().get(data1.method5());
         bridge5extension_34.bridge$clickSlot(bridge3_188);
         return true;
      } else {
         return false;
      }
   }

   @Nullable
   private SkyblockWardrobeHotkeys.Data method13() {
      SkyblockMenuType highlighttype1 = this.field9.method7();
      return highlighttype1 == null ? null : this.field18.stream().filter(arg1x -> {
         for (SkyblockMenuType highlighttype5 : arg1x.method6()) {
            if (highlighttype1 == highlighttype5) {
               return true;
            }
         }

         return false;
      }).findFirst().orElse(null);
   }

   public String getId() {
      return "SKYBLOCK_WARDROBE_HOTKEYS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field11, this.field12, this.field13, this.field17, this.field15, this.field16});
         arg1x.method9(this.field14);
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method2(new String[]{"Equipment Hotkeys", "Loadout Hotkeys"})
         .method11(this);
   }

   private class Data {
      private final ToggleOption field1;
      private final int field2;
      private final int[] field3;
      private final int field4;
      private final int field5;
      private final SkyblockMenuType[] field6;

      private Data(ToggleOption lightingextension4431, int number2, int[] items3, int number4, int number5, SkyblockMenuType... items6) {
         this.field1 = lightingextension4431;
         this.field2 = number2;
         this.field3 = items3;
         this.field4 = number4;
         this.field5 = number5;
         this.field6 = items6;
      }

      public ToggleOption method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public int[] method3() {
         return this.field3;
      }

      public int method4() {
         return this.field4;
      }

      public int method5() {
         return this.field5;
      }

      public SkyblockMenuType[] method6() {
         return this.field6;
      }
   }
}
