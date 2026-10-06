package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.TrackedRoom;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.StackedPlayersTooltip;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SetRoomCoordinatesAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SetRoomStateAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SetRoomTypeAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.AddRoomPositionAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SetRoomInstanceAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RenameRoomAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RemoveRoomPositionAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomEvent;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomPuzzle;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonTrackable;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.MapRoomType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.PuzzleType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DetectedRoomShape;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class DungeonRoomTracker implements DungeonTrackable {
   private static final Pattern field1 = Pattern.compile("(-?\\d+),(-?\\d+)");
   private List<RoomEvent> field2 = new ArrayList<>();
   private int field3 = -1;
   private final DungeonStateTracker field4;
   private final List<WorldPosition> field5 = new ArrayList<>();
   private final List<RoomStateHistory> field6 = new ArrayList<>();
   private RoomInstance field7;
   private final TrackedRoom field8 = new TrackedRoom();
   private RoomPuzzle field9 = null;
   private long field10 = -1L;

   public DungeonRoomTracker(DungeonStateTracker holograms2_51, MapRoomType hologramstype52) {
      this.field4 = holograms2_51;
      this.method16(hologramstype52);
   }

   public void method1(RoomInstance holograms31) {
      if (this.field7 != holograms31) {
         this.field2.add(new SetRoomInstanceAction(this.field7, holograms31));
         this.field7 = holograms31;
         if (holograms31.method26() != null) {
            this.field8.method18(holograms31.method26().secrets());
            this.field2.add(new RenameRoomAction(this.field8.getDisplayName(), holograms31.method26().communityName()));
            this.field8.setDisplayName(holograms31.method26().communityName());
            int number2 = this.method14();
            int number3 = this.field8.method1();
            int number4 = holograms31.method26().getSecretCount();
            this.field8.method17(number4);
            if (this.field8.method2() == RoomState.COMPLETED) {
               this.field8.method10(this.field8.method8());
            }

            this.field2.add(new SetRoomCoordinatesAction(number2, this.method14(), number3, this.field8.method1()));
         }
      }
   }

   public void method2(RoomInstance holograms31) {
      this.field7 = holograms31;
      if (holograms31 != null && holograms31.method26() != null) {
         this.field8.method18(holograms31.method26().secrets());
      }
   }

   public void method3(WorldPosition nameplate41) {
      this.field5.add(nameplate41);
   }

   public void method4() {
      this.field5.clear();
   }

   public void method5(WorldPosition nameplate41) {
      this.field2.add(new AddRoomPositionAction(nameplate41));
      this.field5.add(nameplate41);
      this.field4.method20(this, nameplate41);
   }

   public void method6(WorldPosition nameplate41) {
      this.field2.add(new RemoveRoomPositionAction(nameplate41));
      this.method28().remove(nameplate41);
      this.field4.method21(nameplate41);
   }

   public List<RoomStateHistory> method7() {
      return this.field4.method19().stream().filter(arg1 -> this.method24(arg1.method6()) || this.method24(arg1.method7())).toList();
   }

   public List<DungeonPlayerTracker> method8() {
      ArrayList list1 = new ArrayList();

      for (DungeonPlayerTracker holograms4updater3 : this.field4.getPlayers()) {
         DungeonRoomTracker holograms4iterator4 = holograms4updater3.method7();
         if (this.equals(holograms4iterator4)) {
            list1.add(holograms4updater3);
         }
      }

      return list1;
   }

   @Override
   public List<Component> method1() {
      ArrayList list1 = new ArrayList();
      NamedTextColor namedtextcolor2 = NamedTextColor.WHITE;
      if (this.field8.method1() == this.method14()) {
         namedtextcolor2 = NamedTextColor.GREEN;
      }

      list1.add(Component.text(this.method11(false)));
      list1.add(
         ((TextComponent)((TextComponent)((TextComponent)Component.text("Type: ").color(NamedTextColor.GRAY))
                  .append(Component.text(this.field8.method6().toString())))
               .append(Component.text(" ")))
            .append(Component.text(this.method12().name))
      );
      if (this.method14() != 0) {
         if (this.method14() == -1) {
            list1.add(Component.text("Secrets: ").append(Component.text("? / ?").color(NamedTextColor.GRAY)));
         } else {
            list1.add(
               ((TextComponent)((TextComponent)Component.text("Secrets: ").append(Component.text(this.field8.method1()).color(namedtextcolor2)))
                     .append(Component.text(" / ").color(NamedTextColor.GRAY)))
                  .append(Component.text(this.method14()).color(namedtextcolor2))
            );
         }
      }

      if (this.field8.method4() != null) {
         String text3 = String.format("%.2fs", this.field8.method5());
         list1.add(
            ((TextComponent)((TextComponent)Component.text("Cleared by: ").append(Component.text(this.field8.method4()).color(NamedTextColor.YELLOW)))
                  .append(Component.text(" in ").color(NamedTextColor.GRAY)))
               .append(Component.text(text3).color(NamedTextColor.YELLOW))
         );
      }

      if (this.field8.method3() != null) {
         list1.add(Component.text("Last in room: ").append(Component.text(this.field8.method3().method20(true)).color(NamedTextColor.YELLOW)));
      }

      return list1;
   }

   public void method10(RoomState hologramstype21) {
      if (hologramstype21 != this.field8.method2()) {
         if (hologramstype21 == RoomState.OPENED) {
            this.field10 = Ref.method3().bridge$getSystemTime();
         }

         if (hologramstype21 == RoomState.COMPLETED && this.field8.method7() != -1) {
            this.field2
               .add(
                  new SetRoomCoordinatesAction(
                     Math.max(this.field8.method8(), this.field8.method7()),
                     Math.max(this.field8.method8(), this.field8.method7()),
                     this.field8.method1(),
                     this.field8.method7()
                  )
               );
            this.field8.method10(this.field8.method7());
         }

         if ((hologramstype21 == RoomState.COMPLETED || hologramstype21 == RoomState.CLEARED && this.field8.method6() != MapRoomType.BLOOD) && this.field10 > 0L) {
            double value2 = (Ref.method3().bridge$getSystemTime() - this.field10) / 1000.0;
            if (this.field8.method3() != null) {
               String text4 = this.field8.method3().method20(true);
               List list5 = this.method8();
               if (!list5.isEmpty()) {
                  StringBuilder builder6 = new StringBuilder();
                  boolean flag7 = true;

                  for (DungeonPlayerTracker holograms4updater9 : list5) {
                     if (!flag7) {
                        builder6.append(", ");
                     }

                     builder6.append(holograms4updater9.method20(true));
                     flag7 = false;
                  }

                  text4 = builder6.toString();
               }

               this.field8.method13(text4);
               this.field8.method14(value2);
            }

            this.field10 = -1L;
         }

         if ((this.field8.method2() == RoomState.OPENED || this.field8.method2() == RoomState.ADJACENT)
            && (hologramstype21 == RoomState.CLEARED || hologramstype21 == RoomState.COMPLETED)
            && this.field8.method6() != MapRoomType.FAIRY
            && this.field8.method6() != MapRoomType.SPAWN) {
            List list10 = this.method8();
            StackedPlayersTooltip holograms2_43 = new StackedPlayersTooltip(list10, this);

            for (DungeonPlayerTracker holograms4updater12 : list10) {
               holograms4updater12.method22(holograms2_43);
            }
         }

         this.field2.add(new SetRoomStateAction(this.field8.method2(), hologramstype21));
         this.field8.method11(hologramstype21);
      }
   }

   public String method11(boolean flag1) {
      if (this.field8.method6() == MapRoomType.MINIBOSS) {
         return "Miniboss";
      } else if (this.field8.method6() == MapRoomType.BLOOD) {
         return "Blood";
      } else if (this.field8.method6() == MapRoomType.FAIRY) {
         return "Fairy";
      } else if (this.field8.method6() == MapRoomType.SPAWN) {
         return "Spawn";
      } else {
         String text2 = this.field8.getDisplayName();
         if (text2 == null && !flag1) {
            return this.field8.method6() != MapRoomType.CLEAR
               ? "Unknown (" + this.field8.method6().toString() + ")"
               : "Unknown (" + this.method12().name + ")";
         } else {
            return text2;
         }
      }
   }

   public DetectedRoomShape method12() {
      if (this.method28().size() == 1) {
         return DetectedRoomShape.ONE_BY_ONE;
      }

      if (this.method28().size() == 2) {
         return DetectedRoomShape.ONE_BY_TWO;
      }

      HashSet set1 = new HashSet();

      for (WorldPosition nameplate43 : this.method28()) {
         set1.add(nameplate43.method8());
      }

      if (this.method28().size() == 3) {
         return set1.size() != 3 && set1.size() != 1 ? DetectedRoomShape.L_SHAPE : DetectedRoomShape.ONE_BY_THREE;
      } else if (this.method28().size() == 4) {
         return set1.size() != 4 && set1.size() != 1 ? DetectedRoomShape.TWO_BY_TWO : DetectedRoomShape.ONE_BY_FOUR;
      } else {
         return DetectedRoomShape.NONE;
      }
   }

   public void method13(int number1) {
      if (number1 != this.field8.method1()) {
         this.field2
            .add(
               new SetRoomCoordinatesAction(
                  Math.max(this.field8.method8(), this.field8.method7()), Math.max(this.field8.method8(), this.field8.method7()), this.field8.method1(), number1
               )
            );
         this.field8.method10(number1);
      }
   }

   public int method14() {
      return this.field8.method8() == -1 ? this.field8.method7() : this.field8.method8();
   }

   public void method15(int number1) {
      if (number1 != this.field8.method7()) {
         this.field2
            .add(
               new SetRoomCoordinatesAction(
                  Math.max(this.field8.method8(), this.field8.method7()), Math.max(this.field8.method8(), number1), this.field8.method1(), this.field8.method1()
               )
            );
         this.field8.method16(number1);
         if (this.field8.method2() == RoomState.COMPLETED) {
            this.field2
               .add(
                  new SetRoomCoordinatesAction(
                     Math.max(this.field8.method8(), this.field8.method7()),
                     Math.max(this.field8.method8(), this.field8.method7()),
                     this.field8.method1(),
                     this.field8.method7()
                  )
               );
            this.field8.method10(this.field8.method7());
         }
      }
   }

   public void method16(MapRoomType hologramstype51) {
      if (this.field8.method6() != hologramstype51) {
         if (this.field8.method2() != RoomState.ADJACENT) {
            this.field10 = Ref.method3().bridge$getSystemTime();
         }

         this.field2.add(new SetRoomTypeAction(this.field8.method6(), hologramstype51));
         this.field8.method15(hologramstype51);
         if (!hologramstype51.hasSecrets()) {
            this.field8.method10(0);
            this.field8.method16(0);
            this.field8.method17(0);
         }
      }
   }

   public void method17() {
      for (WorldPosition nameplate42 : this.method28()) {
         this.field4.method21(nameplate42);
         this.field2.add(new RemoveRoomPositionAction(nameplate42));
      }

      this.field5.clear();
   }

   public boolean method18() {
      return this.field9 != null;
   }

   public void method19(RoomPuzzle holograms41) {
      this.field9 = holograms41;
      this.field2.add(new RenameRoomAction(this.field8.getDisplayName(), holograms41.getName()));
      this.field8.setDisplayName(holograms41.getName());
   }

   public void method20(DungeonPlayerTracker holograms4updater1) {
      this.field8.method12(holograms4updater1);
   }

   public void method21(long number1) {
      while (this.method22(number1)) {
      }
   }

   private boolean method22(long number1) {
      if (this.field3 >= 0) {
         RoomEvent holograms3_53 = this.field2.get(this.field3);
         if (holograms3_53.getTimestamp() > number1) {
            this.field3--;
            holograms3_53.method2(this);
            return true;
         }
      }

      if (this.field2.size() <= this.field3 + 1) {
         return false;
      } else {
         RoomEvent holograms3_54 = this.field2.get(this.field3 + 1);
         if (holograms3_54.getTimestamp() < number1) {
            this.field3++;
            holograms3_54.method1(this);
            return true;
         } else {
            return false;
         }
      }
   }

   public Optional<RoomInstance> method23() {
      return Optional.ofNullable(this.field7);
   }

   public boolean contains(double value1, double value3) {
      return this.method24(new WorldPosition(value1, value3, this.field4));
   }

   public boolean method24(WorldPosition nameplate41) {
      for (WorldPosition nameplate43 : this.method28()) {
         if (nameplate41.method8() == nameplate43.method8() && nameplate41.method9() == nameplate43.method9()) {
            return true;
         }
      }

      return false;
   }

   public PuzzleType method25() {
      if (this.method30().method6() != MapRoomType.PUZZLE) {
         return null;
      } else {
         RoomInstance holograms31 = this.method23().orElse(null);
         if (holograms31 != null && holograms31.method26() != null) {
            Matcher matcher2 = field1.matcher(holograms31.method26().ids()[0]);
            return matcher2.find() ? PuzzleType.getByCoords(Integer.parseInt(matcher2.group(1)), Integer.parseInt(matcher2.group(2))) : null;
         } else {
            return null;
         }
      }
   }

   @Generated
   public List<RoomEvent> getEvents() {
      return this.field2;
   }

   @Generated
   public void method26(List<RoomEvent> list1) {
      this.field2 = list1;
   }

   @Generated
   public DungeonStateTracker method27() {
      return this.field4;
   }

   @Generated
   public List<WorldPosition> method28() {
      return this.field5;
   }

   @Generated
   public List<RoomStateHistory> method29() {
      return this.field6;
   }

   @Generated
   public TrackedRoom method30() {
      return this.field8;
   }
}
