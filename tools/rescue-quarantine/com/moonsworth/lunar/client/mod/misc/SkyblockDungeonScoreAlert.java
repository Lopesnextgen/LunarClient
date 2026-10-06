package com.moonsworth.lunar.client.mod.misc;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.client.framework.Calculator2Handler;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework11;
import com.moonsworth.lunar.client.framework.Framework2;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.Framework8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler26;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.LightingExtension443;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.LightingExtension443.Data;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers.Data10;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers.Data11;
import com.moonsworth.lunar.client.lighting.Lighting;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension4915;
import com.moonsworth.lunar.client.lighting.rewindhandlers.RewindhandlersType;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockDungeonScoreAlert extends Framework7Extension2 {
   private final GuiRewindhandlersHandler26 field8 = (GuiRewindhandlersHandler26)this.method19(GuiRewindhandlersHandler26.class);
   private final LightingExtension443 field9 = (LightingExtension443)LightingExtension443.method10("dungeonSAlert")
      .method2(2.0F)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field10 = (LightingExtension443)((Data)LightingExtension443.method10("dungeonSPlusAlert")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method2(2.0F)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final com.moonsworth.lunar.client.lighting.LightingExtension443 field11 = (com.moonsworth.lunar.client.lighting.LightingExtension443)Lighting.method7(
         "announce270Score"
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4915 field12 = (LightingExtension4915)((com.moonsworth.lunar.client.lighting.LightingExtension4915.Data)Lighting.method12(
            "announce270Message"
         )
         .HIIIOHRRROCICIOIORRRIRCRCHHIII("270 score reached!"))
      .method3(250)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final com.moonsworth.lunar.client.lighting.LightingExtension443 field13 = (com.moonsworth.lunar.client.lighting.LightingExtension443)Lighting.method7(
         "announce300Score"
      )
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4915 field14 = (LightingExtension4915)((com.moonsworth.lunar.client.lighting.LightingExtension4915.Data)Lighting.method12(
            "announce300Message"
         )
         .HIIIOHRRROCICIOIORRRIRCRCHHIII("300 score reached!"))
      .method3(250)
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final SkyblockDungeonRoomNotifications field15 = new SkyblockDungeonRoomNotifications(this);
   private final SkyblockDungeonBloodNotification field16 = new SkyblockDungeonBloodNotification(this);
   private boolean field17;
   private boolean field18;
   private boolean field19;
   private boolean field20;

   public SkyblockDungeonScoreAlert(Skyblock var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(Framework.field17, Framework2.method2(RewindhandlersType.DUNGEONS));
      this.method2(Framework.field19, Framework11.method1(this, () -> Click3.method2() == Gui2Extension3.DUNGEON));
      this.RCIOICOHRIOIIRRRROCRHCIICRROHO(this::onEnable);
      this.handle(Data10.class, this::method1);
      this.handle(Data11.class, this::method2);
   }

   private void onEnable() {
      this.field17 = false;
      this.field18 = false;
      this.field19 = false;
      this.field20 = false;
   }

   private void method1(Data10 var1) {
      this.onEnable();
   }

   private void method2(Data11 var1) {
      if (var1.method1() >= 300) {
         if (!this.field18 && (Boolean)this.field10.get()) {
            this.field8.method4("DUNGEON_SCORE", Component.text("300 Score!", NamedTextColor.RED), this.field10, this);
            this.field18 = true;
         }

         if (!this.field20 && (Boolean)this.field13.get()) {
            Fishing_3.method1("/pc " + (String)this.field14.get());
            this.field20 = true;
         }
      } else if (var1.method1() >= 270) {
         if (!this.field17 && (Boolean)this.field9.get()) {
            this.field8.method4("DUNGEON_SCORE", Component.text("270 Score!", NamedTextColor.RED), this.field9, this);
            this.field17 = true;
         }

         if (!this.field19 && (Boolean)this.field11.get()) {
            Fishing_3.method1("/pc " + (String)this.field12.get());
            this.field19 = true;
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_SCORE_ALERT";
   }

   protected List<Framework7Extension> method9() {
      return ImmutableList.of(this.field15, this.field16);
   }

   public void method2(LightingExtension23 var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(RewindhandlersType.GENERAL, var1x -> {
         this.field9.method1(var1x);
         this.field10.method1(var1x);
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field12}));
         var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field14}));
         this.field15.method2(var1x);
         this.field16.method1(var1x);
      });
   }

   protected Framework8 method20() {
      return Framework8.method7().method1(new Calculator2Handler[]{Calculator2Handler.field5}).method11(this);
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
   public com.moonsworth.lunar.client.lighting.LightingExtension443 method16() {
      return this.field11;
   }

   @Generated
   public LightingExtension4915 method17() {
      return this.field12;
   }

   @Generated
   public com.moonsworth.lunar.client.lighting.LightingExtension443 method19() {
      return this.field13;
   }

   @Generated
   public LightingExtension4915 method21() {
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
