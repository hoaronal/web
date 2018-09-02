package com.app.vn.common.client;

import java.io.IOException;

public interface ApiTemplate {

    public String get(String path);
    public String post(String path, String jsonObject) throws IOException;

    public String put(String path);

    public String delete(String path);

}
