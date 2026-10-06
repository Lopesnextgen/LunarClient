package com.moonsworth.lunar.client.mod.player.hotbarkeyoverlay;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.InventoryPlayerBridge;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderInventoryScreen.EventRenderHotbarPost;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.player.inventorymod.InventoryMods;

public class HotbarKeyOverlay extends AbstractFeature {
   private final EnumOption<HotbarKeyOverlay.Type> field8 = (EnumOption<HotbarKeyOverlay.Type>)OptionFactory.method10(
         "position", HotbarKeyOverlay.Type.TOP_LEFT
      )
      .method31();
   private final ColorOption field9 = (ColorOption)((Data)OptionFactory.method8("color").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("small").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("lowercase").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("textShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("padding").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method17(() -> this.field8.get() == HotbarKeyOverlay.Type.CENTER))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideWhenSelected").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideWhenEmpty").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideWhenNotEmpty").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("fadeWhenEmpty").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("fadeWhenNotEmpty").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final String[] field19 = new String[9];

   public HotbarKeyOverlay(InventoryMods inventorymod1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(inventorymod1));
      this.handle(EventRenderHotbarPost.class, this::method2);
      this.method2(EventTick.class, this::method13);
   }

   public String getId() {
      return "HOTBAR_KEY_OVERLAY";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8, this.field9, this.field10});
      lightingextension231.method14();
      lightingextension231.method9(new ClientOption[]{this.field11});
      lightingextension231.method14();
      lightingextension231.method9(new ClientOption[]{this.field12});
      lightingextension231.method14();
      lightingextension231.method9(new ClientOption[]{this.field13});
      lightingextension231.method13();
      lightingextension231.method9(new ClientOption[]{this.field15, this.field16, this.field17, this.field18, this.field14});
   }

   private void method2(EventRenderHotbarPost data101) {
      Bridge5Extension_5 bridge5extension_52 = this.mc.bridge$getPlayer();
      if (bridge5extension_52 != null) {
         InventoryPlayerBridge bridge_243 = bridge5extension_52.bridge$getInventory();
         if (bridge_243 != null) {
            List list4 = bridge_243.bridge$getMainInventory();
            if (list4 != null && list4.size() >= 9) {
               MixinHelper_4 mixinhelper_45 = data101.method1();
               Bridge10_2 bridge10_26 = this.mc.bridge$getFontRenderer();
               HotbarKeyOverlay.Type type7 = (HotbarKeyOverlay.Type)this.field8.get();
               boolean flag8 = (Boolean)this.field12.get();
               boolean flag9 = type7 == HotbarKeyOverlay.Type.CENTER;
               boolean flag10 = type7 == HotbarKeyOverlay.Type.TOP_RIGHT || type7 == HotbarKeyOverlay.Type.BOTTOM_RIGHT;
               boolean flag11 = type7 == HotbarKeyOverlay.Type.BOTTOM_LEFT || type7 == HotbarKeyOverlay.Type.BOTTOM_RIGHT;
               float value12 = !flag9 && this.field13.get() ? 2.0F : 1.0F;

               for (int index13 = 0; index13 < 9; index13++) {
                  String text14 = this.field19[index13];
                  if (text14 != null && (bridge_243.bridge$getSelectedSlot() != index13 || !(Boolean)this.field14.get())) {
                     ItemStackBridge bridgeextension_415 = (ItemStackBridge)list4.get(index13);
                     boolean flag16 = bridgeextension_415 == null || bridgeextension_415.bridge$isEmpty();
                     if ((!flag16 || !(Boolean)this.field15.get()) && (flag16 || !(Boolean)this.field16.get())) {
                        int number17 = this.field9.method14(index13 * 20);
                        if (flag16 && (Boolean)this.field17.get() || !flag16 && (Boolean)this.field18.get()) {
                           number17 = ColorUtils.method18(number17, 0.5F);
                        }

                        float value18 = bridge10_26.bridge$getStringWidth(text14) - 1.0F + (flag8 ? 1.0F : 0.0F);
                        float value19 = bridge10_26.method19() - 2.0F + (flag8 ? 1.0F : 0.0F);
                        float value20 = 16.0F;
                        float value21 = this.field10.get() ? 0.75F : 1.0F;
                        float value22 = value18 * value21 > value20 - value12 * 2.0F && value18 * value21 <= value20 ? 0.0F : value12;
                        value21 = Math.max(0.5F, Math.min(value21, (value20 - value22 * 2.0F) / value18));
                        value18 *= value21;
                        value19 *= value21;
                        float value23 = data101.getX() + index13 * 20 + 3.0F;
                        float value24 = data101.getY() + 3.0F;
                        if (flag9) {
                           value23 += Math.round(value20 - value18) / 2.0F;
                           value24 += Math.round(value20 - value19) / 2.0F;
                        } else {
                           value23 += flag10 ? value20 - value18 - value22 : value22;
                           value24 += flag11 ? value20 - value19 - value12 : value12;
                        }

                        if (!Character.isDigit(text14.charAt(0)) && (Boolean)this.field11.get()) {
                           value24 -= 0.5F * value21;
                        }

                        mixinhelper_45.push();

                        try {
                           mixinhelper_45.method38(value23, value24, 301.0F);
                           mixinhelper_45.scale(value21, value21, 1.0F);
                           mixinhelper_45.method18(bridge10_26, text14, 0, 0, number17, flag8);
                        } finally {
                           mixinhelper_45.pop();
                        }
                     }
                  }
               }

               mixinhelper_45.method44(arg0 -> arg0.method29().method33());
            }
         }
      }
   }

   private void method13() {
      if (EventTick.field1 % 5 == 0) {
         Arrays.fill(this.field19, null);
         GameOptionsBridge mixinhelper2_81 = this.mc.bridge$getGameSettings();
         if (mixinhelper2_81 != null) {
            KeyBindingBridge[] items2 = mixinhelper2_81.bridge$getKeyBindings();
            if (items2 != null) {
               boolean flag3 = (Boolean)this.field11.get();

               for (KeyBindingBridge mixinhelper_157 : items2) {
                  if (mixinhelper_157 != null) {
                     int index8 = this.method4(mixinhelper_157.bridge$getUntranslatedKeyDescription());
                     if (index8 >= 0 && index8 < this.field19.length) {
                        KeyCode bridgetype_89 = mixinhelper_157.bridge$getKey();
                        if (bridgetype_89 != null && bridgetype_89 != KeyCode.KEY_NONE) {
                           String text10 = mixinhelper_157.bridge$getKeyName();
                           if (text10 != null && !text10.isBlank()) {
                              String text11 = bridgetype_89.getShortName(text10);
                              this.field19[index8] = flag3 ? text11.toLowerCase(Locale.ROOT) : text11.toUpperCase(Locale.ROOT);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private int method4(String text1) {
      if (text1 != null && text1.startsWith("key.hotbar.")) {
         try {
            return Integer.parseInt(text1.substring("key.hotbar.".length())) - 1;
         } catch (NumberFormatException numberformatexception3) {
            return -1;
         }
      } else {
         return -1;
      }
   }

   private enum Type implements OptionEnumValue {
      TOP_LEFT("topLeft"),
      BOTTOM_LEFT("bottomLeft"),
      TOP_RIGHT("topRight"),
      BOTTOM_RIGHT("bottomRight"),
      CENTER("center");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
