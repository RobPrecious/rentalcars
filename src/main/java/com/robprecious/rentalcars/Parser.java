package com.robprecious.rentalcars;

import java.net.URL;
import javax.json.JsonObject;

public interface Parser {
    JsonObject getJSON(URL url);
}
