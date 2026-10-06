package com.moonsworth.lunar.client.mod.skyblock.professorguardianhp;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class SkyblockProfessorGuardianHp extends AbstractFeature {
   private final DungeonFloorListener field8 = (DungeonFloorListener)this.method63(DungeonFloorListener.class);
   private final DungeonScoreListener field9 = (DungeonScoreListener)this.method63(DungeonScoreListener.class);
   private static final Pattern field10 = Pattern.compile("^(?<mobTypes>[^ ]+ )?(Chaos|Healthy|Reinforced|Laser) Guardian (?<hp>[\\dkM,.]+❤)$");
   private final HashMap<BridgeExtension, Component> field11 = new HashMap<>();

   public SkyblockProfessorGuardianHp(Skyblock skyblock1) {
      super(false);
      this.method4(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method4(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method4(
         ModTraits.field19,
         DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON && this.field8.method6().getNumber() == 3 && this.field9.method10())
      );
      this.handle(EventEntitySpawn.class, this::method2);
      this.handle(HudRenderLegacyEventAlt.class, this::method3);
      this.handle(EventRenderEntity.class, this::method4);
      this.handle(EventRenderNameTag.class, this::method5);
      this.handle(EventEntityRemove.class, this::method6);
      this.handle(EventWorldChange.class, this::method7);
      this.method51(this::onDisable);
      this.method50(this::onEnable);
   }

   private void onEnable() {
      if (Ref.method8() != null) {
         Ref.method8().bridge$getEntities().forEach(this::method1);
      }
   }

   private void onDisable() {
      this.field11.clear();
   }

   private void method1(BridgeExtension bridgeextension1) {
      if (bridgeextension1 instanceof EntityArmorStandBridge) {
         Component component2 = bridgeextension1.bridge$getCustomName();
         if (component2 != null) {
            String text3 = TextBridge.getTextContent(bridgeextension1.bridge$getCustomName()).trim();
            Matcher matcher4 = field10.matcher(text3);
            if (matcher4.matches()) {
               String text5 = TextBridge.asLegacyString(component2);
               text5 = text5.substring(text5.lastIndexOf(32) + 1);
               TextComponent text6 = TextBridge.asAdventure(text5);
               this.field11.put(bridgeextension1, text6);
            }
         }
      }
   }

   private void method2(EventEntitySpawn highlightimpl6_21) {
      this.method1(highlightimpl6_21.field1);
   }

   private void method3(HudRenderLegacyEventAlt highlightimpl41) {
      if (!this.field11.isEmpty()) {
         EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
         float value3 = highlightimpl41.method5();

         for (Entry entry5 : this.field11.entrySet()) {
            BridgeExtension bridgeextension6 = (BridgeExtension)entry5.getKey();
            Component component7 = (Component)entry5.getValue();
            double value8 = bridgeextension6.method8(value3) - bridge2_432.bridge$renderPosX();
            double value10 = bridgeextension6.method9(value3) - bridge2_432.bridge$renderPosY();
            double value12 = bridgeextension6.method10(value3) - bridge2_432.bridge$renderPosZ();
            double value14 = 5.0;
            Bridge.method14().method5(Ref.method10(), component7, value8 / value14, value10 / value14, value12 / value14, 0.0F, false, false, bridgeextension6);
         }
      }
   }

   private void method4(EventRenderEntity data81) {
      if (!this.field11.isEmpty()) {
         BridgeExtension bridgeextension2 = data81.method1();
         if (bridgeextension2 instanceof EntityArmorStandBridge) {
            if (this.field11.containsKey(bridgeextension2)) {
               data81.setCancelled(true);
            }
         }
      }
   }

   private void method5(EventRenderNameTag highlightimpl111) {
      if (!this.field11.isEmpty()) {
         Component component2 = highlightimpl111.method3();
         if (component2 != null) {
            String text3 = TextBridge.getTextContent(component2);
            if (field10.matcher(text3).matches()) {
               highlightimpl111.setCancelled(true);
            }
         }
      }
   }

   private void method6(EventEntityRemove highlightimpl121) {
      if (!this.field11.isEmpty()) {
         BridgeExtension bridgeextension2 = highlightimpl121.method1();
         if (bridgeextension2 instanceof EntityArmorStandBridge) {
            this.field11.remove(bridgeextension2);
         }
      }
   }

   private void method7(EventWorldChange data31) {
      this.field11.clear();
   }

   public String getId() {
      return "SKYBLOCK_PROFESSOR_GUARDIAN_HP";
   }
}
