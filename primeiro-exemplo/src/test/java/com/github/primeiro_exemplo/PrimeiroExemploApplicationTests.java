package com.github.primeiro_exemplo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // <-- Força o Spring a usar as configurações de teste
class PrimeiroExemploApplicationTests {

    @Test
    void contextLoads() {
    }

}
