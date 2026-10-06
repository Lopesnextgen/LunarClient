package com.moonsworth.lunar.client.mod.skyblock.terminalsolvers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickTypeBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.TerminalActivateEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.TerminalActivateEvent.Type;
import com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import lombok.Generated;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockTerminalSolvers extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method71(HighlightTypeListener.class);
   private final ScreenTitleListener field9 = (ScreenTitleListener)this.method71(ScreenTitleListener.class);
   private final DungeonFloorListener field10 = (DungeonFloorListener)this.method71(DungeonFloorListener.class);
   private final LocalPlayerNameListener field11 = (LocalPlayerNameListener)this.method71(LocalPlayerNameListener.class);
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("selectColorTerminal").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("clickInOrderTerminal").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("firstLetterTerminal").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("matchColorTerminal").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("simonSaysDevice").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("arrowAlignDevice").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("targetPracticeDevice").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideWrongTerminalItems").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field20 = (ColorOption)((Data)OptionFactory.method8("correctTerminalColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final IntegerOption field21 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "showNextClickInOrder"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 3))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showAllSimonSaysButtons").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field23 = (ColorOption)((Data)OptionFactory.method8("correctTerminalColorTwo").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-256))
      .method31();
   private final ColorOption field24 = (ColorOption)((Data)OptionFactory.method8("correctTerminalColorThree")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ToggleOption field25 = (ToggleOption)OptionFactory.method7("blockWrongTerminalClicks").method31();
   private final ModifierKeybindOption field26 = (ModifierKeybindOption)OptionFactory.method18("blockWrongTerminalClicksOverride")
      .method5(KeyCode.KEY_LCONTROL)
      .method11()
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideTooltipsTerminals").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("terminalMiddleClicks").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockMatchColorsArrows")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field30 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("firstClickProtection").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field31 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "firstClickProtectionTime"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(350))
         .method7(350, 1000))
      .method31();
   private final ToggleOption field32 = (ToggleOption)OptionFactory.method7("announceMelody").method31();
   private final ToggleOption field33 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("melodySendCoords").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final TextOption field34 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
            "melodyStart"
         )
         .method2("Melody terminal started!"))
      .method3(250)
      .method31();
   private final TextOption field35 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
            "melody25"
         )
         .method2("Melody 25%"))
      .method3(250)
      .method31();
   private final TextOption field36 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
            "melody50"
         )
         .method2("Melody 50%"))
      .method3(250)
      .method31();
   private final TextOption field37 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
            "melody75"
         )
         .method2("Melody 75%"))
      .method3(250)
      .method31();
   private final TextOption field38 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
            "melodyComplete"
         )
         .method2("Melody terminal completed!"))
      .method3(250)
      .method31();
   private final ToggleOption field39 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("sendInPartyChat").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field40 = (ToggleOption)OptionFactory.method7("customTerminalGuiScale").method31();
   private final IntegerOption field41 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "terminalGuiScale"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(1, 5))
      .method31();
   private final SkyblockArrowAlign field42 = new SkyblockArrowAlign(this, this.field17);
   private final SkyblockClickInOrder field43 = new SkyblockClickInOrder(this, this.field13);
   private final SkyblockFirstLetter field44 = new SkyblockFirstLetter(this, this.field14);
   private final SkyblockMatchColors field45 = new SkyblockMatchColors(this, this.field15);
   private final SkyblockSelectColor field46 = new SkyblockSelectColor(this, this.field12);
   private final SkyblockSimonSays field47 = new SkyblockSimonSays(this, this.field16);
   private final SkyblockTargetPractice field48 = new SkyblockTargetPractice(this, this.field18);
   private HudTimer field49;
   private boolean field50;
   private int field51;
   private int field52;

   public SkyblockTerminalSolvers(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.Data.class, arg1x -> {
         this.method1(arg1x);
         this.method2(arg1x);
      });
      this.handle(TerminalActivateEvent.class, this::method3);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method4);
      this.handle(EventSlotUpdate.class, this::method6);
      this.handle(EventRenderSlot.class, arg1x -> {
         this.method8(arg1x);
         this.method9(arg1x);
         this.method12(arg1x);
      });
      this.handle(EventServerTick.class, this::method7);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre.class, this::method13);
      this.handle(EventScreenChange.class, this::method14);
   }

   private void method1(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.Data data1) {
      if ((Boolean)this.field30.get()) {
         this.field49 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1()
            .method2()
            .method4()
            .method5(((Integer)this.field31.get()).intValue())
            .method7()
            .method2();
      }
   }

   private void method2(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.Data data1) {
      if ((Boolean)this.field32.get()) {
         if (data1.method1() != SkyblockMenuType.TERMINAL_MELODY) {
            this.field50 = false;
         } else {
            this.field50 = true;
            this.field51 = 0;
            Ref.method7().bridge$sendChatMessage((this.field39.get() ? "/pc " : "/ac ") + (String)this.field34.get());
            if ((Boolean)this.field33.get()) {
               this.field52 = 10;
            }
         }
      }
   }

   private void method3(TerminalActivateEvent data31) {
      if ((Boolean)this.field32.get()) {
         if (this.field50) {
            if (data31.method1() == Type.TERMINAL) {
               if (data31.method2().equals(this.field11.method5())) {
                  this.method13();
               }
            }
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if ((Boolean)this.field32.get()) {
         if (this.field50) {
            if (data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH().equals("You solved the puzzle!")) {
               this.method13();
            }
         }
      }
   }

   private void method13() {
      Ref.method7().bridge$sendChatMessage((this.field39.get() ? "/pc " : "/ac ") + (String)this.field38.get());
   }

   private void method6(EventSlotUpdate highlightimpl1) {
      if ((Boolean)this.field32.get()) {
         if (this.field8.method7() == SkyblockMenuType.TERMINAL_MELODY) {
            ItemStackBridge bridgeextension_42 = highlightimpl1.method3();
            if (bridgeextension_42 != null && bridgeextension_42.bridge$getItem() == Bridge.method28().method91()) {
               int number3 = highlightimpl1.getSlot() / 9;
               if (number3 > this.field51) {
                  this.field51 = number3;

                  String text4 = switch (number3) {
                     case 2 -> (String)this.field35.get();
                     case 3 -> (String)this.field36.get();
                     case 4 -> (String)this.field37.get();
                     default -> "";
                  };
                  if (!text4.isBlank()) {
                     Ref.method7().bridge$sendChatMessage((this.field39.get() ? "/pc " : "/ac ") + text4);
                  }
               }
            }
         }
      }
   }

   private void method7(EventServerTick highlightimpl91) {
      if ((Boolean)this.field32.get()) {
         if (this.field52 > 0) {
            if (--this.field52 <= 0) {
               Bridge5Extension_5 bridge5extension_52 = Ref.method7();
               if (bridge5extension_52 != null) {
                  Ref.method7().bridge$sendChatMessage("/sendcoords" + (this.field39.get() ? " party " : " all"));
               }
            }
         }
      }
   }

   private void method8(EventRenderSlot highlightimpl51) {
      if ((Boolean)this.field30.get()) {
         if (this.field49 != null && this.field49.get() > 0L) {
            highlightimpl51.cancel();
         }
      }
   }

   private void method9(EventRenderSlot highlightimpl51) {
      if ((Boolean)this.field25.get() && !this.field26.isKeyDown()) {
         if (!highlightimpl51.isCancelled()) {
            SkyblockMenuType highlighttype2 = this.field8.method7();
            if (highlighttype2 != null) {
               switch (highlighttype2) {
                  case TERMINAL_CORRECT_PANES:
                     this.method10(highlightimpl51);
                     break;
                  case TERMINAL_MELODY:
                     this.method11(highlightimpl51);
               }
            }
         }
      }
   }

   private void method10(EventRenderSlot highlightimpl51) {
      SlotBridge bridge3_182 = highlightimpl51.method5();
      if (bridge3_182 != null) {
         ItemStackBridge bridgeextension_43 = bridge3_182.bridge$getItemStack();
         if (bridgeextension_43 != null) {
            if (bridgeextension_43.bridge$getDisplayName().endsWith("On")) {
               highlightimpl51.cancel();
            }
         }
      }
   }

   private void method11(EventRenderSlot highlightimpl51) {
      int number2 = -1;
      GuiContainerBridge bridge5extension_33 = this.field9.method6();
      List list4 = bridge5extension_33.bridge$inventorySlots();

      for (int index5 = 50; index5 >= 0; index5--) {
         ItemStackBridge bridgeextension_46 = ((SlotBridge)list4.get(index5)).bridge$getItemStack();
         DyeColor type27 = SkyblockItemUtil.method5(bridgeextension_46);
         if (type27 == DyeColor.MAGENTA) {
            number2 = index5 % 9;
         } else if (type27 == DyeColor.LIME && number2 != -1) {
            highlightimpl51.setCancelled(index5 % 9 != number2);
            return;
         }
      }
   }

   private void method12(EventRenderSlot highlightimpl51) {
      if ((Boolean)this.field28.get()) {
         if (!highlightimpl51.isCancelled()) {
            if (highlightimpl51.method6() == 0) {
               SkyblockMenuType highlighttype2 = this.field8.method7();
               if (highlighttype2 != null && highlighttype2.isTerminalGui()) {
                  highlightimpl51.method2(2);
                  highlightimpl51.method3(ClickTypeBridge.CLONE);
               }
            }
         }
      }
   }

   private void method13(com.moonsworth.lunar.client.event.render.EventRenderTooltip.EventRenderTooltipPre data21) {
      if ((Boolean)this.field27.get()) {
         SkyblockMenuType highlighttype2 = this.field8.method7();
         if (highlighttype2 != null && highlighttype2.isTerminalGui()) {
            data21.cancel();
         }
      }
   }

   private void method14(EventScreenChange highlightimpl71) {
      if ((Boolean)this.field40.get()) {
         GuiScreenBridge bridge5extension62 = highlightimpl71.method1();
         if (bridge5extension62 instanceof GuiContainerBridge) {
            SkyblockMenuType highlighttype3 = this.field8.method7();
            if (highlighttype3 != null && highlighttype3.isTerminalGui()) {
               bridge5extension62.bridge$setInventoryScale((Integer)this.field41.get());
            }
         }
      }
   }

   public List<Framework7Extension> method9() {
      return List.of(this.field42, this.field43, this.field44, this.field45, this.field46, this.field47, this.field48);
   }

   public String getId() {
      return "SKYBLOCK_TERMINAL_SOLVERS";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field12, this.field13});
            arg1x.method9(new ClientOption[]{this.field21}).method3(() -> !(Boolean)this.field13.get());
            arg1x.method9(new ClientOption[]{this.field14, this.field16});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field15, arg1xx -> arg1xx.method9(new ClientOption[]{this.field29}));
            arg1x.method9(new ClientOption[]{this.field22}).method3(() -> !(Boolean)this.field16.get());
            arg1x.method9(new ClientOption[]{this.field17, this.field18, this.field19, this.field20});
            arg1x.method9(new ClientOption[]{this.field23})
               .method3(() -> !(Boolean)this.field22.get() && (!(Boolean)this.field13.get() || (Integer)this.field21.get() < 2));
            arg1x.method9(new ClientOption[]{this.field24})
               .method3(() -> !(Boolean)this.field22.get() && !(Boolean)this.field13.get() || (Integer)this.field21.get() < 3);
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field25, arg1xx -> arg1xx.method9(new ClientOption[]{this.field26}));
            arg1x.method9(new ClientOption[]{this.field27, this.field28});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field30, arg1xx -> arg1xx.method9(new ClientOption[]{this.field31}));
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field32,
               arg1xx -> arg1xx.method9(
                  new ClientOption[]{this.field33, this.field34, this.field35, this.field36, this.field37, this.field38, this.field39}
               )
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field40, arg1xx -> arg1xx.method9(new ClientOption[]{this.field41}));
         }
      );
   }

   @Generated
   public HighlightTypeListener method19() {
      return this.field8;
   }

   @Generated
   public ScreenTitleListener method21() {
      return this.field9;
   }

   @Generated
   public DungeonFloorListener method22() {
      return this.field10;
   }

   @Generated
   public LocalPlayerNameListener method23() {
      return this.field11;
   }

   @Generated
   public ToggleOption method24() {
      return this.field12;
   }

   @Generated
   public ToggleOption method25() {
      return this.field13;
   }

   @Generated
   public ToggleOption method26() {
      return this.field14;
   }

   @Generated
   public ToggleOption method27() {
      return this.field15;
   }

   @Generated
   public ToggleOption method28() {
      return this.field16;
   }

   @Generated
   public ToggleOption method29() {
      return this.field17;
   }

   @Generated
   public ToggleOption method30() {
      return this.field18;
   }

   @Generated
   public ToggleOption method34() {
      return this.field19;
   }

   @Generated
   public ColorOption method35() {
      return this.field20;
   }

   @Generated
   public IntegerOption method36() {
      return this.field21;
   }

   @Generated
   public ToggleOption method37() {
      return this.field22;
   }

   @Generated
   public ColorOption method38() {
      return this.field23;
   }

   @Generated
   public ColorOption method39() {
      return this.field24;
   }

   @Generated
   public ToggleOption method40() {
      return this.field25;
   }

   @Generated
   public ModifierKeybindOption method41() {
      return this.field26;
   }

   @Generated
   public ToggleOption method42() {
      return this.field27;
   }

   @Generated
   public ToggleOption method43() {
      return this.field28;
   }

   @Generated
   public ToggleOption method44() {
      return this.field29;
   }

   @Generated
   public ToggleOption method45() {
      return this.field30;
   }

   @Generated
   public IntegerOption method46() {
      return this.field31;
   }

   @Generated
   public ToggleOption method47() {
      return this.field32;
   }

   @Generated
   public ToggleOption method48() {
      return this.field33;
   }

   @Generated
   public TextOption method49() {
      return this.field34;
   }

   @Generated
   public TextOption method50() {
      return this.field35;
   }

   @Generated
   public TextOption method51() {
      return this.field36;
   }

   @Generated
   public TextOption method52() {
      return this.field37;
   }

   @Generated
   public TextOption method53() {
      return this.field38;
   }

   @Generated
   public ToggleOption method54() {
      return this.field39;
   }

   @Generated
   public ToggleOption method55() {
      return this.field40;
   }

   @Generated
   public IntegerOption method56() {
      return this.field41;
   }

   @Generated
   public SkyblockArrowAlign method57() {
      return this.field42;
   }

   @Generated
   public SkyblockClickInOrder method58() {
      return this.field43;
   }

   @Generated
   public SkyblockFirstLetter method59() {
      return this.field44;
   }

   @Generated
   public SkyblockMatchColors method60() {
      return this.field45;
   }

   @Generated
   public SkyblockSelectColor method61() {
      return this.field46;
   }

   @Generated
   public SkyblockSimonSays method62() {
      return this.field47;
   }

   @Generated
   public SkyblockTargetPractice method63() {
      return this.field48;
   }

   @Generated
   public HudTimer method64() {
      return this.field49;
   }

   @Generated
   public boolean method65() {
      return this.field50;
   }

   @Generated
   public int method66() {
      return this.field51;
   }

   @Generated
   public int method67() {
      return this.field52;
   }
}
