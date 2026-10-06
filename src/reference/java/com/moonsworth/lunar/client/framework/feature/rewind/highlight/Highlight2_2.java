package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator23;
import java.util.UUID;
import org.apache.commons.lang3.Range;

public class Highlight2_2 {
   private final RewindIterator23 field1;
   private final Range<Integer> field2;
   private final UUID field3;
   private final int field4;

   public Highlight2_2(RewindIterator23 iterator, Range<Integer> range, UUID uUID, int value) {
      this.field1 = iterator;
      this.field2 = range;
      this.field3 = uUID;
      this.field4 = value;
   }

   public RewindIterator23 method1() {
      return this.field1;
   }

   public Range<Integer> method2() {
      return this.field2;
   }

   public UUID method3() {
      return this.field3;
   }

   public int method4() {
      return this.field4;
   }
}
