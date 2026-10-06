package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.List;
import java.util.Random;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockDebugRandomLore extends AbstractFeature {
   private static final String[] field8 = new String[]{
      "Ancient",
      "Cursed",
      "Mythic",
      "Glistening",
      "Forgotten",
      "Echoing",
      "Stoic",
      "Ravenous",
      "Soulbound",
      "Eldritch",
      "Resplendent",
      "Ironclad",
      "Slumbering",
      "Voidtouched",
      "Verdant"
   };
   private static final String[] field9 = new String[]{
      "Strength", "Crit Damage", "Ability Damage", "Magic Find", "Pet Luck", "Sea Creature Chance", "Intelligence", "Speed", "Mining Speed", "Foraging Fortune"
   };
   private static final NamedTextColor[] field10 = new NamedTextColor[]{
      NamedTextColor.GRAY, NamedTextColor.DARK_GRAY, NamedTextColor.GOLD, NamedTextColor.AQUA, NamedTextColor.LIGHT_PURPLE
   };
   private final IntegerOption field11 = (IntegerOption)((Data)((Data)OptionFactory.method4("debugRandomLoreLineCount")
            .method4(3))
         .method7(1, 10))
      .method31();

   public SkyblockDebugRandomLore(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.handle(EventRenderTooltipPre.class, this::method3);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_RANDOM_LORE";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field11});
   }

   private void method3(EventRenderTooltipPre data21) {
      ItemStackBridge bridgeextension_42 = (ItemStackBridge)data21.method1().orElse(null);
      long number3 = bridgeextension_42 == null ? 0L : bridgeextension_42.bridge$getDisplayName().hashCode();
      Random random5 = new Random(number3);
      int number6 = (Integer)this.field11.get();
      List list7 = data21.method3();

      for (int index8 = 0; index8 < number6; index8++) {
         Component component9 = this.method4(random5);
         list7.add((ClickableText)Bridge.method8().method89(component9));
      }

      data21.method2(list7);
   }

   private Component method4(Random random1) {
      String text2 = field8[random1.nextInt(field8.length)];
      String text3 = field9[random1.nextInt(field9.length)];
      int number4 = random1.nextInt(100) + 1;
      NamedTextColor namedtextcolor5 = field10[random1.nextInt(field10.length)];
      return ((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text("[debug] ", NamedTextColor.DARK_GRAY)
                     .append(Component.text(text2, namedtextcolor5)))
                  .append(Component.text(" ", NamedTextColor.GRAY)))
               .append(Component.text(text3, NamedTextColor.GRAY)))
            .append(Component.text(": ", NamedTextColor.GRAY)))
         .append(Component.text("+" + number4, NamedTextColor.GREEN));
   }
}
