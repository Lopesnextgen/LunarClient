package com.moonsworth.lunar.client.mod.skyblock.dianamobhud;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.EntityFinder;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin.BurrowType;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.combat.EventPreAttackEntity;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyblockDianaMobHud extends AbstractFeature {
   private final HypixelLocationListener field8 = (HypixelLocationListener)this.method63(HypixelLocationListener.class);
   private static final Pattern field9 = Pattern.compile(
      "^(?<level>\\[Lv[\\d,]+]) (?<mobTypes>[^ ]+ )(?<name>[A-Za-z ]+)(?<currentHealth>[\\d.,]+[kmbKMB]?)/(?<totalHealth>[\\d.,]+[kmbKMB]?)❤(?<shuriken> ✯)?$"
   );
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showLootShareWarning").method4(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showShurikenWarning").method4(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("rareMobsOnlyOption").method4(true))
      .method31();
   private final MultiSelectOption field13 = (MultiSelectOption)((com.moonsworth.lunar.client.config.option.MultiSelectOption.Data)OptionFactory.method27(
            "rareDianaMobs"
         )
         .method2(BurrowType.getEnabledByDefault()))
      .method3(BurrowType.noLynxes())
      .method31();
   @Nullable
   private BridgeExtension field14;
   private boolean field15;
   private boolean field16;

   public SkyblockDianaMobHud(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.EVENT));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockDianaMobHud.Data()));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> this.field8.method9() == SkyblockIsland.HUB));
      this.method50(this::reset);
      this.handle(EventEntitySpawn.class, this::method4);
      this.handle(EventPreAttackEntity.class, this::method2);
      this.handle(EventEntityRemove.class, this::method3);
      this.handle(EventWorldChange.class, arg1x -> this.reset());
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10, this.field11, this.field12, this.field13});
   }

   private void reset() {
      this.field14 = null;
      this.field16 = false;
      this.field15 = false;
   }

   private void method2(EventPreAttackEntity highlightimpl5_21) {
      if (IslandUtils.getIsland() == SkyblockIsland.HUB) {
         if (this.field14 != null) {
            BridgeExtension bridgeextension2 = EntityFinder.findNearest(this.field14, Objects.requireNonNull(Ref.method8()).bridge$getEntities());
            if (highlightimpl5_21.method2().equals(bridgeextension2)) {
               this.field15 = true;
            }
         }
      }
   }

   private void method3(EventEntityRemove highlightimpl121) {
      if (IslandUtils.getIsland() == SkyblockIsland.HUB) {
         if (highlightimpl121.method1().equals(this.field14)) {
            this.reset();
         }
      }
   }

   private void method4(EventEntitySpawn highlightimpl6_21) {
      if (IslandUtils.getIsland() == SkyblockIsland.HUB) {
         BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
         Bridge5Extension_5 bridge5extension_53 = Ref.method7();
         if (bridgeextension2 != null && bridge5extension_53 != null) {
            Component component4 = bridgeextension2.bridge$getCustomName();
            if (component4 != null) {
               String text5 = TextBridge.getTextContent(component4);
               Matcher matcher6 = field9.matcher(text5);
               if (matcher6.matches()) {
                  double value7 = bridge5extension_53.HHRROIIHRRICIIHIIHICRHHRHOHHOO(bridgeextension2);
                  if (!(value7 > 225.0)) {
                     if (this.field14 == null || !(value7 > bridge5extension_53.HHRROIIHRRICIIHIIHICRHHRHOHHOO(this.field14))) {
                        String text9 = matcher6.group("mobTypes");
                        if (text9.contains("✿") || text9.contains("\ue07e")) {
                           if ((Boolean)this.field12.get()) {
                              String text10 = matcher6.group("name");
                              boolean flag11 = ((Set)this.field13.get()).stream().anyMatch(text10::contains);
                              if (!flag11) {
                                 return;
                              }
                           }

                           if (!bridgeextension2.equals(this.field14)) {
                              this.reset();
                           }

                           this.field14 = bridgeextension2;
                           this.field16 = this.field16 || matcher6.group("shuriken") != null;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_DIANA_MOB_HUD";
   }

   private class Data extends TypedHudRenderer<List<Component>> {
      private static final Component field31 = ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text(
                                    "[", NamedTextColor.DARK_GRAY
                                 )
                                 .append(Component.text("Lv200", NamedTextColor.GRAY)))
                              .append(Component.text("]", NamedTextColor.DARK_GRAY)))
                           .append(Component.text(" ✿", NamedTextColor.DARK_GREEN)))
                        .append(Component.text('✰', NamedTextColor.YELLOW)))
                     .append(Component.text(" Empyrean Minos Hunter ", NamedTextColor.DARK_GREEN)))
                  .append(Component.text("1.1M", NamedTextColor.YELLOW)))
               .append(Component.text("/", NamedTextColor.WHITE)))
            .append(Component.text("1.8M", NamedTextColor.GREEN)))
         .append(Component.text('❤', NamedTextColor.RED));

      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 30, 70, 120, 200);
      }

      @Nullable
      public List<Component> method2(boolean flag1) {
         if (flag1) {
            return this.method3(field31, false, false);
         } else {
            return SkyblockDianaMobHud.this.field14 == null
               ? List.of()
               : this.method3(SkyblockDianaMobHud.this.field14.bridge$getCustomName(), SkyblockDianaMobHud.this.field16, SkyblockDianaMobHud.this.field15);
         }
      }

      private List<Component> method3(@Nullable Component component1, boolean flag2, boolean flag3) {
         ArrayList list4 = new ArrayList();
         if (component1 != null) {
            list4.add(component1);
         }

         if ((Boolean)SkyblockDianaMobHud.this.field11.get() && !flag2) {
            list4.add(Component.text("NO SHURIKEN!", NamedTextColor.RED, new TextDecoration[]{TextDecoration.BOLD}));
         }

         if ((Boolean)SkyblockDianaMobHud.this.field10.get() && !flag3) {
            list4.add(Component.text("NO LOOTSHARE!", NamedTextColor.DARK_RED, new TextDecoration[]{TextDecoration.BOLD}));
         }

         return list4;
      }

      public boolean method4(boolean flag1) {
         return IslandUtils.getIsland() != SkyblockIsland.HUB ? false : super.method4(flag1);
      }

      protected boolean method17() {
         return false;
      }

      protected boolean method19() {
         return false;
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }
   }
}
