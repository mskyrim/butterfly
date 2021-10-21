package com.butterfly.version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    private static final Logger logger = LoggerFactory.getLogger(VersionController.class);

    @GetMapping("/version")
    public String getVersion() {
        logger.info("Call to version endpoint");
        logger.warn("Call to version endpoint");
        logger.debug("Call to version endpoint");
        logger.error("Call to version endpoint");
        return "1.0";
    }
}
