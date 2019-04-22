package Models;
import Models.Interfaces.IClientDetails;

public class ClientDetails implements IClientDetails
{
    public int userId;
    public String FirstName;
    public String LastName;
    public String UserName;
    //public String Password;
    public boolean HasLicense;
    public int TimestampLicenseExp;
    public int NumOfPurchase;

    @Override
    public void ToString() {
        System.out.println(
                "UserID: " + userId + "\t" +
                "First Name: " + FirstName + "\t" +
                "Last Name: " + LastName + "\t" +
                "UserName: " + UserName + "\t" +
                "LicendeExp: " + TimestampLicenseExp + "\t" +
                "Num Of Purchases: " + userId + "\n"
        );
    }
}
