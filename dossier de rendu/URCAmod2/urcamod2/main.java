package com.github.broussk.URCAmod2;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Urcamod2.MODID)
public final class Urcamod2 {
    public static void main(String[] args) {

        public static final String MODID = "urcamod2";

        public static final Logger LOGGER = LogManager.getLogger(MODID);

        public Urcamod2() {
            LOGGER.debug("Hello from urcamod");
        }
    }
}