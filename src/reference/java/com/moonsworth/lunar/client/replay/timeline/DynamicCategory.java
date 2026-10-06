package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class DynamicCategory extends AnimatedPropertyGroup {
   private final Function<RewindHandlers, List<SettingOption>> field13;
   private boolean field14 = false;

   public DynamicCategory(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21,
      List<String> list2,
      String text3,
      Function<RewindHandlers, List<SettingOption>> function4
   ) {
      this(nameplate21, list2, text3, null, function4);
   }

   public DynamicCategory(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21,
      List<String> list2,
      String text3,
      ToggleOption lightingextension4434,
      Function<RewindHandlers, List<SettingOption>> function5
   ) {
      this(nameplate21, list2, text3, true, true, lightingextension4434, function5);
   }

   public DynamicCategory(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21,
      List<String> list2,
      String text3,
      boolean flag4,
      boolean flag5,
      ToggleOption lightingextension4436,
      Function<RewindHandlers, List<SettingOption>> function7
   ) {
      super(nameplate21, list2, text3, Collections::emptyList, flag4, flag5, lightingextension4436);
      this.field13 = function7;
      this.method23(true);
      this.method24(true);
   }

   @Override
   public PropertyGroup method3(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3
   ) {
      DynamicCategory nameplate34 = new DynamicCategory(nameplate21, this.method10(), this.type(), this.field13);
      return this.method6(nameplate34, nameplate21, range2, range3);
   }

   public boolean method2(String text1) {
      if (this.field14) {
         return true;
      } else {
         return this.method16() == PropertyGroup.NodeType.KEYFRAMES
            ? !this.method12().containsKey(text1)
            : !this.method11().containsKey(text1);
      }
   }

   @Generated
   public Function<RewindHandlers, List<SettingOption>> method3() {
      return this.field13;
   }

   @Generated
   public void method4(boolean flag1) {
      this.field14 = flag1;
   }
}
