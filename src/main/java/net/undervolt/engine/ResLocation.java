package net.undervolt.engine;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class ResLocation {
    private String resDomain;
    private String resPath;

    public ResLocation(String resDomain, String resPath) {
        this.resDomain = resDomain;
        this.resPath = resPath;
    }

    public ResLocation(String resPath) {
        this("undervolt", resPath);
    }

    public String getPath(){
        return "/" + this.resDomain + "/" + this.resPath;
    }

    public InputStream getAsStream(){
        return ResLocation.class.getResourceAsStream(this.getPath());
    }

    public String getAbsolutePath(){
        return new File(this.resDomain+ File.separator + this.resPath).getAbsolutePath();
    }

    public String getResDomain() {
        return resDomain;
    }

    public String getResPath() {
        return resPath;
    }
}
