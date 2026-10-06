package com.moonsworth.lunar.client.mod.skyblock.firefreezetimer;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.joml.Vector3d;

public class SkyblockFireFreezeTimer extends AbstractFeature {
   private final DungeonFloorListener field8 = (DungeonFloorListener)this.method63(DungeonFloorListener.class);
   private final DungeonScoreListener field9 = (DungeonScoreListener)this.method63(DungeonScoreListener.class);
   private static final Vector3d field10 = new Vector3d(1.5, 72.5, 1.5);
   private final EnumOption<NamedColorOption> field11 = (EnumOption<NamedColorOption>)OptionFactory.method10("timerColor", NamedColorOption.GREEN)
      .method31();
   private final IntegerOption field12 = (IntegerOption)((Data)((Data)OptionFactory.method4("decimalPlaces").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(0, 3))
      .method31();
   private HudTimer field13;

   public SkyblockFireFreezeTimer(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(
         ModTraits.field19,
         DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON && this.field8.method6().getNumber() == 3 && this.field9.method10())
      );
      this.handle(HudRenderLegacyEventAlt.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
      this.handle(EventWorldChange.class, this::method3);
   }

   private void method1(HudRenderLegacyEventAlt highlightimpl41) {
      if (this.field13 != null && this.field13.get() > 0L) {
         EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
         AbstractRenderContext bridgeextension_93 = highlightimpl41.method3();
         bridgeextension_93.push();
         bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
         double value4 = this.field13.get() / 1000.0;
         String text6 = String.format("%." + this.field12.get() + "f", value4) + "s";
         TextComponent text7 = Component.text(text6, TextComponentFactory.styleOf(this.field11).decorate(TextDecoration.BOLD));
         WorldRenderUtils.drawComponent(bridgeextension_93, text7, field10.x(), field10.y(), field10.z(), true, 1.0F, true);
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH().equals("[BOSS] The Professor: Oh? You found my Guardians' one weakness?")) {
         this.field13 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method2().method4().method5(5200L).method7().method2();
      }
   }

   private void method3(EventWorldChange data31) {
      this.field13 = null;
   }

   public String getId() {
      return "SKYBLOCK_FIRE_FREEZE_TIMER";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field11, this.field12})
      );
   }
}
