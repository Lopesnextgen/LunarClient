package com.moonsworth.lunar.client.event.mixin.fishing.mixin;

import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_2;
import com.moonsworth.lunar.client.highlight.Highlight;
import java.util.Collection;
import javax.annotation.Nullable;
import lombok.Generated;

public class EventMapUpdateLegacy extends Highlight {
   private final int field1;
   private final Itemcounter2_3 field2;
   private final boolean field3;
   @Nullable
   private final Collection<Itemcounter_2> field4;

   @Generated
   public int getId() {
      return this.field1;
   }

   @Generated
   public Itemcounter2_3 method1() {
      return this.field2;
   }

   @Generated
   public boolean method2() {
      return this.field3;
   }

   @Nullable
   @Generated
   public Collection<Itemcounter_2> method3() {
      return this.field4;
   }

   @Generated
   public EventMapUpdateLegacy(int value, Itemcounter2_3 itemcounter2_3, boolean flag, @Nullable Collection<Itemcounter_2> var4) {
      this.field1 = value;
      this.field2 = itemcounter2_3;
      this.field3 = flag;
      this.field4 = var4;
   }
}
