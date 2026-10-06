package com.moonsworth.lunar.bridge.horsestats;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.Generated;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.minecraft.AxisCoordinateChooser;

public enum HorsestatsType$Type implements Predicate<HorsestatsType_2> {
   X("x", (arg0, arg2, arg4) -> arg0),
   Y("y", (arg0, arg2, arg4) -> arg2),
   Z("z", (arg0, arg2, arg4) -> arg4);

   private static final HorsestatsType$Type[] VALUES = values();
   private static final Map<String, HorsestatsType$Type> BY_NAME = Arrays.stream(VALUES)
      .collect(Collectors.toMap(HorsestatsType$Type::getName, arg0 -> (HorsestatsType$Type)arg0));
   private final String name;
   private final AxisCoordinateChooser chooser;

   HorsestatsType$Type(String text, AxisCoordinateChooser horsestatstype$extension4) {
      this.name = text;
      this.chooser = horsestatstype$extension4;
   }

   public static HorsestatsType$Type fromName(String text) {
      return BY_NAME.get(text.toLowerCase(Locale.ROOT));
   }

   public boolean isVertical() {
      return this == Y;
   }

   public boolean isHorizontal() {
      return this == X || this == Z;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public boolean test(HorsestatsType_2 horsestatstype_21) {
      return horsestatstype_21 != null && horsestatstype_21.getAxis() == this;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public HorsestatsType$Type3 getType() {
      return switch (this) {
         case X, Z -> HorsestatsType$Type3.HORIZONTAL;
         case Y -> HorsestatsType$Type3.VERTICAL;
      };
   }

   public int choose(int value, int value2, int value3) {
      return (int)this.chooser.choose(value, value2, value3);
   }

   public double choose(double value, double value2, double value3) {
      return this.chooser.choose(value, value2, value3);
   }

   @Generated
   public String getName() {
      return this.name;
   }
}
