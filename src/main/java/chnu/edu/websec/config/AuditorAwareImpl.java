package chnu.edu.websec.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
/*
  @author   qwert
  @project   web-sec
  @class  AuditorAwareImpl
  @version  1.0.0 
  @since 13.10.2026 - 16.51

*/



public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Просто вказуєте своє ім'я або нікнейм:
        return Optional.of("illianita");
    }
}