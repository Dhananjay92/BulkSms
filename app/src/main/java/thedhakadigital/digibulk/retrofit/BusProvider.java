package thedhakadigital.digibulk.retrofit;

import com.squareup.otto.Bus;

/**
 * Created by y34h1a on 5/13/17.
 */

public class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    public BusProvider(){}
}