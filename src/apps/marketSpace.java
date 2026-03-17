// By Eve, Daniel
public class MarketSpace {

    // Private fields (Encapsulation)
    private String  spaceID;
    private String  marketName;
    private String  location;          // e.g. "Usafi Market, Stall 12"
    private String  zone;              // e.g. "CBD", "Nakawa"
    private double  monthlyRent;       // in UGX
    private boolean isOccupied;
    private String  assignedVendorID;  // null when vacant
    private String  marketType;        // "PUBLIC" | "PRIVATE"

    // Constructor
    public MarketSpace(String spaceID, String marketName, String location,
                       String zone, double monthlyRent, String marketType) {
        this.spaceID          = spaceID;
        this.marketName       = marketName;
        this.location         = location;
        this.zone             = zone;
        this.monthlyRent      = monthlyRent;
        this.isOccupied       = false;
        this.assignedVendorID = null;
        this.marketType       = marketType;
    }

    // Assign a vendor to this space
    public boolean assignVendor(Vendor vendor) {
        if (!isOccupied) {
            this.isOccupied       = true;
            this.assignedVendorID = vendor.getEntityID();
            vendor.setAssignedMarket(location);
            System.out.println("Space " + spaceID + " assigned to " + vendor.getName());
            System.out.println("Market: " + marketName + " | Rent: UGX " + monthlyRent);
            return true;
        }
        System.out.println("Space " + spaceID + " is already occupied.");
        return false;
    }

    // Vacate this space
    public void vacateSpace() {
        if (isOccupied) {
            System.out.println("Space " + spaceID + " vacated (was: " + assignedVendorID + ")");
            this.isOccupied       = false;
            this.assignedVendorID = null;
        } else {
            System.out.println("Space " + spaceID + " is already vacant.");
        }
    }

    // Display space details
    public void displaySpaceInfo() {
        System.out.println("[" + spaceID + "] " + marketName + " | " + location);
        System.out.println("  Rent   : UGX " + monthlyRent);
        System.out.println("  Status : " + (isOccupied ? "OCCUPIED by " + assignedVendorID : "AVAILABLE"));
        System.out.println("  Zone   : " + zone + " | Type: " + marketType);
    }

    // Getters
    public String  getSpaceID()          { return spaceID; }
    public String  getMarketName()       { return marketName; }
    public String  getLocation()         { return location; }
    public String  getZone()             { return zone; }
    public double  getMonthlyRent()      { return monthlyRent; }
    public boolean isOccupied()          { return isOccupied; }
    public String  getAssignedVendorID() { return assignedVendorID; }
    public String  getMarketType()       { return marketType; }
}
