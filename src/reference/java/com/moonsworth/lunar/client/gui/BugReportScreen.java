package com.moonsworth.lunar.client.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextFieldWidget;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.ui.widget.TitledWidget;
import com.moonsworth.lunar.client.ui.widget.AttachedPanel;
import com.moonsworth.lunar.client.ui.widget.SpacerWidget;
import com.moonsworth.lunar.client.ui.widget.IconTextButton;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import com.moonsworth.lunar.client.ui.BugReportCategory;

public class BugReportScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private static long field19 = 0L;
   private TitledWidget field20;
   private final IconTextButton field21;
   private final SpacerWidget field22;
   private final SpacerWidget field23;
   private final SpacerWidget field24;
   private ProgressBarWidget field25;
   private final TextLabelWidget field26;
   private final ProgressBarWidget field27;
   private BugReportCategory field28 = BugReportCategory.MOD;
   private final Bridge5Extension6 field29;
   private final float field30 = 120.0F;

   public BugReportScreen(Bridge5Extension6 var1) {
      this.field29 = var1;
      String var2 = this.field28.toString();
      this.field20
         .method14(
            ImmutableList.of(
               this.field22 = new SpacerWidget((LabelOption)OptionFactory.method15("shortDescription").method31(), null),
               this.field23 = new SpacerWidget((LabelOption)OptionFactory.method15("section").method31(), null),
               this.field24 = new SpacerWidget((LabelOption)OptionFactory.method15(var2).method31(), null),
               this.field21 = new IconTextButton(this.field20, FontRegistry.method17(), "description", 553648127, 905969663),
               this.field26 = new TextLabelWidget(this.field20, "submit"),
               this.field27 = new ProgressBarWidget(this.field20, ResourceLocationBridge.create("lunar", "icons/cosmetics/back-40x40.png")),
               this.field25 = new ProgressBarWidget(this.field20, ResourceLocationBridge.create("lunar", "icons/assets/arrow-down-17x17.png"))
            )
         );
      this.field25
         .method4(
            (var1x, var2x) -> {
               this.field20
                  .method4(
                     new AttachedPanel(this.field20) {
                        @Override
                        protected List<GuiWidget> method5() {
                           ArrayList var1x = new ArrayList();

                           for (BugReportCategory var5 : BugReportCategory.values()) {
                              TextFieldWidget var6;
                              var1x.add(
                                 var6 = new TextFieldWidget(
                                    this.ROHOROORIICHCHICIOOOHICHOICRRI,
                                    BugReportScreen.this.field24.getOption().method1(var5.toString(), new Object[0]),
                                    -1
                                 )
                              );
                              var6.method4(
                                 (var2x, var3) -> {
                                    float var4 = FontRegistry.method9().method4(BugReportScreen.this.field23.getOption().getName().toUpperCase());
                                    BugReportScreen.this.field28 = var5;
                                    BugReportScreen.this.field24.getOption().OIRHOOIICOCIOOHICRRRICORIHHIHC(var5.toString());
                                    BugReportScreen.this.field24
                                       .RIIIOHCCHRRRORICCHIIHHOORIIOIR(
                                          BugReportScreen.this.field20.getX() + BugReportScreen.this.field20.getWidth() - 74.0F,
                                          BugReportScreen.this.field20.getY() + 70.0F,
                                          var4
                                       );
                                    BugReportScreen.this.field20.method4(null);
                                    return true;
                                 }
                              );
                           }

                           return var1x;
                        }
                     }
                  );
               this.field20.method16().method2(var1x);
               return true;
            }
         );
      this.field21.method17(true);
      this.field21.method28(180);
      this.field21.method40(() -> {
         if (this.field21.method27() == -65536) {
            this.field21.method25(0);
         }
      });
      this.field26.method4((var2x, var3) -> {
         try {
            if (this.field21.getText().isEmpty() || !this.field21.getText().contains(" ") || this.field21.getText().length() < 5) {
               this.field21.method25(-65536);
               return false;
            } else if (this.field21.getText().toLowerCase().contains("penis") || this.field21.getText().toLowerCase().contains("dick")) {
               this.field21.method25(-65536);
               return false;
            } else if (System.currentTimeMillis() - field19 < 3000L) {
               ThreadModuleDump63.method3().bridge$displayScreen(var1);
               return true;
            } else {
               String var4 = this.field21.getText();
               String var5 = this.field28.name();
               ThreadModuleDump63.method3().bridge$displayScreen(var1);
               ThreadModuleDump63.method4().method69().method3("The bug report has been submitted. Thank you!");
               field19 = System.currentTimeMillis();
               return true;
            }
         } catch (Exception var6) {
            var6.printStackTrace();
            return false;
         }
      });
      this.field27.method4((var1x, var2x) -> {
         ThreadModuleDump63.method3().bridge$displayScreen(var1);
         return true;
      });
   }

   @Override
   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field20 = new TitledWidget(null, "reportABug") {
         @Override
         public void method3(float var1, float var2, float var3, float var4) {
            BugReportScreen.this.field22.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1, var2 + 32.0F, 16.0F, 16.0F);
            BugReportScreen.this.field21.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 8.0F, var2 + 48.0F, var3 - 16.0F, 16.0F);
            BugReportScreen.this.field25.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 24.0F, var2 + 70.0F, 14.0F, 14.0F);
            BugReportScreen.this.field23.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1, var2 + 70.0F, 14.0F, 14.0F);
            BugReportScreen.this.field24.RIIIOHCCHRRRORICCHIIHHOORIIOIR(var1 + var3 - 74.0F, var2 + 70.0F, 0.0F);
            BugReportScreen.this.field26.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 56.0F, var2 + 120.0F - 24.0F, 50.0F, 18.0F);
            BugReportScreen.this.field27.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 6.0F, var2 + 120.0F - 24.0F, 18.0F, 18.0F);
         }
      });
   }

   @Override
   public void init() {
      float var1 = 350.0F;
      float var2 = this.method22() / 2.0F - var1 / 2.0F;
      float var3 = this.method23() / 2.0F - 60.0F;
      this.field20.method2(var2, var3, var1, 120.0F);
      Bridge.method18().method3(true);
   }

   @Override
   public void update() {
      this.field22
         .getOption()
         .OIRHOOIICOCIOOHICRRRICORIHHIHC(this.method1("shortDescriptionChars", new Object[]{180 - this.field21.getText().length()}));
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
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
      return super.getLanguagePath() + ".bug";
   }
}
