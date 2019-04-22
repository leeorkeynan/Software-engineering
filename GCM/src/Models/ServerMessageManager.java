package Models;

import Models.Interfaces.IMessage;
import Models.Interfaces.IMessageManager;

public class ServerMessageManager implements IMessageManager

{

    //region Fields

//    private EchoServer echoServer;
//    private ServerConsole server;

    //endregion

    //region Public Methods

    @Override
    public void Handle(IMessage message)
    {
        //Todo: Make real
    }

    @Override
    public IMessage CreateMessage()
    {
        //Todo: Make real
        return null;
    }

    @Override
    public void Send(IMessage message)
    {
        //Todo: Make real

    }

    @Override
    public void Listen()
    {
        //Todo: Make real
    }

    @Override
    public boolean IsListening()
    {
        //Todo: Make real
        return false;
    }

    //endregion

    //region Private Methods

    //endregion

}