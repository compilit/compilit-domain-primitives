package world.solidcoding.domainprimitives;

final class ExceptionMessage {

  private ExceptionMessage() {
  }

  public static <T> String invalidDomainPrimitive(String domainPrimitiveName, T value) {
    return String.format("Invalid %s: %s", domainPrimitiveName, value);
  }
}
