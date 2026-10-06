package com.moonsworth.lunar.ichor;

import java.util.List;
import lombok.Generated;
import org.objectweb.asm.tree.AnnotationNode;

public class AnnotationRemapTask {
   private final AnnotationNode field1;

   public void run() {
      this.field1.desc = this.field2.field3.mapDesc(this.field1.desc);
      boolean flag1 = this.field1.desc.contains("kotlin/Metadata");
      if (this.field1.values != null) {
         for (int index2 = 0; index2 < this.field1.values.size(); index2++) {
            Object obj3 = this.field2.field3.mapValue(this.field1.values.get(index2));
            if (flag1 && obj3 instanceof List list4) {
               for (int index5 = 0; index5 < list4.size(); index5++) {
                  if (list4.get(index5) instanceof String text7) {
                     String text8 = text7;

                     try {
                        if (text7.startsWith("(")) {
                           text8 = this.field2.field3.mapMethodDesc(text7);
                        } else if (text7.startsWith("L") && text7.startsWith(";")) {
                           text8 = this.field2.field3.mapType(text7);
                        }
                     } catch (Exception exception10) {
                     }

                     if (!text8.equals(text7)) {
                        list4.set(index5, text8);
                     }
                  }
               }
            }

            this.field1.values.set(index2, obj3);
         }
      }
   }

   @Generated
   public AnnotationRemapTask(ClassNodeRemapper mixinmisc31, AnnotationNode annotation2) {
      this.field2 = mixinmisc31;
      this.field1 = annotation2;
   }
}
