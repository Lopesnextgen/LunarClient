package com.moonsworth.lunar.client.mod.skyblock.revivehud;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonClass;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyblockReviveHud extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^ ❣ (?<revivedPlayer>\\w+) was revived by (?<reviver>\\w+)!$");
   private static final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "skyblock/hud/revive_stone.png");
   private static final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "steve.png");
   private final DungeonMapListener field11 = (DungeonMapListener)this.method63(DungeonMapListener.class);
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "reviveHeaderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field13 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "playerNameColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "reviveCooldownColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-3726080))
      .method31();
   private final ColorOption field15 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "reviveReadyColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final HashMap<String, Long> field16 = new HashMap<>();
   private int field17;

   public SkyblockReviveHud(Skyblock skyblock1) {
      super(false);
      this.method9(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method9(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockReviveHud.Data()));
      this.method9(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method9(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.method1(this::onDisable);
      this.handle(EventSecond.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
      this.handle(EventWorldChange.class, this::method3);
   }

   private void method1(EventSecond highlightimpl41) {
      this.field11.method5().ifPresent(arg1x -> this.field17 = arg1x.getPlayers().stream().mapToInt(this::method4).sum());
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      DungeonStateTracker holograms2_52 = (DungeonStateTracker)this.field11.method5().orElse(null);
      if (holograms2_52 != null) {
         Matcher matcher3 = field8.matcher(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
         if (matcher3.matches()) {
            String text4 = matcher3.group("revivedPlayer");
            String text5 = matcher3.group("reviver");
            if (this.field16.getOrDefault(text5, 0L) <= Ref.method3().bridge$getSystemTime()) {
               holograms2_52.method31(text5).ifPresent(arg4x -> {
                  if (arg4x.method37() == DungeonClass.HEALER) {
                     if (arg4x.method33() <= 0 || !text4.equals(text5)) {
                        long number5x = holograms2_52.getPlayers().stream().filter(arg0 -> arg0.method37() == DungeonClass.HEALER).count();
                        int number7 = 100 - arg4x.method38() - (number5x == 1L ? 10 : 0);
                        this.field16.put(text5, Ref.method3().bridge$getSystemTime() + number7 * 1000L);
                     }
                  }
               });
            }
         }
      }
   }

   private void onDisable() {
      this.field16.clear();
      this.field17 = 0;
   }

   private void method3(EventWorldChange data31) {
      this.field16.clear();
      this.field17 = 0;
   }

   private int method4(DungeonPlayerTracker holograms4updater1) {
      int index2 = holograms4updater1.method33();
      if (holograms4updater1.method37() == DungeonClass.HEALER
         && this.field16.getOrDefault(holograms4updater1.method20(false), 0L) < Ref.method3().bridge$getSystemTime()) {
         index2++;
      }

      return index2;
   }

   public String getId() {
      return "SKYBLOCK_REVIVE_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.COLOR,
         arg1x -> arg1x.method9(new ClientOption[]{this.field12, this.field13, this.field14, this.field15})
      );
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 100, 200, 100, 160, 300);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         if (IslandUtils.getIsland() != SkyblockIsland.DUNGEON) {
            return null;
         }

         TextColor textcolor2 = TextColor.color(SkyblockReviveHud.this.field12.method14(0.0F));
         TextColor textcolor3 = TextColor.color(SkyblockReviveHud.this.field15.method14(0.0F));
         TextColor textcolor4 = TextColor.color(SkyblockReviveHud.this.field14.method14(0.0F));
         TextColor textcolor5 = TextColor.color(SkyblockReviveHud.this.field12.method14(0.0F));
         if (flag1) {
            return List.of(
               new HudLine(
                  SkyblockReviveHud.field9,
                  TextComponentFactory.builder()
                     .method2(SkyblockReviveHud.this.method8("availableRevives", new Object[0]))
                     .method4("3")
                     .method5(textcolor2)
                     .method13(new TextDecoration[]{TextDecoration.BOLD})
                     .method7(textcolor3)
                     .method14(new TextDecoration[]{TextDecoration.BOLD})
                     .build()
               ),
               new HudLine(SkyblockReviveHud.field10, TextComponentFactory.builder().method2("Player_1").method4("1").method5(textcolor5).method7(textcolor3).build()),
               new HudLine(SkyblockReviveHud.field10, TextComponentFactory.builder().method2("Player_2").method4("2").method5(textcolor5).method7(textcolor3).build()),
               new HudLine(SkyblockReviveHud.field10, TextComponentFactory.builder().method2("Player_3").method4("0").method5(textcolor5).method7(textcolor4).build())
            );
         }

         DungeonStateTracker holograms2_56 = (DungeonStateTracker)SkyblockReviveHud.this.field11.method5().orElse(null);
         if (holograms2_56 == null) {
            return null;
         }

         ArrayList list7 = new ArrayList();
         list7.add(
            new HudLine(
               SkyblockReviveHud.field9,
               TextComponentFactory.builder()
                  .method2(SkyblockReviveHud.this.method8("availableRevives", new Object[0]))
                  .method4(Integer.toString(SkyblockReviveHud.this.field17))
                  .method5(textcolor2)
                  .method13(new TextDecoration[]{TextDecoration.BOLD})
                  .method7(SkyblockReviveHud.this.field17 > 0 ? textcolor3 : textcolor4)
                  .method14(new TextDecoration[]{TextDecoration.BOLD})
                  .build()
            )
         );
         holograms2_56.getPlayers()
            .forEach(
               arg4x -> {
                  int number5x = SkyblockReviveHud.this.method4(arg4x);
                  Bridge6_10 bridge6_106x = (Bridge6_10)arg4x.method27().orElse(null);
                  ResourceLocationBridge horsestats147x = bridge6_106x != null ? bridge6_106x.bridge$getServerSkinTexture() : null;
                  TextComponent text8 = TextComponentFactory.builder()
                     .method2(arg4x.method20(false))
                     .method4(Integer.toString(number5x))
                     .method5(TextColor.color(SkyblockReviveHud.this.field13.method14(0.0F)))
                     .method7(number5x > 0 ? textcolor3 : textcolor4)
                     .build();
                  if (horsestats147x != null) {
                     list7.add(new HudLine(arg1xx -> {
                        arg1xx.push();
                        arg1xx.method40(2.0F, 2.0F);
                        LcuiScreen.method50(arg1xx, horsestats147x, 0.0F, 0.0F, -1, true);
                        arg1xx.pop();
                     }, text8));
                  } else {
                     list7.add(new HudLine(SkyblockReviveHud.field10, text8));
                  }
               }
            );
         return list7;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }

      protected boolean method23() {
         return true;
      }
   }
}
