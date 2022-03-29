package com.compilit.domainprimitives;

final class DomainPrimitiveException extends RuntimeException {

  public <T> DomainPrimitiveException(String domainPrimitiveName, T value) {
    super(String.format("Provided value '%s' is not a valid %s", value, domainPrimitiveName));
  }

}
