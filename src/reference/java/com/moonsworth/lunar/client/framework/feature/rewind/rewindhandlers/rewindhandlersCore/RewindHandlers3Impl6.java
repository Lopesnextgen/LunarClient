package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.render.EventFogSetup.FogSource;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.highlight.NameTagRenderEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.EntityLabelRenderEvent.EntityLabelLinesEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;

public class RewindHandlers3Impl6 extends RewindHandlers3 {
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("worldRendering").method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("chromaKeying").method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderBlocks").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderClouds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderEntities").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderPlayers").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderSky").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderParticles").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderNametags").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderWorldBorder").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field19 = (ColorOption)((Data)OptionFactory.method8("chromaColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("chromaSky").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)OptionFactory.method7("chromaBlocks").method31();
   private double field22 = 0.0;

   public RewindHandlers3Impl6(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
      this.handle(NameTagRenderEvent.class, this::method1);
      this.method1(com.moonsworth.lunar.client.event.render.EventFogSetup.EventFogTint.class, this::method3, 0);
      this.handle(EventClientTick.class, this::method4);
      if (ThreadModuleDump63.MC_VERSION >= 16) {
         this.handle(EntityLabelLinesEvent.class, this::method2);
      }
   }

   private void method1(NameTagRenderEvent var1) {
      if ((Boolean)this.field9.get() && !(Boolean)this.field17.get()) {
         var1.setCancelled(true);
      }
   }

   @Annotation2(min = 16)
   private void method2(EntityLabelLinesEvent var1) {
      if ((Boolean)this.field9.get() && !(Boolean)this.field17.get() && var1.method2() != null) {
         var1.setCancelled(true);
      }
   }

   private void method3(com.moonsworth.lunar.client.event.render.EventFogSetup.EventFogTint var1) {
      if ((Boolean)this.field10.get()
         && ((Boolean)this.field20.get() || (Boolean)this.field21.get())
         && (var1.method1() == FogSource.RENDER_DISTANCE || var1.method1() == FogSource.ATMOSPHERIC)) {
         float var2 = (float)var1.method2();
         int var3 = this.field19.method1(var2);
         var1.method1(ThreadModuleDump23.method5(var3), ThreadModuleDump23.greenFloat(var3), ThreadModuleDump23.method7(var3));
      }
   }

   private void method4(EventClientTick var1) {
      if (ThreadModuleDump63.MC_VERSION >= 35) {
         double var2 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getChunkSectionFadeInTime$v1_21_11();
         if ((Boolean)ThreadModuleDump63.method4().method90().method23().get()) {
            if (this.field22 != 0.0) {
               ThreadModuleDump63.method3().bridge$getGameSettings().bridge$setChunkSectionFadeInTime$v1_21_11(this.field22);
               this.field22 = 0.0;
            }
         } else if (var2 != 0.0) {
            this.field22 = var2;
            ThreadModuleDump63.method3().bridge$getGameSettings().bridge$setChunkSectionFadeInTime$v1_21_11(0.0);
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
