package gov.iti.jets.dto;

public class CurrentAdminDto {
    private static CurrentAdminDto instance = new CurrentAdminDto();
    private AdminDto admin = new AdminDto();

    public static CurrentAdminDto getInstance() {
        return instance;
    }

    public AdminDto getAdmin() {
        return admin;
    }

    public void setAdmin(AdminDto admin) {
        this.admin = admin;
    }
}
