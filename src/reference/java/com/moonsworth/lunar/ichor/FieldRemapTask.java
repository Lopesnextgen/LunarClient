package com.moonsworth.lunar.ichor;

import lombok.Generated;
import org.objectweb.asm.tree.FieldNode;

public class FieldRemapTask {
   protected final FieldNode field1;

   public void run() {
      this.field1.name = this.field2.field3.mapFieldName(this.field2.field2.name, this.field1.name, this.field1.desc);
      this.field1.desc = this.field2.field3.mapDesc(this.field1.desc);
      this.field1.signature = this.field2.field3.mapSignature(this.field1.signature, true);
      if (this.field1.value != null) {
         this.field1.value = this.field2.field3.mapValue(this.field1.value);
      }

      this.field2.method13(this.field1.visibleAnnotations, this.field1.invisibleAnnotations);
      this.field2.method13(this.field1.visibleTypeAnnotations, this.field1.invisibleTypeAnnotations);
   }

   @Generated
   public FieldRemapTask(ClassNodeRemapper mixinmisc31, FieldNode field2_) {
      this.field2 = mixinmisc31;
      this.field1 = field2_;
   }
}
