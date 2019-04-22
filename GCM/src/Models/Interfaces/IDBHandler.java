package Models.Interfaces;

public interface IDBHandler
{
    //region Methods
    boolean HandleMassege(IMessage message);
    void Init();
    boolean IsConnectedToDB();
    IDBData GetResoultsFromDB();
    //endregion

    //region Properties

    //endregion

}
