package com.moonsworth.lunar.client.mod.skyblock.dungeonscorealert;

import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler26;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.LightingExtension443;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers$Data10;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockDungeonRoomNotifications extends AbstractFeature {
   private static final String field8 = "DUNGEON_ROOM_CLEARED";
   private static final String field9 = "DUNGEON_ROOM_SECRETS";
   private final GuiRewindhandlersHandler26 field10 = (GuiRewindhandlersHandler26)this.method19(GuiRewindhandlersHandler26.class);
   private final GuiRewindhandlersHandler2_2 field11 = (GuiRewindhandlersHandler2_2)this.method19(GuiRewindhandlersHandler2_2.class);
   private final LightingExtension443 field12 = (LightingExtension443)((LightingExtension443.Data)LightingExtension443.method10("dungeonRoomClearedAlert")
         .method4(true))
      .method2(0.5F)
      .method31();
   private final LightingExtension443 field13 = (LightingExtension443)((LightingExtension443.Data)LightingExtension443.method10("dungeonSecretsDoneAlert")
         .method4(true))
      .method2(0.5F)
      .method31();
   private Holograms4Iterator field14;
   private HologramsType2 field15;

   public SkyblockDungeonRoomNotifications(SkyblockDungeonScoreAlert var1) {
      super(true);
      this.method6(Framework.field16, Framework4.method4(false, var1));
      this.handle(Rewindhandlers$Data10.class, this::method3);
      this.handle(EventClientTick.class, this::method4);
   }

   private SkyblockDungeonScoreAlert method13() {
      return ((Framework4)this.method7(Framework.field16)).method1();
   }

   public void method2(RootSettingsAssembler.Data var1) {
      this.field12.method1(var1);
      this.field13.method1(var1);
   }

   private void method3(Rewindhandlers$Data10 var1) {
      this.field14 = null;
      this.field15 = null;
   }

   private void method4(EventClientTick var1) {
      Holograms2_5 var2 = this.field11.method5().orElse(null);
      if (var2 != null) {
         Holograms4Iterator var3 = var2.method29().method7();
         if (var3 != null) {
            HologramsType2 var4 = var3.method30().method2();
            if (var3 != this.field14) {
               this.field14 = var3;
               this.field15 = var4;
            } else {
               if (var4 != this.field15) {
                  this.method5(var3, var4);
                  this.field15 = var4;
               }
            }
         }
      }
   }

   private void method5(Holograms4Iterator var1, HologramsType2 var2) {
      if (var2 == HologramsType2.COMPLETED) {
         if (!this.method9(var1)) {
            this.method8(var1);
         }
      } else if (var2 == HologramsType2.CLEARED) {
         this.method8(var1);
      }
   }

   private boolean method6(Holograms4Iterator var1) {
      HologramsType5 var2 = var1.method30().method6();
      return var2 != HologramsType5.FAIRY && var2 != HologramsType5.SPAWN;
   }

   private boolean method7(Holograms4Iterator var1) {
      return var1.method14() > 0;
   }

   private void method8(Holograms4Iterator var1) {
      if (this.method6(var1)) {
         this.method10("DUNGEON_ROOM_CLEARED", Component.text(this.method13().method1("roomCleared", new Object[0]), NamedTextColor.WHITE), this.field12);
      }
   }

   private boolean method9(Holograms4Iterator var1) {
      if (this.field13.get() && this.method7(var1)) {
         this.method10("DUNGEON_ROOM_SECRETS", Component.text(this.method13().method1("secretsDone", new Object[0]), NamedTextColor.GREEN), this.field13);
         return true;
      } else {
         return false;
      }
   }

   private void method10(String var1, Component var2, LightingExtension443 var3) {
      this.field10.method4(var1, var2, var3, this.method13());
   }

   @Override
   public String getId() {
      return "SKYBLOCK_DUNGEON_ROOM_NOTIFICATIONS";
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }
}
