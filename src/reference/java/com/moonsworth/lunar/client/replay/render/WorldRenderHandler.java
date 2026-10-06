package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.render.EventFog.FogKind;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityLabel.EventRenderEntityLabelLines;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;

public class WorldRenderHandler extends RewindHandler {
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("worldRendering").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("chromaKeying").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderBlocks").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderClouds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderEntities").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderPlayers").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderSky").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderParticles").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderNametags").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderWorldBorder").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ColorOption field19 = (ColorOption)((Data)OptionFactory.method8("chromaColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("chromaSky").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ToggleOption field21 = (ToggleOption)OptionFactory.method7("chromaBlocks").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private double field22 = 0.0;

   public WorldRenderHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
      this.handle(EventRenderNameTag.class, this::method1);
      this.method1(com.moonsworth.lunar.client.event.render.EventFog.EventFogColor.class, this::method3, 0);
      this.handle(EventTick.class, this::method4);
      if (Ref.MC_VERSION >= 16) {
         this.handle(EventRenderEntityLabelLines.class, this::method2);
      }
   }

   private void method1(EventRenderNameTag highlightimpl111) {
      if ((Boolean)this.field9.get() && !(Boolean)this.field17.get()) {
         highlightimpl111.setCancelled(true);
      }
   }

   @VersionGate(min = 16)
   private void method2(EventRenderEntityLabelLines data31) {
      if ((Boolean)this.field9.get() && !(Boolean)this.field17.get() && data31.CIHCCCHOCCOHHHRCCRRIOCCHIOOIOC() != null) {
         data31.setCancelled(true);
      }
   }

   private void method3(com.moonsworth.lunar.client.event.render.EventFog.EventFogColor data21) {
      if ((Boolean)this.field10.get()
         && ((Boolean)this.field20.get() || (Boolean)this.field21.get())
         && (data21.HROHCHROIIOCOIOHIRCCOHCRIIHROC() == FogKind.RENDER_DISTANCE || data21.HROHCHROIIOCOIOHIRCCOHCRIIHROC() == FogKind.ATMOSPHERIC)) {
         float value2 = (float)data21.method2();
         int number3 = this.field19.method1(value2);
         data21.method1(ColorUtils.method5(number3), ColorUtils.method6(number3), ColorUtils.method7(number3));
      }
   }

   private void method4(EventTick highlightimpl21) {
      if (Ref.MC_VERSION >= 35) {
         double value2 = Ref.method3().bridge$getGameSettings().bridge$getChunkSectionFadeInTime$v1_21_11();
         if ((Boolean)Ref.method4().method90().method23().get()) {
            if (this.field22 != 0.0) {
               Ref.method3().bridge$getGameSettings().bridge$setChunkSectionFadeInTime$v1_21_11(this.field22);
               this.field22 = 0.0;
            }
         } else if (value2 != 0.0) {
            this.field22 = value2;
            Ref.method3().bridge$getGameSettings().bridge$setChunkSectionFadeInTime$v1_21_11(0.0);
         }
      }
   }

   @Generated
   public ToggleOption method14() {
      return this.field9;
   }

   @Generated
   public ToggleOption method15() {
      return this.field10;
   }

   @Generated
   public ToggleOption method16() {
      return this.field11;
   }

   @Generated
   public ToggleOption method17() {
      return this.field12;
   }

   @Generated
   public ToggleOption method19() {
      return this.field13;
   }

   @Generated
   public ToggleOption method21() {
      return this.field14;
   }

   @Generated
   public ToggleOption method22() {
      return this.field15;
   }

   @Generated
   public ToggleOption method23() {
      return this.field16;
   }

   @Generated
   public ToggleOption method24() {
      return this.field17;
   }

   @Generated
   public ToggleOption method25() {
      return this.field18;
   }

   @Generated
   public ColorOption method26() {
      return this.field19;
   }

   @Generated
   public ToggleOption method27() {
      return this.field20;
   }

   @Generated
   public ToggleOption method28() {
      return this.field21;
   }

   @Generated
   public double method29() {
      return this.field22;
   }
}
