package Models.Interfaces;

public interface IDBHandler
{
    //region Methods
    boolean HandleMassege(IMessage message);


    void Init();
    boolean IsConnectedToDB();
    IDBData GetResoultsFromDB();
    //endregion

    int GetNumOfPurchases(int p_userID);
    void IncreaseNumOfPurchases(int p_userID, int p_value);
    IClientDetails GetClientDetails(int p_userID);

}
