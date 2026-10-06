package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudComponent;
import com.moonsworth.lunar.client.ui.hud.TextureHudComponent;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.ItemStackHudComponent;
import com.moonsworth.lunar.client.ui.hud.BackgroundHudComponent;
import com.moonsworth.lunar.client.ui.hud.PaddedHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.mod.skyblock.debug.SkyblockDebugMod;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.render.color.AnimatedColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

public class SkyblockDebugHud extends AbstractFeature {
   private static final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "skyblock/hud/dolphin.png");

   public SkyblockDebugHud(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.method45(ModTraits.field1, MixinCore9Base.method7(0.0F, 0.0F, HudAnchor.TOP_RIGHT, WidgetFactory.withBackground(this.method13())));
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_HUD";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   private HudComponent method13() {
      return new HudComponentGroup(true, HudComponentGroup.field1)
         .method3(2.0F)
         .method5(new TextHudComponent(Component.text("Debug HUD", NamedTextColor.GOLD)).method14(1.2))
         .method5(
            new HudComponentGroup(HudComponentGroup.field2)
               .method3(3.0F)
               .method5(new TextureHudComponent(field8, 16.0F, 16.0F))
               .method5(new ItemStackHudComponent(Bridge.method28().method27()).method6(2.0))
               .method5(new TextHudComponent(Component.text("image + item + text", NamedTextColor.GRAY)))
         )
         .method6(HudComponentGroup.field1, new TextHudComponent(Component.text("left aligned")))
         .method6(HudComponentGroup.field2, new TextHudComponent(Component.text("centered")))
         .method6(HudComponentGroup.field3, new TextHudComponent(Component.text("right aligned")))
         .method5(new TextHudComponent(Component.text("obfuscated").style(Style.style().decorate(TextDecoration.OBFUSCATED).build())))
         .method5(new TextHudComponent(Component.text("chroma text")).method7(AnimatedColor.method12()))
         .method5(
            new BackgroundHudComponent(new PaddedHudComponent(new TextHudComponent(Component.text("chroma background"))).method2(4.0F))
               .method3(AnimatedColor.method12())
         );
   }
}
