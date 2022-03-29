package com.compilit.domainprimitives.testutil;

import java.util.Objects;
import com.compilit.domainprimitives.DomainPrimitive;

public class BookId extends DomainPrimitive<String> {

  public BookId(String value) {
    super(value, BookId.class.getSimpleName());
  }

  @Override
  protected boolean isValid(String value) {
    if (Objects.isNull(value) || value.isBlank())
      return false;
    var substrings = value.split(":");
    var digits = substrings[0];
    var letters = substrings[1];
    return digits.length() == 10
        && letters.length() == 5
        && digits.chars().allMatch(Character::isDigit)
        && letters.chars().allMatch(Character::isAlphabetic);
  }

}
