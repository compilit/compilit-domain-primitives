package world.solidcoding.domainprimitives.testutil;

import world.solidcoding.domainprimitives.DomainPrimitive;

public class BookId extends DomainPrimitive<String> {

  public BookId(String value) {
    super(value);
  }

  @Override
  protected boolean isValid(String value) {
    var substrings = value.split(":");
    var digits = substrings[0];
    var letters = substrings[1];
    return digits.length() == 10
        && letters.length() == 5
        && digits.chars().allMatch(Character::isDigit)
        && letters.chars().allMatch(Character::isAlphabetic);
  }

}
