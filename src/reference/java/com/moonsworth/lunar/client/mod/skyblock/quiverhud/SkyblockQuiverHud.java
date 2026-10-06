package com.moonsworth.lunar.client.mod.skyblock.quiverhud;

import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ChatComponentStyleBridge;
import com.moonsworth.lunar.bridge.NBTTagListBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.bridge.DataComponentTypes;
import com.moonsworth.lunar.bridge.LoreComponent;
import com.moonsworth.lunar.bridge.CompoundTagComponent;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileLoadEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkyblockQuiverHud extends AbstractFeature {
   private final SkyblockProfileCache field8 = (SkyblockProfileCache)this.method63(SkyblockProfileCache.class);
   private static final Pattern field9 = Pattern.compile("^Arrows Remaining: ([\\d,]+)$");
   private TextComponent field10 = Component.empty();

   public SkyblockQuiverHud(Skyblock skyblock1) {
      super(false);
      this.method8(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method8(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockQuiverHud.Data()));
      this.method8(ModTraits.field17, ModCategories.method2(SettingsPage.HUD));
      this.method8(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSecond.class, this::method2);
      this.handle(SkyblockProfileLoadEvent.class, this::method1);
   }

   private void method1(SkyblockProfileLoadEvent data141) {
      if (!this.method13()) {
         this.field10 = Component.empty();
      }
   }

   private void method2(EventSecond highlightimpl41) {
      if (IslandUtils.isOnIsland()) {
         Bridge5Extension_5 bridge5extension_52 = this.mc.bridge$getPlayer();
         if (bridge5extension_52 != null) {
            ItemStackBridge bridgeextension_43 = (ItemStackBridge)bridge5extension_52.bridge$getInventory().bridge$getMainInventory().get(8);
            if (bridgeextension_43 != null) {
               this.method4(bridgeextension_43);
            }
         }
      }
   }

   private boolean method3(@NotNull ItemStackBridge bridgeextension_41) {
      return Optional.ofNullable((CompoundTagComponent)bridgeextension_41.bridge$getDataComponent(DataComponentTypes.field1))
         .<CompoundTagBridge>map(CompoundTagComponent::bridge$getData)
         .map(arg0 -> arg0.bridge$getString("quiver_arrow"))
         .filter(arg0 -> arg0.equals("true"))
         .isPresent();
   }

   private void method4(@NotNull ItemStackBridge bridgeextension_41) {
      if (this.method3(bridgeextension_41)) {
         String text2 = Optional.ofNullable((ChatComponentStyleBridge)bridgeextension_41.bridge$getDataComponent(DataComponentTypes.field6))
            .map(arg0 -> TextBridge.getTextContent(arg0.moonBridge$asAdventureComponent()))
            .orElse(null);
         if (text2 != null) {
            List list3 = Optional.ofNullable((LoreComponent)bridgeextension_41.bridge$getDataComponent(DataComponentTypes.field8))
               .<List>map(LoreComponent::bridge$getLines)
               .orElse(null);
            if (list3 != null) {
               for (ChatComponentStyleBridge bridge3_255 : list3) {
                  String text6 = TextBridge.getTextContent(bridge3_255.moonBridge$asAdventureComponent());
                  Matcher matcher7 = field9.matcher(text6);
                  if (matcher7.find()) {
                     this.field10 = method6(text2, matcher7.group(1));
                     return;
                  }
               }
            }
         }
      }
   }

   private boolean method13() {
      try {
         Member member1 = this.field8.method9();
         String text2 = (String)member1.itemData().favoriteArrow().orElse(null);
         String text3 = (String)member1.inventory().bagContents().quiver().data().orElse(null);
         NBTTagListBridge bridge3_64 = SkyblockItemUtil.method22(text3);
         byte number5 = 0;

         for (int index6 = 0; index6 < bridge3_64.bridge$size(); index6++) {
            CompoundTagBridge bridge_577 = bridge3_64.bridge$getCompoundAt(index6);
            String text8 = bridge_577.bridge$getCompoundTag("tag").bridge$getCompoundTag("ExtraAttributes").bridge$getString("id");
            if (text8.equals(text2)) {
               number5 += bridge_577.bridge$getByte("Count");
            }
         }

         this.field10 = method6(method7(text2), Integer.toString(number5));
         return true;
      } catch (Exception exception9) {
         return false;
      }
   }

   public String getId() {
      return "SKYBLOCK_QUIVER_HUD";
   }

   private static TextComponent method6(String text0, String text1) {
      return TextComponentFactory.builder().method2(text0).method4(text1).build();
   }

   private static String method7(String text0) {
      if (text0 != null && !text0.isEmpty()) {
         if (text0.equals("ARROW")) {
            return "Flint";
         }

         StringBuilder builder1 = new StringBuilder();

         for (int index2 = 0; index2 < text0.length() - "_ARROW".length(); index2++) {
            if (index2 == 0) {
               builder1.append(Character.toUpperCase(text0.charAt(index2)));
            } else if (text0.charAt(index2) == '_') {
               builder1.append('-');
            } else {
               builder1.append(Character.toLowerCase(text0.charAt(index2)));
            }
         }

         return builder1.toString();
      } else {
         return "";
      }
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 30, 70, 120, 200);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         return flag1 && SkyblockQuiverHud.this.field10.equals(Component.empty())
            ? new HudLine(Bridge.method28().method11(), SkyblockQuiverHud.method6("Reinforced Iron Arrow", "1,234"))
            : new HudLine(Bridge.method28().method11(), SkyblockQuiverHud.this.field10);
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
