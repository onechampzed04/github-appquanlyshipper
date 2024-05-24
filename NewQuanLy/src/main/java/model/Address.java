package model;

/**
 *
 * @author ADMIN
 */
public class Address {
    private String provinceId;
    private String districtId;
    private String wardId;

    public Address(String provinceId, String districtId, String wardId) {
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    
}