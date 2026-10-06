package com.moonsworth.lunar.client.mod.skyblock.terminalsolvers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.practice.TerminalSolverModule;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.Data;
import com.moonsworth.lunar.client.event.render.EventRenderHologram;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramText;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.apache.commons.lang3.math.NumberUtils;

public class SkyblockMatchColors extends TerminalSolverModule {
   private SkyblockMatchColors.Type field11;

   public SkyblockMatchColors(SkyblockTerminalSolvers skyblockterminalsolvers1, ToggleOption lightingextension4432) {
      super(SkyblockMenuType.TERMINAL_MATCH_COLOR);
      this.method3(ModTraits.field16, ChildModBinding.method4(false, skyblockterminalsolvers1));
      this.method3(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(EventRenderSlot.class, this::method3);
      this.handle(Data.class, this::method2);
      this.handle(EventRenderContainerSlotPost.class, this::method4);
      this.handle(EventRenderHologramItem.class, this::method5);
      this.handle(EventRenderHologramText.class, this::method5);
   }

   public String getId() {
      return "SKYBLOCK_MATCH_COLORS";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(Data data1) {
      this.field11 = null;
   }

   private void method3(EventRenderSlot highlightimpl51) {
      SkyblockTerminalSolvers skyblockterminalsolvers2 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockterminalsolvers2.method40().get() && this.isInGui() && !skyblockterminalsolvers2.method41().isKeyDown()) {
         if (this.field11 != null) {
            SlotBridge bridge3_183 = highlightimpl51.method5();
            if (bridge3_183 != null) {
               ItemStackBridge bridgeextension_44 = bridge3_183.bridge$getItemStack();
               if (bridgeextension_44 != null) {
                  SkyblockMatchColors.Type type5 = SkyblockMatchColors.Type.colorOf(SkyblockItemUtil.method5(bridgeextension_44));
                  if (this.field11.getDistance(type5) == 0) {
                     highlightimpl51.cancel();
                  }
               }
            }
         }
      }
   }

   private void method4(EventRenderContainerSlot highlightimpl151) {
      if (this.isInGui()) {
         GuiContainerBridge bridge5extension_32 = this.method13();
         List list3 = bridge5extension_32.bridge$inventorySlots();
         if (this.field11 == null) {
            int index4 = 0;
            int index5 = 0;
            int index6 = 0;
            int index7 = 0;
            int index8 = 0;

            for (int index9 = 12; index9 <= 32; index9++) {
               DyeColor type210 = SkyblockItemUtil.method5(((SlotBridge)list3.get(index9)).bridge$getItemStack());
               if (type210 != null) {
                  switch (type210) {
                     case ORANGE:
                        index5++;
                        break;
                     case YELLOW:
                        index6++;
                        break;
                     case BLUE:
                        index8++;
                        break;
                     case GREEN:
                        index7++;
                        break;
                     case RED:
                        index4++;
                  }
               }
            }

            int number11 = NumberUtils.max(new int[]{index4, index5, index6, index7, index8});
            if (number11 == 0) {
               this.field11 = null;
            } else if (number11 == index4) {
               this.field11 = SkyblockMatchColors.Type.RED;
            } else if (number11 == index5) {
               this.field11 = SkyblockMatchColors.Type.ORANGE;
            } else if (number11 == index6) {
               this.field11 = SkyblockMatchColors.Type.YELLOW;
            } else if (number11 == index7) {
               this.field11 = SkyblockMatchColors.Type.GREEN;
            } else {
               this.field11 = SkyblockMatchColors.Type.BLUE;
            }
         }
      }
   }

   private void method5(EventRenderHologram highlightbase1) {
      if (this.isInGui()) {
         if (this.field11 != null) {
            SlotBridge bridge3_182 = highlightbase1.method3();
            if (this.method3(bridge3_182, 45)) {
               DyeColor type23 = SkyblockItemUtil.method5(bridge3_182.bridge$getItemStack());
               if (type23 != null) {
                  SkyblockMatchColors.Type type4 = SkyblockMatchColors.Type.colorOf(type23);
                  if (type4 != null) {
                     SkyblockTerminalSolvers skyblockterminalsolvers5 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
                     int number6 = this.field11.getDistance(type4);
                     if (number6 != 0) {
                        if (highlightbase1 instanceof EventRenderHologramText data67) {
                           data67.method1(this.method6(number6));
                        }
                     } else if ((Boolean)skyblockterminalsolvers5.method34().get() && highlightbase1 instanceof EventRenderHologramItem data58) {
                        data58.method2(Bridge.method8().method41());
                     }
                  }
               }
            }
         }
      }
   }

   private TextComponent method6(int number1) {
      SkyblockTerminalSolvers skyblockterminalsolvers2 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      String text3;
      if ((Boolean)skyblockterminalsolvers2.method44().get()) {
         text3 = switch (number1) {
            case -2 -> ">>";
            case -1 -> ">";
            default -> number1 + "";
            case 1 -> "<";
            case 2 -> "<<";
         };
      } else {
         text3 = number1 + "";
      }

      return Component.text(text3);
   }

   private enum Type {
      RED,
      ORANGE,
      YELLOW,
      GREEN,
      BLUE;

      Type() {
      }

      private static SkyblockMatchColors.Type colorOf(DyeColor type20) {
         if (type20 == null) {
            return null;
         }

         try {
            return valueOf(type20.name());
         } catch (IllegalArgumentException illegalargumentexception2) {
            return null;
         }
      }

      private int getDistance(SkyblockMatchColors.Type type1) {
         if (type1 == null) {
            return 0;
         }

         int number2 = this.ordinal() - type1.ordinal();
         if (number2 >= 3) {
            number2 -= 5;
         } else if (number2 <= -3) {
            number2 += 5;
         }

         return number2;
      }
   }
}
