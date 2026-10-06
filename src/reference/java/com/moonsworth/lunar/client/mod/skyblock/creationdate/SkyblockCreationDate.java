package com.moonsworth.lunar.client.mod.skyblock.creationdate;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockCreationDate extends AbstractFeature {
   private static final DateTimeFormatter field8 = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a");
   private static final DateTimeFormatter field9 = DateTimeFormatter.ofPattern("M/d/yy h:mm a").withZone(ZoneId.of("America/Montreal"));

   public SkyblockCreationDate(Skyblock skyblock1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.ITEMS));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventRenderTooltipPre.class, this::method1);
   }

   public String getId() {
      return "SKYBLOCK_CREATION_DATE";
   }

   private void method1(EventRenderTooltipPre data21) {
      ItemStackBridge bridgeextension_42 = (ItemStackBridge)data21.HHCOCHOIIIOOROCORRRRORORIOOHRC().orElse(null);
      if (bridgeextension_42 != null) {
         CompoundTagComponent mixinhelper_103 = (CompoundTagComponent)bridgeextension_42.bridge$getDataComponent(DataComponentTypes.field1);
         if (mixinhelper_103 != null) {
            CompoundTagBridge bridge_574 = mixinhelper_103.bridge$getData();
            if (bridge_574 != null) {
               List list5 = data21.method3();
               if (bridge_574.bridge$contains("timestamp", 4)) {
                  long number6 = bridge_574.bridge$getLong("timestamp");
                  ZonedDateTime zoneddatetime8 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(number6), ZoneId.systemDefault());
                  TextComponent text9 = (TextComponent)Component.text("Creation Date: " + zoneddatetime8.format(field8)).color(NamedTextColor.GRAY);
                  list5.add((ClickableText)Bridge.method8().method89(text9));
                  data21.method2(list5);
               } else if (bridge_574.bridge$contains("timestamp", 8)) {
                  String text11 = bridge_574.bridge$getString("timestamp");
                  long number7 = NumberUtils.method1(text11);
                  ZonedDateTime zoneddatetime12 = number7 != 0L
                     ? ZonedDateTime.ofInstant(Instant.ofEpochMilli(number7), ZoneId.systemDefault())
                     : ZonedDateTime.parse(text11, field9);
                  TextComponent text10 = (TextComponent)Component.text("Creation Date: " + zoneddatetime12.format(field8)).color(NamedTextColor.GRAY);
                  list5.add((ClickableText)Bridge.method8().method89(text10));
                  data21.method2(list5);
               }
            }
         }
      }
   }
}
