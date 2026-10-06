package com.moonsworth.lunar.client.replay.timeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import lombok.Generated;

public class UndoRedoManager {
   private final Stack<UndoRedoManager.UndoRedoCommand> field1 = new Stack<>();
   private final Stack<UndoRedoManager.UndoRedoCommand> field2 = new Stack<>();
   private List<UndoRedoManager.UndoRedoCommand> field3 = null;
   private boolean running = false;

   public UndoRedoManager() {
   }

   public void method1() {
      if (!this.running) {
         this.field3 = new ArrayList<>();
      }
   }

   public void endBatch() {
      if (!this.running) {
         if (this.field3 != null) {
            if (!this.field3.isEmpty()) {
               this.field1.push(new UndoRedoManager.UndoRedoBatch(this.field3));
               this.field2.clear();
            }

            this.field3 = null;
         }
      }
   }

   public void method2() {
      if (!this.running && this.method3()) {
         UndoRedoManager.UndoRedoBatch data21 = new UndoRedoManager.UndoRedoBatch(this.field3);
         this.field3 = null;
         this.running = true;

         try {
            data21.field1.run();
         } finally {
            this.running = false;
         }
      }
   }

   public boolean method3() {
      return this.field3 != null;
   }

   public void method4(Runnable runnable1, Runnable runnable2) {
      if (!this.running) {
         UndoRedoManager.UndoRedoCommand data33 = new UndoRedoManager.UndoRedoCommand(runnable1, runnable2);
         if (this.method3()) {
            this.field3.add(data33);
         } else {
            this.field1.push(data33);
            this.field2.clear();
         }

         if (this.field1.size() > 50) {
            this.field1.remove(0);
         }
      }
   }

   public void undo() {
      if (!this.field1.isEmpty()) {
         UndoRedoManager.UndoRedoCommand data31 = this.field1.pop();
         this.running = true;
         data31.field1.run();
         this.running = false;
         this.field2.push(data31);
      }
   }

   public void redo() {
      if (!this.field2.isEmpty()) {
         UndoRedoManager.UndoRedoCommand data31 = this.field2.pop();
         this.running = true;
         data31.field2.run();
         this.running = false;
         this.field1.push(data31);
      }
   }

   public void clear() {
      this.field1.clear();
      this.field2.clear();
   }

   public boolean canUndo() {
      return !this.field1.isEmpty();
   }

   public boolean canRedo() {
      return !this.field2.isEmpty();
   }

   @Generated
   public void setRunning(boolean flag1) {
      this.running = flag1;
   }

   public static class UndoRedoBatch extends UndoRedoManager.UndoRedoCommand {
      public UndoRedoBatch(List<UndoRedoManager.UndoRedoCommand> list1) {
         super(() -> {
            for (int index1x = list1.size() - 1; index1x >= 0; index1x--) {
               ((UndoRedoManager.UndoRedoCommand)list1.get(index1x)).field1.run();
            }
         }, () -> {
            for (UndoRedoManager.UndoRedoCommand data32 : list1) {
               data32.field2.run();
            }
         });
      }
   }

   public static class UndoRedoCommand {
      private final Runnable field1;
      private final Runnable field2;

      @Generated
      public UndoRedoCommand(Runnable runnable1, Runnable runnable2) {
         this.field1 = runnable1;
         this.field2 = runnable2;
      }
   }
}
