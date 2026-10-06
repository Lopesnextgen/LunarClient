package com.moonsworth.lunar.ichor;

import lombok.Generated;
import org.objectweb.asm.tree.RecordComponentNode;

public class RecordComponentRemapTask {
   protected final RecordComponentNode field1;

   public void run() {
      this.field1.name = this.field2.field3.mapRecordComponentName(this.field2.field2.name, this.field1.name, this.field1.descriptor);
      this.field1.descriptor = this.field2.field3.mapDesc(this.field1.descriptor);
      this.field1.signature = this.field2.field3.mapSignature(this.field1.signature, true);
      this.field2.method13(this.field1.visibleAnnotations, this.field1.invisibleAnnotations);
      this.field2.method13(this.field1.visibleTypeAnnotations, this.field1.invisibleTypeAnnotations);
   }

   @Generated
   public RecordComponentRemapTask(ClassNodeRemapper mixinmisc31, RecordComponentNode recordcomponentnode2) {
      this.field2 = mixinmisc31;
      this.field1 = recordcomponentnode2;
   }
}
