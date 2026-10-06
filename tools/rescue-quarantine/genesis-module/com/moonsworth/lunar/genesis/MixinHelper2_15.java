package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;

@Annotation2
@Annotation3
public final class MixinHelper2_15 {
   private final Readable field1;
   private final @Nullable Reader field2;
   private final CharBuffer field3 = CharStreams.createBuffer();
   private final char[] field4 = this.field3.array();
   private final Queue<String> field5 = new LinkedList<>();
   private final MixinHelper3_8 field6 = new MixinHelper3_8() {
      @Override
      protected void handleLine(String var1, String var2) {
         MixinHelper2_15.this.field5.add(var1);
      }
   };

   public MixinHelper2_15(Readable var1) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = var1 instanceof java.io.Reader ? (java.io.Reader)var1 : null;
   }

   @CanIgnoreReturnValue
   public String readLine() {
      while (this.field5.peek() == null) {
         ((Buffer)this.field3).clear();
         int var1 = this.field2 != null ? this.field2.read(this.field4, 0, this.field4.length) : this.field1.read(this.field3);
         if (var1 == -1) {
            this.field6.finish();
            break;
         }

         this.field6.add(this.field4, 0, var1);
      }

      return this.field5.poll();
   }
}
