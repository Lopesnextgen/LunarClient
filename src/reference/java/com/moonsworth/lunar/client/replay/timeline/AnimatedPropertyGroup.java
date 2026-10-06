package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import java.util.List;
import java.util.function.Supplier;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class AnimatedPropertyGroup extends PropertyGroup {
   private final List<String> field10;
   private String type;
   private final Supplier<List<KeyframeProperty<?, ?>>> field11;
   private final ToggleOption field12;

   public AnimatedPropertyGroup(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21,
      List<String> list2,
      String text3,
      Supplier<List<KeyframeProperty<?, ?>>> supplier4
   ) {
      this(nameplate21, list2, text3, supplier4, null);
   }

   public AnimatedPropertyGroup(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21,
      List<String> list2,
      String text3,
      Supplier<List<KeyframeProperty<?, ?>>> supplier4,
      ToggleOption lightingextension4435
   ) {
      this(nameplate21, list2, text3, supplier4, true, true, lightingextension4435);
   }

   public AnimatedPropertyGroup(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21,
      List<String> list2,
      String text3,
      Supplier<List<KeyframeProperty<?, ?>>> supplier4,
      boolean flag5,
      boolean flag6,
      ToggleOption lightingextension4437
   ) {
      super(nameplate21);
      this.field10 = list2;
      this.type = text3;
      this.field11 = supplier4;
      this.method20(flag5);
      this.method21(flag6);
      this.field12 = lightingextension4437;
      ((List)supplier4.get()).forEach(this::method3);
   }

   @Override
   public void method6(ValueHolder<ReplayContext> threadmoduledump61, int number2) {
      if (this.field12 != null && (Boolean)this.field12.get() != this.isEnabled()) {
         this.field12.method10(this.isEnabled());
      }

      super.method6(threadmoduledump61, number2);
   }

   @Override
   public void method7() {
      if (this.field12 != null) {
         this.field12.method10(this.field12.method8());
      }

      super.method7();
   }

   public PropertyGroup method3(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3
   ) {
      AnimatedPropertyGroup fishing2iterator34 = new AnimatedPropertyGroup(
         nameplate21, this.field10, this.type, this.field11, this.method14(), this.method15(), this.field12
      );
      return this.method6(fishing2iterator34, nameplate21, range2, range3);
   }

   @Override
   public String type() {
      return this.type;
   }

   @Override
   public List<String> method10() {
      return this.field10;
   }

   @Generated
   public void setType(String text1) {
      this.type = text1;
   }
}
