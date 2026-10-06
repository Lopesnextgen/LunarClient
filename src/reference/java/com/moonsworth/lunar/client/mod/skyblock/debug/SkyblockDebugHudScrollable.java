package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudComponent;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.ScrollableHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.config.option.ConstantName;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockDebugHudScrollable extends AbstractFeature {
   private static final int field8 = 15;
   private static final float field9 = 70.0F;

   public SkyblockDebugHudScrollable(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.method45(ModTraits.field1, MixinCore9Base.method7(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, WidgetFactory.withBackground(this.method13())));
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_HUD_SCROLLABLE";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   private HudComponent method13() {
      HudComponentGroup mixincore5iterator1 = new HudComponentGroup(true, HudComponentGroup.field1).method3(2.0F);

      for (int index2 = 1; index2 <= 15; index2++) {
         mixincore5iterator1.method5(new TextHudComponent(Component.text("Row " + index2 + " of 15", NamedTextColor.GRAY)));
      }

      ScrollableHudComponent mixincore5impl53 = new ScrollableHudComponent(mixincore5iterator1, true).method1(70.0F).method3(true);
      return new HudComponentGroup(true, HudComponentGroup.field1)
         .method3(3.0F)
         .method5(new TextHudComponent(Component.text("Scrollable", NamedTextColor.GOLD)).method14(1.2))
         .method5(mixincore5impl53);
   }
}
