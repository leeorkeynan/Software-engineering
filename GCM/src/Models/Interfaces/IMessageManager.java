package Models.Interfaces;

public interface IMessageManager
{
    //region Methods

    void Handle(IMessage message);
    IMessage CreateMessage();
    void Send(IMessage message);
    void Listen();
    boolean IsListening();

    //endregion

    //region Properties
    IDBHandler DbHandler = null;
    //endregion

}
