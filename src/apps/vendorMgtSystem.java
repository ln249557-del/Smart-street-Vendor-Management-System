//  By Joseph, Neuton, Timothy
import java.util.ArrayList;
import java.util.List;

public class VendorManagementSystem {

    List<Vendor> vendors = new ArrayList<>();
    List<MarketSpace> spaces = new ArrayList<>();
    List<KCCAOfficial> officials = new ArrayList<>();
    List<GrievanceReport> grievances = new ArrayList<>();
    int grievanceCounter = 1;

    // ADD ENTITIES
    void addVendor(Vendor v) {
        vendors.add(v);
    }

    void addMarketSpace(MarketSpace s) {
        spaces.add(s);
    }

    void addOfficial(KCCAOfficial o) {
        officials.add(o);
    }

    // REGISTER VENDORS
    void registerAllVendors() {
        for (Vendor v : vendors) {
            if (v.status.equals("UNREGISTERED")) {
                v.register();
            }
        }
    }

    // SEARCH VENDORS
    List<Vendor> searchVendors(String keyword) {
        List<Vendor> results = new ArrayList<>();

        for (Vendor v : vendors) {
            if (v.name.contains(keyword) ||
                v.id.contains(keyword) ||
                v.type.contains(keyword) ||
                (v.market != null && v.market.contains(keyword))) {

                results.add(v);
            }
        }
        return results;
    }

    // UPDATE STATUS (ADMIN ONLY)
    void updateVendorStatus(KCCAOfficial officer, String vendorID, String newStatus) {

        if (!officer.isAdmin()) {
            System.out.println("Access denied");
            return;
        }

        for (Vendor v : vendors) {
            if (v.id.equals(vendorID)) {
                v.status = newStatus;
                System.out.println("Status updated");
                return;
            }
        }

        System.out.println("Vendor not found");
    }

    // FIND BEST SPACE
    MarketSpace findBestSpace(Vendor v) {
        for (MarketSpace s : spaces) {
            if (!s.occupied && s.rent <= v.capital) {
                return s;
            }
        }
        return null;
    }

    // AUTO ALLOCATE
    void autoAllocateAll() {
        for (Vendor v : vendors) {
            if (v.market == null) {
                MarketSpace s = findBestSpace(v);

                if (s != null) {
                    s.assignVendor(v);
                } else {
                    System.out.println("No space for " + v.name);
                }
            }
        }
    }

    // SUBMIT GRIEVANCE
    void submitGrievance(Vendor v, String type, String details) {
        String id = "GRV-" + grievanceCounter;
        grievanceCounter++;

        GrievanceReport g = new GrievanceReport(id, v, type, details);
        grievances.add(g);
    }

    // RESOLVE GRIEVANCE
    void resolveGrievance(KCCAOfficial officer, String grievanceID) {

        if (!officer.isAdmin()) {
            System.out.println("Access denied");
            return;
        }

        for (GrievanceReport g : grievances) {
            if (g.id.equals(grievanceID)) {
                g.status = "RESOLVED";
                System.out.println("Grievance resolved");
                return;
            }
        }

        System.out.println("Grievance not found");
    }

    // REPORT
    void generateReport() {

        int total = vendors.size();
        int active = 0;
        int occupied = 0;

        for (Vendor v : vendors) {
            if (v.status.equals("ACTIVE")) {
                active++;
            }
        }

        for (MarketSpace s : spaces) {
            if (s.occupied) {
                occupied++;
            }
        }

        System.out.println("Total Vendors: " + total);
        System.out.println("Active Vendors: " + active);
        System.out.println("Occupied Spaces: " + occupied);
    }

    // UNMATCHED VENDORS
    void printUnmatchedVendors() {
        for (Vendor v : vendors) {
            if (v.market == null) {
                System.out.println(v.id + " - " + v.name);
            }
        }
    }
}
