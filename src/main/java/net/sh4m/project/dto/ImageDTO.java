package net.sh4m.project.dto;

import net.sh4m.genericjdbc.dto.AbstractDTO;

public class ImageDTO extends AbstractDTO<Long> {

    private static final long serialVersionUID = 3300124787749821631L;
    
    private String dir;
    private String fulldir;
    private String filename;
    private String woNo;
    
    public String getDir() {
        return dir;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }
    public String getFulldir() {
        return fulldir;
    }
    public void setFulldir(String fulldir) {
        this.fulldir = fulldir;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getWoNo() {
        return woNo;
    }
    public void setWoNo(String woNo) {
        this.woNo = woNo;
    }

}
