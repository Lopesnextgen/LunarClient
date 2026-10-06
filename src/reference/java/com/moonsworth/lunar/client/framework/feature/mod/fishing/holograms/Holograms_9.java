package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler29;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
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

public class Holograms_9 {
   private static final Map<HologramsType5, RewindhandlersExtension> field1 = new EnumMap<HologramsType5, RewindhandlersExtension>(HologramsType5.class) {
      {
         this.put(HologramsType5.CLEAR, RewindhandlersExtension.method23(-9288933));
         this.put(HologramsType5.SPAWN, RewindhandlersExtension.method23(-16745472));
         this.put(HologramsType5.PUZZLE, RewindhandlersExtension.method23(-5092136));
         this.put(HologramsType5.MINIBOSS, RewindhandlersExtension.method23(-1710797));
         this.put(HologramsType5.FAIRY, RewindhandlersExtension.method23(-884827));
         this.put(HologramsType5.BLOOD, RewindhandlersExtension.method23(-65536));
         this.put(HologramsType5.TRAP, RewindhandlersExtension.method23(-2588877));
         this.put(HologramsType5.UNKNOWN, RewindhandlersExtension.method23(-12500671));
         this.put(HologramsType5.WITHER_DOOR, RewindhandlersExtension.method23(-16777216));
      }
   };
   private static final Map<HologramsType5, RewindhandlersExtension> field2 = new EnumMap<HologramsType5, RewindhandlersExtension>(HologramsType5.class) {
      {
         this.put(HologramsType5.CLEAR, RewindhandlersExtension.method23(-9749999));
         this.put(HologramsType5.SPAWN, RewindhandlersExtension.method23(-15432448));
         this.put(HologramsType5.PUZZLE, RewindhandlersExtension.method23(-9109371));
         this.put(HologramsType5.MINIBOSS, RewindhandlersExtension.method23(-73984));
         this.put(HologramsType5.FAIRY, RewindhandlersExtension.method23(-2096897));
         this.put(HologramsType5.BLOOD, RewindhandlersExtension.method23(-65536));
         this.put(HologramsType5.TRAP, RewindhandlersExtension.method23(-2588877));
         this.put(HologramsType5.UNKNOWN, RewindhandlersExtension.method23(-12500671));
         this.put(HologramsType5.WITHER_DOOR, RewindhandlersExtension.method23(-16777216));
      }
   };
   private static final RewindhandlersExtension field3 = RewindhandlersExtension.method23(-10734578);
   private static final RewindhandlersExtension field4 = RewindhandlersExtension.method23(0);
   private final HologramsType field5;
   @NotNull
   private final GuiRewindhandlersHandler29 field6;
   private final LabelOption field7 = (LabelOption)OptionFactory.method15("mapStyle").method31();
   private final LabelOption field8 = (LabelOption)OptionFactory.method15("mapTickStyle").method31();
   private final LabelOption field9 = (LabelOption)OptionFactory.method15("mapPlayerStyle").method31();
   private final LabelOption field10 = (LabelOption)OptionFactory.method15("mapScoreInfo").method31();
   private final LabelOption field11 = (LabelOption)OptionFactory.method15("currentRoomInfo").method31();
   private final EnumOption<Gui2Extension6> field12;
   private final EnumOption<Gui2Extension2> field13;
   private final EnumOption<Gui2Extension7> field14;
   private final EnumOption<Gui2Extension5> field15;
   private final EnumOption<Gui2Extension4> field16;
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
   private final EnumOption<Gui2Extension2> field32;
   private final ToggleOption field33;
   private final EnumOption<Gui2Extension2> field34;
   private final ToggleOption field35;
   private final IntegerOption field36;
   private final FloatOption field37;
   private final EnumOption<Gui2Extension> field38;
   private final FloatOption field39;
   private final ColorOption field40;
   private final ColorOption field41;
   private final ColorOption field42;
   private final ColorOption field43;
   private final ColorOption field44;
   private final ColorOption field45;
   private final ColorOption field46;
   private final EnumOption<Gui2Extension3> field47;
   private final ToggleOption field48;
   private final ToggleOption field49;
   private final ToggleOption field50;
   private final ToggleOption field51;
   private final ToggleOption field52;
   private final ToggleOption field53;
   private final ToggleOption field54;
   private final FloatOption field55;
   private boolean field56 = false;

   public Holograms_9(HologramsType var1, @NotNull GuiRewindhandlersHandler29 var2) {
      this.field5 = var1;
      this.field6 = var2;
      this.field12 = (EnumOption<Gui2Extension6>)OptionFactory.method10(this.method5("MapStyle"), Gui2Extension6.LEGAL_MAP)
         .method31();
      this.field13 = (EnumOption<Gui2Extension2>)OptionFactory.method10(this.method5("MapTickStyle"), Gui2Extension2.SECRETS)
         .method31();
      this.field14 = (EnumOption<Gui2Extension7>)OptionFactory.method10(this.method5("MapPlayerStyle"), Gui2Extension7.HEADS)
         .method31();
      this.field15 = (EnumOption<Gui2Extension5>)OptionFactory.method10(
            this.method5("MapScoreInfo"), var1 == HologramsType.SECONDARY ? Gui2Extension5.NONE : Gui2Extension5.SIMPLIFIED
         )
         .method31();
      this.field16 = (EnumOption<Gui2Extension4>)OptionFactory.method10(
            this.method5("CurrentRoomInfo"), var1 == HologramsType.SECONDARY ? Gui2Extension4.OFF : Gui2Extension4.RIGHT
         )
         .method31();
      this.field17 = (FloatOption)((Data)((Data)OptionFactory.method2(this.method5("CurrentRoomInfoScale")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.1F, 2.0F))
         .method31();
      this.field18 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MapBackground"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1426063360))
         .method31();
      this.field19 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MapBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
         .method31();
      this.field20 = (FloatOption)((Data)((Data)OptionFactory.method2(this.method5("MapBorderThickness")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.0F, 2.0F))
         .method31();
      this.field21 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  this.method5("RoomGap")
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(20))
            .method7(0, 100))
         .method31();
      this.field22 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  this.method5("DoorWidth")
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(30))
            .method7(0, 100))
         .method31();
      this.field23 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("ClearRoom"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-9288933))
         .method31();
      this.field24 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("SpawnRoom"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16745472))
         .method31();
      this.field25 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("PuzzleRoom"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5092136))
         .method31();
      this.field26 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MiniBoss"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1710797))
         .method31();
      this.field27 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("Fairy"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-884827))
         .method31();
      this.field28 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("Blood"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
         .method31();
      this.field29 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("Trap"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2588877))
         .method31();
      this.field30 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("Unknown"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-12500671))
         .method31();
      this.field31 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MapZoom"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
         .method31();
      this.field32 = (EnumOption<Gui2Extension2>)OptionFactory.method10(this.method5("SecondaryTickStyle"), Gui2Extension2.FANCY)
         .method4(Gui2Extension2.$VALUES)
         .method31();
      this.field33 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("SecondaryForCompleted")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method31();
      this.field34 = (EnumOption<Gui2Extension2>)OptionFactory.method10(this.method5("PuzzleTickStyle"), Gui2Extension2.ROOM_NAME)
         .method31();
      this.field35 = (ToggleOption)OptionFactory.method7(this.method5("CenterCheckMarks")).method31();
      this.field36 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  this.method5("TickScale")
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(80))
            .method7(0, 100))
         .method31();
      this.field37 = (FloatOption)((Data)((Data)OptionFactory.method2(this.method5("HeadScale")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
            .method8(0.0F, 6.0F))
         .method31();
      this.field38 = (EnumOption<Gui2Extension>)OptionFactory.method10(this.method5("HeadBorderStyle"), Gui2Extension.NONE)
         .method31();
      this.field39 = (FloatOption)((Data)((Data)OptionFactory.method2(this.method5("HeadBorderThickness")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.0F, 2.0F))
         .method31();
      this.field40 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("SelfBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
         .method31();
      this.field41 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("OtherBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16777216))
         .method31();
      this.field42 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("ArcherBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-14767566))
         .method31();
      this.field43 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("BerserkerBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
         .method31();
      this.field44 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("HealerBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1030416))
         .method31();
      this.field45 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("MageBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-12135726))
         .method31();
      this.field46 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(this.method5("TankBorder"))
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-6908266))
         .method31();
      this.field47 = (EnumOption<Gui2Extension3>)OptionFactory.method10(this.method5("PlayerNames"), Gui2Extension3.LEAP_HELD)
         .method31();
      this.field48 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("ShowInBoss")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(var1 != HologramsType.SECONDARY))
         .method31();
      this.field49 = (ToggleOption)OptionFactory.method7(this.method5("ShowWithTab")).method31();
      this.field50 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("ShowTabs")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(var1 != HologramsType.SECONDARY))
         .method31();
      this.field51 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("Spinny")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(var1 == HologramsType.SECONDARY))
         .method31();
      this.field52 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("OrientIcons")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method31();
      this.field53 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7(this.method5("Centered")).OOOIROIIOCOOHICRIRHHHRROHHHHIO(var1 == HologramsType.SECONDARY))
         .method31();
      this.field54 = (ToggleOption)OptionFactory.method7(this.method5("MimicPrefix")).method31();
      this.field55 = (FloatOption)((Data)((Data)((Data)OptionFactory.method2(this.method5("MapZoom")).CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(100.0F))
               .method8(25.0F, 200.0F))
            .method6(1))
         .method31();
   }

   public void method1(RootSettingsAssembler var1) {
      this.method2(var1, null);
   }

   public void method2(RootSettingsAssembler var1, BooleanSupplier var2) {
      this.method4(var1, var2, this::method8, this.field48, this.field49, this.field50, this.field53);
      this.method3(var1, var2, this.field51);
      this.method3(var1, () -> var2 != null && var2.getAsBoolean() || !(Boolean)this.field51.get() && !(Boolean)this.field53.get(), this.field55);
      this.method4(var1, var2, this::method7, this.field52);
      this.method4(var1, var2, this::method8, this.field10, this.field11);
      this.method3(var1, var2, this.field7, this.field8, this.field9);
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field15}))
         .method2(new ClientOption[]{this.field10});
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field54}))
         .method2(new ClientOption[]{this.field10})
         .method2(this::method13);
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field16}))
         .method2(new ClientOption[]{this.field11});
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field17}))
         .method2(new ClientOption[]{this.field11})
         .method2(() -> this.field16.get() == Gui2Extension4.OFF);
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field12, this.field19, this.field20, this.field18}))
         .method2(new ClientOption[]{this.field7});
      ((SettingsSectionImpl)var1.method11(
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
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field13, this.field34, this.field35}))
         .method2(new ClientOption[]{this.field8});
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field36})).method2(new ClientOption[]{this.field8});
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field32, this.field33}))
         .method2(new ClientOption[]{this.field8})
         .method2(this::method6);
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field14})).method2(new ClientOption[]{this.field9});
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field47}))
         .method2(new ClientOption[]{this.field9})
         .method2(this::method8);
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field38, this.field37}))
         .method2(new ClientOption[]{this.field9})
         .method2(this::method11);
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field39}))
         .method2(new ClientOption[]{this.field9})
         .method2(() -> this.method9() || this.method11());
      ((SettingsSectionImpl)var1.method11(
            new ClientOption[]{this.field42, this.field43, this.field44, this.field45, this.field46}
         ))
         .method2(new ClientOption[]{this.field9})
         .method2(this::method10);
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field40}))
         .method2(new ClientOption[]{this.field9})
         .method2(() -> this.method12() || this.method11() || this.method9() || !this.method10());
      ((SettingsSectionImpl)var1.method11(new ClientOption[]{this.field41}))
         .method2(new ClientOption[]{this.field9})
         .method2(() -> this.method11() || this.method9() || !this.method10());
   }

   private void method3(RootSettingsAssembler var1, BooleanSupplier var2, ClientOption<?>... var3) {
      if (var2 != null) {
         ((SettingsSectionImpl)var1.method11(var3)).method2(var2);
      } else {
         var1.method11(var3);
      }
   }

   private void method4(RootSettingsAssembler var1, BooleanSupplier var2, BooleanSupplier var3, ClientOption<?>... var4) {
      if (var2 == null) {
         ((SettingsSectionImpl)var1.method11(var4)).method2(var3);
      } else {
         ((SettingsSectionImpl)var1.method11(var4)).method2(() -> var2.getAsBoolean() || var3.getAsBoolean());
      }
   }

   private String method5(String var1) {
      return this.field5.getOptionPrefix() + "BetterMap" + var1;
   }

   private boolean method6() {
      return this.field13.get() != Gui2Extension2.SECRETS
         && this.field13.get() != Gui2Extension2.ROOM_NAME
         && this.field13.get() != Gui2Extension2.SECRETS_AND_NAME;
   }

   private boolean method7() {
      return !(Boolean)this.field51.get();
   }

   public boolean method8() {
      return this.field5 == HologramsType.SPIRIT_LEAP;
   }

   private boolean method9() {
      return this.field38.get() == Gui2Extension.NONE;
   }

   private boolean method10() {
      return this.field38.get() != Gui2Extension.NONE;
   }

   private boolean method11() {
      return this.field14.get() == Gui2Extension7.OFF || this.field14.get() == Gui2Extension7.ICONS;
   }

   private boolean method12() {
      return this.field14.get() == Gui2Extension7.SELF_ICON;
   }

   private boolean method13() {
      return this.field15.get() != Gui2Extension5.SIMPLIFIED;
   }

   private boolean method14() {
      return this.field12.get() != Gui2Extension6.CUSTOM;
   }

   private Gui2Extension6 method15() {
      return (Gui2Extension6)this.field12.get();
   }

   private float method16() {
      return switch (this.method15()) {
         case HYPIXEL -> 25.0F;
         case LEGAL_MAP -> 23.0F;
         default -> ((Integer)this.field21.get()).intValue();
      };
   }

   public float method17(Holograms2_5 var1) {
      float var2 = this.method16();
      return (100.0F - (Float)this.field20.get() * 2.0F) / Math.max(var1.method27(), var1.method28()) * (100.0F - var2) / 100.0F;
   }

   public float method18(Holograms2_5 var1) {
      float var2 = switch (this.method15()) {
         case HYPIXEL -> 42.0F;
         case LEGAL_MAP -> 33.0F;
         default -> ((Integer)this.field22.get()).intValue();
      };
      return this.method17(var1) * var2 / 100.0F;
   }

   public float method19(Holograms2_5 var1) {
      float var2 = this.method16();
      return (100.0F - (Float)this.field20.get() * 2.0F) / Math.max(var1.method27(), var1.method28()) * var2 / 100.0F;
   }

   public RewindhandlersExtension method20(HologramsType5 var1, boolean var2) {
      return (RewindhandlersExtension)(switch (this.method15()) {
         case HYPIXEL -> (RewindhandlersExtension)field1.get(var1);
         case LEGAL_MAP -> var2 && var1 == HologramsType5.CLEAR ? field3 : (RewindhandlersExtension)field2.get(var1);
         case CUSTOM -> {
            switch (var1) {
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

      if (this.field47.get() != Gui2Extension3.LEAP_HELD) {
         return this.field47.get() == Gui2Extension3.ALWAYS;
      }

      String var1 = this.field6.method9();
      return "SPIRIT_LEAP".equals(var1) || "INFINITE_SPIRIT_LEAP".equals(var1);
   }

   public boolean method22() {
      return this.field13.get() != Gui2Extension2.SECRETS
            && this.field13.get() != Gui2Extension2.ROOM_NAME
            && this.field13.get() != Gui2Extension2.SECRETS_AND_NAME
         || this.field32.get() != Gui2Extension2.NONE;
   }

   public HologramsType2 method23(Holograms4Iterator var1) {
      Gui2Extension2 var2;
      if (var1.method30().method6() != HologramsType5.PUZZLE) {
         boolean var3 = this.method26(this.method25(var1));
         if (var3 && var1.method14() != -1 && (var1.method14() != var1.method30().method1() || !(Boolean)this.field33.get())) {
            return null;
         }

         var2 = var3 ? (Gui2Extension2)this.field32.get() : (Gui2Extension2)this.field13.get();
      } else {
         var2 = (Gui2Extension2)this.field34.get();
      }

      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 var4 = var1.method30().method2();
      switch (var2) {
         case FANCY:
            switch (var4) {
               case COMPLETED:
                  return HologramsType2.FANCY_GREEN;
               case CLEARED:
                  return HologramsType2.FANCY_WHITE;
               case ADJACENT:
                  return HologramsType2.FANCY_UNKNOWN;
               case FAILED:
                  return HologramsType2.FANCY_CROSS;
            }
         case HIGHRES:
            switch (var4) {
               case COMPLETED:
                  return HologramsType2.HIGHRES_GREEN;
               case CLEARED:
                  return HologramsType2.HIGHRES_WHITE;
               case ADJACENT:
                  return HologramsType2.HIGHRES_UNKNOWN;
               case FAILED:
                  return HologramsType2.HIGHRES_CROSS;
            }
         case HYPIXEL_OLD:
            switch (var4) {
               case COMPLETED:
                  return HologramsType2.HYPIXEL_OLD_GREEN;
               case CLEARED:
                  return HologramsType2.HYPIXEL_OLD_WHITE;
               case ADJACENT:
                  return HologramsType2.HYPIXEL_OLD_UNKNOWN;
               case FAILED:
                  return HologramsType2.HYPIXEL_OLD_CROSS;
            }
         case HYPIXEL_NEW:
            switch (var4) {
               case COMPLETED:
                  return HologramsType2.HYPIXEL_NEW_GREEN;
               case CLEARED:
                  return HologramsType2.HYPIXEL_NEW_WHITE;
               case ADJACENT:
                  return HologramsType2.HYPIXEL_NEW_UNKNOWN;
               case FAILED:
                  return HologramsType2.HYPIXEL_NEW_CROSS;
            }
         default:
            return null;
      }
   }

   public float method24(Holograms2_5 var1) {
      return this.method17(var1) * ((Integer)this.field36.get()).intValue() / 100.0F;
   }

   public Gui2Extension2 method25(Holograms4Iterator var1) {
      return var1.method30().method6() == HologramsType5.PUZZLE ? (Gui2Extension2)this.field34.get() : (Gui2Extension2)this.field13.get();
   }

   public boolean method26(Gui2Extension2 var1) {
      return var1 == Gui2Extension2.ROOM_NAME || var1 == Gui2Extension2.SECRETS || var1 == Gui2Extension2.SECRETS_AND_NAME;
   }

   public ColorOption method27(Holograms4Updater var1) {
      if (this.field38.get() == Gui2Extension.NONE) {
         HologramsType2_2 var2 = var1.method37();
         if (var2 != null) {
            return switch (var2) {
               case MAGE -> this.field45;
               case TANK -> this.field46;
               case ARCHER -> this.field42;
               case HEALER -> this.field44;
               case BERSERK -> this.field43;
            };
         }
      }

      return var1.method19() ? this.field40 : this.field41;
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

   public Gui2Extension5 method31() {
      return this.field56 ? Gui2Extension5.NONE : (Gui2Extension5)this.field15.get();
   }

   public Gui2Extension4 method32() {
      return this.field56 ? Gui2Extension4.OFF : (Gui2Extension4)this.field16.get();
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

   public Gui2Extension7 method36() {
      return (Gui2Extension7)this.field14.get();
   }

   public float method37() {
      return (Float)this.field37.get();
   }

   public float method38() {
      return (Float)this.field39.get();
   }

   public Gui2Extension7 method39() {
      return (Gui2Extension7)this.field14.get();
   }

   public Gui2Extension method40() {
      return (Gui2Extension)this.field38.get();
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

   public Gui2Extension2 method46() {
      return (Gui2Extension2)this.field34.get();
   }

   public Gui2Extension2 method47() {
      return (Gui2Extension2)this.field13.get();
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

   public void method51(Runnable var1) {
      this.field50.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> var1.run());
   }

   public void method52(Runnable var1) {
      this.field15.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> var1.run());
   }

   @Generated
   public boolean method53() {
      return this.field56;
   }

   @Generated
   public void method54(boolean var1) {
      this.field56 = var1;
   }
}
