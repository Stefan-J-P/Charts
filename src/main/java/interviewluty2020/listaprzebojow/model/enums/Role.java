package interviewluty2020.listaprzebojow.model.enums;

public enum Role
{
    ROLE_ADMIN("ADMIN"), ROLE_USER("USER");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    
}
