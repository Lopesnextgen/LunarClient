package com.moonsworth.lunar.client.mod.skyblock.enchantdisplay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.Gui;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Locale;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class SkyblockEnchantDisplay extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showItemEnchant")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showBookEnchant")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showUltimateEnchant")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showEnchant")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showEnchantOnlyOnBook")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field13 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("ultimateEnchantColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43521))
      .method31();
   private final ColorOption field14 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("enchantColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141121))
      .method31();
   private final EnumOption<NotificationAnchor> field15 = (EnumOption<NotificationAnchor>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "position", NotificationAnchor.TOP_LEFT
      )
      .method31();
   private final FloatOption field16 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "scale"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.6666667F))
         .method8(0.25F, 1.0F))
      .method31();

   public SkyblockEnchantDisplay(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize.ItemStackSize.class, this::method1);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize.ItemStackSize data1) {
      ItemStackBridge bridgeextension_42 = data1.getItem();
      Object2IntOpenHashMap object2intopenhashmap3 = SkyblockItemUtil.method29(bridgeextension_42);
      if (!object2intopenhashmap3.isEmpty()) {
         ItemBridge bridge6_44 = bridgeextension_42.bridge$getItem();
         if (bridge6_44 == Bridge.method28().method24()) {
            if (!(Boolean)this.field9.get()) {
               return;
            }

            String text5 = this.method2(object2intopenhashmap3, true);
            if (text5 != null) {
               this.method3(text5, data1);
            }
         } else {
            if (!(Boolean)this.field8.get()) {
               return;
            }

            String text6 = this.method2(object2intopenhashmap3, false);
            if (text6 != null) {
               this.method3(text6, data1);
            }
         }
      }
   }

   @Nullable
   private String method2(Object2IntOpenHashMap<String> object2intopenhashmap1, boolean flag2) {
      if ((Boolean)this.field10.get()) {
         ObjectIterator objectiterator3 = object2intopenhashmap1.keySet().iterator();

         while (objectiterator3.hasNext()) {
            String text4 = (String)objectiterator3.next();
            if (text4.startsWith("ultimate_")) {
               return text4;
            }
         }
      }

      return !this.field11.get() || object2intopenhashmap1.size() != 1 || !flag2 && this.field12.get() ? null : (String)object2intopenhashmap1.keySet().iterator().next();
   }

   private void method3(String text1, com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize.ItemStackSize data2) {
      Map map3 = ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method26();
      if (map3 != null) {
         Gui gui4 = (Gui)map3.get(text1.toUpperCase(Locale.ROOT));
         if (gui4 != null) {
            String text5 = gui4.method5();
            MixinHelper_4 mixinhelper_46 = data2.method2();
            mixinhelper_46.push();
            float value7 = Ref.method10().bridge$getStringWidth(text5);
            float value8 = (Float)this.field16.get();
            NotificationAnchor gui2extension9 = (NotificationAnchor)this.field15.get();
            float value10 = gui2extension9 != NotificationAnchor.TOP_RIGHT && gui2extension9 != NotificationAnchor.BOTTOM_RIGHT ? 0.0F : 17.0F - value7 * value8;
            float value11 = gui2extension9 != NotificationAnchor.BOTTOM_LEFT && gui2extension9 != NotificationAnchor.BOTTOM_RIGHT ? 0.0F : 10.0F;
            mixinhelper_46.method39(data2.getX() + value10, data2.getY() + value11);
            mixinhelper_46.method40(value8, value8);
            int number12 = gui4.method2() ? this.field13.method14(0.0F) : this.field14.method14(0.0F);
            mixinhelper_46.method18(Ref.method10(), text5, 0, 0, number12, true);
            mixinhelper_46.pop();
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field8, this.field9});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, arg1xx -> arg1xx.method9(new ClientOption[]{this.field12}));
         arg1x.method9(new ClientOption[]{this.field10, this.field15, this.field16});
      });
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field14, this.field13})
      );
   }

   public String getId() {
      return "SKYBLOCK_ENCHANT_DISPLAY";
   }
}
