package Views;

import Models.Interfaces.IMessageManager;
import Views.Interfaces.IGCMBoundry;

public class AppGCM implements IGCMBoundry
{
    //region Fields

    private IMessageManager messageManager;

    //endregion

    //region C'tor

    void AppGCM()
    {
        // TODO: MessageManager = new ClientMessageManager();
    };

    //endregion

    //region Public Methods
    @Override
    public void StartSession() {
        System.out.println("Welcome to GCM");

        System.out.println("**********************************");

        System.out.println("Please enter costumer name to view purchases counter,\nor type 'exit' to leave");

        // Todo: continue

    }

    @Override
    public void EndSession()
    {
        //Todo: make real
    }

    //endregion

    //region Private Methods

    private int GetPurchasesNumber()
    {
        //Todo: Make real
        return 0;
    }

    private void IncPurchaseNomberByOne()
    {
        //Todo: Make real
    }

    private void WriteNewValueToDB()
    {
        //Todo: Make real

    }

    private void PrintCosumerDetails()
    {
        //Todo: Make real

    }

    //endregion

}
