package com.moonsworth.lunar.client.mod.skyblock.dungeonhighlightdoors;

import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data4;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SkyblockDungeonHighlightDoors extends AbstractFeature {
   private static final int field8 = -8355712;
   private final GuiRewindhandlersHandler2_2 field9 = (GuiRewindhandlersHandler2_2)this.method19(GuiRewindhandlersHandler2_2.class);
   private final ColorOption field10 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "dungeonDoorOpenable"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1140915968))
      .method31();
   private final ToggleOption field11 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dungeonDoorOpenableFill")
      .method31();
   private final ColorOption field12 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "dungeonDoorUnopenable"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1157562368))
      .method31();
   private final ToggleOption field13 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dungeonDoorUnopenableFill")
      .method31();
   private final ToggleOption field14 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dungeonDoorAllDoors")
      .method31();
   private final ColorOption field15 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "dungeonDoorAllDoorsColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1145342207))
      .method31();
   private final FloatOption field16 = (FloatOption)((FloatOption.Data)((FloatOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "allDoorThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(3.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final ToggleOption field17 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dungeonDoorAllDoorsFill")
      .method31();
   private final ToggleOption field18 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("dungeonDoorBranchStatus")
      .method31();
   private final Map<Rewindhandlers, Boolean> field19 = new HashMap<>();

   public SkyblockDungeonHighlightDoors(Skyblock var1) {
      super(false);
      this.method4(Framework.field16, Framework4.method3(var1));
      this.method4(Framework.field17, Framework2.method2(SettingsPage.DUNGEONS));
      this.method4(Framework.field19, Framework11.method1(this, () -> Click3.getIsland() == Gui2Extension3.DUNGEON));
      this.handle(HudRenderLegacyEventAlt.class, this::method1);
      this.handle(HighlightBase$Data4.class, var1x -> this.method13());
      this.handle(HighlightBase$Data3.class, var1x -> this.method13());
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond.class, var1x -> this.method13());
   }

   private void method1(HudRenderLegacyEventAlt var1) {
      Holograms2_5 var2 = this.field9.method5().orElse(null);
      if (var2 != null) {
         Holograms4Iterator var3 = var2.method29().method7();
         if (var3 != null) {
            List var4 = this.method4(var2, var3);
            if (!var4.isEmpty()) {
               AbstractRenderContext var5 = var1.method3();
               Bridge2_43 var6 = ThreadModuleDump63.method13();
               var5.push();
               var5.translate(-var6.bridge$renderPosX(), -var6.bridge$renderPosY(), -var6.bridge$renderPosZ());
               boolean var7 = var2.method49() > 0;
               ColorOption var8 = var7 ? this.field10 : this.field12;
               boolean var9 = var7 ? this.field11.get() : this.field13.get();
               int var10 = var9 ? var8.method1(0.0F) : ThreadModuleDump23.method31(var8.method1(0.0F));
               ArrayList var11 = new ArrayList();

               for (Rewindhandlers var13 : var4) {
                  boolean var14 = var2.method19().contains(var13);
                  if (var14) {
                     if (!var2.method50()) {
                        var11.add(new SkyblockDungeonHighlightDoors.Data(this.method2(var13), var10, var9));
                     }
                  } else if (this.field14.get()) {
                     boolean var15 = this.field18.get() && !this.field19.getOrDefault(var13, true);
                     if (var15) {
                        var11.add(new SkyblockDungeonHighlightDoors.Data(this.method2(var13), -8355712, false));
                     } else {
                        boolean var16 = this.field17.get();
                        int var17 = var16 ? this.field15.method1(0.0F) : ThreadModuleDump23.method31(this.field15.method1(0.0F));
                        var11.add(new SkyblockDungeonHighlightDoors.Data(this.method2(var13), var17, var16));
                     }
                  }
               }

               for (SkyblockDungeonHighlightDoors.Data var20 : var11) {
                  if (var20.method3()) {
                     Click.drawBlockHighlight(var5, var20.method1(), var20.method2(), false, this.field16.get());
                  }
               }

               Bridge_28 var19 = var5.method11(this.field16.get());

               for (SkyblockDungeonHighlightDoors.Data var22 : var11) {
                  if (!var22.method3()) {
                     Click.drawBoxWires(var19, var22.method1(), var22.method2());
                  }
               }

               var19.end();
               var5.pop();
            }
         }
      }
   }

   private AxisAlignedBBBridge method2(Rewindhandlers var1) {
      byte var3 = 69;
      int var2;
      int var4;
      if (var1.method6().method8() == var1.method7().method8()) {
         var2 = (int)(var1.method6().method2() + 14.0F);
         var4 = (int)(var1.method6().method3() + 30.0F);
      } else {
         var2 = (int)(var1.method6().method2() + 30.0F);
         var4 = (int)(var1.method6().method3() + 14.0F);
      }

      return AxisAlignedBBBridge.method2(var2, var3, var4, var2 + 3, var3 + 4, var4 + 3);
   }

   private void method13() {
      Holograms2_5 var1 = this.field9.method5().orElse(null);
      this.field19.clear();
      if (var1 != null && this.field18.get()) {
         Holograms4Iterator var2 = var1.method29().method7();
         if (var2 != null) {
            for (Rewindhandlers var4 : this.method4(var1, var2)) {
               this.field19.put(var4, this.method5(var1, var2, var4));
            }
         }
      }
   }

   private List<Rewindhandlers> method4(Holograms2_5 var1, Holograms4Iterator var2) {
      ArrayList var3 = new ArrayList();

      for (Rewindhandlers var5 : var1.method24()) {
         if (var2.method24(var5.method6()) || var2.method24(var5.method7())) {
            var3.add(var5);
         }
      }

      return var3;
   }

   private boolean method5(Holograms2_5 var1, Holograms4Iterator var2, Rewindhandlers var3) {
      Holograms4Iterator var4 = var1.method14(var3.method6());
      Holograms4Iterator var5 = var1.method14(var3.method7());
      Holograms4Iterator var6 = var4 == var2 ? var5 : var4;
      if (var6 == null) {
         return true;
      }

      HashSet var7 = new HashSet();
      ArrayDeque var8 = new ArrayDeque();
      var7.add(var2);
      var7.add(var6);
      var8.add(var6);

      while (!var8.isEmpty()) {
         Holograms4Iterator var9 = (Holograms4Iterator)var8.poll();
         if (this.method6(var9)) {
            return true;
         }

         for (Rewindhandlers var11 : this.method4(var1, var9)) {
            if (var11 != var3) {
               Holograms4Iterator var12 = var1.method14(var11.method6());
               Holograms4Iterator var13 = var1.method14(var11.method7());
               Holograms4Iterator var14 = var12 == var9 ? var13 : var12;
               if (var14 == null) {
                  return true;
               }

               if (var14 != var2 && var7.add(var14)) {
                  var8.add(var14);
               }
            }
         }
      }

      return false;
   }

   private boolean method6(Holograms4Iterator var1) {
      HologramsType5 var2 = var1.method30().method6();
      if (var2 != HologramsType5.FAIRY && var2 != HologramsType5.SPAWN && var2 != HologramsType5.BOSS && var2 != HologramsType5.BLOOD) {
         HologramsType2 var3 = var1.method30().method2();

         return switch (var3) {
            case COMPLETED -> false;
            case OPENED, FAILED, ADJACENT -> true;
            case CLEARED -> {
               int var4 = var1.method14();
               yield var4 == -1 || var1.method30().method1() < var4;
            }
         };
      } else {
         return false;
      }
   }

   @Override
   public String getId() {
      return "SKYBLOCK_DUNGEON_HIGHLIGHT_DOORS";
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.method230(new ClientOption[]{this.field10, this.field11, this.field12, this.field13, this.field16});
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         this.field14, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field15, this.field17, this.field18})
      );
   }

   private class Data {
      private final AxisAlignedBBBridge field1;
      private final int field2;
      private final boolean fill;

      private Data(AxisAlignedBBBridge var1, int var2, boolean var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.fill = var3;
      }

      public AxisAlignedBBBridge method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public boolean method3() {
         return this.fill;
      }
   }
}
