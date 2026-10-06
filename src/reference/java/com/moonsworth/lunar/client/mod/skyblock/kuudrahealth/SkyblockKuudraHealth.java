package com.moonsworth.lunar.client.mod.skyblock.kuudrahealth;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin.KuudraBossHealth;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.NumberFormat;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3d;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockKuudraHealth extends AbstractFeature {
   private final KuudraBossHealth field8 = (KuudraBossHealth)this.method63(KuudraBossHealth.class);
   private static final Component field9 = Component.text('❤', NamedTextColor.RED);
   private static final NumberFormat field10 = NumberFormat.getIntegerInstance(Locale.ROOT);

   public SkyblockKuudraHealth(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.CRIMSON_ISLE));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.KUUDRA));
      this.handle(HudRenderLegacyEventAlt.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_KUUDRA_HEALTH";
   }

   private void method1(HudRenderLegacyEventAlt highlightimpl41) {
      if (this.field8.method9() != null) {
         float value2 = this.field8.getHealth();
         int number3 = this.field8.method5();
         Component component4 = ((TextComponent)Component.text(field10.format(value2) + " / " + field10.format(number3), NamedTextColor.YELLOW).appendSpace())
            .append(field9);
         EntityRenderDispatcherBridge bridge2_435 = Ref.method13();
         Vector3d vector3d6 = this.field8.method6(highlightimpl41.method5());
         double value7 = vector3d6.x() - bridge2_435.bridge$renderPosX();
         double value9 = vector3d6.y() - bridge2_435.bridge$renderPosY() - 71.5;
         double value11 = vector3d6.z() - bridge2_435.bridge$renderPosZ();
         Bridge.method14().method5(Ref.method10(), component4, value7 / 5.0, value9 / 5.0, value11 / 5.0, 0.0F, false, false, this.field8.method9());
      }
   }
}
