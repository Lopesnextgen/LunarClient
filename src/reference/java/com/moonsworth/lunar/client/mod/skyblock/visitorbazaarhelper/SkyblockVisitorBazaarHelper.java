package com.moonsworth.lunar.client.mod.skyblock.visitorbazaarhelper;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiEditSignBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockVisitorBazaarHelper extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^ (?<item>[\\w' ]+?)( x(?<amount>[\\d,]+))?$");
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method11(ScreenTitleListener.class);
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("openRecipeMenu").method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showAmountInOrderMenu").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field12 = (ColorOption)((Data)OptionFactory.method8("orderMenuAmountColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field13 = (ColorOption)((Data)OptionFactory.method8("commandColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method15()
      .method31();
   private boolean field14;
   private String field15;
   private String itemName;
   private String command;
   private int field16;

   public SkyblockVisitorBazaarHelper(Skyblock skyblock1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.GARDEN));
      this.method51(this::reset);
      this.handle(EventSlotUpdate.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method2);
      this.handle(EventRenderSlot.class, this::method3);
      this.handle(EventRenderContainerSlotPost.class, this::method4);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange.class, arg1x -> this.reset());
   }

   private void method1(EventSlotUpdate highlightimpl1) {
      ItemStackBridge bridgeextension_42 = highlightimpl1.method3();
      if (this.method5(bridgeextension_42)) {
         this.reset();
         boolean flag3 = false;
         List list4 = SkyblockItemUtil.method15(bridgeextension_42);

         for (int index5 = list4.size() - 1; index5 >= 0; index5--) {
            String text6 = (String)list4.get(index5);
            if (text6.equals("Click to give!")) {
               return;
            }

            if (text6.equals("Rewards:")) {
               flag3 = true;
            } else if (flag3) {
               Matcher matcher7 = field8.matcher(text6);
               if (matcher7.matches()) {
                  String text8 = matcher7.group("item");
                  String text9 = matcher7.group("amount");
                  int number10 = text9 == null ? 1 : Integer.parseInt(text9.replaceAll(",", ""));
                  number10 -= this.method7(text8);
                  if (number10 > 0) {
                     this.field15 = this.field9.method5();
                     this.itemName = text8;
                     this.field16 = number10;
                     this.field14 = true;
                     if (text8.equals("Jack o' Lantern")) {
                        this.command = "/call builder";
                     } else if (text8.equals("Golden Carrot")) {
                        this.command = "/call alchemist";
                     } else {
                        this.command = (this.field10.get() ? "/recipe " : "/bz ") + text8;
                     }
                  }
               }
            }
         }
      }
   }

   private void method2(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      if (this.field14 && this.itemName != null) {
         if (this.method5((ItemStackBridge)data21.method1().orElse(null))) {
            List list2 = data21.method3();
            TextColor textcolor3 = TextColor.color(this.field13.method14(0.0F));
            list2.add(TextComponentFactory.clickable(Component.text(this.method14("clickToRun", new Object[0]), textcolor3)));
            list2.add(TextComponentFactory.clickable(Component.text(this.command, textcolor3)));
            data21.method2(list2);
         }
      }
   }

   private void method3(EventRenderSlot highlightimpl51) {
      if (this.field14 && this.itemName != null) {
         SlotBridge bridge3_182 = highlightimpl51.method5();
         if (bridge3_182 != null) {
            ItemStackBridge bridgeextension_43 = bridge3_182.bridge$getItemStack();
            if (!this.method5(bridgeextension_43)) {
               if (this.method6(bridgeextension_43)) {
                  this.reset();
               }
            } else {
               highlightimpl51.setCancelled(true);
               Ref.method7().bridge$closeScreen();
               ChatMessageQueue.method1(this.command);
            }
         }
      }
   }

   private void method4(EventRenderContainerSlotPost data31) {
      if ((Boolean)this.field11.get()) {
         if (this.field14 && this.itemName != null) {
            GuiScreenBridge bridge5extension62 = data31.method3();
            if (bridge5extension62 != null && bridge5extension62.method1(GuiEditSignBridge.class)) {
               GuiEditSignBridge bridge5extension3_23 = (GuiEditSignBridge)bridge5extension62;
               String text4 = this.field10.get() ? "of crafts" : "to order";
               boolean flag5 = bridge5extension3_23.bridge$getLine(1).equals("^^^^^^^^^^^^^^^")
                  && bridge5extension3_23.bridge$getLine(2).equals("Enter amount")
                  && bridge5extension3_23.bridge$getLine(3).equals(text4);
               if (flag5) {
                  data31.method5()
                     .method27(
                        Ref.method10(),
                        Component.text(this.method14("visitorWants", new Object[]{this.field15, this.field16, this.itemName})),
                        bridge5extension62.bridge$getWidth() / 2,
                        25,
                        this.field12.method14(0.0F),
                        true
                     );
               }
            }
         }
      }
   }

   private boolean method5(ItemStackBridge bridgeextension_41) {
      return bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty() ? bridgeextension_41.bridge$getDisplayName().endsWith("Accept Offer") : false;
   }

   private boolean method6(ItemStackBridge bridgeextension_41) {
      return bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty() ? bridgeextension_41.bridge$getDisplayName().endsWith("Refuse Offer") : false;
   }

   private int method7(String text1) {
      int number2 = 0;

      for (ItemStackBridge bridgeextension_44 : Ref.method7().bridge$getInventory().bridge$getMainInventory()) {
         if (bridgeextension_44 != null && !bridgeextension_44.bridge$isEmpty() && ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_44.bridge$getDisplayName()).equals(text1)) {
            number2 += bridgeextension_44.bridge$getStackSize();
         }
      }

      return number2;
   }

   private void reset() {
      this.field14 = false;
      this.field15 = null;
      this.itemName = null;
      this.field16 = 0;
   }

   public String getId() {
      return "SKYBLOCK_VISITOR_BAZAAR_HELPER";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12}));
      });
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field13}));
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
