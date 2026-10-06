package com.moonsworth.lunar.client.mod.skyblock.caughtcrittershud;

import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudComponent;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.ScrollableHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class SkyblockCaughtCrittersHud extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^CAPTURE! You caught a (?<critter>[\\w\\s]+?) and .*!$");
   private static final Pattern field9 = Pattern.compile("^CAPTURE! You found (?<critter>[\\w\\s]+?), and .*!$");
   private static final float field10 = 1.0F;
   private static final int field11 = 8;
   private final IntegerOption field12 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "caughtCrittersVisibleRows"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(5))
         .method7(2, 25))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final Object2IntMap<String> field14 = new Object2IntLinkedOpenHashMap();
   private final HudComponentGroup field15 = new HudComponentGroup(true, HudComponentGroup.field1).method3(1.0F);
   private int field16;

   public SkyblockCaughtCrittersHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockCaughtCrittersHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FORAGING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.SAFARI));
      this.method50(this::reset);
      this.method51(this::reset);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
      this.method14(EventWorldChange.class, this::reset);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.HUD, arg1x -> arg1x.method9(new ClientOption[]{this.field12, this.field13})
      );
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      Matcher matcher3 = field8.matcher(text2);
      if (!matcher3.matches()) {
         matcher3 = field9.matcher(text2);
         if (!matcher3.matches()) {
            return;
         }
      }

      String text4 = matcher3.group("critter");
      this.field14.put(text4, this.field14.getOrDefault(text4, 0) + 1);
      this.field16++;
      this.method13();
   }

   private void reset() {
      this.field14.clear();
      this.field16 = 0;
      this.method13();
   }

   private void method13() {
      this.field15.method8();
      if (this.field14.isEmpty()) {
         this.field15.method5(new TextHudComponent(() -> this.method14("noCrittersCaught", new Object[0]), ChatFormatting.GRAY));
      } else {
         ObjectIterator objectiterator1 = this.field14.object2IntEntrySet().iterator();

         while (objectiterator1.hasNext()) {
            Entry entry2 = (Entry)objectiterator1.next();
            this.field15.method5(new TextHudComponent(method4((String)entry2.getKey(), entry2.getIntValue())));
         }
      }
   }

   private static Component method4(String text0, int number1) {
      return Component.text(text0, NamedTextColor.WHITE).append(Component.text(" (" + number1 + ")", NamedTextColor.GRAY));
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_CAUGHT_CRITTERS_HUD";
   }

   private class Data extends MixinCore9Base {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT);
         this.method1(this.method1(SkyblockCaughtCrittersHud.this.field15, () -> this.method4(SkyblockCaughtCrittersHud.this.field16)), this.method15());
      }

      private HudComponent method1(HudComponent mixincore51, Supplier<String> supplier2) {
         HudComponentGroup mixincore5iterator3 = new HudComponentGroup(true, HudComponentGroup.field1)
            .method3(3.0F)
            .method5(new TextHudComponent().method4(() -> Component.text((String)supplier2.get(), NamedTextColor.GOLD).decorate(TextDecoration.BOLD)))
            .method5(new ScrollableHudComponent(mixincore51, true).method2(this::method5).method3(true));
         return WidgetFactory.withBackground(mixincore5iterator3, SkyblockCaughtCrittersHud.this.field13::get);
      }

      private float method5() {
         int number1 = (Integer)SkyblockCaughtCrittersHud.this.field12.get();
         return number1 * Ref.method10().method19() + (number1 - 1) * 1.0F;
      }

      private HudComponent method15() {
         HudComponentGroup mixincore5iterator1 = new HudComponentGroup(true, HudComponentGroup.field1).method3(1.0F);

         for (int index2 = 1; index2 <= 8; index2++) {
            int number3 = index2;
            mixincore5iterator1.method5(
               new TextHudComponent()
                  .method4(
                     () -> SkyblockCaughtCrittersHud.method4(
                        SkyblockCaughtCrittersHud.this.method14("critterPreview", new Object[]{number3}), 8 - number3 + 1
                     )
                  )
            );
         }

         return this.method1(mixincore5iterator1, () -> this.method4(8));
      }

      private String method4(int number1) {
         return SkyblockCaughtCrittersHud.this.method14("caughtCritters", new Object[0]) + " (" + number1 + ")";
      }

      public boolean method4(boolean flag1) {
         return super.method4(flag1) && IslandUtils.getIsland() == SkyblockIsland.SAFARI;
      }

      public boolean method30() {
         return super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI() && IslandUtils.getIsland() == SkyblockIsland.SAFARI;
      }
   }
}
