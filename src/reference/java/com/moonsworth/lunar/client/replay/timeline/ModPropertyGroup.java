package com.moonsworth.lunar.client.replay.timeline;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import java.util.List;
import java.util.function.Supplier;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@SerializedNameOnly
public class ModPropertyGroup extends PropertyGroup {
   private final Framework7Extension field10;
   private final boolean field11;
   private final Supplier<List<PropertyGroup>> field12;
   private final Supplier<List<KeyframeProperty<?, ?>>> field13;

   public ModPropertyGroup(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21,
      Framework7Extension framework7extension2,
      Supplier<List<PropertyGroup>> supplier3,
      Supplier<List<KeyframeProperty<?, ?>>> supplier4
   ) {
      super(nameplate21);
      this.field10 = framework7extension2;
      this.field11 = framework7extension2.isEnabled();
      this.field12 = supplier3;
      this.field13 = supplier4;
      ((List)supplier3.get()).forEach(this::method2);
      ((List)supplier4.get()).forEach(this::method3);
   }

   public PropertyGroup method2(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate21, Range<Integer> range2, Range<Integer> range3
   ) {
      ModPropertyGroup fishing2iterator24 = new ModPropertyGroup(nameplate21, this.field10, this.field12, this.field13);
      return this.method6(fishing2iterator24, nameplate21, range2, range3);
   }

   @Override
   public void method6(ValueHolder<ReplayContext> threadmoduledump61, int number2) {
      if (this.field10.isEnabled() != this.isEnabled()) {
         for (KeyframeProperty fishing2loader4 : super.field2.values()) {
            fishing2loader4.method3();
         }
      }

      ModEnabledState framework35 = (ModEnabledState)this.field10.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field6);
      if (framework35 != null) {
         framework35.setEnabled(this.isEnabled());
      }

      super.method6(threadmoduledump61, number2);
   }

   @Override
   public void method7() {
      ModEnabledState framework31 = (ModEnabledState)this.field10.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field6);
      if (framework31 != null) {
         framework31.setEnabled(this.field11);
      }

      super.method7();
   }

   @Override
   public String type() {
      return this.field10.getId();
   }

   @Override
   public List<String> method10() {
      return List.of("gameplay", "effect");
   }

   @Generated
   public Framework7Extension getFeature() {
      return this.field10;
   }
}
