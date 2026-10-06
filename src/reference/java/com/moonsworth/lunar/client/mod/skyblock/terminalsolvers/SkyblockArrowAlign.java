package com.moonsworth.lunar.client.mod.skyblock.terminalsolvers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityItemFrameBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.util.math.Direction2D;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.practice.TerminalSolverModule;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.Generated;

public class SkyblockArrowAlign extends TerminalSolverModule {
   private static final AxisAlignedBBBridge field11 = AxisAlignedBBBridge.method2(-2.0, 120.0, 75.0, -1.0, 125.0, 80.0);
   private final List<SkyblockArrowAlign.ArrowFrame> field12 = new ArrayList<>();
   private boolean[][] field13 = new boolean[5][5];
   private int field14 = 0;

   public SkyblockArrowAlign(SkyblockTerminalSolvers skyblockterminalsolvers1, ToggleOption lightingextension4432) {
      super(null);
      this.method9(ModTraits.field16, ChildModBinding.method4(false, skyblockterminalsolvers1));
      this.method9(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.method2(this::method14);
      this.handle(EventWorldChange.class, arg1x -> this.method14());
      this.handle(HudRenderLegacyEvent.class, this::method7);
      this.handle(EventSecond.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.entity.EventEntityInteract.Interact.class, arg1x -> {
         this.method4(arg1x);
         this.method3(arg1x);
      });
      this.handle(EventEntitySpawn.class, this::method5);
   }

   public String getId() {
      return "SKYBLOCK_ARROW_ALIGN";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventSecond highlightimpl41) {
      if (Ref.method8() != null) {
         if (Ref.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(-3.0, 122.0, 77.0) > 400.0) {
            this.method14();
         } else {
            if (!this.field12.isEmpty()) {
               boolean flag2 = false;

               for (SkyblockArrowAlign.ArrowFrame data24 : this.field12) {
                  if (data24.method4().bridge$isRemoved()) {
                     flag2 = true;
                     break;
                  }
               }

               if (!flag2) {
                  return;
               }

               this.method14();
            }

            boolean flag11 = false;
            EntityItemFrameBridge[][] items12 = new EntityItemFrameBridge[5][5];

            for (BridgeExtension bridgeextension6 : Ref.method8().bridge$getEntities(field11, arg0 -> arg0 instanceof EntityItemFrameBridge)) {
               EntityItemFrameBridge bridgeextension57 = (EntityItemFrameBridge)bridgeextension6;
               ItemStackBridge bridgeextension_48 = bridgeextension57.bridge$getItemStack();
               if (bridgeextension_48 != null && (bridgeextension_48.bridge$getItem() == Bridge.method28().method11() || SkyblockItemUtil.method6(bridgeextension_48) != null)) {
                  if (SkyblockItemUtil.method6(bridgeextension_48) == DyeColor.LIME) {
                     this.field14++;
                  }

                  int index9 = 124 - (int)Math.floor(bridgeextension57.bridge$getPosY());
                  int index10 = 79 - (int)Math.floor(bridgeextension57.bridge$getPosZ());
                  items12[index9][index10] = bridgeextension57;
                  flag11 = true;
               }
            }

            if (flag11) {
               for (int index14 = 0; index14 < items12.length; index14++) {
                  for (int index15 = 0; index15 < items12[index14].length; index15++) {
                     if (items12[index14][index15] != null && SkyblockItemUtil.method6(items12[index14][index15].bridge$getItemStack()) == DyeColor.LIME) {
                        this.field12.addAll(this.method6(items12, index14, index15));
                     }
                  }
               }
            }
         }
      }
   }

   private void method3(com.moonsworth.lunar.client.event.entity.EventEntityInteract.Interact data1) {
      if (!data1.isCancelled()) {
         if (!this.field12.isEmpty()) {
            if (data1.method1() instanceof EntityItemFrameBridge bridgeextension52) {
               SkyblockArrowAlign.ArrowFrame data24 = this.method9(bridgeextension52);
               if (data24 != null) {
                  data24.method1();
               }
            }
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.entity.EventEntityInteract.Interact data1) {
      SkyblockTerminalSolvers skyblockterminalsolvers2 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockterminalsolvers2.method40().get() && !skyblockterminalsolvers2.method41().isKeyDown()) {
         if (!this.field12.isEmpty()) {
            if (data1.method1() instanceof EntityItemFrameBridge bridgeextension53) {
               SkyblockArrowAlign.ArrowFrame data25 = this.method9(bridgeextension53);
               if (data25 != null) {
                  if (data25.method3() == 0) {
                     data1.setCancelled(true);
                  }
               }
            }
         }
      }
   }

   private void method5(EventEntitySpawn highlightimpl6_21) {
      if (!this.field12.isEmpty()) {
         if (highlightimpl6_21.field1 instanceof EntityItemFrameBridge bridgeextension52) {
            SkyblockArrowAlign.ArrowFrame data24 = this.method9(bridgeextension52);
            if (data24 != null) {
               data24.update();
            }
         }
      }
   }

   private ArrayList<SkyblockArrowAlign.ArrowFrame> method6(EntityItemFrameBridge[][] items1, int number2, int number3) {
      LinkedList list4 = new LinkedList();
      ArrayList list5 = new ArrayList();
      ArrayList list6 = new ArrayList();
      list6.add(new SkyblockArrowAlign.Data(number2, number3, null));
      list4.add(list6);

      while (!list4.isEmpty()) {
         ArrayList list7 = (ArrayList)list4.remove();
         SkyblockArrowAlign.Data data8 = (SkyblockArrowAlign.Data)list7.get(list7.size() - 1);

         for (Direction2D fovtype212 : Direction2D.values()) {
            if (fovtype212 != data8.method3()) {
               int index13 = data8.method1() - fovtype212.getJ();
               int index14 = data8.method2() + fovtype212.getI();
               if (index13 >= 0 && index13 < 5 && index14 >= 0 && index14 < 5) {
                  EntityItemFrameBridge bridgeextension515 = items1[index13][index14];
                  if (bridgeextension515 != null) {
                     ItemStackBridge bridgeextension_416 = bridgeextension515.bridge$getItemStack();
                     if (bridgeextension_416 != null && !bridgeextension_416.bridge$isEmpty()) {
                        ItemBridge bridge6_417 = bridgeextension_416.bridge$getItem();
                        if (bridge6_417 == Bridge.method28().method11()) {
                           ArrayList list24 = new ArrayList(list7);
                           list24.add(new SkyblockArrowAlign.Data(index13, index14, fovtype212.getOpposite()));
                           list4.add(list24);
                        } else if (SkyblockItemUtil.method6(bridgeextension_416) == DyeColor.RED) {
                           for (int index18 = 1; index18 < list7.size() - 1; index18++) {
                              SkyblockArrowAlign.Data data19 = (SkyblockArrowAlign.Data)list7.get(index18);
                              int index20 = data19.method1();
                              int index21 = data19.method2();
                              if (!this.field13[index20][index21]) {
                                 SkyblockArrowAlign.ArrowFrame data222 = new SkyblockArrowAlign.ArrowFrame(
                                    items1[index20][index21], ((SkyblockArrowAlign.Data)list7.get(index18 + 1)).method3().getOpposite()
                                 );
                                 list5.add(data222);
                                 this.field13[index20][index21] = true;
                              }
                           }

                           int index23 = data8.method1();
                           int index25 = data8.method2();
                           if (!this.field13[index23][index25]) {
                              SkyblockArrowAlign.ArrowFrame data226 = new SkyblockArrowAlign.ArrowFrame(items1[index23][index25], fovtype212);
                              list5.add(data226);
                              this.field13[index23][index25] = true;
                           }

                           if (this.field14 != 1) {
                              return list5;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return list5;
   }

   private void method7(HudRenderLegacyEvent highlightimpl21) {
      EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
      AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
      bridgeextension_93.push();
      bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());

      for (SkyblockArrowAlign.ArrowFrame data25 : this.field12) {
         if (data25.method5() != null) {
            int number6 = data25.method3();
            if (number6 > 0) {
               WorldRenderUtils.drawString(
                  highlightimpl21.method3(),
                  number6 + "",
                  data25.method4().bridge$getPosX() + 0.2,
                  data25.method4().bridge$getPosY() + 0.2,
                  data25.method4().bridge$getPosZ(),
                  -1,
                  true
               );
            }
         }
      }

      bridgeextension_93.pop();
   }

   private void method14() {
      if (!this.field12.isEmpty()) {
         this.field12.clear();
         this.field13 = new boolean[5][5];
         this.field14 = 0;
      }
   }

   private SkyblockArrowAlign.ArrowFrame method9(EntityItemFrameBridge bridgeextension51) {
      for (SkyblockArrowAlign.ArrowFrame data23 : this.field12) {
         if (data23.method4() == bridgeextension51) {
            return data23;
         }
      }

      return null;
   }

   private class Data {
      private final int field1;
      private final int field2;
      private final Direction2D field3;

      private Data(int number1, int number2, Direction2D fovtype23) {
         this.field1 = number1;
         this.field2 = number2;
         this.field3 = fovtype23;
      }

      public int method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public Direction2D method3() {
         return this.field3;
      }
   }

   private static class ArrowFrame {
      private final EntityItemFrameBridge field1;
      private final Direction2D field2;
      private int field3;
      private int field4;

      public ArrowFrame(EntityItemFrameBridge bridgeextension51, Direction2D fovtype22) {
         this.field1 = bridgeextension51;
         this.field2 = fovtype22;
         this.field4 = bridgeextension51.bridge$getRotation();
      }

      public void method1() {
         this.field3++;
      }

      public void update() {
         int number1 = this.method2(this.field1.bridge$getRotation() - this.field4);
         this.field3 -= number1;
         this.field4 = this.field1.bridge$getRotation();
      }

      private int method2(int number1) {
         while (number1 < 0) {
            number1 += 8;
         }

         return number1 % 8;
      }

      public int method3() {
         int number1 = switch (this.field2) {
            case UP -> 7 - this.field1.bridge$getRotation();
            case DOWN -> this.method2(3 - this.field1.bridge$getRotation());
            case LEFT -> this.method2(5 - this.field1.bridge$getRotation());
            case RIGHT -> this.method2(1 - this.field1.bridge$getRotation());
            default -> throw new IncompatibleClassChangeError();
         };
         return number1 - this.field3;
      }

      @Generated
      public EntityItemFrameBridge method4() {
         return this.field1;
      }

      @Generated
      public Direction2D method5() {
         return this.field2;
      }
   }
}
