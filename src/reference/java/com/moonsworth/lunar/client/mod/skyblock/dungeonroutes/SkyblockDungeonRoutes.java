package com.moonsworth.lunar.client.mod.skyblock.dungeonroutes;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteManager;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteRenderer.Type;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.RouteRenderType;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.feature.mod.mixin.RouteCommand;
import com.moonsworth.lunar.client.event.entity.EventEntityJoinWorld;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakProgress;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockPlace;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventEntityItemSpawn;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.io.FileExplorer;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.intellij.lang.annotations.Pattern;

public class SkyblockDungeonRoutes extends AbstractFeature {
   private final DungeonMapListener field8 = (DungeonMapListener)this.method63(DungeonMapListener.class);
   private final EquippedItemListener field9 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private final RouteManager field10 = new RouteManager(this);
   private Set<String> field11 = new HashSet<>();
   private Set<String> field12 = new HashSet<>();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "defaultSecretRoutesEnabled"
            )
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "secretRoutesNamesAtStart"
            )
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
         .RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   private final ColorOption field15 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("secretRoutesNameColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ToggleOption field16 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("secretRoutesAllowPearling")
      .method31();
   private final ToggleOption field17 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("secretRoutesDisableHelpText")
      .method31();
   private final EnumOption<RouteRenderType> field18 = (EnumOption<RouteRenderType>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "secretRoutesPathType", RouteRenderType.PARTICLES
      )
      .method31();
   private final ToggleOption field19 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("secretRoutesDisableSuperboom")
      .method31();
   private final ToggleOption field20 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("secretRoutesDisableBlock")
      .method31();
   private final ToggleOption field21 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("secretRoutesDisableLever")
      .method31();
   private final ToggleOption field22 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("secretRoutesDisableEtherwarp")
      .method31();
   private final ToggleOption field23 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("secretRoutesDisableSecret")
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
               "secretRoutesDisableMisc"
            )
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
         .RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   private final ToggleOption field25 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("secretRoutesDisablePearls")
      .method31();

   public SkyblockDungeonRoutes(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.method27(new RouteCommand());
      this.handle(HudRenderLegacyEvent.class, arg0 -> RouteTracker.method1(arg0.method3(), Type.DEPTH));
      this.handle(HudRenderLegacyEventAlt.class, arg0 -> RouteTracker.method1(arg0.method3(), Type.ESP));
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventTick.class, arg0 -> RouteTracker.method2());
      this.handle(EventPlaySound.class, RouteTracker::method9);
      this.handle(EventBlockPlace.class, arg0 -> RouteTracker.method5(arg0.method2()));
      this.handle(EventBlockBreakProgress.class, arg1x -> RouteTracker.method4(arg1x.method2(), this.field9.method9()));
      this.handle(
         com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate.class,
         arg1x -> RouteTracker.method6(arg1x.method1(), this.field9.method9())
      );
      this.handle(EventEntityItemSpawn.class, arg0 -> RouteTracker.method7(arg0.method1()));
      this.handle(EventEntityJoinWorld.class, RouteTracker::method11);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, arg0 -> {
         if (arg0.OROIIOCCOORRCRCIIHHOCCCRHICRCC().equals("That chest is locked!")) {
            RouteTracker.method10();
         }
      });
   }

   public static SkyblockDungeonRoutes method13() {
      return Ref.method4().method40().method82().method72();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method12("secretRoutesHelpLabel")).method4(true);
      lightingextension231.method9(new ClientOption[]{this.field13, this.field14});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field15})).method2(() -> !(Boolean)this.field14.get());
      lightingextension231.method9(
         new OptionProvider[]{
            this.field17,
            this.field18,
            this.field16,
            this.field19,
            this.field20,
            this.field21,
            this.field22,
            this.field23,
            this.field24,
            this.field25,
            com.moonsworth.lunar.client.config.option.OptionFactory.method14("openCustomRoutesFolder").method5(200.0F).method4(() -> {
               boolean flag1x = false;

               try {
                  RouteManager.field1.mkdirs();
                  flag1x = FileExplorer.method1(RouteManager.field1);
               } catch (Exception exception3) {
               }

               if (!flag1x) {
                  SkyBlockChat.sendMessage(Component.text(this.method27("failedToOpenFolder", new Object[0]), NamedTextColor.RED));
               }
            })
         }
      );
   }

   @Pattern("[A-Z0-9_]{2,}")
   public String getId() {
      return "SKYBLOCK_DUNGEON_ROUTES";
   }

   public void method1(JsonObject json1) {
      json1.add("dungeonRoutesRoomWhitelist", LunarConstants.field22.toJsonTree(this.field11));
      json1.add("dungeonRoutesRoomBlacklist", LunarConstants.field22.toJsonTree(this.field12));
      super.method1(json1);
   }

   public void load(JsonObject json1) {
      Set set2 = (Set)LunarConstants.field22.fromJson(json1.get("dungeonRoutesRoomWhitelist"), (new TypeToken<Set<String>>() {}).getType());
      Set set3 = (Set)LunarConstants.field22.fromJson(json1.get("dungeonRoutesRoomBlacklist"), (new TypeToken<Set<String>>() {}).getType());
      if (set2 != null) {
         this.field11 = set2;
      }

      if (set3 != null) {
         this.field12 = set3;
      }

      super.load(json1);
   }

   @Generated
   public DungeonMapListener method14() {
      return this.field8;
   }

   @Generated
   public EquippedItemListener method15() {
      return this.field9;
   }

   @Generated
   public RouteManager method16() {
      return this.field10;
   }

   @Generated
   public Set<String> method17() {
      return this.field11;
   }

   @Generated
   public Set<String> method19() {
      return this.field12;
   }

   @Generated
   public ToggleOption method21() {
      return this.field13;
   }

   @Generated
   public ToggleOption method22() {
      return this.field14;
   }

   @Generated
   public ColorOption method23() {
      return this.field15;
   }

   @Generated
   public ToggleOption method24() {
      return this.field16;
   }

   @Generated
   public ToggleOption method25() {
      return this.field17;
   }

   @Generated
   public EnumOption<RouteRenderType> method26() {
      return this.field18;
   }

   @Generated
   public ToggleOption method27() {
      return this.field19;
   }

   @Generated
   public ToggleOption method28() {
      return this.field20;
   }

   @Generated
   public ToggleOption method29() {
      return this.field21;
   }

   @Generated
   public ToggleOption method30() {
      return this.field22;
   }

   @Generated
   public ToggleOption method34() {
      return this.field23;
   }

   @Generated
   public ToggleOption method35() {
      return this.field24;
   }

   @Generated
   public ToggleOption method36() {
      return this.field25;
   }
}
