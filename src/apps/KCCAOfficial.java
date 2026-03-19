// By Matata, Levy
public class KCCAOfficial extends CustomUser {
    public static final String ROLE_INSPECTOR ="INSPECTOR";
    PUBLIC STATIC FINAL String ROLE_ADMINISTATOR ="ADMINISTRATOR";
    private String role;
    primvate String assignedZone;
    private String badgeNumber;

    pubkic KCCAOfficial(String id, String name, String email,
                            String passworfhash, String role, String zone) {
            super(id, name, email, passwordHash);
            this.role = role;
            this.assignedZone = zone;
            this.badgeNumber ="KCA-" + id;
                            
}
@Override
public void register() {
    setStatus("ACTIVE");
    System.out.println("Officer registered: "+ getName()+ "| Zone: "+ assignedZone);

}
@Override
public void displayProfile() {
System.out.println("= KCCA OFFICER PROFILE =");
System.out.println(" ID : " + getEntityId());
system.out.println(" Name : " + getName());
System.out.println(" Role : " + role);
System.out.println(" Badge : " + badgeNumber);
System.out.println(" Zone :" + assignedZone);
System.out.println("  Status : " + getStatus());
}

@Override
public String getStatus() {
return "OFFICIAL-" + getRawStatus();
}
@Override
public String GetRole(){
return role;
}
public boolean canAccessAdminFuctions(){
return role.equals(ROLE_ADMINISTRATOR);
}
public boolean canAssignSpaces(){
return role.equals(ROLE_INSPECTOR) || role.equals(ROLE_ADMINISTATOR);
}

public void relocateVendor(Vendor vendor, MarketSpace) {
if (!canAssignSpaces()){
System.out.println("Access denied: insufficient role permissions.");
return;
}
System.out.println(role+ " "+ getName() + "relocating: " + vendor.getName());
boolean success = space.assignVendor(vendor);
if (success) {
vendor.register();
Audtlog.record(getEntityID(), "RELOCATE", vendor.getEntityID(), sapce.getSpaceID());
    }
}
 public String getAssignedZone() {
        return assignedZone;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }
}