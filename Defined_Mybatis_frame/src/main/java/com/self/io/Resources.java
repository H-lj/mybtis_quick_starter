package com.self.io;

import java.io.InputStream;

public class Resources {
    //根据配置文件路径，加载配置文件成字节输入流，存储在内存中
    public static InputStream getResourceAsSteam(String path) {
        InputStream resource = Resources.class.getClassLoader().getResourceAsStream(path);
        return resource;
    }
}
