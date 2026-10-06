package com.moonsworth.lunar.client.mod.skyblock.vampireslayer;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.EntityFinder;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.player.EventPlayerRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.Optional;
import net.kyori.adventure.text.TextComponent;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockVampireSlayer extends AbstractFeature {
   private static final String field8 = "ewogICJ0aW1lc3RhbXAiIDogMTYxNTg4ODAwMDU1MywKICAicHJvZmlsZUlkIiA6ICI5ZDIyZGRhOTVmZGI0MjFmOGZhNjAzNTI1YThkZmE4ZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJTYWZlRHJpZnQ0OCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jMDM0MDkyM2E2ZGU0ODI1YTE3NjgxM2QxMzM1MDNlZmYxODZkYjA4OTZlMzJiNjcwNDkyOGMyYTJiZjY4NDIyIgogICAgfQogIH0KfQ==";
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("vampireSteakDisplay").method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("vampireIchorDisplay").method31();
   private final HashSet<BridgeExtension> field11 = new HashSet<>();
   private final HashSet<BridgeExtension> field12 = new HashSet<>();
   private final HashSet<BridgeExtension> field13 = new HashSet<>();

   public SkyblockVampireSlayer(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.SLAYER));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.RIFT));
      this.method51(this::clear);
      this.handle(EventEntitySpawn.class, arg1x -> {
         this.method2(arg1x);
         this.method3(arg1x);
      });
      this.handle(HudRenderLegacyEvent.class, arg1x -> {
         this.method5(arg1x);
         this.method6(arg1x);
      });
      this.handle(EventEntityRemove.class, arg1x -> this.method4(arg1x.method1()));
      this.handle(EventPlayerRemove.class, arg1x -> this.method4(arg1x.method1()));
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9, this.field10});
   }

   public String getId() {
      return "SKYBLOCK_VAMPIRE_SLAYER";
   }

   private void clear() {
      this.field12.clear();
      this.field13.clear();
      this.field11.clear();
   }

   private void method2(EventEntitySpawn highlightimpl6_21) {
      if ((Boolean)this.field9.get()) {
         if (!this.field11.contains(highlightimpl6_21.field1)) {
            if (highlightimpl6_21.field1.bridge$getCustomName() instanceof TextComponent text2) {
               String text6 = TextBridge.getTextContent(text2);
               if (text6.contains("҉") && text6.contains("Bloodfiend")) {
                  WorldBridgeExtension itemcounter6extension4 = Ref.method8();
                  if (itemcounter6extension4 == null) {
                     return;
                  }

                  BridgeExtension bridgeextension5 = EntityFinder.findNearest(
                     highlightimpl6_21.field1,
                     itemcounter6extension4.bridge$getEntities(),
                     arg0 -> arg0 instanceof Bridge5Extension2 bridge5extension21x ? bridge5extension21x.bridge$getName().equals("Bloodfiend ") : false
                  );
                  if (bridgeextension5 == null) {
                     return;
                  }

                  this.field12.add(bridgeextension5);
                  this.field11.add(highlightimpl6_21.field1);
               }
            }
         }
      }
   }

   private void method3(EventEntitySpawn highlightimpl6_21) {
      if ((Boolean)this.field10.get()) {
         if (highlightimpl6_21.field1 instanceof EntityArmorStandBridge bridgeextension_22) {
            ItemStackBridge bridgeextension_45 = bridgeextension_22.bridge$getHelmet();
            if (bridgeextension_45 == null) {
               return;
            }

            Optional optional4 = SkyblockItemUtil.method11(bridgeextension_45);
            if (optional4.isPresent()
               && ((String)optional4.get())
                  .equals(
                     "ewogICJ0aW1lc3RhbXAiIDogMTYxNTg4ODAwMDU1MywKICAicHJvZmlsZUlkIiA6ICI5ZDIyZGRhOTVmZGI0MjFmOGZhNjAzNTI1YThkZmE4ZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJTYWZlRHJpZnQ0OCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jMDM0MDkyM2E2ZGU0ODI1YTE3NjgxM2QxMzM1MDNlZmYxODZkYjA4OTZlMzJiNjcwNDkyOGMyYTJiZjY4NDIyIgogICAgfQogIH0KfQ=="
                  )) {
               this.field13.add(highlightimpl6_21.field1);
            }
         }
      }
   }

   private void method4(BridgeExtension bridgeextension1) {
      this.field12.remove(bridgeextension1);
      this.field11.remove(bridgeextension1);
      this.field13.remove(bridgeextension1);
   }

   private void method5(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field9.get()) {
         this.field12.removeIf(arg0 -> {
            AxisAlignedBBBridge horsestats121x = arg0.bridge$getBoundingBox();
            return horsestats121x.bridge$getMaxX() - horsestats121x.bridge$getMinX() < 0.25;
         });
         if (!this.field12.isEmpty()) {
            AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
            EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
            bridgeextension_92.push();
            bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
            DrawBufferBridge bridge2_324 = bridgeextension_92.method10(LunarRenderTypes.field15);
            bridge2_324.method1();

            for (BridgeExtension bridgeextension6 : this.field12) {
               AxisAlignedBBBridge horsestats127 = bridgeextension6.method11(highlightimpl21.method5());
               WorldRenderUtils.fillBox(
                  bridge2_324,
                  horsestats127.bridge$getMinX(),
                  horsestats127.bridge$getMinY(),
                  horsestats127.bridge$getMinZ(),
                  horsestats127.bridge$getMaxX(),
                  horsestats127.bridge$getMaxY(),
                  horsestats127.bridge$getMaxZ(),
                  570490879
               );
            }

            bridge2_324.method17(BufferMode.BATCHED);
            BufferBuilderBridge bridge_289 = bridgeextension_92.method11(1.0F);

            for (BridgeExtension bridgeextension11 : this.field12) {
               AxisAlignedBBBridge horsestats128 = bridgeextension11.method11(highlightimpl21.method5());
               WorldRenderUtils.drawBoxOutline(
                  bridge_289,
                  horsestats128.bridge$getMinX(),
                  horsestats128.bridge$getMinY(),
                  horsestats128.bridge$getMinZ(),
                  horsestats128.bridge$getMaxX(),
                  horsestats128.bridge$getMaxY(),
                  horsestats128.bridge$getMaxZ(),
                  -16711681
               );
            }

            bridge_289.end();
            bridgeextension_92.pop();
         }
      }
   }

   private void method6(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field10.get()) {
         if (!this.field13.isEmpty()) {
            AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
            EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
            bridgeextension_92.push();
            bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
            DrawBufferBridge bridge2_324 = bridgeextension_92.method10(LunarRenderTypes.field15);
            bridge2_324.method1();

            for (BridgeExtension bridgeextension6 : this.field13) {
               double value7 = bridgeextension6.method3() + (bridgeextension6.bridge$getPosX() - bridgeextension6.method3()) * highlightimpl21.method5();
               double value9 = bridgeextension6.method4() + (bridgeextension6.bridge$getPosY() - bridgeextension6.method4()) * highlightimpl21.method5();
               double value11 = bridgeextension6.method5() + (bridgeextension6.bridge$getPosZ() - bridgeextension6.method5()) * highlightimpl21.method5();
               AxisAlignedBBBridge horsestats1213 = AxisAlignedBBBridge.method2(value7 - 0.5, value9 + 1.0, value11 - 0.5, value7 + 0.5, value9 + 2.0, value11 + 0.5);
               WorldRenderUtils.fillBox(
                  bridge2_324,
                  horsestats1213.bridge$getMinX(),
                  horsestats1213.bridge$getMinY(),
                  horsestats1213.bridge$getMinZ(),
                  horsestats1213.bridge$getMaxX(),
                  horsestats1213.bridge$getMaxY(),
                  horsestats1213.bridge$getMaxZ(),
                  570490879
               );
            }

            bridge2_324.method17(BufferMode.BATCHED);
            BufferBuilderBridge bridge_2815 = bridgeextension_92.method11(1.0F);

            for (BridgeExtension bridgeextension17 : this.field13) {
               double value8 = bridgeextension17.method3() + (bridgeextension17.bridge$getPosX() - bridgeextension17.method3()) * highlightimpl21.method5();
               double value10 = bridgeextension17.method4() + (bridgeextension17.bridge$getPosY() - bridgeextension17.method4()) * highlightimpl21.method5();
               double value12 = bridgeextension17.method5() + (bridgeextension17.bridge$getPosZ() - bridgeextension17.method5()) * highlightimpl21.method5();
               AxisAlignedBBBridge horsestats1214 = AxisAlignedBBBridge.method2(value8 - 0.5, value10 + 1.0, value12 - 0.5, value8 + 0.5, value10 + 2.0, value12 + 0.5);
               WorldRenderUtils.drawBoxOutline(
                  bridge_2815,
                  horsestats1214.bridge$getMinX(),
                  horsestats1214.bridge$getMinY(),
                  horsestats1214.bridge$getMinZ(),
                  horsestats1214.bridge$getMaxX(),
                  horsestats1214.bridge$getMaxY(),
                  horsestats1214.bridge$getMaxZ(),
                  -16711681
               );
            }

            bridge_2815.end();
            bridgeextension_92.pop();
         }
      }
   }
}
