package com.moonsworth.lunar.client.ui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.ConfirmScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextFieldWidget;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.ui.widget.TitledWidget;
import com.moonsworth.lunar.client.ui.widget.AttachedPanel;
import com.moonsworth.lunar.client.ui.widget.SpacerWidget;
import com.moonsworth.lunar.client.ui.widget.IconTextButton;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.framework.ItemSetHandler;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.profile.ModProfileManager;
import com.moonsworth.lunar.client.profile.ModProfile;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class ProfileEditorScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private TitledWidget field19;
   private final IconTextButton field20;
   private final SpacerWidget field21;
   private final SpacerWidget field22;
   private final SpacerWidget field23;
   private final IconTextButton field24;
   private final ProgressBarWidget field25;
   private final TextLabelWidget field26;
   private final TextLabelWidget field27;
   private final TextLabelWidget field28;
   private final TextLabelWidget field29;
   private final ProgressBarWidget field30;
   private final Bridge5Extension6 field31;
   private final ModProfile field32;
   private String field33;
   private ResourceLocationBridge field34;
   private final float field35 = 152.0F;

   public ProfileEditorScreen(Bridge5Extension6 var1, ModProfile var2) {
      this.field31 = var1;
      this.field32 = var2;
      this.field33 = var2.getIconName();
      if (!this.field33.equalsIgnoreCase("")) {
         this.field34 = ResourceLocationBridge.create("lunar", "icons/profiles/" + this.field33 + ".png");
      } else {
         this.field34 = null;
      }

      this.field19
         .method14(
            ImmutableList.of(
               this.field21 = new SpacerWidget((LabelOption)OptionFactory.method15("profileName").method31(), null),
               this.field22 = new SpacerWidget((LabelOption)OptionFactory.method15("icon").method31(), null),
               this.field20 = new IconTextButton(this.field19, FontRegistry.method17(), var2.getDisplayName(), 553648127, 905969663),
               this.field23 = new SpacerWidget((LabelOption)OptionFactory.method15("lblServer").method31(), null),
               this.field24 = new IconTextButton(
                  this.field19, FontRegistry.method17(), var2.getServer().equals("") ? "Unknown" : var2.getServer(), 553648127, 905969663
               ),
               this.field26 = new TextLabelWidget(this.field19, "save"),
               this.field27 = new TextLabelWidget(this.field19, "reset"),
               this.field28 = new TextLabelWidget(this.field19, "delete"),
               this.field29 = new TextLabelWidget(this.field19, "updateDefaults"),
               this.field30 = new ProgressBarWidget(this.field19, ResourceLocationBridge.create("lunar", "icons/cosmetics/back-40x40.png")),
               this.field25 = new ProgressBarWidget(this.field19, ResourceLocationBridge.create("lunar", "icons/assets/arrow-down-17x17.png"))
            )
         );
      this.field25.method4((var1x, var2x) -> {
         this.field19.method4(new AttachedPanel(this.field19) {
            @Override
            protected List<GuiWidget> method5() {
               ArrayList var1x = new ArrayList();

               for (ProfileEditorScreen.Type var5 : ProfileEditorScreen.Type.values()) {
                  TextFieldWidget var6;
                  var1x.add(var6 = new TextFieldWidget(this.ROHOROORIICHCHICIOOOHICHOICRRI, var5.displayId, -1));
                  var6.method4((var2x, var3) -> {
                     ProfileEditorScreen.this.field33 = var5.id;
                     if (var5 != ProfileEditorScreen.Type.NONE) {
                        ProfileEditorScreen.this.field34 = ResourceLocationBridge.create("lunar", "icons/profiles/" + ProfileEditorScreen.this.field33 + ".png");
                     } else {
                        ProfileEditorScreen.this.field34 = null;
                     }

                     ProfileEditorScreen.this.field19.method4(null);
                     return true;
                  });
               }

               return var1x;
            }
         });
         this.field19.method16().method2(var1x);
         return true;
      });
      this.field20.method36(false);
      this.field20.setText(var2.getDisplayName());
      this.field20.method28(20);
      this.field20.method40(() -> {
         if (this.field20.method27() == -65536) {
            this.field20.method25(0);
         }
      });
      this.field24.method36(false);
      this.field24.setText(var2.getServer());
      this.field24.method28(60);
      this.field24.method40(() -> {
         if (this.field24.method27() == -65536) {
            this.field24.method25(0);
         }
      });
      this.field26.HRICOROOOCCOCOROCRHHCRRIRCOICO((var3, var4) -> {
         if (this.field20.getText().isEmpty()) {
            this.field20.method25(-65536);
            return false;
         } else {
            var2.setDisplayName(this.field20.getText());
            var2.method1(this.field33);
            var2.setServer(this.field24.getText());
            ThreadModuleDump63.method3().bridge$displayScreen(var1);
            return true;
         }
      });
      this.field27.method4((var2x, var3) -> {
         Bridge5Extension6 var4 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
         ThreadModuleDump63.method3()
            .bridge$displayScreen(Bridge.method8().method18(new ConfirmScreen(this.getLanguagePath() + ".resetProfile", var2xx -> {
               if (!var2xx) {
                  ThreadModuleDump63.method3().bridge$displayScreen(var4);
               } else {
                  method1(var2);
               }
            })));
         return true;
      });
      this.field28
         .method4(
            (var2x, var3) -> {
               if (var2.method2()) {
                  return false;
               }

               Bridge5Extension6 var4 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
               ThreadModuleDump63.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new ConfirmScreen(this.getLanguagePath() + ".deleteConfirm", var2xx -> {
                     if (var2xx) {
                        if (var2.isActive()) {
                           ThreadModuleDump63.method4().method61().method3(ThreadModuleDump63.method4().method61().method3());
                        }

                        Client.method109().method61().method5(var2);
                        com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen var3x = new com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen(
                           null
                        );
                        var3x.method10().method2(var3x.method10().method14());
                        if (ThreadModuleDump63.method8() == null) {
                           ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new MainMenuButton(var3x)));
                        } else {
                           ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var3x));
                        }
                     } else {
                        ThreadModuleDump63.method3().bridge$displayScreen(var4);
                     }
                  })));
               return true;
            }
         );
      this.field29
         .method4(
            (var2x, var3) -> {
               if (!ModProfileManager.field1.contains(var2.getName())) {
                  return false;
               }

               Bridge5Extension6 var4 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
               ThreadModuleDump63.method3()
                  .bridge$displayScreen(Bridge.method8().method18(new ConfirmScreen(this.getLanguagePath() + ".updateDefaults", var2xx -> {
                     if (!var2xx) {
                        ThreadModuleDump63.method3().bridge$displayScreen(var4);
                     } else {
                        ModProfileManager var3x = ThreadModuleDump63.method4().method61();
                        ModProfile var4x = var3x.method13(var2);
                        var3x.method3(var4x);
                        ThreadModuleDump63.method3().bridge$displayScreen(var4);
                     }
                  })));
               return true;
            }
         );
      this.field30
         .method4(
            (var3, var4) -> {
               if (this.field20.getText().equalsIgnoreCase(var2.getDisplayName()) && this.field33.equalsIgnoreCase(var2.getIconName())) {
                  ThreadModuleDump63.method3().bridge$displayScreen(var1);
                  return true;
               } else {
                  Bridge5Extension6 var5 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
                  ThreadModuleDump63.method3()
                     .bridge$displayScreen(Bridge.method8().method18(new ConfirmScreen(this.getLanguagePath() + ".backConfirm", var2xx -> {
                        if (var2xx) {
                           ThreadModuleDump63.method3().bridge$displayScreen(var1);
                        } else {
                           ThreadModuleDump63.method3().bridge$displayScreen(var5);
                        }
                     })));
                  return true;
               }
            }
         );
   }

   private static void method1(ModProfile var0) {
      ModProfile var1 = ThreadModuleDump63.method4().method61().method14();
      if (!var0.isActive()) {
         ThreadModuleDump63.method4().method61().method3(var0);
      }

      ArrayList var2 = new ArrayList();

      for (ItemSetHandler var4 : ThreadModuleDump63.method4().method41().IORHHHROCRRHORHRCHCCHHIHICCRCO().values()) {
         var2.addAll(var4.method13());
      }

      for (Framework7Extension var12 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         var12.method3(Framework.field6).ifPresent(ModEnabledState::method2);
         AlertExtension var5 = (AlertExtension)var12.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
         if (var5 != null) {
            var5.HROOOICICRCOCIROHIRICCCOCCIORH(var1x -> {
               var1x.method3(Framework.field6).ifPresent(ModEnabledState::method2);
               Framework5 var2x = (Framework5)var1x.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
               if (var2x != null) {
                  var2.addAll(var2x.method2());
               }
            });
         }

         MixinCore9Extension var6 = (MixinCore9Extension)var12.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field1);
         if (var6 != null) {
            var6.method18();
         }

         Framework5 var7 = (Framework5)var12.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
         if (var7 != null) {
            var2.addAll(var7.method2());
         }
      }

      ThreadModuleDump63.method4().method41().method6().method68().reset();

      for (ClientOption var13 : var2) {
         var13.reset();
      }

      for (Framework7Extension var14 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         var14.method4();
      }

      if (!var1.isActive()) {
         ThreadModuleDump63.method4().method61().method3(var1);
      }

      com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen var11 = new com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen(null);
      var11.method10().method2(var11.method10().method14());
      if (ThreadModuleDump63.method8() == null) {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new MainMenuButton(var11)));
      } else {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var11));
      }
   }

   @Override
   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field19 = new TitledWidget(null, "profileEditor") {
         @Override
         public void method3(float var1, float var2, float var3, float var4) {
            ProfileEditorScreen.this.field21.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1, var2 + 32.0F, 16.0F, 16.0F);
            ProfileEditorScreen.this.field20.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 8.0F, var2 + 48.0F, var3 - 112.0F, 16.0F);
            ProfileEditorScreen.this.field23.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1, var2 + 64.0F, 16.0F, 16.0F);
            ProfileEditorScreen.this.field24.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 8.0F, var2 + 80.0F, var3 - 112.0F, 16.0F);
            ProfileEditorScreen.this.field22.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 29.0F, var2 + 32.0F, 14.0F, 14.0F);
            ProfileEditorScreen.this.field25.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 24.0F, var2 + 48.0F, 14.0F, 14.0F);
            int var5 = 0;
            float var10001 = var1 + var3;
            var5 += 56;
            ProfileEditorScreen.this.field26.RIIICIRHRCIHOOOORHOICRIICCCRHR(var10001 - var5, var2 + 152.0F - 24.0F, 50.0F, 18.0F);
            if (!ProfileEditorScreen.this.field32.method2()) {
               var10001 = var1 + var3;
               var5 += 56;
               ProfileEditorScreen.this.field28.RIIICIRHRCIHOOOORHOICRIICCCRHR(var10001 - var5, var2 + 152.0F - 24.0F, 50.0F, 18.0F);
            }

            if (ThreadModuleDump63.method8() != null) {
               var10001 = var1 + var3;
               var5 += 56;
               ProfileEditorScreen.this.field27.RIIICIRHRCIHOOOORHOICRIICCCRHR(var10001 - var5, var2 + 152.0F - 24.0F, 50.0F, 18.0F);
            }

            if (ModProfileManager.field1.contains(ProfileEditorScreen.this.field32.getName())) {
               var10001 = var1 + var3;
               var5 += 56;
               ProfileEditorScreen.this.field29.RIIICIRHRCIHOOOORHOICRIICCCRHR(var10001 - var5, var2 + 152.0F - 24.0F, 50.0F, 18.0F);
            }

            ProfileEditorScreen.this.field30.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 6.0F, var2 + 152.0F - 24.0F, 18.0F, 18.0F);
         }
      });
   }

   @Override
   public void init() {
      float var1 = 350.0F;
      float var2 = this.method22() / 2.0F - var1 / 2.0F;
      float var3 = this.method23() / 2.0F - 76.0F;
      this.field19.method2(var2, var3, var1, 152.0F);
      Bridge.method18().method3(true);
   }

   @Override
   public void update() {
      this.field21
         .getOption()
         .OIRHOOIICOCIOOHICRRRICORIHHIHC(this.method105("shortDescriptionChars", new Object[]{20 - this.field20.getText().length()}));
      this.field23.getOption().OIRHOOIICOCIOOHICRRRICORIHHIHC(this.method105("lblServer", new Object[0]));
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      if (this.field34 != null) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method31(
            var1, this.field34, this.field19.getX() + this.field19.getWidth() - 36.0F, this.field19.getY() + 50.0F, 10.0F, 10.0F, -1
         );
      }
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
   }

   @Override
   public void method12(MarkerModel.Data2 var1, int var2) {
   }

   @Override
   public void method14(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
      Bridge.method18().method3(false);
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + ".profile";
   }

   private enum Type {
      NONE("", "None"),
      CLOCK("clock", "Clock"),
      APPLE("apple", "Apple"),
      HYPIXEL("hypixel", "Hypixel"),
      SWORDS("crossed-swords", "Swords"),
      MOUSE("mouse", "Mouse");

      private final String id;
      private final String displayId;

      @Override
      public String toString() {
         return this.displayId;
      }

      @Generated
      Type(String var3, String var4) {
         this.id = var3;
         this.displayId = var4;
      }
   }
}
