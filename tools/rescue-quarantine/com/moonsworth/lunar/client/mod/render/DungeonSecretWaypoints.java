package com.moonsworth.lunar.client.mod.render;

import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.BridgeType_17;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.MixinHelper5_6;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20;
import com.moonsworth.lunar.client.framework.Calculator2Handler;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework11;
import com.moonsworth.lunar.client.framework.Framework2;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.Framework8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase.Data3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase.Data4;
import com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl4;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.lighting.LightingExtension4222;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.lighting.LightingExtension443.Data2;
import com.moonsworth.lunar.client.lighting.rewindhandlers.RewindhandlersType;
import com.moonsworth.lunar.client.mod.misc.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import lombok.Generated;

public class DungeonSecretWaypoints extends Framework7Extension2 {
   private final GuiRewindhandlersHandler2_2 field8 = (GuiRewindhandlersHandler2_2)this.IHRHHRIHICHOOICIRIOOHOICHIRHOI(GuiRewindhandlersHandler2_2.class);
   private final LightingExtension443 field9 = (LightingExtension443)((Data2)com.moonsworth.lunar.client.lighting.Lighting.method7("hideCollectedWaypoints")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field10 = (LightingExtension443)((Data2)com.moonsworth.lunar.client.lighting.Lighting.method7("showWaypointDistances")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field11 = (LightingExtension443)((Data2)com.moonsworth.lunar.client.lighting.Lighting.method7("showSecretLevers")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field12 = (LightingExtension443)((Data2)com.moonsworth.lunar.client.lighting.Lighting.method7("showSecretEntrances")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field13 = (LightingExtension443)((Data2)com.moonsworth.lunar.client.lighting.Lighting.method7("showSecretSuperbooms")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field14 = (LightingExtension443)((Data2)com.moonsworth.lunar.client.lighting.Lighting.method7("showSecretRedstoneKeys")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field15 = (LightingExtension443)com.moonsworth.lunar.client.lighting.Lighting.method7("showFairySouls")
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension443 field16 = (LightingExtension443)com.moonsworth.lunar.client.lighting.Lighting.method7("secretWaypointsTightBoxes")
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field17 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "batSecretColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(419495935))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field18 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "chestSecretColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(435971584))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field19 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "itemDropSecretColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(422969663))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field20 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "essenceSecretColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(421799206))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field21 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "fairySoulColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436179364))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field22 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "secretLeverColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436207360))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field23 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "secretEntranceColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(419495680))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field24 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "secretSuperboomColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(427851904))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field25 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "secretRedstoneKeyColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436142080))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final LightingExtension4222 field26 = (LightingExtension4222)((com.moonsworth.lunar.client.lighting.LightingExtension4222.Data)com.moonsworth.lunar.client.lighting.Lighting.method8(
            "secretRedstoneKeyPlacementColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436142080))
      .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final ArrayList<Holograms_7> field27 = new ArrayList<>();
   private final ArrayList<DungeonSecretWaypoints.Data> field28 = new ArrayList<>();

   public DungeonSecretWaypoints(Skyblock var1) {
      super(false);
      this.method7(Framework.field16, Framework4.method3(var1));
      this.method7(Framework.field17, Framework2.method2(RewindhandlersType.DUNGEONS));
      this.method7(Framework.field19, Framework11.method1(this, () -> Click3.method2() == Gui2Extension3.DUNGEON));
      this.handle(Data4.class, this::method1);
      this.handle(Data3.class, this::method2);
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers3.Data.class, var1x -> this.method13());
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers3.Data2.class, var1x -> this.method13());
      this.handle(HighlightImpl4.class, this::method4);
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

   private void method1(Data4 var1) {
      this.field27.clear();
      this.field28.clear();
      Holograms4Iterator var2 = ((Holograms2_5)this.field8.method5().get()).method29().method7();
      if (var2 != null && var2.method30().method6().hasSecrets()) {
         this.field27.addAll(var2.method30().method9());
         this.method13();
      }
   }

   private void method2(Data3 var1) {
      this.field27.clear();
      this.field28.clear();
   }

   private void method13() {
      this.field28.clear();
      if (this.isEnabled() && ((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1().isEnabled()) {
         ArrayList var1 = new ArrayList();
         Holograms2_5 var2 = (Holograms2_5)this.field8.method5().orElse(null);
         if (var2 != null) {
            Holograms4Iterator var3 = var2.method29().method7();
            if (var3 != null) {
               Holograms3 var4 = (Holograms3)var3.method23().orElse(null);
               if (var4 != null) {
                  for (int var5 = 0; var5 < this.field27.size(); var5++) {
                     Holograms_7 var6 = this.field27.get(var5);
                     if ((!(Boolean)this.field9.get() || !var6.isFound()) && ((Boolean)this.field15.get() || var6.getType() != HologramsType6.FAIRY_SOUL)) {
                        Horsestats20 var7 = var4.method1(var6.getPos());
                        this.field28
                           .add(
                              new DungeonSecretWaypoints.Data(
                                 var7, var5 + 1 + " - " + var6.getType(), this.method6(var6.getType()), var6.getType().isNonstandardShape()
                              )
                           );
                        if ((Boolean)this.field12.get() && var6.getEntrance() != null) {
                           Horsestats20 var8 = var4.method1(var6.getEntrance());
                           if (!this.method8(var1, var8)) {
                              this.field28.add(new DungeonSecretWaypoints.Data(var8, var5 + 1 + " - Entrance", this.field23, false));
                           }
                        }

                        if ((Boolean)this.field11.get() && var6.getLevers() != null) {
                           ArrayList var13 = var6.getLevers();

                           for (int var9 = 0; var9 < var13.size(); var9++) {
                              if (!(Boolean)this.field9.get() || !(Boolean)var6.method10().get(var9)) {
                                 Horsestats20 var10 = (Horsestats20)var13.get(var9);
                                 Horsestats20 var11 = var4.method1(var10);
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

                        if ((Boolean)this.field13.get() && var6.getSuperboom() != null && (!(Boolean)this.field9.get() || !var6.method11())) {
                           Horsestats20 var14 = var4.method1(var6.getSuperboom());
                           if (!this.method8(var1, var14)) {
                              this.field28.add(new DungeonSecretWaypoints.Data(var14, var5 + 1 + " - Superboom", this.field24, false));
                           }
                        }

                        if ((Boolean)this.field14.get() && var6.getRedstoneKey() != null && !var6.getRedstoneKey().isEmpty()) {
                           ArrayList var15 = var6.getRedstoneKey();
                           if (!(Boolean)this.field9.get() || !var6.method12()) {
                              for (int var17 = 0; var17 < var15.size(); var17++) {
                                 Horsestats20 var18 = (Horsestats20)var15.get(var17);
                                 Horsestats20 var19 = var4.method1(var18);
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

                        if ((Boolean)this.field14.get() && var6.getRedstoneKeyPlacement() != null && (!(Boolean)this.field9.get() || !var6.method13())) {
                           Horsestats20 var16 = var4.method1(var6.getRedstoneKeyPlacement());
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

   private void method4(HighlightImpl4 var1) {
      if (!this.field27.isEmpty() && !this.field28.isEmpty()) {
         if (!this.method14()) {
            Skyblock var2 = (Skyblock)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
            if (!var2.method113().method21()) {
               BridgeExtension_9 var3 = var1.method3();
               Itemcounter6Extension var4 = ThreadModuleDump63.method8();
               Bridge2_43 var5 = ThreadModuleDump63.method13();
               var3.push();
               var3.translate(-var5.bridge$renderPosX(), -var5.bridge$renderPosY(), -var5.bridge$renderPosZ());
               Bridge2_32 var6 = var3.method10(MixinHelper5_6.field15);
               var6.method1();

               for (DungeonSecretWaypoints.Data var8 : this.field28) {
                  Horsestats20 var9 = var8.method1();
                  if ((Boolean)this.field16.get() && var8.isNonstandardShape()) {
                     Click.method25(var6, Click.method56(var4, var9.bridge$toJoml()), var8.method2().method1(0.0F));
                  } else {
                     Click.method26(
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

               var6.method17(BridgeType_17.BATCHED);
               Bridge_28 var11 = var3.method11(
                  (Float)((Skyblock)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1()).method19().get()
               );

               for (DungeonSecretWaypoints.Data var14 : this.field28) {
                  Horsestats20 var10 = var14.method1();
                  if ((Boolean)this.field16.get() && var14.isNonstandardShape()) {
                     Click.method23(var11, Click.method56(var4, var10.bridge$toJoml()), ThreadModuleDump23.method31(var14.method2().method1(0.0F)));
                  } else {
                     Click.method24(
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
      Holograms2_5 var1 = (Holograms2_5)this.field8.method5().orElse(null);
      if (var1 == null) {
         return true;
      }

      Holograms4Iterator var2 = var1.method29().method7();
      return var2 == null ? true : var2.method14() == var2.method30().method1();
   }

   private LightingExtension4222 method6(HologramsType6 var1) {
      return switch (var1) {
         case BAT -> this.field17;
         case CHEST -> this.field18;
         case ITEM_DROP -> this.field19;
         case ESSENCE -> this.field20;
         case FAIRY_SOUL -> this.field21;
         default -> throw new IncompatibleClassChangeError();
      };
   }

   private void method7(BridgeExtension_9 var1, String var2, Horsestats20 var3) {
      int var4 = var3.bridge$getX();
      int var5 = var3.bridge$getY();
      int var6 = var3.bridge$getZ();
      Click.method9(var1, var2, var4 + 0.5, var5 + 2.0, var6 + 0.5, -1, true);
      if ((Boolean)this.field10.get()) {
         int var7 = (int)ThreadModuleDump63.method7().HROHOIOCHIRIHICOORIHOHCIOIRIIH(var4, var5, var6);
         Click.method9(var1, var7 + "m", var4 + 0.5, var5 + 1.5, var6 + 0.5, -256, true);
      }
   }

   private boolean method8(ArrayList<Horsestats20> var1, Horsestats20 var2) {
      for (Horsestats20 var4 : var1) {
         if (var4.method1(var2)) {
            return true;
         }
      }

      var1.add(var2);
      return false;
   }

   public String getId() {
      return "DUNGEON_SECRET_WAYPOINTS";
   }

   public void method2(LightingExtension23 var1) {
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field9, this.field10, this.field16});
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field22}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field12, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field23}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field24}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field14, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field25, this.field26}));
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field15, var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field21}));
      var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new LightingExtension[]{this.field17, this.field18, this.field19, this.field20});
   }

   protected Framework8 method20() {
      return Framework8.method7().method1(new Calculator2Handler[]{Calculator2Handler.field5}).method11(this);
   }

   private static class Data {
      private final Horsestats20 field1;
      private final String field2;
      private final LightingExtension4222 field3;
      private final boolean field4;

      @Generated
      public Horsestats20 method1() {
         return this.field1;
      }

      @Generated
      public String getText() {
         return this.field2;
      }

      @Generated
      public LightingExtension4222 method2() {
         return this.field3;
      }

      @Generated
      public boolean isNonstandardShape() {
         return this.field4;
      }

      @Generated
      public Data(Horsestats20 var1, String var2, LightingExtension4222 var3, boolean var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }
   }
}
