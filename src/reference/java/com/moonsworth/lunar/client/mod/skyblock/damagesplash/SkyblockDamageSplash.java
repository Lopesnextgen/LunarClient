package com.moonsworth.lunar.client.mod.skyblock.damagesplash;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;

public class SkyblockDamageSplash extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^(\\d{1,3}(,\\d{3})*|\\d+)$");
   private static final Pattern field9 = Pattern.compile("^[✧✯](\\d{1,3}(,\\d{3})*|\\d+)[✧✯]$");
   private static final Pattern field10 = Pattern.compile("[✧❤✯, ]");
   private static final List<NamedTextColor> field11 = List.of(NamedTextColor.YELLOW, NamedTextColor.WHITE, NamedTextColor.RED, NamedTextColor.GOLD);
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("truncateDamageSplash").method4(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideNonCrits").method4(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)OptionFactory.method7("hideAllDamageSplash").method31();
   private final EnumOption<NamedColorOption> field15 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "damageSplashCustomColor", NamedColorOption.OFF
      )
      .method31();

   public SkyblockDamageSplash(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventEntitySpawn.class, this::method1);
   }

   public void method1(EventEntitySpawn highlightimpl6_21) {
      BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
      if (bridgeextension2 instanceof EntityArmorStandBridge bridgeextension_23) {
         if (bridgeextension_23.bridge$isMarker()) {
            Component component4 = bridgeextension2.bridge$getCustomName();
            if (component4 != null) {
               String text5 = TextBridge.getTextContent(component4);
               Matcher matcher6 = field8.matcher(text5);
               boolean flag7 = matcher6.find();
               if (!flag7 || !(Boolean)this.field13.get() && !(Boolean)this.field14.get()) {
                  if (flag7 && (Boolean)this.field12.get()) {
                     int number12 = Integer.parseInt(text5.replaceAll(field10.pattern(), ""));
                     Component component13 = Component.text(this.method2(number12, false))
                        .style(
                           Style.empty()
                              .color(
                                 (TextColor)(this.field15.get() != NamedColorOption.OFF
                                    ? ((NamedColorOption)this.field15.get()).getColor().getAdventureColor()
                                    : component4.color())
                              )
                        );
                     bridgeextension2.bridge$setCustomName(component13);
                  } else {
                     matcher6 = field9.matcher(text5);
                     boolean flag8 = matcher6.find();
                     if (flag8 && (Boolean)this.field14.get()) {
                        bridgeextension2.bridge$setCustomNameVisible(false);
                     } else if (flag8 && (Boolean)this.field12.get()) {
                        int number9 = Integer.parseInt(text5.replaceAll(field10.pattern(), ""));
                        Component component10 = this.field15.get() != NamedColorOption.OFF
                           ? Component.text(this.method2(number9, true))
                              .style(Style.empty().color(((NamedColorOption)this.field15.get()).getColor().getAdventureColor()))
                           : this.method3(this.method2(number9, true));
                        bridgeextension2.bridge$setCustomName(component10);
                     } else {
                        if ((flag7 || flag8) && this.field15.get() != NamedColorOption.OFF) {
                           bridgeextension2.bridge$setCustomName(
                              Component.text(text5).style(Style.empty().color(((NamedColorOption)this.field15.get()).getColor().getAdventureColor()))
                           );
                        }
                     }
                  }
               } else {
                  bridgeextension2.bridge$setCustomNameVisible(false);
               }
            }
         }
      }
   }

   private String method2(int number1, boolean flag2) {
      String text3 = NumberUtils.method10(number1);
      return flag2 ? "✧" + text3 + "✧ " : text3;
   }

   private Component method3(String text1) {
      Object obj2 = Component.empty();
      int index3 = 0;

      for (int index4 = 0; index4 < text1.length(); index4++) {
         if (text1.charAt(index4) == ' ') {
            obj2 = obj2.append(Component.text(' '));
         } else {
            obj2 = obj2.append(Component.text(text1.charAt(index4)).color((TextColor)field11.get(index3 % field11.size())));
            index3++;
         }
      }

      return (Component)obj2;
   }

   public String getId() {
      return "SKYBLOCK_DAMAGE_SPLASH";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field12, this.field13, this.field14, this.field15});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
