package world.solidcoding.domainprimitives;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import world.solidcoding.domainprimitives.testutil.BookId;

public class BookIdTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "1234567890:abcde",
      "9999999999:defga",
      "0000000000:pihsa"
  })
  void ctor_validBookId_shouldNotThrowException() {
    Assertions.assertThatNoException().isThrownBy(() -> new BookId("1234567890:abcde"));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "123456789:abcde",
      "someweirdId:123455",
      ""
  })
  void ctor_invalidBookId_shouldThrowException() {
    Assertions.assertThatThrownBy(() -> new BookId("someweirdId:123455"))
              .isInstanceOf(DomainPrimitiveException.class);
  }

}
