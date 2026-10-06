package com.moonsworth.lunar.client.mod.skyblock.dungeonbloodcamphelper;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data4;
import com.moonsworth.lunar.client.event.entity.EventEntityRemoval;
import com.moonsworth.lunar.client.event.entity.EventEntityMove;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.gui.ServerTickEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump45;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.Map.Entry;
import lombok.Generated;
import org.joml.Vector3d;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockDungeonBloodCampHelper extends AbstractFeature {
   private final GuiRewindhandlersHandler2_2 field8 = (GuiRewindhandlersHandler2_2)this.method19(GuiRewindhandlersHandler2_2.class);
   private final ColorOption field9 = (ColorOption)((ColorOption.Data)OptionFactory.method8("bloodMobColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1157562368))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showBloodMobLine")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showBloodMobTime")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field12 = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2(
               "bloodCampHelperLineThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(4.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final HashMap<BridgeExtension, SkyblockDungeonBloodCampHelper.Data> field13 = new HashMap<>();
   private int field14 = 1;

   public SkyblockDungeonBloodCampHelper(Skyblock var1) {
      super(false);
      this.method2(Framework.field16, Framework4.method3(var1));
      this.method2(Framework.field17, Framework2.method2(SettingsPage.DUNGEONS));
      this.method2(Framework.field19, Framework11.method1(this, () -> Click3.getIsland() == Gui2Extension3.DUNGEON));
      this.OHROCHICOIOICHOCRROORRCIIICIHO(this::onDisable);
      this.handle(HighlightBase$Data4.class, this::method1);
      this.handle(HighlightBase$Data3.class, this::method2);
      this.handle(EventEntityMove.class, this::method4);
      this.handle(HudRenderLegacyEvent.class, this::method3);
      this.handle(EventEntityRemoval.class, this::method5);
      this.handle(ServerTickEvent.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data.class, this::method7);
      this.handle(EventWorldChanged.class, this::method8);
   }

   private void onDisable() {
      this.reset();
      this.field14 = 1;
   }

   private void method1(HighlightBase$Data4 var1) {
      this.reset();
   }

   private void method2(HighlightBase$Data3 var1) {
      this.reset();
   }

   private void method3(HudRenderLegacyEvent var1) {
      if (this.method13()) {
         AbstractRenderContext var2 = var1.method3();
         Bridge2_43 var3 = ThreadModuleDump63.method13();
         var2.push();
         var2.translate(-var3.bridge$renderPosX(), -var3.bridge$renderPosY(), -var3.bridge$renderPosZ());
         Bridge2_32 var4 = var2.method10(LunarRenderTypes.field15);
         var4.method1();

         for (SkyblockDungeonBloodCampHelper.Data var6 : this.field13.values()) {
            Vector3d var7 = var6.method5();
            if (var7 != null) {
               double var8 = var7.x();
               double var10 = var7.y();
               double var12 = var7.z();
               Click.drawBoxFilled(var4, var8 - 0.5, var10 - 0.5, var12 - 0.5, var8 + 0.5, var10 + 1.5, var12 + 0.5, this.field9.method1(0.0F));
            }
         }

         var4.method17(BufferBuildMode.BATCHED);
         if (this.field10.get()) {
            Bridge_28 var23 = var2.method11(this.field12.get());

            for (Entry var27 : this.field13.entrySet()) {
               BridgeExtension var29 = (BridgeExtension)var27.getKey();
               SkyblockDungeonBloodCampHelper.Data var9 = (SkyblockDungeonBloodCampHelper.Data)var27.getValue();
               Vector3d var31 = var9.method5();
               if (var31 != null) {
                  double var11 = var29.bridge$getPosX();
                  double var13 = var29.bridge$getPosY();
                  double var15 = var29.bridge$getPosZ();
                  double var17 = var31.x();
                  double var19 = var31.y();
                  double var21 = var31.z();
                  Click.drawBoxWires(
                     var23,
                     var17 - 0.5,
                     var19 - 0.5,
                     var21 - 0.5,
                     var17 + 0.5,
                     var19 + 1.5,
                     var21 + 0.5,
                     ThreadModuleDump23.method31(this.field9.method1(0.0F))
                  );
                  var23.method3(var11, var13 + var29.bridge$getEyeHeight(), var15, var17, var19, var21);
               }
            }

            var23.end();
         }

         if (this.field11.get()) {
            for (SkyblockDungeonBloodCampHelper.Data var26 : this.field13.values()) {
               Vector3d var28 = var26.method5();
               if (var28 != null) {
                  double var30 = var28.x();
                  double var32 = var28.y();
                  double var33 = var28.z();
                  Click.drawStringCentered(var2, var26.method1(), var30, var32 - 1.0, var33, ThreadModuleDump23.method31(this.field9.method1(0.0F)), true);
               }
            }
         }

         var2.pop();
      }
   }

   private void method4(EventEntityMove var1) {
      if (this.method13()) {
         if (var1.method2()) {
            BridgeExtension var2 = var1.method1();
            if (var2 instanceof ArmorStandBridge var3) {
               double var4 = var2.bridge$getPosX();
               double var6 = var2.bridge$getPosY();
               double var8 = var2.bridge$getPosZ();
               if (!(var6 < 71.0)) {
                  if (var4 != var2.method3() || var8 != var2.method5()) {
                     Holograms2_5 var10 = this.field8.method5().orElse(null);
                     if (var10 != null) {
                        Holograms4Iterator var11 = var10.method29().method7();
                        if (var11 != null && var11.contains(var2.bridge$getPosX(), var2.bridge$getPosZ())) {
                           if (var3.bridge$getHelmet() != null && !var3.bridge$getHelmet().bridge$isEmpty()) {
                              if (var2.bridge$getCustomName() == null) {
                                 if (!this.field13.containsKey(var2)) {
                                    this.field13
                                       .put(var2, new SkyblockDungeonBloodCampHelper.Data(this.field14 == 1 ? 81 : 41, new Vector3d(var4, var6, var8)));
                                 }

                                 SkyblockDungeonBloodCampHelper.Data var12 = this.field13.get(var2);
                                 Vector3d var13 = var12.method4();
                                 double var14 = var13.x();
                                 double var16 = var13.y();
                                 double var18 = var13.z();
                                 int var20 = var12.method3();
                                 double var21 = var20 - var12.method2().get() / 50.0;
                                 double var23 = var21 == 0.0 ? 0.0 : (var4 - var14) / var21;
                                 double var25 = var21 == 0.0 ? 0.0 : (var6 - var16) / var21;
                                 double var27 = var21 == 0.0 ? 0.0 : (var8 - var18) / var21;
                                 var12.method6(new Vector3d(var14 + var23 * var20, var16 + var25 * var20 + 2.0, var18 + var27 * var20));
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method5(EventEntityRemoval var1) {
      if (this.method13()) {
         if (this.field13.containsKey(var1.method1())) {
            this.field13.remove(var1.method1());
         }
      }
   }

   private void method6(ServerTickEvent var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         this.field13.entrySet().removeIf(var0 -> var0.getValue().method2().get() <= 0L);
      }
   }

   private void method7(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         if (var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH().equals("[BOSS] The Watcher: Let's see how you can handle this.")) {
            this.field14 = 2;
         }
      }
   }

   private void method8(EventWorldChanged var1) {
      this.reset();
      this.field14 = 1;
   }

   private void reset() {
      this.field13.clear();
   }

   private boolean method13() {
      Holograms2_5 var1 = this.field8.method5().orElse(null);
      if (var1 == null) {
         return false;
      }

      Holograms4Iterator var2 = var1.method29().method7();
      return var2 != null && var2.method30().method6() == HologramsType5.BLOOD;
   }

   @Override
   public String getId() {
      return "SKYBLOCK_DUNGEON_BLOOD_CAMP_HELPER";
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.method11(new ClientOption[]{this.field9, this.field10, this.field11, this.field12});
   }

   private static class Data {
      private final ThreadModuleDump45 field1;
      private final int field2;
      private final Vector3d field3;
      private Vector3d field4;

      public Data(int var1, Vector3d var2) {
         this.field1 = ThreadModuleDump45.Data.method1().method2().method4().method5(var1 * 50L).method7().method2();
         this.field2 = var1;
         this.field3 = var2;
      }

      public String method1() {
         return this.field1.method1();
      }

      @Generated
      public ThreadModuleDump45 method2() {
         return this.field1;
      }

      @Generated
      public int method3() {
         return this.field2;
      }

      @Generated
      public Vector3d method4() {
         return this.field3;
      }

      @Generated
      public Vector3d method5() {
         return this.field4;
      }

      @Generated
      public void method6(Vector3d var1) {
         this.field4 = var1;
      }
   }
}
