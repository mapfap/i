package com.code.i.console;

import com.code.i.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Init implements CommandLineRunner {

    private final WarehouseService warehouseService;

    @Override
    public void run(String... args) throws Exception {
        warehouseService.test();
        log.info(warehouseService.test2().toString());

    }
}
