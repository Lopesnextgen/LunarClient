package com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles;

import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms7_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data4;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.dungeonpuzzles.SkyblockCreeperBeams;
import com.moonsworth.lunar.client.util.Annotation5;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDungeonPuzzles extends AbstractFeature {
   private final GuiRewindhandlersHandler2_2 field8 = (GuiRewindhandlersHandler2_2)this.method39(GuiRewindhandlersHandler2_2.class);
   private final Holograms7_2 field9;
   private final SkyblockTicTacToe field10;
   private final SkyblockThreeWeirdos field11;
   private final SkyblockCreeperBeams field12;
   private final SkyblockWaterRoom field13;
   private final SkyblockTpMaze field14;
   private final SkyblockIceFill field15;
   private final SkyblockIcePath field16;
   private final SkyblockQuiz field17;
   private final SkyblockHigherLower field18;
   private final SkyblockBoulder field19;
   private final ToggleOption field20 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("threeWeirdos")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("creeperBeams")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("waterBoard")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field23 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("tpMaze")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("quiz")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("boulder")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("secretBoulder")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("ticTacToe")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("iceFill")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field29 = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2(
               "iceFillLineThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final ToggleOption field30 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("icePath")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field31 = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2(
               "icePathLineThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final ToggleOption field32 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("higherLower")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field33 = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2(
               "higherLowerLineThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final ToggleOption field34 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("fastWaterRoom")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field35 = (ToggleOption)OptionFactory.method7("showWaterDebugInfo").method31();
   private Holograms4Iterator field36;
   private Vector3iBridge field37;

   public SkyblockDungeonPuzzles(Skyblock var1) {
      super(false);
      this.field9 = new Holograms7_2(this);
      this.field10 = new SkyblockTicTacToe(this, this.field27);
      this.field11 = new SkyblockThreeWeirdos(this, this.field20);
      this.field12 = new SkyblockCreeperBeams(this, this.field21);
      this.field13 = new SkyblockWaterRoom(this, this.field22);
      this.field14 = new SkyblockTpMaze(this, this.field23);
      this.field15 = new SkyblockIceFill(this, this.field28);
      this.field16 = new SkyblockIcePath(this, this.field30);
      this.field17 = new SkyblockQuiz(this, this.field24);
      this.field18 = new SkyblockHigherLower(this, this.field32);
      this.field19 = new SkyblockBoulder(this, this.field25);
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(Framework.field17, Framework2.method2(SettingsPage.DUNGEONS));
      this.method2(Framework.field19, Framework11.method1(this, () -> Click3.getIsland() == Gui2Extension3.DUNGEON));
      this.handle(EventWorldChanged.class, this::method5);
      this.handle(HighlightBase$Data4.class, var1x -> this.method13());
      this.handle(HighlightBase$Data3.class, var1x -> this.field36 = null);
      this.handle(Data.class, this::method3);
      this.OHROCHICOIOICHOCRROORRCIIICIHO(this::onDisable);
   }

   private void onDisable() {
      this.field36 = null;
   }

   @Override
   public List<Framework7Extension> method9() {
      return List.of(this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, this.field16, this.field17, this.field18, this.field19);
   }

   @Override
   public void method3(boolean var1) {
      if (var1) {
         this.method13();
      }
   }

   private void method3(Data var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         if (var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC().startsWith("You used the Architect's First Draft to reset ")) {
            this.method13();
         }
      }
   }

   public void method13() {
      Holograms2_5 var1 = this.field8.method5().orElse(null);
      if (var1 == null) {
         this.field36 = null;
      } else {
         Holograms4Iterator var2 = var1.method29().method7();
         if (var2 != null && var2.method30().method6() == HologramsType5.PUZZLE) {
            HologramsType8 var3 = var2.method25();
            if (var3 != null && !var2.method23().isEmpty()) {
               this.field36 = var2;
               this.field9.method1(var2, var3);
            } else {
               this.field36 = null;
            }
         } else {
            this.field36 = null;
         }
      }
   }

   private void method5(EventWorldChanged var1) {
      this.field36 = null;
   }

   @Annotation5
   @Override
   public String getId() {
      return "SKYBLOCK_DUNGEON_PUZZLES";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.method230(
         new ClientOption[]{
            this.field27, this.field20, this.field21, this.field22, this.field23, this.field24, this.field25, this.field26, this.field34, this.field35
         }
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field28, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field29}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field30, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field31}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field32, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field33}));
   }

   @Generated
   public GuiRewindhandlersHandler2_2 method14() {
      return this.field8;
   }

   @Generated
   public Holograms7_2 method15() {
      return this.field9;
   }

   @Generated
   public SkyblockTicTacToe method16() {
      return this.field10;
   }

   @Generated
   public SkyblockThreeWeirdos method17() {
      return this.field11;
   }

   @Generated
   public SkyblockCreeperBeams method19() {
      return this.field12;
   }

   @Generated
   public SkyblockWaterRoom method21() {
      return this.field13;
   }

   @Generated
   public SkyblockTpMaze method22() {
      return this.field14;
   }

   @Generated
   public SkyblockIceFill method23() {
      return this.field15;
   }

   @Generated
   public SkyblockIcePath method24() {
      return this.field16;
   }

   @Generated
   public SkyblockQuiz method25() {
      return this.field17;
   }

   @Generated
   public SkyblockHigherLower method26() {
      return this.field18;
   }

   @Generated
   public SkyblockBoulder method27() {
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

   @Generated
   public ToggleOption method37() {
      return this.field26;
   }

   @Generated
   public ToggleOption method38() {
      return this.field27;
   }

   @Generated
   public ToggleOption method39() {
      return this.field28;
   }

   @Generated
   public FloatOption method40() {
      return this.field29;
   }

   @Generated
   public ToggleOption method41() {
      return this.field30;
   }

   @Generated
   public FloatOption method42() {
      return this.field31;
   }

   @Generated
   public ToggleOption method43() {
      return this.field32;
   }

   @Generated
   public FloatOption method44() {
      return this.field33;
   }

   @Generated
   public ToggleOption method45() {
      return this.field34;
   }

   @Generated
   public ToggleOption method46() {
      return this.field35;
   }

   @Generated
   public Holograms4Iterator method47() {
      return this.field36;
   }

   @Generated
   public Vector3iBridge method48() {
      return this.field37;
   }
}
