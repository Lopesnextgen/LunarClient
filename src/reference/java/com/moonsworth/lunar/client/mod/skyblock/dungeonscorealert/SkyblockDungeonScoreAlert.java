package com.moonsworth.lunar.client.mod.skyblock.dungeonscorealert;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler26;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.LightingExtension443;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data11;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDungeonScoreAlert extends AbstractFeature {
   private final GuiRewindhandlersHandler26 field8 = this.method19(GuiRewindhandlersHandler26.class);
   private final LightingExtension443 field9 = (LightingExtension443)LightingExtension443.method10("dungeonSAlert")
      .method2(2.0F)
      .method31();
   private final LightingExtension443 field10 = (LightingExtension443)((LightingExtension443.Data)LightingExtension443.method10("dungeonSPlusAlert")
         .method4(true))
      .method2(2.0F)
      .method31();
   private final com.moonsworth.lunar.client.config.option.ToggleOption field11 = (com.moonsworth.lunar.client.config.option.ToggleOption)OptionFactory.method7(
         "announce270Score"
      )
      .method31();
   private final TextOption field12 = (TextOption)((TextOption.Data)OptionFactory.method12("announce270Message")
         .method2("270 score reached!"))
      .method3(250)
      .method31();
   private final com.moonsworth.lunar.client.config.option.ToggleOption field13 = (com.moonsworth.lunar.client.config.option.ToggleOption)OptionFactory.method7(
         "announce300Score"
      )
      .method31();
   private final TextOption field14 = (TextOption)((TextOption.Data)OptionFactory.method12("announce300Message")
         .method2("300 score reached!"))
      .method3(250)
      .method31();
   private final SkyblockDungeonRoomNotifications field15 = new SkyblockDungeonRoomNotifications(this);
   private final SkyblockDungeonBloodNotification field16 = new SkyblockDungeonBloodNotification(this);
   private boolean field17;
   private boolean field18;
   private boolean field19;
   private boolean field20;

   public SkyblockDungeonScoreAlert(Skyblock var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(Framework.field17, Framework2.method2(SettingsPage.DUNGEONS));
      this.method2(Framework.field19, Framework11.method1(this, () -> Click3.getIsland() == Gui2Extension3.DUNGEON));
      this.RCIOICOHRIOIIRRRROCRHCIICRROHO(this::onEnable);
      this.handle(Rewindhandlers$Data10.class, this::method1);
      this.handle(Rewindhandlers$Data11.class, this::method2);
   }

   private void onEnable() {
      this.field17 = false;
      this.field18 = false;
      this.field19 = false;
      this.field20 = false;
   }

   private void method1(Rewindhandlers$Data10 var1) {
      this.onEnable();
   }

   private void method2(Rewindhandlers$Data11 var1) {
      if (var1.method1() >= 300) {
         if (!this.field18 && this.field10.get()) {
            this.field8.method4("DUNGEON_SCORE", Component.text("300 Score!", NamedTextColor.RED), this.field10, this);
            this.field18 = true;
         }

         if (!this.field20 && this.field13.get()) {
            Fishing_3.method1("/pc " + this.field14.get());
            this.field20 = true;
         }
      } else if (var1.method1() >= 270) {
         if (!this.field17 && this.field9.get()) {
            this.field8.method4("DUNGEON_SCORE", Component.text("270 Score!", NamedTextColor.RED), this.field9, this);
            this.field17 = true;
         }

         if (!this.field19 && this.field11.get()) {
            Fishing_3.method1("/pc " + this.field12.get());
            this.field19 = true;
         }
      }
   }

   @Override
   public String getId() {
      return "SKYBLOCK_DUNGEON_SCORE_ALERT";
   }

   @Override
   protected List<Framework7Extension> method9() {
      return ImmutableList.of(this.field15, this.field16);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(SettingsPage.GENERAL, var1x -> {
         this.field9.method1(var1x);
         this.field10.method1(var1x);
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, var1xx -> var1xx.method9(new ClientOption[]{this.field12}));
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, var1xx -> var1xx.method9(new ClientOption[]{this.field14}));
         this.field15.method2(var1x);
         this.field16.method1(var1x);
      });
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   @Generated
   public GuiRewindhandlersHandler26 method13() {
      return this.field8;
   }

   @Generated
   public LightingExtension443 method14() {
      return this.field9;
   }

   @Generated
   public LightingExtension443 method15() {
      return this.field10;
   }

   @Generated
   public com.moonsworth.lunar.client.config.option.ToggleOption method16() {
      return this.field11;
   }

   @Generated
   public TextOption method17() {
      return this.field12;
   }

   @Generated
   public com.moonsworth.lunar.client.config.option.ToggleOption method19() {
      return this.field13;
   }

   @Generated
   public TextOption method21() {
      return this.field14;
   }

   @Generated
   public SkyblockDungeonRoomNotifications method22() {
      return this.field15;
   }

   @Generated
   public SkyblockDungeonBloodNotification method23() {
      return this.field16;
   }

   @Generated
   public boolean method24() {
      return this.field17;
   }

   @Generated
   public boolean method25() {
      return this.field18;
   }

   @Generated
   public boolean method26() {
      return this.field19;
   }

   @Generated
   public boolean method27() {
      return this.field20;
   }
}
