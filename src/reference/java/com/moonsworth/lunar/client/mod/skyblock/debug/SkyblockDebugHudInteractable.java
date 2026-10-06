package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Task;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockDebugHudInteractable extends AbstractFeature {
   private final MixinCore5Task field8;
   private int field9;
   private int field10;

   public SkyblockDebugHudInteractable(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.field8 = this.method13();
      this.method45(ModTraits.field1, MixinCore9Base.method7(0.0F, 0.0F, HudAnchor.MIDDLE_RIGHT, WidgetFactory.withBackground(this.field8)));
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_HUD_INTERACTABLE";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   private MixinCore5Task method13() {
      HudComponentGroup mixincore5iterator1 = new HudComponentGroup(true, HudComponentGroup.field1)
         .method3(2.0F)
         .method5(new TextHudComponent(Component.text("Interactable", NamedTextColor.GOLD)).method14(1.2))
         .method5(new TextHudComponent(() -> "Clicks: " + this.field9))
         .method5(new TextHudComponent(() -> "Hover events: " + this.field10))
         .method5(new TextHudComponent(() -> "Hovered: " + (this.field8 != null && this.field8.method18())));
      return new MixinCore5Task(mixincore5iterator1)
         .method3(List.of("Left click to increment", "Right click to reset"))
         .method10(() -> this.field9++)
         .method11(() -> this.field9 = 0)
         .method13(() -> this.field10++)
         .method14(() -> this.field10++);
   }
}
