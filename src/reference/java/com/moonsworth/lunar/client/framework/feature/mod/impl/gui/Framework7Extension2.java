package com.moonsworth.lunar.client.framework.feature.mod.impl.gui;

import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler24;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy;
import com.moonsworth.lunar.client.ui.hud.row.Gui2Extension;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.Annotation6;
import java.util.List;

public abstract class Framework7Extension2 extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   protected final GuiRewindhandlersHandler24 field8 = (GuiRewindhandlersHandler24)this.method19(GuiRewindhandlersHandler24.class);
   protected final ToggleOption field9 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockStatShowText")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field10 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockStatShowIcon")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field11 = (ToggleOption)OptionFactory.method7("skyblockStatShowBar").method31();
   protected final ToggleOption field12 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockStatShowBackground")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ColorOption field13 = (ColorOption)((ColorOption.Data)OptionFactory.method8("skyblockStatBackgroundColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   protected final EnumOption<Gui2Extension> field14 = (EnumOption<Gui2Extension>)OptionFactory.method10(
         "skyblockStatAlignment", Gui2Extension.CENTER
      )
      .method31();
   protected final ToggleOption field15 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("skyblockStatSingleLine")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final Nameplate3 field16;

   protected Framework7Extension2(boolean var1, Skyblock var2) {
      super(var1);
      Framework7Extension2.Data var3 = this.method13();
      this.field16 = new Nameplate3(var3.field1, var3.field2, var3.field3, this.method16(), this::method15);
      this.method2(Framework.field16, Framework4.method3(var2));
      this.method2(Framework.field1, Nameplate.method4(this.field16));
      this.method2(Framework.field17, Framework2.method2(SettingsPage.HUD));
      this.method2(Framework.field19, Framework11.method1(this, Click3::hasIsland));
      this.handle(EventChatMessageLegacy.Data2.class, this::method2);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.method11(
         new ClientOption[]{this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15}
      );
   }

   private void method2(EventChatMessageLegacy.Data2 var1) {
      this.field16.method9(this.method14());
   }

   protected abstract Framework7Extension2.Data method13();

   protected abstract List<Nameplate3.Data> method14();

   protected abstract List<Nameplate3.Data> method15();

   private Nameplate3.Data2 method16() {
      return new Nameplate3.Data2(this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15);
   }

   protected class Data {
      private final int field1;
      private final int field2;
      private final HudAnchor field3;

      protected Data(
         @Annotation6(method1 = Annotation6.Type.X) int var1,
         @Annotation6(method1 = Annotation6.Type.Y) int var2,
         @Annotation6(method1 = Annotation6.Type.POSITION) HudAnchor var3
      ) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public int x() {
         return this.field1;
      }

      public int y() {
         return this.field2;
      }

      public HudAnchor method1() {
         return this.field3;
      }
   }
}
