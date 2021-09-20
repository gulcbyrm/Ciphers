package enums;


/**
 * ENUM sınıf. ui nesnelerini ve yol tanımlarını tutmaktadır.
 */
public enum WPath {

    SERVER("server"),
    HAKKINDA("hakkinda"),
    SETTING("settings");

    private final String fxmlPath;
    private final String fxmlFileName;
    
    
    WPath( String fxmlFileName) {
        this.fxmlPath = "/ui/";
        this.fxmlFileName=fxmlFileName;
    }


    public String getFxmlPath() {
        return fxmlPath;
    }
    public String getFxmlFileName() {
        return fxmlFileName;
    }
}
