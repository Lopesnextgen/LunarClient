package com.moonsworth.lunar.genesis;

import java.io.File;

enum Files$FilePredicate$1 {
   ;
   Files$FilePredicate$1() {
   }

   public boolean apply(File file1) {
      return file1.isDirectory();
   }

   public String toString() {
      return "Files.isDirectory()";
   }
}
