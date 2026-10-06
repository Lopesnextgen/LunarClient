package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class BettermapSettings {
   private static final Map<MapRoomType, RewindhandlersExtension> field1 = new EnumMap<MapRoomType, RewindhandlersExtension>(MapRoomType.class) {
      {
         this.put(MapRoomType.CLEAR, RewindhandlersExtension.method23(-9288933));
         this.put(MapRoomType.SPAWN, RewindhandlersExtension.method23(-16745472));
         this.put(MapRoomType.PUZZLE, RewindhandlersExtension.method23(-5092136));
         this.put(MapRoomType.MINIBOSS, RewindhandlersExtension.method23(-1710797));
         this.put(MapRoomType.FAIRY, RewindhandlersExtension.method23(-884827));
         this.put(MapRoomType.BLOOD, RewindhandlersExtension.method23(-65536));
         this.put(MapRoomType.TRAP, RewindhandlersExtension.method23(-2588877));
         this.put(MapRoomType.UNKNOWN, RewindhandlersExtension.method23(-12500671));
         this.put(MapRoomType.WITHER_DOOR, RewindhandlersExtension.method23(-16777216));
      }
   };
   private static final Map<MapRoomType, RewindhandlersExtension> field2 = new EnumMap<MapRoomType, RewindhandlersExtension>(MapRoomType.class) {
      {
         this.put(MapRoomType.CLEAR, RewindhandlersExtension.method23(-9749999));
         this.put(MapRoomType.SPAWN, RewindhandlersExtension.method23(-15432448));
         this.put(MapRoomType.PUZZLE, RewindhandlersExtension.method23(-9109371));
         this.put(MapRoomType.MINIBOSS, RewindhandlersExtension.method23(-73984));
         this.put(MapRoomType.FAIRY, RewindhandlersExtension.method23(-2096897));
         this.put(MapRoomType.BLOOD, RewindhandlersExtension.method23(-65536));
         this.put(MapRoomType.TRAP, RewindhandlersExtension.method23(-2588877));
         this.put(MapRoomType.UNKNOWN, RewindhandlersExtension.method23(-12500671));
         this.put(MapRoomType.WITHER_DOOR, RewindhandlersExtension.method23(-16777216));
      }
   };
   private static final RewindhandlersExtension field3 = RewindhandlersExtension.method23(-10734578);
   private static final RewindhandlersExtension field4 = RewindhandlersExtension.method23(0);
   private final BettermapVariant field5;
   @NotNull
   private final EquippedItemListener field6;
   private final LabelOption field7 = (LabelOption)OptionFactory.method15("mapStyle").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LabelOption field8 = (LabelOption)OptionFactory.method15("mapTickStyle").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LabelOption field9 = (LabelOption)OptionFactory.method15("mapPlayerStyle").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LabelOption field10 = (LabelOption)OptionFactory.method15("mapScoreInfo").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LabelOption field11 = (LabelOption)OptionFactory.method15("currentRoomInfo").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final EnumOption<MapStyle> field12;
   private final EnumOption<MapTickStyle> field13;
   private final EnumOption<MapPlayerStyle> field14;
   private final EnumOption<MapScoreStyle> field15;
   private final EnumOption<RoomInfoPosition> field16;
   private final FloatOption field17;
   private final ColorOption field18;
   private final ColorOption field19;
   private final FloatOption field20;
   private final IntegerOption field21;
   private final IntegerOption field22;
   private final ColorOption field23;
   private final ColorOption field24;
   private final ColorOption field25;
   private final ColorOption field26;
   private final ColorOption field27;
   private final ColorOption field28;
   private final ColorOption field29;
   private final ColorOption field30;
   private final ColorOption field31;
   private final EnumOption<MapTickStyle> field32;
   private final ToggleOption field33;
   private final EnumOption<MapTickStyle> field34;
   private final ToggleOption field35;
   private final IntegerOption field36;
   private final FloatOption field37;
   private final EnumOption<HeadBorderStyle> field38;
   private final FloatOption field39;
   private final ColorOption field40;
   private final ColorOption field41;
   private final ColorOption field42;
   private final ColorOption field43;
   private final ColorOption field44;
   private final ColorOption field45;
   private final ColorOption field46;
   private final EnumOption<PlayerNameDisplay> field47;
   private final ToggleOption field48;
   private final ToggleOption field49;
   private final ToggleOption field50;
   private final ToggleOption field51;
   private final ToggleOption field52;
   private final ToggleOption field53;
   private final ToggleOption field54;
   private final FloatOption field55;
   private boolean field56 = false;

   public BettermapSettings(BettermapVariant hologramstype1, @NotNull EquippedItemListener guirewindhandlershandler292) {
      this.field5 = hologramstype1;
      this.field6 = guirewindhandlershandler292;
      this.field12 = (EnumOption<MapStyle>)OptionFactory.method10(this.method5("MapStyle"), MapStyle.LEGAL_MAP)
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field13 = (EnumOption<MapTickStyle>)OptionFactory.method10(this.method5("MapTickStyle"), MapTickStyle.SECRETS)
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field14 = (EnumOption<MapPlayerStyle>)OptionFactory.method10(this.method5("MapPlayerStyle"), MapPlayerStyle.HEADS)
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field15 = (EnumOption<MapScoreStyle>)OptionFactory.method10(
            this.method5("MapScoreInfo"), hologramstype1 == BettermapVariant.SECONDARY ? MapScoreStyle.NONE : MapScoreStyle.SIMPLIFIED
         )
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field16 = (EnumOption<RoomInfoPosition>)OptionFactory.method10(
            this.method5("CurrentRoomInfo"), hologramstype1 == BettermapVariant.SECONDARY ? RoomInfoPosition.OFF : RoomInfoPosition.RIGHT
         )
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field17 = (FloatOption)((Data)((Data)OptionFactory.method2(this.method5("CurrentRoomInfoScale")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.1F, 2.0F))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field18 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MapBackground"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1426063360))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field19 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MapBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field20 = (FloatOption)((Data)((Data)OptionFactory.method2(this.method5("MapBorderThickness")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 2.0F))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field21 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  this.method5("RoomGap")
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(20))
            .OCRRICRIORICCCRHIOHORCICIHHICO(0, 100))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field22 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  this.method5("DoorWidth")
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(30))
            .OCRRICRIORICCCRHIOHORCICIHHICO(0, 100))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field23 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("ClearRoom"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-9288933))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field24 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("SpawnRoom"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16745472))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field25 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("PuzzleRoom"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5092136))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field26 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MiniBoss"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1710797))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field27 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("Fairy"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-884827))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field28 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("Blood"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field29 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("Trap"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2588877))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field30 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("Unknown"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-12500671))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field31 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MapZoom"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field32 = (EnumOption<MapTickStyle>)OptionFactory.method10(this.method5("SecondaryTickStyle"), MapTickStyle.FANCY)
         .method4(MapTickStyle.UNKNOWN_ROOM_VALUES)
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field33 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("SecondaryForCompleted")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field34 = (EnumOption<MapTickStyle>)OptionFactory.method10(this.method5("PuzzleTickStyle"), MapTickStyle.ROOM_NAME)
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field35 = (ToggleOption)OptionFactory.method7(this.method5("CenterCheckMarks")).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field36 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  this.method5("TickScale")
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(80))
            .OCRRICRIORICCCRHIOHORCICIHHICO(0, 100))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field37 = (FloatOption)((Data)((Data)OptionFactory.method2(this.method5("HeadScale")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
            .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 6.0F))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field38 = (EnumOption<HeadBorderStyle>)OptionFactory.method10(this.method5("HeadBorderStyle"), HeadBorderStyle.CLASS_COLORS)
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field39 = (FloatOption)((Data)((Data)OptionFactory.method2(this.method5("HeadBorderThickness")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 2.0F))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field40 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("SelfBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field41 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("OtherBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field42 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("ArcherBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-14767566))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field43 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("BerserkerBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field44 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("HealerBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1030416))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field45 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MageBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-12135726))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field46 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("TankBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-6908266))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field47 = (EnumOption<PlayerNameDisplay>)OptionFactory.method10(this.method5("PlayerNames"), PlayerNameDisplay.LEAP_HELD)
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field48 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("ShowInBoss")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(hologramstype1 != BettermapVariant.SECONDARY))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field49 = (ToggleOption)OptionFactory.method7(this.method5("ShowWithTab")).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field50 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("ShowTabs")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(hologramstype1 != BettermapVariant.SECONDARY))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field51 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("Spinny")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(hologramstype1 == BettermapVariant.SECONDARY))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field52 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("OrientIcons")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field53 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("Centered")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(hologramstype1 == BettermapVariant.SECONDARY))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field54 = (ToggleOption)OptionFactory.method7(this.method5("MimicPrefix")).RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field55 = (FloatOption)((Data)((Data)((Data)OptionFactory.method2(this.method5("MapZoom")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(100.0F))
               .HRRCROICHIIROIHRCOIHRRHCCRIIRH(25.0F, 200.0F))
            .HHCRCHCOIHHORCHIRHIRHCRIIHOIIR(1))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   }

   public void method1(RootSettingsBuilder lightingextension231) {
      this.method2(lightingextension231, null);
   }

   public void method2(RootSettingsBuilder lightingextension231, BooleanSupplier booleansupplier2) {
      this.method4(lightingextension231, booleansupplier2, this::method8, this.field48, this.field49, this.field50, this.field53);
      this.method3(lightingextension231, booleansupplier2, this.field51);
      this.method3(lightingextension231, () -> booleansupplier2 != null && booleansupplier2.getAsBoolean() || !(Boolean)this.field51.get() && !(Boolean)this.field53.get(), this.field55);
      this.method4(lightingextension231, booleansupplier2, this::method7, this.field52);
      this.method4(lightingextension231, booleansupplier2, this::method8, this.field10, this.field11);
      this.method3(lightingextension231, booleansupplier2, this.field7, this.field8, this.field9);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field15}))
         .method2(new ClientOption[]{this.field10});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field54}))
         .method2(new ClientOption[]{this.field10})
         .method2(this::method13);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field16}))
         .method2(new ClientOption[]{this.field11});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field17}))
         .method2(new ClientOption[]{this.field11})
         .method2(() -> this.field16.get() == RoomInfoPosition.OFF);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field12, this.field19, this.field20, this.field18}))
         .method2(new ClientOption[]{this.field7});
      ((SettingsSectionImpl)lightingextension231.method9(
            new ClientOption[]{
               this.field21,
               this.field22,
               this.field23,
               this.field24,
               this.field25,
               this.field26,
               this.field27,
               this.field28,
               this.field29,
               this.field30,
               this.field31
            }
         ))
         .method2(new ClientOption[]{this.field7})
         .method2(this::method14);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field13, this.field34, this.field35}))
         .method2(new ClientOption[]{this.field8});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field36})).method2(new ClientOption[]{this.field8});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field32, this.field33}))
         .method2(new ClientOption[]{this.field8})
         .method2(this::method6);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field14})).method2(new ClientOption[]{this.field9});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field47}))
         .method2(new ClientOption[]{this.field9})
         .method2(this::method8);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field38, this.field37}))
         .method2(new ClientOption[]{this.field9})
         .method2(this::method11);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field39}))
         .method2(new ClientOption[]{this.field9})
         .method2(() -> this.method9() || this.method11());
      ((SettingsSectionImpl)lightingextension231.method9(
            new ClientOption[]{this.field42, this.field43, this.field44, this.field45, this.field46}
         ))
         .method2(new ClientOption[]{this.field9})
         .method2(this::method10);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field40}))
         .method2(new ClientOption[]{this.field9})
         .method2(() -> this.method12() || this.method11() || this.method9() || !this.method10());
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field41}))
         .method2(new ClientOption[]{this.field9})
         .method2(() -> this.method11() || this.method9() || !this.method10());
   }

   private void method3(RootSettingsBuilder lightingextension231, BooleanSupplier booleansupplier2, ClientOption<?>... items3) {
      if (booleansupplier2 != null) {
         ((SettingsSectionImpl)lightingextension231.method9(items3)).method2(booleansupplier2);
      } else {
         lightingextension231.method9(items3);
      }
   }

   private void method4(RootSettingsBuilder lightingextension231, BooleanSupplier booleansupplier2, BooleanSupplier booleansupplier3, ClientOption<?>... items4) {
      if (booleansupplier2 == null) {
         ((SettingsSectionImpl)lightingextension231.method9(items4)).method2(booleansupplier3);
      } else {
         ((SettingsSectionImpl)lightingextension231.method9(items4)).method2(() -> booleansupplier2.getAsBoolean() || booleansupplier3.getAsBoolean());
      }
   }

   private String method5(String text1) {
      return this.field5.getOptionPrefix() + "BetterMap" + text1;
   }

   private boolean method6() {
      return this.field13.get() != MapTickStyle.SECRETS
         && this.field13.get() != MapTickStyle.ROOM_NAME
         && this.field13.get() != MapTickStyle.SECRETS_AND_NAME;
   }

   private boolean method7() {
      return !(Boolean)this.field51.get();
   }

   public boolean method8() {
      return this.field5 == BettermapVariant.SPIRIT_LEAP;
   }

   private boolean method9() {
      return this.field38.get() == HeadBorderStyle.NONE;
   }

   private boolean method10() {
      return this.field38.get() != HeadBorderStyle.CLASS_COLORS;
   }

   private boolean method11() {
      return this.field14.get() == MapPlayerStyle.OFF || this.field14.get() == MapPlayerStyle.ICONS;
   }

   private boolean method12() {
      return this.field14.get() == MapPlayerStyle.SELF_ICON;
   }

   private boolean method13() {
      return this.field15.get() != MapScoreStyle.SIMPLIFIED;
   }

   private boolean method14() {
      return this.field12.get() != MapStyle.CUSTOM;
   }

   private MapStyle method15() {
      return (MapStyle)this.field12.get();
   }

   private float method16() {
      return switch (this.method15()) {
         case HYPIXEL -> 25.0F;
         case LEGAL_MAP -> 23.0F;
         default -> ((Integer)this.field21.get()).intValue();
      };
   }

   public float method17(DungeonStateTracker holograms2_51) {
      float value2 = this.method16();
      return (100.0F - (Float)this.field20.get() * 2.0F) / Math.max(holograms2_51.method27(), holograms2_51.method28()) * (100.0F - value2) / 100.0F;
   }

   public float method18(DungeonStateTracker holograms2_51) {
      float value2 = switch (this.method15()) {
         case HYPIXEL -> 42.0F;
         case LEGAL_MAP -> 33.0F;
         default -> ((Integer)this.field22.get()).intValue();
      };
      return this.method17(holograms2_51) * value2 / 100.0F;
   }

   public float method19(DungeonStateTracker holograms2_51) {
      float value2 = this.method16();
      return (100.0F - (Float)this.field20.get() * 2.0F) / Math.max(holograms2_51.method27(), holograms2_51.method28()) * value2 / 100.0F;
   }

   public RewindhandlersExtension method20(MapRoomType hologramstype51, boolean flag2) {
      return (RewindhandlersExtension)(switch (this.method15()) {
         case HYPIXEL -> (RewindhandlersExtension)field1.get(hologramstype51);
         case LEGAL_MAP -> flag2 && hologramstype51 == MapRoomType.CLEAR ? field3 : (RewindhandlersExtension)field2.get(hologramstype51);
         case CUSTOM -> {
            switch (hologramstype51) {
               case SPAWN:
                  yield this.field24;
               case CLEAR:
                  yield this.field23;
               case PUZZLE:
                  yield this.field25;
               case MINIBOSS:
                  yield this.field26;
               case FAIRY:
                  yield this.field27;
               case BLOOD:
                  yield this.field28;
               case TRAP:
                  yield this.field29;
               case BOSS:
                  yield null;
               case UNKNOWN:
                  yield this.field30;
               case WITHER_DOOR:
                  yield this.field31;
               default:
                  throw new IncompatibleClassChangeError();
            }
         }
      });
   }

   public boolean method21() {
      if (this.field56) {
         return true;
      }

      if (this.field47.get() != PlayerNameDisplay.LEAP_HELD) {
         return this.field47.get() == PlayerNameDisplay.ALWAYS;
      }

      String text1 = this.field6.method9();
      return "SPIRIT_LEAP".equals(text1) || "INFINITE_SPIRIT_LEAP".equals(text1);
   }

   public boolean method22() {
      return this.field13.get() != MapTickStyle.SECRETS
            && this.field13.get() != MapTickStyle.ROOM_NAME
            && this.field13.get() != MapTickStyle.SECRETS_AND_NAME
         || this.field32.get() != MapTickStyle.NONE;
   }

   public MapTickIcon method23(DungeonRoomTracker holograms4iterator1) {
      MapTickStyle gui2extension22;
      if (holograms4iterator1.method30().method6() != MapRoomType.PUZZLE) {
         boolean flag3 = this.method26(this.method25(holograms4iterator1));
         if (flag3 && holograms4iterator1.method14() != -1 && (holograms4iterator1.method14() != holograms4iterator1.method30().method1() || !(Boolean)this.field33.get())) {
            return null;
         }

         gui2extension22 = flag3 ? (MapTickStyle)this.field32.get() : (MapTickStyle)this.field13.get();
      } else {
         gui2extension22 = (MapTickStyle)this.field34.get();
      }

      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState hologramstype24 = holograms4iterator1.method30().method2();
      switch (gui2extension22) {
         case FANCY:
            switch (hologramstype24) {
               case COMPLETED:
                  return MapTickIcon.FANCY_GREEN;
               case CLEARED:
                  return MapTickIcon.FANCY_WHITE;
               case ADJACENT:
                  return MapTickIcon.FANCY_UNKNOWN;
               case FAILED:
                  return MapTickIcon.FANCY_CROSS;
            }
         case HIGHRES:
            switch (hologramstype24) {
               case COMPLETED:
                  return MapTickIcon.HIGHRES_GREEN;
               case CLEARED:
                  return MapTickIcon.HIGHRES_WHITE;
               case ADJACENT:
                  return MapTickIcon.HIGHRES_UNKNOWN;
               case FAILED:
                  return MapTickIcon.HIGHRES_CROSS;
            }
         case HYPIXEL_OLD:
            switch (hologramstype24) {
               case COMPLETED:
                  return MapTickIcon.HYPIXEL_OLD_GREEN;
               case CLEARED:
                  return MapTickIcon.HYPIXEL_OLD_WHITE;
               case ADJACENT:
                  return MapTickIcon.HYPIXEL_OLD_UNKNOWN;
               case FAILED:
                  return MapTickIcon.HYPIXEL_OLD_CROSS;
            }
         case HYPIXEL_NEW:
            switch (hologramstype24) {
               case COMPLETED:
                  return MapTickIcon.HYPIXEL_NEW_GREEN;
               case CLEARED:
                  return MapTickIcon.HYPIXEL_NEW_WHITE;
               case ADJACENT:
                  return MapTickIcon.HYPIXEL_NEW_UNKNOWN;
               case FAILED:
                  return MapTickIcon.HYPIXEL_NEW_CROSS;
            }
         default:
            return null;
      }
   }

   public float method24(DungeonStateTracker holograms2_51) {
      return this.method17(holograms2_51) * ((Integer)this.field36.get()).intValue() / 100.0F;
   }

   public MapTickStyle method25(DungeonRoomTracker holograms4iterator1) {
      return holograms4iterator1.method30().method6() == MapRoomType.PUZZLE ? (MapTickStyle)this.field34.get() : (MapTickStyle)this.field13.get();
   }

   public boolean method26(MapTickStyle gui2extension21) {
      return gui2extension21 == MapTickStyle.ROOM_NAME || gui2extension21 == MapTickStyle.SECRETS || gui2extension21 == MapTickStyle.SECRETS_AND_NAME;
   }

   public ColorOption method27(DungeonPlayerTracker holograms4updater1) {
      if (this.field38.get() == HeadBorderStyle.CLASS_COLORS) {
         DungeonClass hologramstype2_22 = holograms4updater1.method37();
         if (hologramstype2_22 != null) {
            return switch (hologramstype2_22) {
               case MAGE -> this.field45;
               case TANK -> this.field46;
               case ARCHER -> this.field42;
               case HEALER -> this.field44;
               case BERSERK -> this.field43;
            };
         }
      }

      return holograms4updater1.method19() ? this.field40 : this.field41;
   }

   public boolean method28() {
      return this.field56 ? false : (Boolean)this.field50.get();
   }

   public RewindhandlersExtension method29() {
      return (RewindhandlersExtension)(this.field56 ? field4 : this.field18);
   }

   public RewindhandlersExtension method30() {
      return (RewindhandlersExtension)(this.field56 ? field4 : this.field19);
   }

   public MapScoreStyle method31() {
      return this.field56 ? MapScoreStyle.NONE : (MapScoreStyle)this.field15.get();
   }

   public RoomInfoPosition method32() {
      return this.field56 ? RoomInfoPosition.OFF : (RoomInfoPosition)this.field16.get();
   }

   public boolean method33() {
      return this.field56 ? false : (Boolean)this.field51.get();
   }

   public boolean method34() {
      return this.field56 ? false : (Boolean)this.field53.get();
   }

   public float method35() {
      return (Float)this.field20.get();
   }

   public MapPlayerStyle method36() {
      return (MapPlayerStyle)this.field14.get();
   }

   public float method37() {
      return (Float)this.field37.get();
   }

   public float method38() {
      return (Float)this.field39.get();
   }

   public MapPlayerStyle method39() {
      return (MapPlayerStyle)this.field14.get();
   }

   public HeadBorderStyle method40() {
      return (HeadBorderStyle)this.field38.get();
   }

   public boolean method41() {
      return (Boolean)this.field48.get();
   }

   public boolean method42() {
      return (Boolean)this.field49.get();
   }

   public float method43() {
      return (Float)this.field17.get();
   }

   public boolean method44() {
      return (Boolean)this.field54.get();
   }

   public boolean method45() {
      return (Boolean)this.field52.get();
   }

   public MapTickStyle method46() {
      return (MapTickStyle)this.field34.get();
   }

   public MapTickStyle method47() {
      return (MapTickStyle)this.field13.get();
   }

   public float method48() {
      return ((Integer)this.field36.get()).intValue();
   }

   public boolean method49() {
      return (Boolean)this.field35.get();
   }

   public float method50() {
      return (Float)this.field55.get() / 100.0F;
   }

   public void method51(Runnable runnable1) {
      this.field50.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> runnable1.run());
   }

   public void method52(Runnable runnable1) {
      this.field15.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> runnable1.run());
   }

   @Generated
   public boolean method53() {
      return this.field56;
   }

   @Generated
   public void method54(boolean flag1) {
      this.field56 = flag1;
   }
}
