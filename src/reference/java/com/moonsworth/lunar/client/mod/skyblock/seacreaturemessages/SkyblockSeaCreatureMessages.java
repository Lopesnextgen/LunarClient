package com.moonsworth.lunar.client.mod.skyblock.seacreaturemessages;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.SeaCreature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers.SeaCreatureRegistry;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockSeaCreatureMessages extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("compactDoubleHook").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final TextOption field9 = (TextOption)((Data)OptionFactory.method12("doubleHookText")
         .method2("DOUBLE HOOK!"))
      .method31();
   private final ColorOption field10 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "doubleHookColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method15()
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("boldDoubleHook").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSpawnMessage").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("shortSpawnMessage").method31();
   private final ToggleOption field14 = (ToggleOption)OptionFactory.method7("forceNotBold").method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("changeSpawnMessageColor").method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "spawnMessageColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method15()
      .method31();
   private boolean field17;

   public SkyblockSeaCreatureMessages(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method1);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      if ((Boolean)this.field8.get() && text2.startsWith("It's a Double Hook!")) {
         data1.cancel();
         this.field17 = true;
      } else {
         SeaCreatureRegistry rewindhandlers23 = Ref.method4().method40().method82().method15().method39();
         if (rewindhandlers23 != null) {
            SeaCreature rewindhandlers4 = (SeaCreature)rewindhandlers23.method2().get(text2);
            if (rewindhandlers4 != null) {
               if (!(Boolean)this.field12.get()) {
                  data1.cancel();
               } else {
                  data1.OHROCHICOIOICHOCRROORRCIIICIHO(this.method2(data1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI(), rewindhandlers4));
                  this.field17 = false;
               }
            }
         }
      }
   }

   private TextComponent method2(Component component1, SeaCreature rewindhandlers2) {
      Builder builder3 = Component.text();
      if ((Boolean)this.field8.get() && this.field17) {
         TextComponent text4 = Component.text((String)this.field9.get(), TextColor.color(this.field10.method14(0.0F)));
         if ((Boolean)this.field11.get()) {
            text4 = (TextComponent)text4.decorate(TextDecoration.BOLD);
         }

         ((Builder)builder3.append(text4)).appendSpace();
      }

      Object obj8 = component1;
      if ((Boolean)this.field13.get()) {
         String text6 = rewindhandlers2.name();
         String text7 = text6.toLowerCase(Locale.ROOT);
         String text5;
         if (text7.startsWith("the")) {
            text5 = this.method14("caught", new Object[]{text6});
         } else if (this.method3(text7.charAt(0))) {
            text5 = this.method14("caughtAn", new Object[]{text6});
         } else {
            text5 = this.method14("caughtA", new Object[]{text6});
         }

         obj8 = Component.text(text5);
      }

      if ((Boolean)this.field15.get()) {
         obj8 = obj8.color(TextColor.color(this.field16.method14(0.0F)));
      }

      if ((Boolean)this.field14.get()) {
         obj8 = obj8.decoration(TextDecoration.BOLD, false);
      }

      return (TextComponent)((Builder)builder3.append((Component)obj8)).build();
   }

   private boolean method3(char character1) {
      return "aeiou".indexOf(character1) >= 0;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field8, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field9, this.field11, this.field10})
            );
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12, this.field13, this.field14});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field15, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16}));
         }
      );
   }

   public String getId() {
      return "SKYBLOCK_SEA_CREATURE_MESSAGES";
   }
}
