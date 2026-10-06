package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate;

import com.moonsworth.lunar.bridge.IResourcePackBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge_26;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui7;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.Generated;

public class Nameplate4 {
   private final RewindHandlers field1;
   private final Nameplate3 field2 = new Nameplate3();
   private final Nameplate2 field3 = new Nameplate2();
   private Nameplate field4 = new Nameplate();
   private final Nameplate5 field5 = new Nameplate5();
   private List<IResourcePackBridge> field6 = List.of();
   private List<Integer> field7 = List.of();
   private List<IResourcePackBridge> field8 = List.of();
   private List<String> field9 = List.of();
   private Map<GuiType3, Boolean> field10 = new HashMap<>();
   private Gui7 field11 = null;
   private Bridge_26 field12;
   private boolean field13 = false;
   private boolean field14 = false;
   private int field15;
   private int tick;
   private int field16 = -1;
   private final Queue<Runnable> field17 = new ArrayDeque<>();

   public void method1(boolean var1) {
      this.tick += var1 ? -1 : 1;
   }

   public boolean method2() {
      return this.field16 != this.tick;
   }

   public void method3(Runnable var1) {
      this.field17.add(var1);
   }

   public Highlight_3 method4() {
      return this.field1.method40().method37();
   }

   public boolean method5() {
      return this.field3.method5() instanceof Bridge5Extension612;
   }

   @Generated
   public Nameplate4(RewindHandlers var1) {
      this.field1 = var1;
   }

   @Generated
   public RewindHandlers method6() {
      return this.field1;
   }

   @Generated
   public Nameplate3 method7() {
      return this.field2;
   }

   @Generated
   public Nameplate2 method8() {
      return this.field3;
   }

   @Generated
   public Nameplate method9() {
      return this.field4;
   }

   @Generated
   public Nameplate5 method10() {
      return this.field5;
   }

   @Generated
   public List<IResourcePackBridge> method11() {
      return this.field6;
   }

   @Generated
   public List<Integer> method12() {
      return this.field7;
   }

   @Generated
   public List<IResourcePackBridge> method13() {
      return this.field8;
   }

   @Generated
   public List<String> method14() {
      return this.field9;
   }

   @Generated
   public Map<GuiType3, Boolean> getProvider() {
      return this.field10;
   }

   @Generated
   public Gui7 method16() {
      return this.field11;
   }

   @Generated
   public Bridge_26 method17() {
      return this.field12;
   }

   @Generated
   public boolean method18() {
      return this.field13;
   }

   @Generated
   public boolean method19() {
      return this.field14;
   }

   @Generated
   public int method20() {
      return this.field15;
   }

   @Generated
   public int getTick() {
      return this.tick;
   }

   @Generated
   public int method22() {
      return this.field16;
   }

   @Generated
   public Queue<Runnable> method23() {
      return this.field17;
   }

   @Generated
   public void method24(Nameplate var1) {
      this.field4 = var1;
   }

   @Generated
   public void method25(List<IResourcePackBridge> var1) {
      this.field6 = var1;
   }

   @Generated
   public void method26(List<Integer> var1) {
      this.field7 = var1;
   }

   @Generated
   public void method27(List<IResourcePackBridge> var1) {
      this.field8 = var1;
   }

   @Generated
   public void method28(List<String> var1) {
      this.field9 = var1;
   }

   @Generated
   public void method29(Map<GuiType3, Boolean> var1) {
      this.field10 = var1;
   }

   @Generated
   public void method30(Gui7 var1) {
      this.field11 = var1;
   }

   @Generated
   public void method31(Bridge_26 var1) {
      this.field12 = var1;
   }

   @Generated
   public void method32(boolean var1) {
      this.field13 = var1;
   }

   @Generated
   public void method33(boolean var1) {
      this.field14 = var1;
   }

   @Generated
   public void method34(int var1) {
      this.field15 = var1;
   }

   @Generated
   public void setTick(int var1) {
      this.tick = var1;
   }

   @Generated
   public void method36(int var1) {
      this.field16 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Nameplate4 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (this.method18() != var2.method18()) {
            return false;
         }

         if (this.method19() != var2.method19()) {
            return false;
         }

         if (this.method20() != var2.method20()) {
            return false;
         }

         if (this.getTick() != var2.getTick()) {
            return false;
         }

         if (this.method22() != var2.method22()) {
            return false;
         }

         RewindHandlers var3 = this.method6();
         RewindHandlers var4 = var2.method6();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Nameplate3 var5 = this.method7();
            Nameplate3 var6 = var2.method7();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               Nameplate2 var7 = this.method8();
               Nameplate2 var8 = var2.method8();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  Nameplate var9 = this.method9();
                  Nameplate var10 = var2.method9();
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     Nameplate5 var11 = this.method10();
                     Nameplate5 var12 = var2.method10();
                     if (var11 == null ? var12 == null : var11.equals(var12)) {
                        List var13 = this.method11();
                        List var14 = var2.method11();
                        if (var13 == null ? var14 == null : var13.equals(var14)) {
                           List var15 = this.method12();
                           List var16 = var2.method12();
                           if (var15 == null ? var16 == null : var15.equals(var16)) {
                              List var17 = this.method13();
                              List var18 = var2.method13();
                              if (var17 == null ? var18 == null : var17.equals(var18)) {
                                 List var19 = this.method14();
                                 List var20 = var2.method14();
                                 if (var19 == null ? var20 == null : var19.equals(var20)) {
                                    Map var21 = this.getProvider();
                                    Map var22 = var2.method15();
                                    if (var21 == null ? var22 == null : var21.equals(var22)) {
                                       Gui7 var23 = this.method16();
                                       Gui7 var24 = var2.method16();
                                       if (var23 == null ? var24 == null : var23.equals(var24)) {
                                          Bridge_26 var25 = this.method17();
                                          Bridge_26 var26 = var2.method17();
                                          if (var25 == null ? var26 == null : var25.equals(var26)) {
                                             Queue var27 = this.method23();
                                             Queue var28 = var2.method23();
                                             return var27 == null ? var28 == null : var27.equals(var28);
                                          } else {
                                             return false;
                                          }
                                       } else {
                                          return false;
                                       }
                                    } else {
                                       return false;
                                    }
                                 } else {
                                    return false;
                                 }
                              } else {
                                 return false;
                              }
                           } else {
                              return false;
                           }
                        } else {
                           return false;
                        }
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Nameplate4;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + (this.method18() ? 79 : 97);
      var2 = var2 * 59 + (this.method19() ? 79 : 97);
      var2 = var2 * 59 + this.method20();
      var2 = var2 * 59 + this.getTick();
      var2 = var2 * 59 + this.method22();
      RewindHandlers var3 = this.method6();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Nameplate3 var4 = this.method7();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      Nameplate2 var5 = this.method8();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      Nameplate var6 = this.method9();
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      Nameplate5 var7 = this.method10();
      var2 = var2 * 59 + (var7 == null ? 43 : var7.hashCode());
      List var8 = this.method11();
      var2 = var2 * 59 + (var8 == null ? 43 : var8.hashCode());
      List var9 = this.method12();
      var2 = var2 * 59 + (var9 == null ? 43 : var9.hashCode());
      List var10 = this.method13();
      var2 = var2 * 59 + (var10 == null ? 43 : var10.hashCode());
      List var11 = this.method14();
      var2 = var2 * 59 + (var11 == null ? 43 : var11.hashCode());
      Map var12 = this.getProvider();
      var2 = var2 * 59 + (var12 == null ? 43 : var12.hashCode());
      Gui7 var13 = this.method16();
      var2 = var2 * 59 + (var13 == null ? 43 : var13.hashCode());
      Bridge_26 var14 = this.method17();
      var2 = var2 * 59 + (var14 == null ? 43 : var14.hashCode());
      Queue var15 = this.method23();
      return var2 * 59 + (var15 == null ? 43 : var15.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "ReplayContext(rewindPlayer="
         + this.method6()
         + ", localPlayerContext="
         + this.method7()
         + ", guiScreenContext="
         + this.method8()
         + ", layerPropertiesContext="
         + this.method9()
         + ", rewindingContext="
         + this.method10()
         + ", serverResourcePacks="
         + this.method11()
         + ", serverPacksToLoad="
         + this.method12()
         + ", clientResourcePacks="
         + this.method13()
         + ", clientPacksToLoad="
         + this.method14()
         + ", keybinds="
         + this.getProvider()
         + ", currentLocation="
         + this.method16()
         + ", netHandler="
         + this.method17()
         + ", doneLoadingTerrain="
         + this.method18()
         + ", gamePaused="
         + this.method19()
         + ", recorderVersion="
         + this.method20()
         + ", tick="
         + this.getTick()
         + ", skipTick="
         + this.method22()
         + ", postTickTasks="
         + this.method23()
         + ")";
   }
}
