package com.moonsworth.lunar.client.mod.hud.tab;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.account.BadgeManager;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModRestriction;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.net.ServerUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.UUID;
import lombok.Generated;

public class Tab extends AbstractFeature {
   private final HypixelLocationListener hypixelLocationListener = (HypixelLocationListener)this.method50(HypixelLocationListener.class);
   private final ToggleOption pingRow = (ToggleOption)OptionFactory.method7("pingRow").method31();
   private final ColorOption headerColor = (ColorOption)((Data)OptionFactory.method8("headerColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(Integer.MIN_VALUE))
      .method31();
   private final ColorOption footerColor = (ColorOption)((Data)OptionFactory.method8("footerColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(Integer.MIN_VALUE))
      .method31();
   private final ColorOption backgroundColor = (ColorOption)((Data)OptionFactory.method8("backgroundColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(Integer.MIN_VALUE))
      .method31();
   private final ColorOption rowsColor = (ColorOption)((Data)OptionFactory.method8("rowsColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(536870912))
      .method31();
   private final ToggleOption highlightOwnName = (ToggleOption)OptionFactory.method7("highlightOwnName").method31();
   private final ColorOption nameColor = (ColorOption)((Data)OptionFactory.method8("nameColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ToggleOption disableHeader = (ToggleOption)OptionFactory.method7("disableHeader").method31();
   private final ToggleOption disableFooter = (ToggleOption)OptionFactory.method7("disableFooter").method31();
   private final ToggleOption hideNpc = (ToggleOption)OptionFactory.method7("hideNPC").method31();
   private final ToggleOption nameShadow = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("nameShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption pingNumberShadow = (ToggleOption)OptionFactory.method7("pingNumberShadow").method31();
   private final ToggleOption hidePing = (ToggleOption)OptionFactory.method7("hidePing").method31();
   private final ToggleOption hidePingIfOver500 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hidePingIfOver500").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption dynamicPingColor = (ToggleOption)OptionFactory.method7("dynamicPingColor").method31();
   private final ToggleOption displayPingAsNumber = (ToggleOption)OptionFactory.method7("displayPingAsNumber").method31();
   private final ColorOption pingNumberColor = (ColorOption)((Data)OptionFactory.method8("pingNumberColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption lowPingNumberColor = (ColorOption)((Data)OptionFactory.method8("lowPingNumberColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ColorOption mediumPingNumberColor = (ColorOption)((Data)OptionFactory.method8("mediumPingNumberColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption highPingNumberColor = (ColorOption)((Data)OptionFactory.method8("highPingNumberColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();
   private final ColorOption extremePingNumberColor = (ColorOption)((Data)OptionFactory.method8("extremePingNumberColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5636096))
      .method31();
   private final ToggleOption showLunarIconsOnRight = (ToggleOption)OptionFactory.method7("showLunarIconsOnRight").method31();
   private final ToggleOption moveSelfToTop = (ToggleOption)OptionFactory.method7("moveSelfToTop").method31();
   private final ToggleOption displayPlayerHead = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("displayPlayerHead").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final SimpleKeybindOption tabKeybind = ((SimpleKeybindOption)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)((com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data)OptionFactory.method17(
                     "tabKeybind"
                  )
                  .method2(KeyCode.KEY_TAB))
               .method18(this))
            .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
         .method31())
      .method1(this.mc.bridge$getGameSettings().bridge$keyBindPlayerList());
   private final ToggleOption toggleKeyTab = (ToggleOption)OptionFactory.method7("toggleKeyTab").method31();
   private boolean active;

   public Tab() {
      super(true);
      this.method5(ModTraits.field20, ModRestriction.method2(this, () -> {
         String text1 = this.hypixelLocationListener.method7().field3;
         return text1 != null && text1.startsWith("RAVENGARD");
      }));
   }

   public String getId() {
      return "TAB";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.tabKeybind});
      ((SettingsSectionImpl)lightingextension231.method7(
            SettingsPage.GENERAL,
            arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new ClientOption[]{this.toggleKeyTab, this.disableHeader, this.displayPlayerHead, this.disableFooter, this.hideNpc, this.showLunarIconsOnRight}
            )
         ))
         .method6(1);
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.headerColor, this.footerColor}).method6(1);
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.backgroundColor, this.pingRow});
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.rowsColor}).method3(this.pingRow::get);
      });
      lightingextension231.method1("nameOptions", arg1x -> {
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.highlightOwnName, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.nameColor}));
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.nameShadow});
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.moveSelfToTop});
      });
      lightingextension231.method1(
         "pingOptions",
         arg1x -> {
            arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.hidePing, this.hidePingIfOver500});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.displayPingAsNumber,
               arg1xx -> {
                  arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.pingNumberShadow});
                  arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                     this.dynamicPingColor,
                     arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.lowPingNumberColor, this.mediumPingNumberColor, this.highPingNumberColor, this.extremePingNumberColor})
                  );
                  arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.pingNumberColor}).method3(this.dynamicPingColor::get);
               }
            );
         }
      );
      this.tabKeybind.method3(() -> {
         if (Ref.method11() == null) {
            if (!this.active) {
               this.active = true;
            } else {
               this.tabKeybind.method11(0L);
            }
         }
      }).method2(arg1x -> {
         if (!(Boolean)this.toggleKeyTab.get() || !arg1x) {
            this.active = false;
         }
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field4}).method11(this);
   }

   public boolean renderLeftIcon(MixinHelper_4 mixinhelper_41, float value2, float value3, UUID uuid4) {
      return !Ref.method4().method41().method6().method41().get() ? false : method5(mixinhelper_41, value2, value3, 8.0F, uuid4);
   }

   public boolean renderRightIcon(MixinHelper_4 mixinhelper_41, float value2, float value3, UUID uuid4) {
      return !Ref.method4().method41().method6().method42().get() ? false : renderBadge(mixinhelper_41, value2 + 1.0F, value3, 8.0F, uuid4);
   }

   public static boolean method5(MixinHelper_4 mixinhelper_40, float value1, float value2, float value3, UUID uuid4) {
      com.moonsworth.lunar.client.account.TabLogoManager.Data data5 = Ref.method4().method91().method6(uuid4);
      if (data5 == null) {
         return false;
      }

      float value6 = value3 / 8.0F;
      mixinhelper_40.push();
      mixinhelper_40.method44(arg0x -> arg0x.method29().method33());
      int number7 = Ref.method3().bridge$getGuiScale();

      ResourceLocationBridge horsestats148 = switch (Math.round(number7 * value6)) {
         case 1, 2 -> CosmeticManager.field41;
         case 3 -> CosmeticManager.field42;
         case 4 -> CosmeticManager.field43;
         default -> CosmeticManager.field39;
      };
      LcuiScreen.method31(mixinhelper_40, horsestats148, value1, value2, value3, value3, data5.method1());
      if (data5.method2() != 0) {
         value3 = LcuiScreen.method135(3.0F * value6);
         LcuiScreen.method31(
            mixinhelper_40, CosmeticManager.field37, LcuiScreen.method135(value1 + 5.0F * value6), LcuiScreen.method135(value2 + 1.5F * value6), value3, value3, data5.method2()
         );
      }

      mixinhelper_40.pop();
      mixinhelper_40.method44(arg0x -> {
         AbstractRenderContext bridgeextension_91x = arg0x.method29();
         bridgeextension_91x.method18();
         bridgeextension_91x.method16();
      });
      return true;
   }

   public static boolean renderBadge(MixinHelper_4 mixinhelper_40, float value1, float value2, float value3, UUID uuid4) {
      com.moonsworth.lunar.client.account.TabLogoManager.Data data5 = Ref.method4().method91().method6(uuid4);
      if (data5 != null && data5.method3() != null) {
         mixinhelper_40.push();
         mixinhelper_40.method44(arg0x -> {
            AbstractRenderContext bridgeextension_91x = arg0x.method29();
            bridgeextension_91x.method33();
            bridgeextension_91x.method19();
         });
         BadgeManager.method8(mixinhelper_40, data5.method3(), value1, value2, value3, value3, -1);
         mixinhelper_40.pop();
         mixinhelper_40.method44(arg0x -> {
            AbstractRenderContext bridgeextension_91x = arg0x.method29();
            bridgeextension_91x.method18();
            bridgeextension_91x.method16();
            if (bridgeextension_91x.method38()) {
               bridgeextension_91x.method30().method48();
            }
         });
         return true;
      } else {
         return false;
      }
   }

   public boolean renderPingNumber(AbstractRenderContext bridgeextension_91, int number2, float value3, float value4) {
      if (this.isEnabled() && (Boolean)this.displayPingAsNumber.get()) {
         bridgeextension_91.push();
         String text5 = number2 + "";
         int number6;
         if (!(Boolean)this.dynamicPingColor.get()) {
            number6 = this.pingNumberColor.method14(0.0F);
         } else {
            number6 = this.getPingColor(number2);
         }

         if ((Boolean)this.pingNumberShadow.get()) {
            FontRegistry.field6.method4(bridgeextension_91, text5, value3 - FontRegistry.field6.method4(text5), value4, number6, true);
         } else {
            FontRegistry.field6.method10(bridgeextension_91, text5, value3 - FontRegistry.field6.method4(text5), value4, number6, false, true);
         }

         bridgeextension_91.pop();
         return true;
      } else {
         return false;
      }
   }

   public boolean shouldHidePing(int number1) {
      if (number1 < 0) {
         return true;
      } else {
         return ServerUtils.isServer("skyblock") && number1 == 1
            ? true
            : this.isEnabled() && (Boolean)this.hidePing.get() || number1 <= 0 || number1 > 500 && (Boolean)this.hidePingIfOver500.get();
      }
   }

   public int getPingColor(int number1) {
      if (number1 < 0) {
         return this.pingNumberColor.method14(0.0F);
      } else if (number1 < 65) {
         return this.lowPingNumberColor.method14(0.0F);
      } else if (number1 < 120) {
         return this.mediumPingNumberColor.method14(0.0F);
      } else {
         return number1 < 250 ? this.highPingNumberColor.method14(0.0F) : this.extremePingNumberColor.method14(0.0F);
      }
   }

   public boolean isNpcHidden() {
      return this.isEnabled() && (Boolean)this.hideNpc.get() && HypixelLocationListener.field7.method7().method1();
   }

   public boolean isMoveSelfToTop() {
      return this.isEnabled() && (Boolean)this.moveSelfToTop.get();
   }

   public boolean isLunarIconsOnRight() {
      return this.isEnabled() && (Boolean)this.showLunarIconsOnRight.get();
   }

   @Generated
   public HypixelLocationListener getHypixelLocationListener() {
      return this.hypixelLocationListener;
   }

   @Generated
   public ToggleOption getPingRow() {
      return this.pingRow;
   }

   @Generated
   public ColorOption getHeaderColor() {
      return this.headerColor;
   }

   @Generated
   public ColorOption getFooterColor() {
      return this.footerColor;
   }

   @Generated
   public ColorOption getBackgroundColor() {
      return this.backgroundColor;
   }

   @Generated
   public ColorOption getRowsColor() {
      return this.rowsColor;
   }

   @Generated
   public ToggleOption getHighlightOwnName() {
      return this.highlightOwnName;
   }

   @Generated
   public ColorOption getNameColor() {
      return this.nameColor;
   }

   @Generated
   public ToggleOption getDisableHeader() {
      return this.disableHeader;
   }

   @Generated
   public ToggleOption getDisableFooter() {
      return this.disableFooter;
   }

   @Generated
   public ToggleOption getHideNpc() {
      return this.hideNpc;
   }

   @Generated
   public ToggleOption getNameShadow() {
      return this.nameShadow;
   }

   @Generated
   public ToggleOption getPingNumberShadow() {
      return this.pingNumberShadow;
   }

   @Generated
   public ToggleOption getHidePing() {
      return this.hidePing;
   }

   @Generated
   public ToggleOption getHidePingIfOver500() {
      return this.hidePingIfOver500;
   }

   @Generated
   public ToggleOption getDynamicPingColor() {
      return this.dynamicPingColor;
   }

   @Generated
   public ToggleOption getDisplayPingAsNumber() {
      return this.displayPingAsNumber;
   }

   @Generated
   public ColorOption getPingNumberColor() {
      return this.pingNumberColor;
   }

   @Generated
   public ColorOption getLowPingNumberColor() {
      return this.lowPingNumberColor;
   }

   @Generated
   public ColorOption getMediumPingNumberColor() {
      return this.mediumPingNumberColor;
   }

   @Generated
   public ColorOption getHighPingNumberColor() {
      return this.highPingNumberColor;
   }

   @Generated
   public ColorOption getExtremePingNumberColor() {
      return this.extremePingNumberColor;
   }

   @Generated
   public ToggleOption getShowLunarIconsOnRight() {
      return this.showLunarIconsOnRight;
   }

   @Generated
   public ToggleOption getMoveSelfToTop() {
      return this.moveSelfToTop;
   }

   @Generated
   public ToggleOption getDisplayPlayerHead() {
      return this.displayPlayerHead;
   }

   @Generated
   public SimpleKeybindOption getTabKeybind() {
      return this.tabKeybind;
   }

   @Generated
   public ToggleOption getToggleKeyTab() {
      return this.toggleKeyTab;
   }

   @Generated
   public boolean isActive() {
      return this.active;
   }
}
