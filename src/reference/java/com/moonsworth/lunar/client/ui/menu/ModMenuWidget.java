package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.ui.widget.ScreenLifecycle;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.Staffxray;
import com.moonsworth.lunar.client.profile.ModProfile;
import com.moonsworth.lunar.client.profile.importer.ExternalProfileLocator;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.MigrationContextLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;

public class ModMenuWidget extends GuiWidget implements EditState {
   private List<EditableLabelWidget> field16 = new ArrayList<>();
   private SubLabelWidget field17;
   private EditableLabelWidget field18;
   private EditableLabelWidget field19;
   private ModSearchWidget field20;
   private SearchResultsWidget field21;
   private ModListWidget field22;
   private GuiWidget field23;
   private GuiWidget field24;
   private com.moonsworth.lunar.client.ui.widget.ProgressBarWidget field25;
   private Map<ModProfile, com.moonsworth.lunar.client.ui.widget.TextFieldWidget> field26 = new LinkedHashMap<>();
   private ResourceLocationBridge field27 = ResourceLocationBridge.create("lunar", "icons/pencil-64.png");
   private ResourceLocationBridge field28 = ResourceLocationBridge.create("lunar", "icons/exit-17x17.png");
   private final FeatureSettingsScreen field29;

   public ModMenuWidget(FeatureSettingsScreen var1, GuiWidget var2) {
      super(var2);
      this.field29 = var1;
      this.field20 = new ModSearchWidget(this);
      this.field22 = new ModListWidget(this);
      this.field21 = new SearchResultsWidget(this);
      this.field23 = this.field20;
      EditableLabelWidget var3;
      this.field16.add(var3 = new EditableLabelWidget(this, "mods"));
      var3.setActive(true);
      EditableLabelWidget var4;
      this.field16.add(var4 = new EditableLabelWidget(this, "settings"));
      EditableLabelWidget var5;
      this.field16.add(var5 = new EditableLabelWidget(this, "waypoints"));
      EditableLabelWidget var6 = null;

      for (Framework7Extension var8 : ThreadModuleDump63.method4().method44().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         if (((Staffxray)var8.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field2)).method1()) {
            this.field16.add(var6 = new EditableLabelWidget(this, "staff"));
            break;
         }
      }

      this.field25 = new com.moonsworth.lunar.client.ui.widget.ProgressBarWidget(null, ResourceLocationBridge.create("lunar", "icons/mainmenu/exit-17x17.png"));
      this.field17 = new SubLabelWidget(this, "editHudLayout", FontRegistry.method7());
      this.field18 = new EditableLabelWidget(this, "saveNewProfile", FontRegistry.method7());
      this.field19 = new EditableLabelWidget(this, "importProfile", FontRegistry.method7());
      this.method1();
      this.method4(
         (var1x, var2x) -> {
            if (this.field24 != null) {
               return this.field24.method6(var1x, var2x);
            }

            for (GuiWidget var4x : this.field16) {
               if (var4x.method1(var1x)) {
                  return var4x.method6(var1x, var2x);
               }
            }

            for (com.moonsworth.lunar.client.ui.widget.TextFieldWidget var6x : this.field26.values()) {
               if (var6x.method3(var1x)) {
                  var6x.IHRHHRIHICHOOICIRIOOHOICHIRHOI(var1x, var2x);
               }
            }

            if (this.field17.method3(var1x)) {
               return this.field17.method6(var1x, var2x);
            } else if (this.field18.method3(var1x)) {
               return this.field18.method6(var1x, var2x);
            } else {
               return this.method7() && this.field19.method3(var1x)
                  ? this.field19.method6(var1x, var2x)
                  : this.field23.method6(var1x, var2x);
            }
         }
      );
      this.method3(
         (var1x, var2x) -> {
            if (this.field24 != null) {
               return this.field24.method8(var1x, var2x);
            }

            for (GuiWidget var4x : this.field16) {
               if (var4x.method1(var1x)) {
                  return var4x.method8(var1x, var2x);
               }
            }

            for (com.moonsworth.lunar.client.ui.widget.TextFieldWidget var6x : this.field26.values()) {
               if (var6x.method3(var1x)) {
                  var6x.method2(var1x, var2x);
               }
            }

            if (!this.field17.method3(var1x)) {
               return this.field17.method2(var1x, var2x);
            } else {
               return !this.field18.method3(var1x)
                  ? this.field18.method2(var1x, var2x)
                  : this.field23 != null && this.field23.method8(var1x, var2x);
            }
         }
      );
      this.HRICOROOOCCOCOROCRHHCRRIRCOICO((var2x, var3x) -> {
         if (this.field24 != null) {
            return this.field24.method7(var2x, var3x);
         }

         if (this.field25.method3(var2x)) {
            ThreadModuleDump63.method3().bridge$displayScreen(var1.method12());
            return true;
         }

         for (GuiWidget var5x : this.field16) {
            if (var5x.method1(var2x)) {
               return var5x.method7(var2x, var3x);
            }
         }

         for (com.moonsworth.lunar.client.ui.widget.TextFieldWidget var7 : this.field26.values()) {
            if (var7.method3(var2x)) {
               var7.IIORCIOOIHRRRICOHIRCIHOOCCOHRO(var2x, var3x);
            }
         }

         if (this.field17.method3(var2x)) {
            return this.field17.method7(var2x, var3x);
         } else if (this.field18.method3(var2x)) {
            return this.field18.method7(var2x, var3x);
         } else if (this.method7() && this.field19.method3(var2x)) {
            return this.field19.method7(var2x, var3x);
         } else {
            return this.field23.method1(var2x) ? this.field23.method7(var2x, var3x) : false;
         }
      });
      var3.method4((var1x, var2x) -> {
         this.method2(this.field20);
         this.method3(0);
         return true;
      });
      var4.method4((var1x, var2x) -> {
         this.method2(this.field22);
         this.method3(1);
         return true;
      });
      var5.method4((var1x, var2x) -> {
         DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field17);
         this.method3(2);
         return true;
      });
      if (var6 != null) {
         var6.method4((var1x, var2x) -> {
            this.method2(this.field21);
            this.method3(3);
            return true;
         });
      }

      this.field18.method4((var2x, var3x) -> {
         if (ThreadModuleDump63.method4().method61().method2().size() >= 8) {
            return false;
         }

         ThreadModuleDump63.method4().method61().method6(this.method22("profile", new Object[]{ThreadLocalRandom.current().nextInt()}));
         this.method1();
         var1.init();
         return true;
      });
      this.field19.method4((var0, var1x) -> {
         DriverViewportLegacy.method50().method17(DriverRouteRegistryLegacy.field21, new MigrationContextLegacy(false));
         return true;
      });
      this.field17
         .method4(
            (var0, var1x) -> {
               try {
                  if (ThreadModuleDump63.method3().bridge$getWorld() == null) {
                     ThreadModuleDump63.method3()
                        .bridge$displayScreen(
                           Bridge.method8().method18(new MainMenuButton(new com.moonsworth.lunar.client.ui.hud.HudEditorScreen()))
                        );
                  } else {
                     ThreadModuleDump63.method3()
                        .bridge$displayScreen(Bridge.method8().method18(new com.moonsworth.lunar.client.ui.hud.HudEditorScreen()));
                  }
               } catch (Exception var3x) {
                  var3x.printStackTrace();
               }

               return true;
            }
         );
   }

   public void method1() {
      this.field26.clear();
      List var1 = ThreadModuleDump63.method4().method61().method2();
      Collections.reverse(var1);

      for (ModProfile var3 : var1) {
         this.field26.put(var3, new com.moonsworth.lunar.client.ui.widget.TextFieldWidget(this, ""));
      }

      float var6 = 43.0F;

      for (Entry var4 : this.field26.entrySet()) {
         float var5 = var6;
         ((com.moonsworth.lunar.client.ui.widget.TextFieldWidget)var4.getValue())
            .method4(
               (var3x, var4x) -> {
                  if (this.method3(var3x)
                     && var3x.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() > this.y + var5
                     && var3x.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() < this.y + var5 + 14.0F
                     && var3x.HHHCHORHIHRCOHIOICICICHCRRICCI() >= this.x + 95.0F
                     && var3x.HHHCHORHIHRCOHIOICICICHCRRICCI() <= this.x + 104.0F) {
                     if (ThreadModuleDump63.method3().bridge$getWorld() == null) {
                        ThreadModuleDump63.method3()
                           .bridge$displayScreen(
                              Bridge.method8()
                                 .method18(
                                    new MainMenuButton(
                                       new com.moonsworth.lunar.client.ui.ProfileEditorScreen(
                                          ThreadModuleDump63.method3().bridge$getCurrentScreen(), (ModProfile)var4.getKey()
                                       )
                                    )
                                 )
                           );
                     } else {
                        ThreadModuleDump63.method3()
                           .bridge$displayScreen(
                              Bridge.method8()
                                 .method18(
                                    new com.moonsworth.lunar.client.ui.ProfileEditorScreen(
                                       ThreadModuleDump63.method3().bridge$getCurrentScreen(), (ModProfile)var4.getKey()
                                    )
                                 )
                           );
                     }

                     return true;
                  } else {
                     if (ThreadModuleDump63.method4().method61().method14().equals(var4.getKey())) {
                        return false;
                     }

                     ThreadModuleDump63.method4().method61().method3((ModProfile)var4.getKey());
                     if (this.field23 instanceof FeatureSettingsWidget var5x) {
                        var5x.method15();
                     }

                     this.field29.init();
                     return true;
                  }
               }
            );
         var6 += 16.0F;
      }
   }

   public void method2(GuiWidget var1) {
      if (this.field23 instanceof ModSearchWidget && !(var1 instanceof ModSearchWidget)) {
         ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      }

      if (this.field23 instanceof ScreenLifecycle var2) {
         var2.onClose();
      }

      this.field23 = var1;
      if (this.field23 instanceof ScreenLifecycle var4) {
         var4.method19();
      }
   }

   public void method3(int var1) {
      for (int var2 = 0; var2 < this.field16.size(); var2++) {
         this.field16.get(var2).setActive(var2 == var1);
      }
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      float var5 = 0.0F;
      float var6 = 0.0F;
      this.field25.method2(var1 + this.getWidth() - 24.0F, var2 + 8.0F, 16.0F, 16.0F);

      for (EditableLabelWidget var8 : this.field16) {
         var6 += var8.getWidth() + (this.field16.indexOf(var8) == this.field16.size() - 1 ? 0.0F : 8.0F);
      }

      for (EditableLabelWidget var12 : this.field16) {
         var12.method1(var1 + this.getWidth() / 2.0F - var6 / 2.0F + var5, var2 + 8.0F);
         var5 += var12.getWidth() + (this.field16.indexOf(var12) == this.field16.size() - 1 ? 0.0F : 8.0F);
      }

      float var11 = 43.0F;

      for (Entry var9 : this.field26.entrySet()) {
         if (Client.method109().method61().method2().contains(var9.getKey())) {
            ((com.moonsworth.lunar.client.ui.widget.TextFieldWidget)var9.getValue())
               .method2(var1, var2 + (var11 - 1.0F), 105.0F, 16.0F);
            var11 += 16.0F;
         }
      }

      this.field17.method2(var1 + 8.0F, var2 + var4 - 15.5F, 88.0F, 11.0F);
      this.field18.method2(var1 + 5.0F, var2 + var4 - 46.0F, 94.0F, 12.0F);
      this.field18.setOffset(1.0F);
      this.field19.method2(var1 + 5.0F, var2 + var4 - 60.0F, 94.0F, 12.0F);
      this.field19.setOffset(1.0F);
   }

   @Override
   public void update() {
      this.field23.update();
      if (this.field24 != null) {
         this.field24.update();
      }
   }

   @Override
   public boolean method6(MarkerModel.Data2 var1, int var2) {
      return this.field5 != null && this.field5.accept(var1, var2);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      boolean var4 = var3;
      var3 = var3 && this.field24 == null;
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, this.x - 1.0F, this.y, this.width + 2.0F, this.height, 4.0F, 1612586526);
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 3.25F, 1075715614);
      com.moonsworth.lunar.client.ui.LcuiScreen.method117(var1, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 5.0F, -1306912744);
      com.moonsworth.lunar.client.ui.LcuiScreen.method106(var1, this.x, this.y + 1.0F, this.width, 32.0F, 5.0F, 905969664);
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x, this.y + 32.5F, this.width, 0.5F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method101(
         var1, this.x, this.y + this.height - 20.0F, 105.0F, 19.0F, 7.0F, 536870912, false, true, true, false
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method101(
         var1, this.x, this.y + 42.0F, 105.0F, this.height - 70.0F, 8.0F, 536870912, false, true, false, true
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method101(
         var1, this.x, this.y + this.height - 66.0F, 105.0F, 38.0F, 8.0F, 1073741824, false, false, false, true
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method122(var1, this.x + 9.0F, this.y + 8.0F, true);
      this.field25.method3(var1, var2, var3 && this.field25.method3(var2));
      float var5 = 43.0F;
      int var6 = 0;

      for (ModProfile var8 : this.field26.keySet()) {
         boolean var9 = var6 == 0;
         if (var8.isActive()) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method101(
               var1, this.x, this.y + var5 - 1.0F, 105.0F, 16.0F, 8.0F, 553648127, false, var9, false, false
            );
         }

         com.moonsworth.lunar.client.ui.LcuiScreen.method51(
            var1, this.x, this.y + var5, 104.0F, 14.0F, 5.0F, 553648127, false, var9, false, false
         );
         com.moonsworth.lunar.client.ui.LcuiScreen.method39(var1, this.field27, 3.5F, this.x + 95.0F, this.y + (var5 + 3.5F), 872415231);
         if (this.method3(var2)
            && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() > this.y + var5
            && var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < this.y + var5 + 14.0F
            && var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() >= this.x + 95.0F
            && var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() <= this.x + 104.0F) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method39(var1, this.field27, 3.5F, this.x + 95.0F, this.y + (var5 + 3.5F), 872415231);
         }

         float var10 = 8.0F;
         if (var8.getIcon() != null) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method31(
               var1, var8.getIcon(), this.x + 2.0F, this.y + (var5 + 3.5F), 8.0F, 8.0F, -1493172225
            );
            var10 += 6.0F;
         }

         StringBuilder var11 = new StringBuilder();
         char[] var12 = var8.getDisplayName().toCharArray();
         int var13 = var12.length;

         for (int var14 = 0; var14 < var13; var14++) {
            Character var15 = var12[var14];
            var11.append(var15).append(" ");
         }

         FontRegistry.method9().method13(var1, var11.toString(), this.x + var10, this.y + (var5 + 3.5F), -1342177281);
         var5 += 16.0F;
         var6++;
      }

      this.field23.method2(this.x + 110.0F, this.y + 42.5F, this.width - 115.0F, this.height - 48.0F);

      for (GuiWidget var18 : this.field16) {
         var18.method3(var1, var2, var3);
      }

      this.field17.method3(var1, var2, var3);
      this.field18.method3(var1, var2, var3 && ThreadModuleDump63.method4().method61().method2().size() < 7);
      if (this.method7()) {
         this.field19.method3(var1, var2, var3 && ThreadModuleDump63.method4().method61().method2().size() < 8);
      }

      this.field23.method3(var1, var2, var3);
      if (this.field24 != null) {
         this.field24.method2(0.0F, 0.0F, this.field29.method22(), this.field29.method23());
         this.field24.method3(var1, var2, var4);
      }
   }

   private boolean method7() {
      return ExternalProfileLocator.method1();
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (this.field24 != null) {
         this.field24.method4(var1, var2);
      } else {
         this.field23.method4(var1, var2);
      }
   }

   @Override
   public boolean method5(int var1) {
      return this.field24 != null ? true : super.method5(var1) || this.field23.method5(var1);
   }

   @Override
   public void close() {
      if (this.field23 instanceof ScreenLifecycle var1) {
         var1.onClose();
      }

      if (this.field24 != null) {
         this.field24.close();
      }

      this.field23.close();
      Bridge.method18().method3(false);
   }

   @Override
   public boolean isEditing() {
      return this.field24 != null || this.field23 instanceof EditState && ((EditState)this.field23).isEditing();
   }

   @Generated
   public List<EditableLabelWidget> method10() {
      return this.field16;
   }

   @Generated
   public ModSearchWidget method14() {
      return this.field20;
   }

   @Generated
   public SearchResultsWidget method15() {
      return this.field21;
   }

   @Generated
   public ModListWidget method16() {
      return this.field22;
   }

   @Generated
   public GuiWidget method17() {
      return this.field23;
   }

   @Generated
   public GuiWidget method18() {
      return this.field24;
   }

   @Generated
   public void method16(GuiWidget var1) {
      this.field24 = var1;
   }

   @Generated
   public Map<ModProfile, com.moonsworth.lunar.client.ui.widget.TextFieldWidget> method19() {
      return this.field26;
   }
}
