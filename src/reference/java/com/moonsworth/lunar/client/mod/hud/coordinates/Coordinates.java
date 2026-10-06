package com.moonsworth.lunar.client.mod.hud.coordinates;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.ReducedDebugInfoNotifier;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.coordinates.CoordinatesChildHudModImpl;
import com.moonsworth.lunar.client.framework.feature.coordinates.CoordinatesChildHudModImpl2;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler27;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler27.Data3;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class Coordinates extends AbstractFeature {
   private final GuiRewindhandlersHandler27 biomeListener = (GuiRewindhandlersHandler27)this.method19(GuiRewindhandlersHandler27.class);
   private final ToggleOption showWhileTyping = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showWhileTyping").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption textShadow = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("textShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption background = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").method31();
   private final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final ColorOption backgroundColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   private final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final EnumOption<Gui2Extension> mode = (EnumOption<Gui2Extension>)OptionFactory.method10("mode", Gui2Extension.VERTICAL)
      .method31();
   private final ModifierKeybindOption copyCoords = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
               "copyCoords"
            )
            .method18(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ToggleOption moveChildrenIndividually = (ToggleOption)OptionFactory.method7("moveChildrenIndividually").method31();
   private final ToggleOption decimalCoordinates = (ToggleOption)OptionFactory.method7("decimalCoordinates").method31();
   private final CoordinatesHudEntry xChild = CoordinatesHudEntry.create(
      this, "X", () -> this.formatCoordinate(ThreadModuleDump63.method7().bridge$getPosX()), "500"
   );
   private final CoordinatesHudEntry yChild = CoordinatesHudEntry.create(
      this, "Y", () -> this.formatCoordinate(ThreadModuleDump63.method7().bridge$getBoundingBox().bridge$getMinY()), "62"
   );
   private final CoordinatesHudEntry zChild = CoordinatesHudEntry.create(
      this, "Z", () -> this.formatCoordinate(ThreadModuleDump63.method7().bridge$getPosZ()), "250"
   );
   private final CoordinatesHudEntry renderCountChild = CoordinatesHudEntry.create(this, "C", () -> {
      int var1 = this.mc.bridge$getLevelRenderer().bridge$getMaximumRenderCount();
      int var2 = this.mc.bridge$getLevelRenderer().bridge$getUnculledRenderCount();
      return var2 + "/" + var1;
   }, "92/4269");
   private final CoordinatesChildHudModImpl biomeChild = CoordinatesChildHudModImpl.render(this, "Biome", "Plains");
   private final CoordinatesChildHudModImpl2 directionChild = CoordinatesChildHudModImpl2.shouldRender(this, "Direction", "N");

   public Coordinates() {
      super(true);
      this.registerOptions(Framework.field1, new Coordinates.Data());
   }

   public String getId() {
      return "COORDINATES";
   }

   protected List<Framework7Extension> getChildMods() {
      return List.of(this.xChild, this.yChild, this.zChild, this.renderCountChild, this.directionChild, this.biomeChild);
   }

   public void registerOptions(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.method9(new ClientOption[]{this.mode, this.textShadow, this.showWhileTyping}).method3(this.moveChildrenIndividually::get);
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.background,
                  var1xx -> var1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                     this.border, var1xxx -> var1xxx.method9(new ClientOption[]{this.borderThickness})
                  )
               )
               .method3(this.moveChildrenIndividually::get);
            var1x.method9(new ClientOption[]{this.copyCoords}).method3(this.moveChildrenIndividually::get);
            var1x.method9(new ClientOption[]{this.decimalCoordinates, this.moveChildrenIndividually});
         }
      );
      ((SettingsSectionImpl)var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
            SettingsPage.COLOR, var1x -> var1x.method9(new ClientOption[]{this.backgroundColor, this.borderColor})
         ))
         .method2(this.moveChildrenIndividually::get);
      this.copyCoords.method3(() -> {
         if (!ThreadModuleDump63.method4().method40().method85().method19()) {
            if (ThreadModuleDump63.method8() != null) {
               Bridge5Extension_5 var0 = ThreadModuleDump63.method7();
               String var1x = String.format("X: %s Y: %s Z: %s", (int)var0.bridge$getPosX(), (int)var0.bridge$getPosY(), (int)var0.bridge$getPosZ());
               ThreadModuleDump68.setClipboardString(var1x);
               ThreadModuleDump63.method4().method69().method3("Copied coordinates to clipboard!");
            }
         }
      });
   }

   public boolean moveChildrenTogether() {
      return !(Boolean)this.moveChildrenIndividually.get();
   }

   public void shouldRender(CoordinatesHudEntry var1) {
      AlertExtension var2 = (AlertExtension)this.method7(Framework.field5);
      if (var2 != null) {
         for (Framework7Extension var4 : var2.getChildren()) {
            if (var4 instanceof CoordinatesHudEntry var5) {
               var5.method16().method11(var1.getLabelColor());
               var5.method14().method11(var1.getLabelColorOption());
            }
         }
      }
   }

   private String formatCoordinate(double var1) {
      if ((Boolean)this.decimalCoordinates.get()) {
         int var3 = (int)(var1 * 100.0);
         return String.valueOf(var3 / 100.0);
      } else {
         return String.valueOf(ThreadModuleDump67.method9(var1));
      }
   }

   @NotNull
   public static String getCardinalDirection(float var0) {
      String[] var1 = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
      double var2 = ThreadModuleDump67.method13(var0) + 180.0;
      var2 += 22.5;
      var2 %= 360.0;
      var2 /= 45.0;
      return var1[ThreadModuleDump67.method9(var2)];
   }

   public Data3 getBiome() {
      return this.biomeListener.method5();
   }

   @Generated
   public ToggleOption getShowWhileTyping() {
      return this.showWhileTyping;
   }

   @Generated
   public ToggleOption getMoveChildrenIndividually() {
      return this.moveChildrenIndividually;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void render(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
         if (var4) {
            double var5 = 500.0;
            double var7 = 62.0;
            double var9 = 250.0;
            this.shouldRender(var1.method2(), var5, var7, var9, 180.0F, 92, 4269, var2, var3, true);
         } else {
            double var15 = ThreadModuleDump63.method7().bridge$getPosX();
            double var16 = ThreadModuleDump63.method7().bridge$getBoundingBox().bridge$getMinY();
            double var17 = ThreadModuleDump63.method7().bridge$getPosZ();
            int var11 = Coordinates.this.mc.bridge$getLevelRenderer().bridge$getUnculledRenderCount();
            int var12 = Coordinates.this.mc.bridge$getLevelRenderer().bridge$getMaximumRenderCount();
            double var13;
            if (Coordinates.this.mc.bridge$getRenderViewEntity() != null) {
               var13 = Coordinates.this.mc.bridge$getRenderViewEntity().bridge$getRotationYaw();
            } else {
               var13 = Coordinates.this.mc.bridge$getPlayer().bridge$getRotationYaw();
            }

            this.shouldRender(var1.method2(), var15, var16, var17, (float)var13, var11, var12, var2, var3, false);
         }
      }

      public boolean method30() {
         return Coordinates.this.moveChildrenTogether();
      }

      public boolean shouldRender(boolean var1) {
         if ((Boolean)Coordinates.this.moveChildrenIndividually.get()) {
            return false;
         } else if (!(Boolean)Coordinates.this.showWhileTyping.get() && ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            return false;
         } else if (Coordinates.this.renderCountChild.isEnabled()) {
            return true;
         } else {
            boolean var2 = ReducedDebugInfoNotifier.method1() && !var1;
            if (var2
               || !Coordinates.this.xChild.isEnabled()
                  && !Coordinates.this.yChild.isEnabled()
                  && !Coordinates.this.zChild.isEnabled()
                  && !Coordinates.this.biomeChild.isEnabled()
                  && !Coordinates.this.directionChild.isEnabled()) {
               this.method58(0.0F, 0.0F);
               return false;
            } else {
               return true;
            }
         }
      }

      private void shouldRender(MixinHelper_4 var1, double var2, double var4, double var6, float var8, int var9, int var10, float var11, float var12, boolean var13) {
         boolean var14 = ReducedDebugInfoNotifier.method1() && !var13;
         boolean var15 = !var14 && Coordinates.this.xChild.isEnabled();
         boolean var16 = !var14 && Coordinates.this.yChild.isEnabled();
         boolean var17 = !var14 && Coordinates.this.zChild.isEnabled();
         boolean var18 = !var14 && Coordinates.this.biomeChild.isEnabled();
         boolean var19 = !var14 && Coordinates.this.directionChild.isEnabled();
         boolean var20 = !var14 && (Boolean)Coordinates.this.directionChild.method19().get();
         boolean var21 = Coordinates.this.renderCountChild.isEnabled();
         Bridge10_2 var22 = ThreadModuleDump63.method10();
         boolean var23 = (Boolean)Coordinates.this.background.get();
         boolean var24 = (Boolean)Coordinates.this.textShadow.get();
         if (var23) {
            Coordinates.this.backgroundColor.method11(var1, var11, var12, this.getWidth(), this.getHeight());
            if ((Boolean)Coordinates.this.border.get()) {
               Coordinates.this.borderColor.method11(var1, this, var11, var12, this.getWidth(), this.getHeight(), (Float)Coordinates.this.borderThickness.get());
            }
         }

         var1.method38(var11, var12, 0.0F);
         float var26 = 0.0F;
         float var25;
         if (Coordinates.this.mode.get() == Gui2Extension.HORIZONTAL) {
            ColorOption var27 = null;
            if (var15) {
               var27 = Coordinates.this.xChild.method16();
            } else if (var16) {
               var27 = Coordinates.this.yChild.method16();
            } else if (var17) {
               var27 = Coordinates.this.zChild.method16();
            } else if (var21) {
               var27 = Coordinates.this.renderCountChild.method16();
            }

            float var28 = var23 ? 3.0F : 0.0F;
            var25 = 5.0F;
            if (!var23 && var27 != null) {
               var25 = var27.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, "(", var25, var28, var24);
            }

            String var29 = Coordinates.getCardinalDirection(var8);
            if (var15) {
               var25 = Coordinates.this.xChild.method15().get()
                  ? Coordinates.this.xChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, "X: ", var25, var28, var24)
                  : var25;
               var25 = Coordinates.this.xChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, Coordinates.this.formatCoordinate(var2), var25, var28, var24);
               ColorOption var30;
               if (var20 && (var29.contains("W") || var29.contains("E"))) {
                  var25 = Coordinates.this.directionChild.method21().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var29.contains("W") ? " (-)" : " (+)", var25, var28, var24);
                  var30 = Coordinates.this.directionChild.method21();
               } else {
                  var30 = Coordinates.this.xChild.method14();
               }

               if (var16 || var17 || var21) {
                  var25 = var30.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, ", ", var25, var28, var24);
               }
            }

            if (var16) {
               var25 = Coordinates.this.yChild.method15().get()
                  ? Coordinates.this.yChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, "Y: ", var25, var28, var24)
                  : var25;
               var25 = Coordinates.this.yChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, Coordinates.this.formatCoordinate(var4), var25, var28, var24);
               if (var17 || var21) {
                  var25 = Coordinates.this.yChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, ", ", var25, var28, var24);
               }
            }

            if (var17) {
               var25 = Coordinates.this.zChild.method15().get()
                  ? Coordinates.this.zChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, "Z: ", var25, var28, var24)
                  : var25;
               var25 = Coordinates.this.zChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, Coordinates.this.formatCoordinate(var6), var25, var28, var24);
               ColorOption var48;
               if (var20 && (var29.contains("N") || var29.contains("S"))) {
                  var25 = Coordinates.this.directionChild.method22().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var29.contains("N") ? " (-)" : " (+)", var25, var28, var24);
                  var48 = Coordinates.this.directionChild.method22();
               } else {
                  var48 = Coordinates.this.zChild.method14();
               }

               if (var21) {
                  var25 = var48.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, ", ", var25, var28, var24);
               }
            }

            if (var21) {
               var25 = Coordinates.this.renderCountChild.method15().get()
                  ? Coordinates.this.renderCountChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, "C: ", var25, var28, var24)
                  : var25;
               var25 = Coordinates.this.renderCountChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var9 + "/" + var10, var25, var28, var24);
            }

            if (!var23) {
               if (var27 != null) {
                  var25 = var27.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, ")", var25, var28, var24);
               }
            } else {
               var26 += 4.0F;
            }

            if (var19 && (Boolean)Coordinates.this.directionChild.method17().get()) {
               boolean var49 = var15 || var16 || var17 || var21 || var18;
               if (var49) {
                  var29 = " " + var29;
               }

               var25 = Coordinates.this.directionChild.RRROIIRRRRICHCIIRHIRHRRHCICICC().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var29, var25, var28, var24);
            }

            var26 += var22.method19();
         } else {
            var25 = 0.0F;
            var26 = 5.0F;
            float var45 = 0.0F;
            float var46 = 0.0F;
            float var47 = 0.0F;
            float var50 = 0.0F;
            float var31 = 0.0F;
            float var33 = 0.0F;
            if (var15) {
               float var34 = Coordinates.this.xChild.method15().get()
                  ? Coordinates.this.xChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, "X: ", 5.0F, var26, var24)
                  : 5.0F;
               var25 = Math.max(
                  var25, Coordinates.this.xChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, Coordinates.this.formatCoordinate(var2), var34, var26, var24)
               );
               var46 = var26;
               var26 += var22.method19() + 2;
            }

            if (var16) {
               float var52 = Coordinates.this.yChild.method15().get()
                  ? Coordinates.this.yChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, "Y: ", 5.0F, var26, var24)
                  : 5.0F;
               var25 = Math.max(
                  var25, Coordinates.this.yChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, Coordinates.this.formatCoordinate(var4), var52, var26, var24)
               );
               var45 = var26;
               var26 += var22.method19() + 2;
            }

            if (var17) {
               float var53 = Coordinates.this.zChild.method15().get()
                  ? Coordinates.this.zChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, "Z: ", 5.0F, var26, var24)
                  : 5.0F;
               var25 = Math.max(
                  var25, Coordinates.this.zChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, Coordinates.this.formatCoordinate(var6), var53, var26, var24)
               );
               var47 = var26;
               var26 += var22.method19() + 2;
            }

            if (var21) {
               float var54 = Coordinates.this.renderCountChild.method15().get()
                  ? Coordinates.this.renderCountChild.method16().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, "C: ", 5.0F, var26, var24)
                  : 5.0F;
               var25 = Math.max(var25, Coordinates.this.renderCountChild.method14().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var9 + "/" + var10, var54, var26, var24));
               var50 = var26;
               var26 += var22.method19() + 2;
            }

            if (var18) {
               float var55 = Coordinates.this.biomeChild.OHCCCOORCROHHHCIIHHIIOORRCRICI().get()
                  ? Coordinates.this.biomeChild
                     .RCRIRCIIROOOHROHCORRORRHIIOCRI()
                     .HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, Coordinates.this.shouldRender("biome", new Object[0]) + ": ", 5.0F, var26, var24)
                  : 5.0F;
               Data3 var35 = Coordinates.this.getBiome();
               int var36 = Coordinates.this.biomeChild.RRROIIRRRRICHCIIRHIRHRRHCICICC().method14(var11 + var12);
               if ((Boolean)Coordinates.this.biomeChild.method17().get()) {
                  var36 = var35.method1();
               }

               var1.method19(var22, var35.name(), var55, var26, var36, var24);
               var25 = Math.max(var25, var55 + var22.bridge$getStringWidth(var35.name()));
               var31 = var26;
               var26 += var22.method19() + 2;
            }

            String var56 = Coordinates.getCardinalDirection(var8);
            if (var19) {
               boolean var57 = var15 || var16 || var17 || var21 || var18;
               if (var57) {
                  var25 += 20.0F;
               } else {
                  var25 = var22.bridge$getStringWidth(var56);
                  var26 = var22.method19() + 3;
               }

               float var37 = 12.0F;
               float var32 = var25 - var37 + 3.0F;
               float var38 = var57 ? var37 - var22.bridge$getStringWidth(var56) : 0.0F;
               if (var16) {
                  var33 = var45;
               } else if (!var15 && !var17) {
                  if (var21) {
                     var33 = var50;
                  } else if (var18) {
                     var33 = var31;
                  } else {
                     var33 = 3.0F;
                     var32 = 3.0F;
                     var20 = false;
                  }
               } else if (var15 && var17) {
                  var33 = var46 + (var47 - var46) / 2.0F;
                  var32 -= 9.0F;
               } else if (var15) {
                  var33 = var46;
                  var20 = false;
               } else {
                  var33 = var47;
                  var20 = false;
               }

               if ((Boolean)Coordinates.this.directionChild.method17().get()) {
                  Coordinates.this.directionChild.RRROIIRRRRICHCIIRHIRHRRHCICICC().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var56, var32 + var38 / 2.0F, var33, var24);
               }
            }

            if (var19 && var20) {
               if (var15 && (var56.contains("W") || var56.contains("E"))) {
                  Coordinates.this.directionChild
                     .method21()
                     .HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var56.contains("W") ? "-" : "+", var25 - var22.bridge$getStringWidth("-"), var46, var24);
               }

               if (var17 && (var56.contains("N") || var56.contains("S"))) {
                  Coordinates.this.directionChild
                     .method22()
                     .HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var56.contains("N") ? "-" : "+", var25 - var22.bridge$getStringWidth("-"), var47, var24);
               }
            }

            if (!var23) {
               var26 += 8.0F;
            } else {
               var26 += 2.0F;
            }
         }

         this.method58(var25 != 0.0F ? var25 + 5.0F : 0.0F, var25 != 0.0F ? var26 : 0.0F);
         var1.method38(-var11, -var12, 0.0F);
      }
   }
}
