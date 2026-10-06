package com.moonsworth.lunar.client.mod.skyblock.starredmobhighlight;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
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
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.player.EventPlayerRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class StarredMobHighlight extends AbstractFeature {
   private final Set<StarredMobHighlight.Data> field8 = new HashSet<>();
   private final Pattern field9 = Pattern.compile("^(?<mobTypes>[^ ]+ )?✯[A-Za-z ]+([\\d.,]+[kmbKMB]?)❤$");
   private final ColorOption field10 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "starredMobHighlightColor"
         )
         .method4(872393216))
      .method31();

   public StarredMobHighlight(Skyblock skyblock1) {
      super(false);
      this.method7(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method7(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method7(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.method50(this::onEnable);
      this.method8(this::onDisable);
      this.handle(EventEntitySpawn.class, this::method1);
      this.handle(HudRenderLegacyEvent.class, this::method4);
      this.handle(EventEntityRemove.class, arg1x -> this.method3(arg1x.method1()));
      this.handle(EventPlayerRemove.class, arg1x -> this.method3(arg1x.method1()));
      this.handle(EventWorldChange.class, arg1x -> this.field8.clear());
   }

   private void onEnable() {
      if (Ref.method8() != null) {
         Ref.method8().bridge$getEntities().forEach(this::method2);
      }
   }

   private void onDisable() {
      this.field8.clear();
   }

   private void method1(EventEntitySpawn highlightimpl6_21) {
      this.method2(highlightimpl6_21.field1);
   }

   private void method2(BridgeExtension bridgeextension1) {
      Component component2 = bridgeextension1.bridge$getCustomName();
      if (component2 != null) {
         String text3 = TextBridge.getTextContent(component2);
         Matcher matcher4 = this.field9.matcher(text3);
         if (matcher4.matches()) {
            WorldBridgeExtension itemcounter6extension5 = Ref.method8();
            if (itemcounter6extension5 != null) {
               BridgeExtension bridgeextension6 = EntityFinder.findNearest(bridgeextension1, itemcounter6extension5.bridge$getEntities());
               if (bridgeextension6 != null) {
                  this.field8.add(new StarredMobHighlight.Data(bridgeextension1, bridgeextension6));
               }
            }
         }
      }
   }

   private void method3(BridgeExtension bridgeextension1) {
      this.field8.removeIf(arg1x -> arg1x.method1().equals(bridgeextension1) || arg1x.method2().equals(bridgeextension1));
   }

   private void method4(HudRenderLegacyEvent highlightimpl21) {
      this.field8.removeIf(arg0 -> {
         AxisAlignedBBBridge horsestats121x = arg0.method2().bridge$getBoundingBox();
         return horsestats121x.bridge$getMaxX() - horsestats121x.bridge$getMinX() < 0.25;
      });
      if (!this.field8.isEmpty()) {
         AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
         EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
         bridgeextension_92.push();
         bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
         DrawBufferBridge bridge2_324 = bridgeextension_92.method10(LunarRenderTypes.field15);
         bridge2_324.method1();

         for (StarredMobHighlight.Data data6 : this.field8) {
            BridgeExtension bridgeextension7 = data6.method2();
            if (!bridgeextension7.bridge$isInvisible()) {
               AxisAlignedBBBridge horsestats128 = bridgeextension7.method11(highlightimpl21.method5());
               WorldRenderUtils.fillBox(
                  bridge2_324,
                  horsestats128.bridge$getMinX(),
                  horsestats128.bridge$getMinY(),
                  horsestats128.bridge$getMinZ(),
                  horsestats128.bridge$getMaxX(),
                  horsestats128.bridge$getMaxY(),
                  horsestats128.bridge$getMaxZ(),
                  this.field10.method1(0.0F)
               );
            }
         }

         bridge2_324.method17(BufferMode.BATCHED);
         BufferBuilderBridge bridge_2810 = bridgeextension_92.method11((Float)Ref.method4().method40().method82().method19().get());

         for (StarredMobHighlight.Data data12 : this.field8) {
            BridgeExtension bridgeextension13 = data12.method2();
            if (!bridgeextension13.bridge$isInvisible()) {
               AxisAlignedBBBridge horsestats129 = bridgeextension13.method11(highlightimpl21.method5());
               WorldRenderUtils.drawBoxOutline(
                  bridge_2810,
                  horsestats129.bridge$getMinX(),
                  horsestats129.bridge$getMinY(),
                  horsestats129.bridge$getMinZ(),
                  horsestats129.bridge$getMaxX(),
                  horsestats129.bridge$getMaxY(),
                  horsestats129.bridge$getMaxZ(),
                  ColorUtils.method31(this.field10.method1(0.0F))
               );
            }
         }

         bridge_2810.end();
         bridgeextension_92.pop();
      }
   }

   public String getId() {
      return "STARRED_MOB_HIGHLIGHT";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10});
   }

   private static class Data {
      private final BridgeExtension field1;
      private final BridgeExtension field2;

      @Generated
      public BridgeExtension method1() {
         return this.field1;
      }

      @Generated
      public BridgeExtension method2() {
         return this.field2;
      }

      @Generated
      @Override
      public boolean equals(Object obj1) {
         if (obj1 == this) {
            return true;
         } else if (!(obj1 instanceof StarredMobHighlight.Data data2)) {
            return false;
         } else if (!data2.canEqual(this)) {
            return false;
         } else {
            BridgeExtension bridgeextension3 = this.method1();
            BridgeExtension bridgeextension4 = data2.method1();
            if (bridgeextension3 == null ? bridgeextension4 == null : bridgeextension3.equals(bridgeextension4)) {
               BridgeExtension bridgeextension5 = this.method2();
               BridgeExtension bridgeextension6 = data2.method2();
               return bridgeextension5 == null ? bridgeextension6 == null : bridgeextension5.equals(bridgeextension6);
            } else {
               return false;
            }
         }
      }

      @Generated
      protected boolean canEqual(Object obj1) {
         return obj1 instanceof StarredMobHighlight.Data;
      }

      @Generated
      @Override
      public int hashCode() {
         byte number1 = 59;
         int number2 = 1;
         BridgeExtension bridgeextension3 = this.method1();
         number2 = number2 * 59 + (bridgeextension3 == null ? 43 : bridgeextension3.hashCode());
         BridgeExtension bridgeextension4 = this.method2();
         return number2 * 59 + (bridgeextension4 == null ? 43 : bridgeextension4.hashCode());
      }

      @Generated
      public Data(BridgeExtension bridgeextension1, BridgeExtension bridgeextension2) {
         this.field1 = bridgeextension1;
         this.field2 = bridgeextension2;
      }
   }
}
