package github.tornaco.xposedmoduletest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "ACCESS_INFO".
 */
@Entity
public class AccessInfo {

    @Id
    private Integer id;
    private Long when;
    private String url;
    private String appName;
    private String pkgName;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated(hash = 686945494)
    public AccessInfo() {
    }

    public AccessInfo(Integer id) {
        this.id = id;
    }

    @Generated(hash = 614527620)
    public AccessInfo(Integer id, Long when, String url, String appName, String pkgName) {
        this.id = id;
        this.when = when;
        this.url = url;
        this.appName = appName;
        this.pkgName = pkgName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getWhen() {
        return when;
    }

    public void setWhen(Long when) {
        this.when = when;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}