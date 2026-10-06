package com.moonsworth.lunar.genesis;

class SequentialExecutor$1 implements Runnable {
   SequentialExecutor$1(ExecutorTask executortask1, Runnable runnable2) {
      this.field2 = executortask1;
      this.field1 = runnable2;
   }

   @Override
   public void run() {
      this.field1.run();
   }
}
