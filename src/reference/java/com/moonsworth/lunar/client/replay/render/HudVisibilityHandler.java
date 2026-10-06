package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.ResultEvent.Outcome;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderCrosshair;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import lombok.Generated;

public class HudVisibilityHandler extends RewindHandler {
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("uiRendering").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("hideEnabled").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("crosshair").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("chat").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("titles").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("scoreboard").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("bossbar").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hotbar").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("overlayMessage").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("vignette").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hudMods").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("potionEffectsHud").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

   public HudVisibilityHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
      this.handle(EventRenderCrosshair.class, this::method1);
   }

   private void method1(EventRenderCrosshair highlightimpl221) {
      if (!this.method14()) {
         highlightimpl221.method2(Outcome.DENY);
      }
   }

   public boolean method14() {
      return !(Boolean)this.field9.get() || this.field11.get() != this.field10.get();
   }

   public boolean method15() {
      return !(Boolean)this.field9.get() || this.field12.get() != this.field10.get();
   }

   public boolean method16() {
      return !(Boolean)this.field9.get() || this.field13.get() != this.field10.get();
   }

   public boolean method17() {
      return !(Boolean)this.field9.get() || this.field14.get() != this.field10.get();
   }

   public boolean method19() {
      return !(Boolean)this.field9.get() || this.field15.get() != this.field10.get();
   }

   public boolean method21() {
      return !(Boolean)this.field9.get() || this.field16.get() != this.field10.get();
   }

   public boolean method22() {
      return !(Boolean)this.field9.get() || this.field17.get() != this.field10.get();
   }

   public boolean method23() {
      return !(Boolean)this.field9.get() || this.field18.get() != this.field10.get();
   }

   public boolean method24() {
      return !(Boolean)this.field9.get() || this.field19.get() != this.field10.get();
   }

   public boolean method25() {
      return !(Boolean)this.field9.get() || this.field20.get() != this.field10.get();
   }

   @Generated
   public ToggleOption method26() {
      return this.field9;
   }

   @Generated
   public ToggleOption method27() {
      return this.field10;
   }

   @Generated
   public ToggleOption method28() {
      return this.field11;
   }

   @Generated
   public ToggleOption method29() {
      return this.field12;
   }

   @Generated
   public ToggleOption method30() {
      return this.field13;
   }

   @Generated
   public ToggleOption method34() {
      return this.field14;
   }

   @Generated
   public ToggleOption method35() {
      return this.field15;
   }

   @Generated
   public ToggleOption method36() {
      return this.field16;
   }

   @Generated
   public ToggleOption method37() {
      return this.field17;
   }

   @Generated
   public ToggleOption method38() {
      return this.field18;
   }

   @Generated
   public ToggleOption method39() {
      return this.field19;
   }

   @Generated
   public ToggleOption method40() {
      return this.field20;
   }
}
