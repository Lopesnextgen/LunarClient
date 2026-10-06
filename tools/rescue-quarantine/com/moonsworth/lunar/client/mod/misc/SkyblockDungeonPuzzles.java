package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework11;
import com.moonsworth.lunar.client.framework.Framework2;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms7_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase.Data4;
import com.moonsworth.lunar.client.highlight.mixin.fishing.HighlightBase4.Data3;
import com.moonsworth.lunar.client.lighting.Lighting;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.lighting.LightingExtension472;
import com.moonsworth.lunar.client.lighting.LightingExtension443.Data2;
import com.moonsworth.lunar.client.lighting.LightingExtension472.Data;
import com.moonsworth.lunar.client.lighting.rewindhandlers.RewindhandlersType;
import com.moonsworth.lunar.client.mod.render.SkyblockCreeperBeams;
import com.moonsworth.lunar.client.util.Annotation5;
import java.util.List;
import lombok.Generated;

public class SkyblockDungeonPuzzles extends Framework7Extension2 {
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
   private final LightingExtension443 field20 = (LightingExtension443)((Data2)Lighting.method7("threeWeirdos").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field21 = (LightingExtension443)((Data2)Lighting.method7("creeperBeams").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field22 = (LightingExtension443)((Data2)Lighting.method7("waterBoard").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field23 = (LightingExtension443)((Data2)Lighting.method7("tpMaze").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field24 = (LightingExtension443)((Data2)Lighting.method7("quiz").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field25 = (LightingExtension443)((Data2)Lighting.method7("boulder").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field26 = (LightingExtension443)((Data2)Lighting.method7("secretBoulder").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field27 = (LightingExtension443)((Data2)Lighting.method7("ticTacToe").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field28 = (LightingExtension443)((Data2)Lighting.method7("iceFill").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension472 field29 = (LightingExtension472)((Data)((Data)Lighting.method2("iceFillLineThickness")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(1.0F, 10.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field30 = (LightingExtension443)((Data2)Lighting.method7("icePath").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension472 field31 = (LightingExtension472)((Data)((Data)Lighting.method2("icePathLineThickness")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(1.0F, 10.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field32 = (LightingExtension443)((Data2)Lighting.method7("higherLower").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension472 field33 = (LightingExtension472)((Data)((Data)Lighting.method2("higherLowerLineThickness")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(1.0F, 10.0F))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field34 = (LightingExtension443)((Data2)Lighting.method7("fastWaterRoom").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field35 = (LightingExtension443)Lighting.method7("showWaterDebugInfo").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private Holograms4Iterator field36;
   private Horsestats20 field37;

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
      this.method2(Framework.field17, Framework2.method2(RewindhandlersType.DUNGEONS));
      this.method2(Framework.field19, Framework11.method1(this, () -> Click3.method2() == Gui2Extension3.DUNGEON));
      this.handle(Data3.class, this::method5);
      this.handle(Data4.class, var1x -> this.method13());
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase.Data3.class, var1x -> this.field36 = null);
      this.handle(com.moonsworth.lunar.client.highlight.mixin.HighlightImpl.Data.class, this::method3);
      this.OHROCHICOIOICHOCRROORRCIIICIHO(this::onDisable);
   }

   private void onDisable() {
      this.field36 = null;
   }

   public List<Framework7Extension> method9() {
      return List.of(this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, this.field16, this.field17, this.field18, this.field19);
   }

   public void method3(boolean var1) {
      if (var1) {
         this.method13();
      }
   }

   private void method3(com.moonsworth.lunar.client.highlight.mixin.HighlightImpl.Data var1) {
      if (Click3.method2() == Gui2Extension3.DUNGEON) {
         if (var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC().startsWith("You used the Architect's First Draft to reset ")) {
            this.method13();
         }
      }
   }

   public void method13() {
      Holograms2_5 var1 = (Holograms2_5)this.field8.method5().orElse(null);
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

   private void method5(Data3 var1) {
      this.field36 = null;
   }

   @Annotation5
   public String getId() {
      return "SKYBLOCK_DUNGEON_PUZZLES";
   }

   public void method2(LightingExtension23 var1) {
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
         new LightingExtension[]{
            this.field27, this.field20, this.field21, this.field22, this.field23, this.field24, this.field25, this.field26, this.field34, this.field35
         }
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field28, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field29}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field30, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field31}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field32, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field33}));
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
   public LightingExtension443 method28() {
      return this.field20;
   }

   @Generated
   public LightingExtension443 method29() {
      return this.field21;
   }

   @Generated
   public LightingExtension443 method30() {
      return this.field22;
   }

   @Generated
   public LightingExtension443 method34() {
      return this.field23;
   }

   @Generated
   public LightingExtension443 method35() {
      return this.field24;
   }

   @Generated
   public LightingExtension443 method36() {
      return this.field25;
   }

   @Generated
   public LightingExtension443 method37() {
      return this.field26;
   }

   @Generated
   public LightingExtension443 method38() {
      return this.field27;
   }

   @Generated
   public LightingExtension443 method39() {
      return this.field28;
   }

   @Generated
   public LightingExtension472 method40() {
      return this.field29;
   }

   @Generated
   public LightingExtension443 method41() {
      return this.field30;
   }

   @Generated
   public LightingExtension472 method42() {
      return this.field31;
   }

   @Generated
   public LightingExtension443 method43() {
      return this.field32;
   }

   @Generated
   public LightingExtension472 method44() {
      return this.field33;
   }

   @Generated
   public LightingExtension443 method45() {
      return this.field34;
   }

   @Generated
   public LightingExtension443 method46() {
      return this.field35;
   }

   @Generated
   public Holograms4Iterator method47() {
      return this.field36;
   }

   @Generated
   public Horsestats20 method48() {
      return this.field37;
   }
}
