package com.oway.otto;

import com.squareup.otto.Bus;

public class BusProvider {
    private static final MainThreadBus bus = new MainThreadBus();

    public static Bus getInstance() {

        return bus;
    }

    private BusProvider(){

    }
}