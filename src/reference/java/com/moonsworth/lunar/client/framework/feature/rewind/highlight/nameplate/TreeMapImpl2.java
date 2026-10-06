package com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing;
import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight;
import java.util.Comparator;
import java.util.UUID;
import java.util.AbstractMap.SimpleImmutableEntry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public class TreeMapImpl2<V extends Fishing2<Integer, V> & Fishing<UUID>> extends TreeMapImpl<Range<Integer>, V> {
   private final Highlight<V> field2;

   public TreeMapImpl2(Nameplate2 var1, Comparator<Range<Integer>> var2, Highlight<V> highlight) {
      super(var1, var2);
      this.field2 = highlight;
   }

   public V method1(Range<Integer> var1, V var2) {
      this.field2.method12().put(((Fishing)var2).method1(), new SimpleImmutableEntry<>(var1, var2));
      return super.method3(var1, (V)var2);
   }

   public V method2(Object var1) {
      Fishing2 var2 = super.method5(var1);
      if (var2 != null) {
         this.field2.method12().remove(((Fishing)var2).method1());
      }

      return (V)var2;
   }

   @Override
   public void method7() {
      this.field2.method12().clear();
      super.method7();
   }

   @Generated
   public Highlight<V> method4() {
      return this.field2;
   }
}
