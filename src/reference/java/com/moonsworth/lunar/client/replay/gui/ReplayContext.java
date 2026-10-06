package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.INetHandlerBridge;
import com.moonsworth.lunar.client.replay.recording.ReplayLocation;
import com.moonsworth.lunar.client.replay.recording.KeybindAction;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.Generated;

public class ReplayContext {
   private final RewindHandlers field1;
   private final LocalPlayerContext field2 = new LocalPlayerContext();
   private final GuiScreenContext field3 = new GuiScreenContext();
   private LayerPropertiesContext field4 = new LayerPropertiesContext();
   private final RewindingContext field5 = new RewindingContext();
   private List<ResourcePackBridge> field6 = List.of();
   private List<Integer> field7 = List.of();
   private List<ResourcePackBridge> field8 = List.of();
   private List<String> field9 = List.of();
   private Map<KeybindAction, Boolean> field10 = new HashMap<>();
   private ReplayLocation field11 = null;
   private INetHandlerBridge field12;
   private boolean field13 = false;
   private boolean field14 = false;
   private int field15;
   private int tick;
   private int field16 = -1;
   private final Queue<Runnable> field17 = new ArrayDeque<>();

   public void method1(boolean flag1) {
      this.tick += flag1 ? -1 : 1;
   }

   public boolean method2() {
      return this.field16 != this.tick;
   }

   public void method3(Runnable runnable1) {
      this.field17.add(runnable1);
   }

   public ReplayTimeline method4() {
      return this.field1.method40().method37();
   }

   public boolean method5() {
      return this.field3.method5() instanceof Bridge5Extension612;
   }

   @Generated
   public ReplayContext(RewindHandlers rewindhandlers1) {
      this.field1 = rewindhandlers1;
   }

   @Generated
   public RewindHandlers method6() {
      return this.field1;
   }

   @Generated
   public LocalPlayerContext method7() {
      return this.field2;
   }

   @Generated
   public GuiScreenContext method8() {
      return this.field3;
   }

   @Generated
   public LayerPropertiesContext method9() {
      return this.field4;
   }

   @Generated
   public RewindingContext method10() {
      return this.field5;
   }

   @Generated
   public List<ResourcePackBridge> method11() {
      return this.field6;
   }

   @Generated
   public List<Integer> method12() {
      return this.field7;
   }

   @Generated
   public List<ResourcePackBridge> method13() {
      return this.field8;
   }

   @Generated
   public List<String> method14() {
      return this.field9;
   }

   @Generated
   public Map<KeybindAction, Boolean> method15() {
      return this.field10;
   }

   @Generated
   public ReplayLocation method16() {
      return this.field11;
   }

   @Generated
   public INetHandlerBridge method17() {
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
   public void method24(LayerPropertiesContext nameplate1) {
      this.field4 = nameplate1;
   }

   @Generated
   public void method25(List<ResourcePackBridge> list1) {
      this.field6 = list1;
   }

   @Generated
   public void method26(List<Integer> list1) {
      this.field7 = list1;
   }

   @Generated
   public void method27(List<ResourcePackBridge> list1) {
      this.field8 = list1;
   }

   @Generated
   public void method28(List<String> list1) {
      this.field9 = list1;
   }

   @Generated
   public void method29(Map<KeybindAction, Boolean> map) {
      this.field10 = map;
   }

   @Generated
   public void method30(ReplayLocation gui71) {
      this.field11 = gui71;
   }

   @Generated
   public void method31(INetHandlerBridge bridge_261) {
      this.field12 = bridge_261;
   }

   @Generated
   public void method32(boolean flag1) {
      this.field13 = flag1;
   }

   @Generated
   public void method33(boolean flag1) {
      this.field14 = flag1;
   }

   @Generated
   public void method34(int number1) {
      this.field15 = number1;
   }

   @Generated
   public void setTick(int number1) {
      this.tick = number1;
   }

   @Generated
   public void method36(int number1) {
      this.field16 = number1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof ReplayContext nameplate42)) {
         return false;
      } else {
         if (!nameplate42.canEqual(this)) {
            return false;
         }

         if (this.method18() != nameplate42.method18()) {
            return false;
         }

         if (this.method19() != nameplate42.method19()) {
            return false;
         }

         if (this.method20() != nameplate42.method20()) {
            return false;
         }

         if (this.getTick() != nameplate42.getTick()) {
            return false;
         }

         if (this.method22() != nameplate42.method22()) {
            return false;
         }

         RewindHandlers rewindhandlers3 = this.method6();
         RewindHandlers rewindhandlers4 = nameplate42.method6();
         if (rewindhandlers3 == null ? rewindhandlers4 == null : rewindhandlers3.equals(rewindhandlers4)) {
            LocalPlayerContext nameplate35 = this.method7();
            LocalPlayerContext nameplate36 = nameplate42.method7();
            if (nameplate35 == null ? nameplate36 == null : nameplate35.equals(nameplate36)) {
               GuiScreenContext nameplate27 = this.method8();
               GuiScreenContext nameplate28 = nameplate42.method8();
               if (nameplate27 == null ? nameplate28 == null : nameplate27.equals(nameplate28)) {
                  LayerPropertiesContext nameplate9 = this.method9();
                  LayerPropertiesContext nameplate10 = nameplate42.method9();
                  if (nameplate9 == null ? nameplate10 == null : nameplate9.equals(nameplate10)) {
                     RewindingContext nameplate511 = this.method10();
                     RewindingContext nameplate512 = nameplate42.method10();
                     if (nameplate511 == null ? nameplate512 == null : nameplate511.equals(nameplate512)) {
                        List list13 = this.method11();
                        List list14 = nameplate42.method11();
                        if (list13 == null ? list14 == null : list13.equals(list14)) {
                           List list15 = this.method12();
                           List list16 = nameplate42.method12();
                           if (list15 == null ? list16 == null : list15.equals(list16)) {
                              List list17 = this.method13();
                              List list18 = nameplate42.method13();
                              if (list17 == null ? list18 == null : list17.equals(list18)) {
                                 List list19 = this.method14();
                                 List list20 = nameplate42.method14();
                                 if (list19 == null ? list20 == null : list19.equals(list20)) {
                                    Map map21 = this.method15();
                                    Map map22 = nameplate42.method15();
                                    if (map21 == null ? map22 == null : map21.equals(map22)) {
                                       ReplayLocation gui723 = this.method16();
                                       ReplayLocation gui724 = nameplate42.method16();
                                       if (gui723 == null ? gui724 == null : gui723.equals(gui724)) {
                                          INetHandlerBridge bridge_2625 = this.method17();
                                          INetHandlerBridge bridge_2626 = nameplate42.method17();
                                          if (bridge_2625 == null ? bridge_2626 == null : bridge_2625.equals(bridge_2626)) {
                                             Queue list27 = this.method23();
                                             Queue list28 = nameplate42.method23();
                                             return list27 == null ? list28 == null : list27.equals(list28);
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
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof ReplayContext;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + (this.method18() ? 79 : 97);
      number2 = number2 * 59 + (this.method19() ? 79 : 97);
      number2 = number2 * 59 + this.method20();
      number2 = number2 * 59 + this.getTick();
      number2 = number2 * 59 + this.method22();
      RewindHandlers rewindhandlers3 = this.method6();
      number2 = number2 * 59 + (rewindhandlers3 == null ? 43 : rewindhandlers3.hashCode());
      LocalPlayerContext nameplate34 = this.method7();
      number2 = number2 * 59 + (nameplate34 == null ? 43 : nameplate34.hashCode());
      GuiScreenContext nameplate25 = this.method8();
      number2 = number2 * 59 + (nameplate25 == null ? 43 : nameplate25.hashCode());
      LayerPropertiesContext nameplate6 = this.method9();
      number2 = number2 * 59 + (nameplate6 == null ? 43 : nameplate6.hashCode());
      RewindingContext nameplate57 = this.method10();
      number2 = number2 * 59 + (nameplate57 == null ? 43 : nameplate57.hashCode());
      List list8 = this.method11();
      number2 = number2 * 59 + (list8 == null ? 43 : list8.hashCode());
      List list9 = this.method12();
      number2 = number2 * 59 + (list9 == null ? 43 : list9.hashCode());
      List list10 = this.method13();
      number2 = number2 * 59 + (list10 == null ? 43 : list10.hashCode());
      List list11 = this.method14();
      number2 = number2 * 59 + (list11 == null ? 43 : list11.hashCode());
      Map map12 = this.method15();
      number2 = number2 * 59 + (map12 == null ? 43 : map12.hashCode());
      ReplayLocation gui713 = this.method16();
      number2 = number2 * 59 + (gui713 == null ? 43 : gui713.hashCode());
      INetHandlerBridge bridge_2614 = this.method17();
      number2 = number2 * 59 + (bridge_2614 == null ? 43 : bridge_2614.hashCode());
      Queue list15 = this.method23();
      return number2 * 59 + (list15 == null ? 43 : list15.hashCode());
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
         + this.method15()
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
