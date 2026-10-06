package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.OutcomeEvent.Type;
import com.moonsworth.lunar.client.event.mixin.highlight.CrosshairRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import lombok.Generated;

public class RewindHandlers3Impl4 extends RewindHandlers3 {
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("uiRendering").method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("hideEnabled").method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("crosshair").method4(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("chat").method4(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("titles").method4(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("scoreboard").method4(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("bossbar").method4(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hotbar").method4(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("overlayMessage").method4(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("vignette").method4(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hudMods").method4(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("potionEffectsHud").method4(true))
      .method31();

   public RewindHandlers3Impl4(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
      this.handle(CrosshairRenderEvent.class, this::method1);
   }

   private void method1(CrosshairRenderEvent var1) {
      if (!this.method14()) {
         var1.method2(Type.DENY);
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
