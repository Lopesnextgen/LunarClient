package com.moonsworth.lunar.client.mod.misc.hypixelbedwars;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudRowElement;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.ui.hud.HudRowLayout;
import com.moonsworth.lunar.client.ui.hud.HudRow;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import com.moonsworth.lunar.client.config.option.ConfigRangeBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.Nullable;

public class HypixelBedwarsResourceCounter extends AbstractFeature {
   private final ScreenTitleListener screenTitleListener = (ScreenTitleListener)this.method12(ScreenTitleListener.class);
   private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor(
      new DefaultThreadFactory("lunar-bedwars-resource-counter-thread", true)
   );
   private static final ItemStackBridge IRON_ITEM = Bridge.method8().method38(Bridge.method28().method26());
   private static final ItemStackBridge GOLD_ITEM = Bridge.method8().method38(Bridge.method28().method27());
   private static final ItemStackBridge DIAMOND_ITEM = Bridge.method8().method38(Bridge.method28().method28());
   private static final ItemStackBridge EMERALD_ITEM = Bridge.method8().method38(Bridge.method28().method29());
   private final ToggleOption textShadow = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("textShadow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption background = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("background").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").method31();
   private final ToggleOption autoAlign = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("autoAlign").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption borderThickness = (FloatOption)((Data)((Data)OptionFactory.method2("borderThickness").CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final EnumOption<HudRowAlignment> alignment = (EnumOption<HudRowAlignment>)OptionFactory.method10("alignment", HudRowAlignment.LEFT)
      .method31();
   private final ToggleOption showTitle = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTitle").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption useResourceIcons = (ToggleOption)OptionFactory.method7("useResourceIcons").method31();
   private final ToggleOption showResourcesInEnderchest = (ToggleOption)OptionFactory.method7("showResourcesInEnderchest").method31();
   private final ToggleOption showIron = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showIron").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showGold = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showGold").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showDiamonds = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showDiamonds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showEmeralds = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showEmeralds").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption titleColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "titleColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption textColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "textColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption numberColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "numberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ColorOption dividerColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "dividerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-8355712))
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
   private boolean active;
   private int ironCount;
   private int goldCount;
   private int diamondCount;
   private int emeraldCount;
   private int enderIronCount;
   private int enderGoldCount;
   private int enderDiamondCount;
   private int enderEmeraldCount;

   public HypixelBedwarsResourceCounter(HypixelBedwars hypixelbedwars1) {
      super(true);
      this.registerOptions(ModTraits.field18, ConfigRangeBuilder::method16);
      this.registerOptions(ModTraits.field16, ChildModBinding.method3(hypixelbedwars1));
      this.registerOptions(ModTraits.field1, this.createElement());
      field9.submit(new Runnable() {
         @Override
         public void run() {
            HypixelBedwarsResourceCounter.this.registerOptions(Ref.method3().bridge$getCurrentScreen());
            HypixelBedwarsResourceCounter.EXECUTOR.schedule(this, 500L, TimeUnit.MILLISECONDS);
         }
      });
      this.handle(EventScreenChange.class, this::onScreenChange);
   }

   public String getId() {
      return "HYPIXEL_BEDWARS_RESOURCE_COUNTER_CHILD";
   }

   private void onScreenChange(EventScreenChange highlightimpl71) {
      if (highlightimpl71.method1() == null) {
         this.registerOptions(this.mc.bridge$getCurrentScreen());
      } else {
         field9.schedule(() -> this.registerOptions(Ref.method3().bridge$getCurrentScreen()), 100L, TimeUnit.MILLISECONDS);
      }
   }

   private void registerOptions(GuiScreenBridge bridge5extension61) {
      if (this.active) {
         this.ironCount = this.goldCount = this.diamondCount = this.emeraldCount = 0;

         try {
            if (Ref.method7() != null) {
               for (ItemStackBridge bridgeextension_43 : Ref.method7().bridge$getInventory().bridge$getMainInventory()) {
                  if (bridgeextension_43 != null) {
                     if (bridgeextension_43.bridge$getItem() == Bridge.method28().method26()) {
                        this.ironCount = this.ironCount + bridgeextension_43.bridge$getStackSize();
                     } else if (bridgeextension_43.bridge$getItem() == Bridge.method28().method27()) {
                        this.goldCount = this.goldCount + bridgeextension_43.bridge$getStackSize();
                     } else if (bridgeextension_43.bridge$getItem() == Bridge.method28().method28()) {
                        this.diamondCount = this.diamondCount + bridgeextension_43.bridge$getStackSize();
                     } else if (bridgeextension_43.bridge$getItem() == Bridge.method28().method29()) {
                        this.emeraldCount = this.emeraldCount + bridgeextension_43.bridge$getStackSize();
                     }
                  }
               }

               if (bridge5extension61 instanceof GuiContainerBridge bridge5extension_38) {
                  String text9 = this.screenTitleListener.method5();
                  if (text9 != null && (text9.equals("Ender Chest") || text9.equals("container.enderchest"))) {
                     this.enderIronCount = this.enderGoldCount = this.enderDiamondCount = this.enderEmeraldCount = 0;

                     for (SlotBridge bridge3_185 : bridge5extension_38.bridge$inventorySlots()) {
                        if (bridge3_185 != null) {
                           ItemStackBridge bridgeextension_46 = bridge3_185.bridge$getItemStack();
                           if (bridgeextension_46 != null) {
                              if (bridgeextension_46.bridge$getItem() == Bridge.method28().method26()) {
                                 this.enderIronCount = this.enderIronCount + bridgeextension_46.bridge$getStackSize();
                              } else if (bridgeextension_46.bridge$getItem() == Bridge.method28().method27()) {
                                 this.enderGoldCount = this.enderGoldCount + bridgeextension_46.bridge$getStackSize();
                              } else if (bridgeextension_46.bridge$getItem() == Bridge.method28().method28()) {
                                 this.enderDiamondCount = this.enderDiamondCount + bridgeextension_46.bridge$getStackSize();
                              } else if (bridgeextension_46.bridge$getItem() == Bridge.method28().method29()) {
                                 this.enderEmeraldCount = this.enderEmeraldCount + bridgeextension_46.bridge$getStackSize();
                              }
                           }
                        }
                     }

                     this.enderIronCount = this.enderIronCount - this.ironCount;
                     if (this.enderIronCount < 0) {
                        this.enderIronCount = 0;
                     }

                     this.enderGoldCount = this.enderGoldCount - this.goldCount;
                     if (this.enderGoldCount < 0) {
                        this.enderGoldCount = 0;
                     }

                     this.enderDiamondCount = this.enderDiamondCount - this.diamondCount;
                     if (this.enderDiamondCount < 0) {
                        this.enderDiamondCount = 0;
                     }

                     this.enderEmeraldCount = this.enderEmeraldCount - this.emeraldCount;
                     if (this.enderEmeraldCount < 0) {
                        this.enderEmeraldCount = 0;
                     }
                  }
               }
            }
         } catch (Exception exception7) {
         }
      }
   }

   public void setActive(@Nullable HypixelLocation rewindhandlers21) {
      HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if (hypixelbedwars2.method38()) {
         HypixelLocation rewindhandlers23 = rewindhandlers21 == null ? HypixelLocationListener.field7.method7() : rewindhandlers21;
         this.active = hypixelbedwars2.method35() && rewindhandlers23 != null && rewindhandlers23.field4 != null;
         if (!this.active) {
            this.enderIronCount = this.enderGoldCount = this.enderDiamondCount = this.enderEmeraldCount = 0;
         }
      }
   }

   protected ModDetails createDetails() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "hudDisplayOptions",
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.textShadow});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.background,
               arg1xx -> arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.border, arg1xxx -> arg1xxx.method9(new ClientOption[]{this.borderThickness})
               )
            );
            arg1x.method9(new ClientOption[]{this.autoAlign});
            arg1x.method9(new ClientOption[]{this.alignment}).method3(this.autoAlign::get);
         }
      );
      lightingextension231.method1(
         "renderOptions",
         arg1x -> arg1x.method9(
            new ClientOption[]{this.showTitle, this.useResourceIcons, this.showResourcesInEnderchest, this.showIron, this.showGold, this.showDiamonds, this.showEmeralds}
         )
      );
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> {
         arg1x.method9(new ClientOption[]{this.titleColor, this.textColor, this.numberColor, this.dividerColor});
         arg1x.method9(new ClientOption[]{this.backgroundColor}).method3(() -> !(Boolean)this.background.get());
         arg1x.method9(new ClientOption[]{this.borderColor}).method3(() -> !(Boolean)this.border.get());
      });
   }

   private HudRow createRow(String text1, ItemStackBridge bridgeextension_42, int number3, int number4, int number5) {
      if ((Boolean)this.showResourcesInEnderchest.get()) {
         return this.useResourceIcons.get()
            ? HudRowLayout.method4(
               number3,
               new HudRow[]{
                  HudRowLayout.method6(bridgeextension_42),
                  HudRowLayout.method7(4),
                  HudRowLayout.method5(number4 + "", this.numberColor, (Boolean)this.textShadow.get()),
                  HudRowLayout.method5(" + ", this.dividerColor, (Boolean)this.textShadow.get()),
                  HudRowLayout.method5(number5 + "", this.numberColor, (Boolean)this.textShadow.get())
               }
            )
            : HudRowLayout.method4(
               number3,
               new HudRow[]{
                  HudRowLayout.method5(text1 + ": ", this.textColor, (Boolean)this.textShadow.get()),
                  HudRowLayout.method5(number4 + "", this.numberColor, (Boolean)this.textShadow.get()),
                  HudRowLayout.method5(" + ", this.dividerColor, (Boolean)this.textShadow.get()),
                  HudRowLayout.method5(number5 + "", this.numberColor, (Boolean)this.textShadow.get())
               }
            );
      } else {
         return this.useResourceIcons.get()
            ? HudRowLayout.method4(number3, new HudRow[]{HudRowLayout.method6(bridgeextension_42), HudRowLayout.method7(4), HudRowLayout.method5(number4 + "", this.numberColor, (Boolean)this.textShadow.get())})
            : HudRowLayout.method4(
               number3,
               new HudRow[]{
                  HudRowLayout.method5(text1 + ": ", this.textColor, (Boolean)this.textShadow.get()), HudRowLayout.method5(number4 + "", this.numberColor, (Boolean)this.textShadow.get())
               }
            );
      }
   }

   private HudRowElement createElement() {
      return new HudRowElement(0.0F, 0.0F, HudAnchor.TOP_RIGHT) {
         public void registerOptions(MixinHelper_4 mixinhelper_41, float value2, float value3, boolean flag4, List<HudRow> list5) {
            this.registerOptions(
               mixinhelper_41,
               value2,
               value3,
               (Boolean)HypixelBedwarsResourceCounter.this.autoAlign.get(),
               (HudRowAlignment)HypixelBedwarsResourceCounter.this.alignment.get(),
               (Boolean)HypixelBedwarsResourceCounter.this.background.get(),
               HypixelBedwarsResourceCounter.this.backgroundColor,
               (Boolean)HypixelBedwarsResourceCounter.this.border.get(),
               (Float)HypixelBedwarsResourceCounter.this.borderThickness.get(),
               HypixelBedwarsResourceCounter.this.borderColor
            );
         }

         @Nullable
         protected List<HudRow> buildRows(boolean flag1) {
            ArrayList list2 = new ArrayList();
            byte number3 = 0;
            byte number4 = 0;
            switch (HudRowLayout.method3(
               (Boolean)HypixelBedwarsResourceCounter.this.autoAlign.get(),
               this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH(),
               (HudRowAlignment)HypixelBedwarsResourceCounter.this.alignment.get()
            )) {
               case LEFT:
                  number4 = 4;
               default:
                  if ((Boolean)HypixelBedwarsResourceCounter.this.showTitle.get()) {
                     list2.add(
                        HudRowLayout.method4(
                           number3,
                           new HudRow[]{
                              HudRowLayout.method5(
                                 "§lResources", HypixelBedwarsResourceCounter.this.titleColor, (Boolean)HypixelBedwarsResourceCounter.this.textShadow.get()
                              )
                           }
                        )
                     );
                  }

                  if ((Boolean)HypixelBedwarsResourceCounter.this.showIron.get()) {
                     list2.add(
                        HypixelBedwarsResourceCounter.this.createRow(
                           "Iron",
                           HypixelBedwarsResourceCounter.IRON_ITEM,
                           number4,
                           flag1 ? 37 : HypixelBedwarsResourceCounter.this.ironCount,
                           flag1 ? 32 : HypixelBedwarsResourceCounter.this.enderIronCount
                        )
                     );
                  }

                  if ((Boolean)HypixelBedwarsResourceCounter.this.showGold.get()) {
                     list2.add(
                        HypixelBedwarsResourceCounter.this.createRow(
                           "Gold",
                           HypixelBedwarsResourceCounter.GOLD_ITEM,
                           number4,
                           flag1 ? 12 : HypixelBedwarsResourceCounter.this.goldCount,
                           flag1 ? 1 : HypixelBedwarsResourceCounter.this.enderGoldCount
                        )
                     );
                  }

                  if ((Boolean)HypixelBedwarsResourceCounter.this.showDiamonds.get()) {
                     list2.add(
                        HypixelBedwarsResourceCounter.this.createRow(
                           "Diamonds",
                           HypixelBedwarsResourceCounter.DIAMOND_ITEM,
                           number4,
                           flag1 ? 4 : HypixelBedwarsResourceCounter.this.diamondCount,
                           flag1 ? 0 : HypixelBedwarsResourceCounter.this.enderDiamondCount
                        )
                     );
                  }

                  if ((Boolean)HypixelBedwarsResourceCounter.this.showEmeralds.get()) {
                     list2.add(
                        HypixelBedwarsResourceCounter.this.createRow(
                           "Emeralds",
                           HypixelBedwarsResourceCounter.EMERALD_ITEM,
                           number4,
                           flag1 ? 0 : HypixelBedwarsResourceCounter.this.emeraldCount,
                           flag1 ? 4 : HypixelBedwarsResourceCounter.this.enderEmeraldCount
                        )
                     );
                  }

                  return list2;
            }
         }

         public boolean shouldRender(boolean flag1) {
            HypixelBedwars hypixelbedwars2 = (HypixelBedwars)((ChildModBinding)HypixelBedwarsResourceCounter.this.method7(ModTraits.field16))
               .method1();
            if (flag1 ? hypixelbedwars2.method35() : HypixelBedwarsResourceCounter.this.active) {
               return (
                     (Boolean)HypixelBedwarsResourceCounter.this.showTitle.get()
                        || (Boolean)HypixelBedwarsResourceCounter.this.showIron.get()
                        || (Boolean)HypixelBedwarsResourceCounter.this.showGold.get()
                        || (Boolean)HypixelBedwarsResourceCounter.this.showDiamonds.get()
                        || (Boolean)HypixelBedwarsResourceCounter.this.showEmeralds.get()
                  )
                  && super.shouldRender(flag1);
            }

            this.method14(0.0F, 0.0F);
            return false;
         }
      };
   }
}
