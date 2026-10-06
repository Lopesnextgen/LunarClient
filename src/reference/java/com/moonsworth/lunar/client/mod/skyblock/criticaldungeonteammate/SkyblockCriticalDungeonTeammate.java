package com.moonsworth.lunar.client.mod.skyblock.criticaldungeonteammate;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonClass;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.MultiSelectOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.LinkedHashSet;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockCriticalDungeonTeammate extends AbstractFeature {
   private final DungeonMapListener field8 = (DungeonMapListener)this.method63(DungeonMapListener.class);
   private final AlertDisplayListener field9 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyBlockOnlyAlertAsHealer")
         .method4(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyBlockOnlyAlertWhenWishOffCooldown")
         .method4(true))
      .method31();
   private final EnumOption<SkyblockCriticalDungeonTeammate.Type> field12 = (EnumOption<SkyblockCriticalDungeonTeammate.Type>)OptionFactory.method10(
         "skyBlockHealthAlertLevel", SkyblockCriticalDungeonTeammate.Type.CRITICAL
      )
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyBlockAlertSelfHealth").method4(true))
      .method31();
   private final MultiSelectOption field14 = (MultiSelectOption)((Data)OptionFactory.method27("skyBlockHealthAlertClassList")
         .method2(new LinkedHashSet(DungeonClass.ids())))
      .method3(DungeonClass.ids())
      .method31();
   private long field15;

   public SkyblockCriticalDungeonTeammate(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(EventScoreboardUpdate.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.EventActionBarMessage.class, this::method1);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.EventActionBarMessage data21) {
      if ((Boolean)this.field13.get() && !this.method13()) {
         DungeonStateTracker holograms2_52 = (DungeonStateTracker)this.field8.method5().orElse(null);
         if (holograms2_52 != null) {
            DungeonPlayerTracker holograms4updater3 = holograms2_52.method29();
            this.method4(holograms4updater3);
         }
      }
   }

   private void method2(EventScoreboardUpdate highlightimpl21) {
      if (!this.method13()) {
         DungeonStateTracker holograms2_52 = (DungeonStateTracker)this.field8.method5().orElse(null);
         if (holograms2_52 != null) {
            for (DungeonPlayerTracker holograms4updater4 : holograms2_52.getPlayers()) {
               if (!holograms4updater4.method19() && holograms4updater4.method37() != null && !holograms4updater4.isDead() && this.field14.contains(holograms4updater4.method37().getChatDisplayName())) {
                  this.method4(holograms4updater4);
               }
            }
         }
      }
   }

   private boolean method13() {
      if (Ref.method3().bridge$getSystemTime() - this.field15 < 900L) {
         return true;
      }

      if (!this.method14()) {
         if ((Boolean)this.field10.get()) {
            return true;
         }
      } else if ((Boolean)this.field11.get() && !this.method15()) {
         return true;
      }

      return false;
   }

   private void method4(DungeonPlayerTracker holograms4updater1) {
      Component component2 = holograms4updater1.method44();
      if (component2 != null) {
         if (holograms4updater1.method24()) {
            this.field9
               .method2(
                  ComparableImpl.method2()
                     .method1("DUNGEON_HP")
                     .method2(component2.append(Component.text(this.method1("isCritical", new Object[0]), NamedTextColor.RED)))
                     .method4(com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type.HIGH)
                     .method6()
               );
            this.field15 = Ref.method3().bridge$getSystemTime();
         } else if (this.field12.get() == SkyblockCriticalDungeonTeammate.Type.LOW && holograms4updater1.method23()) {
            this.field9
               .method2(
                  ComparableImpl.method2()
                     .method1("DUNGEON_HP")
                     .method2(component2.append(Component.text(this.method1("isLow", new Object[]{NamedTextColor.YELLOW}))))
                     .method4(com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type.HIGH)
                     .method6()
               );
            this.field15 = Ref.method3().bridge$getSystemTime();
         }
      }
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_CRITICAL_DUNGEON_TEAMMATE";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field10, this.field13, this.field12, this.field14});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field11})).method2(() -> !(Boolean)this.field10.get());
   }

   private boolean method14() {
      DungeonStateTracker holograms2_51 = (DungeonStateTracker)this.field8.method5().orElse(null);
      if (holograms2_51 == null) {
         return false;
      }

      DungeonPlayerTracker holograms4updater2 = holograms2_51.method29();
      return holograms4updater2.method37() == DungeonClass.HEALER;
   }

   private boolean method15() {
      return Ref.method7() != null && Ref.method7().bridge$getExperienceLevel() == 0;
   }

   private enum Type implements OptionEnumValue {
      LOW("low"),
      CRITICAL("critical");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
