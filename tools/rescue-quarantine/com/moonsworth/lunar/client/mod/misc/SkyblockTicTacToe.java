package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge5_4;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension5;
import com.moonsworth.lunar.bridge.BridgeExtension_4;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework3;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers.Data10;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl12;
import com.moonsworth.lunar.client.highlight.fishing.HighlightImpl6_2;
import com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl2;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import lombok.Generated;
import org.joml.Vector3i;

public class SkyblockTicTacToe extends Framework7Extension2 {
   private final int field8 = 114;
   private final int field9 = 33;
   private final Map<Vector3i, SkyblockTicTacToe.Type> field10 = new HashMap<>();
   private final Vector3i field11 = new Vector3i(8, 70, 17);
   private SkyblockTicTacToe.Data field12 = null;
   private Vector3i field13;

   public SkyblockTicTacToe(SkyblockDungeonPuzzles var1, LightingExtension443 var2) {
      super(true);
      this.method9(Framework.field16, Framework4.method4(false, var1));
      this.method9(Framework.field6, Framework3.method7(var2));
      this.method6(this::method13);
      this.handle(Data10.class, var1x -> this.method13());
      this.handle(HighlightImpl6_2.class, var1x -> this.method5(var1x.field1));
      this.handle(HighlightImpl2.class, this::method7);
      this.handle(HighlightImpl12.class, this::method6);
   }

   public String getId() {
      return "SKYBLOCK_TIC_TAC_TOE";
   }

   protected void method1(boolean var1) {
   }

   public void method3(boolean var1) {
      if (var1) {
         SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
         var2.method13();
      }
   }

   public void method3(Holograms4Iterator var1) {
      this.field12 = new SkyblockTicTacToe.Data(((Holograms3)var1.method23().get()).method4(this.field11), ((Holograms3)var1.method23().get()).method28());
      Optional var2 = this.method9(this.field12);
      var2.ifPresent(var2x -> {
         SkyblockTicTacToe.Type var3 = this.method12(this.field12.field4);
         if (var3 == SkyblockTicTacToe.Type.O) {
            this.field13 = ((Holograms3)var1.method23().get()).method4(new Vector3i(this.field11).add(0, (Integer)var2x.second(), -(Integer)var2x.first()));
         } else {
            this.field13 = null;
         }
      });
   }

   private void method13() {
      this.field12 = null;
      this.field13 = null;
      this.field10.clear();
   }

   private void method5(BridgeExtension var1) {
      if (Click3.method2() == Gui2Extension3.DUNGEON) {
         if (var1 instanceof BridgeExtension5 var2) {
            if (var2.bridge$getItemStack().bridge$getItem() instanceof Bridge5_4 var3) {
               Itemcounter2_3 var13 = var3.bridge$getMapData(var2.bridge$getItemStack(), ThreadModuleDump63.method8());
               if (var13 != null) {
                  byte[] var5 = var13.bridge$getColors();
                  if (var5.length == 16384) {
                     byte var6 = var5[8256];
                     Horsestats20 var7 = var2.bridge$getHangingPosition();
                     Vector3i var8 = new Vector3i(var7.bridge$getX(), var7.bridge$getY(), var7.bridge$getZ());
                     if (var6 == 114) {
                        this.field10.put(var8, SkyblockTicTacToe.Type.X);
                     } else if (var6 == 33) {
                        this.field10.put(var8, SkyblockTicTacToe.Type.O);
                     }

                     SkyblockDungeonPuzzles var9 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
                     Holograms4Iterator var10 = var9.method47();
                     if (var10 != null && var10.method25() == HologramsType8.TIC_TAC_TOE) {
                        if (this.field12 != null) {
                           Pair var11 = this.method8(var8, this.field12.field2, this.field12.field3);
                           if (var11 != null) {
                              this.field12.field4[var11.first()][var11.second()] = this.field10.get(var8);
                              Optional var12 = this.method9(this.field12);
                              var12.ifPresent(
                                 var2x -> {
                                    if (this.method12(this.field12.field4) == SkyblockTicTacToe.Type.O) {
                                       this.field13 = ((Holograms3)var10.method23().get())
                                          .method4(new Vector3i(this.field11).add(0, (Integer)var2x.second(), -(Integer)var2x.first()));
                                    } else {
                                       this.field13 = null;
                                    }
                                 }
                              );
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method6(HighlightImpl12 var1) {
      if (Click3.method2() == Gui2Extension3.DUNGEON) {
         if (var1.method1() instanceof BridgeExtension5 var2) {
            BridgeExtension_4 var6 = var2.bridge$getItemStack();
            if (var6 != null) {
               if (var6.bridge$getItem() instanceof Bridge5_4) {
                  Horsestats20 var4 = var2.bridge$getHangingPosition();
                  Vector3i var5 = new Vector3i(var4.bridge$getX(), var4.bridge$getY(), var4.bridge$getZ());
                  this.field10.remove(var5);
               }
            }
         }
      }
   }

   private void method7(HighlightImpl2 var1) {
      SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.TIC_TAC_TOE) {
         if (this.field12 != null && this.field13 != null) {
            Bridge2_43 var4 = ThreadModuleDump63.method13();
            BridgeExtension_9 var5 = var1.method3();
            var5.push();
            var5.translate(-var4.bridge$renderPosX(), -var4.bridge$renderPosY(), -var4.bridge$renderPosZ());
            Click.method2(var5, this.field13, 570490624);
            var5.pop();
         }
      }
   }

   private Pair<Integer, Integer> method8(Vector3i var1, Vector3i var2, HologramsType_3 var3) {
      int var4 = var1.y() - var2.y();
      if (var4 >= 0 && var4 <= 3) {
         switch (var3) {
            case NORTH:
            case SOUTH:
               if (var1.z() != var2.z()) {
                  return null;
               }

               int var6 = Math.abs(var1.x() - var2.x());
               return Pair.of(var6, var4);
            case EAST:
            case WEST:
               if (var1.x() != var2.x()) {
                  return null;
               }

               int var5 = Math.abs(var1.z() - var2.z());
               return Pair.of(var5, var4);
            default:
               return null;
         }
      } else {
         return null;
      }
   }

   private Optional<Pair<Integer, Integer>> method9(SkyblockTicTacToe.Data var1) {
      SkyblockTicTacToe.Type[][] var2 = var1.field4;
      SkyblockTicTacToe.Type var3 = this.method12(var2);
      int var4 = var3 == SkyblockTicTacToe.Type.X ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      Pair var5 = null;

      for (int var6 = 0; var6 < var2.length; var6++) {
         for (int var7 = 0; var7 < var2[var6].length; var7++) {
            if (var2[var6][var7] == null) {
               var2[var6][var7] = var3;
               int var8 = this.method10(var2, var3.getNext());
               var2[var6][var7] = null;
               boolean var9 = var3 == SkyblockTicTacToe.Type.X && var8 > var4 || var3 == SkyblockTicTacToe.Type.O && var8 < var4;
               if (var9) {
                  var4 = var8;
                  var5 = Pair.of(var6, var7);
               }
            }
         }
      }

      return var5 != null ? Optional.of(var5) : Optional.empty();
   }

   private int method10(SkyblockTicTacToe.Type[][] var1, SkyblockTicTacToe.Type var2) {
      SkyblockTicTacToe.Type2 var3 = this.method11(var1, var2);
      switch (var3) {
         case WIN_X:
            return 1;
         case WIN_O:
            return -1;
         case DRAW:
            return 0;
         default:
            int var4 = var2 == SkyblockTicTacToe.Type.X ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int var5 = 0; var5 < var1.length; var5++) {
               for (int var6 = 0; var6 < var1[var5].length; var6++) {
                  if (var1[var5][var6] == null) {
                     var1[var5][var6] = var2;
                     int var7 = this.method10(var1, var2.getNext());
                     var1[var5][var6] = null;
                     boolean var8 = var2 == SkyblockTicTacToe.Type.X && var7 > var4 || var2 == SkyblockTicTacToe.Type.O && var7 < var4;
                     if (var8) {
                        var4 = var7;
                     }
                  }
               }
            }

            return var4;
      }
   }

   private SkyblockTicTacToe.Type2 method11(SkyblockTicTacToe.Type[][] var1, SkyblockTicTacToe.Type var2) {
      int var3 = var1.length;
      SkyblockTicTacToe.Type var4 = var2.getNext();

      for (int var5 = 0; var5 < var3; var5++) {
         for (int var6 = 0; var6 < var3; var6++) {
            SkyblockTicTacToe.Type var7 = var1[var5][var6];
            if (var7 != var4) {
               break;
            }

            if (var6 == var3 - 1) {
               return var4 == SkyblockTicTacToe.Type.X ? SkyblockTicTacToe.Type2.WIN_X : SkyblockTicTacToe.Type2.WIN_O;
            }
         }
      }

      for (int var8 = 0; var8 < var3; var8++) {
         for (int var12 = 0; var12 < var3; var12++) {
            SkyblockTicTacToe.Type var16 = var1[var12][var8];
            if (var16 != var4) {
               break;
            }

            if (var12 == var3 - 1) {
               return var4 == SkyblockTicTacToe.Type.X ? SkyblockTicTacToe.Type2.WIN_X : SkyblockTicTacToe.Type2.WIN_O;
            }
         }
      }

      for (int var9 = 0; var9 < var3; var9++) {
         SkyblockTicTacToe.Type var13 = var1[var9][var9];
         if (var13 != var4) {
            break;
         }

         if (var9 == var3 - 1) {
            return var4 == SkyblockTicTacToe.Type.X ? SkyblockTicTacToe.Type2.WIN_X : SkyblockTicTacToe.Type2.WIN_O;
         }
      }

      for (int var10 = 0; var10 < var3; var10++) {
         SkyblockTicTacToe.Type var14 = var1[var10][var3 - 1 - var10];
         if (var14 != var4) {
            break;
         }

         if (var10 == var3 - 1) {
            return var4 == SkyblockTicTacToe.Type.X ? SkyblockTicTacToe.Type2.WIN_X : SkyblockTicTacToe.Type2.WIN_O;
         }
      }

      boolean var11 = false;

      for (int var15 = 0; var15 < var3; var15++) {
         for (int var17 = 0; var17 < var3; var17++) {
            if (var1[var15][var17] == null) {
               var11 = true;
               break;
            }
         }
      }

      return !var11 ? SkyblockTicTacToe.Type2.DRAW : SkyblockTicTacToe.Type2.UNDETERMINED;
   }

   private SkyblockTicTacToe.Type method12(SkyblockTicTacToe.Type[][] var1) {
      int var2 = 0;

      for (SkyblockTicTacToe.Type[] var6 : var1) {
         for (SkyblockTicTacToe.Type var10 : var6) {
            if (var10 != null) {
               var2++;
            }
         }
      }

      return var2 % 2 == 0 ? SkyblockTicTacToe.Type.X : SkyblockTicTacToe.Type.O;
   }

   private void method14() {
      Fishing2_2.method1("Internal state:");

      for (int var1 = 0; var1 < this.field12.field4.length; var1++) {
         StringBuilder var2 = new StringBuilder();

         for (int var3 = 0; var3 < this.field12.field4[var1].length; var3++) {
            var2.append(this.field12.field4[var1][var3]).append(" ");
         }

         Fishing2_2.method1(var2.toString());
      }

      SkyblockTicTacToe.Type var4 = this.method12(this.field12.field4);
      Fishing2_2.method1("Turn: " + var4);
      Fishing2_2.method1("Winner: " + this.method11(this.field12.field4, var4));
   }

   private class Data {
      private final int field1 = 3;
      private final Vector3i field2;
      private final HologramsType_3 field3;
      private final SkyblockTicTacToe.Type[][] field4;

      public Data(Vector3i var2, HologramsType_3 var3) {
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = new SkyblockTicTacToe.Type[3][3];
         this.method1();
      }

      private void method1() {
         Itemcounter6Extension var1 = ThreadModuleDump63.method8();
         if (var1 != null) {
            for (Entry var3 : SkyblockTicTacToe.this.field10.entrySet()) {
               Pair var4 = SkyblockTicTacToe.this.method8((Vector3i)var3.getKey(), this.field2, this.field3);
               if (var4 != null) {
                  this.field4[var4.first()][var4.second()] = (SkyblockTicTacToe.Type)var3.getValue();
               }
            }
         }
      }
   }

   enum Type {
      X,
      O;

      private SkyblockTicTacToe.Type next;

      @Generated
      public SkyblockTicTacToe.Type getNext() {
         return this.next;
      }

      static {
         X.next = O;
         O.next = X;
      }
   }

   enum Type2 {
      WIN_X,
      WIN_O,
      DRAW,
      UNDETERMINED;
   }
}
