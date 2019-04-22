package Views;

import Models.Interfaces.IMessageManager;
import Views.Interfaces.IGCMBoundry;

public class SystemGCM implements IGCMBoundry
{

    //region Fields

    private IMessageManager messageManager;

    //endregion

    //region C'tor

    void AppGCM()
    {
        // TODO: MessageManager = new ServerMessageManager();
    };

    //endregion

    //region Public Methods
    @Override
    public void StartSession()
    {
        //Todo: make real
    }

    @Override
    public void EndSession()
    {
        //Todo: make real
    }

    //endregion

    //region Private Methods


    //endregion
}
