package com.moonsworth.lunar.client.config.option;

import java.lang.annotation.Documented;
import org.intellij.lang.annotations.Pattern;

@Documented
@Pattern("[A-Z0-9_]{2,}")
public @interface ConstantName {
}
