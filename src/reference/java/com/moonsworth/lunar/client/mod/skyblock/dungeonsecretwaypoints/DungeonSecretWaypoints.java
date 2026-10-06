package com.moonsworth.lunar.client.mod.skyblock.dungeonsecretwaypoints;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data4;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import lombok.Generated;

public class DungeonSecretWaypoints extends AbstractFeature {
   private final GuiRewindhandlersHandler2_2 field8 = (GuiRewindhandlersHandler2_2)this.method19(GuiRewindhandlersHandler2_2.class);
   private final ToggleOption field9 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "hideCollectedWaypoints"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showWaypointDistances"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showSecretLevers"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showSecretEntrances"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showSecretSuperbooms"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showSecretRedstoneKeys"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showFairySouls")
      .method31();
   private final ToggleOption field16 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("secretWaypointsTightBoxes")
      .method31();
   private final ColorOption field17 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "batSecretColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(419495935))
      .method31();
   private final ColorOption field18 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "chestSecretColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(435971584))
      .method31();
   private final ColorOption field19 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "itemDropSecretColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(422969663))
      .method31();
   private final ColorOption field20 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "essenceSecretColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(421799206))
      .method31();
   private final ColorOption field21 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "fairySoulColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436179364))
      .method31();
   private final ColorOption field22 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "secretLeverColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436207360))
      .method31();
   private final ColorOption field23 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "secretEntranceColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(419495680))
      .method31();
   private final ColorOption field24 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "secretSuperboomColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(427851904))
      .method31();
   private final ColorOption field25 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "secretRedstoneKeyColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436142080))
      .method31();
   private final ColorOption field26 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "secretRedstoneKeyPlacementColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436142080))
      .method31();
   private final ArrayList<Holograms_7> field27 = new ArrayList<>();
   private final ArrayList<DungeonSecretWaypoints.Data> field28 = new ArrayList<>();

   public DungeonSecretWaypoints(Skyblock var1) {
      super(false);
      this.method7(Framework.field16, Framework4.method3(var1));
      this.method7(Framework.field17, Framework2.method2(SettingsPage.DUNGEONS));
      this.method7(Framework.field19, Framework11.method1(this, () -> Click3.getIsland() == Gui2Extension3.DUNGEON));
      this.handle(HighlightBase$Data4.class, this::method1);
      this.handle(HighlightBase$Data3.class, this::method2);
      this.handle(Rewindhandlers3.Data.class, var1x -> this.method13());
      this.handle(Rewindhandlers3.Data2.class, var1x -> this.method13());
      this.handle(HudRenderLegacyEventAlt.class, this::method4);
      this.OHROCHICOIOICHOCRROORRCIIICIHO(this::onDisable);
      this.field9.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method13());
      this.field11.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method13());
      this.field12.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method13());
      this.field13.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method13());
      this.field14.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method13());
      this.field15.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var1x -> this.method13());
   }

   private void onDisable() {
      this.field27.clear();
      this.field28.clear();
   }

   private void method1(HighlightBase$Data4 var1) {
      this.field27.clear();
      this.field28.clear();
      Holograms4Iterator var2 = this.field8.method5().get().method29().method7();
      if (var2 != null && var2.method30().method6().hasSecrets()) {
         this.field27.addAll(var2.method30().method9());
         this.method13();
      }
   }

   private void method2(HighlightBase$Data3 var1) {
      this.field27.clear();
      this.field28.clear();
   }

   private void method13() {
      this.field28.clear();
      if (this.isEnabled() && ((Framework4)this.method7(Framework.field16)).<Framework7Extension>method1().isEnabled()) {
         ArrayList var1 = new ArrayList();
         Holograms2_5 var2 = this.field8.method5().orElse(null);
         if (var2 != null) {
            Holograms4Iterator var3 = var2.method29().method7();
            if (var3 != null) {
               Holograms3 var4 = var3.method23().orElse(null);
               if (var4 != null) {
                  for (int var5 = 0; var5 < this.field27.size(); var5++) {
                     Holograms_7 var6 = this.field27.get(var5);
                     if ((!this.field9.get() || !var6.isFound()) && (this.field15.get() || var6.getType() != HologramsType6.FAIRY_SOUL)) {
                        Vector3iBridge var7 = var4.method1(var6.getPos());
                        this.field28
                           .add(
                              new DungeonSecretWaypoints.Data(
                                 var7, var5 + 1 + " - " + var6.getType(), this.method6(var6.getType()), var6.getType().isNonstandardShape()
                              )
                           );
                        if (this.field12.get() && var6.getEntrance() != null) {
                           Vector3iBridge var8 = var4.method1(var6.getEntrance());
                           if (!this.method8(var1, var8)) {
                              this.field28.add(new DungeonSecretWaypoints.Data(var8, var5 + 1 + " - Entrance", this.field23, false));
                           }
                        }

                        if (this.field11.get() && var6.getLevers() != null) {
                           ArrayList var13 = var6.getLevers();

                           for (int var9 = 0; var9 < var13.size(); var9++) {
                              if (!this.field9.get() || !var6.method10().get(var9)) {
                                 Vector3iBridge var10 = (Vector3iBridge)var13.get(var9);
                                 Vector3iBridge var11 = var4.method1(var10);
                                 if (!this.method8(var1, var11)) {
                                    String var12 = var5 + 1 + " - Lever";
                                    if (var13.size() > 1) {
                                       var12 = var12 + " " + (var9 + 1);
                                    }

                                    this.field28.add(new DungeonSecretWaypoints.Data(var11, var12, this.field22, true));
                                 }
                              }
                           }
                        }

                        if (this.field13.get() && var6.getSuperboom() != null && (!this.field9.get() || !var6.method11())) {
                           Vector3iBridge var14 = var4.method1(var6.getSuperboom());
                           if (!this.method8(var1, var14)) {
                              this.field28.add(new DungeonSecretWaypoints.Data(var14, var5 + 1 + " - Superboom", this.field24, false));
                           }
                        }

                        if (this.field14.get() && var6.getRedstoneKey() != null && !var6.getRedstoneKey().isEmpty()) {
                           ArrayList var15 = var6.getRedstoneKey();
                           if (!this.field9.get() || !var6.method12()) {
                              for (int var17 = 0; var17 < var15.size(); var17++) {
                                 Vector3iBridge var18 = (Vector3iBridge)var15.get(var17);
                                 Vector3iBridge var19 = var4.method1(var18);
                                 if (!this.method8(var1, var19)) {
                                    String var20 = var5 + 1 + " - Redstone Key";
                                    if (var15.size() > 1) {
                                       var20 = var20 + " " + (var17 + 1);
                                    }

                                    this.field28.add(new DungeonSecretWaypoints.Data(var19, var20, this.field25, true));
                                 }
                              }
                           }
                        }

                        if (this.field14.get() && var6.getRedstoneKeyPlacement() != null && (!this.field9.get() || !var6.method13())) {
                           Vector3iBridge var16 = var4.method1(var6.getRedstoneKeyPlacement());
                           if (!this.method8(var1, var16)) {
                              this.field28.add(new DungeonSecretWaypoints.Data(var16, var5 + 1 + " - Redstone Key Placement", this.field26, false));
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(HudRenderLegacyEventAlt var1) {
      if (!this.field27.isEmpty() && !this.field28.isEmpty()) {
         if (!this.method14()) {
            Skyblock var2 = ((Framework4)this.method7(Framework.field16)).method1();
            if (!var2.method113().method21()) {
               AbstractRenderContext var3 = var1.method3();
               Itemcounter6Extension var4 = ThreadModuleDump63.method8();
               Bridge2_43 var5 = ThreadModuleDump63.method13();
               var3.push();
               var3.translate(-var5.bridge$renderPosX(), -var5.bridge$renderPosY(), -var5.bridge$renderPosZ());
               Bridge2_32 var6 = var3.method10(LunarRenderTypes.field15);
               var6.method1();

               for (DungeonSecretWaypoints.Data var8 : this.field28) {
                  Vector3iBridge var9 = var8.method1();
                  if (this.field16.get() && var8.isNonstandardShape()) {
                     Click.drawBoxFilled(var6, Click.getBlockAABB(var4, var9.bridge$toJoml()), var8.method2().method1(0.0F));
                  } else {
                     Click.drawBoxFilled(
                        var6,
                        var9.bridge$getX(),
                        var9.bridge$getY(),
                        var9.bridge$getZ(),
                        var9.bridge$getX() + 1,
                        var9.bridge$getY() + 1,
                        var9.bridge$getZ() + 1,
                        var8.method2().method1(0.0F)
                     );
                  }
               }

               var6.method17(BufferBuildMode.BATCHED);
               Bridge_28 var11 = var3.method11(((Framework4)this.method7(Framework.field16)).<Skyblock>method1().method19().get());

               for (DungeonSecretWaypoints.Data var14 : this.field28) {
                  Vector3iBridge var10 = var14.method1();
                  if (this.field16.get() && var14.isNonstandardShape()) {
                     Click.drawBoxWires(var11, Click.getBlockAABB(var4, var10.bridge$toJoml()), ThreadModuleDump23.method31(var14.method2().method1(0.0F)));
                  } else {
                     Click.drawBoxWires(
                        var11,
                        var10.bridge$getX(),
                        var10.bridge$getY(),
                        var10.bridge$getZ(),
                        var10.bridge$getX() + 1,
                        var10.bridge$getY() + 1,
                        var10.bridge$getZ() + 1,
                        ThreadModuleDump23.method31(var14.method2().method1(0.0F))
                     );
                  }
               }

               var11.end();

               for (DungeonSecretWaypoints.Data var15 : this.field28) {
                  this.method7(var3, var15.getText(), var15.method1());
               }

               var3.pop();
            }
         }
      }
   }

   private boolean method14() {
      Holograms2_5 var1 = this.field8.method5().orElse(null);
      if (var1 == null) {
         return true;
      }

      Holograms4Iterator var2 = var1.method29().method7();
      return var2 == null ? true : var2.method14() == var2.method30().method1();
   }

   private ColorOption method6(HologramsType6 var1) {
      return switch (var1) {
         case BAT -> this.field17;
         case CHEST -> this.field18;
         case ITEM_DROP -> this.field19;
         case ESSENCE -> this.field20;
         case FAIRY_SOUL -> this.field21;
      };
   }

   private void method7(AbstractRenderContext var1, String var2, Vector3iBridge var3) {
      int var4 = var3.bridge$getX();
      int var5 = var3.bridge$getY();
      int var6 = var3.bridge$getZ();
      Click.drawStringCentered(var1, var2, var4 + 0.5, var5 + 2.0, var6 + 0.5, -1, true);
      if (this.field10.get()) {
         int var7 = (int)ThreadModuleDump63.method7().HROHOIOCHIRIHICOORIHOHCIOIRIIH(var4, var5, var6);
         Click.drawStringCentered(var1, var7 + "m", var4 + 0.5, var5 + 1.5, var6 + 0.5, -256, true);
      }
   }

   private boolean method8(ArrayList<Vector3iBridge> var1, Vector3iBridge var2) {
      for (Vector3iBridge var4 : var1) {
         if (var4.method1(var2)) {
            return true;
         }
      }

      var1.add(var2);
      return false;
   }

   @Override
   public String getId() {
      return "DUNGEON_SECRET_WAYPOINTS";
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.method230(new ClientOption[]{this.field9, this.field10, this.field16});
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field22}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field12, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field23}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field24}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field14, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field25, this.field26}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field15, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field21}));
      var1.method230(new ClientOption[]{this.field17, this.field18, this.field19, this.field20});
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   private static class Data {
      private final Vector3iBridge field1;
      private final String field2;
      private final ColorOption field3;
      private final boolean field4;

      @Generated
      public Vector3iBridge method1() {
         return this.field1;
      }

      @Generated
      public String getText() {
         return this.field2;
      }

      @Generated
      public ColorOption method2() {
         return this.field3;
      }

      @Generated
      public boolean isNonstandardShape() {
         return this.field4;
      }

      @Generated
      public Data(Vector3iBridge var1, String var2, ColorOption var3, boolean var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }
   }
}
